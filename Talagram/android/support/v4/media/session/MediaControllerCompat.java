package android.support.v4.media.session;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder$DeathRecipient;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.app.f;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class MediaControllerCompat {
    class MediaControllerImplApi21 {
        class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference a;

            protected void onReceiveResult(int arg4, Bundle arg5) {
                Object v4 = this.a.get();
                if(v4 != null) {
                    if(arg5 == null) {
                    }
                    else {
                        Object v0 = ((MediaControllerImplApi21)v4).a;
                        __monitor_enter(v0);
                        try {
                            ((MediaControllerImplApi21)v4).b.a(a.a(f.a(arg5, "android.support.v4.media.session.EXTRA_BINDER")));
                            ((MediaControllerImplApi21)v4).b.a(arg5.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE"));
                            ((MediaControllerImplApi21)v4).a();
                            __monitor_exit(v0);
                            return;
                        label_20:
                            __monitor_exit(v0);
                        }
                        catch(Throwable v4_1) {
                            goto label_20;
                        }

                        throw v4_1;
                    }
                }
            }
        }

        class android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$a extends c {
            android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$a(android.support.v4.media.session.MediaControllerCompat$a arg1) {
                super(arg1);
            }

            public void a() {
                throw new AssertionError();
            }

            public void a(Bundle arg1) {
                throw new AssertionError();
            }

            public void a(MediaMetadataCompat arg1) {
                throw new AssertionError();
            }

            public void a(ParcelableVolumeInfo arg1) {
                throw new AssertionError();
            }

            public void a(CharSequence arg1) {
                throw new AssertionError();
            }

            public void a(List arg1) {
                throw new AssertionError();
            }
        }

        final Object a;
        final Token b;
        private final List c;
        private HashMap d;

        void a() {
            if(this.b.a() == null) {
                return;
            }

            Iterator v0 = this.c.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$a v2 = new android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$a(((android.support.v4.media.session.MediaControllerCompat$a)v1));
                this.d.put(v1, v2);
                ((android.support.v4.media.session.MediaControllerCompat$a)v1).c = ((android.support.v4.media.session.a)v2);
                try {
                    this.b.a().a(((android.support.v4.media.session.a)v2));
                }
                catch(RemoteException v0_1) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", ((Throwable)v0_1));
                    break;
                }

                ((android.support.v4.media.session.MediaControllerCompat$a)v1).a(13, null, null);
            }

            this.c.clear();
        }
    }

    public abstract class android.support.v4.media.session.MediaControllerCompat$a implements IBinder$DeathRecipient {
        class android.support.v4.media.session.MediaControllerCompat$a$a extends Handler {
            boolean a;

            public void handleMessage(Message arg3) {
                if(!this.a) {
                    return;
                }

                switch(arg3.what) {
                    case 1: {
                        Bundle v0 = arg3.getData();
                        MediaSessionCompat.a(v0);
                        this.b.a(arg3.obj, v0);
                        break;
                    }
                    case 2: {
                        this.b.a(arg3.obj);
                        break;
                    }
                    case 3: {
                        this.b.a(arg3.obj);
                        break;
                    }
                    case 4: {
                        this.b.a(arg3.obj);
                        break;
                    }
                    case 5: {
                        this.b.a(arg3.obj);
                        break;
                    }
                    case 6: {
                        this.b.a(arg3.obj);
                        break;
                    }
                    case 7: {
                        Object v3 = arg3.obj;
                        MediaSessionCompat.a(((Bundle)v3));
                        this.b.a(((Bundle)v3));
                        break;
                    }
                    case 8: {
                        this.b.b();
                        break;
                    }
                    case 9: {
                        this.b.a(arg3.obj.intValue());
                        break;
                    }
                    case 11: {
                        this.b.a(arg3.obj.booleanValue());
                        break;
                    }
                    case 12: {
                        this.b.b(arg3.obj.intValue());
                        break;
                    }
                    case 13: {
                        this.b.a();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }

        class b implements android.support.v4.media.session.c$a {
            private final WeakReference a;

            b(android.support.v4.media.session.MediaControllerCompat$a arg2) {
                super();
                this.a = new WeakReference(arg2);
            }

            public void a() {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).b();
                }
            }

            public void a(int arg9, int arg10, int arg11, int arg12, int arg13) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(new android.support.v4.media.session.MediaControllerCompat$b(arg9, arg10, arg11, arg12, arg13));
                }
            }

            public void a(Bundle arg2) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(arg2);
                }
            }

            public void a(CharSequence arg2) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(arg2);
                }
            }

            public void a(Object arg3) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    if(((android.support.v4.media.session.MediaControllerCompat$a)v0).c != null) {
                    }
                    else {
                        ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(PlaybackStateCompat.a(arg3));
                    }
                }
            }

            public void a(String arg4, Bundle arg5) {
                Object v0 = this.a.get();
                if(v0 != null && (((android.support.v4.media.session.MediaControllerCompat$a)v0).c == null || Build$VERSION.SDK_INT >= 23)) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(arg4, arg5);
                }
            }

            public void a(List arg2) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(QueueItem.a(arg2));
                }
            }

            public void b(Object arg2) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(MediaMetadataCompat.a(arg2));
                }
            }
        }

        class c extends android.support.v4.media.session.a$a {
            private final WeakReference a;

            c(android.support.v4.media.session.MediaControllerCompat$a arg2) {
                super();
                this.a = new WeakReference(arg2);
            }

            public void a() {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(8, null, null);
                }
            }

            public void a(int arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(9, Integer.valueOf(arg4), null);
                }
            }

            public void a(Bundle arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(7, arg4, null);
                }
            }

            public void a(MediaMetadataCompat arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(3, arg4, null);
                }
            }

            public void a(ParcelableVolumeInfo arg10) {
                android.support.v4.media.session.MediaControllerCompat$b v8;
                Object v0 = this.a.get();
                if(v0 != null) {
                    Bundle v1 = null;
                    if(arg10 != null) {
                        v8 = new android.support.v4.media.session.MediaControllerCompat$b(arg10.a, arg10.b, arg10.c, arg10.d, arg10.e);
                    }
                    else {
                        Object v8_1 = v1;
                    }

                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(4, v8, v1);
                }
            }

            public void a(PlaybackStateCompat arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(2, arg4, null);
                }
            }

            public void a(CharSequence arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(6, arg4, null);
                }
            }

            public void a(String arg3, Bundle arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(1, arg3, arg4);
                }
            }

            public void a(List arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(5, arg4, null);
                }
            }

            public void a(boolean arg1) {
            }

            public void b() {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(13, null, null);
                }
            }

            public void b(int arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(12, Integer.valueOf(arg4), null);
                }
            }

            public void b(boolean arg4) {
                Object v0 = this.a.get();
                if(v0 != null) {
                    ((android.support.v4.media.session.MediaControllerCompat$a)v0).a(11, Boolean.valueOf(arg4), null);
                }
            }
        }

        final Object a;
        android.support.v4.media.session.MediaControllerCompat$a$a b;
        android.support.v4.media.session.a c;

        public android.support.v4.media.session.MediaControllerCompat$a() {
            c v0_1;
            super();
            if(Build$VERSION.SDK_INT >= 21) {
                Object v0 = android.support.v4.media.session.c.a(new b(this));
            }
            else {
                v0_1 = new c(this);
                this.c = ((android.support.v4.media.session.a)v0_1);
            }

            this.a = v0_1;
        }

        void a(int arg2, Object arg3, Bundle arg4) {
            if(this.b != null) {
                Message v2 = this.b.obtainMessage(arg2, arg3);
                v2.setData(arg4);
                v2.sendToTarget();
            }
        }

        public void a() {
        }

        public void a(int arg1) {
        }

        public void a(Bundle arg1) {
        }

        public void a(MediaMetadataCompat arg1) {
        }

        public void a(android.support.v4.media.session.MediaControllerCompat$b arg1) {
        }

        public void a(PlaybackStateCompat arg1) {
        }

        public void a(CharSequence arg1) {
        }

        public void a(String arg1, Bundle arg2) {
        }

        public void a(List arg1) {
        }

        public void a(boolean arg1) {
        }

        public void b() {
        }

        public void b(int arg1) {
        }
    }

    public final class android.support.v4.media.session.MediaControllerCompat$b {
        private final int a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;

        android.support.v4.media.session.MediaControllerCompat$b(int arg1, int arg2, int arg3, int arg4, int arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
        }
    }

}

