import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material';

import { NavBarComponent } from './../../components/nav-bar/nav-bar.component';
import { HomeRoutingModule }  from './../home/home-routing.module';
import { BookListRoutingModule }  from './../book-list/book-list-routing.module';
import { HoursRoutingModule }  from './../hours/hours-routing.module';
import { FaqRoutingModule }  from './../faq/faq-routing.module';
import { SharedModule } from './../shared/shared.module';

@NgModule({
  declarations: [ NavBarComponent ],
  imports: [
    CommonModule,
    FormsModule,
    MatMenuModule,

    HomeRoutingModule,
    BookListRoutingModule,
    HoursRoutingModule,
    FaqRoutingModule,
    SharedModule
  ],
  exports: [
  	NavBarComponent
  ]
})
export class NavBarModule { }
