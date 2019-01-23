package com.github.yc_39.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class SwingUtils {
	/**  
     * Don't let anyone instantiate this class.  
     */ 
	private SwingUtils() {}

	/**
	 * 调用JFileChooser选择文件，返回选择的路径
	 * 
	 * @return
	 */
	public static String chooseFile() {
		JFileChooser fileChooser = new JFileChooser("选择文件");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showDialog(new JLabel(), "选择文件");
		File file = fileChooser.getSelectedFile();
//		if (file.isDirectory()) {
//			System.out.println("文件夹:" + file.getAbsolutePath());
//		} else if (file.isFile()) {
//			System.out.println("文件:" + file.getAbsolutePath());
//		}
		return file.getAbsolutePath();
	}

	/**
	 * 调用JFileChooser选择文件夹，返回选择的路径
	 * 
	 * @return
	 */
	public static String chooseDirectory() {
		JFileChooser fileChooser = new JFileChooser("选择文件夹");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.showDialog(new JLabel(), "选择文件夹");
		File file = fileChooser.getSelectedFile();
//		if (file.isDirectory()) {
//			System.out.println("文件夹:" + file.getAbsolutePath());
//		} else if (file.isFile()) {
//			System.out.println("文件:" + file.getAbsolutePath());
//		}
		return file.getAbsolutePath();
	}

	/**
	 * 调用JFileChooser选择文件或文件夹，返回选择的路径
	 * 
	 * @return
	 */
	public static String chooseDirectoryOrFile() {
		JFileChooser fileChooser = new JFileChooser("选择文件路径");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showDialog(new JLabel(), "选择文件或文件夹");
		File file = fileChooser.getSelectedFile();
//		if (file.isDirectory()) {
//			System.out.println("文件夹:" + file.getAbsolutePath());
//		} else if (file.isFile()) {
//			System.out.println("文件:" + file.getAbsolutePath());
//		}
		return file.getAbsolutePath();
	}

	
	/**
	 * 居中
	 * @param component
	 */
	public static void centerShowJFrame(Component component) {
		// 获得屏幕的大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获得窗口的大小
		Dimension frameSize = component.getSize();

		// 如果窗口的大小比屏幕的要大，就用屏幕的赋值给窗口的。
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}

		// 设置窗口的显示位置居中。
		component.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		// 显示窗口
		component.setVisible(true);
	}
}
