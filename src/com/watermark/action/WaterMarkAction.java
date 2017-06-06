package com.watermark.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.watermark.entity.PicInfo;
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
	 * 默认请求处理方法，接收用户的图片上传以及水印的添加和请求
	 * @return
	 * @throws Exception
	 */
	public String watermark() throws Exception{
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
}
