# 스프링부트 JPA 블로그 V2

### 1. 의존성
- devtools
- spring web (mvc)
- mustache
- lombok
- jpa
- mariadb

### 2. DB 설정
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```
### 3. yml 설정
```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8

spring:
  mustache:
    expose-session-attributes: true
  datasource:
      url: jdbc:mariadb://localhost:3306/greendb
      driver-class-name: org.mariadb.jdbc.Driver
      username: green
      password: green1234

  jpa: 
    open-in-view: true
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
    properties:
      hibernate.format_sql: true
  output:
    ansi:
      enabled: always
```

### 4.기능정리
1. 엔티티 생성 완료 및 UI 작업중 

***
2. 유저 관련 UI 완성

**회원가입 화면**

<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfNTcg/MDAxNjUyMzQ5OTExOTIw.8fiPFZ5JiSlSYudExuG8NxaFXheBVhoCiwPyWdZXJeMg.fU9dm_BMLBudIoP1Pe1N2jpqPknlv9kbl1HhmpsvP6kg.PNG.ysh3872/1.%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85_%ED%99%94%EB%A9%B4.png?type=w773"  width="700" height="330">

**로그인 화면**

<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTYz/MDAxNjUyMzQ5OTExOTIz.OgNGJp2Ysc6OavnLBLRsyKVcLjBrQRUD0p-U3lR0iGMg.b9zcsC_lTa_XTdp2D3EWXRMvpvt9n_MiWtfTJVS1x18g.PNG.ysh3872/2.%EB%A1%9C%EA%B7%B8%EC%9D%B8_%ED%99%94%EB%A9%B4.png?type=w773"  width="700" height="330">

***
3. 회원가입 로그인 처리 완료
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfNCAg/MDAxNjUyMzQ5OTExODgy.T-N-h6bmQNm1mKjo3uCiy24K9hnLr0ApEy5F_CwNpokg.UmI6Bw_a9kZFxMq7rza51JGL9cJkI81yrI4HXU9OYiQg.PNG.ysh3872/3.%EB%A1%9C%EA%B7%B8%EC%9D%B8_%EC%99%84%EB%A3%8C_%ED%8E%98%EC%9D%B4%EC%A7%80.png?type=w773"  width="700" height="330">

***
4. 회원 정보 보기 및 유효성 검사 

***
5. 로그아웃 처리

***
6. 회원 수정페이지 데이터 렌더링 

***
7. 쿠키를 이용한 유저네임 기억하기

**https://blog.naver.com/ysh3872/222666465170**

***
8. 인증 필터링을 위한 주소 설계 변경

**https://blog.naver.com/ysh3872/222666960487**

***
9. 글쓰기 페이지 완료
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfODkg/MDAxNjUyMzQ5OTExODgy.qvOjpbCVbYsp-lE2Kt3yfboXRJhmaFWUAnQT_c1VTU8g.BzuJN__tUV5lcZmQWbrkMxwFRLrfgZ2RzmVBfcNNIpkg.PNG.ysh3872/4.%EA%B8%80%EC%93%B0%EA%B8%B0_%ED%8E%98%EC%9D%B4%EC%A7%80.png?type=w773"  width="700" height="330">

***
10. 글목록 페이지 완료

***
11. 글 상세보기 페이지 완료
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTUy/MDAxNjUyMzQ5OTEyMTIz.MX4TfiSGycPoEjbUP6mUyIv9FGZU3g8gwI9_xX4fwSog.EVvLOJ3yhE8DZH3-36jU_3RMLCjPNkVgIyv9phlWxXsg.PNG.ysh3872/4.%EC%83%81%EC%84%B8%EB%B3%B4%EA%B8%B0.png?type=w773"  width="700" height="330">
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTQz/MDAxNjUyMzQ5OTExODY2.124uF0-JWcLmbvkRZ5lMbH5Qw-mS-cKf5MRFO3hyvjEg.jTAS55CCIx1s43tcmbAjnrGpbCp-TFoXrp_9Du32-94g.PNG.ysh3872/5._%EA%B8%80_%EC%83%81%EC%84%B8%EB%B3%B4%EA%B8%B0_%ED%8E%98%EC%9D%B4%EC%A7%80.png?type=w773"  width="700" height="330">

***
12. LAZY & EAGER 전략 

**https://blog.naver.com/ysh3872/222668867115**

***
13. 게시글 목록 DESC 정렬하기

***
14. 게시글 목록 PAGING 

**https://blog.naver.com/ysh3872/222673563065**
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTcg/MDAxNjUyMzQ5OTEyMTIy.d4rzVRZgQ9ypnC8LI0xFnIEdRdwZgT05yCZcEAJEXugg.mFDjUY07-BmiQ96RT44Hek-4xdtcv0V4qmyJvM32fVkg.PNG.ysh3872/6.%EA%B2%8C%EC%8B%9C%EA%B8%80_%ED%8E%98%EC%9D%B4%EC%A7%95_1.png?type=w773"  width="700" height="330">
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMjgz/MDAxNjUyMzQ5OTExODgz.bmoc7xSfiXj5Uom9sP7qT-DbhWN-Go1NguCJNgc6rcMg.8oqjMAl2pFySV17FqdyFqExmniG7_uOZJydDmscFQBkg.PNG.ysh3872/6._%EA%B2%8C%EC%8B%9C%EA%B8%80_%ED%8E%98%EC%9D%B4%EC%A7%95_2.png?type=w773"  width="700" height="330">

***
15. 유저네임 중복검사 AJAX 사용

**https://blog.naver.com/ysh3872/222673768923**

***
16. SERVICE LAYER를 사용한 TRANSACTION 관리

**https://blog.naver.com/ysh3872/222677071680**

***
17. 회원정보 수정 - DIRTY CHECKING 사용

**https://blog.naver.com/ysh3872/222677080893**

***
18. 게시글 삭제하기 

**https://blog.naver.com/ysh3872/222678985015**
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTEw/MDAxNjUyMzQ5OTEyMTk2.ZpbX-CyUH4G7lNKDQ9DkmA-JKhUExXA6xDWTqbzFjU0g.928ABAylhtbnPKFPLcP34p8jJLpuBNzj3uEVauXNQAwg.PNG.ysh3872/7.%EA%B2%8C%EC%8B%9C%EA%B8%80_%EC%82%AD%EC%A0%9C.png?type=w773"  width="700" height="330">

***
19. 게시글 상세보기 프론트 화면 권한 체크 
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTY5/MDAxNjUyMzQ5OTEyMjE0.NR-6ejuutN9Q6bgrDNfbB0xUW7-R2agcZdB7XGLe5kYg.yEGB9cuPBPVFS9YU7dy1qCy8fvNqgHc_RZw_sYTFhy4g.PNG.ysh3872/8.cos%EA%B6%8C%ED%95%9C_%EC%97%86%EC%9D%8C.png?type=w773"  width="700" height="330">
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfNjYg/MDAxNjUyMzQ5OTEyMjE4.TI9GWzMoWzFvVoxpyfTiRtt3E7E93XbrJx_xKBUvk0Ug.oMhBIAcm2PHlomphMD9yj50CbbACkCCD49mRLclF2i4g.PNG.ysh3872/8.ssar_%EA%B6%8C%ED%95%9C%EC%9E%88%EC%9D%8C.png?type=w773"  width="700" height="330">

***
20. 게시글 수정하기
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTkz/MDAxNjUyMzQ5OTEyMzcx.WdkiEmRBns2rRFTs9bk8RFx1ds88ueoC8DjvT5iY7_cg.Wggp0hEG0ILRmhJUwVIjEhal1fd9izi2qAPRUXVp0Wwg.PNG.ysh3872/9.%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%88%98%EC%A0%951.png?type=w773"  width="700" height="330">
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTM1/MDAxNjUyMzQ5OTEyNDA2.qAy5CeUa7ACv6cXxU7DepzTpE4GmEBm_0hCZtjL-FzIg.AJSqM5ozZsVqR31DEl1u9raAECRxHEtnXohoGIHFxAIg.PNG.ysh3872/9.%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%88%98%EC%A0%953.png?type=w773"  width="700" height="330">
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMjg3/MDAxNjUyMzQ5OTEyMjUx.LPo0h44YSA4oNGyX1NUAqKyj8HJxrI3He4OVSIy_1E0g.MV91r7g-uIMc37DeDJOjszarKc7yZ5nA7E0KWErL8vUg.PNG.ysh3872/9.%EA%B2%8C%EC%8B%9C%EA%B8%80_%EC%88%98%EC%A0%952.png?type=w773"  width="700" height="330">

***
21. SUMMERNOTE 사용하기
<img src="https://postfiles.pstatic.net/MjAyMjA1MTJfMTU5/MDAxNjUyMzQ5OTQxOTQ5.bSIpjMfqvoygv8BkqEvBQxpXfzwuWgo-BFKKKEuT874g.5E1TjuQM_JWa2cemk60jmMW99fedymQtHnzzO_jRE_gg.PNG.ysh3872/%EC%8D%B8%EB%A8%B8.png?type=w773"  width="700" height="330">

***
22. SCRIPT 공격 방어하기 
***

### 블로그
**https://blog.naver.com/ysh3872**
