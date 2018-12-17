package org.telegram.ui.Components;

import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import org.telegram.messenger.AndroidUtilities;

public class LayoutHelper {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    public LayoutHelper() {
        super();
    }

    public static FrameLayout$LayoutParams createFrame(int arg1, int arg2, int arg3) {
        return new FrameLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)), arg3);
    }

    public static FrameLayout$LayoutParams createFrame(int arg1, float arg2, int arg3, float arg4, float arg5, float arg6, float arg7) {
        FrameLayout$LayoutParams v0 = new FrameLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(arg2), arg3);
        v0.setMargins(AndroidUtilities.dp(arg4), AndroidUtilities.dp(arg5), AndroidUtilities.dp(arg6), AndroidUtilities.dp(arg7));
        return v0;
    }

    public static FrameLayout$LayoutParams createFrame(int arg1, float arg2) {
        return new FrameLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(arg2));
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
        LinearLayout$LayoutParams v0 = new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)));
        v0.setMargins(AndroidUtilities.dp(((float)arg4)), AndroidUtilities.dp(((float)arg5)), AndroidUtilities.dp(((float)arg6)), AndroidUtilities.dp(((float)arg7)));
        v0.gravity = arg3;
        return v0;
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2) {
        return new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)));
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2, float arg3, int arg4, int arg5, int arg6, int arg7) {
        LinearLayout$LayoutParams v0 = new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)), arg3);
        v0.setMargins(AndroidUtilities.dp(((float)arg4)), AndroidUtilities.dp(((float)arg5)), AndroidUtilities.dp(((float)arg6)), AndroidUtilities.dp(((float)arg7)));
        return v0;
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2, float arg3, float arg4, float arg5, float arg6) {
        LinearLayout$LayoutParams v0 = new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)));
        v0.setMargins(AndroidUtilities.dp(arg3), AndroidUtilities.dp(arg4), AndroidUtilities.dp(arg5), AndroidUtilities.dp(arg6));
        return v0;
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2, float arg3) {
        return new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)), arg3);
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2, float arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
        LinearLayout$LayoutParams v0 = new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)), arg3);
        v0.setMargins(AndroidUtilities.dp(((float)arg5)), AndroidUtilities.dp(((float)arg6)), AndroidUtilities.dp(((float)arg7)), AndroidUtilities.dp(((float)arg8)));
        v0.gravity = arg4;
        return v0;
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2, int arg3) {
        LinearLayout$LayoutParams v0 = new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)));
        v0.gravity = arg3;
        return v0;
    }

    public static LinearLayout$LayoutParams createLinear(int arg1, int arg2, float arg3, int arg4) {
        LinearLayout$LayoutParams v0 = new LinearLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)), arg3);
        v0.gravity = arg4;
        return v0;
    }

    public static RelativeLayout$LayoutParams createRelative(float arg9, float arg10, int arg11, int arg12, int arg13, int arg14, int arg15, int arg16) {
        return LayoutHelper.createRelative(arg9, arg10, arg11, arg12, arg13, arg14, -1, arg15, arg16);
    }

    public static RelativeLayout$LayoutParams createRelative(float arg1, float arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
        RelativeLayout$LayoutParams v0 = new RelativeLayout$LayoutParams(LayoutHelper.getSize(arg1), LayoutHelper.getSize(arg2));
        if(arg7 >= 0) {
            v0.addRule(arg7);
        }

        if(arg8 >= 0 && arg9 >= 0) {
            v0.addRule(arg8, arg9);
        }

        v0.leftMargin = AndroidUtilities.dp(((float)arg3));
        v0.topMargin = AndroidUtilities.dp(((float)arg4));
        v0.rightMargin = AndroidUtilities.dp(((float)arg5));
        v0.bottomMargin = AndroidUtilities.dp(((float)arg6));
        return v0;
    }

    public static RelativeLayout$LayoutParams createRelative(int arg9, int arg10) {
        return LayoutHelper.createRelative(((float)arg9), ((float)arg10), 0, 0, 0, 0, -1, -1, -1);
    }

    public static RelativeLayout$LayoutParams createRelative(int arg9, int arg10, int arg11) {
        return LayoutHelper.createRelative(((float)arg9), ((float)arg10), 0, 0, 0, 0, arg11, -1, -1);
    }

    public static RelativeLayout$LayoutParams createRelative(int arg9, int arg10, int arg11, int arg12) {
        return LayoutHelper.createRelative(((float)arg9), ((float)arg10), 0, 0, 0, 0, -1, arg11, arg12);
    }

    public static RelativeLayout$LayoutParams createRelative(int arg9, int arg10, int arg11, int arg12, int arg13) {
        return LayoutHelper.createRelative(((float)arg9), ((float)arg10), 0, 0, 0, 0, arg11, arg12, arg13);
    }

    public static RelativeLayout$LayoutParams createRelative(int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
        return LayoutHelper.createRelative(((float)arg9), ((float)arg10), arg11, arg12, arg13, arg14, -1, -1, -1);
    }

    public static RelativeLayout$LayoutParams createRelative(int arg9, int arg10, int arg11, int arg12, int arg13, int arg14, int arg15) {
        return LayoutHelper.createRelative(((float)arg9), ((float)arg10), arg11, arg12, arg13, arg14, arg15, -1, -1);
    }

    public static FrameLayout$LayoutParams createScroll(int arg1, int arg2, int arg3) {
        return new FrameLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)), arg3);
    }

    public static FrameLayout$LayoutParams createScroll(int arg1, int arg2, int arg3, float arg4, float arg5, float arg6, float arg7) {
        FrameLayout$LayoutParams v0 = new FrameLayout$LayoutParams(LayoutHelper.getSize(((float)arg1)), LayoutHelper.getSize(((float)arg2)), arg3);
        v0.leftMargin = AndroidUtilities.dp(arg4);
        v0.topMargin = AndroidUtilities.dp(arg5);
        v0.rightMargin = AndroidUtilities.dp(arg6);
        v0.bottomMargin = AndroidUtilities.dp(arg7);
        return v0;
    }

    private static int getSize(float arg1) {
        if(arg1 < 0f) {
        }
        else {
            arg1 = ((float)AndroidUtilities.dp(arg1));
        }

        return ((int)arg1);
    }
}

