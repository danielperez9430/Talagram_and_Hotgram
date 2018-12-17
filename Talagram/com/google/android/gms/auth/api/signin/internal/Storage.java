package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

public class Storage {
    private static final Lock zzaf;
    @GuardedBy(value="sLk") private static Storage zzag;
    private final Lock zzah;
    @GuardedBy(value="mLk") private final SharedPreferences zzai;

    static {
        Storage.zzaf = new ReentrantLock();
    }

    @VisibleForTesting private Storage(Context arg3) {
        super();
        this.zzah = new ReentrantLock();
        this.zzai = arg3.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public void clear() {
        this.zzah.lock();
        try {
            this.zzai.edit().clear().apply();
        }
        catch(Throwable v0) {
            this.zzah.unlock();
            throw v0;
        }

        this.zzah.unlock();
    }

    @Nullable protected String getFromStore(String arg3) {
        this.zzah.lock();
        try {
            arg3 = this.zzai.getString(arg3, null);
        }
        catch(Throwable v3) {
            this.zzah.unlock();
            throw v3;
        }

        this.zzah.unlock();
        return arg3;
    }

    public static Storage getInstance(Context arg1) {
        Storage v1_1;
        Preconditions.checkNotNull(arg1);
        Storage.zzaf.lock();
        try {
            if(Storage.zzag == null) {
                Storage.zzag = new Storage(arg1.getApplicationContext());
            }

            v1_1 = Storage.zzag;
        }
        catch(Throwable v1) {
            Storage.zzaf.unlock();
            throw v1;
        }

        Storage.zzaf.unlock();
        return v1_1;
    }

    @Nullable public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return this.zzb(this.getFromStore("defaultGoogleSignInAccount"));
    }

    @Nullable public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return this.zzc(this.getFromStore("defaultGoogleSignInAccount"));
    }

    @Nullable public String getSavedRefreshToken() {
        return this.getFromStore("refreshToken");
    }

    protected void removeFromStore(String arg2) {
        this.zzah.lock();
        try {
            this.zzai.edit().remove(arg2).apply();
        }
        catch(Throwable v2) {
            this.zzah.unlock();
            throw v2;
        }

        this.zzah.unlock();
    }

    public void removeSavedDefaultGoogleSignInAccount() {
        String v0 = this.getFromStore("defaultGoogleSignInAccount");
        this.removeFromStore("defaultGoogleSignInAccount");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            this.removeFromStore(Storage.zza("googleSignInAccount", v0));
            this.removeFromStore(Storage.zza("googleSignInOptions", v0));
        }
    }

    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount arg3, GoogleSignInOptions arg4) {
        Preconditions.checkNotNull(arg3);
        Preconditions.checkNotNull(arg4);
        this.saveToStore("defaultGoogleSignInAccount", arg3.getObfuscatedIdentifier());
        Preconditions.checkNotNull(arg3);
        Preconditions.checkNotNull(arg4);
        String v0 = arg3.getObfuscatedIdentifier();
        this.saveToStore(Storage.zza("googleSignInAccount", v0), arg3.toJsonForStorage());
        this.saveToStore(Storage.zza("googleSignInOptions", v0), arg4.toJson());
    }

    public void saveRefreshToken(String arg2) {
        if(!TextUtils.isEmpty(((CharSequence)arg2))) {
            this.saveToStore("refreshToken", arg2);
        }
    }

    protected void saveToStore(String arg2, String arg3) {
        this.zzah.lock();
        try {
            this.zzai.edit().putString(arg2, arg3).apply();
        }
        catch(Throwable v2) {
            this.zzah.unlock();
            throw v2;
        }

        this.zzah.unlock();
    }

    @VisibleForTesting public static void setInstance(@Nullable Storage arg1) {
        Storage.zzaf.lock();
        try {
            Storage.zzag = arg1;
        }
        catch(Throwable v1) {
            Storage.zzaf.unlock();
            throw v1;
        }

        Storage.zzaf.unlock();
    }

    private static String zza(String arg2, String arg3) {
        StringBuilder v1 = new StringBuilder(String.valueOf(arg2).length() + 1 + String.valueOf(arg3).length());
        v1.append(arg2);
        v1.append(":");
        v1.append(arg3);
        return v1.toString();
    }

    @VisibleForTesting @Nullable private final GoogleSignInAccount zzb(String arg3) {
        GoogleSignInAccount v1 = null;
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            return v1;
        }

        arg3 = this.getFromStore(Storage.zza("googleSignInAccount", arg3));
        if(arg3 == null) {
            return v1;
        }

        try {
            return GoogleSignInAccount.fromJsonString(arg3);
        }
        catch(JSONException ) {
            return v1;
        }
    }

    @VisibleForTesting @Nullable private final GoogleSignInOptions zzc(String arg3) {
        GoogleSignInOptions v1 = null;
        if(TextUtils.isEmpty(((CharSequence)arg3))) {
            return v1;
        }

        arg3 = this.getFromStore(Storage.zza("googleSignInOptions", arg3));
        if(arg3 == null) {
            return v1;
        }

        try {
            return GoogleSignInOptions.fromJsonString(arg3);
        }
        catch(JSONException ) {
            return v1;
        }
    }
}

