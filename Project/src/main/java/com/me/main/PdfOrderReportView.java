package com.me.main;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfOrderReportView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		Map<String,String> billData = (Map<String,String>) model.get("billData");
		
		Table table = new Table(2);
		table.addCell("Product");
		table.addCell("Status");
		
		for (Map.Entry<String, String> entry : billData.entrySet()) {

			table.addCell(entry.getKey());
			table.addCell(entry.getValue());
			
        }
		
		document.add(table);
				
	}

}
