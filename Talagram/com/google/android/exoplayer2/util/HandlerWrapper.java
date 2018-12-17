package com.google.android.exoplayer2.util;

import android.os.Looper;
import android.os.Message;

public interface HandlerWrapper {
    Looper getLooper();

    Message obtainMessage(int arg1);

    Message obtainMessage(int arg1, int arg2, int arg3);

    Message obtainMessage(int arg1, int arg2, int arg3, Object arg4);

    Message obtainMessage(int arg1, Object arg2);

    boolean post(Runnable arg1);

    boolean postDelayed(Runnable arg1, long arg2);

    void removeCallbacksAndMessages(Object arg1);

    void removeMessages(int arg1);

    boolean sendEmptyMessage(int arg1);

    boolean sendEmptyMessageAtTime(int arg1, long arg2);
}

