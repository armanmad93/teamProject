package com.example.techthink.converter.address;

import com.example.techthink.controller.address.model.AddressResponse;
import com.example.techthink.persistence.entity.address.Address;

import java.util.List;

public interface AddressConverter {

    AddressResponse fromAddressToResponse(Address address);

    List<AddressResponse> fromAddressToResponseList(List<Address> addresses);
}
