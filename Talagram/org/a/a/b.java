package org.a.a;

public class b extends RuntimeException {
    Throwable a;

    public b(String arg3, Throwable arg4) {
        if(arg4 == null) {
        }
        else {
            StringBuffer v0 = new StringBuffer();
            v0.append("Exception while initializing ");
            v0.append(arg3);
            v0.append(": ");
            v0.append(arg4);
            arg3 = v0.toString();
        }

        super(arg3);
        this.a = arg4;
    }

    public b() {
        super();
    }

    public Throwable getCause() {
        return this.a;
    }
}

