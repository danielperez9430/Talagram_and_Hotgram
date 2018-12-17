package android.support.v4.content.a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.util.Base64;
import android.util.TypedValue;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class c {
    public interface a {
    }

    public final class b implements a {
        private final android.support.v4.content.a.c$c[] a;

        public b(android.support.v4.content.a.c$c[] arg1) {
            super();
            this.a = arg1;
        }

        public android.support.v4.content.a.c$c[] a() {
            return this.a;
        }
    }

    public final class android.support.v4.content.a.c$c {
        private final String a;
        private int b;
        private boolean c;
        private String d;
        private int e;
        private int f;

        public android.support.v4.content.a.c$c(String arg1, int arg2, boolean arg3, String arg4, int arg5, int arg6) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
            this.f = arg6;
        }

        public String a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }

        public int f() {
            return this.f;
        }
    }

    public final class d implements a {
        private final android.support.v4.d.a a;
        private final int b;
        private final int c;

        public d(android.support.v4.d.a arg1, int arg2, int arg3) {
            super();
            this.a = arg1;
            this.c = arg2;
            this.b = arg3;
        }

        public android.support.v4.d.a a() {
            return this.a;
        }

        public int b() {
            return this.c;
        }

        public int c() {
            return this.b;
        }
    }

    private static int a(TypedArray arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getType(arg3);
        }

        TypedValue v0 = new TypedValue();
        arg2.getValue(arg3, v0);
        return v0.type;
    }

    public static a a(XmlPullParser arg3, Resources arg4) {
        while(true) {
            int v0 = arg3.next();
            int v1 = 2;
            if(v0 != v1 && v0 != 1) {
                continue;
            }

            break;
        }

        if(v0 == v1) {
            return c.b(arg3, arg4);
        }

        throw new XmlPullParserException("No start tag found");
    }

    public static List a(Resources arg5, int arg6) {
        ArrayList v1;
        List v5_1;
        if(arg6 == 0) {
            return Collections.emptyList();
        }

        TypedArray v0 = arg5.obtainTypedArray(arg6);
        try {
            if(v0.length() != 0) {
                goto label_9;
            }

            v5_1 = Collections.emptyList();
        }
        catch(Throwable v5) {
            goto label_31;
        }

        v0.recycle();
        return v5_1;
        try {
        label_9:
            v1 = new ArrayList();
            if(c.a(v0, 0) == 1) {
                for(arg6 = 0; arg6 < v0.length(); ++arg6) {
                    int v3 = v0.getResourceId(arg6, 0);
                    if(v3 != 0) {
                        ((List)v1).add(c.a(arg5.getStringArray(v3)));
                    }
                }
            }
            else {
                ((List)v1).add(c.a(arg5.getStringArray(arg6)));
            }
        }
        catch(Throwable v5) {
        label_31:
            v0.recycle();
            throw v5;
        }

        v0.recycle();
        return ((List)v1);
    }

    private static List a(String[] arg5) {
        ArrayList v0 = new ArrayList();
        int v1 = arg5.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            ((List)v0).add(Base64.decode(arg5[v3], 0));
        }

        return ((List)v0);
    }

    private static void a(XmlPullParser arg2) {
        int v0;
        for(v0 = 1; v0 > 0; ++v0) {
            switch(arg2.next()) {
                case 2: {
                    goto label_7;
                }
                case 3: {
                    goto label_5;
                }
            }

            continue;
        label_5:
            --v0;
            continue;
        label_7:
        }
    }

    private static a b(XmlPullParser arg3, Resources arg4) {
        String v1 = null;
        arg3.require(2, v1, "font-family");
        if(arg3.getName().equals("font-family")) {
            return c.c(arg3, arg4);
        }

        c.a(arg3);
        return ((a)v1);
    }

    private static a c(XmlPullParser arg8, Resources arg9) {
        TypedArray v0 = arg9.obtainAttributes(Xml.asAttributeSet(arg8), android.support.a.a$d.FontFamily);
        String v1 = v0.getString(android.support.a.a$d.FontFamily_fontProviderAuthority);
        String v2 = v0.getString(android.support.a.a$d.FontFamily_fontProviderPackage);
        String v3 = v0.getString(android.support.a.a$d.FontFamily_fontProviderQuery);
        int v4 = v0.getResourceId(android.support.a.a$d.FontFamily_fontProviderCerts, 0);
        int v5 = v0.getInteger(android.support.a.a$d.FontFamily_fontProviderFetchStrategy, 1);
        int v6 = v0.getInteger(android.support.a.a$d.FontFamily_fontProviderFetchTimeout, 500);
        v0.recycle();
        int v0_1 = 3;
        if(v1 != null && v2 != null && v3 != null) {
            while(arg8.next() != v0_1) {
                c.a(arg8);
            }

            return new d(new android.support.v4.d.a(v1, v2, v3, c.a(arg9, v4)), v5, v6);
        }

        ArrayList v1_1 = new ArrayList();
        while(arg8.next() != v0_1) {
            if(arg8.getEventType() != 2) {
                continue;
            }

            if(arg8.getName().equals("font")) {
                ((List)v1_1).add(c.d(arg8, arg9));
                continue;
            }

            c.a(arg8);
        }

        if(((List)v1_1).isEmpty()) {
            return null;
        }

        return new b(((List)v1_1).toArray(new android.support.v4.content.a.c$c[((List)v1_1).size()]));
    }

    private static android.support.v4.content.a.c$c d(XmlPullParser arg9, Resources arg10) {
        TypedArray v10 = arg10.obtainAttributes(Xml.asAttributeSet(arg9), android.support.a.a$d.FontFamilyFont);
        int v0 = v10.hasValue(android.support.a.a$d.FontFamilyFont_fontWeight) ? android.support.a.a$d.FontFamilyFont_fontWeight : android.support.a.a$d.FontFamilyFont_android_fontWeight;
        int v4 = v10.getInt(v0, 400);
        v0 = v10.hasValue(android.support.a.a$d.FontFamilyFont_fontStyle) ? android.support.a.a$d.FontFamilyFont_fontStyle : android.support.a.a$d.FontFamilyFont_android_fontStyle;
        boolean v5 = 1 == v10.getInt(v0, 0) ? true : false;
        v0 = v10.hasValue(android.support.a.a$d.FontFamilyFont_ttcIndex) ? android.support.a.a$d.FontFamilyFont_ttcIndex : android.support.a.a$d.FontFamilyFont_android_ttcIndex;
        int v2 = v10.hasValue(android.support.a.a$d.FontFamilyFont_fontVariationSettings) ? android.support.a.a$d.FontFamilyFont_fontVariationSettings : android.support.a.a$d.FontFamilyFont_android_fontVariationSettings;
        String v6 = v10.getString(v2);
        int v7 = v10.getInt(v0, 0);
        v0 = v10.hasValue(android.support.a.a$d.FontFamilyFont_font) ? android.support.a.a$d.FontFamilyFont_font : android.support.a.a$d.FontFamilyFont_android_font;
        int v8 = v10.getResourceId(v0, 0);
        String v3 = v10.getString(v0);
        v10.recycle();
        while(arg9.next() != 3) {
            c.a(arg9);
        }

        return new android.support.v4.content.a.c$c(v3, v4, v5, v6, v7, v8);
    }
}

