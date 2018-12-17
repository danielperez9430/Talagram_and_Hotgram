package com.persianswitch.sdk.payment.model;

import com.persianswitch.sdk.base.webservice.WebService$WSStatus;
import com.persianswitch.sdk.base.webservice.data.WSResponse;

public enum TransactionStatus {
    public static final enum TransactionStatus a;
    public static final enum TransactionStatus b;
    public static final enum TransactionStatus c;

    static {
        TransactionStatus.a = new TransactionStatus("SUCCESS", 0);
        TransactionStatus.b = new TransactionStatus("FAIL", 1);
        TransactionStatus.c = new TransactionStatus("UNKNOWN", 2);
        TransactionStatus.d = new TransactionStatus[]{TransactionStatus.a, TransactionStatus.b, TransactionStatus.c};
    }

    private TransactionStatus(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static boolean a(WSStatus arg0, WSResponse arg1) {
        boolean v0 = (arg0.a()) || arg1 == null || (arg1.c().b()) ? true : false;
        return v0;
    }

    public static TransactionStatus valueOf(String arg1) {
        return Enum.valueOf(TransactionStatus.class, arg1);
    }

    public static TransactionStatus[] values() {
        // Method was not decompiled
    }
}

