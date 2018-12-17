package org.telegram.customization.voip.linphoneSip.linphone;

import android.os.Handler;
import android.os.Looper;

public class UIThreadDispatcher {
    private static Handler sHandler;

    static {
        UIThreadDispatcher.sHandler = new Handler(Looper.getMainLooper());
    }

    public UIThreadDispatcher() {
        super();
    }

    public static void dispatch(Runnable arg1) {
        UIThreadDispatcher.sHandler.post(arg1);
    }
}

