import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { HomeComponent } from './../../components/home/home.component';

const homeRoute: Routes = [
	{ path: '', redirectTo: '/home', pathMatch: 'full' },
 	{ path: 'home', component: HomeComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(homeRoute)
	],
	exports: [
		RouterModule
	]
})
export class HomeRoutingModule {}

