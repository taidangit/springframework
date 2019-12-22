import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { CheckoutComponent } from './../../components/checkout/checkout.component';

const checkoutRoute: Routes = [
 	{ path: 'checkout', component: CheckoutComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(checkoutRoute)
	],
	exports: [
		RouterModule
	]
})
export class CheckoutRoutingModule {}
