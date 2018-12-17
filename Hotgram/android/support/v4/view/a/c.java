package android.support.v4.view.a;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo$CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo;

public class c {
    public class a {
        final Object a;

        a(Object arg1) {
            super();
            this.a = arg1;
        }

        public static a a(int arg2, int arg3, boolean arg4, int arg5) {
            if(Build$VERSION.SDK_INT >= 21) {
                return new a(AccessibilityNodeInfo$CollectionInfo.obtain(arg2, arg3, arg4, arg5));
            }

            if(Build$VERSION.SDK_INT >= 19) {
                return new a(AccessibilityNodeInfo$CollectionInfo.obtain(arg2, arg3, arg4));
            }

            return new a(null);
        }
    }

    public class b {
        final Object a;

        b(Object arg1) {
            super();
            this.a = arg1;
        }

        public static b a(int arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7) {
            if(Build$VERSION.SDK_INT >= 21) {
                return new b(AccessibilityNodeInfo$CollectionItemInfo.obtain(arg2, arg3, arg4, arg5, arg6, arg7));
            }

            if(Build$VERSION.SDK_INT >= 19) {
                return new b(AccessibilityNodeInfo$CollectionItemInfo.obtain(arg2, arg3, arg4, arg5, arg6));
            }

            return new b(null);
        }
    }

    public int a;
    private final AccessibilityNodeInfo b;

    private c(AccessibilityNodeInfo arg2) {
        super();
        this.a = -1;
        this.b = arg2;
    }

    public void a(int arg2) {
        this.b.addAction(arg2);
    }

    public static c a(c arg0) {
        return c.a(AccessibilityNodeInfo.obtain(arg0.b));
    }

    public static c a(AccessibilityNodeInfo arg1) {
        return new c(arg1);
    }

    public static c a(View arg0) {
        return c.a(AccessibilityNodeInfo.obtain(arg0));
    }

    private void a(int arg5, boolean arg6) {
        Bundle v0 = this.u();
        if(v0 != null) {
            v0.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0 | v0.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (arg5 ^ -1));
        }
    }

    public AccessibilityNodeInfo a() {
        return this.b;
    }

    public void a(Rect arg2) {
        this.b.getBoundsInParent(arg2);
    }

    public void a(View arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            this.b.setSource(arg3, arg4);
        }
    }

    public void a(CharSequence arg2) {
        this.b.setPackageName(arg2);
    }

    public void a(Object arg3) {
        AccessibilityNodeInfo$CollectionInfo v3;
        if(Build$VERSION.SDK_INT >= 19) {
            AccessibilityNodeInfo v0 = this.b;
            if(arg3 == null) {
                v3 = null;
            }
            else {
                arg3 = ((a)arg3).a;
            }

            v0.setCollectionInfo(v3);
        }
    }

    public void a(boolean arg2) {
        this.b.setCheckable(arg2);
    }

    public void b(CharSequence arg2) {
        this.b.setClassName(arg2);
    }

    public static c b() {
        return c.a(AccessibilityNodeInfo.obtain());
    }

    private static String b(int arg0) {
        switch(arg0) {
            case 1: {
                return "ACTION_FOCUS";
            }
            case 2: {
                return "ACTION_CLEAR_FOCUS";
            }
            case 4: {
                return "ACTION_SELECT";
            }
            case 8: {
                return "ACTION_CLEAR_SELECTION";
            }
            case 16: {
                return "ACTION_CLICK";
            }
            case 32: {
                return "ACTION_LONG_CLICK";
            }
            case 64: {
                return "ACTION_ACCESSIBILITY_FOCUS";
            }
            case 128: {
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            }
            case 256: {
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            }
            case 512: {
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            }
            case 1024: {
                return "ACTION_NEXT_HTML_ELEMENT";
            }
            case 2048: {
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            }
            case 4096: {
                return "ACTION_SCROLL_FORWARD";
            }
            case 8192: {
                return "ACTION_SCROLL_BACKWARD";
            }
            case 16384: {
                return "ACTION_COPY";
            }
            case 32768: {
                return "ACTION_PASTE";
            }
            case 65536: {
                return "ACTION_CUT";
            }
            case 131072: {
                return "ACTION_SET_SELECTION";
            }
        }

        return "ACTION_UNKNOWN";
    }

    public void b(Rect arg2) {
        this.b.setBoundsInParent(arg2);
    }

    public void b(View arg2) {
        this.b.setParent(arg2);
    }

    public void b(View arg3, int arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            this.b.addChild(arg3, arg4);
        }
    }

    public void b(Object arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            AccessibilityNodeInfo v0 = this.b;
            if(arg3 == null) {
                AccessibilityNodeInfo$CollectionItemInfo v3 = null;
            }
            else {
                arg3 = ((b)arg3).a;
            }

            v0.setCollectionItemInfo(((AccessibilityNodeInfo$CollectionItemInfo)arg3));
        }
    }

    public void b(boolean arg2) {
        this.b.setChecked(arg2);
    }

    public void c(CharSequence arg2) {
        this.b.setText(arg2);
    }

    public int c() {
        return this.b.getChildCount();
    }

    public void c(Rect arg2) {
        this.b.getBoundsInScreen(arg2);
    }

    public void c(View arg3, int arg4) {
        this.a = arg4;
        if(Build$VERSION.SDK_INT >= 16) {
            this.b.setParent(arg3, arg4);
        }
    }

    public void c(boolean arg2) {
        this.b.setFocusable(arg2);
    }

    public int d() {
        return this.b.getActions();
    }

    public void d(Rect arg2) {
        this.b.setBoundsInScreen(arg2);
    }

    public void d(CharSequence arg2) {
        this.b.setContentDescription(arg2);
    }

    public void d(boolean arg2) {
        this.b.setFocused(arg2);
    }

    public void e(CharSequence arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            this.b.setHintText(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 19) {
            this.b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", arg3);
        }
    }

    public void e(boolean arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            this.b.setVisibleToUser(arg3);
        }
    }

    public boolean e() {
        return this.b.isCheckable();
    }

    public boolean equals(Object arg5) {
        if(this == (((c)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(this.b == null) {
            if(((c)arg5).b != null) {
                return 0;
            }
        }
        else if(!this.b.equals(((c)arg5).b)) {
            return 0;
        }

        return 1;
    }

    public void f(CharSequence arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            this.b.setError(arg3);
        }
    }

    public void f(boolean arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            this.b.setAccessibilityFocused(arg3);
        }
    }

    public boolean f() {
        return this.b.isChecked();
    }

    public void g(boolean arg2) {
        this.b.setSelected(arg2);
    }

    public boolean g() {
        return this.b.isFocusable();
    }

    public void h(boolean arg2) {
        this.b.setEnabled(arg2);
    }

    public boolean h() {
        return this.b.isFocused();
    }

    public int hashCode() {
        int v0 = this.b == null ? 0 : this.b.hashCode();
        return v0;
    }

    public void i(boolean arg2) {
        this.b.setScrollable(arg2);
    }

    public boolean i() {
        return this.b.isSelected();
    }

    public void j(boolean arg3) {
        if(Build$VERSION.SDK_INT >= 19) {
            this.b.setContentInvalid(arg3);
        }
    }

    public boolean j() {
        return this.b.isClickable();
    }

    public void k(boolean arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            this.b.setShowingHintText(arg3);
        }
        else {
            this.a(4, arg3);
        }
    }

    public boolean k() {
        return this.b.isLongClickable();
    }

    public boolean l() {
        return this.b.isEnabled();
    }

    public boolean m() {
        return this.b.isPassword();
    }

    public boolean n() {
        return this.b.isScrollable();
    }

    public CharSequence o() {
        return this.b.getPackageName();
    }

    public CharSequence p() {
        return this.b.getClassName();
    }

    public CharSequence q() {
        return this.b.getText();
    }

    public CharSequence r() {
        return this.b.getContentDescription();
    }

    public void s() {
        this.b.recycle();
    }

    public String t() {
        if(Build$VERSION.SDK_INT >= 18) {
            return this.b.getViewIdResourceName();
        }

        return null;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append(super.toString());
        Rect v1 = new Rect();
        this.a(v1);
        v0.append("; boundsInParent: " + v1);
        this.c(v1);
        v0.append("; boundsInScreen: " + v1);
        v0.append("; packageName: ");
        v0.append(this.o());
        v0.append("; className: ");
        v0.append(this.p());
        v0.append("; text: ");
        v0.append(this.q());
        v0.append("; contentDescription: ");
        v0.append(this.r());
        v0.append("; viewId: ");
        v0.append(this.t());
        v0.append("; checkable: ");
        v0.append(this.e());
        v0.append("; checked: ");
        v0.append(this.f());
        v0.append("; focusable: ");
        v0.append(this.g());
        v0.append("; focused: ");
        v0.append(this.h());
        v0.append("; selected: ");
        v0.append(this.i());
        v0.append("; clickable: ");
        v0.append(this.j());
        v0.append("; longClickable: ");
        v0.append(this.k());
        v0.append("; enabled: ");
        v0.append(this.l());
        v0.append("; password: ");
        v0.append(this.m());
        v0.append("; scrollable: " + this.n());
        v0.append("; [");
        int v1_2 = this.d();
        while(v1_2 != 0) {
            int v2_1 = 1 << Integer.numberOfTrailingZeros(v1_2);
            v1_2 &= v2_1 ^ -1;
            v0.append(c.b(v2_1));
            if(v1_2 == 0) {
                continue;
            }

            v0.append(", ");
        }

        v0.append("]");
        return v0.toString();
    }

    public Bundle u() {
        if(Build$VERSION.SDK_INT >= 19) {
            return this.b.getExtras();
        }

        return new Bundle();
    }
}

