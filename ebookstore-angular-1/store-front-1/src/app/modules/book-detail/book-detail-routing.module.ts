import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { BookDetailComponent } from './../../components/book-detail/book-detail.component';


const bookDetailRoute: Routes = [
 	{ path: 'bookDetail/:id', component: BookDetailComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(bookDetailRoute)
	],
	exports: [
		RouterModule
	]
})
export class BookDetailRoutingModule {}
