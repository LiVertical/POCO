package com.util;

import java.util.List;


public class Page<T> {
	
	private List<T> data;
	
	private int count;
	
	public Page(List<T> data,int count){
		this.data=data;
		this.count=count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
