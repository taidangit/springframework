import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HoursComponent } from './../../components/hours/hours.component';
import { HoursRoutingModule }  from './hours-routing.module';

@NgModule({
  declarations: [ HoursComponent ],
  imports: [
    CommonModule,

    HoursRoutingModule
  ]
})
export class HoursModule { }
