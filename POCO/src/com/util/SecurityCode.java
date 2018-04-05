package com.util;

import java.util.Arrays;

    //SecurityCode��֤��
	//�����࣬�����������֤���ַ���

public class SecurityCode {

	//��֤����Ѷȼ���simple,ֻ�������֣�Medium�������ֺ�СдӢ����ĸ��Hard�������ֺʹ�СдӢ����ĸ
	//ö������
	public enum SecurityCodeLevel {Simple,Medium,Hard};

	//������λ��֤�룬�Ѷ��е�
	public static String getSecurityCode(){
			return getSecurityCode(4,SecurityCodeLevel.Medium,false);
	}
	
	
	//�������Ⱥ��Ѷ��������֤��
	/*
	 * @param length ��֤�볤��
	 * @param level �Ѷȵȼ�
	 * @param isCanRepeat �ܷ�����ظ������֣����Ϊtrue,����ܳ���2233�����İ����ظ����ֵģ����Ϊfalse�򲻻����
	 * @return
	 * 
	 * */
	
	public static String getSecurityCode(int length,SecurityCodeLevel level,boolean isCanRepeat){
		//�����ȡlen���ַ�
		int len = length;
		//�ַ����ϣ�ȥ�����׻������ַ�������0��1����ĸl,o��
		char[] codes = {'1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','f','h','i','j',
				'k','l','m','n','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','P','Q','R','S','T','U','V','W','X','Y','Z'};
		//���ݲ�ͬ�������ѡȡ�ַ�����
		if(level == SecurityCodeLevel.Simple){
			codes = Arrays.copyOfRange(codes, 0, 9); 
		}else if(level == SecurityCodeLevel.Medium){
			codes = Arrays.copyOfRange(codes, 0, 33);
		}
		
		//�ַ����ϵĳ���
		int n = codes.length;
		//�����Ĳ�������ʾ�����ֻ���ĸ�ķ�Χ���������еģ��������ظ�ʹ�����ֻ���ĸ
		if(len>n && isCanRepeat == false){
			String.format("����getSecurityCode�����쳣��ô�޸����length",len,level,isCanRepeat,n);
		}
		//��ų�ȡ�������ַ�
		char[] result = new char[len];
		//�ж��Ƿ�����ظ����ַ�
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



























