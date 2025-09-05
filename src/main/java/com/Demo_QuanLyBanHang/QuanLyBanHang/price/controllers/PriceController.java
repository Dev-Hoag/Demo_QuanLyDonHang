package com.Demo_QuanLyBanHang.QuanLyBanHang.price.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.price.dtos.request.PriceRequestDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.dtos.response.PriceResponseDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.entities.Price;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.entities.ServiceEntity;
import com.Demo_QuanLyBanHang.QuanLyBanHang.price.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
@CrossOrigin(origins = "*")
public class PriceController {
    @Autowired
    private PriceService priceService;

    // POST request to calculate price
    @PostMapping("/calculate")
    public PriceResponseDTO calculatePrice(@RequestBody PriceRequestDTO requestDTO) {
        return priceService.calculatePrice(requestDTO);
    }

    @GetMapping("/prices")
    public List<Price> getPrices() {
        return priceService.getAllPrices();
    }

    @GetMapping("/services")
    public List<ServiceEntity> getServices() {
        return priceService.getAllServices();
    }
    @PostMapping("/prices")
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        Price savedPrice = priceService.addPrice(price);
        return ResponseEntity.ok(savedPrice);
    }

    @PostMapping("/services")
    public ResponseEntity<ServiceEntity> createService(@RequestBody ServiceEntity serviceEntity) {
        ServiceEntity savedService = priceService.addService(serviceEntity);
        return ResponseEntity.ok(savedService);
    }
    // Update
    @PutMapping("/prices/{id}")
    public Price updatePrice(@PathVariable Long id, @RequestBody Price price) {
        return priceService.updatePrice(id, price);
    }

    // Delete
    @DeleteMapping("/prices/{id}")
    public String deletePrice(@PathVariable Long id) {
        priceService.deletePrice(id);
        return "Deleted Price with ID " + id;
    }
}
