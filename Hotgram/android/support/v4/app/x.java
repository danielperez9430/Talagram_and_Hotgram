package android.support.v4.app;

import android.app.Notification$Action$Builder;
import android.app.Notification$Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class x implements v {
    private final Notification$Builder a;
    private final d b;
    private RemoteViews c;
    private RemoteViews d;
    private final List e;
    private final Bundle f;
    private int g;
    private RemoteViews h;

    x(d arg10) {
        String v6_1;
        Bundle v2_2;
        super();
        this.e = new ArrayList();
        this.f = new Bundle();
        this.b = arg10;
        int v1 = 26;
        Notification$Builder v0 = Build$VERSION.SDK_INT >= v1 ? new Notification$Builder(arg10.a, arg10.I) : new Notification$Builder(arg10.a);
        this.a = v0;
        Notification v0_1 = arg10.N;
        Notification$Builder v2 = this.a.setWhen(v0_1.when).setSmallIcon(v0_1.icon, v0_1.iconLevel).setContent(v0_1.contentView).setTicker(v0_1.tickerText, arg10.h).setVibrate(v0_1.vibrate).setLights(v0_1.ledARGB, v0_1.ledOnMS, v0_1.ledOffMS);
        boolean v3 = (v0_1.flags & 2) != 0 ? true : false;
        v2 = v2.setOngoing(v3);
        v3 = (v0_1.flags & 8) != 0 ? true : false;
        v2 = v2.setOnlyAlertOnce(v3);
        int v6 = 16;
        v3 = (v0_1.flags & v6) != 0 ? true : false;
        v2 = v2.setAutoCancel(v3).setDefaults(v0_1.defaults).setContentTitle(arg10.d).setContentText(arg10.e).setContentInfo(arg10.j).setContentIntent(arg10.f).setDeleteIntent(v0_1.deleteIntent);
        PendingIntent v3_1 = arg10.g;
        boolean v7 = (v0_1.flags & 128) != 0 ? true : false;
        v2.setFullScreenIntent(v3_1, v7).setLargeIcon(arg10.i).setNumber(arg10.k).setProgress(arg10.r, arg10.s, arg10.t);
        int v3_2 = 21;
        if(Build$VERSION.SDK_INT < v3_2) {
            this.a.setSound(v0_1.sound, v0_1.audioStreamType);
        }

        int v7_1 = 20;
        if(Build$VERSION.SDK_INT >= v6) {
            this.a.setSubText(arg10.p).setUsesChronometer(arg10.n).setPriority(arg10.l);
            Iterator v2_1 = arg10.b.iterator();
            while(v2_1.hasNext()) {
                this.a(v2_1.next());
            }

            if(arg10.B != null) {
                this.f.putAll(arg10.B);
            }

            if(Build$VERSION.SDK_INT < v7_1) {
                if(arg10.x) {
                    this.f.putBoolean("android.support.localOnly", true);
                }

                if(arg10.u != null) {
                    this.f.putString("android.support.groupKey", arg10.u);
                    if(arg10.v) {
                        v2_2 = this.f;
                        v6_1 = "android.support.isGroupSummary";
                    }
                    else {
                        v2_2 = this.f;
                        v6_1 = "android.support.useSideChannel";
                    }

                    v2_2.putBoolean(v6_1, true);
                }

                if(arg10.w == null) {
                    goto label_148;
                }

                this.f.putString("android.support.sortKey", arg10.w);
            }

        label_148:
            this.c = arg10.F;
            this.d = arg10.G;
        }

        if(Build$VERSION.SDK_INT >= 19) {
            this.a.setShowWhen(arg10.m);
            if(Build$VERSION.SDK_INT < v3_2 && arg10.O != null && !arg10.O.isEmpty()) {
                this.f.putStringArray("android.people", arg10.O.toArray(new String[arg10.O.size()]));
            }
        }

        if(Build$VERSION.SDK_INT >= v7_1) {
            this.a.setLocalOnly(arg10.x).setGroup(arg10.u).setGroupSummary(arg10.v).setSortKey(arg10.w);
            this.g = arg10.M;
        }

        if(Build$VERSION.SDK_INT >= v3_2) {
            this.a.setCategory(arg10.A).setColor(arg10.C).setVisibility(arg10.D).setPublicVersion(arg10.E).setSound(v0_1.sound, v0_1.audioAttributes);
            Iterator v0_2 = arg10.O.iterator();
            while(v0_2.hasNext()) {
                this.a.addPerson(v0_2.next());
            }

            this.h = arg10.H;
            if(arg10.c.size() > 0) {
                Bundle v0_3 = arg10.a().getBundle("android.car.EXTENSIONS");
                if(v0_3 == null) {
                    v0_3 = new Bundle();
                }

                v2_2 = new Bundle();
                for(v3_2 = 0; v3_2 < arg10.c.size(); ++v3_2) {
                    v2_2.putBundle(Integer.toString(v3_2), y.a(arg10.c.get(v3_2)));
                }

                v0_3.putBundle("invisible_actions", v2_2);
                arg10.a().putBundle("android.car.EXTENSIONS", v0_3);
                this.f.putBundle("android.car.EXTENSIONS", v0_3);
            }
        }

        if(Build$VERSION.SDK_INT >= 24) {
            this.a.setExtras(arg10.B).setRemoteInputHistory(arg10.q);
            if(arg10.F != null) {
                this.a.setCustomContentView(arg10.F);
            }

            if(arg10.G != null) {
                this.a.setCustomBigContentView(arg10.G);
            }

            if(arg10.H == null) {
                goto label_263;
            }

            this.a.setCustomHeadsUpContentView(arg10.H);
        }

    label_263:
        if(Build$VERSION.SDK_INT >= v1) {
            this.a.setBadgeIconType(arg10.J).setShortcutId(arg10.K).setTimeoutAfter(arg10.L).setGroupAlertBehavior(arg10.M);
            if(arg10.z) {
                this.a.setColorized(arg10.y);
            }

            if(TextUtils.isEmpty(arg10.I)) {
                return;
            }

            this.a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
    }

    private void a(a arg6) {
        if(Build$VERSION.SDK_INT >= 20) {
            Notification$Action$Builder v0 = new Notification$Action$Builder(arg6.a(), arg6.b(), arg6.c());
            if(arg6.f() != null) {
                RemoteInput[] v1 = ac.a(arg6.f());
                int v2 = v1.length;
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    v0.addRemoteInput(v1[v3]);
                }
            }

            Bundle v1_1 = arg6.d() != null ? new Bundle(arg6.d()) : new Bundle();
            v1_1.putBoolean("android.support.allowGeneratedReplies", arg6.e());
            if(Build$VERSION.SDK_INT >= 24) {
                v0.setAllowGeneratedReplies(arg6.e());
            }

            v1_1.putInt("android.support.action.semanticAction", arg6.g());
            if(Build$VERSION.SDK_INT >= 28) {
                v0.setSemanticAction(arg6.g());
            }

            v1_1.putBoolean("android.support.action.showsUserInterface", arg6.i());
            v0.addExtras(v1_1);
            this.a.addAction(v0.build());
        }
        else {
            if(Build$VERSION.SDK_INT < 16) {
                return;
            }

            this.e.add(y.a(this.a, arg6));
        }
    }

    private void a(Notification arg2) {
        arg2.sound = null;
        arg2.vibrate = null;
        arg2.defaults &= -2;
        arg2.defaults &= -3;
    }

    public Notification$Builder a() {
        return this.a;
    }

    public Notification b() {
        i v0 = this.b.o;
        if(v0 != null) {
            v0.a(((v)this));
        }

        RemoteViews v1 = v0 != null ? v0.b(((v)this)) : null;
        Notification v2 = this.c();
        if(v1 != null) {
        label_10:
            v2.contentView = v1;
        }
        else if(this.b.F != null) {
            v1 = this.b.F;
            goto label_10;
        }

        int v3 = 16;
        if(Build$VERSION.SDK_INT >= v3 && v0 != null) {
            v1 = v0.c(((v)this));
            if(v1 != null) {
                v2.bigContentView = v1;
            }
        }

        if(Build$VERSION.SDK_INT >= 21 && v0 != null) {
            v1 = this.b.o.d(((v)this));
            if(v1 != null) {
                v2.headsUpContentView = v1;
            }
        }

        if(Build$VERSION.SDK_INT >= v3 && v0 != null) {
            Bundle v1_1 = w.a(v2);
            if(v1_1 != null) {
                v0.a(v1_1);
            }
        }

        return v2;
    }

    protected Notification c() {
        Notification v0;
        if(Build$VERSION.SDK_INT >= 26) {
            return this.a.build();
        }

        int v3 = 2;
        if(Build$VERSION.SDK_INT >= 24) {
            v0 = this.a.build();
            if(this.g != 0) {
                if(v0.getGroup() != null && (v0.flags & 512) != 0 && this.g == v3) {
                    this.a(v0);
                }

                if(v0.getGroup() == null) {
                    return v0;
                }

                if((v0.flags & 512) != 0) {
                    return v0;
                }

                if(this.g != 1) {
                    return v0;
                }

                this.a(v0);
            }

            return v0;
        }

        if(Build$VERSION.SDK_INT >= 21) {
            this.a.setExtras(this.f);
            v0 = this.a.build();
            if(this.c != null) {
                v0.contentView = this.c;
            }

            if(this.d != null) {
                v0.bigContentView = this.d;
            }

            if(this.h != null) {
                v0.headsUpContentView = this.h;
            }

            if(this.g != 0) {
                if(v0.getGroup() != null && (v0.flags & 512) != 0 && this.g == v3) {
                    this.a(v0);
                }

                if(v0.getGroup() == null) {
                    return v0;
                }

                if((v0.flags & 512) != 0) {
                    return v0;
                }

                if(this.g != 1) {
                    return v0;
                }

                this.a(v0);
            }

            return v0;
        }

        if(Build$VERSION.SDK_INT >= 20) {
            this.a.setExtras(this.f);
            v0 = this.a.build();
            if(this.c != null) {
                v0.contentView = this.c;
            }

            if(this.d != null) {
                v0.bigContentView = this.d;
            }

            if(this.g != 0) {
                if(v0.getGroup() != null && (v0.flags & 512) != 0 && this.g == v3) {
                    this.a(v0);
                }

                if(v0.getGroup() == null) {
                    return v0;
                }

                if((v0.flags & 512) != 0) {
                    return v0;
                }

                if(this.g != 1) {
                    return v0;
                }

                this.a(v0);
            }

            return v0;
        }

        if(Build$VERSION.SDK_INT >= 19) {
            SparseArray v0_1 = y.a(this.e);
            if(v0_1 != null) {
                this.f.putSparseParcelableArray("android.support.actionExtras", v0_1);
            }

            this.a.setExtras(this.f);
            v0 = this.a.build();
            if(this.c != null) {
                v0.contentView = this.c;
            }

            if(this.d != null) {
                v0.bigContentView = this.d;
            }

            return v0;
        }

        if(Build$VERSION.SDK_INT >= 16) {
            v0 = this.a.build();
            Bundle v1 = w.a(v0);
            Bundle v2 = new Bundle(this.f);
            Iterator v3_1 = this.f.keySet().iterator();
            while(v3_1.hasNext()) {
                Object v4 = v3_1.next();
                if(!v1.containsKey(((String)v4))) {
                    continue;
                }

                v2.remove(((String)v4));
            }

            v1.putAll(v2);
            SparseArray v1_1 = y.a(this.e);
            if(v1_1 != null) {
                w.a(v0).putSparseParcelableArray("android.support.actionExtras", v1_1);
            }

            if(this.c != null) {
                v0.contentView = this.c;
            }

            if(this.d != null) {
                v0.bigContentView = this.d;
            }

            return v0;
        }

        return this.a.getNotification();
    }
}

