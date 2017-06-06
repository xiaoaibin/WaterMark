package com.watermark.service;

import java.awt.Color;
import java.awt.Font;
import java.io.File;


/**
 * 水印接口
 * @author aibinxiao
 * @date 2017年6月6日 上午11:46:06
 */
public interface MarkService {
	
	public static final String MARK_TEXT = "https://my.oschina.net/aibinxiao"; // 水印文字
	
	public static final String FONT_NAME = "微软雅黑"; // 水印文字字体
	public static final int FONT_STYLE = Font.BOLD; // 水印文字样式
	public static final int FONT_SIZE = 50; // 水印文字大小，像素
	public static final Color FONT_COLOR = Color.BLACK; // 水印文字颜色
	
	public static final int X = 10; // 水印文字X坐标
	public static final int Y = 10; // 水印文字Y坐标
	
	public static final float ALPHA = 0.3F; // 水印文字透明设置参数 30%
	
	/**
	 * 添加水印接口
	 * @param image 需要添加水印的图片
	 * @param imageFileName 图片名称
	 * @param uploadPath 图片上传的相对路径
	 * @param realUploadPath 图片上传的绝对路径
	 * @return
	 */
	public String watermark(File image,String imageFileName,String uploadPath,String realUploadPath);
}