package com.zeeroam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeeroam.entity.BusProvider;
import com.zeeroam.exception.CustomException;
import com.zeeroam.exception.ErrorCode;
import com.zeeroam.repository.BusProviderRepository;

@Service
public class BusProviderService {

    @Autowired
    private BusProviderRepository busProviderRepository;

    // Register a new bus provider
    public BusProvider registerBusProvider(String name, String contact, String email) {
        // Check if a bus provider with the given email already exists
        if (busProviderRepository.findByEmail(email).isPresent()) {
            throw new CustomException(ErrorCode.BUS_PROVIDER_EXISTS);
        }

        // Create a new BusProvider instance
        BusProvider busProvider = new BusProvider();
        busProvider.setName(name);
        busProvider.setContact(contact);
        busProvider.setEmail(email);

        // Save the bus provider to the database
        return busProviderRepository.save(busProvider);
    }
}
