import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { ShoppingCartComponent } from './../../components/shopping-cart/shopping-cart.component';


const shoppingCartRoute: Routes = [
 	{ path: 'shoppingCart', component: ShoppingCartComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(shoppingCartRoute)
	],
	exports: [
		RouterModule
	]
})
export class ShoppingCartRoutingModule {}
