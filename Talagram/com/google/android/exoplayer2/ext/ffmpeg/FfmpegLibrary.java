package com.google.android.exoplayer2.ext.ffmpeg;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;

public final class FfmpegLibrary {
    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ffmpeg");
    }

    private FfmpegLibrary() {
        super();
    }

    private static native String ffmpegGetVersion() {
    }

    private static native boolean ffmpegHasDecoder(String arg0) {
    }

    static String getCodecName(String arg1, int arg2) {
        int v1;
        switch(arg1.hashCode()) {
            case -432837260: {
                if(arg1.equals("audio/mpeg-L1")) {
                    v1 = 2;
                    goto label_84;
                }

            label_83:
                v1 = -1;
                break;
            }
            case -432837259: {
                if(!arg1.equals("audio/mpeg-L2")) {
                    goto label_83;
                }

                v1 = 3;
                break;
            }
            case -53558318: {
                if(!arg1.equals("audio/mp4a-latm")) {
                    goto label_83;
                }

                v1 = 0;
                break;
            }
            case 187078296: {
                if(!arg1.equals("audio/ac3")) {
                    goto label_83;
                }

                v1 = 4;
                break;
            }
            case 187094639: {
                if(!arg1.equals("audio/raw")) {
                    goto label_83;
                }

                v1 = 15;
                break;
            }
            case 1503095341: {
                if(!arg1.equals("audio/3gpp")) {
                    goto label_83;
                }

                v1 = 11;
                break;
            }
            case 1504470054: {
                if(!arg1.equals("audio/alac")) {
                    goto label_83;
                }

                v1 = 14;
                break;
            }
            case 1504578661: {
                if(!arg1.equals("audio/eac3")) {
                    goto label_83;
                }

                v1 = 5;
                break;
            }
            case 1504619009: {
                if(!arg1.equals("audio/flac")) {
                    goto label_83;
                }

                v1 = 13;
                break;
            }
            case 1504831518: {
                if(!arg1.equals("audio/mpeg")) {
                    goto label_83;
                }

                v1 = 1;
                break;
            }
            case 1504891608: {
                if(!arg1.equals("audio/opus")) {
                    goto label_83;
                }

                v1 = 10;
                break;
            }
            case 1505942594: {
                if(!arg1.equals("audio/vnd.dts.hd")) {
                    goto label_83;
                }

                v1 = 8;
                break;
            }
            case 1556697186: {
                if(!arg1.equals("audio/true-hd")) {
                    goto label_83;
                }

                v1 = 6;
                break;
            }
            case -1606874997: {
                if(!arg1.equals("audio/amr-wb")) {
                    goto label_83;
                }

                v1 = 12;
                break;
            }
            case -1095064472: {
                if(!arg1.equals("audio/vnd.dts")) {
                    goto label_83;
                }

                v1 = 7;
                break;
            }
            case -1003765268: {
                if(!arg1.equals("audio/vorbis")) {
                    goto label_83;
                }

                v1 = 9;
                break;
            }
            default: {
                goto label_83;
            }
        }

    label_84:
        String v0 = null;
        switch(v1) {
            case 0: {
                return "aac";
            }
            case 1: 
            case 2: 
            case 3: {
                return "mp3";
            }
            case 4: {
                return "ac3";
            }
            case 5: {
                return "eac3";
            }
            case 6: {
                return "truehd";
            }
            case 7: 
            case 8: {
                return "dca";
            }
            case 9: {
                return "vorbis";
            }
            case 10: {
                return "opus";
            }
            case 11: {
                return "amrnb";
            }
            case 12: {
                return "amrwb";
            }
            case 13: {
                return "flac";
            }
            case 14: {
                return "alac";
            }
            case 15: {
                goto label_87;
            }
        }

        return v0;
    label_87:
        if(arg2 == 268435456) {
            return "pcm_mulaw";
        }

        if(arg2 == 536870912) {
            return "pcm_alaw";
        }

        return v0;
    }

    public static String getVersion() {
        return FfmpegLibrary.ffmpegGetVersion();
    }

    public static boolean supportsFormat(String arg0, int arg1) {
        arg0 = FfmpegLibrary.getCodecName(arg0, arg1);
        boolean v0 = arg0 == null || !FfmpegLibrary.ffmpegHasDecoder(arg0) ? false : true;
        return v0;
    }
}

