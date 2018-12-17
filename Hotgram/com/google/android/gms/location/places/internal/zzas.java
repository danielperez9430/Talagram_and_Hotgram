package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class zzas extends zzav implements Place {
    private final String placeId;
    private final zzah zzgo;

    public zzas(DataHolder arg7, int arg8) {
        zzah v7_1;
        int v7;
        super(arg7, arg8);
        this.placeId = ((zzav)this).zzc("place_id", "");
        if(this.getPlaceTypes().size() <= 0) {
            if(this.getPhoneNumber() != null && this.getPhoneNumber().length() > 0) {
                goto label_27;
            }

            if(this.getWebsiteUri() != null && !this.getWebsiteUri().equals(Uri.EMPTY)) {
                goto label_27;
            }

            if(this.getRating() >= 0f) {
                goto label_27;
            }

            if(this.getPriceLevel() >= 0) {
                goto label_27;
            }

            v7 = 0;
        }
        else {
        label_27:
            v7 = 1;
        }

        String v8 = null;
        if(v7 != 0) {
            v7_1 = null;
            List v1 = this.getPlaceTypes();
            if(this.getPhoneNumber() != null) {
                v8 = this.getPhoneNumber().toString();
            }

            super(v1, v8, this.getWebsiteUri(), this.getRating(), this.getPriceLevel());
        }
        else {
            v7_1 = ((zzah)v8);
        }

        this.zzgo = v7_1;
    }

    public final Object freeze() {
        zzb v0 = new zzb().zze(this.getAddress().toString()).zzd(this.zzai()).zzc(this.getId());
        String v1 = "place_is_permanently_closed";
        boolean v1_1 = !((zzav)this).hasColumn(v1) || (((zzav)this).hasNull(v1)) ? false : ((zzav)this).getBoolean(v1);
        PlaceEntity v0_1 = v0.zzb(v1_1).zzb(this.getLatLng()).zzb(((zzav)this).zzb("place_level_number", 0f)).zzd(this.getName().toString()).zzf(this.getPhoneNumber().toString()).zzf(this.getPriceLevel()).zzc(this.getRating()).zzc(this.getPlaceTypes()).zzb(this.getViewport()).zzb(this.getWebsiteUri()).zzb(((zzav)this).zzb("place_opening_hours", zzam.CREATOR)).zzb(this.zzgo).zzg(((zzav)this).zzc("place_adr_address", "")).zzag();
        v0_1.setLocale(this.getLocale());
        return v0_1;
    }

    public final CharSequence getAddress() {
        return ((zzav)this).zzc("place_address", "");
    }

    public final CharSequence getAttributions() {
        return zzh.zzg(this.zzai());
    }

    public final String getId() {
        return this.placeId;
    }

    public final LatLng getLatLng() {
        return ((zzav)this).zzb("place_lat_lng", LatLng.CREATOR);
    }

    public final Locale getLocale() {
        String v0 = ((zzav)this).zzc("place_locale_language", "");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            return new Locale(v0, ((zzav)this).zzc("place_locale_country", ""));
        }

        v0 = ((zzav)this).zzc("place_locale", "");
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            return new Locale(v0);
        }

        return Locale.getDefault();
    }

    public final CharSequence getName() {
        return ((zzav)this).zzc("place_name", "");
    }

    public final CharSequence getPhoneNumber() {
        return ((zzav)this).zzc("place_phone_number", "");
    }

    public final List getPlaceTypes() {
        return ((zzav)this).zzb("place_types", Collections.emptyList());
    }

    public final int getPriceLevel() {
        return ((zzav)this).zzc("place_price_level", -1);
    }

    public final float getRating() {
        return ((zzav)this).zzb("place_rating", -1f);
    }

    public final LatLngBounds getViewport() {
        return ((zzav)this).zzb("place_viewport", LatLngBounds.CREATOR);
    }

    public final Uri getWebsiteUri() {
        String v1 = null;
        String v0 = ((zzav)this).zzc("place_website_uri", v1);
        if(v0 == null) {
            return ((Uri)v1);
        }

        return Uri.parse(v0);
    }

    private final List zzai() {
        return ((zzav)this).zzc("place_attributions", Collections.emptyList());
    }
}

