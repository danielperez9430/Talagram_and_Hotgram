package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;
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
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator="GoogleSignInOptionsCreator") public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional, ReflectedParcelable {
    public final class Builder {
        private Set mScopes;
        private Map zzab;
        private Account zzs;
        private boolean zzt;
        private boolean zzu;
        private boolean zzv;
        private String zzw;
        private String zzx;

        public Builder() {
            super();
            this.mScopes = new HashSet();
            this.zzab = new HashMap();
        }

        public Builder(GoogleSignInOptions arg3) {
            super();
            this.mScopes = new HashSet();
            this.zzab = new HashMap();
            Preconditions.checkNotNull(arg3);
            this.mScopes = new HashSet(GoogleSignInOptions.zza(arg3));
            this.zzu = GoogleSignInOptions.zzb(arg3);
            this.zzv = GoogleSignInOptions.zzc(arg3);
            this.zzt = GoogleSignInOptions.zzd(arg3);
            this.zzw = GoogleSignInOptions.zze(arg3);
            this.zzs = GoogleSignInOptions.zzf(arg3);
            this.zzx = GoogleSignInOptions.zzg(arg3);
            this.zzab = GoogleSignInOptions.zzb(GoogleSignInOptions.zzh(arg3));
        }

        public final Builder addExtension(GoogleSignInOptionsExtension arg4) {
            if(!this.zzab.containsKey(Integer.valueOf(arg4.getExtensionType()))) {
                if(arg4.getImpliedScopes() != null) {
                    this.mScopes.addAll(arg4.getImpliedScopes());
                }

                this.zzab.put(Integer.valueOf(arg4.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(arg4));
                return this;
            }

            throw new IllegalStateException("Only one extension per type may be added");
        }

        public final GoogleSignInOptions build() {
            if((this.mScopes.contains(GoogleSignInOptions.SCOPE_GAMES)) && (this.mScopes.contains(GoogleSignInOptions.SCOPE_GAMES_LITE))) {
                this.mScopes.remove(GoogleSignInOptions.SCOPE_GAMES_LITE);
            }

            if((this.zzt) && (this.zzs == null || !this.mScopes.isEmpty())) {
                this.requestId();
            }

            return new GoogleSignInOptions(3, new ArrayList(this.mScopes), this.zzs, this.zzt, this.zzu, this.zzv, this.zzw, this.zzx, this.zzab, null);
        }

        public final Builder requestEmail() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_EMAIL);
            return this;
        }

        public final Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_OPEN_ID);
            return this;
        }

        public final Builder requestIdToken(String arg2) {
            this.zzt = true;
            this.zzw = this.zza(arg2);
            return this;
        }

        public final Builder requestPhatIdToken(String arg1) {
            return this.requestIdToken(arg1).requestProfile().requestEmail();
        }

        public final Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_PROFILE);
            return this;
        }

        public final Builder requestScopes(Scope arg2, Scope[] arg3) {
            this.mScopes.add(arg2);
            this.mScopes.addAll(Arrays.asList(((Object[])arg3)));
            return this;
        }

        public final Builder requestServerAuthCode(String arg2) {
            return this.requestServerAuthCode(arg2, false);
        }

        public final Builder requestServerAuthCode(String arg2, boolean arg3) {
            this.zzu = true;
            this.zzw = this.zza(arg2);
            this.zzv = arg3;
            return this;
        }

        public final Builder setAccount(Account arg1) {
            this.zzs = Preconditions.checkNotNull(arg1);
            return this;
        }

        public final Builder setAccountName(String arg3) {
            this.zzs = new Account(Preconditions.checkNotEmpty(arg3), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String arg1) {
            this.zzx = Preconditions.checkNotEmpty(arg1);
            return this;
        }

        private final String zza(String arg3) {
            Preconditions.checkNotEmpty(arg3);
            boolean v0 = this.zzw == null || (this.zzw.equals(arg3)) ? true : false;
            Preconditions.checkArgument(v0, "two different server client ids provided");
            return arg3;
        }
    }

    public static final Parcelable$Creator CREATOR;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    @VisibleForTesting public static final Scope SCOPE_EMAIL;
    @VisibleForTesting public static final Scope SCOPE_GAMES;
    @VisibleForTesting public static final Scope SCOPE_GAMES_LITE;
    @VisibleForTesting public static final Scope SCOPE_OPEN_ID;
    @VisibleForTesting public static final Scope SCOPE_PROFILE;
    @VersionField(id=1) private final int versionCode;
    private static Comparator zzaa;
    @Field(getter="getScopes", id=2) private final ArrayList zzr;
    @Field(getter="getAccount", id=3) private Account zzs;
    @Field(getter="isIdTokenRequested", id=4) private boolean zzt;
    @Field(getter="isServerAuthCodeRequested", id=5) private final boolean zzu;
    @Field(getter="isForceCodeForRefreshToken", id=6) private final boolean zzv;
    @Field(getter="getServerClientId", id=7) private String zzw;
    @Field(getter="getHostedDomain", id=8) private String zzx;
    @Field(getter="getExtensions", id=9) private ArrayList zzy;
    private Map zzz;

    static {
        GoogleSignInOptions.SCOPE_PROFILE = new Scope("profile");
        GoogleSignInOptions.SCOPE_EMAIL = new Scope("email");
        GoogleSignInOptions.SCOPE_OPEN_ID = new Scope("openid");
        GoogleSignInOptions.SCOPE_GAMES_LITE = new Scope("https://www.googleapis.com/auth/games_lite");
        GoogleSignInOptions.SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
        GoogleSignInOptions.DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(GoogleSignInOptions.SCOPE_GAMES_LITE, new Scope[0]).build();
        GoogleSignInOptions.CREATOR = new GoogleSignInOptionsCreator();
        GoogleSignInOptions.zzaa = new zzb();
    }

    @Constructor GoogleSignInOptions(@Param(id=1) int arg11, @Param(id=2) ArrayList arg12, @Param(id=3) Account arg13, @Param(id=4) boolean arg14, @Param(id=5) boolean arg15, @Param(id=6) boolean arg16, @Param(id=7) String arg17, @Param(id=8) String arg18, @Param(id=9) ArrayList arg19) {
        this(arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, GoogleSignInOptions.zza(((List)arg19)));
    }

    private GoogleSignInOptions(int arg1, ArrayList arg2, Account arg3, boolean arg4, boolean arg5, boolean arg6, String arg7, String arg8, Map arg9) {
        super();
        this.versionCode = arg1;
        this.zzr = arg2;
        this.zzs = arg3;
        this.zzt = arg4;
        this.zzu = arg5;
        this.zzv = arg6;
        this.zzw = arg7;
        this.zzx = arg8;
        this.zzy = new ArrayList(arg9.values());
        this.zzz = arg9;
    }

    GoogleSignInOptions(int arg11, ArrayList arg12, Account arg13, boolean arg14, boolean arg15, boolean arg16, String arg17, String arg18, Map arg19, zzb arg20) {
        this(3, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19);
    }

    public boolean equals(Object arg4) {
        if(arg4 == null) {
            return 0;
        }

        try {
            if(this.zzy.size() <= 0) {
                if(((GoogleSignInOptions)arg4).zzy.size() > 0) {
                }
                else if(this.zzr.size() == ((GoogleSignInOptions)arg4).getScopes().size()) {
                    if(!this.zzr.containsAll(((GoogleSignInOptions)arg4).getScopes())) {
                    }
                    else {
                        if(this.zzs != null) {
                            if(this.zzs.equals(((GoogleSignInOptions)arg4).getAccount())) {
                                goto label_29;
                            }

                            return 0;
                        }
                        else if(((GoogleSignInOptions)arg4).getAccount() == null) {
                        }
                        else {
                            return 0;
                        }

                    label_29:
                        if(!TextUtils.isEmpty(this.zzw)) {
                            goto label_36;
                        }
                        else if(TextUtils.isEmpty(((GoogleSignInOptions)arg4).getServerClientId())) {
                        }
                        else {
                            return 0;
                        }

                    label_40:
                        if(this.zzv != ((GoogleSignInOptions)arg4).isForceCodeForRefreshToken()) {
                            return 0;
                        }

                        goto label_43;
                    label_36:
                        if(!this.zzw.equals(((GoogleSignInOptions)arg4).getServerClientId())) {
                            return 0;
                        }

                        goto label_40;
                    label_43:
                        if(this.zzt != ((GoogleSignInOptions)arg4).isIdTokenRequested()) {
                            return 0;
                        }

                        if(this.zzu != ((GoogleSignInOptions)arg4).isServerAuthCodeRequested()) {
                            return 0;
                        }

                        return 1;
                    }
                }
            }
        }
        catch(ClassCastException ) {
        }

        return 0;
    }

    public static GoogleSignInOptions fromJsonString(String arg15) {
        GoogleSignInOptions v1 = null;
        if(TextUtils.isEmpty(((CharSequence)arg15))) {
            return v1;
        }

        JSONObject v0 = new JSONObject(arg15);
        HashSet v15 = new HashSet();
        JSONArray v2 = v0.getJSONArray("scopes");
        int v3 = v2.length();
        int v4;
        for(v4 = 0; v4 < v3; ++v4) {
            ((Set)v15).add(new Scope(v2.getString(v4)));
        }

        String v2_1 = v0.optString("accountName", ((String)v1));
        Account v8 = !TextUtils.isEmpty(((CharSequence)v2_1)) ? new Account(v2_1, "com.google") : ((Account)v1);
        return new GoogleSignInOptions(3, new ArrayList(((Collection)v15)), v8, v0.getBoolean("idTokenRequested"), v0.getBoolean("serverAuthRequested"), v0.getBoolean("forceCodeForRefreshToken"), v0.optString("serverClientId", ((String)v1)), v0.optString("hostedDomain", ((String)v1)), new HashMap());
    }

    public Account getAccount() {
        return this.zzs;
    }

    public GoogleSignInOptionsExtensionParcelable getExtension(@TypeId int arg2) {
        return this.zzz.get(Integer.valueOf(arg2));
    }

    public ArrayList getExtensions() {
        return this.zzy;
    }

    public String getHostedDomain() {
        return this.zzx;
    }

    public Scope[] getScopeArray() {
        return this.zzr.toArray(new Scope[this.zzr.size()]);
    }

    public ArrayList getScopes() {
        return new ArrayList(this.zzr);
    }

    public String getServerClientId() {
        return this.zzw;
    }

    public boolean hasExtension(@TypeId int arg2) {
        return this.zzz.containsKey(Integer.valueOf(arg2));
    }

    public int hashCode() {
        ArrayList v0 = new ArrayList();
        ArrayList v1 = this.zzr;
        int v2 = v1.size();
        int v3 = 0;
        while(v3 < v2) {
            Object v4 = v1.get(v3);
            ++v3;
            v0.add(((Scope)v4).getScopeUri());
        }

        Collections.sort(((List)v0));
        return new HashAccumulator().addObject(v0).addObject(this.zzs).addObject(this.zzw).addBoolean(this.zzv).addBoolean(this.zzt).addBoolean(this.zzu).hash();
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zzv;
    }

    public boolean isIdTokenRequested() {
        return this.zzt;
    }

    public boolean isServerAuthCodeRequested() {
        return this.zzu;
    }

    public String toJson() {
        return this.zza().toString();
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.versionCode);
        SafeParcelWriter.writeTypedList(arg5, 2, this.getScopes(), false);
        SafeParcelWriter.writeParcelable(arg5, 3, this.getAccount(), arg6, false);
        SafeParcelWriter.writeBoolean(arg5, 4, this.isIdTokenRequested());
        SafeParcelWriter.writeBoolean(arg5, 5, this.isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(arg5, 6, this.isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(arg5, 7, this.getServerClientId(), false);
        SafeParcelWriter.writeString(arg5, 8, this.getHostedDomain(), false);
        SafeParcelWriter.writeTypedList(arg5, 9, this.getExtensions(), false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    private static Map zza(List arg3) {
        HashMap v0 = new HashMap();
        if(arg3 == null) {
            return ((Map)v0);
        }

        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            Object v1 = v3.next();
            ((Map)v0).put(Integer.valueOf(((GoogleSignInOptionsExtensionParcelable)v1).getType()), v1);
        }

        return ((Map)v0);
    }

    static ArrayList zza(GoogleSignInOptions arg0) {
        return arg0.zzr;
    }

    private final JSONObject zza() {
        JSONObject v0 = new JSONObject();
        try {
            JSONArray v1 = new JSONArray();
            Collections.sort(this.zzr, GoogleSignInOptions.zzaa);
            ArrayList v2 = this.zzr;
            int v3 = v2.size();
            int v4 = 0;
            while(v4 < v3) {
                Object v5 = v2.get(v4);
                ++v4;
                v1.put(((Scope)v5).getScopeUri());
            }

            v0.put("scopes", v1);
            if(this.zzs != null) {
                v0.put("accountName", this.zzs.name);
            }

            v0.put("idTokenRequested", this.zzt);
            v0.put("forceCodeForRefreshToken", this.zzv);
            v0.put("serverAuthRequested", this.zzu);
            if(!TextUtils.isEmpty(this.zzw)) {
                v0.put("serverClientId", this.zzw);
            }

            if(!TextUtils.isEmpty(this.zzx)) {
                v0.put("hostedDomain", this.zzx);
            }
        }
        catch(JSONException v0_1) {
            goto label_49;
        }

        return v0;
    label_49:
        throw new RuntimeException(((Throwable)v0_1));
    }

    static Map zzb(List arg0) {
        return GoogleSignInOptions.zza(arg0);
    }

    static boolean zzb(GoogleSignInOptions arg0) {
        return arg0.zzu;
    }

    static boolean zzc(GoogleSignInOptions arg0) {
        return arg0.zzv;
    }

    static boolean zzd(GoogleSignInOptions arg0) {
        return arg0.zzt;
    }

    static String zze(GoogleSignInOptions arg0) {
        return arg0.zzw;
    }

    static Account zzf(GoogleSignInOptions arg0) {
        return arg0.zzs;
    }

    static String zzg(GoogleSignInOptions arg0) {
        return arg0.zzx;
    }

    static ArrayList zzh(GoogleSignInOptions arg0) {
        return arg0.zzy;
    }
}

