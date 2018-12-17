package com.google.a.b.a.a;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class a {
    private static final TimeZone a;

    static {
        a.a = TimeZone.getTimeZone("UTC");
    }

    private static int a(String arg2, int arg3) {
        while(true) {
            if(arg3 >= arg2.length()) {
                goto label_11;
            }

            int v0 = arg2.charAt(arg3);
            if(v0 >= 48) {
                if(v0 > 57) {
                }
                else {
                    ++arg3;
                    continue;
                }
            }

            return arg3;
        }

        return arg3;
    label_11:
        return arg2.length();
    }

    private static int a(String arg4, int arg5, int arg6) {
        StringBuilder v1_1;
        int v2;
        int v0;
        if(arg5 >= 0 && arg6 <= arg4.length() && arg5 <= arg6) {
            int v1 = 10;
            if(arg5 < arg6) {
                v0 = arg5 + 1;
                v2 = Character.digit(arg4.charAt(arg5), v1);
                if(v2 >= 0) {
                    v2 = -v2;
                }
                else {
                    v1_1 = new StringBuilder();
                    v1_1.append("Invalid number: ");
                    v1_1.append(arg4.substring(arg5, arg6));
                    throw new NumberFormatException(v1_1.toString());
                }
            }
            else {
                v0 = arg5;
                v2 = 0;
            }

            while(true) {
                if(v0 >= arg6) {
                    goto label_44;
                }

                int v3 = v0 + 1;
                v0 = Character.digit(arg4.charAt(v0), v1);
                if(v0 < 0) {
                    break;
                }

                v2 = v2 * 10 - v0;
                v0 = v3;
            }

            v1_1 = new StringBuilder();
            v1_1.append("Invalid number: ");
            v1_1.append(arg4.substring(arg5, arg6));
            throw new NumberFormatException(v1_1.toString());
        label_44:
            return -v2;
        }

        throw new NumberFormatException(arg4);
    }

    public static Date a(String arg17, ParsePosition arg18) {
        StringBuilder v3_1;
        TimeZone v4_2;
        int v14;
        int v12_1;
        int v13;
        int v7_1;
        String v1 = arg17;
        ParsePosition v2 = arg18;
        try {
            int v0_1 = arg18.getIndex();
            int v3 = v0_1 + 4;
            v0_1 = a.a(v1, v0_1, v3);
            char v4 = '-';
            if(a.a(v1, v3, v4)) {
                ++v3;
            }

            int v5 = v3 + 2;
            v3 = a.a(v1, v3, v5);
            if(a.a(v1, v5, v4)) {
                ++v5;
            }

            int v6 = v5 + 2;
            v5 = a.a(v1, v5, v6);
            boolean v7 = a.a(v1, v6, 'T');
            if(!v7 && arg17.length() <= v6) {
                GregorianCalendar v4_1 = new GregorianCalendar(v0_1, v3 - 1, v5);
                v2.setIndex(v6);
                return ((Calendar)v4_1).getTime();
            }

            int v9 = 43;
            int v10 = 90;
            if(v7) {
                ++v6;
                v7_1 = v6 + 2;
                v6 = a.a(v1, v6, v7_1);
                char v12 = ':';
                if(a.a(v1, v7_1, v12)) {
                    ++v7_1;
                }

                v13 = v7_1 + 2;
                v7_1 = a.a(v1, v7_1, v13);
                if(a.a(v1, v13, v12)) {
                    ++v13;
                }

                if(arg17.length() <= v13) {
                    goto label_80;
                }

                v12_1 = v1.charAt(v13);
                if(v12_1 == v10) {
                    goto label_80;
                }

                if(v12_1 == v9) {
                    goto label_80;
                }

                if(v12_1 == v4) {
                    goto label_80;
                }

                v12_1 = v13 + 2;
                v13 = a.a(v1, v13, v12_1);
                v14 = 59;
                if(v13 <= v14 || v13 >= 63) {
                    v14 = v13;
                }
                else {
                }

                if(a.a(v1, v12_1, '.')) {
                    ++v12_1;
                    v13 = a.a(v1, v12_1 + 1);
                    int v15 = Math.min(v13, v12_1 + 3);
                    int v16 = a.a(v1, v12_1, v15);
                    switch(v15 - v12_1) {
                        case 1: {
                            v16 *= 100;
                            break;
                        }
                        case 2: {
                            v16 *= 10;
                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    v12_1 = v16;
                    goto label_82;
                }

                v13 = v12_1;
                v12_1 = 0;
            }
            else {
                v13 = v6;
                v6 = 0;
                v7_1 = 0;
            label_80:
                v12_1 = 0;
                v14 = 0;
            }

        label_82:
            if(arg17.length() > v13) {
                char v15_1 = v1.charAt(v13);
                int v11 = 5;
                if(v15_1 == v10) {
                    v4_2 = a.a;
                    ++v13;
                }
                else {
                    if(v15_1 != v9) {
                        if(v15_1 == v4) {
                        }
                        else {
                            v3_1 = new StringBuilder();
                            v3_1.append("Invalid time zone indicator \'");
                            v3_1.append(v15_1);
                            v3_1.append("\'");
                            throw new IndexOutOfBoundsException(v3_1.toString());
                        }
                    }

                    String v4_3 = v1.substring(v13);
                    if(v4_3.length() >= v11) {
                    }
                    else {
                        v4_3 = v4_3 + "00";
                    }

                    v13 += v4_3.length();
                    if(!"+0000".equals(v4_3)) {
                        if("+00:00".equals(v4_3)) {
                        }
                        else {
                            v4_3 = "GMT" + v4_3;
                            TimeZone v9_2 = TimeZone.getTimeZone(v4_3);
                            String v10_1 = v9_2.getID();
                            if(!v10_1.equals(v4_3)) {
                                if(v10_1.replace(":", "").equals(v4_3)) {
                                }
                                else {
                                    v3_1 = new StringBuilder();
                                    v3_1.append("Mismatching time zone indicator: ");
                                    v3_1.append(v4_3);
                                    v3_1.append(" given, resolves to ");
                                    v3_1.append(v9_2.getID());
                                    throw new IndexOutOfBoundsException(v3_1.toString());
                                }
                            }

                            v4_2 = v9_2;
                            goto label_155;
                        }
                    }

                    v4_2 = a.a;
                }

            label_155:
                GregorianCalendar v9_3 = new GregorianCalendar(v4_2);
                ((Calendar)v9_3).setLenient(false);
                ((Calendar)v9_3).set(1, v0_1);
                ((Calendar)v9_3).set(2, v3 - 1);
                ((Calendar)v9_3).set(5, v5);
                ((Calendar)v9_3).set(11, v6);
                ((Calendar)v9_3).set(12, v7_1);
                ((Calendar)v9_3).set(13, v14);
                ((Calendar)v9_3).set(14, v12_1);
                v2.setIndex(v13);
                return ((Calendar)v9_3).getTime();
            }

            throw new IllegalArgumentException("No time zone indicator");
        }
        catch(IllegalArgumentException v0) {
            v1 = v1 == null ? null : '\"' + v1 + "\'";
            String v3_2 = ((Exception)v0).getMessage();
            if(v3_2 == null || (v3_2.isEmpty())) {
                v3_2 = "(" + v0.getClass().getName() + ")";
            }

            StringBuilder v5_1 = new StringBuilder();
            v5_1.append("Failed to parse date [");
            v5_1.append(v1);
            v5_1.append("]: ");
            v5_1.append(v3_2);
            ParseException v4_4 = new ParseException(v5_1.toString(), arg18.getIndex());
            v4_4.initCause(((Throwable)v0));
            throw v4_4;
        }
    }

    private static boolean a(String arg1, int arg2, char arg3) {
        boolean v1 = arg2 >= arg1.length() || arg1.charAt(arg2) != arg3 ? false : true;
        return v1;
    }
}

