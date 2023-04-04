package com.cricket.crickais.crickcode;

public class song {
    private String pname;
    private String pout;
    private String pimage;
    private Integer prun;
    private String pball;
    private String pfour;
    private String psix;
    private String psr;
    private String pid;


    public song() {
    }

    public song(String pname, String pout, String pimage, Integer prun, String pball, String pfour, String psix, String psr, String pid) {
        this.pname = pname;
        this.pout = pout;
        this.pimage = pimage;
        this.prun = prun;
        this.pball = pball;
        this.pfour = pfour;
        this.psix = psix;
        this.psr = psr;
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPout() {
        return pout;
    }

    public void setPout(String pout) {
        this.pout = pout;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public Integer getPrun() {
        return prun;
    }

    public void setPrun(Integer prun) {
        this.prun = prun;
    }

    public String getPball() {
        return pball;
    }

    public void setPball(String pball) {
        this.pball = pball;
    }

    public String getPfour() {
        return pfour;
    }

    public void setPfour(String pfour) {
        this.pfour = pfour;
    }

    public String getPsix() {
        return psix;
    }

    public void setPsix(String psix) {
        this.psix = psix;
    }

    public String getPsr() {
        return psr;
    }

    public void setPsr(String psr) {
        this.psr = psr;
    }
}
