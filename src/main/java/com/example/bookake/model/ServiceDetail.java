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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mbart
 */
@Entity
@Table(name = "service_detail")
public class ServiceDetail
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "service_detail_id")
    private Long serviceDetailId;
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "price")
    private double price;
    @NotNull
    @Column(name = "execution_time_min")
    private int executionTimeMin;
    @JsonManagedReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonBackReference
    @OneToMany(mappedBy = "serviceDetail")
    private List<ServiceOrder> serviceOrder = new ArrayList<>();
    @JsonManagedReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    
    
    public ServiceDetail(){};

    public ServiceDetail(String title, String description, double price, int executionTimeMin, Category category, Service service)
    {
        this.title = title;
        this.description = description;
        this.price = price;
        this.executionTimeMin = executionTimeMin;
        this.category = category;
        this.service = service;
    }

    public Long getServiceDetailId()
    {
        return serviceDetailId;
    }

    public void setServiceDetailId(Long serviceDetailId)
    {
        this.serviceDetailId = serviceDetailId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getExecutionTimeMin()
    {
        return executionTimeMin;
    }

    public void setExecutionTimeMin(int executionTimeMin)
    {
        this.executionTimeMin = executionTimeMin;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public List<ServiceOrder> getServiceOrder()
    {
        return serviceOrder;
    }

    public void setServiceOrder(List<ServiceOrder> serviceOrder)
    {
        this.serviceOrder = serviceOrder;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public void addServiceOrder(ServiceOrder sOrder)
    {
        this.serviceOrder.add(sOrder);
        sOrder.setServiceDetail(this);
    }
    
    public void removeServiceOrder(ServiceOrder sOrder)
    {
        this.serviceOrder.remove(sOrder);
        sOrder.setServiceDetail(null);
    }
    
}
