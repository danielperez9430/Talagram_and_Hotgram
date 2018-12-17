package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.view.x;
import android.support.v4.widget.m;
import android.support.v7.d.a.c;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

class aj extends ListView {
    class a extends c {
        private boolean a;

        a(Drawable arg1) {
            super(arg1);
            this.a = true;
        }

        void a(boolean arg1) {
            this.a = arg1;
        }

        public void draw(Canvas arg2) {
            if(this.a) {
                super.draw(arg2);
            }
        }

        public void setHotspot(float arg2, float arg3) {
            if(this.a) {
                super.setHotspot(arg2, arg3);
            }
        }

        public void setHotspotBounds(int arg2, int arg3, int arg4, int arg5) {
            if(this.a) {
                super.setHotspotBounds(arg2, arg3, arg4, arg5);
            }
        }

        public boolean setState(int[] arg2) {
            if(this.a) {
                return super.setState(arg2);
            }

            return 0;
        }

        public boolean setVisible(boolean arg2, boolean arg3) {
            if(this.a) {
                return super.setVisible(arg2, arg3);
            }

            return 0;
        }
    }

    class b implements Runnable {
        b(aj arg1) {
            this.a = arg1;
            super();
        }

        public void a() {
            this.a.a = null;
            this.a.removeCallbacks(((Runnable)this));
        }

        public void b() {
            this.a.post(((Runnable)this));
        }

        public void run() {
            this.a.a = null;
            this.a.drawableStateChanged();
        }
    }

    b a;
    private final Rect b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private Field h;
    private a i;
    private boolean j;
    private boolean k;
    private boolean l;
    private x m;
    private m n;

    aj(Context arg3, boolean arg4) {
        super(arg3, null, android.support.v7.a.a$a.dropDownListViewStyle);
        this.b = new Rect();
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.k = arg4;
        this.setCacheColorHint(0);
        try {
            this.h = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.h.setAccessible(true);
        }
        catch(NoSuchFieldException v3) {
            v3.printStackTrace();
        }
    }

    private void a() {
        Drawable v0 = this.getSelector();
        if(v0 != null && (this.c()) && (this.isPressed())) {
            v0.setState(this.getDrawableState());
        }
    }

    private void a(int arg5, View arg6) {
        Drawable v0 = this.getSelector();
        boolean v1 = true;
        int v3 = v0 == null || arg5 == -1 ? 0 : 1;
        if(v3 != 0) {
            v0.setVisible(false, false);
        }

        this.b(arg5, arg6);
        if(v3 != 0) {
            Rect v5 = this.b;
            float v6 = v5.exactCenterX();
            float v5_1 = v5.exactCenterY();
            if(this.getVisibility() == 0) {
            }
            else {
                v1 = false;
            }

            v0.setVisible(v1, false);
            android.support.v4.graphics.drawable.a.a(v0, v6, v5_1);
        }
    }

    private void a(int arg2, View arg3, float arg4, float arg5) {
        this.a(arg2, arg3);
        Drawable v3 = this.getSelector();
        if(v3 != null && arg2 != -1) {
            android.support.v4.graphics.drawable.a.a(v3, arg4, arg5);
        }
    }

    private void a(Canvas arg3) {
        if(!this.b.isEmpty()) {
            Drawable v0 = this.getSelector();
            if(v0 != null) {
                v0.setBounds(this.b);
                v0.draw(arg3);
            }
        }
    }

    private void a(View arg3, int arg4) {
        this.performItemClick(arg3, arg4, this.getItemIdAtPosition(arg4));
    }

    private void a(View arg7, int arg8, float arg9, float arg10) {
        this.l = true;
        int v2 = 21;
        if(Build$VERSION.SDK_INT >= v2) {
            this.drawableHotspotChanged(arg9, arg10);
        }

        if(!this.isPressed()) {
            this.setPressed(true);
        }

        this.layoutChildren();
        if(this.g != -1) {
            View v1 = this.getChildAt(this.g - this.getFirstVisiblePosition());
            if(v1 != null && v1 != arg7 && (v1.isPressed())) {
                v1.setPressed(false);
            }
        }

        this.g = arg8;
        float v1_1 = arg9 - (((float)arg7.getLeft()));
        float v3 = arg10 - (((float)arg7.getTop()));
        if(Build$VERSION.SDK_INT >= v2) {
            arg7.drawableHotspotChanged(v1_1, v3);
        }

        if(!arg7.isPressed()) {
            arg7.setPressed(true);
        }

        this.a(arg8, arg7, arg9, arg10);
        this.setSelectorEnabled(false);
        this.refreshDrawableState();
    }

    public int a(int arg11, int arg12, int arg13, int arg14, int arg15) {
        arg12 = this.getListPaddingTop();
        arg13 = this.getListPaddingBottom();
        this.getListPaddingLeft();
        this.getListPaddingRight();
        int v0 = this.getDividerHeight();
        Drawable v1 = this.getDivider();
        ListAdapter v2 = this.getAdapter();
        if(v2 == null) {
            return arg12 + arg13;
        }

        arg12 += arg13;
        if(v0 <= 0 || v1 == null) {
            v0 = 0;
        }
        else {
        }

        int v1_1 = v2.getCount();
        View v3 = null;
        int v5 = arg12;
        View v6 = v3;
        arg12 = 0;
        int v4 = 0;
        int v7 = 0;
        while(arg12 < v1_1) {
            int v8 = v2.getItemViewType(arg12);
            if(v8 != v4) {
                v6 = v3;
                v4 = v8;
            }

            v6 = v2.getView(arg12, v6, ((ViewGroup)this));
            ViewGroup$LayoutParams v8_1 = v6.getLayoutParams();
            if(v8_1 == null) {
                v8_1 = this.generateDefaultLayoutParams();
                v6.setLayoutParams(v8_1);
            }

            v8 = v8_1.height > 0 ? View$MeasureSpec.makeMeasureSpec(v8_1.height, 1073741824) : View$MeasureSpec.makeMeasureSpec(0, 0);
            v6.measure(arg11, v8);
            v6.forceLayout();
            if(arg12 > 0) {
                v5 += v0;
            }

            v5 += v6.getMeasuredHeight();
            if(v5 >= arg14) {
                if(arg15 >= 0 && arg12 > arg15 && v7 > 0 && v5 != arg14) {
                    arg14 = v7;
                }

                return arg14;
            }

            if(arg15 >= 0 && arg12 >= arg15) {
                v7 = v5;
            }

            ++arg12;
        }

        return v5;
    }

    public boolean a(MotionEvent arg8, int arg9) {
        int v0 = arg8.getActionMasked();
        switch(v0) {
            case 1: {
                goto label_12;
            }
            case 2: {
                goto label_10;
            }
            case 3: {
                goto label_7;
            }
        }

        goto label_4;
    label_10:
        boolean v3 = true;
        goto label_13;
    label_12:
        v3 = false;
    label_13:
        arg9 = arg8.findPointerIndex(arg9);
        if(arg9 < 0) {
        label_7:
            arg9 = 0;
            v3 = false;
            goto label_34;
        }

        int v4 = ((int)arg8.getX(arg9));
        arg9 = ((int)arg8.getY(arg9));
        int v5 = this.pointToPosition(v4, arg9);
        if(v5 == -1) {
            arg9 = 1;
            goto label_34;
        }

        View v3_1 = this.getChildAt(v5 - this.getFirstVisiblePosition());
        this.a(v3_1, v5, ((float)v4), ((float)arg9));
        if(v0 == 1) {
            this.a(v3_1, v5);
        }

    label_4:
        arg9 = 0;
        v3 = true;
    label_34:
        if(!v3 || arg9 != 0) {
            this.b();
        }

        if(v3) {
            if(this.n == null) {
                this.n = new m(((ListView)this));
            }

            this.n.a(true);
            this.n.onTouch(((View)this), arg8);
        }
        else {
            if(this.n == null) {
                return v3;
            }

            this.n.a(false);
        }

        return v3;
    }

    private void b(int arg6, View arg7) {
        Rect v0 = this.b;
        v0.set(arg7.getLeft(), arg7.getTop(), arg7.getRight(), arg7.getBottom());
        v0.left -= this.c;
        v0.top -= this.d;
        v0.right += this.e;
        v0.bottom += this.f;
        try {
            boolean v0_1 = this.h.getBoolean(this);
            if(arg7.isEnabled() == v0_1) {
                return;
            }

            this.h.set(this, Boolean.valueOf((((int)v0_1)) ^ 1));
            if(arg6 == -1) {
                return;
            }

            this.refreshDrawableState();
        }
        catch(IllegalAccessException v6) {
            v6.printStackTrace();
        }
    }

    private void b() {
        this.l = false;
        this.setPressed(false);
        this.drawableStateChanged();
        View v1 = this.getChildAt(this.g - this.getFirstVisiblePosition());
        if(v1 != null) {
            v1.setPressed(false);
        }

        if(this.m != null) {
            this.m.b();
            this.m = null;
        }
    }

    private boolean c() {
        return this.l;
    }

    protected void dispatchDraw(Canvas arg1) {
        this.a(arg1);
        super.dispatchDraw(arg1);
    }

    protected void drawableStateChanged() {
        if(this.a != null) {
            return;
        }

        super.drawableStateChanged();
        this.setSelectorEnabled(true);
        this.a();
    }

    public boolean hasFocus() {
        boolean v0 = (this.k) || (super.hasFocus()) ? true : false;
        return v0;
    }

    public boolean hasWindowFocus() {
        boolean v0 = (this.k) || (super.hasWindowFocus()) ? true : false;
        return v0;
    }

    public boolean isFocused() {
        boolean v0 = (this.k) || (super.isFocused()) ? true : false;
        return v0;
    }

    public boolean isInTouchMode() {
        boolean v0 = (this.k) && (this.j) || (super.isInTouchMode()) ? true : false;
        return v0;
    }

    protected void onDetachedFromWindow() {
        this.a = null;
        super.onDetachedFromWindow();
    }

    public boolean onHoverEvent(MotionEvent arg5) {
        if(Build$VERSION.SDK_INT < 26) {
            return super.onHoverEvent(arg5);
        }

        int v0 = arg5.getActionMasked();
        if(v0 == 10 && this.a == null) {
            this.a = new b(this);
            this.a.b();
        }

        boolean v1 = super.onHoverEvent(arg5);
        int v3 = -1;
        if(v0 == 9 || v0 == 7) {
            int v5 = this.pointToPosition(((int)arg5.getX()), ((int)arg5.getY()));
            if(v5 != v3 && v5 != this.getSelectedItemPosition()) {
                View v0_1 = this.getChildAt(v5 - this.getFirstVisiblePosition());
                if(v0_1.isEnabled()) {
                    this.setSelectionFromTop(v5, v0_1.getTop() - this.getTop());
                }

                this.a();
            }
        }
        else {
            this.setSelection(v3);
        }

        return v1;
    }

    public boolean onTouchEvent(MotionEvent arg3) {
        if(arg3.getAction() != 0) {
        }
        else {
            this.g = this.pointToPosition(((int)arg3.getX()), ((int)arg3.getY()));
        }

        if(this.a != null) {
            this.a.a();
        }

        return super.onTouchEvent(arg3);
    }

    void setListSelectionHidden(boolean arg1) {
        this.j = arg1;
    }

    public void setSelector(Drawable arg2) {
        a v0 = arg2 != null ? new a(arg2) : null;
        this.i = v0;
        super.setSelector(this.i);
        Rect v0_1 = new Rect();
        if(arg2 != null) {
            arg2.getPadding(v0_1);
        }

        this.c = v0_1.left;
        this.d = v0_1.top;
        this.e = v0_1.right;
        this.f = v0_1.bottom;
    }

    private void setSelectorEnabled(boolean arg2) {
        if(this.i != null) {
            this.i.a(arg2);
        }
    }
}

