package com.persianswitch.sdk.payment.model;

public class CardPin {
    private final boolean a;
    private String b;
    private String c;
    private String d;
    private String e;

    public CardPin(String arg1, String arg2, String arg3, String arg4, boolean arg5) {
        super();
        this.b = arg1;
        this.c = arg2;
        this.d = arg3;
        this.e = arg4;
        this.a = arg5;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append(this.b);
        v0.append(";");
        v0.append(this.c);
        v0.append(";");
        String v1_1 = this.a ? this.e + this.d : "0000";
        v0.append(v1_1);
        v0.append(",1");
        return v0.toString();
    }
}

