package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.ImageView;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class jb extends ImageView implements View$OnClickListener {
    private final CompanionData a;
    private final jd b;
    private final String c;
    private final List d;

    public jb(Context arg1, jd arg2, CompanionData arg3, String arg4, List arg5) {
        super(arg1);
        this.b = arg2;
        this.a = arg3;
        this.c = arg4;
        this.d = arg5;
        this.setOnClickListener(((View$OnClickListener)this));
    }

    static CompanionData a(jb arg0) {
        return arg0.a;
    }

    Bitmap a(String arg2) {
        return BitmapFactory.decodeStream(new URL(arg2).openConnection().getInputStream());
    }

    public void a() {
        new AsyncTask() {
            Exception a;

            protected Bitmap a(Void[] arg2) {
                try {
                    return this.b.a(jb.a(this.b).src());
                }
                catch(IOException v2) {
                    this.a = ((Exception)v2);
                    return null;
                }
            }

            protected void a(Bitmap arg5) {
                if(arg5 == null) {
                    String v0 = jb.a(this.b).src();
                    String v1 = String.valueOf(this.a);
                    StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 33 + String.valueOf(v1).length());
                    v3.append("Loading image companion ");
                    v3.append(v0);
                    v3.append(" failed: ");
                    v3.append(v1);
                    Log.e("IMASDK", v3.toString());
                    return;
                }

                jb.b(this.b);
                this.b.setImageBitmap(arg5);
            }

            protected Object doInBackground(Object[] arg1) {
                return this.a(((Void[])arg1));
            }

            protected void onPostExecute(Object arg1) {
                this.a(((Bitmap)arg1));
            }
        }.execute(new Void[0]);
    }

    private void b() {
        this.b.a(this.a.companionId(), this.c);
    }

    static void b(jb arg0) {
        arg0.b();
    }

    public void onClick(View arg2) {
        Iterator v2 = this.d.iterator();
        while(v2.hasNext()) {
            v2.next().onCompanionAdClick();
        }

        this.b.c(this.a.clickThroughUrl());
    }
}

