package org.telegram.messenger.voip;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.os.Build$VERSION;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Iterator;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

public class JNIUtilities {
    public JNIUtilities() {
        super();
    }

    @TargetApi(value=23) public static String getCurrentNetworkInterfaceName() {
        Object v0 = ApplicationLoader.applicationContext.getSystemService("connectivity");
        Network v1 = ((ConnectivityManager)v0).getActiveNetwork();
        String v2 = null;
        if(v1 == null) {
            return v2;
        }

        LinkProperties v0_1 = ((ConnectivityManager)v0).getLinkProperties(v1);
        if(v0_1 == null) {
            return v2;
        }

        return v0_1.getInterfaceName();
    }

    public static String[] getLocalNetworkAddressesAndInterfaceName() {
        String v9_1;
        Object v1_3;
        Enumeration v0_3;
        Object v0 = ApplicationLoader.applicationContext.getSystemService("connectivity");
        int v2 = 2;
        int v4 = 3;
        int v5 = 240;
        String[] v7 = null;
        if(Build$VERSION.SDK_INT >= 23) {
            Network v1 = ((ConnectivityManager)v0).getActiveNetwork();
            if(v1 == null) {
                return v7;
            }

            LinkProperties v0_1 = ((ConnectivityManager)v0).getLinkProperties(v1);
            if(v0_1 == null) {
                return v7;
            }

            Iterator v1_1 = v0_1.getLinkAddresses().iterator();
            String v8;
            for(v8 = ((String)v7); v1_1.hasNext(); v8 = v9.getHostAddress()) {
                InetAddress v9 = v1_1.next().getAddress();
                if((v9 instanceof Inet4Address)) {
                    if(v9.isLinkLocalAddress()) {
                        continue;
                    }

                    String v7_1 = v9.getHostAddress();
                    continue;
                }

                if(!(v9 instanceof Inet6Address)) {
                    continue;
                }

                if(v9.isLinkLocalAddress()) {
                    continue;
                }

                if((v9.getAddress()[0] & v5) == v5) {
                    continue;
                }
            }

            String[] v1_2 = new String[v4];
            v1_2[0] = v0_1.getInterfaceName();
            v1_2[1] = ((String)v7);
            v1_2[v2] = v8;
            return v1_2;
        }

        try {
            v0_3 = NetworkInterface.getNetworkInterfaces();
            if(v0_3 == null) {
                return v7;
            }

            while(true) {
                if(!v0_3.hasMoreElements()) {
                    return v7;
                }

                v1_3 = v0_3.nextElement();
                if(((NetworkInterface)v1_3).isLoopback()) {
                    continue;
                }

                if(((NetworkInterface)v1_3).isUp()) {
                    break;
                }
            }

            v0_3 = ((NetworkInterface)v1_3).getInetAddresses();
            v8 = ((String)v7);
            v9_1 = v8;
            goto label_61;
        }
        catch(Exception v0_2) {
            goto label_88;
        }

        return v7;
        try {
        label_61:
            while(v0_3.hasMoreElements()) {
                Object v10 = v0_3.nextElement();
                if((v10 instanceof Inet4Address)) {
                    if(((InetAddress)v10).isLinkLocalAddress()) {
                        continue;
                    }

                    v8 = ((InetAddress)v10).getHostAddress();
                    continue;
                }

                if(!(v10 instanceof Inet6Address)) {
                    continue;
                }

                if(((InetAddress)v10).isLinkLocalAddress()) {
                    continue;
                }

                if((((InetAddress)v10).getAddress()[0] & v5) == v5) {
                    continue;
                }

                v9_1 = ((InetAddress)v10).getHostAddress();
            }

            String[] v0_4 = new String[v4];
            v0_4[0] = ((NetworkInterface)v1_3).getName();
            v0_4[1] = v8;
            v0_4[v2] = v9_1;
            return v0_4;
        }
        catch(Exception v0_2) {
        label_88:
            FileLog.e(((Throwable)v0_2));
            return v7;
        }
    }
}

