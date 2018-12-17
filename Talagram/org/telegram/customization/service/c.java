package org.telegram.customization.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.customization.Model.Favourite;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter$NotificationCenterDelegate;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;

public class c extends Service {
    class org.telegram.customization.service.c$1 implements NotificationCenterDelegate {
        org.telegram.customization.service.c$1(c arg1) {
            this.a = arg1;
            super();
        }

        public void didReceivedNotification(int arg7, int arg8, Object[] arg9) {
            FileLog.d("ForwardFavMessage 4");
            Object v7 = arg9[2];
            __monitor_enter(this);
            try {
                FileLog.d("ForwardFavMessage 5");
                if(v7 != null && ((ArrayList)v7).size() > 0) {
                    FileLog.d("ForwardFavMessage 6");
                    int v9;
                    for(v9 = 0; v9 < ((ArrayList)v7).size(); ++v9) {
                        int v0;
                        for(v0 = 0; v0 < c.a(this.a).size(); ++v0) {
                            FileLog.d("ForwardFavMessage 7");
                            if((((long)((ArrayList)v7).get(v9).getId())) == c.a(this.a).get(v0).getMsg_id() && ((ArrayList)v7).get(v9).getDialogId() == c.a(this.a).get(v0).getChatID()) {
                                FileLog.d("ForwardFavMessage 8");
                                ArrayList v1 = new ArrayList();
                                v1.add(((ArrayList)v7).get(v9));
                                SendMessagesHelper.getInstance(UserConfig.selectedAccount).sendMessage(v1, ((long)UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()));
                                c.b(this.a);
                            }
                        }
                    }
                }

                c.c(this.a);
                __monitor_exit(this);
                return;
            label_62:
                __monitor_exit(this);
            }
            catch(Throwable v7_1) {
                goto label_62;
            }

            throw v7_1;
        }
    }

    NotificationCenterDelegate a;
    private ArrayList b;
    private int c;

    public c() {
        super();
        this.c = 0;
        this.a = new org.telegram.customization.service.c$1(this);
    }

    static ArrayList a(c arg0) {
        return arg0.b;
    }

    private void a() {
        Integer v1_2;
        StringBuilder v0 = new StringBuilder();
        v0.append("ForwardFavMessage 9 ");
        boolean v1 = this.b == null ? true : false;
        v0.append(v1);
        v0.append(" - ");
        if(this.b == null) {
            String v1_1 = "0";
        }
        else {
            v1_2 = Integer.valueOf(this.b.size());
        }

        v0.append(v1_2);
        v0.append(" - ");
        v0.append(this.c);
        FileLog.d(v0.toString());
        if(this.b == null || (this.b.isEmpty()) || this.c >= this.b.size()) {
            FileLog.d("ForwardFavMessage 10 ");
            NotificationCenter.getInstance(UserConfig.selectedAccount).removeObserver(this.a, NotificationCenter.messagesDidLoaded);
            this.stopSelf();
        }
    }

    static int b(c arg2) {
        int v0 = arg2.c;
        arg2.c = v0 + 1;
        return v0;
    }

    static void c(c arg0) {
        arg0.a();
    }

    public IBinder onBind(Intent arg1) {
        return null;
    }

    public void onCreate() {
        c v0 = this;
        super.onCreate();
        FileLog.d("ForwardFavMessage 1");
        v0.b = Favourite.getFavorites();
        if(v0.b == null || (v0.b.isEmpty())) {
            FileLog.d("ForwardFavMessage 3");
            this.stopSelf();
        }
        else {
            FileLog.d("ForwardFavMessage 2");
            NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(v0.a, NotificationCenter.messagesDidLoaded);
            Iterator v1 = v0.b.iterator();
            while(v1.hasNext()) {
                Object v2 = v1.next();
                MessagesController.getInstance(UserConfig.selectedAccount).loadMessages(((Favourite)v2).getChatID(), 1, ((int)(((Favourite)v2).getMsg_id() + 1)), 0, true, 0, 0, 4, 0, true, 0);
            }
        }
    }
}

