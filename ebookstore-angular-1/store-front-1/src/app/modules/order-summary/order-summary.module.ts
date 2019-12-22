import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderSummaryComponent } from './../../components/order-summary/order-summary.component';
import { OrderSummaryRoutingModule }  from './order-summary-routing.module';



@NgModule({
  declarations: [ OrderSummaryComponent ],
  imports: [
    CommonModule,

    OrderSummaryRoutingModule
  ]
})
export class OrderSummaryModule { }
