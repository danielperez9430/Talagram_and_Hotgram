package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.wallet.R$styleable;

@Class(creator="WalletFragmentOptionsCreator") @Reserved(value={1}) public final class WalletFragmentOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        Builder(WalletFragmentOptions arg1, zze arg2) {
            this(arg1);
        }

        private Builder(WalletFragmentOptions arg1) {
            this.zzgc = arg1;
            super();
        }

        public final WalletFragmentOptions build() {
            return this.zzgc;
        }

        public final Builder setEnvironment(int arg2) {
            WalletFragmentOptions.zza(this.zzgc, arg2);
            return this;
        }

        public final Builder setFragmentStyle(int arg3) {
            WalletFragmentOptions.zza(this.zzgc, new WalletFragmentStyle().setStyleResourceId(arg3));
            return this;
        }

        public final Builder setFragmentStyle(WalletFragmentStyle arg2) {
            WalletFragmentOptions.zza(this.zzgc, arg2);
            return this;
        }

        public final Builder setMode(int arg2) {
            WalletFragmentOptions.zzc(this.zzgc, arg2);
            return this;
        }

        public final Builder setTheme(int arg2) {
            WalletFragmentOptions.zzb(this.zzgc, arg2);
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(defaultValueUnchecked="com.google.android.gms.wallet.WalletConstants.ENVIRONMENT_PRODUCTION", getter="getEnvironment", id=2) private int environment;
    @Field(defaultValueUnchecked="com.google.android.gms.wallet.fragment.WalletFragmentMode.BUY_BUTTON", getter="getMode", id=5) private int mode;
    @Field(defaultValueUnchecked="com.google.android.gms.wallet.WalletConstants.THEME_DARK", getter="getTheme", id=3) private int theme;
    @Field(getter="getFragmentStyle", id=4) private WalletFragmentStyle zzgb;

    static {
        WalletFragmentOptions.CREATOR = new zzf();
    }

    @Constructor WalletFragmentOptions(@Param(id=2) int arg1, @Param(id=3) int arg2, @Param(id=4) WalletFragmentStyle arg3, @Param(id=5) int arg4) {
        super();
        this.environment = arg1;
        this.theme = arg2;
        this.zzgb = arg3;
        this.mode = arg4;
    }

    private WalletFragmentOptions() {
        super();
        this.environment = 3;
        this.zzgb = new WalletFragmentStyle();
    }

    public final int getEnvironment() {
        return this.environment;
    }

    public final WalletFragmentStyle getFragmentStyle() {
        return this.zzgb;
    }

    public final int getMode() {
        return this.mode;
    }

    public final int getTheme() {
        return this.theme;
    }

    public static Builder newBuilder() {
        return new Builder(new WalletFragmentOptions(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 2, this.getEnvironment());
        SafeParcelWriter.writeInt(arg5, 3, this.getTheme());
        SafeParcelWriter.writeParcelable(arg5, 4, this.getFragmentStyle(), arg6, false);
        SafeParcelWriter.writeInt(arg5, 5, this.getMode());
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }

    public final void zza(Context arg2) {
        if(this.zzgb != null) {
            this.zzgb.zza(arg2);
        }
    }

    public static WalletFragmentOptions zza(Context arg5, AttributeSet arg6) {
        TypedArray v6 = arg5.obtainStyledAttributes(arg6, styleable.WalletFragmentOptions);
        int v0 = v6.getInt(styleable.WalletFragmentOptions_appTheme, 0);
        int v2 = v6.getInt(styleable.WalletFragmentOptions_environment, 1);
        int v1 = v6.getResourceId(styleable.WalletFragmentOptions_fragmentStyle, 0);
        int v3 = v6.getInt(styleable.WalletFragmentOptions_fragmentMode, 1);
        v6.recycle();
        WalletFragmentOptions v6_1 = new WalletFragmentOptions();
        v6_1.theme = v0;
        v6_1.environment = v2;
        v6_1.zzgb = new WalletFragmentStyle().setStyleResourceId(v1);
        v6_1.zzgb.zza(arg5);
        v6_1.mode = v3;
        return v6_1;
    }

    static int zza(WalletFragmentOptions arg0, int arg1) {
        arg0.environment = arg1;
        return arg1;
    }

    static WalletFragmentStyle zza(WalletFragmentOptions arg0, WalletFragmentStyle arg1) {
        arg0.zzgb = arg1;
        return arg1;
    }

    static int zzb(WalletFragmentOptions arg0, int arg1) {
        arg0.theme = arg1;
        return arg1;
    }

    static int zzc(WalletFragmentOptions arg0, int arg1) {
        arg0.mode = arg1;
        return arg1;
    }
}

