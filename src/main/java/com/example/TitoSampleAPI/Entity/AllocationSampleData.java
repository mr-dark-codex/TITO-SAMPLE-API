package com.example.TitoSampleAPI.Entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllocationSampleData {
    private String DRIVERNAME;
    private Long GATESLIP;
    private String VEHICLE;
    private String ENTRYDATE;
    private String ENTRYTIME;
    private String VEHTYPE;
    // private String PLANT;
    private String MATTYPE;
    private String MATCODE;
    private List<TjDelivery> TJ_DELIVERY;

    // Constructors
    public AllocationSampleData() {
    }

    // Getters and Setters
    public String getDriverName() {
        return DRIVERNAME;
    }

    public void setDriverName(String driverName) {
        this.DRIVERNAME = driverName;
    }

    public Long getGateSlip() {
        return GATESLIP;
    }

    public void setGateSlip(Long gateSlip) {
        this.GATESLIP = gateSlip;
    }

    public String getVehicle() {
        return VEHICLE;
    }

    public void setVehicle(String vehicle) {
        this.VEHICLE = vehicle;
    }

    public String getEntryDate() {
        return ENTRYDATE;
    }

    public void setEntryDate(String entryDate) {
        this.ENTRYDATE = entryDate;
    }

    public String getEntryTime() {
        return ENTRYTIME;
    }

    public void setEntryTime(String entryTime) {
        this.ENTRYTIME = entryTime;
    }

    public String getVehType() {
        return VEHTYPE;
    }

    public void setVehType(String vehType) {
        this.VEHTYPE = vehType;
    }

    public List<TjDelivery> getTJ_DELIVERY() {
        return TJ_DELIVERY;
    }

    public void setTJ_DELIVERY(List<TjDelivery> tjDelivery) {
        this.TJ_DELIVERY = tjDelivery;
    }

    
    // public String getPLANT() {
    //     return PLANT;
    // }

    public String getMATTYPE() {
        return MATTYPE;
    }

    public String getMATCODE() {
        return MATCODE;
    }

    public void setMATTYPE(String mattype) {
        this.MATTYPE = mattype;
    }

    public void setMATCODE(String matcode) {
        this.MATCODE = matcode;
    }

    @Override
    public String toString() {
        return "AllocationSampleData [DRIVERNAME=" + DRIVERNAME + ", GATESLIP=" + GATESLIP + ", VEHICLE=" + VEHICLE
                + ", ENTRYDATE=" + ENTRYDATE + ", ENTRYTIME=" + ENTRYTIME + ", VEHTYPE=" + VEHTYPE + ", MATTYPE="
                + MATTYPE + ", MATCODE=" + MATCODE + ", TJ_DELIVERY=" + TJ_DELIVERY + "]";
    }

   

}