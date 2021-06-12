## Express Session
- 쿠키생성(connect.sid)하여 세션 키를 저장


### install express session
```shell
    $ npm install express-session
```

### session() option
| Option | Desc |
| name   | 쿠키의 네임 속성 지정 |
| store  | 세션 저장소 지정 |
| cookie | 생성할 쿠키와 관련된 정보 저장 |
| secret | 비밀키 생성 |
| resave | 세션이 변경되지 않았어도 세션 저장소에 반영할지 설정 |
| saveUninitialized| 초기화되지 않은 세션을 세션 저장소에 저장할지 설정  |

### Cookie property default
{ "originalMaxAge": null, 
  "expires": null,
  "httpOnly": true,
  "path": "/"
}

### Request 객체의 session 속성
| 메소드 명      | 설명 |
| regenerate() | 세션을 다시 생성 |
| destroy()    | 세션을 제거 |
| reload()     | 세션을 다시 불러움 |
| save()       | 세션을 저장 |