package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map$Entry;
import java.util.Map;

public class ju {
    public static String a(Map arg5) {
        if(arg5 != null) {
            if(arg5.size() == 0) {
            }
            else {
                StringBuilder v0 = new StringBuilder();
                int v1 = 0;
                Iterator v2 = arg5.entrySet().iterator();
                while(v2.hasNext()) {
                    Object v3 = v2.next();
                    Object v4 = ((Map$Entry)v3).getKey();
                    v3 = ((Map$Entry)v3).getValue();
                    v0.append(((String)v4));
                    v0.append("=");
                    v0.append(((String)v3));
                    if(v1 < arg5.size() - 1) {
                        v0.append("&");
                    }

                    ++v1;
                }

                return v0.toString();
            }
        }

        return "";
    }

    public static Map a(Uri arg7) {
        if(arg7 != null && !arg7.isOpaque()) {
            String v7 = arg7.getEncodedQuery();
            if(v7 != null) {
                if(v7.length() == 0) {
                }
                else {
                    LinkedHashMap v0 = new LinkedHashMap();
                    int v1 = 0;
                    int v2 = v7.indexOf(35);
                    int v3 = -1;
                    if(v2 == v3) {
                        v2 = v7.length();
                    }

                    do {
                        int v4 = v7.indexOf(38, v1);
                        if(v4 == v3) {
                            v4 = v2;
                        }

                        int v5 = v7.indexOf(61, v1);
                        if(v5 > v4 || v5 == v3) {
                            v5 = v4;
                        }

                        String v1_1 = v7.substring(v1, v5);
                        String v6 = "";
                        if(v5 < v4) {
                            v6 = v7.substring(v5 + 1, v4);
                        }

                        ((Map)v0).put(v1_1, v6);
                        v1 = v4 + 1;
                    }
                    while(v1 < v2);

                    return Collections.unmodifiableMap(((Map)v0));
                }
            }

            return Collections.emptyMap();
        }

        throw new UnsupportedOperationException("This isn\'t a hierarchical URI.");
    }
}

