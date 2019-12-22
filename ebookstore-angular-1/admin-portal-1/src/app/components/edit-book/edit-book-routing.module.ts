import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { EditBookComponent } from './edit-book.component';


const editBookRoute: Routes = [
 	{ path: 'editBook/:id', component: EditBookComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(editBookRoute)
	],
	exports: [
		RouterModule
	]
})
export class EditBookRoutingModule {}
