package com.dlfc.zfgj.common.pdf.factory;

import com.dlfc.admin.common.utils.PropertyUtils;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.IOException;

/**
 * ITextRenderer对象工厂,提供性能,加载中文字体集(大小20M),故增加对象池
 *
 * @author lihengjun
 *         修改时间： 2013年11月13日 下午3:00:20
 *         修改内容：新建
 * @ClassName: ITextRendererObjectFactory
 * @Description:
 */

@Slf4j
public class ITextRendererObjectFactory extends
        BasePoolableObjectFactory {
    private static GenericObjectPool itextRendererObjectPool = null;

    @Override
    public Object makeObject() throws Exception {
        ITextRenderer renderer = createTextRenderer();
        return renderer;
    }

    /**
     * 获取对象池,使用对象工厂 后提供性能,能够支持 500线程 迭代10
     *
     * @return GenericObjectPool
     * @Title: getObjectPool
     * @Description: 获取对象池
     * @author lihengjun
     * 修改时间： 2013年11月13日 下午2:15:15
     * 修改内容：新建
     */
    public static GenericObjectPool getObjectPool() {
        synchronized (ITextRendererObjectFactory.class) {
            if (itextRendererObjectPool == null) {
                itextRendererObjectPool = new GenericObjectPool(
                        new ITextRendererObjectFactory());
                GenericObjectPool.Config config = new GenericObjectPool.Config();
                config.maxActive = 15;
                config.maxIdle = 5;
                config.minIdle = 1;
                config.maxWait = 5 * 1000;
                itextRendererObjectPool.setConfig(config);
            }
        }

        return itextRendererObjectPool;
    }

    /**
     * 初始化
     *
     * @return
     * @throws DocumentException
     * @throws IOException
     * @Title: initTextRenderer
     * @Description:
     * @author lihengjun 修改时间： 2013年11月13日 上午10:39:39 修改内容：新建
     */
    public static synchronized ITextRenderer createTextRenderer()
            throws DocumentException, IOException {
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        addFonts(fontResolver);
        return renderer;
    }

    /**
     * 添加字体
     *
     * @param fontResolver
     * @throws DocumentException
     * @throws IOException
     * @Title: addFonts
     * @Description:
     * @author lihengjun 修改时间： 2013年11月5日 下午1:37:57 修改内容：新建
     */
    public static ITextFontResolver addFonts(ITextFontResolver fontResolver)
            throws DocumentException, IOException {
//        File fontsDir = new File(ResourceLoader.getPath("fonts"));
        File fontsDir = new File(PropertyUtils.getSysVal("pdf.file.fonts.directory"));
        log.info(fontsDir.getPath());
        if (fontsDir != null && fontsDir.isDirectory()) {
            File[] files = fontsDir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f == null || f.isDirectory()) {
                    break;
                }
                fontResolver.addFont(f.getPath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            }
        }
        return fontResolver;
    }
}
