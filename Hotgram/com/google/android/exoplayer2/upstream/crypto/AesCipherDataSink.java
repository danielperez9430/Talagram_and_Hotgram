package com.google.android.exoplayer2.upstream.crypto;

import com.google.android.exoplayer2.upstream.DataSink;
import com.google.android.exoplayer2.upstream.DataSpec;

public final class AesCipherDataSink implements DataSink {
    private AesFlushingCipher cipher;
    private final byte[] scratch;
    private final byte[] secretKey;
    private final DataSink wrappedDataSink;

    public AesCipherDataSink(byte[] arg2, DataSink arg3) {
        this(arg2, arg3, null);
    }

    public AesCipherDataSink(byte[] arg1, DataSink arg2, byte[] arg3) {
        super();
        this.wrappedDataSink = arg2;
        this.secretKey = arg1;
        this.scratch = arg3;
    }

    public void close() {
        this.cipher = null;
        this.wrappedDataSink.close();
    }

    public void open(DataSpec arg9) {
        this.wrappedDataSink.open(arg9);
        this.cipher = new AesFlushingCipher(1, this.secretKey, CryptoUtil.getFNV64Hash(arg9.key), arg9.absoluteStreamPosition);
    }

    public void write(byte[] arg11, int arg12, int arg13) {
        if(this.scratch == null) {
            this.cipher.updateInPlace(arg11, arg12, arg13);
            this.wrappedDataSink.write(arg11, arg12, arg13);
        }
        else {
            int v1;
            for(v1 = 0; v1 < arg13; v1 += v2) {
                int v2 = Math.min(arg13 - v1, this.scratch.length);
                this.cipher.update(arg11, arg12 + v1, v2, this.scratch, 0);
                this.wrappedDataSink.write(this.scratch, 0, v2);
            }
        }
    }
}

