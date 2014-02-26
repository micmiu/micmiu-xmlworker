package com.micmiu.pdf.itext;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfWriter;

public class HelloWordCN {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String pdfPath = "d:/test/itext/";

		// long time = System.currentTimeMillis();
		// BaseFont bfCN = BaseFont.createFont("STSongStd-Light",
		// "UniGB-UCS2-H",
		// false);
		// System.out.println(System.currentTimeMillis() - time);
		// time = System.currentTimeMillis();
		// Font fontCN = new Font(bfCN, 12, Font.NORMAL, BaseColor.GREEN);
		// System.out.println(System.currentTimeMillis() - time);

		HelloWordCN test = new HelloWordCN();
		// test.testCN1(pdfPath + "cn1.pdf");
		// test.testCN2(pdfPath + "cn2.pdf");
		test.testCN3(pdfPath + "cn3.pdf");

	}

	public void testCN1(String pdfFile) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
			doc.open();
			BaseFont bfCN = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", false);
			Font fontCN = new Font(bfCN, 12, Font.NORMAL, BaseColor.GREEN);
			Paragraph pf = new Paragraph();
			pf.add(new Paragraph("Hello World, 欢迎来到 itext.", fontCN));
			pf.add(new Paragraph("Hello World, Wlecome to itext."));
			doc.add(pf);

			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCN2(String pdfFile) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
			doc.open();
			FontSelector selector = new FontSelector();
			selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
			selector.addFont(FontFactory.getFont("STSongStd-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
			String text = "Hello World, 欢迎来到 itext.";
			Phrase ph = selector.process(text);
			doc.add(new Paragraph(ph));
			doc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testCN3(String pdfFile) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
			doc.open();
			BaseFont bfCN = BaseFont.createFont("STSongStd-Light",
					"UniGB-UCS2-H", false);
			Font fontCN = new Font(bfCN, 12, Font.NORMAL, BaseColor.GREEN);
			String text = "Hello World, 欢迎来到 itext.한가인 韩文";
			Chunk ch = new Chunk(text, fontCN);
			doc.add(ch);
			doc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
