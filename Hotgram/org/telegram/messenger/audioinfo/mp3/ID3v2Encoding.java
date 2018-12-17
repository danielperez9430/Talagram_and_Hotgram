package org.telegram.messenger.audioinfo.mp3;

import java.nio.charset.Charset;

public enum ID3v2Encoding {
    public static final enum ID3v2Encoding ISO_8859_1;
    public static final enum ID3v2Encoding UTF_16;
    public static final enum ID3v2Encoding UTF_16BE;
    public static final enum ID3v2Encoding UTF_8;
    private final Charset charset;
    private final int zeroBytes;

    static {
        ID3v2Encoding.ISO_8859_1 = new ID3v2Encoding("ISO_8859_1", 0, Charset.forName("ISO-8859-1"), 1);
        ID3v2Encoding.UTF_16 = new ID3v2Encoding("UTF_16", 1, Charset.forName("UTF-16"), 2);
        ID3v2Encoding.UTF_16BE = new ID3v2Encoding("UTF_16BE", 2, Charset.forName("UTF-16BE"), 2);
        ID3v2Encoding.UTF_8 = new ID3v2Encoding("UTF_8", 3, Charset.forName("UTF-8"), 1);
        ID3v2Encoding.$VALUES = new ID3v2Encoding[]{ID3v2Encoding.ISO_8859_1, ID3v2Encoding.UTF_16, ID3v2Encoding.UTF_16BE, ID3v2Encoding.UTF_8};
    }

    private ID3v2Encoding(String arg1, int arg2, Charset arg3, int arg4) {
        super(arg1, arg2);
        this.charset = arg3;
        this.zeroBytes = arg4;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public int getZeroBytes() {
        return this.zeroBytes;
    }

    public static ID3v2Encoding valueOf(String arg1) {
        return Enum.valueOf(ID3v2Encoding.class, arg1);
    }

    public static ID3v2Encoding[] values() {
        // Method was not decompiled
    }
}

