import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';
import { UserShipping } from './../models/user-shipping';

@Injectable({
  providedIn: 'root'
})
export class ShippingService {

	private serverPath = AppConst.serverPath;

  	constructor(private http: HttpClient) { }

  	newUserShipping(userShipping: UserShipping, username: string): Observable<any> {
  		let url = this.serverPath+"/shipping/add/"+username;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.post(url, userShipping, {headers : tokenHeader, responseType: 'text'});
  	}

  	getUserShippingList(username: string): Observable<any> {
  		let url = this.serverPath+"/shipping/list/"+username;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.get(url, {headers : tokenHeader});
  	}

  	removeUserShipping(userShippingId: number): Observable<any>  {
  		let url = this.serverPath+"/shipping/remove/"+userShippingId;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.delete(url, {headers : tokenHeader, responseType: 'text'});
  	}

  	setDefaultShipping(userShippingId: number, username: string): Observable<any>  {
  		let url = this.serverPath+"/shipping/setDefaultShipping/"+userShippingId+"/"+username;

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});
	  	return this.http.get(url, {headers : tokenHeader, responseType: 'text'});
  	} 
}
