import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';
import { User } from './../models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

	private serverPath = AppConst.serverPath;

  	constructor(private http: HttpClient) { }

  	newUser(user: User): Observable<any> {
	  	let url = this.serverPath+'/user/newUser';
	  	let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json'
	  	});

	  	return this.http.post(url, user, {headers : tokenHeader, responseType: 'text'});
  	}

  	 retrievePassword(email: string): Observable<any> {
	  	let url = this.serverPath+'/user/forgetPassword';
	  	let userInfo = {
	  		"email" : email
	  	}
	  	let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json'
	  	});

  		return this.http.post(url, userInfo, {headers : tokenHeader, responseType: 'text'});
  	}

  	updateUserInfo(user: User, newPassword: string): Observable<any> {
  		let url = this.serverPath+'/user/updateUserInfo';
  		let userInfo = {
  			"userId": user.userId,
  			"username": user.username,
  			"firstName": user.firstName,
  			"lastName": user.lastName,
  			"phone": user.phone,
  			"email": user.email,
  			"password": user.password,
  			"newPassword": newPassword
  		}

  		let tokenHeader = new HttpHeaders({
	  		'Content-Type' : 'application/json',
	  		'x-auth-token' : localStorage.getItem('xAuthToken')
	  	});

  		return this.http.post(url, userInfo, {headers : tokenHeader, responseType: 'text'});
  	}

  	getCurrentUser(username: string): Observable<any> {
  		let url = this.serverPath+'/user/getCurrentUser/'+username;
    
	    let tokenHeader = new HttpHeaders({
	      'Content-Type' : 'application/json',
	      'x-auth-token' : localStorage.getItem('xAuthToken'),

	    });

	    return this.http.get(url, {headers : tokenHeader});
  	}
}
