import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { LoginComponent } from './login.component';


const loginRoute: Routes = [
	{ path: '', redirectTo: '/login', pathMatch: 'full' },
 	{ path: 'login', component: LoginComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(loginRoute)
	],
	exports: [
		RouterModule
	]
})
export class LoginRoutingModule {}
