package com.util;

import java.util.Comparator;

import com.vo.WorksInfos;

public class MyComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		
			WorksInfos e1=(WorksInfos)o1;  
			WorksInfos e2=(WorksInfos)o2;   
	       if(e1.getVoteNum()<e2.getVoteNum())  
	        return 1;  
	       else  
	        return 0;  
	       }  

}
