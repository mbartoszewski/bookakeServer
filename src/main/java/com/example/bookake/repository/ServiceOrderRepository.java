/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.repository;

import com.example.bookake.model.ServiceOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mbart
 */
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long>
{
	@Query("SELECT so FROM ServiceOrder so WHERE serviceDetail.serviceDetailId = :serviceDetail AND orderStatus = CONFIRMED OR orderStatus = UNCONFIRMED OR orderStatus = PENDING OR orderStatus = CANCELED ORDER BY serviceStartDate ASC")
	List<ServiceOrder> getOrdersByServiceDetailId(@Param("serviceDetail") Long serviceDetail);
    @Query("SELECT so FROM ServiceOrder so WHERE serviceDetail.service.serviceId = :service AND year(so.serviceStartDate) = :year AND month(so.serviceStartDate) = :month AND day(so.serviceStartDate) = :day AND orderStatus <> 'END' AND orderStatus <> 'CANCELED'")
    List<ServiceOrder> getServiceOrderByServiceDate(@Param("service") Long service, @Param("year") int year, @Param("month") int month, @Param("day") int day);
    @Query("SELECT so FROM ServiceOrder so WHERE user.name = :name AND year(so.serviceStartDate) = :year AND month(so.serviceStartDate) = :month")
    List<ServiceOrder> getServiceOrderByServiceDateAndUsername(@Param("name") String name, @Param("year") int year, @Param("month") int month);
    @Query("SELECT so FROM ServiceOrder so WHERE serviceDetail.service.user.name = :providername AND year(so.serviceStartDate) = :year AND month(so.serviceStartDate) = :month")
    List<ServiceOrder> getServiceOrderByServiceDateAndProvidername(@Param("providername") String providername, @Param("year") int year, @Param("month") int month);
    @Query("SELECT so FROM ServiceOrder so WHERE user.name = :name AND year(so.serviceStartDate) = :year AND month(so.serviceStartDate) = :month AND day(so.serviceStartDate) = :day")
    List<ServiceOrder> getServiceOrderByServiceFullDateAndUsername(@Param("name") String name, @Param("year") int year, @Param("month") int month, @Param("day") int day);
    @Query("SELECT so FROM ServiceOrder so WHERE serviceDetail.service.user.name = :providername AND year(so.serviceStartDate) = :year AND month(so.serviceStartDate) = :month AND day(so.serviceStartDate) = :day")
    List<ServiceOrder> getServiceOrderByServiceFullDateAndProvidername(@Param("providername") String providername, @Param("year") int year, @Param("month") int month, @Param("day") int day);
    //@Query("SELECT so FROM ServiceOrder so WHERE serviceDetail.service.user.name = :name AND orderStatus <> 'END' ORDER BY serviceStartDate ASC")
    //List<ServiceOrder> getServiceOrderByServiceProviderName(@Param("name") String name);
    //@Query("SELECT so FROM ServiceOrder so WHERE user.name = :name AND orderStatus <> 'END' ORDER BY serviceStartDate ASC")
    //List<ServiceOrder> getServiceOrderByServiceUserName(@Param("name") String name);
    //@Query("SELECT so FROM ServiceOrder so WHERE serviceDetail.service.serviceId = :service AND orderStatus <> 'END' AND orderStatus <> 'END' ORDER BY serviceStartDate ASC")
    //List<ServiceOrder> getServiceOrderByService(@Param("service") Long service);
}
