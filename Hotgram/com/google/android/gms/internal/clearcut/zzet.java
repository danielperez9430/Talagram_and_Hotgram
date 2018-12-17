package com.google.android.gms.internal.clearcut;

final class zzet {
    static String zzc(zzbb arg5) {
        String v2_1;
        zzeu v0 = new zzeu(arg5);
        StringBuilder v5 = new StringBuilder(((zzev)v0).size());
        int v1;
        for(v1 = 0; v1 < ((zzev)v0).size(); ++v1) {
            int v2 = ((zzev)v0).zzj(v1);
            if(v2 != 34) {
                if(v2 != 39) {
                    char v3 = '\\';
                    if(v2 != v3) {
                        switch(v2) {
                            case 7: {
                                goto label_49;
                            }
                            case 8: {
                                goto label_47;
                            }
                            case 9: {
                                goto label_45;
                            }
                            case 10: {
                                goto label_43;
                            }
                            case 11: {
                                goto label_41;
                            }
                            case 12: {
                                goto label_39;
                            }
                            case 13: {
                                goto label_37;
                            }
                        }

                        if(v2 < 32 || v2 > 126) {
                            v5.append(v3);
                            v5.append(((char)((v2 >>> 6 & 3) + 48)));
                            v5.append(((char)((v2 >>> 3 & 7) + 48)));
                            v2 = (v2 & 7) + 48;
                        }
                        else {
                        }

                        v5.append(((char)v2));
                        goto label_58;
                    label_49:
                        v2_1 = "\\a";
                        goto label_50;
                    label_37:
                        v2_1 = "\\r";
                        goto label_50;
                    label_39:
                        v2_1 = "\\f";
                        goto label_50;
                    label_41:
                        v2_1 = "\\v";
                        goto label_50;
                    label_43:
                        v2_1 = "\\n";
                        goto label_50;
                    label_45:
                        v2_1 = "\\t";
                        goto label_50;
                    label_47:
                        v2_1 = "\\b";
                    }
                    else {
                        v2_1 = "\\\\";
                    }
                }
                else {
                    v2_1 = "\\\'";
                }

                goto label_50;
            }
            else {
                v2_1 = "\\\"";
            label_50:
                v5.append(v2_1);
            }

        label_58:
        }

        return v5.toString();
    }
}

