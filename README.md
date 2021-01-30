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
      <a href="#Agile_Office">Agile Office</a>
      <ul>
        <li><a href="#Outline">Outline</a></li>
        <li><a href="#Built-in">Built-in</a></li>
      </ul>
    </li>
    <li>
      <a href="#Diagrams"></a>
      <ul>
        <li><a href="#DB">DB</a></li>
        <li><a href="#Design_Pattern">Design_Pattern</a></li>
      </ul>
    </li>
    <li>
      <a href="#Development">Development</a>
      <ul>
        <li><a href="#common_library">common_library</a></li>
        <li><a href="#credential">credential</a></li>
        <li><a href="#Front_Office">Front Office</a></li>
        <li><a href="#Back_Office">Back Office</a></li>
      </ul>
    </li>
    <li><a href="#License">License</a></li>
    <li><a href="#contact">Contact</a></li>	  
  </ol>
</details>


<!-- Agile Office -->

## Agile_Office


## Outline
**Agile Office**<br>
In software development, agile practices involve discovering requirements and developing solutions through <br>
the collaborative effort of self-organizing and cross-functional teams and their customer/end user<br>
With this concept in mind, Agile Office aims to create a flexible website and respond to user requirements.<br>

**Agile Office Aspects**<br>

Agile Office Is Simple. - Considering the user's UI and UX, design is intuitive.<br>
Agile Office Is Accurate. - Records in a report tables are based on DB.<br>


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

# Diagrams
**Front Office**:  taking an order, make a payment<br>
**BackOffice**:  monitoring an inventory, displaying sales reports<br><br>
![alt text](/static/image/capture_image/ownermain.png)<br>

## DB 
DB tool: Excel, Dataedo<br>

### Excel<br>
[db_excel ](/static/image/db_excel.xlsx) <br>

### Dataedo<br>
Design ERD<br><br>

**ERD**<br>
<br>
![alt text](/static/image/erd/entire_relation.png)
<br>

**Item ERD**<br>
<br>
![alt text](/static/image/erd/item_relation.png)
<br>

**Order ERD** <br>
<br>
![alt text](/static/image/erd/order_relation.png)
<br>

**Review ERD**<br>
<br>
![alt text](/static/image/erd/review_relation.png)
<br>


## Design_Pattern
Built in MVC design pattern<br>
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

# Development

## common_library

**FileManager**<br>
[FileManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/FileManager.java)<br>

**Formatter**<br>
[Formatter.java](/src/main/java/com/tsycsm/agileoffice/model/common/Formatter.java)<br>

**MessageData**<br>
[MessageData.java](/src/main/java/com/tsycsm/agileoffice/model/common/MessageData.java)<br>

**Pager**<br>
[Pager.java](/src/main/java/com/tsycsm/agileoffice/model/common/Pager.java)<br>

**SecureManager**<br>
[SecureManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/SecureManager.java)<br>

**CustomerSessionCheckAspect**<br>
[CustomerSessionCheckAspect.java](/src/main/java/com/tsycsm/agileoffice/client/aop/ClientSessionCheckAspect.java)<br>

## Credential
**Owner Credential**<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/main/owner_credential.jsp)<br>
[Open Rest Controller source codes](/src/main/java/com/tsycsm/agileoffice/rest/controller/owner/RestOwnerController.java)<br>
[Open Service source codes](/src/main/java/com/tsycsm/agileoffice/model/owner/service/OwnerServiceImpl.java)<br>
[Open DAO source codes](/src/main/java/com/tsycsm/agileoffice/model/owner/repository/MybatisOwnerDAO.java)<br>
[Open Mapper source codes](/src/main/java/com/tsycsm/agileoffice/mybatis/mapper/OwnerMapper.xml)<br><br>
![alt text](/static/image/capture_image/ownerCredential.png)<br>

Sign up, sign in, and ID check requests are all in one page<br>
Forward requests in async<br>

**Sign up**<br>
![alt text](/static/image/capture_image/signup.png)<br>

**Sign in**<br>
![alt text](/static/image/capture_image/login.png)<br>

## Front_Office
**Customer Credential**<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/main/customer_credential.jsp)<br>
[Open Rest Controller source codes](/src/main/java/com/tsycsm/agileoffice/rest/controller/customer/RestCustomerController.java)<br>
[Open Service source codes](/src/main/java/com/tsycsm/agileoffice/model/customer/service/CustomerServiceImpl.java)<br>
[Open DAO source codes](/src/main/java/com/tsycsm/agileoffice/model/customer/repository/MybatisCustomerDAO.java)<br>
[Open Mapper source codes](/src/main/java/com/tsycsm/agileoffice/mybatis/mapper/CustomerMapper.xml)<br><br>
Sign up, sign in, and ID check requests are in one page<br>
Requests in async<br>

![alt text](/static/image/capture_image/kiosk.png)<br>

**Order**<br>
Each request(Order detail) is not committed without success of transactions(Order Summary)<br>
Using AOP transaction<br>
![alt text](/static/image/capture_image/menu.png)<br>

[Open View source codes](/src/main/webapp/WEB-INF/views/customer/main.jsp)<br>
[Open Rest Controller source codes](/src/main/java/com/tsycsm/agileoffice/rest/controller/order/RestOrderController.java)<br>
[Open Service source codes](/src/main/java/com/tsycsm/agileoffice/model/order/service/OrderServiceImpl.java)<br>
[Open Order Summary DAO source codes](/src/main/java/com/tsycsm/agileoffice/model/order/repository/MybatisOrderSummaryDAO.java)<br>
[Open Order Summary Mapper source codes](/src/main/java/com/tsycsm/agileoffice/mybatis/mapper/OrderSummaryMapper.xml)<br><br>
[Open Order Detail DAO source codes](/src/main/java/com/tsycsm/agileoffice/model/order/repository/MybatisOrderDetailDAO.java)<br>
[Open Order Detail Mapper source codes](/src/main/java/com/tsycsm/agileoffice/mybatis/mapper/OrderSummaryDetail.xml)<br><br>


**Review**<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/customer/reviews.jsp)<br>
[Open Rest Controller source codes](/src/main/java/com/tsycsm/agileoffice/rest/controller/review/RestReviewController.java)<br>
[Open Service source codes](/src/main/java/com/tsycsm/agileoffice/model/review/service/ReviewServiceImpl.java)<br>
[Open DAO source codes](/src/main/java/com/tsycsm/agileoffice/model/review/repository/MybatisReviewDAO.java)<br>
[Open Mapper source codes](/src/main/java/com/tsycsm/agileoffice/mybatis/mapper/ReviewMapper.xml)<br><br>
CRUD and paging in async<br>
![alt text](/static/image/capture_image/review.png)<br>

## Back_Office<br>
Add an Item<br><br>
[Open View source codes](/src/main/webapp/WEB-INF/views/owner/inventory/item_add.jsp)<br>
![alt text](/static/image/capture_image/itemadd.png)<br><br>
Show an list of items<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/owner/inventory/item_list.jsp)<br>
![alt text](/static/image/capture_image/itemlist.png)<br><br>
Show an List of categories<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/owner/inventory/category_list.jsp)<br>
![alt text](/static/image/capture_image/categorylist.png)<br><br>

**Sales Report**<br>
To show Sales in numerous ways, map with collection & association tags in Mapper XML<br>
[Open report Mapper source codes](/src/main/java/com/tsycsm/agileoffice/mybatis/mapper/ReportMapper.xml)<br><br>

Sales report by date<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/owner/reports/sales_summary.jsp)<br>
![alt text](/static/image/capture_image/report.png)<br><br>
Sales report by an item<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/owner/reports/sales_detail.jsp)<br>
![alt text](/static/image/capture_image/reportbyitem.png)<br><br>
Receipts list<br>
[Open View source codes](/src/main/webapp/WEB-INF/views/owner/reports/receipts.jsp)<br>
![alt text](/static/image/capture_image/receipt_01.png)<br><br>
A receipt<br>
![alt text](/static/image/capture_image/receipt_03.png)<br><br>
Sales report by a customer
[Open View source codes](/src/main/webapp/WEB-INF/views/owner/reports/customer_list.jsp)<br>
![alt text](/static/image/capture_image/receipt_02.png)<br><br>


<!-- LICENSE -->
# License
MIT License

<!-- CONTACT -->
# Contact
탁*연: https://github.com/SyngyeonTak<br>
최*민: https://github.com/simin2<br>


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

