package com.ssafy.happyhouse.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.happyhouse.model.dto.Env;

public class EnvSaxParser {

	private List<Env> envList;
	private int size;
	
	public EnvSaxParser() {
		loadData();
	}

	private void loadData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		String path = "res/EnvInfo.xml"; //데이터 주소
		
		try {
			SAXParser parser = factory.newSAXParser();
			EnvSAXhandler handler = new EnvSAXhandler();
			parser.parse(path, handler);
			envList = handler.getEnvInfo();
			
			
			
			size = envList.size();
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
	
	public static void main(String[] args) {
		new EnvSaxParser();
	}
	
}
