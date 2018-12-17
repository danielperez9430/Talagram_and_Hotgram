package com.persianswitch.sdk.base.manager;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.view.ContextThemeWrapper;
import com.persianswitch.sdk.base.BaseSetting;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import java.util.Locale;

public final class LanguageManager {
    private static LanguageManager a;
    private final Context b;

    private LanguageManager(Context arg2) {
        super();
        if(LanguageManager.a == null) {
            this.b = arg2;
            return;
        }

        throw new InstantiationError();
    }

    public static String a() {
        return "fa";
    }

    public static LanguageManager a(Context arg1) {
        if(LanguageManager.a == null) {
            LanguageManager.a = new LanguageManager(arg1);
        }

        return LanguageManager.a;
    }

    public static Context a(Context arg3, Locale arg4) {
        Resources v0 = arg3.getResources();
        if(!StringUtils.a(LanguageManager.c(arg3).getLanguage(), arg4.getLanguage())) {
            Configuration v4 = LanguageManager.a(v0.getConfiguration(), arg4);
            if(LanguageManager.d()) {
                return arg3.createConfigurationContext(v4);
            }
            else {
                v0.updateConfiguration(v4, v0.getDisplayMetrics());
            }
        }

        return arg3;
    }

    private static Configuration a(Configuration arg1, Locale arg2) {
        Configuration v0 = new Configuration(arg1);
        if(LanguageManager.d()) {
            v0.setLocale(arg2);
        }
        else {
            v0.locale = arg2;
        }

        return v0;
    }

    public static void a(Context arg3, ContextThemeWrapper arg4) {
        Locale v0 = LanguageManager.c(arg3);
        Locale v1 = new Locale(LanguageManager.a(arg3).c());
        if(!StringUtils.a(v0.getLanguage(), v1.getLanguage())) {
            LanguageManager.a(arg4, LanguageManager.a(arg3.getResources().getConfiguration(), v1));
        }

        LanguageManager.b(arg3.getApplicationContext());
    }

    private static void a(ContextThemeWrapper arg1, Configuration arg2) {
        try {
            if(LanguageManager.d()) {
                arg1.applyOverrideConfiguration(arg2);
            }
            else {
                Resources v1 = arg1.getResources();
                v1.updateConfiguration(arg2, v1.getDisplayMetrics());
            }

            return;
        }
        catch(Throwable ) {
            return;
        }
    }

    public void a(String arg2) {
        if(("fa".equals(arg2)) || ("en".equals(arg2))) {
            BaseSetting.e(this.b, arg2);
        }
    }

    public static Context b(Context arg2) {
        return LanguageManager.a(arg2, new Locale(LanguageManager.a(arg2).c()));
    }

    public boolean b() {
        return "fa".equals(this.c());
    }

    public String c() {
        return BaseSetting.i(this.b);
    }

    private static Locale c(Context arg2) {
        Locale v2 = Build$VERSION.SDK_INT >= 24 ? arg2.getResources().getConfiguration().getLocales().get(0) : arg2.getResources().getConfiguration().locale;
        return v2;
    }

    private static boolean d() {
        boolean v0 = Build$VERSION.SDK_INT >= 23 ? true : false;
        return v0;
    }
}

