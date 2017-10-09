package com.rest.udemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger
@ComponentScan({"com.rest.udemy.controller", "com.rest.udemy.service", "com.rest.udemy.config"})
public class RestApplication {

	@Autowired
	private SpringSwaggerConfig swaggerConfig;

	public static void main(String[] args) {
		//SpringApplication.run(RestApplication.class, args);
		new SpringApplicationBuilder(RestApplication.class).web(true).run(args);
	}

	@Bean
	public SwaggerSpringMvcPlugin groupOnePlugin() {
		return new SwaggerSpringMvcPlugin(swaggerConfig)
				//Adiciona as configurações do Swagger ao SwaggerSpringMvcPlugin 
				.apiInfo(apiInfo()) //Adiciona as propriedades de configuração
				.includePatterns("/perfil.*?", "/usuario.*?") //Habilita o Swagger para os nossos 2 endpoints
				.swaggerGroup("admin");
	}
	
	private ApiInfo apiInfo() {
	       ApiInfo apiInfo = new ApiInfo( //Configurações de contato, licença etc não nescessáriamente precisa ser definida
	             "Swagger With Spring Boot",
	             "This is a simple application to demonstrate how to work with Swagger in Spring Boot project!",
	             "Free to use and mess around",
	             "erudio@gmail.com",
	             "Open Licence",
	             "myemail@gmail.com"
	       );
	       return apiInfo;
	    }

	//Compartilhamento entre origens diferentes sem bloqueio
	@Bean
	public CorsFilter corsFilter() {

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config =  new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("OPTIONS");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
