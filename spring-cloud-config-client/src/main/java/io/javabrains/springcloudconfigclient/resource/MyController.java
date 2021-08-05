package io.javabrains.springcloudconfigclient.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RefreshScope
public class MyController {

    @Value("${app.description}")
    private String appDesc;

    @RequestMapping("/data")
    public String getValue() {
        return "desc " + appDesc;

    }
}
