package org.telegram.customization.Model;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.a.a.c;
import java.util.Comparator;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Utilities;
import utils.a.b;

public class ProxyServerModel implements Comparable {
    final class org.telegram.customization.Model.ProxyServerModel$1 implements Comparator {
        org.telegram.customization.Model.ProxyServerModel$1() {
            super();
        }

        public int compare(Object arg3, Object arg4) {
            if(((arg3 instanceof ProxyServerModel)) && ((arg4 instanceof ProxyServerModel))) {
                return ((int)(((ProxyServerModel)arg4).getExpireDateSecs() - ((ProxyServerModel)arg3).getExpireDateSecs()));
            }

            return 0;
        }
    }

    public static Comparator comparator;
    @c(a="ttl") long expireDateSecs;
    String ip;
    long localExpireTime;
    @c(a="pwd") String passWord;
    @c(a="prt") int port;
    @c(a="prh") boolean porxyHealth;
    int usedCount;
    @c(a="usr") String userName;

    static {
        ProxyServerModel.comparator = new org.telegram.customization.Model.ProxyServerModel$1();
    }

    public ProxyServerModel(String arg2, String arg3, String arg4, String arg5) {
        super();
        this.porxyHealth = true;
        this.ip = arg2;
        this.port = Utilities.parseInt(arg3).intValue();
        this.userName = arg4;
        this.passWord = arg5;
        this.usedCount = 0;
        this.expireDateSecs = 0;
        this.localExpireTime = System.currentTimeMillis();
    }

    public ProxyServerModel() {
        super();
        this.porxyHealth = true;
        this.ip = "";
        this.port = 0;
        this.userName = "";
        this.passWord = "";
        this.usedCount = 0;
        this.expireDateSecs = 0;
        this.localExpireTime = System.currentTimeMillis();
    }

    public int compareTo(Object arg5) {
        if((arg5 instanceof ProxyServerModel)) {
            return ((int)(((ProxyServerModel)arg5).getExpireDateSecs() - this.getExpireDateSecs()));
        }

        return 0;
    }

    public boolean equalsWith(ProxyServerModel arg4) {
        boolean v0 = false;
        try {
            if((this.ip.equals(arg4.ip)) && (this.passWord.equals(arg4.passWord)) && (this.userName.equals(arg4.userName)) && this.port == arg4.port) {
                return true;
            }
        }
        catch(Exception ) {
        }

        return v0;
    }

    public void fillLocalExpireTime() {
        Log.d("LEE", "expireDateSecs: " + this.expireDateSecs);
        this.localExpireTime = System.currentTimeMillis() + this.expireDateSecs * 1000;
    }

    public long getExpireDateSecs() {
        return this.expireDateSecs;
    }

    public static ProxyServerModel getFromShared() {
        ProxyServerModel v0 = new ProxyServerModel();
        v0.setIp(b.aj(ApplicationLoader.applicationContext));
        v0.setPort(Utilities.parseInt(b.ak(ApplicationLoader.applicationContext)).intValue());
        v0.setUserName(b.al(ApplicationLoader.applicationContext));
        v0.setPassWord(b.am(ApplicationLoader.applicationContext));
        v0.setPorxyHealth(b.ao(ApplicationLoader.applicationContext));
        v0.setExpireDateSecs(b.a());
        return v0;
    }

    public static ProxyServerModel getFromTelegram() {
        ProxyServerModel v0 = new ProxyServerModel();
        SharedPreferences v1 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        try {
            v0.setIp(v1.getString("proxy_ip", ""));
            goto label_10;
        }
        catch(Exception ) {
            try {
            label_10:
                v0.setPort(v1.getInt("proxy_port", 0));
                goto label_13;
            }
            catch(Exception ) {
                try {
                label_13:
                    v0.setPassWord(v1.getString("proxy_pass", ""));
                    goto label_17;
                }
                catch(Exception ) {
                    try {
                    label_17:
                        v0.setUserName(v1.getString("proxy_user", ""));
                        goto label_21;
                    }
                    catch(Exception ) {
                    label_21:
                        try {
                            v0.setPorxyHealth(true);
                            return v0;
                        }
                        catch(Exception ) {
                            return v0;
                        }
                    }
                }
            }
        }
    }

    public static ProxyServerModel getHardcodedProxy() {
        return new ProxyServerModel();
    }

    public String getIp() {
        return this.ip;
    }

    public long getLocalExpireTime() {
        return this.localExpireTime;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public int getPort() {
        return this.port;
    }

    public String getUserName() {
        return this.userName;
    }

    public boolean isHalfUsed() {
        boolean v0 = this.usedCount == 4 ? true : false;
        return v0;
    }

    public boolean isPorxyHealth() {
        return this.porxyHealth;
    }

    public boolean isUsed() {
        boolean v0 = this.usedCount >= 4 ? true : false;
        return v0;
    }

    public void setExpireDateSecs(long arg1) {
        this.expireDateSecs = arg1;
    }

    public void setIp(String arg1) {
        this.ip = arg1;
    }

    public void setPassWord(String arg1) {
        this.passWord = arg1;
    }

    public void setPort(int arg1) {
        this.port = arg1;
    }

    public void setPorxyHealth(boolean arg1) {
        this.porxyHealth = arg1;
    }

    public void setUsed(boolean arg1) {
        int v1 = arg1 ? this.usedCount + 1 : 0;
        this.usedCount = v1;
    }

    public void setUserName(String arg1) {
        this.userName = arg1;
    }
}

