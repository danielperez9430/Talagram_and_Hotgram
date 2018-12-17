package com.google.android.gms.internal.clearcut;

final class zzee {
    private final String info;
    private int position;

    zzee(String arg1) {
        super();
        this.info = arg1;
        this.position = 0;
    }

    final boolean hasNext() {
        if(this.position < this.info.length()) {
            return 1;
        }

        return 0;
    }

    final int next() {
        int v3_1;
        String v0 = this.info;
        int v1 = this.position;
        this.position = v1 + 1;
        int v0_1 = v0.charAt(v1);
        v1 = 55296;
        if(v0_1 < v1) {
            return v0_1;
        }

        v0_1 &= 8191;
        int v2;
        for(v2 = 13; true; v2 += 13) {
            String v3 = this.info;
            int v4 = this.position;
            this.position = v4 + 1;
            v3_1 = v3.charAt(v4);
            if(v3_1 < v1) {
                break;
            }

            v0_1 |= (v3_1 & 8191) << v2;
        }

        return v0_1 | v3_1 << v2;
    }
}

