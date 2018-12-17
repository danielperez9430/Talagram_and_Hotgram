package org.linphone.tools;

import android.os.Build$VERSION;
import org.linphone.core.LinphoneCore;
import org.linphone.mediastream.Factory;
import org.linphone.mediastream.Log;

public class H264Helper {
    private static String FILTER_NAME_MEDIA_CODEC_DEC = "MSMediaCodecH264Dec";
    private static String FILTER_NAME_MEDIA_CODEC_ENC = "MSMediaCodecH264Enc";
    private static String FILTER_NAME_OPENH264_DEC = "MSOpenH264Dec";
    private static String FILTER_NAME_OPENH264_ENC = "MSOpenH264Enc";
    public static String MODE_AUTO = "Auto";
    public static String MODE_MEDIA_CODEC = "MediaCodec";
    public static String MODE_OPENH264 = "OpenH264";

    static {
    }

    public H264Helper() {
        super();
    }

    public static void setH264Mode(String arg6, LinphoneCore arg7) {
        Object[] v7_1;
        Object[] v0;
        int v1 = 2;
        if(arg6.equals(H264Helper.MODE_OPENH264)) {
            v0 = new Object[v1];
            v0[0] = "H264Helper";
            v0[1] = " setH264Mode  MODE_OPENH264 - Mode = " + arg6;
            goto label_16;
        }
        else {
            if(arg6.equals(H264Helper.MODE_MEDIA_CODEC)) {
                v0 = new Object[v1];
                v0[0] = "H264Helper";
                v0[1] = " setH264Mode  MODE_MEDIA_CODEC - Mode = " + arg6;
            }
            else if(arg6.equals(H264Helper.MODE_AUTO)) {
                v0 = new Object[v1];
                v0[0] = "H264Helper";
                v0[1] = " setH264Mode  MODE_AUTO - Mode = " + arg6;
                Log.i(v0);
                if(Build$VERSION.SDK_INT >= 22) {
                    v0 = new Object[v1];
                    v0[0] = "H264Helper";
                    v0[1] = " setH264Mode  MODE_AUTO 1 - Mode = " + arg6;
                    Log.i(v0);
                    v0 = new Object[v1];
                    v0[0] = "LinphoneCoreFactoryImpl";
                    v0[1] = " Openh264 disabled on the project, now using MediaCodec";
                }
                else {
                    goto label_90;
                }
            }
            else {
                goto label_107;
            }

            Log.i(v0);
            arg7.getMSFactory().enableFilterFromName(H264Helper.FILTER_NAME_OPENH264_DEC, false);
            arg7.getMSFactory().enableFilterFromName(H264Helper.FILTER_NAME_OPENH264_ENC, false);
            arg7.getMSFactory().enableFilterFromName(H264Helper.FILTER_NAME_MEDIA_CODEC_DEC, true);
            Factory v7 = arg7.getMSFactory();
            String v0_1 = H264Helper.FILTER_NAME_MEDIA_CODEC_ENC;
            goto label_28;
        label_90:
            v0 = new Object[v1];
            v0[0] = "H264Helper";
            v0[1] = " setH264Mode  MODE_AUTO 2 - Mode = " + arg6;
            Log.i(v0);
            v0 = new Object[v1];
            v0[0] = "LinphoneCoreFactoryImpl";
            v0[1] = " Openh264 enabled on the project";
        label_16:
            Log.i(v0);
            arg7.getMSFactory().enableFilterFromName(H264Helper.FILTER_NAME_MEDIA_CODEC_DEC, false);
            arg7.getMSFactory().enableFilterFromName(H264Helper.FILTER_NAME_MEDIA_CODEC_ENC, false);
            arg7.getMSFactory().enableFilterFromName(H264Helper.FILTER_NAME_OPENH264_DEC, true);
            v7 = arg7.getMSFactory();
            v0_1 = H264Helper.FILTER_NAME_OPENH264_ENC;
        label_28:
            v7.enableFilterFromName(v0_1, true);
            goto label_113;
        label_107:
            v7_1 = new Object[v1];
            v7_1[0] = "LinphoneCoreFactoryImpl";
            v7_1[1] = " Error: Openh264 mode not reconized !";
            Log.i(v7_1);
        }

    label_113:
        v7_1 = new Object[v1];
        v7_1[0] = "H264Helper";
        v7_1[1] = " setH264Mode - Mode = " + arg6;
        Log.i(v7_1);
    }
}

