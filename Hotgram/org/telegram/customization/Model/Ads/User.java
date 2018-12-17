package org.telegram.customization.Model.Ads;

import java.util.ArrayList;

public class User {
    ArrayList info;
    int point;
    ArrayList transactions;

    public User() {
        super();
    }

    public ArrayList getInfo() {
        return this.info;
    }

    public int getPoint() {
        return this.point;
    }

    public ArrayList getTransactions() {
        return this.transactions;
    }

    public void setInfo(ArrayList arg1) {
        this.info = arg1;
    }

    public void setPoint(int arg1) {
        this.point = arg1;
    }

    public void setTransactions(ArrayList arg1) {
        this.transactions = arg1;
    }
}

