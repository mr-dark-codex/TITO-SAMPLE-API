package com.example.TitoSampleAPI.Entity;

public class TjDelivery {
    private String VBELN;
    private Integer POSNR;
    private String MATNR;
    private String MAKTX;
    private Integer LFIMG;
    private String MEINS;
    
    // Constructors
    public TjDelivery() {}
    
    // Getters and Setters
    public String getVBELN() { return VBELN; }
    public void setVbeln(String VBELN) { this.VBELN = VBELN; }
    
    public Integer getPOSNR() { return POSNR; }
    public void setPosnr(Integer POSNR) { this.POSNR = POSNR; }
    
    public String getMATNR() { return MATNR; }
    public void setMatnr(String MATNR) { this.MATNR = MATNR; }
    
    public String getMAKTX() { return MAKTX; }
    public void setMaktx(String MAKTX) { this.MAKTX = MAKTX; }
    
    public Integer getLFIMG() { return LFIMG; }
    public void setLfimg(Integer LFIMG) { this.LFIMG = LFIMG; }
    
    public String getMEINS() { return MEINS; }
    public void setMeins(String MEINS) { this.MEINS = MEINS; }

    @Override
    public String toString() {
        return "TjDelivery [VBELN=" + VBELN + ", POSNR=" + POSNR + ", MATNR=" + MATNR + ", MAKTX=" + MAKTX + ", LFIMG="
                + LFIMG + ", MEINS=" + MEINS + "]";
    }

    
}