package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos.GuestCreateDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos.GuestResponseDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos.GuestSearchDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.entities.Guest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.repositories.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestService {
    
    private final GuestRepository guestRepository;
    
    // Tạo mới khách hàng
    public GuestResponseDto createGuest(GuestCreateDto createDto) {
        // Kiểm tra số điện thoại đã tồn tại chưa
        if (guestRepository.existsByPhoneNumber(createDto.getPhoneNumber())) {
            throw new RuntimeException("Số điện thoại đã tồn tại trong hệ thống");
        }
        
        // Kiểm tra email nếu có
        if (createDto.getEmail() != null && !createDto.getEmail().trim().isEmpty()) {
            Optional<Guest> existingGuest = guestRepository.findByEmail(createDto.getEmail());
            if (existingGuest.isPresent()) {
                throw new RuntimeException("Email đã tồn tại trong hệ thống");
            }
        }
        
        // Tạo entity mới
        Guest guest = new Guest();
        guest.setFullName(createDto.getFullName().trim());
        guest.setPhoneNumber(createDto.getPhoneNumber().trim());
        guest.setEmail(createDto.getEmail() != null ? createDto.getEmail().trim() : null);
        guest.setAddress(createDto.getAddress().trim());
        guest.setGuestType(createDto.getGuestType());
        guest.setIsActive(true);
        
        Guest savedGuest = guestRepository.save(guest);
        return GuestResponseDto.fromEntity(savedGuest);
    }
    
    // Lấy thông tin khách hàng theo ID
    @Transactional(readOnly = true)
    public GuestResponseDto getGuestById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + id));
        return GuestResponseDto.fromEntity(guest);
    }
    
    // Lấy thông tin khách hàng theo số điện thoại
    @Transactional(readOnly = true)
    public GuestResponseDto getGuestByPhoneNumber(String phoneNumber) {
        Guest guest = guestRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với số điện thoại: " + phoneNumber));
        return GuestResponseDto.fromEntity(guest);
    }
    
    // Cập nhật thông tin khách hàng
    public GuestResponseDto updateGuest(Long id, GuestCreateDto updateDto) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + id));
        
        // Kiểm tra số điện thoại mới có trùng với khách hàng khác không
        if (!guest.getPhoneNumber().equals(updateDto.getPhoneNumber()) &&
            guestRepository.existsByPhoneNumber(updateDto.getPhoneNumber())) {
            throw new RuntimeException("Số điện thoại đã tồn tại trong hệ thống");
        }
        
        // Cập nhật thông tin
        guest.setFullName(updateDto.getFullName().trim());
        guest.setPhoneNumber(updateDto.getPhoneNumber().trim());
        guest.setEmail(updateDto.getEmail() != null ? updateDto.getEmail().trim() : null);
        guest.setAddress(updateDto.getAddress().trim());
        guest.setGuestType(updateDto.getGuestType());
        
        Guest updatedGuest = guestRepository.save(guest);
        return GuestResponseDto.fromEntity(updatedGuest);
    }
    
    // Xóa khách hàng (soft delete)
    public void deleteGuest(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với ID: " + id));
        guest.setIsActive(false);
        guestRepository.save(guest);
    }
    
    // Tìm kiếm khách hàng
    @Transactional(readOnly = true)
    public List<GuestResponseDto> searchGuests(GuestSearchDto searchDto) {
        List<Guest> guests = guestRepository.searchGuests(
            searchDto.getFullName(),
            searchDto.getPhoneNumber(),
            searchDto.getEmail(),
            searchDto.getAddress()
        );
        
        return guests.stream()
                .map(GuestResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    // Lấy tất cả khách hàng đang active
    @Transactional(readOnly = true)
    public List<GuestResponseDto> getAllActiveGuests() {
        List<Guest> guests = guestRepository.findByIsActiveTrue();
        return guests.stream()
                .map(GuestResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    // Lấy khách hàng theo loại (SENDER/RECEIVER)
    @Transactional(readOnly = true)
    public List<GuestResponseDto> getGuestsByType(Guest.GuestType guestType) {
        List<Guest> guests = guestRepository.findByGuestType(guestType);
        return guests.stream()
                .map(GuestResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    
    // Kiểm tra khách hàng có tồn tại không
    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(String phoneNumber) {
        return guestRepository.existsByPhoneNumber(phoneNumber);
    }
    
    // Lấy khách hàng theo số điện thoại (không throw exception)
    @Transactional(readOnly = true)
    public Optional<GuestResponseDto> findGuestByPhoneNumber(String phoneNumber) {
        return guestRepository.findByPhoneNumber(phoneNumber)
                .map(GuestResponseDto::fromEntity);
    }
} 