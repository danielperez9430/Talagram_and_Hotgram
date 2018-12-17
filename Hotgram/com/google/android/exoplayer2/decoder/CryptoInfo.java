package com.google.android.exoplayer2.decoder;

import android.annotation.TargetApi;
import android.media.MediaCodec$CryptoInfo$Pattern;
import android.media.MediaCodec$CryptoInfo;
import com.google.android.exoplayer2.util.Util;

public final class CryptoInfo {
    class com.google.android.exoplayer2.decoder.CryptoInfo$1 {
    }

    @TargetApi(value=24) final class PatternHolderV24 {
        private final MediaCodec$CryptoInfo frameworkCryptoInfo;
        private final MediaCodec$CryptoInfo$Pattern pattern;

        PatternHolderV24(MediaCodec$CryptoInfo arg1, com.google.android.exoplayer2.decoder.CryptoInfo$1 arg2) {
            this(arg1);
        }

        private PatternHolderV24(MediaCodec$CryptoInfo arg2) {
            super();
            this.frameworkCryptoInfo = arg2;
            this.pattern = new MediaCodec$CryptoInfo$Pattern(0, 0);
        }

        static void access$100(PatternHolderV24 arg0, int arg1, int arg2) {
            arg0.set(arg1, arg2);
        }

        private void set(int arg2, int arg3) {
            this.pattern.set(arg2, arg3);
            this.frameworkCryptoInfo.setPattern(this.pattern);
        }
    }

    public int clearBlocks;
    public int encryptedBlocks;
    private final MediaCodec$CryptoInfo frameworkCryptoInfo;
    public byte[] iv;
    public byte[] key;
    public int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    public int numSubSamples;
    private final PatternHolderV24 patternHolder;

    public CryptoInfo() {
        super();
        com.google.android.exoplayer2.decoder.CryptoInfo$1 v1 = null;
        MediaCodec$CryptoInfo v0 = Util.SDK_INT >= 16 ? this.newFrameworkCryptoInfoV16() : ((MediaCodec$CryptoInfo)v1);
        this.frameworkCryptoInfo = v0;
        PatternHolderV24 v0_1 = Util.SDK_INT >= 24 ? new PatternHolderV24(this.frameworkCryptoInfo, v1) : ((PatternHolderV24)v1);
        this.patternHolder = v0_1;
    }

    @TargetApi(value=16) public MediaCodec$CryptoInfo getFrameworkCryptoInfoV16() {
        return this.frameworkCryptoInfo;
    }

    @TargetApi(value=16) private MediaCodec$CryptoInfo newFrameworkCryptoInfoV16() {
        return new MediaCodec$CryptoInfo();
    }

    public void set(int arg1, int[] arg2, int[] arg3, byte[] arg4, byte[] arg5, int arg6, int arg7, int arg8) {
        this.numSubSamples = arg1;
        this.numBytesOfClearData = arg2;
        this.numBytesOfEncryptedData = arg3;
        this.key = arg4;
        this.iv = arg5;
        this.mode = arg6;
        this.encryptedBlocks = arg7;
        this.clearBlocks = arg8;
        if(Util.SDK_INT >= 16) {
            this.updateFrameworkCryptoInfoV16();
        }
    }

    @TargetApi(value=16) private void updateFrameworkCryptoInfoV16() {
        this.frameworkCryptoInfo.numSubSamples = this.numSubSamples;
        this.frameworkCryptoInfo.numBytesOfClearData = this.numBytesOfClearData;
        this.frameworkCryptoInfo.numBytesOfEncryptedData = this.numBytesOfEncryptedData;
        this.frameworkCryptoInfo.key = this.key;
        this.frameworkCryptoInfo.iv = this.iv;
        this.frameworkCryptoInfo.mode = this.mode;
        if(Util.SDK_INT >= 24) {
            PatternHolderV24.access$100(this.patternHolder, this.encryptedBlocks, this.clearBlocks);
        }
    }
}

