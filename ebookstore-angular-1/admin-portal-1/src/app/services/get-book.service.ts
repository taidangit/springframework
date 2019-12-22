import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetBookService {

  constructor(private http: HttpClient) { }

  getBook(bookId: number): Observable<any> {
  	let url: string = "http://localhost:8083/api/book/view/"+bookId;
	  let headers = new HttpHeaders({
  		'Content-Type': 'application/json',
  		'x-auth-token': localStorage.getItem('xAuthToken')

	});
	return this.http.get(url, {headers: headers});
  }
}
