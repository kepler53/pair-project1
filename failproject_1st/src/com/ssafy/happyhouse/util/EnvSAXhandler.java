package com.ssafy.happyhouse.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.model.dto.Env;
import com.ssafy.happyhouse.model.dto.HouseInfo;

public class EnvSAXhandler extends DefaultHandler {

	private List<Env> envs;
	private Env env;

	private String temp;
	private StringBuilder sb;

	public EnvSAXhandler() {
		envs = new LinkedList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		sb = new StringBuilder();

		if (qName.equals("소재지주소")) {
			env = new Env(); 
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);

		if (env != null) {
			if (qName.equals("소재지주소")) {
				System.out.println(sb);
				env.setAddress(sb.toString());
			}
			envs.add(env); // 호출타이밍 문제 
		}
		//나의 생각은 여기에서 envs.add(env); 이부분
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);

		sb.append(ch, start, length);
	}
	
	public List<Env> getEnvInfo() {
		
		return envs;
	}
	
	

}
