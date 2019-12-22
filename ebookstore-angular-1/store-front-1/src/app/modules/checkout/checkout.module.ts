import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatStepperModule, MatButtonModule } from '@angular/material';

import { CheckoutComponent } from './../../components/checkout/checkout.component';
import { CheckoutRoutingModule }  from './checkout-routing.module';


@NgModule({
  declarations: [ CheckoutComponent ],
  imports: [
    CommonModule,
    FormsModule,

    MatStepperModule,
    MatButtonModule,

    CheckoutRoutingModule
  ]
})
export class CheckoutModule { }
