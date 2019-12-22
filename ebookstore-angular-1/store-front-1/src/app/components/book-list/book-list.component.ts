import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { BookService } from './../../services/book.service';
import { Book } from './../../models/book';
import { AppConst } from './../../constants/app-const';

import * as $ from 'jquery';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  private filterQuery: string = "";
  private rowsOnPage: number = 5;

  private books: Book[] = [];
  private serverPathImage = AppConst.serverPathImage;
  private classActiveAll: boolean = true;
  private emptyList: boolean = false;

  private subscriptionQueryParams: Subscription;

  constructor(
      private bookService: BookService,
      private activatedRoute: ActivatedRoute, 
      private router: Router
    ) { }

  ngOnInit() {
       
       this.subscriptionQueryParams = this.activatedRoute.queryParams.subscribe(
        (params: Params) => {
          if(params['books']) {
            this.books = JSON.parse(params['books']);
            console.log(this.books);
          } else {
             this.getBookList();
          }
        });
  }

  getBookList() {
      this.bookService.getBookList().subscribe(
        res => {
          console.log(res);
          this.books = res;
        },
        err => {
          console.log(err);
        }
      );
   
  }

  onSearchByCategory(category: string) {
    $('.list-group-item').removeClass('active');
    $('#'+category).addClass('active');

    this.bookService.searchBookByCategory(category).subscribe(
      res => {
        console.log(res);
        this.books = res;
        if(this.books.length == 0) {
          this.emptyList = true;
        }
      },
      err => {
        console.log(err);
      }
    );
  }

  onBookList() {
    $('.list-group-item').removeClass('active');
    $('#allBook').addClass('active');
    this.getBookList();
  }

  setClasses(category) {
    return {
      'active': this.classActiveAll
    };
  }

  ngOnDestroy(){
    this.subscriptionQueryParams.unsubscribe();
  }

  
}
