package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class p extends f {
    private int j;
    private int k;
    private LayoutInflater l;

    @Deprecated public p(Context arg1, int arg2, Cursor arg3, boolean arg4) {
        super(arg1, arg3, arg4);
        this.k = arg2;
        this.j = arg2;
        this.l = arg1.getSystemService("layout_inflater");
    }

    public View a(Context arg2, Cursor arg3, ViewGroup arg4) {
        return this.l.inflate(this.j, arg4, false);
    }

    public View b(Context arg2, Cursor arg3, ViewGroup arg4) {
        return this.l.inflate(this.k, arg4, false);
    }
}

