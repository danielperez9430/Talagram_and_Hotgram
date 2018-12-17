package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.drawable.Drawable;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.p;
import android.support.v7.a.a$f;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class be extends p implements View$OnClickListener {
    final class a {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public a(View arg2) {
            super();
            this.a = arg2.findViewById(16908308);
            this.b = arg2.findViewById(16908309);
            this.c = arg2.findViewById(16908295);
            this.d = arg2.findViewById(16908296);
            this.e = arg2.findViewById(f.edit_query);
        }
    }

    private final SearchManager j;
    private final SearchView k;
    private final SearchableInfo l;
    private final Context m;
    private final WeakHashMap n;
    private final int o;
    private boolean p;
    private int q;
    private ColorStateList r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;

    public be(Context arg4, SearchView arg5, SearchableInfo arg6, WeakHashMap arg7) {
        super(arg4, arg5.getSuggestionRowLayout(), null, true);
        this.p = false;
        this.q = 1;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.j = this.d.getSystemService("search");
        this.k = arg5;
        this.l = arg6;
        this.o = arg5.getSuggestionCommitIconResId();
        this.m = arg4;
        this.n = arg7;
    }

    public static String a(Cursor arg0, String arg1) {
        return be.a(arg0, arg0.getColumnIndex(arg1));
    }

    public void a(int arg1) {
        this.q = arg1;
    }

    private Drawable a(ComponentName arg4) {
        String v0 = arg4.flattenToShortString();
        Drawable v2 = null;
        if(this.n.containsKey(v0)) {
            Object v4 = this.n.get(v0);
            if(v4 == null) {
            }
            else {
                v2 = ((Drawable$ConstantState)v4).newDrawable(this.m.getResources());
            }

            return v2;
        }

        Drawable v4_1 = this.b(arg4);
        if(v4_1 == null) {
        }
        else {
            Drawable$ConstantState v2_1 = v4_1.getConstantState();
        }

        this.n.put(v0, v2);
        return v4_1;
    }

    private Drawable a(String arg5) {
        Drawable v0 = null;
        if(arg5 != null && !arg5.isEmpty() && !"0".equals(arg5)) {
            try {
                int v1 = Integer.parseInt(arg5);
                String v2_1 = "android.resource://" + this.m.getPackageName() + "/" + v1;
                Drawable v3 = this.b(v2_1);
                if(v3 != null) {
                    return v3;
                }

                Drawable v1_1 = android.support.v4.content.a.a(this.m, v1);
                this.a(v2_1, v1_1);
                return v1_1;
            }
            catch(Resources$NotFoundException ) {
                Log.w("SuggestionsAdapter", "Icon resource not found: " + arg5);
                return v0;
            }
            catch(NumberFormatException ) {
                v0 = this.b(arg5);
                if(v0 != null) {
                    return v0;
                }

                v0 = this.b(Uri.parse(arg5));
                this.a(arg5, v0);
            }
        }

        return v0;
    }

    private void a(String arg2, Drawable arg3) {
        if(arg3 != null) {
            this.n.put(arg2, arg3.getConstantState());
        }
    }

    private static String a(Cursor arg2, int arg3) {
        String v0 = null;
        if(arg3 == -1) {
            return v0;
        }

        try {
            return arg2.getString(arg3);
        }
        catch(Exception v2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", ((Throwable)v2));
            return v0;
        }
    }

    private void a(ImageView arg1, Drawable arg2, int arg3) {
        arg1.setImageDrawable(arg2);
        if(arg2 == null) {
            arg1.setVisibility(arg3);
        }
        else {
            arg1.setVisibility(0);
            arg2.setVisible(false, false);
            arg2.setVisible(true, false);
        }
    }

    private void a(TextView arg1, CharSequence arg2) {
        arg1.setText(arg2);
        int v2 = TextUtils.isEmpty(arg2) ? 8 : 0;
        arg1.setVisibility(v2);
    }

    Drawable a(Uri arg8) {
        int v0_1;
        StringBuilder v1_1;
        Resources v1;
        String v0 = arg8.getAuthority();
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            goto label_68;
        }

        try {
            v1 = this.d.getPackageManager().getResourcesForApplication(v0);
        }
        catch(PackageManager$NameNotFoundException ) {
            v1_1 = new StringBuilder();
            v1_1.append("No package found for authority: ");
            v1_1.append(arg8);
            throw new FileNotFoundException(v1_1.toString());
        }

        List v2 = arg8.getPathSegments();
        if(v2 == null) {
            goto label_50;
        }

        int v3 = v2.size();
        if(v3 == 1) {
            try {
                v0_1 = Integer.parseInt(v2.get(0));
            }
            catch(NumberFormatException ) {
                v1_1 = new StringBuilder();
                v1_1.append("Single path segment is not a resource ID: ");
                v1_1.append(arg8);
                throw new FileNotFoundException(v1_1.toString());
            }
        }
        else if(v3 == 2) {
            v0_1 = v1.getIdentifier(v2.get(1), v2.get(0), v0);
        }
        else {
            goto label_41;
        }

        if(v0_1 != 0) {
            return v1.getDrawable(v0_1);
        }

        v1_1 = new StringBuilder();
        v1_1.append("No resource found for: ");
        v1_1.append(arg8);
        throw new FileNotFoundException(v1_1.toString());
    label_41:
        v1_1 = new StringBuilder();
        v1_1.append("More than two path segments: ");
        v1_1.append(arg8);
        throw new FileNotFoundException(v1_1.toString());
    label_50:
        v1_1 = new StringBuilder();
        v1_1.append("No path: ");
        v1_1.append(arg8);
        throw new FileNotFoundException(v1_1.toString());
    label_68:
        v1_1 = new StringBuilder();
        v1_1.append("No authority: ");
        v1_1.append(arg8);
        throw new FileNotFoundException(v1_1.toString());
    }

    Cursor a(SearchableInfo arg10, String arg11, int arg12) {
        Cursor v0 = null;
        if(arg10 == null) {
            return v0;
        }

        String v1 = arg10.getSuggestAuthority();
        if(v1 == null) {
            return v0;
        }

        Uri$Builder v1_1 = new Uri$Builder().scheme("content").authority(v1).query("").fragment("");
        String v2 = arg10.getSuggestPath();
        if(v2 != null) {
            v1_1.appendEncodedPath(v2);
        }

        v1_1.appendPath("search_suggest_query");
        String v6 = arg10.getSuggestSelection();
        if(v6 != null) {
            String[] v0_1 = new String[]{arg11};
        }
        else {
            v1_1.appendPath(arg11);
        }

        String[] v7 = ((String[])v0);
        if(arg12 > 0) {
            v1_1.appendQueryParameter("limit", String.valueOf(arg12));
        }

        return this.d.getContentResolver().query(v1_1.build(), null, v6, v7, null);
    }

    public Cursor a(CharSequence arg4) {
        String v4 = arg4 == null ? "" : arg4.toString();
        Cursor v1 = null;
        if(this.k.getVisibility() == 0 && this.k.getWindowVisibility() == 0) {
            try {
                Cursor v4_2 = this.a(this.l, v4, 50);
                if(v4_2 == null) {
                    return v1;
                }

                v4_2.getCount();
                return v4_2;
            }
            catch(RuntimeException v4_1) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", ((Throwable)v4_1));
            }
        }

        return v1;
    }

    public View a(Context arg1, Cursor arg2, ViewGroup arg3) {
        View v1 = super.a(arg1, arg2, arg3);
        v1.setTag(new a(v1));
        v1.findViewById(f.edit_query).setImageResource(this.o);
        return v1;
    }

    public void a(Cursor arg3) {
        if(this.p) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if(arg3 != null) {
                arg3.close();
            }

            return;
        }

        try {
            super.a(arg3);
            if(arg3 == null) {
                return;
            }

            this.s = arg3.getColumnIndex("suggest_text_1");
            this.t = arg3.getColumnIndex("suggest_text_2");
            this.u = arg3.getColumnIndex("suggest_text_2_url");
            this.v = arg3.getColumnIndex("suggest_icon_1");
            this.w = arg3.getColumnIndex("suggest_icon_2");
            this.x = arg3.getColumnIndex("suggest_flags");
        }
        catch(Exception v3) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", ((Throwable)v3));
        }
    }

    public void a(View arg7, Context arg8, Cursor arg9) {
        CharSequence v1_1;
        Object v7 = arg7.getTag();
        int v8 = this.x != -1 ? arg9.getInt(this.x) : 0;
        if(((a)v7).a != null) {
            this.a(((a)v7).a, be.a(arg9, this.s));
        }

        int v2 = 2;
        if(((a)v7).b != null) {
            String v1 = be.a(arg9, this.u);
            if(v1 != null) {
                v1_1 = this.b(((CharSequence)v1));
            }
            else {
                v1 = be.a(arg9, this.t);
            }

            if(TextUtils.isEmpty(v1_1)) {
                if(((a)v7).a != null) {
                    ((a)v7).a.setSingleLine(false);
                    ((a)v7).a.setMaxLines(v2);
                }
            }
            else if(((a)v7).a != null) {
                ((a)v7).a.setSingleLine(true);
                ((a)v7).a.setMaxLines(1);
            }

            this.a(((a)v7).b, v1_1);
        }

        if(((a)v7).c != null) {
            this.a(((a)v7).c, this.e(arg9), 4);
        }

        int v4 = 8;
        if(((a)v7).d != null) {
            this.a(((a)v7).d, this.f(arg9), v4);
        }

        if(this.q != v2) {
            if(this.q == 1 && (v8 & 1) != 0) {
                goto label_65;
            }

            ((a)v7).e.setVisibility(v4);
        }
        else {
        label_65:
            ((a)v7).e.setVisibility(0);
            ((a)v7).e.setTag(((a)v7).a.getText());
            ((a)v7).e.setOnClickListener(((View$OnClickListener)this));
        }
    }

    private Drawable b(ComponentName arg6) {
        String v6_1;
        String v0_1;
        ActivityInfo v1_1;
        PackageManager v0 = this.d.getPackageManager();
        int v1 = 128;
        Drawable v2 = null;
        try {
            v1_1 = v0.getActivityInfo(arg6, v1);
        }
        catch(PackageManager$NameNotFoundException v6) {
            v0_1 = "SuggestionsAdapter";
            v6_1 = v6.toString();
            goto label_23;
        }

        int v3 = v1_1.getIconResource();
        if(v3 == 0) {
            return v2;
        }

        Drawable v0_2 = v0.getDrawable(arg6.getPackageName(), v3, v1_1.applicationInfo);
        if(v0_2 == null) {
            v0_1 = "SuggestionsAdapter";
            v6_1 = "Invalid icon resource " + v3 + " for " + arg6.flattenToShortString();
        label_23:
            Log.w(v0_1, v6_1);
            return v2;
        }

        return v0_2;
    }

    private Drawable b(String arg2) {
        Object v2 = this.n.get(arg2);
        if(v2 == null) {
            return null;
        }

        return ((Drawable$ConstantState)v2).newDrawable();
    }

    private Drawable b(Uri arg7) {
        Drawable v2_2;
        InputStream v1_1;
        StringBuilder v2;
        String v0 = null;
        try {
            if("android.resource".equals(arg7.getScheme())) {
                try {
                    return this.a(arg7);
                }
                catch(Resources$NotFoundException ) {
                    v2 = new StringBuilder();
                    v2.append("Resource does not exist: ");
                    v2.append(arg7);
                    throw new FileNotFoundException(v2.toString());
                }
            }

            v1_1 = this.m.getContentResolver().openInputStream(arg7);
            if(v1_1 == null) {
                goto label_46;
            }
        }
        catch(FileNotFoundException v1) {
            goto label_58;
        }

        try {
            v2_2 = Drawable.createFromStream(v1_1, v0);
            goto label_21;
        }
        catch(Throwable v2_1) {
            try {
                v1_1.close();
                goto label_45;
            }
            catch(FileNotFoundException v1) {
            }
            catch(IOException v1_2) {
                try {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + arg7, ((Throwable)v1_2));
                label_45:
                    throw v2_1;
                }
                catch(FileNotFoundException v1) {
                    goto label_58;
                }

                try {
                label_21:
                    v1_1.close();
                    return v2_2;
                }
                catch(FileNotFoundException v1) {
                }
                catch(IOException v1_2) {
                    try {
                        Log.e("SuggestionsAdapter", "Error closing icon stream for " + arg7, ((Throwable)v1_2));
                    }
                    catch(FileNotFoundException v1) {
                        goto label_58;
                    }

                    return v2_2;
                    try {
                    label_46:
                        v2 = new StringBuilder();
                        v2.append("Failed to open ");
                        v2.append(arg7);
                        throw new FileNotFoundException(v2.toString());
                    }
                    catch(FileNotFoundException v1) {
                    label_58:
                        Log.w("SuggestionsAdapter", "Icon not found: " + arg7 + ", " + v1.getMessage());
                        return ((Drawable)v0);
                    }
                }
            }
        }
    }

    private CharSequence b(CharSequence arg9) {
        if(this.r == null) {
            TypedValue v0 = new TypedValue();
            this.d.getTheme().resolveAttribute(android.support.v7.a.a$a.textColorSearchUrl, v0, true);
            this.r = this.d.getResources().getColorStateList(v0.resourceId);
        }

        SpannableString v0_1 = new SpannableString(arg9);
        v0_1.setSpan(new TextAppearanceSpan(null, 0, 0, this.r, null), 0, arg9.length(), 33);
        return ((CharSequence)v0_1);
    }

    public CharSequence c(Cursor arg3) {
        CharSequence v0 = null;
        if(arg3 == null) {
            return v0;
        }

        String v1 = be.a(arg3, "suggest_intent_query");
        if(v1 != null) {
            return ((CharSequence)v1);
        }

        if(this.l.shouldRewriteQueryFromData()) {
            v1 = be.a(arg3, "suggest_intent_data");
            if(v1 != null) {
                return ((CharSequence)v1);
            }
        }

        if(this.l.shouldRewriteQueryFromText()) {
            String v3 = be.a(arg3, "suggest_text_1");
            if(v3 != null) {
                return ((CharSequence)v3);
            }
        }

        return v0;
    }

    private void d(Cursor arg2) {
        Bundle v2 = arg2 != null ? arg2.getExtras() : null;
        if(v2 != null) {
            v2.getBoolean("in_progress");
        }
    }

    private Drawable e(Cursor arg3) {
        if(this.v == -1) {
            return null;
        }

        Drawable v0 = this.a(arg3.getString(this.v));
        if(v0 != null) {
            return v0;
        }

        return this.g(arg3);
    }

    private Drawable f(Cursor arg3) {
        if(this.w == -1) {
            return null;
        }

        return this.a(arg3.getString(this.w));
    }

    private Drawable g(Cursor arg1) {
        Drawable v1 = this.a(this.l.getSearchActivity());
        if(v1 != null) {
            return v1;
        }

        return this.d.getPackageManager().getDefaultActivityIcon();
    }

    public View getDropDownView(int arg2, View arg3, ViewGroup arg4) {
        try {
            return super.getDropDownView(arg2, arg3, arg4);
        }
        catch(RuntimeException v2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", ((Throwable)v2));
            arg3 = this.b(this.d, this.c, arg4);
            if(arg3 != null) {
                arg3.getTag().a.setText(v2.toString());
            }

            return arg3;
        }
    }

    public View getView(int arg2, View arg3, ViewGroup arg4) {
        try {
            return super.getView(arg2, arg3, arg4);
        }
        catch(RuntimeException v2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", ((Throwable)v2));
            arg3 = this.a(this.d, this.c, arg4);
            if(arg3 != null) {
                arg3.getTag().a.setText(v2.toString());
            }

            return arg3;
        }
    }

    public boolean hasStableIds() {
        return 0;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.d(this.a());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        this.d(this.a());
    }

    public void onClick(View arg2) {
        Object v2 = arg2.getTag();
        if((v2 instanceof CharSequence)) {
            this.k.a(((CharSequence)v2));
        }
    }
}

