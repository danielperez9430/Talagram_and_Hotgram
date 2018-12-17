package android.support.v4.content.a;

import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader$TileMode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

final class d {
    final class a {
        final int[] a;
        final float[] b;

        a(int arg4, int arg5, int arg6) {
            super();
            this.a = new int[]{arg4, arg5, arg6};
            this.b = new float[]{0f, 0.5f, 1f};
        }

        a(int arg4, int arg5) {
            super();
            this.a = new int[]{arg4, arg5};
            this.b = new float[]{0f, 1f};
        }

        a(List arg5, List arg6) {
            super();
            int v0 = arg5.size();
            this.a = new int[v0];
            this.b = new float[v0];
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a[v1] = arg5.get(v1).intValue();
                this.b[v1] = arg6.get(v1).floatValue();
            }
        }
    }

    static Shader a(Resources arg21, XmlPullParser arg22, AttributeSet arg23, Resources$Theme arg24) {
        XmlPullParser v0 = arg22;
        String v1 = arg22.getName();
        if(!v1.equals("gradient")) {
            goto label_85;
        }

        TypedArray v1_1 = g.a(arg21, arg24, arg23, android.support.a.a$d.GradientColor);
        float v9 = g.a(v1_1, v0, "startX", android.support.a.a$d.GradientColor_android_startX, 0f);
        float v10 = g.a(v1_1, v0, "startY", android.support.a.a$d.GradientColor_android_startY, 0f);
        float v11 = g.a(v1_1, v0, "endX", android.support.a.a$d.GradientColor_android_endX, 0f);
        float v12 = g.a(v1_1, v0, "endY", android.support.a.a$d.GradientColor_android_endY, 0f);
        float v14 = g.a(v1_1, v0, "centerX", android.support.a.a$d.GradientColor_android_centerX, 0f);
        float v15 = g.a(v1_1, v0, "centerY", android.support.a.a$d.GradientColor_android_centerY, 0f);
        int v5 = g.a(v1_1, v0, "type", android.support.a.a$d.GradientColor_android_type, 0);
        int v6 = g.b(v1_1, v0, "startColor", android.support.a.a$d.GradientColor_android_startColor, 0);
        boolean v13 = g.a(v0, "centerColor");
        int v2 = g.b(v1_1, v0, "centerColor", android.support.a.a$d.GradientColor_android_centerColor, 0);
        int v3 = g.b(v1_1, v0, "endColor", android.support.a.a$d.GradientColor_android_endColor, 0);
        int v4 = g.a(v1_1, v0, "tileMode", android.support.a.a$d.GradientColor_android_tileMode, 0);
        float v20 = v12;
        float v16 = g.a(v1_1, v0, "gradientRadius", android.support.a.a$d.GradientColor_android_gradientRadius, 0f);
        v1_1.recycle();
        a v0_1 = d.a(d.b(arg21, arg22, arg23, arg24), v6, v3, v13, v2);
        switch(v5) {
            case 1: {
                goto label_70;
            }
            case 2: {
                goto label_65;
            }
        }

        return new LinearGradient(v9, v10, v11, v20, v0_1.a, v0_1.b, d.a(v4));
    label_65:
        return new SweepGradient(v14, v15, v0_1.a, v0_1.b);
    label_70:
        if(v16 > 0f) {
            return new RadialGradient(v14, v15, v16, v0_1.a, v0_1.b, d.a(v4));
        }

        throw new XmlPullParserException("<gradient> tag requires \'gradientRadius\' attribute with radial type");
    label_85:
        StringBuilder v3_1 = new StringBuilder();
        v3_1.append(arg22.getPositionDescription());
        v3_1.append(": invalid gradient color tag ");
        v3_1.append(v1);
        throw new XmlPullParserException(v3_1.toString());
    }

    private static Shader$TileMode a(int arg0) {
        switch(arg0) {
            case 1: {
                goto label_5;
            }
            case 2: {
                goto label_3;
            }
        }

        return Shader$TileMode.CLAMP;
    label_3:
        return Shader$TileMode.MIRROR;
    label_5:
        return Shader$TileMode.REPEAT;
    }

    private static a a(a arg0, int arg1, int arg2, boolean arg3, int arg4) {
        if(arg0 != null) {
            return arg0;
        }

        if(arg3) {
            return new a(arg1, arg4, arg2);
        }

        return new a(arg1, arg2);
    }

    private static a b(Resources arg8, XmlPullParser arg9, AttributeSet arg10, Resources$Theme arg11) {
        int v0 = arg9.getDepth() + 1;
        ArrayList v2 = new ArrayList(20);
        ArrayList v4 = new ArrayList(20);
        while(true) {
            int v3 = arg9.next();
            if(v3 != 1) {
                int v5 = arg9.getDepth();
                if(v5 < v0 && v3 == 3) {
                    goto label_55;
                }

                if(v3 != 2) {
                    continue;
                }

                if(v5 > v0) {
                    continue;
                }

                if(!arg9.getName().equals("item")) {
                    continue;
                }

                TypedArray v3_1 = g.a(arg8, arg11, arg10, android.support.a.a$d.GradientColorItem);
                boolean v5_1 = v3_1.hasValue(android.support.a.a$d.GradientColorItem_android_color);
                boolean v6 = v3_1.hasValue(android.support.a.a$d.GradientColorItem_android_offset);
                if((v5_1) && (v6)) {
                    v5 = v3_1.getColor(android.support.a.a$d.GradientColorItem_android_color, 0);
                    float v6_1 = v3_1.getFloat(android.support.a.a$d.GradientColorItem_android_offset, 0f);
                    v3_1.recycle();
                    ((List)v4).add(Integer.valueOf(v5));
                    ((List)v2).add(Float.valueOf(v6_1));
                    continue;
                }

                break;
            }

            goto label_55;
        }

        StringBuilder v10 = new StringBuilder();
        v10.append(arg9.getPositionDescription());
        v10.append(": <item> tag requires a \'color\' attribute and a \'offset\' ");
        v10.append("attribute!");
        throw new XmlPullParserException(v10.toString());
    label_55:
        if(((List)v4).size() > 0) {
            return new a(((List)v4), ((List)v2));
        }

        return null;
    }
}

