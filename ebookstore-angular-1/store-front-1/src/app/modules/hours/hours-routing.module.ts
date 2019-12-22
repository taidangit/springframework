import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { HoursComponent } from './../../components/hours/hours.component';

const hoursRoute: Routes = [
 	{ path: 'hours', component: HoursComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(hoursRoute)
	],
	exports: [
		RouterModule
	]
})
export class HoursRoutingModule {}
