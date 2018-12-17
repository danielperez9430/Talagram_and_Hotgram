package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build$VERSION;
import android.util.Log;

public final class u {
    public static Intent a(Activity arg4) {
        Intent v4;
        if(Build$VERSION.SDK_INT >= 16) {
            Intent v0 = arg4.getParentActivityIntent();
            if(v0 != null) {
                return v0;
            }
        }

        String v0_1 = u.b(arg4);
        Intent v1 = null;
        if(v0_1 == null) {
            return v1;
        }

        ComponentName v2 = new ComponentName(((Context)arg4), v0_1);
        try {
            v4 = u.b(((Context)arg4), v2) == null ? Intent.makeMainActivity(v2) : new Intent().setComponent(v2);
        }
        catch(PackageManager$NameNotFoundException ) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName \'" + v0_1 + "\' in manifest");
            return v1;
        }

        return v4;
    }

    public static Intent a(Context arg2, ComponentName arg3) {
        String v0 = u.b(arg2, arg3);
        if(v0 == null) {
            return null;
        }

        ComponentName v1 = new ComponentName(arg3.getPackageName(), v0);
        Intent v2 = u.b(arg2, v1) == null ? Intent.makeMainActivity(v1) : new Intent().setComponent(v1);
        return v2;
    }

    public static boolean a(Activity arg2, Intent arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.shouldUpRecreateTask(arg3);
        }

        String v2 = arg2.getIntent().getAction();
        boolean v2_1 = v2 == null || (v2.equals("android.intent.action.MAIN")) ? false : true;
        return v2_1;
    }

    public static String b(Activity arg1) {
        try {
            return u.b(((Context)arg1), arg1.getComponentName());
        }
        catch(PackageManager$NameNotFoundException v1) {
            throw new IllegalArgumentException(((Throwable)v1));
        }
    }

    public static String b(Context arg2, ComponentName arg3) {
        ActivityInfo v3 = arg2.getPackageManager().getActivityInfo(arg3, 128);
        if(Build$VERSION.SDK_INT >= 16) {
            String v0 = v3.parentActivityName;
            if(v0 != null) {
                return v0;
            }
        }

        String v1 = null;
        if(v3.metaData == null) {
            return v1;
        }

        String v3_1 = v3.metaData.getString("android.support.PARENT_ACTIVITY");
        if(v3_1 == null) {
            return v1;
        }

        if(v3_1.charAt(0) == 46) {
            v3_1 = arg2.getPackageName() + v3_1;
        }

        return v3_1;
    }

    public static void b(Activity arg2, Intent arg3) {
        if(Build$VERSION.SDK_INT >= 16) {
            arg2.navigateUpTo(arg3);
        }
        else {
            arg3.addFlags(67108864);
            arg2.startActivity(arg3);
            arg2.finish();
        }
    }
}

