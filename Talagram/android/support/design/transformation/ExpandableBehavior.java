package android.support.design.transformation;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout$b;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.List;

public abstract class ExpandableBehavior extends b {
    private int a;

    public ExpandableBehavior() {
        super();
        this.a = 0;
    }

    public ExpandableBehavior(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.a = 0;
    }

    static int a(ExpandableBehavior arg0) {
        return arg0.a;
    }

    private boolean a(boolean arg4) {
        boolean v0 = false;
        if(arg4) {
            if(this.a == 0 || this.a == 2) {
                v0 = true;
            }

            return v0;
        }

        if(this.a == 1) {
            v0 = true;
        }

        return v0;
    }

    protected android.support.design.d.b a(CoordinatorLayout arg6, View arg7) {
        List v0 = arg6.c(arg7);
        int v1 = v0.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = v0.get(v2);
            if(this.a(arg6, arg7, ((View)v3))) {
                return ((android.support.design.d.b)v3);
            }
        }

        return null;
    }

    public abstract boolean a(CoordinatorLayout arg1, View arg2, View arg3);

    public boolean a(CoordinatorLayout arg3, View arg4, int arg5) {
        if(!t.A(arg4)) {
            android.support.design.d.b v3 = this.a(arg3, arg4);
            if(v3 != null && (this.a(v3.a()))) {
                arg5 = v3.a() ? 1 : 2;
                this.a = arg5;
                arg4.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver$OnPreDrawListener(arg4, this.a, v3) {
                    public boolean onPreDraw() {
                        this.a.getViewTreeObserver().removeOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
                        if(ExpandableBehavior.a(this.d) == this.b) {
                            this.d.a(this.c, this.a, this.c.a(), false);
                        }

                        return 0;
                    }
                });
            }
        }

        return 0;
    }

    protected abstract boolean a(View arg1, View arg2, boolean arg3, boolean arg4);

    public boolean b(CoordinatorLayout arg2, View arg3, View arg4) {
        if(this.a(((android.support.design.d.b)arg4).a())) {
            int v2 = ((android.support.design.d.b)arg4).a() ? 1 : 2;
            this.a = v2;
            return this.a(arg4, arg3, ((android.support.design.d.b)arg4).a(), true);
        }

        return 0;
    }
}

