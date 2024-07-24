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

#### Request Example
```json
`` FORM DATAで送ること ``
{
    "email" : "satomi1004@gmail.com",
    "password": "password1004"
    
    "email" : "",
    "password": "dnflwlq1408"
}
```

#### Response Example
### ログイン成功時
![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/7bd378ee-044b-4bee-82b4-7d77fb6bca25/c17e72f0-c4e1-411f-a4ea-b6c9d296925b/Untitled.png)
```json
`` まだテスト段階なので、ResponseBodyにJwtTokenとUserデータを表示しといた（削除予定） ``
`` 今後はJwt TokenをResponseHeaderにAddするので、Tokenを使ってUserを識別すること ``
{
    "role": [
        {
            "authority": "ROLE_ADMIN"
        }
    ],
    "member": {
        "id": 2,
        "name": "石原　さとみ",
        "ruby": "イシハラ サトミ",
        "email": "satomi1004@gmail.com",
        "phoneNumber": "08047830333",
        "rankPoint": 0,
        "accountId": 1
    },
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Impvbmd3b24zMzQwQGdtYWlsLmNvbSIsInJvbGUiOiJST0xFX0FETUlOIiwiaWF0IjoxNzE4MTE3OTA2LCJleHAiOjE3MTgxNTM5MDZ9.05jJTIpitM-Pn5NGbJi9P5WNPnCsk_bdQYCtaEkkrpk"
}
```

