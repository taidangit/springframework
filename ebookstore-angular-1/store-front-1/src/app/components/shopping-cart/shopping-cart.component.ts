import { Component, OnInit } from '@angular/core';

import { ShoppingCartService} from './../../services/shopping-cart.service';
import { Book } from './../../models/book';
import { ShoppingCart } from './../../models/shopping-cart';
import { CartItem } from './../../models/cart-item';
import { AppConst } from './../../constants/app-const';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

	private serverPathImage = AppConst.serverPathImage;
	private cartItems: CartItem[] = [];
	private book: Book = new Book();
	private qty: number;
	private shoppingCart: ShoppingCart = new ShoppingCart();
	private cartItemUpdated: boolean = false;
	private emptyCart: boolean = false;
	private notEnoughStock: boolean = false;
  private addBookSuccess: boolean = false;
  
  private username: string = localStorage.getItem("authenticaterUser");

  	constructor(
  		private shoppingCartService: ShoppingCartService
  		) { }

 	  ngOnInit() {
 		  this.getShoppingCart();
      this.getCartItemList();
  	}

  	getCartItemList() {
  		this.shoppingCartService.getCartItemList(this.username).subscribe(
	        res => {
	          console.log(res);
	          this.cartItems = res;
            if(this.cartItems.length == 0){
              this.emptyCart = true;
            }
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
	        err => {
	          console.log(err);
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
  			error => {
  				console.log(error);
  			}
  		);
  	}

    onRemoveAllCartItem() {
      this.shoppingCartService.removeAllCartItem(this.username).subscribe(
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

  	onUpdateCartItem(cartItem: CartItem) {
  		this.shoppingCartService.updateCartItem(cartItem.cartItemId, cartItem.qty).subscribe(
  			res => {
  				console.log(res);
  				this.getCartItemList();
  				this.getShoppingCart();
          this.cartItemUpdated = true;
  			},
  			err => {
  				console.log(err);
  			}
  		);
  	}

  	// onCheckout() {
  	// 	if(this.cartItemSize == 0) {
  	// 		this.emptyCart = true;
  	// 	} else {
  	// 		for(let item of this.cartItems) {
  	// 			if(item.qty > item.book.inStockNumber) {
  	// 				this.notEnoughStock = true;
  	// 				break;
  	// 			}
  				

  	// 		}
  	// 	}
  	// }

}
