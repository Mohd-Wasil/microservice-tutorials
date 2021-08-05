package io.javabrains.springbootconfig.services;

import io.javabrains.springbootconfig.aspects.CustomTransaction;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {

    @CustomTransaction
    public String saveEmployee(){
        System.out.println("calling save employee...");
        return "Hello saved Employeed - Tom";
    }

    public void getAllEmployee(){
        System.out.println("calling getAllEmployee list...");
    }
}
