package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.a.a$g;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class f implements o, AdapterView$OnItemClickListener {
    class a extends BaseAdapter {
        private int b;

        public a(f arg1) {
            this.a = arg1;
            super();
            this.b = -1;
            this.a();
        }

        public j a(int arg3) {
            ArrayList v0 = this.a.c.m();
            arg3 += this.a.e;
            if(this.b >= 0 && arg3 >= this.b) {
                ++arg3;
            }

            return v0.get(arg3);
        }

        void a() {
            j v0 = this.a.c.s();
            if(v0 != null) {
                ArrayList v1 = this.a.c.m();
                int v2 = v1.size();
                int v3 = 0;
                while(v3 < v2) {
                    if(v1.get(v3) == v0) {
                        this.b = v3;
                        return;
                    }
                    else {
                        ++v3;
                        continue;
                    }

                    break;
                }
            }

            this.b = -1;
        }

        public int getCount() {
            int v0 = this.a.c.m().size() - this.a.e;
            if(this.b < 0) {
                return v0;
            }

            return v0 - 1;
        }

        public Object getItem(int arg1) {
            return this.a(arg1);
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public View getView(int arg3, View arg4, ViewGroup arg5) {
            if(arg4 == null) {
                arg4 = this.a.b.inflate(this.a.g, arg5, false);
            }

            arg4.a(this.a(arg3), 0);
            return arg4;
        }

        public void notifyDataSetChanged() {
            this.a();
            super.notifyDataSetChanged();
        }
    }

    Context a;
    LayoutInflater b;
    h c;
    ExpandedMenuView d;
    int e;
    int f;
    int g;
    a h;
    private android.support.v7.view.menu.o$a i;

    public f(Context arg2, int arg3) {
        this(arg3, 0);
        this.a = arg2;
        this.b = LayoutInflater.from(this.a);
    }

    public f(int arg1, int arg2) {
        super();
        this.g = arg1;
        this.f = arg2;
    }

    public void a(android.support.v7.view.menu.o$a arg1) {
        this.i = arg1;
    }

    public p a(ViewGroup arg4) {
        if(this.d == null) {
            this.d = this.b.inflate(g.abc_expanded_menu_layout, arg4, false);
            if(this.h == null) {
                this.h = new a(this);
            }

            this.d.setAdapter(this.h);
            this.d.setOnItemClickListener(((AdapterView$OnItemClickListener)this));
        }

        return this.d;
    }

    public ListAdapter a() {
        if(this.h == null) {
            this.h = new a(this);
        }

        return this.h;
    }

    public void a(Context arg3, h arg4) {
        if(this.f != 0) {
            this.a = new ContextThemeWrapper(arg3, this.f);
            goto label_6;
        }
        else if(this.a != null) {
            this.a = arg3;
            if(this.b == null) {
            label_6:
                this.b = LayoutInflater.from(this.a);
            }
        }

        this.c = arg4;
        if(this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public void a(h arg2, boolean arg3) {
        if(this.i != null) {
            this.i.a(arg2, arg3);
        }
    }

    public boolean a(h arg1, j arg2) {
        return 0;
    }

    public boolean a(u arg3) {
        if(!arg3.hasVisibleItems()) {
            return 0;
        }

        new i(((h)arg3)).a(null);
        if(this.i != null) {
            this.i.a(((h)arg3));
        }

        return 1;
    }

    public void b(boolean arg1) {
        if(this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public boolean b() {
        return 0;
    }

    public boolean b(h arg1, j arg2) {
        return 0;
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        this.c.a(this.h.a(arg3), ((o)this), 0);
    }
}

