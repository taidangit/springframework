import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';
import { Order } from './../models/order';
import { ShippingAddress } from './../models/shipping-address';
import { BillingAddress } from './../models/billing-address';
import { Payment } from './../models/payment';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

	private serverPath = AppConst.serverPath;

  	constructor(private http: HttpClient) { }

  	order(
	  	shippingAddress: ShippingAddress,
	  	billingAddress: BillingAddress,
	  	payment: Payment,
	  	shippingMethod: string,
	    username: string
	  ): Observable<any> {
	  		let order = {
	  			"shippingAddress": shippingAddress,
	  			"billingAddress": billingAddress,
	  			"payment": payment,
	  			"shippingMethod": shippingMethod
	  		}

	  		let url: string = this.serverPath+"/order/"+username;
		  	let headers = new HttpHeaders({
		  		'Content-Type': 'application/json',
		  		'x-auth-token': localStorage.getItem('xAuthToken')

		  		});
		  	return this.http.post(url, order,  {headers: headers});
	  }

  	getOrderList(username: string): Observable<any> {
  		let url: string = this.serverPath+"/order/list/"+username;
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.get(url, {headers: headers});
  	}
}
