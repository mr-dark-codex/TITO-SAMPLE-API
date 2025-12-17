package com.example.TitoSampleAPI.controller;

import com.example.TitoSampleAPI.Entity.AllocationSampleData;
import com.example.TitoSampleAPI.Entity.DeallocationSampleData;
import com.example.TitoSampleAPI.service.DataSeederService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sap/bc/rest/")
public class AllocationController2 {
    Map<Long, AllocationSampleData> allocationMap = new HashMap<>();
    Map<Long, DeallocationSampleData> deallocationMap = new HashMap<>();
    Map<String, String> plantDetails = new HashMap<>();
    Map<Long, Boolean> gatesliMap = new HashMap<>();
    private final String PLANT_NO = "N365";

    @Autowired
    private DataSeederService dataSeederService;

    @GetMapping("/titogatepass")
    public ResponseEntity<?> getAllocationSample(
            @RequestParam Long gateslip,
            @RequestParam String vehtype,
            @RequestParam(required = true) String plant,
            @RequestParam(name = "sap-client", required = false) String sapClient) {

        // Validate empty strings
        if (vehtype == null || vehtype.trim().isEmpty() ||
                plant == null || plant.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid parameter gateslip or vehtype or plant");
        }

        System.out.println("Passing Parameter gateslip : " + gateslip);
        System.out.println("Passing Parameter SAP client No : " + sapClient);
        System.out.println("Passing Parameter SAP Vehtype : " + vehtype);
        System.out.println("Passing Parameter Plant No : " + plant);

        // First Validation
        if (!vehtype.equalsIgnoreCase("LOADING") && !vehtype.equalsIgnoreCase("UNLOADING")) {
            return ResponseEntity.ok(new HashMap<>());
        }

        AllocationSampleData data = allocationMap.getOrDefault(gateslip, null);
        if (data != null) {
            System.out.println("Allocation API Data : " + data.toString());
            if (vehtype.equalsIgnoreCase(data.getVehType())) {

                boolean isPlantCodeRequired = gatesliMap.containsKey(gateslip) && gatesliMap.get(gateslip);
                System.out.println("IS PANT CODE RQUIRED : " + isPlantCodeRequired);
                if (isPlantCodeRequired) {

                    boolean isValidPlantCode = plantDetails.containsKey(plant);
                    if (!isValidPlantCode) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Plant Code");
                    }

                    if (plant.equalsIgnoreCase(this.PLANT_NO)) {
                        return ResponseEntity.ok(data);
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Plant No");
                    }

                } else {

                    if (plant.equalsIgnoreCase("N406")) {
                        return ResponseEntity.ok(data);
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Plant No");

                    }
                }

            } else {
                System.out.println("Mismatched vehicle type: " + vehtype);
                return ResponseEntity.ok(Map.of("VEHTYPE", vehtype));
            }

        } else {
            System.out.println("No data found for gateslip: " + gateslip);
            return ResponseEntity.ok(Map.of("GATESLIP", gateslip, "VEHTYPE", vehtype));
        }
    }

    @GetMapping("/tito_weighment")
    public ResponseEntity<?> getDeallocationSample(
            @RequestParam Long gateslip,
            @RequestParam String vehtype,
            @RequestParam(required = true) String plant,
            @RequestParam(name = "sap-client", required = false) String sapClientNo) {

        // Validate empty strings
        if (vehtype == null || vehtype.trim().isEmpty() ||
                plant == null || plant.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid parameter gateslip or vehtype or plant");
        }

        // Validate vehtype
        if (!vehtype.equalsIgnoreCase("LOADING") && !vehtype.equalsIgnoreCase("UNLOADING")) {
            return ResponseEntity.ok(List.of(Map.of("WERKS", plant, "NAME1", "Poultry Feed- Inhouse-Tumkur")));
        }

        AllocationSampleData data = allocationMap.get(gateslip);
        DeallocationSampleData dellocatedData = deallocationMap.get(gateslip);

        boolean isPlantCodeRequired = gatesliMap.containsKey(gateslip) && gatesliMap.get(gateslip);
        boolean isGateslipValid = dellocatedData != null;
        boolean isPlantValid = false;
        boolean isVehtypeValid = dellocatedData != null && vehtype.equalsIgnoreCase(data.getVehType());
        System.out.println("IS PLANT CODE RQUIRED : " + isPlantCodeRequired);

        if (isPlantCodeRequired) {
            boolean isValidPlantCode = plantDetails.containsKey(plant);
            if (!isValidPlantCode) {
                return ResponseEntity.badRequest().body("Invalid Plant Code");
            }
            isPlantValid = true; // Set Plant is Valid

        } else {
            isPlantValid = !plant.isEmpty() && plant.equalsIgnoreCase("N406");
        }

        System.out.println("dellocatedData : " + dellocatedData);
        System.out.println("IS GATESLIP VALID : " + isGateslipValid);
        System.out.println("IS PLANT VALID : " + isPlantValid);
        System.out.println("IS VEHTYPE VALID : " + isVehtypeValid);

        // All three invalid
        if (!isGateslipValid && !isPlantValid && !isVehtypeValid) {
            return ResponseEntity.ok(List.of(Map.of("WERKS", plant)));
        }

        // Two conditions invalid
        if (!isGateslipValid && !isPlantValid) {
            return ResponseEntity.ok(List.of(Map.of("WERKS", plant, "UNIT1", "KG")));
        }

        if (!isGateslipValid && !isVehtypeValid) {
            return ResponseEntity.ok(List.of(Map.of("WERKS", plant, "NAME1", dellocatedData.getName1())));
        }

        if (!isPlantValid && !isVehtypeValid) {
            return ResponseEntity.ok(List.of(Map.of("WERKS", plant)));
        }

        // Single condition invalid
        if (!isGateslipValid) {
            return ResponseEntity
                    .ok(List.of(Map.of("WERKS", plant, "NAME1", dellocatedData.getName1(), "UNIT1", "KG")));
        }

        if (!isPlantValid) {
            return ResponseEntity
                    .ok(List.of(Map.of("WERKS", dellocatedData.getWerks(), "GINUS", dellocatedData.getGinus(),
                            "ETWEIGHT", dellocatedData.getEtweight(), "LTWEIGHT", dellocatedData.getLtweight(), "UNIT1",
                            "KG")));
        }

        if (!isVehtypeValid) {
            return ResponseEntity.ok(List.of(Map.of("WERKS", plant, "NAME1", dellocatedData.getName1())));
        }

        // All valid - return full data
        return ResponseEntity.ok(List.of(dellocatedData));
    }

    @PostConstruct
    private void initMaps() {
        allocationMap = dataSeederService.getAllocationMap();
        deallocationMap = dataSeederService.getDeallocationMap();
        plantDetails = dataSeederService.getPlantDetails();
        gatesliMap = dataSeederService.getPlantNoRequiredMap();
    }

    // @ExceptionHandler(MissingServletRequestParameterException.class)
    // public ResponseEntity<ErrorResponse>
    // handleMissingParams(MissingServletRequestParameterException ex) {
    // return ResponseEntity.badRequest()
    // // .body(new ErrorResponse("Invalid parameter gateslip or vehtype or
    // plant"));
    // .body(ResponseEntity.ok("Invalid parameter gateslip or vehtype or plant"));
    // }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest()
                .body("Invalid parameter gateslip or vehtype or plant");
    }

}
