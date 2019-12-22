package com.tedu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.tedu.entity.Invoice;
import com.tedu.util.Constant;
import com.tedu.util.DateUtil;

public class InvoiceReport extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Invoice> invoices =(List<Invoice>) model.get(Constant.KEY_GOODS_RECEIPT_REPORT);
		String fileName = "";
		if(invoices.get(0).getType() == Constant.TYPE_GOODS_RECEIPT) {
			 fileName = "goods-receipt-"+System.currentTimeMillis()+".xlsx";
		} else {
			 fileName = "goods-issue-"+System.currentTimeMillis()+".xlsx";
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		Sheet sheet = workbook.createSheet("data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("#");
		header.createCell(1).setCellValue("Code");
		header.createCell(2).setCellValue("Quantity");
		header.createCell(3).setCellValue("Price");
		header.createCell(4).setCellValue("Product");
		header.createCell(5).setCellValue("Update date");
		
		int rowNum=1;
		for(Invoice invoice :invoices) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(rowNum-1);
			row.createCell(1).setCellValue(invoice.getCode());
			row.createCell(2).setCellValue(invoice.getQuantity());
			row.createCell(3).setCellValue(invoice.getPrice().toString());
			row.createCell(4).setCellValue(invoice.getProduct().getName());
			row.createCell(5).setCellValue(DateUtil.dateToString(invoice.getUpdateDate()));
			
		}
	}
}
