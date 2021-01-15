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
처음 설계할 때 도식화를 하기위해 Draw.io라는 툴을 사용하였습니다.
마인드 매핑으로 설계를 하였기 때문에 구현가능성보다는 Agile Office의 컨셉, 핵심기능을 정하는데 많이 도움이 되었습니다.

### DB
db 설계는 테이블 구조를 쉽게 확인할 수 있는 excel에서 먼저 했습니다. 
상품, 고객관리를 owner가 직접하기 때문에 owner 한 명당 다수의 customer, item을 가지는 테이블이 필요했습니다.
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


<!-- LICENSE -->
## License


<!-- CONTACT -->
## Contact


<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* 
* 





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

