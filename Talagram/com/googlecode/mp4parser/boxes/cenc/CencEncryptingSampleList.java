package com.googlecode.mp4parser.boxes.cenc;

import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.util.CastUtils;
import com.googlecode.mp4parser.util.RangeStartMap;
import com.mp4parser.iso23001.part7.a$j;
import com.mp4parser.iso23001.part7.a;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
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
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;

public class CencEncryptingSampleList extends AbstractList {
    class com.googlecode.mp4parser.boxes.cenc.CencEncryptingSampleList$1 {
    }

    class EncryptedSampleImpl implements Sample {
        private final SecretKey cek;
        private final a cencSampleAuxiliaryDataFormat;
        private final Cipher cipher;
        private final Sample clearSample;

        static {
        }

        EncryptedSampleImpl(CencEncryptingSampleList arg1, Sample arg2, a arg3, Cipher arg4, SecretKey arg5, EncryptedSampleImpl arg6) {
            this(arg1, arg2, arg3, arg4, arg5);
        }

        private EncryptedSampleImpl(CencEncryptingSampleList arg1, Sample arg2, a arg3, Cipher arg4, SecretKey arg5) {
            CencEncryptingSampleList.this = arg1;
            super();
            this.clearSample = arg2;
            this.cencSampleAuxiliaryDataFormat = arg3;
            this.cipher = arg4;
            this.cek = arg5;
        }

        public ByteBuffer asByteBuffer() {
            int v3;
            Buffer v0 = this.clearSample.asByteBuffer().rewind();
            ByteBuffer v1 = ByteBuffer.allocate(((ByteBuffer)v0).limit());
            a v2 = this.cencSampleAuxiliaryDataFormat;
            CencEncryptingSampleList.this.initCipher(this.cencSampleAuxiliaryDataFormat.a, this.cek);
            try {
                int v4 = 0;
                if(v2.b != null) {
                    j[] v2_1 = v2.b;
                    v3 = v2_1.length;
                    while(v4 < v3) {
                        j v5 = v2_1[v4];
                        byte[] v6 = new byte[v5.a()];
                        ((ByteBuffer)v0).get(v6);
                        v1.put(v6);
                        if(v5.b() > 0) {
                            byte[] v5_1 = new byte[CastUtils.l2i(v5.b())];
                            ((ByteBuffer)v0).get(v5_1);
                            v1.put(this.cipher.update(v5_1));
                        }

                        ++v4;
                    }
                }
                else {
                    byte[] v2_2 = new byte[((ByteBuffer)v0).limit()];
                    ((ByteBuffer)v0).get(v2_2);
                    if("cbc1".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                        v3 = v2_2.length / 16 * 16;
                        v1.put(this.cipher.doFinal(v2_2, 0, v3));
                        v1.put(v2_2, v3, v2_2.length - v3);
                        goto label_61;
                    }

                    if(!"cenc".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                        goto label_61;
                    }

                    v1.put(this.cipher.doFinal(v2_2));
                }

            label_61:
                ((ByteBuffer)v0).rewind();
            }
            catch(BadPaddingException v0_1) {
                throw new RuntimeException(((Throwable)v0_1));
            }
            catch(IllegalBlockSizeException v0_2) {
                throw new RuntimeException(((Throwable)v0_2));
            }

            v1.rewind();
            return v1;
        }

        public long getSize() {
            return this.clearSample.getSize();
        }

        public void writeTo(WritableByteChannel arg14) {
            ByteBuffer v1_1;
            int v3;
            byte[] v1;
            Buffer v0 = this.clearSample.asByteBuffer().rewind();
            CencEncryptingSampleList.this.initCipher(this.cencSampleAuxiliaryDataFormat.a, this.cek);
            try {
                int v2 = 0;
                if(this.cencSampleAuxiliaryDataFormat.b == null || this.cencSampleAuxiliaryDataFormat.b.length <= 0) {
                    v1 = new byte[((ByteBuffer)v0).limit()];
                    ((ByteBuffer)v0).get(v1);
                    if("cbc1".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                        v3 = v1.length / 16 * 16;
                        arg14.write(ByteBuffer.wrap(this.cipher.doFinal(v1, 0, v3)));
                        v1_1 = ByteBuffer.wrap(v1, v3, v1.length - v3);
                        goto label_25;
                    }

                    if(!"cenc".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                        goto label_78;
                    }

                    v1_1 = ByteBuffer.wrap(this.cipher.doFinal(v1));
                label_25:
                    arg14.write(v1_1);
                }
                else {
                    v1 = new byte[((ByteBuffer)v0).limit()];
                    ((ByteBuffer)v0).get(v1);
                    j[] v9 = this.cencSampleAuxiliaryDataFormat.b;
                    int v10 = v9.length;
                    v3 = 0;
                    while(v2 < v10) {
                        j v11 = v9[v2];
                        int v12 = v3 + v11.a();
                        if(v11.b() > 0) {
                            this.cipher.update(v1, v12, CastUtils.l2i(v11.b()), v1, v12);
                            v3 = ((int)((((long)v12)) + v11.b()));
                        }
                        else {
                            v3 = v12;
                        }

                        ++v2;
                    }

                    v1_1 = ByteBuffer.wrap(v1);
                    goto label_25;
                }

            label_78:
                ((ByteBuffer)v0).rewind();
                return;
            }
            catch(ShortBufferException v14) {
                throw new RuntimeException(((Throwable)v14));
            }
            catch(BadPaddingException v14_1) {
                throw new RuntimeException(((Throwable)v14_1));
            }
            catch(IllegalBlockSizeException v14_2) {
                throw new RuntimeException(((Throwable)v14_2));
            }
        }
    }

    List auxiliaryDataFormats;
    RangeStartMap ceks;
    Cipher cipher;
    private final String encryptionAlgo;
    List parent;

    public CencEncryptingSampleList(RangeStartMap arg2, List arg3, List arg4, String arg5) {
        Cipher v2_2;
        super();
        this.ceks = new RangeStartMap();
        this.auxiliaryDataFormats = arg4;
        this.ceks = arg2;
        this.encryptionAlgo = arg5;
        this.parent = arg3;
        try {
            if("cenc".equals(arg5)) {
                v2_2 = Cipher.getInstance("AES/CTR/NoPadding");
            }
            else if("cbc1".equals(arg5)) {
                v2_2 = Cipher.getInstance("AES/CBC/NoPadding");
            }
            else {
                goto label_22;
            }

            this.cipher = v2_2;
            return;
        label_22:
            throw new RuntimeException("Only cenc & cbc1 is supported as encryptionAlgo");
        }
        catch(NoSuchPaddingException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
        catch(NoSuchAlgorithmException v2_1) {
            throw new RuntimeException(((Throwable)v2_1));
        }
    }

    public CencEncryptingSampleList(SecretKey arg3, List arg4, List arg5) {
        this(new RangeStartMap(Integer.valueOf(0), arg3), arg4, arg5, "cenc");
    }

    static String access$1(CencEncryptingSampleList arg0) {
        return arg0.encryptionAlgo;
    }

    public Sample get(int arg9) {
        Object v3 = this.parent.get(arg9);
        if(this.ceks.get(Integer.valueOf(arg9)) != null) {
            return new EncryptedSampleImpl(this, ((Sample)v3), this.auxiliaryDataFormats.get(arg9), this.cipher, this.ceks.get(Integer.valueOf(arg9)), null);
        }

        return ((Sample)v3);
    }

    public Object get(int arg1) {
        return this.get(arg1);
    }

    protected void initCipher(byte[] arg4, SecretKey arg5) {
        int v0 = 16;
        try {
            byte[] v0_1 = new byte[v0];
            System.arraycopy(arg4, 0, v0_1, 0, arg4.length);
            this.cipher.init(1, ((Key)arg5), new IvParameterSpec(v0_1));
            return;
        }
        catch(InvalidKeyException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
        catch(InvalidAlgorithmParameterException v4_1) {
            throw new RuntimeException(((Throwable)v4_1));
        }
    }

    public int size() {
        return this.parent.size();
    }
}

