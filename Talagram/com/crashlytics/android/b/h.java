package com.crashlytics.android.b;

import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import c.a.a.a.a.a.d;
import c.a.a.a.c;
import c.a.a.a.l;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class h implements d {
    public h() {
        super();
    }

    public String a(Context arg9) {
        String v3_3;
        ZipInputStream v9_2;
        long v0 = System.nanoTime();
        String v2 = "";
        ZipInputStream v3 = null;
        try {
            v9_2 = this.a(arg9, "io.crash.air");
            goto label_5;
        }
        catch(Throwable v0_1) {
        }
        catch(PackageManager$NameNotFoundException ) {
        }
        catch(IOException v9) {
            goto label_34;
            try {
            label_5:
                v3_3 = this.a(v9_2);
                if(v9_2 == null) {
                    goto label_14;
                }

                goto label_7;
            }
            catch(Throwable v0_1) {
                v3 = v9_2;
            }
            catch(IOException v3_1) {
                IOException v7_1 = v3_1;
                v3 = v9_2;
                v9 = v7_1;
                try {
                label_34:
                    c.h().e("Beta", "Failed to read the APK file", ((Throwable)v9));
                    if(v3 == null) {
                        goto label_61;
                    }
                }
                catch(Throwable v0_1) {
                label_32:
                    goto label_79;
                }

                try {
                    v3.close();
                }
                catch(IOException v9) {
                label_60:
                    c.h().e("Beta", "Failed to close the APK file", ((Throwable)v9));
                }

                goto label_61;
            }
            catch(FileNotFoundException v3_2) {
                FileNotFoundException v7 = v3_2;
                v3 = v9_2;
                v9_1 = v7;
                try {
                label_42:
                    c.h().e("Beta", "Failed to find the APK file", ((Throwable)v9_1));
                    if(v3 == null) {
                        goto label_61;
                    }
                }
                catch(Throwable v0_1) {
                    goto label_32;
                }

                try {
                    v3.close();
                    goto label_61;
                }
                catch(IOException v9) {
                    goto label_60;
                }
            }
            catch(PackageManager$NameNotFoundException ) {
                v3 = v9_2;
                try {
                    c.h().a("Beta", "Beta by Crashlytics app is not installed");
                    if(v3 == null) {
                        goto label_61;
                    }
                }
                catch(Throwable v0_1) {
                    goto label_32;
                }

                try {
                    v3.close();
                    goto label_61;
                }
                catch(IOException v9) {
                    goto label_60;
                }
            }
        }
        catch(FileNotFoundException v9_1) {
            goto label_42;
        }

    label_79:
        if(v3 != null) {
            try {
                v3.close();
            }
            catch(IOException v9) {
                c.h().e("Beta", "Failed to close the APK file", ((Throwable)v9));
            }
        }

        throw v0_1;
        try {
        label_7:
            v9_2.close();
        }
        catch(IOException v9) {
            c.h().e("Beta", "Failed to close the APK file", ((Throwable)v9));
        }

    label_14:
        v2 = v3_3;
    label_61:
        double v0_2 = ((double)(System.nanoTime() - v0));
        Double.isNaN(v0_2);
        l v9_3 = c.h();
        v9_3.a("Beta", "Beta device token load took " + v0_2 / 1000000 + "ms");
        return v2;
    }

    ZipInputStream a(Context arg2, String arg3) {
        return new ZipInputStream(new FileInputStream(arg2.getPackageManager().getApplicationInfo(arg3, 0).sourceDir));
    }

    String a(ZipInputStream arg3) {
        ZipEntry v3 = arg3.getNextEntry();
        if(v3 != null) {
            String v3_1 = v3.getName();
            if(v3_1.startsWith("assets/com.crashlytics.android.beta/dirfactor-device-token=")) {
                return v3_1.substring("assets/com.crashlytics.android.beta/dirfactor-device-token=".length(), v3_1.length() - 1);
            }
        }

        return "";
    }

    public Object b(Context arg1) {
        return this.a(arg1);
    }
}

