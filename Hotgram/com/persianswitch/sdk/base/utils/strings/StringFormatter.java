package com.persianswitch.sdk.base.utils.strings;

import android.content.Context;
import com.persianswitch.sdk.R$string;

public class StringFormatter {
    public StringFormatter() {
        super();
    }

    public static String a(Context arg1, String arg2) {
        return StringFormatter.a(arg1.getString(string.asanpardakht_amount_unit_irr), arg2);
    }

    public static String a(String arg1, String arg2) {
        return StringFormatter.a(arg2) + " " + arg1;
    }

    private static String a(String arg3) {
        StringBuilder v0 = new StringBuilder();
        int v1;
        for(v1 = 0; v1 < arg3.length(); ++v1) {
            if(Character.isDigit(arg3.charAt(v1))) {
                if((arg3.length() - v1) % 3 == 0 && v1 > 0 && v1 != arg3.length() - 1) {
                    v0.append(",");
                }

                v0.append(arg3.charAt(v1));
            }
            else {
                v0.append(v1);
            }
        }

        return v0.toString();
    }
}

