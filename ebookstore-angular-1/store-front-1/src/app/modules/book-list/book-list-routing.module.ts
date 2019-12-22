import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { BookListComponent } from './../../components/book-list/book-list.component';


const bookListRoute: Routes = [
 	{ path: 'bookList', component: BookListComponent }
];


@NgModule({
	imports: [
		RouterModule.forChild(bookListRoute)
	],
	exports: [
		RouterModule
	]
})
export class BookListRoutingModule {}
