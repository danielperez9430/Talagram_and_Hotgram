package org.telegram.customization.h;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.persianswitch.sdk.api.IPaymentService$Stub;
import com.persianswitch.sdk.api.IPaymentService;
import com.persianswitch.sdk.api.PaymentService;
import com.persianswitch.sdk.api.Response$General;
import org.telegram.customization.f.b;
import org.telegram.customization.util.d;

public class a extends AsyncTask {
    class org.telegram.customization.h.a$1 implements ServiceConnection {
        org.telegram.customization.h.a$1(a arg1) {
            this.a = arg1;
            super();
        }

        public void onServiceConnected(ComponentName arg1, IBinder arg2) {
            a.a(this.a, Stub.a(arg2));
            d.a(this.a, new Void[0]);
        }

        public void onServiceDisconnected(ComponentName arg2) {
            a.a(this.a, null);
        }
    }

    b a;
    private Bundle b;
    private Context c;
    private IPaymentService d;
    private final ServiceConnection e;

    public a(Bundle arg2, b arg3) {
        super();
        this.e = new org.telegram.customization.h.a$1(this);
        this.b = arg2;
        this.a = arg3;
    }

    static IPaymentService a(a arg0, IPaymentService arg1) {
        arg0.d = arg1;
        return arg1;
    }

    protected Bundle a(Void[] arg2) {
        try {
            return this.d.b(this.b);
        }
        catch(Exception ) {
            return null;
        }
    }

    public void a(Context arg4) {
        this.c = arg4;
        arg4.bindService(new Intent(arg4, PaymentService.class), this.e, 1);
    }

    protected void a(Bundle arg5) {
        Toast v5_1;
        if(arg5 != null) {
            int v5 = arg5.getInt(General.a);
            if(v5 == 0) {
                Log.i("alirezaaaaa", "alirezaaaaa sdk error : " + v5);
                Toast.makeText(this.c, "The mobile number registered successfully. Now user can pay...", 1).show();
                this.a.a();
            }
            else {
                if(v5 == 1102) {
                    Log.i("alirezaaaaa", "alirezaaaaa sdk error : " + v5);
                    v5_1 = Toast.makeText(this.c, "The mobile number registered successfully. Now user can pay...", 1);
                }
                else {
                    Context v1 = this.c;
                    v2 = new StringBuilder();
                    v2.append("The Registration failed. Host app should retry registration phase.");
                    v2.append(v5);
                    v5_1 = Toast.makeText(v1, v2.toString(), 1);
                }

                v5_1.show();
                this.a.b();
            }
        }

        this.c.unbindService(this.e);
    }

    protected Object doInBackground(Object[] arg1) {
        return this.a(((Void[])arg1));
    }

    protected void onPostExecute(Object arg1) {
        this.a(((Bundle)arg1));
    }
}

