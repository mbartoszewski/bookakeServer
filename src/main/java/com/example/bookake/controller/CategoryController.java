/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bookake.controller;

import com.example.bookake.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mbart
 */
@RestController
public class CategoryController
{
    @Autowired
    CategoryRepository categoryRepository;
    
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity getAllCategories()
    {
        return new ResponseEntity(categoryRepository.findAll(), HttpStatus.OK);
    }
}
