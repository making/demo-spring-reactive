package com.example;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.support.ByteBufferEncoder;
import org.springframework.core.codec.support.JacksonJsonEncoder;
import org.springframework.core.codec.support.JsonObjectEncoder;
import org.springframework.core.codec.support.StringEncoder;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.convert.support.ReactiveStreamsToCompletableFutureConverter;
import org.springframework.core.convert.support.ReactiveStreamsToReactorConverter;
import org.springframework.web.reactive.handler.SimpleHandlerResultHandler;
import org.springframework.web.reactive.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.reactive.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.reactive.method.annotation.ResponseBodyResultHandler;

@Configuration
public class ReactiveConfig {

    @Bean
    RequestMappingHandlerMapping handlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    @Bean
    RequestMappingHandlerAdapter handlerAdapter() {
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setConversionService(conversionService());
        return handlerAdapter;
    }

    @Bean
    ConversionService conversionService() {
        GenericConversionService service = new GenericConversionService();
        service.addConverter(new ReactiveStreamsToCompletableFutureConverter());
        service.addConverter(new ReactiveStreamsToReactorConverter());
        // service.addConverter(new ReactiveStreamsToRxJava1Converter());
        return service;
    }

    @Bean
    ResponseBodyResultHandler responseBodyResultHandler() {
        return new ResponseBodyResultHandler(Arrays.asList(
                new ByteBufferEncoder(), new StringEncoder(),
                new JacksonJsonEncoder(new JsonObjectEncoder())), conversionService());
    }

    @Bean
    SimpleHandlerResultHandler simpleHandlerResultHandler() {
        return new SimpleHandlerResultHandler(conversionService());
    }
}
