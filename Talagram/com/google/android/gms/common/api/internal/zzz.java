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

final class zzz implements OnCompleteListener {
    private SignInConnectionListener zzgv;

    zzz(zzw arg1, SignInConnectionListener arg2) {
        this.zzgu = arg1;
        super();
        this.zzgv = arg2;
    }

    final void cancel() {
        this.zzgv.onComplete();
    }

    public final void onComplete(Task arg6) {
        Map v1_1;
        SignInConnectionListener v6_1;
        zzw.zza(this.zzgu).lock();
        try {
            if(!zzw.zzb(this.zzgu)) {
                v6_1 = this.zzgv;
            }
            else {
                if(arg6.isSuccessful()) {
                    zzw.zzb(this.zzgu, new a(zzw.zzm(this.zzgu).size()));
                    Iterator v6_2 = zzw.zzm(this.zzgu).values().iterator();
                    while(v6_2.hasNext()) {
                        zzw.zzg(this.zzgu).put(v6_2.next().zzm(), ConnectionResult.RESULT_SUCCESS);
                    }
                }
                else if((arg6.getException() instanceof AvailabilityException)) {
                    Exception v6_3 = arg6.getException();
                    if(zzw.zze(this.zzgu)) {
                        zzw.zzb(this.zzgu, new a(zzw.zzm(this.zzgu).size()));
                        Iterator v0 = zzw.zzm(this.zzgu).values().iterator();
                        while(v0.hasNext()) {
                            Object v1 = v0.next();
                            zzh v2 = ((GoogleApi)v1).zzm();
                            ConnectionResult v3 = ((AvailabilityException)v6_3).getConnectionResult(((GoogleApi)v1));
                            if(zzw.zza(this.zzgu, ((zzv)v1), v3)) {
                                v1_1 = zzw.zzg(this.zzgu);
                                v3 = new ConnectionResult(16);
                            }
                            else {
                                v1_1 = zzw.zzg(this.zzgu);
                            }

                            v1_1.put(v2, v3);
                        }
                    }
                    else {
                        zzw.zzb(this.zzgu, ((AvailabilityException)v6_3).zzl());
                    }
                }
                else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", arg6.getException());
                    zzw.zzb(this.zzgu, Collections.emptyMap());
                }

                if(this.zzgu.isConnected()) {
                    zzw.zzd(this.zzgu).putAll(zzw.zzg(this.zzgu));
                    if(zzw.zzf(this.zzgu) == null) {
                        zzw.zzi(this.zzgu);
                        zzw.zzj(this.zzgu);
                        zzw.zzl(this.zzgu).signalAll();
                    }
                }

                v6_1 = this.zzgv;
            }

            v6_1.onComplete();
        }
        catch(Throwable v6) {
            zzw.zza(this.zzgu).unlock();
            throw v6;
        }

        zzw.zza(this.zzgu).unlock();
    }
}

