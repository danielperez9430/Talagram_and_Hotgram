package android.support.v7.view;

import android.content.Context;
import android.support.v4.f.m;
import android.support.v7.view.menu.q;
import android.view.ActionMode$Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

public class f extends ActionMode {
    public class a implements android.support.v7.view.b$a {
        final ActionMode$Callback a;
        final Context b;
        final ArrayList c;
        final m d;

        public a(Context arg1, ActionMode$Callback arg2) {
            super();
            this.b = arg1;
            this.a = arg2;
            this.c = new ArrayList();
            this.d = new m();
        }

        private Menu a(Menu arg3) {
            Menu v0_1;
            Object v0 = this.d.get(arg3);
            if(v0 == null) {
                v0_1 = q.a(this.b, arg3);
                this.d.put(arg3, v0_1);
            }

            return v0_1;
        }

        public void a(b arg2) {
            this.a.onDestroyActionMode(this.b(arg2));
        }

        public boolean a(b arg2, Menu arg3) {
            return this.a.onCreateActionMode(this.b(arg2), this.a(arg3));
        }

        public boolean a(b arg3, MenuItem arg4) {
            return this.a.onActionItemClicked(this.b(arg3), q.a(this.b, ((android.support.v4.a.a.b)arg4)));
        }

        public ActionMode b(b arg5) {
            int v0 = this.c.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.c.get(v1);
                if(v2 != null && ((f)v2).b == arg5) {
                    return ((ActionMode)v2);
                }
            }

            f v0_1 = new f(this.b, arg5);
            this.c.add(v0_1);
            return ((ActionMode)v0_1);
        }

        public boolean b(b arg2, Menu arg3) {
            return this.a.onPrepareActionMode(this.b(arg2), this.a(arg3));
        }
    }

    final Context a;
    final b b;

    public f(Context arg1, b arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public void finish() {
        this.b.c();
    }

    public View getCustomView() {
        return this.b.i();
    }

    public Menu getMenu() {
        return q.a(this.a, this.b.b());
    }

    public MenuInflater getMenuInflater() {
        return this.b.a();
    }

    public CharSequence getSubtitle() {
        return this.b.g();
    }

    public Object getTag() {
        return this.b.j();
    }

    public CharSequence getTitle() {
        return this.b.f();
    }

    public boolean getTitleOptionalHint() {
        return this.b.k();
    }

    public void invalidate() {
        this.b.d();
    }

    public boolean isTitleOptional() {
        return this.b.h();
    }

    public void setCustomView(View arg2) {
        this.b.a(arg2);
    }

    public void setSubtitle(int arg2) {
        this.b.b(arg2);
    }

    public void setSubtitle(CharSequence arg2) {
        this.b.a(arg2);
    }

    public void setTag(Object arg2) {
        this.b.a(arg2);
    }

    public void setTitle(int arg2) {
        this.b.a(arg2);
    }

    public void setTitle(CharSequence arg2) {
        this.b.b(arg2);
    }

    public void setTitleOptionalHint(boolean arg2) {
        this.b.a(arg2);
    }
}

