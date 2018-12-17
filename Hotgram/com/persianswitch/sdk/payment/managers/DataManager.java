package com.persianswitch.sdk.payment.managers;

import android.content.Context;
import com.persianswitch.sdk.base.db.BaseDatabase;
import com.persianswitch.sdk.payment.database.SDKDatabase;

public final class DataManager {
    public DataManager() {
        super();
    }

    public static void a(Context arg1) {
        new BaseDatabase(arg1).a();
        new SDKDatabase(arg1).a();
    }
}

