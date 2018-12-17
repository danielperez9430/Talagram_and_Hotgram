package c.b.c;

import c.b.e.g.a;

public final class b {
    public static RuntimeException a(Throwable arg0) {
        throw a.a(arg0);
    }

    public static void b(Throwable arg1) {
        if(!(arg1 instanceof VirtualMachineError)) {
            if(!(arg1 instanceof ThreadDeath)) {
                if(!(arg1 instanceof LinkageError)) {
                    return;
                }

                throw arg1;
            }

            throw arg1;
        }

        throw arg1;
    }
}

