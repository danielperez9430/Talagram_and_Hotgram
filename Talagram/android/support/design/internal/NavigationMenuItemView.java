package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.design.a$d;
import android.support.design.a$e;
import android.support.design.a$f;
import android.support.design.a$h;
import android.support.v4.view.a.c;
import android.support.v4.view.t;
import android.support.v4.widget.q;
import android.support.v7.view.menu.j;
import android.support.v7.widget.bm;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;

public class NavigationMenuItemView extends a implements android.support.v7.view.menu.p$a {
    class android.support.design.internal.NavigationMenuItemView$1 extends android.support.v4.view.a {
        android.support.design.internal.NavigationMenuItemView$1(NavigationMenuItemView arg1) {
            this.a = arg1;
            super();
        }

        public void onInitializeAccessibilityNodeInfo(View arg1, c arg2) {
            super.onInitializeAccessibilityNodeInfo(arg1, arg2);
            arg2.a(this.a.c);
        }
    }

    boolean c;
    private static final int[] d;
    private final int e;
    private boolean f;
    private final CheckedTextView g;
    private FrameLayout h;
    private j i;
    private ColorStateList j;
    private boolean k;
    private Drawable l;
    private final android.support.v4.view.a m;

    static {
        NavigationMenuItemView.d = new int[]{16842912};
    }

    public NavigationMenuItemView(Context arg2) {
        this(arg2, null);
    }

    public NavigationMenuItemView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public NavigationMenuItemView(Context arg2, AttributeSet arg3, int arg4) {
        super(arg2, arg3, arg4);
        this.m = new android.support.design.internal.NavigationMenuItemView$1(this);
        this.setOrientation(0);
        LayoutInflater.from(arg2).inflate(h.design_navigation_menu_item, ((ViewGroup)this), true);
        this.e = arg2.getResources().getDimensionPixelSize(d.design_navigation_icon_size);
        this.g = this.findViewById(f.design_menu_item_text);
        this.g.setDuplicateParentStateEnabled(true);
        t.a(this.g, this.m);
    }

    public void a(j arg1, int arg2) {
        this.i = arg1;
        arg2 = arg1.isVisible() ? 0 : 8;
        this.setVisibility(arg2);
        if(this.getBackground() == null) {
            t.a(((View)this), this.d());
        }

        this.setCheckable(arg1.isCheckable());
        this.setChecked(arg1.isChecked());
        this.setEnabled(arg1.isEnabled());
        this.setTitle(arg1.getTitle());
        this.setIcon(arg1.getIcon());
        this.setActionView(arg1.getActionView());
        this.setContentDescription(arg1.getContentDescription());
        bm.a(((View)this), arg1.getTooltipText());
        this.c();
    }

    public boolean a() {
        return 0;
    }

    private boolean b() {
        boolean v0 = this.i.getTitle() != null || this.i.getIcon() != null || this.i.getActionView() == null ? false : true;
        return v0;
    }

    private void c() {
        int v1;
        ViewGroup$LayoutParams v0;
        if(this.b()) {
            this.g.setVisibility(8);
            if(this.h != null) {
                v0 = this.h.getLayoutParams();
                v1 = -1;
                goto label_10;
            }
        }
        else {
            this.g.setVisibility(0);
            if(this.h != null) {
                v0 = this.h.getLayoutParams();
                v1 = -2;
            label_10:
                ((android.support.v7.widget.ap$a)v0).width = v1;
                this.h.setLayoutParams(v0);
            }
        }
    }

    private StateListDrawable d() {
        TypedValue v0 = new TypedValue();
        if(this.getContext().getTheme().resolveAttribute(android.support.v7.a.a$a.colorControlHighlight, v0, true)) {
            StateListDrawable v1 = new StateListDrawable();
            v1.addState(NavigationMenuItemView.d, new ColorDrawable(v0.data));
            v1.addState(NavigationMenuItemView.EMPTY_STATE_SET, new ColorDrawable(0));
            return v1;
        }

        return null;
    }

    public j getItemData() {
        return this.i;
    }

    protected int[] onCreateDrawableState(int arg2) {
        int[] v2 = super.onCreateDrawableState(arg2 + 1);
        if(this.i != null && (this.i.isCheckable()) && (this.i.isChecked())) {
            NavigationMenuItemView.mergeDrawableStates(v2, NavigationMenuItemView.d);
        }

        return v2;
    }

    private void setActionView(View arg2) {
        if(arg2 != null) {
            if(this.h == null) {
                this.h = this.findViewById(f.design_menu_item_action_area_stub).inflate();
            }

            this.h.removeAllViews();
            this.h.addView(arg2);
        }
    }

    public void setCheckable(boolean arg3) {
        this.refreshDrawableState();
        if(this.c != arg3) {
            this.c = arg3;
            this.m.sendAccessibilityEvent(this.g, 2048);
        }
    }

    public void setChecked(boolean arg2) {
        this.refreshDrawableState();
        this.g.setChecked(arg2);
    }

    public void setHorizontalPadding(int arg2) {
        this.setPadding(arg2, 0, arg2, 0);
    }

    public void setIcon(Drawable arg4) {
        if(arg4 != null) {
            if(this.k) {
                Drawable$ConstantState v1 = arg4.getConstantState();
                if(v1 == null) {
                }
                else {
                    arg4 = v1.newDrawable();
                }

                arg4 = android.support.v4.graphics.drawable.a.g(arg4).mutate();
                android.support.v4.graphics.drawable.a.a(arg4, this.j);
            }

            arg4.setBounds(0, 0, this.e, this.e);
        }
        else {
            if(!this.f) {
                goto label_33;
            }

            if(this.l == null) {
                this.l = android.support.v4.content.a.f.a(this.getResources(), e.navigation_empty_icon, this.getContext().getTheme());
                if(this.l != null) {
                    this.l.setBounds(0, 0, this.e, this.e);
                }
            }

            arg4 = this.l;
        }

    label_33:
        q.a(this.g, arg4, null, null, null);
    }

    public void setIconPadding(int arg2) {
        this.g.setCompoundDrawablePadding(arg2);
    }

    void setIconTintList(ColorStateList arg1) {
        this.j = arg1;
        boolean v1 = this.j != null ? true : false;
        this.k = v1;
        if(this.i != null) {
            this.setIcon(this.i.getIcon());
        }
    }

    public void setNeedsEmptyIcon(boolean arg1) {
        this.f = arg1;
    }

    public void setTextAppearance(int arg2) {
        q.a(this.g, arg2);
    }

    public void setTextColor(ColorStateList arg2) {
        this.g.setTextColor(arg2);
    }

    public void setTitle(CharSequence arg2) {
        this.g.setText(arg2);
    }
}

