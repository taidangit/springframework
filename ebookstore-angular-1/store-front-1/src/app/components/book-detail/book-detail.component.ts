
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { BookService } from './../../services/book.service';
import { ShoppingCartService } from './../../services/shopping-cart.service';
import { Book } from './../../models/book';
import { AppConst } from './../../constants/app-const';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit, OnDestroy {

	private serverPathImage = AppConst.serverPathImage;
	private book: Book = new Book();
	private bookId: number;
	private subscriptionParams: Subscription;
	private qtyList: number[] = [1,2,3,4,5,6,7,8,9,10];
	private qty: number = 1;
	private username: string = localStorage.getItem("authenticaterUser");
	private addBookSuccess: boolean = false;
	private notEnoughStock: boolean = false;

  	constructor(
  		private bookService: BookService,
  		private shoppingCartService: ShoppingCartService,
  		private activatedRoute: ActivatedRoute, 
  		private router: Router
  	) { }

  	ngOnInit() {
	  	this.subscriptionParams = this.activatedRoute.params.subscribe(
	        (params: Params) => {
	          
	          this.bookService.getBook(params['id']).subscribe(
	            res => {
	             
	              this.book = res;
	              console.log(this.book);
	            },
	            err => {
	              console.log(err);
	            }
	          );
	        }
	      );
 	 }

 	 onAddToCart(bookId: number) {
 	 	this.addBookSuccess = false; 
        this.notEnoughStock = false;

      this.shoppingCartService.addItem(bookId, this.qty, this.username).subscribe(
        res => {
          console.log(res);
          this.addBookSuccess = true;
        },
        err => {
          console.log(err);
          if(err.error === "notEnoughStock") {
             this.notEnoughStock = true;
          } 
        }
      );
    }

  	ngOnDestroy(){
		this.subscriptionParams.unsubscribe();
	}

}
