package com.ssafy.happyhouse.util;

import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class JongnoEnvParser {
	
	public JongnoEnvParser() {
		loadData();
	}
	
	//xml 파일 파싱.
	private void loadData() {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		String envInfoFilePath = "res/JongnoEnv.xml";
		
		try {
			SAXParser parser = factory.newSAXParser();
			HouseSAXHandler handler = new HouseSAXHandler();
			parser.parse(envInfoFilePath, handler);
		}catch(Exception e) {
			System.out.println("파싱에러!");
		}
		
	}
}
