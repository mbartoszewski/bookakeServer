/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author mbart
 */
@Entity
@Table(name = "service_order")
public class ServiceOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_order_id")
    private Long serviceOrderId;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "service_start_date", columnDefinition = "DATETIME")
    private String serviceStartDate;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "service_end_date", columnDefinition = "DATETIME")
    private String serviceEndDate;
    @Column(name = "description")
    private String description = "Brak";
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus = OrderStatusEnum.UNCONFIRMED;
    @JsonManagedReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "service_detail_id")
    private ServiceDetail serviceDetail;
    @JsonManagedReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    /*
    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    */
    public ServiceOrder(){};

    public ServiceOrder(String serviceStartDate, String serviceEndDate, String description, ServiceDetail serviceDetail, User user)
    {
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.description = description;
        this.serviceDetail = serviceDetail;
        this.user = user;
    }

    public Long getServiceOrderId()
    {
        return serviceOrderId;
    }

    public void setServiceOrderId(Long serviceOrderId)
    {
        this.serviceOrderId = serviceOrderId;
    }

    public String getServiceStartDate()
    {
        return serviceStartDate;
    }

    public void setServiceStartDate(String serviceStartDate)
    {
        this.serviceStartDate = serviceStartDate;
    }

    public String getServiceEndDate()
    {
        return serviceEndDate;
    }

    public void setServiceEndDate(String serviceEndDate)
    {
        this.serviceEndDate = serviceEndDate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public OrderStatusEnum getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public ServiceDetail getServiceDetail()
    {
        return serviceDetail;
    }

    public void setServiceDetail(ServiceDetail serviceDetail)
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
}
