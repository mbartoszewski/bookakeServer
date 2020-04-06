/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.repository;

import com.example.bookake.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mbart
 */
public interface ContactRepository extends JpaRepository<Contact, Long>
{
    
}
