package com.example.TitoSampleAPI.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeallocationSampleData {
    private String WERKS;
    private String NAME1;
    private String GINUS;
    private Integer ETWEIGHT;
    private Integer LTWEIGHT;
    private String UNIT1;
    
    // Constructors
    public DeallocationSampleData() {}
    
    // Getters and Setters
    public String getWerks() { return WERKS; }
    public void setWerks(String werks) { this.WERKS = werks; }
    
    public String getName1() { return NAME1; }
    public void setName1(String name1) { this.NAME1 = name1; }
    
    public String getGinus() { return GINUS; }
    public void setGinus(String ginus) { this.GINUS = ginus; }
    
    public Integer getEtweight() { return ETWEIGHT; }
    public void setEtweight(Integer etweight) { this.ETWEIGHT = etweight; }
    
    public Integer getLtweight() { return LTWEIGHT; }
    public void setLtweight(Integer ltweight) { this.LTWEIGHT = ltweight; }
    
    public String getUnit1() { return UNIT1; }
    public void setUnit1(String unit1) { this.UNIT1 = unit1; }

    @Override
    public String toString() {
        return "DeallocationSampleData [WERKS=" + WERKS + ", NAME1=" + NAME1 + ", GINUS=" + GINUS + ", ETWEIGHT="
                + ETWEIGHT + ", LTWEIGHT=" + LTWEIGHT + ", UNIT1=" + UNIT1 + "]";
    }

    
}