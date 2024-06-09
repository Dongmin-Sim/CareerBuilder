## Products 제품 

재고 관리를 위한 제품의 정보를 등록하거나, 조회, 수정, 삭제하기 위해 다음과 같은 API를 사용합니다.



| 구분                                   | API 명           | 설명               |
| -------------------------------------- | ---------------- | ------------------ |
| [제품 등록](#등록-/products)           | `/products`      | 제품 정보 등록     |
| [제품 단건 조회](#조회-/products/{id}) | `/products/{id}` | 제품 상세정보 조회 |
| [제품 리스트 조회](#조회-/products/)   | `/products`      | 제품 리스트 조회   |
| [제품 수정](#수정-/products/{id})      | `/products/{id}` | 제품 정보를 수정   |
| [제품 삭제](#삭제-/products/{id})      | `/products/{id}` | 제품 삭제          |



</br>

</br>

---



## 등록 /products

제품의 정보를 등록합니다.

### Request

**요청 예제**

```bash
curl -X POST http://http://example.careerbuilder.com/api/v1/products \
   -H "Content-Type: application/json;charset=utf-8" \
   -d '{
     "name":"{PRODUCT_NAME}",
     "barcode":"{BARCODE}",
     "photo_url":"{PHOTO_URL}",
     "cost":"{COST}",
     "price":"{PRICE}",
     "location_id":"{LOCATION_ID}",
     "initial_quantity":"{INITIAL_QUANTITY}"
   }'
````

**요청 방식**

| 메서드 | 요청 URL                                    |
| ------ | ------------------------------------------- |
| POST   | http://ex.careerbuilder.com/api/v1/products |

**요청 구성**

| 파라미터        | 타입    | 필수 | 설명                |
| --------------- | ------- | ---- | ------------------- |
| name            | String  | O    | 제품명              |
| barcode         | String  | X    | 제품의 바코드 넘버  |
| photoUrl        | String  | X    | 제품의 이미지 주소  |
| cost            | Integer | O    | 제품의 구매가       |
| price           | Integer | O    | 제품의 판매가       |
| locationId      | Integer | O    | 제품 초기 재고 위치 |
| initialQuantity | Integer | O    | 제품 초기 재고 수량 |

### Response
**성공 응답 예제**

```json
{
  "success":true,
  "data":{
    "id":1,
    "name":"name",
    "barcode":"barcode",
    "photo_url":"photo_url",
    "cost":1000,
    "price":2000
  }
}
````



**에러 응답 예제**

```json
{
  "success":fasle,
  "error":{
    "code":"invalid_parameter",
    "message":"파라미터는 제공되었지만, 해당 값이 올바르지 않음."
  }
}
```



**응답 구성**

| 파라미터  | 타입    | 필수 | 설명                                                         |
| --------- | ------- | ---- | ------------------------------------------------------------ |
| success   | boolean | O    | API 호출 결과 (성공:true / 실패:false)                       |
| data      | Object  | X    | 제품의 상세 정보<br />- API 호출 결과가 성공(true)일 경우 반환 |
| id        | Integer | O    | 제품의 ID                                                    |
| name      | String  | O    | 제품명                                                       |
| barcode   | String  | O    | 제품의 바코드 넘버                                           |
| photo_url | String  | O    | 제품의 이미지 주소                                           |
| cost      | Integer | O    | 제품의 구매가                                                |
| price     | Integer | O    | 제품의 판매가                                                |
| error     | Object  | X    | 오류 원인에 대한 설명 <br />- API 호출 결과가 싱패(false)일 경우 반환<br />- 상세 에러 코드는 공통 API 가이드 - 공통 Error [참고](./careerbuilder_v1_api.md) |
|           |         |      | `product_not_found` : 요청한 제품을 찾을 수 없음.            |



---



## 조회 /products/{id}

제품 ID로 제품의 상세정보를 조회합니다.

### Request

**요청 예제**

```bash
curl -X POST http://http://example.careerbuilder.com/api/v1/products/{product_id} \
   -H "Content-Type: application/json;charset=utf-8"
````

**요청 방식**

| 메서드 | 요청 URL                                                 |
| ------ | -------------------------------------------------------- |
| GET    | http://ex.careerbuilder.com/api/v1/products/{product_id} |

**요청 방식 Path Parameter**

| 파라미터   | 타입    | 필수 | 설명                 |
| ---------- | ------- | ---- | -------------------- |
| product_id | Integer | O    | 조회하려는 제품의 id |



### Response

**성공 응답 예제**

```json
{
  "success":true,
  "data":{
    "product":{
      "id":1,
      "name":"name",
      "barcode":"barcode",
      "photo_url":"photo_url",
      "cost":1000,
      "price":2000
    },
    "productAttributions":[
      {
        "attribution":{
          "id":1,
          "type":"STRING",
          "name":"name",
          "rankNum":1,
        },
        "value":"attr1"
      },
      {
        "attribution":{
          "id":2,
          "type":"STRING",
          "name":"name",
          "rankNum":2,
        },
        "value":"attr2"
      }
      ...
    ]
  }
}
````



**에러 응답 예제**

```json
{
  "success":fasle,
  "error":{
    "code":"product_not_found",
    "message":"요청한 제품을 찾을 수 없습니다."
  }
}
```



**응답 구성**

| 파라미터            | 타입    | 필수 | 설명                                                         |
| ------------------- | ------- | ---- | ------------------------------------------------------------ |
| success             | boolean | O    | API 호출 결과 (성공:true / 실패:false)                       |
| data                | Object  | X    | 제품과 제품속성들의 상세 정보<br />- API 호출 결과가 성공(true)일 경우 반환 |
| product             | Object  | O    | 제품 상세 정보                                               |
| id                  | Integer | O    | 제품의 ID                                                    |
| name                | String  | O    | 제품명                                                       |
| barcode             | String  | O    | 제품의 바코드 넘버                                           |
| photo_url           | String  | O    | 제품의 이미지 주소                                           |
| cost                | Integer | O    | 제품의 구매가                                                |
| price               | Integer | O    | 제품의 판매가                                                |
| productAttributions | List    | O    | 제품 속성 상세 정보                                          |
| attribution         | Object  | X    | 속성 정보                                                    |
| id                  | Integer | O    | 속성 ID                                                      |
| type                | String  | O    | 속성 type                                                    |
| name                | String  | O    | 속성 이름                                                    |
| value               | String  | X    | 속성 내용                                                    |
| error               | Object  | X    | 오류 원인에 대한 설명 <br />- API 호출 결과가 싱패(false)일 경우 반환<br />- 상세 에러 코드는 공통 API 가이드- 공통 Error [참고](./careerbuilder_v1_api.md) |
|                     |         |      | `product_not_found` : 요청한 제품을 찾을 수 없음.            |



---



## 조회 /products/

제품 리스트를 조회합니다.

### Request

**요청 예제**

```bash
curl -X GET http://http://example.careerbuilder.com/api/v1/products \
   -H "Content-Type: application/json;charset=utf-8"
````

**요청 방식**

| 메서드 | 요청 URL                                    |
| ------ | ------------------------------------------- |
| GET    | http://ex.careerbuilder.com/api/v1/products |



### Response

**성공 응답 예제**

```json
{
  "success":true,
  "data":{
    [
    {
      "id":1,
      "name":"name",
      "barcode":"barcode",
      "photo_url":"photo_url",
      "cost":1000,
      "price":2000      
    },
    {
      "id":2,
      "name":"name",
      "barcode":"barcode",
      "photo_url":"photo_url",
      "cost":1000,
      "price":2000      
    }
    ...
    ]
  }
}
````



**응답 구성**

| 파라미터  | 타입    | 필수 | 설명                                                         |
| --------- | ------- | ---- | ------------------------------------------------------------ |
| success   | boolean | O    | API 호출 결과 (성공:true / 실패:false)                       |
| data      | Object  | X    | 제품과 제품속성들의 상세 정보<br />- API 호출 결과가 성공(true)일 경우 반환 |
| product   | Object  | O    | 제품 상세 정보                                               |
| id        | Integer | O    | 제품의 ID                                                    |
| name      | String  | O    | 제품명                                                       |
| barcode   | String  | O    | 제품의 바코드 넘버                                           |
| photo_url | String  | O    | 제품의 이미지 주소                                           |
| cost      | Integer | O    | 제품의 구매가                                                |
| price     | Integer | O    | 제품의 판매가                                                |
| error     | Object  | X    | 오류 원인에 대한 설명 <br />- API 호출 결과가 싱패(false)일 경우 반환<br />- 상세 에러 코드는 공통 API 가이드- 공통 Error [참고](./careerbuilder_v1_api.md) |
|           |         |      | `product_not_found` : 요청한 제품을 찾을 수 없음.            |



---



## 수정 /products/{id}

제품을 수정합니다.

### Request

**요청 예제**

```bash
curl -X PUT http://http://example.careerbuilder.com/api/v1/products/{product_id} \
   -H "Content-Type: application/json;charset=utf-8" \
   -d '{
     "name":"{PRODUCT_NAME}",
     "barcode":"{BARCODE}",
     "photo_url":"{PHOTO_URL}",
     "cost":"{COST}",
     "price":"{PRICE}"
     }'
````

**요청 방식**

| 메서드 | 요청 URL                                                 |
| ------ | -------------------------------------------------------- |
| PUT    | http://ex.careerbuilder.com/api/v1/products/{product_id} |

**요청 방식 Path Parameter**

| 파라미터   | 타입    | 필수 | 설명                 |
| ---------- | ------- | ---- | -------------------- |
| product_id | Integer | O    | 조회하려는 제품의 id |

**요청 구성**

| 파라미터 | 타입    | 필수 | 설명               |
| -------- | ------- | ---- | ------------------ |
| name     | String  | O    | 제품명             |
| barcode  | String  | X    | 제품의 바코드 넘버 |
| photoUrl | String  | X    | 제품의 이미지 주소 |
| cost     | Integer | O    | 제품의 구매가      |
| price    | Integer | O    | 제품의 판매가      |

### Response

**성공 응답 예제**

```json
{
  "success":true,
  "data":{
    "id":1,
    "name":"new name",
    "barcode":"new barcode",
    "photo_url":"new photo_url",
    "cost":10000,
    "price":20000
  }
}
````



**에러 응답 예제**

```json
{
  "success":fasle,
  "error":{
    "code":"product_not_found",
    "message":"요청한 제품을 찾을 수 없습니다."
  }
}
```



**응답 구성**

| 파라미터  | 타입    | 필수 | 설명                                                         |
| --------- | ------- | ---- | ------------------------------------------------------------ |
| success   | boolean | O    | API 호출 결과 (성공:true / 실패:false)                       |
| data      | Object  | X    | 제품의 상세 정보<br />- API 호출 결과가 성공(true)일 경우 반환 |
| id        | Integer | O    | 제품의 ID                                                    |
| name      | String  | O    | 제품명                                                       |
| barcode   | String  | O    | 제품의 바코드 넘버                                           |
| photo_url | String  | O    | 제품의 이미지 주소                                           |
| cost      | Integer | O    | 제품의 구매가                                                |
| price     | Integer | O    | 제품의 판매가                                                |
| error     | Object  | X    | 오류 원인에 대한 설명 <br />- API 호출 결과가 싱패(false)일 경우 반환<br />- 상세 에러 코드는 공통 API 가이드- 공통 Error [참고](./careerbuilder_v1_api.md) |
|           |         |      | `product_not_found` : 요청한 제품을 찾을 수 없음.            |



---





## 삭제 /products/{id}

제품을 삭제합니다.

### Request

**요청 예제**

```bash
curl -X DELETE http://http://example.careerbuilder.com/api/v1/products/{product_id} \
   -H "Content-Type: application/json;charset=utf-8" 
````

**요청 방식**

| 메서드 | 요청 URL                                                 |
| ------ | -------------------------------------------------------- |
| DELETE | http://ex.careerbuilder.com/api/v1/products/{product_id} |

**요청 방식 Path Parameter**

| 파라미터   | 타입    | 필수 | 설명                 |
| ---------- | ------- | ---- | -------------------- |
| product_id | Integer | O    | 조회하려는 제품의 id |

### Response

**성공 응답 예제**

```json
{
  "success":true
}
````



**에러 응답 예제**

```json
{
  "success":fasle,
  "error":{
    "code":"product_not_found",
    "message":"요청한 제품을 찾을 수 없습니다."
  }
}
```



**응답 구성**

| 파라미터 | 타입    | 필수 | 설명                                                         |
| -------- | ------- | ---- | ------------------------------------------------------------ |
| success  | boolean | O    | API 호출 결과 (성공:true / 실패:false)                       |
| error    | Object  | X    | 오류 원인에 대한 설명 <br />- API 호출 결과가 싱패(false)일 경우 반환<br />- 상세 에러 코드는 공통 API 가이드- 공통 Error [참고](./careerbuilder_v1_api.md) |
|          |         |      | `product_not_found` : 요청한 제품을 찾을 수 없음.            |

