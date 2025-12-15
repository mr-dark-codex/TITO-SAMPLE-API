package com.example.TitoSampleAPI.controller;

import com.example.TitoSampleAPI.Entity.AllocationSampleData;
import com.example.TitoSampleAPI.Entity.ErrorResponse;
import com.example.TitoSampleAPI.Entity.TjDelivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sap/bc/rest/")
public class AllocationController2 {
    Map<Long, AllocationSampleData> map = new HashMap<>();
    Map<String, String> plantDetails = new HashMap<>();
    Map<Long, Boolean> gatesliMap = new HashMap<>();
    private final String PLANT_NO = "N365";
    // Map<

    @GetMapping("/titogatepass")
    public ResponseEntity<?> getAllocationSample(
            @RequestParam Long gateslip,
            @RequestParam String vehtype,
            @RequestParam String plant,
            @RequestParam(required = false) String sapClientNo) {

        System.out.println("gateslip : " + gateslip);
        // seed the data
        this.seedSampleData();
        this.initPlantDetails();

        // First Validation
        if (!vehtype.equalsIgnoreCase("LOADING") && !vehtype.equalsIgnoreCase("UNLOADING")) {
            return ResponseEntity.ok(new HashMap<>());
        }

        AllocationSampleData data = map.get(gateslip);
        if (data != null) {
            System.out.println("Allocation API Data : " + data.toString());
            if (vehtype.equalsIgnoreCase(data.getVehType())) {
                // return ResponseEntity.ok(data);

                // Plant No:
                if (gatesliMap.containsKey(gateslip) && plant != null && !plant.trim().isEmpty()) {
                    if (!plantDetails.containsKey(plant)) {
                        System.out
                                .println("Mismatched plant number: " + plant + ", expected: " + this.PLANT_NO);
                        return ResponseEntity.ok("Invalid Plant No");
                    }
                }
                return ResponseEntity.ok(data);

            } else {
                System.out.println("Mismatched vehicle type: " + vehtype);
                return ResponseEntity.ok(Map.of("VEHTYPE", vehtype));
            }

            
        } else {
            System.out.println("No data found for gateslip: " + gateslip);
            return ResponseEntity.ok(Map.of("GATESLIP", gateslip, "VEHTYPE", vehtype));
        }
    }

    public void seedSampleData() {
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

        data.setTjDelivery(Arrays.asList(item1, item2));

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

        data2.setTjDelivery(Arrays.asList(item3, item4));

        map.put(6205115649L, data);
        map.put(6205143387L, data2);

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
                .body(new ErrorResponse("Invalid parameter gateslip or vehtype or plant"));
    }
}
