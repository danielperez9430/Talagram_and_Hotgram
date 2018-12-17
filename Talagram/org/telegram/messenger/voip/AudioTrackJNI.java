package org.telegram.messenger.voip;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class AudioTrackJNI {
    private AudioTrack audioTrack;
    private byte[] buffer;
    private int bufferSize;
    private long nativeInst;
    private boolean needResampling;
    private boolean running;
    private Thread thread;

    public AudioTrackJNI(long arg2) {
        super();
        this.buffer = new byte[1920];
        this.nativeInst = arg2;
    }

    static AudioTrack access$000(AudioTrackJNI arg0) {
        return arg0.audioTrack;
    }

    static boolean access$100(AudioTrackJNI arg0) {
        return arg0.needResampling;
    }

    static boolean access$200(AudioTrackJNI arg0) {
        return arg0.running;
    }

    static byte[] access$300(AudioTrackJNI arg0) {
        return arg0.buffer;
    }

    static void access$400(AudioTrackJNI arg0, byte[] arg1) {
        arg0.nativeCallback(arg1);
    }

    private int getBufferSize(int arg3, int arg4) {
        return Math.max(AudioTrack.getMinBufferSize(arg4, 4, 2), arg3);
    }

    public void init(int arg10, int arg11, int arg12, int arg13) {
        if(this.audioTrack == null) {
            int v5 = this.getBufferSize(arg13, 48000);
            this.bufferSize = arg13;
            AudioTrack v10 = null;
            int v2 = 48000;
            int v3 = arg12 == 1 ? 4 : 12;
            super(0, v2, v3, 2, v5, 1);
            this.audioTrack = v10;
            if(this.audioTrack.getState() != 1) {
                try {
                    this.audioTrack.release();
                    goto label_25;
                }
                catch(Throwable ) {
                label_25:
                    v5 = this.getBufferSize(arg13 * 6, 44100);
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.d("buffer size: " + v5);
                    }

                    v10 = null;
                    v2 = 44100;
                    v3 = arg12 == 1 ? 4 : 12;
                    super(0, v2, v3, 2, v5, 1);
                    this.audioTrack = v10;
                    this.needResampling = true;
                }
            }

            return;
        }

        throw new IllegalStateException("already inited");
    }

    private native void nativeCallback(byte[] arg1) {
    }

    public void release() {
        this.running = false;
        Thread v1 = null;
        if(this.thread != null) {
            try {
                this.thread.join();
            }
            catch(InterruptedException v0) {
                FileLog.e(((Throwable)v0));
            }

            this.thread = v1;
        }

        if(this.audioTrack != null) {
            this.audioTrack.release();
            this.audioTrack = ((AudioTrack)v1);
        }
    }

    public void start() {
        if(this.thread == null) {
            this.startThread();
        }
        else {
            this.audioTrack.play();
        }
    }

    private void startThread() {
        if(this.thread == null) {
            this.running = true;
            this.thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        AudioTrackJNI.this.audioTrack.play();
                    }
                    catch(Exception v0) {
                        if(BuildVars.LOGS_ENABLED) {
                            FileLog.e("error starting AudioTrack", ((Throwable)v0));
                        }

                        return;
                    }

                    ByteBuffer v1 = null;
                    int v2 = 1920;
                    ByteBuffer v0_1 = AudioTrackJNI.this.needResampling ? ByteBuffer.allocateDirect(v2) : v1;
                    int v4 = 1764;
                    if(AudioTrackJNI.this.needResampling) {
                        v1 = ByteBuffer.allocateDirect(v4);
                    }

                    while(AudioTrackJNI.this.running) {
                        try {
                            if(AudioTrackJNI.this.needResampling) {
                                AudioTrackJNI.this.nativeCallback(AudioTrackJNI.this.buffer);
                                v0_1.rewind();
                                v0_1.put(AudioTrackJNI.this.buffer);
                                Resampler.convert48to44(v0_1, v1);
                                v1.rewind();
                                v1.get(AudioTrackJNI.this.buffer, 0, v4);
                                AudioTrackJNI.this.audioTrack.write(AudioTrackJNI.this.buffer, 0, v4);
                            }
                            else {
                                AudioTrackJNI.this.nativeCallback(AudioTrackJNI.this.buffer);
                                AudioTrackJNI.this.audioTrack.write(AudioTrackJNI.this.buffer, 0, v2);
                            }

                            if(AudioTrackJNI.this.running) {
                                continue;
                            }

                            AudioTrackJNI.this.audioTrack.stop();
                            break;
                        }
                        catch(Exception v3) {
                            FileLog.e(((Throwable)v3));
                            continue;
                        }

                        break;
                    }

                    Log.i("tg-voip", "audiotrack thread exits");
                }
            });
            this.thread.start();
            return;
        }

        throw new IllegalStateException("thread already started");
    }

    public void stop() {
        if(this.audioTrack != null) {
            try {
                this.audioTrack.stop();
                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }
}

