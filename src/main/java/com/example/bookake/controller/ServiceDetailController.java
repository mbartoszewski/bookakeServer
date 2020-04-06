/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.controller;

import com.example.bookake.model.ServiceDetail;
import com.example.bookake.repository.CategoryRepository;
import com.example.bookake.repository.ServiceDetailRepository;
import com.example.bookake.repository.ServiceRepository;
import com.example.bookake.repository.UserRepository;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbart
 */
@RestController
public class ServiceDetailController
{
    @Autowired
    ServiceDetailRepository serviceDetailRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ServiceRepository serviceRepository;
    
    @RequestMapping(value = "/provider/service/details", method = RequestMethod.POST)
    ResponseEntity addDetailsToService(Principal p,@RequestParam(name = "category") Long category, ServiceDetail serviceDetail)
    {
        serviceDetail.setService(userRepository.findByName(p.getName()).get().getService());
        categoryRepository.findById(category).get().addServiceDetail(serviceDetail);
        serviceDetailRepository.save(serviceDetail);
        return new ResponseEntity(Collections.singletonMap("message", "Added"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/provider/service/details", method = RequestMethod.PUT)
    ResponseEntity editDetails(Principal p,@RequestParam(name = "category") Long category, ServiceDetail serviceDetail)
    {
        //serviceDetail.setService(userRepository.findByName(p.getName()).get().getService());
        //serviceDetailRepository.findById(serviceDetail);

        categoryRepository.findById(category).get().addServiceDetail(serviceDetail);
        serviceDetailRepository.save(serviceDetail);
        return new ResponseEntity(Collections.singletonMap("message", "Added"), HttpStatus.OK);
    }
    @RequestMapping(value = "/provider/service/details", method = RequestMethod.DELETE)
    ResponseEntity deleteServiceDetail(Principal p,@RequestParam(name = "serviceDetail") Long serviceDetail)
    {
       List<ServiceDetail> serviceDetailList = serviceDetailRepository.getAllDetailsByUsername(p.getName());
       for(ServiceDetail sD : serviceDetailList)
       {
           if(Objects.equals(sD.getServiceDetailId(), serviceDetail))
           {  
                serviceDetailRepository.deleteById(serviceDetail);
                return new ResponseEntity(Collections.singletonMap("message", "Deleted"), HttpStatus.OK); 
           }
       }    
        return new ResponseEntity(Collections.singletonMap("message", "Error, not deleted."), HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/{service}", method = RequestMethod.GET)
    ResponseEntity getServiceDetail(@PathVariable(value = "service") Long service)
    {
        return new ResponseEntity(serviceDetailRepository.getAllDetailsById(service), HttpStatus.OK);
    }
}
