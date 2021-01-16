# Project2
POS Management Web Application in Spring MVC

<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



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
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Best-README-Template</h3>

  <p align="center">
    An awesome README template to jumpstart your projects!
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Report Bug</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Request Feature</a>
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
        <li><a href="#구현언어">구현언어</a></li>
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
      <a href="#개발">개발</a>
      <ul>
        <li><a href="#common_라이브러리">common 라이브러리</a></li>
        <li><a href="#회원가입/로그인">회원가입/로그인</a></li>
        <li><a href="#Front_Office">Front Office</a></li>
        <li><a href="#Back_Office">Back Office</a></li>
      </ul>
    </li>
    <li><a href="#">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>


<!-- Agile Office -->

## Agile Office


### 개요
<pre>
'Agile'은 사전적으로 '민첩한'이라는 뜻입니다. 소프트웨어 생명주기적으로는 고객의 요구사항 변화에 
유연하게 대응할 수 있도록 일정한 주기를 반복하는 개발과정을 뜻합니다.
이 개념에 착안하여 유연하게 사용자 요구사항에 대응하는 웹사이트를 만드는 것을 목표로 하였습니다.
그러던 중, 사용자에게 직접 상품관리 기능을 제공하면 더욱 유연한 웹사이트가 될 것이라고 기대하였습니다.
또한 해당 상품이 결재될 때 주문정보를 같이 연동하여 영수증, 매출정보를 제공하였습니다. 
Agile Office는 사업자들의 자유도를 고려한 만큼 취급 상품에 제한을 두지 않았습니다.
다양한 상품을 취급하는 것과 동시에 사용자들이 원하는 카테고리까지 추가할 수 있도록 설계하였습니다.
</pre>


### 구현언어

* programing language<br>
[JAVA](https://getbootstrap.com)<br>
[HTML/CSS](https://jquery.com)<br>
[JavaScript/Jquery](https://laravel.com)<br>

* Server FrameWork<br>
Spring MVC (Spring Legacy Project)<br>

* java library<br>
Lombok<br>


* DataBase<br>
Oracle<br>

<!-- 설계 -->
## 설계
크게 상품을 관리하는 owner와 상품을 주문하는 customer로 나누어서 설계하였습니다.
owner부분은 사용자가 상품, 카테고리를 추가, 삭제, 수정하고 매출을 확인하는 백오피스(Back Office)입니다.
크게 상품, 매출 보고서, 고객관리, 직원관리로 구분하였습니다.
customer부분은 해당 상품을 주문하고 상품에 대한 리뷰를 남길 수 있도록 설계하였습니다.
admin 부분도 따로 나누어 사용자(owner)들을 관리하는 기능을 두었습니다.
admin은 Agile Office 사용자(owner)를 관리하고 사용자는 자신의 고객(customer)을 관리하는 구조로 설계하였습니다.


### Draw.io
처음 설계할 때 도식화를 하기위해 Draw.io 툴을 사용하였습니다.
마인드 매핑으로 설계를 하였기 때문에 구현가능성보다는 Agile Office의 컨셉, 핵심기능을 정하는데 많이 도움이 되었습니다.

### DB
db 설계는 테이블 구조를 쉽게 확인할 수 있는 excel에서 먼저 했습니다. <br>
[db_excel 자료](/static/image/db_excel.xlsx) <br>

상품, 고객관리를 owner가 직접하기 때문에 owner 한 명당 다수의 customer, item을 가지는 테이블이 필요했습니다.
주요 db 관계
item -> category <br>

owner_detail -> owner_summary -> review<br>
owner_detail -> review<br>



owner라는 부모 테이블을 두고 이를 참조하는 형태가 가장 이상적이었습니다.
한편, item은 품목 카테고리를 참조하는 관계를 구현하였습니다. 
또한, customer 한 건당 다수의 리뷰, 주문정보를 가지는데, 이는 customer가 부모 테이블이 되는 구조로 구현하였습니다.
주문정보는 주문 한 건당 item별 상세 정보를 여러 건 가질 수 있습니다. 
이 또한 정규화가 필요한 과정이라 상세 정보가 주문정보와 item을 참조하는 관계도 만들었습니다.

매출을 출력하는 부분은 테이블들을 조인하여 구현하였습니다.
매출 전체 부분은 테이블 하나로 보여줄수 있지만 사용자가 customer별, item별로 
매출액을 확인을 원할 수 있음을 고려하였습니다. 미리 db 설계 시 정규화, 참조관계를 구축하니 매출상황을 다양하게 보여줄 수 있게 되었습니다.
매출 전체 부분은 날짜별로 그룹화 하여 매출총액을 보여 주었습니다.
각 날짜의 매출을 아이템별로 알고 싶을 경우 해당 날짜의 order_detail 테이블과 item 테이블을 조인하여 출력하였습니다.
고객별로 매출을 알고 싶을 경우 order_summary, customer 테이블을 조인하여 출력하였습니다.
주문 한 건당 상세정보를 알고 싶을 경우는 영수증을 보여주었습니다. 이를 구현하기 위해 order_detail과 item을 먼저 조인 테이블을 select 하고
이를 order_summary에서 한번 더 조인 하였습니다.
상세 정보를 따로 나누니 영수증(주문정보, 상세정보, item이름)을 구현할 때 도움이 되었습니다.



### 디자인패턴
MVC 모델(Model2)을 사용하여 유지보수를 꽤하였습니다. model view controller로 나누어 로직을 분담시키니 수정하기 쉬웠습니다.
model은 service, dao의 기능을 담당하고 view는 jsp로 클라이언트 영역에 보여주는 역할을 하게하였습니다.
또한 controller를 따로 두어 각 view의 요청사항들을 효율적으로 전담케 하였습니다.<br>
model
![alt text](/static/image/tree_model.png)

view
![alt text](/static/image/tree_view.png)

controller
![alt text](/static/image/tree_controller.png)

mybatis
![alt text](/static/image/tree_mybatis.png)

## 개발

### common_라이브러리
개발할 때 공통으로 쓰이는 로직은 클래스로 분류하였습니다. 또한 메모리 효율을 고려한 @Component를 사용하여 bean에 등록하여 new를 단 한 번만 해도 언제든지 쓸 수 있도록 구현하였습니다.
또는 static을 지정하여 new하지 않고도 메소드를 사용할 수 있게끔 하였습니다.


* FileManager [FileManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/FileManager.java)

* Formatter [Formatter.java](/src/main/java/com/tsycsm/agileoffice/model/common/Formatter.java)<br>
숫자를 기입하면 통화형식으로 반환해줍니다.<br>

* MessageData [MessageData.java](/src/main/java/com/tsycsm/agileoffice/model/common/MessageData.java)<br>
비동기로 응답할 때 반환되는 msg, url 등을 json형태로 담아두었습니다.<br>

* MailSender [MailSender.java](/src/main/java/com/tsycsm/agileoffice/model/common/MailSender.java)<br>
구글 SMTP를 이용하여 발신자와 수신자를 등록할 수 있게끔 합니다.<br>

* Pager [Pager.java](/src/main/java/com/tsycsm/agileoffice/model/common/Pager.java)<br>
Pager객체는 각 페이지에 대한 정보(현재페이지, List<>, blockSize, pageSize, firstPage/lastPage 등)를 담고 있습니다.<br>
각각의 페이지 정보들은 현재 페이지, List에 따라 달라져야 하므로 init메소드로 그 연산을 처리하였습니다.<br>
Pager는 bean에 의해 등록되어 new를 계속 할 수 없으므로 생성자가 아닌 init메소드를 호출하는 방식을 사용하였습니다.<br>
pager 객체는 페이지에 대한 정보들이 담아져있으며, 현재페이지와 List<>를 인수로 받습니다.<br>

* SecureManager [SecureManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/SecureManager.java)<br>
비밀번호가 그대로 DB에 저장되면 노출 위험이 있기 때문에 SHA-256 해시를 이용하여 암호화 해주었습니다.<br>

* ExcelManager [ExcelManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/)<br>

* CustomerSessionCheckAspect [OwnerSessionCheckAspect.java](/src/main/java/com/tsycsm/agileoffice/client/aop/OwnerSessionCheckAspect.java)<br>
controller에서 조건문을 통한 세션검사를 하면 코드의 일관성이 떨어질 수 있습니다.<br>
따라서 클래스로 조건문 로직을 따로 만듭니다. 이 클래스는 필요할때마다 new하지 않고 한 번만 메모리에 올려놓아야하므로 bean에 등록합니다.<br>
bean에 등록하고 인스턴스를 호출할 시점을 aop를 통하여 정합니다.<br>
이때 세션검사 로직과 더불어 proxy역할을 하게끔 해주어야합니다.<br>
또한 호출할 시점이 중요한데 먼저 세션검사를 하고 성공하면 controller의 메소드의 반환값을 그대로 반환해주어야 되기 때문에<br>
around로 설정합니다.<br>

### 회원가입/로그인
유저의 편의성을 고려하여 회원가입/로그인 페이지를 한 곳에 두었습니다.<br>

* 회원가입<br>
회원가입을 할 때 비동기방식으로 요청을 하여 페이지 전환없이 id중복체크, 회원 등록을 하게끔 하였습니다.<br>
성공/실패 여부를 MessageData 객체에 담아 응답을 받았습니다.<br>
가입성공 여부를 알리기 위해 메일을 보냅니다.<br>

* 로그인<br>
로그인성공/실패 여부를 비동기로 요청하고 성공이라는 응답을 받았을 때 관리 page로 이동하게끔 합니다.<br>

### Front_Office
* 맴버 회원가입/로그인<br>
각 owner가 고객을 확보할 수 있도록 customer의 회원가입/로그인 처리를 할 수 있게끔 하였으며 이역시 비동기 방식으로 요청/응답합니다.<br>
회원가입등록을 하지 않더라도 상품주문을 할 수 있게끔 하였으며, 회원가입을 한 고객은 주문 건당 point가 쌓이는 혜택을 제공하였습니다.<br>

* 주문<br>
등록된 상품을 주문할 때 주문상세(주문자, 결제 총액), 주문요약(상품id, 상품별 수량/가격)을 db에 insert하였습니다.<br>
또한 owner가 등록한 상품의 재고의 수가 0보다 아래가 되면 결제 처리를 못하도록 하였습니다.<br>
aop로 commit되는 시점을 정하여 위의 트랜잭션 처리가 하나라도 실패되면 rollback하도록 TransactionManager를 사용하였습니다.<br>


* 리뷰<br>
리뷰 등록시 맴버십에 등록된 고객만이 등록할 수 있도록 aop를 이용한 세션검사를 합니다.<br>
주문부분과 리뷰부분을 한 페이지에 놓아두고 탭 버튼을 구현하여 페이지 전환없이 이용할 수 있게 하였습니다.<br>
따라서 리뷰의 등록, 삭제, 수정, 페이징 처리를 비동기 방식으로 구현하였습니다.<br>
리뷰의 등록, 삭제, 수정은 앞선 방식으로 비동기 요청을 하였습니다.
비동기 페이징 Pager객체를 반환하였습니다.
비동기 방식으로 pager를 반환 받아 리스트의 정보를 클라이언트 영역에 출력합니다.

### Back_Office<br>
* 상품/카테고리 관리<br>
--상품추가시 해당 상품의 이미지도 같이 upload(multipart-formdata(?))라이브러리 사용<br>
--상품을 일괄 추가할 수 있게끔 엑셀에서 미리 저장된 정보를 일괄적으로 등록시킴<br>
--이미지는 filemaner라는 클래스를 따로 만들어서 절대경로로 저장되게 함<br>
owner의 자유도를 고려한 만큼 owner가 직접 카테고리를 만들도록 하였습니다. 또한 카테고리가 없어도 상품을 등록할 수 있게끔하였습니다.<br>
category는 이미지 파일이 아닌 color picker를 사용하여 사용자가 직접 색을 정하여 구분하게끔 하였습니다.<br>
기볹거으로 상품의 목록은 비동기 페이징 방식을 사용하고, 카테고리 별로도 상품의 목록을 보게끔 비동기 sorting을 하였습니다.<br>

* 매출 보고서<br>
매출요약 - 날짜별로 그룹화하여 매출총액을 보여줌<br>
날짜별로 그룹화하여 매출총액을 보여줍니다. 또한 owner의 매출액 비교 편의성을 고려하여 매출액이 없는 날짜도 명시하였습니다.<br>
item과 order_detail을 날짜별로 group하여 매출액을 보여줍니다.<br>
영수증 보관함에서는 주문요약, 주문내역, item을 조인하여 영수증 형태로 매출을 보여줍니다.<br>
고객 관리에서는 등록된 고객의 주문내역, point 마일리지를 보여줍니다.<br>

<!-- LICENSE -->
## License
MIT를 씀

<!-- CONTACT -->
## Contact
탁*연: https://github.com/SyngyeonTak<br>
최*민: https://github.com/simin2<br>

<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* w3school

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/TSYCSM/Project2?style=for-the-badge
[contributors-url]: https://github.com/TSYCSM/Project2/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/TSYCSM/Project2?style=for-the-badge
[forks-url]:  https://github.com/TSYCSM/Project2/forks
[stars-shield]: https://img.shields.io/github/stars/TSYCSM/Project2?style=for-the-badge
[stars-url]: https://github.com/TSYCSM/Project2/stars
[license-shield]: https://img.shields.io/github/license/TSYCSM/Project2?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png

