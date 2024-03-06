---
description: 장소는 제품을 보관하는 물리적 장소를 말합니다.  장소별로 제품의 재고량을 가지고 있습니다.
---

# Location 장소

## 장소 생성

* 사용자는 장소를 생성할 수 있습니다.
* 장소는 이름과 메모로 이루어져 있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/locations" method="post" expanded="false" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}



## 장소 조회

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/locations" method="get" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/locations/{location_id}" method="get" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 사용자는 등록한 장소를 조회할 수 있습니다.
* 장소의 이름과 메모, 재고 수량을 확인할 수 있습니다.



## 장소 수정

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/locations/{location_id}" method="put" expanded="false" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 사용자는 등록한 장소을 수정할 수 있습니다.
* 장소의 이름과 메모를 수정할 수 있습니다.
  * 수정할 새로운 장소의 이름은 기존 다른 장소의 이름과 중복되면 안됩니다.



## 장소 삭제

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/locations/{location_id}" method="delete" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 사용자는 등록한 장소를 삭제할 수 있습니다.
* 장소가 삭제되면 해당 위치에 있는 제품의 재고들도 같이 삭제됩니다.





