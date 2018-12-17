package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

public final class DataBufferUtils {
    public static final String KEY_NEXT_PAGE_TOKEN = "next_page_token";
    public static final String KEY_PREV_PAGE_TOKEN = "prev_page_token";
    public static final String PAGE_PLACEHOLDER_TOKEN = "has_local_data";

    private DataBufferUtils() {
        super();
    }

    public static ArrayList freezeAndClose(DataBuffer arg3) {
        ArrayList v0 = new ArrayList(arg3.getCount());
        try {
            Iterator v1 = arg3.iterator();
            while(v1.hasNext()) {
                v0.add(v1.next().freeze());
            }
        }
        catch(Throwable v0_1) {
            goto label_13;
        }

        arg3.close();
        return v0;
    label_13:
        arg3.close();
        throw v0_1;
    }

    public static boolean hasData(DataBuffer arg0) {
        if(arg0 != null && arg0.getCount() > 0) {
            return 1;
        }

        return 0;
    }

    public static boolean hasNextPage(DataBuffer arg1) {
        Bundle v1 = arg1.getMetadata();
        if(v1 != null && v1.getString("next_page_token") != null) {
            return 1;
        }

        return 0;
    }

    public static boolean hasPrevPage(DataBuffer arg1) {
        Bundle v1 = arg1.getMetadata();
        if(v1 != null && v1.getString("prev_page_token") != null) {
            return 1;
        }

        return 0;
    }
}

