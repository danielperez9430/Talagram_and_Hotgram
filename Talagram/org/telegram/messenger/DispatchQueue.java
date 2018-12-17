package org.telegram.messenger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.CountDownLatch;

public class DispatchQueue extends Thread {
    private volatile Handler handler;
    private CountDownLatch syncLatch;

    public DispatchQueue(String arg3) {
        super();
        this.handler = null;
        this.syncLatch = new CountDownLatch(1);
        this.setName(arg3);
        this.start();
    }

    public void cancelRunnable(Runnable arg2) {
        try {
            this.syncLatch.await();
            this.handler.removeCallbacks(arg2);
        }
        catch(Exception v2) {
            FileLog.e(((Throwable)v2));
        }
    }

    public void cleanupQueue() {
        try {
            this.syncLatch.await();
            this.handler.removeCallbacksAndMessages(null);
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public void handleMessage(Message arg1) {
    }

    public void postRunnable(Runnable arg3) {
        this.postRunnable(arg3, 0);
    }

    public void postRunnable(Runnable arg4, long arg5) {
        try {
            this.syncLatch.await();
            if(arg5 <= 0) {
                this.handler.post(arg4);
                return;
            }

            this.handler.postDelayed(arg4, arg5);
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }

    public void run() {
        Looper.prepare();
        this.handler = new Handler() {
            public void handleMessage(Message arg2) {
                DispatchQueue.this.handleMessage(arg2);
            }
        };
        this.syncLatch.countDown();
        Looper.loop();
    }

    public void sendMessage(Message arg4, int arg5) {
        try {
            this.syncLatch.await();
            if(arg5 <= 0) {
                this.handler.sendMessage(arg4);
                return;
            }

            this.handler.sendMessageDelayed(arg4, ((long)arg5));
        }
        catch(Exception v4) {
            FileLog.e(((Throwable)v4));
        }
    }
}

