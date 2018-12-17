package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.a.a.b;
import android.support.v4.f.a;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

abstract class c extends d {
    final Context a;
    private Map c;
    private Map d;

    c(Context arg1, Object arg2) {
        super(arg2);
        this.a = arg1;
    }

    final MenuItem a(MenuItem arg3) {
        if((arg3 instanceof b)) {
            MenuItem v0 = arg3;
            if(this.c == null) {
                this.c = new a();
            }

            Object v3 = this.c.get(arg3);
            if(v3 != null) {
                return arg3;
            }

            arg3 = q.a(this.a, ((b)v0));
            this.c.put(v0, arg3);
        }

        return arg3;
    }

    final SubMenu a(SubMenu arg3) {
        if((arg3 instanceof android.support.v4.a.a.c)) {
            if(this.d == null) {
                this.d = new a();
            }

            Object v0 = this.d.get(arg3);
            if(v0 == null) {
                SubMenu v0_1 = q.a(this.a, ((android.support.v4.a.a.c)arg3));
                this.d.put(arg3, v0_1);
            }

            return ((SubMenu)v0);
        }

        return arg3;
    }

    final void a() {
        if(this.c != null) {
            this.c.clear();
        }

        if(this.d != null) {
            this.d.clear();
        }
    }

    final void a(int arg3) {
        if(this.c == null) {
            return;
        }

        Iterator v0 = this.c.keySet().iterator();
        while(v0.hasNext()) {
            if(arg3 != v0.next().getGroupId()) {
                continue;
            }

            v0.remove();
        }
    }

    final void b(int arg3) {
        if(this.c == null) {
            return;
        }

        Iterator v0 = this.c.keySet().iterator();
        do {
            if(v0.hasNext()) {
                if(arg3 != v0.next().getItemId()) {
                    continue;
                }

                break;
            }

            return;
        }
        while(true);

        v0.remove();
    }
}

