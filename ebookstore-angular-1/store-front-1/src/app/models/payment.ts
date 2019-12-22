export class Payment {
	public paymentId: number;
	public type: string;
	public cardName: string;
	public cardNumber: string;
	public expiryMonth: number;
	public expiryYear: number;
	public cvc: number;
	public holderName: string;
	public defaultPayment: boolean;
}
