import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';

import { MyAccountComponent } from './../../components/my-account/my-account.component';
import { MyAccountRoutingModule }  from './my-account-routing.module';
import { SharedModule } from './../shared/shared.module';

@NgModule({
  declarations: [ MyAccountComponent ],
  imports: [
    CommonModule,
    FormsModule,
    MatTabsModule,
    ReactiveFormsModule,
    SharedModule,

    MyAccountRoutingModule
  ]
})
export class MyAccountModule { }
