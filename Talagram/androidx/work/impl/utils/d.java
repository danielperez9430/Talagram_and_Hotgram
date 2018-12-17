package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.work.j;

public class d {
    public static void a(Context arg6, Class arg7, boolean arg8) {
        String v3_1;
        int v0 = 2;
        try {
            PackageManager v3 = arg6.getPackageManager();
            ComponentName v4 = new ComponentName(arg6, arg7.getName());
            int v6_1 = arg8 ? 1 : 2;
            v3.setComponentEnabledSetting(v4, v6_1, 1);
            String v6_2 = "PackageManagerHelper";
            v3_1 = "%s %s";
            Object[] v4_1 = new Object[v0];
            v4_1[0] = arg7.getName();
            String v5 = arg8 ? "enabled" : "disabled";
            v4_1[1] = v5;
            j.b(v6_2, String.format(v3_1, v4_1), new Throwable[0]);
        }
        catch(Exception v6) {
            v3_1 = "PackageManagerHelper";
            String v4_2 = "%s could not be %s";
            Object[] v0_1 = new Object[v0];
            v0_1[0] = arg7.getName();
            String v7 = arg8 ? "enabled" : "disabled";
            v0_1[1] = v7;
            j.b(v3_1, String.format(v4_2, v0_1), new Throwable[]{v6});
        }
    }
}

