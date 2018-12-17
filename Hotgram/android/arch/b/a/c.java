package android.arch.b.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build$VERSION;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface c {
    public abstract class a {
        public final int a;

        public a(int arg1) {
            super();
            this.a = arg1;
        }

        private void a(String arg4) {
            if(!arg4.equalsIgnoreCase(":memory:") && arg4.trim().length() != 0) {
                Log.w("SupportSQLite", "deleting the database file: " + arg4);
                try {
                    if(Build$VERSION.SDK_INT < 16) {
                        goto label_22;
                    }

                    SQLiteDatabase.deleteDatabase(new File(arg4));
                    return;
                }
                catch(Exception v4) {
                    goto label_43;
                }

                try {
                label_22:
                    if(new File(arg4).delete()) {
                        return;
                    }

                    Log.e("SupportSQLite", "Could not delete the database file " + arg4);
                }
                catch(Exception v4) {
                    try {
                        Log.e("SupportSQLite", "error while deleting corrupted database file", ((Throwable)v4));
                    }
                    catch(Exception v4) {
                    label_43:
                        Log.w("SupportSQLite", "delete failed: ", ((Throwable)v4));
                    }
                }
            }
        }

        public void a(b arg1) {
        }

        public abstract void a(b arg1, int arg2, int arg3);

        public abstract void b(b arg1);

        public void b(b arg3, int arg4, int arg5) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Can\'t downgrade database from version ");
            v0.append(arg4);
            v0.append(" to ");
            v0.append(arg5);
            throw new SQLiteException(v0.toString());
        }

        public void c(b arg1) {
        }

        public void d(b arg4) {
            Iterator v4;
            List v0;
            Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + arg4.f());
            if(!arg4.e()) {
                this.a(arg4.f());
                return;
            }

            try {
                v0 = arg4.g();
                goto label_20;
            }
            catch(Throwable v1_1) {
            }
            catch(SQLiteException ) {
                try {
                label_20:
                    arg4.close();
                    goto label_33;
                }
                catch(IOException ) {
                label_33:
                    if(v0 != null) {
                        v4 = v0.iterator();
                        while(v4.hasNext()) {
                            this.a(v4.next().second);
                        }
                    }
                    else {
                        this.a(arg4.f());
                    }

                    return;
                }
                catch(Throwable v1_1) {
                    if(v0 != null) {
                        v4 = v0.iterator();
                        while(v4.hasNext()) {
                            this.a(v4.next().second);
                        }
                    }
                    else {
                        this.a(arg4.f());
                    }

                    throw v1_1;
                }
            }
        }
    }

    public class android.arch.b.a.c$b {
        public class android.arch.b.a.c$b$a {
            Context a;
            String b;
            a c;

            android.arch.b.a.c$b$a(Context arg1) {
                super();
                this.a = arg1;
            }

            public android.arch.b.a.c$b$a a(a arg1) {
                this.c = arg1;
                return this;
            }

            public android.arch.b.a.c$b$a a(String arg1) {
                this.b = arg1;
                return this;
            }

            public android.arch.b.a.c$b a() {
                if(this.c != null) {
                    if(this.a != null) {
                        return new android.arch.b.a.c$b(this.a, this.b, this.c);
                    }

                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                }

                throw new IllegalArgumentException("Must set a callback to create the configuration.");
            }
        }

        public final Context a;
        public final String b;
        public final a c;

        android.arch.b.a.c$b(Context arg1, String arg2, a arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public static android.arch.b.a.c$b$a a(Context arg1) {
            return new android.arch.b.a.c$b$a(arg1);
        }
    }

    public interface android.arch.b.a.c$c {
        c a(android.arch.b.a.c$b arg1);
    }

    b a();

    void a(boolean arg1);
}

