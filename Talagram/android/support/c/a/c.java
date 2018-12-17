package android.support.c.a;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.content.a.g;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

public class c extends h implements b {
    class android.support.c.a.c$1 implements Drawable$Callback {
        android.support.c.a.c$1(c arg1) {
            this.a = arg1;
            super();
        }

        public void invalidateDrawable(Drawable arg1) {
            this.a.invalidateSelf();
        }

        public void scheduleDrawable(Drawable arg1, Runnable arg2, long arg3) {
            this.a.scheduleSelf(arg2, arg3);
        }

        public void unscheduleDrawable(Drawable arg1, Runnable arg2) {
            this.a.unscheduleSelf(arg2);
        }
    }

    class a extends Drawable$ConstantState {
        int a;
        i b;
        AnimatorSet c;
        ArrayList d;
        android.support.v4.f.a e;

        public a(Context arg3, a arg4, Drawable$Callback arg5, Resources arg6) {
            super();
            if(arg4 != null) {
                this.a = arg4.a;
                int v0 = 0;
                if(arg4.b != null) {
                    Drawable$ConstantState v3 = arg4.b.getConstantState();
                    Drawable v3_1 = arg6 != null ? v3.newDrawable(arg6) : v3.newDrawable();
                    this.b = ((i)v3_1);
                    this.b = this.b.mutate();
                    this.b.setCallback(arg5);
                    this.b.setBounds(arg4.b.getBounds());
                    this.b.a(false);
                }

                if(arg4.d == null) {
                    return;
                }

                int v3_2 = arg4.d.size();
                this.d = new ArrayList(v3_2);
                this.e = new android.support.v4.f.a(v3_2);
                while(v0 < v3_2) {
                    Object v5 = arg4.d.get(v0);
                    Animator v6 = ((Animator)v5).clone();
                    v5 = arg4.e.get(v5);
                    v6.setTarget(this.b.a(((String)v5)));
                    this.d.add(v6);
                    this.e.put(v6, v5);
                    ++v0;
                }

                this.a();
            }
        }

        public void a() {
            if(this.c == null) {
                this.c = new AnimatorSet();
            }

            this.c.playTogether(this.d);
        }

        public int getChangingConfigurations() {
            return this.a;
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources arg2) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    class android.support.c.a.c$b extends Drawable$ConstantState {
        private final Drawable$ConstantState a;

        public android.support.c.a.c$b(Drawable$ConstantState arg1) {
            super();
            this.a = arg1;
        }

        public boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }

        public Drawable newDrawable() {
            c v0 = new c();
            v0.c = this.a.newDrawable();
            v0.c.setCallback(v0.b);
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3) {
            c v0 = new c();
            v0.c = this.a.newDrawable(arg3);
            v0.c.setCallback(v0.b);
            return ((Drawable)v0);
        }

        public Drawable newDrawable(Resources arg3, Resources$Theme arg4) {
            c v0 = new c();
            v0.c = this.a.newDrawable(arg3, arg4);
            v0.c.setCallback(v0.b);
            return ((Drawable)v0);
        }
    }

    ArrayList a;
    final Drawable$Callback b;
    private a d;
    private Context e;
    private ArgbEvaluator f;
    private Animator$AnimatorListener g;

    c() {
        this(null, null, null);
    }

    private c(Context arg3, a arg4, Resources arg5) {
        super();
        this.f = null;
        this.g = null;
        this.a = null;
        this.b = new android.support.c.a.c$1(this);
        this.e = arg3;
        this.d = arg4 != null ? arg4 : new a(arg3, arg4, this.b, arg5);
    }

    private c(Context arg2) {
        this(arg2, null, null);
    }

    public static c a(Context arg1, Resources arg2, XmlPullParser arg3, AttributeSet arg4, Resources$Theme arg5) {
        c v0 = new c(arg1);
        v0.inflate(arg2, arg3, arg4, arg5);
        return v0;
    }

    private void a(Animator arg4) {
        if((arg4 instanceof AnimatorSet)) {
            ArrayList v0 = arg4.getChildAnimations();
            if(v0 != null) {
                int v1;
                for(v1 = 0; v1 < ((List)v0).size(); ++v1) {
                    this.a(((List)v0).get(v1));
                }
            }
        }

        if((arg4 instanceof ObjectAnimator)) {
            String v0_1 = ((ObjectAnimator)arg4).getPropertyName();
            if(!"fillColor".equals(v0_1) && !"strokeColor".equals(v0_1)) {
                return;
            }

            if(this.f == null) {
                this.f = new ArgbEvaluator();
            }

            ((ObjectAnimator)arg4).setEvaluator(this.f);
        }
    }

    private void a(String arg3, Animator arg4) {
        arg4.setTarget(this.d.b.a(arg3));
        if(Build$VERSION.SDK_INT < 21) {
            this.a(arg4);
        }

        if(this.d.d == null) {
            this.d.d = new ArrayList();
            this.d.e = new android.support.v4.f.a();
        }

        this.d.d.add(arg4);
        this.d.e.put(arg4, arg3);
    }

    public void applyTheme(Resources$Theme arg2) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2);
        }
    }

    public boolean canApplyTheme() {
        if(this.c != null) {
            return android.support.v4.graphics.drawable.a.d(this.c);
        }

        return 0;
    }

    public void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas arg2) {
        if(this.c != null) {
            this.c.draw(arg2);
            return;
        }

        this.d.b.draw(arg2);
        if(this.d.c.isStarted()) {
            this.invalidateSelf();
        }
    }

    public int getAlpha() {
        if(this.c != null) {
            return android.support.v4.graphics.drawable.a.c(this.c);
        }

        return this.d.b.getAlpha();
    }

    public int getChangingConfigurations() {
        if(this.c != null) {
            return this.c.getChangingConfigurations();
        }

        return super.getChangingConfigurations() | this.d.a;
    }

    public ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public Drawable$ConstantState getConstantState() {
        if(this.c != null && Build$VERSION.SDK_INT >= 24) {
            return new android.support.c.a.c$b(this.c.getConstantState());
        }

        return null;
    }

    public Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        if(this.c != null) {
            return this.c.getIntrinsicHeight();
        }

        return this.d.b.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        if(this.c != null) {
            return this.c.getIntrinsicWidth();
        }

        return this.d.b.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        if(this.c != null) {
            return this.c.getOpacity();
        }

        return this.d.b.getOpacity();
    }

    public boolean getPadding(Rect arg1) {
        return super.getPadding(arg1);
    }

    public int[] getState() {
        return super.getState();
    }

    public Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources arg7, XmlPullParser arg8, AttributeSet arg9, Resources$Theme arg10) {
        TypedArray v0_2;
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg7, arg8, arg9, arg10);
            return;
        }

        int v0 = arg8.getEventType();
        int v1 = arg8.getDepth() + 1;
        while(v0 != 1) {
            if(arg8.getDepth() < v1 && v0 == 3) {
                break;
            }

            if(v0 == 2) {
                String v0_1 = arg8.getName();
                if("animated-vector".equals(v0_1)) {
                    v0_2 = g.a(arg7, arg10, arg9, android.support.c.a.a.e);
                    int v3 = v0_2.getResourceId(0, 0);
                    if(v3 != 0) {
                        i v3_1 = i.a(arg7, v3, arg10);
                        v3_1.a(false);
                        v3_1.setCallback(this.b);
                        if(this.d.b != null) {
                            this.d.b.setCallback(null);
                        }

                        this.d.b = v3_1;
                    }
                }
                else {
                    if(!"target".equals(v0_1)) {
                        goto label_59;
                    }

                    v0_2 = arg7.obtainAttributes(arg9, android.support.c.a.a.f);
                    String v3_2 = v0_2.getString(0);
                    int v4 = v0_2.getResourceId(1, 0);
                    if(v4 == 0) {
                        goto label_38;
                    }

                    if(this.e != null) {
                        this.a(v3_2, e.a(this.e, v4));
                        goto label_38;
                    }

                    v0_2.recycle();
                    throw new IllegalStateException("Context can\'t be null when inflating animators");
                }

            label_38:
                v0_2.recycle();
            }

        label_59:
            v0 = arg8.next();
        }

        this.d.a();
    }

    public void inflate(Resources arg2, XmlPullParser arg3, AttributeSet arg4) {
        this.inflate(arg2, arg3, arg4, null);
    }

    public boolean isAutoMirrored() {
        if(this.c != null) {
            return android.support.v4.graphics.drawable.a.b(this.c);
        }

        return this.d.b.isAutoMirrored();
    }

    public boolean isRunning() {
        if(this.c != null) {
            return this.c.isRunning();
        }

        return this.d.c.isRunning();
    }

    public boolean isStateful() {
        if(this.c != null) {
            return this.c.isStateful();
        }

        return this.d.b.isStateful();
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        if(this.c != null) {
            this.c.mutate();
        }

        return this;
    }

    protected void onBoundsChange(Rect arg2) {
        if(this.c != null) {
            this.c.setBounds(arg2);
            return;
        }

        this.d.b.setBounds(arg2);
    }

    protected boolean onLevelChange(int arg2) {
        if(this.c != null) {
            return this.c.setLevel(arg2);
        }

        return this.d.b.setLevel(arg2);
    }

    protected boolean onStateChange(int[] arg2) {
        if(this.c != null) {
            return this.c.setState(arg2);
        }

        return this.d.b.setState(arg2);
    }

    public void setAlpha(int arg2) {
        if(this.c != null) {
            this.c.setAlpha(arg2);
            return;
        }

        this.d.b.setAlpha(arg2);
    }

    public void setAutoMirrored(boolean arg2) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2);
            return;
        }

        this.d.b.setAutoMirrored(arg2);
    }

    public void setChangingConfigurations(int arg1) {
        super.setChangingConfigurations(arg1);
    }

    public void setColorFilter(int arg1, PorterDuff$Mode arg2) {
        super.setColorFilter(arg1, arg2);
    }

    public void setColorFilter(ColorFilter arg2) {
        if(this.c != null) {
            this.c.setColorFilter(arg2);
            return;
        }

        this.d.b.setColorFilter(arg2);
    }

    public void setFilterBitmap(boolean arg1) {
        super.setFilterBitmap(arg1);
    }

    public void setHotspot(float arg1, float arg2) {
        super.setHotspot(arg1, arg2);
    }

    public void setHotspotBounds(int arg1, int arg2, int arg3, int arg4) {
        super.setHotspotBounds(arg1, arg2, arg3, arg4);
    }

    public boolean setState(int[] arg1) {
        return super.setState(arg1);
    }

    public void setTint(int arg2) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2);
            return;
        }

        this.d.b.setTint(arg2);
    }

    public void setTintList(ColorStateList arg2) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2);
            return;
        }

        this.d.b.setTintList(arg2);
    }

    public void setTintMode(PorterDuff$Mode arg2) {
        if(this.c != null) {
            android.support.v4.graphics.drawable.a.a(this.c, arg2);
            return;
        }

        this.d.b.setTintMode(arg2);
    }

    public boolean setVisible(boolean arg2, boolean arg3) {
        if(this.c != null) {
            return this.c.setVisible(arg2, arg3);
        }

        this.d.b.setVisible(arg2, arg3);
        return super.setVisible(arg2, arg3);
    }

    public void start() {
        if(this.c != null) {
            this.c.start();
            return;
        }

        if(this.d.c.isStarted()) {
            return;
        }

        this.d.c.start();
        this.invalidateSelf();
    }

    public void stop() {
        if(this.c != null) {
            this.c.stop();
            return;
        }

        this.d.c.end();
    }
}

