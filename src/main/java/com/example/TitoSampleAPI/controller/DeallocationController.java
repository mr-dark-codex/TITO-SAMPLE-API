package com.example.TitoSampleAPI.controller;

import com.example.TitoSampleAPI.Entity.DeallocationSampleData;
import com.example.TitoSampleAPI.Entity.ErrorResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sap/bc/rest/")
public class DeallocationController {

    Map<Long, DeallocationSampleData> map = new HashMap<>();

    @GetMapping("/tito_weighment")
    public List<DeallocationSampleData> getDeallocationSample(
            @RequestParam Long gateslip,
            @RequestParam String vehtype,
            @RequestParam(required = false) String plant,
            @RequestParam(required = false) String sapClientNo) {

        this.seedSampleData();

        DeallocationSampleData data = map.get(gateslip);
        if (data != null) {
            return List.of(data);
        }

        return Arrays.asList(new DeallocationSampleData());
    }

    public void seedSampleData() {
        DeallocationSampleData data = new DeallocationSampleData();
        data.setWerks("N441");
        data.setName1("Poultry Feed-Inhouse-Rajasthan");
        data.setGinus("9685715419");
        data.setEtweight(14610);
        data.setLtweight(21990);
        data.setUnit1("KG");

        map.put(6205115649L, data);
        map.put(6205143387L, data);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Invalid parameter gateslip or vehtype or plant or sapClientNo"));
    }
}