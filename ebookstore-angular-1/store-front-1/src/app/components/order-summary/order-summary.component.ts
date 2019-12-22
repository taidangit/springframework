import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { Order } from './../../models/order';
import { CartItem } from './../../models/cart-item';
import { AppConst } from './../../constants/app-const';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {

	private subscriptionQueryParams: Subscription;
	private order: Order = new Order();
	private estimatedDeliveryDate: string = "";
	private cartItems: CartItem[] = [];

	private serverPathImage = AppConst.serverPathImage;


  	constructor(
  		private activatedRoute: ActivatedRoute, 
  		private router: Router
  	) { }

  	ngOnInit() {
  		this.subscriptionQueryParams = this.activatedRoute.queryParams.subscribe(
			(params: Params) => {
			
				this.order = JSON.parse(params['order']);
				console.log(this.order);

				let deliveryDate = new Date();
				if(this.order.shippingMethod === "groundShipping") {
					deliveryDate.setDate(deliveryDate.getDate()+5);
				} else {
					deliveryDate.setDate(deliveryDate.getDate()+3);
				}

				let days: string[] = 
					["Sunday", "Monday", "Tuesday", "Webnesday", "Thurday", "Friday", "Saturday"];

				this.estimatedDeliveryDate = 
					days[deliveryDate.getDay()]+", "+
					deliveryDate.getDate()+"/"+
					(deliveryDate.getMonth()+1)+"/"+
					deliveryDate.getFullYear();

				this.cartItems = this.order.cartItems;
			}
		);

  	}

  	ngOnDestroy(){
		this.subscriptionQueryParams.unsubscribe();
	}
}
