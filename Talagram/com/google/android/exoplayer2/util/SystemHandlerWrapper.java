package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class SystemHandlerWrapper implements HandlerWrapper {
    private final Handler handler;

    public SystemHandlerWrapper(Handler arg1) {
        super();
        this.handler = arg1;
    }

    public Looper getLooper() {
        return this.handler.getLooper();
    }

    public Message obtainMessage(int arg2) {
        return this.handler.obtainMessage(arg2);
    }

    public Message obtainMessage(int arg2, int arg3, int arg4) {
        return this.handler.obtainMessage(arg2, arg3, arg4);
    }

    public Message obtainMessage(int arg2, int arg3, int arg4, Object arg5) {
        return this.handler.obtainMessage(arg2, arg3, arg4, arg5);
    }

    public Message obtainMessage(int arg2, Object arg3) {
        return this.handler.obtainMessage(arg2, arg3);
    }

    public boolean post(Runnable arg2) {
        return this.handler.post(arg2);
    }

    public boolean postDelayed(Runnable arg2, long arg3) {
        return this.handler.postDelayed(arg2, arg3);
    }

    public void removeCallbacksAndMessages(Object arg2) {
        this.handler.removeCallbacksAndMessages(arg2);
    }

    public void removeMessages(int arg2) {
        this.handler.removeMessages(arg2);
    }

    public boolean sendEmptyMessage(int arg2) {
        return this.handler.sendEmptyMessage(arg2);
    }

    public boolean sendEmptyMessageAtTime(int arg2, long arg3) {
        return this.handler.sendEmptyMessageAtTime(arg2, arg3);
    }
}

