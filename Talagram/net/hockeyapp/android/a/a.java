package net.hockeyapp.android.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import net.hockeyapp.android.c.c;
import net.hockeyapp.android.views.b;

public class a extends BaseAdapter {
    private Context a;
    private ArrayList b;

    public a(Context arg1, ArrayList arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public void a() {
        if(this.b != null) {
            this.b.clear();
        }
    }

    public void a(c arg2) {
        if(arg2 != null && this.b != null) {
            this.b.add(arg2);
        }
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int arg2) {
        return this.b.get(arg2);
    }

    public long getItemId(int arg3) {
        return ((long)arg3);
    }

    public View getView(int arg3, View arg4, ViewGroup arg5) {
        b v4;
        Object v5 = this.b.get(arg3);
        if(arg4 == null) {
            v4 = new b(this.a, null);
        }

        if(v5 != null) {
            v4.setFeedbackMessage(((c)v5));
        }

        v4.setIndex(arg3);
        return ((View)v4);
    }
}

