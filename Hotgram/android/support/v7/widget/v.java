package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.view.s;
import android.support.v4.view.t;
import android.support.v7.a.a$j;
import android.support.v7.view.d;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class v extends Spinner implements s {
    class a implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public a(SpinnerAdapter arg3, Resources$Theme arg4) {
            super();
            this.a = arg3;
            if((arg3 instanceof ListAdapter)) {
                this.b = arg3;
            }

            if(arg4 != null) {
                if(Build$VERSION.SDK_INT >= 23 && ((arg3 instanceof ThemedSpinnerAdapter))) {
                    if(((ThemedSpinnerAdapter)arg3).getDropDownViewTheme() != arg4) {
                        ((ThemedSpinnerAdapter)arg3).setDropDownViewTheme(arg4);
                    }
                    else {
                    }

                    return;
                }

                if(!(arg3 instanceof bg)) {
                    return;
                }

                if(((bg)arg3).a() != null) {
                    return;
                }

                ((bg)arg3).a(arg4);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter v0 = this.b;
            if(v0 != null) {
                return v0.areAllItemsEnabled();
            }

            return 1;
        }

        public int getCount() {
            int v0 = this.a == null ? 0 : this.a.getCount();
            return v0;
        }

        public View getDropDownView(int arg2, View arg3, ViewGroup arg4) {
            View v2 = this.a == null ? null : this.a.getDropDownView(arg2, arg3, arg4);
            return v2;
        }

        public Object getItem(int arg2) {
            Object v2 = this.a == null ? null : this.a.getItem(arg2);
            return v2;
        }

        public long getItemId(int arg3) {
            long v0 = this.a == null ? -1 : this.a.getItemId(arg3);
            return v0;
        }

        public int getItemViewType(int arg1) {
            return 0;
        }

        public View getView(int arg1, View arg2, ViewGroup arg3) {
            return this.getDropDownView(arg1, arg2, arg3);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            boolean v0 = this.a == null || !this.a.hasStableIds() ? false : true;
            return v0;
        }

        public boolean isEmpty() {
            boolean v0 = this.getCount() == 0 ? true : false;
            return v0;
        }

        public boolean isEnabled(int arg2) {
            ListAdapter v0 = this.b;
            if(v0 != null) {
                return v0.isEnabled(arg2);
            }

            return 1;
        }

        public void registerDataSetObserver(DataSetObserver arg2) {
            if(this.a != null) {
                this.a.registerDataSetObserver(arg2);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver arg2) {
            if(this.a != null) {
                this.a.unregisterDataSetObserver(arg2);
            }
        }
    }

    class b extends aq {
        ListAdapter a;
        private CharSequence h;
        private final Rect i;

        public b(v arg1, Context arg2, AttributeSet arg3, int arg4) {
            this.b = arg1;
            super(arg2, arg3, arg4);
            this.i = new Rect();
            this.b(((View)arg1));
            this.a(true);
            this.a(0);
            this.a(new AdapterView$OnItemClickListener(arg1) {
                public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                    this.b.b.setSelection(arg3);
                    if(this.b.b.getOnItemClickListener() != null) {
                        this.b.b.performItemClick(arg2, arg3, this.b.a.getItemId(arg3));
                    }

                    this.b.c();
                }
            });
        }

        public void a(CharSequence arg1) {
            this.h = arg1;
        }

        public void a() {
            boolean v0 = this.d();
            this.f();
            this.h(2);
            super.a();
            this.e().setChoiceMode(1);
            this.i(this.b.getSelectedItemPosition());
            if(v0) {
                return;
            }

            ViewTreeObserver v0_1 = this.b.getViewTreeObserver();
            if(v0_1 != null) {
                android.support.v7.widget.v$b$2 v1 = new ViewTreeObserver$OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        if(!this.a.a(this.a.b)) {
                            this.a.c();
                        }
                        else {
                            this.a.f();
                            b.a(this.a);
                        }
                    }
                };
                v0_1.addOnGlobalLayoutListener(((ViewTreeObserver$OnGlobalLayoutListener)v1));
                this.a(new PopupWindow$OnDismissListener(((ViewTreeObserver$OnGlobalLayoutListener)v1)) {
                    public void onDismiss() {
                        ViewTreeObserver v0 = this.b.b.getViewTreeObserver();
                        if(v0 != null) {
                            v0.removeGlobalOnLayoutListener(this.a);
                        }
                    }
                });
            }
        }

        public void a(ListAdapter arg1) {
            super.a(arg1);
            this.a = arg1;
        }

        static void a(b arg0) {
            super.a();
        }

        boolean a(View arg2) {
            boolean v2 = !t.D(arg2) || !arg2.getGlobalVisibleRect(this.i) ? false : true;
            return v2;
        }

        public CharSequence b() {
            return this.h;
        }

        void f() {
            int v4;
            int v0_1;
            Drawable v0 = this.h();
            int v1 = 0;
            if(v0 != null) {
                v0.getPadding(this.b.c);
                v0_1 = bs.a(this.b) ? this.b.c.right : -this.b.c.left;
                v1 = v0_1;
            }
            else {
                Rect v0_2 = this.b.c;
                this.b.c.right = 0;
                v0_2.left = 0;
            }

            v0_1 = this.b.getPaddingLeft();
            int v2 = this.b.getPaddingRight();
            int v3 = this.b.getWidth();
            if(this.b.b == -2) {
                v4 = this.b.a(this.a, this.h());
                int v5 = this.b.getContext().getResources().getDisplayMetrics().widthPixels - this.b.c.left - this.b.c.right;
                if(v4 > v5) {
                    v4 = v5;
                }

                v4 = Math.max(v4, v3 - v0_1 - v2);
            }
            else {
                if(this.b.b == -1) {
                    v4 = v3 - v0_1 - v2;
                    goto label_57;
                }

                v4 = this.b.b;
            }

        label_57:
            this.g(v4);
            v1 += bs.a(this.b) ? v3 - v2 - this.l() : v0_1;
            this.c(v1);
        }
    }

    b a;
    int b;
    final Rect c;
    private static final int[] d;
    private final g e;
    private final Context f;
    private am g;
    private SpinnerAdapter h;
    private final boolean i;

    static {
        v.d = new int[]{16843505};
    }

    public v(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, android.support.v7.a.a$a.spinnerStyle);
    }

    public v(Context arg2, AttributeSet arg3, int arg4) {
        this(arg2, arg3, arg4, -1);
    }

    public v(Context arg7, AttributeSet arg8, int arg9, int arg10) {
        this(arg7, arg8, arg9, arg10, null);
    }

    public v(Context arg8, AttributeSet arg9, int arg10, int arg11, Resources$Theme arg12) {
        TypedArray v12_2;
        d v3;
        super(arg8, arg9, arg10);
        this.c = new Rect();
        bk v0 = bk.a(arg8, arg9, j.Spinner, arg10, 0);
        this.e = new g(((View)this));
        TypedArray v2 = null;
        if(arg12 != null) {
            v3 = new d(arg8, arg12);
            goto label_14;
        }
        else {
            int v12 = v0.g(j.Spinner_popupTheme, 0);
            if(v12 != 0) {
                v3 = new d(arg8, v12);
            label_14:
                this.f = ((Context)v3);
            }
            else {
                Context v12_1 = Build$VERSION.SDK_INT < 23 ? arg8 : ((Context)v2);
                this.f = v12_1;
            }
        }

        if(this.f == null) {
            goto label_81;
        }

        if(arg11 != -1) {
            goto label_59;
        }

        try {
            v12_2 = arg8.obtainStyledAttributes(arg9, v.d, arg10, 0);
        }
        catch(Throwable v8) {
            v12_2 = v2;
            goto label_56;
        }
        catch(Exception v4) {
            v12_2 = v2;
            goto label_50;
        }

        try {
            if(v12_2.hasValue(0)) {
                arg11 = v12_2.getInt(0, 0);
            }

            goto label_40;
        }
        catch(Throwable v8) {
        }
        catch(Exception v4) {
            try {
            label_50:
                Log.i("AppCompatSpinner", "Could not read android:spinnerMode", ((Throwable)v4));
                if(v12_2 != null) {
                }
                else {
                    goto label_59;
                }
            }
            catch(Throwable v8) {
                goto label_56;
            }

            goto label_41;
        }

    label_56:
        if(v12_2 != null) {
            v12_2.recycle();
        }

        throw v8;
    label_40:
        if(v12_2 != null) {
        label_41:
            v12_2.recycle();
        }

    label_59:
        if(arg11 == 1) {
            b v11 = new b(this, this.f, arg9, arg10);
            bk v12_3 = bk.a(this.f, arg9, j.Spinner, arg10, 0);
            this.b = v12_3.f(j.Spinner_android_dropDownWidth, -2);
            v11.a(v12_3.a(j.Spinner_android_popupBackground));
            v11.a(v0.d(j.Spinner_android_prompt));
            v12_3.a();
            this.a = v11;
            this.g = new am(((View)this), v11) {
                public android.support.v7.view.menu.s a() {
                    return this.a;
                }

                public boolean b() {
                    if(!this.b.a.d()) {
                        this.b.a.a();
                    }

                    return 1;
                }
            };
        }

    label_81:
        CharSequence[] v11_1 = v0.f(j.Spinner_android_entries);
        if(v11_1 != null) {
            ArrayAdapter v12_4 = new ArrayAdapter(arg8, 17367048, ((Object[])v11_1));
            v12_4.setDropDownViewResource(android.support.v7.a.a$g.support_simple_spinner_dropdown_item);
            this.setAdapter(((SpinnerAdapter)v12_4));
        }

        v0.a();
        this.i = true;
        if(this.h != null) {
            this.setAdapter(this.h);
            this.h = ((SpinnerAdapter)v2);
        }

        this.e.a(arg9, arg10);
    }

    int a(SpinnerAdapter arg11, Drawable arg12) {
        int v0 = 0;
        if(arg11 == null) {
            return 0;
        }

        int v1 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
        int v2 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
        int v3 = Math.max(0, this.getSelectedItemPosition());
        int v4 = Math.min(arg11.getCount(), v3 + 15);
        v3 = Math.max(0, v3 - (15 - (v4 - v3)));
        View v5 = null;
        View v6 = v5;
        int v7 = 0;
        while(v3 < v4) {
            int v8 = arg11.getItemViewType(v3);
            if(v8 != v0) {
                v6 = v5;
                v0 = v8;
            }

            v6 = arg11.getView(v3, v6, ((ViewGroup)this));
            if(v6.getLayoutParams() == null) {
                v6.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
            }

            v6.measure(v1, v2);
            v7 = Math.max(v7, v6.getMeasuredWidth());
            ++v3;
        }

        if(arg12 != null) {
            arg12.getPadding(this.c);
            v7 += this.c.left + this.c.right;
        }

        return v7;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.e != null) {
            this.e.c();
        }
    }

    public int getDropDownHorizontalOffset() {
        if(this.a != null) {
            return this.a.j();
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }

        return 0;
    }

    public int getDropDownVerticalOffset() {
        if(this.a != null) {
            return this.a.k();
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }

        return 0;
    }

    public int getDropDownWidth() {
        if(this.a != null) {
            return this.b;
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }

        return 0;
    }

    public Drawable getPopupBackground() {
        if(this.a != null) {
            return this.a.h();
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }

        return null;
    }

    public Context getPopupContext() {
        if(this.a != null) {
            return this.f;
        }

        if(Build$VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }

        return null;
    }

    public CharSequence getPrompt() {
        CharSequence v0 = this.a != null ? this.a.b() : super.getPrompt();
        return v0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        ColorStateList v0 = this.e != null ? this.e.a() : null;
        return v0;
    }

    public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode v0 = this.e != null ? this.e.b() : null;
        return v0;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.a != null && (this.a.d())) {
            this.a.c();
        }
    }

    protected void onMeasure(int arg3, int arg4) {
        super.onMeasure(arg3, arg4);
        if(this.a != null && View$MeasureSpec.getMode(arg3) == -2147483648) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.a(this.getAdapter(), this.getBackground())), View$MeasureSpec.getSize(arg3)), this.getMeasuredHeight());
        }
    }

    public boolean onTouchEvent(MotionEvent arg2) {
        if(this.g != null && (this.g.onTouch(((View)this), arg2))) {
            return 1;
        }

        return super.onTouchEvent(arg2);
    }

    public boolean performClick() {
        if(this.a != null) {
            if(!this.a.d()) {
                this.a.a();
            }

            return 1;
        }

        return super.performClick();
    }

    public void setAdapter(SpinnerAdapter arg4) {
        if(!this.i) {
            this.h = arg4;
            return;
        }

        super.setAdapter(arg4);
        if(this.a != null) {
            Context v0 = this.f == null ? this.getContext() : this.f;
            this.a.a(new a(arg4, v0.getTheme()));
        }
    }

    public void setAdapter(Adapter arg1) {
        this.setAdapter(((SpinnerAdapter)arg1));
    }

    public void setBackgroundDrawable(Drawable arg2) {
        super.setBackgroundDrawable(arg2);
        if(this.e != null) {
            this.e.a(arg2);
        }
    }

    public void setBackgroundResource(int arg2) {
        super.setBackgroundResource(arg2);
        if(this.e != null) {
            this.e.a(arg2);
        }
    }

    public void setDropDownHorizontalOffset(int arg3) {
        if(this.a != null) {
            this.a.c(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(arg3);
        }
    }

    public void setDropDownVerticalOffset(int arg3) {
        if(this.a != null) {
            this.a.d(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(arg3);
        }
    }

    public void setDropDownWidth(int arg3) {
        if(this.a != null) {
            this.b = arg3;
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(arg3);
        }
    }

    public void setPopupBackgroundDrawable(Drawable arg3) {
        if(this.a != null) {
            this.a.a(arg3);
        }
        else if(Build$VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(arg3);
        }
    }

    public void setPopupBackgroundResource(int arg2) {
        this.setPopupBackgroundDrawable(android.support.v7.c.a.a.b(this.getPopupContext(), arg2));
    }

    public void setPrompt(CharSequence arg2) {
        if(this.a != null) {
            this.a.a(arg2);
        }
        else {
            super.setPrompt(arg2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList arg2) {
        if(this.e != null) {
            this.e.a(arg2);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff$Mode arg2) {
        if(this.e != null) {
            this.e.a(arg2);
        }
    }
}

