package com.me.main;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelBillReportView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String,String> billData = (Map<String,String>) model.get("billData");
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Bill Report");
 
		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Product");
		header.createCell(1).setCellValue("Amount");
 
		int rowNum = 1;
		for (Map.Entry<String, String> entry : billData.entrySet()) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(entry.getValue());
                }
		
	}

}
