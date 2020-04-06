/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.security;

import com.example.bookake.model.User;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author mbart
 */
public class CustomUserDetails extends User implements UserDetails {

    String role;
    public CustomUserDetails(User user, String role)
    {
        super(user);
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        return AuthorityUtils.createAuthorityList(role);    
    }

    @Override
    public String getUsername() {
        return super.getName();
    }
    
    @Override
    public String getPassword() {
        return super.getPassword();
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
       if(super.getEnabled() == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
