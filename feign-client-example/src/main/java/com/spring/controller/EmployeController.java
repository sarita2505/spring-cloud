package com.spring.controller;

import com.spring.feignclient.RemoteCallService;
import com.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeController {
    @Autowired
    RemoteCallService service;
    @GetMapping("/emps")
    public Employee getDetails(){
        Employee employee=service.getData();
        System.out.println(employee.getName());
        return employee;
    }
}
