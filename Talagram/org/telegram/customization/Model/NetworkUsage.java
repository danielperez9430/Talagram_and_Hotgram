package org.telegram.customization.Model;

import java.util.ArrayList;
import utils.d;

public class NetworkUsage {
    String carrierName;
    ArrayList mediaUsageStatistics;
    long time;
    int type;

    public NetworkUsage() {
        super();
    }

    public String getCarrierName() {
        return d.c();
    }

    public ArrayList getMediaUsageStatistics() {
        return this.mediaUsageStatistics;
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public int getType() {
        return this.type;
    }

    public void setCarrierName(String arg1) {
        this.carrierName = arg1;
    }

    public void setMediaUsageStatistics(ArrayList arg1) {
        this.mediaUsageStatistics = arg1;
    }

    public void setTime(long arg1) {
        this.time = arg1;
    }

    public void setType(int arg1) {
        this.type = arg1;
    }
}

