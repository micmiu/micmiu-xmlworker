package com.micmiu.pdf.itext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class HTML2PDFByXMLWorker {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String rootPath = "d:/test/itext/";
		String htmlFile = "demo-cn.html";
		String pdfFile = "demo-cnxxx.pdf";

		HTML2PDFByXMLWorker hander = new HTML2PDFByXMLWorker();
		long times = System.currentTimeMillis();
		System.out.println(" --------------- > start...");
		hander.buildPDF(rootPath, htmlFile, pdfFile);
		System.out.println(" --------------- > end. use time(ms):"
				+ (System.currentTimeMillis() - times));

		// FileInputStream ins = new FileInputStream(new File(rootPath +
		// htmlFile));
		//
		// InputStreamReader isr = new InputStreamReader(ins, "utf-8");
		// int b;
		// while ((b = isr.read()) != -1) {
		// System.out.print((char) b);
		// }
		// isr.close();
	}

	private void buildPDF(String rootPath, String htmlFile, String pdfFile) {
		try {

			FileInputStream ins = new FileInputStream(new File(rootPath
					+ htmlFile));

			InputStreamReader isr = new InputStreamReader(ins, "UTF-8");

			Document document = new Document(PageSize.A4);
			//document.setMargins(0, 0, 0, 0);

			// pdf method
			FileOutputStream outputStream = new FileOutputStream(rootPath
					+ pdfFile);
			PdfWriter pdfwriter = PdfWriter.getInstance(document, outputStream);
			pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
			document.open();

			// XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document,
			// isr);

			HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
			htmlContext.charSet(Charset.forName("UTF-8"));
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
			// final String dir =
			// request.getSession().getServletContext().getRealPath("/");
			// htmlContext.setImageProvider(new AbstractImageProvider() {
			// public String getImageRootPath() {
			// return dir+"/pdftemplate/"; //web realpath for images.
			// }
			// });
			//
			// htmlContext.setLinkProvider(new LinkProvider() {
			// public String getLinkRoot() {
			// return dir+"/pdftemplate/";
			// }
			//
			// });

			CSSResolver cssResolver = XMLWorkerHelper.getInstance()
					.getDefaultCssResolver(true);
			Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
					new HtmlPipeline(htmlContext, new PdfWriterPipeline(
							document, pdfwriter)));
			XMLWorker worker = new XMLWorker(pipeline, true);
			XMLParser p = new XMLParser();
			p.addListener(worker);
			p.parse(isr);
			p.flush();
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
