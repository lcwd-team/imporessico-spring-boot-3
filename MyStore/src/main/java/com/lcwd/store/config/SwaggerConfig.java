package com.lcwd.store.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.lang.Collections;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(getApiInfo());

		docket.securityContexts(Arrays.asList(getSecurityContext()));
		docket.securitySchemes(Arrays.asList(getSchemes()));

		ApiSelectorBuilder select = docket.select();
		select.apis(RequestHandlerSelectors.any());
		select.paths(PathSelectors.any());
		Docket build = select.build();
		return build;
	}

	private ApiKey getSchemes() {		
		return new ApiKey("apiKey","Authorization","header");
	}

	private SecurityContext getSecurityContext() {
		SecurityContext context = SecurityContext.builder().securityReferences(defaultAuth()).build();
		return context;
	}

	private List<SecurityReference> defaultAuth() {
		
		AuthorizationScope scope=new AuthorizationScope("Global", "Access Everything");
		
		AuthorizationScope[] scopes= {scope};		
	
		return Arrays.asList(new SecurityReference("apiKey", scopes));
	}

	private ApiInfo getApiInfo() {
		ApiInfo apiInfo = new ApiInfo("MyStore API Documentation", "This is a documentation of mystore created by LCWD",
				"1.0", "Terms of Services", new Contact("Learn Code With Durgesh",
						"https://www.learncodewithdurgesh.com", "learncodewithdurgesh@gmail.com"),
				"License of API", "API license URL", new ArrayList<>());

		return apiInfo;
	}

}
