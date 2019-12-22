import { FormControl, FormGroup } from '@angular/forms';

export class Zvalidators {

	public static emailValidator(formControl: FormControl){
		//console.log(formControl);
		let value:string = (formControl.value != null) ? formControl.value : '';
		let pattern: RegExp = /[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}/;

		if(pattern.test(value) || value === ''){
			return null;
		}else{
			 return { 'invalidEmail': true};
		}
	}

	public static matchingPasswords(
		passwordControlName: string, 
		confirmPasswordControlName: string){

		return (group: FormGroup) : { [key:string]: boolean} => {
			console.log(group);
			let passwordControlValue: string = group.controls[passwordControlName].value;
			let confirmPasswordControlValue: string = group.controls[confirmPasswordControlName].value;
			if(passwordControlValue !== confirmPasswordControlValue) {
				return { 'mismatchedPasswords': true}
			} else {
				return null;	
			}
			
		}
	}
}
