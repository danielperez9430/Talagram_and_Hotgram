package com.f.a;

import android.os.AsyncTask;
import android.os.Build$VERSION;
import com.f.a.a.h;
import com.f.a.c.d;
import com.f.a.e.c;
import java.util.concurrent.Executor;

public class a {
    class com.f.a.a$1 implements b {
        com.f.a.a$1(a arg1) {
            this.a = arg1;
            super();
        }

        public void a(com.f.a.b.a arg2, String arg3, Executor arg4, com.f.a.b arg5) {
            a.a(this.a, arg4, new AsyncTask(arg3, arg2, arg5) {
                protected com.f.a.a$a a(Void[] arg4) {
                    Exception v4 = null;
                    try {
                        return new com.f.a.a$a(this.d.a, d.a(c.a(this.b), com.f.a.c.c.a(this.a).a()), v4, ((com.f.a.a$1)v4));
                    }
                    catch(h v0) {
                        return new com.f.a.a$a(this.d.a, ((com.f.a.b.b)v4), ((Exception)v0), ((com.f.a.a$1)v4));
                    }
                }

                protected void a(com.f.a.a$a arg3) {
                    a.a(this.d.a, arg3, this.c);
                }

                protected Object doInBackground(Object[] arg1) {
                    return this.a(((Void[])arg1));
                }

                protected void onPostExecute(Object arg1) {
                    this.a(((com.f.a.a$a)arg1));
                }
            });
        }
    }

    class com.f.a.a$a {
        final com.f.a.b.b a;
        final Exception b;

        private com.f.a.a$a(a arg1, com.f.a.b.b arg2, Exception arg3) {
            this.c = arg1;
            super();
            this.b = arg3;
            this.a = arg2;
        }

        com.f.a.a$a(a arg1, com.f.a.b.b arg2, Exception arg3, com.f.a.a$1 arg4) {
            this(arg1, arg2, arg3);
        }
    }

    interface b {
        void a(com.f.a.b.a arg1, String arg2, Executor arg3, com.f.a.b arg4);
    }

    b a;
    private String b;

    public a() {
        super();
        this.a = new com.f.a.a$1(this);
    }

    public a(String arg2) {
        super();
        this.a = new com.f.a.a$1(this);
        this.a(arg2);
    }

    public void a(String arg1) {
        this.b(arg1);
        this.b = arg1;
    }

    private void a(com.f.a.a$a arg2, com.f.a.b arg3) {
        Exception v2;
        if(arg2.a != null) {
            arg3.onSuccess(arg2.a);
        }
        else {
            if(arg2.b != null) {
                v2 = arg2.b;
            }
            else {
                RuntimeException v2_1 = new RuntimeException("Somehow got neither a token response or an error response");
            }

            arg3.onError(v2);
        }
    }

    static void a(a arg0, com.f.a.a$a arg1, com.f.a.b arg2) {
        arg0.a(arg1, arg2);
    }

    static void a(a arg0, Executor arg1, AsyncTask arg2) {
        arg0.a(arg1, arg2);
    }

    private void a(Executor arg4, AsyncTask arg5) {
        if(arg4 == null || Build$VERSION.SDK_INT <= 11) {
            arg5.execute(new Void[0]);
        }
        else {
            arg5.executeOnExecutor(arg4, new Void[0]);
        }
    }

    public void a(com.f.a.b.a arg2, com.f.a.b arg3) {
        this.a(arg2, this.b, arg3);
    }

    public void a(com.f.a.b.a arg2, String arg3, com.f.a.b arg4) {
        this.a(arg2, arg3, null, arg4);
    }

    public void a(com.f.a.b.a arg2, String arg3, Executor arg4, com.f.a.b arg5) {
        if(arg2 == null) {
            goto label_10;
        }

        if(arg5 == null) {
            goto label_6;
        }

        try {
            this.b(arg3);
            this.a.a(arg2, arg3, arg4, arg5);
            return;
        label_6:
            throw new RuntimeException("Required Parameter: \'callback\' is required to use the created token and handle errors");
        label_10:
            throw new RuntimeException("Required Parameter: \'card\' is required to create a token");
        }
        catch(com.f.a.a.c v2) {
            arg5.onError(((Exception)v2));
        }
    }

    private void b(String arg4) {
        String v1 = null;
        if(arg4 != null && arg4.length() != 0) {
            if(!arg4.startsWith("sk_")) {
                return;
            }
            else {
                throw new com.f.a.a.c("Invalid Publishable Key: You are using a secret key to create a token, instead of the publishable one. For more info, see https://stripe.com/docs/stripe.js", v1, Integer.valueOf(0));
            }
        }

        throw new com.f.a.a.c("Invalid Publishable Key: You must use a valid publishable key to create a token.  For more info, see https://stripe.com/docs/stripe.js.", v1, Integer.valueOf(0));
    }
}

