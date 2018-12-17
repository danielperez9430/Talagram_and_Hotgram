package org.telegram.customization.util;

import android.app.DownloadManager$Query;
import android.app.DownloadManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.R$styleable;

public class DownloadProgressView extends LinearLayout {
    public interface a {
        void a();

        void a(int arg1);

        void a(String arg1);
    }

    private final ProgressBar a;
    private final TextView b;
    private final TextView c;
    private final TextView d;
    private final ImageView e;
    private final DownloadManager f;
    private final Context g;
    private int h;
    private int i;
    private int j;
    private long k;
    private boolean l;
    private List m;

    public DownloadProgressView(Context arg4, AttributeSet arg5) {
        super(arg4, arg5);
        this.m = new ArrayList();
        this.g = arg4;
        TypedArray v5 = arg4.getTheme().obtainStyledAttributes(arg5, styleable.DownloadProgressView, 0, 0);
        int v0 = -16777216;
        try {
            this.h = v5.getColor(0, v0);
            this.i = v5.getColor(2, v0);
            this.j = v5.getColor(2, v0);
        }
        catch(Throwable v4) {
            v5.recycle();
            throw v4;
        }

        v5.recycle();
        this.setOrientation(1);
        this.setGravity(17);
        arg4.getSystemService("layout_inflater").inflate(2131492986, ((ViewGroup)this), true);
        this.f = arg4.getSystemService("download");
        this.b = this.findViewById(2131296452);
        this.c = this.findViewById(2131296977);
        this.d = this.findViewById(2131296781);
        this.a = this.findViewById(2131296450);
        this.b.setTextColor(ColorStateList.valueOf(this.j));
        this.c.setTextColor(ColorStateList.valueOf(this.j));
        this.d.setTextColor(ColorStateList.valueOf(this.j));
        this.e = this.findViewById(2131296393);
        this.e.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg5) {
                if(DownloadProgressView.a(this.a) != null) {
                    DownloadProgressView.a(this.a).remove(new long[]{DownloadProgressView.b(this.a)});
                    try {
                        Iterator v5 = DownloadProgressView.c(this.a).iterator();
                        while(true) {
                            if(v5.hasNext()) {
                                v5.next().a();
                                continue;
                            }

                            goto label_20;
                        }
                    }
                    catch(Exception ) {
                    label_20:
                        this.a.setVisibility(8);
                        return;
                    }
                }

                goto label_20;
            }
        });
        this.setVisibility(8);
    }

    public void a(long arg1, a arg3) {
        this.k = arg1;
        this.m.add(arg3);
        this.a();
    }

    static DownloadManager a(DownloadProgressView arg0) {
        return arg0.f;
    }

    static List a(DownloadProgressView arg0, List arg1) {
        arg0.m = arg1;
        return arg1;
    }

    private void a() {
        this.setVisibility(0);
        new Thread() {
            public void run() {
                do {
                    DownloadProgressView.a(this.a, true);
                    DownloadManager$Query v0 = new DownloadManager$Query();
                    v0.setFilterById(new long[]{DownloadProgressView.b(this.a)});
                    Cursor v0_1 = DownloadProgressView.a(this.a).query(v0);
                    if(v0_1.moveToFirst()) {
                        int v4 = v0_1.getInt(v0_1.getColumnIndex("status"));
                        int v11 = v0_1.getInt(v0_1.getColumnIndex("reason"));
                        long v5 = ((long)v0_1.getInt(v0_1.getColumnIndex("bytes_so_far")));
                        long v7 = ((long)v0_1.getInt(v0_1.getColumnIndex("total_size")));
                        long v1 = 0;
                        if(v7 > v1) {
                            v1 = 100 * v5 / v7;
                        }

                        new Handler(Looper.getMainLooper()).post(new Runnable(v4, v5, v7, v1, v11) {
                            public void run() {
                                if(this.a == 2) {
                                    DownloadProgressView.a(this.f.a, true);
                                    DownloadProgressView.d(this.f.a).setIndeterminate(false);
                                    TextView v0 = DownloadProgressView.e(this.f.a);
                                    Object[] v4 = new Object[1];
                                    double v5 = ((double)this.b);
                                    Double.isNaN(v5);
                                    v4[0] = Double.valueOf(v5 * 1 / 1024 / 1024);
                                    v0.setText(String.format("%.3fMB", v4));
                                    v0 = DownloadProgressView.f(this.f.a);
                                    Object[] v2 = new Object[1];
                                    double v4_1 = ((double)this.c);
                                    Double.isNaN(v4_1);
                                    v2[0] = Double.valueOf(v4_1 * 1 / 1024 / 1024);
                                    v0.setText(String.format("%.2fMB", v2));
                                    v0 = DownloadProgressView.g(this.f.a);
                                    v0.setText((((int)this.d)) + "%");
                                    DownloadProgressView.d(this.f.a).setProgress(((int)this.d));
                                }
                                else {
                                    int v4_2 = 8;
                                    if(this.a == 16) {
                                        DownloadProgressView.a(this.f.a, false);
                                        this.f.a.setVisibility(v4_2);
                                        try {
                                            Iterator v0_1 = DownloadProgressView.c(this.f.a).iterator();
                                            while(v0_1.hasNext()) {
                                                v0_1.next().a(this.e);
                                            }
                                        }
                                        catch(Exception ) {
                                        }
                                    }
                                    else if(this.a == v4_2) {
                                        DownloadProgressView.a(this.f.a, false);
                                        this.f.a.setVisibility(v4_2);
                                        try {
                                            DownloadManager$Query v0_2 = new DownloadManager$Query();
                                            v0_2.setFilterById(new long[]{DownloadProgressView.b(this.f.a)});
                                            Cursor v0_3 = DownloadProgressView.a(this.f.a).query(v0_2);
                                            if(v0_3.moveToFirst()) {
                                                String v1_1 = v0_3.getString(v0_3.getColumnIndex("local_uri"));
                                                List v2_1 = null;
                                                v1_1 = v1_1 != null ? new File(Uri.parse(v1_1).getPath()).getAbsolutePath() : ((String)v2_1);
                                                Iterator v3 = DownloadProgressView.c(this.f.a).iterator();
                                                while(v3.hasNext()) {
                                                    v3.next().a(v1_1);
                                                }

                                                DownloadProgressView.a(this.f.a, v2_1);
                                            }

                                            v0_3.close();
                                        }
                                        catch(Exception ) {
                                        }
                                    }
                                    else {
                                        DownloadProgressView.a(this.f.a, true);
                                        DownloadProgressView.e(this.f.a).setText("");
                                        DownloadProgressView.f(this.f.a).setText("");
                                        DownloadProgressView.g(this.f.a).setText("");
                                        DownloadProgressView.d(this.f.a).setIndeterminate(true);
                                    }
                                }
                            }
                        });
                    }
                    else {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                DownloadProgressView.a(this.a.a, false);
                                this.a.a.setVisibility(8);
                                try {
                                    Iterator v0 = DownloadProgressView.c(this.a.a).iterator();
                                    while(true) {
                                        if(!v0.hasNext()) {
                                            return;
                                        }

                                        v0.next().a(-1);
                                    }
                                }
                                catch(Exception ) {
                                    return;
                                }
                            }
                        });
                    }

                    v0_1.close();
                }
                while(DownloadProgressView.h(this.a));
            }
        }.start();
    }

    static boolean a(DownloadProgressView arg0, boolean arg1) {
        arg0.l = arg1;
        return arg1;
    }

    static long b(DownloadProgressView arg2) {
        return arg2.k;
    }

    static List c(DownloadProgressView arg0) {
        return arg0.m;
    }

    static ProgressBar d(DownloadProgressView arg0) {
        return arg0.a;
    }

    static TextView e(DownloadProgressView arg0) {
        return arg0.b;
    }

    static TextView f(DownloadProgressView arg0) {
        return arg0.c;
    }

    static TextView g(DownloadProgressView arg0) {
        return arg0.d;
    }

    public int getDownloadedSizeColor() {
        return this.h;
    }

    public int getPercentageColor() {
        return this.j;
    }

    public int getTotalSizeColor() {
        return this.i;
    }

    static boolean h(DownloadProgressView arg0) {
        return arg0.l;
    }

    public void setDownloadedSizeColor(int arg2) {
        this.h = arg2;
        this.b.setTextColor(ColorStateList.valueOf(this.j));
        this.invalidate();
        this.requestLayout();
    }

    public void setPercentageColor(int arg2) {
        this.j = arg2;
        this.d.setTextColor(ColorStateList.valueOf(arg2));
        this.invalidate();
        this.requestLayout();
    }

    public void setTotalSizeColor(int arg2) {
        this.i = arg2;
        this.c.setTextColor(ColorStateList.valueOf(this.j));
        this.invalidate();
        this.requestLayout();
    }
}

