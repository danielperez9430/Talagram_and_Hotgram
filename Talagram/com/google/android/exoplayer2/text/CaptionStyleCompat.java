package com.google.android.exoplayer2.text;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager$CaptionStyle;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class CaptionStyleCompat {
    @Retention(value=RetentionPolicy.SOURCE) @public interface EdgeType {
    }

    public static final CaptionStyleCompat DEFAULT = null;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int USE_TRACK_COLOR_SETTINGS = 1;
    public final int backgroundColor;
    public final int edgeColor;
    public final int edgeType;
    public final int foregroundColor;
    public final Typeface typeface;
    public final int windowColor;

    static {
        CaptionStyleCompat.DEFAULT = new CaptionStyleCompat(-1, -16777216, 0, 0, -1, null);
    }

    public CaptionStyleCompat(int arg1, int arg2, int arg3, int arg4, int arg5, Typeface arg6) {
        super();
        this.foregroundColor = arg1;
        this.backgroundColor = arg2;
        this.windowColor = arg3;
        this.edgeType = arg4;
        this.edgeColor = arg5;
        this.typeface = arg6;
    }

    @TargetApi(value=19) public static CaptionStyleCompat createFromCaptionStyle(CaptioningManager$CaptionStyle arg2) {
        if(Util.SDK_INT >= 21) {
            return CaptionStyleCompat.createFromCaptionStyleV21(arg2);
        }

        return CaptionStyleCompat.createFromCaptionStyleV19(arg2);
    }

    @TargetApi(value=19) private static CaptionStyleCompat createFromCaptionStyleV19(CaptioningManager$CaptionStyle arg8) {
        return new CaptionStyleCompat(arg8.foregroundColor, arg8.backgroundColor, 0, arg8.edgeType, arg8.edgeColor, arg8.getTypeface());
    }

    @TargetApi(value=21) private static CaptionStyleCompat createFromCaptionStyleV21(CaptioningManager$CaptionStyle arg8) {
        CaptionStyleCompat v7 = null;
        int v0 = arg8.hasForegroundColor() ? arg8.foregroundColor : CaptionStyleCompat.DEFAULT.foregroundColor;
        int v1 = v0;
        v0 = arg8.hasBackgroundColor() ? arg8.backgroundColor : CaptionStyleCompat.DEFAULT.backgroundColor;
        int v2 = v0;
        v0 = arg8.hasWindowColor() ? arg8.windowColor : CaptionStyleCompat.DEFAULT.windowColor;
        int v3 = v0;
        v0 = arg8.hasEdgeType() ? arg8.edgeType : CaptionStyleCompat.DEFAULT.edgeType;
        int v4 = v0;
        v0 = arg8.hasEdgeColor() ? arg8.edgeColor : CaptionStyleCompat.DEFAULT.edgeColor;
        int v5 = v0;
        this(v1, v2, v3, v4, v5, arg8.getTypeface());
        return v7;
    }
}

