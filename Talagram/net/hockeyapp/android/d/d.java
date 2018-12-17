package net.hockeyapp.android.d;

import android.annotation.SuppressLint;
import android.app.AlertDialog$Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build$VERSION;
import android.os.StrictMode$VmPolicy$Builder;
import android.os.StrictMode$VmPolicy;
import android.os.StrictMode;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import net.hockeyapp.android.b.a;
import net.hockeyapp.android.e.e;

@SuppressLint(value={"StaticFieldLeak"}) public class d extends AsyncTask {
    protected Context a;
    protected a b;
    protected String c;
    protected String d;
    protected File e;
    protected ProgressDialog f;
    private String g;

    public d(Context arg3, String arg4, a arg5) {
        super();
        this.a = arg3;
        this.c = arg4;
        this.d = UUID.randomUUID() + ".apk";
        this.e = new File(arg3.getExternalFilesDir(null), "Download");
        this.b = arg5;
        this.g = null;
    }

    protected Long a(Void[] arg13) {
        Long v0_2;
        Long v13_4;
        int v13_1;
        FileOutputStream v2_2;
        BufferedInputStream v5;
        File v4_1;
        int v3;
        FileOutputStream v13 = null;
        long v0 = 0;
        try {
            URLConnection v2_1 = this.a(new URL(this.a()), 6);
            v2_1.connect();
            v3 = v2_1.getContentLength();
            String v4 = v2_1.getContentType();
            if(v4 != null && (v4.contains("text"))) {
                this.g = "The requested download does not appear to be a file.";
                return Long.valueOf(v0);
            }

            if(!this.e.mkdirs()) {
                if(this.e.exists()) {
                }
                else {
                    StringBuilder v3_1 = new StringBuilder();
                    v3_1.append("Could not create the dir(s):");
                    v3_1.append(this.e.getAbsolutePath());
                    throw new IOException(v3_1.toString());
                }
            }

            v4_1 = new File(this.e, this.d);
            v5 = new BufferedInputStream(v2_1.getInputStream());
        }
        catch(Throwable v0_1) {
            v5 = ((BufferedInputStream)v13);
            goto label_102;
        }
        catch(IOException v2) {
            v5 = ((BufferedInputStream)v13);
            goto label_87;
        }

        try {
            v2_2 = new FileOutputStream(v4_1);
            v13_1 = 1024;
            goto label_46;
        }
        catch(Throwable v0_1) {
        }
        catch(IOException v2) {
            goto label_87;
            try {
            label_46:
                byte[] v13_3 = new byte[v13_1];
                long v6 = v0;
                while(true) {
                    int v4_2 = ((InputStream)v5).read(v13_3);
                    if(v4_2 == -1) {
                        break;
                    }

                    v6 += ((long)v4_2);
                    this.publishProgress(new Integer[]{Integer.valueOf(Math.round((((float)v6)) * 100f / (((float)v3))))});
                    ((OutputStream)v2_2).write(v13_3, 0, v4_2);
                }

                ((OutputStream)v2_2).flush();
                v13_4 = Long.valueOf(v6);
            }
            catch(Throwable v0_1) {
                goto label_73;
            }
            catch(IOException v13_2) {
                goto label_76;
            }

            try {
                ((OutputStream)v2_2).close();
                ((InputStream)v5).close();
                return v13_4;
            }
            catch(IOException ) {
                return v13_4;
            }

        label_76:
            FileOutputStream v11 = v2_2;
            v2 = v13_2;
            v13 = v11;
            try {
            label_87:
                e.b("Failed to download " + this.c, ((Throwable)v2));
                v0_2 = Long.valueOf(v0);
                if(v13 == null) {
                    goto label_98;
                }
            }
            catch(Throwable v0_1) {
                goto label_102;
            }
        }

        try {
            ((OutputStream)v13).close();
        label_98:
            if(v5 != null) {
                ((InputStream)v5).close();
            }

            return v0_2;
        }
        catch(IOException ) {
            return v0_2;
        }

    label_73:
        v13 = v2_2;
    label_102:
        if(v13 != null) {
            try {
                ((OutputStream)v13).close();
            label_104:
                if(v5 != null) {
                    ((InputStream)v5).close();
                }

                goto label_106;
            }
            catch(IOException ) {
            label_106:
                throw v0_1;
            }
        }

        goto label_104;
    }

    protected URLConnection a(URL arg4, int arg5) {
        URLConnection v0 = arg4.openConnection();
        this.a(((HttpURLConnection)v0));
        int v1 = ((HttpURLConnection)v0).getResponseCode();
        if(v1 == 301 || v1 == 302 || v1 == 303) {
            if(arg5 == 0) {
                return v0;
            }
            else {
                URL v1_1 = new URL(((HttpURLConnection)v0).getHeaderField("Location"));
                if(!arg4.getProtocol().equals(v1_1.getProtocol())) {
                    ((HttpURLConnection)v0).disconnect();
                    return this.a(v1_1, arg5 - 1);
                }
            }
        }

        return v0;
    }

    protected String a() {
        return this.c + "&type=apk";
    }

    protected void a(HttpURLConnection arg3) {
        arg3.addRequestProperty("User-Agent", "HockeySDK/Android 5.1.0");
        arg3.setInstanceFollowRedirects(true);
    }

    protected void a(Long arg5) {
        String v0_1;
        if(this.f != null) {
            try {
                this.f.dismiss();
                goto label_4;
            }
            catch(Exception ) {
            label_4:
                if(arg5.longValue() > 0) {
                    this.b.a(this);
                    Intent v5 = new Intent("android.intent.action.INSTALL_PACKAGE");
                    v5.setDataAndType(Uri.fromFile(new File(this.e, this.d)), "application/vnd.android.package-archive");
                    v5.setFlags(268435456);
                    StrictMode$VmPolicy v0 = null;
                    if(Build$VERSION.SDK_INT >= 24) {
                        v0 = StrictMode.getVmPolicy();
                        StrictMode.setVmPolicy(new StrictMode$VmPolicy$Builder().penaltyLog().build());
                    }

                    this.a.startActivity(v5);
                    if(v0 == null) {
                        return;
                    }

                    StrictMode.setVmPolicy(v0);
                }
                else {
                    try {
                        AlertDialog$Builder v5_1 = new AlertDialog$Builder(this.a);
                        v5_1.setTitle(net.hockeyapp.android.f$d.hockeyapp_download_failed_dialog_title);
                        if(this.g == null) {
                            v0_1 = this.a.getString(net.hockeyapp.android.f$d.hockeyapp_download_failed_dialog_message);
                        }
                        else {
                            goto label_47;
                        }

                    label_48:
                        v5_1.setMessage(((CharSequence)v0_1));
                        v5_1.setNegativeButton(net.hockeyapp.android.f$d.hockeyapp_download_failed_dialog_negative_button, new DialogInterface$OnClickListener() {
                            public void onClick(DialogInterface arg2, int arg3) {
                                this.a.b.a(this.a, Boolean.valueOf(false));
                            }
                        });
                        v5_1.setPositiveButton(net.hockeyapp.android.f$d.hockeyapp_download_failed_dialog_positive_button, new DialogInterface$OnClickListener() {
                            public void onClick(DialogInterface arg2, int arg3) {
                                this.a.b.a(this.a, Boolean.valueOf(true));
                            }
                        });
                        v5_1.create().show();
                        return;
                    label_47:
                        v0_1 = this.g;
                        goto label_48;
                    }
                    catch(Exception ) {
                        return;
                    }
                }

                return;
            }
        }

        goto label_4;
    }

    protected void a(Integer[] arg5) {
        try {
            if(this.f == null) {
                this.f = new ProgressDialog(this.a);
                this.f.setProgressStyle(1);
                this.f.setMessage(this.a.getString(net.hockeyapp.android.f$d.hockeyapp_update_loading));
                this.f.setCancelable(false);
                this.f.show();
            }

            this.f.setProgress(arg5[0].intValue());
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    protected Object doInBackground(Object[] arg1) {
        return this.a(((Void[])arg1));
    }

    protected void onPostExecute(Object arg1) {
        this.a(((Long)arg1));
    }

    protected void onProgressUpdate(Object[] arg1) {
        this.a(((Integer[])arg1));
    }
}

