package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow$OnDismissListener;

abstract class m implements o, s, AdapterView$OnItemClickListener {
    private Rect a;

    m() {
        super();
    }

    public void a(Rect arg1) {
        this.a = arg1;
    }

    public abstract void a(h arg1);

    public abstract void a(PopupWindow$OnDismissListener arg1);

    public abstract void a(View arg1);

    public abstract void a(boolean arg1);

    public abstract void a(int arg1);

    protected static int a(ListAdapter arg9, ViewGroup arg10, Context arg11, int arg12) {
        int v0 = 0;
        int v1 = View$MeasureSpec.makeMeasureSpec(0, 0);
        int v2 = View$MeasureSpec.makeMeasureSpec(0, 0);
        int v3 = arg9.getCount();
        View v4 = null;
        ViewGroup v6 = arg10;
        View v7 = v4;
        int v10 = 0;
        int v5 = 0;
        while(v0 < v3) {
            int v8 = arg9.getItemViewType(v0);
            if(v8 != v10) {
                v7 = v4;
                v10 = v8;
            }

            if(v6 == null) {
                FrameLayout v6_1 = new FrameLayout(arg11);
            }

            v7 = arg9.getView(v0, v7, v6);
            v7.measure(v1, v2);
            v8 = v7.getMeasuredWidth();
            if(v8 >= arg12) {
                return arg12;
            }

            if(v8 > v5) {
                v5 = v8;
            }

            ++v0;
        }

        return v5;
    }

    protected static g a(ListAdapter arg1) {
        if((arg1 instanceof HeaderViewListAdapter)) {
            return ((HeaderViewListAdapter)arg1).getWrappedAdapter();
        }

        return arg1;
    }

    public void a(Context arg1, h arg2) {
    }

    public boolean a(h arg1, j arg2) {
        return 0;
    }

    public abstract void b(int arg1);

    protected static boolean b(h arg5) {
        int v0 = arg5.size();
        boolean v1 = false;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            MenuItem v3 = arg5.getItem(v2);
            if((v3.isVisible()) && v3.getIcon() != null) {
                return true;
            }
        }

        return v1;
    }

    public boolean b(h arg1, j arg2) {
        return 0;
    }

    public abstract void c(boolean arg1);

    public abstract void c(int arg1);

    protected boolean f() {
        return 1;
    }

    public Rect g() {
        return this.a;
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        Adapter v1 = arg1.getAdapter();
        h v2 = m.a(((ListAdapter)v1)).a;
        Object v1_1 = ((ListAdapter)v1).getItem(arg3);
        arg3 = this.f() ? 0 : 4;
        v2.a(((MenuItem)v1_1), ((o)this), arg3);
    }
}

