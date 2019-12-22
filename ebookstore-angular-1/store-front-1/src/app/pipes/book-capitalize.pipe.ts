import { Pipe, PipeTransform } from '@angular/core';

import * as _ from "lodash";

@Pipe({
  name: 'bookCapitalize'
})
export class BookCapitalizePipe implements PipeTransform {

 	 transform(value: string) : string {
		return _.replace(value, /\w\S*/g, function(txt) {
			return txt.charAt(0).toUpperCase() + txt.slice(1).toLowerCase();
		})
  }

}
