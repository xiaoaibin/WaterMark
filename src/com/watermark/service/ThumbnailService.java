package com.watermark.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;

/**
 * ThumbnailService
 * 将上传的图进行等比例缩略
 * @author aibinxiao
 * @date 2017年6月9日 上午11:00:46
 */
public class ThumbnailService {
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	
	public String thumbnail(File image, String imageFileName, String uploadPath, String realUploadPath){
		String thumFileName = "thum_" + imageFileName;
		InputStream is =null;
		
		try {
			is = new FileInputStream(image);
			String des = realUploadPath + File.separator + thumFileName; 
			Thumbnails.of(is).size(WIDTH, HEIGHT).toFile(des);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is!=null){
				try {
					is.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return uploadPath + File.separator + thumFileName;
	}
}
