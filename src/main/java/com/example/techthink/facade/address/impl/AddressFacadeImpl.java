package com.example.techthink.facade.address.impl;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.address.model.AddressRequest;
import com.example.techthink.controller.address.model.AddressResponse;
import com.example.techthink.converter.address.AddressConverter;
import com.example.techthink.facade.address.AddressFacade;
import com.example.techthink.persistence.entity.address.Address;
import com.example.techthink.service.model.dto.address.AddressDTO;
import com.example.techthink.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class AddressFacadeImpl implements AddressFacade {

    private final AddressService addressService;
    private final AddressConverter converter;

    @Autowired
    public AddressFacadeImpl(AddressService addressService, AddressConverter converter) {
        this.addressService = addressService;
        this.converter = converter;
    }

    @Override
    public AddressResponse create(AddressRequest request){
        AddressDTO addressDTO = convertToDTO(request);
        return converter.fromAddressToResponse(addressService.create(addressDTO));
    }

    @Override
    public AddressResponse readById(Integer id){
        Address address = addressService.readById(id);
        return converter.fromAddressToResponse(address);
    }

    @Override
    public List<AddressResponse> readAll(){
        List<Address> addresses = addressService.readAll();
        return converter.fromAddressToResponseList(addresses);
    }

    @Override
    public AddressResponse update(Integer id, AddressRequest request){
        AddressDTO addressDTO = convertToDTO(request);
        return converter.fromAddressToResponse(addressService.update(id, addressDTO));
    }

    @Override
    public boolean delete(Integer id){
        return addressService.delete(id);
    }

    private AddressDTO convertToDTO(AddressRequest request){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry(request.getCountry());
        addressDTO.setCity(request.getCity());
        addressDTO.setStreet(request.getStreet());
        return addressDTO;
    }

}
