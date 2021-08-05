package io.javabrains.springbootconfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class SpringBootConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfigApplication.class, args);
	}

	@Bean
	public Docket enableSwaggerConfiguration(){
		//crate docket instance and set config ondocket 0 builder method
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant( "/topics/*"))
				.apis(RequestHandlerSelectors.basePackage("io.javabrains"))
				//.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				//.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.build()
				/*.globalOperationParameters(
						Collections.singletonList(
								new ParameterBuilder()
										.name("Authorization")
										.description("Bearer token")
										.modelRef(new ModelRef("string"))
										.parameterType("header")
										.required(true)
										.build()));*/
				.apiInfo(getApiInfo());

	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Courses API",
				"Sample course API for documentation",
				"1.0",
				"Free Free free",
				new Contact("Wasil", "http://nowhere", "helow@gmail.com"),
				"Api license",
				"https://jpm.com",
				Collections.emptyList()
		);
	}
}
