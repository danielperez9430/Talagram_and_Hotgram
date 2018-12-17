package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import net.hockeyapp.android.d.f;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.e.k;

public class LoginActivity extends Activity {
    class a extends Handler {
        private final WeakReference a;

        a(Activity arg2) {
            super();
            this.a = new WeakReference(arg2);
        }

        public void handleMessage(Message arg3) {
            Object v0 = this.a.get();
            if(v0 == null) {
                return;
            }

            if(arg3.getData().getBoolean("success")) {
                ((Activity)v0).finish();
                if(d.a != null) {
                    d.a.a();
                }
            }
            else {
                Toast.makeText(((Context)v0), "Login failed. Check your credentials.", 1).show();
            }
        }
    }

    private String a;
    private String b;
    private int c;
    private f d;
    private Handler e;

    public LoginActivity() {
        super();
    }

    private void a() {
        if(this.c == 1) {
            this.findViewById(b.input_password).setVisibility(4);
        }

        View v0 = this.findViewById(b.text_headline);
        int v1 = this.c == 1 ? net.hockeyapp.android.f$d.hockeyapp_login_headline_text_email_only : net.hockeyapp.android.f$d.hockeyapp_login_headline_text;
        ((TextView)v0).setText(v1);
        this.findViewById(b.button_login).setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                LoginActivity.a(this.a);
            }
        });
    }

    static void a(LoginActivity arg0) {
        arg0.c();
    }

    public String a(String arg2) {
        try {
            return k.a(k.a(arg2.getBytes(), "MD5"));
        }
        catch(NoSuchAlgorithmException v2) {
            e.b("Failed to create MD5 hash", ((Throwable)v2));
            return "";
        }
    }

    private void b() {
        this.e = new a(((Activity)this));
    }

    private void c() {
        if(!k.a(((Context)this))) {
            Toast.makeText(((Context)this), net.hockeyapp.android.f$d.hockeyapp_error_no_network_message, 1).show();
            return;
        }

        String v0 = this.findViewById(b.input_email).getText().toString();
        String v2 = this.findViewById(b.input_password).getText().toString();
        HashMap v8 = new HashMap();
        int v4 = 0;
        if(this.c == 1) {
            v4 = TextUtils.isEmpty(((CharSequence)v0)) ^ 1;
            ((Map)v8).put("email", v0);
            StringBuilder v3 = new StringBuilder();
            v3.append(this.b);
            v3.append(v0);
            ((Map)v8).put("authcode", this.a(v3.toString()));
        }
        else if(this.c == 2) {
            if(!TextUtils.isEmpty(((CharSequence)v0)) && !TextUtils.isEmpty(((CharSequence)v2))) {
                v4 = 1;
            }

            ((Map)v8).put("email", v0);
            ((Map)v8).put("password", v2);
        }

        if(v4 != 0) {
            this.d = new f(this, this.e, this.a, this.c, ((Map)v8));
            net.hockeyapp.android.e.a.a(this.d);
        }
        else {
            Toast.makeText(((Context)this), this.getString(net.hockeyapp.android.f$d.hockeyapp_login_missing_credentials_toast), 1).show();
        }
    }

    protected void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        this.setContentView(c.hockeyapp_activity_login);
        arg2 = this.getIntent().getExtras();
        if(arg2 != null) {
            this.a = arg2.getString("url");
            this.b = arg2.getString("secret");
            this.c = arg2.getInt("mode");
        }

        this.a();
        this.b();
        Object v2 = this.getLastNonConfigurationInstance();
        if(v2 != null) {
            this.d = ((f)v2);
            this.d.a(((Context)this), this.e);
        }
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(arg2 == 4) {
            if(d.a != null) {
                d.a.b();
            }

            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    public Object onRetainNonConfigurationInstance() {
        if(this.d != null) {
            this.d.a();
        }

        return this.d;
    }
}

