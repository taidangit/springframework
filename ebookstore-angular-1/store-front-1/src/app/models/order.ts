import { CartItem } from './cart-item';
import { ShippingAddress }  from './shipping-address';
import { BillingAddress }  from './billing-address';
import { Payment }  from './payment';
import { User }  from './user';

export class Order {
	public orderId: number;
	public orderDate: string;
	public shippingDate: string;
	public shippingMethod: string;
	public orderStatus: string;
	public orderTotal: number;
	public shippingAddress: ShippingAddress;
	public billingAddress: BillingAddress;
	public payment: Payment;
	public user: User;
	public cartItems: CartItem[];
}
