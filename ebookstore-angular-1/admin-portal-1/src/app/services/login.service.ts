import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  	constructor(private http: HttpClient) { }

  	sendCredential(username: string, password: string): Observable<any>{
  		let url: string = "http://localhost:8083/api/token";
  		let encodeCredentials = btoa(username+";"+password);
  		let basicHeader = "Basic"+ encodeCredentials;
  		
  		let headers = new HttpHeaders({
  			'Content-Type': 'application/x-www-form-urlencoded',
  			'Authorization': basicHeader

  		});
  		return this.http.get(url, {headers: headers});
    }

  checkSession() {
  	let url: string = "http://localhost:8083/api/checkSession";
  	let headers = new HttpHeaders({
  			'x-auth-token': localStorage.getItem('xAuthToken')

  		});
  		return this.http.get(url, {headers: headers, responseType: 'text'});
  }

 logout() {
  	let url: string = "http://localhost:8083/api/logout";
  	let headers = new HttpHeaders({
  			'x-auth-token': localStorage.getItem('xAuthToken')

  		});
  		return this.http.get(url, {headers: headers, responseType: 'text'});
  }

}
