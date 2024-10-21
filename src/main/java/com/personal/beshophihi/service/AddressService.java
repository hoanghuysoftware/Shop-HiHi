package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.AddressDTO;
import com.personal.beshophihi.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    List<Address> getAddressByUserId(Long idUser);
    Address getAddressById(Long id);
    Address createAddress(Long idUser,AddressDTO addressDTO);
    Address updateAddress(Long id,AddressDTO addressDTO);
    void deleteAddress(Long id);
}
