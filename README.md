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

# Agile Office


## Outline
**Agile Office**<br>
in software development, agile practices involve discovering requirements and developing solutions through <br>
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
**Front Office**:  taking an order, make a payment
**BackOffice**:  monitoring an inventory, displaying sales reports<br>

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

**ExcelManager**<br>
[ExcelManager.java](/src/main/java/com/tsycsm/agileoffice/model/common/)<br>

**CustomerSessionCheckAspect**<br>
[CustomerSessionCheckAspect.java](/src/main/java/com/tsycsm/agileoffice/client/aop/OwnerSessionCheckAspect.java)<br>

## Credential

**Sign up**<br>

**Sign in**<br>

## Front_Office
**Customer Credential**<br>

**Order**<br>

**Review**<br>

## Back_Office<br>


**Sales Report**<br>

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

