package g.a.a.a.a;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build$VERSION;
import android.text.Layout$Alignment;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout$Builder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.Gravity;
import java.text.Bidi;

public class g {
    public static Typeface a(String arg0, int arg1, int arg2) {
        Typeface v0;
        if(arg0 != null) {
            v0 = Typeface.create(arg0, arg2);
            if(v0 != null) {
                return v0;
            }
        }
        else {
            v0 = null;
        }

        switch(arg1) {
            case 1: {
                v0 = Typeface.SANS_SERIF;
                break;
            }
            case 2: {
                v0 = Typeface.SERIF;
                break;
            }
            case 3: {
                v0 = Typeface.MONOSPACE;
                break;
            }
            default: {
                break;
            }
        }

        return Typeface.create(v0, arg2);
    }

    public static PorterDuff$Mode a(int arg1, PorterDuff$Mode arg2) {
        if(arg1 == 3) {
            goto label_19;
        }

        if(arg1 == 5) {
            goto label_17;
        }

        if(arg1 == 9) {
            goto label_15;
        }

        switch(arg1) {
            case 14: {
                goto label_13;
            }
            case 15: {
                goto label_11;
            }
            case 16: {
                goto label_8;
            }
        }

        return arg2;
    label_8:
        return PorterDuff$Mode.valueOf("ADD");
    label_11:
        return PorterDuff$Mode.SCREEN;
    label_13:
        return PorterDuff$Mode.MULTIPLY;
    label_15:
        return PorterDuff$Mode.SRC_ATOP;
    label_17:
        return PorterDuff$Mode.SRC_IN;
    label_19:
        return PorterDuff$Mode.SRC_OVER;
    }

    public static float a(float arg0, Rect arg1, int arg2, float arg3) {
        if(arg1 != null) {
            arg2 = arg1.right - arg1.left;
        }

        return Math.max(80f, Math.min(arg0, (((float)arg2)) - arg3 * 2f));
    }

    public static float a(Layout arg4) {
        float v0 = 0f;
        if(arg4 != null) {
            int v1 = 0;
            int v2 = arg4.getLineCount();
            while(v1 < v2) {
                v0 = Math.max(v0, arg4.getLineWidth(v1));
                ++v1;
            }
        }

        return v0;
    }

    @SuppressLint(value={"RtlHardcoded"}) public static Layout$Alignment a(Resources arg6, int arg7, CharSequence arg8) {
        Layout$Alignment v6_1;
        int v6;
        int v1 = 5;
        int v3 = 8388613;
        int v4 = 8388611;
        if(Build$VERSION.SDK_INT >= 17) {
            v6 = arg6.getConfiguration().getLayoutDirection();
            if(arg8 != null && v6 == 1 && (new Bidi(arg8.toString(), -2).isRightToLeft())) {
                if(arg7 == v4) {
                    arg7 = 8388613;
                }
                else if(arg7 == v3) {
                    arg7 = 8388611;
                }
            }

            v6 = Gravity.getAbsoluteGravity(arg7, v6);
        }
        else {
            if((arg7 & v4) == v4) {
                v6 = 3;
                goto label_33;
            }

            if((arg7 & v3) == v3) {
                v6 = 5;
                goto label_33;
            }

            v6 = arg7 & 7;
        }

    label_33:
        if(v6 == 1) {
            v6_1 = Layout$Alignment.ALIGN_CENTER;
        }
        else if(v6 != v1) {
            v6_1 = Layout$Alignment.ALIGN_NORMAL;
        }
        else {
            v6_1 = Layout$Alignment.ALIGN_OPPOSITE;
        }

        return v6_1;
    }

    public static StaticLayout a(CharSequence arg8, TextPaint arg9, int arg10, Layout$Alignment arg11, float arg12) {
        StaticLayout v8_1;
        SpannableStringBuilder v1 = new SpannableStringBuilder(arg8);
        v1.setSpan(new a(arg12), 0, v1.length(), 18);
        if(Build$VERSION.SDK_INT >= 23) {
            StaticLayout$Builder v8 = StaticLayout$Builder.obtain(((CharSequence)v1), 0, arg8.length(), arg9, arg10);
            v8.setAlignment(arg11);
            v8_1 = v8.build();
        }
        else {
            v8_1 = new StaticLayout(((CharSequence)v1), arg9, arg10, arg11, 1f, 0f, false);
        }

        return v8_1;
    }

    public static void a(TextPaint arg1, Typeface arg2, int arg3) {
        if(arg3 > 0) {
            arg2 = arg2 == null ? Typeface.defaultFromStyle(arg3) : Typeface.create(arg2, arg3);
            arg1.setTypeface(arg2);
            boolean v0 = false;
            int v2 = arg2 != null ? arg2.getStyle() : 0;
            v2 = (v2 ^ -1) & arg3;
            if((v2 & 1) != 0) {
                v0 = true;
            }

            arg1.setFakeBoldText(v0);
            float v2_1 = (v2 & 2) != 0 ? -0.25f : 0f;
            arg1.setTextSkewX(v2_1);
        }
        else {
            if(arg2 == null) {
                arg2 = Typeface.defaultFromStyle(arg3);
            }

            arg1.setTypeface(arg2);
        }
    }

    public static boolean a(float arg4, float arg5, PointF arg6, float arg7) {
        boolean v4 = Math.pow(((double)(arg4 - arg6.x)), 2) + Math.pow(((double)(arg5 - arg6.y)), 2) < Math.pow(((double)arg7), 2) ? true : false;
        return v4;
    }

    public static boolean a(Rect arg1, int arg2, int arg3, int arg4) {
        boolean v1 = arg3 <= arg1.left + arg2 || arg3 >= arg1.right - arg2 || arg4 <= arg1.top + arg2 || arg4 >= arg1.bottom - arg2 ? false : true;
        return v1;
    }

    public static boolean a(Layout arg6, Resources arg7) {
        boolean v1_1;
        boolean v0 = false;
        if(arg6 != null) {
            int v1 = arg6.getAlignment() == Layout$Alignment.ALIGN_OPPOSITE ? 1 : 0;
            boolean v2 = arg6.isRtlCharAt(0);
            if(v1 != 0 && (v2)) {
                goto label_14;
            }
            else if(v1 != 0 || (v2)) {
            label_18:
                v1_1 = true;
            }
            else {
            label_14:
                if(v2) {
                    goto label_18;
                }
                else {
                    v1_1 = false;
                }
            }

            if(!v1_1 && arg6.getAlignment() == Layout$Alignment.ALIGN_NORMAL && Build$VERSION.SDK_INT >= 17) {
                if(arg7.getConfiguration().getLayoutDirection() == 1) {
                    v0 = true;
                }
                else {
                }

                return v0;
            }

            if(arg6.getAlignment() == Layout$Alignment.ALIGN_OPPOSITE && (v2)) {
                return v0;
            }

            v0 = v1_1;
        }

        return v0;
    }
}

