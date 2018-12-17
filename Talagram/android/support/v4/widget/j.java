package android.support.v4.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.a.c;
import android.support.v4.view.a.d;
import android.support.v4.view.a.e;
import android.support.v4.view.a;
import android.support.v4.view.t;
import android.support.v4.view.w;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityRecord;
import java.util.ArrayList;
import java.util.List;

public abstract class j extends a {
    final class android.support.v4.widget.j$1 implements android.support.v4.widget.k$a {
        android.support.v4.widget.j$1() {
            super();
        }
    }

    final class android.support.v4.widget.j$2 implements b {
        android.support.v4.widget.j$2() {
            super();
        }
    }

    class android.support.v4.widget.j$a extends d {
        android.support.v4.widget.j$a(j arg1) {
            this.a = arg1;
            super();
        }

        public c a(int arg2) {
            return c.a(this.a.a(arg2));
        }

        public boolean a(int arg2, int arg3, Bundle arg4) {
            return this.a.a(arg2, arg3, arg4);
        }

        public c b(int arg2) {
            arg2 = arg2 == 2 ? this.a.a : this.a.b;
            if(arg2 == -2147483648) {
                return null;
            }

            return this.a(arg2);
        }
    }

    int a;
    int b;
    private static final Rect c;
    private final Rect d;
    private final Rect e;
    private final Rect f;
    private final int[] g;
    private final AccessibilityManager h;
    private final View i;
    private android.support.v4.widget.j$a j;
    private int k;
    private static final android.support.v4.widget.k$a l;
    private static final b m;

    static {
        j.c = new Rect(2147483647, 2147483647, -2147483648, -2147483648);
        j.l = new android.support.v4.widget.j$1();
        j.m = new android.support.v4.widget.j$2();
    }

    public j(View arg3) {
        super();
        this.d = new Rect();
        this.e = new Rect();
        this.f = new Rect();
        this.g = new int[2];
        this.a = -2147483648;
        this.b = -2147483648;
        this.k = -2147483648;
        if(arg3 != null) {
            this.i = arg3;
            this.h = arg3.getContext().getSystemService("accessibility");
            arg3.setFocusable(true);
            if(t.e(arg3) == 0) {
                t.b(arg3, 1);
            }

            return;
        }

        throw new IllegalArgumentException("View may not be null");
    }

    private boolean a(int arg2, Bundle arg3) {
        return t.a(this.i, arg2, arg3);
    }

    private boolean a(Rect arg4) {
        boolean v0 = false;
        if(arg4 != null) {
            if(arg4.isEmpty()) {
            }
            else if(this.i.getWindowVisibility() != 0) {
                return 0;
            }
            else {
                View v4 = this.i;
                do {
                    ViewParent v4_1 = v4.getParent();
                    if((v4_1 instanceof View)) {
                        if(((View)v4_1).getAlpha() > 0f && ((View)v4_1).getVisibility() == 0) {
                            continue;
                        }

                        return 0;
                    }
                    else {
                        goto label_19;
                    }
                }
                while(true);

                return 0;
            label_19:
                if(v4_1 == null) {
                    return v0;
                }

                v0 = true;
            }
        }

        return v0;
    }

    protected abstract void a(List arg1);

    c a(int arg2) {
        if(arg2 == -1) {
            return this.d();
        }

        return this.f(arg2);
    }

    protected void a(int arg1, AccessibilityEvent arg2) {
    }

    public final boolean a(int arg3, int arg4) {
        if(arg3 != -2147483648) {
            if(!this.h.isEnabled()) {
            }
            else {
                ViewParent v1 = this.i.getParent();
                if(v1 == null) {
                    return 0;
                }
                else {
                    return w.a(v1, this.i, this.c(arg3, arg4));
                }
            }
        }

        return 0;
    }

    protected abstract void a(int arg1, c arg2);

    public final int a() {
        return this.a;
    }

    protected abstract int a(float arg1, float arg2);

    protected void a(int arg1, boolean arg2) {
    }

    protected void a(c arg1) {
    }

    protected void a(AccessibilityEvent arg1) {
    }

    boolean a(int arg2, int arg3, Bundle arg4) {
        if(arg2 != -1) {
            return this.c(arg2, arg3, arg4);
        }

        return this.a(arg3, arg4);
    }

    public final boolean a(MotionEvent arg6) {
        boolean v1 = false;
        if(this.h.isEnabled()) {
            if(!this.h.isTouchExplorationEnabled()) {
            }
            else {
                int v0 = arg6.getAction();
                int v4 = -2147483648;
                if(v0 != 7) {
                    switch(v0) {
                        case 9: {
                            goto label_20;
                        }
                        case 10: {
                            goto label_15;
                        }
                    }

                    return 0;
                label_15:
                    if(this.k != v4) {
                        this.d(v4);
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }

            label_20:
                int v6 = this.a(arg6.getX(), arg6.getY());
                this.d(v6);
                if(v6 == v4) {
                    return v1;
                }

                v1 = true;
            }
        }

        return v1;
    }

    protected abstract boolean b(int arg1, int arg2, Bundle arg3);

    public final boolean b(int arg3) {
        if(!this.i.isFocused() && !this.i.requestFocus()) {
            return 0;
        }

        if(this.b == arg3) {
            return 0;
        }

        if(this.b != -2147483648) {
            this.c(this.b);
        }

        this.b = arg3;
        this.a(arg3, true);
        this.a(arg3, 8);
        return 1;
    }

    public final void b() {
        this.b(-1, 1);
    }

    public final void b(int arg3, int arg4) {
        if(arg3 != -2147483648 && (this.h.isEnabled())) {
            ViewParent v0 = this.i.getParent();
            if(v0 != null) {
                AccessibilityEvent v3 = this.c(arg3, 2048);
                android.support.v4.view.a.a.a(v3, arg4);
                w.a(v0, this.i, v3);
            }
        }
    }

    private AccessibilityEvent c(int arg2, int arg3) {
        if(arg2 != -1) {
            return this.d(arg2, arg3);
        }

        return this.e(arg3);
    }

    private boolean c(int arg2, int arg3, Bundle arg4) {
        if(arg3 == 64) {
            goto label_13;
        }

        if(arg3 == 128) {
            goto label_11;
        }

        switch(arg3) {
            case 1: {
                goto label_9;
            }
            case 2: {
                goto label_7;
            }
        }

        return this.b(arg2, arg3, arg4);
    label_7:
        return this.c(arg2);
    label_9:
        return this.b(arg2);
    label_11:
        return this.h(arg2);
    label_13:
        return this.g(arg2);
    }

    public final boolean c(int arg3) {
        if(this.b != arg3) {
            return 0;
        }

        this.b = -2147483648;
        this.a(arg3, false);
        this.a(arg3, 8);
        return 1;
    }

    @Deprecated public int c() {
        return this.a();
    }

    private AccessibilityEvent d(int arg4, int arg5) {
        AccessibilityEvent v5 = AccessibilityEvent.obtain(arg5);
        c v0 = this.a(arg4);
        v5.getText().add(v0.q());
        v5.setContentDescription(v0.r());
        v5.setScrollable(v0.n());
        v5.setPassword(v0.m());
        v5.setEnabled(v0.l());
        v5.setChecked(v0.f());
        this.a(arg4, v5);
        if(v5.getText().isEmpty()) {
            if(v5.getContentDescription() != null) {
            }
            else {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            }
        }

        v5.setClassName(v0.p());
        e.a(((AccessibilityRecord)v5), this.i, arg4);
        v5.setPackageName(this.i.getContext().getPackageName());
        return v5;
    }

    private c d() {
        c v0 = c.a(this.i);
        t.a(this.i, v0);
        ArrayList v1 = new ArrayList();
        this.a(((List)v1));
        if(v0.c() > 0) {
            if(v1.size() <= 0) {
            }
            else {
                throw new RuntimeException("Views cannot have both real and virtual children");
            }
        }

        int v2 = 0;
        int v3 = v1.size();
        while(v2 < v3) {
            v0.b(this.i, v1.get(v2).intValue());
            ++v2;
        }

        return v0;
    }

    private void d(int arg3) {
        if(this.k == arg3) {
            return;
        }

        int v0 = this.k;
        this.k = arg3;
        this.a(arg3, 128);
        this.a(v0, 256);
    }

    private AccessibilityEvent e(int arg2) {
        AccessibilityEvent v2 = AccessibilityEvent.obtain(arg2);
        this.i.onInitializeAccessibilityEvent(v2);
        return v2;
    }

    private c f(int arg8) {
        c v0 = c.b();
        v0.h(true);
        v0.c(true);
        v0.b("android.view.View");
        v0.b(j.c);
        v0.d(j.c);
        v0.b(this.i);
        this.a(arg8, v0);
        if(v0.q() == null) {
            if(v0.r() != null) {
            }
            else {
                throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
            }
        }

        v0.a(this.e);
        if(!this.e.equals(j.c)) {
            int v2 = v0.d();
            if((v2 & 64) == 0) {
                int v3 = 128;
                if((v2 & v3) == 0) {
                    v0.a(this.i.getContext().getPackageName());
                    v0.a(this.i, arg8);
                    if(this.a == arg8) {
                        v0.f(true);
                        v0.a(v3);
                    }
                    else {
                        v0.f(false);
                        v0.a(64);
                    }

                    boolean v8 = this.b == arg8 ? true : false;
                    if(v8) {
                        v0.a(2);
                    }
                    else if(v0.g()) {
                        v0.a(1);
                    }

                    v0.d(v8);
                    this.i.getLocationOnScreen(this.g);
                    v0.c(this.d);
                    if(this.d.equals(j.c)) {
                        v0.a(this.d);
                        v2 = -1;
                        if(v0.a != v2) {
                            c v8_1 = c.b();
                            for(v3 = v0.a; v3 != v2; v3 = v8_1.a) {
                                v8_1.c(this.i, v2);
                                v8_1.b(j.c);
                                this.a(v3, v8_1);
                                v8_1.a(this.e);
                                this.d.offset(this.e.left, this.e.top);
                            }

                            v8_1.s();
                        }

                        this.d.offset(this.g[0] - this.i.getScrollX(), this.g[1] - this.i.getScrollY());
                    }

                    if(this.i.getLocalVisibleRect(this.f)) {
                        this.f.offset(this.g[0] - this.i.getScrollX(), this.g[1] - this.i.getScrollY());
                        if(this.d.intersect(this.f)) {
                            v0.d(this.d);
                            if(this.a(this.d)) {
                                v0.e(true);
                            }
                        }
                    }

                    return v0;
                }

                throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }

            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }

        throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
    }

    private boolean g(int arg3) {
        if(this.h.isEnabled()) {
            if(!this.h.isTouchExplorationEnabled()) {
            }
            else if(this.a != arg3) {
                if(this.a != -2147483648) {
                    this.h(this.a);
                }

                this.a = arg3;
                this.i.invalidate();
                this.a(arg3, 32768);
                return 1;
            }
        }

        return 0;
    }

    public d getAccessibilityNodeProvider(View arg1) {
        if(this.j == null) {
            this.j = new android.support.v4.widget.j$a(this);
        }

        return this.j;
    }

    private boolean h(int arg2) {
        if(this.a == arg2) {
            this.a = -2147483648;
            this.i.invalidate();
            this.a(arg2, 65536);
            return 1;
        }

        return 0;
    }

    public void onInitializeAccessibilityEvent(View arg1, AccessibilityEvent arg2) {
        super.onInitializeAccessibilityEvent(arg1, arg2);
        this.a(arg2);
    }

    public void onInitializeAccessibilityNodeInfo(View arg1, c arg2) {
        super.onInitializeAccessibilityNodeInfo(arg1, arg2);
        this.a(arg2);
    }
}

