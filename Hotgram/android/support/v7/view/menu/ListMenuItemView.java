package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.t;
import android.support.v7.a.a$f;
import android.support.v7.a.a$g;
import android.support.v7.widget.bk;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.AbsListView$SelectionBoundsAdjuster;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListMenuItemView extends LinearLayout implements a, AbsListView$SelectionBoundsAdjuster {
    private j a;
    private ImageView b;
    private RadioButton c;
    private TextView d;
    private CheckBox e;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private LinearLayout i;
    private Drawable j;
    private int k;
    private Context l;
    private boolean m;
    private Drawable n;
    private boolean o;
    private int p;
    private LayoutInflater q;
    private boolean r;

    public ListMenuItemView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, android.support.v7.a.a$a.listMenuViewStyle);
    }

    public ListMenuItemView(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5);
        bk v5 = bk.a(this.getContext(), arg5, android.support.v7.a.a$j.MenuView, arg6, 0);
        this.j = v5.a(android.support.v7.a.a$j.MenuView_android_itemBackground);
        this.k = v5.g(android.support.v7.a.a$j.MenuView_android_itemTextAppearance, -1);
        this.m = v5.a(android.support.v7.a.a$j.MenuView_preserveIconSpacing, false);
        this.l = arg4;
        this.n = v5.a(android.support.v7.a.a$j.MenuView_subMenuArrow);
        TypedArray v4 = arg4.getTheme().obtainStyledAttributes(null, new int[]{16843049}, android.support.v7.a.a$a.dropDownListViewStyle, 0);
        this.o = v4.hasValue(0);
        v5.a();
        v4.recycle();
    }

    private void a(View arg2) {
        this.a(arg2, -1);
    }

    private void a(View arg2, int arg3) {
        if(this.i != null) {
            this.i.addView(arg2, arg3);
        }
        else {
            this.addView(arg2, arg3);
        }
    }

    public void a(j arg2, int arg3) {
        this.a = arg2;
        this.p = arg3;
        arg3 = arg2.isVisible() ? 0 : 8;
        this.setVisibility(arg3);
        this.setTitle(arg2.a(((a)this)));
        this.setCheckable(arg2.isCheckable());
        this.a(arg2.f(), arg2.d());
        this.setIcon(arg2.getIcon());
        this.setEnabled(arg2.isEnabled());
        this.setSubMenuArrowVisible(arg2.hasSubMenu());
        this.setContentDescription(arg2.getContentDescription());
    }

    public void a(boolean arg2, char arg3) {
        int v2 = !arg2 || !this.a.f() ? 8 : 0;
        if(v2 == 0) {
            this.f.setText(this.a.e());
        }

        if(this.f.getVisibility() != v2) {
            this.f.setVisibility(v2);
        }
    }

    public boolean a() {
        return 0;
    }

    public void adjustListItemSelectionBounds(Rect arg5) {
        if(this.h != null && this.h.getVisibility() == 0) {
            ViewGroup$LayoutParams v0 = this.h.getLayoutParams();
            arg5.top += this.h.getHeight() + ((LinearLayout$LayoutParams)v0).topMargin + ((LinearLayout$LayoutParams)v0).bottomMargin;
        }
    }

    private void b() {
        this.b = this.getInflater().inflate(g.abc_list_menu_item_icon, ((ViewGroup)this), false);
        this.a(this.b, 0);
    }

    private void c() {
        this.c = this.getInflater().inflate(g.abc_list_menu_item_radio, ((ViewGroup)this), false);
        this.a(this.c);
    }

    private void d() {
        this.e = this.getInflater().inflate(g.abc_list_menu_item_checkbox, ((ViewGroup)this), false);
        this.a(this.e);
    }

    private LayoutInflater getInflater() {
        if(this.q == null) {
            this.q = LayoutInflater.from(this.getContext());
        }

        return this.q;
    }

    public j getItemData() {
        return this.a;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        t.a(((View)this), this.j);
        this.d = this.findViewById(f.title);
        if(this.k != -1) {
            this.d.setTextAppearance(this.l, this.k);
        }

        this.f = this.findViewById(f.shortcut);
        this.g = this.findViewById(f.submenuarrow);
        if(this.g != null) {
            this.g.setImageDrawable(this.n);
        }

        this.h = this.findViewById(f.group_divider);
        this.i = this.findViewById(f.content);
    }

    protected void onMeasure(int arg4, int arg5) {
        if(this.b != null && (this.m)) {
            ViewGroup$LayoutParams v0 = this.getLayoutParams();
            ViewGroup$LayoutParams v1 = this.b.getLayoutParams();
            if(v0.height > 0 && ((LinearLayout$LayoutParams)v1).width <= 0) {
                ((LinearLayout$LayoutParams)v1).width = v0.height;
            }
        }

        super.onMeasure(arg4, arg5);
    }

    public void setCheckable(boolean arg4) {
        CheckBox v1;
        RadioButton v0;
        if(!arg4 && this.c == null && this.e == null) {
            return;
        }

        if(this.a.g()) {
            if(this.c == null) {
                this.c();
            }

            v0 = this.c;
            v1 = this.e;
        }
        else {
            if(this.e == null) {
                this.d();
            }

            CheckBox v0_1 = this.e;
            RadioButton v1_1 = this.c;
        }

        int v2 = 8;
        if(arg4) {
            ((CompoundButton)v0).setChecked(this.a.isChecked());
            if(((CompoundButton)v0).getVisibility() != 0) {
                ((CompoundButton)v0).setVisibility(0);
            }

            if(v1 == null) {
                return;
            }

            if(((CompoundButton)v1).getVisibility() == v2) {
                return;
            }

            ((CompoundButton)v1).setVisibility(v2);
        }
        else {
            if(this.e != null) {
                this.e.setVisibility(v2);
            }

            if(this.c == null) {
                return;
            }

            this.c.setVisibility(v2);
        }
    }

    public void setChecked(boolean arg2) {
        RadioButton v0;
        if(this.a.g()) {
            if(this.c == null) {
                this.c();
            }

            v0 = this.c;
        }
        else {
            if(this.e == null) {
                this.d();
            }

            CheckBox v0_1 = this.e;
        }

        ((CompoundButton)v0).setChecked(arg2);
    }

    public void setForceShowIcon(boolean arg1) {
        this.r = arg1;
        this.m = arg1;
    }

    public void setGroupDividerEnabled(boolean arg3) {
        if(this.h != null) {
            ImageView v0 = this.h;
            int v3 = (this.o) || !arg3 ? 8 : 0;
            v0.setVisibility(v3);
        }
    }

    public void setIcon(Drawable arg4) {
        int v0 = (this.a.i()) || (this.r) ? 1 : 0;
        if(v0 == 0 && !this.m) {
            return;
        }

        if(this.b == null && arg4 == null && !this.m) {
            return;
        }

        if(this.b == null) {
            this.b();
        }

        if(arg4 != null || (this.m)) {
            ImageView v2 = this.b;
            if(v0 != 0) {
            }
            else {
                arg4 = null;
            }

            v2.setImageDrawable(arg4);
            if(this.b.getVisibility() == 0) {
                return;
            }

            this.b.setVisibility(0);
        }
        else {
            this.b.setVisibility(8);
        }
    }

    private void setSubMenuArrowVisible(boolean arg2) {
        if(this.g != null) {
            ImageView v0 = this.g;
            int v2 = arg2 ? 0 : 8;
            v0.setVisibility(v2);
        }
    }

    public void setTitle(CharSequence arg2) {
        int v0;
        TextView v2;
        if(arg2 != null) {
            this.d.setText(arg2);
            if(this.d.getVisibility() != 0) {
                v2 = this.d;
                v0 = 0;
                goto label_8;
            }
        }
        else {
            v0 = 8;
            if(this.d.getVisibility() != v0) {
                v2 = this.d;
            label_8:
                v2.setVisibility(v0);
            }
        }
    }
}

