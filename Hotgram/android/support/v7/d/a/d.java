package android.support.v7.d.a;

import android.content.Context;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.content.a.g;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.util.StateSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class d extends b {
    class a extends android.support.v7.d.a.b$b {
        int[][] L;

        a(a arg1, d arg2, Resources arg3) {
            super(((android.support.v7.d.a.b$b)arg1), ((b)arg2), arg3);
            int[][] v1 = arg1 != null ? arg1.L : new int[this.c()][];
            this.L = v1;
        }

        int a(int[] arg2, Drawable arg3) {
            int v3 = this.a(arg3);
            this.L[v3] = arg2;
            return v3;
        }

        void a() {
            // Method was not decompiled
        }

        int b(int[] arg5) {
            int[][] v0 = this.L;
            int v1 = this.d();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                if(StateSet.stateSetMatches(v0[v2], arg5)) {
                    return v2;
                }
            }

            return -1;
        }

        public void e(int arg3, int arg4) {
            super.e(arg3, arg4);
            int[][] v4 = new int[arg4][];
            System.arraycopy(this.L, 0, v4, 0, arg3);
            this.L = v4;
        }

        public Drawable newDrawable() {
            return new d(this, null);
        }

        public Drawable newDrawable(Resources arg2) {
            return new d(this, arg2);
        }
    }

    private a a;
    private boolean b;

    d() {
        this(null, null);
    }

    d(a arg2, Resources arg3) {
        super();
        this.a(new a(arg2, this, arg3));
        this.onStateChange(this.getState());
    }

    d(a arg1) {
        super();
        if(arg1 != null) {
            this.a(((android.support.v7.d.a.b$b)arg1));
        }
    }

    protected void a(android.support.v7.d.a.b$b arg2) {
        super.a(arg2);
        if((arg2 instanceof a)) {
            this.a = ((a)arg2);
        }
    }

    private void a(Context arg9, Resources arg10, XmlPullParser arg11, AttributeSet arg12, Resources$Theme arg13) {
        int v4;
        a v0 = this.a;
        int v1 = arg11.getDepth() + 1;
        while(true) {
        label_4:
            int v3 = arg11.next();
            if(v3 != 1) {
                v4 = arg11.getDepth();
                if(v4 < v1 && v3 == 3) {
                    return;
                }

                int v5 = 2;
                if(v3 != v5) {
                    continue;
                }

                if(v4 > v1) {
                    continue;
                }

                if(arg11.getName().equals("item")) {
                    break;
                }

                continue;
            }

            return;
        }

        TypedArray v3_1 = g.a(arg10, arg13, arg12, j.StateListDrawableItem);
        Drawable v4_1 = null;
        int v6 = v3_1.getResourceId(j.StateListDrawableItem_android_drawable, -1);
        if(v6 > 0) {
            v4_1 = android.support.v7.c.a.a.b(arg9, v6);
        }

        v3_1.recycle();
        int[] v3_2 = this.a(arg12);
        if(v4_1 != null) {
            goto label_54;
        }

        goto label_30;
        return;
        while(true) {
        label_30:
            v4 = arg11.next();
            if(v4 != 4) {
                break;
            }
        }

        if(v4 != v5) {
            StringBuilder v10 = new StringBuilder();
            v10.append(arg11.getPositionDescription());
            v10.append(": <item> tag requires a \'drawable\' attribute or ");
            v10.append("child tag defining a drawable");
            throw new XmlPullParserException(v10.toString());
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            v4_1 = Drawable.createFromXmlInner(arg10, arg11, arg12, arg13);
        }
        else {
            v4_1 = Drawable.createFromXmlInner(arg10, arg11, arg12);
        }

    label_54:
        v0.a(v3_2, v4_1);
        goto label_4;
    }

    int[] a(AttributeSet arg9) {
        int v0 = arg9.getAttributeCount();
        int[] v1 = new int[v0];
        int v3 = 0;
        int v4 = 0;
        while(v3 < v0) {
            int v5 = arg9.getAttributeNameResource(v3);
            if(v5 != 0 && v5 != 16842960 && v5 != 16843161) {
                int v6 = v4 + 1;
                if(arg9.getAttributeBooleanValue(v3, false)) {
                }
                else {
                    v5 = -v5;
                }

                v1[v4] = v5;
                v4 = v6;
            }

            ++v3;
        }

        return StateSet.trimStateSet(v1, v4);
    }

    private void a(TypedArray arg4) {
        a v0 = this.a;
        if(Build$VERSION.SDK_INT >= 21) {
            v0.f |= arg4.getChangingConfigurations();
        }

        v0.k = arg4.getBoolean(j.StateListDrawable_android_variablePadding, v0.k);
        v0.n = arg4.getBoolean(j.StateListDrawable_android_constantSize, v0.n);
        v0.C = arg4.getInt(j.StateListDrawable_android_enterFadeDuration, v0.C);
        v0.D = arg4.getInt(j.StateListDrawable_android_exitFadeDuration, v0.D);
        v0.z = arg4.getBoolean(j.StateListDrawable_android_dither, v0.z);
    }

    public void applyTheme(Resources$Theme arg1) {
        super.applyTheme(arg1);
        this.onStateChange(this.getState());
    }

    a b() {
        return new a(this.a, this, null);
    }

    public void b(Context arg4, Resources arg5, XmlPullParser arg6, AttributeSet arg7, Resources$Theme arg8) {
        TypedArray v0 = g.a(arg5, arg8, arg7, j.StateListDrawable);
        this.setVisible(v0.getBoolean(j.StateListDrawable_android_visible, true), true);
        this.a(v0);
        this.a(arg5);
        v0.recycle();
        this.a(arg4, arg5, arg6, arg7, arg8);
        this.onStateChange(this.getState());
    }

    android.support.v7.d.a.b$b c() {
        return this.b();
    }

    public boolean isStateful() {
        return 1;
    }

    public Drawable mutate() {
        if(!this.b && super.mutate() == this) {
            this.a.a();
            this.b = true;
        }

        return this;
    }

    protected boolean onStateChange(int[] arg3) {
        boolean v0 = super.onStateChange(arg3);
        int v3 = this.a.b(arg3);
        if(v3 < 0) {
            v3 = this.a.b(StateSet.WILD_CARD);
        }

        boolean v3_1 = (this.a(v3)) || (v0) ? true : false;
        return v3_1;
    }
}

