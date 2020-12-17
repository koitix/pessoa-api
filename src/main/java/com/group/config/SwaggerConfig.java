package com.group.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket pessoaApi(){
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.group")).build().apiInfo(informacaoApi());
	
	}
	
	private ApiInfo informacaoApi() {
	ApiInfo apiInfo = new ApiInfo("Pessoas Api Rest","Cadastro de Pessoas com Contatos","1.0","",new Contact("Silas Kobayashi","https://github.com/koitix","silas.kobayashi@gmail.com"),"","",new ArrayList<>());
	return apiInfo;
	}
}
