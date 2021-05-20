# Project2
POS Management Web Application in Spring MVC

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![MIT License][license-shield]][license-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
    <img src="/static/image/logo_dark_big/logo.png" alt="Logo" align="center" width="300" height="300">
  <h3 align="center">Agile Office</h3>

  <p align="center">
    <br />
    <br />
    <br />
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#Agile Office">Agile Office</a>
      <ul>
        <li><a href="#개요">개요</a></li>
        <li><a href="#Built-in">Built-in</a></li>
      </ul>
    </li>
    <li>
      <a href="#설계">설계</a>
      <ul>
        <li><a href="#Draw.io">Draw.io 설계</a></li>
        <li><a href="#DB">DB 설계</a></li>
        <li><a href="#디자인패턴">디자인 패턴</a></li>
      </ul>
    </li>
    <li>
      <a href="#구현">구현</a>
      <ul>
        <li><a href="#common_라이브러리">common 라이브러리</a></li>
        <li><a href="#회원가입/로그인">회원가입/로그인</a></li>
        <li><a href="#Front_Office">Front Office</a></li>
        <li><a href="#Back_Office">Back Office</a></li>
      </ul>
    </li>
    <li><a href="#License">License</a></li>
    <li><a href="#contact">Contact</a></li>	  
  </ol>
</details>


<!-- Agile Office -->

# Agile Office


## 개요
**Agile Office**<br>
'Agile'은 소프트웨어 개발론에서 고객의 요구사항 변화에 유연하게 대응할 수 있도록 일정한 주기를 반복하는 개발과정을 뜻합니다.
이 개념에 착안하여 융통성있게 사용자 요구사항에 대응하는 웹사이트를 만드는 것을 목표로 하였습니다.<br>
주 사용자 층을 사업자로 잡았으며, 사업자들의 상품을 효율적으로 관리하고자 하였습니다.<br>
어떻게 상품을 효율적으로 관리할까를 고민하던 중, "사용자에게 직접 상품관리 기능을 제공하면 어떨까?"라는 질문을 던져보았습니다.
상품관리하는 방법을 제시하고 사용자들이 그것을 토대로 이용하면, 개발자들은 개발에 전념할 수 있다는 기대를 해보았습니다.<br>

**Agile Office Aspects**<br>
Agile Office는 쉽습니다. - 사용자들의 UI, UX를 고려하여 간단하게 만들었습니다.<br>
Agile Office는 자율적입니다. - 사용자들의 자유도를 위하여 취급 상품에 제한을 두지 않았습니다.<br>
Agile Office는 정확하고 유연합니다. - 주문정보와 상품정보를 연동하여 다양하게 상품재고와 매출액을 출력합니다.<br>
Agile Office는 효율적입니다. - 사업자들이 직접 상품을 관리하기 때문에 개발자들의 유지보수가 쉽습니다..<br>

[Agile Office는 [Loyverse](https://loyverse.com/)를 벤처마킹하였습니다.]<br><br>

## Built-in

![](/static/image/logo/java.png=40X40)
![](/static/image/logo/html-5.png=40X40)
<img src="/static/image/logo/java.png" width="40" height="40">
<img src="/static/image/logo/html-5.png" width="40" height="40">
<img src="/static/image/logo/css-3.png" width="40" height="40">
<img src="/static/image/logo/javascript.png" width="40" height="40">
<img src="/static/image/logo/iconfinder_jquery_3069646.png" width="40" height="40"><br>
<img src="/static/image/logo/mybatis.png" width="100" height="70">
<img src="/static/image/logo/tomcat.png" width="100" height="70"><br>
<img src="/static/image/logo/OG-Spring.png" width="100" height="70">
<img src="/static/image/logo/iconfinder_oracle_294664.png" width="100" height="70">
<br>

<!-- 설계 -->
# 설계
상품을 주문하는 Front Office와 상품을 관리하는 Back Office를 나누어서 설계하였습니다.<br>
**Front Office**:  고객의 주문을 받는 역할<br>
**BackOffice**:  상품, 카테고리를 추가, 삭제, 수정, 매출을 확인<br>
![alt text](/static/image/capture_image/ownermain.png)<br>

## Draw.io
처음 설계할 때 도식화를 하기위해 Draw.io 툴을 사용하였습니다.
마인드 매핑으로 설계를 하였기 때문에 구현가능성보다는 Agile Office의 컨셉, 핵심기능을 정하는데 많이 도움이 되었습니다.
[draw.io 자료](/static/draw/AgileOffice.drawio)
## DB 
DB 설계사용 툴: Excel, Dataedo<br>

### Excel<br>
db 설계는 테이블 구조를 쉽게 확인할 수 있는 excel에서 먼저 했습니다. 상품, 고객관리를 owner가 직접하기 때문에 owner 한 명당 다수의 customer, item을 가지는 테이블이 필요했습니다.
owner라는 부모 테이블을 두고 이를 참조하는 형태가 가장 이상적이었습니다.
[db_excel 자료](/static/image/db_excel.xlsx) <br>

### Dataedo<br>
테이블을 DB에 만들기전에 ERD로 참조관계를 먼저 확인 했습니다.<br><br>

**전체 ERD**<br>
모든 테이블이 owner테이블을 참조하게하였습니다. 이는 owner에 따라서 매출액정보나 상품관리를 해야하기 때문에<br>
join을 용이하게 위함이었습니다. 매출 전체 부분은 날짜별로 그룹화 하여 매출총액을 보여 주었습니다. <br>
각 날짜의 매출을 아이템별로 알고 싶을 경우 해당 날짜의 order_detail 테이블과 item 테이블을 조인하여 출력하였습니다.<br>
고객별로 매출을 알고 싶을 경우 order_summary, customer 테이블을 조인하여 출력하였습니다.<br>
주문 한 건당 상세정보를 알고 싶을 경우는 영수증을 보여주었습니다.<br>
이를 구현하기 위해 order_detail과 item을 먼저 조인 테이블을 select 하고 이를 order_summary에서 한 번 더 조인 하였습니다.<br>
상세 정보를 따로 나누니 영수증(주문정보, 상세정보, item이름)을 구현할 때 도움이 되었습니다.<br>
<br>
![alt text](/static/image/erd/entire_relation.png)
<br>

**상품 등록 ERD**<br>
item테이블은 category테이블을 참조하게 하였습니다. 단 item테이블의 category 외래키가 null이 될 수 도 있게 하였습니다. 이는 item이 category없이 분류 될 수 도 있음을 고려한 것입니다<br>
<br>
![alt text](/static/image/erd/item_relation.png)
<br>

**주문 등록 ERD** <br>
order_detail테이블은 order_summary 한 건을 참조합니다. 이때 order_detail에는 상품별 수량, 가격정보를 담기 때문에 item테이블에 대한 참조관계도 두었습니다. order_summary는 customer를 참조합니다.<br>
<br>
![alt text](/static/image/erd/order_relation.png)
<br>

**리뷰 등록 ERD**<br>
Review테이블은 customer테이블을 참조합니다. 이는 맴버십으로 등록된 고객만 리뷰를 등록할 수 있도록 고려한 것입니다.<br>
한편, review 테이블은 상품별로 구분하여야 하기때문에 item테이블과도 참조관계를 갖습니다.<br>
<br>
![alt text](/static/image/erd/review_relation.png)
<br>


## 디자인패턴
MVC 모델(Model2)을 사용하여 유지보수를 꽤하였습니다. model view controller로 나누어 로직을 분담시키니 수정하기 쉬웠습니다.
model은 service, dao의 기능을 담당하고 view는 jsp로 클라이언트 영역에 보여주는 역할을 하게하였습니다.
또한 controller를 따로 두어 각 view의 요청사항들을 효율적으로 전담케 하였습니다.<br><br>
**model**
<br>
![alt text](/static/image/mvc/tree_model.png)
<br>
**view**
<br>
![alt text](/static/image/mvc/tree_view.png)
<br>
**controller**
<br>
![alt text](/static/image/mvc/tree_controller.png)
<br>
**mybatis**
<br>
![alt text](/static/image/mvc/tree_mybatis.png)

# 

## common_라이브러리
개발할 때 공통으로 쓰이는 로직은 클래스로 분류하였습니다. 또한 메모리 효율을 고려한 @Component를 사용하여 bean에 등록하여 new를 단 한 번만 해도 언제든지 쓸 수 있도록 구현하였습니다.
또는 static을 지정하여 new하지 않고도 메소드를 사용할 수 있게끔 하였습니다.


**FileManager**<br>
[FileManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/FileManager.java)<br>
파라미터로 넘겨준 파일의 이름을 확장자만 남게 수정합니다.<br>
지정할 경로와 파일정보가 담긴 Multipartfile 인스턴스를 인자로 받아 파일을 저장합니다.<br>
파일을 삭제시켜줍니다.<br>

**Formatter**<br>
[Formatter.java](/src/main/java/com/tsycsm/agileoffice/model/common/Formatter.java)<br>
숫자를 기입하면 통화형식으로 반환해줍니다.<br>

**MessageData**<br>
[MessageData.java](/src/main/java/com/tsycsm/agileoffice/model/common/MessageData.java)<br>
비동기로 응답할 때 반환되는 msg, url 등을 json형태로 담아두었습니다.<br>

**MailSender**<br>
[MailSender.java](/src/main/java/com/tsycsm/agileoffice/model/common/MailSender.java)<br>
구글 SMTP를 이용하여 발신자와 수신자를 등록할 수 있게끔 합니다.<br>

**Pager**<br>
[Pager.java](/src/main/java/com/tsycsm/agileoffice/model/common/Pager.java)<br>
Pager객체는 각 페이지에 대한 정보를 담고 있습니다.<br>
각각의 페이지 정보들은 현재 페이지, List에 따라 달라져야 하므로 init메소드로 그 연산을 처리하였습니다.<br>
Pager는 bean에 의해 등록되어 new를 계속 할 수 없으므로 생성자가 아닌 init메소드를 호출하는 방식을 사용하였습니다.<br>
pager 객체는 페이지에 대한 정보들이 담아져있으며, 현재페이지와 List<>를 인수로 받습니다.<br>

**SecureManager**<br>
[SecureManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/SecureManager.java)<br>
비밀번호가 그대로 DB에 저장되면 노출 위험이 있기 때문에 SHA-256 해시를 이용하여 암호화 해주었습니다.<br>

**ExcelManager**<br>
[ExcelManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/)<br>

**CustomerSessionCheckAspect**<br>
[CustomerSessionCheckAspect.java](/src/main/java/com/tsycsm/agileoffice/rest/aop/CustomerSessionCheckAspect.java)<br>
controller에서 조건문을 통한 세션검사를 하면 코드의 일관성이 떨어질 수 있습니다. 따라서 클래스로 조건문 로직을 따로 만듭니다.<br>
이 클래스는 필요할때마다 new하지 않고 한 번만 메모리에 올려놓아야하므로 bean에 등록합니다. <br>
bean에 등록하고 인스턴스를 호출할 시점을 aop를 통하여 정합니다.<br>
세션검사 로직과 더불어 proxy역할을 하게끔 해주어야합니다.<br>
먼저 세션검사를 하고 성공하면 controller의 메소드의 반환값을 그대로 반환해주어야 되기 때문에 적용시점을 around로 설정합니다.<br>

## 회원가입/로그인
유저의 편의성을 고려하여 회원가입/로그인 페이지를 한 곳에 두었습니다.<br>
![alt text](/static/image/capture_image/ownerCredential.png)<br>
**회원가입**<br>
회원가입을 할 때 비동기방식으로 요청을 하여 페이지 전환없이 id중복체크, 회원 등록을 구현 하였습니다.<br>
```
  success: function(responseData){
				console.log(responseData);

				if(responseData.resultCode ==1){
					alert(responseData.msg);
				}else{
					alert(responseData.msg);				
				}
			}
```

성공/실패 여부를 MessageData 객체에 담아 응답을 받았습니다.<br>
가입성공 여부를 알리기 위해 메일을 보냅니다.<br>
![alt text](/static/image/capture_image/signup.png)<br>
**로그인**<br>
로그인성공/실패 여부를 비동기로 요청하고 성공이라는 응답을 받았을 때 관리 page로 이동시킵니다.<br>
![alt text](/static/image/capture_image/login.png)<br>
## Front_Office
**맴버 회원가입/로그인**<br>
각 owner가 고객을 확보할 수 있도록 customer의 회원가입/로그인 처리를 할 수 있게끔 하였으며 이역시 비동기 방식으로 요청/응답합니다.<br>
회원가입등록을 하지 않더라도 상품주문을 할 수 있게끔 하였으며, 회원가입을 한 고객은 주문 건당 point가 쌓이는 혜택을 제공하였습니다.<br>
![alt text](/static/image/capture_image/kiosk.png)<br>
**주문**<br>
등록된 상품을 주문할 때 주문상세(주문자, 결제 총액), 주문요약(상품id, 상품별 수량/가격)을 db에 insert하였습니다.<br>
또한 owner가 등록한 상품의 재고의 수가 0보다 아래가 되면 결제 처리를 못하도록 하였습니다.<br>
aop로 commit되는 시점을 정하여 위의 트랜잭션 처리가 하나라도 실패되면 rollback하도록 TransactionManager를 사용하였습니다.<br>

![alt text](/static/image/capture_image/menu.png)<br>

```
  	<tx:advice id="txAdvice" transaction-manager="dataSourceTransactionManager">
		<tx:attributes>
			<!-- <tx:method name="send" propagation="REQUIRED" rollback-for="Exception"/> -->
			<tx:method name="regist" propagation="REQUIRED" rollback-for="Exception"/>
		</tx:attributes>
	</tx:advice>
	
	<!-- 트랜잭션을 적용할 대상인 서비스 등록 -->
	<aop:config>
		<aop:pointcut expression="execution(public * com.tsycsm.agileoffice.model.order.service..*(..))" id="txPointcut"/>
		<aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>				
	</aop:config>	
```


**리뷰**<br>
리뷰 등록시 맴버십에 등록된 고객만이 등록할 수 있도록 aop를 이용한 세션검사를 합니다.<br>
주문부분과 리뷰부분을 한 페이지에 놓아두고 탭 버튼을 구현하여 페이지 전환없이 이용할 수 있게 하였습니다.<br>
따라서 리뷰의 등록, 삭제, 수정, 페이징 처리를 비동기 방식으로 구현하였습니다.<br>
리뷰의 등록, 삭제, 수정은 앞선 방식으로 비동기 요청을 하였습니다.
비동기 페이징 Pager객체를 반환하였습니다.
비동기 방식으로 pager를 반환 받아 리스트의 정보를 클라이언트 영역에 출력합니다.

![alt text](/static/image/capture_image/review.png)<br>

## Back_Office<br>
**상품/카테고리 관리**<br>
상품 추가
![alt text](/static/image/capture_image/itemadd.png)<br><br>
상품목록<br>
![alt text](/static/image/capture_image/itemlist.png)<br><br>
상품 등록 시 이미지도 파일업로드를 합니다. 이미지를 수정하면 원래 있던 이미지는 지워지고 새로운 이미지 파일로 대체시킵니다. <br>
상품삭제를 하면 파일업로드된 이미지도 지워집니다.<br>
상품을 일괄 추가할 수 있게끔 엑셀에서 미리 저장된 정보를 DB에 저장시킵니다.<br><br>

카테고리 목록<br>
![alt text](/static/image/capture_image/categorylist.png)<br><br>
owner의 자유도를 고려한 만큼 owner가 직접 카테고리를 만들도록 하였습니다. 또한 카테고리가 없어도 상품을 등록할 수 있게끔하였습니다.<br>
category는 이미지 파일이 아닌 color picker를 사용하여 사용자가 직접 색을 정하여 구분하게끔 하였습니다.<br>
기볹거으로 상품의 목록은 비동기 페이징 방식을 사용하고, 카테고리 별로도 상품의 목록을 보게끔 비동기 sorting을 하였습니다.<br>

**매출 보고서**<br>

날짜별 매출<br>
날짜별로 그룹화하여 매출총액을 보여줍니다. 또한 owner의 매출액 비교 편의성을 고려하여 매출액이 없는 날짜도 명시하였습니다.<br>
![alt text](/static/image/capture_image/report.png)<br><br>
아이템별 매출<br>
item과 order_detail을 날짜별로 group하여 매출액을 보여줍니다.<br>
![alt text](/static/image/capture_image/reportbyitem.png)<br><br>
영수증 보관함<br>
영수증 보관함에서는 주문요약, 주문내역, item을 조인하여 영수증 형태로 매출을 보여줍니다.<br>
![alt text](/static/image/capture_image/receipt_03.png)<br><br>
고객별 매출<br>
고객 관리에서는 등록된 고객의 주문내역, point 마일리지를 보여줍니다.<br>
![alt text](/static/image/capture_image/receipt_01.png)<br><br>
![alt text](/static/image/capture_image/receipt_02.png)<br><br>

**사업자 커뮤니티**<br>
자영업자들이 정보를 공유할 수 있게끔 커뮤니티를 마련했습니다.
![alt text](/static/image/capture_image/board_list.png)<br><br>

![alt text](/static/image/capture_image/board_detail.png)<br><br>
<!-- LICENSE -->
# License
MIT를 씀

<!-- CONTACT -->
# Contact
탁 * 연 : https://github.com/SyngyeonTak<br>
최 * 민 : https://github.com/simiin<br>


<!-- LICENSE -->
# License
MIT License

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/TSYCSM/project2?style=for-the-badge
[contributors-url]: https://github.com/TSYCSM/Project2/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/TSYCSM/Project2?style=for-the-badge
[forks-url]:  https://github.com/TSYCSM/Project2/forks
[stars-shield]: https://img.shields.io/github/stars/TSYCSM/Project2?style=for-the-badge
[stars-url]: https://github.com/TSYCSM/Project2/stars
[license-shield]: https://img.shields.io/github/license/TSYCSM/Project2?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png

