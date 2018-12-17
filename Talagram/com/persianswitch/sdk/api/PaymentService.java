package com.persianswitch.sdk.api;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.persianswitch.sdk.payment.managers.ServiceManager;

public class PaymentService extends Service {
    public PaymentService() {
        super();
    }

    public IBinder onBind(Intent arg1) {
        return new ServiceManager(((Context)this));
    }
}

