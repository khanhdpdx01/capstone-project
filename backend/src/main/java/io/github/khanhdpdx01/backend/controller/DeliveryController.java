package io.github.khanhdpdx01.backend.controller;

import io.github.khanhdpdx01.backend.dto.delivery.DeliveryDto;
import io.github.khanhdpdx01.backend.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_REPRESENTATIVE')")
    public ResponseEntity<?> deliver(@RequestBody DeliveryDto deliveryDto) {
        deliveryService.deliver(deliveryDto);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
