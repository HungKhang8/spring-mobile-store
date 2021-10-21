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
5. phần Set Properties để trống
6. Nhấn OK

Tài khoản admin để create Product:
* username: admin1
* password: 123

(không gõ password y chang trong DB, password trong DB là đã mã hóa)

1. Nguyen Gia Nguyen:
```
main/java/com/fa/springmobilestore/config/SpringMobileStoreApplication.java
main/java/com/fa/springmobilestore/config/SpringWebAppInitializer.java
main/java/com/fa/springmobilestore/config/WebMvcConfig.java
main/java/com/fa/springmobilestore/config/WebSecurityConfig.java
```
2. Nguyen Thien Phuc:
```
main/java/com/fa/springmobilestore/controller/CartController.java
main/java/com/fa/springmobilestore/utils/Utils.java
main/java/com/fa/springmobilestore/model/CartInfo.java
main/java/com/fa/springmobilestore/model/CartItemInfo.java
```
3. Nguyen Tuan Hoang:
```
main/java/com/fa/springmobilestore/entity/Product.java
main/java/com/fa/springmobilestore/dao/ProductDAO.java
main/java/com/fa/springmobilestore/form/ProductForm.java
main/java/com/fa/springmobilestore/validator/ProductFormValidator.java
```
4. Nguyen Van Lung:
```
main/resources/application.properties
main/resources/templates/index.html
main/resources/templates/productDetails.html
main/resources/static/css/home.css
```
5. Hoang Anh Vu:
```
main/java/com/fa/springmobilestore/config/MobileStoreDBAuthentication.java
main/java/com/fa/springmobilestore/controller/AdminController.java
main/java/com/fa/springmobilestore/entity/User.java
main/java/com/fa/springmobilestore/dao/UserDAO.java
```
6. Nguyen Tien Dung:
```
main/resources/validation.properties
main/java/com/fa/springmobilestore/controller/MainController.java
main/java/com/fa/springmobilestore/controller/ProductController.java
main/java/com/fa/springmobilestore/model/ProductInfo.java
```
7. Chung Hung Khang:
```
main/resources/templates/createProduct.html
main/resources/templates/login.html
main/resources/templates/cart.html
main/resources/static/css/createProduct.css
main/resources/static/css/login.css
main/resources/static/css/cart.css
```
