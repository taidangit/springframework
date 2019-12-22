package com.tedu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.dao.InvoiceDAO;
import com.tedu.entity.Invoice;
import com.tedu.entity.Paging;

import com.tedu.util.Constant;

@Service
public class InvoiceService {

	private static final Logger log = Logger.getLogger(InvoiceService.class);
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ProductInStockService productInStockService;
	
	@Autowired
	private InvoiceDAO<Invoice> invoiceDAO;
	

	public void saveInvoice(Invoice invoice) {

		invoice.setActiveFlag(1);
		invoice.setCreateDate(new Date());
		invoice.setUpdateDate(new Date());
	
		invoiceDAO.save(invoice);
		historyService.saveHistory(invoice, Constant.ACTION_ADD);
		
		productInStockService.saveOrUpdateProductInStock(invoice);
	}

	public void updateInvoice(Invoice invoice) {
		Invoice invoice2 = invoiceDAO.findById(Invoice.class, invoice.getInvoiceId());
		
		invoice.setActiveFlag(1);
		invoice.setCreateDate(invoice2.getCreateDate());
		invoice.setUpdateDate(new Date());
		
		invoiceDAO.update(invoice);
		
		historyService.saveHistory(invoice, Constant.ACTION_EDIT);
		productInStockService.saveOrUpdateProductInStock(invoice);
		
	}

	public Invoice findInvoiceById(int id) {
		log.info("Find invoice by id= "+id);
		return invoiceDAO.findById(Invoice.class, id);
	}
	
	public List<Invoice> findInvoice(String property, Object value) {
		return invoiceDAO.findByProperty(property, value);
	}
	
	public List<Invoice> getInvoices(Paging paging) {
		log.info("Get all invoicw");
		return invoiceDAO.findAll(paging);
	}
	
	
	public List<Invoice> searchInvoice(String property, String invoiceCode, Paging paging) {
		return invoiceDAO.searchInvoices(property, invoiceCode, paging);
	}
	
	public void deleteInvoice(String property, int id) {
		invoiceDAO.delete(property, id);
	}
	

}
