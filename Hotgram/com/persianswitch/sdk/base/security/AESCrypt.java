package com.persianswitch.sdk.base.security;

import android.util.Base64;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypt {
    private final SecretKeySpec a;
    private AlgorithmParameterSpec b;

    protected AESCrypt(byte[] arg3, byte[] arg4) {
        super();
        this.a = new SecretKeySpec(arg3, "AES");
        this.b = new IvParameterSpec(arg4);
    }

    public String a(String arg5) {
        Cipher v0 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        v0.init(1, this.a, this.b);
        return new String(Base64.encode(v0.doFinal(arg5.getBytes("UTF-8")), 2), "UTF-8");
    }

    public String b(String arg5) {
        Cipher v0 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        v0.init(2, this.a, this.b);
        return new String(v0.doFinal(Base64.decode(arg5, 2)), "UTF-8");
    }
}

