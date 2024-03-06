---
description: 거래는 위지의 제품 재고량 변화를 일으키는 행위입니다.
---

# Transactions 거래

입고, 출고, 조정, 이동을 통해 재고 수량을 변경할수 있습니다.

* **입고** : 재고가 추가 될 때 사용됩니다.
* **출고** : 재고가 빠질 때 사용됩니다.
* **조정** : 입/출고가 아닌 다른 사유로 인해 재고를 수정할 때 사용됩니다. (분실 또는 파손 등)
* **이동** : 위치간 재고를 이동할때 사용됩니다.

## 거래 생성

* 사용자는 거래를 생성할 수 있습니다.
* 거래처는 종류(type, 입고, 출고, 조정, 이동), 상품목록(products), 출고처(from\_location\_id), 입고처(from\_location\_id), 메모(memo)로 이루어져있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder.yaml" path="/location-transactions" method="post" %}
[careerbuilder.yaml](../../.gitbook/assets/careerbuilder.yaml)
{% endswagger %}



## 거래 조회&#x20;

* 사용자는 거래 내역 전체를 조회할 수 있습니다.
  * 거래 내역의 목록의 각 거래의 종류(type, 입고, 출고, 조정, 이동), 거래처(partner), 출고처(from\_location\_id), 입고처(from\_location\_id), 상품목록(products), 수량(quantity), 메모(memo), 생성일(created\_at), 상태(status) 을 확인할 수 있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/location-transactions" method="get" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

* 사용자는 거래 내역의 ID로 거래를 개별로 조회할 수 있습니다.
  * 사용자는 개별 이동 내역에서 종류(type, 입고, 출고, 조정, 이동), 거래처(partner), 출고처(from\_location\_id), 입고처(from\_location\_id), 상품목록(products), 수량(quantity), 메모(memo), 생성일(created\_at)을 확인할 수 있습니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/location-transactions/{transaction_id}" method="get" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}



## 거래 수정&#x20;

* 사용자는 거래를 수정할 수 있어야 합니다.
  * 사용자는 거래처를 수정할 수 있어야 합니다.
  * 사용자는 출발 위치를 수정할 수 있어야 합니다.
  * 사용자는 도착 위치를 수정할 수 있어야 합니다.
  * 사용자는 제품을 수정을 할 수 있어야 합니다.
  * 사용자는 메모를 수정할 수 있어야 합니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/location-transactions/{transaction_id}" method="put" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}



## 거래 삭제

* 사용자는 거래 삭제할 수 있어야 합니다.
  * 단, 삭제 시 메모를 입력할 수 있습니다.
  * 거래가 삭제될 시 출발지와 도착지 위치에 있는 재고도 거래 전으로 변경되어야 합니다.

{% swagger src="../../.gitbook/assets/careerbuilder (1).yaml" path="/location-transactions/{transaction_id}" method="delete" %}
[careerbuilder (1).yaml](<../../.gitbook/assets/careerbuilder (1).yaml>)
{% endswagger %}

