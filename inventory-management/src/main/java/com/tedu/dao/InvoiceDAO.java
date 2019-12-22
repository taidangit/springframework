package com.tedu.dao;

import java.util.Date;
import java.util.List;

import com.tedu.entity.Paging;

public interface InvoiceDAO<E> extends BaseDAO<E> {

	List<E> searchInvoices(String property, String productName, Paging paging);
}
