import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ShoppingCart } from './../../models/shopping-cart';
import { BillingAddress } from './../../models/billing-address';
import { ShippingAddress } from './../../models/shipping-address';
import { Payment } from './../../models/payment';

import { UserPayment } from './../../models/user-payment';
import { UserBilling } from './../../models/user-billing';
import { UserShipping } from './../../models/user-shipping';

import { CartItem } from './../../models/cart-item';
import { AppConst } from './../../constants/app-const';

import { ShoppingCartService } from './../../services/shopping-cart.service';
import { OrderService } from './../../services/order.service';
import { ShippingService } from './../../services/shipping.service';
import { PaymentService } from './../../services/payment.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

	private shoppingCart: ShoppingCart = new ShoppingCart();
	private billingAddress: BillingAddress = new BillingAddress();
	private payment: Payment = new Payment();
	private shippingAddress: ShippingAddress = new ShippingAddress();
	private userPayment: UserPayment = new UserPayment();
	private userBilling: UserBilling = new UserBilling();
	
	private userShippings: UserShipping[] = [];
	private userPayments: UserPayment[] = [];
	private cartItems: any[] = [];

	private states: string[] = [];
	private shippingMethod: string = "groundShipping";

	private serverPathImage = AppConst.serverPathImage;

  	private emptyShippingList: boolean = false;
  	private emptyPaymentList: boolean = false;

  	private username: string = localStorage.getItem("authenticaterUser");

  	constructor(
  		private paymentService: PaymentService,
  		private orderService: OrderService,
  		private shoppingCartService: ShoppingCartService,
  		private shippingService: ShippingService,
  		private router: Router
  	) { }

  	ngOnInit() {
  		this.getCartItemList();
  		this.getUserPaymentList();
  		this.getUserShippingList();
  		this.getShoppingCart();
  		this.getStateList();
  		
  	}

  	getStateList() {
  		for(let state in AppConst.usStates) {
        	this.states.push(state);
      }
  	}

  	getCartItemList() {
  		this.shoppingCartService.getCartItemList(this.username).subscribe(
	        res => {
	          console.log(res);
	          this.cartItems = res;
	        },
	        err => {
	          	console.log(err);
	          	
	        }
      );
  	}

  	getShoppingCart() {
  		this.shoppingCartService.getShoppingCart(this.username).subscribe(
	        res => {
	          console.log(res);
	          this.shoppingCart = res;
	        },
	        error => {
	          console.log(error);
	        }
      );
  	}

  	onRemoveCartItem(cartItemId: number) {
  		this.shoppingCartService.removeCartItem(cartItemId).subscribe(
  			res => {
  				console.log(res);
  				this.getCartItemList();
  				this.getShoppingCart();
  			},
  			err => {
  				console.log(err);
  			}
  		);
  	}

  	getUserShippingList() {
  		this.shippingService.getUserShippingList(this.username).subscribe(
  			res => {
  				console.log(res);
  				this.userShippings = res;
  				if(this.userShippings.length == 0) {
  					this.emptyShippingList = true;
  				}

          if(this.userShippings.length > 0) {
            for(let userShipping of this.userShippings) {
              if(userShipping.shippingDefault) {
                this.onSetShippingAddress(userShipping);
                break;
              }
            }
          }
  				
  			}, 
  			err => {
  				console.log(err);
  			}
  		);
  	}

  	getUserPaymentList() {
  		this.paymentService.getUserPaymentList(this.username).subscribe(
  			res => {
  				console.log(res);
  				this.userPayments = res;
  				if(this.userPayments.length == 0) {
  					this.emptyPaymentList = true;;
  				}

          if(this.userPayments.length >  0) {
            for(let userPayment of this.userPayments) {
              if(userPayment.defaultPayment) {
                this.onSetPaymentMethod(userPayment);
                break;
              }
            }
          }

  				
  			}, 
  			error => {
  				console.log(error);
  			}
  		);
  	}

  	onSetShippingAddress(userShipping: UserShipping) {

  		this.shippingAddress.shippingAddressCity = userShipping.city;
  		this.shippingAddress.shippingAddressCountry = userShipping.country;
  		this.shippingAddress.shippingAddressName = userShipping.name;
  		this.shippingAddress.shippingAddressState = userShipping.state;
  		this.shippingAddress.shippingAddressStreet = userShipping.street;
  		this.shippingAddress.shippingAddressZipcode = userShipping.zipCode;
  	}

  	onSetPaymentMethod(userPayment: UserPayment) {
  		this.payment.cardNumber = userPayment.cardNumber;
  		this.payment.cvc = userPayment.cvc;
  		this.payment.expiryMonth = userPayment.expiryMonth;
  		this.payment.expiryYear = userPayment.expiryYear;
  		this.payment.holderName = userPayment.holderName;
  		this.payment.type = userPayment.type;
  		this.payment.cardName = userPayment.cardName;

  		this.billingAddress.billingAddressCity = userPayment.userBilling.city;
  		this.billingAddress.billingAddressCountry = userPayment.userBilling.country;
  		this.billingAddress.billingAddressName = userPayment.userBilling.name;
  		this.billingAddress.billingAddressState = userPayment.userBilling.state;
  		this.billingAddress.billingAddressStreet = userPayment.userBilling.street;
  		this.billingAddress.billingAddressZipcode = userPayment.userBilling.zipCode;
		
  	}

  	onSetBillingSameAsShipping(checked: boolean) {
  		if(checked) {
  			this.billingAddress.billingAddressCity = this.shippingAddress.shippingAddressCity
  			this.billingAddress.billingAddressCountry = this.shippingAddress.shippingAddressCountry
  			this.billingAddress.billingAddressName = this.shippingAddress.shippingAddressName;
  			this.billingAddress.billingAddressState = this.shippingAddress.shippingAddressState
  			this.billingAddress.billingAddressStreet = this.shippingAddress.shippingAddressStreet
  			this.billingAddress.billingAddressZipcode = this.shippingAddress.shippingAddressZipcode
  		}
  	}

  	onOrder() {
  		this.orderService.order(
  			this.shippingAddress,
  			this.billingAddress,
  			this.payment,
  			this.shippingMethod,
  			this.username
  		).subscribe(
  			res => {
  				console.log(res);
  				this.router.navigate(['/orderSummary'], {
  					queryParams: {
  						"order": JSON.stringify(res)
  					}
				  });
  			},	
  			err => {
  				console.log(err);
  			}
  		);
  	}

}
