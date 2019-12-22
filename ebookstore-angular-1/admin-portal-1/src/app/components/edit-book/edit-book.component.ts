import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { UploadImageService} from './../../services/upload-image.service';
import { GetBookService} from './../../services/get-book.service';
import { EditBookService} from './../../services/edit-book.service';
import { Book } from './../../models/book';


@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

	private bookId: number;
	private book: Book = new Book();
	private bookUpdated: boolean;

	private subscriptionParams: Subscription;

  	constructor(
  		private getBookService: GetBookService,
  		private uploadImageService: UploadImageService,
  		private editBookService: EditBookService,
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

  	onSubmit() {
  		this.editBookService.editBook(this.book).subscribe(
  			res => {
  				console.log(res);
  				this.uploadImageService.modify(res.bookId);
  				this.bookUpdated = true;
  			}, 
  			err => {
  				console.log(err);
  			}
  		);
  	}

  	ngOnDestroy(){
		  this.subscriptionParams.unsubscribe();
	  }

}
