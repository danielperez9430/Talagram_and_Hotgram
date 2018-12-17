package com.google.android.gms.common.images;

public final class Size {
    private final int zzps;
    private final int zzpt;

    public Size(int arg1, int arg2) {
        super();
        this.zzps = arg1;
        this.zzpt = arg2;
    }

    public final boolean equals(Object arg5) {
        if(arg5 == null) {
            return 0;
        }

        if(this == (((Size)arg5))) {
            return 1;
        }

        if(((arg5 instanceof Size)) && this.zzps == ((Size)arg5).zzps && this.zzpt == ((Size)arg5).zzpt) {
            return 1;
        }

        return 0;
    }

    public final int getHeight() {
        return this.zzpt;
    }

    public final int getWidth() {
        return this.zzps;
    }

    public final int hashCode() {
        return this.zzpt ^ (this.zzps << 16 | this.zzps >>> 16);
    }

    public static Size parseSize(String arg3) {
        if(arg3 != null) {
            int v0 = arg3.indexOf(42);
            if(v0 < 0) {
                v0 = arg3.indexOf(120);
            }

            if(v0 >= 0) {
                try {
                    return new Size(Integer.parseInt(arg3.substring(0, v0)), Integer.parseInt(arg3.substring(v0 + 1)));
                }
                catch(NumberFormatException ) {
                    throw Size.zzi(arg3);
                }
            }

            throw Size.zzi(arg3);
        }

        throw new IllegalArgumentException("string must not be null");
    }

    public final String toString() {
        int v0 = this.zzps;
        int v1 = this.zzpt;
        StringBuilder v2 = new StringBuilder(23);
        v2.append(v0);
        v2.append("x");
        v2.append(v1);
        return v2.toString();
    }

    private static NumberFormatException zzi(String arg3) {
        StringBuilder v2 = new StringBuilder(String.valueOf(arg3).length() + 16);
        v2.append("Invalid Size: \"");
        v2.append(arg3);
        v2.append("\"");
        throw new NumberFormatException(v2.toString());
    }
}

