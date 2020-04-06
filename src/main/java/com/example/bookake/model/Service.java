/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mbart
 */
@Entity
@Table(name = "service")
public class Service
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id",updatable = false, nullable = false)
    private Long serviceId;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "description")
    @Size(max = 600)
    private String description;
    @JsonManagedReference
    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true)
    private Address serviceAddress;
    @JsonManagedReference
    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "contact_id", unique = true)
    private Contact serviceContact;
    @JsonBackReference
    @OneToMany(mappedBy = "service", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ServiceDetail> serviceDetail = new ArrayList<>();
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
    @JsonManagedReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    public Service(){};

    public Service(String name, String description, Address serviceAddress, Contact serviceContact, User user, Category category)
    {
        this.name = name;
        this.description = description;
        this.serviceAddress = serviceAddress;
        this.serviceContact = serviceContact;
        this.user = user;
        this.category = category;
    }

    public Long getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(Long serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Address getServiceAddress()
    {
        return serviceAddress;
    }

    public void setServiceAddress(Address serviceAddress)
    {
        this.serviceAddress = serviceAddress;
    }

    public Contact getServiceContact()
    {
        return serviceContact;
    }

    public void setServiceContact(Contact serviceContact)
    {
        this.serviceContact = serviceContact;
    }

    public List<ServiceDetail> getServiceDetail()
    {
        return serviceDetail;
    }
    public void setServiceDetail(List<ServiceDetail> serviceDetail)
    {
        this.serviceDetail = serviceDetail;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }
   
    public void addServiceDetail(ServiceDetail sD)
    {
        this.serviceDetail.add(sD);
        sD.setService(this);
    }
    public void removeServiceDetail(ServiceDetail sD)
    {
        this.serviceDetail.remove(sD);
        sD.setService(null);
    }
}
