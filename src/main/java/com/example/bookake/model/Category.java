/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mbart
 */
@Entity
@Table(name = "Category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false, nullable = false)
    private Long categoryId;
    @Column(name = "parent_category_id", nullable = true)
    private Long parentCategoryId;
    @NotNull
    @Column(name = "name")
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Service> service = new LinkedHashSet();
    @JsonBackReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ServiceDetail> serviceDetail = new LinkedHashSet();
   
    public Category(){};
    public Category(Long parentCategoryId, String name)
    {
        this.parentCategoryId = parentCategoryId;
        this.name = name;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getParentCategoryId()
    {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId)
    {
        this.parentCategoryId = parentCategoryId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Service> getService()
    {
        return service;
    }

    public void setService(Set<Service> service)
    {
        this.service = service;
    }

    public Set<ServiceDetail> getServiceDetail()
    {
        return serviceDetail;
    }

    public void setServiceDetail(Set<ServiceDetail> serviceDetail)
    {
        this.serviceDetail = serviceDetail;
    }
/*
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
        sOrder.setCategory(this);
    }
    
    public void removeServiceOrder(ServiceOrder sOrder)
    {
        this.serviceOrder.remove(sOrder);
        sOrder.setCategory(null);
    }
*/
    public void addServiceDetail(ServiceDetail sDetail)
    {
        this.serviceDetail.add(sDetail);
        sDetail.setCategory(this);
    }
    
    public void removeServiceDetail(ServiceDetail sDetail)
    {
        this.serviceDetail.remove(sDetail);
        sDetail.setCategory(null);
    }
    public void addService(Service s)
    {
        this.service.add(s);
        s.setCategory(this);
    }
    
    public void removeService(Service s)
    {
        this.service.remove(s);
        s.setCategory(null);
    }
}
