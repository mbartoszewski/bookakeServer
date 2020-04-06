/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mbart
 */
@Entity
@Table(name = "address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id",updatable = false, nullable = false)
    private Long addressId;    
    @NotNull
    @Column(name = "city")
    private String city;
    @NotNull
    @Column(name = "address_state")
    private String addressState;
    @NotNull
    @Column(name = "street")
    private String street;
    @NotNull
    @Column(name = "zip")
    private String zip;
    @JsonBackReference
    @OneToOne(optional = true, mappedBy = "userAddress")
    private User user;
    @JsonBackReference
    @OneToOne(optional = true, mappedBy = "serviceAddress")
    private Service service;
    
    public Address(){};
    public Address(String city, String addressState, String street, String zip) 
    {
        this.city = city;
        this.addressState = addressState;
        this.street = street;
        this.zip = zip;
    }

    public Long getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Long addressId)
    {
        this.addressId = addressId;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getAddressState()
    {
        return addressState;
    }

    public void setAddressState(String addressState)
    {
        this.addressState = addressState;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public boolean isNull()
    {
        return street == null && city == null && addressState == null && zip == null;
    }
}
