package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import java.util.ArrayList;

public final class ad {
    public class a {
        private Activity a;
        private Intent b;
        private ArrayList c;
        private ArrayList d;
        private ArrayList e;
        private ArrayList f;

        private a(Activity arg4) {
            super();
            this.a = arg4;
            this.b = new Intent().setAction("android.intent.action.SEND");
            this.b.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", arg4.getPackageName());
            this.b.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", arg4.getComponentName());
            this.b.addFlags(524288);
        }

        public static a a(Activity arg1) {
            return new a(arg1);
        }

        private void a(String arg5, ArrayList arg6) {
            String[] v0 = this.b.getStringArrayExtra(arg5);
            int v2 = v0 != null ? v0.length : 0;
            String[] v3 = new String[arg6.size() + v2];
            arg6.toArray(((Object[])v3));
            if(v0 != null) {
                System.arraycopy(v0, 0, v3, arg6.size(), v2);
            }

            this.b.putExtra(arg5, v3);
        }

        public Intent a() {
            ArrayList v1 = null;
            if(this.c != null) {
                this.a("android.intent.extra.EMAIL", this.c);
                this.c = v1;
            }

            if(this.d != null) {
                this.a("android.intent.extra.CC", this.d);
                this.d = v1;
            }

            if(this.e != null) {
                this.a("android.intent.extra.BCC", this.e);
                this.e = v1;
            }

            int v3 = 1;
            if(this.f == null || this.f.size() <= 1) {
                v3 = 0;
            }
            else {
            }

            boolean v0 = this.b.getAction().equals("android.intent.action.SEND_MULTIPLE");
            if(v3 == 0 && (v0)) {
                this.b.setAction("android.intent.action.SEND");
                if(this.f == null || (this.f.isEmpty())) {
                    this.b.removeExtra("android.intent.extra.STREAM");
                }
                else {
                    this.b.putExtra("android.intent.extra.STREAM", this.f.get(0));
                }

                this.f = v1;
            }

            if(v3 != 0 && !v0) {
                this.b.setAction("android.intent.action.SEND_MULTIPLE");
                if(this.f != null && !this.f.isEmpty()) {
                    this.b.putParcelableArrayListExtra("android.intent.extra.STREAM", this.f);
                    goto label_70;
                }

                this.b.removeExtra("android.intent.extra.STREAM");
            }

        label_70:
            return this.b;
        }

        public a a(CharSequence arg3) {
            this.b.putExtra("android.intent.extra.TEXT", arg3);
            return this;
        }

        public a a(String arg2) {
            this.b.setType(arg2);
            return this;
        }

        public a b(String arg3) {
            this.b.putExtra("android.intent.extra.SUBJECT", arg3);
            return this;
        }
    }

}

