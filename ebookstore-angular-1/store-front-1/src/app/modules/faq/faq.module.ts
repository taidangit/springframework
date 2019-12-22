import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FaqComponent } from './../../components/faq/faq.component';
import { FaqRoutingModule }  from './faq-routing.module';


@NgModule({
  declarations: [ FaqComponent ],
  imports: [
    CommonModule,

    FaqRoutingModule
  ]
})
export class FaqModule { }
