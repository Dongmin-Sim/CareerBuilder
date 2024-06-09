## Transaction 거래
거래는 제품의 재고량 변화를 일으키는 행위입니다.
거래는 특정 type을 가집니다.
- IN("입고") : 재고를 증가시킬때 사용합니다.
- OUT("출고") : 재고가 감소시킬때 사용됩니다.
- ADJUST("조정") : 재고를 수정할 때 사용합니다.
- MOVE("이동") : 재고간의 이동이 있을때 사용합니다.

#### 2.5.1 출고, 재고 감소 행위
- **목적** : 특정 위치의 특정 상품의 재고를 감소시킬 수 있습니다.
- **입력** : 거래타입(OUT), 위치식별자(출고지), 제품리스트<제품식별자, 수량>
- **출력** : 거래식별자(ID), 제품별 현재 재고량
    1. 거래타입과 위치식별자(출고지)는 필수값입니다.
    2. 거래타입은 출고를 나타내는 "OUT" 이어야만 합니다.
    3. 출고할 제품리스트는 최소 한개 이상이어야 합니다.
    4. 위치식별자(출고지)를 가진 위치가 존재하지 않는 경우 작업을 수행할 수 없습니다.
    5. 제품식별자를 가진 제품이 존재하지 않는 경우 작업을 수행할 수 없습니다.
    6. 위치식별자(출고지)에 제품식별자를 가진 제품이 없는 경우 작업을 수행할 수 없습니다.
    7. 위치식별자(출고지)의 재고가, 출고할 수량보다 적어도 가능합니다. (마이너스 재고 허용)
    8. 수량은 음수값을 가질 수 없습니다.

#### 2.5.2 입고, 재고 증가 행위
- **목적** : 특정 위치의 특정 상품의 재고를 증가시킬 수 있습니다.
- **입력** : 거래타입(IN), 위치식별자(입고지), 제품리스트<제품식별자, 수량>
- **출력** : 거래식별자(ID), 제품별 현재 재고량
- **제한 사항**
    1. 거래타입과 위치식별자(입고지)는 필수값입니다.
    2. 거래타입은 입고를 나타내는 "IN" 이어야만 합니다.
    3. 입고할 제품리스트는 최소 한개 이상이어야 합니다.
    4. 위치식별자(입고지)를 가진 위치가 존재하지 않는 경우 작업을 수행할 수 없습니다.
    5. 제품식별자를 가진 제품이 존재하지 않는 경우 작업을 수행할 수 없습니다.
    6. 위치식별자(입고지)에 제품식별자를 가진 제품이 없는 경우 작업을 수행할 수 없습니다.
    7. 수량은 음수값을 가질 수 없습니다.

#### 2.5.3 이동, 재고 이동 행위
- **목적** : 특정 위치들의 특정 상품의 재고를 이동시킬 수 있습니다.
- **입력** : 거래타입(MOVE), 위치식별자(촐고지), 위치식별자(입고지), 제품리스트<제품식별자, 수량>
- **출력** : 거래식별자(ID), 제품별 이동량
- **제한 사항**
    1. 거래타입과 위치식별자(출고지), 위치식별자(입고지)는 필수값입니다.
    2. 거래타입은 입고를 나타내는 "MOVE" 이어야만 합니다.
    3. 입고할 제품리스트는 최소 한개 이상이어야 합니다.
    4. 위치식별자(출고지)를 가진 위치가 존재하지 않는 경우 작업을 수행할 수 없습니다.
    5. 위치식별자(입고지)를 가진 위치가 존재하지 않는 경우 작업을 수행할 수 없습니다.
    6. 제품식별자를 가진 제품이 존재하지 않는 경우 작업을 수행할 수 없습니다.
    7. 위치식별자(출고지)에 제품식별자를 가진 제품이 없는 경우 작업을 수행할 수 없습니다.
    8. 위치식별자(출고지)의 재고가, 이동할 수량보다 적어도 가능합니다 (마이너스 재고 허용)
    9. 수량은 음수값을 가질 수 없습니다.

#### 2.5.4 조정, 재고 조정 행위
- **목적** : 특정 위치의 특정 상품의 재고를 변경할 수 있습니다.
- **입력** : 거래타입(ADJUST), 위치식별자, 제품리스트<제품식별자, 수량>
- **출력** : 거래식별자(ID), 제품별 재고량
- **제한 사항**
    1. 거래타입과 위치식별자는 필수값입니다.
    2. 거래타입은 입고를 나타내는 "ADJUST" 이어야만 합니다.
    3. 조정할 제품리스트는 최소 한개 이상이어야 합니다.
    4. 위치식별자를 가진 위치가 존재하지 않는 경우 작업을 수행할 수 없습니다.
    5. 제품식별자를 가진 제품이 존재하지 않는 경우 작업을 수행할 수 없습니다.

#### 2.5.5 거래 수정 행위
- **목적** : 거래내역을 수정할 수 있습니다.
- **입력** : 거래식별자(ID), 위치식별자(출발지 or 도착지), 거래처식별자, 메모, 제품리스트<제품식별자, 수량>
- **출력**
    - 성공시 : 거래식별자(ID)를 포함한 수정 내역
    - 실패시 : 실패코드를 담은 표준 에러 응답
- **제한 사항**
    1. 거래타입은 수정할 수 없습니다.
    2. 조정할 제품리스트는 최소 한개 이상이어야 합니다.
    3. 위치식별자를 가진 위치가 존재하지 않는 경우 작업을 수행할 수 없습니다.
    4. 제품식별자를 가진 제품이 존재하지 않는 경우 작업을 수행할 수 없습니다.
    5. 거래가 수정될 경우, 수정 이전 거래의 제품과 수량이 재고에 반영되어야 합니다.
        1. 예시1) [재고수량: 200] [거래타입: IN, 제품id: 1, 수량: 200]인 거래를 [수량: 100]으로 수정할 경우 제품id:1의 재고는 100이 되어야 합니다.
    6.  