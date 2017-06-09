package com.dlfc.zfgj.common.pdf.freemarker;

import com.dlfc.zfgj.common.pdf.utils.ResourceLoader;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * freemark配置
 *
 * @author lihengjun
 *         修改时间： 2013年11月5日 下午3:25:17
 *         修改内容：新建
 * @ClassName: FreemarkerConfiguration
 * @Description: freemark配置
 */

@Slf4j
public class FreemarkerConfiguration {

    private static Configuration config = null;

    /**
     * 获取 FreemarkerConfiguration
     *
     * @return
     * @Title: getConfiguation
     * @Description:
     * @author lihengjun 修改时间： 2013年11月11日 下午5:27:32 修改内容：新建
     */
    public static synchronized Configuration getConfiguation() {
        if (config == null) {
            setConfiguation();
        }
        return config;
    }

    /**
     * 设置 配置
     *
     * @Title: setConfiguation
     * @Description:
     * @author lihengjun
     * 修改时间： 2013年11月5日 下午3:25:42
     * 修改内容：新建
     */
    private static void setConfiguation() {
        config = new Configuration();
//        String path = ResourceLoader.getPath("");
        String path = ResourceLoader.getPath("templates");
        System.out.println("path=" + path);
        try {
            config.setDefaultEncoding("UTF-8");
            config.setClassForTemplateLoading(FreemarkerConfiguration.class, "/templates");
        } catch (Exception e) {
            log.error("配置模板路径失败", e);
        }
    }


}