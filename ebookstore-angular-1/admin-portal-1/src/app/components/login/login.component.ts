import { Component, OnInit } from '@angular/core';

import { LoginService } from './../../services/login.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	private credential: any = {'username': 'taidang', 'password': '123456'};
	private loggedIn: boolean = false;

  	constructor(private loginService: LoginService) { }

  	ngOnInit() {
      //console.log("Init");
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

  	onSubmit() {
  		this.loginService.sendCredential(this.credential.username, this.credential.password)
  		.subscribe(
  			res => {
  				console.log(res);
  				localStorage.setItem("xAuthToken", res.token);
  				location.reload();

  			},
  			error => {
  				console.log(error);
  			}
  		);
  	}
}
