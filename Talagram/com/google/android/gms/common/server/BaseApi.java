package com.google.android.gms.common.server;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseApi {
    public class BaseApiaryOptions {
        public final class Collector {
            private boolean zzvx;
            private boolean zzvy;
            private int zzvz;
            private StringBuilder zzwa;

            public Collector(BaseApiaryOptions arg1) {
                this.zzwb = arg1;
                super();
                this.zzwa = new StringBuilder();
            }

            public final void addPiece(String arg1) {
                this.append(arg1);
                this.zzvy = true;
            }

            private final void append(String arg3) {
                String v1;
                StringBuilder v0;
                if(this.zzvx) {
                    this.zzvx = false;
                    v0 = this.zzwa;
                    v1 = ",";
                    goto label_6;
                }
                else if(this.zzvy) {
                    this.zzvy = false;
                    v0 = this.zzwa;
                    v1 = "/";
                label_6:
                    v0.append(v1);
                }

                this.zzwa.append(arg3);
            }

            public final void beginSubCollection(String arg2) {
                this.append(arg2);
                this.zzwa.append("(");
                ++this.zzvz;
            }

            public final void endSubCollection() {
                this.zzwa.append(")");
                --this.zzvz;
                if(this.zzvz == 0) {
                    this.zzwb.addField(this.zzwa.toString());
                    this.zzwa.setLength(0);
                    this.zzvx = false;
                    this.zzvy = false;
                    return;
                }

                this.zzvx = true;
            }

            public final void finishPiece(String arg2) {
                this.append(arg2);
                if(this.zzvz == 0) {
                    this.zzwb.addField(this.zzwa.toString());
                    this.zzwa.setLength(0);
                    return;
                }

                this.zzvx = true;
            }
        }

        private final ArrayList zzvt;
        private final HashMap zzvu;
        private String zzvv;
        private final Collector zzvw;

        public BaseApiaryOptions() {
            super();
            this.zzvt = new ArrayList();
            this.zzvu = new HashMap();
            this.zzvw = new Collector(this);
        }

        public final BaseApiaryOptions addField(String arg2) {
            this.zzvt.add(arg2);
            return this;
        }

        @Deprecated public final String appendParametersToUrl(String arg4) {
            arg4 = BaseApi.append(arg4, "prettyPrint", BaseApiaryOptions.zzcy());
            if(this.zzvv != null) {
                arg4 = BaseApi.append(arg4, "trace", this.getTrace());
            }

            if(!this.zzvt.isEmpty()) {
                arg4 = BaseApi.append(arg4, "fields", TextUtils.join(",", this.getFields().toArray()));
            }

            return arg4;
        }

        public void appendParametersToUrl(StringBuilder arg4) {
            BaseApi.append(arg4, "prettyPrint", BaseApiaryOptions.zzcy());
            if(this.zzvv != null) {
                BaseApi.append(arg4, "trace", this.getTrace());
            }

            if(!this.zzvt.isEmpty()) {
                BaseApi.append(arg4, "fields", TextUtils.join(",", this.getFields().toArray()));
            }
        }

        public final BaseApiaryOptions buildFrom(BaseApiaryOptions arg2) {
            if(arg2.zzvv != null) {
                this.zzvv = arg2.zzvv;
            }

            if(!arg2.zzvt.isEmpty()) {
                this.zzvt.clear();
                this.zzvt.addAll(arg2.zzvt);
            }

            return this;
        }

        protected final Collector getCollector() {
            return this.zzvw;
        }

        public final List getFields() {
            return this.zzvt;
        }

        public final Map getHeaders() {
            return this.zzvu;
        }

        public final String getTrace() {
            return this.zzvv;
        }

        public final BaseApiaryOptions setEtag(String arg2) {
            return this.setHeader("ETag", arg2);
        }

        public final BaseApiaryOptions setHeader(String arg2, String arg3) {
            this.zzvu.put(arg2, arg3);
            return this;
        }

        public final BaseApiaryOptions setTraceByLdap(String arg3) {
            String v0 = String.valueOf("email:");
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            this.zzvv = arg3;
            return this;
        }

        public final BaseApiaryOptions setTraceByToken(String arg3) {
            String v0 = String.valueOf("token:");
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v0.concat(arg3) : new String(v0);
            this.zzvv = arg3;
            return this;
        }

        private static String zzcy() {
            return String.valueOf(DeviceProperties.isUserBuild() ^ 1);
        }
    }

    public class FieldCollection {
        private final Collector zzvw;
        private final Object zzwc;

        protected FieldCollection(Object arg1, Collector arg2) {
            FieldCollection v1;
            super();
            if(arg1 == null) {
                v1 = this;
            }

            this.zzwc = v1;
            this.zzvw = arg2;
        }

        protected Collector getCollector() {
            return this.zzvw;
        }

        protected Object getParent() {
            return this.zzwc;
        }
    }

    public BaseApi() {
        super();
    }

    @Deprecated public static String append(String arg2, String arg3, String arg4) {
        StringBuilder v0 = new StringBuilder();
        v0.append(arg2);
        char v2 = arg2.indexOf("?") != -1 ? '&' : '?';
        v0.append(v2);
        v0.append(arg3);
        v0.append('=');
        v0.append(arg4);
        return v0.toString();
    }

    public static void append(StringBuilder arg2, String arg3, String arg4) {
        char v0 = arg2.indexOf("?") != -1 ? '&' : '?';
        arg2.append(v0);
        arg2.append(arg3);
        arg2.append('=');
        arg2.append(arg4);
    }

    public static String enc(String arg1) {
        Preconditions.checkNotNull(arg1, "Encoding a null parameter!");
        return Uri.encode(arg1);
    }

    protected static List enc(List arg4) {
        int v0 = arg4.size();
        ArrayList v1 = new ArrayList(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            v1.add(BaseApi.enc(arg4.get(v2)));
        }

        return ((List)v1);
    }
}

