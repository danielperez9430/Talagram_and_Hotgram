package org.telegram.messenger.support.customtabsclient.shared;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class KeepAliveService extends Service {
    private static final Binder sBinder;

    static {
        KeepAliveService.sBinder = new Binder();
    }

    public KeepAliveService() {
        super();
    }

    public IBinder onBind(Intent arg1) {
        return KeepAliveService.sBinder;
    }
}

