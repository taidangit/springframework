import { Book } from './book';

export class CartItem {
	public cartItemId: number;
	public subtotal: number;
	public qty: number;
	public book: Book;
	public toUpdate: boolean;
}
