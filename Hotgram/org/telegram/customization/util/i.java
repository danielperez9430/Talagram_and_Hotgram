package org.telegram.customization.util;

public class i {
    public static String a(String arg3) {
        if(arg3 != null) {
            if(arg3.length() < 7) {
            }
            else {
                int v1 = 2;
                if((arg3.startsWith("00")) && arg3.length() > v1) {
                    arg3 = arg3.substring(v1);
                }

                if((arg3.startsWith("+")) && arg3.length() > 1) {
                    arg3 = arg3.substring(1);
                }

                if((arg3.startsWith("98")) && arg3.length() > v1) {
                    arg3 = arg3.substring(v1);
                }

                if(arg3.startsWith("0")) {
                    return arg3;
                }

                arg3 = "0" + arg3;
            }
        }

        return arg3;
    }
}

