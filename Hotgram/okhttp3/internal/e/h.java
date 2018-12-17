package okhttp3.internal.e;

import e.c;
import e.e;
import e.f;
import e.s;
import e.t;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

final class h implements Closeable {
    final class a implements s {
        int a;
        byte b;
        int c;
        int d;
        short e;
        private final e f;

        a(e arg1) {
            super();
            this.f = arg1;
        }

        public long a(c arg6, long arg7) {
            long v1;
            while(true) {
                v1 = -1;
                if(this.d != 0) {
                    break;
                }

                this.f.h(((long)this.e));
                this.e = 0;
                if((this.b & 4) != 0) {
                    return v1;
                }

                this.b();
            }

            long v6 = this.f.a(arg6, Math.min(arg7, ((long)this.d)));
            if(v6 == v1) {
                return v1;
            }

            this.d = ((int)((((long)this.d)) - v6));
            return v6;
        }

        public t a() {
            return this.f.a();
        }

        private void b() {
            int v0 = this.c;
            int v1 = h.a(this.f);
            this.d = v1;
            this.a = v1;
            byte v1_1 = ((byte)(this.f.i() & 255));
            this.b = ((byte)(this.f.i() & 255));
            if(h.a.isLoggable(Level.FINE)) {
                h.a.fine(okhttp3.internal.e.e.a(true, this.c, this.a, v1_1, this.b));
            }

            this.c = this.f.k() & 2147483647;
            if(v1_1 == 9) {
                if(this.c == v0) {
                    return;
                }

                throw okhttp3.internal.e.e.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            }

            throw okhttp3.internal.e.e.b("%s != TYPE_CONTINUATION", new Object[]{Byte.valueOf(v1_1)});
        }

        public void close() {
        }
    }

    interface b {
        void a(int arg1, int arg2, int arg3, boolean arg4);

        void a(boolean arg1, int arg2, int arg3, List arg4);

        void a(boolean arg1, int arg2, e arg3, int arg4);

        void a(int arg1, okhttp3.internal.e.b arg2);

        void a();

        void a(boolean arg1, m arg2);

        void a(int arg1, int arg2, List arg3);

        void a(boolean arg1, int arg2, int arg3);

        void a(int arg1, okhttp3.internal.e.b arg2, f arg3);

        void a(int arg1, long arg2);
    }

    static final Logger a;
    final okhttp3.internal.e.d$a b;
    private final e c;
    private final a d;
    private final boolean e;

    static {
        h.a = Logger.getLogger(okhttp3.internal.e.e.class.getName());
    }

    h(e arg2, boolean arg3) {
        super();
        this.c = arg2;
        this.e = arg3;
        this.d = new a(this.c);
        this.b = new okhttp3.internal.e.d$a(4096, this.d);
    }

    public void a(b arg7) {
        f v7;
        if(!this.e) {
            v7 = this.c.c(((long)okhttp3.internal.e.e.a.g()));
            if(h.a.isLoggable(Level.FINE)) {
                h.a.fine(okhttp3.internal.c.a("<< CONNECTION %s", new Object[]{v7.e()}));
            }

            if(!okhttp3.internal.e.e.a.equals(v7)) {
                goto label_31;
            }
        }
        else if(this.a(true, arg7)) {
        }
        else {
            throw okhttp3.internal.e.e.b("Required SETTINGS preface not received", new Object[0]);
        }

        return;
    label_31:
        throw okhttp3.internal.e.e.b("Expected a connection header but was %s", new Object[]{v7.a()});
    }

    public boolean a(boolean arg7, b arg8) {
        try {
            this.c.a(9);
        }
        catch(IOException ) {
            return 0;
        }

        int v1 = h.a(this.c);
        if(v1 >= 0 && v1 <= 16384) {
            byte v3 = ((byte)(this.c.i() & 255));
            if(arg7) {
                if(v3 == 4) {
                }
                else {
                    throw okhttp3.internal.e.e.b("Expected a SETTINGS frame but was %s", new Object[]{Byte.valueOf(v3)});
                }
            }

            byte v7 = ((byte)(this.c.i() & 255));
            int v0 = this.c.k() & 2147483647;
            if(h.a.isLoggable(Level.FINE)) {
                h.a.fine(okhttp3.internal.e.e.a(true, v0, v1, v3, v7));
            }

            switch(v3) {
                case 0: {
                    this.b(arg8, v1, v7, v0);
                    break;
                }
                case 1: {
                    this.a(arg8, v1, v7, v0);
                    break;
                }
                case 2: {
                    this.c(arg8, v1, v7, v0);
                    break;
                }
                case 3: {
                    this.d(arg8, v1, v7, v0);
                    break;
                }
                case 4: {
                    this.e(arg8, v1, v7, v0);
                    break;
                }
                case 5: {
                    this.f(arg8, v1, v7, v0);
                    break;
                }
                case 6: {
                    this.g(arg8, v1, v7, v0);
                    break;
                }
                case 7: {
                    this.h(arg8, v1, v7, v0);
                    break;
                }
                case 8: {
                    this.i(arg8, v1, v7, v0);
                    break;
                }
                default: {
                    this.c.h(((long)v1));
                    break;
                }
            }

            return 1;
        }

        throw okhttp3.internal.e.e.b("FRAME_SIZE_ERROR: %s", new Object[]{Integer.valueOf(v1)});
    }

    static int a(int arg1, byte arg2, short arg3) {
        if((arg2 & 8) != 0) {
            --arg1;
        }

        if(arg3 <= arg1) {
            return ((short)(arg1 - arg3));
        }

        throw okhttp3.internal.e.e.b("PROTOCOL_ERROR padding %s > remaining length %s", new Object[]{Short.valueOf(arg3), Integer.valueOf(arg1)});
    }

    static int a(e arg2) {
        return arg2.i() & 255 | ((arg2.i() & 255) << 16 | (arg2.i() & 255) << 8);
    }

    private List a(int arg3, short arg4, byte arg5, int arg6) {
        a v0 = this.d;
        this.d.d = arg3;
        v0.a = arg3;
        this.d.e = arg4;
        this.d.b = arg5;
        this.d.c = arg6;
        this.b.a();
        return this.b.b();
    }

    private void a(b arg5, int arg6) {
        int v0 = this.c.k();
        boolean v1 = (-2147483648 & v0) != 0 ? true : false;
        arg5.a(arg6, v0 & 2147483647, (this.c.i() & 255) + 1, v1);
    }

    private void a(b arg4, int arg5, byte arg6, int arg7) {
        short v0 = 0;
        if(arg7 != 0) {
            boolean v1 = (arg6 & 1) != 0 ? true : false;
            if((arg6 & 8) != 0) {
                v0 = ((short)(this.c.i() & 255));
            }

            if((arg6 & 32) != 0) {
                this.a(arg4, arg7);
                arg5 += -5;
            }

            arg4.a(v1, arg7, -1, this.a(h.a(arg5, arg6, v0), v0, arg6, arg7));
            return;
        }

        throw okhttp3.internal.e.e.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
    }

    private void b(b arg5, int arg6, byte arg7, int arg8) {
        short v0 = 0;
        if(arg8 != 0) {
            int v2 = 1;
            boolean v1 = (arg7 & 1) != 0 ? true : false;
            if((arg7 & 32) != 0) {
            }
            else {
                v2 = 0;
            }

            if(v2 == 0) {
                if((arg7 & 8) != 0) {
                    v0 = ((short)(this.c.i() & 255));
                }

                arg5.a(v1, arg8, this.c, h.a(arg6, arg7, v0));
                this.c.h(((long)v0));
                return;
            }

            throw okhttp3.internal.e.e.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }

        throw okhttp3.internal.e.e.b("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
    }

    private void c(b arg2, int arg3, byte arg4, int arg5) {
        if(arg3 == 5) {
            if(arg5 != 0) {
                this.a(arg2, arg5);
                return;
            }

            throw okhttp3.internal.e.e.b("TYPE_PRIORITY streamId == 0", new Object[0]);
        }

        throw okhttp3.internal.e.e.b("TYPE_PRIORITY length: %d != 5", new Object[]{Integer.valueOf(arg3)});
    }

    public void close() {
        this.c.close();
    }

    private void d(b arg3, int arg4, byte arg5, int arg6) {
        if(arg4 == 4) {
            if(arg6 != 0) {
                arg4 = this.c.k();
                okhttp3.internal.e.b v1 = okhttp3.internal.e.b.a(arg4);
                if(v1 != null) {
                    arg3.a(arg6, v1);
                    return;
                }

                throw okhttp3.internal.e.e.b("TYPE_RST_STREAM unexpected error code: %d", new Object[]{Integer.valueOf(arg4)});
            }

            throw okhttp3.internal.e.e.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }

        throw okhttp3.internal.e.e.b("TYPE_RST_STREAM length: %d != 4", new Object[]{Integer.valueOf(arg4)});
    }

    private void e(b arg6, int arg7, byte arg8, int arg9) {
        if(arg9 == 0) {
            if((arg8 & 1) != 0) {
                if(arg7 == 0) {
                    arg6.a();
                    return;
                }

                throw okhttp3.internal.e.e.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }

            if(arg7 % 6 == 0) {
                m v8 = new m();
                int v1;
                for(v1 = 0; v1 < arg7; v1 += 6) {
                    int v2 = this.c.j() & 65535;
                    int v3 = this.c.k();
                    switch(v2) {
                        case 2: {
                            if(v3 == 0) {
                                goto label_53;
                            }

                            if(v3 == 1) {
                                goto label_53;
                            }

                            throw okhttp3.internal.e.e.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        }
                        case 3: {
                            v2 = 4;
                            break;
                        }
                        case 4: {
                            v2 = 7;
                            if(v3 >= 0) {
                                goto label_53;
                            }

                            throw okhttp3.internal.e.e.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        }
                        case 5: {
                            if(v3 >= 16384 && v3 <= 16777215) {
                                goto label_53;
                            }

                            throw okhttp3.internal.e.e.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", new Object[]{Integer.valueOf(v3)});
                        }
                        default: {
                            break;
                        }
                    }

                label_53:
                    v8.a(v2, v3);
                }

                arg6.a(false, v8);
                return;
            }

            throw okhttp3.internal.e.e.b("TYPE_SETTINGS length %% 6 != 0: %s", new Object[]{Integer.valueOf(arg7)});
        }

        throw okhttp3.internal.e.e.b("TYPE_SETTINGS streamId != 0", new Object[0]);
    }

    private void f(b arg4, int arg5, byte arg6, int arg7) {
        short v0 = 0;
        if(arg7 != 0) {
            if((arg6 & 8) != 0) {
                v0 = ((short)(this.c.i() & 255));
            }

            arg4.a(arg7, this.c.k() & 2147483647, this.a(h.a(arg5 - 4, arg6, v0), v0, arg6, arg7));
            return;
        }

        throw okhttp3.internal.e.e.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
    }

    private void g(b arg4, int arg5, byte arg6, int arg7) {
        boolean v0 = false;
        if(arg5 == 8) {
            if(arg7 == 0) {
                arg5 = this.c.k();
                arg7 = this.c.k();
                if((arg6 & 1) != 0) {
                    v0 = true;
                }

                arg4.a(v0, arg5, arg7);
                return;
            }

            throw okhttp3.internal.e.e.b("TYPE_PING streamId != 0", new Object[0]);
        }

        throw okhttp3.internal.e.e.b("TYPE_PING length != 8: %s", new Object[]{Integer.valueOf(arg5)});
    }

    private void h(b arg4, int arg5, byte arg6, int arg7) {
        int v0 = 8;
        if(arg5 >= v0) {
            if(arg7 == 0) {
                arg7 = this.c.k();
                int v2 = this.c.k();
                arg5 -= v0;
                okhttp3.internal.e.b v0_1 = okhttp3.internal.e.b.a(v2);
                if(v0_1 != null) {
                    f v6 = f.b;
                    if(arg5 > 0) {
                        v6 = this.c.c(((long)arg5));
                    }

                    arg4.a(arg7, v0_1, v6);
                    return;
                }

                throw okhttp3.internal.e.e.b("TYPE_GOAWAY unexpected error code: %d", new Object[]{Integer.valueOf(v2)});
            }

            throw okhttp3.internal.e.e.b("TYPE_GOAWAY streamId != 0", new Object[0]);
        }

        throw okhttp3.internal.e.e.b("TYPE_GOAWAY length < 8: %s", new Object[]{Integer.valueOf(arg5)});
    }

    private void i(b arg6, int arg7, byte arg8, int arg9) {
        if(arg7 == 4) {
            long v1 = (((long)this.c.k())) & 2147483647;
            if(v1 != 0) {
                arg6.a(arg9, v1);
                return;
            }

            throw okhttp3.internal.e.e.b("windowSizeIncrement was 0", new Object[]{Long.valueOf(v1)});
        }

        throw okhttp3.internal.e.e.b("TYPE_WINDOW_UPDATE length !=4: %s", new Object[]{Integer.valueOf(arg7)});
    }
}

