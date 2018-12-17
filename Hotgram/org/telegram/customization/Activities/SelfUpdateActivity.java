package org.telegram.customization.Activities;

import android.app.Activity;
import android.app.DownloadManager$Request;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.crashlytics.android.a.b;
import com.crashlytics.android.a.m;
import com.google.a.f;
import java.io.File;
import org.telegram.customization.Model.SettingAndUpdate;
import org.telegram.customization.util.DownloadProgressView$a;
import org.telegram.customization.util.DownloadProgressView;

public class SelfUpdateActivity extends Activity implements View$OnClickListener {
    SettingAndUpdate a;
    LinearLayout b;
    DownloadProgressView c;
    private long d;
    private DownloadManager e;
    private DownloadManager$Request f;
    private static String[] g;

    static {
        SelfUpdateActivity.g = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    public SelfUpdateActivity() {
        super();
    }

    private void a() {
        DownloadManager$Request v0 = this.f;
        Context v1 = this.getApplicationContext();
        v0.setDestinationInExternalFilesDir(v1, null, System.currentTimeMillis() + ".apk");
        this.f.allowScanningByMediaScanner();
        this.d = this.e.enqueue(this.f);
        this.c.a(this.d, new a() {
            public void a() {
                if(SelfUpdateActivity.a(this.a)) {
                    this.a.setResult(0);
                    this.a.finish();
                    SelfUpdateActivity.c(this.a).remove(new long[]{SelfUpdateActivity.b(this.a)});
                }

                this.a.findViewById(2131296449).setVisibility(0);
            }

            public void a(int arg5) {
                if(SelfUpdateActivity.a(this.a)) {
                    this.a.setResult(0);
                    SelfUpdateActivity.c(this.a).remove(new long[]{SelfUpdateActivity.b(this.a)});
                    this.a.finish();
                }
            }

            public void a(String arg3) {
                this.a.a(this.a.getApplicationContext(), arg3);
            }
        });
    }

    static boolean a(SelfUpdateActivity arg0) {
        return arg0.b();
    }

    public void a(Context arg8, String arg9) {
        int v5_1;
        int v4_2;
        Intent v4_1;
        File v0 = new File(arg9);
        int v2 = -1;
        if(!v0.exists()) {
            if(this.b()) {
                this.setResult(0);
            }
            else {
                this.setResult(v2);
            }

            return;
        }

        int v3 = 268435456;
        try {
            v4_1 = new Intent("android.intent.action.VIEW");
            v4_1.setFlags(v3);
            StringBuilder v5 = new StringBuilder();
            v5.append("file://");
            v5.append(v0.toString());
            v4_1.setDataAndType(Uri.parse(v5.toString()), "application/vnd.android.package-archive");
            this.startActivity(v4_1);
            v4_2 = 1;
            v5_1 = -1;
        }
        catch(Exception v4) {
            v4.printStackTrace();
            v4_2 = 0;
            v5_1 = 0;
        }

        if(v4_2 == 0) {
            try {
                v4_1 = new Intent("android.intent.action.INSTALL_PACKAGE");
                v4_1.setFlags(v3);
                v4_1.setFlags(1);
                v4_1.setData(FileProvider.a(arg8, "ir.hotgram.mobile.android.provider", v0));
                this.startActivity(v4_1);
                v5_1 = -1;
            }
            catch(Exception v8) {
                v8.printStackTrace();
            }
        }

        if(!this.b() || v5_1 == v2) {
            this.setResult(v2);
        }
        else {
            this.setResult(0);
        }
    }

    static long b(SelfUpdateActivity arg2) {
        return arg2.d;
    }

    private boolean b() {
        boolean v0 = this.a == null || !this.a.getUpdate().isForceUpdate() ? false : true;
        return v0;
    }

    static DownloadManager c(SelfUpdateActivity arg0) {
        return arg0.e;
    }

    protected void onActivityResult(int arg1, int arg2, Intent arg3) {
        super.onActivityResult(arg1, arg2, arg3);
        if(arg1 != 8888) {
        }
        else if(arg2 != -1) {
            this.finish();
        }
    }

    public void onBackPressed() {
        if(this.a == null || !this.a.getUpdate().isForceUpdate()) {
            this.setResult(-1);
            super.onBackPressed();
        }
        else {
            this.setResult(0);
        }
    }

    public void onClick(View arg7) {
        int v7 = arg7.getId();
        if(v7 == 2131296392) {
            goto label_49;
        }

        int v3 = 2131296449;
        if(v7 != 2131296444 && v7 != v3) {
            return;
        }

        try {
            b.c().a(new m("TRACKER_VIEW_ACTION").a("Download Clicked", Integer.valueOf(166)));
            goto label_19;
        }
        catch(Exception ) {
        label_19:
            if(this.a.getUpdate().isFromMarket()) {
                try {
                    Intent v7_2 = new Intent("android.intent.action.VIEW");
                    v7_2.setData(Uri.parse("bazaar://details?id=ir.hotgram.mobile.android"));
                    v7_2.setPackage("com.farsitel.bazaar");
                    this.startActivity(v7_2);
                    return;
                }
                catch(Exception v7_1) {
                    goto label_34;
                }
            }

            if(android.support.v4.content.a.b(((Context)this), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                android.support.v4.app.a.a(((Activity)this), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            }

            this.findViewById(v3).setVisibility(8);
            try {
                this.a();
            }
            catch(Exception v7_1) {
            label_34:
                v7_1.printStackTrace();
            }

            return;
        }

        try {
        label_49:
            if(this.e != null) {
                this.e.remove(new long[]{this.d});
            }

            if(this.a != null && (this.a.getUpdate().isForceUpdate())) {
                this.setResult(0);
                return;
            }

            this.setResult(-1);
            this.finish();
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    protected void onCreate(Bundle arg5) {
        super.onCreate(arg5);
        this.setContentView(2131492912);
        try {
            b.c().a(new m("TRACKER_VIEW_ACTION").a("UPDATE ACTIVITY SHOWN", Integer.valueOf(166)));
            goto label_12;
        }
        catch(Exception ) {
        label_12:
            String v5 = this.getIntent().getStringExtra("EXTRA_UPDATE_MODEL");
            if(!TextUtils.isEmpty(((CharSequence)v5))) {
                this.a = new f().a(v5, SettingAndUpdate.class);
            }

            this.c = this.findViewById(2131296451);
            this.findViewById(2131296392).setOnClickListener(((View$OnClickListener)this));
            View v5_1 = this.findViewById(2131296449);
            View v0 = this.findViewById(2131296444);
            ((TextView)v5_1).setOnClickListener(((View$OnClickListener)this));
            ((TextView)v0).setOnClickListener(((View$OnClickListener)this));
            this.b = this.findViewById(2131296674);
            if(this.a.getUpdate().isFromMarket()) {
                ((TextView)v5_1).setText(this.getString(2131626752));
            }

            if(this.a.getUpdate().isDownloadBoth()) {
                ((TextView)v5_1).setText(this.getString(2131626751));
                ((TextView)v0).setVisibility(0);
            }

            int v5_2 = 0;
            try {
                while(true) {
                    if(v5_2 >= this.a.getUpdate().getChangeList().size()) {
                        goto label_75;
                    }

                    v0 = this.getLayoutInflater().inflate(2131493114, null);
                    ((TextView)v0).setText(this.a.getUpdate().getChangeList().get(v5_2));
                    ((TextView)v0).setTextColor(Color.parseColor("#000000"));
                    this.b.addView(v0);
                    ++v5_2;
                }
            }
            catch(Exception ) {
                try {
                label_75:
                    if(this.a.getUpdate().getDownloadLink() != null && !TextUtils.isEmpty(this.a.getUpdate().getDownloadLink())) {
                        this.e = this.getSystemService("download");
                        this.f = new DownloadManager$Request(Uri.parse(this.a.getUpdate().getDownloadLink()));
                        return;
                    }

                    this.setResult(-1);
                    this.finish();
                }
                catch(Exception ) {
                    this.setResult(0);
                    this.finish();
                }

                return;
            }
        }
    }

    public void onRequestPermissionsResult(int arg1, String[] arg2, int[] arg3) {
        if(arg1 != 1) {
            return;
        }

        if(arg3.length > 0 && arg3[0] == 0) {
            try {
                this.a();
            }
            catch(Exception v1) {
                v1.printStackTrace();
            }
        }
        else {
            this.setResult(0);
            this.finish();
        }
    }
}

