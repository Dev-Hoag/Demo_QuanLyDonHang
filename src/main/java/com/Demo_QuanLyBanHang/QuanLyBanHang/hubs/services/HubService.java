package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.responses.HubResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
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

        String hubName = hubRequest.getHubName();

        if(hubRepository.existsByHubName(hubName)) {
            throw new AppException(ErrorCode.HUBNAME_EXISTED);
        }

        Hub hub = new Hub();
        hubMapper.toHub(hubRequest);
        return hubMapper.toHubResponse(hubRepository.save(hub));
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

}
