import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './../../services/login.service';
import { BookService } from './../../services/book.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

	  private loggedIn: boolean = false;
    private keyword: string = "";

  	constructor(
  		private loginService: LoginService,
      private bookService: BookService,
  		private router: Router) { }

  	ngOnInit() {
     
	  	this.loginService.checkSession().subscribe(
  			res => {
  				console.log(res);
  				this.loggedIn = true;
  			},
  			err => {
  				console.log(err);
  				this.loggedIn = false;
  			}
  		);
  	}

    onSearchByTitle() {
      console.log(this.keyword);
      if(this.keyword.trim().length > 0) {
          this.bookService.searchBook(this.keyword.trim()).subscribe(
          res => {
            console.log(res);
            this.router.navigate(['/bookList'], {
              queryParams: {
                "books": JSON.stringify(res)
              }
            });
          },  
          err => {
            console.log(err);
          }
        );
 
      } else {

        this.bookService.getBookList().subscribe(
          res => {
            console.log(res);
            this.router.navigate(['/bookList'], {
              queryParams: {
                "books": JSON.stringify(res)
              }
            });
          },  
          err => {
            console.log(err);
          }
        );
      }
    }

  	logout() {
      this.loginService.logout().subscribe(
        res => {
          console.log(res);
          localStorage.removeItem("xAuthToken");
          localStorage.removeItem("authenticaterUser");

          location.reload();
        },
        error => {
          console.log(error);
          
        }
      );

      this.router.navigate(['/myAccount']);
    }

}
