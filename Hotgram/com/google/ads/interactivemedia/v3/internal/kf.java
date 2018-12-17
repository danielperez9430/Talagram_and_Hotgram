package com.google.ads.interactivemedia.v3.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class kf {
    public class a extends Exception {
        public a(kf arg1) {
            this.a = arg1;
            super();
        }

        public a(kf arg1, Throwable arg2) {
            this.a = arg1;
            super(arg2);
        }
    }

    private final kd a;
    private final SecureRandom b;

    public kf(kd arg1, SecureRandom arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    static void a(byte[] arg2) {
        int v0;
        for(v0 = 0; v0 < arg2.length; ++v0) {
            arg2[v0] = ((byte)(arg2[v0] ^ 68));
        }
    }

    public byte[] a(String arg3) {
        try {
            byte[] v3_1 = this.a.a(arg3, false);
            if(v3_1.length == 32) {
                ByteBuffer v3_2 = ByteBuffer.wrap(v3_1, 4, 16);
                byte[] v0 = new byte[16];
                v3_2.get(v0);
                kf.a(v0);
                return v0;
            }

            throw new a(this);
        }
        catch(IllegalArgumentException v3) {
            throw new a(this, ((Throwable)v3));
        }
    }

    public byte[] a(byte[] arg5, String arg6) {
        int v1 = 16;
        if(arg5.length == v1) {
            try {
                byte[] v6 = this.a.a(arg6, false);
                if(v6.length > v1) {
                    ByteBuffer v0 = ByteBuffer.allocate(v6.length);
                    v0.put(v6);
                    v0.flip();
                    byte[] v2 = new byte[v1];
                    v6 = new byte[v6.length - v1];
                    v0.get(v2);
                    v0.get(v6);
                    SecretKeySpec v0_1 = new SecretKeySpec(arg5, "AES");
                    Cipher v5_7 = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    v5_7.init(2, ((Key)v0_1), new IvParameterSpec(v2));
                    return v5_7.doFinal(v6);
                }

                throw new a(this);
            }
            catch(IllegalArgumentException v5) {
                throw new a(this, ((Throwable)v5));
            }
            catch(InvalidAlgorithmParameterException v5_1) {
                throw new a(this, ((Throwable)v5_1));
            }
            catch(BadPaddingException v5_2) {
                throw new a(this, ((Throwable)v5_2));
            }
            catch(NoSuchPaddingException v5_3) {
                throw new a(this, ((Throwable)v5_3));
            }
            catch(IllegalBlockSizeException v5_4) {
                throw new a(this, ((Throwable)v5_4));
            }
            catch(InvalidKeyException v5_5) {
                throw new a(this, ((Throwable)v5_5));
            }
            catch(NoSuchAlgorithmException v5_6) {
                throw new a(this, ((Throwable)v5_6));
            }
        }

        throw new a(this);
    }
}

