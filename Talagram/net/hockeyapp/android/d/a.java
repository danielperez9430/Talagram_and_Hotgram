package net.hockeyapp.android.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.e.g;

public class a {
    class net.hockeyapp.android.d.a$1 {
    }

    class net.hockeyapp.android.d.a$a {
        static final a a;

        static {
            net.hockeyapp.android.d.a$a.a = new a(null);
        }
    }

    class b extends Handler {
        private final a a;

        b(a arg1) {
            super();
            this.a = arg1;
        }

        static a a(b arg0) {
            return arg0.a;
        }

        public void handleMessage(Message arg4) {
            Object v4 = a.a(this.a).poll();
            if(!((c)v4).c() && (((c)v4).e())) {
                this.postDelayed(new Runnable(((c)v4)) {
                    public void run() {
                        a.a(b.a(this.b)).add(this.a);
                        a.b(b.a(this.b));
                    }
                }, 3000);
            }

            a.a(this.a, false);
            a.b(this.a);
        }
    }

    class c {
        private final net.hockeyapp.android.c.b a;
        private final net.hockeyapp.android.views.a b;
        private boolean c;
        private int d;

        c(net.hockeyapp.android.c.b arg1, net.hockeyapp.android.views.a arg2, net.hockeyapp.android.d.a$1 arg3) {
            this(arg1, arg2);
        }

        private c(net.hockeyapp.android.c.b arg1, net.hockeyapp.android.views.a arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = false;
            this.d = 2;
        }

        net.hockeyapp.android.c.b a() {
            return this.a;
        }

        void a(boolean arg1) {
            this.c = arg1;
        }

        net.hockeyapp.android.views.a b() {
            return this.b;
        }

        boolean c() {
            return this.c;
        }

        boolean d() {
            boolean v0 = this.d > 0 ? true : false;
            return v0;
        }

        boolean e() {
            boolean v1 = true;
            int v0 = this.d - 1;
            this.d = v0;
            if(v0 >= 0) {
            }
            else {
                v1 = false;
            }

            return v1;
        }
    }

    @SuppressLint(value={"StaticFieldLeak"}) class d extends AsyncTask {
        private final c a;
        private final Handler b;
        private final Context c;
        private Bitmap d;
        private int e;

        d(c arg1, Handler arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg1.b().getContext();
            this.d = null;
            this.e = 1;
        }

        private URLConnection a(URL arg3) {
            URLConnection v3 = arg3.openConnection();
            ((HttpURLConnection)v3).addRequestProperty("User-Agent", "HockeySDK/Android 5.1.0");
            ((HttpURLConnection)v3).setInstanceFollowRedirects(true);
            return v3;
        }

        private void a(File arg4) {
            try {
                net.hockeyapp.android.views.a v0 = this.a.b();
                this.e = g.a(arg4);
                int v1 = this.e == 0 ? v0.getWidthLandscape() : v0.getWidthPortrait();
                int v0_1 = this.e == 0 ? v0.getMaxHeightLandscape() : v0.getMaxHeightPortrait();
                this.d = g.a(arg4, v1, v0_1);
            }
            catch(IOException v4) {
                e.b("Failed to load image thumbnail", ((Throwable)v4));
                this.d = null;
            }
        }

        private boolean a(String arg17, File arg18) {
            long v4;
            long v13;
            Integer[] v8;
            boolean v14;
            int v12;
            byte[] v3_2;
            int v3_1;
            FileOutputStream v7;
            URLConnection v15;
            BufferedInputStream v6;
            int v0_2;
            URLConnection v5;
            d v1 = this;
            File v2 = arg18;
            FileOutputStream v3 = null;
            try {
                v5 = v1.a(new URL(arg17));
            }
            catch(IOException v0) {
                goto label_97;
            }
            catch(Throwable v0_1) {
                goto label_93;
            }

            try {
                ((HttpURLConnection)v5).connect();
                goto label_10;
            }
            catch(Throwable v0_1) {
            }
            catch(IOException v0) {
            }
            catch(Throwable v0_1) {
            label_93:
                v6 = ((BufferedInputStream)v3);
                v15 = ((URLConnection)v6);
                goto label_115;
                try {
                label_10:
                    v0_2 = ((HttpURLConnection)v5).getContentLength();
                    String v6_1 = ((HttpURLConnection)v5).getHeaderField("Status");
                    if(v6_1 != null) {
                        goto label_14;
                    }

                    goto label_28;
                }
                catch(IOException v0) {
                    goto label_89;
                }
                catch(Throwable v0_1) {
                    goto label_85;
                }

                try {
                label_14:
                    if(v6_1.startsWith("200")) {
                        goto label_28;
                    }
                }
                catch(Throwable v0_1) {
                    v6 = ((BufferedInputStream)v3);
                    goto label_22;
                }
                catch(IOException v0) {
                    v6 = ((BufferedInputStream)v3);
                    goto label_26;
                }

                if(v5 != null) {
                    ((HttpURLConnection)v5).disconnect();
                }

                return 0;
                try {
                label_28:
                    v6 = new BufferedInputStream(((HttpURLConnection)v5).getInputStream());
                }
                catch(Throwable v0_1) {
                label_85:
                    v15 = v5;
                    v6 = ((BufferedInputStream)v3);
                    goto label_115;
                }
                catch(IOException v0) {
                label_89:
                    v15 = v5;
                    v6 = ((BufferedInputStream)v3);
                    goto label_99;
                }
            }
            catch(IOException v0) {
            label_97:
                v6 = ((BufferedInputStream)v3);
                v15 = ((URLConnection)v6);
                goto label_99;
            }

            try {
                v7 = new FileOutputStream(v2);
                v3_1 = 1024;
                goto label_34;
            }
            catch(Throwable v0_1) {
            }
            catch(IOException v0) {
            label_26:
                v15 = v5;
                goto label_99;
            }

        label_22:
            v15 = v5;
            goto label_115;
            try {
            label_34:
                v3_2 = new byte[v3_1];
                long v10 = 0;
                while(true) {
                label_36:
                    v12 = ((InputStream)v6).read(v3_2);
                    v14 = true;
                    if(v12 == -1) {
                        goto label_57;
                    }

                    v10 += ((long)v12);
                    v8 = new Integer[1];
                    v13 = 100 * v10;
                    v15 = v5;
                    v4 = ((long)v0_2);
                    break;
                }
            }
            catch(IOException v0) {
                goto label_77;
            }
            catch(Throwable v0_1) {
                goto label_73;
            }

            try {
                v8[0] = Integer.valueOf(((int)(v13 / v4)));
                v1.publishProgress(((Object[])v8));
                ((OutputStream)v7).write(v3_2, 0, v12);
                v5 = v15;
                goto label_36;
            label_57:
                v15 = v5;
                ((OutputStream)v7).flush();
                if(v10 <= 0) {
                    goto label_62;
                }
            }
            catch(Throwable v0_1) {
                goto label_74;
            }
            catch(IOException v0) {
                goto label_78;
            }

            try {
            label_63:
                ((OutputStream)v7).close();
                ((InputStream)v6).close();
            }
            catch(IOException ) {
            }

            goto label_65;
        label_62:
            v14 = false;
            goto label_63;
        label_65:
            if(v15 != null) {
                ((HttpURLConnection)v15).disconnect();
            }

            return v14;
        label_77:
            v15 = v5;
        label_78:
            v3 = v7;
            try {
            label_99:
                e.b("Failed to download attachment to " + v2, ((Throwable)v0));
                if((((FileOutputStream)v3_1)) == null) {
                    goto label_108;
                }
            }
            catch(Throwable v0_1) {
                goto label_115;
            }

            try {
                ((OutputStream)v3_1).close();
            label_108:
                if(v6 != null) {
                    ((InputStream)v6).close();
                }

                goto label_110;
            }
            catch(IOException ) {
            label_110:
                if(v15 != null) {
                    ((HttpURLConnection)v15).disconnect();
                }

                return 0;
            }

        label_73:
            v15 = v5;
        label_74:
            v3 = v7;
        label_115:
            if((((FileOutputStream)v3_1)) != null) {
                try {
                    ((OutputStream)v3_1).close();
                label_117:
                    if(v6 != null) {
                        ((InputStream)v6).close();
                    }

                    goto label_119;
                }
                catch(IOException ) {
                label_119:
                    if(v15 != null) {
                        ((HttpURLConnection)v15).disconnect();
                    }

                    throw v0_1;
                }
            }

            goto label_117;
        }

        protected Boolean a(Void[] arg4) {
            net.hockeyapp.android.c.b v4 = this.a.a();
            File v0 = new File(net.hockeyapp.android.a.b(this.c), v4.c());
            if(v0.exists()) {
                e.c("Cached...");
                this.a(v0);
                return Boolean.valueOf(true);
            }

            e.c("Downloading...");
            boolean v4_1 = this.a(v4.b(), v0);
            if(v4_1) {
                this.a(v0);
            }

            return Boolean.valueOf(v4_1);
        }

        protected void a(Boolean arg4) {
            net.hockeyapp.android.views.a v0 = this.a.b();
            this.a.a(arg4.booleanValue());
            if(arg4.booleanValue()) {
                v0.a(this.d, this.e);
            }
            else if(!this.a.d()) {
                v0.b();
            }

            this.b.sendEmptyMessage(0);
        }

        protected void a(Integer[] arg1) {
        }

        protected Object doInBackground(Object[] arg1) {
            return this.a(((Void[])arg1));
        }

        protected void onPostExecute(Object arg1) {
            this.a(((Boolean)arg1));
        }

        protected void onPreExecute() {
        }

        protected void onProgressUpdate(Object[] arg1) {
            this.a(((Integer[])arg1));
        }
    }

    private Queue a;
    private boolean b;
    private final Handler c;

    private a() {
        super();
        this.c = new b(this);
        this.a = new LinkedList();
        this.b = false;
    }

    a(net.hockeyapp.android.d.a$1 arg1) {
        this();
    }

    static Queue a(a arg0) {
        return arg0.a;
    }

    public static a a() {
        return net.hockeyapp.android.d.a$a.a;
    }

    static boolean a(a arg0, boolean arg1) {
        arg0.b = arg1;
        return arg1;
    }

    public void a(net.hockeyapp.android.c.b arg4, net.hockeyapp.android.views.a arg5) {
        this.a.add(new c(arg4, arg5, null));
        this.b();
    }

    private void b() {
        if(this.b) {
            return;
        }

        Object v0 = this.a.peek();
        if(v0 != null) {
            this.b = true;
            net.hockeyapp.android.e.a.a(new d(((c)v0), this.c));
        }
    }

    static void b(a arg0) {
        arg0.b();
    }
}

