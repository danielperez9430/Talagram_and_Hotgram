package android.support.v4.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.a.a$c;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.PointerIcon;
import android.view.View$AccessibilityDelegate;
import android.view.View$OnApplyWindowInsetsListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class t {
    public interface a {
        boolean a(View arg1, KeyEvent arg2);
    }

    class b {
        private static final ArrayList a;
        private WeakHashMap b;
        private SparseArray c;
        private WeakReference d;

        static {
            b.a = new ArrayList();
        }

        b() {
            super();
            this.b = null;
            this.c = null;
            this.d = null;
        }

        boolean a(KeyEvent arg6) {
            if(this.d != null && this.d.get() == arg6) {
                return 0;
            }

            this.d = new WeakReference(arg6);
            Object v0 = null;
            SparseArray v2 = this.a();
            if(arg6.getAction() == 1) {
                int v3 = v2.indexOfKey(arg6.getKeyCode());
                if(v3 >= 0) {
                    v0 = v2.valueAt(v3);
                    v2.removeAt(v3);
                }
            }

            if(v0 == null) {
                v0 = v2.get(arg6.getKeyCode());
            }

            if(v0 != null) {
                v0 = ((WeakReference)v0).get();
                if(v0 != null && (t.D(((View)v0)))) {
                    this.c(((View)v0), arg6);
                }

                return 1;
            }

            return 0;
        }

        static b a(View arg2) {
            Object v0 = arg2.getTag(c.tag_unhandled_key_event_manager);
            if(v0 == null) {
                b v0_1 = new b();
                arg2.setTag(c.tag_unhandled_key_event_manager, v0_1);
            }

            return ((b)v0);
        }

        boolean a(View arg3, KeyEvent arg4) {
            if(arg4.getAction() == 0) {
                this.b();
            }

            arg3 = this.b(arg3, arg4);
            if(arg4.getAction() == 0) {
                int v4 = arg4.getKeyCode();
                if(arg3 != null && !KeyEvent.isModifierKey(v4)) {
                    this.a().put(v4, new WeakReference(arg3));
                }
            }

            boolean v3 = arg3 != null ? true : false;
            return v3;
        }

        private SparseArray a() {
            if(this.c == null) {
                this.c = new SparseArray();
            }

            return this.c;
        }

        private View b(View arg5, KeyEvent arg6) {
            View v1 = null;
            if(this.b != null) {
                if(!this.b.containsKey(arg5)) {
                }
                else {
                    if((arg5 instanceof ViewGroup)) {
                        View v0 = arg5;
                        int v2 = ((ViewGroup)v0).getChildCount() - 1;
                        while(v2 >= 0) {
                            View v3 = this.b(((ViewGroup)v0).getChildAt(v2), arg6);
                            if(v3 != null) {
                                return v3;
                            }
                            else {
                                --v2;
                                continue;
                            }

                            break;
                        }
                    }

                    if(!this.c(arg5, arg6)) {
                        return v1;
                    }

                    return arg5;
                }
            }

            return v1;
        }

        private void b() {
            if(this.b != null) {
                this.b.clear();
            }

            if(b.a.isEmpty()) {
                return;
            }

            ArrayList v0 = b.a;
            __monitor_enter(v0);
            try {
                if(this.b == null) {
                    this.b = new WeakHashMap();
                }

                int v1_1;
                for(v1_1 = b.a.size() - 1; v1_1 >= 0; --v1_1) {
                    Object v2 = b.a.get(v1_1).get();
                    if(v2 == null) {
                        b.a.remove(v1_1);
                    }
                    else {
                        this.b.put(v2, Boolean.TRUE);
                        ViewParent v2_1;
                        for(v2_1 = ((View)v2).getParent(); (v2_1 instanceof View); v2_1 = v2_1.getParent()) {
                            this.b.put(v2_1, Boolean.TRUE);
                        }
                    }
                }

                __monitor_exit(v0);
                return;
            label_43:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_43;
            }

            throw v1;
        }

        private boolean c(View arg5, KeyEvent arg6) {
            Object v0 = arg5.getTag(c.tag_unhandled_key_listeners);
            if(v0 != null) {
                int v1 = ((ArrayList)v0).size() - 1;
                while(v1 >= 0) {
                    if(((ArrayList)v0).get(v1).a(arg5, arg6)) {
                        return 1;
                    }
                    else {
                        --v1;
                        continue;
                    }

                    return 0;
                }
            }

            return 0;
        }
    }

    private static final AtomicInteger a;
    private static Field b;
    private static boolean c;
    private static Field d;
    private static boolean e;
    private static WeakHashMap f;
    private static WeakHashMap g;
    private static Field h;
    private static boolean i;
    private static ThreadLocal j;

    static {
        t.a = new AtomicInteger(1);
        t.g = null;
        t.i = false;
    }

    public static boolean A(View arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.isLaidOut();
        }

        boolean v2 = arg2.getWidth() <= 0 || arg2.getHeight() <= 0 ? false : true;
        return v2;
    }

    public static float B(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getZ();
        }

        return 0;
    }

    public static Rect C(View arg2) {
        if(Build$VERSION.SDK_INT >= 18) {
            return arg2.getClipBounds();
        }

        return null;
    }

    public static boolean D(View arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.isAttachedToWindow();
        }

        boolean v2 = arg2.getWindowToken() != null ? true : false;
        return v2;
    }

    public static boolean E(View arg2) {
        if(Build$VERSION.SDK_INT >= 15) {
            return arg2.hasOnClickListeners();
        }

        return 0;
    }

    public static Display F(View arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getDisplay();
        }

        if(t.D(arg2)) {
            return arg2.getContext().getSystemService("window").getDefaultDisplay();
        }

        return null;
    }

    private static void G(View arg2) {
        float v0 = arg2.getTranslationY();
        arg2.setTranslationY(1f + v0);
        arg2.setTranslationY(v0);
    }

    public static void a(View arg2, int arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 17) {
            arg2.setPaddingRelative(arg3, arg4, arg5, arg6);
        }
        else {
            arg2.setPadding(arg3, arg4, arg5, arg6);
        }
    }

    public static void a(View arg2, Drawable arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.setBackground(arg3);
        }
        else {
            arg2.setBackgroundDrawable(arg3);
        }
    }

    public static void a(View arg2, p arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            if(arg3 == null) {
                arg2.setOnApplyWindowInsetsListener(null);
                return;
            }
            else {
                arg2.setOnApplyWindowInsetsListener(new View$OnApplyWindowInsetsListener(arg3) {
                    public WindowInsets onApplyWindowInsets(View arg2, WindowInsets arg3) {
                        return ab.a(this.a.a(arg2, ab.a(arg3)));
                    }
                });
            }
        }
    }

    public static void a(View arg0, android.support.v4.view.a arg1) {
        View$AccessibilityDelegate v1 = arg1 == null ? null : arg1.getBridge();
        arg0.setAccessibilityDelegate(v1);
    }

    public static void a(View arg2, r arg3) {
        PointerIcon v3_1;
        if(Build$VERSION.SDK_INT >= 24) {
            if(arg3 != null) {
                Object v3 = arg3.a();
            }
            else {
                v3_1 = null;
            }

            arg2.setPointerIcon(v3_1);
        }
    }

    public static void a(View arg2, Rect arg3) {
        if(Build$VERSION.SDK_INT >= 18) {
            arg2.setClipBounds(arg3);
        }
    }

    public static void a(View arg2, boolean arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.setHasTransientState(arg3);
        }
    }

    public static void a(View arg2, String arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setTransitionName(arg3);
        }
        else {
            if(t.f == null) {
                t.f = new WeakHashMap();
            }

            t.f.put(arg2, arg3);
        }
    }

    public static void a(View arg2, Runnable arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.postOnAnimation(arg3);
        }
        else {
            arg2.postDelayed(arg3, ValueAnimator.getFrameDelay());
        }
    }

    public static ab a(View arg2, ab arg3) {
        WindowInsets v3_1;
        if(Build$VERSION.SDK_INT >= 21) {
            Object v3 = ab.a(arg3);
            WindowInsets v2 = arg2.onApplyWindowInsets(((WindowInsets)v3));
            if(v2 != v3) {
                v3_1 = new WindowInsets(v2);
            }

            return ab.a(v3_1);
        }

        return arg3;
    }

    static boolean a(View arg2, KeyEvent arg3) {
        if(Build$VERSION.SDK_INT >= 28) {
            return 0;
        }

        return b.a(arg2).a(arg3);
    }

    @SuppressLint(value={"InlinedApi"}) public static int a(View arg2) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getImportantForAutofill();
        }

        return 0;
    }

    private static Rect a() {
        Rect v0_1;
        if(t.j == null) {
            t.j = new ThreadLocal();
        }

        Object v0 = t.j.get();
        if(v0 == null) {
            v0_1 = new Rect();
            t.j.set(v0_1);
        }

        v0_1.setEmpty();
        return v0_1;
    }

    @Deprecated public static void a(View arg0, float arg1) {
        arg0.setTranslationX(arg1);
    }

    public static void a(View arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            arg2.setImportantForAutofill(arg3);
        }
    }

    public static void a(View arg2, int arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg2.setScrollIndicators(arg3, arg4);
        }
    }

    public static void a(View arg2, ColorStateList arg3) {
        int v1 = 21;
        if(Build$VERSION.SDK_INT >= v1) {
            arg2.setBackgroundTintList(arg3);
            if(Build$VERSION.SDK_INT == v1) {
                Drawable v3 = arg2.getBackground();
                int v0 = arg2.getBackgroundTintList() != null || arg2.getBackgroundTintMode() != null ? 1 : 0;
                if(v3 == null) {
                    return;
                }

                if(v0 == 0) {
                    return;
                }

                if(v3.isStateful()) {
                    v3.setState(arg2.getDrawableState());
                }

                arg2.setBackground(v3);
            }
        }
        else {
            if(!(arg2 instanceof s)) {
                return;
            }

            ((s)arg2).setSupportBackgroundTintList(arg3);
        }
    }

    public static void a(View arg2, PorterDuff$Mode arg3) {
        int v1 = 21;
        if(Build$VERSION.SDK_INT >= v1) {
            arg2.setBackgroundTintMode(arg3);
            if(Build$VERSION.SDK_INT == v1) {
                Drawable v3 = arg2.getBackground();
                int v0 = arg2.getBackgroundTintList() != null || arg2.getBackgroundTintMode() != null ? 1 : 0;
                if(v3 == null) {
                    return;
                }

                if(v0 == 0) {
                    return;
                }

                if(v3.isStateful()) {
                    v3.setState(arg2.getDrawableState());
                }

                arg2.setBackground(v3);
            }
        }
        else {
            if(!(arg2 instanceof s)) {
                return;
            }

            ((s)arg2).setSupportBackgroundTintMode(arg3);
        }
    }

    public static void a(View arg0, android.support.v4.view.a.c arg1) {
        arg0.onInitializeAccessibilityNodeInfo(arg1.a());
    }

    public static void a(View arg2, Runnable arg3, long arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.postOnAnimationDelayed(arg3, arg4);
        }
        else {
            arg2.postDelayed(arg3, ValueAnimator.getFrameDelay() + arg4);
        }
    }

    public static boolean a(View arg2, int arg3, Bundle arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.performAccessibilityAction(arg3, arg4);
        }

        return 0;
    }

    public static void b(View arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
        label_3:
            arg2.setImportantForAccessibility(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            if(arg3 == 4) {
                arg3 = 2;
            }
            else {
            }

            goto label_3;
        }
    }

    public static ab b(View arg2, ab arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            Object v3 = ab.a(arg3);
            WindowInsets v2 = arg2.dispatchApplyWindowInsets(((WindowInsets)v3));
            if(v2 != v3) {
                WindowInsets v3_1 = new WindowInsets(v2);
            }

            return ab.a(v3);
        }

        return arg3;
    }

    static boolean b(View arg2, KeyEvent arg3) {
        if(Build$VERSION.SDK_INT >= 28) {
            return 0;
        }

        return b.a(arg2).a(arg2, arg3);
    }

    @Deprecated public static void b(View arg0, float arg1) {
        arg0.setTranslationY(arg1);
    }

    @Deprecated public static void b(View arg0, boolean arg1) {
        arg0.setFitsSystemWindows(arg1);
    }

    public static boolean b(View arg4) {
        boolean v1 = false;
        if(t.i) {
            return 0;
        }

        if(t.h == null) {
            try {
                t.h = View.class.getDeclaredField("mAccessibilityDelegate");
                t.h.setAccessible(true);
            }
            catch(Throwable ) {
                t.i = true;
                return 0;
            }
        }

        try {
            if(t.h.get(arg4) == null) {
                return v1;
            }
        }
        catch(Throwable ) {
            t.i = true;
            return 0;
        }

        return true;
    }

    public static void c(View arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            arg2.setAccessibilityLiveRegion(arg3);
        }
    }

    @Deprecated public static void c(View arg0, float arg1) {
        arg0.setAlpha(arg1);
    }

    public static boolean c(View arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.hasTransientState();
        }

        return 0;
    }

    public static void d(View arg1, int arg2) {
        if((arg1 instanceof k)) {
            ((k)arg1).stopNestedScroll(arg2);
        }
        else if(arg2 == 0) {
            t.z(arg1);
        }
    }

    public static void d(View arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.postInvalidateOnAnimation();
        }
        else {
            arg2.postInvalidate();
        }
    }

    @Deprecated public static void d(View arg0, float arg1) {
        arg0.setRotation(arg1);
    }

    public static void e(View arg6, int arg7) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg6.offsetTopAndBottom(arg7);
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            Rect v0 = t.a();
            int v1 = 0;
            ViewParent v2 = arg6.getParent();
            if((v2 instanceof View)) {
                v0.set(v2.getLeft(), v2.getTop(), v2.getRight(), v2.getBottom());
                v1 = v0.intersects(arg6.getLeft(), arg6.getTop(), arg6.getRight(), arg6.getBottom()) ^ 1;
            }

            t.g(arg6, arg7);
            if(v1 == 0) {
                return;
            }

            if(!v0.intersect(arg6.getLeft(), arg6.getTop(), arg6.getRight(), arg6.getBottom())) {
                return;
            }

            ((View)v2).invalidate(v0);
        }
        else {
            t.g(arg6, arg7);
        }
    }

    public static int e(View arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.getImportantForAccessibility();
        }

        return 0;
    }

    @Deprecated public static void e(View arg0, float arg1) {
        arg0.setRotationX(arg1);
    }

    public static int f(View arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getLayoutDirection();
        }

        return 0;
    }

    public static void f(View arg6, int arg7) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg6.offsetLeftAndRight(arg7);
        }
        else if(Build$VERSION.SDK_INT >= 21) {
            Rect v0 = t.a();
            int v1 = 0;
            ViewParent v2 = arg6.getParent();
            if((v2 instanceof View)) {
                v0.set(v2.getLeft(), v2.getTop(), v2.getRight(), v2.getBottom());
                v1 = v0.intersects(arg6.getLeft(), arg6.getTop(), arg6.getRight(), arg6.getBottom()) ^ 1;
            }

            t.h(arg6, arg7);
            if(v1 == 0) {
                return;
            }

            if(!v0.intersect(arg6.getLeft(), arg6.getTop(), arg6.getRight(), arg6.getBottom())) {
                return;
            }

            ((View)v2).invalidate(v0);
        }
        else {
            t.h(arg6, arg7);
        }
    }

    @Deprecated public static void f(View arg0, float arg1) {
        arg0.setRotationY(arg1);
    }

    public static int g(View arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.getAccessibilityLiveRegion();
        }

        return 0;
    }

    private static void g(View arg0, int arg1) {
        arg0.offsetTopAndBottom(arg1);
        if(arg0.getVisibility() == 0) {
            t.G(arg0);
            ViewParent v0 = arg0.getParent();
            if((v0 instanceof View)) {
                t.G(((View)v0));
            }
        }
    }

    @Deprecated public static void g(View arg0, float arg1) {
        arg0.setScaleX(arg1);
    }

    public static int h(View arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getPaddingStart();
        }

        return arg2.getPaddingLeft();
    }

    private static void h(View arg0, int arg1) {
        arg0.offsetLeftAndRight(arg1);
        if(arg0.getVisibility() == 0) {
            t.G(arg0);
            ViewParent v0 = arg0.getParent();
            if((v0 instanceof View)) {
                t.G(((View)v0));
            }
        }
    }

    @Deprecated public static void h(View arg0, float arg1) {
        arg0.setScaleY(arg1);
    }

    public static int i(View arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.getPaddingEnd();
        }

        return arg2.getPaddingRight();
    }

    @Deprecated public static void i(View arg0, float arg1) {
        arg0.setPivotX(arg1);
    }

    @Deprecated public static float j(View arg0) {
        return arg0.getTranslationY();
    }

    @Deprecated public static void j(View arg0, float arg1) {
        arg0.setPivotY(arg1);
    }

    public static void k(View arg2, float arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.setElevation(arg3);
        }
    }

    public static int k(View arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg3.getMinimumWidth();
        }

        if(!t.c) {
            try {
                t.b = View.class.getDeclaredField("mMinWidth");
                t.b.setAccessible(true);
                goto label_14;
            }
            catch(NoSuchFieldException ) {
            label_14:
                t.c = true;
            }
        }

        if(t.b == null) {
            return 0;
        }

        try {
            return t.b.get(arg3).intValue();
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static int l(View arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg3.getMinimumHeight();
        }

        if(!t.e) {
            try {
                t.d = View.class.getDeclaredField("mMinHeight");
                t.d.setAccessible(true);
                goto label_14;
            }
            catch(NoSuchFieldException ) {
            label_14:
                t.e = true;
            }
        }

        if(t.d == null) {
            return 0;
        }

        try {
            return t.d.get(arg3).intValue();
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static x m(View arg2) {
        x v0_1;
        if(t.g == null) {
            t.g = new WeakHashMap();
        }

        Object v0 = t.g.get(arg2);
        if(v0 == null) {
            v0_1 = new x(arg2);
            t.g.put(arg2, v0_1);
        }

        return v0_1;
    }

    @Deprecated public static float n(View arg0) {
        return arg0.getX();
    }

    @Deprecated public static float o(View arg0) {
        return arg0.getY();
    }

    public static float p(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getElevation();
        }

        return 0;
    }

    public static String q(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getTransitionName();
        }

        if(t.f == null) {
            return null;
        }

        return t.f.get(arg2);
    }

    public static int r(View arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.getWindowSystemUiVisibility();
        }

        return 0;
    }

    public static void s(View arg2) {
        if(Build$VERSION.SDK_INT >= 20) {
            arg2.requestApplyInsets();
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            arg2.requestFitSystemWindows();
        }
    }

    public static boolean t(View arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.getFitsSystemWindows();
        }

        return 0;
    }

    public static boolean u(View arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.hasOverlappingRendering();
        }

        return 1;
    }

    public static boolean v(View arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return arg2.isPaddingRelative();
        }

        return 0;
    }

    public static ColorStateList w(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getBackgroundTintList();
        }

        ColorStateList v2 = (arg2 instanceof s) ? ((s)arg2).getSupportBackgroundTintList() : null;
        return v2;
    }

    public static PorterDuff$Mode x(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getBackgroundTintMode();
        }

        PorterDuff$Mode v2 = (arg2 instanceof s) ? ((s)arg2).getSupportBackgroundTintMode() : null;
        return v2;
    }

    public static boolean y(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.isNestedScrollingEnabled();
        }

        if((arg2 instanceof j)) {
            return ((j)arg2).isNestedScrollingEnabled();
        }

        return 0;
    }

    public static void z(View arg2) {
        if(Build$VERSION.SDK_INT >= 21) {
            arg2.stopNestedScroll();
        }
        else if((arg2 instanceof j)) {
            ((j)arg2).stopNestedScroll();
        }
    }
}

