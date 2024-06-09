# REST API 공통 가이드



다음 가이드는 careerbuilder API를 사용할때 참고할 수 있는 내용들을 설명합니다.



**API 공통 사항**

| 공통 사항     | 설명                                         |
| ------------- | -------------------------------------------- |
| 도메인        | http://ex.careerbuilder.com                  |
| HTTP 사용가능 | HTTP 프로토콜 사용 가능, HTTPS 지원하지 않음 |

</br>

---



## API Request

커리어빌더에서 제공하고 있는 API의 요청 URL 형태는 다음과 같습니다.

| 메서드 | 구분 | 파라미터 전달                                          |
| ------ | ---- | ------------------------------------------------------ |
| GET    | URL  | 요청 URL에 표기된 쿼리 파라미터                        |
| POST   | Body | - Request Body에 `application/json` 으로 표현된 데이터 |
| PUT    | Body | - Request Body에 `application/json` 으로 표현된 데이터 |
| DELETE | URL  | 요청 URL에 표기된 쿼리 파라미터                        |

커리어빌더 API 호출방식은 위 4가지 방식을 모두 허용하지만, 복잡한 데이터를 전달받아야하는 API들은 주로 `application/json` 으로 표현된 데이터를 주로 허용합니다.



#### 코드 예제

**API 호출**

```
curl -X 'GET' \
  'http://ex.careerbuilder.com/v1/products \
  -H 'accept: application/json'
```



</br>

---



## API Response

모든 커리어빌더의 API 응답은 아래 `공통 Repsonse` 테이블의 구조를 갖는 JSON 객체로 표현됩니다. `ContentType`은 `application/json`으로 고정됩니다.



### 공통 Response

| 필드      | 타입    | 필수여부 | 설명                                                         |
|---------| ------- | -------- | ------------------------------------------------------------ |
| success | boolean | O        | API 호출 결과 (성공:true / 실패:false)                       |
| data    | Object  | X        | API 응답 데이터 <br> - API 종류에 따라 개별 내용으로 정의됨.<br />- API 호출 결과가 성공(true)일 경우 반환<br /> |
| error   | Object  | X        | 오류 원인에 대한 설명 <br />- API 호출 결과가 싱패(false)일 경우 반환<br /> |



#### 코드 예제

**API 호출 - 성공 응답**

```Json
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "success": true,
  "data": {
    /*  api 호출 결과 데이터  */
  }
}
```

**API 호출 - 실패 응답**

```Json
HTTP/1.1 400 BAD REQUEST
Content-Type: application/json;charset=UTF-8

{
  "success": false,
  "error": {
    "code": "invalid_parameter",
    "message":"요청하신 파라미터가 유효하지 않습니다."
  }
}
```



## 공통 Error

API 호출이 실패한 경우, `error` 필드를 통해 오류에 대한 상세한 정보를 확인할 수 있습니다. 

아래 표는 공통적으로 사용되는 `error` 코드에 대한 설명입니다.

| 필드    | 타입   | 필수 여부 | 설명                                                   |
| ------- | ------ | --------- | ------------------------------------------------------ |
| code    | String | O         | 오류 상황을 구분하기 위한 코드                         |
|         |        |           | `internal_server_error` : 예상치 못한 서버 오류 발생.  |
|         |        |           | `invalid_parameter` : 요청한 파라미터가 유효하지 않음. |
|         |        |           | `api_not_found` : 요청한 API를 찾을 수 없음.           |
| message | String | O         | 오류 원인에 대한 설명                                  |



</br>

---



## 도메인 별 API 설계 문서

- [제품 도메인 API 설계 문서](./product_v1_api.md)
- [제품 도메인 API 설계 문서](./product_v1_api.md)
- [제품 도메인 API 설계 문서](./product_v1_api.md)
- [제품 도메인 API 설계 문서](./product_v1_api.md)
- [제품 도메인 API 설계 문서](./product_v1_api.md)
