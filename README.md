# Spring Data JPA 실습 과제 Solution 코드

### Description
Spring Data JPA 실습 과제 Solution 코드는 라이브 세션에서 사용되는 예제 코드로 구성이 되어 있습니다.
* 라이브 세션용 예제 코드
  * [static 멤버 클래스를 이용한 DTO 클래스 리팩토링](#static-멤버-클래스를-이용한-dto-클래스-리팩토링)
  * [Mapstruct 매핑 예제 코드](#mapstruct-매핑-예제-코드)
  * [Value Object를 타입으로 사용하기 위한 예제 코드](#value-object를-타입으로-사용하기-위한-예제-코드)
  
> 예제 코드에 대한 더 구체적인 정보는 아래에서 확인하세요.

---

### static 멤버 클래스를 이용한 DTO 클래스 리팩토링
static 멤버 클래스를 이용해 DTO 클래스의 개수를 줄이는 예제 코드입니다.
* 소스 코드 경로
  * DTO
    * [src/main/java/com/codestates/member/dto/MemberDto.java](https://github.com/codestates-seb/be-solution-jpa/blob/93bf231948a1188fa4ec8005f2cc23f629239878/src/main/java/com/codestates/member/dto/MemberDto.java)
  * Mapper
    * [src/main/java/com/codestates/member/mapper/MemberMapper.java](https://github.com/codestates-seb/be-solution-jpa/blob/93bf231948a1188fa4ec8005f2cc23f629239878/src/main/java/com/codestates/member/mapper/MemberMapper.java)

---

### Mapstruct 매핑 예제 코드
Mapstruct의 @Mapping 애너테이션을 이용해 필드를 매핑하는 예제 코드입니다.
* 소스 코드 경로
  * DTO
    * [src/main/java/com/codestates/member/dto/MemberDto.java](https://github.com/codestates-seb/be-solution-jpa/blob/93bf231948a1188fa4ec8005f2cc23f629239878/src/main/java/com/codestates/member/dto/MemberDto.java)
  * Mapper
    * [src/main/java/com/codestates/member/mapper/MemberMapper.java](https://github.com/codestates-seb/be-solution-jpa/blob/93bf231948a1188fa4ec8005f2cc23f629239878/src/main/java/com/codestates/member/mapper/MemberMapper.java)
---

### Value Object를 타입으로 사용하기 위한 예제 코드
Spring Data JPA에서 Value Object를 데이터 타입으로 사용하기 위해 @Embeddable / @Embedded 애너테이션을 사용할 수 있습니다.
* Money 타입 Value Object
  * [src/main/java/com/codestates/values](https://github.com/codestates-seb/be-solution-jpa/tree/main/src/main/java/com/codestates/values)
* Entity
  * [src/main/java/com/codestates/coffee/entity](https://github.com/codestates-seb/be-solution-jpa/tree/main/src/main/java/com/codestates/coffee/entity)
* Mapper
  * [src/main/java/com/codestates/coffee/mapper](https://github.com/codestates-seb/be-solution-jpa/tree/main/src/main/java/com/codestates/coffee/mapper)

---
