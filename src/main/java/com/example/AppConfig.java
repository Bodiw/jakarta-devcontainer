package com.example;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.core.Application;

public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(com.example.HelloWorldResource.class);
        return classes;
    }

}