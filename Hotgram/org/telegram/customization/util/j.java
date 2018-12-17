package org.telegram.customization.util;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import com.google.a.c.a;
import com.google.a.f;
import java.util.ArrayList;
import org.telegram.messenger.ApplicationLoader;
import utils.a.b;

public class j {
    public SharedPreferences$Editor a;
    private static j b;
    private SharedPreferences c;

    private j(Context arg2, String arg3) {
        super();
        this.c = arg2.getSharedPreferences(arg3, 0);
        this.a = this.c.edit();
    }

    public static Integer a() {
        return Integer.valueOf(j.a(ApplicationLoader.applicationContext).a("SP_FL"));
    }

    public int a(String arg3) {
        return this.c.getInt(arg3, 0);
    }

    public static j a(Context arg2) {
        if(j.b == null) {
            j.b = new j(arg2, "SP_MAIN");
        }

        return j.b;
    }

    public static void a(int arg2) {
        j.a(ApplicationLoader.applicationContext).a("SP_FL", arg2);
    }

    public void a(String arg2, int arg3) {
        this.a.putInt(arg2, arg3);
        this.a.commit();
    }

    public static void a(Context arg1, int arg2) {
        j.a(arg1).a("SP_MAIN_GHOST", arg2);
    }

    public static void a(Context arg1, String arg2) {
        j.a(arg1).a("SP_FILTERS", arg2);
    }

    public void a(String arg2, String arg3) {
        this.a.putString(arg2, arg3);
        this.a.commit();
    }

    public static void a(Context arg1, boolean arg2) {
        j.a(arg1).a("SP_IS_FIRST_TIME", arg2);
    }

    public void a(String arg2, boolean arg3) {
        this.a.putBoolean(arg2, arg3);
        this.a.commit();
    }

    public static void a(String arg1, Context arg2) {
        j.a(arg2).a("SP_SEARCH_TERM", arg1);
    }

    public static int b(Context arg2) {
        int v2;
        if(b.P(arg2) == 0) {
            return 0;
        }

        try {
            v2 = j.a(arg2).a("SP_MAIN_GHOST");
        }
        catch(Exception ) {
            v2 = 0;
        }

        return v2;
    }

    public static void b(Context arg1, boolean arg2) {
        j.a(arg1).a("SP_IS_SECOND_TIME", arg2);
    }

    public boolean b(String arg3) {
        return this.c.getBoolean(arg3, true);
    }

    public static void c(Context arg1, boolean arg2) {
        j.a(arg1).a("SP_MAIN_GHOST_TU", arg2);
    }

    public static boolean c(Context arg1) {
        return j.a(arg1).b("SP_IS_FIRST_TIME");
    }

    public String c(String arg3) {
        return this.c.getString(arg3, null);
    }

    public static void d(Context arg1, boolean arg2) {
        j.a(arg1).a("SP_IS_3_TIME", arg2);
    }

    public static boolean d(Context arg1) {
        return j.a(arg1).b("SP_IS_SECOND_TIME");
    }

    public static ArrayList e(Context arg3) {
        String v3 = j.a(arg3).c("SP_FILTERS");
        try {
            Object v3_1 = new f().a(v3, new a() {
            }.b());
            if(v3_1 != null && ((ArrayList)v3_1).size() > 0) {
                int v0;
                for(v0 = 0; v0 < ((ArrayList)v3_1).size(); ++v0) {
                    if(((ArrayList)v3_1).get(v0).getType() != 1) {
                        ((ArrayList)v3_1).remove(v0);
                        --v0;
                    }
                    else {
                        ((ArrayList)v3_1).get(v0).setClickable(true);
                    }
                }

                return ((ArrayList)v3_1);
            }

            return new ArrayList();
        }
        catch(Exception ) {
            return new ArrayList();
        }
    }

    public static void e(Context arg1, boolean arg2) {
        j.a(arg1).a("SP_MAIN_ADS_TU", arg2);
    }

    public static ArrayList f(Context arg4) {
        Object v4 = new f().a(j.a(arg4).c("SP_FILTERS"), new a() {
        }.b());
        if(v4 != null && ((ArrayList)v4).size() > 0) {
            int v0;
            for(v0 = 0; v0 < ((ArrayList)v4).size(); ++v0) {
                if(((ArrayList)v4).get(v0).getType() != 2) {
                    ((ArrayList)v4).remove(v0);
                    --v0;
                }
                else {
                    ((ArrayList)v4).get(v0).setClickable(true);
                }
            }

            return ((ArrayList)v4);
        }

        return new ArrayList();
    }

    public static void f(Context arg1, boolean arg2) {
        j.a(arg1).a("SP_MAIN_ADS_TU1", arg2);
    }

    public static String g(Context arg1) {
        return j.a(arg1).c("SP_SEARCH_TERM");
    }

    public static boolean h(Context arg1) {
        return j.a(arg1).b("SP_MAIN_GHOST_TU");
    }

    public static boolean i(Context arg1) {
        return j.a(arg1).b("SP_IS_3_TIME");
    }

    public static boolean j(Context arg1) {
        return j.a(arg1).b("SP_MAIN_ADS_TU");
    }

    public static boolean k(Context arg1) {
        return j.a(arg1).b("SP_MAIN_ADS_TU1");
    }
}

