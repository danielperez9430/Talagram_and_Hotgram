package com.google.android.exoplayer2.text;

import android.graphics.Bitmap;
import android.text.Layout$Alignment;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Cue {
    @Retention(value=RetentionPolicy.SOURCE) @public interface AnchorType {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface LineType {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface TextSizeType {
    }

    public static final int ANCHOR_TYPE_END = 2;
    public static final int ANCHOR_TYPE_MIDDLE = 1;
    public static final int ANCHOR_TYPE_START = 0;
    public static final float DIMEN_UNSET = 0f;
    public static final int LINE_TYPE_FRACTION = 0;
    public static final int LINE_TYPE_NUMBER = 1;
    public static final int TEXT_SIZE_TYPE_ABSOLUTE = 2;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL = 0;
    public static final int TEXT_SIZE_TYPE_FRACTIONAL_IGNORE_PADDING = 1;
    public static final int TYPE_UNSET = -2147483648;
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final int positionAnchor;
    public final float size;
    public final CharSequence text;
    public final Layout$Alignment textAlignment;
    public final float textSize;
    public final int textSizeType;
    public final int windowColor;
    public final boolean windowColorSet;

    public Cue(Bitmap arg16, float arg17, int arg18, float arg19, int arg20, float arg21, float arg22) {
        this(null, null, arg16, arg19, 0, arg20, arg17, arg18, -2147483648, 0f, arg21, arg22, false, -16777216);
    }

    private Cue(CharSequence arg1, Layout$Alignment arg2, Bitmap arg3, float arg4, int arg5, int arg6, float arg7, int arg8, int arg9, float arg10, float arg11, float arg12, boolean arg13, int arg14) {
        super();
        this.text = arg1;
        this.textAlignment = arg2;
        this.bitmap = arg3;
        this.line = arg4;
        this.lineType = arg5;
        this.lineAnchor = arg6;
        this.position = arg7;
        this.positionAnchor = arg8;
        this.size = arg11;
        this.bitmapHeight = arg12;
        this.windowColorSet = arg13;
        this.windowColor = arg14;
        this.textSizeType = arg9;
        this.textSize = arg10;
    }

    public Cue(CharSequence arg10) {
        this(arg10, null, 0f, -2147483648, -2147483648, 0f, -2147483648, 0f);
    }

    public Cue(CharSequence arg12, Layout$Alignment arg13, float arg14, int arg15, int arg16, float arg17, int arg18, float arg19) {
        this(arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, false, -16777216);
    }

    public Cue(CharSequence arg16, Layout$Alignment arg17, float arg18, int arg19, int arg20, float arg21, int arg22, float arg23, boolean arg24, int arg25) {
        this(arg16, arg17, null, arg18, arg19, arg20, arg21, arg22, -2147483648, 0f, arg23, 0f, arg24, arg25);
    }

    public Cue(CharSequence arg16, Layout$Alignment arg17, float arg18, int arg19, int arg20, float arg21, int arg22, float arg23, int arg24, float arg25) {
        this(arg16, arg17, null, arg18, arg19, arg20, arg21, arg22, arg24, arg25, arg23, 0f, false, -16777216);
    }
}

