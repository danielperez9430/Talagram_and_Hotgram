package com.f.a.e;

public class d {
    public static boolean a(String arg3) {
        if(arg3 == null) {
            return 0;
        }

        int v1;
        for(v1 = 0; v1 < arg3.length(); ++v1) {
            if(!Character.isDigit(arg3.charAt(v1))) {
                return 0;
            }
        }

        return 1;
    }

    public static boolean a(String arg4, String[] arg5) {
        if(arg4 == null) {
            return 0;
        }

        int v1 = arg5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(arg4.startsWith(arg5[v2])) {
                return 1;
            }
        }

        return 0;
    }

    public static String b(String arg1) {
        if(d.c(arg1)) {
            arg1 = null;
        }

        return arg1;
    }

    public static boolean c(String arg0) {
        boolean v0 = arg0 == null || arg0.trim().length() == 0 ? true : false;
        return v0;
    }

    public static String d(String arg1) {
        if(d.c(arg1)) {
            return null;
        }

        if("American Express".equalsIgnoreCase(arg1)) {
            return "American Express";
        }

        if("MasterCard".equalsIgnoreCase(arg1)) {
            return "MasterCard";
        }

        if("Diners Club".equalsIgnoreCase(arg1)) {
            return "Diners Club";
        }

        if("Discover".equalsIgnoreCase(arg1)) {
            return "Discover";
        }

        if("JCB".equalsIgnoreCase(arg1)) {
            return "JCB";
        }

        if("Visa".equalsIgnoreCase(arg1)) {
            return "Visa";
        }

        return "Unknown";
    }

    public static String e(String arg1) {
        if(d.c(arg1)) {
            return null;
        }

        if("credit".equalsIgnoreCase(arg1)) {
            return "credit";
        }

        if("debit".equalsIgnoreCase(arg1)) {
            return "debit";
        }

        if("prepaid".equalsIgnoreCase(arg1)) {
            return "prepaid";
        }

        return "unknown";
    }

    public static String f(String arg1) {
        if("card".equals(arg1)) {
            return "card";
        }

        return null;
    }
}

