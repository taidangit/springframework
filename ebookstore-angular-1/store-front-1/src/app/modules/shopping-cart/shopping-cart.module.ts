import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material';

import { ShoppingCartComponent } from './../../components/shopping-cart/shopping-cart.component';
import { ShoppingCartRoutingModule }  from './shopping-cart-routing.module';
import { CheckoutRoutingModule }  from './../checkout/checkout-routing.module';

@NgModule({
  declarations: [ ShoppingCartComponent ],
  imports: [
    CommonModule,
    FormsModule,
    MatButtonModule,
    
    ShoppingCartRoutingModule,
    CheckoutRoutingModule
  ]
})
export class ShoppingCartModule { }
