# 💻1차 관통프로젝트

**9조 이종현, 신준희** 

## ✅ To do & Done

**기본기능 ( 오전 10시~ 오후 2시)**

- [x]  이벤트 람다식으로 구현
- [x]  DAOImpl search함수 구현
- [x]  DAOservice search함수 구현
- [x]  APTRentSAXHandler 완성
- [x]  HouseDealSAXHandler 완성

**추가기능 & 이슈사항 (오후 2시 ~ 오후 11시)** 

- [x]  환경정보 csv → xml 변환 (인코딩 이슈: 메모장을 통해 UTF-8로 변환함), 이 때 파싱하였을 때 태그 네임에 괄호가 포함이 되어있을 경우 element type must be followed by either attribute specifications, ">" or "/>"오류가 발생하여 업체(시설)명의 태그네임을 업체로 바꾸었습니다. (숫자 데이터 값에 공백이 포함되는 이슈 등) ⇒ 이 때 공백을 포함한 숫자는 Integer.parserInt()가 안된다는 것을 깨달았습니다.
- [x]  검색 결과가 없을 시 팝업 창 띄워주기 → JOptionPanel 사용. 검색 결과 배열의 크기가 0일때 나타나도록 구현
- [x]  전월세 계약을 검색하였을 때 rent 비용이 0이면 전세계약을 나타내는 것을 gui에 구현하기, 월세의 경우에는 월세 값이 그대로 출력된다. //이때 숫자인 rent 문자열에 공백이 포함되어 있어 파싱을 하면 NumberFormatException이 나타나는 이슈가 있어 공백포함 문자열을 비교하도록 수정하였습니다.
- [ ]  각 column 눌렀을 시 정렬하여주기 ⇒(문제점 : swing을 사용할 줄 몰라서 구현 못함, 구현부는 comparator를 사용하여 구현하면 되는 문제) ⇒ api document에 DefaultTableModel에서 setDataVector(Object[][] variable,Object[] variable2)를 진행하게 되면 앞의 변수들은 가변적이지만  뒤의 변수들은 고정이 되어서 만약 클릭했을 때 뒤의 변수를 통해 event를 발생시켜 listener를 통해 comparator를 진행하려고 하였지만 이를 구현하지 못하였습니다.
- [x]  환경정보 xml parsing을 통한 특정 동의 폐수 처리 시설 개수 확인(건물의 동과 관련하여 폐수 처리 시설 개수 확인) ⇒ 폐수 정보(EnvCnt) 속성 HouseDeal에 추가하고, xml에서 폐수시설의 동을 토크나이징 → 특정 동의 폐수 시설 개수를 HashMap에 메핑 → 주택, 아파트가 속한 동네의 폐수시설 개수 연결 완료. gui로는 나타내지 못하였습니다... //원래는 for문을 사용하여 cnt 값을 받아오려고 하였는 데 이를 수행하는 for문을 돌리게 된다면  아파트 280개, 환경에관한 리스트가 15000개여서 gui가 뜨게 되는 데 문제가 있었습니다. 이를 해결하기 위하여 앞에 HashMap으로 구현하였습니다. ⇒>>>>알고리즘의 중요성을 다시 한 번 깨달았습니다. 각 건물의 근처 폐수 개수를 콘솔 상에서 확인을 할 수 있습니다.  // 위의 내용을 Env 클래스, EnvSaxpasrer, EnvSaxHandler로 구현하였습니다.
- [x]  HashMap에서 값을 검색할 때, HashMap 객체가 늦게 생성되거나, key값이 없어 null을 리턴할 경우 이를 고려하지 않고 integer 속성에 null 값을 세팅하는 등 다양한 NullPointerException에러가 발생하여 HashMap 객체 생성 시점을 수정하고 null을 리턴할 경우와 그렇지 않을 경우 로직을 분기하여 해결하였습니다.
- [x]  이미지 가져오기  ⇒ HouseInfo의 img값을 HouseDeal에서 img값을 가져오지 못하여서 HouseInfo의 key값인 dong과aptName을 가지고 HouseDeal.setImg를 하였습니다.
- [x]  건물의 번호 순서대로 GUI상에 출력하였습니다. HouseDeal에 Comparable interface의 compareTo() 메소드를 오버라이딩하여 구현하였습니다.

**심화기능 (오후 11시~12시)** 

**⇒ 추가기능을 구현하느라 시간을 다 허비하여 구현하지 못하였습니다.**

- [ ]  상가정보 csv 읽고 화면에 보여주기(....)
- [ ]  이 csv file을 xml로 변환하는 데 문제가 있었습니다.
- [ ]  csv file 그 자체를 parsing하려고 하였지만 이해하는 데 어려움이 있었습니다.

## 🏬 **수행결과**

- **기본 화면(번호 오름차순 정렬, 이미지 변화)**

![기본화면](/uploads/7f88f2bb6758f0e87a63a42bcaf409ee/기본화면.PNG)

- **전세의 경우 월세금액에 '전세계약' 표기**

![전세계약_표시](/uploads/07d7312882c9d4201d351e56412e5bab/전세계약_표시.PNG)

- **검색 결과가 없을 경우 알림창 띄우기**

![검색결과](/uploads/664c20bacd5d2ef63eefcda79271b8f0/검색결과.PNG)

- **선택한 아파트/주택 근처 폐수 처리 시설 개수 계산**

![폐수시설](/uploads/a325550584827e4b763e400b1323cbc8/폐수시설.PNG)
