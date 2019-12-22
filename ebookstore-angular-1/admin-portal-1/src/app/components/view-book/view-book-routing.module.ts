import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { ViewBookComponent } from './view-book.component';


const bookListRoute: Routes = [
 	{ path: 'viewBook/:id', component: ViewBookComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(bookListRoute)
	],
	exports: [
		RouterModule
	]
})
export class ViewBookRoutingModule {}
