package com.persianswitch.sdk.base.security;

import android.content.Context;
import android.util.Base64;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Locale;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

public class SecurityManager {
    private static SecurityManager a;
    private final RSACrypt b;

    static {
    }

    private SecurityManager(Context arg2) {
        super();
        this.b = new RSACrypt(arg2);
    }

    public static SecurityManager a(Context arg1) {
        if(SecurityManager.a == null) {
            SecurityManager.a = new SecurityManager(arg1);
        }

        return SecurityManager.a;
    }

    public String a(String arg4, byte[] arg5) {
        SecureRandom v0 = SecureRandom.getInstance("SHA1PRNG");
        byte[] v1 = new byte[16];
        v0.nextBytes(v1);
        new IvParameterSpec(v1);
        AESCrypt v0_1 = new AESCrypt(arg5, v1);
        String v5 = this.b.a(arg5);
        arg4 = v0_1.a(arg4);
        return "2#" + v5.trim() + "#" + Base64.encodeToString(v1, 2) + "#" + arg4;
    }

    public byte[] a() {
        byte[] v0 = SecurityManager.b();
        KeyGenerator v1 = KeyGenerator.getInstance("AES");
        SecureRandom v2 = SecureRandom.getInstance("SHA1PRNG");
        v2.setSeed(v0);
        v1.init(128, v2);
        return v1.generateKey().getEncoded();
    }

    private static byte[] b() {
        SecureRandom v0 = new SecureRandom();
        byte[] v1 = new byte[16];
        v0.nextBytes(v1);
        Calendar v0_1 = Calendar.getInstance();
        String v2 = String.format(Locale.US, "%2d", Integer.valueOf(v0_1.get(14)));
        String v0_2 = String.format(Locale.US, "%3d", Integer.valueOf(v0_1.get(13)));
        v2 = v2 + v0_2;
        v3 = new StringBuilder();
        v3.append(v0_2);
        v3.append(v2);
        byte[] v0_3 = v3.toString().getBytes();
        System.arraycopy(v0_3, 0, v1, 0, v0_3.length);
        return v1;
    }

    public String b(String arg3, byte[] arg4) {
        return new AESCrypt(arg4, Base64.decode(arg3.split("#")[1], 2)).b(arg3.split("#")[2]);
    }
}

