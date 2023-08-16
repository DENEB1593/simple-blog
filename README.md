# Simple Blog 구현

 ---

간단한 blog를 구현한다.

### 개발환경
- jdk 17
- spring boot 3.1.2
- h2 (mysql로 사용해볼까?)
- JWT (fusionauth-jwt)



### jwt key generate
    node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"