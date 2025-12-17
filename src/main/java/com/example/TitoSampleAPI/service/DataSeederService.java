package com.example.TitoSampleAPI.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.TitoSampleAPI.Entity.AllocationSampleData;
import com.example.TitoSampleAPI.Entity.DeallocationSampleData;
import com.example.TitoSampleAPI.Entity.TjDelivery;

import jakarta.annotation.PostConstruct;

@Service
public class DataSeederService {
    // Map<Long, AllocationSampleData> map = new HashMap<>();
    Map<Long, AllocationSampleData> allocationMap = new HashMap<>();
    Map<Long, DeallocationSampleData> deallocationMap = new HashMap<>();
    Map<String, String> plantDetails = new HashMap<>();
    Map<Long, Boolean> plantNoRequiredMap = new HashMap<>();

    // Getter methods to access the maps
    public Map<Long, AllocationSampleData> getAllocationMap() {
        return allocationMap;
    }

    public Map<Long, DeallocationSampleData> getDeallocationMap() {
        return deallocationMap;
    }

    // getter for plantDetails
    public Map<String, String> getPlantDetails() {
        return plantDetails;
    }

    // getter for plantNoRequiredMap
    public Map<Long, Boolean> getPlantNoRequiredMap() {
        return plantNoRequiredMap;
    }

    @PostConstruct
    public void seedSampleData() {
        System.out.println("Seeding sample data...");
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
        data2.setVehType("LOADING");

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

        // New record from JSON
        AllocationSampleData data3 = new AllocationSampleData();
        data3.setGateSlip(3100510143L);
        data3.setVehicle("Rj09gc6872");
        data3.setMATTYPE("Soyabean DOC (48%)");
        data3.setEntryDate("2025-12-16");
        data3.setEntryTime("16:16:54");
        data3.setVehType("UNLOADING");
        data3.setMATCODE("000000000021000021");

        allocationMap.put(6205115649L, data);
        allocationMap.put(6205143387L, data2);
        allocationMap.put(3100510143L, data3);

        // Plant no required gateslip no
        plantNoRequiredMap.put(6205115649L, true);
        plantNoRequiredMap.put(3100510143L, true);

        // Plant no without required gateslip no
        plantNoRequiredMap.put(6205143387L, false);

    }

    @PostConstruct
    public void seedDeallocationData() {
        DeallocationSampleData data = new DeallocationSampleData();
        data.setWerks("N365");
        data.setName1("Poultry Feed-Inhouse-Rajasthan");
        data.setGinus("BDRDISPGATEI");
        data.setEtweight(14610);
        data.setLtweight(21990);
        data.setUnit1("KG");

        DeallocationSampleData data2 = new DeallocationSampleData();
        data2.setWerks("N365");
        data2.setName1("Poultry Feed- Inhouse-Tolagaon");
        data2.setGinus("9685715419");
        data2.setEtweight(12620);
        data2.setLtweight(52630);
        data2.setUnit1("KG");

        DeallocationSampleData data3 = new DeallocationSampleData();
        data3.setWerks("N406");
        data3.setName1("Poultry Feed-Inhouse-Jajpur");
        data3.setGinus("BDRDISPGATEI");
        data3.setEtweight(14610);
        data3.setLtweight(21990);
        data3.setUnit1("KG");

        deallocationMap.put(6205115649L, data);
        deallocationMap.put(3100510143L, data2);

        deallocationMap.put(6205143387L, data3);
    }

    @PostConstruct
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
}
