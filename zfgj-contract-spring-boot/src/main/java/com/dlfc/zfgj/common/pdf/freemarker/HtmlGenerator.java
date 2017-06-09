package com.dlfc.zfgj.common.pdf.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Slf4j
public class HtmlGenerator {

    /**
     * Generate html string.
     *
     * @param template  the name of freemarker teamlate.
     * @param variables the data of teamlate.
     * @return htmlStr
     * @throws IOException
     * @throws TemplateException
     * @throws Exception
     */
    public String generate(String template, Map<String, Object> variables) throws IOException, TemplateException {
        BufferedWriter writer = null;
        String htmlContent;
        try {
            Configuration config = FreemarkerConfiguration.getConfiguation();
            Template tp = config.getTemplate(template);
            StringWriter stringWriter = new StringWriter();
            writer = new BufferedWriter(stringWriter);

            tp.setOutputEncoding("UTF-8");
            tp.process(variables, writer);
            htmlContent = stringWriter.toString();
            writer.flush();

        } finally {
            if (writer != null)
                writer.close();
        }
        return htmlContent;
    }

} 