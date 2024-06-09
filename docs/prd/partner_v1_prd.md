## 1. 거래처
거래처는 제품의 재고의 거래(변화)를 발생시키는 대상입니다.

#### 2.4.1 거래처 생성
- **목적** : 거래처를 생성합니다.
- **입력** : 거래처의 종류, 이름, 전화번호, 이메일, 주소, 메모
- **출력** : 생성된 거래처 식별자(ID), 종류, 이름, 전화번호, 이메일, 주소, 메모를 담은 정보
- 제한 사항
    1. 거래처의 종류와 이름은 필수값입니다.
    2. 거래처의 종류는 "입고처", "출고처" 중 하나이어야만 합니다.

#### 2.4.2 거래처 리스트 조회
- **목적** : 등록된 거래처의 리스트를 조회합니다.
- **입력** : 없음
- **출력** : 등록된 거래처 리스트
- **제한 사항**
    1. 한번에 불러올 거래처의 개수는 30개로 제한합니다.

#### 2.4.3 거래처 상세 조회
- **목적** : 등록된 거래처의의 상세정보를 조회합니다.
- **입력** : 거래처 식별자(ID)
- **출력** : 거래처 식별자(ID), 종류, 이름, 전화번호, 이메일, 주소, 메모
- **제한 사항**
    1. 거래처 식별자(ID)는 필수값입니다.
    2. 거래처 식별자(ID)를 가진 거래처가 존재하지 않는 경우, 조회할 수 없습니다.

#### 2.4.4 거래처 수정
- **목적** : 등록된 거래처의 정보를 수정합니다.
- **입력** : 거래처 식별자(ID)와 수정할 정보(이름, 전화번호, 이메일, 주소, 메모)
- **출력** : 수정된 거래처 식별자(ID), 이름, 전화번호, 이메일, 주소, 메모
- **제한 사항**
    1. 거래처 식별자(ID)와 수정할 제품 정보는 필수값입니다.
    2. 거래처 식별자(ID)를 가진 제품이 존재하지 않는 경우, 수정할 수 없습니다.
    3. 거래처의 종류는 수정할 수 없습니다.

#### 2.4.5 거래처 삭제
- **목적** : 등록된 거래처를 삭제합니다.
- **입력** : 거래처 식별자(ID)
- **출력** : 삭제된 거래처 식별자(ID)
- **제한 사항**
    1. 거래처 식별자(ID)는 필수값입니다.
    2. 거래처 식별자(ID)를 가진 제품이 존재하지 않는 경우, 삭제할 수 없습니다.