import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTabsModule, MatProgressSpinnerModule, MatButtonModule } from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';

import { MyProfileComponent } from './../../components/my-profile/my-profile.component';
import { ControlMessageComponent } from "./../../components/my-account/control-message.component";

import { MyProfileRoutingModule }  from './my-profile-routing.module';

import { SharedModule } from './../shared/shared.module';

@NgModule({
  declarations: [ MyProfileComponent ],
  imports: [
    CommonModule,
    FormsModule,
    MatTabsModule,
    MatProgressSpinnerModule,
    MatButtonModule,
    ReactiveFormsModule,
    SharedModule,
   

    MyProfileRoutingModule
  ]
})
export class MyProfileModule { }
