package com.example.techthink.facade.address;

import com.example.techthink.controller.address.model.AddressRequest;
import com.example.techthink.controller.address.model.AddressResponse;

import java.util.List;

public interface AddressFacade {

    AddressResponse create(AddressRequest request);

    AddressResponse readById(Integer id);

    List<AddressResponse> readAll();

    AddressResponse update(Integer id, AddressRequest request);

    boolean delete(Integer id);
}
