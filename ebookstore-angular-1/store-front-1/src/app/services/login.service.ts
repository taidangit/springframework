import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

	  private serverPath = AppConst.serverPath;

  	constructor(private http: HttpClient) { }

  	sendCredential(username: string, password: string): Observable<any>{
  		let url: string = this.serverPath+'/token';
  		let encodeCredentials = btoa(username+";"+password);
  		let basicHeader = "Basic"+ encodeCredentials;
  		
  		let headers = new HttpHeaders({
  			'Content-Type': 'application/x-www-form-urlencoded',
  			'Authorization': basicHeader

  		});
  		return this.http.get(url, {headers: headers});
    }

    checkSession(): Observable<any> {
	  	let url: string = this.serverPath+'/checkSession';
	  	let headers = new HttpHeaders({
	  			'x-auth-token': localStorage.getItem('xAuthToken')
	  	});
  		return this.http.get(url, {headers: headers, responseType: 'text'});
  }

 	  logout(): Observable<any> {
  		let url: string = this.serverPath+'/logout';
  		let headers = new HttpHeaders({
  			'x-auth-token': localStorage.getItem('xAuthToken')

  		});
  		return this.http.get(url, {headers: headers, responseType: 'text'});
    }

}
