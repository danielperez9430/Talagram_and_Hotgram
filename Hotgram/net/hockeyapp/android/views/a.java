package net.hockeyapp.android.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.text.TextUtils$TruncateAt;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View$OnClickListener;
import android.view.View$OnFocusChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.hockeyapp.android.c.b;
import net.hockeyapp.android.e.g;
import net.hockeyapp.android.e.k;
import net.hockeyapp.android.f$d;

@SuppressLint(value={"ViewConstructor"}) public class a extends FrameLayout {
    private final Context a;
    private final ViewGroup b;
    private final b c;
    private final Uri d;
    private final String e;
    private ImageView f;
    private TextView g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;

    @SuppressLint(value={"StaticFieldLeak"}) public a(Context arg1, ViewGroup arg2, Uri arg3, boolean arg4) {
        super(arg1);
        this.a = arg1;
        this.b = arg2;
        this.c = null;
        this.d = arg3;
        this.e = arg3.getLastPathSegment();
        this.a(10);
        this.a(arg1, arg4);
        this.g.setText(this.e);
        this.g.setContentDescription(this.g.getText());
        net.hockeyapp.android.e.a.a(new AsyncTask() {
            protected Bitmap a(Void[] arg1) {
                return a.a(this.a);
            }

            protected void a(Bitmap arg3) {
                if(arg3 != null) {
                    a.a(this.a, arg3, false);
                }
                else {
                    a.a(this.a, false);
                }
            }

            protected Object doInBackground(Object[] arg1) {
                return this.a(((Void[])arg1));
            }

            protected void onPostExecute(Object arg1) {
                this.a(((Bitmap)arg1));
            }
        });
    }

    public a(Context arg1, ViewGroup arg2, b arg3, boolean arg4) {
        super(arg1);
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = null;
        this.e = arg3.a();
        this.a(40);
        this.a(arg1, arg4);
        this.m = 1;
        this.g.setText(d.hockeyapp_feedback_attachment_loading);
        this.g.setContentDescription(this.g.getText());
        this.a(false);
    }

    public void a(Bitmap arg3, int arg4) {
        this.g.setText(this.e);
        this.g.setContentDescription(this.g.getText());
        this.m = arg4;
        if(arg3 == null) {
            this.a(true);
        }
        else {
            this.a(arg3, true);
        }
    }

    private void a(int arg4) {
        DisplayMetrics v0 = this.getResources().getDisplayMetrics();
        this.l = Math.round(TypedValue.applyDimension(1, 10f, v0));
        int v0_1 = v0.widthPixels - Math.round(TypedValue.applyDimension(1, ((float)arg4), v0)) * 2;
        arg4 = v0_1 - this.l * 2;
        v0_1 -= this.l;
        this.h = arg4 / 3;
        this.j = v0_1 / 2;
        this.i = this.h * 2;
        this.k = this.j;
    }

    private void a(Context arg10, boolean arg11) {
        int v1 = 80;
        int v2 = -2;
        this.setLayoutParams(new FrameLayout$LayoutParams(v2, v2, v1));
        this.setPadding(0, this.l, 0, 0);
        k.a(this.b, this.a.getString(d.hockeyapp_feedback_attachment_added));
        this.f = new ImageView(arg10);
        LinearLayout v0 = new LinearLayout(arg10);
        int v5 = -1;
        v0.setLayoutParams(new FrameLayout$LayoutParams(v5, v2, v1));
        v0.setGravity(8388611);
        v0.setOrientation(1);
        v0.setBackgroundColor(Color.parseColor("#80262626"));
        this.g = new TextView(arg10);
        this.g.setLayoutParams(new FrameLayout$LayoutParams(v5, v2, 17));
        this.g.setGravity(17);
        this.g.setTextColor(arg10.getResources().getColor(net.hockeyapp.android.f$a.hockeyapp_text_white));
        this.g.setSingleLine();
        this.g.setEllipsize(TextUtils$TruncateAt.MIDDLE);
        if(arg11) {
            ImageButton v11 = new ImageButton(arg10);
            v11.setLayoutParams(new FrameLayout$LayoutParams(v5, v2, v1));
            v11.setAdjustViewBounds(true);
            v11.setImageDrawable(this.a("ic_menu_delete"));
            v11.setBackgroundResource(0);
            v11.setContentDescription(arg10.getString(d.hockeyapp_feedback_attachment_remove_description));
            v11.setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg1) {
                    this.a.a();
                }
            });
            v11.setOnFocusChangeListener(new View$OnFocusChangeListener() {
                public void onFocusChange(View arg1, boolean arg2) {
                    if(arg2) {
                        k.a(a.b(this.a), a.b(this.a).getText());
                    }
                }
            });
            v0.addView(((View)v11));
        }

        v0.addView(this.g);
        this.addView(this.f);
        this.addView(((View)v0));
    }

    static Bitmap a(a arg0) {
        return arg0.c();
    }

    static void a(a arg0, Bitmap arg1, boolean arg2) {
        arg0.a(arg1, arg2);
    }

    static void a(a arg0, boolean arg1) {
        arg0.a(arg1);
    }

    private void a(boolean arg4) {
        this.g.setMaxWidth(this.h);
        this.g.setMinWidth(this.h);
        this.f.setLayoutParams(new FrameLayout$LayoutParams(-2, -2));
        this.f.setAdjustViewBounds(false);
        this.f.setBackgroundColor(Color.parseColor("#eeeeee"));
        this.f.setMinimumHeight(((int)((((float)this.h)) * 1.2f)));
        this.f.setMinimumWidth(this.h);
        this.f.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.f.setImageDrawable(this.a("ic_menu_attachment"));
        this.f.setContentDescription(this.g.getText());
        this.f.setOnClickListener(new View$OnClickListener(arg4) {
            public void onClick(View arg3) {
                if(!this.a) {
                    return;
                }

                Intent v3 = new Intent();
                v3.setAction("android.intent.action.VIEW");
                v3.setDataAndType(a.c(this.b), "*/*");
                a.d(this.b).startActivity(v3);
            }
        });
    }

    private Drawable a(String arg5) {
        if(Build$VERSION.SDK_INT >= 21) {
            return this.getResources().getDrawable(this.getResources().getIdentifier(arg5, "drawable", "android"), this.a.getTheme());
        }

        return this.getResources().getDrawable(this.getResources().getIdentifier(arg5, "drawable", "android"));
    }

    public void a() {
        k.a(this.b, this.a.getString(d.hockeyapp_feedback_attachment_removed));
        this.b.removeView(((View)this));
    }

    private void a(Bitmap arg6, boolean arg7) {
        int v0 = this.m == 0 ? this.j : this.h;
        int v1 = this.m == 0 ? this.k : this.i;
        this.g.setMaxWidth(v0);
        this.g.setMinWidth(v0);
        this.f.setLayoutParams(new FrameLayout$LayoutParams(-2, -2));
        this.f.setAdjustViewBounds(true);
        this.f.setMinimumWidth(v0);
        this.f.setMaxWidth(v0);
        this.f.setMaxHeight(v1);
        this.f.setScaleType(ImageView$ScaleType.CENTER_INSIDE);
        this.f.setImageBitmap(arg6);
        this.f.setContentDescription(this.g.getText());
        this.f.setOnClickListener(new View$OnClickListener(arg7) {
            public void onClick(View arg3) {
                if(!this.a) {
                    return;
                }

                Intent v3 = new Intent();
                v3.setAction("android.intent.action.VIEW");
                v3.setDataAndType(a.c(this.b), "image/*");
                a.d(this.b).startActivity(v3);
            }
        });
    }

    public void b() {
        this.g.setText(d.hockeyapp_feedback_attachment_error);
        this.g.setContentDescription(this.g.getText());
    }

    static TextView b(a arg0) {
        return arg0.g;
    }

    private Bitmap c() {
        try {
            this.m = g.a(this.a, this.d);
            int v0 = this.m == 0 ? this.j : this.h;
            int v1 = this.m == 0 ? this.k : this.i;
            return g.a(this.a, this.d, v0, v1);
        }
        catch(Throwable ) {
            return null;
        }
    }

    static Uri c(a arg0) {
        return arg0.d;
    }

    static Context d(a arg0) {
        return arg0.a;
    }

    public b getAttachment() {
        return this.c;
    }

    public Uri getAttachmentUri() {
        return this.d;
    }

    public int getEffectiveMaxHeight() {
        int v0 = this.m == 0 ? this.k : this.i;
        return v0;
    }

    public int getGap() {
        return this.l;
    }

    public int getMaxHeightLandscape() {
        return this.k;
    }

    public int getMaxHeightPortrait() {
        return this.i;
    }

    public int getWidthLandscape() {
        return this.j;
    }

    public int getWidthPortrait() {
        return this.h;
    }
}

