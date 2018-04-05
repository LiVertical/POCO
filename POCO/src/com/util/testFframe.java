package com.util;

import java.awt.Color;

import javax.swing.JFrame;

public class testFframe {

	public static void main(String[] args) {
		//java是可以绘制页面程序的
		JFrame jf = new JFrame();
		jf.setBackground(Color.blue);
		jf.setSize(400, 300);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

}
