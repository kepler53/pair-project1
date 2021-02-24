//
//package com.ssafy.happyhouse.util;
//
//import java.util.HashMap;
//import java.util.StringTokenizer;
//
//import org.xml.sax.Attributes;
//import org.xml.sax.helpers.DefaultHandler;
//
//import com.ssafy.happyhouse.model.dto.HouseInfo;
//
//public class JongnoEnvHandler extends DefaultHandler {
//	private HashMap<String, Integer> map;
//	String temp;
//	StringBuilder sb;
//	boolean flag;
//	/*
//	 * 맵에 동을 입력 -> 시설 개수 리턴 만약 동이 입력x인 상태 -> null이 리턴 -> map.put(동이름, 1); 동이 이미 입력 된
//	 * 상태 -> cnt = map.get(동이름); map.put(동이름, cnt+1);
//	 */
//
//	public JongnoEnvHandler() {
//		map = new HashMap<>();
//	}
//
//	public void startElement(String uri, String localName, String qName, Attributes att) {
//		if (qName.equals("소재지주소")) {
//			sb = new StringBuilder("");
//			flag = true;
//		}
//	}
//
//	public void endElement(String uri, String localName, String qName) {
//		
//		if(qName.equals("소재지주소")) {
//			StringTokenizer st = new StringTokenizer(sb.toString());
//			int cnt = 1;
//			int value;
//			String unit;
//			while (st.hasMoreTokens()) {
//				unit = st.nextToken();
//				if (cnt == 3) {// 동이 등장
//					if (unit.contains("동")) {
//						if (map.get(unit) == null) {
//							map.put(unit, 1);
//						} else {
//							value = map.get(unit);
//							map.put(unit, value+1);
//						}
//					}
//				}
//				cnt++;
//			}
//			
//		}
//	}
//
//	public void characters(char[] ch, int start, int length) {
//		temp = new String(ch, start, length);
//		sb.append(temp);
//	}
//
//}
