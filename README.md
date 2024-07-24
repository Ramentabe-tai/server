# Back-end Memo

## Member Request

### Endpoints
| HTTP Method | Endpoint          | 설명      |
|-------------|-------------------|-----------|
| POST        | /api/member/join  | 회원 등록 |
| POST        | /api/member/login | 로그인    |

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
