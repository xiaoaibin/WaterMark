package com.watermark.entity;

/**
 * 图片信息类
 * @author aibinxiao
 * @date 2017年6月6日 上午11:13:59
 */
public class PicInfo {
	private String imageURL; // 上传图片的返回路径
	private String logoImageURL; // 添加水印的返回路径
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getLogoImageURL() {
		return logoImageURL;
	}
	public void setLogoImageURL(String logoImageURL) {
		this.logoImageURL = logoImageURL;
	}
	
	@Override
	public String toString() {
		return "PicInfo [imageURL=" + imageURL + ", logoImageURL=" + logoImageURL + "]";
	}
	
}
