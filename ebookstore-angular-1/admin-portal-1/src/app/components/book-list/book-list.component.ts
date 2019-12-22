import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator, MatTableDataSource } from '@angular/material';

import { GetBookListService } from './../../services/get-book-list.service';
import { RemoveBookService } from './../../services/remove-book.service';

import { Book } from './../../models/book';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})

export class BookListComponent implements OnInit {

	  //private selectedBook: Book;
	  private checked: boolean;
    private bookListRemove: Book[] = [];
	  private bookList = new MatTableDataSource<Book[]>();
	  private allChecked: boolean;
	  private removeBookList: Book[] = [];
  
    displayedColumns: string[] = 
      ['select', 'title', 'author', 'category', 'listPrice', 'ourPrice', 'active?', 'operation'];

    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  	
    constructor(
  		private getBookListService: GetBookListService,
      private removeBookService: RemoveBookService,
      private dialog: MatDialog) { }

  	ngOnInit() {
        this.getBookList();
        
  	}

    getBookList() {
      this.getBookListService.getBookList().subscribe(
        res => {
          console.log(res);
          this.bookListRemove = res;
          this.bookList = new MatTableDataSource<Book[]>(res);
          this.bookList.paginator = this.paginator;
        },
        err => {
          console.log(err);
        }
      );
   
    }

    selectBook(checked: boolean, book: Book) {
      if(checked) {
        this.removeBookList.push(book);
       // console.log(this.removeBookList);
      } else {
        this.removeBookList.splice(this.removeBookList.indexOf(book), 1);
        //console.log(this.removeBookList);
      }
    }

    selectAllBooks(checked: boolean) {
      if(checked) {
        this.allChecked = true;
        this.removeBookList = this.bookListRemove;
        //console.log(this.removeBookList);
      } else {
        this.allChecked = false;
        this.removeBookList = [];
      }
    }

    removeSelectedBooks() {
      const dialogRef = this.dialog.open(DialogOverviewExampleDialog);

        dialogRef.afterClosed().subscribe(result => {
            //console.log(result);
            if(result === "yes") {
              if(this.removeBookList.length > 0) {
                for(let book of this.removeBookList) {
                  this.removeBookService.removeBook(book.bookId).subscribe(
                     res => {
                       console.log(res);
                       this.getBookList();
                     },
                     err => {
                        console.log(err);
                     }
                  );
                }
              }
                
            } 
        });
    }

    openDialog(book: Book) {
        const dialogRef = this.dialog.open(DialogOverviewExampleDialog);

        dialogRef.afterClosed().subscribe(result => {
            //console.log(result);
            if(result === "yes") {
                this.removeBookService.removeBook(book.bookId).subscribe(
                   res => {
                     console.log(res);
                     this.getBookList();
                
                   },
                   err => {
                      console.log(err);
                   }
                );
            } 
        });
    }

}


@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
})
export class DialogOverviewExampleDialog {

  constructor(
      public dialogRef: MatDialogRef<DialogOverviewExampleDialog>) {}
}