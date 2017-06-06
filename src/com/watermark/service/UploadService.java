package com.watermark.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片上传逻辑
 * @author aibinxiao
 * @date 2017年6月6日 上午10:52:49
 */
public class UploadService {
	
	/**
	 * 上传图片的方法
	 * @param Image 上传的图片
	 * @param imageFileName 上传图片的名称
	 * @param uploadPath 上传图片的相对路径
	 * @param realUploadPath 上传的绝对路径
	 * @return 上传图片后相对的返回路径
	 */
	public String uploadImage(File image,String imageFileName,String uploadPath,String realUploadPath){
		// 思路：
		// 1.通过输入流使用循环不断去读取图片数据，如果读到了数据，说明没有读完，
		// 2.然后将图片数据通过输出流写入我们的目标文件里
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(image); // 输入流读取图片
			os = new FileOutputStream(realUploadPath+File.separator+imageFileName); // 输出流，指向我们最终要保存的路径，即绝对路径+文件名
			
			byte[] buffer = new byte[1024]; // 创建一个字节数组，用来保存每次读取到的图片数据
			int len = 0; // 用来表示我们每次读取到的数组长度
			
			while((len=is.read(buffer))>0){
				os.write(buffer); // 如果读取数组长度大于0，说明读取到了图片数据，然后我们通过输出流，写入到目标文件中
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(is!=null){
				try {
					is.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if(os!=null){
				try {
					os.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return uploadPath + File.separator + imageFileName;
	}
}
