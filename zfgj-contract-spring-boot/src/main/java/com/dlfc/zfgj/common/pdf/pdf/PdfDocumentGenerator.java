package com.dlfc.zfgj.common.pdf.pdf;

import com.dlfc.zfgj.common.pdf.factory.ITextRendererObjectFactory;
import com.dlfc.zfgj.common.pdf.freemarker.HtmlGenerator;
import com.dlfc.zfgj.exception.DocumentGeneratingException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * pdf 生成
 *
 * @author lihengjun 修改时间： 2013年11月5日 下午3:27:22 修改内容：新建
 * @ClassName: PdfGenerator
 * @Description:pdf 生成
 */

@Service
@Transactional
public class PdfDocumentGenerator {
    private final static Logger logger = Logger.getLogger(PdfDocumentGenerator.class);

    private final static HtmlGenerator htmlGenerator;

    static {
        htmlGenerator = new HtmlGenerator();
    }

    /**
     * 使用模板,模板数据,生成pdf
     *
     * @param template   classpath中路径模板路径
     * @param documentVo 模板数据
     * @param outputFile 生成pdf的路径
     * @throws DocumentGeneratingException
     * @Title: generate
     * @Description: 使用模板, 模板数据, 生成pdf
     * @author lihengjun 修改时间： 2013年11月5日 下午1:38:53 修改内容：新建
     */
    public boolean generate(String template,
                            DocumentVo documentVo,
                            String outputFile) throws DocumentGeneratingException {
        Map<String, Object> variables;

        try {
            variables = documentVo.fillDataMap();
            logger.info(documentVo);
            String htmlContent = htmlGenerator.generate(template, variables);
            logger.info(htmlContent);


            this.generate(htmlContent, outputFile);

            logger.info("The document [primarykey="
                    + documentVo.findPrimaryKey()
                    + "] is generated successfully,and stored in ["
                    + outputFile + "]");
        } catch (Exception e) {
            String error = "The document [primarykey="
                    + documentVo.findPrimaryKey() + "] is failed to generate";
            logger.error(error);
            throw new DocumentGeneratingException(error, e);
        }

        return true;
    }

    /**
     * Output a pdf to the specified outputstream
     *
     * @param htmlContent the htmlstr
     * @param outputFile  the specified outputstream
     * @throws Exception
     */
    public void generate(String htmlContent, String outputFile)
            throws Exception {
        OutputStream out = null;
        ITextRenderer iTextRenderer = null;

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            logger.info(builder);
            Document doc = builder.parse(new ByteArrayInputStream(htmlContent
                    .getBytes("UTF-8")));
            logger.info(htmlContent);
            logger.info(htmlContent.getBytes("UTF-8"));
            logger.info(doc);
            File f = new File(outputFile);
            if (f != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }
            out = new FileOutputStream(outputFile);

            iTextRenderer = (ITextRenderer) ITextRendererObjectFactory
                    .getObjectPool().borrowObject();//获取对象池中对象
            try {
                iTextRenderer.setDocument(doc, null);
                iTextRenderer.layout();
                iTextRenderer.createPDF(out);
            } catch (Exception e) {
                ITextRendererObjectFactory.getObjectPool().invalidateObject(
                        iTextRenderer);
                iTextRenderer = null;
                throw e;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null)
                out.close();
            if (iTextRenderer != null) {
                try {
                    ITextRendererObjectFactory.getObjectPool().returnObject(
                            iTextRenderer);
                } catch (Exception ex) {
                    logger.error("Cannot return object from pool.", ex);
                }
            }
        }
    }

}