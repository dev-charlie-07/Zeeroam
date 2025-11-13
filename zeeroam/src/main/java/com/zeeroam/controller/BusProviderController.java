package com.zeeroam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zeeroam.entity.BusProvider;
import com.zeeroam.service.BusProviderService;

@RestController
@RequestMapping("/api/bus-providers")
public class BusProviderController {

    @Autowired
    private BusProviderService busProviderService;

    // Register a new bus provider
    @PostMapping("/register")
    public ResponseEntity<BusProvider> registerBusProvider(
            @RequestParam String name,
            @RequestParam String contact,
            @RequestParam String email) {

    	BusProvider busProvider = busProviderService.registerBusProvider(name, contact, email);
        return new ResponseEntity<>(busProvider, HttpStatus.CREATED);
    }
}
