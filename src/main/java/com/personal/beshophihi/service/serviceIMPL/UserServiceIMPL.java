package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.UserDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.exception.ExistsEntityException;
import com.personal.beshophihi.model.Address;
import com.personal.beshophihi.model.Role;
import com.personal.beshophihi.model.ShoppingCart;
import com.personal.beshophihi.model.User;
import com.personal.beshophihi.repository.AddressRepo;
import com.personal.beshophihi.repository.RoleRepo;
import com.personal.beshophihi.repository.ShoppingCartRepo;
import com.personal.beshophihi.repository.UserRepo;
import com.personal.beshophihi.service.UserService;
import com.personal.beshophihi.utils.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ShoppingCartRepo shoppingCartRepo;
    private final AddressRepo addressRepo;


    @Override
    public User addNewUser(UserDTO userDTO) {
        boolean existsPhone = userRepo.existsByPhoneNumber(userDTO.getPhoneNumber());
        if(existsPhone){
            throw new ExistsEntityException("Phone number already exists !");
        }

        boolean existsEmail = userRepo.existsByEmail(userDTO.getEmail());
        if(existsEmail){
            throw new ExistsEntityException("Email already exists !");
        }

        boolean existsUsername = userRepo.existsByUsername(userDTO.getUsername());
        if(existsUsername){
            throw new ExistsEntityException("Username already exists !");
        }

        Role role = roleRepo.findById(userDTO.getRoleId()).orElseThrow(
                () -> new EntityNotFound("Not found role with id: "+ userDTO.getRoleId())
        );

        List<Address> addressList = new ArrayList<>();

        User user = User.builder()
                .birthDate(userDTO.getBirthDate())
                .email(userDTO.getEmail())
                .gender(userDTO.getGender()==1? Gender.FEMALE : Gender.MALE)
                .isActive(true)
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .phoneNumber(userDTO.getPhoneNumber())
                .username(userDTO.getUsername())
                .role(role)
                .build();
        Address address = Address.builder()
                .nameAddress(userDTO.getAddress())
                .user(user)
                .build();
        addressList.add(address);
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .user(user)
                .totalPrice(BigDecimal.valueOf(0.0))
                .totalQuantity(0)
                .build();

        user.setShoppingCart(shoppingCart);
        user.setAddresses(addressList);
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found user with id: "+ id)
        );
    }
}
