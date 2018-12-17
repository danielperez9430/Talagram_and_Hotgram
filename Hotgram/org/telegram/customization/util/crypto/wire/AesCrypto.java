package org.telegram.customization.util.crypto.wire;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCrypto {
    private static final String LEGACY_INIT_VECTOR = "YC\'2bmK=b%#NQ?9j";
    private static final Charset UTF8;
    private final IvParameterSpec ivSpec;
    private final SecretKeySpec secretKeySpec;

    static {
        AesCrypto.UTF8 = Charset.forName("UTF8");
    }

    AesCrypto(String arg2, String arg3) {
        this(arg2, arg3, true);
    }

    private AesCrypto(String arg4, String arg5, boolean arg6) {
        super();
        try {
            MessageDigest v0 = MessageDigest.getInstance("MD5");
            byte[] v5 = arg6 ? v0.digest(arg5.getBytes(AesCrypto.UTF8)) : arg5.getBytes(AesCrypto.UTF8);
            this.ivSpec = new IvParameterSpec(v5);
            byte[] v4_1 = arg6 ? v0.digest(arg4.getBytes(AesCrypto.UTF8)) : arg4.getBytes(AesCrypto.UTF8);
            this.secretKeySpec = new SecretKeySpec(v4_1, "AES");
            return;
        }
        catch(GeneralSecurityException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
    }

    public static byte[] decrypt(long arg0, byte[] arg2, boolean arg3, boolean arg4) {
        String v0 = Long.toHexString(arg0);
        if(arg3) {
            arg2 = AesCrypto.getSingleCrypto(v0).decrypt(arg2);
        }

        if(arg4) {
            arg2 = IoUtils.gunzip(arg2);
        }

        return arg2;
    }

    public byte[] decrypt(byte[] arg5) {
        try {
            Cipher v0 = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            v0.init(2, this.secretKeySpec, this.ivSpec);
            return v0.doFinal(arg5);
        }
        catch(GeneralSecurityException v5) {
            throw new RuntimeException(((Throwable)v5));
        }
    }

    public String decryptAsStr(byte[] arg3) {
        return new String(this.decrypt(arg3), AesCrypto.UTF8);
    }

    public static byte[] encrypt(long arg0, byte[] arg2) {
        return AesCrypto.getSingleCrypto(Long.toHexString(arg0)).encrypt(arg2);
    }

    public byte[] encrypt(byte[] arg5) {
        try {
            Cipher v0 = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            v0.init(1, this.secretKeySpec, this.ivSpec);
            return v0.doFinal(arg5);
        }
        catch(GeneralSecurityException v5) {
            throw new RuntimeException(((Throwable)v5));
        }
    }

    public byte[] encrypt(String arg2) {
        return this.encrypt(arg2.getBytes(AesCrypto.UTF8));
    }

    public static AesCrypto getLegacyCrypto(String arg3) {
        return new AesCrypto(arg3, "YC\'2bmK=b%#NQ?9j", false);
    }

    public static AesCrypto getSingleCrypto(String arg2) {
        return new AesCrypto(arg2, arg2, true);
    }
}

