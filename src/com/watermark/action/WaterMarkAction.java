package com.watermark.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.watermark.entity.PicInfo;
import com.watermark.service.ImageMarkService;
import com.watermark.service.MultiTextMarkService;
import com.watermark.service.TextMarkService;
import com.watermark.service.UploadService;

/**
 * 水印处理的Action
 * @author aibinxiao
 * @date 2017年6月6日 上午10:44:07
 */
public class WaterMarkAction extends ActionSupport{
	private File image; // 要上传的图片文件
	private String imageFileName; // 要上传的图片文件名
	private String uploadPath; // 要上传的图片文件相对路径

	
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	PicInfo picInfo = new PicInfo();

	public PicInfo getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(PicInfo picInfo) {
		this.picInfo = picInfo;
	}

	/**
	 * 添加单个文字水印的图片上传以及添加文字水印的添加请求
	 * @return
	 * @throws Exception
	 */
	public String textWaterMark() throws Exception{
		// 绝对路径，是基于我们的相对路径来获取的
		String realUploadPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		UploadService uploadService = new UploadService();
		TextMarkService textMarkService = new TextMarkService();
		// 设置图片上传后返回的相对路径
		picInfo.setImageURL(uploadService.uploadImage(image, imageFileName, uploadPath, realUploadPath));
		// 设置文字水印
		picInfo.setLogoImageURL(textMarkService.watermark(image, imageFileName, uploadPath, realUploadPath));
		
		return SUCCESS;
	}
	
	/**
	 * 添加多个文字水印的图片上传以及添加文字水印的添加请求
	 * @return
	 * @throws Exception
	 */
	public String multiTextWaterMark() throws Exception{
		// 绝对路径，是基于我们的相对路径来获取的
		String realUploadPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		UploadService uploadService = new UploadService();
		MultiTextMarkService multiTextMarkService = new MultiTextMarkService();
		// 设置图片上传后返回的相对路径
		picInfo.setImageURL(uploadService.uploadImage(image, imageFileName, uploadPath, realUploadPath));
		// 设置文字水印
		picInfo.setLogoImageURL(multiTextMarkService.watermark(image, imageFileName, uploadPath, realUploadPath));
		
		return SUCCESS;
	}
	
	/**
	 * 添加单个图片水印的图片上传以及添加文字水印的添加请求
	 * @return
	 * @throws Exception
	 */
	public String imageWaterMark() throws Exception{
		// 绝对路径，是基于我们的相对路径来获取的
		String realUploadPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		UploadService uploadService = new UploadService();
		ImageMarkService imageMarkService = new ImageMarkService();
		// 设置图片上传后返回的相对路径
		picInfo.setImageURL(uploadService.uploadImage(image, imageFileName, uploadPath, realUploadPath));
		// 设置文字水印
		picInfo.setLogoImageURL(imageMarkService.watermark(image, imageFileName, uploadPath, realUploadPath));
		
		return SUCCESS;
	}
}
