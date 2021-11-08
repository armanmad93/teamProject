package com.example.techthink.converter.address.impl;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.address.model.AddressResponse;
import com.example.techthink.converter.address.AddressConverter;
import com.example.techthink.persistence.entity.address.Address;

import java.util.List;
import java.util.stream.Collectors;

@Convert
public class AddressConverterImpl implements AddressConverter {

    @Override
    public AddressResponse fromAddressToResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        return addressResponse;
    }

    @Override
    public List<AddressResponse> fromAddressToResponseList(List<Address> addresses) {
        return addresses.stream()
                .map(each -> fromAddressToResponse(each))
                .collect(Collectors.toList());
    }
}
