package com.watermark.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;


/**
 * Java AWT类库实现缩略图
 * @author aibinxiao
 * @date 2017年6月9日 上午11:16:21
 */
public class ThumbnailAWTService {
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	
	public String thumbnail(File image, String imageFileName, String uploadPath, String realUploadPath){
		String thumFileName = "thum_" + imageFileName;
		OutputStream os = null;
		
		try {
			String des = realUploadPath + File.separator + thumFileName; 
			os = new FileOutputStream(des);
			Image image2 = ImageIO.read(image);
			int width = image2.getWidth(null);// 获取原图宽度
			int height = image2.getHeight(null);// 获取原图高度
			
			int rate1 = width/WIDTH;// 宽度缩略比例
			int rate2 = height/HEIGHT;// 高度缩略比例
			
			int rate = 0;
			if(rate1 > rate2){// 宽度缩略比例大于高度缩略比例，使用宽度缩略比例
				rate = rate1;
			}else{
				rate = rate2;
			}
			
			// 计算缩略图最终的宽度和高度
			int newWidth = width/rate;
			int newHeight = height/rate;
			
			//  创建一个缓存图片对象
			BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			
			// 将原图通过新的高度宽度绘制到缓存图片对象中 image.SCALE_SMOOTH平滑版本
			bufferedImage.getGraphics().drawImage(image2.getScaledInstance(newWidth, newHeight, image2.SCALE_SMOOTH), 0, 0, null);
			
			// "image/jpeg" 获取原图后缀名
			//String imageType = image.getName().substring(image.getName().indexOf(".")+1);
			String imageType = imageFileName.substring(imageFileName.indexOf(".")+1);
			// 通过ImageIO将生成的缓存图片对象输出到对应的输出文件中去
			ImageIO.write(bufferedImage, imageType, os);
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
		
		return uploadPath + File.separator + thumFileName;
	}
}

