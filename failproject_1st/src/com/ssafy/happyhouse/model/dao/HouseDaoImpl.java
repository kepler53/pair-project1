package com.ssafy.happyhouse.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.Env;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.util.EnvSaxParser;
import com.ssafy.happyhouse.util.HouseSaxParser;

public class HouseDaoImpl implements HouseDao{
	
	private Map<String, HouseInfo> houseInfo;
	private Map<String, List<HouseDeal>> deals;
	private int size;
	private List<HouseDeal> search;
	private String[] searchType= {HouseDeal.APT_DEAL, HouseDeal.APT_RENT, HouseDeal.HOUSE_DEAL, HouseDeal.HOUSE_RENT};
	private List<Env> envList;
	private HashMap<String, Integer> envMap;
	public HouseDaoImpl() {
		loadData();
	}
	
	/**
	 * 아파트 정보와 아파트 거래 정보를  xml 파일에서 읽어온다.
	 */
	public void loadData() {
		HouseSaxParser parser = new HouseSaxParser();
		houseInfo = parser.getHouseInfo();
		deals = parser.getDeals();
		size = parser.getSize();
		search = new ArrayList<HouseDeal>(size);
		EnvSaxParser parser2 = new EnvSaxParser();
		envList = parser2.getEnvList();
		envMap = parser2.getEnvMap();
	}
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 주택 목록
	 */
	
//	private Map<String, HouseInfo> houseInfo;
//	private Map<String, List<HouseDeal>> deals;
//	private int size;
//	private List<HouseDeal> search;
	public List<HouseDeal> searchAll(HousePageBean  bean){
		search.clear();
		List<HouseDeal> finds = new LinkedList<HouseDeal>();
		boolean[] type = bean.getSearchType();
		for (int i = 0, size=type.length; i < size; i++) {
			if(type[i]) {
				System.out.println(searchType[i]);
				search.addAll(deals.get(searchType[i]));
			}
		}
		
		for(int i=0; i<search.size(); i++) {
			String key = search.get(i).getDong()+search.get(i).getAptName();
			if(houseInfo.get(key)!=null) 
				search.get(i).setImg(houseInfo.get(key).getImg());
		}
		
		//////////////////////////////////////////////
	//	for(int i=0; i<search.size(); i++) {
	//		String dong = search.get(i).getDong();
	//		int cnt = 0;
	//		for(int j=0; j<envList.size(); j++) {
	//			if(envList.get(i).getAddress().contains(dong))
	//				cnt++;
	//		}
	//		search.get(i).setEnvCnt(cnt);
	//	}
		//////////////////////////////////////////////////
		
		//////////////////////////////////////////////
		for(int i=0; i<search.size(); i++) {
			String dong = search.get(i).getDong();
			if(envMap.get(dong) != null) {
				search.get(i).setEnvCnt(envMap.get(dong));
			}
			else
				search.get(i).setEnvCnt(0);
//			System.out.println("폐수 개수 "+envMap.get(dong));
		}
		//////////////////////////////////////////////////

		
		System.out.println(search);
		
		String dong = bean.getDong();
		String aptName = bean.getAptname();
		String keyword = bean.getKeyword();
		if(dong!=null) {	
			for (HouseDeal deal : search) {
				if(deal.getDong().contains(dong)) {
					finds.add(deal);
				}
			}
		}else if(aptName != null) {
			for (HouseDeal deal : search) {
				if(deal.getAptName().contains(aptName)) {
					finds.add(deal);
				}
			}
		}else if(keyword != null){
			for (HouseDeal deal : search) {
				if(deal.getAptName().contains(keyword)) {
					finds.add(deal);
				}
			}
		}else {
		finds = search;
		}
		return finds;
	}
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환한다.<br/>
	 * 법정동+아파트명을 이용하여 HouseInfo에서 건축연도, 법정코드, 지번, 이미지 정보를 찾아서 HouseDeal에 setting한 정보를 반환한다. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	//주석02번 문제
	
//	private Map<String, HouseInfo> houseInfo;
//	private Map<String, List<HouseDeal>> deals;
//	private int size;
//	private List<HouseDeal> search;
	
	//null값을 넘기는 거 
	
	public HouseDeal search(int no) { 
		HouseDeal houseDeal = new HouseDeal();
		for (int i = 0; i < search.size(); i++) {
			if(no==search.get(i).getNo()) {
				houseDeal = search.get(i);
				}
		}
		System.out.println("근처 폐수 방류 지역"+houseDeal.getEnvCnt()+"개");
		return houseDeal;
	}
	
//	public static void main(String[] args) {
//		HouseDaoImpl dao = new HouseDaoImpl();
//		//dao.loadData();
//		System.out.println(dao.search(1));
//		System.out.println("===========================법정동으로 검색=================================");
//		HousePageBean dongSearch = new HousePageBean();
//		dongSearch.setDong("사직동");
//		print(dao.searchAll(dongSearch));
//		
//		System.out.println("===========================아파트 이름으로 검색=================================");
//		HousePageBean nameSearch = new HousePageBean();
//		nameSearch.setAptname("신동아블루아광화문의 꿈");
//		print(dao.searchAll(nameSearch));
//		
//		System.out.println("===========================전체 검색=================================");
//		print(dao.searchAll(null));
//		System.out.println("============================================================");
//	}
	
	public static void print(List<HouseDeal> houses) {
		for (HouseDeal house : houses) {
			System.out.println(house);
		}
	}
}
