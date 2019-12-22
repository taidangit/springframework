package com.ebookstore.service;

import org.springframework.stereotype.Service;

import com.ebookstore.domain.Payment;
import com.ebookstore.domain.UserPayment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Payment setByUserPayment(UserPayment userPayment, Payment payment) {
		payment.setCardNumber(userPayment.getCardNumber());
		payment.setCvc(userPayment.getCvc());
		payment.setExpiryMonth(userPayment.getExpiryMonth());
		payment.setExpiryYear(userPayment.getExpiryYear());
		payment.setHolderName(userPayment.getHolderName());
		payment.setType(userPayment.getType());
		payment.setCardName(userPayment.getCardName());
		
		
		return payment;
	}

	
}
