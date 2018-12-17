package org.telegram.messenger.voip;

import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build$VERSION;
import android.util.Log;
import java.nio.ByteBuffer;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class AudioRecordJNI {
    private AcousticEchoCanceler aec;
    private AutomaticGainControl agc;
    private AudioRecord audioRecord;
    private ByteBuffer buffer;
    private int bufferSize;
    private long nativeInst;
    private boolean needResampling;
    private NoiseSuppressor ns;
    private boolean running;
    private Thread thread;

    public AudioRecordJNI(long arg2) {
        super();
        this.needResampling = false;
        this.nativeInst = arg2;
    }

    static boolean access$000(AudioRecordJNI arg0) {
        return arg0.running;
    }

    static boolean access$100(AudioRecordJNI arg0) {
        return arg0.needResampling;
    }

    static ByteBuffer access$200(AudioRecordJNI arg0) {
        return arg0.buffer;
    }

    static AudioRecord access$300(AudioRecordJNI arg0) {
        return arg0.audioRecord;
    }

    static void access$400(AudioRecordJNI arg0, ByteBuffer arg1) {
        arg0.nativeCallback(arg1);
    }

    private int getBufferSize(int arg3, int arg4) {
        return Math.max(AudioRecord.getMinBufferSize(arg4, 16, 2), arg3);
    }

    public void init(int arg2, int arg3, int arg4, int arg5) {
        if(this.audioRecord == null) {
            this.bufferSize = arg5;
            arg2 = 48000;
            arg3 = 7;
            boolean v4 = this.tryInit(arg3, arg2);
            if(!v4) {
                this.tryInit(1, arg2);
            }

            arg2 = 44100;
            if(!v4) {
                this.tryInit(arg3, arg2);
            }

            if(!v4) {
                this.tryInit(1, arg2);
            }

            this.buffer = ByteBuffer.allocateDirect(arg5);
            return;
        }

        throw new IllegalStateException("already inited");
    }

    private native void nativeCallback(ByteBuffer arg1) {
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

        if(this.audioRecord != null) {
            this.audioRecord.release();
            this.audioRecord = ((AudioRecord)v1);
        }

        if(this.agc != null) {
            this.agc.release();
            this.agc = ((AutomaticGainControl)v1);
        }

        if(this.ns != null) {
            this.ns.release();
            this.ns = ((NoiseSuppressor)v1);
        }

        if(this.aec != null) {
            this.aec.release();
            this.aec = ((AcousticEchoCanceler)v1);
        }
    }

    public boolean start() {
        try {
            if(this.thread != null) {
                goto label_81;
            }

            if(this.audioRecord == null) {
                return 0;
            }

            this.audioRecord.startRecording();
            if(Build$VERSION.SDK_INT < 16) {
                goto label_79;
            }

            try {
                if(AutomaticGainControl.isAvailable()) {
                    this.agc = AutomaticGainControl.create(this.audioRecord.getAudioSessionId());
                    if(this.agc == null) {
                        goto label_33;
                    }

                    this.agc.setEnabled(false);
                    goto label_33;
                }

                if(!BuildVars.LOGS_ENABLED) {
                    goto label_33;
                }

                FileLog.w("AutomaticGainControl is not available on this device :(");
                goto label_33;
            }
            catch(Throwable v1_1) {
                try {
                    if(!BuildVars.LOGS_ENABLED) {
                        goto label_33;
                    }

                    FileLog.e("error creating AutomaticGainControl", v1_1);
                    try {
                    label_33:
                        if(NoiseSuppressor.isAvailable()) {
                            this.ns = NoiseSuppressor.create(this.audioRecord.getAudioSessionId());
                            if(this.ns == null) {
                                goto label_56;
                            }

                            this.ns.setEnabled(VoIPServerConfig.getBoolean("user_system_ns", true));
                            goto label_56;
                        }

                        if(!BuildVars.LOGS_ENABLED) {
                            goto label_56;
                        }

                        FileLog.w("NoiseSuppressor is not available on this device :(");
                        goto label_56;
                    }
                    catch(Throwable v1_1) {
                        try {
                            if(!BuildVars.LOGS_ENABLED) {
                                goto label_56;
                            }

                            FileLog.e("error creating NoiseSuppressor", v1_1);
                            try {
                            label_56:
                                if(AcousticEchoCanceler.isAvailable()) {
                                    this.aec = AcousticEchoCanceler.create(this.audioRecord.getAudioSessionId());
                                    if(this.aec == null) {
                                        goto label_79;
                                    }

                                    this.aec.setEnabled(VoIPServerConfig.getBoolean("use_system_aec", true));
                                    goto label_79;
                                }

                                if(!BuildVars.LOGS_ENABLED) {
                                    goto label_79;
                                }

                                FileLog.w("AcousticEchoCanceler is not available on this device");
                                goto label_79;
                            }
                            catch(Throwable v1_1) {
                                try {
                                    if(BuildVars.LOGS_ENABLED) {
                                        FileLog.e("error creating AcousticEchoCanceler", v1_1);
                                    }

                                label_79:
                                    this.startThread();
                                    return 1;
                                label_81:
                                    this.audioRecord.startRecording();
                                }
                                catch(Exception v1) {
                                label_86:
                                    if(BuildVars.LOGS_ENABLED) {
                                        FileLog.e("Error initializing AudioRecord", ((Throwable)v1));
                                    }

                                    return 0;
                                }
                            }
                        }
                        catch(Exception v1) {
                            goto label_86;
                        }
                    }
                }
                catch(Exception v1) {
                    goto label_86;
                }
            }
        }
        catch(Exception v1) {
            goto label_86;
        }

        return 1;
    }

    private void startThread() {
        if(this.thread == null) {
            this.running = true;
            ByteBuffer v0 = this.needResampling ? ByteBuffer.allocateDirect(1764) : null;
            this.thread = new Thread(new Runnable(v0) {
                public void run() {
                    while(AudioRecordJNI.this.running) {
                        try {
                            if(!AudioRecordJNI.this.needResampling) {
                                AudioRecordJNI.this.audioRecord.read(AudioRecordJNI.this.buffer, 1920);
                            }
                            else {
                                AudioRecordJNI.this.audioRecord.read(this.val$tmpBuf, 1764);
                                Resampler.convert44to48(this.val$tmpBuf, AudioRecordJNI.this.buffer);
                            }

                            if(!AudioRecordJNI.this.running) {
                                AudioRecordJNI.this.audioRecord.stop();
                                break;
                            }

                            AudioRecordJNI.this.nativeCallback(AudioRecordJNI.this.buffer);
                        }
                        catch(Exception v0) {
                            FileLog.e(((Throwable)v0));
                        }
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        Log.i("tg-voip", "audiorecord thread exits");
                    }
                }
            });
            this.thread.start();
            return;
        }

        throw new IllegalStateException("thread already started");
    }

    public void stop() {
        if(this.audioRecord != null) {
            this.audioRecord.stop();
        }
    }

    private boolean tryInit(int arg9, int arg10) {
        if(this.audioRecord != null) {
            try {
                this.audioRecord.release();
                goto label_4;
            }
            catch(Exception ) {
            label_4:
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.d("Trying to initialize AudioRecord with source=" + arg9 + " and sample rate=" + arg10);
                }

                int v1 = 48000;
                int v7 = this.getBufferSize(this.bufferSize, v1);
                try {
                    this.audioRecord = new AudioRecord(arg9, arg10, 16, 2, v7);
                }
                catch(Exception v9) {
                    FileLog.e("AudioRecord init failed!", ((Throwable)v9));
                }

                boolean v9_1 = false;
                boolean v10 = arg10 != v1 ? true : false;
                this.needResampling = v10;
                if(this.audioRecord != null && this.audioRecord.getState() == 1) {
                    v9_1 = true;
                }

                return v9_1;
            }
        }

        goto label_4;
    }
}

