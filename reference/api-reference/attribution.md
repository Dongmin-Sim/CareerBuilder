---
description: 사용자가 정의한 제품 속성을 커스텀 속성이라 칭합니다. 커스텀 속성을 이용하여 제품에 넣을 정보를 커스터마이징 할 수 있습니다.
---

# Product-Attributions 제품 속성



## 제품 속성 생성&#x20;

* 사용자는 제품에 원하는 정보를 추가하기 위해 제품 속성을 생성할 수 있습니다.
* 제품 속성의 생성은 속성의 타입(type, 문자열, 숫자형, 날짜형 지원), 이름(name), 순서(order)으로 이루어져 있습니다.
  * 속성의 타입(type, 문자열, 숫자형, 날짜형 지원), 이름(name) 필수값입니다.
  * 중복을 허용합니다. 유일하지 않아도 됩니다.&#x20;

{% swagger src="../../.gitbook/assets/careerbuilder.yaml" path="/product-attrs" method="post" %}
[careerbuilder.yaml](../../.gitbook/assets/careerbuilder.yaml)
{% endswagger %}



## 제품 속성 조회&#x20;

* 사용자는 등록한 제품 속성을 조회할 수 있습니다.
* 제품 속성의 id, 타입(type, 문자열, 숫자형, 날짜형 지원), 이름(name)과 값(value), 순서(order)를 확인할 수 있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1) (1).yaml" path="/product-attrs" method="get" %}
[careerbuilder (1) (1).yaml](<../../.gitbook/assets/careerbuilder (1) (1).yaml>)
{% endswagger %}

{% swagger src="../../.gitbook/assets/careerbuilder (1) (1).yaml" path="/product-attrs/{attr_id}" method="get" %}
[careerbuilder (1) (1).yaml](<../../.gitbook/assets/careerbuilder (1) (1).yaml>)
{% endswagger %}



## 제품 속성 수정

* 사용자는 등록한 제품 속성을 수정할 수 있습니다.
  * 사용자는 등록된 제품 속성의 이름(name), 값(value), 순서(order) 필드를 수정할 수 있습니다.
  * 사용자는 등록된 제품 **속성의 종류(type)는 수정할 수 없습니다.**
    * 종류를 수정하려면 삭제 후 재등록해야 합니다.
  * 사용자는 등록한 제품 속성의 순서(order)를 변경할 수 있습니다.
  * 제품(product) 조회 시 제품 속성의 순서를 나타내기 위해 사용됩니다. (오름차순으로 정렬, 낮을 수록 첫번째)

{% swagger src="../../.gitbook/assets/careerbuilder (1) (1).yaml" path="/product-attrs/{attr_id}" method="put" %}
[careerbuilder (1) (1).yaml](<../../.gitbook/assets/careerbuilder (1) (1).yaml>)
{% endswagger %}

## 제품 삭제&#x20;

* 사용자는 등록한 제품 속성을 삭제할 수 있습니다.
  * 제품 속성이 삭제되면 해당 속성을 가지고 있는 제품에서 모두 제거됩니다.
  * 삭제하면 되돌릴 수 없습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1) (1).yaml" path="/product-attrs/{attr_id}" method="delete" %}
[careerbuilder (1) (1).yaml](<../../.gitbook/assets/careerbuilder (1) (1).yaml>)
{% endswagger %}

