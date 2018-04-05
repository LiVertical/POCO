package com.util;

import java.util.Arrays;

    //SecurityCode验证码
	//工具类，生成随机的验证码字符串

public class SecurityCode {

	//验证码的难度级别，simple,只包含数字，Medium包含数字和小写英文字母，Hard包含数字和大小写英文字母
	//枚举类型
	public enum SecurityCodeLevel {Simple,Medium,Hard};

	//生成四位验证码，难度中等
	public static String getSecurityCode(){
			return getSecurityCode(4,SecurityCodeLevel.Medium,false);
	}
	
	
	//产生长度和难度任意的验证码
	/*
	 * @param length 验证码长度
	 * @param level 难度等级
	 * @param isCanRepeat 能否出现重复的数字，如果为true,则可能出现2233这样的包含重复数字的，如果为false则不会出现
	 * @return
	 * 
	 * */
	
	public static String getSecurityCode(int length,SecurityCodeLevel level,boolean isCanRepeat){
		//随机抽取len个字符
		int len = length;
		//字符集合（去除容易混淆的字符，数字0，1，字母l,o）
		char[] codes = {'1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','f','h','i','j',
				'k','l','m','n','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','P','Q','R','S','T','U','V','W','X','Y','Z'};
		//根据不同放入情况选取字符集合
		if(level == SecurityCodeLevel.Simple){
			codes = Arrays.copyOfRange(codes, 0, 9); 
		}else if(level == SecurityCodeLevel.Medium){
			codes = Arrays.copyOfRange(codes, 0, 33);
		}
		
		//字符集合的长度
		int n = codes.length;
		//传进的参数，显示的数字或字母的范围大于我所有的，还不能重复使用数字或字母
		if(len>n && isCanRepeat == false){
			String.format("调用getSecurityCode出现异常那么修改入参length",len,level,isCanRepeat,n);
		}
		//存放抽取出来的字符
		char[] result = new char[len];
		//判断是否出现重复的字符
		if(isCanRepeat){
			for(int i=0;i<result.length;i++){
				int r = (int) (Math.random()*n);
				result[i] = codes[r];
			}
		}else{
			for(int i=0;i<result.length;i++){
				int r = (int) (Math.random()*n);
				result[i] = codes[r]; 
				codes[r] = codes[n-1];
				n--;
			}
		}		
		return String.valueOf(result);
	}

	public static void main(String[] args) {
		SecurityCode sc = new SecurityCode();
		String test = sc.getSecurityCode();
		System.out.println(test);	
 }
}



























