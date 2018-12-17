package org.telegram.customization.dynamicadapter.data;

import android.text.TextUtils;
import com.google.a.d.a;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.l;
import com.google.a.v;
import com.google.a.w;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;

public class DocAvailableInfo {
    public class DocAvailableInfoAdapterFactory implements w {
        class DocAvailableInfoTypeAdapter extends v {
            private final f gson;
            private final DocAvailableInfoAdapterFactory gsonAdapterFactory;
            private final v jsonElementAdapter;

            DocAvailableInfoTypeAdapter(DocAvailableInfoAdapterFactory arg1, f arg2, v arg3) {
                super();
                this.jsonElementAdapter = arg3;
                this.gson = arg2;
                this.gsonAdapterFactory = arg1;
            }

            public Object read(a arg1) {
                return this.read(arg1);
            }

            public DocAvailableInfo read(a arg1) {
                return null;
            }

            public void write(c arg1, Object arg2) {
                this.write(arg1, ((DocAvailableInfo)arg2));
            }

            public void write(c arg7, DocAvailableInfo arg8) {
                arg7.d();
                long v2 = 0;
                if(arg8.docId != v2) {
                    arg7.a("d").a(arg8.docId);
                }

                if(arg8.localId != 0) {
                    arg7.a("l").a(((long)arg8.localId));
                }

                if(arg8.volumeId != v2) {
                    arg7.a("v").a(arg8.volumeId);
                }

                if(arg8.messageId != v2) {
                    arg7.a("m").a(arg8.messageId);
                }

                if(arg8.size != 0) {
                    arg7.a("s").a(((long)arg8.size));
                }

                if(arg8.channelId != v2) {
                    arg7.a("cid").a(arg8.channelId);
                }

                if(!TextUtils.isEmpty(arg8.channelUsername)) {
                    arg7.a("cun").b(arg8.channelUsername);
                }

                if(arg8.viewCount != 0) {
                    arg7.a("pvc").a(((long)arg8.viewCount));
                }

                arg7.e();
            }
        }

        public DocAvailableInfoAdapterFactory() {
            super();
        }

        public v create(f arg2, com.google.a.c.a arg3) {
            if(!DocAvailableInfo.class.isAssignableFrom(arg3.a())) {
                return null;
            }

            return new DocAvailableInfoTypeAdapter(this, arg2, arg2.a(l.class)).nullSafe();
        }
    }

    public class NewDocAvailableInfoAdapterFactory implements w {
        class NewDocAvailableInfoTypeAdapter extends v {
            private final f gson;
            private final NewDocAvailableInfoAdapterFactory gsonAdapterFactory;
            private final v jsonElementAdapter;

            NewDocAvailableInfoTypeAdapter(NewDocAvailableInfoAdapterFactory arg1, f arg2, v arg3) {
                super();
                this.jsonElementAdapter = arg3;
                this.gson = arg2;
                this.gsonAdapterFactory = arg1;
            }

            public Object read(a arg1) {
                return this.read(arg1);
            }

            public DocAvailableInfo read(a arg1) {
                return null;
            }

            public void write(c arg1, Object arg2) {
                this.write(arg1, ((DocAvailableInfo)arg2));
            }

            public void write(c arg7, DocAvailableInfo arg8) {
                c v0;
                arg7.d();
                long v2 = 0;
                if(arg8.docId != v2) {
                    arg7.a("fk").a(arg8.docId);
                }

                if(arg8.localId != 0 && arg8.volumeId != v2) {
                    v0 = arg7.a("fk");
                    v0.b(String.valueOf(arg8.localId) + "." + String.valueOf(arg8.volumeId));
                }

                if(arg8.messageId != v2 && arg8.channelId != v2) {
                    v0 = arg7.a("mk");
                    v0.b(String.valueOf(arg8.messageId) + "." + String.valueOf(arg8.channelId));
                }

                if(arg8.size != 0) {
                    arg7.a("s").a(((long)arg8.size));
                }

                arg7.e();
            }
        }

        public NewDocAvailableInfoAdapterFactory() {
            super();
        }

        public v create(f arg2, com.google.a.c.a arg3) {
            if(!DocAvailableInfo.class.isAssignableFrom(arg3.a())) {
                return null;
            }

            return new NewDocAvailableInfoTypeAdapter(this, arg2, arg2.a(l.class)).nullSafe();
        }
    }

    public static final int SOURCE_TYPE_HOT = 1;
    public static final int SOURCE_TYPE_TELEGRAM;
    @com.google.a.a.c(a="cid") public long channelId;
    @com.google.a.a.c(a="cun") public String channelUsername;
    @com.google.a.a.c(a="d") public long docId;
    @com.google.a.a.c(a="e") public boolean exists;
    @com.google.a.a.c(a="fs") public String freeState;
    @com.google.a.a.c(a="l") public int localId;
    @com.google.a.a.c(a="u") public String localUrl;
    @com.google.a.a.c(a="m") public long messageId;
    @com.google.a.a.c(a="ph") public String phone;
    @com.google.a.a.c(a="s") public int size;
    public int sourceType;
    @com.google.a.a.c(a="t") String stringTags;
    public ArrayList tags;
    @com.google.a.a.c(a="lbl") public String trafficStatusLabel;
    @com.google.a.a.c(a="uid") public long userId;
    @com.google.a.a.c(a="vc") public int versionCode;
    @com.google.a.a.c(a="pvc") int viewCount;
    @com.google.a.a.c(a="v") public long volumeId;

    public DocAvailableInfo() {
        this(0, 0, 0, 0, true);
    }

    public DocAvailableInfo(long arg2, int arg4, long arg5, long arg7, boolean arg9) {
        super();
        this.sourceType = 0;
        this.docId = arg2;
        this.localId = arg4;
        this.volumeId = arg5;
        this.messageId = arg7;
        this.exists = arg9;
    }

    public DocAvailableInfo(long arg2, int arg4, long arg5, int arg7, long arg8, String arg10, long arg11, int arg13) {
        super();
        this.sourceType = 0;
        this.docId = arg2;
        this.localId = arg4;
        this.volumeId = arg5;
        this.messageId = arg11;
        this.channelId = arg8;
        this.size = arg7;
        this.channelUsername = arg10;
        this.viewCount = arg13;
    }

    public boolean equals(Object arg7) {
        boolean v1 = false;
        if(((arg7 instanceof DocAvailableInfo)) && this.volumeId == ((DocAvailableInfo)arg7).volumeId && this.localId == ((DocAvailableInfo)arg7).localId && this.docId == ((DocAvailableInfo)arg7).docId) {
            v1 = true;
        }

        return v1;
    }

    public long getChannelId() {
        return this.channelId;
    }

    public String getChannelUsername() {
        return this.channelUsername;
    }

    public String getLocalUrl() {
        String v0;
        if(TextUtils.isEmpty(this.localUrl)) {
            return this.localUrl;
        }

        try {
            v0 = URLEncoder.encode(org.telegram.customization.g.f.b(ApplicationLoader.applicationContext), "UTF-8");
        }
        catch(UnsupportedEncodingException v1) {
            v1.printStackTrace();
        }

        String v1_1 = "?uId=%s&type=%s&value=%s&source=%s";
        Object[] v2 = new Object[4];
        v2[0] = Integer.valueOf(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId());
        v2[1] = Integer.valueOf(ConnectionsManager.getCurrentNetworkType());
        v2[2] = v0;
        int v0_1 = 3;
        String v3 = this.getSourceType() == 0 ? "default" : "hot";
        v2[v0_1] = v3;
        v0 = String.format(v1_1, v2);
        return this.localUrl + v0;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public String getStringTags() {
        return this.stringTags;
    }

    public ArrayList getTags() {
        ArrayList v1;
        if(TextUtils.isEmpty(this.stringTags)) {
            goto label_23;
        }

        try {
            Object v0_1 = new f().a(this.stringTags, String[].class);
            v1 = new ArrayList();
            int v2 = v0_1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                v1.add(v0_1[v3]);
            }
        }
        catch(Exception v0) {
            goto label_19;
        }

        return v1;
    label_19:
        v0.printStackTrace();
        return new ArrayList();
    label_23:
        return new ArrayList();
    }

    public int hashCode() {
        StringBuilder v0 = new StringBuilder();
        v0.append("");
        v0.append(this.docId);
        v0.append(",");
        v0.append(this.volumeId);
        v0.append(",");
        v0.append(this.localId);
        return v0.toString().hashCode();
    }

    public void setChannelId(long arg1) {
        this.channelId = arg1;
    }

    public void setChannelUsername(String arg1) {
        this.channelUsername = arg1;
    }

    public void setLocalUrl(String arg1) {
        this.localUrl = arg1;
    }

    public void setSourceType(int arg1) {
        this.sourceType = arg1;
    }

    public void setStringTags(String arg1) {
        this.stringTags = arg1;
    }

    public void setTag(ArrayList arg1) {
        this.tags = arg1;
    }

    public void setValues(long arg1, int arg3, long arg4, long arg6) {
        this.docId = arg1;
        this.localId = arg3;
        this.volumeId = arg4;
        this.messageId = arg6;
    }
}

