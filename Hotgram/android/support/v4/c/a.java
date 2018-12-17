package android.support.v4.c;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build$VERSION;

public final class a {
    public static boolean a(ConnectivityManager arg2) {
        if(Build$VERSION.SDK_INT >= 16) {
            return arg2.isActiveNetworkMetered();
        }

        NetworkInfo v2 = arg2.getActiveNetworkInfo();
        if(v2 == null) {
            return 1;
        }

        switch(v2.getType()) {
            case 0: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                return 1;
            }
            case 1: 
            case 7: 
            case 9: {
                return 0;
            }
        }

        return 1;
    }
}

