package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build$VERSION;
import android.support.c.a.c;
import android.support.c.a.i;
import android.support.v4.f.f;
import android.support.v4.f.g;
import android.support.v4.f.n;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class k {
    class a implements d {
        a() {
            super();
        }

        public Drawable a(Context arg2, XmlPullParser arg3, AttributeSet arg4, Resources$Theme arg5) {
            try {
                return android.support.v7.d.a.a.a(arg2, arg2.getResources(), arg3, arg4, arg5);
            }
            catch(Exception v2) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", ((Throwable)v2));
                return null;
            }
        }
    }

    class b implements d {
        b() {
            super();
        }

        public Drawable a(Context arg2, XmlPullParser arg3, AttributeSet arg4, Resources$Theme arg5) {
            try {
                return c.a(arg2, arg2.getResources(), arg3, arg4, arg5);
            }
            catch(Exception v2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", ((Throwable)v2));
                return null;
            }
        }
    }

    class android.support.v7.widget.k$c extends g {
        public android.support.v7.widget.k$c(int arg1) {
            super(arg1);
        }

        PorterDuffColorFilter a(int arg1, PorterDuff$Mode arg2) {
            return this.get(Integer.valueOf(android.support.v7.widget.k$c.b(arg1, arg2)));
        }

        PorterDuffColorFilter a(int arg1, PorterDuff$Mode arg2, PorterDuffColorFilter arg3) {
            return this.put(Integer.valueOf(android.support.v7.widget.k$c.b(arg1, arg2)), arg3);
        }

        private static int b(int arg1, PorterDuff$Mode arg2) {
            return (arg1 + 31) * 31 + arg2.hashCode();
        }
    }

    interface d {
        Drawable a(Context arg1, XmlPullParser arg2, AttributeSet arg3, Resources$Theme arg4);
    }

    class e implements d {
        e() {
            super();
        }

        public Drawable a(Context arg1, XmlPullParser arg2, AttributeSet arg3, Resources$Theme arg4) {
            try {
                return i.a(arg1.getResources(), arg2, arg3, arg4);
            }
            catch(Exception v1) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", ((Throwable)v1));
                return null;
            }
        }
    }

    private static final PorterDuff$Mode a;
    private static k b;
    private static final android.support.v7.widget.k$c c;
    private static final int[] d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private WeakHashMap j;
    private android.support.v4.f.a k;
    private n l;
    private final WeakHashMap m;
    private TypedValue n;
    private boolean o;

    static {
        k.a = PorterDuff$Mode.SRC_IN;
        k.c = new android.support.v7.widget.k$c(6);
        k.d = new int[]{android.support.v7.a.a$e.abc_textfield_search_default_mtrl_alpha, android.support.v7.a.a$e.abc_textfield_default_mtrl_alpha, android.support.v7.a.a$e.abc_ab_share_pack_mtrl_alpha};
        k.e = new int[]{android.support.v7.a.a$e.abc_ic_commit_search_api_mtrl_alpha, android.support.v7.a.a$e.abc_seekbar_tick_mark_material, android.support.v7.a.a$e.abc_ic_menu_share_mtrl_alpha, android.support.v7.a.a$e.abc_ic_menu_copy_mtrl_am_alpha, android.support.v7.a.a$e.abc_ic_menu_cut_mtrl_alpha, android.support.v7.a.a$e.abc_ic_menu_selectall_mtrl_alpha, android.support.v7.a.a$e.abc_ic_menu_paste_mtrl_am_alpha};
        k.f = new int[]{android.support.v7.a.a$e.abc_textfield_activated_mtrl_alpha, android.support.v7.a.a$e.abc_textfield_search_activated_mtrl_alpha, android.support.v7.a.a$e.abc_cab_background_top_mtrl_alpha, android.support.v7.a.a$e.abc_text_cursor_material, android.support.v7.a.a$e.abc_text_select_handle_left_mtrl_dark, android.support.v7.a.a$e.abc_text_select_handle_middle_mtrl_dark, android.support.v7.a.a$e.abc_text_select_handle_right_mtrl_dark, android.support.v7.a.a$e.abc_text_select_handle_left_mtrl_light, android.support.v7.a.a$e.abc_text_select_handle_middle_mtrl_light, android.support.v7.a.a$e.abc_text_select_handle_right_mtrl_light};
        k.g = new int[]{android.support.v7.a.a$e.abc_popup_background_mtrl_mult, android.support.v7.a.a$e.abc_cab_background_internal_bg, android.support.v7.a.a$e.abc_menu_hardkey_panel_mtrl_mult};
        k.h = new int[]{android.support.v7.a.a$e.abc_tab_indicator_material, android.support.v7.a.a$e.abc_textfield_search_material};
        k.i = new int[]{android.support.v7.a.a$e.abc_btn_check_material, android.support.v7.a.a$e.abc_btn_radio_material};
    }

    public k() {
        super();
        this.m = new WeakHashMap(0);
    }

    public static PorterDuffColorFilter a(int arg3, PorterDuff$Mode arg4) {
        PorterDuffColorFilter v1;
        Class v0 = k.class;
        __monitor_enter(v0);
        try {
            v1 = k.c.a(arg3, arg4);
            if(v1 == null) {
                v1 = new PorterDuffColorFilter(arg3, arg4);
                k.c.a(arg3, arg4, v1);
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
        return v1;
    }

    public Drawable a(Context arg2, int arg3) {
        Drawable v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.a(arg2, arg3, false);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }

    public static k a() {
        k v1_1;
        Class v0 = k.class;
        __monitor_enter(v0);
        try {
            if(k.b == null) {
                k.b = new k();
                k.a(k.b);
            }

            v1_1 = k.b;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    Drawable a(Context arg2, int arg3, boolean arg4) {
        Drawable v0;
        __monitor_enter(this);
        try {
            this.f(arg2);
            v0 = this.d(arg2, arg3);
            if(v0 == null) {
                v0 = this.c(arg2, arg3);
            }

            if(v0 == null) {
                v0 = android.support.v4.content.a.a(arg2, arg3);
            }

            if(v0 != null) {
                v0 = this.a(arg2, arg3, arg4, v0);
            }

            if(v0 != null) {
                ai.a(v0);
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v0;
    }

    Drawable a(Context arg2, bp arg3, int arg4) {
        Drawable v2_1;
        __monitor_enter(this);
        try {
            Drawable v0 = this.d(arg2, arg4);
            if(v0 == null) {
                v0 = arg3.a(arg4);
            }

            if(v0 == null) {
                goto label_9;
            }

            v2_1 = this.a(arg2, arg4, false, v0);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    label_9:
        __monitor_exit(this);
        return null;
    }

    static void a(Drawable arg2, bi arg3, int[] arg4) {
        if((ai.b(arg2)) && arg2.mutate() != arg2) {
            Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
            return;
        }

        if((arg3.d) || (arg3.c)) {
            ColorStateList v0 = arg3.d ? arg3.a : null;
            PorterDuff$Mode v3 = arg3.c ? arg3.b : k.a;
            arg2.setColorFilter(k.a(v0, v3, arg4));
        }
        else {
            arg2.clearColorFilter();
        }

        if(Build$VERSION.SDK_INT <= 23) {
            arg2.invalidateSelf();
        }
    }

    private static long a(TypedValue arg4) {
        return (((long)arg4.assetCookie)) << 32 | (((long)arg4.data));
    }

    static PorterDuff$Mode a(int arg1) {
        PorterDuff$Mode v1 = arg1 == android.support.v7.a.a$e.abc_switch_thumb_material ? PorterDuff$Mode.MULTIPLY : null;
        return v1;
    }

    private static PorterDuffColorFilter a(ColorStateList arg1, PorterDuff$Mode arg2, int[] arg3) {
        if(arg1 != null) {
            if(arg2 == null) {
            }
            else {
                return k.a(arg1.getColorForState(arg3, 0), arg2);
            }
        }

        return null;
    }

    private Drawable a(Context arg5, int arg6, boolean arg7, Drawable arg8) {
        int v0_1;
        Drawable v7;
        Drawable v6;
        ColorStateList v0 = this.b(arg5, arg6);
        if(v0 != null) {
            if(ai.b(arg8)) {
                arg8 = arg8.mutate();
            }

            arg8 = android.support.v4.graphics.drawable.a.g(arg8);
            android.support.v4.graphics.drawable.a.a(arg8, v0);
            PorterDuff$Mode v5 = k.a(arg6);
            if(v5 == null) {
                return arg8;
            }

            android.support.v4.graphics.drawable.a.a(arg8, v5);
        }
        else {
            int v1 = 16908301;
            int v2 = 16908303;
            int v3 = 16908288;
            if(arg6 == android.support.v7.a.a$e.abc_seekbar_track_material) {
                v6 = arg8;
                k.a(((LayerDrawable)v6).findDrawableByLayerId(v3), bf.a(arg5, android.support.v7.a.a$a.colorControlNormal), k.a);
                v7 = ((LayerDrawable)v6).findDrawableByLayerId(v2);
                v0_1 = android.support.v7.a.a$a.colorControlNormal;
            }
            else {
                if(arg6 != android.support.v7.a.a$e.abc_ratingbar_material && arg6 != android.support.v7.a.a$e.abc_ratingbar_indicator_material) {
                    if(arg6 == android.support.v7.a.a$e.abc_ratingbar_small_material) {
                    }
                    else {
                        if(k.a(arg5, arg6, arg8)) {
                        }
                        else if(arg7) {
                            arg8 = null;
                        }
                        else {
                        }

                        return arg8;
                    }
                }

                v6 = arg8;
                k.a(((LayerDrawable)v6).findDrawableByLayerId(v3), bf.c(arg5, android.support.v7.a.a$a.colorControlNormal), k.a);
                v7 = ((LayerDrawable)v6).findDrawableByLayerId(v2);
                v0_1 = android.support.v7.a.a$a.colorControlActivated;
            }

            k.a(v7, bf.a(arg5, v0_1), k.a);
            k.a(((LayerDrawable)v6).findDrawableByLayerId(v1), bf.a(arg5, android.support.v7.a.a$a.colorControlActivated), k.a);
        }

        return arg8;
    }

    private static void a(Drawable arg1, int arg2, PorterDuff$Mode arg3) {
        if(ai.b(arg1)) {
            arg1 = arg1.mutate();
        }

        if(arg3 == null) {
            arg3 = k.a;
        }

        arg1.setColorFilter(k.a(arg2, arg3));
    }

    static boolean a(Context arg6, int arg7, Drawable arg8) {
        int v1;
        PorterDuff$Mode v0 = k.a;
        int v2 = 16842801;
        int v3 = -1;
        if(k.a(k.d, arg7)) {
            v2 = android.support.v7.a.a$a.colorControlNormal;
            goto label_9;
        }
        else if(k.a(k.f, arg7)) {
            v2 = android.support.v7.a.a$a.colorControlActivated;
            goto label_9;
        }
        else if(k.a(k.g, arg7)) {
            v0 = PorterDuff$Mode.MULTIPLY;
            goto label_9;
        }
        else if(arg7 == android.support.v7.a.a$e.abc_list_divider_mtrl_alpha) {
            v2 = 16842800;
            v1 = Math.round(40.799999f);
            arg7 = 1;
        }
        else if(arg7 == android.support.v7.a.a$e.abc_dialog_material_background) {
        label_9:
            arg7 = 1;
            v1 = -1;
        }
        else {
            arg7 = 0;
            v1 = -1;
            v2 = 0;
        }

        if(arg7 != 0) {
            if(ai.b(arg8)) {
                arg8 = arg8.mutate();
            }

            arg8.setColorFilter(k.a(bf.a(arg6, v2), v0));
            if(v1 != v3) {
                arg8.setAlpha(v1);
            }

            return 1;
        }

        return 0;
    }

    private Drawable a(Context arg4, long arg5) {
        Drawable v4_1;
        Drawable v1;
        Object v0;
        __monitor_enter(this);
        try {
            v0 = this.m.get(arg4);
            v1 = null;
            if(v0 != null) {
                goto label_7;
            }
        }
        catch(Throwable v4) {
            goto label_19;
        }

        __monitor_exit(this);
        return v1;
        try {
        label_7:
            Object v2 = ((f)v0).a(arg5);
            if(v2 != null) {
                v2 = ((WeakReference)v2).get();
                if(v2 != null) {
                    v4_1 = ((Drawable$ConstantState)v2).newDrawable(arg4.getResources());
                    goto label_13;
                }
                else {
                    goto label_15;
                }
            }

            goto label_16;
        }
        catch(Throwable v4) {
            goto label_19;
        }

    label_13:
        __monitor_exit(this);
        return v4_1;
        try {
        label_15:
            ((f)v0).b(arg5);
        }
        catch(Throwable v4) {
        label_19:
            __monitor_exit(this);
            throw v4;
        }

    label_16:
        __monitor_exit(this);
        return v1;
    }

    private static void a(k arg2) {
        if(Build$VERSION.SDK_INT < 24) {
            arg2.a("vector", new e());
            arg2.a("animated-vector", new b());
            arg2.a("animated-selector", new a());
        }
    }

    private void a(Context arg3, int arg4, ColorStateList arg5) {
        if(this.j == null) {
            this.j = new WeakHashMap();
        }

        Object v0 = this.j.get(arg3);
        if(v0 == null) {
            n v0_1 = new n();
            this.j.put(arg3, v0_1);
        }

        ((n)v0).c(arg4, arg5);
    }

    private void a(String arg2, d arg3) {
        if(this.k == null) {
            this.k = new android.support.v4.f.a();
        }

        this.k.put(arg2, arg3);
    }

    private static boolean a(int[] arg4, int arg5) {
        int v0 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg4[v2] == arg5) {
                return 1;
            }
        }

        return 0;
    }

    private boolean a(Context arg3, long arg4, Drawable arg6) {
        boolean v3_1;
        f v0_1;
        __monitor_enter(this);
        try {
            Drawable$ConstantState v6 = arg6.getConstantState();
            if(v6 != null) {
                Object v0 = this.m.get(arg3);
                if(v0 == null) {
                    v0_1 = new f();
                    this.m.put(arg3, v0_1);
                }

                v0_1.b(arg4, new WeakReference(v6));
                v3_1 = true;
            }
            else {
                goto label_16;
            }

            goto label_14;
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

    label_16:
        v3_1 = false;
    label_14:
        __monitor_exit(this);
        return v3_1;
    }

    private static boolean a(Drawable arg1) {
        boolean v1 = ((arg1 instanceof i)) || ("android.graphics.drawable.VectorDrawable".equals(arg1.getClass().getName())) ? true : false;
        return v1;
    }

    public void a(Context arg2) {
        __monitor_enter(this);
        try {
            Object v2_1 = this.m.get(arg2);
            if(v2_1 != null) {
                ((f)v2_1).c();
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    ColorStateList b(Context arg3, int arg4) {
        int v0_1;
        ColorStateList v0;
        __monitor_enter(this);
        try {
            v0 = this.e(arg3, arg4);
            if(v0 == null) {
                if(arg4 == android.support.v7.a.a$e.abc_edit_text_material) {
                    v0_1 = android.support.v7.a.a$c.abc_tint_edittext;
                    goto label_6;
                }
                else if(arg4 == android.support.v7.a.a$e.abc_switch_track_mtrl_alpha) {
                    v0_1 = android.support.v7.a.a$c.abc_tint_switch_track;
                    goto label_6;
                }
                else if(arg4 == android.support.v7.a.a$e.abc_switch_thumb_material) {
                    v0 = this.e(arg3);
                }
                else if(arg4 == android.support.v7.a.a$e.abc_btn_default_mtrl_shape) {
                    v0 = this.b(arg3);
                }
                else if(arg4 == android.support.v7.a.a$e.abc_btn_borderless_material) {
                    v0 = this.c(arg3);
                }
                else if(arg4 == android.support.v7.a.a$e.abc_btn_colored_material) {
                    v0 = this.d(arg3);
                }
                else {
                    if(arg4 != android.support.v7.a.a$e.abc_spinner_mtrl_am_alpha) {
                        if(arg4 == android.support.v7.a.a$e.abc_spinner_textfield_background_material) {
                        }
                        else if(k.a(k.e, arg4)) {
                            v0 = bf.b(arg3, android.support.v7.a.a$a.colorControlNormal);
                            goto label_55;
                        }
                        else if(k.a(k.h, arg4)) {
                            v0_1 = android.support.v7.a.a$c.abc_tint_default;
                            goto label_6;
                        }
                        else if(k.a(k.i, arg4)) {
                            v0_1 = android.support.v7.a.a$c.abc_tint_btn_checkable;
                            goto label_6;
                        }
                        else if(arg4 == android.support.v7.a.a$e.abc_seekbar_thumb_material) {
                            v0_1 = android.support.v7.a.a$c.abc_tint_seek_thumb;
                            goto label_6;
                        }
                        else {
                            goto label_55;
                        }
                    }

                    v0_1 = android.support.v7.a.a$c.abc_tint_spinner;
                label_6:
                    v0 = android.support.v7.c.a.a.a(arg3, v0_1);
                }

            label_55:
                if(v0 == null) {
                    goto label_57;
                }

                this.a(arg3, arg4, v0);
            }
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

    label_57:
        __monitor_exit(this);
        return v0;
    }

    private ColorStateList b(Context arg2) {
        return this.f(arg2, bf.a(arg2, android.support.v7.a.a$a.colorButtonNormal));
    }

    private ColorStateList c(Context arg2) {
        return this.f(arg2, 0);
    }

    private Drawable c(Context arg8, int arg9) {
        LayerDrawable v1_1;
        if(this.n == null) {
            this.n = new TypedValue();
        }

        TypedValue v0 = this.n;
        arg8.getResources().getValue(arg9, v0, true);
        long v3 = k.a(v0);
        Drawable v1 = this.a(arg8, v3);
        if(v1 != null) {
            return v1;
        }

        if(arg9 == android.support.v7.a.a$e.abc_cab_background_top_material) {
            v1_1 = new LayerDrawable(new Drawable[]{this.a(arg8, android.support.v7.a.a$e.abc_cab_background_internal_bg), this.a(arg8, android.support.v7.a.a$e.abc_cab_background_top_mtrl_alpha)});
        }

        if((((Drawable)v1_1)) != null) {
            ((Drawable)v1_1).setChangingConfigurations(v0.changingConfigurations);
            this.a(arg8, v3, ((Drawable)v1_1));
        }

        return ((Drawable)v1_1);
    }

    private ColorStateList d(Context arg2) {
        return this.f(arg2, bf.a(arg2, android.support.v7.a.a$a.colorAccent));
    }

    private Drawable d(Context arg10, int arg11) {
        Drawable v1 = null;
        if(this.k != null && !this.k.isEmpty()) {
            if(this.l != null) {
                Object v0 = this.l.a(arg11);
                if(!"appcompat_skip_skip".equals(v0)) {
                    if(v0 == null) {
                    }
                    else if(this.k.get(v0) == null) {
                        return v1;
                    }

                    goto label_21;
                }

                return v1;
            }
            else {
                this.l = new n();
            }

        label_21:
            if(this.n == null) {
                this.n = new TypedValue();
            }

            TypedValue v0_1 = this.n;
            Resources v1_1 = arg10.getResources();
            v1_1.getValue(arg11, v0_1, true);
            long v3 = k.a(v0_1);
            Drawable v5 = this.a(arg10, v3);
            if(v5 != null) {
                return v5;
            }

            if(v0_1.string != null && (v0_1.string.toString().endsWith(".xml"))) {
                try {
                    XmlResourceParser v1_2 = v1_1.getXml(arg11);
                    AttributeSet v6 = Xml.asAttributeSet(((XmlPullParser)v1_2));
                    while(true) {
                        int v7 = ((XmlPullParser)v1_2).next();
                        int v8 = 2;
                        if(v7 != v8 && v7 != 1) {
                            continue;
                        }

                        break;
                    }

                    if(v7 == v8) {
                        String v2 = ((XmlPullParser)v1_2).getName();
                        this.l.c(arg11, v2);
                        Object v2_1 = this.k.get(v2);
                        if(v2_1 != null) {
                            v5 = ((d)v2_1).a(arg10, ((XmlPullParser)v1_2), v6, arg10.getTheme());
                        }

                        if(v5 == null) {
                            goto label_71;
                        }

                        v5.setChangingConfigurations(v0_1.changingConfigurations);
                        this.a(arg10, v3, v5);
                        goto label_71;
                    }

                    throw new XmlPullParserException("No start tag found");
                }
                catch(Exception v10) {
                    Log.e("AppCompatDrawableManag", "Exception while inflating drawable", ((Throwable)v10));
                }
            }

        label_71:
            if(v5 == null) {
                this.l.c(arg11, "appcompat_skip_skip");
            }

            return v5;
        }

        return v1;
    }

    private ColorStateList e(Context arg8) {
        int[][] v1 = new int[3][];
        int[] v0 = new int[3];
        ColorStateList v2 = bf.b(arg8, android.support.v7.a.a$a.colorSwitchThumbNormal);
        int v3 = 2;
        if(v2 == null || !v2.isStateful()) {
            v1[0] = bf.a;
            v0[0] = bf.c(arg8, android.support.v7.a.a$a.colorSwitchThumbNormal);
            v1[1] = bf.e;
            v0[1] = bf.a(arg8, android.support.v7.a.a$a.colorControlActivated);
            v1[v3] = bf.h;
            v0[v3] = bf.a(arg8, android.support.v7.a.a$a.colorSwitchThumbNormal);
        }
        else {
            v1[0] = bf.a;
            v0[0] = v2.getColorForState(v1[0], 0);
            v1[1] = bf.e;
            v0[1] = bf.a(arg8, android.support.v7.a.a$a.colorControlActivated);
            v1[v3] = bf.h;
            v0[v3] = v2.getDefaultColor();
        }

        return new ColorStateList(v1, v0);
    }

    private ColorStateList e(Context arg3, int arg4) {
        ColorStateList v1 = null;
        if(this.j != null) {
            Object v3 = this.j.get(arg3);
            if(v3 != null) {
                Object v1_1 = ((n)v3).a(arg4);
            }
        }

        return v1;
    }

    private ColorStateList f(Context arg6, int arg7) {
        int[][] v1 = new int[4][];
        int[] v0 = new int[4];
        int v2 = bf.a(arg6, android.support.v7.a.a$a.colorControlHighlight);
        int v6 = bf.c(arg6, android.support.v7.a.a$a.colorButtonNormal);
        v1[0] = bf.a;
        v0[0] = v6;
        v1[1] = bf.d;
        v0[1] = android.support.v4.graphics.a.a(v2, arg7);
        v1[2] = bf.b;
        v0[2] = android.support.v4.graphics.a.a(v2, arg7);
        v1[3] = bf.h;
        v0[3] = arg7;
        return new ColorStateList(v1, v0);
    }

    private void f(Context arg2) {
        if(this.o) {
            return;
        }

        this.o = true;
        Drawable v2 = this.a(arg2, android.support.v7.a.a$e.abc_vector_test);
        if(v2 != null && (k.a(v2))) {
            return;
        }

        this.o = false;
        throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
    }
}

