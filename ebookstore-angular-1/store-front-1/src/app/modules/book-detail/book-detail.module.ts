import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material';

import { BookDetailComponent } from './../../components/book-detail/book-detail.component';
import { BookDetailRoutingModule }  from './book-detail-routing.module';
import { SharedModule } from './../shared/shared.module';

@NgModule({
  declarations: [ BookDetailComponent ],
  imports: [
    CommonModule,
    FormsModule,

    MatButtonModule,

    BookDetailRoutingModule,
    SharedModule
  ]
})
export class BookDetailModule { }
