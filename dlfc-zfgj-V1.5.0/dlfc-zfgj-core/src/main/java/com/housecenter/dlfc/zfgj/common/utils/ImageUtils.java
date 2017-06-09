/**
 * 
 */
package com.housecenter.dlfc.zfgj.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

import org.apache.log4j.Logger;

import com.dlfc.admin.common.utils.DateUtils;
import com.dlfc.admin.common.utils.FileUtils;
import com.dlfc.admin.common.utils.PropertyUtils;
import com.dlfc.admin.common.utils.UuidUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author Jack
 *
 */
@SuppressWarnings("restriction")
public class ImageUtils {

	/** 日志记录 */
	private static Logger logger = Logger.getLogger(ImageUtils.class);
	/**
	 * 水印类型 LOGO
	 */
	public static final int WATER_MARK_TYPE_LOGO = 1;

	/**
	 * 水印类型满屏
	 */
	public static final int WATER_MARK_TYPE_FULL = 2;
	
	/** 图片格式 */
	private static final String FORMAT_JPG = "jpg";
	/** 压缩界限 */
	private static final int COMPRESS_LIMIT = 200 * 1024;
	/** 500K以下压缩率 */
	private static final float RATE_500K = 0.9f;
	/** 1M以下压缩率500K以上 */
	private static final float RATE_1BELOW = 0.7f;
	/** 1M以上压缩率 */
	private static final float RATE_MORETHAN1 = 0.5f;
	/** 1MB */
	private static final int MB = 1 * 1024 * 1024;
	/** 1MB */
	private static final int KB = 500 * 1024;

	/**
	 * 把图片印刷到图片上
	 * 
	 * @param pressImg
	 *            -- 水印文件
	 * @param targetImg
	 *            -- 目标文件
	 * @param x
	 *            --x坐标
	 * @param y
	 *            --y坐标
	 * @throws IOException
	 */
	public final static void pressImage(String targetImg, int wmType) throws IOException {
		// 目标文件
		File file = new File(targetImg);
		Image src = ImageIO.read(file);
		int wideth = src.getWidth(null);
		int height = src.getHeight(null);
		BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.drawImage(src, 0, 0, wideth, height, null);
		// 水印文件
		File filebiao = null;

		if (wmType == WATER_MARK_TYPE_FULL) {
			filebiao = new File(PropertyUtils.getSysVal("image.watermark.full.url"));
		} else {
			filebiao = new File(PropertyUtils.getSysVal("image.watermark.logo.url"));
		}
		Image src_biao = ImageIO.read(filebiao);
		int wideth_biao = src_biao.getWidth(null);
		int height_biao = src_biao.getHeight(null);
		g.drawImage(src_biao, 10, height - height_biao - 10, wideth_biao, height_biao, null);
		// 水印文件结束
		g.dispose();
		FileOutputStream out = new FileOutputStream(targetImg);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		out.close();
	}

	/**
	 * 打印文字水印图片
	 * 
	 * @param pressText
	 *            --文字
	 * @param targetImg
	 *            -- 目标图片
	 * @param fontName
	 *            -- 字体名
	 * @param fontStyle
	 *            -- 字体样式
	 * @param color
	 *            -- 字体颜色
	 * @param fontSize
	 *            -- 字体大小
	 * @param x
	 *            -- 偏移量
	 * @param y
	 */

	public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, int color,
			int fontSize, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			g.setColor(Color.RED);
			g.setFont(new Font(fontName, fontStyle, fontSize));

			g.drawString(pressText, wideth - fontSize - x, height - fontSize / 2 - y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 图片压缩
	 * @param srcFilePath 读取图片源
	 * @param descFilePath 输出图片路径
	 * @return 成功压缩后的File对象/失败null
	 * @throws IOException 
	 */
	public static boolean imgCompress(String srcFilePath, String descFilePath) throws IOException {
		String jpegPath;
		if (! srcFilePath.endsWith(FORMAT_JPG)) {
			StringBuilder builder = new StringBuilder(subPath(srcFilePath));
			builder.append(UuidUtils.get32UUID()).append(".").append(FORMAT_JPG);
			jpegPath = toConversionJPEG(srcFilePath, builder.toString());
			if (jpegPath == null) {
				return false;
			}
		} else {
			jpegPath = srcFilePath;
		}
		return compressPic(jpegPath, descFilePath);
	}

	/**
	 * bmp、png、gif转换格式
	 * @param srcFilePath 读取图片源
	 * @param descFilePath 输出图片路径
	 * @return 成功返回图片完整路径/失败返回null
	 * @throws IOException 
	 */
	public static String toConversionJPEG(String srcFilePath, String descFilePath) throws IOException {
		BufferedImage bufferedImage;
		File imgConversion = new File(descFilePath);
		try (FileOutputStream out = new FileOutputStream(imgConversion)) {
			// 读取图片
			File img = new File(srcFilePath);
			bufferedImage = ImageIO.read(img);
			// 创建一个空白的，RGB，相同的宽度和高度，和白色的背景
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(newBufferedImage);
			img.delete();
		} catch(IOException e) {
			logger.error(String.format("图片格式转换失败%s", srcFilePath), e);
			throw e;
		}
		return imgConversion.getPath();
	}

	/**
	 * 压缩图片
	 * 
	 * @param srcFilePath 源文件
	 * @param descFilePath 输出文件
	 * @return
	 * @throws IOException 
	 */
	private static boolean compressPic(String srcFilePath, String descFilePath) throws IOException {
		File file = new File(srcFilePath);
		if (!file.isFile()) {
			throw new RuntimeException("压缩文件失败:"+srcFilePath+"压缩源不是一个文件");
		}
		//  图片源存在并且小于200
		if (file.exists() && file.length() < COMPRESS_LIMIT) {
			File descFile = new File(descFilePath);
			if (descFile.isFile()) {
				throw new RuntimeException("压缩文件失败:"+descFilePath+"输出不是一个文件");
			}
			if(descFile.exists()){
				descFile.createNewFile();
			}
			FileUtils.copyFileCover(file.getPath(), descFilePath, true);
			file.delete();
			return true;
		} else if (!file.exists()){
			return false;
		}
		BufferedImage src = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;
		
		// 获取写入图像处理类 指定格式jpg
		imgWrier = ImageIO.getImageWritersByFormatName(FORMAT_JPG).next();
		imgWriteParams = new JPEGImageWriteParam(null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		if(KB >= file.length()){
			imgWriteParams.setCompressionQuality(RATE_500K);
		} else if (MB >= file.length()){
			imgWriteParams.setCompressionQuality(RATE_1BELOW);
		} else {
			imgWriteParams.setCompressionQuality(RATE_MORETHAN1);
		}
		
		imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(
				new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
		
		try (FileOutputStream out = new FileOutputStream(descFilePath)){
			src = ImageIO.read(file);
			
			imgWrier.reset();
			// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
			// OutputStream构造
			imgWrier.setOutput(ImageIO.createImageOutputStream(out));
			// 调用write方法，就可以向输入流写图片
			imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
			file.delete();
		} catch (IOException e) {
			logger.error(String.format("压缩图片失败%s", file.getPath()), e);
			throw e;
		}
		return true;
	}

	/**
	 * 生成LOGO水印的出租图片
	 * @param srcFilePath 源图片地址
	 * @return
	 * @throws IOException
	 */
	public static String generateLeaseImgForLogoWM(String srcFilePath) throws IOException{
		return generateLeaseImage(srcFilePath, WATER_MARK_TYPE_LOGO);
	}
	
	/**
	 * 生成满屏水印的出租图片
	 * @param srcFilePath 源图片地址
	 * @return
	 * @throws IOException
	 */
	public static String generateLeaseImgForFullWM(String srcFilePath) throws IOException{
		return generateLeaseImage(srcFilePath, WATER_MARK_TYPE_FULL);
	}

	/**
	 * 把图片转成大中小三种规格
	 * 
	 * @param srcFilePath
	 *            图片地址
	 * @param wmType
	 *            水印类型 1:logo 2:满屏
	 * @return 返回文件名称【基础名称】实际名称为基础名称+后缀名+.jpg
	 * @throws IOException
	 */
	public static String generateLeaseImage(String srcFilePath, int wmType) throws IOException {
		
		String baseUrl = PropertyUtils.getSysVal("upload.file.real.directory") + PropertyUtils.getSysVal("image.lease.url") + DateUtils.getDate() + File.separator;
		
		File baseFile = new File(baseUrl);
		if(!baseFile.exists()){
			baseFile.mkdirs();
		}
		
		String fileName = UUID.randomUUID().toString().trim();
		// 定义文件名称
		String fileUrl = baseUrl + fileName;
		// 读入文件
		File file = new File(srcFilePath);
		
		Image img = ImageIO.read(file);
		// 取得图片宽度
		int width = img.getWidth(null);
		// 取得图片高度
		int height = img.getHeight(null);

		int maxW = Integer.parseInt(PropertyUtils.getSysVal("image.max.width"));

		if (width > maxW) {
			height = new BigDecimal(maxW).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(height)).intValue();
			width = maxW;
		}

		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, width, height, null); // 绘制缩小后的图
		File destFile = new File(fileUrl + PropertyUtils.getSysVal("image.normal.suffix") + ".jpg");
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
		pressImage(destFile.getPath(), wmType);

		int w = Integer.parseInt(PropertyUtils.getSysVal("image.large.width"));
		int h = new BigDecimal(w).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(height)).intValue();
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		destFile = new File(fileUrl + PropertyUtils.getSysVal("image.large.suffix") + ".jpg");
		out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
		pressImage(destFile.getPath(), wmType);

		w = Integer.parseInt(PropertyUtils.getSysVal("image.middle.width"));
		h = new BigDecimal(w).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(height)).intValue();
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		destFile = new File(fileUrl + PropertyUtils.getSysVal("image.middle.suffix") + ".jpg");
		out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();

		w = Integer.parseInt(PropertyUtils.getSysVal("image.mini.width"));
		h = new BigDecimal(w).divide(new BigDecimal(width), 10, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(height)).intValue();
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		destFile = new File(fileUrl + PropertyUtils.getSysVal("image.mini.suffix") + ".jpg");
		out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();

		return fileName;
	}

	/**
	 * 截取文件路径
	 * @param path 文件路径
	 * @return 文件路径
	 */
	private static String subPath(String path) {
		int index = path.lastIndexOf(File.separator);
		return path.substring(0, index + 1);
	}
}
