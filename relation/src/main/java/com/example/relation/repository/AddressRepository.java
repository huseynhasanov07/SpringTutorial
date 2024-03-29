package com.example.relation.repository;

import com.example.relation.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    Optional<Address> findAddressByStreet(String street);

}
