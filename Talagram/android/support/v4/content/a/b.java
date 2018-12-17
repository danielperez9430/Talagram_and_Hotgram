package android.support.v4.content.a;

import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class b {
    private final Shader a;
    private final ColorStateList b;
    private int c;

    private b(Shader arg1, ColorStateList arg2, int arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    static b a(int arg2) {
        return new b(null, null, arg2);
    }

    static b a(ColorStateList arg3) {
        return new b(null, arg3, arg3.getDefaultColor());
    }

    public static b a(Resources arg0, int arg1, Resources$Theme arg2) {
        try {
            return b.b(arg0, arg1, arg2);
        }
        catch(Exception v0) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", ((Throwable)v0));
            return null;
        }
    }

    static b a(Shader arg3) {
        return new b(arg3, null, 0);
    }

    public Shader a() {
        return this.a;
    }

    public boolean a(int[] arg3) {
        boolean v0;
        if(this.d()) {
            int v3 = this.b.getColorForState(arg3, this.b.getDefaultColor());
            if(v3 != this.c) {
                v0 = true;
                this.c = v3;
            }
            else {
                goto label_11;
            }
        }
        else {
        label_11:
            v0 = false;
        }

        return v0;
    }

    private static b b(Resources arg6, int arg7, Resources$Theme arg8) {
        int v2;
        XmlResourceParser v7 = arg6.getXml(arg7);
        AttributeSet v0 = Xml.asAttributeSet(((XmlPullParser)v7));
        while(true) {
            int v1 = ((XmlPullParser)v7).next();
            v2 = 1;
            int v3 = 2;
            if(v1 != v3 && v1 != 1) {
                continue;
            }

            break;
        }

        if(v1 != v3) {
            goto label_45;
        }

        String v1_1 = ((XmlPullParser)v7).getName();
        int v4 = v1_1.hashCode();
        if(v4 != 89650992) {
            if(v4 != 1191572447) {
                goto label_26;
            }
            else if(v1_1.equals("selector")) {
                v2 = 0;
            }
            else {
                goto label_26;
            }
        }
        else if(v1_1.equals("gradient")) {
        }
        else {
        label_26:
            v2 = -1;
        }

        switch(v2) {
            case 0: {
                goto label_42;
            }
            case 1: {
                goto label_39;
            }
        }

        StringBuilder v8 = new StringBuilder();
        v8.append(((XmlPullParser)v7).getPositionDescription());
        v8.append(": unsupported complex color tag ");
        v8.append(v1_1);
        throw new XmlPullParserException(v8.toString());
    label_39:
        return b.a(d.a(arg6, ((XmlPullParser)v7), v0, arg8));
    label_42:
        return b.a(a.a(arg6, ((XmlPullParser)v7), v0, arg8));
    label_45:
        throw new XmlPullParserException("No start tag found");
    }

    public int b() {
        return this.c;
    }

    public void b(int arg1) {
        this.c = arg1;
    }

    public boolean c() {
        boolean v0 = this.a != null ? true : false;
        return v0;
    }

    public boolean d() {
        boolean v0 = this.a != null || this.b == null || !this.b.isStateful() ? false : true;
        return v0;
    }

    public boolean e() {
        boolean v0 = (this.c()) || this.c != 0 ? true : false;
        return v0;
    }
}

