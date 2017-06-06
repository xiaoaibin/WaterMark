package com.watermark.service;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 为图片添加单个文字水印
 * @author aibinxiao
 * @date 2017年6月6日 上午11:54:47
 */
public class TextMarkService implements MarkService {

	@Override
	public String watermark(File image, String imageFileName, String uploadPath, String realUploadPath) {
		
		String logoFileName = "logo_" + imageFileName;
		OutputStream os = null;
		
		try {
			Image image2 = ImageIO.read(image); // ImageIO工具类，通过read方法去解码对应的图片文件，解码成一个相应的图片对象，在这个对象中就存储这图片的高度、宽度等信息
			int width = image2.getWidth(null);
			int height = image2.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);// 三个参数，图片的高度、宽度、颜色信息
			// BufferedImage.TYPE_INT_RGB 图像颜色设置，用我们这个对象具有整数像素的8位RGB颜色
			
			// 创建绘图工具，专门用于二维图像和文本的处理
			Graphics2D g = bufferedImage.createGraphics();
			// 将原图通过绘图工具绘制到缓存图片对象
			g.drawImage(image2, 0, 0, width, height, null);
			// 参数：1.原图; 23.绘制坐标：因为原图和缓存图片对象高度宽度一致，所以绘制过程中坐标为0; 45.绘制图片的高度宽度：设置为原图的高度宽度即可
			
			// 为图片添加一个文字水印
			g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));// 设置文字水印的字体信息
			g.setColor(FONT_COLOR);// 设置文字水印的颜色
			
			// 获取文字水印的高度、宽度值
			int width1 = FONT_SIZE*getTextLength(MARK_TEXT);
			int height1 = FONT_SIZE;
			
			// 计算原图与文字水印的高度、宽度差,防止水印过大，不能全部在图片上
			int widthDiff = width - width1;
			int heightDiff = height - height1;
			
			int x = X;
			int y = Y;
			
			if(x>widthDiff){//如果x坐标大于宽度差，则表示文字水印无法在横向无法完整显示，所以设置为最大值，即宽度差
				x = widthDiff;
			}
			
			if(y>heightDiff){// 同x
				y = heightDiff;
			}
			
			// 设置文字水印的透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
			g.drawString(MARK_TEXT, x, y + FONT_SIZE);// 注意y坐标需要坐小的调整，需要加上文字大小的参数，因为y坐标值指向的位置是文字水印文本内容的下方，如果不加会导致文字水印靠上显示，导致文字水印显示不全
			g.dispose();// 释放图片绘制工具，此时添加了水印的图片已经生成，但仍然在内存中
			
			// 创建文件输出流，指向最终目标文件
			os = new FileOutputStream(realUploadPath + File.separator + logoFileName);
			
			// 创建图像编码工具类
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			// 使用图片编码工具类对象将内存中的图片对象输出到目标文件中去
			en.encode(bufferedImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(os!=null){
				try {
					os.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return uploadPath + File.separator + logoFileName;// 返回添加水印后的图像路径
	}
	
	/**
	 * 分析文本水印中文字所占的宽度
	 * @param text
	 * @return
	 */
	public int getTextLength(String text){
		int length = text.length();
		
		// 循环分析文本中的每一个字符
		for(int i=0;i<length;i++){
			String s = String.valueOf(text.charAt(i));
			if(s.getBytes().length>1){// 如果字节长度大于1，则为中文
				length++;
			}// 如果是因为字符则不进行处理
		}
		
		// 最后做折半处理
		length = length%2==0?length/2:length/2+1;
		return length;
	}

}
