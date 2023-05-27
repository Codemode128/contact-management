package com.shivam.cms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ContactConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public OpenAPI contactsAPI() {
        return new OpenAPI()
                .info(getInfo())
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    private Info getInfo(){
        Contact contact = new Contact();
        contact.setName("Shivam Singh");
        contact.setEmail("shivamsingh794@gmail.com");
        return new Info().title("Contact Management API")
                .description("APIs to manage contacts of users")
                .version("v1.0.0")
                .contact(contact)
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

    }
}
