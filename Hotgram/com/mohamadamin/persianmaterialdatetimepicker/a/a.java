package com.mohamadamin.persianmaterialdatetimepicker.a;

import java.util.ArrayList;

public class a {
    public static String a(String arg2) {
        return arg2.replace("0", "۰").replace("1", "١").replace("2", "۲").replace("3", "۳").replace("4", "۴").replace("5", "۵").replace("6", "۶").replace("7", "۷").replace("8", "۸").replace("9", "۹");
    }

    public static ArrayList a(ArrayList arg2) {
        int v0;
        for(v0 = 0; v0 < arg2.size(); ++v0) {
            arg2.set(v0, a.a(arg2.get(v0)));
        }

        return arg2;
    }

    public static String[] a(String[] arg2) {
        int v0;
        for(v0 = 0; v0 < arg2.length; ++v0) {
            arg2[v0] = a.a(arg2[v0]);
        }

        return arg2;
    }

    public static String b(String arg2) {
        return arg2.replace("۰", "0").replace("١", "1").replace("۲", "2").replace("۳", "3").replace("۴", "4").replace("۵", "5").replace("۶", "6").replace("۷", "7").replace("۸", "8").replace("۹", "9");
    }
}

