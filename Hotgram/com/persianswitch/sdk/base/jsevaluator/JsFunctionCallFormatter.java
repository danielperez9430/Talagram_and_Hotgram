package com.persianswitch.sdk.base.jsevaluator;

public class JsFunctionCallFormatter {
    public JsFunctionCallFormatter() {
        super();
    }

    public static String a(Object arg3) {
        String v0;
        if((arg3 instanceof String)) {
            v0 = String.format("\"%s\"", ((String)arg3).replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n"));
        }
        else {
            try {
                Double.parseDouble(arg3.toString());
                return arg3.toString();
            }
            catch(NumberFormatException ) {
                return v0;
            }
        }

        return v0;
    }

    public static String a(String arg6, Object[] arg7) {
        StringBuilder v0 = new StringBuilder();
        int v1 = arg7.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = arg7[v3];
            if(v0.length() > 0) {
                v0.append(", ");
            }

            v0.append(JsFunctionCallFormatter.a(v4));
        }

        return String.format("%s(%s)", arg6, v0);
    }
}

