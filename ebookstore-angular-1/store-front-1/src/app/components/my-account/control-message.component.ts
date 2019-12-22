import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

import { Zvalidators } from './../../validators/zvalidators';


@Component({
  	selector: 'app-control-message',
  	templateUrl: './app-control-message.component.html'
})
export class ControlMessageComponent implements OnInit {

    @Input('control') control: FormControl;
 
  	constructor() { }

  	ngOnInit() {
  		
  	}

    get message(): string{
      //console.log(this.control);
      for(let property in this.control.errors){
        if(this.control.dirty && this.control.errors.hasOwnProperty(property)) {
          return this.showError(property, this.control.errors[property]);
        }
      }
    }

    private showError(validatorType: string, validatorValue: any): string {
      //console.log(validatorValue);
      let arrMessage: any = {
        'required'     : 'Required',
        'minlength'    : `Min length: ${validatorValue.requiredLength} characters`,
        'invalidEmail'  : 'Email invalid'
      }

    return arrMessage[validatorType];
  }

}
