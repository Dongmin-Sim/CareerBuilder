---
description: 제품은 재고관리 핵심 기본 단위
---

# Products 제품

제품은 재고관리를 하는 핵심 기본 단위입니다. 제품은 여러 속성을 가질 수 있습니다.



## 제품 생성

* 사용자는 제품을 생성할 수 있습니다.
* 제품은 다음과 같은 속성을 갖습니다.

<table data-view="cards" data-full-width="false"><thead><tr><th>이름</th><th>내용</th></tr></thead><tbody><tr><td>제품명</td><td><ul><li>필수값이어야 합니다.</li><li>제품명은 유일하지 않아도 됩니다.(중복허용)</li><li>최대 255자 이어여 합니다.</li></ul></td></tr><tr><td>바코드</td><td><ul><li>필수값이 아닙니다.</li><li>바코드는 유일해야 합니다.</li></ul></td></tr><tr><td>이미지</td><td><ul><li>필수값이 아닙니다.</li></ul></td></tr><tr><td>구매가</td><td><ul><li>필수값이 아닙니다.</li><li>사용자 입력값이 없을 경우 기본값은 0입니다.</li><li>999,999,999.999 이상 999,999,999.999 이하 정수값이 이어야합니다.</li><li>소수점이 3자리수를 넘으면 안됩니다.</li></ul></td></tr><tr><td>판매가</td><td><ul><li>필수값이 아닙니다.</li><li>사용자 입력값이 없을 경우 기본값은 0입니다.</li><li>999,999,999.999 이상 999,999,999.999 이하 정수값이 이어야합니다.</li><li>소수점이 3자리수를 넘으면 안됩니다.</li></ul></td></tr><tr><td>초기 수량(재고)</td><td><ul><li>필수값이어야 합니다.</li><li>사용자 입력값이 없을 경우 기본값은 0입니다.</li><li>초기 수량은 최초 제품등록 시에만 설정 가능합니다.</li><li>음수도 입력가능합니다.</li></ul></td></tr><tr><td>장소</td><td><ul><li>필수값이 아닙니다.</li><li>사용자 정의 속성</li><li><a data-mention href="locations.md">locations.md</a> 참고</li></ul></td></tr><tr><td>제품 속성</td><td><p></p><ul><li>필수값이 아닙니다.</li><li>사용자 정의 속성</li><li>작성하지 않을 경우 null 값을 갖습니다.</li><li><a data-mention href="product-attributions.md">product-attributions.md</a></li></ul></td></tr><tr><td>생성일</td><td><p></p><ul><li>자동 생성됩니다.</li></ul></td></tr></tbody></table>

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/products" method="post" expanded="true" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 제품명, 바코드, 이미지, 구매가, 판매가는 **기본 속성**입니다.
  * **사용자가 임의로 삭제하거나 변경할 수 없습니다.**
* 사용자는 원하는 속성이 있을 경우 사용자 정의 속성인 **제품 속성**을 생성하여 추가할 수 있습니다.
* 사용자는 원하는 장소가 있을 경우 사용자 정의 속성인 **장소**을 생성하여 추가할 수 있습니다.
* 제품 추가에 성공하면 제품명과 함께 성공메시지를 알려줍니다.



{% hint style="info" %}
**제품 속성**과 **장소**는 별도의 도메인 필드입니다.
{% endhint %}

{% content-ref url="product-attributions.md" %}
[product-attributions.md](product-attributions.md)
{% endcontent-ref %}

{% content-ref url="locations.md" %}
[locations.md](locations.md)
{% endcontent-ref %}

***

## 제품 조회

### 제품 전체 조회

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/products" method="get" expanded="true" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 사용자는 등록한 제품을 전체 조회할 수 있습니다.
  * 조회한 제품들의 전체 리스트를 확인할 수 있습니다.
  * 제품들의 상세 내역을 확인할 수 있습니다.
  * 제품들의 현재 `재고량`를 확인할 수 있습니다.

### 제품 상세 조회

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/products/{product_id}" method="get" expanded="true" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 사용자는 등록한 제품의 상세정보를 조회할 수 있습니다.
  * 기본속성
    * 사용자는 상세 조회를 통해 제품의 `제품명`을(를) 확인할 수 있습니다.
    * 사용자는 상세 조회를 통해 제품의 `바코드`을(를) 확인할 수 있습니다.
    * 사용자는 상세 조회를 통해 제품의 `이미지`을(를) 확인할 수 있습니다.
    * 사용자는 상세 조회를 통해 제품의 `구매가`을(를) 확인할 수 있습니다.
    * 사용자는 상세 조회를 통해 제품의 `판매가`을(를) 확인할 수 있습니다.
  * 사용자 정의 속성
    * 사용자는 상세 조회를 통해 제품의 `제품 속성`을(를) 확인할 수 있습니다. (ex. 사이즈, 카테고리, 안전재고 등)
  * 제품의 현재 `재고량`을 확인할 수 있습니다.



## 제품 수정

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/products/{product_id}" method="put" expanded="true" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 사용자는 등록한 제품의 속성을 수정할 수 있습니다.
  * 기본속성
    * 사용자는 제품의 `제품명`을(를) 수정할 수 있습니다.
    * 사용자는 제품의 `바코드`을(를) 수정할 수 있습니다.
    * 사용자는 제품의 `이미지`을(를) 수정할 수 있습니다.
    * 사용자는 제품의 `구매가`을(를) 수정할 수 있습니다.
    * 사용자는 제품의 `판매가`을(를) 수정할 수 있습니다.
  * 사용자 정의 속성
    * 사용자는 제품의 `제품 속성`을(를) 수정할 수 있습니다. (ex. 사이즈, 카테고리, 안전재고 등)

## 제품 삭제

* 사용자는 등록한 제품을 삭제할 수 있습니다.
  * 제품을 삭제할 경우 다시 되돌릴 수 없습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/products/{product_id}" method="delete" expanded="true" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

