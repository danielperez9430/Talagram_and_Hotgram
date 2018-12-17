package com.google.ads.interactivemedia.v3.internal;

import java.math.BigDecimal;

public final class hb extends Number {
    private final String a;

    public hb(String arg1) {
        super();
        this.a = arg1;
    }

    public double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((hb)arg5))) {
            return 1;
        }

        if((arg5 instanceof hb)) {
            if(this.a != ((hb)arg5).a) {
                if(this.a.equals(((hb)arg5).a)) {
                }
                else {
                    v0 = false;
                }
            }

            return v0;
        }

        return 0;
    }

    public float floatValue() {
        return Float.parseFloat(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public int intValue() {
        try {
            return Integer.parseInt(this.a);
        }
        catch(NumberFormatException ) {
            try {
                return ((int)Long.parseLong(this.a));
            }
            catch(NumberFormatException ) {
                return new BigDecimal(this.a).intValue();
            }
        }
    }

    public long longValue() {
        try {
            return Long.parseLong(this.a);
        }
        catch(NumberFormatException ) {
            return new BigDecimal(this.a).longValue();
        }
    }

    public String toString() {
        return this.a;
    }
}

