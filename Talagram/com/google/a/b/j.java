package com.google.a.b;

import com.google.a.b.a.n;
import com.google.a.d.c;
import com.google.a.d.d;
import com.google.a.l;
import com.google.a.m;
import com.google.a.t;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class j {
    final class a extends Writer {
        class com.google.a.b.j$a$a implements CharSequence {
            char[] a;

            com.google.a.b.j$a$a() {
                super();
            }

            public char charAt(int arg2) {
                return this.a[arg2];
            }

            public int length() {
                return this.a.length;
            }

            public CharSequence subSequence(int arg3, int arg4) {
                return new String(this.a, arg3, arg4 - arg3);
            }
        }

        private final Appendable a;
        private final com.google.a.b.j$a$a b;

        a(Appendable arg2) {
            super();
            this.b = new com.google.a.b.j$a$a();
            this.a = arg2;
        }

        public void close() {
        }

        public void flush() {
        }

        public void write(int arg2) {
            this.a.append(((char)arg2));
        }

        public void write(char[] arg2, int arg3, int arg4) {
            this.b.a = arg2;
            this.a.append(this.b, arg3, arg4 + arg3);
        }
    }

    public static l a(com.google.a.d.a arg2) {
        int v0;
        try {
            arg2.f();
            v0 = 0;
            goto label_2;
        }
        catch(NumberFormatException v2) {
        }
        catch(IOException v2_1) {
        }
        catch(d v2_2) {
        }
        catch(EOFException v2_3) {
            v0 = 1;
            goto label_21;
            try {
            label_2:
                return n.X.read(arg2);
            }
            catch(NumberFormatException v2) {
                throw new t(((Throwable)v2));
            }
            catch(IOException v2_1) {
                throw new m(((Throwable)v2_1));
            }
            catch(d v2_2) {
                throw new t(((Throwable)v2_2));
            }
            catch(EOFException v2_3) {
            label_21:
                if(v0 != 0) {
                    return com.google.a.n.a;
                }

                throw new t(((Throwable)v2_3));
            }
        }
    }

    public static Writer a(Appendable arg1) {
        a v1;
        if((arg1 instanceof Writer)) {
        }
        else {
            v1 = new a(arg1);
        }

        return ((Appendable)v1);
    }

    public static void a(l arg1, c arg2) {
        n.X.write(arg2, arg1);
    }
}

