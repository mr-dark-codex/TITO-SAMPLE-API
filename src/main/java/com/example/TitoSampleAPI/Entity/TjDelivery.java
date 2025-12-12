package com.example.TitoSampleAPI.Entity;

public class TjDelivery {
    private String vbeln;
    private Integer posnr;
    private String matnr;
    private String maktx;
    private Integer lfimg;
    private String meins;
    
    // Constructors
    public TjDelivery() {}
    
    // Getters and Setters
    public String getVbeln() { return vbeln; }
    public void setVbeln(String vbeln) { this.vbeln = vbeln; }
    
    public Integer getPosnr() { return posnr; }
    public void setPosnr(Integer posnr) { this.posnr = posnr; }
    
    public String getMatnr() { return matnr; }
    public void setMatnr(String matnr) { this.matnr = matnr; }
    
    public String getMaktx() { return maktx; }
    public void setMaktx(String maktx) { this.maktx = maktx; }
    
    public Integer getLfimg() { return lfimg; }
    public void setLfimg(Integer lfimg) { this.lfimg = lfimg; }
    
    public String getMeins() { return meins; }
    public void setMeins(String meins) { this.meins = meins; }

    @Override
    public String toString() {
        return "TjDelivery [vbeln=" + vbeln + ", posnr=" + posnr + ", matnr=" + matnr + ", maktx=" + maktx + ", lfimg="
                + lfimg + ", meins=" + meins + "]";
    }

    
}