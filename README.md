### jte gradle multi module issue - https://github.com/casid/jte/issues/278
* 추가: https://github.com/casid/jte/tree/278-module-support (제작자는 하나의 ROOT 디렉토리로 통일해서 사용할 것을 생각하였으나 multi-module의 경우 module 별 root가 달라서 지원을 못하였었음)

  -> Mustaches로 대체 구현

### Core 모듈 설정 관리
 -> 현재는 JPA만 추가하였지만, Jdbc Pool 설정, ObjectMapper도 추가할 예정임
 이렇게 두면 특정 모듈에서 CoreConfig에 Import 시켜서 쉽게 설정 가져올 수 있음
 
### client fw는 `RestClients`로
 -> 비동기 지원 안하지만 깔끔하고 무엇보다 사용해 본적이 없어서 그냥 사용해봄

### view에서 사용하는 객체의 명칭은 `~Model`로 한다.
 -> 화면에서 사용하는 객체는 Model로 칭함.
 DTO, VO 말고 다른걸로 작명하였음(우선 MVC니까 ㅋ)