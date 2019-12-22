import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { OrderSummaryComponent } from './../../components/order-summary/order-summary.component';



const orderSummaryRoute: Routes = [
 	{ path: 'orderSummary', component: OrderSummaryComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(orderSummaryRoute)
	],
	exports: [
		RouterModule
	]
})
export class OrderSummaryRoutingModule {}
