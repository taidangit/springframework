package com.tedu.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.dao.HistoryDAO;
import com.tedu.entity.History;
import com.tedu.entity.Invoice;
import com.tedu.entity.Paging;
import com.tedu.entity.ProductInStock;

@Service
public class HistoryService {

	private static final Logger log = Logger.getLogger(HistoryService.class);
	
	@Autowired
	private HistoryDAO<History> historyDAO;
	
	public List<History> getHistorys(Paging paging) {
		log.info("Get all history");
		return historyDAO.findAll(paging);
	}
	
	public List<History> searchHistory(String property, int type, Paging paging) {
		return historyDAO.searchHistories(property,  type, paging);
	}
	
	public void saveHistory(Invoice invoice, String action) {
		log.info("save history ");
		History history = new History();
		history.setProduct(invoice.getProduct());
		history.setQuantity(invoice.getQuantity());
		history.setType(invoice.getType());
		history.setPrice(invoice.getPrice());
		history.setActiveFlag(1);
		history.setActionName(action);
		history.setCreateDate(new Date());
		history.setUpdateDate(new Date());
		historyDAO.save(history);
	}
}
