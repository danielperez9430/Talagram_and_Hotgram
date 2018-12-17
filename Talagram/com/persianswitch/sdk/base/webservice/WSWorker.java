package com.persianswitch.sdk.base.webservice;

import android.content.Context;
import android.os.AsyncTask;
import com.persianswitch.sdk.base.Config;
import com.persianswitch.sdk.base.Injection$Network;
import com.persianswitch.sdk.base.Injection$ThreadPool;
import com.persianswitch.sdk.base.log.SDKLog;

final class WSWorker extends AsyncTask {
    class com.persianswitch.sdk.base.webservice.WSWorker$1 {
    }

    public final class Builder {
        private Context a;
        private Config b;
        private String c;
        private String d;
        private boolean e;
        private WSWorkerListener f;
        private byte[] g;
        private long h;

        public Builder(Context arg1, Config arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        static Context a(Builder arg0) {
            return arg0.a;
        }

        public Builder a(long arg1) {
            this.h = arg1;
            return this;
        }

        public Builder a(WSWorkerListener arg1) {
            this.f = arg1;
            return this;
        }

        public Builder a(String arg1) {
            this.c = arg1;
            return this;
        }

        public Builder a(boolean arg1) {
            this.e = arg1;
            return this;
        }

        public Builder a(byte[] arg1) {
            this.g = arg1;
            return this;
        }

        public WSWorker a() {
            return new WSWorker(this, null);
        }

        static Config b(Builder arg0) {
            return arg0.b;
        }

        public Builder b(String arg1) {
            this.d = arg1;
            return this;
        }

        static String c(Builder arg0) {
            return arg0.c;
        }

        static String d(Builder arg0) {
            return arg0.d;
        }

        static boolean e(Builder arg0) {
            return arg0.e;
        }

        static WSWorkerListener f(Builder arg0) {
            return arg0.f;
        }

        static byte[] g(Builder arg0) {
            return arg0.g;
        }

        static long h(Builder arg2) {
            return arg2.h;
        }
    }

    interface WSWorkerListener {
        void a(HttpResult arg1);

        void a();
    }

    private final Context a;
    private final Config b;
    private final String c;
    private final String d;
    private final boolean e;
    private final WSWorkerListener f;
    private final byte[] g;
    private final long h;

    private WSWorker(Builder arg3) {
        super();
        this.a = Builder.a(arg3);
        this.b = Builder.b(arg3);
        this.c = Builder.c(arg3);
        this.d = Builder.d(arg3);
        this.e = Builder.e(arg3);
        this.f = Builder.f(arg3);
        this.g = Builder.g(arg3);
        this.h = Builder.h(arg3);
    }

    WSWorker(Builder arg1, com.persianswitch.sdk.base.webservice.WSWorker$1 arg2) {
        this(arg1);
    }

    protected HttpResult a(Void[] arg8) {
        try {
            Thread.sleep(this.h);
        }
        catch(InterruptedException ) {
            SDKLog.c("WSWorker", "error while wait for time : %d ", new Object[]{Long.valueOf(this.h)});
        }

        return Network.a(this.a, this.b).a(this.a, this.c, this.d, this.e, this.g);
    }

    public void a() {
        this.executeOnExecutor(ThreadPool.a(), new Void[0]);
    }

    protected void a(HttpResult arg2) {
        if(this.f != null) {
            this.f.a(arg2);
        }
    }

    public void b() {
        this.a(this.a(new Void[0]));
    }

    protected Object doInBackground(Object[] arg1) {
        return this.a(((Void[])arg1));
    }

    protected void onPostExecute(Object arg1) {
        this.a(((HttpResult)arg1));
    }

    protected void onPreExecute() {
        if(this.f != null) {
            this.f.a();
        }
    }
}

