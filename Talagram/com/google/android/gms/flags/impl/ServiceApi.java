package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Binder;
import android.util.Log;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.android.gms.flags.Flag;
import com.google.android.gms.flags.Singletons;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServiceApi {
    public ServiceApi() {
        super();
    }

    public List getExperimentIdsFromService() {
        ArrayList v0 = new ArrayList();
        long v1 = Binder.clearCallingIdentity();
        try {
            Iterator v3 = Singletons.flagRegistry().registeredServiceExperimentIdFlags().iterator();
            while(v3.hasNext()) {
                Object v4 = new StringUtils(v3.next()).getGservicesValue().get();
                if(v4 == null) {
                    continue;
                }

                ((List)v0).add(v4);
            }
        }
        catch(Throwable v0_1) {
            goto label_19;
        }

        Binder.restoreCallingIdentity(v1);
        return ((List)v0);
    label_19:
        Binder.restoreCallingIdentity(v1);
        throw v0_1;
    }

    public static void persistGserviceValues(Context arg4) {
        SharedPreferences v0;
        try {
            v0 = SharedPreferencesFactory.getSharedPreferences(arg4);
        }
        catch(Exception v4) {
            String v0_1 = "FlagsServiceApi";
            String v1 = "Failed to write shared flags: ";
            String v4_1 = String.valueOf(v4.getMessage());
            v4_1 = v4_1.length() != 0 ? v1.concat(v4_1) : new String(v1);
            Log.e(v0_1, v4_1);
            return;
        }

        SharedPreferences$Editor v0_2 = v0.edit();
        Iterator v1_1 = Singletons.flagRegistry().registeredFlags().iterator();
        while(v1_1.hasNext()) {
            Object v2 = v1_1.next();
            if(((Flag)v2).getSource() != 0) {
                continue;
            }

            DataUtils v2_1 = DataUtils.forFlag(((Flag)v2));
            v2_1.putInSharedPreferences(v0_2, v2_1.getGservicesValue().get());
        }

        SharedPreferencesUtils.publishWorldReadableSharedPreferences(arg4, v0_2, "google_sdk_flags");
    }
}

