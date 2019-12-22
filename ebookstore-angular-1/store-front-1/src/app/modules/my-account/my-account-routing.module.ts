import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { MyAccountComponent } from './../../components/my-account/my-account.component';

const myAccountRoute: Routes = [
 	{ path: 'myAccount', component: MyAccountComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(myAccountRoute)
	],
	exports: [
		RouterModule
	]
})
export class MyAccountRoutingModule {}
