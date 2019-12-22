import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './../../services/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

	  private loggedIn: boolean = false;

  	constructor(
      private loginService: LoginService, 
      private router: Router) { }

  	ngOnInit() {
      this.loginService.checkSession().subscribe(
        res => {
          //console.log(res);
          this.loggedIn = true;
        },
        err => {
          //console.log(err);
          this.loggedIn = false;
        }
      );
  	}

    logout() {
      this.loginService.logout().subscribe(
        res => {
          console.log(res);
          localStorage.removeItem("xAuthToken");
          location.reload();
        },
        err => {
          console.log(err);
          
        }
      );

      this.router.navigate(['/']);
    }


}
