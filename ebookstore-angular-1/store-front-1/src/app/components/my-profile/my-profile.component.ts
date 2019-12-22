import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

import { AppConst } from './../../constants/app-const';
import { UserService } from './../../services/user.service';
import { LoginService } from './../../services/login.service';
import { PaymentService } from './../../services/payment.service';
import { ShippingService } from './../../services/shipping.service';
import { OrderService } from './../../services/order.service';

import { User } from './../../models/user';
import { UserPayment } from './../../models/user-payment';
import { UserBilling } from './../../models/user-billing';
import { UserShipping } from './../../models/user-shipping';
import { Order } from './../../models/order';
import { CartItem } from './../../models/cart-item';

import { Zvalidators } from './../../validators/zvalidators';

import * as $ from 'jquery';

@Component({
	  encapsulation: ViewEncapsulation.None,
  	selector: 'app-my-profile',
  	templateUrl: './my-profile.component.html',
  	styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  	private dataFetched: boolean = false;

  	private user: User = new User();
  	private updateUserInfoSuccess: boolean =false;
  	private newPassword: string;
    private confirmPassword: string;
  	private incorrectPassword: boolean = false;
  	private usernameExists: boolean = false;
  	private emailExists: boolean = false;

  	private username: string = localStorage.getItem("authenticaterUser");

    private selectedProfileTab: number = 0;
    private selectedBillingTab: number = 0;
     private selectedShippingTab: number = 0;

    private userPayment: UserPayment = new UserPayment();
    private userBilling: UserBilling = new UserBilling();
    private userPayments: UserPayment[] = [];
    private defaultPaymentSet: boolean  = false;
    private states: string[] = [];
    private userShipping: UserShipping = new UserShipping();
    private userShippings: UserShipping[] = [];
    private defaultShippingSet: boolean = false;

    private orders: Order[] = [];
    private displayOrderDetail: boolean = false;
    private cartItems: CartItem[] = [];
    private order: Order = new Order();

    // private formProfile: FormGroup;
    // private formPayment: FormGroup;
    // private formShipping: FormGroup;

  	constructor(
  		private userService: UserService,
      private paymentService: PaymentService,
      private shippingService: ShippingService,
      private orderService: OrderService,
      private formBuilder: FormBuilder
  		) { }

  	ngOnInit() {
  	
      this.getCurrentUser();
      this.getUserPayments();
      this.getUserShippings();
      this.getOrders();

      for(let state in AppConst.usStates) {
        this.states.push(state);
      }

      this.userBilling.state = "AL";
      this.userShipping.state = "AL";
      this.userPayment.type = "visa";
      this.userPayment.expiryMonth = 1;
      this.userPayment.expiryYear = 2017;
      this.userPayment.userBilling = this.userBilling;

      /*this.formProfile = this.formBuilder.group({
        username  : ['', [ Validators.required, Validators.minLength(5) ]],
        firstName  : ['', [ Validators.required ]],
        lastName  : ['', [ Validators.required ]],
        phone  : ['', [ Validators.required ]],
        email : ['', [ Validators.required, Zvalidators.emailValidator ]],
        password : ['', [ Validators.required ]],
        newPassword : ['', [ Validators.required ]],
        confirmPassword : ['', [ Validators.required ]],
      }, { validator: Zvalidators.matchingPasswords('newPassword', 'confirmPassword') });

     
       this.formPayment = this.formBuilder.group({
          billingName  : ['', [ Validators.required ]],
          billingStreet  : ['', [ Validators.required ]],
          billingCountry  : ['', [ Validators.required ]],
          billingCity  : ['', [ Validators.required ]],
          billingZipCode : ['', [ Validators.required ]],

          cardName : ['', [ Validators.required ]],
          cardHolder : ['', [ Validators.required ]],
          cardNumber : ['', [ Validators.required ]],
          cardCVC : ['', [ Validators.required ]]

      });*/

        /*this.formShipping = this.formBuilder.group({
          shippingName  : ['', [ Validators.required ]],
          shippingStreet  : ['', [ Validators.required ]],
          shippingCountry  : ['', [ Validators.required ]],
          shippingCity  : ['', [ Validators.required ]],
          shippingZipCode : ['', [ Validators.required ]]
      });*/

     /*  this.formProfile = new FormGroup({
          username: new FormControl('', [ Validators.required, Validators.minLength(5) ] ),
          firstName: new FormControl('', Validators.required ),
          lastName: new FormControl('', Validators.required ),
          phone: new FormControl('', Validators.required ),
          email: new FormControl('', Validators.required ),
          password: new FormControl('', Validators.required ),
          newPassword: new FormControl('', Validators.required ),
          confirmPassword: new FormControl('', Validators.required )
        });

        this.formPayment = new FormGroup({
          billingName: new FormControl('', Validators.required ),
          billingStreet: new FormControl('', Validators.required ),
          billingCountry: new FormControl('', Validators.required ),
          billingCity: new FormControl('', Validators.required ),
          billingZipCode: new FormControl('', Validators.required ),

           cardName: new FormControl('', Validators.required ),
           cardHolder: new FormControl('', Validators.required ),
           cardNumber: new FormControl('', Validators.required ),
           cardCVC: new FormControl('', Validators.required ),
        });

        this.formShipping = new FormGroup({
          shippingName: new FormControl('', Validators.required ),
          shippingStreet: new FormControl('', Validators.required ),
          shippingCountry: new FormControl('', Validators.required ),
          shippingCity: new FormControl('', Validators.required ),
          shippingZipCode: new FormControl('', Validators.required )
        });
*/
        
  	}

    getUserPayments() {
      this.paymentService.getUserPaymentList(this.username).subscribe(
          res => {
            console.log(res);
            this.userPayments = res;
          },
          err => {
            console.log(err);
          }
      );
    }

    getUserShippings() {
      this.shippingService.getUserShippingList(this.username).subscribe(
          res => {
            console.log(res);
            this.userShippings = res;
          },
          error => {
            console.log(error);
          }
      );
    }

    getOrders() {
      this.orderService.getOrderList(this.username).subscribe(
          res => {
            console.log(res);
            this.orders = res;
          },
          err => {
            console.log(err);
          }
      );
    }

    onDisplayOrder(order: Order) {
      this.order = order;
      console.log(order);
      this.displayOrderDetail = true;
    }

    onNewUserPayment() {
      console.log(this.userPayment);
      this.paymentService.newUserPayment(this.userPayment, this.username).subscribe(
          res => {
            console.log(res);
            this. getUserPayments();
            this.selectedBillingTab = 0;
           
          },
          error => {
            console.log(error);
          }
      );

    }

    onNewUserShipping() {
      this.shippingService.newUserShipping(this.userShipping, this.username).subscribe(
          res => {
            console.log(res);
            this. getUserShippings();
            this.selectedShippingTab = 0;
            this.userShipping = new UserShipping();
            this.userShipping.state = "AL";
          },
          err => {
            console.log(err);
          }
      );

    }

    selectedBillingChange(val: number) {
      //console.log(val);
      this.selectedBillingTab = val;
    }

    selectedShippingChange(val: number) {
      this.selectedShippingTab = val;
    }

    onUpdateUserPayment(userPayment: UserPayment) {
        //console.log(userPayment);
        this.userPayment = userPayment;
        this.userBilling = userPayment.userBilling;
        this.selectedBillingTab = 1;
    }

    onUpdateUserShipping(userShipping: UserShipping) {
        this.userShipping = userShipping;
        this.selectedShippingTab = 1;
    }

    onRemoveUserPayment(userPaymentId: number) {
       this.paymentService.removeUserPayment(userPaymentId).subscribe(
          res => {
              console.log(res);
              this. getUserPayments();
          },
          err => {
              console.log(err);
          }
      );
    }

    onRemoveUserShipping(userShippingId: number) {
       this.shippingService.removeUserShipping(userShippingId).subscribe(
          res => {
              console.log(res);
              this. getUserShippings();
          },
          err => {
              console.log(err);
          }
      );
    }

    onSetDefaultPayment(userPaymentId: number) {
      this.paymentService.setDefaultPayment(userPaymentId, this.username).subscribe(
          res => {
              console.log(res);
              this. getUserPayments();
              this.defaultPaymentSet = true;
          },
          err => {
            console.log(err);
          }
      );
    }

    onSetDefaultShipping(userShippingId: number) {
      this.shippingService.setDefaultShipping(userShippingId, this.username).subscribe(
          res => {
              console.log(res);
              this. getUserShippings();
              this.defaultShippingSet = true;
          },
          error => {
            console.log(error);
          }
      );
    }

  	onUpdateUserInfo() {
      this.updateUserInfoSuccess = false;
      this.incorrectPassword = false;
      this.usernameExists = false;
      this.emailExists = false;

  		this.userService.updateUserInfo(this.user, this.newPassword).subscribe(
  			res => {
  				console.log(res);
  				this.updateUserInfoSuccess = true;
  				
  			},
  			err => {
  				console.log(err);
  				if(err.error === "incorrectPassword") {
  					this.incorrectPassword = true;
  				}
  				if(err.error === "usernameExists") {
  					this.usernameExists = true;
  				}
  				if(err.error === "emailExists") {
  					this.emailExists = true;
  				}
  				
  			}
  		);
  	}

    getCurrentUser() {
     
      this.userService.getCurrentUser(this.username).subscribe(
        res => {
          console.log(res);
          this.user = res;
          this.dataFetched = true;
        },
        err => {
          console.log(err);
        }
      );
    }

    onNewPassword() {
      if ($('#txtNewPassword').val() === $('#txtConfirmPassword').val()) {
        $('#resultPass').html('Password match').addClass("badge badge-success");
       
      } else { 
        $('#resultPass').html('Password do not match!').addClass("badge badge-danger");
      }
    }

    onConfirmPassword() {
      if ($('#txtNewPassword').val() === $('#txtConfirmPassword').val()) {
        $('#resultPass').html('Password match').addClass("badge badge-success");
      } else {   
        $('#resultPass').html('Password do not match!').addClass("badge badge-danger");
      }
    }
}
