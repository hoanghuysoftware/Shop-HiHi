# Web Bán Laptop

Ứng dụng web bán laptop cung cấp một nền tảng cho phép người dùng tìm kiếm, xem thông tin và đặt hàng các loại laptop khác nhau.

## Nội Dung

- [Giới Thiệu](#giới-thiệu)
- [Tính Năng](#tính-năng)
- [Cài Đặt](#cài-đặt)
- [Sử Dụng](#sử-dụng)
- [Kiến Trúc](#kiến-trúc)
- [Công Nghệ Sử Dụng](#công-nghệ-sử-dụng)
- [Liên Hệ](#liên-hệ)
- [DB Ứng Dụng](#db-ứng-dụng)

## Giới Thiệu

- Ứng dụng này được phát triển với mục tiêu cung cấp trải nghiệm mua sắm trực tuyến tiện lợi cho người dùng, giúp họ dễ dàng tìm kiếm và so sánh các loại laptop phù hợp với nhu cầu của mình.

## Tính Năng

- **Tìm kiếm sản phẩm**: Người dùng có thể tìm kiếm laptop theo tên, thương hiệu, và các tiêu chí khác.
- **Chi tiết sản phẩm**: Hiển thị thông tin chi tiết về từng sản phẩm, bao gồm cấu hình, giá cả và hình ảnh.
- **Giỏ hàng**: Người dùng có thể thêm sản phẩm vào giỏ hàng và tiến hành thanh toán.
- **Quản lý đơn hàng**: Người dùng có thể theo dõi trạng thái đơn hàng của mình.
- **Đánh giá sản phẩm**: Người dùng có thể để lại đánh giá cho các sản phẩm đã mua.

## Cài Đặt

### Yêu Cầu

- Java JDK 17 trở lên
- Maven
- MySQL (hoặc cơ sở dữ liệu khác nếu cần)

### Hướng Dẫn Cài Đặt

1. Clone repository:
   ```bash
   git clone https://github.com/hoanghuysoftware/Shop-HiHi.git
   cd repository

### Sử Dụng
- Truy cập ứng dụng qua trình duyệt tại địa chỉ http://localhost:8080.
- Tìm kiếm laptop theo nhu cầu của bạn.
- Thêm sản phẩm vào giỏ hàng và thực hiện thanh toán.

## Kiến Trúc

Ứng dụng được xây dựng theo kiến trúc MVC (Model-View-Controller), trong đó:

- **Backend (BE)**: Sử dụng Spring Boot để xử lý logic ứng dụng và trả về API cho frontend. Backend quản lý dữ liệu, xác thực người dùng và xử lý các yêu cầu từ frontend.
- **Frontend (FE)**: Sử dụng ReactJS để tạo giao diện người dùng. Frontend gửi các yêu cầu đến API và nhận dữ liệu từ backend để hiển thị cho người dùng.


### Công Nghệ Sử Dụng
- Backend: Spring Boot, Java
- Frontend: ReactJS
- Cơ sở dữ liệu: MySQL
- Thư viện khác: Hibernate

### Liên Hệ
- Nguyễn Hoàng Huy
- Email: hoanghuy.software@gmail.com

### DB Ứng Dụng
![DB_v2](https://github.com/user-attachments/assets/5e724b6e-01fe-4519-8904-6d2001631102)



