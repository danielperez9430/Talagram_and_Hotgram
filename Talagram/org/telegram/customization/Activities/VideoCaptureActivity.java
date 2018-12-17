package org.telegram.customization.Activities;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera$CameraInfo;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import org.telegram.customization.util.view.CameraPreview;

public class VideoCaptureActivity extends Activity {
    class org.telegram.customization.Activities.VideoCaptureActivity$1 implements View$OnClickListener {
        org.telegram.customization.Activities.VideoCaptureActivity$1(VideoCaptureActivity arg1) {
            this.a = arg1;
            super();
        }

        public void onClick(View arg3) {
            if(!this.a.b) {
                if(Camera.getNumberOfCameras() > 1) {
                    VideoCaptureActivity.a(this.a);
                    this.a.b();
                }
                else {
                    Toast.makeText(VideoCaptureActivity.b(this.a), "Sorry, your phone has only one camera!", 1).show();
                }
            }
        }
    }

    class org.telegram.customization.Activities.VideoCaptureActivity$2 implements View$OnClickListener {
        org.telegram.customization.Activities.VideoCaptureActivity$2(VideoCaptureActivity arg1) {
            this.a = arg1;
            super();
        }

        public void onClick(View arg3) {
            VideoCaptureActivity v3;
            boolean v0 = true;
            if(this.a.b) {
                VideoCaptureActivity.c(this.a).stop();
                VideoCaptureActivity.d(this.a);
                Toast.makeText(this.a, "Video captured!", 1).show();
                v3 = this.a;
                v0 = false;
            }
            else {
                if(!VideoCaptureActivity.e(this.a)) {
                    Toast.makeText(this.a, "Fail in prepareMediaRecorder()!\n - Ended -", 1).show();
                    this.a.finish();
                }

                this.a.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            VideoCaptureActivity.c(this.a.a).start();
                            return;
                        }
                        catch(Exception ) {
                            return;
                        }
                    }
                });
                v3 = this.a;
            }

            v3.b = v0;
        }
    }

    View$OnClickListener a;
    boolean b;
    View$OnClickListener c;
    private Camera d;
    private CameraPreview e;
    private MediaRecorder f;
    private Button g;
    private Button h;
    private Context i;
    private boolean j;

    public VideoCaptureActivity() {
        super();
        this.j = false;
        this.a = new org.telegram.customization.Activities.VideoCaptureActivity$1(this);
        this.b = false;
        this.c = new org.telegram.customization.Activities.VideoCaptureActivity$2(this);
    }

    static void a(VideoCaptureActivity arg0) {
        arg0.g();
    }

    private boolean a(Context arg2) {
        if(arg2.getPackageManager().hasSystemFeature("android.hardware.camera")) {
            return 1;
        }

        return 0;
    }

    public void a() {
        this.e = this.findViewById(2131296391);
        this.e.setCamera(this.d);
        this.e.setActivity(this);
        this.g = this.findViewById(2131296378);
        this.g.setOnClickListener(this.c);
        this.h = this.findViewById(2131296375);
        this.h.setOnClickListener(this.a);
    }

    static Context b(VideoCaptureActivity arg0) {
        return arg0.i;
    }

    public void b() {
        int v0;
        if(this.j) {
            v0 = this.d();
            if(v0 >= 0) {
                goto label_7;
            }
        }
        else {
            v0 = this.c();
            if(v0 >= 0) {
            label_7:
                this.d = Camera.open(v0);
                this.e.a(this.d);
            }
        }
    }

    private int c() {
        int v0 = Camera.getNumberOfCameras();
        int v1 = 0;
        while(true) {
            if(v1 < v0) {
                Camera$CameraInfo v2 = new Camera$CameraInfo();
                Camera.getCameraInfo(v1, v2);
                if(v2.facing == 1) {
                    this.j = true;
                }
                else {
                    ++v1;
                    continue;
                }
            }
            else {
                return -1;
            }

            return v1;
        }

        return -1;
    }

    static MediaRecorder c(VideoCaptureActivity arg0) {
        return arg0.f;
    }

    private int d() {
        int v0 = Camera.getNumberOfCameras();
        int v2 = 0;
        while(true) {
            if(v2 < v0) {
                Camera$CameraInfo v3 = new Camera$CameraInfo();
                Camera.getCameraInfo(v2, v3);
                if(v3.facing == 0) {
                    this.j = false;
                }
                else {
                    ++v2;
                    continue;
                }
            }
            else {
                return -1;
            }

            return v2;
        }

        return -1;
    }

    static void d(VideoCaptureActivity arg0) {
        arg0.e();
    }

    private void e() {
        if(this.f != null) {
            this.f.reset();
            this.f.release();
            this.f = null;
            this.d.lock();
        }
    }

    static boolean e(VideoCaptureActivity arg0) {
        return arg0.f();
    }

    private boolean f() {
        this.f = new MediaRecorder();
        this.d.unlock();
        this.f.setCamera(this.d);
        this.f.setAudioSource(5);
        this.f.setVideoSource(1);
        this.f.setProfile(CamcorderProfile.get(5));
        this.f.setOutputFile("/sdcard/myvideo.mp4");
        this.f.setMaxDuration(600000);
        this.f.setMaxFileSize(50000000);
        try {
            this.f.prepare();
            return 1;
        }
        catch(IOException ) {
            this.e();
            return 0;
        }
        catch(IllegalStateException ) {
            this.e();
            return 0;
        }
    }

    private void g() {
        if(this.d != null) {
            this.d.release();
            this.d = null;
        }
    }

    public void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        this.setContentView(2131492920);
        this.getWindow().addFlags(128);
        this.i = ((Context)this);
        this.a();
    }

    protected void onPause() {
        super.onPause();
        this.g();
    }

    public void onResume() {
        super.onResume();
        if(!this.a(this.i)) {
            Toast.makeText(this.i, "Sorry, your phone does not have a camera!", 1).show();
            this.finish();
        }

        if(this.d == null) {
            if(this.c() < 0) {
                Toast.makeText(((Context)this), "No front facing camera found.", 1).show();
                this.h.setVisibility(8);
            }

            this.d = Camera.open(this.c());
            this.e.a(this.d);
        }
    }
}

