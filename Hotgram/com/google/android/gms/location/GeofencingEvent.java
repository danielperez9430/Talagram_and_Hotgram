package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.internal.location.zzbh;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeofencingEvent {
    private final int errorCode;
    private final int zzam;
    private final List zzan;
    private final Location zzao;

    private GeofencingEvent(int arg1, int arg2, List arg3, Location arg4) {
        super();
        this.errorCode = arg1;
        this.zzam = arg2;
        this.zzan = arg3;
        this.zzao = arg4;
    }

    public static GeofencingEvent fromIntent(Intent arg7) {
        ArrayList v0_1;
        GeofencingEvent v0 = null;
        if(arg7 == null) {
            return v0;
        }

        int v2 = -1;
        int v1 = arg7.getIntExtra("gms_error_code", v2);
        int v3 = arg7.getIntExtra("com.google.android.location.intent.extra.transition", v2);
        if(v3 != v2 && (v3 == 1 || v3 == 2 || v3 == 4)) {
            v2 = v3;
        }

        Serializable v3_1 = arg7.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if(v3_1 == null) {
        }
        else {
            v0_1 = new ArrayList(((ArrayList)v3_1).size());
            int v4 = ((ArrayList)v3_1).size();
            int v5 = 0;
            while(v5 < v4) {
                Object v6 = ((ArrayList)v3_1).get(v5);
                ++v5;
                v0_1.add(zzbh.zza(((byte[])v6)));
            }
        }

        return new GeofencingEvent(v1, v2, ((List)v0_1), arg7.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public int getGeofenceTransition() {
        return this.zzam;
    }

    public List getTriggeringGeofences() {
        return this.zzan;
    }

    public Location getTriggeringLocation() {
        return this.zzao;
    }

    public boolean hasError() {
        if(this.errorCode != -1) {
            return 1;
        }

        return 0;
    }
}

