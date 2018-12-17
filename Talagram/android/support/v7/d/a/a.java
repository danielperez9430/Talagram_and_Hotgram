package android.support.v7.d.a;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.c.a.i;
import android.support.v4.content.a.g;
import android.support.v4.f.n;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.util.StateSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class a extends d {
    class android.support.v7.d.a.a$1 {
    }

    class android.support.v7.d.a.a$a extends f {
        private final Animatable a;

        android.support.v7.d.a.a$a(Animatable arg2) {
            super(null);
            this.a = arg2;
        }

        public void a() {
            this.a.start();
        }

        public void b() {
            this.a.stop();
        }
    }

    class b extends android.support.v7.d.a.d$a {
        android.support.v4.f.f a;
        n b;

        b(b arg1, a arg2, Resources arg3) {
            n v1;
            super(((android.support.v7.d.a.d$a)arg1), ((d)arg2), arg3);
            if(arg1 != null) {
                this.a = arg1.a;
                v1 = arg1.b;
            }
            else {
                this.a = new android.support.v4.f.f();
                v1 = new n();
            }

            this.b = v1;
        }

        int a(int arg3) {
            int v0 = 0;
            if(arg3 < 0) {
            }
            else {
                v0 = this.b.a(arg3, Integer.valueOf(0)).intValue();
            }

            return v0;
        }

        int a(int arg4, int arg5) {
            return ((int)this.a.a(b.f(arg4, arg5), Long.valueOf(-1)).longValue());
        }

        int a(int arg10, int arg11, Drawable arg12, boolean arg13) {
            int v12 = super.a(arg12);
            long v0 = b.f(arg10, arg11);
            long v2 = arg13 ? 8589934592L : 0;
            long v5 = ((long)v12);
            this.a.c(v0, Long.valueOf(v5 | v2));
            if(arg13) {
                this.a.c(b.f(arg11, arg10), Long.valueOf(4294967296L | v5 | v2));
            }

            return v12;
        }

        int a(int[] arg1, Drawable arg2, int arg3) {
            int v1 = super.a(arg1, arg2);
            this.b.b(v1, Integer.valueOf(arg3));
            return v1;
        }

        void a() {
            this.a = this.a.a();
            this.b = this.b.a();
        }

        int a(int[] arg1) {
            int v1 = super.b(arg1);
            if(v1 >= 0) {
                return v1;
            }

            return super.b(StateSet.WILD_CARD);
        }

        boolean b(int arg4, int arg5) {
            boolean v4 = (this.a.a(b.f(arg4, arg5), Long.valueOf(-1)).longValue() & 4294967296L) != 0 ? true : false;
            return v4;
        }

        boolean c(int arg4, int arg5) {
            boolean v4 = (this.a.a(b.f(arg4, arg5), Long.valueOf(-1)).longValue() & 8589934592L) != 0 ? true : false;
            return v4;
        }

        private static long f(int arg2, int arg3) {
            return (((long)arg3)) | (((long)arg2)) << 32;
        }

        public Drawable newDrawable() {
            return new a(this, null);
        }

        public Drawable newDrawable(Resources arg2) {
            return new a(this, arg2);
        }
    }

    class c extends f {
        private final android.support.c.a.c a;

        c(android.support.c.a.c arg2) {
            super(null);
            this.a = arg2;
        }

        public void a() {
            this.a.start();
        }

        public void b() {
            this.a.stop();
        }
    }

    class android.support.v7.d.a.a$d extends f {
        private final ObjectAnimator a;
        private final boolean b;

        android.support.v7.d.a.a$d(AnimationDrawable arg7, boolean arg8, boolean arg9) {
            super(null);
            int v0 = arg7.getNumberOfFrames();
            int v2 = arg8 ? v0 - 1 : 0;
            if(arg8) {
                v0 = 0;
            }
            else {
                --v0;
            }

            e v4 = new e(arg7, arg8);
            ObjectAnimator v7 = ObjectAnimator.ofInt(arg7, "currentIndex", new int[]{v2, v0});
            if(Build$VERSION.SDK_INT >= 18) {
                v7.setAutoCancel(true);
            }

            v7.setDuration(((long)v4.a()));
            v7.setInterpolator(((TimeInterpolator)v4));
            this.b = arg9;
            this.a = v7;
        }

        public void a() {
            this.a.start();
        }

        public void b() {
            this.a.cancel();
        }

        public boolean c() {
            return this.b;
        }

        public void d() {
            this.a.reverse();
        }
    }

    class e implements TimeInterpolator {
        private int[] a;
        private int b;
        private int c;

        e(AnimationDrawable arg1, boolean arg2) {
            super();
            this.a(arg1, arg2);
        }

        int a(AnimationDrawable arg6, boolean arg7) {
            int v0 = arg6.getNumberOfFrames();
            this.b = v0;
            if(this.a == null || this.a.length < v0) {
                this.a = new int[v0];
            }

            int[] v1 = this.a;
            int v2 = 0;
            int v3 = 0;
            while(v2 < v0) {
                int v4 = arg7 ? v0 - v2 - 1 : v2;
                v4 = arg6.getDuration(v4);
                v1[v2] = v4;
                v3 += v4;
                ++v2;
            }

            this.c = v3;
            return v3;
        }

        int a() {
            return this.c;
        }

        public float getInterpolation(float arg5) {
            int v5 = ((int)(arg5 * (((float)this.c)) + 0.5f));
            int v0 = this.b;
            int[] v1 = this.a;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                if(v5 < v1[v2]) {
                    break;
                }

                v5 -= v1[v2];
            }

            arg5 = v2 < v0 ? (((float)v5)) / (((float)this.c)) : 0f;
            return (((float)v2)) / (((float)v0)) + arg5;
        }
    }

    abstract class f {
        private f() {
            super();
        }

        f(android.support.v7.d.a.a$1 arg1) {
            this();
        }

        public abstract void a();

        public abstract void b();

        public boolean c() {
            return 0;
        }

        public void d() {
        }
    }

    private static final String a = "a";
    private b b;
    private f c;
    private int d;
    private int e;
    private boolean f;

    static {
    }

    public a() {
        this(null, null);
    }

    a(b arg2, Resources arg3) {
        super(null);
        this.d = -1;
        this.e = -1;
        this.a(new b(arg2, this, arg3));
        this.onStateChange(this.getState());
        this.jumpToCurrentState();
    }

    public static a a(Context arg8, Resources arg9, XmlPullParser arg10, AttributeSet arg11, Resources$Theme arg12) {
        String v0 = arg10.getName();
        if(v0.equals("animated-selector")) {
            a v0_1 = new a();
            v0_1.b(arg8, arg9, arg10, arg11, arg12);
            return v0_1;
        }

        StringBuilder v9 = new StringBuilder();
        v9.append(arg10.getPositionDescription());
        v9.append(": invalid animated-selector tag ");
        v9.append(v0);
        throw new XmlPullParserException(v9.toString());
    }

    protected void a(android.support.v7.d.a.b$b arg2) {
        super.a(arg2);
        if((arg2 instanceof b)) {
            this.b = ((b)arg2);
        }
    }

    private void a(TypedArray arg4) {
        b v0 = this.b;
        if(Build$VERSION.SDK_INT >= 21) {
            v0.f |= arg4.getChangingConfigurations();
        }

        v0.a(arg4.getBoolean(j.AnimatedStateListDrawableCompat_android_variablePadding, v0.k));
        v0.b(arg4.getBoolean(j.AnimatedStateListDrawableCompat_android_constantSize, v0.n));
        v0.c(arg4.getInt(j.AnimatedStateListDrawableCompat_android_enterFadeDuration, v0.C));
        v0.d(arg4.getInt(j.AnimatedStateListDrawableCompat_android_exitFadeDuration, v0.D));
        this.setDither(arg4.getBoolean(j.AnimatedStateListDrawableCompat_android_dither, v0.z));
    }

    b a() {
        return new b(this.b, this, null);
    }

    public void applyTheme(Resources$Theme arg1) {
        super.applyTheme(arg1);
    }

    public void b(Context arg4, Resources arg5, XmlPullParser arg6, AttributeSet arg7, Resources$Theme arg8) {
        TypedArray v0 = g.a(arg5, arg8, arg7, j.AnimatedStateListDrawableCompat);
        this.setVisible(v0.getBoolean(j.AnimatedStateListDrawableCompat_android_visible, true), true);
        this.a(v0);
        this.a(arg5);
        v0.recycle();
        this.c(arg4, arg5, arg6, arg7, arg8);
        this.e();
    }

    private boolean b(int arg10) {
        c v3_2;
        int v2;
        f v0 = this.c;
        if(v0 == null) {
            v2 = this.d();
        }
        else if(arg10 == this.d) {
            return 1;
        }
        else {
            if(arg10 == this.e && (v0.c())) {
                v0.d();
                this.d = this.e;
                this.e = arg10;
                return 1;
            }

            v2 = this.d;
            v0.b();
        }

        this.c = null;
        this.e = -1;
        this.d = -1;
        b v0_1 = this.b;
        int v3 = v0_1.a(v2);
        int v4 = v0_1.a(arg10);
        if(v4 != 0) {
            if(v3 == 0) {
            }
            else {
                int v6 = v0_1.a(v3, v4);
                if(v6 < 0) {
                    return 0;
                }
                else {
                    boolean v7 = v0_1.c(v3, v4);
                    this.a(v6);
                    Drawable v6_1 = this.getCurrent();
                    if((v6_1 instanceof AnimationDrawable)) {
                        android.support.v7.d.a.a$d v3_1 = new android.support.v7.d.a.a$d(((AnimationDrawable)v6_1), v0_1.b(v3, v4), v7);
                    }
                    else if((v6_1 instanceof android.support.c.a.c)) {
                        v3_2 = new c(((android.support.c.a.c)v6_1));
                    }
                    else if((v6_1 instanceof Animatable)) {
                        android.support.v7.d.a.a$a v3_3 = new android.support.v7.d.a.a$a(((Animatable)v6_1));
                    }
                    else {
                        return 0;
                    }

                    ((f)v3_2).a();
                    this.c = ((f)v3_2);
                    this.e = v2;
                    this.d = arg10;
                    return 1;
                }
            }
        }

        return 0;
    }

    android.support.v7.d.a.d$a b() {
        return this.a();
    }

    private void c(Context arg6, Resources arg7, XmlPullParser arg8, AttributeSet arg9, Resources$Theme arg10) {
        int v0 = arg8.getDepth() + 1;
        while(true) {
            int v2 = arg8.next();
            if(v2 != 1) {
                int v3 = arg8.getDepth();
                if(v3 < v0 && v2 == 3) {
                    return;
                }

                if(v2 != 2) {
                    continue;
                }

                if(v3 > v0) {
                    continue;
                }

                if(arg8.getName().equals("item")) {
                    this.e(arg6, arg7, arg8, arg9, arg10);
                    continue;
                }

                if(!arg8.getName().equals("transition")) {
                    continue;
                }

                this.d(arg6, arg7, arg8, arg9, arg10);
                continue;
            }

            return;
        }
    }

    android.support.v7.d.a.b$b c() {
        return this.a();
    }

    public boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    private int d(Context arg8, Resources arg9, XmlPullParser arg10, AttributeSet arg11, Resources$Theme arg12) {
        StringBuilder v9;
        TypedArray v0 = g.a(arg9, arg12, arg11, j.AnimatedStateListDrawableTransition);
        int v2 = -1;
        int v1 = v0.getResourceId(j.AnimatedStateListDrawableTransition_android_fromId, v2);
        int v3 = v0.getResourceId(j.AnimatedStateListDrawableTransition_android_toId, v2);
        int v4 = v0.getResourceId(j.AnimatedStateListDrawableTransition_android_drawable, v2);
        Drawable v4_1 = v4 > 0 ? android.support.v7.c.a.a.b(arg8, v4) : null;
        boolean v5 = v0.getBoolean(j.AnimatedStateListDrawableTransition_android_reversible, false);
        v0.recycle();
        if(v4_1 == null) {
            while(true) {
                int v0_1 = arg10.next();
                if(v0_1 != 4) {
                    break;
                }
            }

            if(v0_1 != 2) {
                v9 = new StringBuilder();
                v9.append(arg10.getPositionDescription());
                v9.append(": <transition> tag requires a \'drawable\' attribute or child tag defining a drawable");
                throw new XmlPullParserException(v9.toString());
            }
            else if(arg10.getName().equals("animated-vector")) {
                android.support.c.a.c v4_2 = android.support.c.a.c.a(arg8, arg9, arg10, arg11, arg12);
            }
            else if(Build$VERSION.SDK_INT >= 21) {
                v4_1 = Drawable.createFromXmlInner(arg9, arg10, arg11, arg12);
            }
            else {
                v4_1 = Drawable.createFromXmlInner(arg9, arg10, arg11);
            }
        }

        if(v4_1 != null) {
            if(v1 != v2 && v3 != v2) {
                return this.b.a(v1, v3, v4_1, v5);
            }

            v9 = new StringBuilder();
            v9.append(arg10.getPositionDescription());
            v9.append(": <transition> tag requires \'fromId\' & \'toId\' attributes");
            throw new XmlPullParserException(v9.toString());
        }

        v9 = new StringBuilder();
        v9.append(arg10.getPositionDescription());
        v9.append(": <transition> tag requires a \'drawable\' attribute or child tag defining a drawable");
        throw new XmlPullParserException(v9.toString());
    }

    public void draw(Canvas arg1) {
        super.draw(arg1);
    }

    private int e(Context arg5, Resources arg6, XmlPullParser arg7, AttributeSet arg8, Resources$Theme arg9) {
        i v5_2;
        StringBuilder v6;
        TypedArray v0 = g.a(arg6, arg9, arg8, j.AnimatedStateListDrawableItem);
        int v1 = v0.getResourceId(j.AnimatedStateListDrawableItem_android_id, 0);
        int v2 = v0.getResourceId(j.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable v5 = v2 > 0 ? android.support.v7.c.a.a.b(arg5, v2) : null;
        v0.recycle();
        int[] v0_1 = this.a(arg8);
        if(v5 == null) {
            while(true) {
                int v5_1 = arg7.next();
                if(v5_1 != 4) {
                    break;
                }
            }

            if(v5_1 != 2) {
                v6 = new StringBuilder();
                v6.append(arg7.getPositionDescription());
                v6.append(": <item> tag requires a \'drawable\' attribute or child tag defining a drawable");
                throw new XmlPullParserException(v6.toString());
            }
            else if(arg7.getName().equals("vector")) {
                v5_2 = i.a(arg6, arg7, arg8, arg9);
            }
            else if(Build$VERSION.SDK_INT >= 21) {
                v5 = Drawable.createFromXmlInner(arg6, arg7, arg8, arg9);
            }
            else {
                v5 = Drawable.createFromXmlInner(arg6, arg7, arg8);
            }
        }

        if((((Drawable)v5_2)) != null) {
            return this.b.a(v0_1, ((Drawable)v5_2), v1);
        }

        v6 = new StringBuilder();
        v6.append(arg7.getPositionDescription());
        v6.append(": <item> tag requires a \'drawable\' attribute or child tag defining a drawable");
        throw new XmlPullParserException(v6.toString());
    }

    private void e() {
        this.onStateChange(this.getState());
    }

    public int getAlpha() {
        return super.getAlpha();
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    public Drawable$ConstantState getConstantState() {
        return this.b;
    }

    public Drawable getCurrent() {
        return super.getCurrent();
    }

    public void getHotspotBounds(Rect arg1) {
        super.getHotspotBounds(arg1);
    }

    public int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        return super.getOpacity();
    }

    public void getOutline(Outline arg1) {
        super.getOutline(arg1);
    }

    public boolean getPadding(Rect arg1) {
        return super.getPadding(arg1);
    }

    public void invalidateDrawable(Drawable arg1) {
        super.invalidateDrawable(arg1);
    }

    public boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    public boolean isStateful() {
        return 1;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        if(this.c != null) {
            this.c.b();
            this.c = null;
            this.a(this.d);
            this.d = -1;
            this.e = -1;
        }
    }

    public Drawable mutate() {
        if(!this.f && super.mutate() == this) {
            this.b.a();
            this.f = true;
        }

        return this;
    }

    public boolean onLayoutDirectionChanged(int arg1) {
        return super.onLayoutDirectionChanged(arg1);
    }

    protected boolean onStateChange(int[] arg3) {
        int v0 = this.b.a(arg3);
        if(v0 != this.d()) {
            if(!this.b(v0) && !this.a(v0)) {
                goto label_10;
            }

            v0 = 1;
        }
        else {
        label_10:
            v0 = 0;
        }

        Drawable v1 = this.getCurrent();
        if(v1 != null) {
            v0 |= v1.setState(arg3);
        }

        return ((boolean)v0);
    }

    public void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
        super.scheduleDrawable(arg1, arg2, arg3);
    }

    public void setAlpha(int arg1) {
        super.setAlpha(arg1);
    }

    public void setAutoMirrored(boolean arg1) {
        super.setAutoMirrored(arg1);
    }

    public void setColorFilter(ColorFilter arg1) {
        super.setColorFilter(arg1);
    }

    public void setDither(boolean arg1) {
        super.setDither(arg1);
    }

    public void setHotspot(float arg1, float arg2) {
        super.setHotspot(arg1, arg2);
    }

    public void setHotspotBounds(int arg1, int arg2, int arg3, int arg4) {
        super.setHotspotBounds(arg1, arg2, arg3, arg4);
    }

    public void setTintList(ColorStateList arg1) {
        super.setTintList(arg1);
    }

    public void setTintMode(PorterDuff$Mode arg1) {
        super.setTintMode(arg1);
    }

    public boolean setVisible(boolean arg3, boolean arg4) {
        boolean v0 = super.setVisible(arg3, arg4);
        if(this.c != null && ((v0) || (arg4))) {
            if(arg3) {
                this.c.a();
                return v0;
            }

            this.jumpToCurrentState();
        }

        return v0;
    }

    public void unscheduleDrawable(Drawable arg1, Runnable arg2) {
        super.unscheduleDrawable(arg1, arg2);
    }
}

