# Hướng dẫn tích hợp Frontend mới

## Cách tích hợp:

### 1. Copy file frontend vào thư mục này:
```
src/main/resources/static/frontend-new/
├── index.html
├── css/
│   └── styles.css
├── js/
│   └── script.js
└── assets/
    └── images/
```

### 2. Truy cập frontend mới tại:
```
http://localhost:9000/frontend-new/
```

### 3. Điều chỉnh API endpoints trong script.js:
```javascript
// Thay đổi từ
const API_BASE_URL = '/api/guest';

// Thành (nếu cần)
const API_BASE_URL = '/api/guest';
```

### 4. Cấu hình CORS trong Spring Boot (nếu cần):
```java
@CrossOrigin(origins = "*")
```

## Cấu trúc thư mục gợi ý:
```
static/
├── index.html          # Frontend demo hiện tại
├── styles.css
├── script.js
└── frontend-new/       # Frontend chính thức
    ├── index.html
    ├── css/
    ├── js/
    └── assets/
``` 