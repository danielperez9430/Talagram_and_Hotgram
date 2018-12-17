package okhttp3.internal.g;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;
import org.conscrypt.OpenSSLProvider;

public class b extends f {
    private b() {
        super();
    }

    @Nullable public String a(SSLSocket arg2) {
        if(Conscrypt.isConscrypt(arg2)) {
            return Conscrypt.getApplicationProtocol(arg2);
        }

        return super.a(arg2);
    }

    public void a(SSLSocket arg2, String arg3, List arg4) {
        if(Conscrypt.isConscrypt(arg2)) {
            if(arg3 != null) {
                Conscrypt.setUseSessionTickets(arg2, true);
                Conscrypt.setHostname(arg2, arg3);
            }

            Conscrypt.setApplicationProtocols(arg2, f.a(arg4).toArray(new String[0]));
        }
        else {
            super.a(arg2, arg3, arg4);
        }
    }

    public static f b() {
        f v0 = null;
        try {
            Class.forName("org.conscrypt.ConscryptEngineSocket");
            if(!Conscrypt.isAvailable()) {
                return v0;
            }

            Conscrypt.setUseEngineSocketByDefault(true);
            return new b();
        }
        catch(ClassNotFoundException ) {
            return v0;
        }
    }

    private Provider e() {
        return new OpenSSLProvider();
    }

    public SSLContext n_() {
        try {
            return SSLContext.getInstance("TLS", this.e());
        }
        catch(NoSuchAlgorithmException v0) {
            throw new IllegalStateException("No TLS provider", ((Throwable)v0));
        }
    }
}

