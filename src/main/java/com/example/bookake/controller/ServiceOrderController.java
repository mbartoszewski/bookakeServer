/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.controller;

import com.example.bookake.model.OrderStatusEnum;
import com.example.bookake.model.ServiceDetail;
import com.example.bookake.model.ServiceOrder;
import com.example.bookake.repository.CategoryRepository;
import com.example.bookake.repository.ServiceDetailRepository;
import com.example.bookake.repository.ServiceOrderRepository;
import com.example.bookake.repository.UserRepository;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
public class ServiceOrderController
{
    @Autowired
    ServiceOrderRepository serviceOrderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceDetailRepository serviceDetailRepository;
    @Autowired
    CategoryRepository categoryRepository;
    
    @RequestMapping(value = "/user/service/order", method = RequestMethod.POST)
    public ResponseEntity addUserServiceOrder(Principal p,@RequestParam("service") Long service, ServiceOrder serviceOrder)
    {
        serviceOrder.setUser(userRepository.findByName(p.getName()).get());
        ServiceDetail serviceD = serviceDetailRepository.findById(service).get();
        serviceOrder.setServiceStartDate(serviceOrder.getServiceStartDate());
        serviceOrder.setServiceEndDate(serviceOrder.getServiceEndDate());
        serviceD.addServiceOrder(serviceOrder);
        return new ResponseEntity(serviceOrderRepository.save(serviceOrder), HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/user/service/order/{serviceOrderId}", method = RequestMethod.PUT)
    public ResponseEntity updateServiceOrder(Principal p, @PathVariable(value = "serviceOrderId") Long serviceOrderId, ServiceOrder serviceOrder)
    {
        ServiceOrder so = serviceOrderRepository.findById(serviceOrderId).get();
        if(so.getUser().getName().equals(p.getName()))
        {
            so.setDescription(serviceOrder.getDescription());
            so.setOrderStatus(serviceOrder.getOrderStatus());
            so.setServiceEndDate(serviceOrder.getServiceEndDate());
            so.setServiceStartDate(serviceOrder.getServiceStartDate());
            return new ResponseEntity(Collections.singletonMap("message","Service order updated."), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity(Collections.singletonMap("message", "Error. Service order update failed."), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/user/service/order/{serviceOrderId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteServiceOrder(Principal p, @PathVariable(value = "serviceOrderId") Long serviceOrderId)
    {
        if(serviceOrderRepository.findById(serviceOrderId).get().getUser().getName().equals(p.getName()))
        {
        serviceOrderRepository.deleteById(serviceOrderId);
        return new ResponseEntity(Collections.singletonMap("message", "Service order deleted."), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(Collections.singletonMap("message", "Error. Service order delete failed."), HttpStatus.BAD_REQUEST);
        }    
    }
    
    @RequestMapping(value = "/user/order", method = RequestMethod.PUT)
    public ResponseEntity changeOrderStatus(@RequestParam(name = "serviceOrder") Long serviceOrder, @RequestParam(name="status", required = true) String status)
    {
       ServiceOrder sd = serviceOrderRepository.getOne(serviceOrder);
       switch(status)
       {
           case "canceled":
               sd.setOrderStatus(OrderStatusEnum.CANCELED);     
           break;
           case "confirmed":
               sd.setOrderStatus(OrderStatusEnum.CONFIRMED);     
           break;
           case "unconfirmed":
               sd.setOrderStatus(OrderStatusEnum.UNCONFIRMED);     
           break;
           case "pending":
               sd.setOrderStatus(OrderStatusEnum.PENDING);     
           break;
           case "end":
               sd.setOrderStatus(OrderStatusEnum.END);     
           break;  
       }
     
       serviceOrderRepository.save(sd);
       return new ResponseEntity(serviceOrderRepository.save(sd), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/provider/order/detail/{serviceDetail}", method = RequestMethod.GET)
    public ResponseEntity getServiceOrderByServiceDetailId(@PathVariable(value = "serviceDetail") Long serviceDetail)
    {
       return new ResponseEntity(serviceOrderRepository.getOrdersByServiceDetailId(serviceDetail), HttpStatus.OK);
    }
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity getServiceOrderByServiceDate(Long service, int day, int month, int year)
    {
       return new ResponseEntity(serviceOrderRepository.getServiceOrderByServiceDate(service, year, month, day), HttpStatus.OK);     
    }
    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public ResponseEntity getServiceOrderByServiceDateAndUsername(Principal p, @RequestParam(name="day", required = false) Integer day, int month, int year)
    {
        if(day != null)
        {
            return new ResponseEntity(serviceOrderRepository.getServiceOrderByServiceFullDateAndUsername(p.getName(),  year, month, day), HttpStatus.OK); 
        }
        else
        {
          return new ResponseEntity(serviceOrderRepository.getServiceOrderByServiceDateAndUsername(p.getName(),  year, month), HttpStatus.OK);   
        }
           
    }
    @RequestMapping(value = "provider/order", method = RequestMethod.GET)
    public ResponseEntity getServiceOrderByServiceDateAndProvidername(Principal p, @RequestParam(name="day", required = false) Integer day, int month, int year)
    {
        if(day != null)
        {
            return new ResponseEntity(serviceOrderRepository.getServiceOrderByServiceFullDateAndProvidername(p.getName(), year, month, day), HttpStatus.OK);     
        }
        else
        {
            return new ResponseEntity(serviceOrderRepository.getServiceOrderByServiceDateAndProvidername(p.getName(), year, month), HttpStatus.OK);     
        }
    }
    private LocalDateTime convertStringToLocalDateTime(String date)
    {
        DateTimeFormatter stringToLocalDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        return LocalDateTime.parse(date, stringToLocalDateTimeFormat);
    }
}
