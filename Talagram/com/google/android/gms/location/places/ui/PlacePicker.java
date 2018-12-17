package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlacePicker extends zzb {
    public class IntentBuilder extends zzc {
        public IntentBuilder() {
            super("com.google.android.gms.location.places.ui.PICK_PLACE");
            this.intent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        }

        public Intent build(Activity arg1) {
            return super.build(arg1);
        }

        public IntentBuilder setLatLngBounds(LatLngBounds arg3) {
            Preconditions.checkNotNull(arg3);
            SafeParcelableSerializer.serializeToIntentExtra(((SafeParcelable)arg3), this.intent, "latlng_bounds");
            return this;
        }
    }

    public static final int RESULT_ERROR = 2;

    private PlacePicker() {
        super();
    }

    @Deprecated public static String getAttributions(Intent arg1) {
        return arg1.getStringExtra("third_party_attributions");
    }

    public static LatLngBounds getLatLngBounds(Intent arg2) {
        return SafeParcelableSerializer.deserializeFromIntentExtra(arg2, "final_latlng_bounds", LatLngBounds.CREATOR);
    }

    public static Place getPlace(Context arg0, Intent arg1) {
        return zzb.getPlace(arg0, arg1);
    }

    @Deprecated public static Place getPlace(Intent arg0, Context arg1) {
        return zzb.getPlace(arg1, arg0);
    }
}

