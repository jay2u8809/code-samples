# Service Layer
> 트랜잭션(Transaction), 도메인(Domain) 간의 순서 보장의 역할
- 각각의 도메인에서 이벤트를 처리하고 Service Layer 에서는 해당 이벤트의 순서만을 보장하도록 한다.

## Business Logic 처리
> Service Layer 가 아닌 "도메인" 에서 처리한다.
- 트랜잭션 스크립트: Service Layer 에서 모든 비즈니스 로직을 처리하는 방식
  + Service Layer 가 무의미해지고 객체는 단순한 데이터 덩어리의 역할만 한다.
  
## Spring Web Layer

|      Layer       | Data Object  |
|:----------------:|:------------:|
|    Web Layer     |     DTO      |
|  Service Layer   |     DTO      |
|  Service Layer   | Domain Model |
| Repository Layer | Domain Model |


### Web Layer
- @Controller 와 JSP/Freemarker 등의 뷰 템플릿(View Template) 영역
- @Filter, @ControllerAdvice, ExceptionHandler 등 외부 요청과 응답에 관한 영역

### Service Layer
- @Service 영역
- Controller 와 DAO(Data Access Object) 의 중간 영역
- @Transactional 이 사용되는 영역
- application services, infrastructure services

### Repository Layer
- DB 와 같은 데이터 저장소에 접근하는 영역
- DAO(Data Access Object) 영역

### DTO(Data Transfer Object)
- Layer(계층) 간의 데이터 교환을 위한 객체
- 뷰 템플릿(View Template) 엔진에서 사용된 객체나 Repository Layer에서 결과로 넘겨준 객체 등

### Domain Model
- @Entity 를 사용한 영역 및 도메인에 관련한 객체
- 반드시 DB 의 테이블과 관계가 있어야만 하는 것은 아님
- VO 와 같은 값 객체도 해당
- domain services, entities, value objects
