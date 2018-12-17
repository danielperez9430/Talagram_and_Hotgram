package android.support.v4.app;

import android.app.Notification$Action$Builder;
import android.app.Notification$Action;
import android.app.Notification$BigPictureStyle;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import android.app.Notification$InboxStyle;
import android.app.Notification$MessagingStyle$Message;
import android.app.Notification$MessagingStyle;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.app.RemoteInput$Builder;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioAttributes$Builder;
import android.net.Uri;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class w {
    public class a {
        public final class android.support.v4.app.w$a$a {
            private final int a;
            private final CharSequence b;
            private final PendingIntent c;
            private boolean d;
            private final Bundle e;
            private ArrayList f;
            private int g;
            private boolean h;

            public android.support.v4.app.w$a$a(int arg10, CharSequence arg11, PendingIntent arg12) {
                this(arg10, arg11, arg12, new Bundle(), null, true, 0, true);
            }

            private android.support.v4.app.w$a$a(int arg2, CharSequence arg3, PendingIntent arg4, Bundle arg5, ac[] arg6, boolean arg7, int arg8, boolean arg9) {
                super();
                this.d = true;
                this.h = true;
                this.a = arg2;
                this.b = d.e(arg3);
                this.c = arg4;
                this.e = arg5;
                ArrayList v2 = arg6 == null ? null : new ArrayList(Arrays.asList(((Object[])arg6)));
                this.f = v2;
                this.d = arg7;
                this.g = arg8;
                this.h = arg9;
            }

            public android.support.v4.app.w$a$a a(ac arg2) {
                if(this.f == null) {
                    this.f = new ArrayList();
                }

                this.f.add(arg2);
                return this;
            }

            public android.support.v4.app.w$a$a a(boolean arg1) {
                this.d = arg1;
                return this;
            }

            public a a() {
                ac[] v10;
                ArrayList v0 = new ArrayList();
                ArrayList v1 = new ArrayList();
                if(this.f != null) {
                    Iterator v2 = this.f.iterator();
                    while(v2.hasNext()) {
                        Object v3 = v2.next();
                        if(((ac)v3).e()) {
                            ((List)v0).add(v3);
                        }
                        else {
                            ((List)v1).add(v3);
                        }
                    }
                }

                Object[] v3_1 = null;
                if(((List)v0).isEmpty()) {
                    v10 = ((ac[])v3_1);
                }
                else {
                    Object[] v10_1 = ((List)v0).toArray(new ac[((List)v0).size()]);
                }

                if(!((List)v1).isEmpty()) {
                    v3_1 = ((List)v1).toArray(new ac[((List)v1).size()]);
                }

                Object[] v9 = v3_1;
                return new a(this.a, this.b, this.c, this.e, ((ac[])v9), v10, this.d, this.g, this.h);
            }
        }

        final Bundle a;
        boolean b;
        public int c;
        public CharSequence d;
        public PendingIntent e;
        private final ac[] f;
        private final ac[] g;
        private boolean h;
        private final int i;

        public a(int arg11, CharSequence arg12, PendingIntent arg13) {
            this(arg11, arg12, arg13, new Bundle(), null, null, true, 0, true);
        }

        a(int arg2, CharSequence arg3, PendingIntent arg4, Bundle arg5, ac[] arg6, ac[] arg7, boolean arg8, int arg9, boolean arg10) {
            super();
            this.b = true;
            this.c = arg2;
            this.d = d.e(arg3);
            this.e = arg4;
            if(arg5 != null) {
            }
            else {
                arg5 = new Bundle();
            }

            this.a = arg5;
            this.f = arg6;
            this.g = arg7;
            this.h = arg8;
            this.i = arg9;
            this.b = arg10;
        }

        public int a() {
            return this.c;
        }

        public CharSequence b() {
            return this.d;
        }

        public PendingIntent c() {
            return this.e;
        }

        public Bundle d() {
            return this.a;
        }

        public boolean e() {
            return this.h;
        }

        public ac[] f() {
            return this.f;
        }

        public int g() {
            return this.i;
        }

        public ac[] h() {
            return this.g;
        }

        public boolean i() {
            return this.b;
        }
    }

    public class b extends i {
        private Bitmap e;
        private Bitmap f;
        private boolean g;

        public b() {
            super();
        }

        public b a(Bitmap arg1) {
            this.e = arg1;
            return this;
        }

        public b a(CharSequence arg1) {
            this.b = d.e(arg1);
            return this;
        }

        public void a(v arg3) {
            if(Build$VERSION.SDK_INT >= 16) {
                Notification$BigPictureStyle v3 = new Notification$BigPictureStyle(arg3.a()).setBigContentTitle(this.b).bigPicture(this.e);
                if(this.g) {
                    v3.bigLargeIcon(this.f);
                }

                if(!this.d) {
                    return;
                }

                v3.setSummaryText(this.c);
            }
        }

        public b b(CharSequence arg1) {
            this.c = d.e(arg1);
            this.d = true;
            return this;
        }
    }

    public class c extends i {
        private CharSequence e;

        public c() {
            super();
        }

        public c a(CharSequence arg1) {
            this.e = d.e(arg1);
            return this;
        }

        public void a(v arg3) {
            if(Build$VERSION.SDK_INT >= 16) {
                Notification$BigTextStyle v3 = new Notification$BigTextStyle(arg3.a()).setBigContentTitle(this.b).bigText(this.e);
                if(this.d) {
                    v3.setSummaryText(this.c);
                }
            }
        }
    }

    public class d {
        String A;
        Bundle B;
        int C;
        int D;
        Notification E;
        RemoteViews F;
        RemoteViews G;
        RemoteViews H;
        String I;
        int J;
        String K;
        long L;
        int M;
        Notification N;
        @Deprecated public ArrayList O;
        public Context a;
        public ArrayList b;
        ArrayList c;
        CharSequence d;
        CharSequence e;
        PendingIntent f;
        PendingIntent g;
        RemoteViews h;
        Bitmap i;
        CharSequence j;
        int k;
        int l;
        boolean m;
        boolean n;
        i o;
        CharSequence p;
        CharSequence[] q;
        int r;
        int s;
        boolean t;
        String u;
        boolean v;
        String w;
        boolean x;
        boolean y;
        boolean z;

        @Deprecated public d(Context arg2) {
            this(arg2, null);
        }

        public d(Context arg4, String arg5) {
            super();
            this.b = new ArrayList();
            this.c = new ArrayList();
            this.m = true;
            this.x = false;
            this.C = 0;
            this.D = 0;
            this.J = 0;
            this.M = 0;
            this.N = new Notification();
            this.a = arg4;
            this.I = arg5;
            this.N.when = System.currentTimeMillis();
            this.N.audioStreamType = -1;
            this.l = 0;
            this.O = new ArrayList();
        }

        private void a(int arg2, boolean arg3) {
            Notification v3;
            if(arg3) {
                v3 = this.N;
                arg2 |= v3.flags;
            }
            else {
                v3 = this.N;
                arg2 = (arg2 ^ -1) & v3.flags;
            }

            v3.flags = arg2;
        }

        public Bundle a() {
            if(this.B == null) {
                this.B = new Bundle();
            }

            return this.B;
        }

        public d a(int arg2) {
            this.N.icon = arg2;
            return this;
        }

        public d a(int arg2, int arg3, int arg4) {
            this.N.ledARGB = arg2;
            this.N.ledOnMS = arg3;
            this.N.ledOffMS = arg4;
            arg2 = this.N.ledOnMS == 0 || this.N.ledOffMS == 0 ? 0 : 1;
            this.N.flags = arg2 | this.N.flags & -2;
            return this;
        }

        public d a(int arg1, int arg2, boolean arg3) {
            this.r = arg1;
            this.s = arg2;
            this.t = arg3;
            return this;
        }

        public d a(int arg3, CharSequence arg4, PendingIntent arg5) {
            this.b.add(new a(arg3, arg4, arg5));
            return this;
        }

        public d a(long arg2) {
            this.N.when = arg2;
            return this;
        }

        public d a(PendingIntent arg1) {
            this.f = arg1;
            return this;
        }

        public d a(Bitmap arg1) {
            this.i = this.b(arg1);
            return this;
        }

        public d a(Uri arg3) {
            this.N.sound = arg3;
            this.N.audioStreamType = -1;
            if(Build$VERSION.SDK_INT >= 21) {
                this.N.audioAttributes = new AudioAttributes$Builder().setContentType(4).setUsage(5).build();
            }

            return this;
        }

        public d a(Uri arg3, int arg4) {
            this.N.sound = arg3;
            this.N.audioStreamType = arg4;
            if(Build$VERSION.SDK_INT >= 21) {
                this.N.audioAttributes = new AudioAttributes$Builder().setContentType(4).setLegacyStreamType(arg4).build();
            }

            return this;
        }

        public d a(a arg2) {
            this.b.add(arg2);
            return this;
        }

        public d a(f arg1) {
            arg1.a(this);
            return this;
        }

        public d a(i arg2) {
            if(this.o != arg2) {
                this.o = arg2;
                if(this.o != null) {
                    this.o.a(this);
                }
            }

            return this;
        }

        public d a(CharSequence arg1) {
            this.d = d.e(arg1);
            return this;
        }

        public d a(String arg1) {
            this.A = arg1;
            return this;
        }

        public d a(boolean arg1) {
            this.m = arg1;
            return this;
        }

        public d a(long[] arg2) {
            this.N.vibrate = arg2;
            return this;
        }

        private Bitmap b(Bitmap arg10) {
            if(arg10 != null) {
                if(Build$VERSION.SDK_INT >= 27) {
                }
                else {
                    Resources v0 = this.a.getResources();
                    int v1 = v0.getDimensionPixelSize(android.support.a.a$b.compat_notification_large_icon_max_width);
                    int v0_1 = v0.getDimensionPixelSize(android.support.a.a$b.compat_notification_large_icon_max_height);
                    if(arg10.getWidth() <= v1 && arg10.getHeight() <= v0_1) {
                        return arg10;
                    }

                    double v1_1 = ((double)v1);
                    double v5 = ((double)Math.max(1, arg10.getWidth()));
                    Double.isNaN(v1_1);
                    Double.isNaN(v5);
                    v1_1 /= v5;
                    v5 = ((double)v0_1);
                    double v7 = ((double)Math.max(1, arg10.getHeight()));
                    Double.isNaN(v5);
                    Double.isNaN(v7);
                    double v0_2 = Math.min(v1_1, v5 / v7);
                    double v2 = ((double)arg10.getWidth());
                    Double.isNaN(v2);
                    int v2_1 = ((int)Math.ceil(v2 * v0_2));
                    v5 = ((double)arg10.getHeight());
                    Double.isNaN(v5);
                    arg10 = Bitmap.createScaledBitmap(arg10, v2_1, ((int)Math.ceil(v5 * v0_2)), true);
                }
            }

            return arg10;
        }

        public Notification b() {
            return new x(this).b();
        }

        public d b(int arg1) {
            this.k = arg1;
            return this;
        }

        public d b(PendingIntent arg2) {
            this.N.deleteIntent = arg2;
            return this;
        }

        public d b(CharSequence arg1) {
            this.e = d.e(arg1);
            return this;
        }

        public d b(String arg2) {
            this.O.add(arg2);
            return this;
        }

        public d b(boolean arg2) {
            this.a(16, arg2);
            return this;
        }

        public int c() {
            return this.C;
        }

        public d c(int arg2) {
            this.N.defaults = arg2;
            if((arg2 & 4) != 0) {
                this.N.flags |= 1;
            }

            return this;
        }

        public d c(CharSequence arg1) {
            this.p = d.e(arg1);
            return this;
        }

        public d c(String arg1) {
            this.u = arg1;
            return this;
        }

        public d c(boolean arg1) {
            this.x = arg1;
            return this;
        }

        public d d(int arg1) {
            this.l = arg1;
            return this;
        }

        public d d(CharSequence arg2) {
            this.N.tickerText = d.e(arg2);
            return this;
        }

        public d d(String arg1) {
            this.w = arg1;
            return this;
        }

        public d d(boolean arg1) {
            this.v = arg1;
            return this;
        }

        protected static CharSequence e(CharSequence arg2) {
            if(arg2 == null) {
                return arg2;
            }

            int v1 = 5120;
            if(arg2.length() > v1) {
                arg2 = arg2.subSequence(0, v1);
            }

            return arg2;
        }

        public d e(int arg1) {
            this.C = arg1;
            return this;
        }

        public d e(String arg1) {
            this.I = arg1;
            return this;
        }

        public d f(int arg1) {
            this.M = arg1;
            return this;
        }

        public d f(String arg1) {
            this.K = arg1;
            return this;
        }
    }

    public final class e implements f {
        public class android.support.v4.app.w$e$a {
            public class android.support.v4.app.w$e$a$a {
                private final List a;
                private final String b;
                private ac c;
                private PendingIntent d;
                private PendingIntent e;
                private long f;

                public android.support.v4.app.w$e$a$a(String arg2) {
                    super();
                    this.a = new ArrayList();
                    this.b = arg2;
                }

                public android.support.v4.app.w$e$a$a a(long arg1) {
                    this.f = arg1;
                    return this;
                }

                public android.support.v4.app.w$e$a$a a(PendingIntent arg1) {
                    this.d = arg1;
                    return this;
                }

                public android.support.v4.app.w$e$a$a a(PendingIntent arg1, ac arg2) {
                    this.c = arg2;
                    this.e = arg1;
                    return this;
                }

                public android.support.v4.app.w$e$a$a a(String arg2) {
                    this.a.add(arg2);
                    return this;
                }

                public android.support.v4.app.w$e$a a() {
                    return new android.support.v4.app.w$e$a(this.a.toArray(new String[this.a.size()]), this.c, this.e, this.d, new String[]{this.b}, this.f);
                }
            }

            private final String[] a;
            private final ac b;
            private final PendingIntent c;
            private final PendingIntent d;
            private final String[] e;
            private final long f;

            android.support.v4.app.w$e$a(String[] arg1, ac arg2, PendingIntent arg3, PendingIntent arg4, String[] arg5, long arg6) {
                super();
                this.a = arg1;
                this.b = arg2;
                this.d = arg4;
                this.c = arg3;
                this.e = arg5;
                this.f = arg6;
            }

            public String[] a() {
                return this.a;
            }

            public ac b() {
                return this.b;
            }

            public PendingIntent c() {
                return this.c;
            }

            public PendingIntent d() {
                return this.d;
            }

            public String[] e() {
                return this.e;
            }

            public long f() {
                return this.f;
            }
        }

        private Bitmap a;
        private android.support.v4.app.w$e$a b;
        private int c;

        public e() {
            super();
            this.c = 0;
        }

        public d a(d arg4) {
            if(Build$VERSION.SDK_INT < 21) {
                return arg4;
            }

            Bundle v0 = new Bundle();
            if(this.a != null) {
                v0.putParcelable("large_icon", this.a);
            }

            if(this.c != 0) {
                v0.putInt("app_color", this.c);
            }

            if(this.b != null) {
                v0.putBundle("car_conversation", e.b(this.b));
            }

            arg4.a().putBundle("android.car.EXTENSIONS", v0);
            return arg4;
        }

        public e a(android.support.v4.app.w$e$a arg1) {
            this.b = arg1;
            return this;
        }

        private static Bundle b(android.support.v4.app.w$e$a arg7) {
            Bundle v0 = new Bundle();
            int v2 = 0;
            String v1 = arg7.e() == null || arg7.e().length <= 1 ? null : arg7.e()[0];
            Parcelable[] v3 = new Parcelable[arg7.a().length];
            while(v2 < v3.length) {
                Bundle v4 = new Bundle();
                v4.putString("text", arg7.a()[v2]);
                v4.putString("author", v1);
                v3[v2] = ((Parcelable)v4);
                ++v2;
            }

            v0.putParcelableArray("messages", v3);
            ac v1_1 = arg7.b();
            if(v1_1 != null) {
                v0.putParcelable("remote_input", new RemoteInput$Builder(v1_1.a()).setLabel(v1_1.b()).setChoices(v1_1.c()).setAllowFreeFormInput(v1_1.f()).addExtras(v1_1.g()).build());
            }

            v0.putParcelable("on_reply", arg7.c());
            v0.putParcelable("on_read", arg7.d());
            v0.putStringArray("participants", arg7.e());
            v0.putLong("timestamp", arg7.f());
            return v0;
        }
    }

    public interface f {
        d a(d arg1);
    }

    public class g extends i {
        private ArrayList e;

        public g() {
            super();
            this.e = new ArrayList();
        }

        public g a(CharSequence arg1) {
            this.b = d.e(arg1);
            return this;
        }

        public void a(v arg3) {
            if(Build$VERSION.SDK_INT >= 16) {
                Notification$InboxStyle v3 = new Notification$InboxStyle(arg3.a()).setBigContentTitle(this.b);
                if(this.d) {
                    v3.setSummaryText(this.c);
                }

                Iterator v0 = this.e.iterator();
                while(v0.hasNext()) {
                    v3.addLine(v0.next());
                }
            }
        }

        public g b(CharSequence arg1) {
            this.c = d.e(arg1);
            this.d = true;
            return this;
        }

        public g c(CharSequence arg2) {
            this.e.add(d.e(arg2));
            return this;
        }
    }

    public class h extends i {
        public final class android.support.v4.app.w$h$a {
            private final CharSequence a;
            private final long b;
            private final ab c;
            private Bundle d;
            private String e;
            private Uri f;

            public android.support.v4.app.w$h$a(CharSequence arg2, long arg3, ab arg5) {
                super();
                this.d = new Bundle();
                this.a = arg2;
                this.b = arg3;
                this.c = arg5;
            }

            public CharSequence a() {
                return this.a;
            }

            static Bundle[] a(List arg4) {
                Bundle[] v0 = new Bundle[arg4.size()];
                int v1 = arg4.size();
                int v2;
                for(v2 = 0; v2 < v1; ++v2) {
                    v0[v2] = arg4.get(v2).f();
                }

                return v0;
            }

            public android.support.v4.app.w$h$a a(String arg1, Uri arg2) {
                this.e = arg1;
                this.f = arg2;
                return this;
            }

            public long b() {
                return this.b;
            }

            public ab c() {
                return this.c;
            }

            public String d() {
                return this.e;
            }

            public Uri e() {
                return this.f;
            }

            private Bundle f() {
                Bundle v0 = new Bundle();
                if(this.a != null) {
                    v0.putCharSequence("text", this.a);
                }

                v0.putLong("time", this.b);
                if(this.c != null) {
                    v0.putCharSequence("sender", this.c.c());
                    if(Build$VERSION.SDK_INT >= 28) {
                        v0.putParcelable("sender_person", this.c.b());
                    }
                    else {
                        v0.putBundle("person", this.c.a());
                    }
                }

                if(this.e != null) {
                    v0.putString("type", this.e);
                }

                if(this.f != null) {
                    v0.putParcelable("uri", this.f);
                }

                if(this.d != null) {
                    v0.putBundle("extras", this.d);
                }

                return v0;
            }
        }

        private final List e;
        private ab f;
        private CharSequence g;
        private Boolean h;

        private h() {
            super();
            this.e = new ArrayList();
        }

        @Deprecated public h(CharSequence arg2) {
            super();
            this.e = new ArrayList();
            this.f = new android.support.v4.app.ab$a().a(arg2).a();
        }

        private TextAppearanceSpan a(int arg8) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(arg8), null);
        }

        public h a(android.support.v4.app.w$h$a arg2) {
            this.e.add(arg2);
            if(this.e.size() > 25) {
                this.e.remove(0);
            }

            return this;
        }

        public h a(CharSequence arg1) {
            this.g = arg1;
            return this;
        }

        public h a(CharSequence arg2, long arg3, ab arg5) {
            this.a(new android.support.v4.app.w$h$a(arg2, arg3, arg5));
            return this;
        }

        public h a(boolean arg1) {
            this.h = Boolean.valueOf(arg1);
            return this;
        }

        public List a() {
            return this.e;
        }

        public void a(Bundle arg3) {
            super.a(arg3);
            arg3.putCharSequence("android.selfDisplayName", this.f.c());
            arg3.putBundle("android.messagingStyleUser", this.f.a());
            arg3.putCharSequence("android.hiddenConversationTitle", this.g);
            if(this.g != null && (this.h.booleanValue())) {
                arg3.putCharSequence("android.conversationTitle", this.g);
            }

            if(!this.e.isEmpty()) {
                arg3.putParcelableArray("android.messages", android.support.v4.app.w$h$a.a(this.e));
            }

            if(this.h != null) {
                arg3.putBoolean("android.isGroupConversation", this.h.booleanValue());
            }
        }

        public void a(v arg11) {
            CharSequence v3_1;
            Notification$Builder v2_1;
            Notification$MessagingStyle$Message v6;
            int v2;
            this.a(this.b());
            CharSequence v1 = null;
            if(Build$VERSION.SDK_INT >= 24) {
                v2 = 28;
                Notification$MessagingStyle v0 = Build$VERSION.SDK_INT >= v2 ? new Notification$MessagingStyle(this.f.b()) : new Notification$MessagingStyle(this.f.c());
                if((this.h.booleanValue()) || Build$VERSION.SDK_INT >= v2) {
                    v0.setConversationTitle(this.g);
                }

                if(Build$VERSION.SDK_INT >= v2) {
                    v0.setGroupConversation(this.h.booleanValue());
                }

                Iterator v3 = this.e.iterator();
                while(v3.hasNext()) {
                    Object v4 = v3.next();
                    if(Build$VERSION.SDK_INT >= v2) {
                        ab v5 = ((android.support.v4.app.w$h$a)v4).c();
                        CharSequence v7 = ((android.support.v4.app.w$h$a)v4).a();
                        long v8 = ((android.support.v4.app.w$h$a)v4).b();
                        Person v5_1 = v5 == null ? ((Person)v1) : v5.b();
                        v6 = new Notification$MessagingStyle$Message(v7, v8, v5_1);
                    }
                    else {
                        CharSequence v5_2 = ((android.support.v4.app.w$h$a)v4).c() != null ? ((android.support.v4.app.w$h$a)v4).c().c() : v1;
                        v6 = new Notification$MessagingStyle$Message(((android.support.v4.app.w$h$a)v4).a(), ((android.support.v4.app.w$h$a)v4).b(), v5_2);
                    }

                    if(((android.support.v4.app.w$h$a)v4).d() != null) {
                        v6.setData(((android.support.v4.app.w$h$a)v4).d(), ((android.support.v4.app.w$h$a)v4).e());
                    }

                    v0.addMessage(v6);
                }

                v0.setBuilder(arg11.a());
            }
            else {
                android.support.v4.app.w$h$a v0_1 = this.c();
                if(this.g != null && (this.h.booleanValue())) {
                    v2_1 = arg11.a();
                    v3_1 = this.g;
                    goto label_75;
                }
                else if(v0_1 != null) {
                    arg11.a().setContentTitle("");
                    if(v0_1.c() != null) {
                        v2_1 = arg11.a();
                        v3_1 = v0_1.c().c();
                    label_75:
                        v2_1.setContentTitle(v3_1);
                    }
                }

                if(v0_1 != null) {
                    v2_1 = arg11.a();
                    CharSequence v0_2 = this.g != null ? this.b(v0_1) : v0_1.a();
                    v2_1.setContentText(v0_2);
                }

                if(Build$VERSION.SDK_INT < 16) {
                    return;
                }

                SpannableStringBuilder v0_3 = new SpannableStringBuilder();
                v2 = this.g != null || (this.d()) ? 1 : 0;
                int v5_3;
                for(v5_3 = this.e.size() - 1; v5_3 >= 0; --v5_3) {
                    Object v6_1 = this.e.get(v5_3);
                    CharSequence v6_2 = v2 != 0 ? this.b(((android.support.v4.app.w$h$a)v6_1)) : ((android.support.v4.app.w$h$a)v6_1).a();
                    if(v5_3 != this.e.size() - 1) {
                        v0_3.insert(0, "\n");
                    }

                    v0_3.insert(0, v6_2);
                }

                new Notification$BigTextStyle(arg11.a()).setBigContentTitle(v1).bigText(((CharSequence)v0_3));
            }
        }

        private CharSequence b(android.support.v4.app.w$h$a arg7) {
            String v7;
            CharSequence v4_1;
            android.support.v4.e.a v0 = android.support.v4.e.a.a();
            SpannableStringBuilder v1 = new SpannableStringBuilder();
            int v2 = Build$VERSION.SDK_INT >= 21 ? 1 : 0;
            int v3 = v2 != 0 ? -16777216 : -1;
            if(arg7.c() == null) {
                String v4 = "";
            }
            else {
                v4_1 = arg7.c().c();
            }

            if(TextUtils.isEmpty(v4_1)) {
                v4_1 = this.f.c();
                if(v2 != 0 && this.a.c() != 0) {
                    v3 = this.a.c();
                }
            }

            CharSequence v2_1 = v0.a(v4_1);
            v1.append(v2_1);
            v1.setSpan(this.a(v3), v1.length() - v2_1.length(), v1.length(), 33);
            if(arg7.a() == null) {
                v7 = "";
            }
            else {
                CharSequence v7_1 = arg7.a();
            }

            v1.append("  ").append(v0.a(((CharSequence)v7)));
            return ((CharSequence)v1);
        }

        public boolean b() {
            boolean v1 = false;
            if(this.a != null && this.a.a.getApplicationInfo().targetSdkVersion < 28 && this.h == null) {
                if(this.g != null) {
                    v1 = true;
                }

                return v1;
            }

            if(this.h != null) {
                v1 = this.h.booleanValue();
            }

            return v1;
        }

        private android.support.v4.app.w$h$a c() {
            int v0;
            for(v0 = this.e.size() - 1; v0 >= 0; --v0) {
                Object v1 = this.e.get(v0);
                if(((android.support.v4.app.w$h$a)v1).c() != null && !TextUtils.isEmpty(((android.support.v4.app.w$h$a)v1).c().c())) {
                    return ((android.support.v4.app.w$h$a)v1);
                }
            }

            if(!this.e.isEmpty()) {
                return this.e.get(this.e.size() - 1);
            }

            return null;
        }

        private boolean d() {
            int v0;
            for(v0 = this.e.size() - 1; v0 >= 0; --v0) {
                Object v2 = this.e.get(v0);
                if(((android.support.v4.app.w$h$a)v2).c() != null && ((android.support.v4.app.w$h$a)v2).c().c() == null) {
                    return 1;
                }
            }

            return 0;
        }
    }

    public abstract class i {
        protected d a;
        CharSequence b;
        CharSequence c;
        boolean d;

        public i() {
            super();
            this.d = false;
        }

        public void a(d arg2) {
            if(this.a != arg2) {
                this.a = arg2;
                if(this.a != null) {
                    this.a.a(this);
                }
            }
        }

        public void a(Bundle arg1) {
        }

        public void a(v arg1) {
        }

        public RemoteViews b(v arg1) {
            return null;
        }

        public RemoteViews c(v arg1) {
            return null;
        }

        public RemoteViews d(v arg1) {
            return null;
        }
    }

    public final class j implements f {
        private ArrayList a;
        private int b;
        private PendingIntent c;
        private ArrayList d;
        private Bitmap e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int l;
        private String m;
        private String n;

        public j() {
            super();
            this.a = new ArrayList();
            this.b = 1;
            this.d = new ArrayList();
            this.g = 8388613;
            this.h = -1;
            this.i = 0;
            this.k = 80;
        }

        public d a(d arg8) {
            Bundle v4_2;
            Bundle v0 = new Bundle();
            if(!this.a.isEmpty()) {
                int v2 = 16;
                if(Build$VERSION.SDK_INT >= v2) {
                    ArrayList v1 = new ArrayList(this.a.size());
                    Iterator v3 = this.a.iterator();
                    while(v3.hasNext()) {
                        Object v4 = v3.next();
                        if(Build$VERSION.SDK_INT >= 20) {
                            Notification$Action v4_1 = j.b(((a)v4));
                        }
                        else {
                            if(Build$VERSION.SDK_INT < v2) {
                                continue;
                            }

                            v4_2 = y.a(((a)v4));
                        }

                        v1.add(v4_2);
                    }

                    v0.putParcelableArrayList("actions", v1);
                }
                else {
                    v0.putParcelableArrayList("actions", null);
                }
            }

            if(this.b != 1) {
                v0.putInt("flags", this.b);
            }

            if(this.c != null) {
                v0.putParcelable("displayIntent", this.c);
            }

            if(!this.d.isEmpty()) {
                v0.putParcelableArray("pages", this.d.toArray(new Notification[this.d.size()]));
            }

            if(this.e != null) {
                v0.putParcelable("background", this.e);
            }

            if(this.f != 0) {
                v0.putInt("contentIcon", this.f);
            }

            if(this.g != 8388613) {
                v0.putInt("contentIconGravity", this.g);
            }

            if(this.h != -1) {
                v0.putInt("contentActionIndex", this.h);
            }

            if(this.i != 0) {
                v0.putInt("customSizePreset", this.i);
            }

            if(this.j != 0) {
                v0.putInt("customContentHeight", this.j);
            }

            if(this.k != 80) {
                v0.putInt("gravity", this.k);
            }

            if(this.l != 0) {
                v0.putInt("hintScreenTimeout", this.l);
            }

            if(this.m != null) {
                v0.putString("dismissalId", this.m);
            }

            if(this.n != null) {
                v0.putString("bridgeTag", this.n);
            }

            arg8.a().putBundle("android.wearable.EXTENSIONS", v0);
            return arg8;
        }

        public j a() {
            j v0 = new j();
            v0.a = new ArrayList(this.a);
            v0.b = this.b;
            v0.c = this.c;
            v0.d = new ArrayList(this.d);
            v0.e = this.e;
            v0.f = this.f;
            v0.g = this.g;
            v0.h = this.h;
            v0.i = this.i;
            v0.j = this.j;
            v0.k = this.k;
            v0.l = this.l;
            v0.m = this.m;
            v0.n = this.n;
            return v0;
        }

        public j a(a arg2) {
            this.a.add(arg2);
            return this;
        }

        public j a(String arg1) {
            this.m = arg1;
            return this;
        }

        private static Notification$Action b(a arg4) {
            Notification$Action$Builder v0 = new Notification$Action$Builder(arg4.a(), arg4.b(), arg4.c());
            Bundle v1 = arg4.d() != null ? new Bundle(arg4.d()) : new Bundle();
            v1.putBoolean("android.support.allowGeneratedReplies", arg4.e());
            if(Build$VERSION.SDK_INT >= 24) {
                v0.setAllowGeneratedReplies(arg4.e());
            }

            v0.addExtras(v1);
            ac[] v4 = arg4.f();
            if(v4 != null) {
                RemoteInput[] v4_1 = ac.a(v4);
                int v1_1 = v4_1.length;
                int v2;
                for(v2 = 0; v2 < v1_1; ++v2) {
                    v0.addRemoteInput(v4_1[v2]);
                }
            }

            return v0.build();
        }

        public j b(String arg1) {
            this.n = arg1;
            return this;
        }

        public Object clone() {
            return this.a();
        }
    }

    public static Bundle a(Notification arg2) {
        if(Build$VERSION.SDK_INT >= 19) {
            return arg2.extras;
        }

        if(Build$VERSION.SDK_INT >= 16) {
            return y.a(arg2);
        }

        return null;
    }
}

