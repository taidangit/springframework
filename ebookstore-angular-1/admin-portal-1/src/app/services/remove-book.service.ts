import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Book } from './../models/book';

@Injectable({
  providedIn: 'root'
})
export class RemoveBookService {

  constructor(private http: HttpClient) { }

  removeBook(bookId: number): Observable<any> {
  	let url: string = "http://localhost:8083/api/book/remove/"+bookId;
  	let headers = new HttpHeaders({
  		'Content-Type': 'application/json',
  		'x-auth-token': localStorage.getItem('xAuthToken')

  		});
  	return this.http.delete(url, {headers: headers, responseType: 'text'});
  }
}
