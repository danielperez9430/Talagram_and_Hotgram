package com.googlecode.mp4parser.boxes.cenc;

import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.SampleImpl;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.RangeStartMap;
import com.mp4parser.iso23001.part7.a$j;
import com.mp4parser.iso23001.part7.a;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CencDecryptingSampleList extends AbstractList {
    String encryptionAlgo;
    RangeStartMap keys;
    List parent;
    List sencInfo;

    public CencDecryptingSampleList(RangeStartMap arg2, List arg3, List arg4, String arg5) {
        super();
        this.keys = new RangeStartMap();
        this.sencInfo = arg4;
        this.keys = arg2;
        this.parent = arg3;
        this.encryptionAlgo = arg5;
    }

    public CencDecryptingSampleList(SecretKey arg3, List arg4, List arg5) {
        this(new RangeStartMap(Integer.valueOf(0), arg3), arg4, arg5, "cenc");
    }

    public Sample get(int arg11) {
        byte[] v11_3;
        if(this.keys.get(Integer.valueOf(arg11)) != null) {
            Object v0 = this.parent.get(arg11);
            ByteBuffer v1 = ((Sample)v0).asByteBuffer();
            v1.rewind();
            ByteBuffer v2 = ByteBuffer.allocate(v1.limit());
            Object v3 = this.sencInfo.get(arg11);
            Cipher v11 = this.getCipher(this.keys.get(Integer.valueOf(arg11)), ((a)v3).a);
            try {
                int v5 = 0;
                if(((a)v3).b == null || ((a)v3).b.length <= 0) {
                    byte[] v0_1 = new byte[v1.limit()];
                    v1.get(v0_1);
                    if("cbc1".equals(this.encryptionAlgo)) {
                        int v3_3 = v0_1.length / 16 * 16;
                        v2.put(v11.doFinal(v0_1, 0, v3_3));
                        v2.put(v0_1, v3_3, v0_1.length - v3_3);
                        goto label_75;
                    }

                    if(!"cenc".equals(this.encryptionAlgo)) {
                        goto label_75;
                    }

                    v11_3 = v11.doFinal(v0_1);
                label_37:
                    v2.put(v11_3);
                }
                else {
                    j[] v3_1 = ((a)v3).b;
                    int v4 = v3_1.length;
                    while(v5 < v4) {
                        j v6 = v3_1[v5];
                        int v7 = v6.a();
                        int v6_1 = CastUtils.l2i(v6.b());
                        byte[] v7_1 = new byte[v7];
                        v1.get(v7_1);
                        v2.put(v7_1);
                        if(v6_1 > 0) {
                            byte[] v6_2 = new byte[v6_1];
                            v1.get(v6_2);
                            v2.put(v11.update(v6_2));
                        }

                        ++v5;
                    }

                    if(v1.remaining() > 0) {
                        PrintStream v3_2 = System.err;
                        StringBuilder v4_1 = new StringBuilder("Decrypted sample but still data remaining: ");
                        v4_1.append(((Sample)v0).getSize());
                        v3_2.println(v4_1.toString());
                    }

                    v11_3 = v11.doFinal();
                    goto label_37;
                }

            label_75:
                v1.rewind();
            }
            catch(BadPaddingException v11_1) {
                throw new RuntimeException(((Throwable)v11_1));
            }
            catch(IllegalBlockSizeException v11_2) {
                throw new RuntimeException(((Throwable)v11_2));
            }

            v2.rewind();
            return new SampleImpl(v2);
        }

        return this.parent.get(arg11);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    Cipher getCipher(SecretKey arg4, byte[] arg5) {
        Cipher v5;
        byte[] v0 = new byte[16];
        System.arraycopy(arg5, 0, v0, 0, arg5.length);
        try {
            int v1 = 2;
            if("cenc".equals(this.encryptionAlgo)) {
                v5 = Cipher.getInstance("AES/CTR/NoPadding");
                v5.init(v1, ((Key)arg4), new IvParameterSpec(v0));
                return v5;
            }

            if("cbc1".equals(this.encryptionAlgo)) {
                v5 = Cipher.getInstance("AES/CBC/NoPadding");
                v5.init(v1, ((Key)arg4), new IvParameterSpec(v0));
                return v5;
            }

            throw new RuntimeException("Only cenc & cbc1 is supported as encryptionAlgo");
        }
        catch(InvalidKeyException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
        catch(InvalidAlgorithmParameterException v4_1) {
            throw new RuntimeException(((Throwable)v4_1));
        }
        catch(NoSuchPaddingException v4_2) {
            throw new RuntimeException(((Throwable)v4_2));
        }
        catch(NoSuchAlgorithmException v4_3) {
            throw new RuntimeException(((Throwable)v4_3));
        }
    }

    public int size() {
        return this.parent.size();
    }
}

