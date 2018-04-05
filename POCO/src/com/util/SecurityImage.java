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
	 * 验证码生成器
	 * @author Administrator
	 */

public class SecurityImage {

	//生成验证码图片
	public static BufferedImage createImage(String securityCode){
		//验证码的长度
		int codeLength = securityCode.length();
		//字体的大小
		int fSize = 15;
		int fWidth = fSize+1;
		//图片宽度
		int width = codeLength * fWidth + 6;
		int height = fSize * 2 + 1;
		//图片
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		//获取画笔
		Graphics g = image.createGraphics();
		//设置背景色
		g.setColor(Color.lightGray);
		//填充背景
		g.fillRect(0, 0, width, height);
		//设置边框颜色
		g.setColor(Color.LIGHT_GRAY);
		//字体样式
		g.setFont(new Font("Arial",Font.BOLD,height-2));
		//绘制边框
		g.drawRect(0, 0, width-1, height-1);
		//绘制噪点
		Random rand = new Random();
		g.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<codeLength*6;i++){
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawRect(x, y, 1, 1);
		}
		
		//绘制验证码
		int codeY = height - 10;
		g.setColor(new Color(119,182,133));
		g.setFont(new Font("Georgia",Font.BOLD,fSize));
		
		for(int i=0;i<codeLength;i++){
			g.drawString(String.valueOf(securityCode.charAt(i)), i*16+5, codeY);
		}
		//关闭资源
		g.dispose();
		return image;		
	}
	
	private static ByteArrayInputStream converImageToStream(BufferedImage image){
		//输入流
		ByteArrayInputStream InputStream = null;
		//输出流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		//输出图片的格式jpeg
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
			
	//验证码的流格式
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
