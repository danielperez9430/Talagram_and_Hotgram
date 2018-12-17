package org.telegram.customization.i;

import java.util.ArrayList;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.UserConfig;
import utils.d;

public class c {
    public static void a(j arg1) {
        arg1.a(Integer.valueOf(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()));
    }

    public static void a(j arg1, long arg2) {
        ArrayList v0 = new ArrayList();
        v0.add(Long.valueOf(arg2));
        arg1.a(v0);
    }

    public static void a(j arg11, String arg12, Object arg13) {
        String v2 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().username;
        arg11.a(v2, UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().first_name + " " + UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().last_name, arg12, ApplicationLoader.applicationContext.getResources().getInteger(2131361792), 166, 12, utils.c.a(arg13), d.c(ApplicationLoader.applicationContext), arg12);
    }

    public static void b(j arg10, String arg11, Object arg12) {
        String v2 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().username;
        arg10.a(v2, UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().first_name + " " + UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().last_name, arg11, ApplicationLoader.applicationContext.getResources().getInteger(2131361792), 166, 12, utils.c.a(arg12), arg11);
    }

    public static void c(j arg10, String arg11, Object arg12) {
        String v2 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().username;
        arg10.b(v2, UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().first_name + " " + UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().last_name, arg11, ApplicationLoader.applicationContext.getResources().getInteger(2131361792), 166, 12, utils.c.a(arg12), arg11);
    }
}

