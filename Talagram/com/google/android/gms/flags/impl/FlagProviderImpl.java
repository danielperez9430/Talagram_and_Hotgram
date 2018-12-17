package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.flags.IFlagProvider$Stub;

@DynamiteApi public class FlagProviderImpl extends Stub {
    private boolean zzacf;
    private SharedPreferences zzacu;

    public FlagProviderImpl() {
        super();
        this.zzacf = false;
    }

    public boolean getBooleanFlagValue(String arg1, boolean arg2, int arg3) {
        if(!this.zzacf) {
            return arg2;
        }

        return BooleanUtils.getFromSharedPreferencesNoStrict(this.zzacu, arg1, Boolean.valueOf(arg2)).booleanValue();
    }

    public int getIntFlagValue(String arg1, int arg2, int arg3) {
        if(!this.zzacf) {
            return arg2;
        }

        return IntegerUtils.getFromSharedPreferencesNoStrict(this.zzacu, arg1, Integer.valueOf(arg2)).intValue();
    }

    public long getLongFlagValue(String arg1, long arg2, int arg4) {
        if(!this.zzacf) {
            return arg2;
        }

        return LongUtils.getFromSharedPreferencesNoStrict(this.zzacu, arg1, Long.valueOf(arg2)).longValue();
    }

    public String getStringFlagValue(String arg1, String arg2, int arg3) {
        if(!this.zzacf) {
            return arg2;
        }

        return StringUtils.getFromSharedPreferencesNoStrict(this.zzacu, arg1, arg2);
    }

    public void init(IObjectWrapper arg4) {
        Object v4 = ObjectWrapper.unwrap(arg4);
        if(this.zzacf) {
            return;
        }

        try {
            this.zzacu = SharedPreferencesFactory.getSharedPreferences(((Context)v4).createPackageContext("com.google.android.gms", 0));
            this.zzacf = true;
            return;
        }
        catch(PackageManager$NameNotFoundException ) {
            return;
        }
        catch(Exception v4_1) {
            String v0 = "FlagProviderImpl";
            String v1 = "Could not retrieve sdk flags, continuing with defaults: ";
            String v4_2 = String.valueOf(v4_1.getMessage());
            v4_2 = v4_2.length() != 0 ? v1.concat(v4_2) : new String(v1);
            Log.w(v0, v4_2);
            return;
        }
    }
}

