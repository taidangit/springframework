import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Component
import { FaqComponent } from './../../components/faq/faq.component';

const faqRoute: Routes = [
 	{ path: 'faq', component: FaqComponent } 	
];


@NgModule({
	imports: [
		RouterModule.forChild(faqRoute)
	],
	exports: [
		RouterModule
	]
})
export class FaqRoutingModule {}
