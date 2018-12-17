package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.widget.bk;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;

public final class ExpandedMenuView extends ListView implements b, p, AdapterView$OnItemClickListener {
    private static final int[] a;
    private h b;
    private int c;

    static {
        ExpandedMenuView.a = new int[]{16842964, 16843049};
    }

    public ExpandedMenuView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 16842868);
    }

    public ExpandedMenuView(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4);
        this.setOnItemClickListener(((AdapterView$OnItemClickListener)this));
        bk v3 = bk.a(arg3, arg4, ExpandedMenuView.a, arg5, 0);
        if(v3.g(0)) {
            this.setBackgroundDrawable(v3.a(0));
        }

        if(v3.g(1)) {
            this.setDivider(v3.a(1));
        }

        v3.a();
    }

    public void a(h arg1) {
        this.b = arg1;
    }

    public boolean a(j arg3) {
        return this.b.a(((MenuItem)arg3), 0);
    }

    public int getWindowAnimations() {
        return this.c;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.setChildrenDrawingCacheEnabled(false);
    }

    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
        this.a(this.getAdapter().getItem(arg3));
    }
}

