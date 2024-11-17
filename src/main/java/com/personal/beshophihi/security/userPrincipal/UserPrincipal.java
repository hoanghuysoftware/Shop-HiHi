//package com.personal.beshophihi.security.userPrincipal;
//
//import com.personal.beshophihi.model.User;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//@AllArgsConstructor
//public class UserPrincipal extends User implements UserDetails {
//    private final User user;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(
//                new SimpleGrantedAuthority(
//                        user.getRole().getRoleName().name()));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
