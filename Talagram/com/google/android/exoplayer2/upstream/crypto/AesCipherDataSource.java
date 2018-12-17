package com.google.android.exoplayer2.upstream.crypto;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.util.Map;

public final class AesCipherDataSource implements DataSource {
    private AesFlushingCipher cipher;
    private final byte[] secretKey;
    private final DataSource upstream;

    public AesCipherDataSource(byte[] arg1, DataSource arg2) {
        super();
        this.upstream = arg2;
        this.secretKey = arg1;
    }

    public void addTransferListener(TransferListener arg2) {
        this.upstream.addTransferListener(arg2);
    }

    public void close() {
        this.cipher = null;
        this.upstream.close();
    }

    public Map getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    public Uri getUri() {
        return this.upstream.getUri();
    }

    public long open(DataSpec arg11) {
        long v0 = this.upstream.open(arg11);
        this.cipher = new AesFlushingCipher(2, this.secretKey, CryptoUtil.getFNV64Hash(arg11.key), arg11.absoluteStreamPosition);
        return v0;
    }

    public int read(byte[] arg2, int arg3, int arg4) {
        if(arg4 == 0) {
            return 0;
        }

        arg4 = this.upstream.read(arg2, arg3, arg4);
        int v0 = -1;
        if(arg4 == v0) {
            return v0;
        }

        this.cipher.updateInPlace(arg2, arg3, arg4);
        return arg4;
    }
}

