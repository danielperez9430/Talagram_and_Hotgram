package com.persianswitch.sdk.base.webservice;

import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.persianswitch.sdk.base.Config;
import com.persianswitch.sdk.base.Injection$Network;
import com.persianswitch.sdk.base.utils.func.Action1;
import java.io.InputStream;

public final class DownloadLogoTask extends AsyncTask {
    private final Context a;
    private final Config b;
    private final String c;
    private final Action1 d;

    private DownloadLogoTask(Context arg1, Config arg2, String arg3, Action1 arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public static void a(Config arg3, String arg4, ImageView arg5) {
        if(arg5 == null) {
            return;
        }

        AsyncTaskUtil.a(new DownloadLogoTask(arg5.getContext(), arg3, arg4, new Action1(arg5) {
            public void a(Bitmap arg2) {
                this.a.setImageBitmap(arg2);
            }

            public void a(Object arg1) {
                this.a(((Bitmap)arg1));
            }
        }), new Void[0]);
    }

    protected Bitmap a(Void[] arg3) {
        InputStream v3 = Network.a(this.a, this.b).a(this.c);
        BitmapFactory$Options v0 = new BitmapFactory$Options();
        v0.inPreferredConfig = Bitmap$Config.RGB_565;
        v0.inDither = true;
        return BitmapFactory.decodeStream(v3, null, v0);
    }

    protected void a(Bitmap arg2) {
        if(arg2 != null && this.d != null) {
            this.d.a(arg2);
        }
    }

    protected Object doInBackground(Object[] arg1) {
        return this.a(((Void[])arg1));
    }

    protected void onPostExecute(Object arg1) {
        this.a(((Bitmap)arg1));
    }
}

