package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos.GuestCreateDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos.GuestResponseDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos.GuestSearchDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.entities.Guest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.services.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Cho phép CORS cho frontend
public class GuestController {
    
    private final GuestService guestService;
    
    // POST /api/guest - Tạo mới khách hàng
    @PostMapping
    public ResponseEntity<GuestResponseDto> createGuest(@Valid @RequestBody GuestCreateDto createDto) {
        try {
            GuestResponseDto response = guestService.createGuest(createDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // GET /api/guest/{id} - Lấy thông tin khách hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<GuestResponseDto> getGuestById(@PathVariable UUID id) {
        try {
            GuestResponseDto response = guestService.getGuestById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET /api/guest/phone/{phoneNumber} - Lấy thông tin khách hàng theo số điện thoại
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<GuestResponseDto> getGuestByPhoneNumber(@PathVariable String phoneNumber) {
        try {
            GuestResponseDto response = guestService.getGuestByPhoneNumber(phoneNumber);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // PUT /api/guest/{id} - Cập nhật thông tin khách hàng
    @PutMapping("/{id}")
    public ResponseEntity<GuestResponseDto> updateGuest(@PathVariable UUID id,
                                                       @Valid @RequestBody GuestCreateDto updateDto) {
        try {
            GuestResponseDto response = guestService.updateGuest(id, updateDto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // DELETE /api/guest/{id} - Xóa khách hàng (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable UUID id) {
        try {
            guestService.deleteGuest(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET /api/guest - Lấy tất cả khách hàng đang active
    @GetMapping
    public ResponseEntity<List<GuestResponseDto>> getAllGuests() {
        List<GuestResponseDto> guests = guestService.getAllActiveGuests();
        return ResponseEntity.ok(guests);
    }
    
    // GET /api/guest/search - Tìm kiếm khách hàng
    @GetMapping("/search")
    public ResponseEntity<List<GuestResponseDto>> searchGuests(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address) {
        
        GuestSearchDto searchDto = new GuestSearchDto(fullName, phoneNumber, email, address);
        List<GuestResponseDto> guests = guestService.searchGuests(searchDto);
        return ResponseEntity.ok(guests);
    }
    
    // GET /api/guest/type/{guestType} - Lấy khách hàng theo loại
    @GetMapping("/type/{guestType}")
    public ResponseEntity<List<GuestResponseDto>> getGuestsByType(@PathVariable Guest.GuestType guestType) {
        List<GuestResponseDto> guests = guestService.getGuestsByType(guestType);
        return ResponseEntity.ok(guests);
    }
    
    // GET /api/guest/check/{phoneNumber} - Kiểm tra số điện thoại đã tồn tại chưa
    @GetMapping("/check/{phoneNumber}")
    public ResponseEntity<Boolean> checkPhoneNumberExists(@PathVariable String phoneNumber) {
        boolean exists = guestService.existsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(exists);
    }
    
    // GET /api/guest/find/{phoneNumber} - Tìm khách hàng theo số điện thoại (không throw exception)
    @GetMapping("/find/{phoneNumber}")
    public ResponseEntity<GuestResponseDto> findGuestByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<GuestResponseDto> guest = guestService.findGuestByPhoneNumber(phoneNumber);
        return guest.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /api/guest/orders?phone={phoneNumber} - API tra cứu đơn hàng theo số điện thoại
    @GetMapping("/orders")
    public ResponseEntity<?> getOrdersByPhoneNumber(@RequestParam String phone) {
        try {
            // TODO: Tích hợp với Order module để lấy đơn hàng
            // Hiện tại chỉ trả về thông tin khách hàng
            GuestResponseDto guest = guestService.getGuestByPhoneNumber(phone);
            return ResponseEntity.ok(Map.of(
                "guest", guest,
                "orders", List.of() // Sẽ được thay thế bằng dữ liệu thực từ Order module
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 