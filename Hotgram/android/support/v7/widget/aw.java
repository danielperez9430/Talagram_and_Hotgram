package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.a.c;
import android.support.v4.view.a;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class aw extends a {
    public class android.support.v7.widget.aw$a extends a {
        final aw a;

        public android.support.v7.widget.aw$a(aw arg1) {
            super();
            this.a = arg1;
        }

        public void onInitializeAccessibilityNodeInfo(View arg2, c arg3) {
            super.onInitializeAccessibilityNodeInfo(arg2, arg3);
            if(!this.a.a() && this.a.a.getLayoutManager() != null) {
                this.a.a.getLayoutManager().a(arg2, arg3);
            }
        }

        public boolean performAccessibilityAction(View arg2, int arg3, Bundle arg4) {
            if(super.performAccessibilityAction(arg2, arg3, arg4)) {
                return 1;
            }

            if(!this.a.a() && this.a.a.getLayoutManager() != null) {
                return this.a.a.getLayoutManager().a(arg2, arg3, arg4);
            }

            return 0;
        }
    }

    final RecyclerView a;
    final a b;

    public aw(RecyclerView arg1) {
        super();
        this.a = arg1;
        this.b = new android.support.v7.widget.aw$a(this);
    }

    boolean a() {
        return this.a.v();
    }

    public a b() {
        return this.b;
    }

    public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
        super.onInitializeAccessibilityEvent(arg2, arg3);
        arg3.setClassName(RecyclerView.class.getName());
        if(((arg2 instanceof RecyclerView)) && !this.a() && ((RecyclerView)arg2).getLayoutManager() != null) {
            ((RecyclerView)arg2).getLayoutManager().a(arg3);
        }
    }

    public void onInitializeAccessibilityNodeInfo(View arg1, c arg2) {
        super.onInitializeAccessibilityNodeInfo(arg1, arg2);
        arg2.b(RecyclerView.class.getName());
        if(!this.a() && this.a.getLayoutManager() != null) {
            this.a.getLayoutManager().a(arg2);
        }
    }

    public boolean performAccessibilityAction(View arg1, int arg2, Bundle arg3) {
        if(super.performAccessibilityAction(arg1, arg2, arg3)) {
            return 1;
        }

        if(!this.a() && this.a.getLayoutManager() != null) {
            return this.a.getLayoutManager().a(arg2, arg3);
        }

        return 0;
    }
}

