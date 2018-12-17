package com.google.ads.interactivemedia.v3.internal;

import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class le {
    static boolean a(List arg6, Object arg7) {
        if(arg7 == kr.a(arg6)) {
            return 1;
        }

        if(!(arg7 instanceof List)) {
            return 0;
        }

        int v0 = arg6.size();
        if(v0 != ((List)arg7).size()) {
            return 0;
        }

        if(((arg6 instanceof RandomAccess)) && ((arg7 instanceof RandomAccess))) {
            int v3 = 0;
            while(true) {
                if(v3 >= v0) {
                    return 1;
                }
                else if(!ko.a(arg6.get(v3), ((List)arg7).get(v3))) {
                    return 0;
                }
                else {
                    ++v3;
                    continue;
                }

                goto label_26;
            }

            return 1;
        }

    label_26:
        return ld.a(arg6.iterator(), ((List)arg7).iterator());
    }

    static int b(List arg1, Object arg2) {
        if((arg1 instanceof RandomAccess)) {
            return le.d(arg1, arg2);
        }

        ListIterator v1 = arg1.listIterator();
        do {
            if(!v1.hasNext()) {
                return -1;
            }
        }
        while(!ko.a(arg2, v1.next()));

        return v1.previousIndex();
    }

    static int c(List arg1, Object arg2) {
        if((arg1 instanceof RandomAccess)) {
            return le.e(arg1, arg2);
        }

        ListIterator v1 = arg1.listIterator(arg1.size());
        do {
            if(!v1.hasPrevious()) {
                return -1;
            }
        }
        while(!ko.a(arg2, v1.previous()));

        return v1.nextIndex();
    }

    private static int d(List arg3, Object arg4) {
        int v0 = arg3.size();
        int v1 = 0;
        if(arg4 == null) {
            while(v1 < v0) {
                if(arg3.get(v1) == null) {
                    return v1;
                }
                else {
                    ++v1;
                    continue;
                }

                return -1;
            }
        }
        else {
            while(v1 < v0) {
                if(arg4.equals(arg3.get(v1))) {
                    return v1;
                }
                else {
                    ++v1;
                    continue;
                }

                return -1;
            }
        }

        return -1;
    }

    private static int e(List arg2, Object arg3) {
        if(arg3 == null) {
            int v3 = arg2.size() - 1;
            while(v3 >= 0) {
                if(arg2.get(v3) == null) {
                    return v3;
                }
                else {
                    --v3;
                    continue;
                }

                return -1;
            }
        }
        else {
            int v0 = arg2.size() - 1;
            while(v0 >= 0) {
                if(arg3.equals(arg2.get(v0))) {
                    return v0;
                }
                else {
                    --v0;
                    continue;
                }

                return -1;
            }
        }

        return -1;
    }
}

