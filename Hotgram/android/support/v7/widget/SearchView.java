package android.support.v7.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.t;
import android.support.v7.a.a$a;
import android.support.v7.a.a$g;
import android.support.v7.a.a$h;
import android.support.v7.a.a$j;
import android.support.v7.view.c;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View$OnFocusChangeListener;
import android.view.View$OnKeyListener;
import android.view.View$OnLayoutChangeListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView$OnEditorActionListener;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends ap implements c {
    class android.support.v7.widget.SearchView$10 implements AdapterView$OnItemSelectedListener {
        android.support.v7.widget.SearchView$10(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public void onItemSelected(AdapterView arg1, View arg2, int arg3, long arg4) {
            this.a.a(arg3);
        }

        public void onNothingSelected(AdapterView arg1) {
        }
    }

    class android.support.v7.widget.SearchView$1 implements Runnable {
        android.support.v7.widget.SearchView$1(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.d();
        }
    }

    class android.support.v7.widget.SearchView$2 implements TextWatcher {
        android.support.v7.widget.SearchView$2(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public void afterTextChanged(Editable arg1) {
        }

        public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        }

        public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
            this.a.b(arg1);
        }
    }

    class android.support.v7.widget.SearchView$3 implements Runnable {
        android.support.v7.widget.SearchView$3(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if(this.a.g != null && ((this.a.g instanceof be))) {
                this.a.g.a(null);
            }
        }
    }

    class android.support.v7.widget.SearchView$6 implements View$OnClickListener {
        android.support.v7.widget.SearchView$6(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public void onClick(View arg2) {
            if(arg2 == this.a.b) {
                this.a.g();
            }
            else if(arg2 == this.a.d) {
                this.a.f();
            }
            else if(arg2 == this.a.c) {
                this.a.e();
            }
            else if(arg2 == this.a.e) {
                this.a.h();
            }
            else if(arg2 == this.a.a) {
                this.a.l();
            }
        }
    }

    class android.support.v7.widget.SearchView$7 implements View$OnKeyListener {
        android.support.v7.widget.SearchView$7(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public boolean onKey(View arg4, int arg5, KeyEvent arg6) {
            if(this.a.h == null) {
                return 0;
            }

            if((this.a.a.isPopupShowing()) && this.a.a.getListSelection() != -1) {
                return this.a.a(arg4, arg5, arg6);
            }

            if(!this.a.a.a() && (arg6.hasNoModifiers()) && arg6.getAction() == 1 && arg5 == 66) {
                arg4.cancelLongPress();
                this.a.a(0, null, this.a.a.getText().toString());
                return 1;
            }

            return 0;
        }
    }

    class android.support.v7.widget.SearchView$8 implements TextView$OnEditorActionListener {
        android.support.v7.widget.SearchView$8(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public boolean onEditorAction(TextView arg1, int arg2, KeyEvent arg3) {
            this.a.e();
            return 1;
        }
    }

    class android.support.v7.widget.SearchView$9 implements AdapterView$OnItemClickListener {
        android.support.v7.widget.SearchView$9(SearchView arg1) {
            this.a = arg1;
            super();
        }

        public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
            this.a.a(arg3, 0, null);
        }
    }

    class SavedState extends AbsSavedState {
        final class android.support.v7.widget.SearchView$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v7.widget.SearchView$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState a(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.a(arg1, arg2);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        boolean a;

        static {
            SavedState.CREATOR = new android.support.v7.widget.SearchView$SavedState$1();
        }

        SavedState(Parcelable arg1) {
            super(arg1);
        }

        public SavedState(Parcel arg1, ClassLoader arg2) {
            super(arg1, arg2);
            this.a = arg1.readValue(null).booleanValue();
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.a + "}";
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            super.writeToParcel(arg1, arg2);
            arg1.writeValue(Boolean.valueOf(this.a));
        }
    }

    public class SearchAutoComplete extends f {
        class android.support.v7.widget.SearchView$SearchAutoComplete$1 implements Runnable {
            android.support.v7.widget.SearchView$SearchAutoComplete$1(SearchAutoComplete arg1) {
                this.a = arg1;
                super();
            }

            public void run() {
                this.a.b();
            }
        }

        final Runnable a;
        private int b;
        private SearchView c;
        private boolean d;

        public SearchAutoComplete(Context arg2) {
            this(arg2, null);
        }

        public SearchAutoComplete(Context arg2, AttributeSet arg3) {
            this(arg2, arg3, a.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context arg1, AttributeSet arg2, int arg3) {
            super(arg1, arg2, arg3);
            this.a = new android.support.v7.widget.SearchView$SearchAutoComplete$1(this);
            this.b = this.getThreshold();
        }

        boolean a() {
            boolean v0 = TextUtils.getTrimmedLength(this.getText()) == 0 ? true : false;
            return v0;
        }

        void b() {
            if(this.d) {
                this.getContext().getSystemService("input_method").showSoftInput(((View)this), 0);
                this.d = false;
            }
        }

        public boolean enoughToFilter() {
            boolean v0 = this.b <= 0 || (super.enoughToFilter()) ? true : false;
            return v0;
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration v0 = this.getResources().getConfiguration();
            int v1 = v0.screenWidthDp;
            int v2 = v0.screenHeightDp;
            if(v1 >= 960 && v2 >= 720 && v0.orientation == 2) {
                return 256;
            }

            if(v1 < 600 && (v1 < 640 || v2 < 480)) {
                return 160;
            }

            return 192;
        }

        public InputConnection onCreateInputConnection(EditorInfo arg2) {
            InputConnection v2 = super.onCreateInputConnection(arg2);
            if(this.d) {
                this.removeCallbacks(this.a);
                this.post(this.a);
            }

            return v2;
        }

        protected void onFinishInflate() {
            super.onFinishInflate();
            this.setMinWidth(((int)TypedValue.applyDimension(1, ((float)this.getSearchViewTextMinWidthDp()), this.getResources().getDisplayMetrics())));
        }

        protected void onFocusChanged(boolean arg1, int arg2, Rect arg3) {
            super.onFocusChanged(arg1, arg2, arg3);
            this.c.i();
        }

        public boolean onKeyPreIme(int arg3, KeyEvent arg4) {
            if(arg3 == 4) {
                if(arg4.getAction() == 0 && arg4.getRepeatCount() == 0) {
                    KeyEvent$DispatcherState v3 = this.getKeyDispatcherState();
                    if(v3 != null) {
                        v3.startTracking(arg4, this);
                    }

                    return 1;
                }

                if(arg4.getAction() != 1) {
                    goto label_25;
                }

                KeyEvent$DispatcherState v0 = this.getKeyDispatcherState();
                if(v0 != null) {
                    v0.handleUpEvent(arg4);
                }

                if(!arg4.isTracking()) {
                    goto label_25;
                }

                if(arg4.isCanceled()) {
                    goto label_25;
                }

                this.c.clearFocus();
                this.setImeVisibility(false);
                return 1;
            }

        label_25:
            return super.onKeyPreIme(arg3, arg4);
        }

        public void onWindowFocusChanged(boolean arg2) {
            super.onWindowFocusChanged(arg2);
            if((arg2) && (this.c.hasFocus()) && this.getVisibility() == 0) {
                this.d = true;
                if(SearchView.a(this.getContext())) {
                    SearchView.i.a(((AutoCompleteTextView)this), true);
                }
            }
        }

        public void performCompletion() {
        }

        protected void replaceText(CharSequence arg1) {
        }

        void setImeVisibility(boolean arg3) {
            Object v0 = this.getContext().getSystemService("input_method");
            if(!arg3) {
                this.d = false;
                this.removeCallbacks(this.a);
                ((InputMethodManager)v0).hideSoftInputFromWindow(this.getWindowToken(), 0);
                return;
            }

            if(((InputMethodManager)v0).isActive(((View)this))) {
                this.d = false;
                this.removeCallbacks(this.a);
                ((InputMethodManager)v0).showSoftInput(((View)this), 0);
                return;
            }

            this.d = true;
        }

        void setSearchView(SearchView arg1) {
            this.c = arg1;
        }

        public void setThreshold(int arg1) {
            super.setThreshold(arg1);
            this.b = arg1;
        }
    }

    class android.support.v7.widget.SearchView$a {
        private Method a;
        private Method b;
        private Method c;

        android.support.v7.widget.SearchView$a() {
            super();
            try {
                this.a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged");
                this.a.setAccessible(true);
                goto label_10;
            }
            catch(NoSuchMethodException ) {
                try {
                label_10:
                    this.b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged");
                    this.b.setAccessible(true);
                    goto label_17;
                }
                catch(NoSuchMethodException ) {
                    try {
                    label_17:
                        this.c = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                        this.c.setAccessible(true);
                        return;
                    }
                    catch(NoSuchMethodException ) {
                        return;
                    }
                }
            }
        }

        void a(AutoCompleteTextView arg4, boolean arg5) {
            if(this.c != null) {
                try {
                    this.c.invoke(arg4, Boolean.valueOf(arg5));
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }

        void a(AutoCompleteTextView arg3) {
            if(this.a != null) {
                try {
                    this.a.invoke(arg3);
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }

        void b(AutoCompleteTextView arg3) {
            if(this.b != null) {
                try {
                    this.b.invoke(arg3);
                    return;
                }
                catch(Exception ) {
                    return;
                }
            }
        }
    }

    public interface b {
        boolean a();
    }

    public interface android.support.v7.widget.SearchView$c {
        boolean a(String arg1);

        boolean b(String arg1);
    }

    public interface d {
        boolean a(int arg1);

        boolean b(int arg1);
    }

    class e extends TouchDelegate {
        private final View a;
        private final Rect b;
        private final Rect c;
        private final Rect d;
        private final int e;
        private boolean f;

        public e(Rect arg2, Rect arg3, View arg4) {
            super(arg2, arg4);
            this.e = ViewConfiguration.get(arg4.getContext()).getScaledTouchSlop();
            this.b = new Rect();
            this.d = new Rect();
            this.c = new Rect();
            this.a(arg2, arg3);
            this.a = arg4;
        }

        public void a(Rect arg3, Rect arg4) {
            this.b.set(arg3);
            this.d.set(arg3);
            this.d.inset(-this.e, -this.e);
            this.c.set(arg4);
        }

        public boolean onTouchEvent(MotionEvent arg7) {
            float v0_1;
            boolean v2;
            int v0 = ((int)arg7.getX());
            int v1 = ((int)arg7.getY());
            int v3 = 1;
            boolean v4 = false;
            switch(arg7.getAction()) {
                case 0: {
                    if(this.b.contains(v0, v1)) {
                        this.f = true;
                        v2 = true;
                        goto label_26;
                    }

                label_25:
                    v2 = false;
                    break;
                }
                case 1: 
                case 2: {
                    v2 = this.f;
                    if(!v2) {
                        goto label_26;
                    }

                    if(this.d.contains(v0, v1)) {
                        goto label_26;
                    }

                    v3 = 0;
                    break;
                }
                case 3: {
                    v2 = this.f;
                    this.f = false;
                    break;
                }
                default: {
                    goto label_25;
                }
            }

        label_26:
            if(v2) {
                if(v3 == 0 || (this.c.contains(v0, v1))) {
                    v0_1 = ((float)(v0 - this.c.left));
                    v1 -= this.c.top;
                }
                else {
                    v0_1 = ((float)(this.a.getWidth() / 2));
                    v1 = this.a.getHeight() / 2;
                }

                arg7.setLocation(v0_1, ((float)v1));
                v4 = this.a.dispatchTouchEvent(arg7);
            }

            return v4;
        }
    }

    private android.support.v7.widget.SearchView$c A;
    private b B;
    private d C;
    private View$OnClickListener D;
    private boolean E;
    private boolean F;
    private boolean G;
    private CharSequence H;
    private boolean I;
    private boolean J;
    private int K;
    private boolean L;
    private CharSequence M;
    private CharSequence N;
    private boolean O;
    private int P;
    private Bundle Q;
    private final Runnable R;
    private Runnable S;
    private final WeakHashMap T;
    private final View$OnClickListener U;
    private final TextView$OnEditorActionListener V;
    private final AdapterView$OnItemClickListener W;
    final SearchAutoComplete a;
    private final AdapterView$OnItemSelectedListener aa;
    private TextWatcher ab;
    final ImageView b;
    final ImageView c;
    final ImageView d;
    final ImageView e;
    View$OnFocusChangeListener f;
    android.support.v4.widget.f g;
    SearchableInfo h;
    static final android.support.v7.widget.SearchView$a i;
    View$OnKeyListener j;
    private final View k;
    private final View l;
    private final View m;
    private final View n;
    private e o;
    private Rect p;
    private Rect q;
    private int[] r;
    private int[] s;
    private final ImageView t;
    private final Drawable u;
    private final int v;
    private final int w;
    private final Intent x;
    private final Intent y;
    private final CharSequence z;

    static {
        SearchView.i = new android.support.v7.widget.SearchView$a();
    }

    public SearchView(Context arg2) {
        this(arg2, null);
    }

    public SearchView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, a.searchViewStyle);
    }

    public SearchView(Context arg4, AttributeSet arg5, int arg6) {
        super(arg4, arg5, arg6);
        this.p = new Rect();
        this.q = new Rect();
        this.r = new int[2];
        this.s = new int[2];
        this.R = new android.support.v7.widget.SearchView$1(this);
        this.S = new android.support.v7.widget.SearchView$3(this);
        this.T = new WeakHashMap();
        this.U = new android.support.v7.widget.SearchView$6(this);
        this.j = new android.support.v7.widget.SearchView$7(this);
        this.V = new android.support.v7.widget.SearchView$8(this);
        this.W = new android.support.v7.widget.SearchView$9(this);
        this.aa = new android.support.v7.widget.SearchView$10(this);
        this.ab = new android.support.v7.widget.SearchView$2(this);
        bk v5 = bk.a(arg4, arg5, j.SearchView, arg6, 0);
        LayoutInflater.from(arg4).inflate(v5.g(j.SearchView_layout, g.abc_search_view), ((ViewGroup)this), true);
        this.a = this.findViewById(android.support.v7.a.a$f.search_src_text);
        this.a.setSearchView(this);
        this.k = this.findViewById(android.support.v7.a.a$f.search_edit_frame);
        this.l = this.findViewById(android.support.v7.a.a$f.search_plate);
        this.m = this.findViewById(android.support.v7.a.a$f.submit_area);
        this.b = this.findViewById(android.support.v7.a.a$f.search_button);
        this.c = this.findViewById(android.support.v7.a.a$f.search_go_btn);
        this.d = this.findViewById(android.support.v7.a.a$f.search_close_btn);
        this.e = this.findViewById(android.support.v7.a.a$f.search_voice_btn);
        this.t = this.findViewById(android.support.v7.a.a$f.search_mag_icon);
        t.a(this.l, v5.a(j.SearchView_queryBackground));
        t.a(this.m, v5.a(j.SearchView_submitBackground));
        this.b.setImageDrawable(v5.a(j.SearchView_searchIcon));
        this.c.setImageDrawable(v5.a(j.SearchView_goIcon));
        this.d.setImageDrawable(v5.a(j.SearchView_closeIcon));
        this.e.setImageDrawable(v5.a(j.SearchView_voiceIcon));
        this.t.setImageDrawable(v5.a(j.SearchView_searchIcon));
        this.u = v5.a(j.SearchView_searchHintIcon);
        bm.a(this.b, this.getResources().getString(h.abc_searchview_description_search));
        this.v = v5.g(j.SearchView_suggestionRowLayout, g.abc_search_dropdown_item_icons_2line);
        this.w = v5.g(j.SearchView_commitIcon, 0);
        this.b.setOnClickListener(this.U);
        this.d.setOnClickListener(this.U);
        this.c.setOnClickListener(this.U);
        this.e.setOnClickListener(this.U);
        this.a.setOnClickListener(this.U);
        this.a.addTextChangedListener(this.ab);
        this.a.setOnEditorActionListener(this.V);
        this.a.setOnItemClickListener(this.W);
        this.a.setOnItemSelectedListener(this.aa);
        this.a.setOnKeyListener(this.j);
        this.a.setOnFocusChangeListener(new View$OnFocusChangeListener() {
            public void onFocusChange(View arg2, boolean arg3) {
                if(this.a.f != null) {
                    this.a.f.onFocusChange(this.a, arg3);
                }
            }
        });
        this.setIconifiedByDefault(v5.a(j.SearchView_iconifiedByDefault, true));
        arg6 = -1;
        int v4 = v5.e(j.SearchView_android_maxWidth, arg6);
        if(v4 != arg6) {
            this.setMaxWidth(v4);
        }

        this.z = v5.c(j.SearchView_defaultQueryHint);
        this.H = v5.c(j.SearchView_queryHint);
        v4 = v5.a(j.SearchView_android_imeOptions, arg6);
        if(v4 != arg6) {
            this.setImeOptions(v4);
        }

        v4 = v5.a(j.SearchView_android_inputType, arg6);
        if(v4 != arg6) {
            this.setInputType(v4);
        }

        this.setFocusable(v5.a(j.SearchView_android_focusable, true));
        v5.a();
        this.x = new Intent("android.speech.action.WEB_SEARCH");
        this.x.addFlags(268435456);
        this.x.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.y = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.y.addFlags(268435456);
        this.n = this.findViewById(this.a.getDropDownAnchor());
        if(this.n != null) {
            this.n.addOnLayoutChangeListener(new View$OnLayoutChangeListener() {
                public void onLayoutChange(View arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
                    this.a.k();
                }
            });
        }

        this.a(this.E);
        this.r();
    }

    private void a(boolean arg7) {
        this.F = arg7;
        int v0 = 8;
        boolean v1 = false;
        int v2 = arg7 ? 0 : 8;
        int v3 = TextUtils.isEmpty(this.a.getText()) ^ 1;
        this.b.setVisibility(v2);
        this.b(((boolean)v3));
        View v2_1 = this.k;
        int v7 = arg7 ? 8 : 0;
        v2_1.setVisibility(v7);
        if(this.t.getDrawable() != null) {
            if(this.E) {
            }
            else {
                v0 = 0;
            }
        }

        this.t.setVisibility(v0);
        this.p();
        if(v3 == 0) {
            v1 = true;
        }

        this.c(v1);
        this.o();
    }

    private Intent a(Intent arg2, SearchableInfo arg3) {
        Intent v0 = new Intent(arg2);
        ComponentName v2 = arg3.getSearchActivity();
        String v3 = "calling_package";
        String v2_1 = v2 == null ? null : v2.flattenToShortString();
        v0.putExtra(v3, v2_1);
        return v0;
    }

    private Intent a(Cursor arg9, int arg10, String arg11) {
        int v9;
        Intent v0 = null;
        try {
            String v1 = be.a(arg9, "suggest_intent_action");
            if(v1 == null) {
                v1 = this.h.getSuggestIntentAction();
            }

            if(v1 == null) {
                v1 = "android.intent.action.SEARCH";
            }

            String v2 = v1;
            v1 = be.a(arg9, "suggest_intent_data");
            if(v1 == null) {
                v1 = this.h.getSuggestIntentData();
            }

            if(v1 != null) {
                String v3 = be.a(arg9, "suggest_intent_data_id");
                if(v3 != null) {
                    v1 = v1 + "/" + Uri.encode(v3);
                }
            }

            Uri v3_1 = v1 == null ? ((Uri)v0) : Uri.parse(v1);
            return this.a(v2, v3_1, be.a(arg9, "suggest_intent_extra_data"), be.a(arg9, "suggest_intent_query"), arg10, arg11);
        }
        catch(RuntimeException v10) {
            try {
                v9 = arg9.getPosition();
            }
            catch(RuntimeException ) {
                v9 = -1;
            }

            Log.w("SearchView", "Search suggestions cursor at row " + v9 + " returned exception.", ((Throwable)v10));
            return v0;
        }
    }

    private Intent a(String arg2, Uri arg3, String arg4, String arg5, int arg6, String arg7) {
        Intent v0 = new Intent(arg2);
        v0.addFlags(268435456);
        if(arg3 != null) {
            v0.setData(arg3);
        }

        v0.putExtra("user_query", this.N);
        if(arg5 != null) {
            v0.putExtra("query", arg5);
        }

        if(arg4 != null) {
            v0.putExtra("intent_extra_data_key", arg4);
        }

        if(this.Q != null) {
            v0.putExtra("app_data", this.Q);
        }

        if(arg6 != 0) {
            v0.putExtra("action_key", arg6);
            v0.putExtra("action_msg", arg7);
        }

        v0.setComponent(this.h.getSearchActivity());
        return v0;
    }

    private void a(Intent arg5) {
        if(arg5 == null) {
            return;
        }

        try {
            this.getContext().startActivity(arg5);
        }
        catch(RuntimeException v0) {
            Log.e("SearchView", "Failed launch activity: " + arg5, ((Throwable)v0));
        }
    }

    private void a(View arg5, Rect arg6) {
        arg5.getLocationInWindow(this.r);
        this.getLocationInWindow(this.s);
        int v0 = this.r[1] - this.s[1];
        int v1 = this.r[0] - this.s[0];
        arg6.set(v1, v0, arg5.getWidth() + v1, arg5.getHeight() + v0);
    }

    static boolean a(Context arg1) {
        boolean v1 = arg1.getResources().getConfiguration().orientation == 2 ? true : false;
        return v1;
    }

    public void a() {
        if(this.O) {
            return;
        }

        this.O = true;
        this.P = this.a.getImeOptions();
        this.a.setImeOptions(this.P | 33554432);
        this.a.setText("");
        this.setIconified(false);
    }

    void a(int arg8, String arg9, String arg10) {
        this.getContext().startActivity(this.a("android.intent.action.SEARCH", null, null, arg10, arg8, arg9));
    }

    void a(CharSequence arg1) {
        this.setQuery(arg1);
    }

    public void a(CharSequence arg3, boolean arg4) {
        this.a.setText(arg3);
        if(arg3 != null) {
            this.a.setSelection(this.a.length());
            this.N = arg3;
        }

        if((arg4) && !TextUtils.isEmpty(arg3)) {
            this.e();
        }
    }

    boolean a(int arg2) {
        if(this.C != null) {
            if(!this.C.a(arg2)) {
            }
            else {
                return 0;
            }
        }

        this.e(arg2);
        return 1;
    }

    boolean a(int arg1, int arg2, String arg3) {
        if(this.C != null) {
            if(!this.C.b(arg1)) {
            }
            else {
                return 0;
            }
        }

        this.b(arg1, 0, null);
        this.a.setImeVisibility(false);
        this.t();
        return 1;
    }

    boolean a(View arg2, int arg3, KeyEvent arg4) {
        if(this.h == null) {
            return 0;
        }

        if(this.g == null) {
            return 0;
        }

        if(arg4.getAction() == 0 && (arg4.hasNoModifiers())) {
            if(arg3 != 66 && arg3 != 84) {
                if(arg3 == 61) {
                }
                else {
                    int v2 = 21;
                    if(arg3 != v2) {
                        if(arg3 == 22) {
                        }
                        else if(arg3 != 19) {
                            return 0;
                        }
                        else if(this.a.getListSelection() == 0) {
                            return 0;
                        }
                        else {
                            return 0;
                        }
                    }

                    v2 = arg3 == v2 ? 0 : this.a.length();
                    this.a.setSelection(v2);
                    this.a.setListSelection(0);
                    this.a.clearListSelection();
                    SearchView.i.a(this.a, true);
                    return 1;
                }
            }

            return this.a(this.a.getListSelection(), 0, null);
        }

        return 0;
    }

    private void b(boolean arg2) {
        int v2;
        if(!this.G || !this.n() || !this.hasFocus()) {
        label_11:
            v2 = 8;
        }
        else {
            if(!arg2 && (this.L)) {
                goto label_11;
            }

            v2 = 0;
        }

        this.c.setVisibility(v2);
    }

    private Intent b(Intent arg10, SearchableInfo arg11) {
        ComponentName v0 = arg11.getSearchActivity();
        Intent v1 = new Intent("android.intent.action.SEARCH");
        v1.setComponent(v0);
        PendingIntent v1_1 = PendingIntent.getActivity(this.getContext(), 0, v1, 1073741824);
        Bundle v2 = new Bundle();
        if(this.Q != null) {
            v2.putParcelable("app_data", this.Q);
        }

        Intent v3 = new Intent(arg10);
        String v10 = "free_form";
        int v4 = 1;
        Resources v5 = this.getResources();
        if(arg11.getVoiceLanguageModeId() != 0) {
            v10 = v5.getString(arg11.getVoiceLanguageModeId());
        }

        String v7 = null;
        String v6 = arg11.getVoicePromptTextId() != 0 ? v5.getString(arg11.getVoicePromptTextId()) : v7;
        String v5_1 = arg11.getVoiceLanguageId() != 0 ? v5.getString(arg11.getVoiceLanguageId()) : v7;
        if(arg11.getVoiceMaxResults() != 0) {
            v4 = arg11.getVoiceMaxResults();
        }

        v3.putExtra("android.speech.extra.LANGUAGE_MODEL", v10);
        v3.putExtra("android.speech.extra.PROMPT", v6);
        v3.putExtra("android.speech.extra.LANGUAGE", v5_1);
        v3.putExtra("android.speech.extra.MAX_RESULTS", v4);
        v10 = "calling_package";
        if(v0 == null) {
        }
        else {
            v7 = v0.flattenToShortString();
        }

        v3.putExtra(v10, v7);
        v3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", ((Parcelable)v1_1));
        v3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", v2);
        return v3;
    }

    private boolean b(int arg2, int arg3, String arg4) {
        Cursor v0 = this.g.a();
        if(v0 != null && (v0.moveToPosition(arg2))) {
            this.a(this.a(v0, arg3, arg4));
            return 1;
        }

        return 0;
    }

    public void b() {
        this.a("", false);
        this.clearFocus();
        this.a(true);
        this.a.setImeOptions(this.P);
        this.O = false;
    }

    void b(CharSequence arg3) {
        Editable v0 = this.a.getText();
        this.N = ((CharSequence)v0);
        boolean v1 = true;
        int v0_1 = TextUtils.isEmpty(((CharSequence)v0)) ^ 1;
        this.b(((boolean)v0_1));
        if(v0_1 == 0) {
        }
        else {
            v1 = false;
        }

        this.c(v1);
        this.p();
        this.o();
        if(this.A != null && !TextUtils.equals(arg3, this.M)) {
            this.A.b(arg3.toString());
        }

        this.M = arg3.toString();
    }

    private void c(boolean arg3) {
        int v3;
        int v1 = 8;
        if(!this.L || (this.c()) || !arg3) {
            v3 = 8;
        }
        else {
            v3 = 0;
            this.c.setVisibility(v1);
        }

        this.e.setVisibility(v3);
    }

    private CharSequence c(CharSequence arg6) {
        if(this.E) {
            if(this.u == null) {
            }
            else {
                double v0 = ((double)this.a.getTextSize());
                Double.isNaN(v0);
                int v0_1 = ((int)(v0 * 1.25));
                this.u.setBounds(0, 0, v0_1, v0_1);
                SpannableStringBuilder v0_2 = new SpannableStringBuilder("   ");
                v0_2.setSpan(new ImageSpan(this.u), 1, 2, 33);
                v0_2.append(arg6);
                return ((CharSequence)v0_2);
            }
        }

        return arg6;
    }

    public boolean c() {
        return this.F;
    }

    public void clearFocus() {
        this.J = true;
        super.clearFocus();
        this.a.clearFocus();
        this.a.setImeVisibility(false);
        this.J = false;
    }

    void d() {
        int[] v0 = this.a.hasFocus() ? SearchView.FOCUSED_STATE_SET : SearchView.EMPTY_STATE_SET;
        Drawable v1 = this.l.getBackground();
        if(v1 != null) {
            v1.setState(v0);
        }

        v1 = this.m.getBackground();
        if(v1 != null) {
            v1.setState(v0);
        }

        this.invalidate();
    }

    private void e(int arg3) {
        Editable v0 = this.a.getText();
        Cursor v1 = this.g.a();
        if(v1 == null) {
            return;
        }

        if(v1.moveToPosition(arg3)) {
            CharSequence v3 = this.g.c(v1);
            if(v3 != null) {
                this.setQuery(v3);
            }
            else {
                goto label_13;
            }
        }
        else {
        label_13:
            this.setQuery(((CharSequence)v0));
        }
    }

    void e() {
        Editable v0 = this.a.getText();
        if(v0 != null && TextUtils.getTrimmedLength(((CharSequence)v0)) > 0 && (this.A == null || !this.A.a(((CharSequence)v0).toString()))) {
            if(this.h != null) {
                this.a(0, null, ((CharSequence)v0).toString());
            }

            this.a.setImeVisibility(false);
            this.t();
        }
    }

    void f() {
        if(!TextUtils.isEmpty(this.a.getText())) {
            this.a.setText("");
            this.a.requestFocus();
            this.a.setImeVisibility(true);
        }
        else if(this.E) {
            if(this.B != null && (this.B.a())) {
                return;
            }

            this.clearFocus();
            this.a(true);
        }
    }

    void g() {
        this.a(false);
        this.a.requestFocus();
        this.a.setImeVisibility(true);
        if(this.D != null) {
            this.D.onClick(((View)this));
        }
    }

    public int getImeOptions() {
        return this.a.getImeOptions();
    }

    public int getInputType() {
        return this.a.getInputType();
    }

    public int getMaxWidth() {
        return this.K;
    }

    private int getPreferredHeight() {
        return this.getContext().getResources().getDimensionPixelSize(android.support.v7.a.a$d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return this.getContext().getResources().getDimensionPixelSize(android.support.v7.a.a$d.abc_search_view_preferred_width);
    }

    public CharSequence getQuery() {
        return this.a.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence v0;
        if(this.H != null) {
            v0 = this.H;
        }
        else {
            if(this.h != null && this.h.getHintId() != 0) {
                return this.getContext().getText(this.h.getHintId());
            }

            v0 = this.z;
        }

        return v0;
    }

    int getSuggestionCommitIconResId() {
        return this.w;
    }

    int getSuggestionRowLayout() {
        return this.v;
    }

    public android.support.v4.widget.f getSuggestionsAdapter() {
        return this.g;
    }

    void h() {
        Intent v0_1;
        if(this.h == null) {
            return;
        }

        SearchableInfo v0 = this.h;
        try {
            if(v0.getVoiceSearchLaunchWebSearch()) {
                v0_1 = this.a(this.x, v0);
            }
            else if(v0.getVoiceSearchLaunchRecognizer()) {
                v0_1 = this.b(this.y, v0);
            }
            else {
                return;
            }

            this.getContext().startActivity(v0_1);
        }
        catch(ActivityNotFoundException ) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    void i() {
        this.a(this.c());
        this.q();
        if(this.a.hasFocus()) {
            this.l();
        }
    }

    void k() {
        if(this.n.getWidth() > 1) {
            Resources v0 = this.getContext().getResources();
            int v1 = this.l.getPaddingLeft();
            Rect v2 = new Rect();
            boolean v3 = bs.a(((View)this));
            int v4 = this.E ? v0.getDimensionPixelSize(android.support.v7.a.a$d.abc_dropdownitem_icon_width) + v0.getDimensionPixelSize(android.support.v7.a.a$d.abc_dropdownitem_text_padding_left) : 0;
            this.a.getDropDownBackground().getPadding(v2);
            int v0_1 = v3 ? -v2.left : v1 - (v2.left + v4);
            this.a.setDropDownHorizontalOffset(v0_1);
            this.a.setDropDownWidth(this.n.getWidth() + v2.left + v2.right + v4 - v1);
        }
    }

    void l() {
        SearchView.i.a(this.a);
        SearchView.i.b(this.a);
    }

    private boolean m() {
        boolean v1 = false;
        if(this.h != null && (this.h.getVoiceSearchEnabled())) {
            Intent v0 = null;
            if(this.h.getVoiceSearchLaunchWebSearch()) {
                v0 = this.x;
            }
            else if(this.h.getVoiceSearchLaunchRecognizer()) {
                v0 = this.y;
            }

            if(v0 == null) {
                return v1;
            }

            if(this.getContext().getPackageManager().resolveActivity(v0, 65536) == null) {
                return v1;
            }

            v1 = true;
        }

        return v1;
    }

    private boolean n() {
        boolean v0;
        if(!this.G && !this.L) {
            goto label_8;
        }
        else if(!this.c()) {
            v0 = true;
        }
        else {
        label_8:
            v0 = false;
        }

        return v0;
    }

    private void o() {
        int v0;
        if(this.n()) {
            if(this.c.getVisibility() != 0 && this.e.getVisibility() != 0) {
                goto label_10;
            }

            v0 = 0;
        }
        else {
        label_10:
            v0 = 8;
        }

        this.m.setVisibility(v0);
    }

    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.R);
        this.post(this.S);
        super.onDetachedFromWindow();
    }

    protected void onLayout(boolean arg2, int arg3, int arg4, int arg5, int arg6) {
        super.onLayout(arg2, arg3, arg4, arg5, arg6);
        if(arg2) {
            this.a(this.a, this.p);
            this.q.set(this.p.left, 0, this.p.right, arg6 - arg4);
            if(this.o == null) {
                this.o = new e(this.q, this.p, this.a);
                this.setTouchDelegate(this.o);
            }
            else {
                this.o.a(this.q, this.p);
            }
        }
    }

    protected void onMeasure(int arg4, int arg5) {
        if(this.c()) {
            super.onMeasure(arg4, arg5);
            return;
        }

        int v0 = View$MeasureSpec.getMode(arg4);
        arg4 = View$MeasureSpec.getSize(arg4);
        int v1 = -2147483648;
        int v2 = 1073741824;
        if(v0 != v1) {
            if(v0 != 0) {
                if(v0 != v2) {
                }
                else if(this.K > 0) {
                    goto label_23;
                }
            }
            else if(this.K > 0) {
                arg4 = this.K;
            }
            else {
                arg4 = this.getPreferredWidth();
            }
        }
        else if(this.K > 0) {
        label_23:
            v0 = this.K;
            goto label_24;
        }
        else {
            v0 = this.getPreferredWidth();
        label_24:
            arg4 = Math.min(v0, arg4);
        }

        v0 = View$MeasureSpec.getMode(arg5);
        arg5 = View$MeasureSpec.getSize(arg5);
        if(v0 == v1) {
            arg5 = Math.min(this.getPreferredHeight(), arg5);
        }
        else if(v0 != 0) {
        }
        else {
            arg5 = this.getPreferredHeight();
        }

        super.onMeasure(View$MeasureSpec.makeMeasureSpec(arg4, v2), View$MeasureSpec.makeMeasureSpec(arg5, v2));
    }

    protected void onRestoreInstanceState(Parcelable arg2) {
        if(!(arg2 instanceof SavedState)) {
            super.onRestoreInstanceState(arg2);
            return;
        }

        super.onRestoreInstanceState(((SavedState)arg2).getSuperState());
        this.a(((SavedState)arg2).a);
        this.requestLayout();
    }

    protected Parcelable onSaveInstanceState() {
        SavedState v1 = new SavedState(super.onSaveInstanceState());
        v1.a = this.c();
        return ((Parcelable)v1);
    }

    public void onWindowFocusChanged(boolean arg1) {
        super.onWindowFocusChanged(arg1);
        this.q();
    }

    private void p() {
        int v1 = 1;
        int v0 = TextUtils.isEmpty(this.a.getText()) ^ 1;
        int v2 = 0;
        if(v0 == 0 && (!this.E || (this.O))) {
            v1 = 0;
        }

        ImageView v3 = this.d;
        if(v1 != 0) {
        }
        else {
            v2 = 8;
        }

        v3.setVisibility(v2);
        Drawable v1_1 = this.d.getDrawable();
        if(v1_1 != null) {
            int[] v0_1 = v0 != 0 ? SearchView.ENABLED_STATE_SET : SearchView.EMPTY_STATE_SET;
            v1_1.setState(v0_1);
        }
    }

    private void q() {
        this.post(this.R);
    }

    private void r() {
        CharSequence v0 = this.getQueryHint();
        SearchAutoComplete v1 = this.a;
        if(v0 == null) {
            String v0_1 = "";
        }

        v1.setHint(this.c(v0));
    }

    public boolean requestFocus(int arg3, Rect arg4) {
        if(this.J) {
            return 0;
        }

        if(!this.isFocusable()) {
            return 0;
        }

        if(!this.c()) {
            boolean v3 = this.a.requestFocus(arg3, arg4);
            if(v3) {
                this.a(false);
            }

            return v3;
        }

        return super.requestFocus(arg3, arg4);
    }

    private void s() {
        this.a.setThreshold(this.h.getSuggestThreshold());
        this.a.setImeOptions(this.h.getImeOptions());
        int v0 = this.h.getInputType();
        int v2 = 1;
        if((v0 & 15) == 1) {
            v0 &= -65537;
            if(this.h.getSuggestAuthority() != null) {
                v0 = v0 | 65536 | 524288;
            }
        }

        this.a.setInputType(v0);
        if(this.g != null) {
            this.g.a(null);
        }

        if(this.h.getSuggestAuthority() != null) {
            this.g = new be(this.getContext(), this, this.h, this.T);
            this.a.setAdapter(this.g);
            android.support.v4.widget.f v0_1 = this.g;
            if(this.I) {
                v2 = 2;
            }

            ((be)v0_1).a(v2);
        }
    }

    public void setAppSearchData(Bundle arg1) {
        this.Q = arg1;
    }

    public void setIconified(boolean arg1) {
        if(arg1) {
            this.f();
        }
        else {
            this.g();
        }
    }

    public void setIconifiedByDefault(boolean arg2) {
        if(this.E == arg2) {
            return;
        }

        this.E = arg2;
        this.a(arg2);
        this.r();
    }

    public void setImeOptions(int arg2) {
        this.a.setImeOptions(arg2);
    }

    public void setInputType(int arg2) {
        this.a.setInputType(arg2);
    }

    public void setMaxWidth(int arg1) {
        this.K = arg1;
        this.requestLayout();
    }

    public void setOnCloseListener(b arg1) {
        this.B = arg1;
    }

    public void setOnQueryTextFocusChangeListener(View$OnFocusChangeListener arg1) {
        this.f = arg1;
    }

    public void setOnQueryTextListener(android.support.v7.widget.SearchView$c arg1) {
        this.A = arg1;
    }

    public void setOnSearchClickListener(View$OnClickListener arg1) {
        this.D = arg1;
    }

    public void setOnSuggestionListener(d arg1) {
        this.C = arg1;
    }

    private void setQuery(CharSequence arg3) {
        this.a.setText(arg3);
        SearchAutoComplete v0 = this.a;
        int v3 = TextUtils.isEmpty(arg3) ? 0 : arg3.length();
        v0.setSelection(v3);
    }

    public void setQueryHint(CharSequence arg1) {
        this.H = arg1;
        this.r();
    }

    public void setQueryRefinementEnabled(boolean arg2) {
        this.I = arg2;
        if((this.g instanceof be)) {
            android.support.v4.widget.f v0 = this.g;
            int v2 = arg2 ? 2 : 1;
            ((be)v0).a(v2);
        }
    }

    public void setSearchableInfo(SearchableInfo arg2) {
        this.h = arg2;
        if(this.h != null) {
            this.s();
            this.r();
        }

        this.L = this.m();
        if(this.L) {
            this.a.setPrivateImeOptions("nm");
        }

        this.a(this.c());
    }

    public void setSubmitButtonEnabled(boolean arg1) {
        this.G = arg1;
        this.a(this.c());
    }

    public void setSuggestionsAdapter(android.support.v4.widget.f arg2) {
        this.g = arg2;
        this.a.setAdapter(this.g);
    }

    private void t() {
        this.a.dismissDropDown();
    }
}

