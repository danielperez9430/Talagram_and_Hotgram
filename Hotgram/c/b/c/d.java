package c.b.c;

public final class d extends RuntimeException {
    public d(Throwable arg2) {
        NullPointerException v2;
        String v0 = arg2 != null ? arg2.getMessage() : null;
        if(arg2 != null) {
        }
        else {
            v2 = new NullPointerException();
        }

        super(v0, ((Throwable)v2));
    }
}

