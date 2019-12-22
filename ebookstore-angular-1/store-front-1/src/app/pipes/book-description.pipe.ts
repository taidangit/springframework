import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'bookDescription'
})
export class BookDescriptionPipe implements PipeTransform {

  	transform(value: string): string {
  		return value.slice(0, 200) + "...";
	}

}
