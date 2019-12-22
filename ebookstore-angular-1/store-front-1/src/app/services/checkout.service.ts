import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';
import { ShippingAddress } from './../models/shipping-address';
import { BillingAddress } from './../models/billing-address';
import { Payment } from './../models/payment';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  private serverPath = AppConst.serverPath;

  constructor(private http: HttpClient) { }


  getUserOrder(): Observable<any> {
  	let url: string = this.serverPath+"/checkout/getUserOrder";
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.get(url);
  }
}
