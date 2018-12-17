package android.support.v4.view.a;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.List;

public class d {
    class a extends AccessibilityNodeProvider {
        final d a;

        a(d arg1) {
            super();
            this.a = arg1;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int arg2) {
            c v2 = this.a.a(arg2);
            if(v2 == null) {
                return null;
            }

            return v2.a();
        }

        public List findAccessibilityNodeInfosByText(String arg4, int arg5) {
            List v4 = this.a.a(arg4, arg5);
            if(v4 == null) {
                return null;
            }

            ArrayList v5 = new ArrayList();
            int v0 = v4.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                ((List)v5).add(v4.get(v1).a());
            }

            return ((List)v5);
        }

        public boolean performAction(int arg2, int arg3, Bundle arg4) {
            return this.a.a(arg2, arg3, arg4);
        }
    }

    class b extends a {
        b(d arg1) {
            super(arg1);
        }

        public AccessibilityNodeInfo findFocus(int arg2) {
            c v2 = this.a.b(arg2);
            if(v2 == null) {
                return null;
            }

            return v2.a();
        }
    }

    private final Object a;

    public d() {
        Object v0_2;
        super();
        if(Build$VERSION.SDK_INT >= 19) {
            b v0 = new b(this);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            a v0_1 = new a(this);
        }
        else {
            v0_2 = null;
        }

        this.a = v0_2;
    }

    public d(Object arg1) {
        super();
        this.a = arg1;
    }

    public c a(int arg1) {
        return null;
    }

    public Object a() {
        return this.a;
    }

    public List a(String arg1, int arg2) {
        return null;
    }

    public boolean a(int arg1, int arg2, Bundle arg3) {
        return 0;
    }

    public c b(int arg1) {
        return null;
    }
}

