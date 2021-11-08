package com.example.techthink.service.address.impl;

import com.example.techthink.persistence.entity.address.Address;
import com.example.techthink.persistence.repository.address.AddressRepository;
import com.example.techthink.service.address.AddressService;
import com.example.techthink.service.model.dto.address.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(AddressDTO request) {
        return addressRepository.save(build(request));
    }

    @Override
    public Address readById(Integer id) {
        return addressRepository.getById(id);
    }

    @Override
    public List<Address> readAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address update(Integer id, AddressDTO request) {
        Address address = addressRepository.getById(id);
        return addressRepository.save(build(address, request));
    }

    @Override
    public Boolean delete(Integer id) {
        addressRepository.deleteById(id);
        return !addressRepository.existsById(id);
    }

    private Address build(AddressDTO request) {
        return build(new Address(), request);
    }

    private Address build(Address address, AddressDTO request) {
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());
        return address;
    }
}
