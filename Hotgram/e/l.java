package e;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class l {
    static final Logger a;

    static {
        l.a = Logger.getLogger(l.class.getName());
    }

    private l() {
        super();
    }

    public static e a(s arg1) {
        return new n(arg1);
    }

    public static d a(r arg1) {
        return new m(arg1);
    }

    public static r a(Socket arg1) {
        if(arg1 != null) {
            if(arg1.getOutputStream() != null) {
                a v0 = l.c(arg1);
                return v0.a(l.a(arg1.getOutputStream(), ((t)v0)));
            }

            throw new IOException("socket\'s output stream == null");
        }

        throw new IllegalArgumentException("socket == null");
    }

    private static r a(OutputStream arg1, t arg2) {
        if(arg1 != null) {
            if(arg2 != null) {
                return new r(arg2, arg1) {
                    public t a() {
                        return this.a;
                    }

                    public void a_(c arg7, long arg8) {
                        u.a(arg7.b, 0, arg8);
                        while(arg8 > 0) {
                            this.a.g();
                            o v0 = arg7.a;
                            int v1 = ((int)Math.min(arg8, ((long)(v0.c - v0.b))));
                            this.b.write(v0.a, v0.b, v1);
                            v0.b += v1;
                            long v1_1 = ((long)v1);
                            arg8 -= v1_1;
                            arg7.b -= v1_1;
                            if(v0.b != v0.c) {
                                continue;
                            }

                            arg7.a = v0.b();
                            p.a(v0);
                        }
                    }

                    public void close() {
                        this.b.close();
                    }

                    public void flush() {
                        this.b.flush();
                    }

                    public String toString() {
                        return "sink(" + this.b + ")";
                    }
                };
            }

            throw new IllegalArgumentException("timeout == null");
        }

        throw new IllegalArgumentException("out == null");
    }

    public static s a(InputStream arg1) {
        return l.a(arg1, new t());
    }

    private static s a(InputStream arg1, t arg2) {
        if(arg1 != null) {
            if(arg2 != null) {
                return new s(arg2, arg1) {
                    public long a(c arg4, long arg5) {
                        long v0 = 0;
                        if(arg5 >= v0) {
                            if(arg5 == v0) {
                                return v0;
                            }

                            try {
                                this.a.g();
                                o v0_1 = arg4.e(1);
                                int v5 = this.b.read(v0_1.a, v0_1.c, ((int)Math.min(arg5, ((long)(8192 - v0_1.c)))));
                                if(v5 == -1) {
                                    return -1;
                                }

                                v0_1.c += v5;
                                arg5 = ((long)v5);
                                arg4.b += arg5;
                                return arg5;
                            }
                            catch(AssertionError v4) {
                                if(l.a(v4)) {
                                    throw new IOException(((Throwable)v4));
                                }

                                throw v4;
                            }
                        }

                        StringBuilder v0_2 = new StringBuilder();
                        v0_2.append("byteCount < 0: ");
                        v0_2.append(arg5);
                        throw new IllegalArgumentException(v0_2.toString());
                    }

                    public t a() {
                        return this.a;
                    }

                    public void close() {
                        this.b.close();
                    }

                    public String toString() {
                        return "source(" + this.b + ")";
                    }
                };
            }

            throw new IllegalArgumentException("timeout == null");
        }

        throw new IllegalArgumentException("in == null");
    }

    static boolean a(AssertionError arg1) {
        boolean v1 = arg1.getCause() == null || arg1.getMessage() == null || !arg1.getMessage().contains("getsockname failed") ? false : true;
        return v1;
    }

    public static s b(Socket arg1) {
        if(arg1 != null) {
            if(arg1.getInputStream() != null) {
                a v0 = l.c(arg1);
                return v0.a(l.a(arg1.getInputStream(), ((t)v0)));
            }

            throw new IOException("socket\'s input stream == null");
        }

        throw new IllegalArgumentException("socket == null");
    }

    private static a c(Socket arg1) {
        return new a(arg1) {
            protected IOException a(@Nullable IOException arg3) {
                SocketTimeoutException v0 = new SocketTimeoutException("timeout");
                if(arg3 != null) {
                    ((InterruptedIOException)v0).initCause(((Throwable)arg3));
                }

                return ((IOException)v0);
            }

            protected void a() {
                StringBuilder v3;
                Level v2;
                Logger v1;
                try {
                    this.a.close();
                    return;
                }
                catch(AssertionError v0) {
                    if(!l.a(v0)) {
                        goto label_11;
                    }

                    v1 = l.a;
                    v2 = Level.WARNING;
                    v3 = new StringBuilder();
                }
                catch(Exception v0_1) {
                    v1 = l.a;
                    v2 = Level.WARNING;
                    v3 = new StringBuilder();
                }

                v3.append("Failed to close timed out socket ");
                v3.append(this.a);
                v1.log(v2, v3.toString(), ((Throwable)v0_1));
                return;
            label_11:
                throw v0;
            }
        };
    }
}

