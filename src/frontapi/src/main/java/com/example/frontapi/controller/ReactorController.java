package com.example.frontapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;;

@RestController
@RequestMapping("reactor")
public class ReactorController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/{id}")
    public Mono<String> get(@PathVariable Integer id) {
        return Mono.just(id)
            .map(x -> {
                LOGGER.info("Thread # {}: map", Thread.currentThread().getId());
                return "Id: " + x;
            })
            .log()
            .doOnNext(x -> {
                LOGGER.info("Thread # {}: doOnNext {}", Thread.currentThread().getId(), x);
            });
    }
}
