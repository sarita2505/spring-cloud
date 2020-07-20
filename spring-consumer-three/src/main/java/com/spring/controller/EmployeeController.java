package com.spring.controller;

import com.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/emp")
    public ResponseEntity<Employee> getData() {
        String url=null;
        List<ServiceInstance> instances = discoveryClient.getInstances("employee-zuul");
        if (instances.size() > 0) {
            ServiceInstance serviceInstance = instances.get(0);
            url = serviceInstance.getUri().toString();
            url = url + "/employeezuul/employees";
            System.out.println("******"+url);
        }
        //String url="http://localhost:9090/employees";
        RestTemplate template = new RestTemplate();
        return template.exchange(url, HttpMethod.GET, null, Employee.class);
    }
}
