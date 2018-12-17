package com.google.android.exoplayer2.drm;

public class DecryptionException extends Exception {
    public final int errorCode;

    public DecryptionException(int arg1, String arg2) {
        super(arg2);
        this.errorCode = arg1;
    }
}

