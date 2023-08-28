[33mcommit 8e343b85169113e18725ba50c88dae81d3d361bb[m[33m ([m[1;36mHEAD -> [m[1;32mmain[m[33m)[m
Merge: 69c8140 5044f4e
Author: ryulcoder <135496109+ryulcoder@users.noreply.github.com>
Date:   Tue Aug 8 14:08:06 2023 +0900

    Merge pull request #6 from sango7199/feature/join2
    
    ver 1.3.1 권한 관련 설정 수정, 에러페이지 추가

[33mcommit 5044f4ed2e76034a02362dfc28120e52522f594a[m[33m ([m[1;31morigin/feature/join2[m[33m)[m
Author: San <sango7199@gmail.com>
Date:   Tue Aug 8 13:41:33 2023 +0900

    에러 페이지 추가 (403, 404)

[33mcommit af766caed95deb9de21780f8a9f301305fc8499b[m
Author: San <sango7199@gmail.com>
Date:   Tue Aug 8 12:40:06 2023 +0900

    권한 설정 및 페이지 접근 권한 설정 수정

[33mcommit c5f55f1633c960b8acb3c286397dd76964deb183[m
Author: San <sango7199@gmail.com>
Date:   Tue Aug 8 12:19:18 2023 +0900

    join2 브랜치 생성

[33mcommit 69c81406ac3d815ee9242ddf5c5fc1512c4642ca[m
Merge: 714eb68 9336413
Author: jun0289jun <44790938+jun0289jun@users.noreply.github.com>
Date:   Tue Aug 8 09:34:09 2023 +0900

    Merge pull request #4 from sango7199/feature/join
    
    ver 1.3 - 회원가입, 로그인 기능 추가

[33mcommit 93364139c4a0570d703727c7905f8f469bd6d8a5[m[33m ([m[1;31morigin/feature/join[m[33m)[m
Author: San <sango7199@gmail.com>
Date:   Tue Aug 8 00:02:50 2023 +0900

    이전 페이지 기억하는 Interceptor 구현, 페이지 이동 점검, 로그인 권한 테스트(실패..)

[33mcommit c7c7651164f540ebf62435f1093e64abd147d708[m
Author: San <sango7199@gmail.com>
Date:   Mon Aug 7 23:09:16 2023 +0900

    로그인 로직 완료(SpringSecurty 적용 완료, 오류 해결)

[33mcommit a238cfff71801d7ce33512411cf2d3ef00ee70a4[m
Author: San <sango7199@gmail.com>
Date:   Mon Aug 7 11:06:12 2023 +0900

    로그인 HTML 작성

[33mcommit 56eb36ae210311cad0b305c2ead011af6c31c1cf[m
Author: San <sango7199@gmail.com>
Date:   Mon Aug 7 02:52:15 2023 +0900

    보안 적용(SpringSecurity 적용, 비밀번호 암호화)

[33mcommit 282551a4b35d42f93f9bed5bddf24d1b2f1ad883[m
Author: San <sango7199@gmail.com>
Date:   Mon Aug 7 00:10:46 2023 +0900

    회원가입 세부 로직 점검 완료(프론트 데이터 유효성 검사 추가), 비밀번호 해싱 보안설계 추가중

[33mcommit 2a74f4d94d22d9cf0f289c13628d23db19ad40fe[m
Author: San <sango7199@gmail.com>
Date:   Sun Aug 6 20:44:05 2023 +0900

    회원가입 오류 수정(회원가입 일시 날짜와 시간 현재 시간이 아닌 오류 해결, 회원가입 완료 페이지 오류  수정 및 완료)

[33mcommit d47efb6f2c6cc68e0c8caec914eecea45654e7db[m
Author: San <sango7199@gmail.com>
Date:   Sat Aug 5 15:27:41 2023 +0900

    회원가입 로직 완료( 데이터를 서버로 전송못하는 오류 수정)

[33mcommit a476a5d1fb8f06aeaf4a12cc1e80ac0dd0edc30d[m
Author: San <sango7199@gmail.com>
Date:   Sat Aug 5 02:04:22 2023 +0900

    회원가입 페이지 로고 오류 수정

[33mcommit 714eb683061f210b2a603fe40cdc7934fea20ac1[m
Author: San <sango7199@gmail.com>
Date:   Sat Aug 5 01:34:40 2023 +0900

    interceptor 관련 자바 파일 merge 오류로 중복생성되어 삭제처리

[33mcommit 2f65e9c8096054d02c30729d966c790b6bbc2923[m
Merge: 0e3c09f 8451798
Author: San Jung <124389345+sango7199@users.noreply.github.com>
Date:   Fri Aug 4 14:06:32 2023 +0900

    Merge pull request #3 from sango7199/feature/join
    
    ver 1.2 ViewNameInterceptor 추가, MyBatis 설정 완료, join 기능 추가(회원가입 미완)

[33mcommit 8451798ada2879fc92a0b7ccaf2d43ade2fbe083[m
Author: San <sango7199@gmail.com>
Date:   Fri Aug 4 13:36:25 2023 +0900

     myBatis 작동 확인, api/check 구현 완료(미완)

[33mcommit 6b3a82f506277e6cedeebe70716b4af5186a9a3f[m
Author: San <sango7199@gmail.com>
Date:   Fri Aug 4 12:25:36 2023 +0900

    registerUser(join) 기능 추가, myBatis 설정 적용

[33mcommit 7ee847cda2c1b56d4a94ce80908c6d081ab396f0[m
Author: San <sango7199@gmail.com>
Date:   Thu Aug 3 15:41:38 2023 +0900

    ViewName 인터셉터 적용, join 페이지 연결

[33mcommit 550be2e7199594e62263e3f33367fe47560960a2[m
Author: San <sango7199@gmail.com>
Date:   Thu Aug 3 12:01:15 2023 +0900

    회원가입 HTML 페이지 4차  오타 수정

[33mcommit b8235075abc6c0b42f65f4a5bf2889791692003f[m
Author: San <sango7199@gmail.com>
Date:   Thu Aug 3 11:48:01 2023 +0900

    회원가입 HTML 페이지 3차 완성(input 체크 로직 추가)

[33mcommit 923be458af449b0f6f1514af5cf583867c06cd01[m
Author: San <sango7199@gmail.com>
Date:   Wed Aug 2 20:25:02 2023 +0900

    회원가입 HTML 페이지 2차 완성(AJAX 요청)

[33mcommit c02bcd5f8ed2327a2a4f00acdea5aef6ec9dcd9a[m
Author: San <sango7199@gmail.com>
Date:   Wed Aug 2 11:53:44 2023 +0900

    회원가입 HTML 페이지 1차 완성

[33mcommit 0e3c09f9681a6055c01001a083ee41ac752ded54[m
Author: San <sango7199@gmail.com>
Date:   Tue Aug 1 16:29:31 2023 +0900

    ver 1.1.3 User 패키지 수정, 서현님 merge 삭제

[33mcommit 237eacea742abf97dc121ffed6969ba43cb85199[m
Merge: b9411d2 4924ec6
Author: San Jung <124389345+sango7199@users.noreply.github.com>
Date:   Tue Aug 1 15:42:22 2023 +0900

    Merge pull request #2 from sango7199/feature/login
    
    Feature/login

[33mcommit 4924ec6623ed47b33d1ae4576f5d0d42560387ff[m
Author: shinsean00 <sps12066@navercom>
Date:   Tue Aug 1 15:25:31 2023 +0900

    feature/login

[33mcommit 9db4cffbde09d76c1f36ed3dd281b58477941fe8[m
Author: shinsean00 <sps12066@navercom>
Date:   Tue Aug 1 15:00:36 2023 +0900

    feature/login

[33mcommit 02622328c864903652f68cc48826d6e263050021[m
Author: shinsean00 <sps12066@navercom>
Date:   Tue Aug 1 14:06:16 2023 +0900

    feature/login

[33mcommit b9411d2961c9814cf575fceb5a37a628d9b25c79[m
Author: San <sango7199@gmail.com>
Date:   Tue Aug 1 12:07:59 2023 +0900

    ver 1.1.2 myBatis 설정, java 기본 패키지 세팅

[33mcommit c7c6b7ca02ce469e6a78dd7bc33ccdd1d57465e7[m
Author: shinsean00 <sps12066@navercom>
Date:   Tue Aug 1 10:46:39 2023 +0900

    feature/login

[33mcommit 8c02e26351fa0d983bebd43f3f502121c2607ce3[m
Author: San <sango7199@gmail.com>
Date:   Mon Jul 31 18:03:11 2023 +0900

    ver 1.1.1 환경설정 완료

[33mcommit 32d3d9fc9a6f5c72e1c8f9b055e63b9d02091ed6[m
Author: San <sango7199@gmail.com>
Date:   Mon Jul 31 15:55:49 2023 +0900

    branch test2

[33mcommit 0ed4ad0ea99dc98a2766108638e475b2fae27005[m
Merge: 668c07b 9c56e0b
Author: SEOHYEON SHIN <125271667+shinsean00@users.noreply.github.com>
Date:   Mon Jul 31 15:45:18 2023 +0900

    Merge pull request #1 from sango7199/test
    
    branch test

[33mcommit 9c56e0b094891b6fe43fc85cfa1b21d9346b2137[m[33m ([m[1;31morigin/test[m[33m)[m
Author: shinsean00 <sps12066@naver.com>
Date:   Mon Jul 31 15:37:15 2023 +0900

    branch test

[33mcommit 668c07ba3fa7b43e0d0b24b8d84a65a85478c42e[m
Author: San <sango7199@gmail.com>
Date:   Mon Jul 31 15:19:34 2023 +0900

    git test

[33mcommit 9ce15abc83a2d0e812fbfc85731da5b72ae92853[m
Author: San <sango7199@gmail.com>
Date:   Mon Jul 31 14:33:18 2023 +0900

    viewtest

[33mcommit 021018a2d43bb8a03ea2dd4be27a6bd65680dd6c[m
Author: san <sango7199@gmail.com>
Date:   Fri Jul 28 16:22:32 2023 +0900

    ver 1.1 DB MySQL로 변경
