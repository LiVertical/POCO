package com.util;

import java.util.UUID;

public class UUIDUtil {
	
	/**
	 * 随机生成一个符合UUID定义的字符串，
	 * 格式 8-4-4-4-12, 如 84c19de9-37b0-4234-b4c5-e3cd63cda257
	 */
	public static String generateUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 随机生成一个符合UUID定义的字符串，hashCode 值为正
	 */
	public static String generatePositiveUUID(){
		while(true){
			String str = UUID.randomUUID().toString();
			if(str.hashCode() > 0)
				return str;
			else
				continue;
		} 
	}
	
	/**
	 * 随机生成一个符合UUID定义的字符串，hashCode 值为负
	 */
	public static String generateNegativeUUID(){
		while(true){
			String str = UUID.randomUUID().toString();
			if(str.hashCode() < 0)
				return str;
			else
				continue;
		} 
	}
}
