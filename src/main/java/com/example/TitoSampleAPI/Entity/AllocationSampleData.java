package com.example.TitoSampleAPI.Entity;

import java.util.List;

public class AllocationSampleData {
    private String DRIVERNAME;
    private Long GATESLIP;
    private String VEHICLE;
    private String ENTRYDATE;
    private String ENTRYTIME;
    private String VEHTYPE;
    private List<TjDelivery> TJ_DELIVERY;
    
    // Constructors
    public AllocationSampleData() {}
    
    // Getters and Setters
    public String getDriverName() { return DRIVERNAME; }
    public void setDriverName(String driverName) { this.DRIVERNAME = driverName; }
    
    public Long getGateSlip() { return GATESLIP; }
    public void setGateSlip(Long gateSlip) { this.GATESLIP = gateSlip; }
    
    public String getVehicle() { return VEHICLE; }
    public void setVehicle(String vehicle) { this.VEHICLE = vehicle; }
    
    public String getEntryDate() { return ENTRYDATE; }
    public void setEntryDate(String entryDate) { this.ENTRYDATE = entryDate; }
    
    public String getEntryTime() { return ENTRYTIME; }
    public void setEntryTime(String entryTime) { this.ENTRYTIME = entryTime; }
    
    public String getVehType() { return VEHTYPE; }
    public void setVehType(String vehType) { this.VEHTYPE = vehType; }
    
    public List<TjDelivery> getTJ_DELIVERY() { return TJ_DELIVERY; }
    public void setTJ_DELIVERY(List<TjDelivery> tjDelivery) { this.TJ_DELIVERY = tjDelivery; }

    @Override
    public String toString() {
        return "AllocationSampleData [driverName=" + DRIVERNAME + ", gateSlip=" + GATESLIP + ", vehicle=" + VEHICLE 
                + ", entryDate=" + ENTRYDATE + ", entryTime=" + ENTRYTIME + ", vehType=" + VEHTYPE + ", tjDelivery="
                + TJ_DELIVERY + "]";
    }

    
}