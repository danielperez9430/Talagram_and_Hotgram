package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlaceAutocomplete extends zzb {
    public class IntentBuilder extends zzc {
        public IntentBuilder(int arg4) {
            super("com.google.android.gms.location.places.ui.AUTOCOMPLETE");
            this.intent.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            this.intent.putExtra("mode", arg4);
            this.intent.putExtra("origin", 2);
        }

        public Intent build(Activity arg1) {
            return super.build(arg1);
        }

        public IntentBuilder setBoundsBias(LatLngBounds arg3) {
            if(arg3 != null) {
                this.intent.putExtra("bounds", ((Parcelable)arg3));
            }
            else {
                this.intent.removeExtra("bounds");
            }

            return this;
        }

        public IntentBuilder setFilter(AutocompleteFilter arg3) {
            if(arg3 != null) {
                this.intent.putExtra("filter", ((Parcelable)arg3));
            }
            else {
                this.intent.removeExtra("filter");
            }

            return this;
        }

        public final IntentBuilder zzg(int arg3) {
            this.intent.putExtra("origin", 1);
            return this;
        }

        public final IntentBuilder zzh(String arg3) {
            if(arg3 != null) {
                this.intent.putExtra("initial_query", arg3);
            }
            else {
                this.intent.removeExtra("initial_query");
            }

            return this;
        }
    }

    public static final int MODE_FULLSCREEN = 1;
    public static final int MODE_OVERLAY = 2;
    public static final int RESULT_ERROR = 2;

    private PlaceAutocomplete() {
        super();
    }

    public static Place getPlace(Context arg0, Intent arg1) {
        return zzb.getPlace(arg0, arg1);
    }

    public static Status getStatus(Context arg0, Intent arg1) {
        return zzb.getStatus(arg0, arg1);
    }
}

