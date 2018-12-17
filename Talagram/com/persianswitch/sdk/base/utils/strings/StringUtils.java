package com.persianswitch.sdk.base.utils.strings;

public class StringUtils {
    public StringUtils() {
        super();
    }

    public static boolean a(String arg0) {
        boolean v0 = arg0 == null || arg0.length() == 0 ? true : false;
        return v0;
    }

    public static String a(String arg1, Object[] arg2) {
        return StringUtils.a(arg1, true, arg2);
    }

    public static boolean a(String arg2, String arg3) {
        boolean v0 = true;
        if(arg2 == null && arg3 == null) {
            return 1;
        }

        if(arg2 != null) {
            if(arg3 == null || !arg2.equals(arg3)) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public static String a(CharSequence arg3) {
        if(arg3 == null) {
            return null;
        }

        StringBuilder v0 = new StringBuilder(arg3.length());
        int v1;
        for(v1 = 0; v1 < arg3.length(); ++v1) {
            if(Character.isDigit(arg3.charAt(v1))) {
                v0.append(arg3.charAt(v1));
            }
        }

        return v0.toString();
    }

    public static String a(Object arg0) {
        if(arg0 == null) {
            return "";
        }

        return arg0.toString();
    }

    public static String a(String arg3, boolean arg4, Object[] arg5) {
        if(arg5 == null) {
            return "";
        }

        if(arg3 == null) {
            arg3 = "";
        }

        StringBuilder v0 = new StringBuilder(arg5.length * (arg3.length() + 5));
        int v1;
        for(v1 = 0; v1 < arg5.length; ++v1) {
            if(!arg4) {
            label_22:
                if(arg5[v1] != null) {
                    v0.append(StringUtils.b(arg5[v1].toString()));
                    if(v1 != arg5.length - 1) {
                        v0.append(arg3);
                    }
                }
            }
            else if(arg5[v1] != null) {
                if(StringUtils.a(arg5[v1].toString())) {
                }
                else {
                    goto label_22;
                }
            }
        }

        return v0.toString().trim();
    }

    public static boolean a(String arg1, int arg2, int arg3) {
        boolean v1 = (StringUtils.a(arg1)) || arg1.length() < arg2 || arg1.length() > arg3 ? false : true;
        return v1;
    }

    public static String b(String arg0) {
        return StringUtils.a(arg0).trim();
    }

    public static Boolean c(String arg0) {
        try {
            return Boolean.valueOf(Boolean.parseBoolean(arg0));
        }
        catch(Exception ) {
            return null;
        }
    }

    public static Long d(String arg2) {
        try {
            return Long.valueOf(Long.parseLong(arg2));
        }
        catch(Exception ) {
            return null;
        }
    }

    public static Integer e(String arg0) {
        try {
            return Integer.valueOf(Integer.parseInt(arg0));
        }
        catch(Exception ) {
            return null;
        }
    }
}

