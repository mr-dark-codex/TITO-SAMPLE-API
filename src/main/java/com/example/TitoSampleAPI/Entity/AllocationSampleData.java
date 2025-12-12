package com.example.TitoSampleAPI.Entity;

import java.util.List;

public class AllocationSampleData {
    private String driverName;
    private Long gateSlip;
    private String vehicle;
    private String entryDate;
    private String entryTime;
    private String vehType;
    private List<TjDelivery> tjDelivery;
    
    // Constructors
    public AllocationSampleData() {}
    
    // Getters and Setters
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    
    public Long getGateSlip() { return gateSlip; }
    public void setGateSlip(Long gateSlip) { this.gateSlip = gateSlip; }
    
    public String getVehicle() { return vehicle; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }
    
    public String getEntryDate() { return entryDate; }
    public void setEntryDate(String entryDate) { this.entryDate = entryDate; }
    
    public String getEntryTime() { return entryTime; }
    public void setEntryTime(String entryTime) { this.entryTime = entryTime; }
    
    public String getVehType() { return vehType; }
    public void setVehType(String vehType) { this.vehType = vehType; }
    
    public List<TjDelivery> getTjDelivery() { return tjDelivery; }
    public void setTjDelivery(List<TjDelivery> tjDelivery) { this.tjDelivery = tjDelivery; }

    @Override
    public String toString() {
        return "AllocationSampleData [driverName=" + driverName + ", gateSlip=" + gateSlip + ", vehicle=" + vehicle
                + ", entryDate=" + entryDate + ", entryTime=" + entryTime + ", vehType=" + vehType + ", tjDelivery="
                + tjDelivery + "]";
    }

    
}