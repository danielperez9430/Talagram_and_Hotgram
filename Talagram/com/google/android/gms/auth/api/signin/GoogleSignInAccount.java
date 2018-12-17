package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator="GoogleSignInAccountCreator") public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @VisibleForTesting public static Clock sClock;
    @VersionField(id=1) private final int versionCode;
    @Field(getter="getId", id=2) private String zze;
    @Field(getter="getIdToken", id=3) private String zzf;
    @Field(getter="getEmail", id=4) private String zzg;
    @Field(getter="getDisplayName", id=5) private String zzh;
    @Field(getter="getPhotoUrl", id=6) private Uri zzi;
    @Field(getter="getServerAuthCode", id=7) private String zzj;
    @Field(getter="getExpirationTimeSecs", id=8) private long zzk;
    @Field(getter="getObfuscatedIdentifier", id=9) private String zzl;
    @Field(id=10) private List zzm;
    @Field(getter="getGivenName", id=11) private String zzn;
    @Field(getter="getFamilyName", id=12) private String zzo;
    private Set zzp;

    static {
        GoogleSignInAccount.CREATOR = new GoogleSignInAccountCreator();
        GoogleSignInAccount.sClock = DefaultClock.getInstance();
    }

    @Constructor GoogleSignInAccount(@Param(id=1) int arg2, @Param(id=2) String arg3, @Param(id=3) String arg4, @Param(id=4) String arg5, @Param(id=5) String arg6, @Param(id=6) Uri arg7, @Param(id=7) String arg8, @Param(id=8) long arg9, @Param(id=9) String arg11, @Param(id=10) List arg12, @Param(id=11) String arg13, @Param(id=12) String arg14) {
        super();
        this.zzp = new HashSet();
        this.versionCode = arg2;
        this.zze = arg3;
        this.zzf = arg4;
        this.zzg = arg5;
        this.zzh = arg6;
        this.zzi = arg7;
        this.zzj = arg8;
        this.zzk = arg9;
        this.zzl = arg11;
        this.zzm = arg12;
        this.zzn = arg13;
        this.zzo = arg14;
    }

    public static GoogleSignInAccount create(String arg10, String arg11, String arg12, String arg13, Uri arg14, Long arg15, String arg16, Set arg17) {
        return GoogleSignInAccount.create(arg10, arg11, arg12, arg13, null, null, arg14, arg15, arg16, arg17);
    }

    public static GoogleSignInAccount create(String arg16, String arg17, String arg18, String arg19, String arg20, String arg21, Uri arg22, Long arg23, String arg24, Set arg25) {
        Long v0 = arg23 == null ? Long.valueOf(GoogleSignInAccount.sClock.currentTimeMillis() / 1000) : arg23;
        return new GoogleSignInAccount(3, arg16, arg17, arg18, arg19, arg22, null, v0.longValue(), Preconditions.checkNotEmpty(arg24), new ArrayList(Preconditions.checkNotNull(arg25)), arg20, arg21);
    }

    public static GoogleSignInAccount createDefault() {
        return GoogleSignInAccount.zza(new Account("<<default account>>", "com.google"), new HashSet());
    }

    public boolean equals(Object arg5) {
        if((((GoogleSignInAccount)arg5)) == this) {
            return 1;
        }

        if(!(arg5 instanceof GoogleSignInAccount)) {
            return 0;
        }

        if((((GoogleSignInAccount)arg5).getObfuscatedIdentifier().equals(this.getObfuscatedIdentifier())) && (((GoogleSignInAccount)arg5).getRequestedScopes().equals(this.getRequestedScopes()))) {
            return 1;
        }

        return 0;
    }

    public static GoogleSignInAccount fromAccountAndScopes(Account arg1, Scope arg2, Scope[] arg3) {
        Preconditions.checkNotNull(arg1);
        Preconditions.checkNotNull(arg2);
        HashSet v0 = new HashSet();
        ((Set)v0).add(arg2);
        ((Set)v0).addAll(Arrays.asList(((Object[])arg3)));
        return GoogleSignInAccount.zza(arg1, ((Set)v0));
    }

    public static GoogleSignInAccount fromJsonString(String arg13) {
        GoogleSignInAccount v1 = null;
        if(TextUtils.isEmpty(((CharSequence)arg13))) {
            return v1;
        }

        JSONObject v0 = new JSONObject(arg13);
        arg13 = v0.optString("photoUrl", ((String)v1));
        Uri v8 = !TextUtils.isEmpty(((CharSequence)arg13)) ? Uri.parse(arg13) : ((Uri)v1);
        long v2 = Long.parseLong(v0.getString("expirationTime"));
        HashSet v11 = new HashSet();
        JSONArray v13 = v0.getJSONArray("grantedScopes");
        int v4 = v13.length();
        int v5;
        for(v5 = 0; v5 < v4; ++v5) {
            ((Set)v11).add(new Scope(v13.getString(v5)));
        }

        return GoogleSignInAccount.create(v0.optString("id"), v0.optString("tokenId", ((String)v1)), v0.optString("email", ((String)v1)), v0.optString("displayName", ((String)v1)), v0.optString("givenName", ((String)v1)), v0.optString("familyName", ((String)v1)), v8, Long.valueOf(v2), v0.getString("obfuscatedIdentifier"), ((Set)v11)).setServerAuthCode(v0.optString("serverAuthCode", ((String)v1)));
    }

    public Account getAccount() {
        if(this.zzg == null) {
            return null;
        }

        return new Account(this.zzg, "com.google");
    }

    public String getDisplayName() {
        return this.zzh;
    }

    public String getEmail() {
        return this.zzg;
    }

    public long getExpirationTimeSecs() {
        return this.zzk;
    }

    public String getFamilyName() {
        return this.zzo;
    }

    public String getGivenName() {
        return this.zzn;
    }

    public Set getGrantedScopes() {
        return new HashSet(this.zzm);
    }

    public String getId() {
        return this.zze;
    }

    public String getIdToken() {
        return this.zzf;
    }

    public String getObfuscatedIdentifier() {
        return this.zzl;
    }

    public Uri getPhotoUrl() {
        return this.zzi;
    }

    public Set getRequestedScopes() {
        HashSet v0 = new HashSet(this.zzm);
        ((Set)v0).addAll(this.zzp);
        return ((Set)v0);
    }

    public String getServerAuthCode() {
        return this.zzj;
    }

    public int hashCode() {
        return (this.getObfuscatedIdentifier().hashCode() + 527) * 31 + this.getRequestedScopes().hashCode();
    }

    public boolean isExpired() {
        if(GoogleSignInAccount.sClock.currentTimeMillis() / 1000 >= this.zzk - 300) {
            return 1;
        }

        return 0;
    }

    public GoogleSignInAccount requestExtraScopes(Scope[] arg2) {
        if(arg2 != null) {
            Collections.addAll(this.zzp, ((Object[])arg2));
        }

        return this;
    }

    public GoogleSignInAccount setServerAuthCode(String arg1) {
        this.zzj = arg1;
        return this;
    }

    public String toJson() {
        return this.zza().toString();
    }

    public String toJsonForStorage() {
        JSONObject v0 = this.zza();
        v0.remove("serverAuthCode");
        return v0.toString();
    }

    public void writeToParcel(Parcel arg6, int arg7) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg6);
        SafeParcelWriter.writeInt(arg6, 1, this.versionCode);
        SafeParcelWriter.writeString(arg6, 2, this.getId(), false);
        SafeParcelWriter.writeString(arg6, 3, this.getIdToken(), false);
        SafeParcelWriter.writeString(arg6, 4, this.getEmail(), false);
        SafeParcelWriter.writeString(arg6, 5, this.getDisplayName(), false);
        SafeParcelWriter.writeParcelable(arg6, 6, this.getPhotoUrl(), arg7, false);
        SafeParcelWriter.writeString(arg6, 7, this.getServerAuthCode(), false);
        SafeParcelWriter.writeLong(arg6, 8, this.getExpirationTimeSecs());
        SafeParcelWriter.writeString(arg6, 9, this.getObfuscatedIdentifier(), false);
        SafeParcelWriter.writeTypedList(arg6, 10, this.zzm, false);
        SafeParcelWriter.writeString(arg6, 11, this.getGivenName(), false);
        SafeParcelWriter.writeString(arg6, 12, this.getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(arg6, v0);
    }

    static final int zza(Scope arg0, Scope arg1) {
        return arg0.getScopeUri().compareTo(arg1.getScopeUri());
    }

    private static GoogleSignInAccount zza(Account arg10, Set arg11) {
        return GoogleSignInAccount.create(null, null, arg10.name, null, null, null, null, Long.valueOf(0), arg10.name, arg11);
    }

    private final JSONObject zza() {
        JSONObject v0 = new JSONObject();
        try {
            if(this.getId() != null) {
                v0.put("id", this.getId());
            }

            if(this.getIdToken() != null) {
                v0.put("tokenId", this.getIdToken());
            }

            if(this.getEmail() != null) {
                v0.put("email", this.getEmail());
            }

            if(this.getDisplayName() != null) {
                v0.put("displayName", this.getDisplayName());
            }

            if(this.getGivenName() != null) {
                v0.put("givenName", this.getGivenName());
            }

            if(this.getFamilyName() != null) {
                v0.put("familyName", this.getFamilyName());
            }

            if(this.getPhotoUrl() != null) {
                v0.put("photoUrl", this.getPhotoUrl().toString());
            }

            if(this.getServerAuthCode() != null) {
                v0.put("serverAuthCode", this.getServerAuthCode());
            }

            v0.put("expirationTime", this.zzk);
            v0.put("obfuscatedIdentifier", this.getObfuscatedIdentifier());
            JSONArray v1 = new JSONArray();
            Object[] v2 = this.zzm.toArray(new Scope[this.zzm.size()]);
            Arrays.sort(v2, zza.zzq);
            int v3 = v2.length;
            int v4;
            for(v4 = 0; v4 < v3; ++v4) {
                v1.put(v2[v4].getScopeUri());
            }

            v0.put("grantedScopes", v1);
            return v0;
        }
        catch(JSONException v0_1) {
            throw new RuntimeException(((Throwable)v0_1));
        }
    }
}

