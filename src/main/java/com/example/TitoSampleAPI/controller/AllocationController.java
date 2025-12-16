package com.example.TitoSampleAPI.controller;

import com.example.TitoSampleAPI.Entity.AllocationSampleData;
import com.example.TitoSampleAPI.Entity.ErrorResponse;
import com.example.TitoSampleAPI.Entity.TjDelivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/v1/")
public class AllocationController {

    Map<Long, AllocationSampleData> map = new HashMap<>();
    Map<String, String> plantDetails = new HashMap<>();
    private final String PLANT_NO = "N365";

    @GetMapping("/titogatepass")
    public ResponseEntity<?> getAllocationSample(
            @RequestParam(required = false) Long gateslip,
            @RequestParam(required = false) String vehtype,
            @RequestParam(required = false) String plant,
            @RequestParam(required = false) String sapClientNo) {

        // Initialize data
        this.seedSampleData();
        this.initPlantDetails();

        // 1. Check if required parameters are missing
        if (gateslip == null || vehtype == null || vehtype.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Input Parameter \"gateslip or plant or vehtype\" is missing");
        }

        // 5. Check if plant is missing (when required)
        if (plant == null || plant.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Input Parameter \"gateslip or plant or vehtype\" is missing");
        }

        // 2. If vehtype is random/invalid
        if (!vehtype.equalsIgnoreCase("LOADING") && !vehtype.equalsIgnoreCase("UNLOADING")) {
            return ResponseEntity.ok(new HashMap<>());
        }

        // 4. Check if plant is mismatched
        if (!plantDetails.containsKey(plant)) {
            AllocationSampleData data = map.get(gateslip);
            if (data != null) {
                AllocationSampleData response = new AllocationSampleData();
                response.setDriverName(data.getDriverName());
                response.setGateSlip(data.getGateSlip());
                response.setVehicle(data.getVehicle());
                response.setEntryDate(data.getEntryDate());
                response.setEntryTime(data.getEntryTime());
                response.setVehType(data.getVehType());
                // No material details for mismatched plant
                return ResponseEntity.ok(response);
            }
        }

        AllocationSampleData data = map.get(gateslip);
        
        // 7. If gateslip is random/mismatched
        if (data == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("GATESLIP", gateslip);
            response.put("VEHTYPE", vehtype.toUpperCase());
            return ResponseEntity.ok(response);
        }

        // 3. If vehtype is mismatched (opposite of what's required)
        if (!vehtype.equalsIgnoreCase(data.getVehType())) {
            Map<String, String> response = new HashMap<>();
            response.put("VEHTYPE", vehtype.toUpperCase());
            return ResponseEntity.ok(response);
        }

        // 8. Valid gateslip but not for this plant - return without material details
        if (plantDetails.containsKey(plant) && data != null) {
            
            // Check if this is a cross-plant scenario (simplified logic)
            if (!plant.equals(PLANT_NO) && gateslip.equals(6205158602L)) {
                AllocationSampleData response = new AllocationSampleData();
                response.setDriverName("manish");
                response.setGateSlip(6205158602L);
                response.setVehicle("RJ01GD6129");
                response.setEntryDate("2025-12-12");
                response.setEntryTime("10:23:04");
                response.setVehType("LOADING");
                return ResponseEntity.ok(response);
            }
        }

        // Valid case - return full data
        return ResponseEntity.ok(List.of(data));
    }



    @GetMapping("/titogatepass_v2") 
    public ResponseEntity<?> getAllocationSample_v2(
            @RequestParam Long gateslip,
            @RequestParam String vehtype,
            @RequestParam(required = false) String plant,
            @RequestParam(required = false) String sapClientNo
    )  {

        this.seedSampleData();
        this.initPlantDetails();




        return ResponseEntity.ok(new HashMap<>());
    }
    

    public void seedSampleData() {
        if (!map.isEmpty()) return;
        
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

        // Additional sample data for testing scenarios
        AllocationSampleData data3 = new AllocationSampleData();
        data3.setDriverName("BALA saheb");
        data3.setGateSlip(6205158601L);
        data3.setVehicle("TS05UD4488");
        data3.setEntryDate("2025-12-12");
        data3.setEntryTime("11:20:20");
        data3.setVehType("LOADING");

        map.put(6205115649L, data);
        map.put(6205143387L, data2);
        map.put(6205158601L, data3);
    }

    public void initPlantDetails() {
        if (!plantDetails.isEmpty()) return;
        
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