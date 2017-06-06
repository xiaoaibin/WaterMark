package com.watermark.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.watermark.entity.PicInfo;
import com.watermark.service.ImageMarkService;
import com.watermark.service.MultiImageMarkServie;
import com.watermark.service.MultiTextMarkService;
import com.watermark.service.TextMarkService;
import com.watermark.service.UploadService;

/**
 * 批量水印处理的Action
 * @author aibinxiao
 * @date 2017年6月6日 上午10:44:07
 */
public class BatchWaterMarkAction extends ActionSupport{
	private File[] image; // 要上传的图片文件数组
	private String[] imageFileName; // 要上传的图片文件名数组
	private String uploadPath; // 要上传的图片文件相对路径

	
	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	private List<PicInfo> picInfo = new ArrayList<PicInfo>();

	public List<PicInfo> getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(List<PicInfo> picInfo) {
		this.picInfo = picInfo;
	}

	/**
	 * 批量处理添加单个文字水印的图片上传以及添加文字水印的添加请求
	 * @return
	 * @throws Exception
	 */
	public String textWaterMark() throws Exception{
		// 绝对路径，是基于我们的相对路径来获取的
		String realUploadPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		UploadService uploadService = new UploadService();
		TextMarkService textMarkService = new TextMarkService();
		
		// 对上传文件数组做一个判断
		if(image!=null && image.length>0){
			for (int i=0;i<image.length;i++) {
				PicInfo pic = new PicInfo();
				// 设置图片上传后返回的相对路径
				pic.setImageURL(uploadService.uploadImage(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 设置文字水印
				pic.setLogoImageURL(textMarkService.watermark(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 将每个处理好的，添加到数组中
				picInfo.add(pic);
			}
		}	
		
		return SUCCESS;
	}
	
	/**
	 * 批量处理添加多个文字水印的图片上传以及添加文字水印的添加请求
	 * @return
	 * @throws Exception
	 */
	public String multiTextWaterMark() throws Exception{
		// 绝对路径，是基于我们的相对路径来获取的
		String realUploadPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		UploadService uploadService = new UploadService();
		MultiTextMarkService multiTextMarkService = new MultiTextMarkService();
		
		// 对上传文件数组做一个判断
		if(image!=null && image.length>0){
			for (int i=0;i<image.length;i++) {
				PicInfo pic = new PicInfo();
				// 设置图片上传后返回的相对路径
				pic.setImageURL(uploadService.uploadImage(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 设置文字水印
				pic.setLogoImageURL(multiTextMarkService.watermark(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 将每个处理好的，添加到数组中
				picInfo.add(pic);
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 批量处理添加单个图片水印的图片上传以及添加文字水印的添加请求
	 * @return
	 * @throws Exception
	 */
	public String imageWaterMark() throws Exception{
		// 绝对路径，是基于我们的相对路径来获取的
		String realUploadPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		UploadService uploadService = new UploadService();
		ImageMarkService imageMarkService = new ImageMarkService();
		
		// 对上传文件数组做一个判断
		if(image!=null && image.length>0){
			for (int i=0;i<image.length;i++) {
				PicInfo pic = new PicInfo();
				// 设置图片上传后返回的相对路径
				pic.setImageURL(uploadService.uploadImage(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 设置文字水印
				pic.setLogoImageURL(imageMarkService.watermark(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 将每个处理好的，添加到数组中
				picInfo.add(pic);
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 批量处理添加多个图片水印的图片上传以及添加文字水印的添加请求
	 * @return
	 * @throws Exception
	 */
	public String multiImageWaterMark() throws Exception{
		// 绝对路径，是基于我们的相对路径来获取的
		String realUploadPath = ServletActionContext.getServletContext().getRealPath(uploadPath);
		UploadService uploadService = new UploadService();
		MultiImageMarkServie multiImageMarkService = new MultiImageMarkServie();
		
		// 对上传文件数组做一个判断
		if(image!=null && image.length>0){
			for (int i=0;i<image.length;i++) {
				PicInfo pic = new PicInfo();
				// 设置图片上传后返回的相对路径
				pic.setImageURL(uploadService.uploadImage(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 设置文字水印
				pic.setLogoImageURL(multiImageMarkService.watermark(image[i], imageFileName[i], uploadPath, realUploadPath));
				// 将每个处理好的，添加到数组中
				picInfo.add(pic);
			}
		}
		
		return SUCCESS;
	}
}
