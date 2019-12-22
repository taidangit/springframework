import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';
import { ShoppingCart } from './../models/shopping-cart';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
	private serverPath = AppConst.serverPath;

  	constructor(private http: HttpClient) { }

  	addItem(bookId: number, qty: number, username: string): Observable<any> {
  		let url: string = this.serverPath+"/shoppingCart/addItem/"+bookId+"/"+username;
  		let cartItemInfo = {
  			"qty": qty 
  		}
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.post(url, cartItemInfo, {headers: headers, responseType: 'text'});
  	}

  	getCartItemList(username: string): Observable<any> {
  		let url: string = this.serverPath+"/shoppingCart/getCartItems/"+username;
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.get(url, {headers: headers});
  	}

  	getShoppingCart(username: string): Observable<any> {
  		let url: string = this.serverPath+"/shoppingCart/cart/"+username;
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.get(url, {headers: headers});
  	}

  	updateCartItem(cartItemId: number, qty: number): Observable<any> {
  		let url: string = this.serverPath+"/shoppingCart/updateCartItem/"+cartItemId;
  		let cartItemInfo = {
  			"qty": qty 
  		}
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.post(url, cartItemInfo, {headers: headers, responseType: 'text'});
  	}

  	removeCartItem(cartItemId: number): Observable<any> {
  		let url = this.serverPath+"/shoppingCart/removeItem/"+cartItemId;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.delete(url, {headers : tokenHeader, responseType: 'text'});
  	}

  	removeAllCartItem(username: string): Observable<any> {
  		let url = this.serverPath+"/shoppingCart/removeAllItem/"+username;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.get(url, {headers : tokenHeader, responseType: 'text'});
  	}
}
