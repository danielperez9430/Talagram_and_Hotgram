package com.persianswitch.sdk.base.webservice;

import com.persianswitch.sdk.base.webservice.exception.WSCallException;

public class HttpResult {
    private int a;
    private WSCallException b;
    private String c;

    public HttpResult() {
        super();
    }

    public void a(WSCallException arg1) {
        this.b = arg1;
    }

    public void a(int arg1) {
        this.a = arg1;
    }

    public void a(String arg1) {
        this.c = arg1;
    }

    public WSCallException a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    public String c() {
        return this.c;
    }
}

