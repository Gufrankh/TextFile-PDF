package com.demo.txttopdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws DocumentException, IOException {

		File directoryPath = new File(args[0]);
		File filesList[] = directoryPath.listFiles();

		for (File fileDetails : filesList) {
			File file = fileDetails;
			if (file.isFile() && (file.getName().endsWith(".txt") || file.getName().endsWith(".SWIFT") || file.getName().endsWith(".swift") )) {
				Document pdfDoc = new Document(PageSize.A4);
				if(file.getAbsolutePath().endsWith(".txt"))
				{
				PdfWriter.getInstance(pdfDoc, new FileOutputStream(file.getAbsolutePath().replace("txt", "pdf")))
						.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
				}else{
					
					PdfWriter.getInstance(pdfDoc, new FileOutputStream(file.getAbsolutePath().toLowerCase().replace("swift", "pdf")))
					.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
	
				}
				
				pdfDoc.open();
				Font myfont = new Font();
				myfont.setStyle(Font.NORMAL);
				myfont.setSize(10);
				pdfDoc.add(new Paragraph("\n"));
				BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				String strLine;
				while ((strLine = br.readLine()) != null) {
					Paragraph para = new Paragraph(strLine + "\n", myfont);
					para.setAlignment(Element.ALIGN_JUSTIFIED);
					pdfDoc.add(para);
				}
				pdfDoc.close();
				br.close();/* do somthing with content */
			}
		}

		System.out.println("Conversion Done");
	}
}
