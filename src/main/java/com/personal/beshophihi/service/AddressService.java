package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.AddressDTO;
import com.personal.beshophihi.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    Address getAddressById(Long id);
    Address createAddress(AddressDTO addressDTO);
    Address updateAddress(Long id,AddressDTO addressDTO);
    void deleteAddress(Long id);
}
