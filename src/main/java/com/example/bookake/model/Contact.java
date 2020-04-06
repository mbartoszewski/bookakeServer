/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "contact")
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", updatable = false, nullable = false)
    private Long contactId;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "mobile")
    private String mobile;
    @JsonBackReference
    @OneToOne(optional = true, mappedBy = "userContact", fetch = FetchType.LAZY)
    private User user;
    @JsonBackReference
    @OneToOne(optional = true, mappedBy = "serviceContact", fetch = FetchType.LAZY)
    private Service service;
    
    public Contact(){};
    public Contact(String email, String mobile, User user, Service service)
    {
        this.email = email;
        this.mobile = mobile;
        this.user = user;
        this.service = service;
    }

    public Long getContactId()
    {
        return contactId;
    }

    public void setContactId(Long contactId)
    {
        this.contactId = contactId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
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
    
}
