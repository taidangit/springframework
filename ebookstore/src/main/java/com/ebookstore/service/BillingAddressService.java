package com.ebookstore.service;

import com.ebookstore.domain.BillingAddress;
import com.ebookstore.domain.UserBilling;

public interface BillingAddressService {

	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
