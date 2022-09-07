package com.xml.reggie.controller;


import com.xml.reggie.common.R;
import com.xml.reggie.entity.Employee;
import com.xml.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        log.info("登陆成功");
        return null;
    }

}
