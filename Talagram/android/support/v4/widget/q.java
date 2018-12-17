package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint$FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build$VERSION;
import android.support.v4.e.c;
import android.support.v4.f.l;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class q {
    class a implements ActionMode$Callback {
        private final ActionMode$Callback a;
        private final TextView b;
        private Class c;
        private Method d;
        private boolean e;
        private boolean f;

        a(ActionMode$Callback arg1, TextView arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.f = false;
        }

        private Intent a() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }

        private Intent a(ResolveInfo arg3, TextView arg4) {
            return this.a().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", this.a(arg4) ^ 1).setClassName(arg3.activityInfo.packageName, arg3.activityInfo.name);
        }

        private boolean a(TextView arg2) {
            boolean v2 = !(arg2 instanceof Editable) || !arg2.onCheckIsTextEditor() || !arg2.isEnabled() ? false : true;
            return v2;
        }

        private List a(Context arg4, PackageManager arg5) {
            ArrayList v0 = new ArrayList();
            if(!(arg4 instanceof Activity)) {
                return ((List)v0);
            }

            Iterator v5 = arg5.queryIntentActivities(this.a(), 0).iterator();
            while(v5.hasNext()) {
                Object v1 = v5.next();
                if(!this.a(((ResolveInfo)v1), arg4)) {
                    continue;
                }

                ((List)v0).add(v1);
            }

            return ((List)v0);
        }

        private boolean a(ResolveInfo arg4, Context arg5) {
            boolean v1 = true;
            if(arg5.getPackageName().equals(arg4.activityInfo.packageName)) {
                return 1;
            }

            if(!arg4.activityInfo.exported) {
                return 0;
            }

            if(arg4.activityInfo.permission != null) {
                if(arg5.checkSelfPermission(arg4.activityInfo.permission) == 0) {
                }
                else {
                    v1 = false;
                }
            }

            return v1;
        }

        private void a(Menu arg9) {
            int v5;
            Context v0 = this.b.getContext();
            PackageManager v1 = v0.getPackageManager();
            if(this.f) {
                goto label_24;
            }

            this.f = true;
            try {
                this.c = Class.forName("com.android.internal.view.menu.MenuBuilder");
                this.d = this.c.getDeclaredMethod("removeItemAt", Integer.TYPE);
                this.e = true;
            }
            catch(NoSuchMethodException ) {
                this.c = null;
                this.d = null;
                this.e = false;
            }

            try {
            label_24:
                Method v2 = !this.e || !this.c.isInstance(arg9) ? arg9.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE) : this.d;
                v5 = arg9.size() - 1;
                while(true) {
                label_39:
                    if(v5 < 0) {
                        goto label_54;
                    }

                    MenuItem v6 = arg9.getItem(v5);
                    if(v6.getIntent() != null && ("android.intent.action.PROCESS_TEXT".equals(v6.getIntent().getAction()))) {
                        v2.invoke(arg9, Integer.valueOf(v5));
                    }

                    break;
                }
            }
            catch(InvocationTargetException ) {
                return;
            }

            --v5;
            goto label_39;
        label_54:
            List v0_1 = this.a(v0, v1);
            int v2_1;
            for(v2_1 = 0; v2_1 < v0_1.size(); ++v2_1) {
                Object v5_1 = v0_1.get(v2_1);
                arg9.add(0, 0, v2_1 + 100, ((ResolveInfo)v5_1).loadLabel(v1)).setIntent(this.a(((ResolveInfo)v5_1), this.b)).setShowAsAction(1);
            }
        }

        public boolean onActionItemClicked(ActionMode arg2, MenuItem arg3) {
            return this.a.onActionItemClicked(arg2, arg3);
        }

        public boolean onCreateActionMode(ActionMode arg2, Menu arg3) {
            return this.a.onCreateActionMode(arg2, arg3);
        }

        public void onDestroyActionMode(ActionMode arg2) {
            this.a.onDestroyActionMode(arg2);
        }

        public boolean onPrepareActionMode(ActionMode arg2, Menu arg3) {
            this.a(arg3);
            return this.a.onPrepareActionMode(arg2, arg3);
        }
    }

    private static Field a;
    private static boolean b;
    private static Field c;
    private static boolean d;

    public static void a(TextView arg2, Drawable arg3, Drawable arg4, Drawable arg5, Drawable arg6) {
        if(Build$VERSION.SDK_INT >= 18) {
            arg2.setCompoundDrawablesRelative(arg3, arg4, arg5, arg6);
        }
        else if(Build$VERSION.SDK_INT >= 17) {
            int v1 = 1;
            if(arg2.getLayoutDirection() == 1) {
            }
            else {
                v1 = 0;
            }

            Drawable v0 = v1 != 0 ? arg5 : arg3;
            if(v1 != 0) {
            }
            else {
                arg3 = arg5;
            }

            arg2.setCompoundDrawables(v0, arg4, arg3, arg6);
        }
        else {
            arg2.setCompoundDrawables(arg3, arg4, arg5, arg6);
        }
    }

    public static void a(TextView arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg2.setTextAppearance(arg3);
        }
        else {
            arg2.setTextAppearance(arg2.getContext(), arg3);
        }
    }

    public static int a(TextView arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.getMaxLines();
        }

        if(!q.d) {
            q.c = q.a("mMaxMode");
            q.d = true;
        }

        if(q.c != null && q.a(q.c, arg2) == 1) {
            if(!q.b) {
                q.a = q.a("mMaximum");
                q.b = true;
            }

            if(q.a == null) {
                return -1;
            }

            return q.a(q.a, arg2);
        }

        return -1;
    }

    private static int a(TextDirectionHeuristic arg2) {
        if(arg2 == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
            return 1;
        }

        if(arg2 == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 1;
        }

        if(arg2 == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }

        if(arg2 == TextDirectionHeuristics.LTR) {
            return 3;
        }

        if(arg2 == TextDirectionHeuristics.RTL) {
            return 4;
        }

        if(arg2 == TextDirectionHeuristics.LOCALE) {
            return 5;
        }

        if(arg2 == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 6;
        }

        if(arg2 == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
            return 7;
        }

        return 1;
    }

    private static Field a(String arg4) {
        Field v0;
        Field v1;
        try {
            v1 = TextView.class.getDeclaredField(arg4);
            v0 = null;
        }
        catch(NoSuchFieldException ) {
            v1 = v0;
            goto label_7;
        }

        try {
            v1.setAccessible(true);
        }
        catch(NoSuchFieldException ) {
        label_7:
            Log.e("TextViewCompat", "Could not retrieve " + arg4 + " field.");
        }

        return v1;
    }

    private static int a(Field arg2, TextView arg3) {
        try {
            return arg2.getInt(arg3);
        }
        catch(IllegalAccessException ) {
            Log.d("TextViewCompat", "Could not retrieve value of " + arg2.getName() + " field.");
            return -1;
        }
    }

    public static ActionMode$Callback a(TextView arg2, ActionMode$Callback arg3) {
        if(Build$VERSION.SDK_INT >= 26 && Build$VERSION.SDK_INT <= 27) {
            if((arg3 instanceof a)) {
            }
            else {
                return new a(arg3, arg2);
            }
        }

        return arg3;
    }

    public static void a(TextView arg2, android.support.v4.e.c$a arg3) {
        if(Build$VERSION.SDK_INT >= 18) {
            arg2.setTextDirection(q.a(arg3.b()));
        }

        if(Build$VERSION.SDK_INT < 23) {
            float v0 = arg3.a().getTextScaleX();
            arg2.getPaint().set(arg3.a());
            if(v0 == arg2.getTextScaleX()) {
                arg2.setTextScaleX(v0 / 2f + 1f);
            }

            arg2.setTextScaleX(v0);
        }
        else {
            arg2.getPaint().set(arg3.a());
            arg2.setBreakStrategy(arg3.c());
            arg2.setHyphenationFrequency(arg3.d());
        }
    }

    public static void a(TextView arg2, c arg3) {
        PrecomputedText v3;
        if(Build$VERSION.SDK_INT >= 28) {
            v3 = arg3.a();
        }
        else if(q.e(arg2).equals(arg3.b())) {
        }
        else {
            goto label_12;
        }

        arg2.setText(((CharSequence)v3));
        return;
    label_12:
        throw new IllegalArgumentException("Given text can not be applied to TextView.");
    }

    public static Drawable[] b(TextView arg4) {
        if(Build$VERSION.SDK_INT >= 18) {
            return arg4.getCompoundDrawablesRelative();
        }

        if(Build$VERSION.SDK_INT >= 17) {
            int v1 = 1;
            if(arg4.getLayoutDirection() == 1) {
            }
            else {
                v1 = 0;
            }

            Drawable[] v4 = arg4.getCompoundDrawables();
            if(v1 != 0) {
                Drawable v1_1 = v4[2];
                Drawable v3 = v4[0];
                v4[0] = v1_1;
                v4[2] = v3;
            }

            return v4;
        }

        return arg4.getCompoundDrawables();
    }

    public static void b(TextView arg3, int arg4) {
        l.a(arg4);
        if(Build$VERSION.SDK_INT >= 28) {
            arg3.setFirstBaselineToTopHeight(arg4);
            return;
        }

        Paint$FontMetricsInt v0 = arg3.getPaint().getFontMetricsInt();
        int v0_1 = Build$VERSION.SDK_INT < 16 || (arg3.getIncludeFontPadding()) ? v0.top : v0.ascent;
        if(arg4 > Math.abs(v0_1)) {
            arg3.setPadding(arg3.getPaddingLeft(), arg4 - -v0_1, arg3.getPaddingRight(), arg3.getPaddingBottom());
        }
    }

    public static int c(TextView arg1) {
        return arg1.getPaddingTop() - arg1.getPaint().getFontMetricsInt().top;
    }

    public static void c(TextView arg3, int arg4) {
        l.a(arg4);
        Paint$FontMetricsInt v0 = arg3.getPaint().getFontMetricsInt();
        int v0_1 = Build$VERSION.SDK_INT < 16 || (arg3.getIncludeFontPadding()) ? v0.bottom : v0.descent;
        if(arg4 > Math.abs(v0_1)) {
            arg3.setPadding(arg3.getPaddingLeft(), arg3.getPaddingTop(), arg3.getPaddingRight(), arg4 - v0_1);
        }
    }

    public static int d(TextView arg1) {
        return arg1.getPaddingBottom() + arg1.getPaint().getFontMetricsInt().bottom;
    }

    public static void d(TextView arg2, int arg3) {
        l.a(arg3);
        int v0 = arg2.getPaint().getFontMetricsInt(null);
        if(arg3 != v0) {
            arg2.setLineSpacing(((float)(arg3 - v0)), 1f);
        }
    }

    public static android.support.v4.e.c$a e(TextView arg3) {
        if(Build$VERSION.SDK_INT >= 28) {
            return new android.support.v4.e.c$a(arg3.getTextMetricsParams());
        }

        android.support.v4.e.c$a$a v0 = new android.support.v4.e.c$a$a(new TextPaint(arg3.getPaint()));
        if(Build$VERSION.SDK_INT >= 23) {
            v0.a(arg3.getBreakStrategy());
            v0.b(arg3.getHyphenationFrequency());
        }

        if(Build$VERSION.SDK_INT >= 18) {
            v0.a(q.f(arg3));
        }

        return v0.a();
    }

    private static TextDirectionHeuristic f(TextView arg4) {
        if((arg4.getTransformationMethod() instanceof PasswordTransformationMethod)) {
            return TextDirectionHeuristics.LTR;
        }

        int v2 = 0;
        if(Build$VERSION.SDK_INT >= 28 && (arg4.getInputType() & 15) == 3) {
            int v4 = Character.getDirectionality(DecimalFormatSymbols.getInstance(arg4.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            if(v4 != 1) {
                if(v4 == 2) {
                }
                else {
                    return TextDirectionHeuristics.LTR;
                }
            }

            return TextDirectionHeuristics.RTL;
        }

        if(arg4.getLayoutDirection() == 1) {
            v2 = 1;
        }

        switch(arg4.getTextDirection()) {
            case 2: {
                goto label_46;
            }
            case 3: {
                goto label_44;
            }
            case 4: {
                goto label_42;
            }
            case 5: {
                goto label_40;
            }
            case 6: {
                goto label_38;
            }
            case 7: {
                goto label_36;
            }
        }

        TextDirectionHeuristic v4_1 = v2 != 0 ? TextDirectionHeuristics.FIRSTSTRONG_RTL : TextDirectionHeuristics.FIRSTSTRONG_LTR;
        return v4_1;
    label_36:
        return TextDirectionHeuristics.FIRSTSTRONG_RTL;
    label_38:
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    label_40:
        return TextDirectionHeuristics.LOCALE;
    label_42:
        return TextDirectionHeuristics.RTL;
    label_44:
        return TextDirectionHeuristics.LTR;
    label_46:
        return TextDirectionHeuristics.ANYRTL_LTR;
    }
}

