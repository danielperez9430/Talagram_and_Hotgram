package org.telegram.customization.Model.Payment;

import java.util.ArrayList;

public class SettleHelper {
    SettleReportHeader key;
    ArrayList value;

    public SettleHelper() {
        super();
        this.value = new ArrayList();
    }

    public SettleReportHeader getKey() {
        return this.key;
    }

    public ArrayList getValue() {
        return this.value;
    }

    public void setKey(SettleReportHeader arg1) {
        this.key = arg1;
    }

    public void setValue(ArrayList arg1) {
        this.value = arg1;
    }
}

