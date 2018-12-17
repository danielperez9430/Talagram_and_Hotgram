package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog$Builder;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap$CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.hockeyapp.android.e.a;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.e.g;
import net.hockeyapp.android.views.c;

public class PaintActivity extends Activity {
    private c a;
    private Uri b;

    public PaintActivity() {
        super();
    }

    static Uri a(PaintActivity arg0) {
        return arg0.b;
    }

    private String a(Uri arg9, String arg10) {
        Cursor v9 = this.getApplicationContext().getContentResolver().query(arg9, new String[]{"_data"}, null, null, null);
        String v0 = null;
        if(v9 != null) {
            try {
                if(v9.moveToFirst()) {
                    v0 = v9.getString(0);
                }
            }
            catch(Throwable v10) {
                v9.close();
                throw v10;
            }

            v9.close();
        }

        if(v0 == null) {
        }
        else {
            arg10 = new File(v0).getName();
        }

        return arg10;
    }

    static String a(PaintActivity arg0, Uri arg1, String arg2) {
        return arg0.a(arg1, arg2);
    }

    private void a() {
        this.a = new c(((Context)this), this.b, this.getResources().getDisplayMetrics().widthPixels, this.getResources().getDisplayMetrics().heightPixels);
        LinearLayout v0 = new LinearLayout(((Context)this));
        v0.setLayoutParams(new LinearLayout$LayoutParams(-1, -1));
        v0.setGravity(17);
        v0.setOrientation(1);
        LinearLayout v4 = new LinearLayout(((Context)this));
        v4.setLayoutParams(new LinearLayout$LayoutParams(-1, -1));
        v4.setGravity(17);
        v4.setOrientation(0);
        v0.addView(((View)v4));
        v4.addView(this.a);
        this.setContentView(((View)v0));
        Toast.makeText(((Context)this), d.hockeyapp_paint_indicator_toast, 1).show();
    }

    @SuppressLint(value={"StaticFieldLeak"}) private void b() {
        this.a.setDrawingCacheEnabled(true);
        a.a(new AsyncTask(this.a.getDrawingCache()) {
            File a;

            protected Boolean a(Void[] arg8) {
                File v8 = new File(this.c.getCacheDir(), "HockeyApp");
                if(!v8.exists() && !v8.mkdir()) {
                    return Boolean.valueOf(false);
                }

                String v0 = PaintActivity.a(this.c, PaintActivity.a(this.c), PaintActivity.a(this.c).getLastPathSegment());
                StringBuilder v2 = new StringBuilder();
                v2.append(v0);
                v2.append(".jpg");
                this.a = new File(v8, v2.toString());
                int v3;
                for(v3 = 1; this.a.exists(); ++v3) {
                    StringBuilder v5 = new StringBuilder();
                    v5.append(v0);
                    v5.append("_");
                    v5.append(v3);
                    v5.append(".jpg");
                    this.a = new File(v8, v5.toString());
                }

                try {
                    FileOutputStream v8_2 = new FileOutputStream(this.a);
                    this.b.compress(Bitmap$CompressFormat.JPEG, 100, ((OutputStream)v8_2));
                    v8_2.close();
                }
                catch(IOException v8_1) {
                    e.b("Could not save image.", ((Throwable)v8_1));
                    return Boolean.valueOf(false);
                }

                return Boolean.valueOf(true);
            }

            protected void a(Boolean arg3) {
                if(arg3.booleanValue()) {
                    Intent v3 = new Intent();
                    v3.putExtra("imageUri", Uri.fromFile(this.a));
                    int v1 = -1;
                    if(this.c.getParent() == null) {
                        this.c.setResult(v1, v3);
                    }
                    else {
                        this.c.getParent().setResult(v1, v3);
                    }
                }
                else if(this.c.getParent() == null) {
                    this.c.setResult(0);
                }
                else {
                    this.c.getParent().setResult(0);
                }

                this.c.finish();
            }

            protected Object doInBackground(Object[] arg1) {
                return this.a(((Void[])arg1));
            }

            protected void onPostExecute(Object arg1) {
                this.a(((Boolean)arg1));
            }
        });
    }

    static void b(PaintActivity arg0) {
        arg0.a();
    }

    static void c(PaintActivity arg0) {
        arg0.b();
    }

    @SuppressLint(value={"StaticFieldLeak"}) public void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        arg2 = this.getIntent().getExtras();
        if(arg2 != null) {
            if(arg2.getParcelable("imageUri") == null) {
            }
            else {
                this.b = arg2.getParcelable("imageUri");
                a.a(new AsyncTask() {
                    protected Integer a(Void[] arg2) {
                        return Integer.valueOf(g.a(this.a, PaintActivity.a(this.a)));
                    }

                    protected void a(Integer arg3) {
                        this.a.setRequestedOrientation(arg3.intValue());
                        int v0 = this.a.getResources().getDisplayMetrics().widthPixels > this.a.getResources().getDisplayMetrics().heightPixels ? 0 : 1;
                        if(v0 != arg3.intValue()) {
                            e.a("Image loading skipped because activity will be destroyed for orientation change.");
                            return;
                        }

                        PaintActivity.b(this.a);
                    }

                    protected Object doInBackground(Object[] arg1) {
                        return this.a(((Void[])arg1));
                    }

                    protected void onPostExecute(Object arg1) {
                        this.a(((Integer)arg1));
                    }
                });
                return;
            }
        }

        e.c("Can\'t set up PaintActivity as image extra was not provided!");
    }

    public boolean onCreateOptionsMenu(Menu arg5) {
        super.onCreateOptionsMenu(arg5);
        arg5.add(0, 1, 0, this.getString(d.hockeyapp_paint_menu_save));
        arg5.add(0, 2, 0, this.getString(d.hockeyapp_paint_menu_undo));
        arg5.add(0, 3, 0, this.getString(d.hockeyapp_paint_menu_clear));
        return 1;
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(arg2 == 4 && !this.a.c()) {
            net.hockeyapp.android.PaintActivity$2 v2 = new DialogInterface$OnClickListener() {
                public void onClick(DialogInterface arg1, int arg2) {
                    switch(arg2) {
                        case -2: {
                            this.a.finish();
                            break;
                        }
                        case -1: {
                            PaintActivity.c(this.a);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            };
            new AlertDialog$Builder(((Context)this)).setMessage(d.hockeyapp_paint_dialog_message).setPositiveButton(d.hockeyapp_paint_dialog_positive_button, ((DialogInterface$OnClickListener)v2)).setNegativeButton(d.hockeyapp_paint_dialog_negative_button, ((DialogInterface$OnClickListener)v2)).setNeutralButton(d.hockeyapp_paint_dialog_neutral_button, ((DialogInterface$OnClickListener)v2)).show();
            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    public boolean onOptionsItemSelected(MenuItem arg3) {
        switch(arg3.getItemId()) {
            case 1: {
                goto label_11;
            }
            case 2: {
                goto label_8;
            }
            case 3: {
                goto label_5;
            }
        }

        return super.onOptionsItemSelected(arg3);
    label_5:
        this.a.a();
        return 1;
    label_8:
        this.a.b();
        return 1;
    label_11:
        this.b();
        return 1;
    }

    public boolean onPrepareOptionsMenu(Menu arg1) {
        super.onPrepareOptionsMenu(arg1);
        return 1;
    }
}

