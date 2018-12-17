package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public final class fa {
    public interface a {
        void a(c arg1);

        void a(c arg1, IOException arg2);

        void b(c arg1);
    }

    @SuppressLint(value={"HandlerLeak"}) final class b extends Handler implements Runnable {
        private final c b;
        private final a c;
        private volatile Thread d;

        public b(fa arg1, Looper arg2, c arg3, a arg4) {
            this.a = arg1;
            super(arg2);
            this.b = arg3;
            this.c = arg4;
        }

        public void a() {
            this.b.a();
            if(this.d != null) {
                this.d.interrupt();
            }
        }

        private void b() {
            fa.a(this.a, false);
            fa.a(this.a, null);
        }

        public void handleMessage(Message arg3) {
            if(arg3.what != 2) {
                this.b();
                if(this.b.b()) {
                    this.c.b(this.b);
                    return;
                }

                switch(arg3.what) {
                    case 0: {
                        this.c.a(this.b);
                        break;
                    }
                    case 1: {
                        this.c.a(this.b, arg3.obj);
                        break;
                    }
                    default: {
                        break;
                    }
                }

                return;
            }

            throw arg3.obj;
        }

        public void run() {
            Message v0_3;
            try {
                this.d = Thread.currentThread();
                if(!this.b.b()) {
                    fs.a(String.valueOf(this.b.getClass().getSimpleName()).concat(".load()"));
                    this.b.c();
                    fs.a();
                }

                this.sendEmptyMessage(0);
            }
            catch(Error v0) {
                Log.e("LoadTask", "Unexpected error loading stream", ((Throwable)v0));
                this.obtainMessage(2, v0).sendToTarget();
                throw v0;
            }
            catch(Exception v0_1) {
                Log.e("LoadTask", "Unexpected exception loading stream", ((Throwable)v0_1));
                v0_3 = this.obtainMessage(1, new d(v0_1));
            label_42:
                v0_3.sendToTarget();
            }
            catch(InterruptedException ) {
                fe.b(this.b.b());
                this.sendEmptyMessage(0);
            }
            catch(IOException v0_2) {
                v0_3 = this.obtainMessage(1, v0_2);
                goto label_42;
            }
        }
    }

    public interface c {
        void a();

        boolean b();

        void c();
    }

    public final class d extends IOException {
        public d(Exception arg5) {
            String v0 = arg5.getClass().getSimpleName();
            String v1 = arg5.getMessage();
            StringBuilder v3 = new StringBuilder(String.valueOf(v0).length() + 13 + String.valueOf(v1).length());
            v3.append("Unexpected ");
            v3.append(v0);
            v3.append(": ");
            v3.append(v1);
            super(v3.toString(), ((Throwable)arg5));
        }
    }

    private final ExecutorService a;
    private b b;
    private boolean c;

    public fa(String arg1) {
        super();
        this.a = ft.a(arg1);
    }

    static b a(fa arg0, b arg1) {
        arg0.b = arg1;
        return arg1;
    }

    static boolean a(fa arg0, boolean arg1) {
        arg0.c = arg1;
        return arg1;
    }

    public void a(Looper arg3, c arg4, a arg5) {
        fe.b(this.c ^ 1);
        this.c = true;
        this.b = new b(this, arg3, arg4, arg5);
        this.a.submit(this.b);
    }

    public void a(c arg3, a arg4) {
        Looper v0 = Looper.myLooper();
        boolean v1 = v0 != null ? true : false;
        fe.b(v1);
        this.a(v0, arg3, arg4);
    }

    public void a(Runnable arg2) {
        if(this.c) {
            this.b();
        }

        if(arg2 != null) {
            this.a.submit(arg2);
        }

        this.a.shutdown();
    }

    public boolean a() {
        return this.c;
    }

    public void b() {
        fe.b(this.c);
        this.b.a();
    }
}

