package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources$Theme;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;

public class zzc {
    protected final Intent intent;

    public zzc(String arg2) {
        super();
        this.intent = new Intent(arg2);
        this.intent.setPackage("com.google.android.gms");
    }

    protected Intent build(Activity arg7) {
        Resources$Theme v0 = arg7.getTheme();
        TypedValue v1 = new TypedValue();
        TypedValue v2 = new TypedValue();
        if((v0.resolveAttribute(16843827, v1, true)) && !this.intent.hasExtra("primary_color")) {
            this.intent.putExtra("primary_color", v1.data);
        }

        if((v0.resolveAttribute(16843828, v2, true)) && !this.intent.hasExtra("primary_color_dark")) {
            this.intent.putExtra("primary_color_dark", v2.data);
        }

        GoogleApiAvailability.getInstance().verifyGooglePlayServicesIsAvailable(((Context)arg7), 12451000);
        return this.intent;
    }
}

