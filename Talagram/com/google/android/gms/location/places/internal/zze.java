package com.google.android.gms.location.places.internal;

import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public final class zze extends zzav implements AutocompletePrediction {
    public zze(DataHolder arg1, int arg2) {
        super(arg1, arg2);
    }

    public final Object freeze() {
        return new zzb(this.getPlaceId(), this.getPlaceTypes(), ((zzav)this).zzc("ap_personalization_type", 6), Preconditions.checkNotNull(this.zzaa()), this.zzad(), this.zzab(), this.zzae(), this.zzac(), this.zzaf());
    }

    public final CharSequence getFullText(CharacterStyle arg3) {
        return zzh.zzb(this.zzaa(), this.zzad(), arg3);
    }

    public final String getPlaceId() {
        return ((zzav)this).zzc("ap_place_id", null);
    }

    public final List getPlaceTypes() {
        return ((zzav)this).zzb("ap_place_types", Collections.emptyList());
    }

    public final CharSequence getPrimaryText(CharacterStyle arg3) {
        return zzh.zzb(this.zzab(), this.zzae(), arg3);
    }

    public final CharSequence getSecondaryText(CharacterStyle arg3) {
        return zzh.zzb(this.zzac(), this.zzaf(), arg3);
    }

    private final String zzaa() {
        return ((zzav)this).zzc("ap_description", "");
    }

    private final String zzab() {
        return ((zzav)this).zzc("ap_primary_text", "");
    }

    private final String zzac() {
        return ((zzav)this).zzc("ap_secondary_text", "");
    }

    private final List zzad() {
        return ((zzav)this).zzb("ap_matched_subscriptions", zzc.CREATOR, Collections.emptyList());
    }

    private final List zzae() {
        return ((zzav)this).zzb("ap_primary_text_matched", zzc.CREATOR, Collections.emptyList());
    }

    private final List zzaf() {
        return ((zzav)this).zzb("ap_secondary_text_matched", zzc.CREATOR, Collections.emptyList());
    }
}

