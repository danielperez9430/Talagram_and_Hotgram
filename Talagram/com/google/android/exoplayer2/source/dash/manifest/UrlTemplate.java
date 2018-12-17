package com.google.android.exoplayer2.source.dash.manifest;

import java.util.Locale;

public final class UrlTemplate {
    private static final String BANDWIDTH = "Bandwidth";
    private static final int BANDWIDTH_ID = 3;
    private static final String DEFAULT_FORMAT_TAG = "%01d";
    private static final String ESCAPED_DOLLAR = "$$";
    private static final String NUMBER = "Number";
    private static final int NUMBER_ID = 2;
    private static final String REPRESENTATION = "RepresentationID";
    private static final int REPRESENTATION_ID = 1;
    private static final String TIME = "Time";
    private static final int TIME_ID = 4;
    private final int identifierCount;
    private final String[] identifierFormatTags;
    private final int[] identifiers;
    private final String[] urlPieces;

    private UrlTemplate(String[] arg1, int[] arg2, String[] arg3, int arg4) {
        super();
        this.urlPieces = arg1;
        this.identifiers = arg2;
        this.identifierFormatTags = arg3;
        this.identifierCount = arg4;
    }

    public String buildUri(String arg8, long arg9, int arg11, long arg12) {
        Object[] v4;
        String v5;
        Locale v3;
        StringBuilder v0 = new StringBuilder();
        int v2;
        for(v2 = 0; v2 < this.identifierCount; ++v2) {
            v0.append(this.urlPieces[v2]);
            if(this.identifiers[v2] == 1) {
                v0.append(arg8);
            }
            else {
                if(this.identifiers[v2] == 2) {
                    v3 = Locale.US;
                    v5 = this.identifierFormatTags[v2];
                    v4 = new Object[]{Long.valueOf(arg9)};
                }
                else if(this.identifiers[v2] == 3) {
                    v3 = Locale.US;
                    v5 = this.identifierFormatTags[v2];
                    v4 = new Object[]{Integer.valueOf(arg11)};
                }
                else if(this.identifiers[v2] == 4) {
                    v3 = Locale.US;
                    v5 = this.identifierFormatTags[v2];
                    v4 = new Object[]{Long.valueOf(arg12)};
                }
                else {
                    goto label_50;
                }

                v0.append(String.format(v3, v5, v4));
            }

        label_50:
        }

        v0.append(this.urlPieces[this.identifierCount]);
        return v0.toString();
    }

    public static UrlTemplate compile(String arg4) {
        String[] v0 = new String[5];
        int[] v2 = new int[4];
        String[] v1 = new String[4];
        return new UrlTemplate(v0, v2, v1, UrlTemplate.parseTemplate(arg4, v0, v2, v1));
    }

    private static int parseTemplate(String arg10, String[] arg11, int[] arg12, String[] arg13) {
        arg11[0] = "";
        int v0 = 0;
        int v2 = 0;
        while(v0 < arg10.length()) {
            int v3 = arg10.indexOf("$", v0);
            int v4 = -1;
            if(v3 == v4) {
                arg11[v2] = arg11[v2] + arg10.substring(v0);
                v0 = arg10.length();
                continue;
            }

            if(v3 != v0) {
                arg11[v2] = arg11[v2] + arg10.substring(v0, v3);
            }
            else if(arg10.startsWith("$$", v0)) {
                arg11[v2] = arg11[v2] + "$";
                v0 += 2;
                continue;
            }
            else {
                ++v0;
                v3 = arg10.indexOf("$", v0);
                String v0_1 = arg10.substring(v0, v3);
                if(v0_1.equals("RepresentationID")) {
                    arg12[v2] = 1;
                }
                else {
                    int v5 = v0_1.indexOf("%0");
                    String v7 = "%01d";
                    if(v5 != v4) {
                        v7 = v0_1.substring(v5);
                        if(!v7.endsWith("d")) {
                            v7 = v7 + "d";
                        }

                        v0_1 = v0_1.substring(0, v5);
                    }

                    v5 = v0_1.hashCode();
                    int v9 = 2;
                    if(v5 != -1950496919) {
                        if(v5 != 2606829) {
                            if(v5 != 38199441) {
                            }
                            else if(v0_1.equals("Bandwidth")) {
                                v4 = 1;
                            }
                        }
                        else if(v0_1.equals("Time")) {
                            v4 = 2;
                        }
                    }
                    else if(v0_1.equals("Number")) {
                        v4 = 0;
                    }

                    switch(v4) {
                        case 0: {
                            goto label_109;
                        }
                        case 1: {
                            goto label_106;
                        }
                        case 2: {
                            goto label_103;
                        }
                    }

                    StringBuilder v12 = new StringBuilder();
                    v12.append("Invalid template: ");
                    v12.append(arg10);
                    throw new IllegalArgumentException(v12.toString());
                label_103:
                    arg12[v2] = 4;
                    goto label_110;
                label_106:
                    arg12[v2] = 3;
                    goto label_110;
                label_109:
                    arg12[v2] = v9;
                label_110:
                    arg13[v2] = v7;
                }

                ++v2;
                arg11[v2] = "";
                ++v3;
            }

            v0 = v3;
        }

        return v2;
    }
}

