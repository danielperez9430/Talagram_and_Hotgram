package com.persianswitch.sdk.base.security;

import android.os.Build;
import android.os.Process;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Provider;
import java.security.SecureRandomSpi;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.SecretKey;

public class AesCbcWithIntegrity {
    public class CipherTextIvMac {
        private final byte[] a;
        private final byte[] b;
        private final byte[] c;

        public boolean equals(Object arg4) {
            if(this == (((CipherTextIvMac)arg4))) {
                return 1;
            }

            if(arg4 == null) {
                return 0;
            }

            if(this.getClass() != arg4.getClass()) {
                return 0;
            }

            if(!Arrays.equals(this.a, ((CipherTextIvMac)arg4).a)) {
                return 0;
            }

            if(!Arrays.equals(this.b, ((CipherTextIvMac)arg4).b)) {
                return 0;
            }

            return Arrays.equals(this.c, ((CipherTextIvMac)arg4).c);
        }

        public int hashCode() {
            return ((Arrays.hashCode(this.a) + 31) * 31 + Arrays.hashCode(this.b)) * 31 + Arrays.hashCode(this.c);
        }

        public String toString() {
            String v0 = Base64.encodeToString(this.b, 2);
            String v2 = Base64.encodeToString(this.a, 2);
            String v1 = Base64.encodeToString(this.c, 2);
            StringBuilder v3 = new StringBuilder();
            v3.append(v0);
            v3.append(":");
            v3.append(v1);
            v3.append(":");
            v3.append(v2);
            return String.format(v3.toString());
        }
    }

    public final class PrngFixes {
        public class LinuxPRNGSecureRandom extends SecureRandomSpi {
            private static final File a;
            private static final Object b;
            private static DataInputStream c;
            private static OutputStream d;
            private boolean e;

            static {
                LinuxPRNGSecureRandom.a = new File("/dev/urandom");
                LinuxPRNGSecureRandom.b = new Object();
            }

            public LinuxPRNGSecureRandom() {
                super();
            }

            private DataInputStream a() {
                Object v0 = LinuxPRNGSecureRandom.b;
                __monitor_enter(v0);
                try {
                    if(LinuxPRNGSecureRandom.c == null) {
                        try {
                            LinuxPRNGSecureRandom.c = new DataInputStream(new FileInputStream(LinuxPRNGSecureRandom.a));
                            goto label_24;
                        }
                        catch(IOException v1_1) {
                            try {
                                StringBuilder v3 = new StringBuilder();
                                v3.append("Failed to open ");
                                v3.append(LinuxPRNGSecureRandom.a);
                                v3.append(" for reading");
                                throw new SecurityException(v3.toString(), ((Throwable)v1_1));
                            label_24:
                                __monitor_exit(v0);
                                return LinuxPRNGSecureRandom.c;
                            label_28:
                                __monitor_exit(v0);
                                goto label_29;
                            }
                            catch(Throwable v1) {
                                goto label_28;
                            }
                        }
                    }

                    goto label_24;
                }
                catch(Throwable v1) {
                    goto label_28;
                }

            label_29:
                throw v1;
            }

            private OutputStream b() {
                Object v0 = LinuxPRNGSecureRandom.b;
                __monitor_enter(v0);
                try {
                    if(LinuxPRNGSecureRandom.d == null) {
                        LinuxPRNGSecureRandom.d = new FileOutputStream(LinuxPRNGSecureRandom.a);
                    }

                    __monitor_exit(v0);
                    return LinuxPRNGSecureRandom.d;
                label_12:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_12;
                }

                throw v1;
            }

            protected byte[] engineGenerateSeed(int arg1) {
                byte[] v1 = new byte[arg1];
                this.engineNextBytes(v1);
                return v1;
            }

            protected void engineNextBytes(byte[] arg4) {
                DataInputStream v1;
                Object v0;
                if(!this.e) {
                    this.engineSetSeed(PrngFixes.a());
                }

                try {
                    v0 = LinuxPRNGSecureRandom.b;
                    __monitor_enter(v0);
                }
                catch(IOException v4) {
                    goto label_21;
                }

                try {
                    v1 = this.a();
                    __monitor_exit(v0);
                    goto label_8;
                }
                catch(Throwable v4_1) {
                    try {
                    label_16:
                        __monitor_exit(v0);
                    }
                    catch(Throwable v4_1) {
                        goto label_16;
                    }

                    try {
                        throw v4_1;
                    label_8:
                        __monitor_enter(v1);
                    }
                    catch(IOException v4) {
                        goto label_21;
                    }
                }

                try {
                    v1.readFully(arg4);
                    __monitor_exit(v1);
                    return;
                label_13:
                    __monitor_exit(v1);
                }
                catch(Throwable v4_1) {
                    goto label_13;
                }

                try {
                    throw v4_1;
                }
                catch(IOException v4) {
                label_21:
                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Failed to read from ");
                    v1_1.append(LinuxPRNGSecureRandom.a);
                    throw new SecurityException(v1_1.toString(), ((Throwable)v4));
                }
            }

            protected void engineSetSeed(byte[] arg4) {
                OutputStream v2;
                Object v1;
                try {
                    v1 = LinuxPRNGSecureRandom.b;
                    __monitor_enter(v1);
                }
                catch(Throwable v4) {
                    goto label_26;
                }
                catch(IOException ) {
                    goto label_14;
                }

                try {
                    v2 = this.b();
                    __monitor_exit(v1);
                    goto label_5;
                }
                catch(Throwable v4) {
                    try {
                    label_10:
                        __monitor_exit(v1);
                    }
                    catch(Throwable v4) {
                        goto label_10;
                    }

                    try {
                        throw v4;
                    label_5:
                        v2.write(arg4);
                        v2.flush();
                    }
                    catch(Throwable v4) {
                    }
                    catch(IOException ) {
                        try {
                        label_14:
                            String v4_1 = PrngFixes.class.getSimpleName();
                            Log.w(v4_1, "Failed to mix seed into " + LinuxPRNGSecureRandom.a);
                        }
                        catch(Throwable v4) {
                        label_26:
                            this.e = true;
                            throw v4;
                        }
                    }
                }

                this.e = true;
            }
        }

        class LinuxPRNGSecureRandomProvider extends Provider {
            public LinuxPRNGSecureRandomProvider() {
                super("LinuxPRNG", 1, "A Linux-specific random number provider that uses /dev/urandom");
                this.put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom.class.getName());
                this.put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
            }
        }

        private static final byte[] a;

        static {
            PrngFixes.a = PrngFixes.d();
        }

        private PrngFixes() {
            super();
        }

        static byte[] a() {
            return PrngFixes.b();
        }

        private static byte[] b() {
            try {
                ByteArrayOutputStream v0_1 = new ByteArrayOutputStream();
                DataOutputStream v1 = new DataOutputStream(((OutputStream)v0_1));
                v1.writeLong(System.currentTimeMillis());
                v1.writeLong(System.nanoTime());
                v1.writeInt(Process.myPid());
                v1.writeInt(Process.myUid());
                v1.write(PrngFixes.a);
                v1.close();
                return v0_1.toByteArray();
            }
            catch(IOException v0) {
                throw new SecurityException("Failed to generate seed", ((Throwable)v0));
            }
        }

        private static String c() {
            Object v0 = null;
            try {
                return Build.class.getField("SERIAL").get(v0);
            }
            catch(Exception ) {
                return ((String)v0);
            }
        }

        private static byte[] d() {
            StringBuilder v0 = new StringBuilder();
            String v1 = Build.FINGERPRINT;
            if(v1 != null) {
                v0.append(v1);
            }

            v1 = PrngFixes.c();
            if(v1 != null) {
                v0.append(v1);
            }

            try {
                return v0.toString().getBytes("UTF-8");
            }
            catch(UnsupportedEncodingException ) {
                throw new RuntimeException("UTF-8 encoding not supported");
            }
        }
    }

    public class SecretKeys {
        private SecretKey a;
        private SecretKey b;

        public SecretKey a() {
            return this.a;
        }

        public SecretKey b() {
            return this.b;
        }

        public boolean equals(Object arg4) {
            if(this == (((SecretKeys)arg4))) {
                return 1;
            }

            if(arg4 == null) {
                return 0;
            }

            if(this.getClass() != arg4.getClass()) {
                return 0;
            }

            if(!this.b.equals(((SecretKeys)arg4).b)) {
                return 0;
            }

            return this.a.equals(((SecretKeys)arg4).a);
        }

        public int hashCode() {
            return (this.a.hashCode() + 31) * 31 + this.b.hashCode();
        }

        public String toString() {
            return Base64.encodeToString(this.a().getEncoded(), 2) + ":" + Base64.encodeToString(this.b().getEncoded(), 2);
        }
    }

    static final AtomicBoolean a;

    static {
        AesCbcWithIntegrity.a = new AtomicBoolean(false);
    }

    public AesCbcWithIntegrity() {
        super();
    }
}

