package com.example.TitoSampleAPI.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RootController {
    @GetMapping("/")
    public ResponseEntity<?> getRoot() {
        return ResponseEntity.ok(Map.of("health", "API IS WORKING"));
    }
}
