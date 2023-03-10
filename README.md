# SpringBoot-Website
 스프링 부트와 스프링 MVC로 게시판을 만들어 보자

---

#### Editor: IntelliJ Community
#### Develop Tool: Spring Boot 3.0.0
#### Build: Gradle 7.6
#### Java: Java 17 (jdk)
#### DataBase: H2
#### Test Framework: Junit5
#### Template Engine: Thymeleaf
#### Main Library : Spring Data JPA, Spring Security6:3.1.1

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
- `@ManyToMany`를 처음 사용해봤다. 이 어노테이션을 적용하면 새롭게 테이블을 생겨나고 각 참조하는 테이블의 PK를 PK(UK), FK로 사용한다.
- 현재는 Spring Security만 적용했는데 더 나아가서 JWT도 적용해보고 싶다.
- 이 프로젝트는 로컬에서만 실행된다. 앞으로의 계획은 ec2 + docker + Spring Boot + CI/CD 모두 활용하여 배포까지 해보는 것!
