import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

//Service
import { BookService } from './../../services/book.service';
import { CheckoutService } from './../../services/checkout.service';
import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { PaymentService } from './../../services/payment.service';
import { ShippingService } from './../../services/shipping.service';
import { OrderService } from './../../services/order.service';
import { ShoppingCartService } from './../../services/shopping-cart.service';

// Pipe
import { BookCapitalizePipe } from './../../pipes/book-capitalize.pipe';
import { BookDescriptionPipe } from './../../pipes/book-description.pipe';
import { DataFilterPipe } from './../../pipes/data-filter.pipe';

//Directive
import { MenuDirective } from './../../directives/menu.directive';

import { ControlMessageComponent } from "./../../components/my-account/control-message.component";


@NgModule({
  declarations: [
  	BookCapitalizePipe,
  	BookDescriptionPipe,
  	DataFilterPipe,

  	MenuDirective,

    ControlMessageComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
	  BookCapitalizePipe,
  	BookDescriptionPipe,
  	DataFilterPipe,

  	MenuDirective,

    ControlMessageComponent
  ],
  providers: [
  	BookService,
  	CheckoutService,
  	LoginService,
  	UserService,
  	PaymentService,
  	ShippingService,
  	OrderService,
  	ShoppingCartService
  ]
})
export class SharedModule { }
