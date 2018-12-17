package org.telegram.ui.Components;

import android.os.Build$VERSION;
import android.text.Layout$Alignment;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout$Builder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import java.lang.reflect.Constructor;
import org.telegram.messenger.FileLog;

public class StaticLayoutEx {
    private static final String TEXT_DIRS_CLASS = "android.text.TextDirectionHeuristics";
    private static final String TEXT_DIR_CLASS = "android.text.TextDirectionHeuristic";
    private static final String TEXT_DIR_FIRSTSTRONG_LTR = "FIRSTSTRONG_LTR";
    private static boolean initialized;
    private static Constructor sConstructor;
    private static Object[] sConstructorArgs;
    private static Object sTextDirection;

    public StaticLayoutEx() {
        super();
    }

    public static StaticLayout createStaticLayout(CharSequence arg17, int arg18, int arg19, TextPaint arg20, int arg21, Layout$Alignment arg22, float arg23, float arg24, boolean arg25, TextUtils$TruncateAt arg26, int arg27, int arg28) {
        CharSequence v0 = arg17;
        TextPaint v9 = arg20;
        int v1 = arg27;
        int v10 = arg28;
        TextUtils$TruncateAt v11 = null;
        if(v10 != 1) {
            goto label_25;
        }

        float v1_1 = ((float)v1);
        try {
            CharSequence v1_2 = TextUtils.ellipsize(v0, v9, v1_1, TextUtils$TruncateAt.END);
            return new StaticLayout(v1_2, 0, v1_2.length(), arg20, arg21, arg22, arg23, arg24, arg25);
        label_25:
            StaticLayout v1_3 = Build$VERSION.SDK_INT >= 23 ? StaticLayout$Builder.obtain(v0, 0, arg17.length(), v9, arg21).setAlignment(arg22).setLineSpacing(arg24, arg23).setIncludePad(arg25).setEllipsize(v11).setEllipsizedWidth(v1).setBreakStrategy(1).setHyphenationFrequency(0).build() : new StaticLayout(arg17, arg20, arg21, arg22, arg23, arg24, arg25);
            if(v1_3.getLineCount() <= v10) {
                return v1_3;
            }

            int v2 = v10 - 1;
            float v3 = v1_3.getLineLeft(v2);
            if(v3 == 0f) {
                v3 = v1_3.getLineWidth(v2);
            }

            v1 = v1_3.getOffsetForHorizontal(v2, v3);
            SpannableStringBuilder v2_1 = new SpannableStringBuilder(v0.subSequence(0, Math.max(0, v1 - 1)));
            v2_1.append("…");
            StaticLayout v0_2 = new StaticLayout(v2_1, arg20, arg21, arg22, arg23, arg24, arg25);
            return v0_2;
        label_24:
        }
        catch(Exception v0_1) {
            goto label_24;
        }

        FileLog.e(((Throwable)v0_1));
        return ((StaticLayout)v11);
    }

    public static StaticLayout createStaticLayout(CharSequence arg12, TextPaint arg13, int arg14, Layout$Alignment arg15, float arg16, float arg17, boolean arg18, TextUtils$TruncateAt arg19, int arg20, int arg21) {
        return StaticLayoutEx.createStaticLayout(arg12, 0, arg12.length(), arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21);
    }

    public static void init() {
        Class v0_1;
        if(StaticLayoutEx.initialized) {
            return;
        }

        try {
            if(Build$VERSION.SDK_INT >= 18) {
                v0_1 = TextDirectionHeuristic.class;
                StaticLayoutEx.sTextDirection = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }
            else {
                ClassLoader v0_2 = StaticLayoutEx.class.getClassLoader();
                Class v1 = v0_2.loadClass("android.text.TextDirectionHeuristic");
                v0_1 = v0_2.loadClass("android.text.TextDirectionHeuristics");
                StaticLayoutEx.sTextDirection = v0_1.getField("FIRSTSTRONG_LTR").get(v0_1);
                v0_1 = v1;
            }

            Class[] v1_1 = new Class[]{CharSequence.class, Integer.TYPE, Integer.TYPE, TextPaint.class, Integer.TYPE, Layout$Alignment.class, v0_1, Float.TYPE, Float.TYPE, Boolean.TYPE, TextUtils$TruncateAt.class, Integer.TYPE, Integer.TYPE};
            StaticLayoutEx.sConstructor = StaticLayout.class.getDeclaredConstructor(v1_1);
            StaticLayoutEx.sConstructor.setAccessible(true);
            StaticLayoutEx.sConstructorArgs = new Object[v1_1.length];
            StaticLayoutEx.initialized = true;
        }
        catch(Throwable v0) {
            FileLog.e(v0);
        }
    }
}

