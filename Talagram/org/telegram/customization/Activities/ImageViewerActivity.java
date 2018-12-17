package org.telegram.customization.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.q;
import android.text.TextUtils;
import android.util.Log;
import android.view.View$OnClickListener;
import android.view.View;
import com.google.a.c.a;
import com.google.a.f;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.customization.a.n;
import org.telegram.customization.util.m;
import org.telegram.customization.util.view.zoom.ExtendedViewPager;
import org.telegram.news.NewsDescriptionActivity;
import utils.view.FarsiTextView;
import utils.view.TitleTextView;

public class ImageViewerActivity extends Activity implements View$OnClickListener {
    boolean a;
    ExtendedViewPager b;
    int c;
    ArrayList d;
    ArrayList e;
    ArrayList f;
    View g;
    View h;
    View i;
    View j;
    View k;
    TitleTextView l;
    FarsiTextView m;
    Activity n;
    View o;
    View p;
    Dialog q;
    String r;
    f s;
    private boolean t;

    public ImageViewerActivity() {
        super();
        this.c = 0;
        this.d = new ArrayList();
        this.s = new f();
        this.t = false;
    }

    private ArrayList a(ArrayList arg3) {
        ArrayList v0 = new ArrayList();
        if(arg3 != null) {
            Iterator v3 = arg3.iterator();
            while(v3.hasNext()) {
                v0.add(this.b(v3.next()));
            }
        }

        return v0;
    }

    private void a() {
        org.telegram.customization.Activities.ImageViewerActivity$4 v1_1;
        ExtendedViewPager v0;
        if(this.a) {
            Log.d("sadegh", "imagevieweract pos1:" + this.c);
            Activity v2_1 = this.n;
            ArrayList v3 = this.t ? this.a(this.d) : this.d;
            this.b.setAdapter(new n(((Context)this), v2_1, v3));
            this.b.setCurrentItem(this.c);
            this.b.setPageTransformer(true, new m());
            v0 = this.b;
            org.telegram.customization.Activities.ImageViewerActivity$3 v1 = new android.support.v4.view.ViewPager$f() {
                public void onPageScrollStateChanged(int arg1) {
                }

                public void onPageScrolled(int arg1, float arg2, int arg3) {
                }

                public void onPageSelected(int arg4) {
                    this.a.c = arg4;
                    Log.d("sadegh", "imagevieweract pos2:" + this.a.c);
                    this.a.a(this.a.d.get(arg4));
                    Log.d("sadegh", "imagevieweract url:" + this.a.d.get(arg4));
                    ImageViewerActivity.a(this.a);
                    ImageViewerActivity.b(this.a);
                }
            };
        }
        else {
            ArrayList v2_2 = this.t ? this.a(this.d) : this.d;
            org.telegram.customization.a.f v0_1 = new org.telegram.customization.a.f(((Context)this), ((Activity)this), v2_2);
            this.b.setAdapter(((q)v0_1));
            this.b.setOnClickListener(((View$OnClickListener)v0_1));
            this.b.setCurrentItem(this.c);
            this.b.setPageTransformer(true, new m());
            v0 = this.b;
            v1_1 = new android.support.v4.view.ViewPager$f() {
                public void onPageScrollStateChanged(int arg1) {
                }

                public void onPageScrolled(int arg1, float arg2, int arg3) {
                }

                public void onPageSelected(int arg3) {
                    this.a.c = arg3;
                    this.a.a(this.a.d.get(arg3));
                    ImageViewerActivity.a(this.a);
                    ImageViewerActivity.b(this.a);
                }
            };
        }

        v0.setOnPageChangeListener(((android.support.v4.view.ViewPager$f)v1_1));
        this.b();
        this.c();
    }

    public void a(String arg1) {
        this.r = arg1;
    }

    static void a(ImageViewerActivity arg0) {
        arg0.b();
    }

    private String b(String arg3) {
        return "file://" + arg3;
    }

    private void b() {
        if(this.e != null && this.e.size() > this.c) {
            this.m.setText(this.e.get(this.c));
        }

        TitleTextView v0 = this.l;
        v0.setText(this.c + 1 + "/" + this.d.size());
    }

    static void b(ImageViewerActivity arg0) {
        arg0.c();
    }

    private void c() {
        if(this.f != null) {
            this.f.size();
        }

        if(this.f != null && this.f.size() > this.c) {
            Object v0 = this.f.get(this.c);
            this.e.get(this.c);
            this.d.get(this.c);
            this.h.setOnClickListener(new View$OnClickListener(((String)v0)) {
                public void onClick(View arg4) {
                    Intent v4 = new Intent(this.b.n, NewsDescriptionActivity.class);
                    v4.setAction(System.currentTimeMillis() + "");
                    v4.putExtra("SPECIAL_NEWS", this.a);
                    v4.putExtra("EXTRA_BACK_TO_HOME", false);
                    this.b.n.startActivity(v4);
                }
            });
        }
    }

    public void onClick(View arg1) {
        switch(arg1.getId()) {
            case 2131296854: 
            case 2131296855: {
                this.finish();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void onConfigurationChanged(Configuration arg3) {
        super.onConfigurationChanged(arg3);
        if(arg3.orientation == 2) {
            this.a = false;
        }
        else if(arg3.orientation == 1) {
            this.a = true;
        }

        this.a();
    }

    public void onCreate(Bundle arg6) {
        super.onCreate(arg6);
        this.setContentView(2131492902);
        this.overridePendingTransition(2130771999, 2130771988);
        this.g = this.findViewById(2131296633);
        this.k = this.findViewById(2131296598);
        this.h = this.findViewById(2131296635);
        this.i = this.findViewById(2131296608);
        this.j = this.findViewById(2131296643);
        int v6 = 2131296514;
        this.l = this.findViewById(v6);
        this.m = this.findViewById(2131296748);
        this.o = this.findViewById(2131296854);
        this.p = this.findViewById(2131296855);
        this.n = ((Activity)this);
        this.q = new Dialog(this.n);
        int v1 = 0;
        this.a = this.getResources().getConfiguration().orientation == 2 ? false : true;
        int v2 = 8;
        if(this.getIntent().getExtras() != null) {
            this.d = this.s.a(this.getIntent().getStringExtra("id"), new a() {
            }.b());
            if(this.f != null && this.f.size() > 0) {
                this.h.setVisibility(0);
            }

            if(this.t) {
                this.i.setVisibility(v2);
            }

            this.c = this.getIntent().getIntExtra("CURRENT_PIC_ID", 0);
        }

        if(this.e != null) {
            while(v1 < this.e.size()) {
                if(TextUtils.isEmpty(this.e.get(v1))) {
                    this.e.set(v1, " ");
                }

                ++v1;
            }
        }

        this.b = this.findViewById(2131297086);
        this.l = this.findViewById(v6);
        this.a();
        this.a(this.d.get(this.c));
        this.k.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                this.a.onBackPressed();
            }
        });
        this.g.setVisibility(v2);
        this.o.setOnClickListener(((View$OnClickListener)this));
        this.p.setOnClickListener(((View$OnClickListener)this));
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        try {
            this.a();
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }
    }
}

