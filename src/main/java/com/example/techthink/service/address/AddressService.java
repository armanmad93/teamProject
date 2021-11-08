package com.example.techthink.service.address;

import com.example.techthink.persistence.entity.address.Address;
import com.example.techthink.service.model.dto.address.AddressDTO;

import java.util.List;

public interface AddressService {
    Address create(AddressDTO addressDTO);
    Address readById(Integer id);
    List<Address> readAll();
    Address update(Integer id, AddressDTO addressDTO);
    Boolean delete(Integer id);
}
