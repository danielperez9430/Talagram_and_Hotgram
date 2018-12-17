package net.hockeyapp.android.d;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.Serializable;
import java.util.ArrayList;
import net.hockeyapp.android.FeedbackActivity;
import net.hockeyapp.android.b;
import net.hockeyapp.android.c.c;
import net.hockeyapp.android.e.k;
import net.hockeyapp.android.f$d;

@SuppressLint(value={"StaticFieldLeak"}) public class g extends AsyncTask {
    private Context a;
    private String b;
    private Handler c;
    private String d;
    private String e;

    public g(Context arg1, String arg2, Handler arg3, String arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = null;
    }

    private static void a(Context arg11, String arg12) {
        Class v0 = b.a() != null ? b.a().a() : null;
        if(v0 == null) {
            v0 = FeedbackActivity.class;
        }

        int v9 = arg11.getResources().getIdentifier("ic_menu_refresh", "drawable", "android");
        Intent v1 = new Intent();
        v1.setFlags(805306368);
        v1.setClass(arg11, v0);
        v1.putExtra("url", arg12);
        k.a(arg11, 2, k.a(arg11, PendingIntent.getActivity(arg11, 0, v1, 1073741824), arg11.getString(d.hockeyapp_feedback_notification_title), arg11.getString(d.hockeyapp_feedback_new_answer_notification_message), v9, "net.hockeyapp.android.NOTIFICATION"), "net.hockeyapp.android.NOTIFICATION", arg11.getString(d.hockeyapp_feedback_notification_channel));
    }

    private void a(ArrayList arg7) {
        Object v7 = arg7.get(arg7.size() - 1);
        int v0 = ((c)v7).c();
        boolean v3 = false;
        SharedPreferences v1 = this.a.getSharedPreferences("net.hockeyapp.android.feedback", 0);
        if(this.d.equals("send")) {
            v1.edit().putInt("idLastMessageSend", v0).putInt("idLastMessageProcessed", v0).apply();
        }
        else if(this.d.equals("fetch")) {
            int v2 = v1.getInt("idLastMessageSend", -1);
            int v4 = v1.getInt("idLastMessageProcessed", -1);
            if(v0 != v2 && v0 != v4) {
                v1.edit().putInt("idLastMessageProcessed", v0).apply();
                net.hockeyapp.android.c v0_1 = b.a();
                if(v0_1 != null) {
                    v3 = v0_1.a(((c)v7));
                }

                if(v3) {
                    return;
                }

                if(this.e == null) {
                    return;
                }

                g.a(this.a, this.e);
            }
        }
    }

    protected net.hockeyapp.android.c.d a(Void[] arg3) {
        if(this.a != null && this.b != null) {
            net.hockeyapp.android.c.d v3 = net.hockeyapp.android.e.d.a().a(this.b);
            if(v3 != null && v3.b() != null) {
                ArrayList v0 = v3.b().a();
                if(v0 != null && !v0.isEmpty()) {
                    this.a(v0);
                }
            }

            return v3;
        }

        return null;
    }

    protected void a(net.hockeyapp.android.c.d arg4) {
        if(arg4 != null && this.c != null) {
            Message v0 = new Message();
            Bundle v1 = new Bundle();
            v1.putSerializable("parse_feedback_response", ((Serializable)arg4));
            v0.setData(v1);
            this.c.sendMessage(v0);
        }
    }

    protected Object doInBackground(Object[] arg1) {
        return this.a(((Void[])arg1));
    }

    protected void onPostExecute(Object arg1) {
        this.a(((net.hockeyapp.android.c.d)arg1));
    }
}

