package org.linphone.mediastream.video.display;

import android.content.Context;
import android.opengl.GLSurfaceView$EGLConfigChooser;
import android.opengl.GLSurfaceView$EGLContextFactory;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import org.linphone.mediastream.Log;

public class GL2JNIView extends GLSurfaceView {
    class org.linphone.mediastream.video.display.GL2JNIView$1 {
    }

    class ConfigChooser implements GLSurfaceView$EGLConfigChooser {
        private static int EGL_OPENGL_ES2_BIT = 4;
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue;
        private static int[] s_configAttribs2;

        static {
            ConfigChooser.s_configAttribs2 = new int[]{12324, 4, 12323, 4, 12322, 4, 12352, ConfigChooser.EGL_OPENGL_ES2_BIT, 12344};
        }

        public ConfigChooser(int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
            super();
            this.mValue = new int[1];
            this.mRedSize = arg2;
            this.mGreenSize = arg3;
            this.mBlueSize = arg4;
            this.mAlphaSize = arg5;
            this.mDepthSize = arg6;
            this.mStencilSize = arg7;
        }

        public EGLConfig chooseConfig(EGL10 arg9, EGLDisplay arg10) {
            int[] v0 = new int[1];
            arg9.eglChooseConfig(arg10, ConfigChooser.s_configAttribs2, null, 0, v0);
            int v5 = v0[0];
            if(v5 > 0) {
                EGLConfig[] v7 = new EGLConfig[v5];
                arg9.eglChooseConfig(arg10, ConfigChooser.s_configAttribs2, v7, v5, v0);
                return this.chooseConfig(arg9, arg10, v7);
            }

            throw new IllegalArgumentException("No configs match configSpec");
        }

        public EGLConfig chooseConfig(EGL10 arg13, EGLDisplay arg14, EGLConfig[] arg15) {
            int v0 = arg15.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                EGLConfig v8 = arg15[v1];
                int v9 = this.findConfigAttrib(arg13, arg14, v8, 12325, 0);
                int v2 = this.findConfigAttrib(arg13, arg14, v8, 12326, 0);
                if(v9 >= this.mDepthSize) {
                    if(v2 < this.mStencilSize) {
                    }
                    else {
                        v9 = this.findConfigAttrib(arg13, arg14, v8, 12324, 0);
                        int v10 = this.findConfigAttrib(arg13, arg14, v8, 12323, 0);
                        int v11 = this.findConfigAttrib(arg13, arg14, v8, 12322, 0);
                        v2 = this.findConfigAttrib(arg13, arg14, v8, 12321, 0);
                        if(v9 == this.mRedSize && v10 == this.mGreenSize && v11 == this.mBlueSize && v2 == this.mAlphaSize) {
                            return v8;
                        }
                    }
                }
            }

            return null;
        }

        private int findConfigAttrib(EGL10 arg2, EGLDisplay arg3, EGLConfig arg4, int arg5, int arg6) {
            if(arg2.eglGetConfigAttrib(arg3, arg4, arg5, this.mValue)) {
                return this.mValue[0];
            }

            return arg6;
        }

        private void printConfig(EGL10 arg12, EGLDisplay arg13, EGLConfig arg14) {
            int[] v1 = new int[]{12320, 12321, 12322, 12323, 12324, 12325, 12326, 12327, 12328, 12329, 12330, 12331, 12332, 12333, 12334, 12335, 12336, 12337, 12338, 12339, 12340, 12343, 12342, 12341, 12345, 12346, 12347, 12348, 12349, 12350, 12351, 12352, 12354};
            String[] v0 = new String[33];
            v0[0] = "EGL_BUFFER_SIZE";
            v0[1] = "EGL_ALPHA_SIZE";
            int v5 = 2;
            v0[v5] = "EGL_BLUE_SIZE";
            v0[3] = "EGL_GREEN_SIZE";
            v0[4] = "EGL_RED_SIZE";
            v0[5] = "EGL_DEPTH_SIZE";
            v0[6] = "EGL_STENCIL_SIZE";
            v0[7] = "EGL_CONFIG_CAVEAT";
            v0[8] = "EGL_CONFIG_ID";
            v0[9] = "EGL_LEVEL";
            v0[10] = "EGL_MAX_PBUFFER_HEIGHT";
            v0[11] = "EGL_MAX_PBUFFER_PIXELS";
            v0[12] = "EGL_MAX_PBUFFER_WIDTH";
            v0[13] = "EGL_NATIVE_RENDERABLE";
            v0[14] = "EGL_NATIVE_VISUAL_ID";
            v0[15] = "EGL_NATIVE_VISUAL_TYPE";
            v0[16] = "EGL_PRESERVED_RESOURCES";
            v0[17] = "EGL_SAMPLES";
            v0[18] = "EGL_SAMPLE_BUFFERS";
            v0[19] = "EGL_SURFACE_TYPE";
            v0[20] = "EGL_TRANSPARENT_TYPE";
            v0[21] = "EGL_TRANSPARENT_RED_VALUE";
            v0[22] = "EGL_TRANSPARENT_GREEN_VALUE";
            v0[23] = "EGL_TRANSPARENT_BLUE_VALUE";
            v0[24] = "EGL_BIND_TO_TEXTURE_RGB";
            v0[25] = "EGL_BIND_TO_TEXTURE_RGBA";
            v0[26] = "EGL_MIN_SWAP_INTERVAL";
            v0[27] = "EGL_MAX_SWAP_INTERVAL";
            v0[28] = "EGL_LUMINANCE_SIZE";
            v0[29] = "EGL_ALPHA_MASK_SIZE";
            v0[30] = "EGL_COLOR_BUFFER_TYPE";
            v0[31] = "EGL_RENDERABLE_TYPE";
            v0[32] = "EGL_CONFORMANT";
            int[] v2 = new int[1];
            int v6;
            for(v6 = 0; v6 < v1.length; ++v6) {
                int v7 = v1[v6];
                String v8 = v0[v6];
                if(arg12.eglGetConfigAttrib(arg13, arg14, v7, v2)) {
                    Object[] v7_1 = new Object[1];
                    v7_1[0] = String.format("  %s: %d\n", v8, Integer.valueOf(v2[0]));
                    Log.w(v7_1);
                }
                else {
                    while(arg12.eglGetError() != 12288) {
                    }
                }
            }
        }

        private void printConfigs(EGL10 arg9, EGLDisplay arg10, EGLConfig[] arg11) {
            int v0 = arg11.length;
            Log.w(new Object[]{String.format("%d configurations", Integer.valueOf(v0))});
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                Log.w(new Object[]{String.format("Configuration %d:\n", Integer.valueOf(v2))});
                this.printConfig(arg9, arg10, arg11[v2]);
            }
        }
    }

    class ContextFactory implements GLSurfaceView$EGLContextFactory {
        private static int EGL_CONTEXT_CLIENT_VERSION = 12440;

        static {
        }

        ContextFactory(org.linphone.mediastream.video.display.GL2JNIView$1 arg1) {
            this();
        }

        private ContextFactory() {
            super();
        }

        public EGLContext createContext(EGL10 arg5, EGLDisplay arg6, EGLConfig arg7) {
            Log.w(new Object[]{"creating OpenGL ES 2.0 context"});
            GL2JNIView.checkEglError("Before eglCreateContext", arg5);
            EGLContext v6 = arg5.eglCreateContext(arg6, arg7, EGL10.EGL_NO_CONTEXT, new int[]{ContextFactory.EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
            GL2JNIView.checkEglError("After eglCreateContext", arg5);
            return v6;
        }

        public void destroyContext(EGL10 arg1, EGLDisplay arg2, EGLContext arg3) {
            arg1.eglDestroyContext(arg2, arg3);
        }
    }

    private static final boolean DEBUG = false;

    public GL2JNIView(Context arg1) {
        super(arg1);
        this.init(false, 0, 0);
    }

    public GL2JNIView(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.init(false, 0, 0);
    }

    public GL2JNIView(Context arg1, boolean arg2, int arg3, int arg4) {
        super(arg1);
        this.init(arg2, arg3, arg4);
    }

    static void access$100(String arg0, EGL10 arg1) {
        GL2JNIView.checkEglError(arg0, arg1);
    }

    private static void checkEglError(String arg6, EGL10 arg7) {
        while(true) {
            int v0 = arg7.eglGetError();
            if(v0 == 12288) {
                return;
            }

            Log.e(new Object[]{String.format("%s: EGL error: 0x%x", arg6, Integer.valueOf(v0))});
        }
    }

    private void init(boolean arg17, int arg18, int arg19) {
        GL2JNIView v0 = this;
        if(arg17) {
            this.getHolder().setFormat(-3);
        }

        v0.setEGLContextFactory(new ContextFactory(null));
        ConfigChooser v1 = arg17 ? new ConfigChooser(8, 8, 8, 8, arg18, arg19) : new ConfigChooser(5, 6, 5, 0, arg18, arg19);
        v0.setEGLConfigChooser(((GLSurfaceView$EGLConfigChooser)v1));
    }
}

