package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface$OnMultiChoiceClickListener;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.t;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.a.a$f;
import android.support.v7.a.a$j;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class AlertController {
    class android.support.v7.app.AlertController$1 implements View$OnClickListener {
        android.support.v7.app.AlertController$1(AlertController arg1) {
            this.a = arg1;
            super();
        }

        public void onClick(View arg3) {
            Message v3;
            if(arg3 != this.a.c || this.a.d == null) {
                if(arg3 == this.a.e && this.a.f != null) {
                    v3 = this.a.f;
                    goto label_8;
                }

                if(arg3 == this.a.g && this.a.h != null) {
                    v3 = this.a.h;
                label_8:
                    v3 = Message.obtain(v3);
                    goto label_29;
                }

                v3 = null;
            }
            else {
                v3 = this.a.d;
                goto label_8;
            }

        label_29:
            if(v3 != null) {
                v3.sendToTarget();
            }

            this.a.p.obtainMessage(1, this.a.a).sendToTarget();
        }
    }

    public class RecycleListView extends ListView {
        private final int a;
        private final int b;

        public RecycleListView(Context arg2) {
            this(arg2, null);
        }

        public RecycleListView(Context arg2, AttributeSet arg3) {
            super(arg2, arg3);
            TypedArray v2 = arg2.obtainStyledAttributes(arg3, j.RecycleListView);
            this.b = v2.getDimensionPixelOffset(j.RecycleListView_paddingBottomNoButtons, -1);
            this.a = v2.getDimensionPixelOffset(j.RecycleListView_paddingTopNoTitle, -1);
        }

        public void a(boolean arg3, boolean arg4) {
            if(!arg4 || !arg3) {
                int v0 = this.getPaddingLeft();
                int v3 = arg3 ? this.getPaddingTop() : this.a;
                int v1 = this.getPaddingRight();
                int v4 = arg4 ? this.getPaddingBottom() : this.b;
                this.setPadding(v0, v3, v1, v4);
            }
        }
    }

    public class a {
        public interface android.support.v7.app.AlertController$a$a {
            void a(ListView arg1);
        }

        public int A;
        public int B;
        public int C;
        public int D;
        public boolean E;
        public boolean[] F;
        public boolean G;
        public boolean H;
        public int I;
        public DialogInterface$OnMultiChoiceClickListener J;
        public Cursor K;
        public String L;
        public String M;
        public AdapterView$OnItemSelectedListener N;
        public android.support.v7.app.AlertController$a$a O;
        public boolean P;
        public final Context a;
        public final LayoutInflater b;
        public int c;
        public Drawable d;
        public int e;
        public CharSequence f;
        public View g;
        public CharSequence h;
        public CharSequence i;
        public Drawable j;
        public DialogInterface$OnClickListener k;
        public CharSequence l;
        public Drawable m;
        public DialogInterface$OnClickListener n;
        public CharSequence o;
        public Drawable p;
        public DialogInterface$OnClickListener q;
        public boolean r;
        public DialogInterface$OnCancelListener s;
        public DialogInterface$OnDismissListener t;
        public DialogInterface$OnKeyListener u;
        public CharSequence[] v;
        public ListAdapter w;
        public DialogInterface$OnClickListener x;
        public int y;
        public View z;

        public a(Context arg2) {
            super();
            this.c = 0;
            this.e = 0;
            this.E = false;
            this.I = -1;
            this.P = true;
            this.a = arg2;
            this.r = true;
            this.b = arg2.getSystemService("layout_inflater");
        }

        public void a(AlertController arg8) {
            if(this.g != null) {
                arg8.b(this.g);
            }
            else {
                if(this.f != null) {
                    arg8.a(this.f);
                }

                if(this.d != null) {
                    arg8.a(this.d);
                }

                if(this.c != 0) {
                    arg8.b(this.c);
                }

                if(this.e == 0) {
                    goto label_22;
                }

                arg8.b(arg8.c(this.e));
            }

        label_22:
            if(this.h != null) {
                arg8.b(this.h);
            }

            if(this.i != null || this.j != null) {
                arg8.a(-1, this.i, this.k, null, this.j);
            }

            if(this.l != null || this.m != null) {
                arg8.a(-2, this.l, this.n, null, this.m);
            }

            if(this.o != null || this.p != null) {
                arg8.a(-3, this.o, this.q, null, this.p);
            }

            if(this.v != null || this.K != null || this.w != null) {
                this.b(arg8);
            }

            if(this.z != null) {
                if(this.E) {
                    arg8.a(this.z, this.A, this.B, this.C, this.D);
                }
                else {
                    arg8.c(this.z);
                }
            }
            else if(this.y != 0) {
                arg8.a(this.y);
            }
        }

        private void b(AlertController arg11) {
            c v9_4;
            View v0 = this.b.inflate(arg11.l, null);
            if(!this.G) {
                int v1 = this.H ? arg11.n : arg11.o;
                int v4 = v1;
                int v2 = 16908308;
                if(this.K != null) {
                    SimpleCursorAdapter v9_2 = new SimpleCursorAdapter(this.a, v4, this.K, new String[]{this.L}, new int[]{v2});
                    goto label_63;
                }

                if(this.w != null) {
                    ListAdapter v9_3 = this.w;
                    goto label_63;
                }

                v9_4 = new c(this.a, v4, v2, this.v);
            }
            else if(this.K == null) {
                android.support.v7.app.AlertController$a$1 v9 = new ArrayAdapter(this.a, arg11.m, 16908308, this.v, v0) {
                    public View getView(int arg2, View arg3, ViewGroup arg4) {
                        arg3 = super.getView(arg2, arg3, arg4);
                        if(this.b.F != null && (this.b.F[arg2])) {
                            this.a.setItemChecked(arg2, true);
                        }

                        return arg3;
                    }
                };
            }
            else {
                android.support.v7.app.AlertController$a$2 v9_1 = new CursorAdapter(this.a, this.K, false, v0, arg11) {
                    private final int d;
                    private final int e;

                    public void bindView(View arg2, Context arg3, Cursor arg4) {
                        arg2.findViewById(16908308).setText(arg4.getString(this.d));
                        RecycleListView v2 = this.a;
                        int v3 = arg4.getPosition();
                        boolean v0 = true;
                        if(arg4.getInt(this.e) == 1) {
                        }
                        else {
                            v0 = false;
                        }

                        v2.setItemChecked(v3, v0);
                    }

                    public View newView(Context arg2, Cursor arg3, ViewGroup arg4) {
                        return this.c.b.inflate(this.b.m, arg4, false);
                    }
                };
            }

        label_63:
            if(this.O != null) {
                this.O.a(((ListView)v0));
            }

            arg11.j = ((ListAdapter)v9_4);
            arg11.k = this.I;
            if(this.x != null) {
                android.support.v7.app.AlertController$a$3 v1_1 = new AdapterView$OnItemClickListener(arg11) {
                    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                        this.b.x.onClick(this.a.a, arg3);
                        if(!this.b.H) {
                            this.a.a.dismiss();
                        }
                    }
                };
                goto label_74;
            }
            else if(this.J != null) {
                android.support.v7.app.AlertController$a$4 v1_2 = new AdapterView$OnItemClickListener(((RecycleListView)v0), arg11) {
                    public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                        if(this.c.F != null) {
                            this.c.F[arg3] = this.a.isItemChecked(arg3);
                        }

                        this.c.J.onClick(this.b.a, arg3, this.a.isItemChecked(arg3));
                    }
                };
            label_74:
                ((RecycleListView)v0).setOnItemClickListener(((AdapterView$OnItemClickListener)v1_2));
            }

            if(this.N != null) {
                ((RecycleListView)v0).setOnItemSelectedListener(this.N);
            }

            if(this.H) {
                ((RecycleListView)v0).setChoiceMode(1);
            }
            else if(this.G) {
                ((RecycleListView)v0).setChoiceMode(2);
            }

            arg11.b = ((ListView)v0);
        }
    }

    final class b extends Handler {
        private WeakReference a;

        public b(DialogInterface arg2) {
            super();
            this.a = new WeakReference(arg2);
        }

        public void handleMessage(Message arg3) {
            int v0 = arg3.what;
            if(v0 != 1) {
                switch(v0) {
                    case -3: 
                    case -2: 
                    case -1: {
                        arg3.obj.onClick(this.a.get(), arg3.what);
                        return;
                    label_11:
                        arg3.obj.dismiss();
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            else {
                goto label_11;
            }
        }
    }

    class c extends ArrayAdapter {
        public c(Context arg1, int arg2, int arg3, CharSequence[] arg4) {
            super(arg1, arg2, arg3, ((Object[])arg4));
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public boolean hasStableIds() {
            return 1;
        }
    }

    private int A;
    private boolean B;
    private CharSequence C;
    private Drawable D;
    private CharSequence E;
    private Drawable F;
    private CharSequence G;
    private Drawable H;
    private int I;
    private Drawable J;
    private ImageView K;
    private TextView L;
    private TextView M;
    private View N;
    private int O;
    private int P;
    private boolean Q;
    private int R;
    private final View$OnClickListener S;
    final g a;
    ListView b;
    Button c;
    Message d;
    Button e;
    Message f;
    Button g;
    Message h;
    NestedScrollView i;
    ListAdapter j;
    int k;
    int l;
    int m;
    int n;
    int o;
    Handler p;
    private final Context q;
    private final Window r;
    private final int s;
    private CharSequence t;
    private CharSequence u;
    private View v;
    private int w;
    private int x;
    private int y;
    private int z;

    public AlertController(Context arg4, g arg5, Window arg6) {
        super();
        this.B = false;
        this.I = 0;
        this.k = -1;
        this.R = 0;
        this.S = new android.support.v7.app.AlertController$1(this);
        this.q = arg4;
        this.a = arg5;
        this.r = arg6;
        this.p = new b(((DialogInterface)arg5));
        TypedArray v4 = arg4.obtainStyledAttributes(null, j.AlertDialog, android.support.v7.a.a$a.alertDialogStyle, 0);
        this.O = v4.getResourceId(j.AlertDialog_android_layout, 0);
        this.P = v4.getResourceId(j.AlertDialog_buttonPanelSideLayout, 0);
        this.l = v4.getResourceId(j.AlertDialog_listLayout, 0);
        this.m = v4.getResourceId(j.AlertDialog_multiChoiceItemLayout, 0);
        this.n = v4.getResourceId(j.AlertDialog_singleChoiceItemLayout, 0);
        this.o = v4.getResourceId(j.AlertDialog_listItemLayout, 0);
        this.Q = v4.getBoolean(j.AlertDialog_showTitle, true);
        this.s = v4.getDimensionPixelSize(j.AlertDialog_buttonIconDimen, 0);
        v4.recycle();
        arg5.a(1);
    }

    private ViewGroup a(View arg3, View arg4) {
        if(arg3 == null) {
            if((arg4 instanceof ViewStub)) {
                arg4 = ((ViewStub)arg4).inflate();
            }

            return arg4;
        }

        if(arg4 != null) {
            ViewParent v0 = arg4.getParent();
            if((v0 instanceof ViewGroup)) {
                ((ViewGroup)v0).removeView(arg4);
            }
        }

        if((arg3 instanceof ViewStub)) {
            arg3 = ((ViewStub)arg3).inflate();
        }

        return arg3;
    }

    static void a(View arg3, View arg4, View arg5) {
        int v0 = 4;
        if(arg4 != null) {
            int v2 = arg3.canScrollVertically(-1) ? 0 : 4;
            arg4.setVisibility(v2);
        }

        if(arg5 != null) {
            if(arg3.canScrollVertically(1)) {
                v0 = 0;
            }

            arg5.setVisibility(v0);
        }
    }

    private void a(ViewGroup arg6) {
        View v0;
        int v1 = 0;
        if(this.v != null) {
            v0 = this.v;
        }
        else if(this.w != 0) {
            v0 = LayoutInflater.from(this.q).inflate(this.w, arg6, false);
        }
        else {
            v0 = null;
        }

        if(v0 != null) {
            v1 = 1;
        }

        if(v1 == 0 || !AlertController.a(v0)) {
            this.r.setFlags(131072, 131072);
        }

        if(v1 != 0) {
            View v1_1 = this.r.findViewById(f.custom);
            ((FrameLayout)v1_1).addView(v0, new ViewGroup$LayoutParams(-1, -1));
            if(this.B) {
                ((FrameLayout)v1_1).setPadding(this.x, this.y, this.z, this.A);
            }

            if(this.b == null) {
                return;
            }

            arg6.getLayoutParams().g = 0f;
        }
        else {
            arg6.setVisibility(8);
        }
    }

    static boolean a(View arg4) {
        if(arg4.onCheckIsTextEditor()) {
            return 1;
        }

        if(!(arg4 instanceof ViewGroup)) {
            return 0;
        }

        int v0 = ((ViewGroup)arg4).getChildCount();
        do {
            if(v0 <= 0) {
                return 0;
            }

            --v0;
        }
        while(!AlertController.a(((ViewGroup)arg4).getChildAt(v0)));

        return 1;
    }

    private void a(ViewGroup arg5, View arg6, int arg7, int arg8) {
        View v0 = this.r.findViewById(f.scrollIndicatorUp);
        View v1 = this.r.findViewById(f.scrollIndicatorDown);
        if(Build$VERSION.SDK_INT >= 23) {
            t.a(arg6, arg7, arg8);
            if(v0 != null) {
                arg5.removeView(v0);
            }

            if(v1 == null) {
                return;
            }

            arg5.removeView(v1);
        }
        else {
            arg6 = null;
            if(v0 != null && (arg7 & 1) == 0) {
                arg5.removeView(v0);
                v0 = arg6;
            }

            if(v1 == null || (arg7 & 2) != 0) {
                arg6 = v1;
            }
            else {
                arg5.removeView(v1);
            }

            if(v0 == null && arg6 == null) {
                return;
            }

            if(this.u != null) {
                this.i.setOnScrollChangeListener(new android.support.v4.widget.NestedScrollView$b(v0, arg6) {
                    public void a(NestedScrollView arg1, int arg2, int arg3, int arg4, int arg5) {
                        AlertController.a(((View)arg1), this.a, this.b);
                    }
                });
                this.i.post(new Runnable(v0, arg6) {
                    public void run() {
                        AlertController.a(this.c.i, this.a, this.b);
                    }
                });
                return;
            }

            if(this.b != null) {
                this.b.setOnScrollListener(new AbsListView$OnScrollListener(v0, arg6) {
                    public void onScroll(AbsListView arg1, int arg2, int arg3, int arg4) {
                        AlertController.a(((View)arg1), this.a, this.b);
                    }

                    public void onScrollStateChanged(AbsListView arg1, int arg2) {
                    }
                });
                this.b.post(new Runnable(v0, arg6) {
                    public void run() {
                        AlertController.a(this.c.b, this.a, this.b);
                    }
                });
                return;
            }

            if(v0 != null) {
                arg5.removeView(v0);
            }

            if(arg6 == null) {
                return;
            }

            arg5.removeView(arg6);
        }
    }

    private void a(Button arg3) {
        ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
        ((LinearLayout$LayoutParams)v0).gravity = 1;
        ((LinearLayout$LayoutParams)v0).weight = 0.5f;
        arg3.setLayoutParams(v0);
    }

    private static boolean a(Context arg3) {
        TypedValue v0 = new TypedValue();
        boolean v2 = true;
        arg3.getTheme().resolveAttribute(android.support.v7.a.a$a.alertDialogCenterButtons, v0, true);
        if(v0.data != 0) {
        }
        else {
            v2 = false;
        }

        return v2;
    }

    public void a() {
        this.a.setContentView(this.b());
        this.c();
    }

    public void a(int arg2) {
        this.v = null;
        this.w = arg2;
        this.B = false;
    }

    public void a(int arg1, CharSequence arg2, DialogInterface$OnClickListener arg3, Message arg4, Drawable arg5) {
        if(arg4 == null && arg3 != null) {
            arg4 = this.p.obtainMessage(arg1, arg3);
        }

        switch(arg1) {
            case -3: {
                goto label_17;
            }
            case -2: {
                goto label_13;
            }
            case -1: {
                goto label_9;
            }
        }

        throw new IllegalArgumentException("Button does not exist");
    label_17:
        this.G = arg2;
        this.h = arg4;
        this.H = arg5;
        return;
    label_9:
        this.C = arg2;
        this.d = arg4;
        this.D = arg5;
        return;
    label_13:
        this.E = arg2;
        this.f = arg4;
        this.F = arg5;
    }

    public void a(Drawable arg3) {
        this.J = arg3;
        this.I = 0;
        if(this.K != null) {
            if(arg3 != null) {
                this.K.setVisibility(0);
                this.K.setImageDrawable(arg3);
            }
            else {
                this.K.setVisibility(8);
            }
        }
    }

    public void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
        this.v = arg1;
        this.w = 0;
        this.B = true;
        this.x = arg2;
        this.y = arg3;
        this.z = arg4;
        this.A = arg5;
    }

    public void a(CharSequence arg2) {
        this.t = arg2;
        if(this.L != null) {
            this.L.setText(arg2);
        }
    }

    public boolean a(int arg1, KeyEvent arg2) {
        boolean v1 = this.i == null || !this.i.a(arg2) ? false : true;
        return v1;
    }

    private int b() {
        if(this.P == 0) {
            return this.O;
        }

        if(this.R == 1) {
            return this.P;
        }

        return this.O;
    }

    private void b(ViewGroup arg6) {
        int v1 = 8;
        if(this.N != null) {
            arg6.addView(this.N, 0, new ViewGroup$LayoutParams(-1, -2));
            this.r.findViewById(f.title_template).setVisibility(v1);
        }
        else {
            this.K = this.r.findViewById(16908294);
            if((TextUtils.isEmpty(this.t) ^ 1) != 0 && (this.Q)) {
                this.L = this.r.findViewById(f.alertTitle);
                this.L.setText(this.t);
                if(this.I != 0) {
                    this.K.setImageResource(this.I);
                }
                else if(this.J != null) {
                    this.K.setImageDrawable(this.J);
                }
                else {
                    this.L.setPadding(this.K.getPaddingLeft(), this.K.getPaddingTop(), this.K.getPaddingRight(), this.K.getPaddingBottom());
                    this.K.setVisibility(v1);
                }

                return;
            }

            this.r.findViewById(f.title_template).setVisibility(v1);
            this.K.setVisibility(v1);
            arg6.setVisibility(v1);
        }
    }

    public void b(int arg2) {
        this.J = null;
        this.I = arg2;
        if(this.K != null) {
            if(arg2 != 0) {
                this.K.setVisibility(0);
                this.K.setImageResource(this.I);
            }
            else {
                this.K.setVisibility(8);
            }
        }
    }

    public void b(View arg1) {
        this.N = arg1;
    }

    public void b(CharSequence arg2) {
        this.u = arg2;
        if(this.M != null) {
            this.M.setText(arg2);
        }
    }

    public boolean b(int arg1, KeyEvent arg2) {
        boolean v1 = this.i == null || !this.i.a(arg2) ? false : true;
        return v1;
    }

    private void c() {
        ListView v0_2;
        View v0 = this.r.findViewById(f.parentPanel);
        View v1 = v0.findViewById(f.topPanel);
        View v2 = v0.findViewById(f.contentPanel);
        View v3 = v0.findViewById(f.buttonPanel);
        v0 = v0.findViewById(f.customPanel);
        this.a(((ViewGroup)v0));
        View v4 = ((ViewGroup)v0).findViewById(f.topPanel);
        View v5 = ((ViewGroup)v0).findViewById(f.contentPanel);
        View v6 = ((ViewGroup)v0).findViewById(f.buttonPanel);
        ViewGroup v1_1 = this.a(v4, v1);
        ViewGroup v2_1 = this.a(v5, v2);
        ViewGroup v3_1 = this.a(v6, v3);
        this.c(v2_1);
        this.d(v3_1);
        this.b(v1_1);
        int v4_1 = 8;
        int v6_1 = 0;
        int v0_1 = v0 == null || ((ViewGroup)v0).getVisibility() == v4_1 ? 0 : 1;
        boolean v7 = v1_1 == null || v1_1.getVisibility() == v4_1 ? false : true;
        boolean v3_2 = v3_1 == null || v3_1.getVisibility() == v4_1 ? false : true;
        if(!v3_2 && v2_1 != null) {
            v4 = v2_1.findViewById(f.textSpacerNoButtons);
            if(v4 != null) {
                v4.setVisibility(0);
            }
        }

        if(v7) {
            if(this.i != null) {
                this.i.setClipToPadding(true);
            }

            v4 = null;
            if(this.u != null || this.b != null) {
                v4 = v1_1.findViewById(f.titleDividerNoCustom);
            }

            if(v4 == null) {
                goto label_71;
            }

            v4.setVisibility(0);
        }
        else {
            if(v2_1 == null) {
                goto label_71;
            }

            v1 = v2_1.findViewById(f.textSpacerNoTitle);
            if(v1 == null) {
                goto label_71;
            }

            v1.setVisibility(0);
        }

    label_71:
        if((this.b instanceof RecycleListView)) {
            this.b.a(v7, v3_2);
        }

        if(v0_1 == 0) {
            if(this.b != null) {
                v0_2 = this.b;
            }
            else {
                NestedScrollView v0_3 = this.i;
            }

            if(v0_2 == null) {
                goto label_88;
            }

            if(v3_2) {
                v6_1 = 2;
            }

            this.a(v2_1, ((View)v0_2), (((int)v7)) | v6_1, 3);
        }

    label_88:
        v0_2 = this.b;
        if(v0_2 != null && this.j != null) {
            v0_2.setAdapter(this.j);
            int v1_2 = this.k;
            if(v1_2 > -1) {
                v0_2.setItemChecked(v1_2, true);
                v0_2.setSelection(v1_2);
            }
        }
    }

    private void c(ViewGroup arg5) {
        this.i = this.r.findViewById(f.scrollView);
        this.i.setFocusable(false);
        this.i.setNestedScrollingEnabled(false);
        this.M = arg5.findViewById(16908299);
        if(this.M == null) {
            return;
        }

        if(this.u != null) {
            this.M.setText(this.u);
        }
        else {
            int v1 = 8;
            this.M.setVisibility(v1);
            this.i.removeView(this.M);
            if(this.b != null) {
                ViewParent v5 = this.i.getParent();
                int v0 = ((ViewGroup)v5).indexOfChild(this.i);
                ((ViewGroup)v5).removeViewAt(v0);
                ((ViewGroup)v5).addView(this.b, v0, new ViewGroup$LayoutParams(-1, -1));
            }
            else {
                arg5.setVisibility(v1);
            }
        }
    }

    public int c(int arg4) {
        TypedValue v0 = new TypedValue();
        this.q.getTheme().resolveAttribute(arg4, v0, true);
        return v0.resourceId;
    }

    public void c(View arg1) {
        this.v = arg1;
        this.w = 0;
        this.B = false;
    }

    private void d(ViewGroup arg9) {
        Button v3_1;
        int v0;
        this.c = arg9.findViewById(16908313);
        this.c.setOnClickListener(this.S);
        int v1 = 1;
        int v2 = 8;
        Drawable v3 = null;
        if(!TextUtils.isEmpty(this.C) || this.D != null) {
            this.c.setText(this.C);
            if(this.D != null) {
                this.D.setBounds(0, 0, this.s, this.s);
                this.c.setCompoundDrawables(this.D, v3, v3, v3);
            }

            this.c.setVisibility(0);
            v0 = 1;
        }
        else {
            this.c.setVisibility(v2);
            v0 = 0;
        }

        this.e = arg9.findViewById(16908314);
        this.e.setOnClickListener(this.S);
        if(!TextUtils.isEmpty(this.E) || this.F != null) {
            this.e.setText(this.E);
            if(this.F != null) {
                this.F.setBounds(0, 0, this.s, this.s);
                this.e.setCompoundDrawables(this.F, v3, v3, v3);
            }

            this.e.setVisibility(0);
            v0 |= 2;
        }
        else {
            this.e.setVisibility(v2);
        }

        this.g = arg9.findViewById(16908315);
        this.g.setOnClickListener(this.S);
        if(!TextUtils.isEmpty(this.G) || this.H != null) {
            this.g.setText(this.G);
            if(this.D != null) {
                this.D.setBounds(0, 0, this.s, this.s);
                this.c.setCompoundDrawables(this.D, v3, v3, v3);
            }

            this.g.setVisibility(0);
            v0 |= 4;
        }
        else {
            this.g.setVisibility(v2);
        }

        if(AlertController.a(this.q)) {
            if(v0 == 1) {
                v3_1 = this.c;
            }
            else if(v0 == 2) {
                v3_1 = this.e;
            }
            else if(v0 == 4) {
                v3_1 = this.g;
            }
            else {
                goto label_107;
            }

            this.a(v3_1);
        }

    label_107:
        if(v0 != 0) {
        }
        else {
            v1 = 0;
        }

        if(v1 == 0) {
            arg9.setVisibility(v2);
        }
    }
}

