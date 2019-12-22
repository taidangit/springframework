import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';
import { UserPayment } from './../models/user-payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

 	  private serverPath = AppConst.serverPath;

  	constructor(private http: HttpClient) { }

  	newUserPayment(userPayment: UserPayment, username: string): Observable<any> {
  		let url = this.serverPath+"/payment/add/"+username;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.post(url, userPayment, {headers : tokenHeader, responseType: 'text'});
  	}

  	getUserPaymentList(username: string): Observable<any> {
  		let url = this.serverPath+"/payment/list/"+username;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.get(url, {headers : tokenHeader});
  	}

  	removeUserPayment(userPaymentId: number): Observable<any>  {
  		let url = this.serverPath+"/payment/remove/"+userPaymentId;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.delete(url, {headers : tokenHeader, responseType: 'text'});
  	}

  	setDefaultPayment(userPaymentId: number, username: string): Observable<any>  {
  		let url = this.serverPath+"/payment/setDefaultPayment/"+userPaymentId+"/"+username;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.get(url, {headers : tokenHeader, responseType: 'text'});
  	} 
}
