package com.google.a.b;

import java.math.BigDecimal;

public final class f extends Number {
    private final String a;

    public f(String arg1) {
        super();
        this.a = arg1;
    }

    public double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((f)arg5))) {
            return 1;
        }

        if((arg5 instanceof f)) {
            if(this.a != ((f)arg5).a) {
                if(this.a.equals(((f)arg5).a)) {
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

