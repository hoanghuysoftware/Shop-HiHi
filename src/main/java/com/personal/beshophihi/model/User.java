package com.personal.beshophihi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.beshophihi.utils.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String username;
    @JsonIgnore
    private String password;
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    @JsonIgnore
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<StockReceipt> stockReceipts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName().name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
