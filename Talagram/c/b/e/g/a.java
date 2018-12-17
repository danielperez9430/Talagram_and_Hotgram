package c.b.e.g;

public final class a {
    final class c.b.e.g.a$a extends Throwable {
        c.b.e.g.a$a() {
            super("No further exceptions");
        }

        public Throwable fillInStackTrace() {
            return this;
        }
    }

    public static final Throwable a;

    static {
        a.a = new c.b.e.g.a$a();
    }

    public static RuntimeException a(Throwable arg1) {
        if(!(arg1 instanceof Error)) {
            if((arg1 instanceof RuntimeException)) {
                return arg1;
            }

            return new RuntimeException(arg1);
        }

        throw arg1;
    }
}

