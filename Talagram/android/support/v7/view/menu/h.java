package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.a.a.a;
import android.util.SparseArray;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.KeyCharacterMap$KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class h implements a {
    public interface android.support.v7.view.menu.h$a {
        boolean a(h arg1, MenuItem arg2);

        void a(h arg1);
    }

    public interface b {
        boolean a(j arg1);
    }

    private boolean A;
    CharSequence a;
    Drawable b;
    View c;
    private static final int[] d;
    private final Context e;
    private final Resources f;
    private boolean g;
    private boolean h;
    private android.support.v7.view.menu.h$a i;
    private ArrayList j;
    private ArrayList k;
    private boolean l;
    private ArrayList m;
    private ArrayList n;
    private boolean o;
    private int p;
    private ContextMenu$ContextMenuInfo q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private ArrayList w;
    private CopyOnWriteArrayList x;
    private j y;
    private boolean z;

    static {
        h.d = new int[]{1, 4, 5, 3, 2, 0};
    }

    public h(Context arg3) {
        super();
        this.p = 0;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = new ArrayList();
        this.x = new CopyOnWriteArrayList();
        this.z = false;
        this.e = arg3;
        this.f = arg3.getResources();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = true;
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.o = true;
        this.e(true);
    }

    public void a(o arg3, Context arg4) {
        this.x.add(new WeakReference(arg3));
        arg3.a(arg4, this);
        this.o = true;
    }

    public void a(android.support.v7.view.menu.h$a arg1) {
        this.i = arg1;
    }

    public void a(Bundle arg8) {
        int v0 = this.size();
        SparseArray v1 = null;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            MenuItem v3 = this.getItem(v2);
            View v4 = v3.getActionView();
            if(v4 != null && v4.getId() != -1) {
                if(v1 == null) {
                    v1 = new SparseArray();
                }

                v4.saveHierarchyState(v1);
                if(!v3.isActionViewExpanded()) {
                    goto label_19;
                }

                arg8.putInt("android:menu:expandedactionview", v3.getItemId());
            }

        label_19:
            if(v3.hasSubMenu()) {
                v3.getSubMenu().a(arg8);
            }
        }

        if(v1 != null) {
            arg8.putSparseParcelableArray(this.a(), v1);
        }
    }

    public void a(o arg2) {
        this.a(arg2, this.e);
    }

    public h a(int arg1) {
        this.p = arg1;
        return this;
    }

    public boolean a(MenuItem arg2, int arg3) {
        return this.a(arg2, null, arg3);
    }

    public boolean a(MenuItem arg7, o arg8, int arg9) {
        int v1_1;
        if(arg7 != null) {
            if(!((j)arg7).isEnabled()) {
            }
            else {
                boolean v1 = ((j)arg7).b();
                android.support.v4.view.b v2 = ((j)arg7).a();
                int v4 = v2 == null || !v2.e() ? 0 : 1;
                if(((j)arg7).n()) {
                    v1_1 = (((int)v1)) | ((j)arg7).expandActionView();
                    if(v1_1 == 0) {
                        return v1;
                    }

                    goto label_19;
                }
                else {
                    if(!((j)arg7).hasSubMenu()) {
                        if(v4 != 0) {
                        }
                        else if((arg9 & 1) == 0) {
                            goto label_19;
                        }
                        else {
                            return v1;
                        }
                    }

                    if((arg9 & 4) == 0) {
                        this.a(false);
                    }

                    if(!((j)arg7).hasSubMenu()) {
                        ((j)arg7).a(new u(this.f(), this, ((j)arg7)));
                    }

                    SubMenu v7 = ((j)arg7).getSubMenu();
                    if(v4 != 0) {
                        v2.a(v7);
                    }

                    v1_1 = (((int)v1)) | this.a(((u)v7), arg8);
                    if(v1_1 != 0) {
                        return v1;
                    }

                label_19:
                    this.a(true);
                }

                return v1;
            }
        }

        return 0;
    }

    private static int a(ArrayList arg2, int arg3) {
        int v0;
        for(v0 = arg2.size() - 1; v0 >= 0; --v0) {
            if(arg2.get(v0).c() <= arg3) {
                return v0 + 1;
            }
        }

        return 0;
    }

    private j a(int arg10, int arg11, int arg12, int arg13, CharSequence arg14, int arg15) {
        return new j(this, arg10, arg11, arg12, arg13, arg14, arg15);
    }

    private void a(int arg3, CharSequence arg4, int arg5, Drawable arg6, View arg7) {
        Resources v0 = this.e();
        CharSequence v1 = null;
        if(arg7 != null) {
            this.c = arg7;
            this.a = v1;
            this.b = ((Drawable)v1);
        }
        else {
            if(arg3 > 0) {
                this.a = v0.getText(arg3);
            }
            else if(arg4 != null) {
                this.a = arg4;
            }

            if(arg5 > 0) {
                this.b = android.support.v4.content.a.a(this.f(), arg5);
            }
            else if(arg6 != null) {
                this.b = arg6;
            }

            this.c = ((View)v1);
        }

        this.b(false);
    }

    private void a(int arg2, boolean arg3) {
        if(arg2 >= 0) {
            if(arg2 >= this.j.size()) {
            }
            else {
                this.j.remove(arg2);
                if(arg3) {
                    this.b(true);
                }
            }
        }
    }

    private boolean a(u arg4, o arg5) {
        boolean v1 = false;
        if(this.x.isEmpty()) {
            return 0;
        }

        if(arg5 != null) {
            v1 = arg5.a(arg4);
        }

        Iterator v5 = this.x.iterator();
        while(v5.hasNext()) {
            Object v0 = v5.next();
            Object v2 = ((WeakReference)v0).get();
            if(v2 == null) {
                this.x.remove(v0);
                continue;
            }

            if(v1) {
                continue;
            }

            v1 = ((o)v2).a(arg4);
        }

        return v1;
    }

    public int a(int arg3, int arg4) {
        int v0 = this.size();
        if(arg4 < 0) {
            arg4 = 0;
        }

        while(arg4 < v0) {
            if(this.j.get(arg4).getGroupId() == arg3) {
                return arg4;
            }

            ++arg4;
        }

        return -1;
    }

    protected h a(Drawable arg7) {
        this.a(0, null, 0, arg7, null);
        return this;
    }

    protected h a(View arg7) {
        this.a(0, null, 0, null, arg7);
        return this;
    }

    protected h a(CharSequence arg7) {
        this.a(0, arg7, 0, null, null);
        return this;
    }

    j a(int arg12, KeyEvent arg13) {
        ArrayList v0 = this.w;
        v0.clear();
        this.a(((List)v0), arg12, arg13);
        j v2 = null;
        if(v0.isEmpty()) {
            return v2;
        }

        int v1 = arg13.getMetaState();
        KeyCharacterMap$KeyData v3 = new KeyCharacterMap$KeyData();
        arg13.getKeyData(v3);
        int v13 = v0.size();
        if(v13 == 1) {
            return v0.get(0);
        }

        boolean v4 = this.c();
        int v6;
        for(v6 = 0; v6 < v13; ++v6) {
            Object v7 = v0.get(v6);
            int v8 = v4 ? ((j)v7).getAlphabeticShortcut() : ((j)v7).getNumericShortcut();
            if(v8 != v3.meta[0] || (v1 & 2) != 0) {
                if(v8 == v3.meta[2] && (v1 & 2) != 0) {
                    goto label_41;
                }

                if((v4) && v8 == 8 && arg12 == 67) {
                    goto label_41;
                }
            }
            else {
            label_41:
                return ((j)v7);
            }
        }

        return v2;
    }

    void a(List arg13, int arg14, KeyEvent arg15) {
        boolean v0 = this.c();
        int v1 = arg15.getModifiers();
        KeyCharacterMap$KeyData v2 = new KeyCharacterMap$KeyData();
        int v4 = 67;
        if(!arg15.getKeyData(v2) && arg14 != v4) {
            return;
        }

        int v3 = this.j.size();
        int v6;
        for(v6 = 0; v6 < v3; ++v6) {
            Object v7 = this.j.get(v6);
            if(((j)v7).hasSubMenu()) {
                ((j)v7).getSubMenu().a(arg13, arg14, arg15);
            }

            int v8 = v0 ? ((j)v7).getAlphabeticShortcut() : ((j)v7).getNumericShortcut();
            int v9 = v0 ? ((j)v7).getAlphabeticModifiers() : ((j)v7).getNumericModifiers();
            v9 = (v1 & 69647) == (v9 & 69647) ? 1 : 0;
            if(v9 != 0 && v8 != 0) {
                if(v8 != v2.meta[0] && v8 != v2.meta[2]) {
                    if(!v0) {
                    }
                    else if(v8 != 8) {
                    }
                    else if(arg14 == v4) {
                        goto label_48;
                    }

                    goto label_51;
                }

            label_48:
                if(!((j)v7).isEnabled()) {
                    goto label_51;
                }

                arg13.add(v7);
            }

        label_51:
        }
    }

    protected MenuItem a(int arg9, int arg10, int arg11, CharSequence arg12) {
        int v7 = h.f(arg11);
        j v9 = this.a(arg9, arg10, arg11, v7, arg12, this.p);
        if(this.q != null) {
            v9.a(this.q);
        }

        this.j.add(h.a(this.j, v7), v9);
        this.b(true);
        return ((MenuItem)v9);
    }

    protected String a() {
        return "android:menu:actionviewstates";
    }

    void a(j arg1) {
        this.l = true;
        this.b(true);
    }

    void a(MenuItem arg7) {
        int v0 = arg7.getGroupId();
        int v1 = this.j.size();
        this.h();
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = this.j.get(v3);
            if(((j)v4).getGroupId() == v0) {
                if(!((j)v4).g()) {
                }
                else if(!((j)v4).isCheckable()) {
                }
                else {
                    boolean v5 = (((MenuItem)v4)) == arg7 ? true : false;
                    ((j)v4).b(v5);
                }
            }
        }

        this.i();
    }

    public final void a(boolean arg4) {
        if(this.v) {
            return;
        }

        this.v = true;
        Iterator v0 = this.x.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v2 = ((WeakReference)v1).get();
            if(v2 == null) {
                this.x.remove(v1);
                continue;
            }

            ((o)v2).a(this, arg4);
        }

        this.v = false;
    }

    boolean a(h arg2, MenuItem arg3) {
        boolean v2 = this.i == null || !this.i.a(arg2, arg3) ? false : true;
        return v2;
    }

    public MenuItem add(int arg2) {
        return this.a(0, 0, 0, this.f.getString(arg2));
    }

    public MenuItem add(int arg2, int arg3, int arg4, int arg5) {
        return this.a(arg2, arg3, arg4, this.f.getString(arg5));
    }

    public MenuItem add(int arg1, int arg2, int arg3, CharSequence arg4) {
        return this.a(arg1, arg2, arg3, arg4);
    }

    public MenuItem add(CharSequence arg2) {
        return this.a(0, 0, 0, arg2);
    }

    public int addIntentOptions(int arg8, int arg9, int arg10, ComponentName arg11, Intent[] arg12, Intent arg13, int arg14, MenuItem[] arg15) {
        PackageManager v0 = this.e.getPackageManager();
        int v1 = 0;
        List v11 = v0.queryIntentActivityOptions(arg11, arg12, arg13, 0);
        int v2 = v11 != null ? v11.size() : 0;
        if((arg14 & 1) == 0) {
            this.removeGroup(arg8);
        }

        while(v1 < v2) {
            Object v14 = v11.get(v1);
            Intent v4 = ((ResolveInfo)v14).specificIndex < 0 ? arg13 : arg12[((ResolveInfo)v14).specificIndex];
            Intent v3 = new Intent(v4);
            v3.setComponent(new ComponentName(((ResolveInfo)v14).activityInfo.applicationInfo.packageName, ((ResolveInfo)v14).activityInfo.name));
            MenuItem v3_1 = this.add(arg8, arg9, arg10, ((ResolveInfo)v14).loadLabel(v0)).setIcon(((ResolveInfo)v14).loadIcon(v0)).setIntent(v3);
            if(arg15 != null && ((ResolveInfo)v14).specificIndex >= 0) {
                arg15[((ResolveInfo)v14).specificIndex] = v3_1;
            }

            ++v1;
        }

        return v2;
    }

    public SubMenu addSubMenu(int arg2) {
        return this.addSubMenu(0, 0, 0, this.f.getString(arg2));
    }

    public SubMenu addSubMenu(int arg1, int arg2, int arg3, CharSequence arg4) {
        MenuItem v1 = this.a(arg1, arg2, arg3, arg4);
        u v2 = new u(this.e, this, ((j)v1));
        ((j)v1).a(v2);
        return ((SubMenu)v2);
    }

    public SubMenu addSubMenu(int arg2, int arg3, int arg4, int arg5) {
        return this.addSubMenu(arg2, arg3, arg4, this.f.getString(arg5));
    }

    public SubMenu addSubMenu(CharSequence arg2) {
        return this.addSubMenu(0, 0, 0, arg2);
    }

    public boolean b() {
        return this.z;
    }

    public void b(o arg4) {
        Iterator v0 = this.x.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v2 = ((WeakReference)v1).get();
            if(v2 != null && (((o)v2)) != arg4) {
                continue;
            }

            this.x.remove(v1);
        }
    }

    public void b(Bundle arg8) {
        if(arg8 == null) {
            return;
        }

        SparseArray v0 = arg8.getSparseParcelableArray(this.a());
        int v1 = this.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            MenuItem v3 = this.getItem(v2);
            View v4 = v3.getActionView();
            if(v4 != null && v4.getId() != -1) {
                v4.restoreHierarchyState(v0);
            }

            if(v3.hasSubMenu()) {
                v3.getSubMenu().b(arg8);
            }
        }

        int v8 = arg8.getInt("android:menu:expandedactionview");
        if(v8 > 0) {
            MenuItem v8_1 = this.findItem(v8);
            if(v8_1 != null) {
                v8_1.expandActionView();
            }
        }
    }

    public void b(boolean arg3) {
        if(!this.r) {
            if(arg3) {
                this.l = true;
                this.o = true;
            }

            this.d(arg3);
        }
        else {
            this.s = true;
            if(!arg3) {
                return;
            }

            this.t = true;
        }
    }

    public int b(int arg4) {
        int v0 = this.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(this.j.get(v1).getItemId() == arg4) {
                return v1;
            }
        }

        return -1;
    }

    void b(j arg1) {
        this.o = true;
        this.b(true);
    }

    public void c(boolean arg1) {
        this.A = arg1;
    }

    boolean c() {
        return this.g;
    }

    public int c(int arg2) {
        return this.a(arg2, 0);
    }

    public boolean c(j arg5) {
        boolean v1 = false;
        if(this.x.isEmpty()) {
            return 0;
        }

        this.h();
        Iterator v0 = this.x.iterator();
        do {
            if(!v0.hasNext()) {
                break;
            }

            Object v2 = v0.next();
            Object v3 = ((WeakReference)v2).get();
            if(v3 == null) {
                this.x.remove(v2);
                continue;
            }
            else {
                v1 = ((o)v3).a(this, arg5);
                if(!v1) {
                    continue;
                }
            }

            break;
        }
        while(true);

        this.i();
        if(v1) {
            this.y = arg5;
        }

        return v1;
    }

    public void clear() {
        if(this.y != null) {
            this.d(this.y);
        }

        this.j.clear();
        this.b(true);
    }

    public void clearHeader() {
        this.b = null;
        this.a = null;
        this.c = null;
        this.b(false);
    }

    public void close() {
        this.a(true);
    }

    public boolean d(j arg5) {
        boolean v1 = false;
        if(!this.x.isEmpty()) {
            if(this.y != arg5) {
            }
            else {
                this.h();
                Iterator v0 = this.x.iterator();
                do {
                    if(!v0.hasNext()) {
                        break;
                    }

                    Object v2 = v0.next();
                    Object v3 = ((WeakReference)v2).get();
                    if(v3 == null) {
                        this.x.remove(v2);
                        continue;
                    }
                    else {
                        v1 = ((o)v3).b(this, arg5);
                        if(!v1) {
                            continue;
                        }
                    }

                    break;
                }
                while(true);

                this.i();
                if(!v1) {
                    return v1;
                }

                this.y = null;
            }
        }

        return v1;
    }

    private void d(boolean arg4) {
        if(this.x.isEmpty()) {
            return;
        }

        this.h();
        Iterator v0 = this.x.iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v2 = ((WeakReference)v1).get();
            if(v2 == null) {
                this.x.remove(v1);
                continue;
            }

            ((o)v2).b(arg4);
        }

        this.i();
    }

    protected h d(int arg7) {
        this.a(arg7, null, 0, null, null);
        return this;
    }

    public boolean d() {
        return this.h;
    }

    private void e(boolean arg3) {
        boolean v0 = true;
        if(!arg3 || this.f.getConfiguration().keyboard == 1 || !android.support.v4.view.u.c(ViewConfiguration.get(this.e), this.e)) {
            v0 = false;
        }
        else {
        }

        this.h = v0;
    }

    Resources e() {
        return this.f;
    }

    protected h e(int arg7) {
        this.a(0, null, arg7, null, null);
        return this;
    }

    public Context f() {
        return this.e;
    }

    private static int f(int arg2) {
        int v0 = (-65536 & arg2) >> 16;
        if(v0 >= 0 && v0 < h.d.length) {
            return arg2 & 65535 | h.d[v0] << 16;
        }

        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public MenuItem findItem(int arg5) {
        int v0 = this.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.j.get(v1);
            if(((j)v2).getItemId() == arg5) {
                return ((MenuItem)v2);
            }

            if(((j)v2).hasSubMenu()) {
                MenuItem v2_1 = ((j)v2).getSubMenu().findItem(arg5);
                if(v2_1 != null) {
                    return v2_1;
                }
            }
        }

        return null;
    }

    public void g() {
        if(this.i != null) {
            this.i.a(this);
        }
    }

    public MenuItem getItem(int arg2) {
        return this.j.get(arg2);
    }

    public void h() {
        if(!this.r) {
            this.r = true;
            this.s = false;
            this.t = false;
        }
    }

    public boolean hasVisibleItems() {
        if(this.A) {
            return 1;
        }

        int v0 = this.size();
        int v3;
        for(v3 = 0; v3 < v0; ++v3) {
            if(this.j.get(v3).isVisible()) {
                return 1;
            }
        }

        return 0;
    }

    public void i() {
        this.r = false;
        if(this.s) {
            this.s = false;
            this.b(this.t);
        }
    }

    public boolean isShortcutKey(int arg1, KeyEvent arg2) {
        boolean v1 = this.a(arg1, arg2) != null ? true : false;
        return v1;
    }

    public ArrayList j() {
        if(!this.l) {
            return this.k;
        }

        this.k.clear();
        int v0 = this.j.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            Object v3 = this.j.get(v2);
            if(((j)v3).isVisible()) {
                this.k.add(v3);
            }
        }

        this.l = false;
        this.o = true;
        return this.k;
    }

    public void k() {
        Object v4;
        ArrayList v0 = this.j();
        if(!this.o) {
            return;
        }

        Iterator v1 = this.x.iterator();
        int v3;
        for(v3 = 0; v1.hasNext(); v3 |= ((o)v5).b()) {
            v4 = v1.next();
            Object v5 = ((WeakReference)v4).get();
            if(v5 == null) {
                this.x.remove(v4);
                continue;
            }
        }

        if(v3 != 0) {
            this.m.clear();
            this.n.clear();
            int v1_1 = v0.size();
            v3 = 0;
            goto label_26;
        }
        else {
            this.m.clear();
            this.n.clear();
            this.n.addAll(this.j());
            goto label_44;
        label_26:
            while(v3 < v1_1) {
                v4 = v0.get(v3);
                ArrayList v5_1 = ((j)v4).j() ? this.m : this.n;
                v5_1.add(v4);
                ++v3;
            }
        }

    label_44:
        this.o = false;
    }

    public ArrayList l() {
        this.k();
        return this.m;
    }

    public ArrayList m() {
        this.k();
        return this.n;
    }

    public CharSequence n() {
        return this.a;
    }

    public Drawable o() {
        return this.b;
    }

    public View p() {
        return this.c;
    }

    public boolean performIdentifierAction(int arg1, int arg2) {
        return this.a(this.findItem(arg1), arg2);
    }

    public boolean performShortcut(int arg1, KeyEvent arg2, int arg3) {
        j v1 = this.a(arg1, arg2);
        boolean v1_1 = v1 != null ? this.a(((MenuItem)v1), arg3) : false;
        if((arg3 & 2) != 0) {
            this.a(true);
        }

        return v1_1;
    }

    public h q() {
        return this;
    }

    boolean r() {
        return this.u;
    }

    public void removeGroup(int arg6) {
        int v0 = this.c(arg6);
        if(v0 >= 0) {
            int v1 = this.j.size() - v0;
            int v3 = 0;
            while(true) {
                int v4 = v3 + 1;
                if(v3 < v1 && this.j.get(v0).getGroupId() == arg6) {
                    this.a(v0, false);
                    v3 = v4;
                    continue;
                }

                break;
            }

            this.b(true);
        }
    }

    public void removeItem(int arg2) {
        this.a(this.b(arg2), true);
    }

    public j s() {
        return this.y;
    }

    public void setGroupCheckable(int arg5, boolean arg6, boolean arg7) {
        int v0 = this.j.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.j.get(v1);
            if(((j)v2).getGroupId() == arg5) {
                ((j)v2).a(arg7);
                ((j)v2).setCheckable(arg6);
            }
        }
    }

    public void setGroupDividerEnabled(boolean arg1) {
        this.z = arg1;
    }

    public void setGroupEnabled(int arg5, boolean arg6) {
        int v0 = this.j.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.j.get(v1);
            if(((j)v2).getGroupId() == arg5) {
                ((j)v2).setEnabled(arg6);
            }
        }
    }

    public void setGroupVisible(int arg7, boolean arg8) {
        int v0 = this.j.size();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v0) {
            Object v4 = this.j.get(v1);
            if(((j)v4).getGroupId() == arg7 && (((j)v4).c(arg8))) {
                v2 = 1;
            }

            ++v1;
        }

        if(v2 != 0) {
            this.b(true);
        }
    }

    public void setQwertyMode(boolean arg1) {
        this.g = arg1;
        this.b(false);
    }

    public int size() {
        return this.j.size();
    }
}

