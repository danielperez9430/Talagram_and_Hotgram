package android.support.c.a;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build$VERSION;
import android.support.v4.content.a.g;
import android.support.v4.graphics.b$b;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class e {
    class a implements TypeEvaluator {
        private b[] a;

        a() {
            super();
        }

        public b[] a(float arg5, b[] arg6, b[] arg7) {
            if(android.support.v4.graphics.b.a(arg6, arg7)) {
                if(this.a == null || !android.support.v4.graphics.b.a(this.a, arg6)) {
                    this.a = android.support.v4.graphics.b.a(arg6);
                }

                int v0;
                for(v0 = 0; v0 < arg6.length; ++v0) {
                    this.a[v0].a(arg6[v0], arg7[v0], arg5);
                }

                return this.a;
            }

            throw new IllegalArgumentException("Can\'t interpolate between two incompatible pathData");
        }

        public Object evaluate(float arg1, Object arg2, Object arg3) {
            return this.a(arg1, ((b[])arg2), ((b[])arg3));
        }
    }

    private static int a(Resources arg1, Resources$Theme arg2, AttributeSet arg3, XmlPullParser arg4) {
        TypedArray v1 = g.a(arg1, arg2, arg3, android.support.c.a.a.j);
        int v3 = 0;
        TypedValue v2 = g.b(v1, arg4, "value", 0);
        int v4 = v2 != null ? 1 : 0;
        if(v4 != 0 && (e.a(v2.type))) {
            v3 = 3;
        }

        v1.recycle();
        return v3;
    }

    private static boolean a(int arg1) {
        boolean v1 = arg1 < 28 || arg1 > 31 ? false : true;
        return v1;
    }

    private static int a(TypedArray arg3, int arg4, int arg5) {
        TypedValue v4 = arg3.peekValue(arg4);
        int v0 = 1;
        int v1 = 0;
        int v2 = v4 != null ? 1 : 0;
        arg4 = v2 != 0 ? v4.type : 0;
        TypedValue v3 = arg3.peekValue(arg5);
        if(v3 != null) {
        }
        else {
            v0 = 0;
        }

        int v3_1 = v0 != 0 ? v3.type : 0;
        if(v2 != 0 && (e.a(arg4)) || v0 != 0 && (e.a(v3_1))) {
            v1 = 3;
        }

        return v1;
    }

    public static Animator a(Context arg2, int arg3) {
        Animator v2 = Build$VERSION.SDK_INT >= 24 ? AnimatorInflater.loadAnimator(arg2, arg3) : e.a(arg2, arg2.getResources(), arg2.getTheme(), arg3);
        return v2;
    }

    public static Animator a(Context arg1, Resources arg2, Resources$Theme arg3, int arg4) {
        return e.a(arg1, arg2, arg3, arg4, 1f);
    }

    public static Animator a(Context arg2, Resources arg3, Resources$Theme arg4, int arg5, float arg6) {
        Resources$NotFoundException v3;
        StringBuilder v4;
        Animator v2_3;
        XmlResourceParser v1;
        XmlResourceParser v0 = null;
        try {
            v1 = arg3.getAnimation(arg5);
            goto label_2;
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            goto label_18;
            try {
            label_2:
                v2_3 = e.a(arg2, arg3, arg4, ((XmlPullParser)v1), arg6);
                if(v1 == null) {
                    return v2_3;
                }

                goto label_4;
            }
            catch(Throwable v2) {
                v0 = v1;
            }
            catch(IOException v2_1) {
                v0 = v1;
                try {
                label_18:
                    v4 = new StringBuilder();
                    v4.append("Can\'t load animation resource ID #0x");
                    v4.append(Integer.toHexString(arg5));
                    v3 = new Resources$NotFoundException(v4.toString());
                    v3.initCause(((Throwable)v2_1));
                    throw v3;
                }
                catch(Throwable v2) {
                label_16:
                }
            }
            catch(XmlPullParserException v2_2) {
                v0 = v1;
                try {
                label_30:
                    v4 = new StringBuilder();
                    v4.append("Can\'t load animation resource ID #0x");
                    v4.append(Integer.toHexString(arg5));
                    v3 = new Resources$NotFoundException(v4.toString());
                    v3.initCause(((Throwable)v2_2));
                    throw v3;
                }
                catch(Throwable v2) {
                    goto label_16;
                }
            }
        }
        catch(XmlPullParserException v2_2) {
            goto label_30;
        }

        if(v0 != null) {
            v0.close();
        }

        throw v2;
    label_4:
        v1.close();
        return v2_3;
    }

    private static Animator a(Context arg8, Resources arg9, Resources$Theme arg10, XmlPullParser arg11, float arg12) {
        return e.a(arg8, arg9, arg10, arg11, Xml.asAttributeSet(arg11), null, 0, arg12);
    }

    private static Animator a(Context arg18, Resources arg19, Resources$Theme arg20, XmlPullParser arg21, AttributeSet arg22, AnimatorSet arg23, int arg24, float arg25) {
        ValueAnimator v0_1;
        int v14;
        Resources v8 = arg19;
        Resources$Theme v9 = arg20;
        XmlPullParser v10 = arg21;
        AnimatorSet v11 = arg23;
        int v12 = arg21.getDepth();
        ObjectAnimator v0 = null;
        ArrayList v13 = ((ArrayList)v0);
        while(true) {
            int v1 = arg21.next();
            v14 = 0;
            if((v1 != 3 || arg21.getDepth() > v12) && v1 != 1) {
                if(v1 != 2) {
                }
                else {
                    String v1_1 = arg21.getName();
                    if(v1_1.equals("objectAnimator")) {
                        v0 = e.a(arg18, arg19, arg20, arg22, arg25, arg21);
                    }
                    else if(v1_1.equals("animator")) {
                        v0_1 = e.a(arg18, arg19, arg20, arg22, null, arg25, arg21);
                    }
                    else if(v1_1.equals("set")) {
                        AnimatorSet v15 = new AnimatorSet();
                        TypedArray v6 = g.a(v8, v9, arg22, android.support.c.a.a.h);
                        e.a(arg18, arg19, arg20, arg21, arg22, v15, g.a(v6, v10, "ordering", 0, 0), arg25);
                        v6.recycle();
                        AnimatorSet v0_2 = v15;
                    }
                    else if(v1_1.equals("propertyValuesHolder")) {
                        PropertyValuesHolder[] v1_2 = e.a(arg18, v8, v9, v10, Xml.asAttributeSet(arg21));
                        if(v1_2 != null && (((ObjectAnimator)v0_1)) != null && (((((ObjectAnimator)v0_1)) instanceof ValueAnimator))) {
                            ((ObjectAnimator)v0_1).setValues(v1_2);
                        }

                        v14 = 1;
                    }
                    else {
                        break;
                    }

                    if(v11 == null) {
                        continue;
                    }

                    if(v14 != 0) {
                        continue;
                    }

                    if(v13 == null) {
                        v13 = new ArrayList();
                    }

                    v13.add(v0_1);
                }

                continue;
            }

            goto label_97;
        }

        StringBuilder v1_3 = new StringBuilder();
        v1_3.append("Unknown animator name: ");
        v1_3.append(arg21.getName());
        throw new RuntimeException(v1_3.toString());
    label_97:
        if(v11 != null && v13 != null) {
            Animator[] v1_4 = new Animator[v13.size()];
            Iterator v2 = v13.iterator();
            while(v2.hasNext()) {
                v1_4[v14] = v2.next();
                ++v14;
            }

            if(arg24 == 0) {
                v11.playTogether(v1_4);
            }
            else {
                v11.playSequentially(v1_4);
            }
        }

        return ((Animator)v0_1);
    }

    private static ObjectAnimator a(Context arg8, Resources arg9, Resources$Theme arg10, AttributeSet arg11, float arg12, XmlPullParser arg13) {
        ObjectAnimator v7 = new ObjectAnimator();
        e.a(arg8, arg9, arg10, arg11, v7, arg12, arg13);
        return v7;
    }

    private static ValueAnimator a(Context arg2, Resources arg3, Resources$Theme arg4, AttributeSet arg5, ValueAnimator arg6, float arg7, XmlPullParser arg8) {
        TypedArray v0 = g.a(arg3, arg4, arg5, android.support.c.a.a.g);
        TypedArray v3 = g.a(arg3, arg4, arg5, android.support.c.a.a.k);
        if(arg6 == null) {
            arg6 = new ValueAnimator();
        }

        e.a(arg6, v0, v3, arg7, arg8);
        int v4 = g.c(v0, arg8, "interpolator", 0, 0);
        if(v4 > 0) {
            arg6.setInterpolator(d.a(arg2, v4));
        }

        v0.recycle();
        if(v3 != null) {
            v3.recycle();
        }

        return arg6;
    }

    private static PropertyValuesHolder[] a(Context arg17, Resources arg18, Resources$Theme arg19, XmlPullParser arg20, AttributeSet arg21) {
        int v9;
        int v0;
        XmlPullParser v6 = arg20;
        PropertyValuesHolder[] v7 = null;
        ArrayList v8 = ((ArrayList)v7);
        while(true) {
            v0 = arg20.getEventType();
            v9 = 0;
            int v1 = 3;
            if(v0 != v1 && v0 != 1) {
                int v2 = 2;
                if(v0 == v2 && (arg20.getName().equals("propertyValuesHolder"))) {
                    TypedArray v14 = g.a(arg18, arg19, arg21, android.support.c.a.a.i);
                    String v15 = g.a(v14, v6, "propertyName", v1);
                    int v5 = g.a(v14, v6, "valueType", v2, 4);
                    int v16 = v5;
                    PropertyValuesHolder v0_1 = e.a(arg17, arg18, arg19, arg20, v15, v5);
                    if(v0_1 == null) {
                        v0_1 = e.a(v14, v16, 0, 1, v15);
                    }

                    if(v0_1 != null) {
                        if(v8 == null) {
                            v8 = new ArrayList();
                        }

                        v8.add(v0_1);
                    }

                    v14.recycle();
                }

                arg20.next();
                continue;
            }

            break;
        }

        if(v8 != null) {
            v0 = v8.size();
            v7 = new PropertyValuesHolder[v0];
            while(v9 < v0) {
                v7[v9] = v8.get(v9);
                ++v9;
            }
        }

        return v7;
    }

    private static Keyframe a(Keyframe arg2, float arg3) {
        if(arg2.getType() == Float.TYPE) {
            arg2 = Keyframe.ofFloat(arg3);
        }
        else if(arg2.getType() == Integer.TYPE) {
            arg2 = Keyframe.ofInt(arg3);
        }
        else {
            arg2 = Keyframe.ofObject(arg3);
        }

        return arg2;
    }

    private static Keyframe a(Context arg5, Resources arg6, Resources$Theme arg7, AttributeSet arg8, int arg9, XmlPullParser arg10) {
        Keyframe v7_1;
        TypedArray v6 = g.a(arg6, arg7, arg8, android.support.c.a.a.j);
        int v8 = 3;
        float v7 = g.a(v6, arg10, "fraction", v8, -1f);
        TypedValue v0 = g.b(v6, arg10, "value", 0);
        int v3 = v0 != null ? 1 : 0;
        if(arg9 == 4) {
            if(v3 != 0 && (e.a(v0.type))) {
                arg9 = 3;
                goto label_23;
            }

            arg9 = 0;
        }

    label_23:
        if(v3 != 0) {
            if(arg9 != v8) {
                switch(arg9) {
                    case 0: {
                        goto label_28;
                    }
                    case 1: {
                        goto label_33;
                    }
                }

                v7_1 = null;
                goto label_41;
            label_28:
                v7_1 = Keyframe.ofFloat(v7, g.a(v6, arg10, "value", 0, 0f));
                goto label_41;
            }

        label_33:
            v7_1 = Keyframe.ofInt(v7, g.a(v6, arg10, "value", 0, 0));
        }
        else {
            if(arg9 == 0) {
                v7_1 = Keyframe.ofFloat(v7);
                goto label_41;
            }

            v7_1 = Keyframe.ofInt(v7);
        }

    label_41:
        v8 = g.c(v6, arg10, "interpolator", 1, 0);
        if(v8 > 0) {
            v7_1.setInterpolator(d.a(arg5, v8));
        }

        v6.recycle();
        return v7_1;
    }

    private static PropertyValuesHolder a(Context arg9, Resources arg10, Resources$Theme arg11, XmlPullParser arg12, String arg13, int arg14) {
        PropertyValuesHolder v0 = null;
        int v1 = arg14;
        ArrayList v14 = ((ArrayList)v0);
        while(true) {
            int v2 = arg12.next();
            int v3 = 3;
            if(v2 != v3 && v2 != 1) {
                if(!arg12.getName().equals("keyframe")) {
                    continue;
                }

                if(v1 == 4) {
                    v1 = e.a(arg10, arg11, Xml.asAttributeSet(arg12), arg12);
                }

                Keyframe v2_1 = e.a(arg9, arg10, arg11, Xml.asAttributeSet(arg12), v1, arg12);
                if(v2_1 != null) {
                    if(v14 == null) {
                        v14 = new ArrayList();
                    }

                    v14.add(v2_1);
                }

                arg12.next();
                continue;
            }

            break;
        }

        if(v14 != null) {
            int v9 = v14.size();
            if(v9 > 0) {
                int v10 = 0;
                Object v11 = v14.get(0);
                Object v12 = v14.get(v9 - 1);
                float v0_1 = ((Keyframe)v12).getFraction();
                float v2_2 = 1f;
                if(Float.compare(v0_1, v2_2) < 0) {
                    if(v0_1 < 0f) {
                        ((Keyframe)v12).setFraction(v2_2);
                    }
                    else {
                        v14.add(v14.size(), e.a(((Keyframe)v12), v2_2));
                        ++v9;
                    }
                }

                float v12_1 = ((Keyframe)v11).getFraction();
                if(v12_1 != 0f) {
                    if(v12_1 < 0f) {
                        ((Keyframe)v11).setFraction(0f);
                    }
                    else {
                        v14.add(0, e.a(((Keyframe)v11), 0f));
                        ++v9;
                    }
                }

                Keyframe[] v11_1 = new Keyframe[v9];
                v14.toArray(((Object[])v11_1));
                while(v10 < v9) {
                    Keyframe v12_2 = v11_1[v10];
                    if(v12_2.getFraction() < 0f) {
                        if(v10 == 0) {
                            v12_2.setFraction(0f);
                        }
                        else {
                            arg14 = v9 - 1;
                            if(v10 == arg14) {
                                v12_2.setFraction(v2_2);
                            }
                            else {
                                int v12_3 = v10 + 1;
                                int v0_2 = v10;
                                while(v12_3 < arg14) {
                                    if(v11_1[v12_3].getFraction() >= 0f) {
                                    }
                                    else {
                                        v0_2 = v12_3;
                                        ++v12_3;
                                        continue;
                                    }

                                    break;
                                }

                                e.a(v11_1, v11_1[v0_2 + 1].getFraction() - v11_1[v10 - 1].getFraction(), v10, v0_2);
                            }
                        }
                    }

                    ++v10;
                }

                v0 = PropertyValuesHolder.ofKeyframe(arg13, v11_1);
                if(v1 != v3) {
                    return v0;
                }

                v0.setEvaluator(f.a());
            }
        }

        return v0;
    }

    private static void a(Keyframe[] arg2, float arg3, int arg4, int arg5) {
        arg3 /= ((float)(arg5 - arg4 + 2));
        while(arg4 <= arg5) {
            arg2[arg4].setFraction(arg2[arg4 - 1].getFraction() + arg3);
            ++arg4;
        }
    }

    private static PropertyValuesHolder a(TypedArray arg11, int arg12, int arg13, int arg14, String arg15) {
        int v11_4;
        PropertyValuesHolder v11_3;
        float v11_2;
        Object[] v11_1;
        TypedValue v0 = arg11.peekValue(arg13);
        int v3 = v0 != null ? 1 : 0;
        int v0_1 = v3 != 0 ? v0.type : 0;
        TypedValue v4 = arg11.peekValue(arg14);
        int v5 = v4 != null ? 1 : 0;
        int v4_1 = v5 != 0 ? v4.type : 0;
        int v7 = 3;
        if(arg12 == 4) {
            if(v3 != 0 && (e.a(v0_1)) || v5 != 0 && (e.a(v4_1))) {
                arg12 = 3;
                goto label_32;
            }

            arg12 = 0;
        }

    label_32:
        int v6 = arg12 == 0 ? 1 : 0;
        PropertyValuesHolder v8 = null;
        int v9 = 2;
        if(arg12 == v9) {
            String v12 = arg11.getString(arg13);
            String v11 = arg11.getString(arg14);
            b[] v13 = android.support.v4.graphics.b.b(v12);
            b[] v14 = android.support.v4.graphics.b.b(v11);
            if(v13 == null && v14 == null) {
                return v8;
            }

            if(v13 != null) {
                a v0_2 = new a();
                if(v14 == null) {
                    v11_1 = new Object[]{v13};
                }
                else if(android.support.v4.graphics.b.a(v13, v14)) {
                    v11_1 = new Object[v9];
                    v11_1[0] = v13;
                    v11_1[1] = v14;
                }
                else {
                    StringBuilder v14_1 = new StringBuilder();
                    v14_1.append(" Can\'t morph from ");
                    v14_1.append(v12);
                    v14_1.append(" to ");
                    v14_1.append(v11);
                    throw new InflateException(v14_1.toString());
                }

                return PropertyValuesHolder.ofObject(arg15, ((TypeEvaluator)v0_2), v11_1);
            }

            if(v14 == null) {
                return v8;
            }

            v8 = PropertyValuesHolder.ofObject(arg15, new a(), new Object[]{v14});
        }
        else {
            f v12_1 = arg12 == v7 ? f.a() : ((f)v8);
            v7 = 5;
            if(v6 != 0) {
                if(v3 != 0) {
                    float v13_1 = v0_1 == v7 ? arg11.getDimension(arg13, 0f) : arg11.getFloat(arg13, 0f);
                    if(v5 != 0) {
                        v11_2 = v4_1 == v7 ? arg11.getDimension(arg14, 0f) : arg11.getFloat(arg14, 0f);
                        float[] v14_2 = new float[v9];
                        v14_2[0] = v13_1;
                        v14_2[1] = v11_2;
                        v11_3 = PropertyValuesHolder.ofFloat(arg15, v14_2);
                        goto label_112;
                    }

                    v11_3 = PropertyValuesHolder.ofFloat(arg15, new float[]{v13_1});
                }
                else {
                    v11_2 = v4_1 == v7 ? arg11.getDimension(arg14, 0f) : arg11.getFloat(arg14, 0f);
                    v11_3 = PropertyValuesHolder.ofFloat(arg15, new float[]{v11_2});
                }

            label_112:
                v8 = v11_3;
            }
            else {
                if(v3 != 0) {
                    if(v0_1 == v7) {
                        arg13 = ((int)arg11.getDimension(arg13, 0f));
                    }
                    else if(e.a(v0_1)) {
                        arg13 = arg11.getColor(arg13, 0);
                    }
                    else {
                        arg13 = arg11.getInt(arg13, 0);
                    }

                    if(v5 != 0) {
                        if(v4_1 == v7) {
                            v11_4 = ((int)arg11.getDimension(arg14, 0f));
                        }
                        else if(e.a(v4_1)) {
                            v11_4 = arg11.getColor(arg14, 0);
                        }
                        else {
                            v11_4 = arg11.getInt(arg14, 0);
                        }

                        int[] v14_3 = new int[v9];
                        v14_3[0] = arg13;
                        v14_3[1] = v11_4;
                        v8 = PropertyValuesHolder.ofInt(arg15, v14_3);
                        goto label_156;
                    }

                    v8 = PropertyValuesHolder.ofInt(arg15, new int[]{arg13});
                    goto label_156;
                }

                if(v5 == 0) {
                    goto label_156;
                }

                if(v4_1 == v7) {
                    v11_4 = ((int)arg11.getDimension(arg14, 0f));
                }
                else if(e.a(v4_1)) {
                    v11_4 = arg11.getColor(arg14, 0);
                }
                else {
                    v11_4 = arg11.getInt(arg14, 0);
                }

                v8 = PropertyValuesHolder.ofInt(arg15, new int[]{v11_4});
            }

        label_156:
            if(v8 == null) {
                return v8;
            }

            if(v12_1 == null) {
                return v8;
            }

            v8.setEvaluator(((TypeEvaluator)v12_1));
        }

        return v8;
    }

    private static void a(ValueAnimator arg11, TypedArray arg12, TypedArray arg13, float arg14, XmlPullParser arg15) {
        long v2 = ((long)g.a(arg12, arg15, "duration", 1, 300));
        long v5 = ((long)g.a(arg12, arg15, "startOffset", 2, 0));
        int v7 = 4;
        int v0 = g.a(arg12, arg15, "valueType", 7, v7);
        if((g.a(arg15, "valueFrom")) && (g.a(arg15, "valueTo"))) {
            int v8 = 6;
            int v9 = 5;
            if(v0 == v7) {
                v0 = e.a(arg12, v9, v8);
            }

            PropertyValuesHolder v8_1 = e.a(arg12, v0, v9, v8, "");
            if(v8_1 == null) {
                goto label_30;
            }

            arg11.setValues(new PropertyValuesHolder[]{v8_1});
        }

    label_30:
        arg11.setDuration(v2);
        arg11.setStartDelay(v5);
        arg11.setRepeatCount(g.a(arg12, arg15, "repeatCount", 3, 0));
        arg11.setRepeatMode(g.a(arg12, arg15, "repeatMode", v7, 1));
        if(arg13 != null) {
            e.a(arg11, arg13, v0, arg14, arg15);
        }
    }

    private static void a(ValueAnimator arg5, TypedArray arg6, int arg7, float arg8, XmlPullParser arg9) {
        String v0 = g.a(arg6, arg9, "pathData", 1);
        if(v0 != null) {
            String v1 = g.a(arg6, arg9, "propertyXName", 2);
            String v9 = g.a(arg6, arg9, "propertyYName", 3);
            if(v1 == null) {
                if(v9 != null) {
                }
                else {
                    StringBuilder v7 = new StringBuilder();
                    v7.append(arg6.getPositionDescription());
                    v7.append(" propertyXName or propertyYName is needed for PathData");
                    throw new InflateException(v7.toString());
                }
            }

            e.a(android.support.v4.graphics.b.a(v0), ((ObjectAnimator)arg5), arg8 * 0.5f, v1, v9);
        }
        else {
            ((ObjectAnimator)arg5).setPropertyName(g.a(arg6, arg9, "propertyName", 0));
        }
    }

    private static void a(Path arg17, ObjectAnimator arg18, float arg19, String arg20, String arg21) {
        PropertyValuesHolder v12_1;
        float[] v12;
        Path v0 = arg17;
        ObjectAnimator v1 = arg18;
        String v2 = arg20;
        String v3 = arg21;
        PathMeasure v4 = new PathMeasure(v0, false);
        ArrayList v6 = new ArrayList();
        v6.add(Float.valueOf(0f));
        float v8 = 0f;
        do {
            v8 += v4.getLength();
            v6.add(Float.valueOf(v8));
        }
        while(v4.nextContour());

        v4 = new PathMeasure(v0, false);
        int v0_1 = Math.min(100, (((int)(v8 / arg19))) + 1);
        float[] v9 = new float[v0_1];
        float[] v11 = new float[v0_1];
        float[] v13 = new float[2];
        v8 /= ((float)(v0_1 - 1));
        int v7 = 0;
        float v14 = 0f;
        int v15 = 0;
        while(true) {
            v12 = null;
            if(v7 >= v0_1) {
                break;
            }

            v4.getPosTan(v14 - v6.get(v15).floatValue(), v13, v12);
            v9[v7] = v13[0];
            v11[v7] = v13[1];
            v14 += v8;
            int v10 = v15 + 1;
            if(v10 < v6.size() && v14 > v6.get(v10).floatValue()) {
                v4.nextContour();
                v15 = v10;
            }

            ++v7;
        }

        PropertyValuesHolder v0_2 = v2 != null ? PropertyValuesHolder.ofFloat(v2, v9) : ((PropertyValuesHolder)v12);
        if(v3 != null) {
            v12_1 = PropertyValuesHolder.ofFloat(v3, v11);
        }

        if(v0_2 == null) {
            v1.setValues(new PropertyValuesHolder[]{v12_1});
        }
        else if(v12_1 == null) {
            v1.setValues(new PropertyValuesHolder[]{v0_2});
        }
        else {
            v1.setValues(new PropertyValuesHolder[]{v0_2, v12_1});
        }
    }
}

