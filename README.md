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
![loginSuccessReadMe](https://github.com/user-attachments/assets/abf2fcd1-d99c-4a07-8b42-bc9944cf0c75)
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

### ログイン失敗時
```json
{
    "error": "email or password is incorrect"
}
```


## Account Request

| HTTP Method | Endpoint | 説明 |
| --- | --- | --- |
| GET | /api/accounts/{account_id}/ |  |
| GET | /api/accounts/{account_id}/balance | 現在高 |
| GET | /api/accounts/{account_id}/saving-balance | 貯金額 |
| GET | /api/accounts/{account_id}/transactions?type= | 入出金詳細照会 |
| POST | /api/accounts/{account_id}/expense | 出金登録 |
| POST | /api/accounts/{account_id}/income | 入金登録 |
| GET | /api/categories | カテゴリリスト |
| GET | /api/member/{member_id}/missions | 各メンバのミッションリスト |
| PATCH | /api/member/{member_id}/missions/{mission_id} | ミッションクリアのRequest |
| GET | /api/member/{member_id}/exp | メンバの経験値 |

### /api/accounts/{account_id}/balance

#### Request Example
```json
`` Request Data なし ``
```

#### Response Example
```json
{
    "balance": 799000
}
```

### /api/accounts/{account_id}/saving-balance

#### Request Example
```json
`` Request Data なし ``
```

#### Response Example
```json
{
    "savingBalance": 12000
}
```

### /api/accounts/{account_id}/income

#### Request Example
```json
{
		"amount": 3000,
		"memo" : "test income"
}
```
#### Response Example
```json
{
    "deposit": 3000,
    "message": "transaction completed"
}

`` 該当するaccount_idが登録されてない時 ``
{
		"message": "Account not found"
}
```

### /api/accounts/{account_id}/expense

#### Request Example
```json
{
		"amount" : 2000, 
		"memo" : "焼きそば食べた", 
		"categoryId": 1
}
```
#### Response Example
```json
{
    "withdrawal": 2000,
    "message": "transaction completed"
}

`` 該当するaccount_idが登録されてない時 ``
{
		"message": "Account not found"
}

`` 該当するcategoryIdが登録されてない時 ``
{
		"message": "Category not found"
}
```

### /api/accounts/{account_id}/transactions?type=

#### Request Example
```json
/api/accounts/{account_id}/transactions?type=deposit
```

#### Response Example
```json
{
    "data": [
        {
            "month": 1,
            "deposit": 27000
        },
        {
            "month": 2,
            "deposit": 17500
        },
        {
            "month": 3,
            "deposit": 27000
        },
        {
            "month": 4,
            "deposit": 24000
        },
        {
            "month": 5,
            "deposit": 19000
        },
        {
            "month": 6,
            "deposit": 16500
        }
    ]
}
```

#### Request Example
```json
/api/accounts/{account_id}/transactions?type=withdrawal
```

#### Response Example
```json
{
    "data": [
        {
            "month": 1,
            "spending": 15000
        },
        {
            "month": 2,
            "spending": 14500
        },
        {
            "month": 3,
            "spending": 14250
        },
        {
            "month": 4,
            "spending": 19000
        },
        {
            "month": 5,
            "spending": 21000
        },
        {
            "month": 6,
            "spending": 12120
        }
    ]
}
```





