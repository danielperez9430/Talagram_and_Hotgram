package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

final class Aes128DataSource implements DataSource {
    private CipherInputStream cipherInputStream;
    private final byte[] encryptionIv;
    private final byte[] encryptionKey;
    private final DataSource upstream;

    public Aes128DataSource(DataSource arg1, byte[] arg2, byte[] arg3) {
        super();
        this.upstream = arg1;
        this.encryptionKey = arg2;
        this.encryptionIv = arg3;
    }

    public void addTransferListener(TransferListener arg2) {
        this.upstream.addTransferListener(arg2);
    }

    public void close() {
        if(this.cipherInputStream != null) {
            this.cipherInputStream = null;
            this.upstream.close();
        }
    }

    public Map getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    public Uri getUri() {
        return this.upstream.getUri();
    }

    public long open(DataSpec arg5) {
        Cipher v0;
        try {
            v0 = Cipher.getInstance("AES/CBC/PKCS7Padding");
        }
        catch(NoSuchPaddingException v5) {
            throw new RuntimeException(((Throwable)v5));
        }

        SecretKeySpec v1 = new SecretKeySpec(this.encryptionKey, "AES");
        IvParameterSpec v2 = new IvParameterSpec(this.encryptionIv);
        int v3 = 2;
        try {
            v0.init(v3, ((Key)v1), ((AlgorithmParameterSpec)v2));
        }
        catch(InvalidAlgorithmParameterException v5_1) {
            throw new RuntimeException(((Throwable)v5_1));
        }

        DataSourceInputStream v1_1 = new DataSourceInputStream(this.upstream, arg5);
        this.cipherInputStream = new CipherInputStream(((InputStream)v1_1), v0);
        v1_1.open();
        return -1;
    }

    public int read(byte[] arg2, int arg3, int arg4) {
        Assertions.checkNotNull(this.cipherInputStream);
        int v2 = this.cipherInputStream.read(arg2, arg3, arg4);
        if(v2 < 0) {
            v2 = -1;
        }

        return v2;
    }
}

