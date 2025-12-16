package com.example.TitoSampleAPI.controller;

import com.example.TitoSampleAPI.Entity.AllocationSampleData;
import com.example.TitoSampleAPI.Entity.DeallocationSampleData;
import com.example.TitoSampleAPI.Entity.ErrorResponse;
import com.example.TitoSampleAPI.Entity.TjDelivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sap/bc/rest/")
public class DeallocationController {

    Map<Long, AllocationSampleData> allocationMap = new HashMap<>();
    Map<Long, DeallocationSampleData> map = new HashMap<>();
    Map<String, String> plantDetails = new HashMap<>();
    Map<Long, Boolean> gatesliMap = new HashMap<>();
    private final String PLANT_NO = "N365";

    @GetMapping("/tito_weighment2d")
    public ResponseEntity<?> getDeallocationSample(
            @RequestParam Long gateslip,
            @RequestParam String vehtype,
            @RequestParam String plant,
            @RequestParam(required = false) String sapClientNo) {

        this.seedSampleData();
        this.seedSampleAllocationData();
        this.initPlantDetails();

        // Validate vehtype
        if (!vehtype.equalsIgnoreCase("LOADING") && !vehtype.equalsIgnoreCase("UNLOADING")) {
            return ResponseEntity.ok(List.of(Map.of("WERKS", plant, "NAME1", "Poultry Feed- Inhouse-Tumkur")));
        }

        AllocationSampleData data = allocationMap.get(gateslip);
        boolean isPlantValid = plantDetails.containsKey(plant);
        boolean isVehtypeValid = data != null && vehtype.equalsIgnoreCase(data.getVehType());
        boolean isGateslipValid = data != null;

        // All correct - return full data
        if (isGateslipValid && isVehtypeValid && isPlantValid) {
            return ResponseEntity.ok(data);
        }

        // Build error response based on validation failures
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("WERKS", plant);
        errorResponse.put("NAME1", "Poultry Feed- Inhouse-Tumkur");
        errorResponse.put("UNIT1", "KG");

        // Add specific fields based on what's wrong
        if (!isGateslipValid) {
            // Gateslip not found - minimal response
            if (!isPlantValid) {
                return ResponseEntity.ok(List.of(Map.of("WERKS", plant, "UNIT1", "KG")));
            }
        } else if (!isVehtypeValid) {
            // Vehtype mismatch - add weight info
            errorResponse.put("GINUS", "BDRDISPGATEI");
            errorResponse.put("ETWEIGHT", 12210);
            errorResponse.put("LTWEIGHT", 42200);
        }

        return ResponseEntity.ok(List.of(errorResponse));
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

    public void seedSampleAllocationData() {
        AllocationSampleData data = new AllocationSampleData();
        data.setDriverName("Sunil Kumar");
        data.setGateSlip(6205115649L);
        data.setVehicle("RJ32GC2669");
        data.setEntryDate("2025-12-11");
        data.setEntryTime("10:07:31");
        data.setVehType("LOADING");

        TjDelivery item1 = new TjDelivery();
        item1.setVbeln("8124245793");
        item1.setPosnr(10);
        item1.setMatnr(" 71000085");
        item1.setMaktx("IB ROSS BROILER FINISHER FEED");
        item1.setLfimg(7740);
        item1.setMeins("KG");

        TjDelivery item2 = new TjDelivery();
        item2.setVbeln("8124245793");
        item2.setPosnr(20);
        item2.setMatnr(" 71000085");
        item2.setMaktx("IB ROSS BROILER FINISHER FEED");
        item2.setLfimg(7800);
        item2.setMeins("KG");

        data.setTJ_DELIVERY(Arrays.asList(item1, item2));

        AllocationSampleData data2 = new AllocationSampleData();
        data2.setDriverName("Kailash");
        data2.setGateSlip(6205143387L);
        data2.setVehicle("RJ23GA7425");
        data2.setEntryDate("2025-12-11");
        data2.setEntryTime("09:47:23");
        data2.setVehType("UNLOADING");

        TjDelivery item3 = new TjDelivery();
        item3.setVbeln("8124267542");
        item3.setPosnr(10);
        item3.setMatnr(" 71000083");
        item3.setMaktx("IB ROSS BROILER (PRESTARTER) FEED");
        item3.setLfimg(120);
        item3.setMeins("KG");

        TjDelivery item4 = new TjDelivery();
        item4.setVbeln("8124267542");
        item4.setPosnr(20);
        item4.setMatnr(" 71000083");
        item4.setMaktx("IB ROSS BROILER (PRESTARTER) FEED");
        item4.setLfimg(60);
        item4.setMeins("KG");

        data2.setTJ_DELIVERY(Arrays.asList(item3, item4));

        allocationMap.put(6205115649L, data);
        allocationMap.put(6205143387L, data2);

        // Plant no required or not
        gatesliMap.put(6205143387L, false);
        gatesliMap.put(6205115649L, true);

    }

    public void initPlantDetails() {
        if (!plantDetails.isEmpty())
            return;

        plantDetails.put("N101", "SOYA_SOLVENT");
        plantDetails.put("N201", "Refinery_Plant");
        plantDetails.put("N202", "Refinery_ETP");
        plantDetails.put("N205", "Tin_Plant");
        plantDetails.put("N206", "Jar_Plant");
        plantDetails.put("N207", "Bottle_Plant");
        plantDetails.put("N208", "Oil_Packing");
        plantDetails.put("N365", "Poultry_Feed");
        plantDetails.put("N406", "Poultry_Feed_1");
        plantDetails.put("N407", "Rendering_Plant");
        plantDetails.put("N601", "Premix_Plant");
        plantDetails.put("N652", "Soya_Chunk_Plant");
        plantDetails.put("NC07", "Boiler");
        plantDetails.put("NC09", "Central_Store");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Invalid parameter gateslip or vehtype or plant or sapClientNo"));
    }
}