package com.ssafy.happyhouse.util;

import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.happyhouse.model.dto.Env;

public class EnvSaxParser {

	private List<Env> envList;
	private int size;
	private HashMap<String, Integer> envMap;
	
	public EnvSaxParser() {
		loadData();
	}

	private void loadData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		envMap = new HashMap<>();
		
		String path = "res/EnvInfo.xml"; //데이터 주소
		
		try {
			SAXParser parser = factory.newSAXParser();
			EnvSAXhandler handler = new EnvSAXhandler();
			parser.parse(path, handler);
			envList = handler.getEnvInfo();
			size = envList.size();
			
			////////map ///////
			for(int i=0; i<size; i++) {
			StringTokenizer st = new StringTokenizer(envList.get(i).getAddress());
			int cnt = 0;
			while(st.hasMoreTokens()) {
				String dong = st.nextToken();
				System.out.println(cnt+ dong);
				if(cnt == 2) { //동 메핑
					if(!envMap.containsKey(dong))
						envMap.put(dong, 1);
					else
					{
						int val = envMap.get(dong);
						envMap.put(dong, val+1);
					}
				}
				cnt++;
			}
				
			}
			///////////////////
			
			
			
		}catch (Exception e) { //여러 에러 한꺼번에 처리, 그냥 무슨 에러인지 모름
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public List<Env> getEnvList() {
		return envList;
	}

	public void setEnvList(List<Env> envList) {
		this.envList = envList;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public HashMap<String,Integer> getEnvMap(){
		return envMap;
	}
	
	public static void main(String[] args) {
		new EnvSaxParser();
	}
	
	
	
}
