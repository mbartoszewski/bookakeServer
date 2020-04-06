/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.repository;

import com.example.bookake.model.Service;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mbart
 */
public interface ServiceRepository extends JpaRepository<Service, Long>
{
     @Query("SELECT DISTINCT s FROM ServiceDetail sd INNER JOIN Service s ON s.serviceId = sd.service.serviceId WHERE sd.category.categoryId = :categoryId ORDER BY name ASC")
     List<Service> findyServiceByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
     @Query("SELECT s FROM Service s WHERE category.categoryId = :parentCategoryId ORDER BY name ASC")
     List<Service> findyServiceByParentCategoryId(@Param("parentCategoryId") Long categoryId, Pageable pageable);
     @Query("SELECT s FROM Service s ORDER BY name ASC")
     List<Service> getAll(Pageable pageable);
     @Query("SELECT s FROM Service s WHERE user.name = :name")
     Service getServiceByServiceUsername(@Param("name") String name);
     @Query("SELECT s FROM Service s WHERE serviceAddress.city LIKE %:city%")
     List<Service> getServiceByServiceCity(@Param("city") String city);
    //SELECT * FROM bookake.service AS s INNER JOIN bookake.service_detail AS sd ON s.service_id = sd.service_id WHERE sd.category_id = 5;
    //SELECT * FROM bookake.service INNER JOIN bookake.user ON bookake.service.service_id = bookake.user.user_id WHERE bookake.user.name = "user";
    //SELECT *FROM   pracownik INNER JOIN dzial ON pracownik.ID_dzialu = dzial.ID_dzialu where dzial.`ID_dzialu`<103 order by nazwisko DESC
}
