package io.javabrains.springbootconfig;

import io.javabrains.springbootconfig.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceTest  extends  SpringBootConfigApplicationTests{

    @Autowired
    private EmployeeService employeeService;
    @Test
    public void testEmployee(){
        employeeService.saveEmployee();
    }
}
