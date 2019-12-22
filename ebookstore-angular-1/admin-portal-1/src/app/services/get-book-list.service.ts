import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Book } from './../models/book';

@Injectable({
  providedIn: 'root'
})
export class GetBookListService {

  constructor(private http: HttpClient) { }

  getBookList(): Observable<any> {
		let url: string = "http://localhost:8083/api/book/list";
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.get(url, {headers: headers});
	}
}
