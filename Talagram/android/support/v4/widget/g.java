package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter$FilterResults;
import android.widget.Filter;

class g extends Filter {
    interface a {
        Cursor a(CharSequence arg1);

        Cursor a();

        void a(Cursor arg1);

        CharSequence c(Cursor arg1);
    }

    a a;

    g(a arg1) {
        super();
        this.a = arg1;
    }

    public CharSequence convertResultToString(Object arg2) {
        return this.a.c(((Cursor)arg2));
    }

    protected Filter$FilterResults performFiltering(CharSequence arg3) {
        Cursor v3 = this.a.a(arg3);
        Filter$FilterResults v0 = new Filter$FilterResults();
        if(v3 != null) {
            v0.count = v3.getCount();
        }
        else {
            v0.count = 0;
            Object v3_1 = null;
        }

        v0.values = v3;
        return v0;
    }

    protected void publishResults(CharSequence arg2, Filter$FilterResults arg3) {
        Cursor v2 = this.a.a();
        if(arg3.values != null && arg3.values != v2) {
            this.a.a(arg3.values);
        }
    }
}

