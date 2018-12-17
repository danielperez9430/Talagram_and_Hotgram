package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.exoplayer2.audio.AudioCapabilities;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    @Retention(value=RetentionPolicy.SOURCE) @public interface ExtensionRendererMode {
    }

    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    protected static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private final long allowedVideoJoiningTimeMs;
    private final Context context;
    private final DrmSessionManager drmSessionManager;
    private final int extensionRendererMode;

    public DefaultRenderersFactory(Context arg2) {
        this(arg2, 0);
    }

    public DefaultRenderersFactory(Context arg7, int arg8) {
        this(arg7, null, arg8, 5000);
    }

    public DefaultRenderersFactory(Context arg7, int arg8, long arg9) {
        this(arg7, null, arg8, arg9);
    }

    @Deprecated public DefaultRenderersFactory(Context arg1, DrmSessionManager arg2, int arg3, long arg4) {
        super();
        this.context = arg1;
        this.extensionRendererMode = arg3;
        this.allowedVideoJoiningTimeMs = arg4;
        this.drmSessionManager = arg2;
    }

    @Deprecated public DefaultRenderersFactory(Context arg2, DrmSessionManager arg3) {
        this(arg2, arg3, 0);
    }

    @Deprecated public DefaultRenderersFactory(Context arg7, DrmSessionManager arg8, int arg9) {
        this(arg7, arg8, arg9, 5000);
    }

    protected AudioProcessor[] buildAudioProcessors() {
        return new AudioProcessor[0];
    }

    protected void buildAudioRenderers(Context arg14, DrmSessionManager arg15, AudioProcessor[] arg16, Handler arg17, AudioRendererEventListener arg18, int arg19, ArrayList arg20) {
        int v6_5;
        Object v1_3;
        Constructor v1_2;
        Class v1_1;
        int v7_2;
        Object v6_2;
        Class[] v7;
        int v10 = arg19;
        ArrayList v11 = arg20;
        v11.add(new MediaCodecAudioRenderer(arg14, MediaCodecSelector.DEFAULT, arg15, false, arg17, arg18, AudioCapabilities.getCapabilities(arg14), arg16));
        if(v10 == 0) {
            return;
        }

        int v1 = arg20.size();
        int v2 = 2;
        if(v10 == v2) {
            --v1;
        }

        int v4 = 3;
        try {
            Class v6 = Class.forName("com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer");
            v7 = new Class[v4];
            v7[0] = Handler.class;
            v7[1] = AudioRendererEventListener.class;
            v7[v2] = AudioProcessor[].class;
            Constructor v6_1 = v6.getConstructor(v7);
            Object[] v7_1 = new Object[v4];
            v7_1[0] = arg17;
            v7_1[1] = arg18;
            v7_1[v2] = arg16;
            v6_2 = v6_1.newInstance(v7_1);
            v7_2 = v1 + 1;
            goto label_40;
        }
        catch(Exception v0) {
        }
        catch(ClassNotFoundException ) {
            v7_2 = v1;
            goto label_51;
            try {
            label_40:
                v11.add(v1, v6_2);
                Log.i("DefaultRenderersFactory", "Loaded LibopusAudioRenderer.");
                goto label_51;
            }
            catch(ClassNotFoundException ) {
                try {
                label_51:
                    v1_1 = Class.forName("com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer");
                    Class[] v6_3 = new Class[v4];
                    v6_3[0] = Handler.class;
                    v6_3[1] = AudioRendererEventListener.class;
                    v6_3[v2] = AudioProcessor[].class;
                    v1_2 = v1_1.getConstructor(v6_3);
                    Object[] v6_4 = new Object[v4];
                    v6_4[0] = arg17;
                    v6_4[1] = arg18;
                    v6_4[v2] = arg16;
                    v1_3 = v1_2.newInstance(v6_4);
                    v6_5 = v7_2 + 1;
                    goto label_67;
                }
                catch(Exception v0) {
                label_76:
                    throw new RuntimeException("Error instantiating FLAC extension", ((Throwable)v0));
                }
                catch(ClassNotFoundException ) {
                    v6_5 = v7_2;
                    try {
                    label_78:
                        v1_1 = Class.forName("com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer");
                        v7 = new Class[v4];
                        v7[0] = Handler.class;
                        v7[1] = AudioRendererEventListener.class;
                        v7[v2] = AudioProcessor[].class;
                        v1_2 = v1_1.getConstructor(v7);
                        Object[] v4_1 = new Object[v4];
                        v4_1[0] = arg17;
                        v4_1[1] = arg18;
                        v4_1[v2] = arg16;
                        v11.add(v6_5, v1_2.newInstance(v4_1));
                        Log.i("DefaultRenderersFactory", "Loaded FfmpegAudioRenderer.");
                        return;
                    }
                    catch(ClassNotFoundException ) {
                        return;
                    }
                    catch(Exception v0) {
                        goto label_102;
                    }

                    try {
                    label_67:
                        v11.add(v7_2, v1_3);
                        Log.i("DefaultRenderersFactory", "Loaded LibflacAudioRenderer.");
                        goto label_78;
                    }
                    catch(ClassNotFoundException ) {
                        goto label_78;
                    }
                    catch(Exception v0) {
                        goto label_76;
                    }
                }

            label_102:
                throw new RuntimeException("Error instantiating FFmpeg extension", ((Throwable)v0));
                return;
            }
            catch(Exception v0) {
                throw new RuntimeException("Error instantiating Opus extension", ((Throwable)v0));
            }
        }
    }

    protected void buildMetadataRenderers(Context arg1, MetadataOutput arg2, Looper arg3, int arg4, ArrayList arg5) {
        arg5.add(new MetadataRenderer(arg2, arg3));
    }

    protected void buildMiscellaneousRenderers(Context arg1, Handler arg2, int arg3, ArrayList arg4) {
    }

    protected void buildTextRenderers(Context arg1, TextOutput arg2, Looper arg3, int arg4, ArrayList arg5) {
        arg5.add(new TextRenderer(arg2, arg3));
    }

    protected void buildVideoRenderers(Context arg14, DrmSessionManager arg15, long arg16, Handler arg18, VideoRendererEventListener arg19, int arg20, ArrayList arg21) {
        int v0 = arg20;
        ArrayList v1 = arg21;
        v1.add(new MediaCodecVideoRenderer(arg14, MediaCodecSelector.DEFAULT, arg16, arg15, false, arg18, arg19, 50));
        if(v0 == 0) {
            return;
        }

        int v2 = arg21.size();
        int v3 = 2;
        if(v0 == v3) {
            --v2;
        }

        try {
            Class v0_2 = Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer");
            Class[] v5 = new Class[5];
            v5[0] = Boolean.TYPE;
            v5[1] = Long.TYPE;
            v5[v3] = Handler.class;
            v5[3] = VideoRendererEventListener.class;
            v5[4] = Integer.TYPE;
            Constructor v0_3 = v0_2.getConstructor(v5);
            Object[] v4 = new Object[5];
            v4[0] = Boolean.valueOf(true);
            v4[1] = Long.valueOf(arg16);
            v4[v3] = arg18;
            v4[3] = arg19;
            v4[4] = Integer.valueOf(50);
            v1.add(v2, v0_3.newInstance(v4));
            Log.i("DefaultRenderersFactory", "Loaded LibvpxVideoRenderer.");
            return;
        }
        catch(ClassNotFoundException ) {
            return;
        }
        catch(Exception v0_1) {
            throw new RuntimeException("Error instantiating VP9 extension", ((Throwable)v0_1));
        }
    }

    public Renderer[] createRenderers(Handler arg13, VideoRendererEventListener arg14, AudioRendererEventListener arg15, TextOutput arg16, MetadataOutput arg17, DrmSessionManager arg18) {
        DefaultRenderersFactory v9 = this;
        DrmSessionManager v10 = arg18 == null ? v9.drmSessionManager : arg18;
        ArrayList v11 = new ArrayList();
        this.buildVideoRenderers(v9.context, v10, v9.allowedVideoJoiningTimeMs, arg13, arg14, v9.extensionRendererMode, v11);
        this.buildAudioRenderers(v9.context, v10, this.buildAudioProcessors(), arg13, arg15, v9.extensionRendererMode, v11);
        this.buildTextRenderers(v9.context, arg16, arg13.getLooper(), v9.extensionRendererMode, v11);
        this.buildMetadataRenderers(v9.context, arg17, arg13.getLooper(), v9.extensionRendererMode, v11);
        this.buildMiscellaneousRenderers(v9.context, arg13, v9.extensionRendererMode, v11);
        return v11.toArray(new Renderer[v11.size()]);
    }
}

