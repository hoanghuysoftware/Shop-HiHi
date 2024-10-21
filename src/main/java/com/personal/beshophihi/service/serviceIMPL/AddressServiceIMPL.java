package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.AddressDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.exception.ExistsEntityException;
import com.personal.beshophihi.model.Address;
import com.personal.beshophihi.model.User;
import com.personal.beshophihi.repository.AddressRepo;
import com.personal.beshophihi.service.AddressService;
import com.personal.beshophihi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceIMPL implements AddressService {
    private final AddressRepo addressRepo;
    private final UserService userService;

    @Override
    public List<Address> getAllAddress() {
        return addressRepo.findAll();
    }

    @Override
    public List<Address> getAddressByUserId(Long idUser) {
        return addressRepo.getAddressesByUserId(idUser);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found address with id: "+ id)
        );
    }

    @Override
    public Address createAddress(Long idUser,AddressDTO addressDTO) {
        User user = userService.getUserById(idUser);
        boolean isExists = addressRepo.existsByNameAddress(addressDTO.getNameAddress());
        if(isExists){
            throw new ExistsEntityException("Name address already exists !");
        }
        Address address = Address.builder()
                .nameAddress(addressDTO.getNameAddress())
                .user(user)
                .build();
        return addressRepo.save(address);
    }

    @Override
    public Address updateAddress(Long id, AddressDTO addressDTO) {
        Address address = addressRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found address with id: "+ id)
        );
        boolean isExists = addressRepo.existsByNameAddress(addressDTO.getNameAddress());
        if(isExists){
            throw new ExistsEntityException("Name address already exists !");
        }

        address.setNameAddress(addressDTO.getNameAddress());
        return addressRepo.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found address with id: "+ id)
        );
        addressRepo.delete(address);
    }
}
