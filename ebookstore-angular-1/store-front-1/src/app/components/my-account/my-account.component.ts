import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { AppConst } from './../../constants/app-const';
import { User } from './../../models/user';

import { Zvalidators } from './../../validators/zvalidators';

import * as $ from 'jquery';

@Component({
	  encapsulation: ViewEncapsulation.None,
  	selector: 'app-my-account',
  	templateUrl: './my-account.component.html',
  	styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  	private loginError: boolean = false;
  	private credential = {'username':'taidang', 'password':'123456'};

  	private emailSent: boolean = false;
  	private usernameExists: boolean = false;
  	private emailExists: boolean = false;

  	private newUser: User = new User();

  	private emailNotExists: boolean = false ;
  	private forgetPasswordEmailSent: boolean = false;
  	private recoverEmail: string;  

    private formNewAccount: FormGroup;
    private formLogin: FormGroup;
    private formForgetPassword: FormGroup;


  	constructor(
  		private loginService: LoginService,
  		private userService: UserService,
  		private router: Router,
      private formBuilder: FormBuilder

  		) { }

  	ngOnInit() {

      this.formNewAccount = this.formBuilder.group({
        username  : ['', [ Validators.required, Validators.minLength(5) ]],
        firstName  : ['', [ Validators.required ]],
        lastName  : ['', [ Validators.required ]],
        phone  : ['', [ Validators.required ]],
        email : ['', [ Validators.required, Zvalidators.emailValidator ]]
      });

  
      this.formLogin = this.formBuilder.group({
        username  : ['', [ Validators.required ]],
        password  : ['', [ Validators.required ]]
      });

      this.formForgetPassword = this.formBuilder.group({
        email : ['', [ Validators.required, Zvalidators.emailValidator ]]
      });
  	}

    onResetForm() {
      this.formNewAccount.reset();
    }

  	onLogin() {
  		this.loginService.sendCredential(this.credential.username, this.credential.password)
  		.subscribe(
  			res => {
  				console.log(res);
  				localStorage.setItem("xAuthToken", res.token);
          localStorage.setItem("authenticaterUser", this.credential.username);
  				location.reload();
  			},
  			err => {
  				console.log(err);
  				
  			}
  		);
  		this.router.navigate(['/']);

      
  	}
  	
  	onNewAccount() {
      this.emailSent = false;
      this.usernameExists = false;
      this.emailExists = false;

  		this.userService.newUser(this.newUser).subscribe(
  			res => {
  				console.log(res);
  				this.emailSent = true;
  			},
  			err => {
  				console.log(err);

  				if(err.error === "usernameExists") {
  					this.usernameExists = true;
  				}
  				if(err.error === "emailExists") {
  					this.emailExists = true;
  				}
  			}
  		);
  	}

  	onForgetPassword() {
  		this.forgetPasswordEmailSent = false;
  		this.emailNotExists = false;

  		this.userService.retrievePassword(this.recoverEmail).subscribe(
	  		res => {
	  			console.log(res);
	  			this.forgetPasswordEmailSent = true;
	  		},
	  		err => {
	  			console.log(err);
	  			if(err.error === "emailNotExists") {
	  				this.emailNotExists = true;
	  			} 
	  		}
  		);
  	}

}
