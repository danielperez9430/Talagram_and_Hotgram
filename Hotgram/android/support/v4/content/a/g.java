package android.support.v4.content.a;

import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

public class g {
    public static TypedArray a(Resources arg0, Resources$Theme arg1, AttributeSet arg2, int[] arg3) {
        if(arg1 == null) {
            return arg0.obtainAttributes(arg2, arg3);
        }

        return arg1.obtainStyledAttributes(arg2, arg3, 0, 0);
    }

    public static int a(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3, int arg4) {
        if(!g.a(arg1, arg2)) {
            return arg4;
        }

        return arg0.getInt(arg3, arg4);
    }

    public static float a(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3, float arg4) {
        if(!g.a(arg1, arg2)) {
            return arg4;
        }

        return arg0.getFloat(arg3, arg4);
    }

    public static String a(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3) {
        if(!g.a(arg1, arg2)) {
            return null;
        }

        return arg0.getString(arg3);
    }

    public static boolean a(XmlPullParser arg1, String arg2) {
        boolean v1 = arg1.getAttributeValue("http://schemas.android.com/apk/res/android", arg2) != null ? true : false;
        return v1;
    }

    public static b a(TypedArray arg1, XmlPullParser arg2, Resources$Theme arg3, String arg4, int arg5, int arg6) {
        if(g.a(arg2, arg4)) {
            TypedValue v2 = new TypedValue();
            arg1.getValue(arg5, v2);
            if(v2.type >= 28 && v2.type <= 31) {
                return b.a(v2.data);
            }

            b v1 = b.a(arg1.getResources(), arg1.getResourceId(arg5, 0), arg3);
            if(v1 == null) {
                goto label_20;
            }

            return v1;
        }

    label_20:
        return b.a(arg6);
    }

    public static boolean a(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3, boolean arg4) {
        if(!g.a(arg1, arg2)) {
            return arg4;
        }

        return arg0.getBoolean(arg3, arg4);
    }

    public static TypedValue b(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3) {
        if(!g.a(arg1, arg2)) {
            return null;
        }

        return arg0.peekValue(arg3);
    }

    public static int b(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3, int arg4) {
        if(!g.a(arg1, arg2)) {
            return arg4;
        }

        return arg0.getColor(arg3, arg4);
    }

    public static int c(TypedArray arg0, XmlPullParser arg1, String arg2, int arg3, int arg4) {
        if(!g.a(arg1, arg2)) {
            return arg4;
        }

        return arg0.getResourceId(arg3, arg4);
    }
}

