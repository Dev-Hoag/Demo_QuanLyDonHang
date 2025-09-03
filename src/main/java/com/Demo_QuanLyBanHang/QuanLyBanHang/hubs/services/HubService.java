package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.responses.HubResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.enums.HubStatus;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.mappers.HubMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.repositories.HubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubService {
    private final HubMapper hubMapper;
    private final HubRepository hubRepository;

//    @PreAuthorize("hasRole('ADMIN')")
    public HubResponse createHub(HubRequest hubRequest) {

        String hubAddress = hubRequest.getHubAddress();

        if(hubRepository.existsByHubAddress(hubAddress)) {
            throw new AppException(ErrorCode.HUBADDRESS_EXISTED);
        }

        Hub hub = hubMapper.toHub(hubRequest);
        hub.setOrderCount(0);
        hub.setHubStatus(HubStatus.ACTIVE);
        Hub result =  hubRepository.save(hub);
        return hubMapper.toHubResponse(hubRepository.save(result));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public List<HubResponse> getAllHubs() {
        return hubRepository.findAll().stream().map(hubMapper::toHubResponse).toList();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public HubResponse getHubById(UUID id) {
        return hubMapper.toHubResponse(hubRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.HUB_NOT_FOUND)));
    }

    public Hub findHubByRegionOrAddress(String address) {
        String city = detectCityFromAddress(address);

        return hubRepository.findTop1ByHubRegionIgnoreCaseOrderByOrderCountAsc(city)
                .orElseThrow(() -> new AppException(ErrorCode.HUB_NOT_FOUND));
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    public HubResponse updateHub(UUID id, HubUpdateRequest hubUpdateRequest) {
        Hub hub = hubRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HUB_NOT_FOUND));
        hubMapper.updateHub(hub, hubUpdateRequest);
        return hubMapper.toHubResponse(hubRepository.save(hub));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHub(UUID id) {
        hubRepository.deleteById(id);
    }

    public HubResponse getHubByName(String name){
        return hubMapper.toHubResponse(hubRepository.findByHubName(name).orElseThrow(
                () -> new AppException(ErrorCode.HUB_NOT_FOUND)));
    }

    public HubResponse getHubByAddress(String address){
        return hubMapper.toHubResponse(hubRepository.findByHubAddress(address).orElseThrow(
                () -> new AppException(ErrorCode.HUB_NOT_FOUND)));
    }

    public List<HubResponse> searchHubs(String name, String address) {
        List<Hub> hubs = hubRepository.findByHubNameContainingIgnoreCaseAndHubAddressContainingIgnoreCase(
                name == null ? "" : name,
                address == null ? "" : address);
        return hubs.stream().map(hubMapper::toHubResponse).toList();
    }

    private String detectCityFromAddress(String address) {
        String lower =  address.toLowerCase();
        if (lower.contains("hồ chí minh") || lower.contains("sài gòn")) {
            return "HỒ CHÍ MINH";
        } else if (lower.contains("hà nội")) {
            return "HÀ NỘI";
        } else {
            throw new AppException(ErrorCode.UNSUPPORTED_ADDRESS_REGION); // định nghĩa thêm nếu cần
        }
    }
}
