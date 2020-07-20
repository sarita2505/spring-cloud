package com.spring.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private LoadBalancerClient client;

    @GetMapping("/emp")
    @HystrixCommand(fallbackMethod = "getDataFallBack")
    public Employee getData() {
        String url=null;
        ServiceInstance serviceInstance = client.choose("employee-producer");
        System.out.println(serviceInstance+"+++++++++");
            url = serviceInstance.getUri().toString();
            url = url + "/employees";
        System.out.println("***url*****"+url);
        //String url="http://localhost:9090/employees";
        RestTemplate template = new RestTemplate();
        return template.getForObject(url, Employee.class);
    }
    public Employee getDataFallBack() {

        Employee emp = new Employee();
        emp.setName("fallback-emp1");
        emp.setDesignation("fallback-manager");
        emp.setEmpId("fallback-1");
        emp.setSalary(3000);

        return emp;

    }

}
