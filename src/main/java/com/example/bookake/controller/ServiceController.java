/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.controller;

import com.example.bookake.model.Address;
import com.example.bookake.model.Contact;
import com.example.bookake.model.RoleEnum;
import com.example.bookake.model.Service;
import com.example.bookake.model.User;
import com.example.bookake.repository.AddressRepository;
import com.example.bookake.repository.CategoryRepository;
import com.example.bookake.repository.ContactRepository;
import com.example.bookake.repository.ServiceRepository;
import com.example.bookake.repository.UserRepository;
import java.security.Principal;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbart
 */
@RestController
public class ServiceController
{
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CategoryRepository categoryRepository;
    
    @RequestMapping(value = "/user/service", method = RequestMethod.POST)
    public ResponseEntity addService(Principal p,@RequestParam(name = "category") Long category, Service service, Address address, Contact contact)
    {
        User u = userRepository.findByName(p.getName()).get();
        addressRepository.save(address);
        contactRepository.save(contact);
        service.setServiceAddress(address);
        service.setServiceContact(contact);
        service.setUser(u);
        categoryRepository.findById(category).get().addService(service);
        serviceRepository.save(service);
        u.setRole(RoleEnum.ROLE_PROVIDER);
        userRepository.save(u);
        return new ResponseEntity(Collections.singletonMap("message", "Service added to : " + p.getName()),HttpStatus.OK);
    }
    @RequestMapping(value = "/provider/service", method =RequestMethod.DELETE)
    public ResponseEntity deleteService(Principal p)
    {
        User u = userRepository.findByName(p.getName()).get();
        u.setRole(RoleEnum.ROLE_USER);
        serviceRepository.deleteById(u.getService().getServiceId());
        userRepository.save(u);
        return new ResponseEntity(Collections.singletonMap("message", "Service deleted."), HttpStatus.OK);
    } 
    @RequestMapping(value = "/service", method =RequestMethod.GET)
    public ResponseEntity getServiceByUsername(Principal p)
    {
        serviceRepository.getServiceByServiceUsername(p.getName());
        return new ResponseEntity(serviceRepository.getServiceByServiceUsername(p.getName()), HttpStatus.OK);
    }  
    @RequestMapping(value = "/search", method =RequestMethod.GET)
    public ResponseEntity searchService(
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "parentCategory", required = false) Long parentCategoryId,
            @RequestParam(name = "last", required = true, defaultValue = "0") int last)
    {
        if(categoryId != null)
        {
            return new ResponseEntity(serviceRepository.findyServiceByCategoryId(categoryId,PageRequest.of(last, 10)), HttpStatus.OK);
        }
        if(city != null)
        {
            return new ResponseEntity(serviceRepository.getServiceByServiceCity(city), HttpStatus.OK);
        }
        if(parentCategoryId != null)
        {
            return new ResponseEntity(serviceRepository.findyServiceByParentCategoryId(parentCategoryId,PageRequest.of(last, 10)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(serviceRepository.getAll(PageRequest.of(last, 10)), HttpStatus.FORBIDDEN);
        }
    }
}
