package org.telegram.customization.g;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import f.l;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import okhttp3.aa;
import org.telegram.customization.Model.ProxyServerModel;
import org.telegram.customization.i.h;
import org.telegram.customization.j.a.a;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import utils.a.b;
import utils.d;

public class e {
    private static boolean a = false;
    private static long b;

    static {
    }

    public static String a(String arg5) {
        char v3_1;
        int v1 = 0;
        String v2 = "";
        int v0;
        for(v0 = 0; v0 < arg5.length(); ++v0) {
            Character v3 = Character.valueOf(arg5.charAt(v0));
            v3_1 = Character.isLowerCase(v3.charValue()) ? Character.toUpperCase(v3.charValue()) : Character.toLowerCase(v3.charValue());
            v2 = v2 + v3_1;
        }

        arg5 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ?/.,";
        Random v0_1 = new Random();
        while(v1 < 5) {
            v3_1 = arg5.charAt(v0_1.nextInt(arg5.length()));
            v2 = v2 + v3_1;
            ++v1;
        }

        return v2;
    }

    public static void a(Context arg1) {
        e.a(arg1, false);
    }

    public static void a(Context arg9, boolean arg10) {
        ArrayList v0 = b.ap(arg9);
        Log.d("LEE", "getProxyServer:" + v0.size());
        if(v0.size() == 0 || (arg10)) {
            org.telegram.customization.g.e$1 v1 = new a(true, arg9) {
                protected h getApiCallback() {
                    return new h() {
                        public void getProxyFailure(Object arg1, aa arg2, Object arg3, l arg4) {
                            e.a(false);
                            this.a.detach();
                        }

                        public void getProxyResult(ArrayList arg1, aa arg2, Object arg3, l arg4) {
                            e.a(false);
                            if(arg1 != null && arg1.size() > 0) {
                                ArrayList v2 = new ArrayList();
                                Iterator v1 = arg1.iterator();
                                while(true) {
                                    if(v1.hasNext()) {
                                        arg3 = v1.next();
                                        if(arg3 != null) {
                                            goto label_12;
                                        }
                                        else {
                                            continue;
                                            try {
                                            label_12:
                                                ((ProxyServerModel)arg3).fillLocalExpireTime();
                                                v2.add(arg3);
                                            }
                                            catch(Exception ) {
                                            }

                                            continue;
                                        }
                                    }
                                    else {
                                        break;
                                    }

                                    return;
                                }

                                if(v2.size() > 0) {
                                    Collections.shuffle(((List)v2));
                                    b.b(this.a.a, v2);
                                    b.t(ApplicationLoader.applicationContext, true);
                                }

                                e.a(this.a.a);
                                this.a.detach();
                            }
                        }
                    };
                }
            };
            ((a)v1).attach();
            if(System.currentTimeMillis() - e.b > 6000) {
                e.a = false;
            }

            if(b.an(arg9) == 0) {
                return;
            }

            if(e.a) {
                return;
            }

            e.b = System.currentTimeMillis();
            e.a = true;
            String v9 = d.c(ApplicationLoader.applicationContext);
            try {
                ((a)v1).api.a(e.b(), org.telegram.customization.util.a.c(v9));
            }
            catch(UnsupportedEncodingException v9_1) {
                v9_1.printStackTrace();
            }
            catch(NoSuchAlgorithmException v3) {
                ((a)v1).api.a(e.b(), v9);
                v3.printStackTrace();
            }
        }

        Log.d("LEE", "ConnectionManger:" + arg10);
        Object v9_2 = v0.size() > 0 ? v0.get(0) : null;
        if(v9_2 != null && !arg10) {
            Log.d("LEE", "ConnectionManger:ffffff");
            e.a(((ProxyServerModel)v9_2));
        }
    }

    public static void a(ProxyServerModel arg6) {
        try {
            String v0 = arg6.getIp();
            e.a(v0, "" + arg6.getPort(), arg6.getUserName(), arg6.getPassWord(), arg6.getExpireDateSecs());
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    static boolean a(boolean arg0) {
        e.a = arg0;
        return arg0;
    }

    static void a(String arg18, String arg19, String arg20, String arg21, long arg22) {
        String v9 = arg18;
        String v10 = arg20;
        String v11 = arg21;
        Log.d("LEE", "setProxyDirectly 1");
        try {
            ProxyServerModel v0_1 = ProxyServerModel.getFromTelegram();
            String v12 = arg19;
        }
        catch(Exception v0) {
            goto label_27;
        }

        try {
            if(!v0_1.equalsWith(new ProxyServerModel(v9, v12, v10, v11))) {
                goto label_28;
            }

            Log.d("AminProxy", "this is same so no set");
            ConnectionsManager.native_setProxySettings(UserConfig.selectedAccount, arg18, Integer.parseInt(arg19), arg20, arg21, "");
            return;
        }
        catch(Exception v0) {
        }

        try {
        label_27:
            v0.printStackTrace();
        label_28:
            Log.d("LEE", "setProxyDirectly 2");
            b.a(ApplicationLoader.applicationContext, arg18, arg19, arg20, arg21, true, arg22);
            SharedPreferences$Editor v0_2 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();
            boolean v2 = true;
            if(b.an(ApplicationLoader.applicationContext) == 1) {
            }
            else {
                v2 = false;
            }

            v0_2.putBoolean("proxy_enabled", v2);
            v0_2.putBoolean("proxy_enabled_calls", v2);
            v0_2.putString("proxy_ip", v9);
            int v3 = Utilities.parseInt(arg19).intValue();
            v0_2.putInt("proxy_port", v3);
            if(TextUtils.isEmpty(((CharSequence)arg21))) {
                v0_2.remove("proxy_pass");
            }
            else {
                v0_2.putString("proxy_pass", v11);
            }

            if(TextUtils.isEmpty(((CharSequence)arg20))) {
                v0_2.remove("proxy_user");
            }
            else {
                v0_2.putString("proxy_user", v10);
            }

            v0_2.commit();
            if(v2) {
                ConnectionsManager.native_setProxySettings(UserConfig.selectedAccount, "", 0, "", "", "");
                NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.didUpdatedConnectionState, new Object[0]);
                ConnectionsManager.native_setProxySettings(UserConfig.selectedAccount, arg18, v3, arg20, arg21, "");
                if(!UserConfig.getInstance(UserConfig.selectedAccount).isClientActivated()) {
                    ConnectionsManager.getInstance(UserConfig.selectedAccount).checkConnection();
                }
            }
            else {
                ConnectionsManager.native_setProxySettings(UserConfig.selectedAccount, "", 0, "", "", "");
            }

            NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.didUpdatedConnectionState, new Object[0]);
            NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationName(NotificationCenter.proxySettingsChanged, new Object[0]);
        }
        catch(Exception v0) {
            v0.printStackTrace();
        }
    }

    public static boolean a() {
        try {
            SharedPreferences v1_1 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
            boolean v2 = v1_1.getBoolean("proxy_enabled", false);
            String v1_2 = v1_1.getString("proxy_ip", "");
            String v3 = b.aj(ApplicationLoader.applicationContext);
            if(!v2) {
                return 0;
            }

            if(TextUtils.isEmpty(((CharSequence)v1_2))) {
                return 0;
            }

            if(TextUtils.isEmpty(((CharSequence)v3))) {
                return 0;
            }

            if(!v1_2.equals(v3)) {
                return 0;
            }

            if(b.an(ApplicationLoader.applicationContext) <= 0) {
                return 0;
            }
        }
        catch(Exception v1) {
            v1.printStackTrace();
            return 0;
        }

        return 1;
    }

    private static ProxyServerModel b() {
        ProxyServerModel v0 = ProxyServerModel.getFromShared();
        ProxyServerModel v1 = new ProxyServerModel();
        int v3 = 2;
        if(!TextUtils.isEmpty(v0.getIp())) {
            String[] v2 = v0.getIp().split("\\.");
            if(v2.length > v3) {
                v1.setIp(v2[v2.length - v3] + "." + v2[v2.length - 1]);
            }
        }

        if(!TextUtils.isEmpty(v0.getUserName()) && v0.getUserName().length() > v3) {
            v1.setUserName(v0.getUserName().substring(0, v3));
        }

        v1.setPassWord(e.a(v0.getPassWord()));
        v1.setPort(v0.getPort());
        v1.setPorxyHealth(v0.isPorxyHealth());
        v1.setExpireDateSecs(v0.getExpireDateSecs());
        return v1;
    }
}

