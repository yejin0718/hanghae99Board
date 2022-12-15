# 주특기 심화주차 게시판 API
## 팀원 정보
* [김범준](https://github.com/faulty337)
* [김예진](
  https://github.com/yejin0718)
* [서재석](https://github.com/suhjaesuk)
* [한교진](https://github.com/hangj97)

## 주요 기능
* 회원가입
* 로그인
* 게시글 CRUD
  * 유저, 관리자 상태 여부에 따른 수정, 삭제 인가
* 댓글 CRUD
  * 유저, 관리자 상태 여부에 따른 수정, 삭제 인가
* 게시글, 댓글 좋아요 설정

## Tech Stack
### Back-End
<img src="https://img.shields.io/badge/Swagger-green?style=for-the-badge&logo=Swagger&logoColor=white"/> <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Spring Boot-6DB33F.svg?&style=for-the-badge&logo=Spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F.svg?&style=for-the-badge&logo=Spring Security&logoColor=white"> <img src="https://img.shields.io/badge/JWT-000000.svg?&style=for-the-badge&logo=JSON Web Tokens&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A.svg?&style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/Amazon RDS-527FFF.svg?&style=for-the-badge&logo=Amazon RDS&logoColor=white">

### Deploy
<img src="https://img.shields.io/badge/Amazon EC2-yellow?style=for-the-badge&logo=Amazon EC2&logoColor=white">

## ERD
### LV1
<img width="708" alt="image" src="https://user-images.githubusercontent.com/105099062/207764913-50111052-2896-4784-90ae-d3d0eaa77b46.png">



### LV2
#### 변경 전
<img width="706" alt="image" src="https://user-images.githubusercontent.com/105099062/207764889-75804270-a6b4-41dd-8796-a53a5980aa49.png">

#### 변경 후
<img width="711" alt="image" src="https://user-images.githubusercontent.com/105099062/207764935-c13c3505-1b89-407c-967f-0bb3ba62da42.png">

#### ❓변경 이유
- 유지보수 시 비용이 많이 듬. → 연관관계 편의 메서드가 많아짐으로써 복잡하고 Service 코드가 길어짐.
- 데이터 낭비 → 좋아요 테이블에 회원 Id, 댓글 Id 중 하나는 무조건 null 값이 들어감.

## 📍 Git Flow 전략
- 작업 시작 전 자신이 Develop에 있는지 **기능 Branch**에서 진행하는지 확인하기
- Branch명은 **기능 이름**으로 짓기 ex) sign, createComment
- Commit의 간격은 **짧게** → 오류사항을 되돌릴 포인트가 많을 수록 좋다.
    - Commit 메세지는 짧고 간결하고 잘 알아듣게**(알잘딱깔센)**
- Push는 기능이 **완성**되었을 때 즉, 기능이 완성되고 Develop에 merge할 때 하기
    - 만약 컴퓨터가 날아갈 것 같다는 **공포감**이 생기면 Push해도 괜찮다.
- Pull Request 시 팀원 **2명**이 리뷰를 남기고 이상이 없을 시 merge한다.
- **Pull Request할 때 대상이 Develop인지 Main인지 확인하기!!!**

## 📍 Commit Message Convention
<img width="718" alt="image" src="https://user-images.githubusercontent.com/105099062/207764961-561da103-398d-42fa-8a48-85fc0135c990.png">
출처 : https://overcome-the-limits.tistory.com/entry/협업-협업을-위한-기본적인-git-커밋컨벤션-설정하기

## 📍Reference Site
<a href="https://www.notion.so/10-8c45e72928e4478ea1645dd292ff099b#1ef86294da2b42a08df6f4a92ac3436c">![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white&link=https://www.notion.so/10-8c45e72928e4478ea1645dd292ff099b#1ef86294da2b42a08df6f4a92ac3436c)</a>
