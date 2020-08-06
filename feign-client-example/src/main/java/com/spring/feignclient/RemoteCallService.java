package com.spring.feignclient;

import com.spring.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name="employee-producer")
public interface RemoteCallService {
    @RequestMapping(method = RequestMethod.GET,value = "/employees")
    public Employee getData();
}
