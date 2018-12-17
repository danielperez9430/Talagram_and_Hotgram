package org.telegram.customization.util;

import android.app.Activity;
import android.graphics.PorterDuff$Mode;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class l {
    public static boolean a(EditText arg15, TextInputLayout arg16, int arg17, Activity arg18) {
        int v11;
        TextInputLayout v0 = arg16;
        String v1 = arg15.getText().toString();
        try {
            if(!TextUtils.isEmpty(((CharSequence)v1)) && (TextUtils.isDigitsOnly(((CharSequence)v1)))) {
                long v5 = 0;
                if(Long.parseLong(v1) > v5) {
                    int v4 = 10;
                    if(v1.length() == v4 && v1.length() < 11) {
                        long v9 = v5;
                        int v8;
                        for(v8 = 1; true; ++v8) {
                            v11 = 9;
                            if(v8 > v11) {
                                break;
                            }

                            v9 += Long.parseLong(v1.substring(v8 - 1, v8)) * (((long)(11 - v8)));
                        }

                        v9 %= 11;
                        int v1_1 = Integer.parseInt(v1.substring(v11, v4));
                        if((((long)v1_1)) != 11 - v9 && (v9 != v5 || v1_1 != 0)) {
                            if(v9 != 1) {
                                goto label_51;
                            }

                            if(v1_1 != 1) {
                                goto label_51;
                            }
                        }

                        v0.setErrorEnabled(false);
                        arg15.getBackground().setColorFilter(arg18.getResources().getColor(2131100007), PorterDuff$Mode.SRC_ATOP);
                        return 1;
                    }
                }
            }

        label_51:
            v0.setError(arg18.getString(arg17));
            arg15.getBackground().setColorFilter(arg18.getResources().getColor(2131099769), PorterDuff$Mode.SRC_ATOP);
            return 0;
        }
        catch(Exception ) {
            return 0;
        }
    }

    public static boolean a(String arg7) {
        if(TextUtils.isEmpty(((CharSequence)arg7))) {
            return 0;
        }

        arg7 = arg7.replace(" ", "").replace("-", "").toUpperCase();
        Pattern v0 = Pattern.compile("IR[0-9]{24}");
        if(arg7.length() != 26) {
            return 0;
        }

        if(!v0.matcher(((CharSequence)arg7)).find()) {
            return 0;
        }

        String v2 = arg7.substring(4);
        int v3 = arg7.charAt(0) - 55;
        int v5 = arg7.charAt(1) - 55;
        StringBuilder v6 = new StringBuilder();
        v6.append(v2);
        v6.append(String.valueOf(v3));
        v6.append(String.valueOf(v5));
        v6.append(arg7.substring(2, 4));
        if(l.b(v6.toString()) != 1) {
            return 0;
        }

        return 1;
    }

    private static int b(String arg5) {
        while(arg5.length() > 2) {
            int v1 = 9;
            String v0 = arg5.length() >= v1 ? arg5.substring(0, v1) : arg5;
            arg5 = new BigInteger(v0).remainder(new BigInteger("97")).intValue() + arg5.substring(v0.length());
        }

        return new BigInteger(arg5).remainder(new BigInteger("97")).intValue();
    }
}

