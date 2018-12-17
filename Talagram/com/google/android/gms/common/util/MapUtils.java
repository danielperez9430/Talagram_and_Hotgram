package com.google.android.gms.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapUtils {
    public MapUtils() {
        super();
    }

    public static Object getKeyFromMap(Map arg2, Object arg3) {
        Object v0;
        if(arg2.containsKey(arg3)) {
            Iterator v2 = arg2.keySet().iterator();
            do {
                if(v2.hasNext()) {
                    v0 = v2.next();
                    if(!v0.equals(arg3)) {
                        continue;
                    }

                    return v0;
                }

                return null;
            }
            while(true);

            return v0;
        }

        return null;
    }

    public static void writeStringMapToJson(StringBuilder arg5, HashMap arg6) {
        String v2_1;
        arg5.append("{");
        Iterator v0 = arg6.keySet().iterator();
        int v1 = 1;
        while(v0.hasNext()) {
            Object v2 = v0.next();
            if(v1 == 0) {
                arg5.append(",");
            }
            else {
                v1 = 0;
            }

            Object v3 = arg6.get(v2);
            arg5.append("\"");
            arg5.append(((String)v2));
            arg5.append("\":");
            if(v3 == null) {
                v2_1 = "null";
            }
            else {
                arg5.append("\"");
                arg5.append(((String)v3));
                v2_1 = "\"";
            }

            arg5.append(v2_1);
        }

        arg5.append("}");
    }
}

