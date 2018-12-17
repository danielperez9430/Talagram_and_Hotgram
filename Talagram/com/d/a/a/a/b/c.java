package com.d.a.a.a.b;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class c implements a {
    public c() {
        super();
    }

    private byte[] a(byte[] arg2) {
        try {
            MessageDigest v0 = MessageDigest.getInstance("MD5");
            v0.update(arg2);
            arg2 = v0.digest();
        }
        catch(NoSuchAlgorithmException v2) {
            com.d.a.c.c.a(((Throwable)v2));
            arg2 = null;
        }

        return arg2;
    }

    public String a(String arg2) {
        return new BigInteger(this.a(arg2.getBytes())).abs().toString(36);
    }
}

