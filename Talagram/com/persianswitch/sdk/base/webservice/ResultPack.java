package com.persianswitch.sdk.base.webservice;

import com.persianswitch.sdk.base.webservice.data.WSResponse;
import com.persianswitch.sdk.payment.model.TransactionStatus;

public final class ResultPack {
    private TransactionStatus a;
    private WSResponse b;
    private String c;
    private WSStatus d;

    public ResultPack() {
        super();
    }

    public ResultPack a(WSStatus arg1) {
        this.d = arg1;
        return this;
    }

    public ResultPack a(WSResponse arg1) {
        this.b = arg1;
        return this;
    }

    public ResultPack a(TransactionStatus arg1) {
        this.a = arg1;
        return this;
    }

    public ResultPack a(String arg1) {
        this.c = arg1;
        return this;
    }

    public TransactionStatus a() {
        return this.a;
    }

    public WSResponse b() {
        return this.b;
    }
}

