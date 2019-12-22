import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule }  from '@angular/common/http';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { BookDetailModule } from './modules/book-detail/book-detail.module';
import { BookListModule } from './modules/book-list/book-list.module';
import { CheckoutModule } from './modules/checkout/checkout.module';
import { FaqModule } from './modules/faq/faq.module';
import { FooterModule } from './modules/footer/footer.module';
import { HomeModule } from './modules/home/home.module';
import { HoursModule } from './modules/hours/hours.module';
import { MyAccountModule } from './modules/my-account/my-account.module';
import { MyProfileModule } from './modules/my-profile/my-profile.module';
import { NavBarModule } from './modules/nav-bar/nav-bar.module';
import { OrderSummaryModule } from './modules/order-summary/order-summary.module';
import { ShoppingCartModule } from './modules/shopping-cart/shopping-cart.module';
import { SharedModule } from './modules/shared/shared.module';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

const appRoutes: Routes = [
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
  
    SharedModule,
    BookDetailModule,
    BookListModule,
    CheckoutModule,
    FaqModule,
    FooterModule,
    HomeModule,
    HoursModule,
    MyAccountModule,
    MyProfileModule,
    NavBarModule,
    OrderSummaryModule,
    ShoppingCartModule,

    BrowserAnimationsModule,

    RouterModule.forRoot(appRoutes)
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
