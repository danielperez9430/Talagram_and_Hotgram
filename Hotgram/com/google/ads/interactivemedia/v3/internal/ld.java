package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;

public final class ld {
    public static boolean a(Iterator arg3, Iterator arg4) {
        do {
            if(!arg3.hasNext()) {
                goto label_11;
            }

            if(!arg4.hasNext()) {
                return 0;
            }
        }
        while(ko.a(arg3.next(), arg4.next()));

        return 0;
    label_11:
        return arg4.hasNext() ^ 1;
    }
}

