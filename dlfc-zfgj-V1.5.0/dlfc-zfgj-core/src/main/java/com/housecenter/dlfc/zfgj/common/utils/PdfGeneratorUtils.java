package com.housecenter.dlfc.zfgj.common.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.pdf.pdf.PdfDocumentGenerator;
import com.dlfc.pdf.pdf.bean.PdfCancelContract;
import com.dlfc.pdf.pdf.bean.PdfCommitBook;
import com.dlfc.pdf.pdf.bean.PdfConContract;
import com.dlfc.pdf.pdf.bean.PdfDepositContract;
import com.dlfc.pdf.pdf.exception.DocumentGeneratingException;

public class PdfGeneratorUtils {
	/** 日志对象 */
	private final static Logger logger = LoggerFactory.getLogger(PdfGeneratorUtils.class);
	
	/**
	 * 生成pdf
	 * 
	 * @param outputFile 生成pdf地址
	 * @param pcc 合同对象
	 * @param id  合同id
	 * @param template 模板路径
	 * @return 文件地址
	 */
	public static String generatePdf(String destDir, String outputFilePath, PdfConContract pcc, String id, String template) {
		File destFile = new File(destDir);
		outputFilePath = DateUtils.dateToStr(com.dlfc.admin.common.utils.DateUtils.getSynchTime(), DateUtils.DATE_Y_M_D_PATERN) + "/" + id+".pdf";
		String fullOutputFilePath = destFile.getAbsolutePath() + "/" + outputFilePath ;
		File file = new File(fullOutputFilePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		try {
			pdfGenerator.generate(template, pcc, fullOutputFilePath,destDir,id);
		} catch (DocumentGeneratingException e) {
			logger.error("PdfGeneratorUtil generatePdf DocumentGeneratingException", e);
			outputFilePath = null;
		}
		
		return outputFilePath;
	}
	
	/**
	 * 生产pdf
	 * 
	 * @param outputFile 生成pdf地址
	 * @param pCancel 解除协议合同对象
	 * @param id	解除协议合同id
	 * @param template
	 * @return
	 */
	public static String generatePdf(String destDir, String outputFile, PdfCancelContract pCancel, String id, String template) {

		/*long start = System.currentTimeMillis();
		// classpath 路径
		// 生成pdf路径
		outputFile = outputFile == null ? destFile.getAbsoluteFile() + "/" + id+".pdf": outputFile;
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, pCancel, outputFile);
		System.err.println(" \n pdf生成成功  IS OK path=\n" + outputFile);
		System.err.println("耗时time=" + (System.currentTimeMillis() - start) / 1000);*/
		return outputFile;
	}

	/**
	 * 生产pdf
	 * 
	 * @param outputFile
	 * @param pdc 押金分配协议对象
	 * @param id  押金分配协议id
	 * @param template
	 * @return
	 */
	public static String generatePdf(String destDir, String outputFile, PdfDepositContract pdc, String id, String template) {

		/*long start = System.currentTimeMillis();
		// 生成pdf路径
		outputFile = outputFile == null ? destFile.getAbsoluteFile() + "/" + id+".pdf": outputFile;
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, pdc, outputFile);
		System.err.println(" \n pdf生成成功  IS OK path=\n" + outputFile);
		System.err.println("耗时time=" + (System.currentTimeMillis() - start) / 1000);*/
		return outputFile;
	}
	
	/**
	 * 生产pdf
	 * 
	 * @param destDir + outputFilePath 服务器存储承诺书文件路径
	 * @param pcb 承诺书的数据存储
	 * @param id 公司id
	 * @param template 模板路径
	 * @return
	 * @throws Exception
	 */
	public static String generatePdf(String destDir, String outputFilePath, PdfCommitBook pcb, String id, String template) {
		File destFile = new File(destDir);
		outputFilePath = DateUtils.dateToStr(com.dlfc.admin.common.utils.DateUtils.getSynchTime(), DateUtils.DATE_Y_M_D_PATERN) + "/" + id+".pdf";
		String fullOutputFilePath = destFile.getAbsolutePath() + "/" + outputFilePath ;
		File file = new File(fullOutputFilePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		try {
			pdfGenerator.generate(template, pcb, fullOutputFilePath,"","");
		} catch (DocumentGeneratingException e) {
			logger.error("PdfGeneratorUtil generatePdf DocumentGeneratingException", e);
			outputFilePath = null;
		}
		
		return outputFilePath;
	}
}
