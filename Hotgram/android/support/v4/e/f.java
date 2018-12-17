package android.support.v4.e;

import android.os.Build$VERSION;
import android.text.TextUtils;
import java.util.Locale;

public final class f {
    private static final Locale a;

    static {
        f.a = new Locale("", "");
    }

    public static int a(Locale arg2) {
        if(Build$VERSION.SDK_INT >= 17) {
            return TextUtils.getLayoutDirectionFromLocale(arg2);
        }

        if(arg2 != null && !arg2.equals(f.a)) {
            String v0 = b.a(arg2);
            if(v0 == null) {
                return f.b(arg2);
            }
            else {
                if(!v0.equalsIgnoreCase("Arab") && !v0.equalsIgnoreCase("Hebr")) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    private static int b(Locale arg1) {
        switch(Character.getDirectionality(arg1.getDisplayName(arg1).charAt(0))) {
            case 1: 
            case 2: {
                return 1;
            }
        }

        return 0;
    }
}

