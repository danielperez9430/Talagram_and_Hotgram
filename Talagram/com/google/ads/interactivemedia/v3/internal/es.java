package com.google.ads.interactivemedia.v3.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class es implements fc {
    public class a extends IOException {
        public a(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    private final ContentResolver a;
    private final fb b;
    private AssetFileDescriptor c;
    private InputStream d;
    private String e;
    private long f;
    private boolean g;

    public es(Context arg1, fb arg2) {
        super();
        this.a = arg1.getContentResolver();
        this.b = arg2;
    }

    public int a(byte[] arg7, int arg8, int arg9) {
        int v7_1;
        if(this.f == 0) {
            return -1;
        }

        try {
            long v2 = -1;
            if(this.f == v2) {
            }
            else {
                arg9 = ((int)Math.min(this.f, ((long)arg9)));
            }

            v7_1 = this.d.read(arg7, arg8, arg9);
            if(v7_1 <= 0) {
                return v7_1;
            }
        }
        catch(IOException v7) {
            throw new a(v7);
        }

        if(this.f != v2) {
            this.f -= ((long)v7_1);
        }

        if(this.b != null) {
            this.b.a(v7_1);
        }

        return v7_1;
    }

    public long a(eu arg7) {
        try {
            this.e = arg7.a.toString();
            this.c = this.a.openAssetFileDescriptor(arg7.a, "r");
            this.d = new FileInputStream(this.c.getFileDescriptor());
            if(this.d.skip(arg7.d) < arg7.d) {
                goto label_40;
            }

            long v2 = -1;
            if(arg7.e != v2) {
                this.f = arg7.e;
            }
            else {
                this.f = ((long)this.d.available());
                if(this.f == 0) {
                    this.f = v2;
                }
            }
        }
        catch(IOException v7) {
            goto label_46;
        }

        this.g = true;
        if(this.b != null) {
            this.b.a();
        }

        return this.f;
        try {
        label_40:
            throw new EOFException();
        }
        catch(IOException v7) {
        label_46:
            throw new a(v7);
        }
    }

    public void a() {
        String v0 = null;
        this.e = v0;
        try {
            if(this.d != null) {
                this.d.close();
            }
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            try {
                throw new a(v2_1);
            }
            catch(Throwable v2) {
                this.d = ((InputStream)v0);
                try {
                    if(this.c != null) {
                        this.c.close();
                    }
                }
                catch(Throwable v2) {
                }
                catch(IOException v2_1) {
                    try {
                        throw new a(v2_1);
                    }
                    catch(Throwable v2) {
                        this.c = ((AssetFileDescriptor)v0);
                        if(this.g) {
                            this.g = false;
                            if(this.b != null) {
                                this.b.b();
                            }
                        }

                        throw v2;
                    }
                }

                this.c = ((AssetFileDescriptor)v0);
                if(this.g) {
                    this.g = false;
                    if(this.b != null) {
                        this.b.b();
                    }
                }

                throw v2;
            }
        }

        this.d = ((InputStream)v0);
        try {
            if(this.c != null) {
                this.c.close();
            }
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            try {
                throw new a(v2_1);
            }
            catch(Throwable v2) {
                this.c = ((AssetFileDescriptor)v0);
                if(this.g) {
                    this.g = false;
                    if(this.b != null) {
                        this.b.b();
                    }
                }

                throw v2;
            }
        }

        this.c = ((AssetFileDescriptor)v0);
        if(this.g) {
            this.g = false;
            if(this.b != null) {
                this.b.b();
            }
        }
    }
}

