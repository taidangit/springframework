import { Component, OnInit } from '@angular/core';

import { Book } from './../../models/book';
import { AddBookService } from './../../services/add-book.service';
import { UploadImageService } from './../../services/upload-image.service';


@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

	private newBook: Book = new Book();
	private bookAdded: boolean = false;

  	constructor(
  		private addBookService: AddBookService,
  		private uploadImageService: UploadImageService
  		) { }

  	onSubmit() {
  		this.addBookService.addBook(this.newBook).subscribe(
  			res => {
  				console.log(res);
  				this.uploadImageService.upload(res.bookId);
  				this.bookAdded = true;
  				
  			},
  			err => {
  				console.log(err);
  				
  			}
  		);
  	}

  	ngOnInit() {
  		this.newBook.active = true;
  		this.newBook.category = "Management";
  		this.newBook.language = "english";
  		this.newBook.format = "paperback";
  	}

}
