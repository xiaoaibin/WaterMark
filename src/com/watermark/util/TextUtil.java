package com.watermark.util;

/**
 * 文本工具类
 * @author aibinxiao
 * @date 2017年6月6日 下午4:21:37
 */
public class TextUtil {
	
	/**
	 * 分析文本水印中文字所占的宽度
	 * @param text
	 * @return
	 */
	public static int getTextLength(String text){
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
