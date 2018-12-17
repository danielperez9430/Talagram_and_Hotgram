package android.support.v4.f;

import android.os.Build$VERSION;
import java.util.Arrays;
import java.util.Objects;

public class i {
    public static boolean a(Object arg2, Object arg3) {
        boolean v2;
        if(Build$VERSION.SDK_INT >= 19) {
            return Objects.equals(arg2, arg3);
        }

        if(arg2 != arg3) {
            if(arg2 != null && (arg2.equals(arg3))) {
                goto label_12;
            }

            v2 = false;
        }
        else {
        label_12:
            v2 = true;
        }

        return v2;
    }

    public static int a(Object[] arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return Objects.hash(arg2);
        }

        return Arrays.hashCode(arg2);
    }
}

