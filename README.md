# CareerBuilder

## 요약
소규모 사업장을 위한 제품 중심의 실시간 재고 관리 프로젝트입니다.

## 핵심 기능 
- 제품 등록/수정/삭제 관리 기능
- 제품 커스텀 속성 정보 추가 기능
- 재고 위치 관리 가능
- 제품의 입고/출고/조정/이동 거래 관리 기능
- 거래처 등록/수정/삭제 관리 기능

## 기능 요구 사항 정의 
- [제품 요구 사항 정의서-prd 확인](/docs/prd/product_v1_prd.md)
- [제품 속성 요구 사항 정의서-prd 확인](/docs/prd/product_attribution_v1_prd.md)
- [위치 요구 사항 정의서 prd-확인](/docs/prd/location_v1_prd.md)
- [거래처 요구 사항 정의서 prd-확인](/docs/prd/partner_v1_prd.md)
- [거래 요구 사항 정의서 prd-확인](/docs/prd/transaction_v1_prd.md)
- [재고 요구 사항 정의서 prd-확인](/docs/prd/stock_v1_prd.md)


## REST API 설계 
- [REST API Reference 문서 참고](./docs/apidesign/careerbuilder_v1_api.md)

## 개발 순서
1. prd(요구 사항 정의서) 작성 
2. API 설계  
3. Issue 등록  
4. 테스트 작성  
5. 기능 구현  
6. 브랜치 merge


## 기술 스택
- [자세한 tech spec 확인](./docs/tech/careerbuilder_techspec_v1.md)

 
- 어플리케이션
    - Spring boot 3.2.3
        - Spring actuator
        - Spring webmvc
    - Spring data 
        - Spring data jpa
        - MySQL 8.3
- 개발 환경
    - Java 17
    - Gradle 8.6
    - IntelliJ IDEA 2024.1.2 (Ultimate Edition)
- 테스트
    - JUnit 5.10.2
    - Mockito 5.7.0