/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.controller;

import com.example.bookake.model.RoleEnum;
import com.example.bookake.model.User;
import com.example.bookake.repository.AddressRepository;
import com.example.bookake.repository.ServiceRepository;
import com.example.bookake.repository.UserRepository;
import java.security.Principal;
import java.util.Collections;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbart
 */
@RestController
public class UserController
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ServiceRepository serviceRepository;
    Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    
    @RequestMapping(value = "user/login", method = RequestMethod.GET)
    public ResponseEntity loginUser(Principal p, Authentication auth)
    {
        if(p.getName() != null)
        {    
           return auth.getAuthorities().toString().equals("[ROLE_PROVIDER]")?new ResponseEntity(
                   Collections.singletonMap("message", auth.getAuthorities().toString() + " service:" + serviceRepository.getServiceByServiceUsername(p.getName()).getServiceId().toString()), HttpStatus.OK):new ResponseEntity(Collections.singletonMap("message", auth.getAuthorities().toString()), HttpStatus.OK);
        }
        else
            return new ResponseEntity(Collections.singletonMap("message", "Bad credentials"), HttpStatus.UNAUTHORIZED);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(User user)
    {
        if(userRepository.findByName(user.getName()).isPresent())
        {
           return new ResponseEntity(Collections.singletonMap("message", "Error. User with " + user.getName() + " already exist. Please choose another username."), HttpStatus.BAD_REQUEST);
        }
        else
        {
           user.setRole(RoleEnum.ROLE_USER);
           user.setPassword(String.valueOf(BcryptEncoder(user.getPassword())));
           userRepository.save(user);
           return new ResponseEntity(Collections.singletonMap("message", "User " + user.getName() + " created"), HttpStatus.CREATED);
        }    
    }
    
    public CharSequence BcryptEncoder(CharSequence rawPassword)
    {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
