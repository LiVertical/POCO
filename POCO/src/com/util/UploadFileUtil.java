package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadFileUtil {
	
	private static final int BUFFER_SIZE = 1024 * 1024;
	
	public void uploadImgs(File srcFile, File dstFile) throws Exception{
		copy(srcFile, dstFile);
	}
	
	
	// 上传设置大小的方法
		private static void copy(File src, File dst) {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				while ((len = in.read(buffer)) > 0) {
					// 每次读多少，从哪里开始，到哪里结束
					out.write(buffer, 0, len);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != in) {
					try {
						in.close();
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != out) {
					try {
						out.close();
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

}
