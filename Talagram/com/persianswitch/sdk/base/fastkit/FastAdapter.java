package com.persianswitch.sdk.base.fastkit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class FastAdapter extends BaseAdapter {
    protected List a;
    private final Context b;

    protected FastAdapter(Context arg1, List arg2) {
        super();
        this.b = arg1;
        this.a = arg2;
    }

    public Context a() {
        return this.b;
    }

    protected abstract FastViewHolder a(Context arg1, ViewGroup arg2);

    protected abstract void a(FastViewHolder arg1, int arg2);

    public List b() {
        return this.a;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int arg2) {
        return this.a.get(arg2);
    }

    public long getItemId(int arg3) {
        return 0;
    }

    public View getView(int arg2, View arg3, ViewGroup arg4) {
        View v4;
        FastViewHolder v3;
        if(arg3 == null) {
            v3 = this.a(this.b, arg4);
            v4 = v3.a();
            v4.setTag(v3);
        }
        else {
            v4 = arg3;
            Object v3_1 = arg3.getTag();
        }

        this.a(v3, arg2);
        return v4;
    }
}

