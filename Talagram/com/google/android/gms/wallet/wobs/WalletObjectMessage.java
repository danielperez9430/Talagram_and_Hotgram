package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;

@Class(creator="WalletObjectMessageCreator") @Reserved(value={1}) public final class WalletObjectMessage extends AbstractSafeParcelable {
    public final class Builder {
        Builder(WalletObjectMessage arg1, zzm arg2) {
            this(arg1);
        }

        private Builder(WalletObjectMessage arg1) {
            this.zzhj = arg1;
            super();
        }

        public final WalletObjectMessage build() {
            return this.zzhj;
        }

        @Deprecated public final Builder setActionUri(UriData arg2) {
            this.zzhj.zzhh = arg2;
            return this;
        }

        public final Builder setBody(String arg2) {
            this.zzhj.zzhc = arg2;
            return this;
        }

        public final Builder setDisplayInterval(TimeInterval arg2) {
            this.zzhj.zzhg = arg2;
            return this;
        }

        public final Builder setHeader(String arg2) {
            this.zzhj.zzhb = arg2;
            return this;
        }

        @Deprecated public final Builder setImageUri(UriData arg2) {
            this.zzhj.zzhi = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) String zzhb;
    @Field(id=3) String zzhc;
    @Field(id=4) TimeInterval zzhg;
    @Field(id=5) @Deprecated UriData zzhh;
    @Field(id=6) @Deprecated UriData zzhi;

    static {
        WalletObjectMessage.CREATOR = new zzn();
    }

    @Constructor WalletObjectMessage(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) TimeInterval arg3, @Param(id=5) UriData arg4, @Param(id=6) UriData arg5) {
        super();
        this.zzhb = arg1;
        this.zzhc = arg2;
        this.zzhg = arg3;
        this.zzhh = arg4;
        this.zzhi = arg5;
    }

    WalletObjectMessage() {
        super();
    }

    @Deprecated public final UriData getActionUri() {
        return this.zzhh;
    }

    public final String getBody() {
        return this.zzhc;
    }

    public final TimeInterval getDisplayInterval() {
        return this.zzhg;
    }

    public final String getHeader() {
        return this.zzhb;
    }

    @Deprecated public final UriData getImageUri() {
        return this.zzhi;
    }

    public static Builder newBuilder() {
        return new Builder(new WalletObjectMessage(), null);
    }

    public final void writeToParcel(Parcel arg5, int arg6) {
        int v0 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeString(arg5, 2, this.zzhb, false);
        SafeParcelWriter.writeString(arg5, 3, this.zzhc, false);
        SafeParcelWriter.writeParcelable(arg5, 4, this.zzhg, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 5, this.zzhh, arg6, false);
        SafeParcelWriter.writeParcelable(arg5, 6, this.zzhi, arg6, false);
        SafeParcelWriter.finishObjectHeader(arg5, v0);
    }
}

