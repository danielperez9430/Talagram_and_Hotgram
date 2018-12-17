package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Class(creator="CartCreator") @Reserved(value={1}) public final class Cart extends AbstractSafeParcelable implements ReflectedParcelable {
    public final class Builder {
        Builder(Cart arg1, zzf arg2) {
            this(arg1);
        }

        private Builder(Cart arg1) {
            this.zzar = arg1;
            super();
        }

        public final Builder addLineItem(LineItem arg2) {
            this.zzar.zzaq.add(arg2);
            return this;
        }

        public final Cart build() {
            return this.zzar;
        }

        public final Builder setCurrencyCode(String arg2) {
            this.zzar.zzap = arg2;
            return this;
        }

        public final Builder setLineItems(List arg2) {
            this.zzar.zzaq.clear();
            this.zzar.zzaq.addAll(((Collection)arg2));
            return this;
        }

        public final Builder setTotalPrice(String arg2) {
            this.zzar.zzao = arg2;
            return this;
        }
    }

    public static final Parcelable$Creator CREATOR;
    @Field(id=2) String zzao;
    @Field(id=3) String zzap;
    @Field(defaultValueUnchecked="new java.util.ArrayList<LineItem>()", id=4) ArrayList zzaq;

    static {
        Cart.CREATOR = new zzg();
    }

    @Constructor Cart(@Param(id=2) String arg1, @Param(id=3) String arg2, @Param(id=4) ArrayList arg3) {
        super();
        this.zzao = arg1;
        this.zzap = arg2;
        this.zzaq = arg3;
    }

    Cart() {
        super();
        this.zzaq = new ArrayList();
    }

    public final String getCurrencyCode() {
        return this.zzap;
    }

    public final ArrayList getLineItems() {
        return this.zzaq;
    }

    public final String getTotalPrice() {
        return this.zzao;
    }

    public static Builder newBuilder() {
        return new Builder(new Cart(), null);
    }

    public final void writeToParcel(Parcel arg4, int arg5) {
        arg5 = SafeParcelWriter.beginObjectHeader(arg4);
        SafeParcelWriter.writeString(arg4, 2, this.zzao, false);
        SafeParcelWriter.writeString(arg4, 3, this.zzap, false);
        SafeParcelWriter.writeTypedList(arg4, 4, this.zzaq, false);
        SafeParcelWriter.finishObjectHeader(arg4, arg5);
    }
}

