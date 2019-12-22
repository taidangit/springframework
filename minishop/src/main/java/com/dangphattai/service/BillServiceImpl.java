package com.dangphattai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangphattai.dao.BillDAO;
import com.dangphattai.entity.Bill;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDAO billDAO;
	
	@Override
	public Bill addBill(Bill bill) {
		return billDAO.addBill(bill);
	}

	
}
