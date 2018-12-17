package android.support.v4.view;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.v4.view.a.c;
import android.support.v4.view.a.d;
import android.view.View$AccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

public class a {
    final class android.support.v4.view.a$a extends View$AccessibilityDelegate {
        private final a a;

        android.support.v4.view.a$a(a arg1) {
            super();
            this.a = arg1;
        }

        public boolean dispatchPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
            return this.a.dispatchPopulateAccessibilityEvent(arg2, arg3);
        }

        public AccessibilityNodeProvider getAccessibilityNodeProvider(View arg2) {
            Object v2_1;
            d v2 = this.a.getAccessibilityNodeProvider(arg2);
            if(v2 != null) {
                v2_1 = v2.a();
            }
            else {
                AccessibilityNodeProvider v2_2 = null;
            }

            return ((AccessibilityNodeProvider)v2_1);
        }

        public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
            this.a.onInitializeAccessibilityEvent(arg2, arg3);
        }

        public void onInitializeAccessibilityNodeInfo(View arg2, AccessibilityNodeInfo arg3) {
            this.a.onInitializeAccessibilityNodeInfo(arg2, c.a(arg3));
        }

        public void onPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
            this.a.onPopulateAccessibilityEvent(arg2, arg3);
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup arg2, View arg3, AccessibilityEvent arg4) {
            return this.a.onRequestSendAccessibilityEvent(arg2, arg3, arg4);
        }

        public boolean performAccessibilityAction(View arg2, int arg3, Bundle arg4) {
            return this.a.performAccessibilityAction(arg2, arg3, arg4);
        }

        public void sendAccessibilityEvent(View arg2, int arg3) {
            this.a.sendAccessibilityEvent(arg2, arg3);
        }

        public void sendAccessibilityEventUnchecked(View arg2, AccessibilityEvent arg3) {
            this.a.sendAccessibilityEventUnchecked(arg2, arg3);
        }
    }

    private static final View$AccessibilityDelegate DEFAULT_DELEGATE;
    private final View$AccessibilityDelegate mBridge;

    static {
        a.DEFAULT_DELEGATE = new View$AccessibilityDelegate();
    }

    public a() {
        super();
        this.mBridge = new android.support.v4.view.a$a(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
        return a.DEFAULT_DELEGATE.dispatchPopulateAccessibilityEvent(arg2, arg3);
    }

    public d getAccessibilityNodeProvider(View arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            AccessibilityNodeProvider v3 = a.DEFAULT_DELEGATE.getAccessibilityNodeProvider(arg3);
            if(v3 != null) {
                return new d(v3);
            }
        }

        return null;
    }

    View$AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
        a.DEFAULT_DELEGATE.onInitializeAccessibilityEvent(arg2, arg3);
    }

    public void onInitializeAccessibilityNodeInfo(View arg2, c arg3) {
        a.DEFAULT_DELEGATE.onInitializeAccessibilityNodeInfo(arg2, arg3.a());
    }

    public void onPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
        a.DEFAULT_DELEGATE.onPopulateAccessibilityEvent(arg2, arg3);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup arg2, View arg3, AccessibilityEvent arg4) {
        return a.DEFAULT_DELEGATE.onRequestSendAccessibilityEvent(arg2, arg3, arg4);
    }

    public boolean performAccessibilityAction(View arg3, int arg4, Bundle arg5) {
        if(Build$VERSION.SDK_INT >= 16) {
            return a.DEFAULT_DELEGATE.performAccessibilityAction(arg3, arg4, arg5);
        }

        return 0;
    }

    public void sendAccessibilityEvent(View arg2, int arg3) {
        a.DEFAULT_DELEGATE.sendAccessibilityEvent(arg2, arg3);
    }

    public void sendAccessibilityEventUnchecked(View arg2, AccessibilityEvent arg3) {
        a.DEFAULT_DELEGATE.sendAccessibilityEventUnchecked(arg2, arg3);
    }
}

