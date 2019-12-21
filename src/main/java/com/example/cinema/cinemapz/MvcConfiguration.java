package com.example.cinema.cinemapz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class MvcConfiguration { //TODO better name needed


    //needed so json serializer doesn't break lazy fetch
    @Bean
    protected Module module() {
        return new Hibernate5Module();
    }
}
