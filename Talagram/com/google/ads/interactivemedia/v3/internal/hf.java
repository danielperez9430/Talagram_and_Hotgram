package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class hf {
    final class a extends Writer {
        class com.google.ads.interactivemedia.v3.internal.hf$a$a implements CharSequence {
            char[] a;

            com.google.ads.interactivemedia.v3.internal.hf$a$a() {
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
        private final com.google.ads.interactivemedia.v3.internal.hf$a$a b;

        a(Appendable arg2) {
            super();
            this.b = new com.google.ads.interactivemedia.v3.internal.hf$a$a();
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

    public static void a(gf arg1, hz arg2) {
        hu.X.write(arg2, arg1);
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

    public static gf a(hx arg2) {
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
        catch(ia v2_2) {
        }
        catch(EOFException v2_3) {
            v0 = 1;
            goto label_21;
            try {
            label_2:
                return hu.X.read(arg2);
            }
            catch(NumberFormatException v2) {
                throw new gn(((Throwable)v2));
            }
            catch(IOException v2_1) {
                throw new gg(((Throwable)v2_1));
            }
            catch(ia v2_2) {
                throw new gn(((Throwable)v2_2));
            }
            catch(EOFException v2_3) {
            label_21:
                if(v0 != 0) {
                    return gh.a;
                }

                throw new gn(((Throwable)v2_3));
            }
        }
    }
}

