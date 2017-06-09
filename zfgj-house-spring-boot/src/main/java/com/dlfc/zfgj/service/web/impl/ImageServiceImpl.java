package com.dlfc.zfgj.service.web.impl;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.zfgj.service.web.ImageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by K on 2017/5/5.
 */

@Transactional
@Service("houseForW")
public class ImageServiceImpl implements ImageService {

    /**
     * 水印类型 LOGO
     */
    public static final int WATER_MARK_TYPE_LOGO = 1;

    /**
     * 水印类型满屏
     */
    public static final int WATER_MARK_TYPE_FULL = 2;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 出租信息图片转换并加水印
     *
     * @param srcFilePath
     * @param wmType
     * @return 数据库保存图片的路径
     * @throws IOException
     */
    @Override
    public String generateLeaseImage(String srcFilePath, int wmType) throws IOException {

        String parentDirName = "house/" + DateUtils.getDate(DATE_FORMAT);
        String filePath =
                PropertyUtils.getSysVal("upload.file.temporary.directory") + parentDirName + "/" + srcFilePath;
        String dbFilePath = PropertyUtils.getSysVal("image.lease.url")
                + DateUtils.getDate(DATE_FORMAT) + "/" + getFileNameWithoutExtension(srcFilePath);
        String destFilePath = PropertyUtils.getSysVal("upload.file.real.directory") + dbFilePath;

        File destFile = new File(destFilePath);
        File destFolder = destFile.getParentFile();
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        // 读入文件
        File file = new File(filePath).getAbsoluteFile();

        Image img = ImageIO.read(file);
        // 取得图片宽度
        int width = img.getWidth(null);
        // 取得图片高度
        int height = img.getHeight(null);

        int maxW = Integer.parseInt(PropertyUtils.getSysVal("image.max.width"));

        if (width > maxW) {
            height = new BigDecimal(maxW).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN)
                    .multiply(new BigDecimal(height)).intValue();
            width = maxW;
        }

        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, width, height, null); // 绘制缩小后的图
        destFile = new File(destFilePath + PropertyUtils.getSysVal("image.normal.suffix") + ".jpg");
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        ImageIO.write(image, "jpg", destFile);
        out.close();
        String filebiao;
        if (wmType == WATER_MARK_TYPE_FULL) {
            filebiao = PropertyUtils.getSysVal("image.watermark.full.url");
        } else {
            filebiao = PropertyUtils.getSysVal("image.watermark.logo.url");
        }
        pressImage(destFile.getPath(), filebiao);

        int w = Integer.parseInt(PropertyUtils.getSysVal("image.large.width"));
        int h = new BigDecimal(w).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN)
                .multiply(new BigDecimal(height)).intValue();
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        destFile = new File(destFilePath + PropertyUtils.getSysVal("image.large.suffix") + ".jpg");
        out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        ImageIO.write(image, "jpg", destFile);
        out.close();
        pressImage(destFile.getPath(), filebiao);

        w = Integer.parseInt(PropertyUtils.getSysVal("image.middle.width"));
        h = new BigDecimal(w).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN)
                .multiply(new BigDecimal(height)).intValue();
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        destFile = new File(destFilePath + PropertyUtils.getSysVal("image.middle.suffix") + ".jpg");
        out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        ImageIO.write(image, "jpg", destFile);
        out.close();

        w = Integer.parseInt(PropertyUtils.getSysVal("image.mini.width"));
        h = new BigDecimal(w).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN)
                .multiply(new BigDecimal(height)).intValue();
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        destFile = new File(destFilePath + PropertyUtils.getSysVal("image.mini.suffix") + ".jpg");
        out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        ImageIO.write(image, "jpg", destFile);
        out.close();

        dbFilePath = dbFilePath.replaceAll("\\" + File.separator, "/");
        return dbFilePath;
    }

    /**
     * 获取没有扩展名的文件名
     *
     * @param fileName 文件名
     * @return 没有扩展名的文件名
     */
    private String getFileNameWithoutExtension(String fileName) {
        String newFileName = "";
        if (StringUtils.isNotEmpty(fileName)) {
            int index = fileName.lastIndexOf(".");
            if (index > 0) {
                newFileName = fileName.substring(0, index);
            } else {
                newFileName = fileName;
            }
        }
        return newFileName;
    }

    public void pressImage(String targetImg, String watermarkImgFilePath) throws IOException {
        File file = new File(targetImg);
        BufferedImage src = ImageIO.read(file);
        int wideth = src.getWidth(null);
        int height = src.getHeight(null);
        BufferedImage image = new BufferedImage(wideth, height, 1);
        Graphics2D g = image.createGraphics();
        g.drawImage(src, 0, 0, wideth, height, null);
        File filebiao = new File(watermarkImgFilePath);
        BufferedImage src_biao = ImageIO.read(filebiao);
        int wideth_biao = src_biao.getWidth(null);
        int height_biao = src_biao.getHeight(null);
        g.drawImage(src_biao, 10, height - height_biao - 10, wideth_biao, height_biao, null);
        g.dispose();
        FileOutputStream out = new FileOutputStream(targetImg);
        ImageIO.write(image, "jpg", new File(targetImg));
        out.close();
    }

}
