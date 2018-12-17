package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

public abstract class f extends BaseAdapter implements a, Filterable {
    class android.support.v4.widget.f$a extends ContentObserver {
        android.support.v4.widget.f$a(f arg1) {
            this.a = arg1;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return 1;
        }

        public void onChange(boolean arg1) {
            this.a.b();
        }
    }

    class b extends DataSetObserver {
        b(f arg1) {
            this.a = arg1;
            super();
        }

        public void onChanged() {
            this.a.a = true;
            this.a.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.a.a = false;
            this.a.notifyDataSetInvalidated();
        }
    }

    protected boolean a;
    protected boolean b;
    protected Cursor c;
    protected Context d;
    protected int e;
    protected android.support.v4.widget.f$a f;
    protected DataSetObserver g;
    protected g h;
    protected FilterQueryProvider i;

    public f(Context arg1, Cursor arg2, boolean arg3) {
        super();
        int v3 = arg3 ? 1 : 2;
        this.a(arg1, arg2, v3);
    }

    void a(Context arg4, Cursor arg5, int arg6) {
        b v4_1;
        boolean v1 = false;
        if((arg6 & 1) == 1) {
            arg6 |= 2;
            this.b = true;
        }
        else {
            this.b = false;
        }

        if(arg5 != null) {
            v1 = true;
        }

        this.c = arg5;
        this.a = v1;
        this.d = arg4;
        int v4 = v1 ? arg5.getColumnIndexOrThrow("_id") : -1;
        this.e = v4;
        if((arg6 & 2) == 2) {
            this.f = new android.support.v4.widget.f$a(this);
            v4_1 = new b(this);
        }
        else {
            DataSetObserver v4_2 = null;
            this.f = ((android.support.v4.widget.f$a)v4_2);
        }

        this.g = ((DataSetObserver)v4_1);
        if(v1) {
            if(this.f != null) {
                arg5.registerContentObserver(this.f);
            }

            if(this.g == null) {
                return;
            }

            arg5.registerDataSetObserver(this.g);
        }
    }

    public Cursor a() {
        return this.c;
    }

    public Cursor a(CharSequence arg2) {
        if(this.i != null) {
            return this.i.runQuery(arg2);
        }

        return this.c;
    }

    public abstract View a(Context arg1, Cursor arg2, ViewGroup arg3);

    public void a(Cursor arg1) {
        arg1 = this.b(arg1);
        if(arg1 != null) {
            arg1.close();
        }
    }

    public abstract void a(View arg1, Context arg2, Cursor arg3);

    public Cursor b(Cursor arg3) {
        if(arg3 == this.c) {
            return null;
        }

        Cursor v0 = this.c;
        if(v0 != null) {
            if(this.f != null) {
                v0.unregisterContentObserver(this.f);
            }

            if(this.g == null) {
                goto label_14;
            }

            v0.unregisterDataSetObserver(this.g);
        }

    label_14:
        this.c = arg3;
        if(arg3 != null) {
            if(this.f != null) {
                arg3.registerContentObserver(this.f);
            }

            if(this.g != null) {
                arg3.registerDataSetObserver(this.g);
            }

            this.e = arg3.getColumnIndexOrThrow("_id");
            this.a = true;
            this.notifyDataSetChanged();
        }
        else {
            this.e = -1;
            this.a = false;
            this.notifyDataSetInvalidated();
        }

        return v0;
    }

    public View b(Context arg1, Cursor arg2, ViewGroup arg3) {
        return this.a(arg1, arg2, arg3);
    }

    protected void b() {
        if((this.b) && this.c != null && !this.c.isClosed()) {
            this.a = this.c.requery();
        }
    }

    public CharSequence c(Cursor arg1) {
        String v1 = arg1 == null ? "" : arg1.toString();
        return ((CharSequence)v1);
    }

    public int getCount() {
        if((this.a) && this.c != null) {
            return this.c.getCount();
        }

        return 0;
    }

    public View getDropDownView(int arg2, View arg3, ViewGroup arg4) {
        if(this.a) {
            this.c.moveToPosition(arg2);
            if(arg3 == null) {
                arg3 = this.b(this.d, this.c, arg4);
            }

            this.a(arg3, this.d, this.c);
            return arg3;
        }

        return null;
    }

    public Filter getFilter() {
        if(this.h == null) {
            this.h = new g(((a)this));
        }

        return this.h;
    }

    public Object getItem(int arg2) {
        if((this.a) && this.c != null) {
            this.c.moveToPosition(arg2);
            return this.c;
        }

        return null;
    }

    public long getItemId(int arg4) {
        long v1 = 0;
        if((this.a) && this.c != null && (this.c.moveToPosition(arg4))) {
            return this.c.getLong(this.e);
        }

        return v1;
    }

    public View getView(int arg2, View arg3, ViewGroup arg4) {
        if(this.a) {
            if(this.c.moveToPosition(arg2)) {
                if(arg3 == null) {
                    arg3 = this.a(this.d, this.c, arg4);
                }

                this.a(arg3, this.d, this.c);
                return arg3;
            }

            StringBuilder v4 = new StringBuilder();
            v4.append("couldn\'t move cursor to position ");
            v4.append(arg2);
            throw new IllegalStateException(v4.toString());
        }

        throw new IllegalStateException("this should only be called when the cursor is valid");
    }

    public boolean hasStableIds() {
        return 1;
    }
}

