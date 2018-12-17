package com.google.android.exoplayer2.text.ttml;

final class TtmlRegion {
    public final String id;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final float textSize;
    public final int textSizeType;
    public final float width;

    public TtmlRegion(String arg10) {
        this(arg10, 0f, 0f, -2147483648, -2147483648, 0f, -2147483648, 0f);
    }

    public TtmlRegion(String arg1, float arg2, float arg3, int arg4, int arg5, float arg6, int arg7, float arg8) {
        super();
        this.id = arg1;
        this.position = arg2;
        this.line = arg3;
        this.lineType = arg4;
        this.lineAnchor = arg5;
        this.width = arg6;
        this.textSizeType = arg7;
        this.textSize = arg8;
    }
}

