# SpringBoot-Website
 스프링 부트와 스프링 MVC로 게시판을 만들어 보자

---

#### Spring Boot Version: 3.0.0
#### Spring Security
#### Java Version: 17
#### DB: H2
#### ORM: Spring Data Jpa
#### Test Framework: Junit5
#### Template Engine: Thymeleaf

---

### 진행중인 이슈
- 추천 버튼 클릭 시 반응 없음
- 검색 버튼 클릭 시 반응 없음

---

### ERD
![image](https://user-images.githubusercontent.com/91050780/211245465-2724331c-d9f3-4c12-b29e-c8769c7be7cd.png)

draw.io를 사용했다. 선이 깔끔하게 그려지지 않는 점이 아쉽다.

---
### 회고
- @ManyToMany를 처음 사용해봤다. 이 어노테이션을 적용하면 새롭게 테이블을 생겨나고 각 참조하는 테이블의 PK를 PK(UK), FK로 사용한다.
- 현재는 Spring Security만 적용했는데 더 나아가서 JWT도 적용해보고 싶다.
