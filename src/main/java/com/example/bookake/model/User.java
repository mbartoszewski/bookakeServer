/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mbart
 */
@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name",unique = true, length = 45, nullable = false)
    private String name;
    @JsonIgnore
    @Column(name = "password", length = 60, nullable = false)
    private String password;
    @Column(name = "enabled")
    @JsonIgnore
    private int enabled = 1;
    @JsonIgnore
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address userAddress;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "contact_id", unique = true)
    private Contact userContact;
    @JsonManagedReference
    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Service service;
    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ServiceOrder> serviceOrder = new LinkedHashSet();
    
    public User(){};

    public User(User user)
    {
        this.name = user.name;
        this.password = user.password;
        this.role = user.role;
        this.userAddress = user.userAddress;
        this.userContact = user.userContact;
        this.service = user.service;
        this.serviceOrder = user.serviceOrder;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getEnabled()
    {
        return enabled;
    }

    public void setEnabled(int enabled)
    {
        this.enabled = enabled;
    }

    public RoleEnum getRole()
    {
        return role;
    }

    public void setRole(RoleEnum role)
    {
        this.role = role;
    }

    public Address getUserAddress()
    {
        return userAddress;
    }

    public void setUserAddress(Address userAddress)
    {
        this.userAddress = userAddress;
    }

    public Contact getUserContact()
    {
        return userContact;
    }

    public void setUserContact(Contact userContact)
    {
        this.userContact = userContact;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public Set<ServiceOrder> getServiceOrder()
    {
        return serviceOrder;
    }

    public void setServiceOrder(Set<ServiceOrder> serviceOrder)
    {
        this.serviceOrder = serviceOrder;
    }

    public void addServiceOrder(ServiceOrder sOrder)
    {
        this.serviceOrder.add(sOrder);
        sOrder.setUser(this);
    }
    
    public void removeServiceOrder(ServiceOrder sOrder)
    {
        this.serviceOrder.remove(sOrder);
        sOrder.setUser(null);
    }

    
}
