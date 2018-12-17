package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TargetApi(value=16) public final class bh {
    class com.google.ads.interactivemedia.v3.internal.bh$1 {
    }

    final class a {
        public final String a;
        public final boolean b;

        public a(String arg1, boolean arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((a)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(arg5.getClass() != a.class) {
                }
                else {
                    if(!TextUtils.equals(this.a, ((a)arg5).a) || this.b != ((a)arg5).b) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            int v0 = this.a == null ? 0 : this.a.hashCode();
            v0 = (v0 + 31) * 31;
            int v1 = this.b ? 1231 : 1237;
            return v0 + v1;
        }
    }

    public class b extends IOException {
        b(Throwable arg1, com.google.ads.interactivemedia.v3.internal.bh$1 arg2) {
            this(arg1);
        }

        private b(Throwable arg2) {
            super("Failed to query underlying media codecs", arg2);
        }
    }

    interface c {
        int a();

        MediaCodecInfo a(int arg1);

        boolean a(String arg1, MediaCodecInfo$CodecCapabilities arg2);

        boolean b();
    }

    final class d implements c {
        d(com.google.ads.interactivemedia.v3.internal.bh$1 arg1) {
            this();
        }

        private d() {
            super();
        }

        public int a() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo a(int arg1) {
            return MediaCodecList.getCodecInfoAt(arg1);
        }

        public boolean a(String arg1, MediaCodecInfo$CodecCapabilities arg2) {
            return "video/avc".equals(arg1);
        }

        public boolean b() {
            return 0;
        }
    }

    @TargetApi(value=21) final class e implements c {
        private final int a;
        private MediaCodecInfo[] b;

        public e(boolean arg1) {
            super();
            this.a = ((int)arg1);
        }

        public int a() {
            this.c();
            return this.b.length;
        }

        public MediaCodecInfo a(int arg2) {
            this.c();
            return this.b[arg2];
        }

        public boolean a(String arg1, MediaCodecInfo$CodecCapabilities arg2) {
            return arg2.isFeatureSupported("secure-playback");
        }

        public boolean b() {
            return 1;
        }

        private void c() {
            if(this.b == null) {
                this.b = new MediaCodecList(this.a).getCodecInfos();
            }
        }
    }

    private static final ay a;
    private static final Map b;
    private static int c;

    static {
        bh.a = new ay("OMX.google.raw.decoder", null);
        bh.b = new HashMap();
        bh.c = -1;
    }

    public static ay a() {
        return bh.a;
    }

    public static ay a(String arg0, boolean arg1) {
        ay v0_1;
        List v0 = bh.b(arg0, arg1);
        if(v0.isEmpty()) {
            v0_1 = null;
        }
        else {
            Object v0_2 = v0.get(0);
        }

        return v0_1;
    }

    private static List a(a arg16, c arg17) {
        String v14;
        int v13;
        String v10;
        MediaCodecInfo v9;
        int v8;
        String v4;
        ArrayList v3;
        a v1 = arg16;
        c v2 = arg17;
        try {
            v3 = new ArrayList();
            v4 = v1.a;
            int v5 = arg17.a();
            boolean v6 = arg17.b();
            v8 = 0;
            while(true) {
            label_8:
                if(v8 >= v5) {
                    goto label_87;
                }

                v9 = v2.a(v8);
                v10 = v9.getName();
                if(!bh.a(v9, v10, v6)) {
                    goto label_84;
                }

                String[] v11 = v9.getSupportedTypes();
                int v12 = v11.length;
                v13 = 0;
                while(true) {
                label_16:
                    if(v13 >= v12) {
                        goto label_84;
                    }

                    v14 = v11[v13];
                    if(!v14.equalsIgnoreCase(v4)) {
                        goto label_81;
                    }

                    break;
                }
            }
        }
        catch(Exception v0) {
            goto label_92;
        }

        try {
            MediaCodecInfo$CodecCapabilities v0_1 = v9.getCapabilitiesForType(v14);
            boolean v15 = v2.a(v4, v0_1);
            if((v6) && v1.b == v15 || !v6 && !v1.b) {
                ((List)v3).add(new ay(v10, v0_1));
                goto label_81;
            }

            if(v6) {
                goto label_81;
            }

            if(!v15) {
                goto label_81;
            }

            ((List)v3).add(new ay(String.valueOf(v10).concat(".secure"), v0_1));
            return ((List)v3);
        }
        catch(Exception v0) {
            try {
                if(ft.a <= 23 && !((List)v3).isEmpty()) {
                    StringBuilder v7 = new StringBuilder(String.valueOf(v10).length() + 46);
                    v7.append("Skipping codec ");
                    v7.append(v10);
                    v7.append(" (failed to query capabilities)");
                    Log.e("MediaCodecUtil", v7.toString());
                    goto label_81;
                }

                StringBuilder v3_1 = new StringBuilder(String.valueOf(v10).length() + 25 + String.valueOf(v14).length());
                v3_1.append("Failed to query codec ");
                v3_1.append(v10);
                v3_1.append(" (");
                v3_1.append(v14);
                v3_1.append(")");
                Log.e("MediaCodecUtil", v3_1.toString());
                throw v0;
            }
            catch(Exception v0) {
                goto label_92;
            }
        }

    label_81:
        ++v13;
        v1 = arg16;
        goto label_16;
    label_84:
        ++v8;
        v1 = arg16;
        goto label_8;
    label_87:
        return ((List)v3);
    label_92:
        throw new b(((Throwable)v0), null);
    }

    private static boolean a(MediaCodecInfo arg2, String arg3, boolean arg4) {
        if(!arg2.isEncoder() && ((arg4) || !arg3.endsWith(".secure"))) {
            if(ft.a < 21 && (("CIPAACDecoder".equals(arg3)) || ("CIPMP3Decoder".equals(arg3)) || ("CIPVorbisDecoder".equals(arg3)) || ("CIPAMRNBDecoder".equals(arg3)) || ("AACDecoder".equals(arg3)) || ("MP3Decoder".equals(arg3)))) {
                return 0;
            }

            int v4 = 18;
            if(ft.a < v4 && ("OMX.SEC.MP3.Decoder".equals(arg3))) {
                return 0;
            }

            if(ft.a < v4 && ("OMX.MTK.AUDIO.DECODER.AAC".equals(arg3)) && ("a70".equals(ft.b))) {
                return 0;
            }

            v4 = 16;
            if(ft.a == v4 && ft.b != null && ("OMX.qcom.audio.decoder.mp3".equals(arg3)) && (("dlxu".equals(ft.b)) || ("protou".equals(ft.b)) || ("ville".equals(ft.b)) || ("villeplus".equals(ft.b)) || ("villec2".equals(ft.b)) || (ft.b.startsWith("gee")) || ("C6602".equals(ft.b)) || ("C6603".equals(ft.b)) || ("C6606".equals(ft.b)) || ("C6616".equals(ft.b)) || ("L36h".equals(ft.b)) || ("SO-02E".equals(ft.b)))) {
                return 0;
            }

            if(ft.a == v4 && ("OMX.qcom.audio.decoder.aac".equals(arg3)) && (("C1504".equals(ft.b)) || ("C1505".equals(ft.b)) || ("C1604".equals(ft.b)) || ("C1605".equals(ft.b)))) {
                return 0;
            }

            v4 = 19;
            if(ft.a <= v4 && ft.b != null && ((ft.b.startsWith("d2")) || (ft.b.startsWith("serrano")) || (ft.b.startsWith("jflte")) || (ft.b.startsWith("santos"))) && ("samsung".equals(ft.c)) && (arg3.equals("OMX.SEC.vp8.dec"))) {
                return 0;
            }

            if(ft.a <= v4 && ft.b != null && (ft.b.startsWith("jflte")) && ("OMX.qcom.video.decoder.vp8".equals(arg3))) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public static List b(String arg6, boolean arg7) {
        List v6_1;
        e v2_1;
        Object v2;
        a v1;
        Class v0 = bh.class;
        __monitor_enter(v0);
        try {
            v1 = new a(arg6, arg7);
            v2 = bh.b.get(v1);
            if(v2 == null) {
                goto label_9;
            }
        }
        catch(Throwable v6) {
            goto label_58;
        }

        __monitor_exit(v0);
        return ((List)v2);
        try {
        label_9:
            com.google.ads.interactivemedia.v3.internal.bh$1 v3 = null;
            int v4 = 21;
            if(ft.a >= v4) {
                v2_1 = new e(arg7);
            }
            else {
                d v2_2 = new d(v3);
            }

            List v2_3 = bh.a(v1, ((c)v2_1));
            if((arg7) && (v2_3.isEmpty()) && v4 <= ft.a && ft.a <= 23) {
                v2_3 = bh.a(v1, new d(v3));
                if(!v2_3.isEmpty()) {
                    String v3_1 = v2_3.get(0).a;
                    StringBuilder v5 = new StringBuilder(String.valueOf(arg6).length() + 63 + String.valueOf(v3_1).length());
                    v5.append("MediaCodecList API didn\'t list secure decoder for: ");
                    v5.append(arg6);
                    v5.append(". Assuming: ");
                    v5.append(v3_1);
                    Log.w("MediaCodecUtil", v5.toString());
                }
            }

            v6_1 = Collections.unmodifiableList(v2_3);
            bh.b.put(v1, v6_1);
        }
        catch(Throwable v6) {
        label_58:
            __monitor_exit(v0);
            throw v6;
        }

        __monitor_exit(v0);
        return v6_1;
    }
}

