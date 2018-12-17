package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodec$CryptoInfo;

public final class ax {
    public byte[] a;
    public byte[] b;
    public int c;
    public int[] d;
    public int[] e;
    public int f;
    private final MediaCodec$CryptoInfo g;

    public ax() {
        super();
        MediaCodec$CryptoInfo v0 = ft.a >= 16 ? this.b() : null;
        this.g = v0;
    }

    @TargetApi(value=16) public MediaCodec$CryptoInfo a() {
        return this.g;
    }

    public void a(int arg1, int[] arg2, int[] arg3, byte[] arg4, byte[] arg5, int arg6) {
        this.f = arg1;
        this.d = arg2;
        this.e = arg3;
        this.b = arg4;
        this.a = arg5;
        this.c = arg6;
        if(ft.a >= 16) {
            this.c();
        }
    }

    @TargetApi(value=16) private MediaCodec$CryptoInfo b() {
        return new MediaCodec$CryptoInfo();
    }

    @TargetApi(value=16) private void c() {
        this.g.set(this.f, this.d, this.e, this.b, this.a, this.c);
    }
}

