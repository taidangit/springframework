import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { GetBookService} from './../../services/get-book.service';
import { Book } from './../../models/book';

@Component({
  selector: 'app-view-book',
  templateUrl: './view-book.component.html',
  styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements OnInit {

	private book: Book = new Book();
	private bookId: number;
	private subscriptionParams: Subscription;

  	constructor(
  		private getBookService: GetBookService,
  		private activatedRoute: ActivatedRoute, 
  		private router: Router
  		) { }

 	ngOnInit() {
 		
 		this.subscriptionParams = this.activatedRoute.params.subscribe(
			(params: Params) => {
				
				this.getBookService.getBook(params['id']).subscribe(
					res => {
						//console.log(res);
						this.book = res;
					},
					err => {
						console.log(err);
					}
				);
			}
		);
  	}

  	ngOnDestroy(){
		this.subscriptionParams.unsubscribe();
	}

}
