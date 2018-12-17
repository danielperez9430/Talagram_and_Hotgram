package com.google.android.gms.common.api.internal;

import android.support.v4.f.a;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

final class zzy implements OnCompleteListener {
    zzy(zzw arg1, zzx arg2) {
        this(arg1);
    }

    private zzy(zzw arg1) {
        this.zzgu = arg1;
        super();
    }

    public final void onComplete(Task arg6) {
        ConnectionResult v0_1;
        zzw v6_3;
        Map v1_1;
        zzw.zza(this.zzgu).lock();
        try {
            if(zzw.zzb(this.zzgu)) {
                if(arg6.isSuccessful()) {
                    zzw.zza(this.zzgu, new a(zzw.zzc(this.zzgu).size()));
                    Iterator v6_1 = zzw.zzc(this.zzgu).values().iterator();
                    while(v6_1.hasNext()) {
                        zzw.zzd(this.zzgu).put(v6_1.next().zzm(), ConnectionResult.RESULT_SUCCESS);
                    }
                }
                else {
                    if((arg6.getException() instanceof AvailabilityException)) {
                        Exception v6_2 = arg6.getException();
                        if(zzw.zze(this.zzgu)) {
                            zzw.zza(this.zzgu, new a(zzw.zzc(this.zzgu).size()));
                            Iterator v0 = zzw.zzc(this.zzgu).values().iterator();
                            while(v0.hasNext()) {
                                Object v1 = v0.next();
                                zzh v2 = ((GoogleApi)v1).zzm();
                                ConnectionResult v3 = ((AvailabilityException)v6_2).getConnectionResult(((GoogleApi)v1));
                                if(zzw.zza(this.zzgu, ((zzv)v1), v3)) {
                                    v1_1 = zzw.zzd(this.zzgu);
                                    v3 = new ConnectionResult(16);
                                }
                                else {
                                    v1_1 = zzw.zzd(this.zzgu);
                                }

                                v1_1.put(v2, v3);
                            }
                        }
                        else {
                            zzw.zza(this.zzgu, ((AvailabilityException)v6_2).zzl());
                        }

                        v6_3 = this.zzgu;
                        v0_1 = zzw.zzf(this.zzgu);
                    }
                    else {
                        Log.e("ConnectionlessGAC", "Unexpected availability exception", arg6.getException());
                        zzw.zza(this.zzgu, Collections.emptyMap());
                        v6_3 = this.zzgu;
                        v0_1 = new ConnectionResult(8);
                    }

                    zzw.zza(v6_3, v0_1);
                }

                if(zzw.zzg(this.zzgu) != null) {
                    zzw.zzd(this.zzgu).putAll(zzw.zzg(this.zzgu));
                    zzw.zza(this.zzgu, zzw.zzf(this.zzgu));
                }

                if(zzw.zzh(this.zzgu) == null) {
                    zzw.zzi(this.zzgu);
                    zzw.zzj(this.zzgu);
                }
                else {
                    zzw.zza(this.zzgu, false);
                    zzw.zzk(this.zzgu).zzc(zzw.zzh(this.zzgu));
                }

                zzw.zzl(this.zzgu).signalAll();
            }
        }
        catch(Throwable v6) {
            zzw.zza(this.zzgu).unlock();
            throw v6;
        }

        zzw.zza(this.zzgu).unlock();
    }
}

