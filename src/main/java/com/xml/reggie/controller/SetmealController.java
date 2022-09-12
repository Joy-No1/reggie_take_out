package com.xml.reggie.controller;


import com.xml.reggie.service.SetmealDishService;
import com.xml.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService service;

    @Autowired
    private SetmealDishService setmealDishService;


}
