package com.google.android.exoplayer2.upstream.crypto;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesFlushingCipher {
    private final int blockSize;
    private final Cipher cipher;
    private final byte[] flushedBlock;
    private int pendingXorBytes;
    private final byte[] zerosBlock;

    public AesFlushingCipher(int arg6, byte[] arg7, long arg8, long arg10) {
        super();
        try {
            this.cipher = Cipher.getInstance("AES/CTR/NoPadding");
            this.blockSize = this.cipher.getBlockSize();
            this.zerosBlock = new byte[this.blockSize];
            this.flushedBlock = new byte[this.blockSize];
            long v0 = arg10 / (((long)this.blockSize));
            int v10 = ((int)(arg10 % (((long)this.blockSize))));
            this.cipher.init(arg6, new SecretKeySpec(arg7, Util.splitAtFirst(this.cipher.getAlgorithm(), "/")[0]), new IvParameterSpec(this.getInitializationVector(arg8, v0)));
            if(v10 != 0) {
                this.updateInPlace(new byte[v10], 0, v10);
            }
        }
        catch(InvalidAlgorithmParameterException v6) {
            throw new RuntimeException(((Throwable)v6));
        }
    }

    private byte[] getInitializationVector(long arg2, long arg4) {
        return ByteBuffer.allocate(16).putLong(arg2).putLong(arg4).array();
    }

    private int nonFlushingUpdate(byte[] arg7, int arg8, int arg9, byte[] arg10, int arg11) {
        try {
            return this.cipher.update(arg7, arg8, arg9, arg10, arg11);
        }
        catch(ShortBufferException v7) {
            throw new RuntimeException(((Throwable)v7));
        }
    }

    public void update(byte[] arg12, int arg13, int arg14, byte[] arg15, int arg16) {
        boolean v9;
        AesFlushingCipher v6 = this;
        int v2 = arg13;
        int v8 = arg14;
        int v7 = arg16;
        do {
            v9 = true;
            if(v6.pendingXorBytes <= 0) {
                goto label_24;
            }

            arg15[v7] = ((byte)(arg12[v2] ^ v6.flushedBlock[v6.blockSize - v6.pendingXorBytes]));
            ++v7;
            ++v2;
            --v6.pendingXorBytes;
            --v8;
        }
        while(v8 != 0);

        return;
    label_24:
        int v0 = this.nonFlushingUpdate(arg12, v2, v8, arg15, v7);
        if(v8 == v0) {
            return;
        }

        v8 -= v0;
        int v10 = 0;
        boolean v1 = v8 < v6.blockSize ? true : false;
        Assertions.checkState(v1);
        v7 += v0;
        v6.pendingXorBytes = v6.blockSize - v8;
        if(this.nonFlushingUpdate(v6.zerosBlock, 0, v6.pendingXorBytes, v6.flushedBlock, 0) == v6.blockSize) {
        }
        else {
            v9 = false;
        }

        Assertions.checkState(v9);
        while(v10 < v8) {
            arg15[v7] = v6.flushedBlock[v10];
            ++v10;
            ++v7;
        }
    }

    public void updateInPlace(byte[] arg7, int arg8, int arg9) {
        this.update(arg7, arg8, arg9, arg7, arg8);
    }
}

