package com.watermark.service;

import java.awt.AlphaComposite;
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
 * 多个图片水印
 * @author aibinxiao
 * @date 2017年6月6日 下午4:58:59
 */
public class MultiImageMarkServie implements MarkService {

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
			
			// 创建一个变量，指向我们添加的图片logo的水印
			String logoPath = realUploadPath + File.separator + LOGO;
			File logo = new File(logoPath);
			
			Image imageLogo = ImageIO.read(logo);// 通过ImageIo工具类去分析logo图片的高度宽度等信息
			int width1 = imageLogo.getWidth(null);
			int height1 = imageLogo.getHeight(null);
			
			// 设置文字水印的透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
			
			// 倾斜图片，参数1：倾斜角度（单位：幅度），所需需要通过Math将角度转换位幅度，参数23： 为旋转圆心坐标，设置为图片的中心，即高度和宽度的1/2
			g.rotate(Math.toRadians(30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);
			
			// X,Y坐标的设置，由于倾斜之后，X，Y坐标都在一定程度增大了，但是为了避免不必要的计算，我们直接扩大一倍，所以X和Y的向左向上做一个移动
			int x = -width/2;
			int y = -height/2;
			
			// 将宽度扩展1/2
			while(x < width * 1.5){
				
				y = -height/2;// 每次移动之后，y值做一个重新的设定，保证文字水印是从上到下的添加
				
				while(y < height * 1.5){ // 将高度扩展1/2，再通过循环最终完成多个水印的添加
					g.drawImage(imageLogo, x, y, null);
					y += height1 + 240; // 每次y坐标向下移动，并且在添加新的水印时，加上一定的高度间隔，设置为240px
				}
				
				x += width1 + 240; // 每次x坐标向右移动，并且在添加新的水印时，加上一定的宽度间隔，设置为240px
			}

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
