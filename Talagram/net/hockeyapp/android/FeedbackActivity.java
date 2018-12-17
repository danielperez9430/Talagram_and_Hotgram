package net.hockeyapp.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog$Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View$OnClickListener;
import android.view.View$OnFocusChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import net.hockeyapp.android.d.g;
import net.hockeyapp.android.d.h;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.e.i;
import net.hockeyapp.android.e.k;
import net.hockeyapp.android.views.AttachmentListView;

public class FeedbackActivity extends Activity implements View$OnClickListener, View$OnFocusChangeListener {
    class a extends Handler {
        private final WeakReference a;

        a(FeedbackActivity arg2) {
            super();
            this.a = new WeakReference(arg2);
        }

        public void handleMessage(Message arg8) {
            int v8_2;
            Object v0 = this.a.get();
            if(v0 == null) {
                return;
            }

            boolean v1 = false;
            if(arg8 == null || arg8.getData() == null) {
            label_53:
                v8_2 = d.hockeyapp_feedback_send_generic_error;
            }
            else {
                Bundle v8 = arg8.getData();
                String v2 = v8.getString("feedback_response");
                String v3 = v8.getString("feedback_status");
                String v8_1 = v8.getString("request_type");
                if("send".equals(v8_1)) {
                    if(v2 == null) {
                        goto label_53;
                    }
                    else if(Integer.parseInt(v3) != 201) {
                        goto label_53;
                    }
                }

                if(!"fetch".equals(v8_1) || v3 == null) {
                label_38:
                    if(v2 == null) {
                        goto label_51;
                    }

                    FeedbackActivity.a(((FeedbackActivity)v0), v2, v8_1);
                    if(!"send".equals(v8_1)) {
                        goto label_35;
                    }

                    FeedbackActivity.d(((FeedbackActivity)v0)).removeAll(FeedbackActivity.c(((FeedbackActivity)v0)).getAttachments());
                    Toast.makeText(((Context)v0), d.hockeyapp_feedback_sent_toast, 1).show();
                }
                else {
                    if(Integer.parseInt(v3) != 404 && Integer.parseInt(v3) != 422) {
                        goto label_38;
                    }

                    FeedbackActivity.b(((FeedbackActivity)v0));
                }

            label_35:
                v8_2 = 0;
                v1 = true;
                goto label_54;
            label_51:
                v8_2 = d.hockeyapp_feedback_send_network_error;
            }

        label_54:
            if(!v1) {
                FeedbackActivity.a(((FeedbackActivity)v0), v8_2);
            }

            ((FeedbackActivity)v0).c(v1);
        }
    }

    class b extends Handler {
        private final WeakReference a;

        b(FeedbackActivity arg2) {
            super();
            this.a = new WeakReference(arg2);
        }

        @SuppressLint(value={"StaticFieldLeak"}) public void handleMessage(Message arg6) {
            Object v0 = this.a.get();
            if(v0 == null) {
                return;
            }

            int v2 = 0;
            if(arg6 != null && arg6.getData() != null) {
                Serializable v6 = arg6.getData().getSerializable("parse_feedback_response");
                if(v6 != null && (((net.hockeyapp.android.c.d)v6).a().equalsIgnoreCase("success"))) {
                    if(((net.hockeyapp.android.c.d)v6).c() != null) {
                        FeedbackActivity.a(((FeedbackActivity)v0), ((net.hockeyapp.android.c.d)v6).c());
                        net.hockeyapp.android.e.a.a(new AsyncTask(((FeedbackActivity)v0), ((net.hockeyapp.android.c.d)v6)) {
                            protected Object a(Void[] arg3) {
                                i.a().a(this.a, this.b.c());
                                return null;
                            }

                            protected Object doInBackground(Object[] arg1) {
                                return this.a(((Void[])arg1));
                            }
                        });
                        FeedbackActivity.a(((FeedbackActivity)v0), ((net.hockeyapp.android.c.d)v6));
                        FeedbackActivity.a(((FeedbackActivity)v0), false);
                    }

                    v2 = 1;
                }
            }

            if(v2 == 0) {
                FeedbackActivity.a(((FeedbackActivity)v0), d.hockeyapp_dialog_error_message);
            }

            ((FeedbackActivity)v0).a(true);
        }
    }

    private String a;
    private String b;
    private String c;
    private String d;
    private List e;
    private Context f;
    private TextView g;
    private EditText h;
    private EditText i;
    private EditText j;
    private EditText k;
    private Button l;
    private ListView m;
    private AttachmentListView n;
    private h o;
    private Handler p;
    private g q;
    private Handler r;
    private String s;
    private net.hockeyapp.android.a.a t;
    private boolean u;
    private boolean v;
    private boolean w;
    private String x;

    public FeedbackActivity() {
        super();
        this.e = new ArrayList();
    }

    static Context a(FeedbackActivity arg0) {
        return arg0.f;
    }

    static String a(FeedbackActivity arg0, String arg1) {
        arg0.x = arg1;
        return arg1;
    }

    private void a(View arg3) {
        this.getSystemService("input_method").showSoftInput(arg3, 1);
    }

    private void a(EditText arg2, int arg3) {
        arg2.setError(this.getString(arg3));
        new Handler(Looper.getMainLooper()).post(new Runnable(arg2) {
            public void run() {
                this.a.requestFocus();
            }
        });
        this.a(true);
    }

    public void a(boolean arg2) {
        if(this.l != null) {
            this.l.setEnabled(arg2);
        }
    }

    private void a(String arg3, String arg4) {
        this.q = new g(((Context)this), arg3, this.r, arg4);
    }

    private void a(String arg15, String arg16, String arg17, String arg18, String arg19, String arg20, List arg21, String arg22, Handler arg23, boolean arg24) {
        this.o = new h(this.f, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24);
        net.hockeyapp.android.e.a.a(this.o);
    }

    static void a(FeedbackActivity arg0, int arg1) {
        arg0.b(arg1);
    }

    static void a(FeedbackActivity arg0, String arg1, String arg2) {
        arg0.b(arg1, arg2);
    }

    static void a(FeedbackActivity arg0, net.hockeyapp.android.c.d arg1) {
        arg0.a(arg1);
    }

    @SuppressLint(value={"SimpleDateFormat"}) private void a(net.hockeyapp.android.c.d arg7) {
        this.b(true);
        if(arg7 != null && arg7.b() != null && arg7.b().a() != null && arg7.b().a().size() > 0) {
            ArrayList v7 = arg7.b().a();
            Collections.reverse(((List)v7));
            try {
                SimpleDateFormat v1 = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'", Locale.US);
                ((DateFormat)v1).setTimeZone(TimeZone.getTimeZone("UTC"));
                this.g.setText(String.format(this.getString(d.hockeyapp_feedback_last_updated_text), DateFormat.getDateTimeInstance(3, 3).format(((DateFormat)v1).parse(v7.get(0).b()))));
                this.g.setContentDescription(this.g.getText());
                this.g.setVisibility(0);
            }
            catch(ParseException v0) {
                e.b("Failed to parse feedback", ((Throwable)v0));
            }

            if(this.t == null) {
                this.t = new net.hockeyapp.android.a.a(this.f, v7);
            }
            else {
                this.t.a();
                Iterator v7_1 = v7.iterator();
                while(v7_1.hasNext()) {
                    this.t.a(v7_1.next());
                }

                this.t.notifyDataSetChanged();
            }

            this.m.setAdapter(this.t);
        }
    }

    private boolean a(int arg4) {
        Intent v4;
        int v0 = 2;
        if(arg4 == v0) {
            v4 = new Intent();
            v4.setType("*/*");
            v4.setAction("android.intent.action.GET_CONTENT");
            this.startActivityForResult(Intent.createChooser(v4, this.getString(d.hockeyapp_feedback_select_file)), v0);
            return 1;
        }

        if(arg4 == 1) {
            v4 = new Intent();
            v4.setType("image/*");
            v4.setAction("android.intent.action.GET_CONTENT");
            this.startActivityForResult(Intent.createChooser(v4, this.getString(d.hockeyapp_feedback_select_picture)), 1);
            return 1;
        }

        return 0;
    }

    static boolean a(FeedbackActivity arg0, boolean arg1) {
        arg0.u = arg1;
        return arg1;
    }

    @SuppressLint(value={"InflateParams"}) public View a() {
        return this.getLayoutInflater().inflate(c.hockeyapp_activity_feedback, null);
    }

    private void b(int arg3) {
        new AlertDialog$Builder(((Context)this)).setTitle(d.hockeyapp_dialog_error_title).setMessage(arg3).setCancelable(false).setPositiveButton(d.hockeyapp_dialog_positive_button, null).create().show();
    }

    private void b(String arg1, String arg2) {
        this.a(arg1, arg2);
        net.hockeyapp.android.e.a.a(this.q);
    }

    protected void b(boolean arg6) {
        EditText v6_1;
        View v6;
        View v0 = this.findViewById(net.hockeyapp.android.f$b.wrapper_feedback_scroll);
        View v1 = this.findViewById(net.hockeyapp.android.f$b.wrapper_messages);
        this.m = this.findViewById(net.hockeyapp.android.f$b.list_feedback_messages);
        this.n = this.findViewById(net.hockeyapp.android.f$b.wrapper_attachments);
        int v2 = 8;
        if(arg6) {
            ((LinearLayout)v1).setVisibility(0);
            ((ScrollView)v0).setVisibility(v2);
            this.g = this.findViewById(net.hockeyapp.android.f$b.label_last_updated);
            this.g.setVisibility(4);
            v6 = this.findViewById(net.hockeyapp.android.f$b.button_add_response);
            ((Button)v6).setOnClickListener(((View$OnClickListener)this));
            ((Button)v6).setOnFocusChangeListener(((View$OnFocusChangeListener)this));
            v6 = this.findViewById(net.hockeyapp.android.f$b.button_refresh);
            ((Button)v6).setOnClickListener(((View$OnClickListener)this));
        }
        else {
            ((LinearLayout)v1).setVisibility(v2);
            ((ScrollView)v0).setVisibility(0);
            this.h = this.findViewById(net.hockeyapp.android.f$b.input_name);
            this.h.setOnFocusChangeListener(((View$OnFocusChangeListener)this));
            this.i = this.findViewById(net.hockeyapp.android.f$b.input_email);
            this.i.setOnFocusChangeListener(((View$OnFocusChangeListener)this));
            this.j = this.findViewById(net.hockeyapp.android.f$b.input_subject);
            this.j.setOnFocusChangeListener(((View$OnFocusChangeListener)this));
            this.k = this.findViewById(net.hockeyapp.android.f$b.input_message);
            this.k.setOnFocusChangeListener(((View$OnFocusChangeListener)this));
            this.c();
            if(!this.w) {
                this.h.setText(this.a);
                this.i.setText(this.b);
                this.j.setText(this.c);
                if(TextUtils.isEmpty(this.a)) {
                    v6_1 = this.h;
                }
                else if(TextUtils.isEmpty(this.b)) {
                    v6_1 = this.i;
                }
                else if(TextUtils.isEmpty(this.c)) {
                    v6_1 = this.j;
                }
                else {
                    v6_1 = this.k;
                }

                v6_1.requestFocus();
                this.w = true;
            }

            v6_1 = this.h;
            int v1_1 = net.hockeyapp.android.b.b() == net.hockeyapp.android.c.e.a ? 8 : 0;
            v6_1.setVisibility(v1_1);
            v6_1 = this.i;
            v1_1 = net.hockeyapp.android.b.c() == net.hockeyapp.android.c.e.a ? 8 : 0;
            v6_1.setVisibility(v1_1);
            this.k.setText("");
            if((this.v) && !this.u) {
                goto label_112;
            }
            else if(this.x != null) {
                this.j.setVisibility(v2);
            }
            else {
            label_112:
                this.j.setVisibility(0);
            }

            this.n.removeAllViews();
            Iterator v6_2 = this.e.iterator();
            while(v6_2.hasNext()) {
                this.n.addView(new net.hockeyapp.android.views.a(((Context)this), this.n, v6_2.next(), true));
            }

            v6 = this.findViewById(net.hockeyapp.android.f$b.button_attachment);
            ((Button)v6).setOnClickListener(((View$OnClickListener)this));
            ((Button)v6).setOnFocusChangeListener(((View$OnFocusChangeListener)this));
            this.registerForContextMenu(v6);
            this.l = this.findViewById(net.hockeyapp.android.f$b.button_send);
            this.l.setOnClickListener(((View$OnClickListener)this));
        }

        ((Button)v6).setOnFocusChangeListener(((View$OnFocusChangeListener)this));
    }

    private void b() {
        Object v0 = this.getLastNonConfigurationInstance();
        if(v0 != null && ((v0 instanceof h))) {
            this.o = ((h)v0);
            this.o.a(this.p);
        }
    }

    static void b(FeedbackActivity arg0) {
        arg0.h();
    }

    static AttachmentListView c(FeedbackActivity arg0) {
        return arg0.n;
    }

    private void c() {
        if(net.hockeyapp.android.b.b() == net.hockeyapp.android.c.e.c) {
            this.h.setHint(this.getString(d.hockeyapp_feedback_name_hint_required));
        }

        if(net.hockeyapp.android.b.c() == net.hockeyapp.android.c.e.c) {
            this.i.setHint(this.getString(d.hockeyapp_feedback_email_hint_required));
        }

        this.j.setHint(this.getString(d.hockeyapp_feedback_subject_hint_required));
        this.k.setHint(this.getString(d.hockeyapp_feedback_message_hint_required));
    }

    protected void c(boolean arg1) {
    }

    static List d(FeedbackActivity arg0) {
        return arg0.e;
    }

    private void d() {
        if(this.x == null || (this.u)) {
            this.b(false);
        }
        else {
            this.b(true);
            this.a(this.s, null, null, null, null, null, null, this.x, this.p, true);
        }
    }

    private void e() {
        if(this.k != null) {
            this.getSystemService("input_method").hideSoftInputFromWindow(this.k.getWindowToken(), 0);
        }
    }

    private void f() {
        this.p = new a(this);
    }

    private void g() {
        this.r = new b(this);
    }

    @SuppressLint(value={"StaticFieldLeak"}) private void h() {
        this.x = null;
        net.hockeyapp.android.e.a.a(new AsyncTask() {
            protected Object a(Void[] arg4) {
                i.a().a(this.a, null);
                this.a.getSharedPreferences("net.hockeyapp.android.feedback", 0).edit().remove("idLastMessageSend").remove("idLastMessageProcessed").apply();
                return null;
            }

            protected Object doInBackground(Object[] arg1) {
                return this.a(((Void[])arg1));
            }
        });
        this.b(false);
    }

    @SuppressLint(value={"StaticFieldLeak"}) private void i() {
        int v1_1;
        EditText v0;
        if(!k.a(((Context)this))) {
            Toast.makeText(((Context)this), d.hockeyapp_error_no_network_message, 1).show();
            return;
        }

        this.a(false);
        String v1 = !this.v || (this.u) ? this.x : null;
        String v10 = v1;
        String v4 = this.h.getText().toString().trim();
        String v5 = this.i.getText().toString().trim();
        String v6 = this.j.getText().toString().trim();
        String v7 = this.k.getText().toString().trim();
        if(TextUtils.isEmpty(((CharSequence)v6))) {
            this.j.setVisibility(0);
            v0 = this.j;
            v1_1 = d.hockeyapp_feedback_validate_subject_error;
            goto label_39;
        }
        else {
            if(net.hockeyapp.android.b.b() == net.hockeyapp.android.c.e.c && (TextUtils.isEmpty(((CharSequence)v4)))) {
                v0 = this.h;
                v1_1 = d.hockeyapp_feedback_validate_name_error;
                goto label_39;
            }

            if(net.hockeyapp.android.b.c() == net.hockeyapp.android.c.e.c && (TextUtils.isEmpty(((CharSequence)v5)))) {
                v0 = this.i;
                v1_1 = d.hockeyapp_feedback_validate_email_empty;
                goto label_39;
            }

            if(TextUtils.isEmpty(((CharSequence)v7))) {
                v0 = this.k;
                v1_1 = d.hockeyapp_feedback_validate_text_error;
                goto label_39;
            }

            if(net.hockeyapp.android.b.c() == net.hockeyapp.android.c.e.c && !k.b(v5)) {
                v0 = this.i;
                v1_1 = d.hockeyapp_feedback_validate_email_error;
            label_39:
                this.a(v0, v1_1);
                return;
            }

            net.hockeyapp.android.e.a.a(new AsyncTask(v4, v5, v6) {
                protected Object a(Void[] arg5) {
                    i.a().a(FeedbackActivity.a(this.d), this.a, this.b, this.c);
                    return null;
                }

                protected Object doInBackground(Object[] arg1) {
                    return this.a(((Void[])arg1));
                }
            });
            this.a(this.s, v4, v5, v6, v7, this.d, this.n.getAttachments(), v10, this.p, false);
            this.e();
        }
    }

    protected void onActivityResult(int arg3, int arg4, Intent arg5) {
        net.hockeyapp.android.views.a v5;
        AttachmentListView v4;
        Uri v3;
        if(arg4 != -1) {
            return;
        }

        if(arg3 == 2) {
            v3 = arg5.getData();
            if(v3 != null) {
                v4 = this.n;
                v5 = new net.hockeyapp.android.views.a(((Context)this), this.n, v3, true);
            }
            else {
                return;
            }
        }
        else {
            arg4 = 3;
            if(arg3 == 1) {
                v3 = arg5.getData();
                if(v3 != null) {
                    try {
                        arg5 = new Intent(((Context)this), PaintActivity.class);
                        arg5.putExtra("imageUri", ((Parcelable)v3));
                        this.startActivityForResult(arg5, arg4);
                    }
                    catch(ActivityNotFoundException v3_1) {
                        e.b("Paint activity not declared!", ((Throwable)v3_1));
                    }
                }
                else {
                }

                return;
            }
            else if(arg3 == arg4) {
                Parcelable v3_2 = arg5.getParcelableExtra("imageUri");
                if(v3_2 != null) {
                    v4 = this.n;
                    v5 = new net.hockeyapp.android.views.a(((Context)this), this.n, ((Uri)v3_2), true);
                }
                else {
                    return;
                }
            }
            else {
                return;
            }
        }

        v4.addView(((View)v5));
        k.a(this.n, this.getString(d.hockeyapp_feedback_attachment_added));
    }

    public void onClick(View arg13) {
        int v0 = arg13.getId();
        if(v0 == net.hockeyapp.android.f$b.button_send) {
            this.i();
        }
        else if(v0 == net.hockeyapp.android.f$b.button_attachment) {
            int v1 = 3;
            if(this.n.getChildCount() >= v1) {
                Toast.makeText(((Context)this), this.getString(d.hockeyapp_feedback_max_attachments_allowed, new Object[]{Integer.valueOf(v1)}), 0).show();
            }
            else {
                this.openContextMenu(arg13);
            }
        }
        else if(v0 == net.hockeyapp.android.f$b.button_add_response) {
            this.u = true;
            this.b(false);
        }
        else if(v0 == net.hockeyapp.android.f$b.button_refresh) {
            this.a(this.s, null, null, null, null, null, null, this.x, this.p, true);
        }
    }

    public boolean onContextItemSelected(MenuItem arg2) {
        switch(arg2.getItemId()) {
            case 1: 
            case 2: {
                goto label_4;
            }
        }

        return super.onContextItemSelected(arg2);
    label_4:
        return this.a(arg2.getItemId());
    }

    public void onCreate(Bundle arg7) {
        super.onCreate(arg7);
        this.setContentView(this.a());
        this.setTitle(d.hockeyapp_feedback_title);
        this.f = ((Context)this);
        Bundle v0 = this.getIntent().getExtras();
        if(v0 != null) {
            this.s = v0.getString("url");
            this.x = v0.getString("token");
            this.v = v0.getBoolean("forceNewThread");
            this.a = v0.getString("initialUserName");
            this.b = v0.getString("initialUserEmail");
            this.c = v0.getString("initialUserSubject");
            this.d = v0.getString("userId");
            Parcelable[] v0_1 = v0.getParcelableArray("initialAttachments");
            if(v0_1 != null) {
                this.e.clear();
                int v2 = v0_1.length;
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    this.e.add(v0_1[v3]);
                }
            }
        }

        if(arg7 != null) {
            this.w = arg7.getBoolean("feedbackViewInitialized");
            this.u = arg7.getBoolean("inSendFeedback");
            this.x = arg7.getString("token");
        }
        else {
            this.u = false;
            this.w = false;
        }

        k.a(((Context)this), 2);
        this.f();
        this.g();
        this.b();
        this.d();
    }

    public void onCreateContextMenu(ContextMenu arg2, View arg3, ContextMenu$ContextMenuInfo arg4) {
        super.onCreateContextMenu(arg2, arg3, arg4);
        arg2.add(0, 2, 0, this.getString(d.hockeyapp_feedback_attach_file));
        arg2.add(0, 1, 0, this.getString(d.hockeyapp_feedback_attach_picture));
    }

    public void onFocusChange(View arg1, boolean arg2) {
        if(arg2) {
            if((arg1 instanceof EditText)) {
                this.a(arg1);
            }
            else {
                if(!(arg1 instanceof Button) && !(arg1 instanceof ImageButton)) {
                    return;
                }

                this.e();
            }
        }
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(arg2 == 4) {
            if(this.u) {
                this.u = false;
                this.d();
            }
            else {
                this.finish();
            }

            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    protected void onRestoreInstanceState(Bundle arg7) {
        if(arg7 != null) {
            ArrayList v0 = arg7.getParcelableArrayList("attachments");
            if(v0 != null) {
                Iterator v0_1 = v0.iterator();
                while(v0_1.hasNext()) {
                    Object v1 = v0_1.next();
                    if(this.e.contains(v1)) {
                        continue;
                    }

                    this.n.addView(new net.hockeyapp.android.views.a(((Context)this), this.n, ((Uri)v1), true));
                }
            }

            this.w = arg7.getBoolean("feedbackViewInitialized");
        }

        super.onRestoreInstanceState(arg7);
    }

    public Object onRetainNonConfigurationInstance() {
        if(this.o != null) {
            this.o.a();
        }

        return this.o;
    }

    protected void onSaveInstanceState(Bundle arg3) {
        arg3.putParcelableArrayList("attachments", this.n.getAttachments());
        arg3.putBoolean("feedbackViewInitialized", this.w);
        arg3.putBoolean("inSendFeedback", this.u);
        arg3.putString("token", this.x);
        super.onSaveInstanceState(arg3);
    }

    protected void onStart() {
        super.onStart();
        if(this.o != null) {
            this.o.a(((Context)this));
        }
    }

    protected void onStop() {
        super.onStop();
        if(this.o != null) {
            this.o.a();
        }
    }
}

