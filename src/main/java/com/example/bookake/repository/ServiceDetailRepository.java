/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.repository;

import com.example.bookake.model.ServiceDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mbart
 */
public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Long>
{
    @Query("SELECT sd FROM ServiceDetail sd WHERE service.serviceId = :service ORDER BY title ASC")
    List<ServiceDetail> getAllDetailsById(@Param("service") Long service);
    @Query("SELECT sd FROM ServiceDetail sd WHERE sd.service.user.name = :name")
    List<ServiceDetail> getAllDetailsByUsername(@Param("name") String name);
}
