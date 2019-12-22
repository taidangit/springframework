import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { AddBookComponent } from './add-book.component';


const addBookRoute: Routes = [
 	{ path: 'addBook', component: AddBookComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(addBookRoute)
	],
	exports: [
		RouterModule
	]
})
export class AddBookRoutingModule {}
