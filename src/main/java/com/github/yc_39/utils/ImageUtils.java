package com.github.yc_39.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片工具类
 */
public class ImageUtils {
	/**  
     * Don't let anyone instantiate this class.  
     */ 
	private ImageUtils() {}

	private static final String DEFAULT_FORMAT_NAME = "jpeg";
	
	/**
	 * 按比例缩放图片
	 * @param oldImage 旧图
	 * @param rate 放大比率（double型）
	 * @return BufferedImage 新图
	 */
	public BufferedImage zoomImageByRate(BufferedImage oldImage, double rate){
		BufferedImage newImage = null;
		
        int width = new Double(oldImage.getWidth() * rate).intValue(); 
        int height = new Double(oldImage.getHeight() * rate).intValue(); 
        
        newImage = new BufferedImage(width, height, oldImage.getType()); 
        Graphics g = newImage.getGraphics(); 
        g.drawImage(oldImage, 0, 0, width, height, null); 
        g.dispose(); 
		return newImage;
	}
	
	/**
	 *  按比例缩放图片
	 * @param oldImageFile 旧图文件
	 * @param newImageFile 新图存放文件对象
	 * @param rate 放大比率（double型）
	 */
	public void zoomImageByRate(File oldImageFile, File newImageFile, double rate) {
		try {
			// 获取文件的类型
			String formatName = oldImageFile.getName().split(".")[1];
			if (CheckUtils.isEmpty(formatName)) {
				formatName = DEFAULT_FORMAT_NAME;
			}
			// 获取旧图
			BufferedImage bufferedImage = ImageIO.read(oldImageFile);
			// 转换
			BufferedImage newImage = zoomImageByRate(bufferedImage, rate);
			// 保存新图
			ImageIO.write(newImage, formatName, newImageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 按比例缩放图片
	 * @param oldImagePath 旧图文件路径
	 * @param newImagePath 新图存放路径
	 * @param rate 放大比率（double型）
	 */
	public void zoomImageByRate(String oldImagePath, String newImagePath, double rate) {
		// 获取文件的类型
		zoomImageByRate(new File(oldImagePath), new File(newImagePath), rate);
	}
	
	
	
	/**
	 * 限制最大宽度缩放图片
	 * @param oldImage 旧图
	 * @param maxWidth 最大的宽
	 * @return BufferedImage 新图 
	 */
	public BufferedImage zoomImageByMaxWidth(BufferedImage oldImage, int maxWidth) {
		BufferedImage newImage = null;
		
		double rate = maxWidth / oldImage.getWidth() ;
		int width = maxWidth;
		int height = new Double(oldImage.getHeight() * rate).intValue();
		
		newImage = new BufferedImage(width, height, oldImage.getType()); 
		Graphics g = newImage.getGraphics(); 
		g.drawImage(oldImage, 0, 0, width, height, null); 
		g.dispose(); 
		return newImage;
	}
	
	/**
	 * 限制最大宽度缩放图片
	 * @param oldImageFile
	 * @param newImageFile
	 * @param maxWidth 最大的宽
	 */
	public void zoomImageByMaxWidth(File oldImageFile, File newImageFile, int maxWidth) {
		try {
			// 获取文件的类型
			String formatName = oldImageFile.getName().split(".")[1];
			if (CheckUtils.isEmpty(formatName)) {
				formatName = DEFAULT_FORMAT_NAME;
			}
			// 获取旧图
			BufferedImage bufferedImage = ImageIO.read(oldImageFile);
			// 转换
			BufferedImage newImage = zoomImageByMaxWidth(bufferedImage, maxWidth);
			// 保存新图
			ImageIO.write(newImage, formatName, newImageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 限制最大宽度缩放图片
	 * @param oldImagePath
	 * @param newImagePath
	 * @param maxWidth 最大的宽
	 */
	public void zoomImageByMaxWidth(String oldImagePath, String newImagePath, int maxWidth) {
		zoomImageByMaxWidth(new File(oldImagePath), new File(newImagePath), maxWidth);
	}
	
	
	
	/**
	 * 限制最大长度缩放图片
	 * @param oldImage 旧图
	 * @param maxHeight 最大的长
	 * @return BufferedImage 新图
	 */
	public BufferedImage zoomImageByMaxHeight(BufferedImage oldImage, int maxHeight) {
		BufferedImage newImage = null;
		
		double rate = maxHeight / oldImage.getHeight() ;
		int width = new Double(oldImage.getWidth() * rate).intValue(); 
		int height = maxHeight;
		
		newImage = new BufferedImage(width, height, oldImage.getType()); 
		Graphics g = newImage.getGraphics(); 
		g.drawImage(oldImage, 0, 0, width, height, null); 
		g.dispose(); 
		return newImage;
	}
	
	/**
	 * 限制最大长度缩放图片
	 * @param oldImageFile
	 * @param newImageFile
	 * @param maxHeight 最大的长
	 */
	public void zoomImageByMaxHeight(File oldImageFile, File newImageFile, int maxHeight) {
		try {
			// 获取文件的类型
			String formatName = oldImageFile.getName().split(".")[1];
			if (CheckUtils.isEmpty(formatName)) {
				formatName = DEFAULT_FORMAT_NAME;
			}
			// 获取旧图
			BufferedImage bufferedImage = ImageIO.read(oldImageFile);
			// 转换
			BufferedImage newImage = zoomImageByMaxHeight(bufferedImage, maxHeight);
			// 保存新图
			ImageIO.write(newImage, formatName, newImageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 限制最大长度缩放图片
	 * @param oldImagePath
	 * @param newImagePath
	 * @param maxHeight 最大的长
	 */
	public void zoomImageByMaxHeight(String oldImagePath, String newImagePath, int maxHeight) {
		zoomImageByMaxHeight(new File(oldImagePath), new File(newImagePath), maxHeight);
	}
	
	
	
	
	public static void main(String[] args) {
		try {
			
			ImageUtils imageUtil = new ImageUtils();
			
			String pathname = "d://yc//QQ图片20170209174705.jpg";
			File input = new File(pathname);
			
			String formatName = pathname.substring(pathname.lastIndexOf(".")+1);
			
			BufferedImage bufferedImage = ImageIO.read(input);
			
			BufferedImage newImage = imageUtil.zoomImageByMaxWidth(bufferedImage, 1024);
			ImageIO.write(newImage, formatName, new File("d://yc//123.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
