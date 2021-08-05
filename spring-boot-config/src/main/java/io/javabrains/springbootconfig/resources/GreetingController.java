package io.javabrains.springbootconfig.resources;

import io.javabrains.springbootconfig.DBSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @Value("${my.greeting: defaultValue}")
    private String greetingMessage;

    @Value("${app.description}")
    private String appDesc;

    @Value("some value static")
    private String someValue;

    @Value("${my.novalue: default Value for NOVALUE}")
    private String defaultValue;

    @Value("${my.values}")
    private List<String> listValue;

    @Value("#{${db.connection}}")
    private Map<String, String> dbValues;

    @Autowired
    private DBSetting dbSetting;

    @Autowired
    private Environment environment;


    @RequestMapping("/greet/{name}")
    public String getGreeting(@PathVariable("name") String name){

        String response =  "Hello  : "+ name +
                greetingMessage +
                "\n DESC: " + appDesc +
                "\ndefault :" + defaultValue +
                "\nlist valuess: "+ listValue +
                "\n dbValues : "+ dbValues;
        return response;
    }

    @RequestMapping("/db")
    public String getDbSetting(){

        return "db setting connection : " + dbSetting.getConnection() + " host: " + dbSetting.getHost();
    }

    @RequestMapping("/envdetails")
    public String getEnvDetails(){

        return "getEnvDetails : " + environment.getActiveProfiles() + environment;
    }

}
