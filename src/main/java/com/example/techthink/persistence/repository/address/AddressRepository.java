package com.example.techthink.persistence.repository.address;

import com.example.techthink.persistence.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {

}
