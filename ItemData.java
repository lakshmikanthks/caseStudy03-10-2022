package com.gl.caseStudy2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemData  {
	private static Map<String,SnackItem> itemMap;
	static {
		try {
		FileReader fileRead = new FileReader("D:/Training/CaseStudy/SnackItem.txt");
		BufferedReader bReader = new BufferedReader(fileRead);
		itemMap = new HashMap<>();
		while(true) {
			String str = bReader.readLine();
			if(str==null)
				break;
			String strSplit[] = str.split("-");
			if(strSplit[3].equalsIgnoreCase("N")) {
				continue;
			}
			SnackItem snackItem = new SnackItem(strSplit[0],strSplit[1],strSplit[2],strSplit[3],strSplit[4]);
			itemMap.put(strSplit[0],snackItem);
		}
		}catch(Exception e) {
			System.out.println("Not able to read the file");
		}
		
	}
	
	public static Map<String,SnackItem> getAllItems(){
		 return itemMap;
	}
	
	public static SnackItem getItem(String itemName) {
		 return itemMap.get(itemName);
	}
	
	public static boolean isAvailable(String itemName) {
		return itemMap.containsKey(itemName);
	}

}
