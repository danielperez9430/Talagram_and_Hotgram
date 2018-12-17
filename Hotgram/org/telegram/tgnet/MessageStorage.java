package org.telegram.tgnet;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build$VERSION;
import org.telegram.customization.j.a.a;

public class MessageStorage {
    public MessageStorage() {
        super();
    }

    public static long getMessageCount() {
        long v2;
        try {
            Application v0_1 = new a().app;
            Signature[] v1 = null;
            if(Build$VERSION.SDK_INT >= 28) {
                try {
                    v1 = ((Context)v0_1).getPackageManager().getPackageInfo(((Context)v0_1).getPackageName(), 134217728).signingInfo.getApkContentsSigners();
                    goto label_14;
                }
                catch(Throwable ) {
                label_14:
                    if(v1 == null) {
                        try {
                            v1 = ((Context)v0_1).getPackageManager().getPackageInfo(((Context)v0_1).getPackageName(), 64).signatures;
                        label_20:
                            v2 = 1;
                            int v0_2 = v1.length;
                            int v4;
                            for(v4 = 0; true; ++v4) {
                                if(v4 >= v0_2) {
                                    return v2;
                                }

                                v2 *= ((long)v1[v4].hashCode());
                            }
                        }
                        catch(PackageManager$NameNotFoundException v0) {
                            goto label_32;
                        }
                    }

                    goto label_20;
                    return v2;
                }
            }

            goto label_14;
        }
        catch(PackageManager$NameNotFoundException v0) {
        }

    label_32:
        v0.printStackTrace();
        return 0;
    }

    public static void log(String arg0) {
    }
}

