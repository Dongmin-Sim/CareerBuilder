---
description: 거래처는 제품의 재고의 거래(변화)를 발생시키는 대상입니다.
---

# Partner 거래처



## 거래처 생성

* 사용자는 거래처를 생성할 수 있습니다.
* 거래처는 종류(type), 이름(name), 전화번호(phone\_number), 이메일(email), 주소(addree), 메모(memo)로 이루어져있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/partners" method="post" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

## 거래처 조회

* 사용자는 등록한 거래처를 조회할 수 있습니다.
* 거래처의 종류(type), 이름(name), 전화번호(phone\_number), 이메일(email), 주소(addree), 메모(memo)를 확인할 수 있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/partners" method="get" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/partners/{partner_id}" method="get" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

## 거래처 수정

* 사용자는 등록한 거래처를 수정할 수 있습니다.
* 거래처의 이름(name), 전화번호(phone\_number), 이메일(email), 주소(addree), 메모(memo)를 수정할 수 있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/partners/{partner_id}" method="put" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

## 거래처 삭제

* 사용자는 등록한 거래처를 삭제할 수 있습니다.
* 거래처가 삭제되면 해당 거래처로 내역을 검색할 수 없습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/partners/{partner_id}" method="delete" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

