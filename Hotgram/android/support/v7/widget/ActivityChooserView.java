package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.a.a$f;
import android.support.v7.a.a$g;
import android.support.v7.a.a$h;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View$OnLongClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow$OnDismissListener;

public class ActivityChooserView extends ViewGroup {
    public class InnerLayout extends LinearLayout {
        private static final int[] a;

        static {
            InnerLayout.a = new int[]{16842964};
        }

        public InnerLayout(Context arg2, AttributeSet arg3) {
            super(arg2, arg3);
            bk v2 = bk.a(arg2, arg3, InnerLayout.a);
            this.setBackgroundDrawable(v2.a(0));
            v2.a();
        }
    }

    class a extends BaseAdapter {
        private d b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;

        public void a(boolean arg2) {
            if(this.f != arg2) {
                this.f = arg2;
                this.notifyDataSetChanged();
            }
        }

        public void a(int arg2) {
            if(this.c != arg2) {
                this.c = arg2;
                this.notifyDataSetChanged();
            }
        }

        public void a(boolean arg2, boolean arg3) {
            if(this.d != arg2 || this.e != arg3) {
                this.d = arg2;
                this.e = arg3;
                this.notifyDataSetChanged();
            }
        }

        public int a() {
            int v0 = this.c;
            this.c = 2147483647;
            int v1 = 0;
            int v2 = View$MeasureSpec.makeMeasureSpec(0, 0);
            int v3 = View$MeasureSpec.makeMeasureSpec(0, 0);
            int v4 = this.getCount();
            ViewGroup v5 = null;
            View v6 = ((View)v5);
            int v7 = 0;
            while(v1 < v4) {
                v6 = this.getView(v1, v6, v5);
                v6.measure(v2, v3);
                v7 = Math.max(v7, v6.getMeasuredWidth());
                ++v1;
            }

            this.c = v0;
            return v7;
        }

        public void a(d arg3) {
            d v0 = this.a.a.d();
            if(v0 != null && (this.a.isShown())) {
                v0.unregisterObserver(this.a.e);
            }

            this.b = arg3;
            if(arg3 != null && (this.a.isShown())) {
                arg3.registerObserver(this.a.e);
            }

            this.notifyDataSetChanged();
        }

        public ResolveInfo b() {
            return this.b.b();
        }

        public int c() {
            return this.b.a();
        }

        public d d() {
            return this.b;
        }

        public boolean e() {
            return this.d;
        }

        public int getCount() {
            int v0 = this.b.a();
            if(!this.d && this.b.b() != null) {
                --v0;
            }

            v0 = Math.min(v0, this.c);
            if(this.f) {
                ++v0;
            }

            return v0;
        }

        public Object getItem(int arg2) {
            switch(this.getItemViewType(arg2)) {
                case 0: {
                    goto label_7;
                }
                case 1: {
                    return null;
                }
            }

            throw new IllegalArgumentException();
            return null;
        label_7:
            if(!this.d && this.b.b() != null) {
                ++arg2;
            }

            return this.b.a(arg2);
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public int getItemViewType(int arg3) {
            if((this.f) && arg3 == this.getCount() - 1) {
                return 1;
            }

            return 0;
        }

        public View getView(int arg6, View arg7, ViewGroup arg8) {
            switch(this.getItemViewType(arg6)) {
                case 0: {
                    goto label_24;
                }
                case 1: {
                    goto label_7;
                }
            }

            throw new IllegalArgumentException();
        label_7:
            if(arg7 == null || arg7.getId() != 1) {
                arg7 = LayoutInflater.from(this.a.getContext()).inflate(g.abc_activity_chooser_view_list_item, arg8, false);
                arg7.setId(1);
                arg7.findViewById(f.title).setText(this.a.getContext().getString(h.abc_activity_chooser_view_see_all));
            }

            return arg7;
        label_24:
            if(arg7 == null || arg7.getId() != f.list_item) {
                arg7 = LayoutInflater.from(this.a.getContext()).inflate(g.abc_activity_chooser_view_list_item, arg8, false);
            }

            PackageManager v8 = this.a.getContext().getPackageManager();
            View v0 = arg7.findViewById(f.icon);
            Object v3 = this.getItem(arg6);
            ((ImageView)v0).setImageDrawable(((ResolveInfo)v3).loadIcon(v8));
            arg7.findViewById(f.title).setText(((ResolveInfo)v3).loadLabel(v8));
            if(!this.d || arg6 != 0 || !this.e) {
                arg7.setActivated(false);
            }
            else {
                arg7.setActivated(true);
            }

            return arg7;
        }

        public int getViewTypeCount() {
            return 3;
        }
    }

    class b implements View$OnClickListener, View$OnLongClickListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener {
        private void a() {
            if(this.a.f != null) {
                this.a.f.onDismiss();
            }
        }

        public void onClick(View arg2) {
            if(arg2 == this.a.c) {
                this.a.b();
                Intent v2 = this.a.a.d().b(this.a.a.d().a(this.a.a.b()));
                if(v2 != null) {
                    v2.addFlags(524288);
                    this.a.getContext().startActivity(v2);
                }
            }
            else if(arg2 == this.a.b) {
                this.a.g = false;
                this.a.a(this.a.h);
            }
            else {
                goto label_34;
            }

            return;
        label_34:
            throw new IllegalArgumentException();
        }

        public void onDismiss() {
            this.a();
            if(this.a.d != null) {
                this.a.d.a(false);
            }
        }

        public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
            switch(arg1.getAdapter().getItemViewType(arg3)) {
                case 0: {
                    this.a.b();
                    if(this.a.g) {
                        if(arg3 <= 0) {
                            return;
                        }

                        this.a.a.d().c(arg3);
                        return;
                    }

                    if(this.a.a.e()) {
                    }
                    else {
                        ++arg3;
                    }

                    Intent v1 = this.a.a.d().b(arg3);
                    if(v1 == null) {
                        return;
                    }

                    v1.addFlags(524288);
                    this.a.getContext().startActivity(v1);
                    break;
                }
                case 1: {
                    this.a.a(2147483647);
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }

        public boolean onLongClick(View arg3) {
            if(arg3 == this.a.c) {
                if(this.a.a.getCount() > 0) {
                    this.a.g = true;
                    this.a.a(this.a.h);
                }

                return 1;
            }

            throw new IllegalArgumentException();
        }
    }

    final a a;
    final FrameLayout b;
    final FrameLayout c;
    android.support.v4.view.b d;
    final DataSetObserver e;
    PopupWindow$OnDismissListener f;
    boolean g;
    int h;
    private final b i;
    private final View j;
    private final ImageView k;
    private final int l;
    private final ViewTreeObserver$OnGlobalLayoutListener m;
    private aq n;
    private boolean o;
    private int p;

    void a(int arg6) {
        a v3_1;
        if(this.a.d() != null) {
            this.getViewTreeObserver().addOnGlobalLayoutListener(this.m);
            int v0 = this.c.getVisibility() == 0 ? 1 : 0;
            int v3 = this.a.c();
            if(arg6 == 2147483647 || v3 <= arg6 + v0) {
                this.a.a(false);
                v3_1 = this.a;
            }
            else {
                this.a.a(true);
                v3_1 = this.a;
                --arg6;
            }

            v3_1.a(arg6);
            aq v6 = this.getListPopupWindow();
            if(!v6.d()) {
                if((this.g) || v0 == 0) {
                    this.a.a(true, ((boolean)v0));
                }
                else {
                    this.a.a(false, false);
                }

                v6.g(Math.min(this.a.a(), this.l));
                v6.a();
                if(this.d != null) {
                    this.d.a(true);
                }

                v6.e().setContentDescription(this.getContext().getString(h.abc_activitychooserview_choose_application));
                v6.e().setSelector(new ColorDrawable(0));
            }

            return;
        }

        throw new IllegalStateException("No data model. Did you call #setDataModel?");
    }

    public boolean a() {
        if(!this.c()) {
            if(!this.o) {
            }
            else {
                this.g = false;
                this.a(this.h);
                return 1;
            }
        }

        return 0;
    }

    public boolean b() {
        if(this.c()) {
            this.getListPopupWindow().c();
            ViewTreeObserver v0 = this.getViewTreeObserver();
            if(v0.isAlive()) {
                v0.removeGlobalOnLayoutListener(this.m);
            }
        }

        return 1;
    }

    public boolean c() {
        return this.getListPopupWindow().d();
    }

    public d getDataModel() {
        return this.a.d();
    }

    aq getListPopupWindow() {
        if(this.n == null) {
            this.n = new aq(this.getContext());
            this.n.a(this.a);
            this.n.b(((View)this));
            this.n.a(true);
            this.n.a(this.i);
            this.n.a(this.i);
        }

        return this.n;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        d v0 = this.a.d();
        if(v0 != null) {
            v0.registerObserver(this.e);
        }

        this.o = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d v0 = this.a.d();
        if(v0 != null) {
            v0.unregisterObserver(this.e);
        }

        ViewTreeObserver v0_1 = this.getViewTreeObserver();
        if(v0_1.isAlive()) {
            v0_1.removeGlobalOnLayoutListener(this.m);
        }

        if(this.c()) {
            this.b();
        }

        this.o = false;
    }

    protected void onLayout(boolean arg1, int arg2, int arg3, int arg4, int arg5) {
        this.j.layout(0, 0, arg4 - arg2, arg5 - arg3);
        if(!this.c()) {
            this.b();
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        View v0 = this.j;
        if(this.c.getVisibility() != 0) {
            arg4 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg4), 1073741824);
        }

        this.measureChild(v0, arg3, arg4);
        this.setMeasuredDimension(v0.getMeasuredWidth(), v0.getMeasuredHeight());
    }

    public void setActivityChooserModel(d arg2) {
        this.a.a(arg2);
        if(this.c()) {
            this.b();
            this.a();
        }
    }

    public void setDefaultActionButtonContentDescription(int arg1) {
        this.p = arg1;
    }

    public void setExpandActivityOverflowButtonContentDescription(int arg2) {
        this.k.setContentDescription(this.getContext().getString(arg2));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable arg2) {
        this.k.setImageDrawable(arg2);
    }

    public void setInitialActivityCount(int arg1) {
        this.h = arg1;
    }

    public void setOnDismissListener(PopupWindow$OnDismissListener arg1) {
        this.f = arg1;
    }

    public void setProvider(android.support.v4.view.b arg1) {
        this.d = arg1;
    }
}

