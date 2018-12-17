package c.a.a.a.a.b;

class b {
    public final String a;
    public final boolean b;

    b(String arg1, boolean arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public boolean equals(Object arg5) {
        if(this == (((b)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else if(this.b != ((b)arg5).b) {
                return 0;
            }
            else {
                if(this.a != null) {
                    if(!this.a.equals(((b)arg5).a)) {
                        return 0;
                    }
                }
                else if(((b)arg5).a != null) {
                    return 0;
                }

                return 1;
            }
        }

        return 0;
    }

    public int hashCode() {
        int v0 = this.a != null ? this.a.hashCode() : 0;
        return v0 * 31 + this.b;
    }
}

