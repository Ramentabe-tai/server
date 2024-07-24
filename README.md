# Back-end Memo

## Member Request

### Endpoints
| HTTP Method | Endpoint          | 설명      |
|-------------|-------------------|-----------|
| POST        | /api/member/join  | 会員登録 |
| POST        | /api/member/login | ログイン    |

### /api/member/join

#### Request Example
```json
{
  "name": "石原 さとみ",
  "ruby": "イシハラ サトミ",
  "email": "example@example.com",
  "password": "password1004",
  "confirmPassword": "password1004",
  "phoneNumber": "08047830333"
}
```

#### Response Example
```json
{
		"id" : 1,
    "name": "石原　さとみ",
		"ruby" : "イシハラ サトミ",
    "email": "satomi1004@gmail.com",
    "phoneNumber": "08047830333",
    "savingAccountId": 1
}
```

### /api/member/login
```json
`` FORM DATAで送ること ``
{
		"email" : "satomi1004@gmail.com",
    "password": "password1004"
    
    "email" : "",
    "password": "dnflwlq1408"
}
```

