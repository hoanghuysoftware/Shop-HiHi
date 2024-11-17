package com.personal.beshophihi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.beshophihi.model.Address;
import com.personal.beshophihi.model.Role;
import com.personal.beshophihi.model.ShoppingCart;
import com.personal.beshophihi.model.StockReceipt;
import com.personal.beshophihi.utils.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String email;
    @JsonProperty("phone")
    private String phoneNumber;
    private int gender;
    private String username;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birth-date")
    private Date birthDate;
    private String address;
    @JsonProperty("role-id")
    private Long roleId;
}
