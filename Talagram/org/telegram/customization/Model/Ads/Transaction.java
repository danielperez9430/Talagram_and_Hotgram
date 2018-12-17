package org.telegram.customization.Model.Ads;

public class Transaction {
    String date;
    String desc;
    String value;

    public Transaction() {
        super();
    }

    public String getDate() {
        return this.date;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getValue() {
        return this.value;
    }

    public void setDate(String arg1) {
        this.date = arg1;
    }

    public void setDesc(String arg1) {
        this.desc = arg1;
    }

    public void setValue(String arg1) {
        this.value = arg1;
    }
}

