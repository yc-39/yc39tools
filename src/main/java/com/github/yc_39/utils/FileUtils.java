package com.github.yc_39.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/**  
     * Don't let anyone instantiate this class.  
     */ 
	private FileUtils() {}

	/**
	 *  删除文件
	 * @param file
	 */
	public static void deleteFile(File file) {
		System.out.println("开始删除：" + file.getAbsolutePath());
		if (!file.exists()) {
			System.out.println("错误！！！文件或文件夹不存在！");
			return;
		}
		
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File childFile : files) {
				deleteFile(childFile);
			}
		}
		if (file.delete()) {
			System.out.println("删除成功！" + file.getAbsolutePath());
		} else {
			System.out.println("删除失败！" + file.getAbsolutePath());
		}
	}
	
	/**
	 * 读取文本文件
	 * @param file
	 * @return
	 */
	public static String readTxtFile(File file) {
		System.out.println("读取文件内容：" + file.getAbsolutePath());
		// 判断文件是否存在
		if (!file.exists()) {
			System.out.println("错误！！！文件或文件夹不存在！");
			return null;
		}
		
		// 判断是否是文件
		if (!file.isFile()) {
			System.out.println("错误！不是文件类型！");
			return null;
		}

		StringBuffer sb = new StringBuffer();
		// 读取内容
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String temp = br.readLine();
			while (temp != null) {
				sb.append(temp + "\r\n");
				temp = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	/**
	 * 读取文本文件
	 * @param file
	 * @return
	 */
	public static List<String> readTxtFile2(File file) {
		System.out.println("读取文件内容：" + file.getAbsolutePath());
		// 判断文件是否存在
		if (!file.exists()) {
			System.out.println("错误！！！文件或文件夹不存在！");
			return null;
		}
		
		// 判断是否是文件
		if (!file.isFile()) {
			System.out.println("错误！不是文件类型！");
			return null;
		}
		
		List<String> results = new ArrayList<String>();
		// 读取内容
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String temp = br.readLine();
			while (temp != null) {
				results.add(temp);
				temp = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	/**
	 * 复制文件/文件夹
	 * sourceFile与destFile必须同为文件类型或文件夹类型
	 * @param sourceFile
	 * @param destFile
	 */
	public static void copy(File sourceFile, File destFile) {
		System.out.println("复制..." + sourceFile.getAbsolutePath() + "  -->  " + destFile.getAbsolutePath());
		if (!sourceFile.exists()) {
			// 复制的文件不存在
			return;
		}
		
		// 判断父级目录是否存在，不存在则创建
		File parentFile = destFile.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		
		if (sourceFile.isFile()) {
			// 复制文件
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile))) {
				byte[] b = new byte[1024];
				int temp = bis.read(b);
				while(temp != -1) {
					bos.write(b, 0, temp);
					temp = bis.read(b);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			// 复制文件夹
			destFile.mkdirs();
			File[] files = sourceFile.listFiles();
			for (File file : files) {
				copy(file, new File(destFile.getAbsoluteFile()+File.separator+file.getName()));
			}
		}
	}
	
	public static void main(String[] args) {
		String source = "D:\\apache-maven-3.5.4\\conf\\logging";
		String dest = "D:\\12";
		FileUtils.copy(new File(source), new File(dest));
	}
	
	
}
