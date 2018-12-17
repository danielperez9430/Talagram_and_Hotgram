package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff$Mode;
import android.support.v4.view.h;
import android.support.v7.a.a$j;
import android.support.v7.view.menu.k;
import android.support.v7.widget.ai;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class g extends MenuInflater {
    class a implements MenuItem$OnMenuItemClickListener {
        private static final Class[] a;
        private Object b;
        private Method c;

        static {
            a.a = new Class[]{MenuItem.class};
        }

        public a(Object arg5, String arg6) {
            super();
            this.b = arg5;
            Class v5 = arg5.getClass();
            try {
                this.c = v5.getMethod(arg6, a.a);
                return;
            }
            catch(Exception v0) {
                StringBuilder v2 = new StringBuilder();
                v2.append("Couldn\'t resolve menu item onClick handler ");
                v2.append(arg6);
                v2.append(" in class ");
                v2.append(v5.getName());
                InflateException v1 = new InflateException(v2.toString());
                v1.initCause(((Throwable)v0));
                throw v1;
            }
        }

        public boolean onMenuItemClick(MenuItem arg6) {
            try {
                if(this.c.getReturnType() == Boolean.TYPE) {
                    return this.c.invoke(this.b, arg6).booleanValue();
                }

                this.c.invoke(this.b, arg6);
                return 1;
            }
            catch(Exception v6) {
                throw new RuntimeException(((Throwable)v6));
            }
        }
    }

    class b {
        private String A;
        private String B;
        private CharSequence C;
        private CharSequence D;
        private ColorStateList E;
        private PorterDuff$Mode F;
        android.support.v4.view.b a;
        private Menu c;
        private int d;
        private int e;
        private int f;
        private int g;
        private boolean h;
        private boolean i;
        private boolean j;
        private int k;
        private int l;
        private CharSequence m;
        private CharSequence n;
        private int o;
        private char p;
        private int q;
        private char r;
        private int s;
        private int t;
        private boolean u;
        private boolean v;
        private boolean w;
        private int x;
        private int y;
        private String z;

        public b(g arg1, Menu arg2) {
            this.b = arg1;
            super();
            this.E = null;
            this.F = null;
            this.c = arg2;
            this.a();
        }

        public void a(AttributeSet arg3) {
            TypedArray v3 = this.b.e.obtainStyledAttributes(arg3, j.MenuGroup);
            this.d = v3.getResourceId(j.MenuGroup_android_id, 0);
            this.e = v3.getInt(j.MenuGroup_android_menuCategory, 0);
            this.f = v3.getInt(j.MenuGroup_android_orderInCategory, 0);
            this.g = v3.getInt(j.MenuGroup_android_checkableBehavior, 0);
            this.h = v3.getBoolean(j.MenuGroup_android_visible, true);
            this.i = v3.getBoolean(j.MenuGroup_android_enabled, true);
            v3.recycle();
        }

        public void a() {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = true;
            this.i = true;
        }

        private char a(String arg2) {
            if(arg2 == null) {
                return 0;
            }

            return arg2.charAt(0);
        }

        private Object a(String arg3, Class[] arg4, Object[] arg5) {
            try {
                Constructor v4_1 = this.b.e.getClassLoader().loadClass(arg3).getConstructor(arg4);
                v4_1.setAccessible(true);
                return v4_1.newInstance(arg5);
            }
            catch(Exception v4) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + arg3, ((Throwable)v4));
                return null;
            }
        }

        private void a(MenuItem arg6) {
            MenuItem v0 = arg6.setChecked(this.u).setVisible(this.v).setEnabled(this.w);
            int v2 = 0;
            boolean v1 = this.t >= 1 ? true : false;
            v0.setCheckable(v1).setTitleCondensed(this.n).setIcon(this.o);
            if(this.x >= 0) {
                arg6.setShowAsAction(this.x);
            }

            if(this.B != null) {
                if(!this.b.e.isRestricted()) {
                    arg6.setOnMenuItemClickListener(new a(this.b.a(), this.B));
                }
                else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }

            boolean v0_1 = arg6 instanceof android.support.v7.view.menu.j;
            if(this.t >= 2) {
                if(v0_1) {
                    arg6.a(true);
                }
                else if((arg6 instanceof k)) {
                    arg6.a(true);
                }
            }

            if(this.z != null) {
                arg6.setActionView(this.a(this.z, g.a, this.b.c));
                v2 = 1;
            }

            if(this.y > 0) {
                if(v2 == 0) {
                    arg6.setActionView(this.y);
                }
                else {
                    Log.w("SupportMenuInflater", "Ignoring attribute \'itemActionViewLayout\'. Action view already specified.");
                }
            }

            if(this.a != null) {
                h.a(arg6, this.a);
            }

            h.a(arg6, this.C);
            h.b(arg6, this.D);
            h.b(arg6, this.p, this.q);
            h.a(arg6, this.r, this.s);
            if(this.F != null) {
                h.a(arg6, this.F);
            }

            if(this.E != null) {
                h.a(arg6, this.E);
            }
        }

        public void b(AttributeSet arg7) {
            int v0_1;
            TypedArray v7 = this.b.e.obtainStyledAttributes(arg7, j.MenuItem);
            this.k = v7.getResourceId(j.MenuItem_android_id, 0);
            this.l = v7.getInt(j.MenuItem_android_menuCategory, this.e) & -65536 | v7.getInt(j.MenuItem_android_orderInCategory, this.f) & 65535;
            this.m = v7.getText(j.MenuItem_android_title);
            this.n = v7.getText(j.MenuItem_android_titleCondensed);
            this.o = v7.getResourceId(j.MenuItem_android_icon, 0);
            this.p = this.a(v7.getString(j.MenuItem_android_alphabeticShortcut));
            this.q = v7.getInt(j.MenuItem_alphabeticModifiers, 4096);
            this.r = this.a(v7.getString(j.MenuItem_android_numericShortcut));
            this.s = v7.getInt(j.MenuItem_numericModifiers, 4096);
            if(v7.hasValue(j.MenuItem_android_checkable)) {
                boolean v0 = v7.getBoolean(j.MenuItem_android_checkable, false);
            }
            else {
                v0_1 = this.g;
            }

            this.t = v0_1;
            this.u = v7.getBoolean(j.MenuItem_android_checked, false);
            this.v = v7.getBoolean(j.MenuItem_android_visible, this.h);
            this.w = v7.getBoolean(j.MenuItem_android_enabled, this.i);
            int v2 = -1;
            this.x = v7.getInt(j.MenuItem_showAsAction, v2);
            this.B = v7.getString(j.MenuItem_android_onClick);
            this.y = v7.getResourceId(j.MenuItem_actionLayout, 0);
            this.z = v7.getString(j.MenuItem_actionViewClass);
            this.A = v7.getString(j.MenuItem_actionProviderClass);
            v0_1 = this.A != null ? 1 : 0;
            android.support.v4.view.b v3 = null;
            if(v0_1 == 0 || this.y != 0 || this.z != null) {
                if(v0_1 != 0) {
                    Log.w("SupportMenuInflater", "Ignoring attribute \'actionProviderClass\'. Action view already specified.");
                }

                this.a = v3;
            }
            else {
                this.a = this.a(this.A, g.b, this.b.d);
            }

            this.C = v7.getText(j.MenuItem_contentDescription);
            this.D = v7.getText(j.MenuItem_tooltipText);
            this.F = v7.hasValue(j.MenuItem_iconTintMode) ? ai.a(v7.getInt(j.MenuItem_iconTintMode, v2), this.F) : ((PorterDuff$Mode)v3);
            this.E = v7.hasValue(j.MenuItem_iconTint) ? v7.getColorStateList(j.MenuItem_iconTint) : ((ColorStateList)v3);
            v7.recycle();
            this.j = false;
        }

        public void b() {
            this.j = true;
            this.a(this.c.add(this.d, this.k, this.l, this.m));
        }

        public SubMenu c() {
            this.j = true;
            SubMenu v0 = this.c.addSubMenu(this.d, this.k, this.l, this.m);
            this.a(v0.getItem());
            return v0;
        }

        public boolean d() {
            return this.j;
        }
    }

    static final Class[] a;
    static final Class[] b;
    final Object[] c;
    final Object[] d;
    Context e;
    private Object f;

    static {
        g.a = new Class[]{Context.class};
        g.b = g.a;
    }

    public g(Context arg3) {
        super(arg3);
        this.e = arg3;
        this.c = new Object[]{arg3};
        this.d = this.c;
    }

    private Object a(Object arg2) {
        if((arg2 instanceof Activity)) {
            return arg2;
        }

        if((arg2 instanceof ContextWrapper)) {
            arg2 = this.a(((ContextWrapper)arg2).getBaseContext());
        }

        return arg2;
    }

    private void a(XmlPullParser arg9, AttributeSet arg10, Menu arg11) {
        String v4_1;
        b v0 = new b(this, arg11);
        int v11 = arg9.getEventType();
        do {
            if(v11 != 2) {
                v11 = arg9.next();
                if(v11 != 1) {
                    continue;
                }
            }
            else {
                break;
            }

            goto label_23;
        }
        while(true);

        String v11_1 = arg9.getName();
        if(v11_1.equals("menu")) {
            v11 = arg9.next();
        }
        else {
            goto label_12;
        }

    label_23:
        Object v1 = null;
        int v4 = v11;
        Object v6 = v1;
        v11 = 0;
        int v5 = 0;
        goto label_29;
    label_12:
        StringBuilder v10 = new StringBuilder();
        v10.append("Expecting menu, got ");
        v10.append(v11_1);
        throw new RuntimeException(v10.toString());
    label_29:
        while(v11 == 0) {
            switch(v4) {
                case 1: {
                    throw new RuntimeException("Unexpected end of document");
                }
                case 2: {
                    if(v5 != 0) {
                        goto label_89;
                    }

                    v4_1 = arg9.getName();
                    if(v4_1.equals("group")) {
                        v0.a(arg10);
                        goto label_89;
                    }

                    if(v4_1.equals("item")) {
                        v0.b(arg10);
                        goto label_89;
                    }

                    if(v4_1.equals("menu")) {
                        this.a(arg9, arg10, v0.c());
                        goto label_89;
                    }

                    String v6_1 = v4_1;
                    v5 = 1;
                    break;
                }
                case 3: {
                    v4_1 = arg9.getName();
                    if(v5 != 0 && (v4_1.equals(v6_1))) {
                        v6 = v1;
                        v5 = 0;
                        goto label_89;
                    }

                    if(v4_1.equals("group")) {
                        v0.a();
                        goto label_89;
                    }

                    if(v4_1.equals("item")) {
                        if(v0.d()) {
                            goto label_89;
                        }

                        if(v0.a != null && (v0.a.e())) {
                            v0.c();
                            goto label_89;
                        }

                        v0.b();
                        goto label_89;
                    }

                    if(!v4_1.equals("menu")) {
                        goto label_89;
                    }

                    v11 = 1;
                    break;
                }
                default: {
                    break;
                }
            }

        label_89:
            v4 = arg9.next();
        }
    }

    Object a() {
        if(this.f == null) {
            this.f = this.a(this.e);
        }

        return this.f;
    }

    public void inflate(int arg3, Menu arg4) {
        XmlResourceParser v3;
        if(!(arg4 instanceof android.support.v4.a.a.a)) {
            super.inflate(arg3, arg4);
            return;
        }

        XmlResourceParser v0 = null;
        try {
            v3 = this.e.getResources().getLayout(arg3);
            goto label_8;
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            goto label_25;
            try {
            label_8:
                this.a(((XmlPullParser)v3), Xml.asAttributeSet(((XmlPullParser)v3)), arg4);
                if(v3 == null) {
                    return;
                }

                goto label_11;
            }
            catch(Throwable v4) {
                v0 = v3;
            }
            catch(IOException v4_1) {
                v0 = v3;
                try {
                label_25:
                    throw new InflateException("Error inflating menu XML", ((Throwable)v4_1));
                }
                catch(Throwable v4) {
                label_23:
                }
            }
            catch(XmlPullParserException v4_2) {
                v0 = v3;
                try {
                label_30:
                    throw new InflateException("Error inflating menu XML", ((Throwable)v4_2));
                }
                catch(Throwable v4) {
                    goto label_23;
                }
            }
        }
        catch(XmlPullParserException v4_2) {
            goto label_30;
        }

        if(v0 != null) {
            v0.close();
        }

        throw v4;
    label_11:
        v3.close();
    }
}

