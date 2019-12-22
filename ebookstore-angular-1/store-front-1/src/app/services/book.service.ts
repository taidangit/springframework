import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { AppConst } from './../constants/app-const';
import { Book } from './../models/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

	private serverPath = AppConst.serverPath;

  	constructor(private http: HttpClient) { }

  	getBookList(): Observable<any> {
		let url: string = this.serverPath+"/book/list";
	  	let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

	  		});
	  	return this.http.get(url, {headers: headers});
	}

	getBook(bookId: number): Observable<any> {
	  	let url: string = this.serverPath+"/book/view/"+bookId;
		  let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

		});
		return this.http.get(url, {headers: headers});
  	}

  	searchBook(keyword: string): Observable<any> {
  		let url: string = this.serverPath+"/book/searchBook";
		let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

		});
		return this.http.post(url, keyword, {headers: headers});
  	}

  	searchBookByCategory(category: string): Observable<any> {
  		let url: string = this.serverPath+"/book/searchBookByCategory"
		  let headers = new HttpHeaders({
	  		'Content-Type': 'application/json',
	  		'x-auth-token': localStorage.getItem('xAuthToken')

		});
		return this.http.post(url, category, {headers: headers});
  	}
}
