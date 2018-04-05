package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
     /*
	 * ��֤��������
	 * @author Administrator
	 */

public class SecurityImage {

	//������֤��ͼƬ
	public static BufferedImage createImage(String securityCode){
		//��֤��ĳ���
		int codeLength = securityCode.length();
		//����Ĵ�С
		int fSize = 15;
		int fWidth = fSize+1;
		//ͼƬ���
		int width = codeLength * fWidth + 6;
		int height = fSize * 2 + 1;
		//ͼƬ
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		//��ȡ����
		Graphics g = image.createGraphics();
		//���ñ���ɫ
		g.setColor(Color.lightGray);
		//��䱳��
		g.fillRect(0, 0, width, height);
		//���ñ߿���ɫ
		g.setColor(Color.LIGHT_GRAY);
		//������ʽ
		g.setFont(new Font("Arial",Font.BOLD,height-2));
		//���Ʊ߿�
		g.drawRect(0, 0, width-1, height-1);
		//�������
		Random rand = new Random();
		g.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<codeLength*6;i++){
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawRect(x, y, 1, 1);
		}
		
		//������֤��
		int codeY = height - 10;
		g.setColor(new Color(119,182,133));
		g.setFont(new Font("Georgia",Font.BOLD,fSize));
		
		for(int i=0;i<codeLength;i++){
			g.drawString(String.valueOf(securityCode.charAt(i)), i*16+5, codeY);
		}
		//�ر���Դ
		g.dispose();
		return image;		
	}
	
	private static ByteArrayInputStream converImageToStream(BufferedImage image){
		//������
		ByteArrayInputStream InputStream = null;
		//�����
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		//���ͼƬ�ĸ�ʽjpeg
		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
		
		try {
			jpeg.encode(image);
			byte[] bts = bos.toByteArray();
			InputStream = new ByteArrayInputStream(bts);
		} catch (ImageFormatException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return InputStream;		
	}
			
	//��֤�������ʽ
	public static ByteArrayInputStream getImageAsInputStream(String securityCode){
		BufferedImage image = null;
		try {
			image = createImage(securityCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return converImageToStream(image);
	}
	
	
	public static void main(String[] args) {
		SecurityCode sc = new SecurityCode();
		String test = sc.getSecurityCode();
		Graphics img = SecurityImage.createImage(test).getGraphics();
		img.drawLine(0, 10, 30, 100);
	}

}
