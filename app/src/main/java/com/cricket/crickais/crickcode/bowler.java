package com.cricket.crickais.crickcode;

public class bowler {
    private String pname;
    private String pover;
    private String pmedi;
    private String prrun;
    private String pwik;
    private String per;
    private String pimage;
    private String pid;

    public bowler() {
    }

    public bowler(String pname, String pover, String pmedi, String prrun, String pwik, String per, String pimage,String pid) {
        this.pname = pname;
        this.pover = pover;
        this.pmedi = pmedi;
        this.prrun = prrun;
        this.pwik = pwik;
        this.per = per;
        this.pimage = pimage;
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

    public String getPover() {
        return pover;
    }

    public void setPover(String pover) {
        this.pover = pover;
    }

    public String getPmedi() {
        return pmedi;
    }

    public void setPmedi(String pmedi) {
        this.pmedi = pmedi;
    }

    public String getPrrun() {
        return prrun;
    }

    public void setPrrun(String prrun) {
        this.prrun = prrun;
    }

    public String getPwik() {
        return pwik;
    }

    public void setPwik(String pwik) {
        this.pwik = pwik;
    }

    public String getPer() {
        return per;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
}