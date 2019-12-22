package com.ebookstore.service;

import com.ebookstore.domain.Payment;
import com.ebookstore.domain.UserPayment;

public interface PaymentService {

	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
