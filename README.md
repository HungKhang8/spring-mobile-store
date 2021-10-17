## spring-mobile-store

nhớ cập nhật DB mới (MobileStore.bak):
* tblUser: có thêm cột userRole cho Spring Security, cột password đc đổi thành varchar(128)

Chỉnh connection cho DB:
1. Mở src\main\resources
2. Mở application.properties
3. Chỉnh url, username, password phù hợp

Nếu NetBeans không import nbactions.xml:
1. Mở Properties của project
2. Mở Actions
3. Tìm Run project
4. Chỉnh Execute Goals là spring-boot:run
5. Set Properties để trống
6. Nhấn OK

Tài khoản admin để create Product:
* username: admin1
* password: 123

(password trong DB là đã mã hóa)
