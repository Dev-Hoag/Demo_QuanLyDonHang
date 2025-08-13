package com.Demo_QuanLyBanHang.QuanLyBanHang.price.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.price.dtos.request.PriceRequestDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.dtos.response.PriceResponseDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.entities.Price;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.entities.ServiceEntity;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.repositories.PriceRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.repositories.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public PriceResponseDTO calculatePrice(PriceRequestDTO requestDTO) {

        PriceResponseDTO response = new PriceResponseDTO();
        System.out.println(requestDTO.getRouteCode());

        // find base price based on route code and weight
        BigDecimal basePrice = BigDecimal.valueOf(priceRepository
                .findBasePriceByRouteCodeAndWeight(requestDTO.getRouteCode(), requestDTO.getWeight())
                .orElseThrow(() -> new RuntimeException("No base price found for given route & weight")));

        // find coefficient by service type (normal, express, premium)
        BigDecimal coefficient = serviceRepository
                .findCoefficientByServiceType(requestDTO.getServiceType())
                .orElseThrow(() -> new RuntimeException("No coefficient found for given service type"));

        // calculate final price
        BigDecimal finalPrice = basePrice.multiply(BigDecimal.ONE.add(coefficient));

        // set response fields
        response.setStatus("SUCCESS");
        response.setData(requestDTO);
        response.setBaseprice(basePrice.doubleValue());
        response.setServicefee(basePrice.multiply(coefficient).doubleValue());
        response.setFinalprice(finalPrice.doubleValue());
        return response;
    }

    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    public Price addPrice(Price price) {
        return priceRepository.save(price);
    }
    public ServiceEntity addService(ServiceEntity serviceEntity) {
        return serviceRepository.save(serviceEntity);
    }

    public Price updatePrice(Long id, Price updatedPrice) {
        Optional<Price> existingPriceOpt = priceRepository.findById(id);

        if (existingPriceOpt.isPresent()) {
            Price existingPrice = existingPriceOpt.get();
            existingPrice.setRouteCode(updatedPrice.getRouteCode());
            existingPrice.setWeight(updatedPrice.getWeight());
            existingPrice.setBasePrice(updatedPrice.getBasePrice());
            existingPrice.setRouteName(updatedPrice.getRouteName());
            return priceRepository.save(existingPrice);
        } else {
            throw new RuntimeException("Price with ID " + id + " not found");
        }
    }

    public void deletePrice(Long id) {
        if (priceRepository.existsById(id)) {
            priceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Price with ID " + id + " not found");
        }
    }


}

