import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DataTableModule } from 'ng-angular8-datatable';

import { BookListComponent } from './../../components/book-list/book-list.component';

import { BookListRoutingModule }  from './book-list-routing.module';
import { SharedModule } from './../shared/shared.module';


@NgModule({
  declarations: [ BookListComponent ],
  imports: [
   	CommonModule,
    FormsModule,
    DataTableModule,

    BookListRoutingModule,
    SharedModule
  ]
})
export class BookListModule { }
