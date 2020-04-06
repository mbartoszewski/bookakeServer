/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.controller;

import com.example.bookake.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbart
 */
@RestController
public class ContactController
{
    @Autowired
    ContactRepository contactRepository;
}
