-- Tạo bảng guests cho module Guest
CREATE TABLE IF NOT EXISTS guests (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL UNIQUE,
    email VARCHAR(100),
    address VARCHAR(500) NOT NULL,
    guest_type VARCHAR(20) NOT NULL CHECK (guest_type IN ('SENDER', 'RECEIVER')),
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

-- Tạo index cho tìm kiếm nhanh
CREATE INDEX IF NOT EXISTS idx_guests_phone_number ON guests(phone_number);
CREATE INDEX IF NOT EXISTS idx_guests_email ON guests(email);
CREATE INDEX IF NOT EXISTS idx_guests_guest_type ON guests(guest_type);
CREATE INDEX IF NOT EXISTS idx_guests_is_active ON guests(is_active);
CREATE INDEX IF NOT EXISTS idx_guests_full_name ON guests(full_name);

-- Tạo trigger để tự động cập nhật updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_guests_updated_at 
    BEFORE UPDATE ON guests 
    FOR EACH ROW 
    EXECUTE FUNCTION update_updated_at_column();

-- Thêm comment cho bảng
COMMENT ON TABLE guests IS 'Bảng lưu thông tin khách hàng (người gửi/nhận)';
COMMENT ON COLUMN guests.id IS 'ID khách hàng';
COMMENT ON COLUMN guests.full_name IS 'Họ tên khách hàng';
COMMENT ON COLUMN guests.phone_number IS 'Số điện thoại (unique)';
COMMENT ON COLUMN guests.email IS 'Email khách hàng';
COMMENT ON COLUMN guests.address IS 'Địa chỉ khách hàng';
COMMENT ON COLUMN guests.guest_type IS 'Loại khách hàng: SENDER hoặc RECEIVER';
COMMENT ON COLUMN guests.is_active IS 'Trạng thái hoạt động';
COMMENT ON COLUMN guests.created_at IS 'Thời gian tạo';
COMMENT ON COLUMN guests.updated_at IS 'Thời gian cập nhật cuối'; 