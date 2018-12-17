package org.telegram.customization.util;

import android.util.Base64;
import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    private Cipher a;
    private Cipher b;

    public a(String arg2, String arg3, String arg4) {
        super();
        this.a = this.a(1, arg2, arg3, arg4);
        this.b = this.a(2, arg2, arg3, arg4);
    }

    public byte[] a(String arg2) {
        try {
            return this.a.doFinal(this.d(arg2));
        }
        catch(Throwable v2) {
            throw new RuntimeException(v2);
        }
    }

    private Cipher a(int arg2, String arg3, String arg4, String arg5) {
        Cipher v0 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        v0.init(arg2, this.a(arg4, arg5), new IvParameterSpec(this.d(arg3)));
        return v0;
    }

    private static String a(byte[] arg9) {
        int v7;
        int v6;
        StringBuilder v0 = new StringBuilder();
        int v1 = arg9.length;
        int v3 = 0;
        while(true) {
        label_5:
            if(v3 >= v1) {
                goto label_27;
            }

            int v4 = arg9[v3];
            int v5 = v4 >>> 4 & 15;
            v6 = 0;
            while(true) {
            label_10:
                v5 += v5 < 0 || v5 > 9 ? 87 : 48;
                v0.append(((char)v5));
                v5 = v4 & 15;
                v7 = v6 + 1;
                if(v6 < 1) {
                    break;
                }

                ++v3;
                goto label_5;
            }
        }

        v6 = v7;
        goto label_10;
    label_27:
        return v0.toString();
    }

    private Key a(String arg5, String arg6) {
        return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(arg6.toCharArray(), this.d(arg5), 1000, 128)).getEncoded(), "AES");
    }

    public String b(String arg2) {
        return new String(this.b.doFinal(Base64.decode(this.d(arg2), 0)));
    }

    public static String c(String arg3) {
        MessageDigest v0 = MessageDigest.getInstance("SHA-1");
        byte[] v3 = arg3.getBytes("iso-8859-1");
        v0.update(v3, 0, v3.length);
        return a.a(v0.digest());
    }

    private byte[] d(String arg2) {
        return arg2.getBytes("UTF-8");
    }
}

