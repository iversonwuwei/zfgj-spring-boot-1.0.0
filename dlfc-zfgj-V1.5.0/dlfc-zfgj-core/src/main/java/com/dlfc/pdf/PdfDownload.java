package com.dlfc.pdf;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import com.dlfc.pdf.pdf.PdfDocumentGenerator;
import com.dlfc.pdf.pdf.bean.PdfConContract;
import com.dlfc.pdf.pdf.exception.DocumentGeneratingException;
import com.dlfc.pdf.utils.ResourceLoader;

import junit.framework.TestCase;

public class PdfDownload extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String logConfigPath = ResourceLoader.getPath("log4j.properties");
		PropertyConfigurator.configure(logConfigPath);
	}

	/**
	 * 
	 * @param outputFile
	 *            生成pdf路径
	 * @param pcc
	 *            合同数据
	 * @param id
	 *            合同id
	 * @throws DocumentGeneratingException
	 */
	public String generatePdf(String outputFile, PdfConContract pcc, String id) throws DocumentGeneratingException {

		long start = System.currentTimeMillis();

		// classpath 中模板路径
		String template = "templates/pdfTemplates/overseaAssistance1.html";
		// classpath 路径
		String outputFileClass = ResourceLoader.getPath("");
		// 生成pdf路径
		outputFile = outputFile == null ? new File(outputFileClass).getParentFile().getParent() + "/tmp/" + id + ".pdf"
				: outputFile;

		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		/*pdfGenerator.generate(template, pcc, outputFile);*/

		System.err.println(" \n pdf生成成功  IS OK path=\n" + outputFile);
		System.err.println("耗时time=" + (System.currentTimeMillis() - start) / 1000);
		return outputFile;
	}

	/*public ResponseEntity<byte[]> download(String getPath) throws IOException {

		String path = "";
		File file = new File(getPath);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String("你好.txt".getBytes("iso-8859-1"), "UTF-8");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}*/
	
}