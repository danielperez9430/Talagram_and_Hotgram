package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Message;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ji implements Handler$Callback {
    public class a {
        private final Handler a;

        public a(Handler arg1) {
            super();
            this.a = arg1;
        }

        protected void a(int arg2) {
            this.a.removeMessages(arg2);
        }

        protected boolean a(int arg2, long arg3) {
            return this.a.sendEmptyMessageDelayed(arg2, arg3);
        }

        protected boolean b(int arg2) {
            return this.a.sendEmptyMessage(arg2);
        }

        protected boolean c(int arg3) {
            return this.a.sendMessageAtFrontOfQueue(Message.obtain(this.a, arg3));
        }
    }

    public interface b {
        void a(VideoProgressUpdate arg1);
    }

    private final a a;
    protected final long b;
    protected boolean c;
    private List d;

    ji(long arg2) {
        this(null, arg2);
    }

    ji(a arg3, long arg4) {
        super();
        this.c = false;
        this.d = new ArrayList(1);
        this.b = arg4;
        if(arg3 == null) {
            arg3 = new a(new Handler(((Handler$Callback)this)));
        }

        this.a = arg3;
    }

    public abstract VideoProgressUpdate a();

    public void a(b arg2) {
        this.d.add(arg2);
    }

    public void b() {
        if(this.c) {
            return;
        }

        this.c = true;
        this.a.b(1);
    }

    public void b(b arg2) {
        this.d.remove(arg2);
    }

    public void c() {
        if(this.c) {
            this.c = false;
            this.a.c(2);
        }
    }

    public boolean handleMessage(Message arg4) {
        switch(arg4.what) {
            case 1: {
                VideoProgressUpdate v4 = this.a();
                Iterator v1 = this.d.iterator();
                while(v1.hasNext()) {
                    v1.next().a(v4);
                }

                this.a.a(1, this.b);
                break;
            }
            case 2: {
                this.a.a(1);
                break;
            }
            default: {
                break;
            }
        }

        return 1;
    }
}

