package com.google.android.exoplayer2.text.ttml;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan$Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import java.util.Map;

final class TtmlRenderUtil {
    private TtmlRenderUtil() {
        super();
    }

    public static void applyStylesToSpan(SpannableStringBuilder arg3, int arg4, int arg5, TtmlStyle arg6) {
        int v1 = 33;
        if(arg6.getStyle() != -1) {
            arg3.setSpan(new StyleSpan(arg6.getStyle()), arg4, arg5, v1);
        }

        if(arg6.isLinethrough()) {
            arg3.setSpan(new StrikethroughSpan(), arg4, arg5, v1);
        }

        if(arg6.isUnderline()) {
            arg3.setSpan(new UnderlineSpan(), arg4, arg5, v1);
        }

        if(arg6.hasFontColor()) {
            arg3.setSpan(new ForegroundColorSpan(arg6.getFontColor()), arg4, arg5, v1);
        }

        if(arg6.hasBackgroundColor()) {
            arg3.setSpan(new BackgroundColorSpan(arg6.getBackgroundColor()), arg4, arg5, v1);
        }

        if(arg6.getFontFamily() != null) {
            arg3.setSpan(new TypefaceSpan(arg6.getFontFamily()), arg4, arg5, v1);
        }

        if(arg6.getTextAlign() != null) {
            arg3.setSpan(new AlignmentSpan$Standard(arg6.getTextAlign()), arg4, arg5, v1);
        }

        switch(arg6.getFontSizeUnit()) {
            case 1: {
                goto label_55;
            }
            case 2: {
                goto label_51;
            }
            case 3: {
                goto label_45;
            }
        }

        return;
    label_51:
        RelativeSizeSpan v0 = new RelativeSizeSpan(arg6.getFontSize());
        goto label_60;
    label_55:
        AbsoluteSizeSpan v0_1 = new AbsoluteSizeSpan(((int)arg6.getFontSize()), true);
        goto label_60;
    label_45:
        v0 = new RelativeSizeSpan(arg6.getFontSize() / 100f);
    label_60:
        arg3.setSpan(v0, arg4, arg5, v1);
    }

    static String applyTextElementSpacePolicy(String arg2) {
        return arg2.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " ");
    }

    static void endParagraph(SpannableStringBuilder arg3) {
        int v0;
        for(v0 = arg3.length() - 1; v0 >= 0; --v0) {
            if(arg3.charAt(v0) != 32) {
                break;
            }
        }

        if(v0 >= 0) {
            char v1 = '\n';
            if(arg3.charAt(v0) != v1) {
                arg3.append(v1);
            }
        }
    }

    public static TtmlStyle resolveStyle(TtmlStyle arg3, String[] arg4, Map arg5) {
        int v1;
        if(arg3 == null && arg4 == null) {
            return null;
        }

        int v0 = 0;
        if(arg3 == null && arg4.length == 1) {
            return arg5.get(arg4[0]);
        }

        if(arg3 == null && arg4.length > 1) {
            arg3 = new TtmlStyle();
            v1 = arg4.length;
            while(v0 < v1) {
                arg3.chain(arg5.get(arg4[v0]));
                ++v0;
            }

            return arg3;
        }

        if(arg3 != null && arg4 != null && arg4.length == 1) {
            return arg3.chain(arg5.get(arg4[0]));
        }

        if(arg3 != null && arg4 != null && arg4.length > 1) {
            v1 = arg4.length;
            while(v0 < v1) {
                arg3.chain(arg5.get(arg4[v0]));
                ++v0;
            }
        }

        return arg3;
    }
}

