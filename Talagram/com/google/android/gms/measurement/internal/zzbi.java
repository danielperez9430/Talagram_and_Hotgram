package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzu;

final class zzbi implements Runnable {
    zzbi(zzbh arg1, zzu arg2, ServiceConnection arg3) {
        this.zzaoh = arg1;
        this.zzaof = arg2;
        this.zzaog = arg3;
        super();
    }

    public final void run() {
        String v2_1;
        zzar v1_2;
        zzbg v0 = this.zzaoh.zzaoe;
        String v1 = zzbh.zza(this.zzaoh);
        zzu v2 = this.zzaof;
        ServiceConnection v3 = this.zzaog;
        Bundle v1_1 = v0.zza(v1, v2);
        v0.zzadj.zzgn().zzaf();
        if(v1_1 != null) {
            long v4 = 0;
            long v8 = 1000;
            long v6 = v1_1.getLong("install_begin_timestamp_seconds", v4) * v8;
            if(v6 == v4) {
                v1_2 = v0.zzadj.zzgo().zzjd();
                v2_1 = "Service response is missing Install Referrer install timestamp";
            }
            else {
                v2_1 = v1_1.getString("install_referrer");
                if(v2_1 != null) {
                    if(v2_1.isEmpty()) {
                    }
                    else {
                        v0.zzadj.zzgo().zzjl().zzg("InstallReferrer API result", v2_1);
                        zzfk v10 = v0.zzadj.zzgm();
                        String v11 = "?";
                        v2_1 = String.valueOf(v2_1);
                        v2_1 = v2_1.length() != 0 ? v11.concat(v2_1) : new String(v11);
                        Bundle v2_2 = v10.zza(Uri.parse(v2_1));
                        if(v2_2 == null) {
                            v1_2 = v0.zzadj.zzgo().zzjd();
                            v2_1 = "No campaign params defined in install referrer result";
                            goto label_21;
                        }

                        String v10_1 = v2_2.getString("medium");
                        int v10_2 = v10_1 == null || ("(not set)".equalsIgnoreCase(v10_1)) || ("organic".equalsIgnoreCase(v10_1)) ? 0 : 1;
                        if(v10_2 != 0) {
                            long v10_3 = v1_1.getLong("referrer_click_timestamp_seconds", v4) * v8;
                            if(v10_3 == v4) {
                                v1_2 = v0.zzadj.zzgo().zzjd();
                                v2_1 = "Install Referrer is missing click timestamp for ad campaign";
                                goto label_21;
                            }
                            else {
                                v2_2.putLong("click_timestamp", v10_3);
                            }
                        }

                        if(v6 == v0.zzadj.zzgp().zzank.get()) {
                            v0.zzadj.zzgr();
                            v1_2 = v0.zzadj.zzgo().zzjl();
                            v2_1 = "Campaign has already been logged";
                            goto label_21;
                        }

                        v0.zzadj.zzgp().zzank.set(v6);
                        v0.zzadj.zzgr();
                        v0.zzadj.zzgo().zzjl().zzg("Logging Install Referrer campaign from sdk with ", "referrer API");
                        v2_2.putString("_cis", "referrer API");
                        v0.zzadj.zzge().logEvent("auto", "_cmp", v2_2);
                        goto label_114;
                    }
                }

                v1_2 = v0.zzadj.zzgo().zzjd();
                v2_1 = "No referrer defined in install referrer response";
            }

        label_21:
            v1_2.zzbx(v2_1);
        }

    label_114:
        if(v3 != null) {
            ConnectionTracker.getInstance().unbindService(v0.zzadj.getContext(), v3);
        }
    }
}

