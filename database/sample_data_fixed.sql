-- Dữ liệu mẫu cho bảng guests (ĐÃ SỬA LỖI TRÙNG LẶP)
-- Người gửi (SENDER)
INSERT INTO guests (full_name, phone_number, email, address, guest_type, is_active) VALUES
('Nguyễn Văn An', '0123456789', 'nguyenvanan@gmail.com', '123 Đường Lê Lợi, Quận 1, TP.HCM', 'SENDER', true),
('Trần Thị Bình', '0987654321', 'tranthibinh@yahoo.com', '456 Đường Nguyễn Huệ, Quận 3, TP.HCM', 'SENDER', true),
('Lê Văn Cường', '0369852147', 'levancuong@gmail.com', '789 Đường Võ Văn Tần, Quận 3, TP.HCM', 'SENDER', true),
('Phạm Thị Dung', '0521478963', 'phamthidung@gmail.com', '321 Đường Hai Bà Trưng, Quận 1, TP.HCM', 'SENDER', true),
('Hoàng Văn Em', '0147852369', 'hoangvanem@yahoo.com', '654 Đường Pasteur, Quận 1, TP.HCM', 'SENDER', true);

-- Người nhận (RECEIVER)
INSERT INTO guests (full_name, phone_number, email, address, guest_type, is_active) VALUES
('Vũ Thị Phương', '0258963147', 'vuthiphuong@gmail.com', '147 Đường Cách Mạng Tháng 8, Quận 10, TP.HCM', 'RECEIVER', true),
('Đặng Văn Giang', '0369852148', 'dangvangiang@yahoo.com', '258 Đường 3/2, Quận 10, TP.HCM', 'RECEIVER', true),
('Bùi Thị Hoa', '0478963251', 'buithihoa@gmail.com', '369 Đường Sư Vạn Hạnh, Quận 10, TP.HCM', 'RECEIVER', true),
('Ngô Văn Inh', '0589632147', 'ngovaninh@gmail.com', '741 Đường Lý Thường Kiệt, Quận 10, TP.HCM', 'RECEIVER', true),
('Đinh Thị Kim', '0698521473', 'dinhthikim@yahoo.com', '852 Đường Ngô Gia Tự, Quận 10, TP.HCM', 'RECEIVER', true);

-- Thêm một số khách hàng ở Hà Nội
INSERT INTO guests (full_name, phone_number, email, address, guest_type, is_active) VALUES
('Lý Văn Lan', '0123456788', 'lyvanlan@gmail.com', '123 Đường Trần Phú, Ba Đình, Hà Nội', 'SENDER', true),
('Mai Thị Mỹ', '0987654322', 'maithimy@yahoo.com', '456 Đường Nguyễn Trãi, Thanh Xuân, Hà Nội', 'RECEIVER', true),
('Võ Văn Nam', '0369852149', 'vovannam@gmail.com', '789 Đường Lê Duẩn, Đống Đa, Hà Nội', 'SENDER', true),
('Hồ Thị Oanh', '0521478964', 'hothioanh@gmail.com', '321 Đường Trần Duy Hưng, Cầu Giấy, Hà Nội', 'RECEIVER', true);

-- Thêm một số khách hàng ở Đà Nẵng
INSERT INTO guests (full_name, phone_number, email, address, guest_type, is_active) VALUES
('Tô Văn Phúc', '0147852370', 'tovanphuc@gmail.com', '123 Đường Nguyễn Văn Linh, Hải Châu, Đà Nẵng', 'SENDER', true),
('Lưu Thị Quỳnh', '0258963148', 'luuthiquynh@yahoo.com', '456 Đường Lê Duẩn, Hải Châu, Đà Nẵng', 'RECEIVER', true),
('Hà Văn Rồng', '0369852150', 'havanrong@gmail.com', '789 Đường Trần Phú, Hải Châu, Đà Nẵng', 'SENDER', true);

-- Thêm một số khách hàng test với email trống
INSERT INTO guests (full_name, phone_number, address, guest_type, is_active) VALUES
('Test User 1', '0123456787', '123 Test Street, Test City', 'SENDER', true),
('Test User 2', '0987654323', '456 Test Avenue, Test City', 'RECEIVER', true); 