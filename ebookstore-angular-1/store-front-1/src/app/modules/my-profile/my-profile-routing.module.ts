import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { MyProfileComponent } from './../../components/my-profile/my-profile.component';


const myProfileRoute: Routes = [
 	{ path: 'myProfile', component: MyProfileComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(myProfileRoute)
	],
	exports: [
		RouterModule
	]
})
export class MyProfileRoutingModule {}
