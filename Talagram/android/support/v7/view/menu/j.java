package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.a.a.b;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug$CapturedViewProperty;
import android.widget.LinearLayout;

public final class j implements b {
    private View A;
    private android.support.v4.view.b B;
    private MenuItem$OnActionExpandListener C;
    private boolean D;
    private ContextMenu$ContextMenuInfo E;
    h a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private CharSequence f;
    private CharSequence g;
    private Intent h;
    private char i;
    private int j;
    private char k;
    private int l;
    private Drawable m;
    private int n;
    private u o;
    private Runnable p;
    private MenuItem$OnMenuItemClickListener q;
    private CharSequence r;
    private CharSequence s;
    private ColorStateList t;
    private PorterDuff$Mode u;
    private boolean v;
    private boolean w;
    private boolean x;
    private int y;
    private int z;

    j(h arg3, int arg4, int arg5, int arg6, int arg7, CharSequence arg8, int arg9) {
        super();
        this.j = 4096;
        this.l = 4096;
        this.n = 0;
        this.t = null;
        this.u = null;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = 16;
        this.z = 0;
        this.D = false;
        this.a = arg3;
        this.b = arg5;
        this.c = arg4;
        this.d = arg6;
        this.e = arg7;
        this.f = arg8;
        this.z = arg9;
    }

    public void a(boolean arg2) {
        int v0 = this.y & -5;
        int v2 = arg2 ? 4 : 0;
        this.y = v2 | v0;
    }

    CharSequence a(a arg1) {
        CharSequence v1 = arg1 == null || !arg1.a() ? this.getTitle() : this.getTitleCondensed();
        return v1;
    }

    void a(ContextMenu$ContextMenuInfo arg1) {
        this.E = arg1;
    }

    public android.support.v4.view.b a() {
        return this.B;
    }

    public void a(u arg2) {
        this.o = arg2;
        arg2.setHeaderTitle(this.getTitle());
    }

    private Drawable a(Drawable arg2) {
        if(arg2 != null && (this.x) && ((this.v) || (this.w))) {
            arg2 = android.support.v4.graphics.drawable.a.g(arg2).mutate();
            if(this.v) {
                android.support.v4.graphics.drawable.a.a(arg2, this.t);
            }

            if(this.w) {
                android.support.v4.graphics.drawable.a.a(arg2, this.u);
            }

            this.x = false;
        }

        return arg2;
    }

    private static void a(StringBuilder arg0, int arg1, int arg2, String arg3) {
        if((arg1 & arg2) == arg2) {
            arg0.append(arg3);
        }
    }

    public b a(int arg4) {
        Context v0 = this.a.f();
        this.a(LayoutInflater.from(v0).inflate(arg4, new LinearLayout(v0), false));
        return this;
    }

    public b a(View arg3) {
        this.A = arg3;
        this.B = null;
        if(arg3 != null && arg3.getId() == -1 && this.b > 0) {
            arg3.setId(this.b);
        }

        this.a.b(this);
        return this;
    }

    public b a(android.support.v4.view.b arg2) {
        if(this.B != null) {
            this.B.f();
        }

        this.A = null;
        this.B = arg2;
        this.a.b(true);
        if(this.B != null) {
            this.B.a(new android.support.v4.view.b$b() {
                public void a(boolean arg2) {
                    this.a.a.a(this.a);
                }
            });
        }

        return this;
    }

    public b a(CharSequence arg2) {
        this.r = arg2;
        this.a.b(false);
        return this;
    }

    void b(boolean arg4) {
        int v0 = this.y;
        int v1 = this.y & -3;
        int v4 = arg4 ? 2 : 0;
        this.y = v4 | v1;
        if(v0 != this.y) {
            this.a.b(false);
        }
    }

    public boolean b() {
        if(this.q != null && (this.q.onMenuItemClick(((MenuItem)this)))) {
            return 1;
        }

        if(this.a.a(this.a, ((MenuItem)this))) {
            return 1;
        }

        if(this.p != null) {
            this.p.run();
            return 1;
        }

        if(this.h != null) {
            try {
                this.a.f().startActivity(this.h);
                return 1;
            }
            catch(ActivityNotFoundException v0) {
                Log.e("MenuItemImpl", "Can\'t find activity to handle intent; ignoring", ((Throwable)v0));
            }
        }

        if(this.B != null && (this.B.d())) {
            return 1;
        }

        return 0;
    }

    public b b(int arg1) {
        this.setShowAsAction(arg1);
        return this;
    }

    public b b(CharSequence arg2) {
        this.s = arg2;
        this.a.b(false);
        return this;
    }

    public int c() {
        return this.e;
    }

    boolean c(boolean arg4) {
        int v0 = this.y;
        int v1 = this.y & -9;
        boolean v2 = false;
        int v4 = arg4 ? 0 : 8;
        this.y = v4 | v1;
        if(v0 != this.y) {
            v2 = true;
        }

        return v2;
    }

    public boolean collapseActionView() {
        if((this.z & 8) == 0) {
            return 0;
        }

        if(this.A == null) {
            return 1;
        }

        if(this.C != null) {
            if(this.C.onMenuItemActionCollapse(((MenuItem)this))) {
            }
            else {
                return 0;
            }
        }

        return this.a.d(this);
    }

    char d() {
        char v0 = this.a.c() ? this.k : this.i;
        return v0;
    }

    public void d(boolean arg1) {
        int v1 = arg1 ? this.y | 32 : this.y & -33;
        this.y = v1;
    }

    public void e(boolean arg2) {
        this.D = arg2;
        this.a.b(false);
    }

    String e() {
        int v0_1;
        char v0 = this.d();
        if(v0 == 0) {
            return "";
        }

        Resources v1 = this.a.f().getResources();
        StringBuilder v2 = new StringBuilder();
        if(ViewConfiguration.get(this.a.f()).hasPermanentMenuKey()) {
            v2.append(v1.getString(android.support.v7.a.a$h.abc_prepend_shortcut_label));
        }

        int v3 = this.a.c() ? this.l : this.j;
        j.a(v2, v3, 65536, v1.getString(android.support.v7.a.a$h.abc_menu_meta_shortcut_label));
        j.a(v2, v3, 4096, v1.getString(android.support.v7.a.a$h.abc_menu_ctrl_shortcut_label));
        j.a(v2, v3, 2, v1.getString(android.support.v7.a.a$h.abc_menu_alt_shortcut_label));
        j.a(v2, v3, 1, v1.getString(android.support.v7.a.a$h.abc_menu_shift_shortcut_label));
        j.a(v2, v3, 4, v1.getString(android.support.v7.a.a$h.abc_menu_sym_shortcut_label));
        j.a(v2, v3, 8, v1.getString(android.support.v7.a.a$h.abc_menu_function_shortcut_label));
        if(v0 == 8) {
            v0_1 = android.support.v7.a.a$h.abc_menu_delete_shortcut_label;
        label_59:
            v2.append(v1.getString(v0_1));
        }
        else if(v0 == 10) {
            v0_1 = android.support.v7.a.a$h.abc_menu_enter_shortcut_label;
            goto label_59;
        }
        else if(v0 != 32) {
            v2.append(v0);
        }
        else {
            v0_1 = android.support.v7.a.a$h.abc_menu_space_shortcut_label;
            goto label_59;
        }

        return v2.toString();
    }

    public boolean expandActionView() {
        if(!this.n()) {
            return 0;
        }

        if(this.C != null) {
            if(this.C.onMenuItemActionExpand(((MenuItem)this))) {
            }
            else {
                return 0;
            }
        }

        return this.a.c(this);
    }

    boolean f() {
        boolean v0 = !this.a.d() || this.d() == 0 ? false : true;
        return v0;
    }

    public boolean g() {
        boolean v0 = (this.y & 4) != 0 ? true : false;
        return v0;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView() {
        if(this.A != null) {
            return this.A;
        }

        if(this.B != null) {
            this.A = this.B.a(((MenuItem)this));
            return this.A;
        }

        return null;
    }

    public int getAlphabeticModifiers() {
        return this.l;
    }

    public char getAlphabeticShortcut() {
        return this.k;
    }

    public CharSequence getContentDescription() {
        return this.r;
    }

    public int getGroupId() {
        return this.c;
    }

    public Drawable getIcon() {
        Drawable v0;
        if(this.m != null) {
            v0 = this.m;
        }
        else if(this.n != 0) {
            v0 = android.support.v7.c.a.a.b(this.a.f(), this.n);
            this.n = 0;
            this.m = v0;
        }
        else {
            return null;
        }

        return this.a(v0);
    }

    public ColorStateList getIconTintList() {
        return this.t;
    }

    public PorterDuff$Mode getIconTintMode() {
        return this.u;
    }

    public Intent getIntent() {
        return this.h;
    }

    @ViewDebug$CapturedViewProperty public int getItemId() {
        return this.b;
    }

    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    public int getNumericModifiers() {
        return this.j;
    }

    public char getNumericShortcut() {
        return this.i;
    }

    public int getOrder() {
        return this.d;
    }

    public SubMenu getSubMenu() {
        return this.o;
    }

    @ViewDebug$CapturedViewProperty public CharSequence getTitle() {
        return this.f;
    }

    public CharSequence getTitleCondensed() {
        String v0_1;
        CharSequence v0 = this.g != null ? this.g : this.f;
        if(Build$VERSION.SDK_INT < 18 && v0 != null && !(v0 instanceof String)) {
            v0_1 = v0.toString();
        }

        return ((CharSequence)v0_1);
    }

    public CharSequence getTooltipText() {
        return this.s;
    }

    public void h() {
        this.a.b(this);
    }

    public boolean hasSubMenu() {
        boolean v0 = this.o != null ? true : false;
        return v0;
    }

    public boolean i() {
        return this.a.r();
    }

    public boolean isActionViewExpanded() {
        return this.D;
    }

    public boolean isCheckable() {
        boolean v1 = true;
        if((this.y & 1) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean isChecked() {
        boolean v0 = (this.y & 2) == 2 ? true : false;
        return v0;
    }

    public boolean isEnabled() {
        boolean v0 = (this.y & 16) != 0 ? true : false;
        return v0;
    }

    public boolean isVisible() {
        boolean v1 = false;
        if(this.B != null && (this.B.b())) {
            if((this.y & 8) == 0 && (this.B.c())) {
                v1 = true;
            }

            return v1;
        }

        if((this.y & 8) == 0) {
            v1 = true;
        }

        return v1;
    }

    public boolean j() {
        boolean v0 = (this.y & 32) == 32 ? true : false;
        return v0;
    }

    public boolean k() {
        boolean v1 = true;
        if((this.z & 1) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean l() {
        boolean v0 = (this.z & 2) == 2 ? true : false;
        return v0;
    }

    public boolean m() {
        boolean v0 = (this.z & 4) == 4 ? true : false;
        return v0;
    }

    public boolean n() {
        boolean v1 = false;
        if((this.z & 8) != 0) {
            if(this.A == null && this.B != null) {
                this.A = this.B.a(((MenuItem)this));
            }

            if(this.A == null) {
                return v1;
            }

            v1 = true;
        }

        return v1;
    }

    public MenuItem setActionProvider(ActionProvider arg2) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public MenuItem setActionView(int arg1) {
        return this.a(arg1);
    }

    public MenuItem setActionView(View arg1) {
        return this.a(arg1);
    }

    public MenuItem setAlphabeticShortcut(char arg2) {
        if(this.k == arg2) {
            return this;
        }

        this.k = Character.toLowerCase(arg2);
        this.a.b(false);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char arg2, int arg3) {
        if(this.k == arg2 && this.l == arg3) {
            return this;
        }

        this.k = Character.toLowerCase(arg2);
        this.l = KeyEvent.normalizeMetaState(arg3);
        this.a.b(false);
        return this;
    }

    public MenuItem setCheckable(boolean arg3) {
        int v0 = this.y;
        this.y = (((int)arg3)) | this.y & -2;
        if(v0 != this.y) {
            this.a.b(false);
        }

        return this;
    }

    public MenuItem setChecked(boolean arg2) {
        if((this.y & 4) != 0) {
            this.a.a(((MenuItem)this));
        }
        else {
            this.b(arg2);
        }

        return this;
    }

    public MenuItem setContentDescription(CharSequence arg1) {
        return this.a(arg1);
    }

    public MenuItem setEnabled(boolean arg2) {
        int v2 = arg2 ? this.y | 16 : this.y & -17;
        this.y = v2;
        this.a.b(false);
        return this;
    }

    public MenuItem setIcon(int arg2) {
        this.m = null;
        this.n = arg2;
        this.x = true;
        this.a.b(false);
        return this;
    }

    public MenuItem setIcon(Drawable arg2) {
        this.n = 0;
        this.m = arg2;
        this.x = true;
        this.a.b(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList arg2) {
        this.t = arg2;
        this.v = true;
        this.x = true;
        this.a.b(false);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff$Mode arg2) {
        this.u = arg2;
        this.w = true;
        this.x = true;
        this.a.b(false);
        return this;
    }

    public MenuItem setIntent(Intent arg1) {
        this.h = arg1;
        return this;
    }

    public MenuItem setNumericShortcut(char arg2) {
        if(this.i == arg2) {
            return this;
        }

        this.i = arg2;
        this.a.b(false);
        return this;
    }

    public MenuItem setNumericShortcut(char arg2, int arg3) {
        if(this.i == arg2 && this.j == arg3) {
            return this;
        }

        this.i = arg2;
        this.j = KeyEvent.normalizeMetaState(arg3);
        this.a.b(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem$OnActionExpandListener arg1) {
        this.C = arg1;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem$OnMenuItemClickListener arg1) {
        this.q = arg1;
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2) {
        this.i = arg1;
        this.k = Character.toLowerCase(arg2);
        this.a.b(false);
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2, int arg3, int arg4) {
        this.i = arg1;
        this.j = KeyEvent.normalizeMetaState(arg3);
        this.k = Character.toLowerCase(arg2);
        this.l = KeyEvent.normalizeMetaState(arg4);
        this.a.b(false);
        return this;
    }

    public void setShowAsAction(int arg2) {
        switch(arg2 & 3) {
            case 0: 
            case 1: 
            case 2: {
                goto label_6;
            }
        }

        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    label_6:
        this.z = arg2;
        this.a.b(this);
    }

    public MenuItem setShowAsActionFlags(int arg1) {
        return this.b(arg1);
    }

    public MenuItem setTitle(int arg2) {
        return this.setTitle(this.a.f().getString(arg2));
    }

    public MenuItem setTitle(CharSequence arg3) {
        this.f = arg3;
        this.a.b(false);
        if(this.o != null) {
            this.o.setHeaderTitle(arg3);
        }

        return this;
    }

    public MenuItem setTitleCondensed(CharSequence arg2) {
        this.g = arg2;
        this.a.b(false);
        return this;
    }

    public MenuItem setTooltipText(CharSequence arg1) {
        return this.b(arg1);
    }

    public MenuItem setVisible(boolean arg1) {
        if(this.c(arg1)) {
            this.a.a(this);
        }

        return this;
    }

    public String toString() {
        String v0 = this.f != null ? this.f.toString() : null;
        return v0;
    }
}

