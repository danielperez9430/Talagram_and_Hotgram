package org.telegram.customization.Model.Ads;

import com.google.a.a.c;

public class VastRequest {
    String ca;
    long ci;
    long di;
    @c(a="du") long duration;
    int fwdCId;
    String ip;
    boolean isPersonalChat;
    double lat;
    @c(a="long") double lng;
    int nt;
    long ui;

    public VastRequest() {
        super();
    }

    public VastRequest(long arg4, String arg6, long arg7, long arg9, double arg11, double arg13, int arg15, String arg16, boolean arg17, int arg18, long arg19) {
        super();
        this.ui = arg4;
        this.ca = arg6;
        this.ci = arg7;
        this.di = arg9;
        this.lat = arg11;
        this.lng = arg13;
        this.nt = arg15;
        this.ip = arg16;
        this.isPersonalChat = arg17;
        this.fwdCId = arg18;
        this.duration = arg19;
    }

    public String getCa() {
        return this.ca;
    }

    public long getCi() {
        return this.ci;
    }

    public long getDi() {
        return this.di;
    }

    public int getFwdCId() {
        return this.fwdCId;
    }

    public String getIp() {
        return this.ip;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public int getNt() {
        return this.nt;
    }

    public long getUi() {
        return this.ui;
    }

    public boolean isPersonalChat() {
        return this.isPersonalChat;
    }

    public void setCa(String arg1) {
        this.ca = arg1;
    }

    public void setCi(long arg1) {
        this.ci = arg1;
    }

    public void setDi(long arg1) {
        this.di = arg1;
    }

    public void setFwdCId(int arg1) {
        this.fwdCId = arg1;
    }

    public void setIp(String arg1) {
        this.ip = arg1;
    }

    public void setLat(double arg1) {
        this.lat = arg1;
    }

    public void setLng(double arg1) {
        this.lng = arg1;
    }

    public void setNt(int arg1) {
        this.nt = arg1;
    }

    public void setPersonalChat(boolean arg1) {
        this.isPersonalChat = arg1;
    }

    public void setUi(long arg1) {
        this.ui = arg1;
    }
}

