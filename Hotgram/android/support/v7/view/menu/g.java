package android.support.v7.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class g extends BaseAdapter {
    h a;
    private int b;
    private boolean c;
    private final boolean d;
    private final LayoutInflater e;
    private final int f;

    public g(h arg2, LayoutInflater arg3, boolean arg4, int arg5) {
        super();
        this.b = -1;
        this.d = arg4;
        this.e = arg3;
        this.a = arg2;
        this.f = arg5;
        this.b();
    }

    public h a() {
        return this.a;
    }

    public j a(int arg3) {
        ArrayList v0 = this.d ? this.a.m() : this.a.j();
        if(this.b >= 0 && arg3 >= this.b) {
            ++arg3;
        }

        return v0.get(arg3);
    }

    public void a(boolean arg1) {
        this.c = arg1;
    }

    void b() {
        j v0 = this.a.s();
        if(v0 != null) {
            ArrayList v1 = this.a.m();
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
        ArrayList v0 = this.d ? this.a.m() : this.a.j();
        if(this.b < 0) {
            return v0.size();
        }

        return v0.size() - 1;
    }

    public Object getItem(int arg1) {
        return this.a(arg1);
    }

    public long getItemId(int arg3) {
        return ((long)arg3);
    }

    public View getView(int arg6, View arg7, ViewGroup arg8) {
        if(arg7 == null) {
            arg7 = this.e.inflate(this.f, arg8, false);
        }

        int v8 = this.a(arg6).getGroupId();
        int v1 = arg6 - 1;
        v1 = v1 >= 0 ? this.a(v1).getGroupId() : v8;
        View v2 = arg7;
        boolean v8_1 = !this.a.b() || v8 == v1 ? false : true;
        ((ListMenuItemView)v2).setGroupDividerEnabled(v8_1);
        View v8_2 = arg7;
        if(this.c) {
            ((ListMenuItemView)v2).setForceShowIcon(true);
        }

        ((a)v8_2).a(this.a(arg6), 0);
        return arg7;
    }

    public void notifyDataSetChanged() {
        this.b();
        super.notifyDataSetChanged();
    }
}

