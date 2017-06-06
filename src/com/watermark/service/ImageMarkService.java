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
 * 为图片添加单个图片水印
 * @author aibinxiao
 * @date 2017年6月6日 下午3:32:51
 */
public class ImageMarkService implements MarkService {

	@Override
	public String watermark(File image, String imageFileName, String uploadPath, String realUploadPath) {
		String logoFileName = "logo_" + imageFileName;
		OutputStream os = null;
		
		// logo水印所在路径
		String logoPath = realUploadPath + File.separator + LOGO;
		
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
			
			// 为图片添加一个图片水印
			File logo = new File(logoPath);
			Image imageLogo = ImageIO.read(logo);// 通过ImageIo工具类去分析logo图片的高度宽度等信息
			int width1 = imageLogo.getWidth(null);
			int height1 = imageLogo.getHeight(null);
			
			// 计算原图与图片水印的高度、宽度差,防止水印过大，不能全部在图片上
			int widthDiff = width - width1;
			int heightDiff = height - height1;
			
			int x = X;
			int y = Y;
			
			if(x>widthDiff){//如果x坐标大于宽度差，则表示图片水印无法在横向无法完整显示，所以设置为最大值，即宽度差
				x = widthDiff;
			}
			
			if(y>heightDiff){// 同x
				y = heightDiff;
			}
			
			// 设置文字水印的透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
			
			g.drawImage(imageLogo, x, y, null);
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

	

}
