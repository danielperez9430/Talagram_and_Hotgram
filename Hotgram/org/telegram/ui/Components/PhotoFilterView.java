package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PorterDuff$Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build$VERSION;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;
import android.view.View$MeasureSpec;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController$SavedFilterState;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView$LayoutManager;
import org.telegram.messenger.support.widget.RecyclerView$ViewHolder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.PhotoEditRadioCell;
import org.telegram.ui.Cells.PhotoEditToolCell;

@SuppressLint(value={"NewApi"}) public class PhotoFilterView extends FrameLayout {
    public class CurvesToolValue {
        public static final int CurvesTypeBlue = 3;
        public static final int CurvesTypeGreen = 2;
        public static final int CurvesTypeLuminance = 0;
        public static final int CurvesTypeRed = 1;
        public int activeType;
        public CurvesValue blueCurve;
        public ByteBuffer curveBuffer;
        public CurvesValue greenCurve;
        public CurvesValue luminanceCurve;
        public CurvesValue redCurve;

        public CurvesToolValue() {
            super();
            this.luminanceCurve = new CurvesValue();
            this.redCurve = new CurvesValue();
            this.greenCurve = new CurvesValue();
            this.blueCurve = new CurvesValue();
            this.curveBuffer = null;
            this.curveBuffer = ByteBuffer.allocateDirect(800);
            this.curveBuffer.order(ByteOrder.LITTLE_ENDIAN);
        }

        public void fillBuffer() {
            this.curveBuffer.position(0);
            float[] v0 = this.luminanceCurve.getDataPoints();
            float[] v2 = this.redCurve.getDataPoints();
            float[] v3 = this.greenCurve.getDataPoints();
            float[] v4 = this.blueCurve.getDataPoints();
            int v5;
            for(v5 = 0; v5 < 200; ++v5) {
                this.curveBuffer.put(((byte)(((int)(v2[v5] * 255f)))));
                this.curveBuffer.put(((byte)(((int)(v3[v5] * 255f)))));
                this.curveBuffer.put(((byte)(((int)(v4[v5] * 255f)))));
                this.curveBuffer.put(((byte)(((int)(v0[v5] * 255f)))));
            }

            this.curveBuffer.position(0);
        }

        public boolean shouldBeSkipped() {
            boolean v0 = !this.luminanceCurve.isDefault() || !this.redCurve.isDefault() || !this.greenCurve.isDefault() || !this.blueCurve.isDefault() ? false : true;
            return v0;
        }
    }

    public class CurvesValue {
        public float blacksLevel;
        public float[] cachedDataPoints;
        public float highlightsLevel;
        public float midtonesLevel;
        public float previousBlacksLevel;
        public float previousHighlightsLevel;
        public float previousMidtonesLevel;
        public float previousShadowsLevel;
        public float previousWhitesLevel;
        public float shadowsLevel;
        public float whitesLevel;

        public CurvesValue() {
            super();
            this.blacksLevel = 0f;
            this.shadowsLevel = 25f;
            this.midtonesLevel = 50f;
            this.highlightsLevel = 75f;
            this.whitesLevel = 100f;
            this.previousBlacksLevel = 0f;
            this.previousShadowsLevel = 25f;
            this.previousMidtonesLevel = 50f;
            this.previousHighlightsLevel = 75f;
            this.previousWhitesLevel = 100f;
        }

        public float[] getDataPoints() {
            if(this.cachedDataPoints == null) {
                this.interpolateCurve();
            }

            return this.cachedDataPoints;
        }

        public float[] interpolateCurve() {
            int v2;
            CurvesValue v0 = this;
            float[] v1 = new float[14];
            v1[0] = -0.001f;
            int v5 = 1;
            v1[1] = v0.blacksLevel / 100f;
            float v3 = 0f;
            int v6 = 2;
            v1[v6] = 0f;
            v1[3] = v0.blacksLevel / 100f;
            v1[4] = 0.25f;
            v1[5] = v0.shadowsLevel / 100f;
            float v7 = 0.5f;
            v1[6] = v7;
            v1[7] = v0.midtonesLevel / 100f;
            v1[8] = 0.75f;
            v1[9] = v0.highlightsLevel / 100f;
            float v8 = 1f;
            v1[10] = v8;
            v1[11] = v0.whitesLevel / 100f;
            int v9 = 12;
            v1[v9] = 1.001f;
            int v4 = 13;
            v1[v4] = v0.whitesLevel / 100f;
            int v11 = 100;
            ArrayList v10 = new ArrayList(v11);
            ArrayList v12 = new ArrayList(v11);
            v12.add(Float.valueOf(v1[0]));
            v12.add(Float.valueOf(v1[1]));
            int v13 = 1;
            while(v13 < v1.length / v6 - v6) {
                int v14 = (v13 - 1) * 2;
                float v15 = v1[v14];
                float v14_1 = v1[v14 + v5];
                int v16 = v13 * 2;
                float v17 = v1[v16];
                float v16_1 = v1[v16 + 1];
                int v18 = v13 + 1;
                int v19 = v18 * 2;
                float v20 = v1[v19];
                float v19_1 = v1[v19 + 1];
                v13 = (v13 + 2) * 2;
                float v21 = v1[v13];
                float v13_1 = v1[v13 + v5];
                v2 = 1;
                while(v2 < v11) {
                    float v5_1 = (((float)v2)) * 0.01f;
                    float v22 = v5_1 * v5_1;
                    float v23 = v22 * v5_1;
                    float v25 = (v17 * 2f + (v20 - v15) * v5_1 + (v15 * 2f - v17 * 5f + v20 * 4f - v21) * v22 + (v17 * 3f - v15 - v20 * 3f + v21) * v23) * v7;
                    v5_1 = Math.max(v3, Math.min(v8, (v16_1 * 2f + (v19_1 - v14_1) * v5_1 + (2f * v14_1 - 5f * v16_1 + 4f * v19_1 - v13_1) * v22 + (v16_1 * 3f - v14_1 - 3f * v19_1 + v13_1) * v23) * v7));
                    if(v25 > v15) {
                        v12.add(Float.valueOf(v25));
                        v12.add(Float.valueOf(v5_1));
                    }

                    if((v2 - 1) % v6 == 0) {
                        v10.add(Float.valueOf(v5_1));
                    }

                    ++v2;
                    v3 = 0f;
                }

                v12.add(Float.valueOf(v20));
                v12.add(Float.valueOf(v19_1));
                v13 = v18;
                v3 = 0f;
                v5 = 1;
            }

            v12.add(Float.valueOf(v1[v9]));
            v12.add(Float.valueOf(v1[v4]));
            v0.cachedDataPoints = new float[v10.size()];
            int v1_1;
            for(v1_1 = 0; v1_1 < v0.cachedDataPoints.length; ++v1_1) {
                v0.cachedDataPoints[v1_1] = v10.get(v1_1).floatValue();
            }

            v1 = new float[v12.size()];
            for(v2 = 0; v2 < v1.length; ++v2) {
                v1[v2] = v12.get(v2).floatValue();
            }

            return v1;
        }

        public boolean isDefault() {
            double v2 = 0.00001;
            boolean v0 = (((double)Math.abs(this.blacksLevel - 0f))) >= v2 || (((double)Math.abs(this.shadowsLevel - 25f))) >= v2 || (((double)Math.abs(this.midtonesLevel - 50f))) >= v2 || (((double)Math.abs(this.highlightsLevel - 75f))) >= v2 || (((double)Math.abs(this.whitesLevel - 100f))) >= v2 ? false : true;
            return v0;
        }

        public void restoreValues() {
            this.blacksLevel = this.previousBlacksLevel;
            this.shadowsLevel = this.previousShadowsLevel;
            this.midtonesLevel = this.previousMidtonesLevel;
            this.highlightsLevel = this.previousHighlightsLevel;
            this.whitesLevel = this.previousWhitesLevel;
            this.interpolateCurve();
        }

        public void saveValues() {
            this.previousBlacksLevel = this.blacksLevel;
            this.previousShadowsLevel = this.shadowsLevel;
            this.previousMidtonesLevel = this.midtonesLevel;
            this.previousHighlightsLevel = this.highlightsLevel;
            this.previousWhitesLevel = this.whitesLevel;
        }
    }

    public class EGLThread extends DispatchQueue {
        class org.telegram.ui.Components.PhotoFilterView$EGLThread$1 implements Runnable {
            org.telegram.ui.Components.PhotoFilterView$EGLThread$1(EGLThread arg1) {
                this.this$1 = arg1;
                super();
            }

            public void run() {
                if(!this.this$1.initied) {
                    return;
                }

                if((!this.this$1.eglContext.equals(this.this$1.egl10.eglGetCurrentContext()) || !this.this$1.eglSurface.equals(this.this$1.egl10.eglGetCurrentSurface(12377))) && !this.this$1.egl10.eglMakeCurrent(this.this$1.eglDisplay, this.this$1.eglSurface, this.this$1.eglSurface, this.this$1.eglContext)) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.this$1.egl10.eglGetError()));
                    }

                    return;
                }

                GLES20.glViewport(0, 0, this.this$1.renderBufferWidth, this.this$1.renderBufferHeight);
                this.this$1.drawEnhancePass();
                this.this$1.drawSharpenPass();
                this.this$1.drawCustomParamsPass();
                this.this$1.blured = this.this$1.drawBlurPass();
                GLES20.glViewport(0, 0, this.this$1.surfaceWidth, this.this$1.surfaceHeight);
                GLES20.glBindFramebuffer(36160, 0);
                GLES20.glClear(0);
                GLES20.glUseProgram(this.this$1.simpleShaderProgram);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.this$1.renderTexture[this.this$1.blured ^ 1]);
                GLES20.glUniform1i(this.this$1.simpleSourceImageHandle, 0);
                GLES20.glEnableVertexAttribArray(this.this$1.simpleInputTexCoordHandle);
                GLES20.glVertexAttribPointer(this.this$1.simpleInputTexCoordHandle, 2, 5126, false, 8, this.this$1.textureBuffer);
                GLES20.glEnableVertexAttribArray(this.this$1.simplePositionHandle);
                GLES20.glVertexAttribPointer(this.this$1.simplePositionHandle, 2, 5126, false, 8, this.this$1.vertexBuffer);
                GLES20.glDrawArrays(5, 0, 4);
                this.this$1.egl10.eglSwapBuffers(this.this$1.eglDisplay, this.this$1.eglSurface);
            }
        }

        private final int EGL_CONTEXT_CLIENT_VERSION;
        private final int EGL_OPENGL_ES2_BIT;
        private static final int PGPhotoEnhanceHistogramBins = 256;
        private static final int PGPhotoEnhanceSegments = 4;
        private static final String blurFragmentShaderCode = "uniform sampler2D sourceImage;varying highp vec2 blurCoordinates[9];void main() {lowp vec4 sum = vec4(0.0);sum += texture2D(sourceImage, blurCoordinates[0]) * 0.133571;sum += texture2D(sourceImage, blurCoordinates[1]) * 0.233308;sum += texture2D(sourceImage, blurCoordinates[2]) * 0.233308;sum += texture2D(sourceImage, blurCoordinates[3]) * 0.135928;sum += texture2D(sourceImage, blurCoordinates[4]) * 0.135928;sum += texture2D(sourceImage, blurCoordinates[5]) * 0.051383;sum += texture2D(sourceImage, blurCoordinates[6]) * 0.051383;sum += texture2D(sourceImage, blurCoordinates[7]) * 0.012595;sum += texture2D(sourceImage, blurCoordinates[8]) * 0.012595;gl_FragColor = sum;}";
        private int blurHeightHandle;
        private int blurInputTexCoordHandle;
        private int blurPositionHandle;
        private int blurShaderProgram;
        private int blurSourceImageHandle;
        private static final String blurVertexShaderCode = "attribute vec4 position;attribute vec4 inputTexCoord;uniform highp float texelWidthOffset;uniform highp float texelHeightOffset;varying vec2 blurCoordinates[9];void main() {gl_Position = position;vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);blurCoordinates[0] = inputTexCoord.xy;blurCoordinates[1] = inputTexCoord.xy + singleStepOffset * 1.458430;blurCoordinates[2] = inputTexCoord.xy - singleStepOffset * 1.458430;blurCoordinates[3] = inputTexCoord.xy + singleStepOffset * 3.403985;blurCoordinates[4] = inputTexCoord.xy - singleStepOffset * 3.403985;blurCoordinates[5] = inputTexCoord.xy + singleStepOffset * 5.351806;blurCoordinates[6] = inputTexCoord.xy - singleStepOffset * 5.351806;blurCoordinates[7] = inputTexCoord.xy + singleStepOffset * 7.302940;blurCoordinates[8] = inputTexCoord.xy - singleStepOffset * 7.302940;}";
        private int blurWidthHandle;
        private boolean blured;
        private int contrastHandle;
        private Bitmap currentBitmap;
        private int[] curveTextures;
        private int curvesImageHandle;
        private Runnable drawRunnable;
        private EGL10 egl10;
        private EGLConfig eglConfig;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private static final String enhanceFragmentShaderCode = "precision highp float;varying vec2 texCoord;uniform sampler2D sourceImage;uniform sampler2D inputImageTexture2;uniform float intensity;float enhance(float value) {const vec2 offset = vec2(0.001953125, 0.03125);value = value + offset.x;vec2 coord = (clamp(texCoord, 0.125, 1.0 - 0.125001) - 0.125) * 4.0;vec2 frac = fract(coord);coord = floor(coord);float p00 = float(coord.y * 4.0 + coord.x) * 0.0625 + offset.y;float p01 = float(coord.y * 4.0 + coord.x + 1.0) * 0.0625 + offset.y;float p10 = float((coord.y + 1.0) * 4.0 + coord.x) * 0.0625 + offset.y;float p11 = float((coord.y + 1.0) * 4.0 + coord.x + 1.0) * 0.0625 + offset.y;vec3 c00 = texture2D(inputImageTexture2, vec2(value, p00)).rgb;vec3 c01 = texture2D(inputImageTexture2, vec2(value, p01)).rgb;vec3 c10 = texture2D(inputImageTexture2, vec2(value, p10)).rgb;vec3 c11 = texture2D(inputImageTexture2, vec2(value, p11)).rgb;float c1 = ((c00.r - c00.g) / (c00.b - c00.g));float c2 = ((c01.r - c01.g) / (c01.b - c01.g));float c3 = ((c10.r - c10.g) / (c10.b - c10.g));float c4 = ((c11.r - c11.g) / (c11.b - c11.g));float c1_2 = mix(c1, c2, frac.x);float c3_4 = mix(c3, c4, frac.x);return mix(c1_2, c3_4, frac.y);}vec3 hsv_to_rgb(vec3 c) {vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);}void main() {vec4 texel = texture2D(sourceImage, texCoord);vec4 hsv = texel;hsv.y = min(1.0, hsv.y * 1.2);hsv.z = min(1.0, enhance(hsv.z) * 1.1);gl_FragColor = vec4(hsv_to_rgb(mix(texel.xyz, hsv.xyz, intensity)), texel.w);}";
        private int enhanceInputImageTexture2Handle;
        private int enhanceInputTexCoordHandle;
        private int enhanceIntensityHandle;
        private int enhancePositionHandle;
        private int enhanceShaderProgram;
        private int enhanceSourceImageHandle;
        private int[] enhanceTextures;
        private int exposureHandle;
        private int fadeAmountHandle;
        private GL gl;
        private int grainHandle;
        private int heightHandle;
        private int highlightsHandle;
        private int highlightsTintColorHandle;
        private int highlightsTintIntensityHandle;
        private boolean hsvGenerated;
        private boolean initied;
        private int inputTexCoordHandle;
        private long lastRenderCallTime;
        private int linearBlurAngleHandle;
        private int linearBlurAspectRatioHandle;
        private int linearBlurExcludeBlurSizeHandle;
        private int linearBlurExcludePointHandle;
        private int linearBlurExcludeSizeHandle;
        private static final String linearBlurFragmentShaderCode = "varying highp vec2 texCoord;uniform sampler2D sourceImage;uniform sampler2D inputImageTexture2;uniform lowp float excludeSize;uniform lowp vec2 excludePoint;uniform lowp float excludeBlurSize;uniform highp float angle;uniform highp float aspectRatio;void main() {lowp vec4 sharpImageColor = texture2D(sourceImage, texCoord);lowp vec4 blurredImageColor = texture2D(inputImageTexture2, texCoord);highp vec2 texCoordToUse = vec2(texCoord.x, (texCoord.y * aspectRatio + 0.5 - 0.5 * aspectRatio));highp float distanceFromCenter = abs((texCoordToUse.x - excludePoint.x) * aspectRatio * cos(angle) + (texCoordToUse.y - excludePoint.y) * sin(angle));gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeSize - excludeBlurSize, excludeSize, distanceFromCenter));}";
        private int linearBlurInputTexCoordHandle;
        private int linearBlurPositionHandle;
        private int linearBlurShaderProgram;
        private int linearBlurSourceImage2Handle;
        private int linearBlurSourceImageHandle;
        private boolean needUpdateBlurTexture;
        private int positionHandle;
        private int radialBlurAspectRatioHandle;
        private int radialBlurExcludeBlurSizeHandle;
        private int radialBlurExcludePointHandle;
        private int radialBlurExcludeSizeHandle;
        private static final String radialBlurFragmentShaderCode = "varying highp vec2 texCoord;uniform sampler2D sourceImage;uniform sampler2D inputImageTexture2;uniform lowp float excludeSize;uniform lowp vec2 excludePoint;uniform lowp float excludeBlurSize;uniform highp float aspectRatio;void main() {lowp vec4 sharpImageColor = texture2D(sourceImage, texCoord);lowp vec4 blurredImageColor = texture2D(inputImageTexture2, texCoord);highp vec2 texCoordToUse = vec2(texCoord.x, (texCoord.y * aspectRatio + 0.5 - 0.5 * aspectRatio));highp float distanceFromCenter = distance(excludePoint, texCoordToUse);gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeSize - excludeBlurSize, excludeSize, distanceFromCenter));}";
        private int radialBlurInputTexCoordHandle;
        private int radialBlurPositionHandle;
        private int radialBlurShaderProgram;
        private int radialBlurSourceImage2Handle;
        private int radialBlurSourceImageHandle;
        private int renderBufferHeight;
        private int renderBufferWidth;
        private int[] renderFrameBuffer;
        private int[] renderTexture;
        private static final String rgbToHsvFragmentShaderCode = "precision highp float;varying vec2 texCoord;uniform sampler2D sourceImage;vec3 rgb_to_hsv(vec3 c) {vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);float d = q.x - min(q.w, q.y);float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}void main() {vec4 texel = texture2D(sourceImage, texCoord);gl_FragColor = vec4(rgb_to_hsv(texel.rgb), texel.a);}";
        private int rgbToHsvInputTexCoordHandle;
        private int rgbToHsvPositionHandle;
        private int rgbToHsvShaderProgram;
        private int rgbToHsvSourceImageHandle;
        private int saturationHandle;
        private int shadowsHandle;
        private int shadowsTintColorHandle;
        private int shadowsTintIntensityHandle;
        private static final String sharpenFragmentShaderCode = "precision highp float;varying vec2 texCoord;varying vec2 leftTexCoord;varying vec2 rightTexCoord;varying vec2 topTexCoord;varying vec2 bottomTexCoord;uniform sampler2D sourceImage;uniform float sharpen;void main() {vec4 result = texture2D(sourceImage, texCoord);vec3 leftTextureColor = texture2D(sourceImage, leftTexCoord).rgb;vec3 rightTextureColor = texture2D(sourceImage, rightTexCoord).rgb;vec3 topTextureColor = texture2D(sourceImage, topTexCoord).rgb;vec3 bottomTextureColor = texture2D(sourceImage, bottomTexCoord).rgb;result.rgb = result.rgb * (1.0 + 4.0 * sharpen) - (leftTextureColor + rightTextureColor + topTextureColor + bottomTextureColor) * sharpen;gl_FragColor = result;}";
        private int sharpenHandle;
        private int sharpenHeightHandle;
        private int sharpenInputTexCoordHandle;
        private int sharpenPositionHandle;
        private int sharpenShaderProgram;
        private int sharpenSourceImageHandle;
        private static final String sharpenVertexShaderCode = "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;uniform highp float inputWidth;uniform highp float inputHeight;varying vec2 leftTexCoord;varying vec2 rightTexCoord;varying vec2 topTexCoord;varying vec2 bottomTexCoord;void main() {gl_Position = position;texCoord = inputTexCoord;highp vec2 widthStep = vec2(1.0 / inputWidth, 0.0);highp vec2 heightStep = vec2(0.0, 1.0 / inputHeight);leftTexCoord = inputTexCoord - widthStep;rightTexCoord = inputTexCoord + widthStep;topTexCoord = inputTexCoord + heightStep;bottomTexCoord = inputTexCoord - heightStep;}";
        private int sharpenWidthHandle;
        private static final String simpleFragmentShaderCode = "varying highp vec2 texCoord;uniform sampler2D sourceImage;void main() {gl_FragColor = texture2D(sourceImage, texCoord);}";
        private int simpleInputTexCoordHandle;
        private int simplePositionHandle;
        private int simpleShaderProgram;
        private int simpleSourceImageHandle;
        private static final String simpleVertexShaderCode = "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;void main() {gl_Position = position;texCoord = inputTexCoord;}";
        private int skipToneHandle;
        private int sourceImageHandle;
        private volatile int surfaceHeight;
        private SurfaceTexture surfaceTexture;
        private volatile int surfaceWidth;
        private FloatBuffer textureBuffer;
        private static final String toolsFragmentShaderCode = "varying highp vec2 texCoord;uniform sampler2D sourceImage;uniform highp float width;uniform highp float height;uniform sampler2D curvesImage;uniform lowp float skipTone;uniform lowp float shadows;const mediump vec3 hsLuminanceWeighting = vec3(0.3, 0.3, 0.3);uniform lowp float highlights;uniform lowp float contrast;uniform lowp float fadeAmount;const mediump vec3 satLuminanceWeighting = vec3(0.2126, 0.7152, 0.0722);uniform lowp float saturation;uniform lowp float shadowsTintIntensity;uniform lowp float highlightsTintIntensity;uniform lowp vec3 shadowsTintColor;uniform lowp vec3 highlightsTintColor;uniform lowp float exposure;uniform lowp float warmth;uniform lowp float grain;const lowp float permTexUnit = 1.0 / 256.0;const lowp float permTexUnitHalf = 0.5 / 256.0;const lowp float grainsize = 2.3;uniform lowp float vignette;highp float getLuma(highp vec3 rgbP) {return (0.299 * rgbP.r) + (0.587 * rgbP.g) + (0.114 * rgbP.b);}lowp vec3 rgbToHsv(lowp vec3 c) {highp vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);highp vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);highp vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);highp float d = q.x - min(q.w, q.y);highp float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}lowp vec3 hsvToRgb(lowp vec3 c) {highp vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);highp vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);}highp vec3 rgbToHsl(highp vec3 color) {highp vec3 hsl;highp float fmin = min(min(color.r, color.g), color.b);highp float fmax = max(max(color.r, color.g), color.b);highp float delta = fmax - fmin;hsl.z = (fmax + fmin) / 2.0;if (delta == 0.0) {hsl.x = 0.0;hsl.y = 0.0;} else {if (hsl.z < 0.5) {hsl.y = delta / (fmax + fmin);} else {hsl.y = delta / (2.0 - fmax - fmin);}highp float deltaR = (((fmax - color.r) / 6.0) + (delta / 2.0)) / delta;highp float deltaG = (((fmax - color.g) / 6.0) + (delta / 2.0)) / delta;highp float deltaB = (((fmax - color.b) / 6.0) + (delta / 2.0)) / delta;if (color.r == fmax) {hsl.x = deltaB - deltaG;} else if (color.g == fmax) {hsl.x = (1.0 / 3.0) + deltaR - deltaB;} else if (color.b == fmax) {hsl.x = (2.0 / 3.0) + deltaG - deltaR;}if (hsl.x < 0.0) {hsl.x += 1.0;} else if (hsl.x > 1.0) {hsl.x -= 1.0;}}return hsl;}highp float hueToRgb(highp float f1, highp float f2, highp float hue) {if (hue < 0.0) {hue += 1.0;} else if (hue > 1.0) {hue -= 1.0;}highp float res;if ((6.0 * hue) < 1.0) {res = f1 + (f2 - f1) * 6.0 * hue;} else if ((2.0 * hue) < 1.0) {res = f2;} else if ((3.0 * hue) < 2.0) {res = f1 + (f2 - f1) * ((2.0 / 3.0) - hue) * 6.0;} else {res = f1;} return res;}highp vec3 hslToRgb(highp vec3 hsl) {if (hsl.y == 0.0) {return vec3(hsl.z);} else {highp float f2;if (hsl.z < 0.5) {f2 = hsl.z * (1.0 + hsl.y);} else {f2 = (hsl.z + hsl.y) - (hsl.y * hsl.z);}highp float f1 = 2.0 * hsl.z - f2;return vec3(hueToRgb(f1, f2, hsl.x + (1.0/3.0)), hueToRgb(f1, f2, hsl.x), hueToRgb(f1, f2, hsl.x - (1.0/3.0)));}}highp vec3 rgbToYuv(highp vec3 inP) {highp float luma = getLuma(inP);return vec3(luma, (1.0 / 1.772) * (inP.b - luma), (1.0 / 1.402) * (inP.r - luma));}lowp vec3 yuvToRgb(highp vec3 inP) {return vec3(1.402 * inP.b + inP.r, (inP.r - (0.299 * 1.402 / 0.587) * inP.b - (0.114 * 1.772 / 0.587) * inP.g), 1.772 * inP.g + inP.r);}lowp float easeInOutSigmoid(lowp float value, lowp float strength) {if (value > 0.5) {return 1.0 - pow(2.0 - 2.0 * value, 1.0 / (1.0 - strength)) * 0.5;} else {return pow(2.0 * value, 1.0 / (1.0 - strength)) * 0.5;}}lowp vec3 applyLuminanceCurve(lowp vec3 pixel) {highp float index = floor(clamp(pixel.z / (1.0 / 200.0), 0.0, 199.0));pixel.y = mix(0.0, pixel.y, smoothstep(0.0, 0.1, pixel.z) * (1.0 - smoothstep(0.8, 1.0, pixel.z)));pixel.z = texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).a;return pixel;}lowp vec3 applyRGBCurve(lowp vec3 pixel) {highp float index = floor(clamp(pixel.r / (1.0 / 200.0), 0.0, 199.0));pixel.r = texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).r;index = floor(clamp(pixel.g / (1.0 / 200.0), 0.0, 199.0));pixel.g = clamp(texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).g, 0.0, 1.0);index = floor(clamp(pixel.b / (1.0 / 200.0), 0.0, 199.0));pixel.b = clamp(texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).b, 0.0, 1.0);return pixel;}highp vec3 fadeAdjust(highp vec3 color, highp float fadeVal) {return (color * (1.0 - fadeVal)) + ((color + (vec3(-0.9772) * pow(vec3(color), vec3(3.0)) + vec3(1.708) * pow(vec3(color), vec3(2.0)) + vec3(-0.1603) * vec3(color) + vec3(0.2878) - color * vec3(0.9))) * fadeVal);}lowp vec3 tintRaiseShadowsCurve(lowp vec3 color) {return vec3(-0.003671) * pow(color, vec3(3.0)) + vec3(0.3842) * pow(color, vec3(2.0)) + vec3(0.3764) * color + vec3(0.2515);}lowp vec3 tintShadows(lowp vec3 texel, lowp vec3 tintColor, lowp float tintAmount) {return clamp(mix(texel, mix(texel, tintRaiseShadowsCurve(texel), tintColor), tintAmount), 0.0, 1.0);} lowp vec3 tintHighlights(lowp vec3 texel, lowp vec3 tintColor, lowp float tintAmount) {return clamp(mix(texel, mix(texel, vec3(1.0) - tintRaiseShadowsCurve(vec3(1.0) - texel), (vec3(1.0) - tintColor)), tintAmount), 0.0, 1.0);}highp vec4 rnm(in highp vec2 tc) {highp float noise = sin(dot(tc, vec2(12.9898, 78.233))) * 43758.5453;return vec4(fract(noise), fract(noise * 1.2154), fract(noise * 1.3453), fract(noise * 1.3647)) * 2.0 - 1.0;}highp float fade(in highp float t) {return t * t * t * (t * (t * 6.0 - 15.0) + 10.0);}highp float pnoise3D(in highp vec3 p) {highp vec3 pi = permTexUnit * floor(p) + permTexUnitHalf;highp vec3 pf = fract(p);highp float perm = rnm(pi.xy).a;highp float n000 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf);highp float n001 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(0.0, 0.0, 1.0));perm = rnm(pi.xy + vec2(0.0, permTexUnit)).a;highp float n010 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(0.0, 1.0, 0.0));highp float n011 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(0.0, 1.0, 1.0));perm = rnm(pi.xy + vec2(permTexUnit, 0.0)).a;highp float n100 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(1.0, 0.0, 0.0));highp float n101 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(1.0, 0.0, 1.0));perm = rnm(pi.xy + vec2(permTexUnit, permTexUnit)).a;highp float n110 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(1.0, 1.0, 0.0));highp float n111 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(1.0, 1.0, 1.0));highp vec4 n_x = mix(vec4(n000, n001, n010, n011), vec4(n100, n101, n110, n111), fade(pf.x));highp vec2 n_xy = mix(n_x.xy, n_x.zw, fade(pf.y));return mix(n_xy.x, n_xy.y, fade(pf.z));}lowp vec2 coordRot(in lowp vec2 tc, in lowp float angle) {return vec2(((tc.x * 2.0 - 1.0) * cos(angle) - (tc.y * 2.0 - 1.0) * sin(angle)) * 0.5 + 0.5, ((tc.y * 2.0 - 1.0) * cos(angle) + (tc.x * 2.0 - 1.0) * sin(angle)) * 0.5 + 0.5);}void main() {lowp vec4 source = texture2D(sourceImage, texCoord);lowp vec4 result = source;const lowp float toolEpsilon = 0.005;if (skipTone < toolEpsilon) {result = vec4(applyRGBCurve(hslToRgb(applyLuminanceCurve(rgbToHsl(result.rgb)))), result.a);}mediump float hsLuminance = dot(result.rgb, hsLuminanceWeighting);mediump float shadow = clamp((pow(hsLuminance, 1.0 / shadows) + (-0.76) * pow(hsLuminance, 2.0 / shadows)) - hsLuminance, 0.0, 1.0);mediump float highlight = clamp((1.0 - (pow(1.0 - hsLuminance, 1.0 / (2.0 - highlights)) + (-0.8) * pow(1.0 - hsLuminance, 2.0 / (2.0 - highlights)))) - hsLuminance, -1.0, 0.0);lowp vec3 hsresult = vec3(0.0, 0.0, 0.0) + ((hsLuminance + shadow + highlight) - 0.0) * ((result.rgb - vec3(0.0, 0.0, 0.0)) / (hsLuminance - 0.0));mediump float contrastedLuminance = ((hsLuminance - 0.5) * 1.5) + 0.5;mediump float whiteInterp = contrastedLuminance * contrastedLuminance * contrastedLuminance;mediump float whiteTarget = clamp(highlights, 1.0, 2.0) - 1.0;hsresult = mix(hsresult, vec3(1.0), whiteInterp * whiteTarget);mediump float invContrastedLuminance = 1.0 - contrastedLuminance;mediump float blackInterp = invContrastedLuminance * invContrastedLuminance * invContrastedLuminance;mediump float blackTarget = 1.0 - clamp(shadows, 0.0, 1.0);hsresult = mix(hsresult, vec3(0.0), blackInterp * blackTarget);result = vec4(hsresult.rgb, result.a);result = vec4(clamp(((result.rgb - vec3(0.5)) * contrast + vec3(0.5)), 0.0, 1.0), result.a);if (abs(fadeAmount) > toolEpsilon) {result.rgb = fadeAdjust(result.rgb, fadeAmount);}lowp float satLuminance = dot(result.rgb, satLuminanceWeighting);lowp vec3 greyScaleColor = vec3(satLuminance);result = vec4(clamp(mix(greyScaleColor, result.rgb, saturation), 0.0, 1.0), result.a);if (abs(shadowsTintIntensity) > toolEpsilon) {result.rgb = tintShadows(result.rgb, shadowsTintColor, shadowsTintIntensity * 2.0);}if (abs(highlightsTintIntensity) > toolEpsilon) {result.rgb = tintHighlights(result.rgb, highlightsTintColor, highlightsTintIntensity * 2.0);}if (abs(exposure) > toolEpsilon) {mediump float mag = exposure * 1.045;mediump float exppower = 1.0 + abs(mag);if (mag < 0.0) {exppower = 1.0 / exppower;}result.r = 1.0 - pow((1.0 - result.r), exppower);result.g = 1.0 - pow((1.0 - result.g), exppower);result.b = 1.0 - pow((1.0 - result.b), exppower);}if (abs(warmth) > toolEpsilon) {highp vec3 yuvVec;if (warmth > 0.0 ) {yuvVec = vec3(0.1765, -0.1255, 0.0902);} else {yuvVec = -vec3(0.0588, 0.1569, -0.1255);}highp vec3 yuvColor = rgbToYuv(result.rgb);highp float luma = yuvColor.r;highp float curveScale = sin(luma * 3.14159);yuvColor += 0.375 * warmth * curveScale * yuvVec;result.rgb = yuvToRgb(yuvColor);}if (abs(grain) > toolEpsilon) {highp vec3 rotOffset = vec3(1.425, 3.892, 5.835);highp vec2 rotCoordsR = coordRot(texCoord, rotOffset.x);highp vec3 noise = vec3(pnoise3D(vec3(rotCoordsR * vec2(width / grainsize, height / grainsize),0.0)));lowp vec3 lumcoeff = vec3(0.299,0.587,0.114);lowp float luminance = dot(result.rgb, lumcoeff);lowp float lum = smoothstep(0.2, 0.0, luminance);lum += luminance;noise = mix(noise,vec3(0.0),pow(lum,4.0));result.rgb = result.rgb + noise * grain;}if (abs(vignette) > toolEpsilon) {const lowp float midpoint = 0.7;const lowp float fuzziness = 0.62;lowp float radDist = length(texCoord - 0.5) / sqrt(0.5);lowp float mag = easeInOutSigmoid(radDist * midpoint, fuzziness) * vignette * 0.645;result.rgb = mix(pow(result.rgb, vec3(1.0 / (1.0 - mag))), vec3(0.0), mag * mag);}gl_FragColor = result;}";
        private int toolsShaderProgram;
        private FloatBuffer vertexBuffer;
        private FloatBuffer vertexInvertBuffer;
        private int vignetteHandle;
        private int warmthHandle;
        private int widthHandle;

        public EGLThread(PhotoFilterView arg3, SurfaceTexture arg4, Bitmap arg5) {
            PhotoFilterView.this = arg3;
            super("EGLThread");
            this.EGL_CONTEXT_CLIENT_VERSION = 12440;
            this.EGL_OPENGL_ES2_BIT = 4;
            this.needUpdateBlurTexture = true;
            this.enhanceTextures = new int[2];
            this.renderTexture = new int[3];
            this.renderFrameBuffer = new int[3];
            this.curveTextures = new int[1];
            this.drawRunnable = new org.telegram.ui.Components.PhotoFilterView$EGLThread$1(this);
            this.surfaceTexture = arg4;
            this.currentBitmap = arg5;
        }

        static boolean access$2200(EGLThread arg0) {
            return arg0.initied;
        }

        static EGL10 access$2300(EGLThread arg0) {
            return arg0.egl10;
        }

        static EGLContext access$2400(EGLThread arg0) {
            return arg0.eglContext;
        }

        static EGLSurface access$2500(EGLThread arg0) {
            return arg0.eglSurface;
        }

        static EGLDisplay access$2600(EGLThread arg0) {
            return arg0.eglDisplay;
        }

        static int access$2700(EGLThread arg0) {
            return arg0.renderBufferWidth;
        }

        static int access$2800(EGLThread arg0) {
            return arg0.renderBufferHeight;
        }

        static void access$2900(EGLThread arg0) {
            arg0.drawEnhancePass();
        }

        static void access$3000(EGLThread arg0) {
            arg0.drawSharpenPass();
        }

        static void access$3100(EGLThread arg0) {
            arg0.drawCustomParamsPass();
        }

        static boolean access$3200(EGLThread arg0) {
            return arg0.blured;
        }

        static boolean access$3202(EGLThread arg0, boolean arg1) {
            arg0.blured = arg1;
            return arg1;
        }

        static boolean access$3300(EGLThread arg0) {
            return arg0.drawBlurPass();
        }

        static int access$3400(EGLThread arg0) {
            return arg0.surfaceWidth;
        }

        static int access$3500(EGLThread arg0) {
            return arg0.surfaceHeight;
        }

        static int access$3600(EGLThread arg0) {
            return arg0.simpleShaderProgram;
        }

        static int[] access$3700(EGLThread arg0) {
            return arg0.renderTexture;
        }

        static int access$3800(EGLThread arg0) {
            return arg0.simpleSourceImageHandle;
        }

        static int access$3900(EGLThread arg0) {
            return arg0.simpleInputTexCoordHandle;
        }

        static FloatBuffer access$4000(EGLThread arg0) {
            return arg0.textureBuffer;
        }

        static int access$4100(EGLThread arg0) {
            return arg0.simplePositionHandle;
        }

        static FloatBuffer access$4200(EGLThread arg0) {
            return arg0.vertexBuffer;
        }

        static int[] access$4300(EGLThread arg0) {
            return arg0.renderFrameBuffer;
        }

        static Bitmap access$4400(EGLThread arg0) {
            return arg0.getRenderBufferBitmap();
        }

        static Bitmap access$4602(EGLThread arg0, Bitmap arg1) {
            arg0.currentBitmap = arg1;
            return arg1;
        }

        static boolean access$4700(EGLThread arg0) {
            return arg0.needUpdateBlurTexture;
        }

        static boolean access$4702(EGLThread arg0, boolean arg1) {
            arg0.needUpdateBlurTexture = arg1;
            return arg1;
        }

        static long access$4800(EGLThread arg2) {
            return arg2.lastRenderCallTime;
        }

        static long access$4802(EGLThread arg0, long arg1) {
            arg0.lastRenderCallTime = arg1;
            return arg1;
        }

        static Runnable access$4900(EGLThread arg0) {
            return arg0.drawRunnable;
        }

        private Bitmap createBitmap(Bitmap arg8, int arg9, int arg10, float arg11) {
            Matrix v5 = new Matrix();
            v5.setScale(arg11, arg11);
            v5.postRotate(((float)PhotoFilterView.this.orientation));
            return Bitmap.createBitmap(arg8, 0, 0, arg8.getWidth(), arg8.getHeight(), v5, true);
        }

        private boolean drawBlurPass() {
            int v11;
            int v15;
            boolean v14;
            int v13;
            int v12;
            EGLThread v0 = this;
            if(!v0.this$0.showOriginal) {
                if(v0.this$0.blurType == 0) {
                }
                else {
                    int v3 = 4;
                    int v4 = 5;
                    int v5 = 33984;
                    int v6 = 36064;
                    int v7 = 2;
                    int v9 = 36160;
                    int v10 = 3553;
                    if(v0.needUpdateBlurTexture) {
                        GLES20.glUseProgram(v0.blurShaderProgram);
                        GLES20.glUniform1i(v0.blurSourceImageHandle, 0);
                        GLES20.glEnableVertexAttribArray(v0.blurInputTexCoordHandle);
                        GLES20.glVertexAttribPointer(v0.blurInputTexCoordHandle, 2, 5126, false, 8, v0.textureBuffer);
                        GLES20.glEnableVertexAttribArray(v0.blurPositionHandle);
                        GLES20.glVertexAttribPointer(v0.blurPositionHandle, 2, 5126, false, 8, v0.vertexInvertBuffer);
                        GLES20.glBindFramebuffer(v9, v0.renderFrameBuffer[0]);
                        GLES20.glFramebufferTexture2D(v9, v6, v10, v0.renderTexture[0], 0);
                        GLES20.glClear(0);
                        GLES20.glActiveTexture(v5);
                        GLES20.glBindTexture(v10, v0.renderTexture[1]);
                        GLES20.glUniform1f(v0.blurWidthHandle, 0f);
                        GLES20.glUniform1f(v0.blurHeightHandle, 1f / (((float)v0.renderBufferHeight)));
                        GLES20.glDrawArrays(v4, 0, v3);
                        GLES20.glBindFramebuffer(v9, v0.renderFrameBuffer[v7]);
                        GLES20.glFramebufferTexture2D(v9, v6, v10, v0.renderTexture[v7], 0);
                        GLES20.glClear(0);
                        GLES20.glActiveTexture(v5);
                        GLES20.glBindTexture(v10, v0.renderTexture[0]);
                        GLES20.glUniform1f(v0.blurWidthHandle, 1f / (((float)v0.renderBufferWidth)));
                        GLES20.glUniform1f(v0.blurHeightHandle, 0f);
                        GLES20.glDrawArrays(v4, 0, v3);
                        v0.needUpdateBlurTexture = false;
                    }

                    GLES20.glBindFramebuffer(v9, v0.renderFrameBuffer[0]);
                    GLES20.glFramebufferTexture2D(v9, v6, v10, v0.renderTexture[0], 0);
                    GLES20.glClear(0);
                    if(v0.this$0.blurType == 1) {
                        GLES20.glUseProgram(v0.radialBlurShaderProgram);
                        GLES20.glUniform1i(v0.radialBlurSourceImageHandle, 0);
                        GLES20.glUniform1i(v0.radialBlurSourceImage2Handle, 1);
                        GLES20.glUniform1f(v0.radialBlurExcludeSizeHandle, v0.this$0.blurExcludeSize);
                        GLES20.glUniform1f(v0.radialBlurExcludeBlurSizeHandle, v0.this$0.blurExcludeBlurSize);
                        GLES20.glUniform2f(v0.radialBlurExcludePointHandle, v0.this$0.blurExcludePoint.x, v0.this$0.blurExcludePoint.y);
                        GLES20.glUniform1f(v0.radialBlurAspectRatioHandle, (((float)v0.renderBufferHeight)) / (((float)v0.renderBufferWidth)));
                        GLES20.glEnableVertexAttribArray(v0.radialBlurInputTexCoordHandle);
                        v12 = 2;
                        v13 = 5126;
                        v14 = false;
                        v15 = 8;
                        GLES20.glVertexAttribPointer(v0.radialBlurInputTexCoordHandle, v12, v13, false, v15, v0.textureBuffer);
                        GLES20.glEnableVertexAttribArray(v0.radialBlurPositionHandle);
                        v11 = v0.radialBlurPositionHandle;
                        goto label_132;
                    }
                    else if(v0.this$0.blurType == v7) {
                        GLES20.glUseProgram(v0.linearBlurShaderProgram);
                        GLES20.glUniform1i(v0.linearBlurSourceImageHandle, 0);
                        GLES20.glUniform1i(v0.linearBlurSourceImage2Handle, 1);
                        GLES20.glUniform1f(v0.linearBlurExcludeSizeHandle, v0.this$0.blurExcludeSize);
                        GLES20.glUniform1f(v0.linearBlurExcludeBlurSizeHandle, v0.this$0.blurExcludeBlurSize);
                        GLES20.glUniform1f(v0.linearBlurAngleHandle, v0.this$0.blurAngle);
                        GLES20.glUniform2f(v0.linearBlurExcludePointHandle, v0.this$0.blurExcludePoint.x, v0.this$0.blurExcludePoint.y);
                        GLES20.glUniform1f(v0.linearBlurAspectRatioHandle, (((float)v0.renderBufferHeight)) / (((float)v0.renderBufferWidth)));
                        GLES20.glEnableVertexAttribArray(v0.linearBlurInputTexCoordHandle);
                        v12 = 2;
                        v13 = 5126;
                        v14 = false;
                        v15 = 8;
                        GLES20.glVertexAttribPointer(v0.linearBlurInputTexCoordHandle, v12, v13, false, v15, v0.textureBuffer);
                        GLES20.glEnableVertexAttribArray(v0.linearBlurPositionHandle);
                        v11 = v0.linearBlurPositionHandle;
                    label_132:
                        GLES20.glVertexAttribPointer(v11, v12, v13, v14, v15, v0.vertexInvertBuffer);
                    }

                    GLES20.glActiveTexture(v5);
                    GLES20.glBindTexture(v10, v0.renderTexture[1]);
                    GLES20.glActiveTexture(33985);
                    GLES20.glBindTexture(v10, v0.renderTexture[v7]);
                    GLES20.glDrawArrays(v4, 0, v3);
                    return 1;
                }
            }

            return 0;
        }

        private void drawCustomParamsPass() {
            GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[1]);
            int v4 = 3553;
            GLES20.glFramebufferTexture2D(36160, 36064, v4, this.renderTexture[1], 0);
            GLES20.glClear(0);
            GLES20.glUseProgram(this.toolsShaderProgram);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(v4, this.renderTexture[0]);
            GLES20.glUniform1i(this.sourceImageHandle, 0);
            float v2 = 1f;
            if(PhotoFilterView.this.showOriginal) {
                GLES20.glUniform1f(this.shadowsHandle, v2);
                GLES20.glUniform1f(this.highlightsHandle, v2);
                GLES20.glUniform1f(this.exposureHandle, 0f);
                GLES20.glUniform1f(this.contrastHandle, v2);
                GLES20.glUniform1f(this.saturationHandle, v2);
                GLES20.glUniform1f(this.warmthHandle, 0f);
                GLES20.glUniform1f(this.vignetteHandle, 0f);
                GLES20.glUniform1f(this.grainHandle, 0f);
                GLES20.glUniform1f(this.fadeAmountHandle, 0f);
                GLES20.glUniform3f(this.highlightsTintColorHandle, 0f, 0f, 0f);
                GLES20.glUniform1f(this.highlightsTintIntensityHandle, 0f);
                GLES20.glUniform3f(this.shadowsTintColorHandle, 0f, 0f, 0f);
                GLES20.glUniform1f(this.shadowsTintIntensityHandle, 0f);
                GLES20.glUniform1f(this.skipToneHandle, v2);
            }
            else {
                GLES20.glUniform1f(this.shadowsHandle, PhotoFilterView.this.getShadowsValue());
                GLES20.glUniform1f(this.highlightsHandle, PhotoFilterView.this.getHighlightsValue());
                GLES20.glUniform1f(this.exposureHandle, PhotoFilterView.this.getExposureValue());
                GLES20.glUniform1f(this.contrastHandle, PhotoFilterView.this.getContrastValue());
                GLES20.glUniform1f(this.saturationHandle, PhotoFilterView.this.getSaturationValue());
                GLES20.glUniform1f(this.warmthHandle, PhotoFilterView.this.getWarmthValue());
                GLES20.glUniform1f(this.vignetteHandle, PhotoFilterView.this.getVignetteValue());
                GLES20.glUniform1f(this.grainHandle, PhotoFilterView.this.getGrainValue());
                GLES20.glUniform1f(this.fadeAmountHandle, PhotoFilterView.this.getFadeValue());
                GLES20.glUniform3f(this.highlightsTintColorHandle, (((float)(PhotoFilterView.this.tintHighlightsColor >> 16 & 255))) / 255f, (((float)(PhotoFilterView.this.tintHighlightsColor >> 8 & 255))) / 255f, (((float)(PhotoFilterView.this.tintHighlightsColor & 255))) / 255f);
                GLES20.glUniform1f(this.highlightsTintIntensityHandle, PhotoFilterView.this.getTintHighlightsIntensityValue());
                GLES20.glUniform3f(this.shadowsTintColorHandle, (((float)(PhotoFilterView.this.tintShadowsColor >> 16 & 255))) / 255f, (((float)(PhotoFilterView.this.tintShadowsColor >> 8 & 255))) / 255f, (((float)(PhotoFilterView.this.tintShadowsColor & 255))) / 255f);
                GLES20.glUniform1f(this.shadowsTintIntensityHandle, PhotoFilterView.this.getTintShadowsIntensityValue());
                boolean v0 = PhotoFilterView.this.curvesToolValue.shouldBeSkipped();
                int v6 = this.skipToneHandle;
                if(v0) {
                }
                else {
                    v2 = 0f;
                }

                GLES20.glUniform1f(v6, v2);
                if(v0) {
                    goto label_179;
                }

                PhotoFilterView.this.curvesToolValue.fillBuffer();
                GLES20.glActiveTexture(33985);
                GLES20.glBindTexture(v4, this.curveTextures[0]);
                GLES20.glTexParameteri(v4, 10241, 9729);
                GLES20.glTexParameteri(v4, 10240, 9729);
                GLES20.glTexParameteri(v4, 10242, 33071);
                GLES20.glTexParameteri(v4, 10243, 33071);
                GLES20.glTexImage2D(3553, 0, 6408, 200, 1, 0, 6408, 5121, PhotoFilterView.this.curvesToolValue.curveBuffer);
                GLES20.glUniform1i(this.curvesImageHandle, 1);
            }

        label_179:
            GLES20.glUniform1f(this.widthHandle, ((float)this.renderBufferWidth));
            GLES20.glUniform1f(this.heightHandle, ((float)this.renderBufferHeight));
            GLES20.glEnableVertexAttribArray(this.inputTexCoordHandle);
            GLES20.glVertexAttribPointer(this.inputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
            GLES20.glEnableVertexAttribArray(this.positionHandle);
            GLES20.glVertexAttribPointer(this.positionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
            GLES20.glDrawArrays(5, 0, 4);
        }

        private void drawEnhancePass() {
            float v2_1;
            int v0_2;
            ByteBuffer v11_1;
            EGLThread v1 = this;
            int v2 = 5;
            int v3 = 33984;
            int v4 = 36064;
            int v5 = 4;
            int v6 = 36160;
            int v9 = 3553;
            if(!v1.hsvGenerated) {
                GLES20.glBindFramebuffer(v6, v1.renderFrameBuffer[0]);
                GLES20.glFramebufferTexture2D(v6, v4, v9, v1.renderTexture[0], 0);
                GLES20.glClear(0);
                GLES20.glUseProgram(v1.rgbToHsvShaderProgram);
                GLES20.glActiveTexture(v3);
                GLES20.glBindTexture(v9, v1.renderTexture[1]);
                GLES20.glUniform1i(v1.rgbToHsvSourceImageHandle, 0);
                GLES20.glEnableVertexAttribArray(v1.rgbToHsvInputTexCoordHandle);
                GLES20.glVertexAttribPointer(v1.rgbToHsvInputTexCoordHandle, 2, 5126, false, 8, v1.textureBuffer);
                GLES20.glEnableVertexAttribArray(v1.rgbToHsvPositionHandle);
                GLES20.glVertexAttribPointer(v1.rgbToHsvPositionHandle, 2, 5126, false, 8, v1.vertexBuffer);
                GLES20.glDrawArrays(v2, 0, v5);
                ByteBuffer v0 = ByteBuffer.allocateDirect(v1.renderBufferWidth * v1.renderBufferHeight * 4);
                GLES20.glReadPixels(0, 0, v1.renderBufferWidth, v1.renderBufferHeight, 6408, 5121, v0);
                GLES20.glBindTexture(v9, v1.enhanceTextures[0]);
                GLES20.glTexParameteri(v9, 10241, 9729);
                GLES20.glTexParameteri(v9, 10240, 9729);
                GLES20.glTexParameteri(v9, 10242, 33071);
                GLES20.glTexParameteri(v9, 10243, 33071);
                v2 = 10243;
                v5 = 33071;
                v3 = 10242;
                v4 = 10240;
                int v8 = 9729;
                v6 = 10241;
                GLES20.glTexImage2D(3553, 0, 6408, v1.renderBufferWidth, v1.renderBufferHeight, 0, 6408, 5121, v0);
                ByteBuffer v10 = null;
                int v11 = 16384;
                try {
                    v11_1 = ByteBuffer.allocateDirect(v11);
                }
                catch(Exception v0_1) {
                    v11_1 = v10;
                    goto label_100;
                }

                try {
                    Utilities.calcCDT(v0, v1.renderBufferWidth, v1.renderBufferHeight, v11_1);
                    goto label_101;
                }
                catch(Exception v0_1) {
                }

            label_100:
                FileLog.e(((Throwable)v0_1));
            label_101:
                GLES20.glBindTexture(v9, v1.enhanceTextures[1]);
                GLES20.glTexParameteri(v9, v6, v8);
                GLES20.glTexParameteri(v9, v4, v8);
                GLES20.glTexParameteri(v9, v3, v5);
                GLES20.glTexParameteri(v9, v2, v5);
                GLES20.glTexImage2D(3553, 0, 6408, 256, 16, 0, 6408, 5121, v11_1);
                v1.hsvGenerated = true;
            }

            GLES20.glBindFramebuffer(36160, v1.renderFrameBuffer[1]);
            GLES20.glFramebufferTexture2D(36160, 36064, v9, v1.renderTexture[1], 0);
            GLES20.glClear(0);
            GLES20.glUseProgram(v1.enhanceShaderProgram);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(v9, v1.enhanceTextures[0]);
            GLES20.glUniform1i(v1.enhanceSourceImageHandle, 0);
            GLES20.glActiveTexture(33985);
            GLES20.glBindTexture(v9, v1.enhanceTextures[1]);
            GLES20.glUniform1i(v1.enhanceInputImageTexture2Handle, 1);
            if(v1.this$0.showOriginal) {
                v0_2 = v1.enhanceIntensityHandle;
                v2_1 = 0f;
            }
            else {
                v0_2 = v1.enhanceIntensityHandle;
                v2_1 = v1.this$0.getEnhanceValue();
            }

            GLES20.glUniform1f(v0_2, v2_1);
            GLES20.glEnableVertexAttribArray(v1.enhanceInputTexCoordHandle);
            GLES20.glVertexAttribPointer(v1.enhanceInputTexCoordHandle, 2, 5126, false, 8, v1.textureBuffer);
            GLES20.glEnableVertexAttribArray(v1.enhancePositionHandle);
            GLES20.glVertexAttribPointer(v1.enhancePositionHandle, 2, 5126, false, 8, v1.vertexBuffer);
            GLES20.glDrawArrays(5, 0, 4);
        }

        private void drawSharpenPass() {
            float v2;
            int v0;
            GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
            GLES20.glClear(0);
            GLES20.glUseProgram(this.sharpenShaderProgram);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.renderTexture[1]);
            GLES20.glUniform1i(this.sharpenSourceImageHandle, 0);
            if(PhotoFilterView.this.showOriginal) {
                v0 = this.sharpenHandle;
                v2 = 0f;
            }
            else {
                v0 = this.sharpenHandle;
                v2 = PhotoFilterView.this.getSharpenValue();
            }

            GLES20.glUniform1f(v0, v2);
            GLES20.glUniform1f(this.sharpenWidthHandle, ((float)this.renderBufferWidth));
            GLES20.glUniform1f(this.sharpenHeightHandle, ((float)this.renderBufferHeight));
            GLES20.glEnableVertexAttribArray(this.sharpenInputTexCoordHandle);
            GLES20.glVertexAttribPointer(this.sharpenInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
            GLES20.glEnableVertexAttribArray(this.sharpenPositionHandle);
            GLES20.glVertexAttribPointer(this.sharpenPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
            GLES20.glDrawArrays(5, 0, 4);
        }

        public void finish() {
            EGLSurface v1 = null;
            if(this.eglSurface != null) {
                this.egl10.eglMakeCurrent(this.eglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.egl10.eglDestroySurface(this.eglDisplay, this.eglSurface);
                this.eglSurface = v1;
            }

            if(this.eglContext != null) {
                this.egl10.eglDestroyContext(this.eglDisplay, this.eglContext);
                this.eglContext = ((EGLContext)v1);
            }

            if(this.eglDisplay != null) {
                this.egl10.eglTerminate(this.eglDisplay);
                this.eglDisplay = ((EGLDisplay)v1);
            }
        }

        private Bitmap getRenderBufferBitmap() {
            ByteBuffer v0 = ByteBuffer.allocateDirect(this.renderBufferWidth * this.renderBufferHeight * 4);
            GLES20.glReadPixels(0, 0, this.renderBufferWidth, this.renderBufferHeight, 6408, 5121, v0);
            Bitmap v1 = Bitmap.createBitmap(this.renderBufferWidth, this.renderBufferHeight, Bitmap$Config.ARGB_8888);
            v1.copyPixelsFromBuffer(((Buffer)v0));
            return v1;
        }

        public Bitmap getTexture() {
            if(!this.initied) {
                return null;
            }

            CountDownLatch v0 = new CountDownLatch(1);
            Bitmap[] v1 = new Bitmap[1];
            try {
                this.postRunnable(new Runnable(v1, v0) {
                    public void run() {
                        GLES20.glBindFramebuffer(36160, this.this$1.renderFrameBuffer[1]);
                        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.this$1.renderTexture[1 ^ this.this$1.blured], 0);
                        GLES20.glClear(0);
                        this.val$object[0] = this.this$1.getRenderBufferBitmap();
                        this.val$countDownLatch.countDown();
                        GLES20.glBindFramebuffer(36160, 0);
                        GLES20.glClear(0);
                    }
                });
                v0.await();
            }
            catch(Exception v0_1) {
                FileLog.e(((Throwable)v0_1));
            }

            return v1[0];
        }

        private boolean initGL() {
            this.egl10 = EGLContext.getEGL();
            this.eglDisplay = this.egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if(this.eglDisplay == EGL10.EGL_NO_DISPLAY) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("eglGetDisplay failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                }

                this.finish();
                return 0;
            }

            int v0_1 = 2;
            if(!this.egl10.eglInitialize(this.eglDisplay, new int[v0_1])) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("eglInitialize failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                }

                this.finish();
                return 0;
            }

            int[] v9 = new int[1];
            EGLConfig[] v10 = new EGLConfig[1];
            if(!this.egl10.eglChooseConfig(this.eglDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344}, v10, 1, v9)) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("eglChooseConfig failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                }

                this.finish();
                return 0;
            }

            if(v9[0] > 0) {
                this.eglConfig = v10[0];
                this.eglContext = this.egl10.eglCreateContext(this.eglDisplay, this.eglConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
                if(this.eglContext == null) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("eglCreateContext failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                    }

                    this.finish();
                    return 0;
                }

                if((this.surfaceTexture instanceof SurfaceTexture)) {
                    this.eglSurface = this.egl10.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, this.surfaceTexture, null);
                    if(this.eglSurface != null) {
                        if(this.eglSurface == EGL10.EGL_NO_SURFACE) {
                        }
                        else if(!this.egl10.eglMakeCurrent(this.eglDisplay, this.eglSurface, this.eglSurface, this.eglContext)) {
                            if(BuildVars.LOGS_ENABLED) {
                                FileLog.e("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                            }

                            this.finish();
                            return 0;
                        }
                        else {
                            this.gl = this.eglContext.getGL();
                            float[] v4 = new float[]{-1f, 1f, 1f, 1f, -1f, -1f, 1f, -1f};
                            ByteBuffer v5 = ByteBuffer.allocateDirect(v4.length * 4);
                            v5.order(ByteOrder.nativeOrder());
                            this.vertexBuffer = v5.asFloatBuffer();
                            this.vertexBuffer.put(v4);
                            this.vertexBuffer.position(0);
                            v4 = new float[]{-1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f};
                            v5 = ByteBuffer.allocateDirect(v4.length * 4);
                            v5.order(ByteOrder.nativeOrder());
                            this.vertexInvertBuffer = v5.asFloatBuffer();
                            this.vertexInvertBuffer.put(v4);
                            this.vertexInvertBuffer.position(0);
                            float[] v3 = new float[]{0f, 0f, 1f, 0f, 0f, 1f, 1f, 1f};
                            ByteBuffer v4_1 = ByteBuffer.allocateDirect(v3.length * 4);
                            v4_1.order(ByteOrder.nativeOrder());
                            this.textureBuffer = v4_1.asFloatBuffer();
                            this.textureBuffer.put(v3);
                            this.textureBuffer.position(0);
                            GLES20.glGenTextures(1, this.curveTextures, 0);
                            GLES20.glGenTextures(v0_1, this.enhanceTextures, 0);
                            int v3_1 = 35633;
                            v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;void main() {gl_Position = position;texCoord = inputTexCoord;}");
                            int v5_1 = 35632;
                            int v4_2 = this.loadShader(v5_1, "varying highp vec2 texCoord;uniform sampler2D sourceImage;uniform highp float width;uniform highp float height;uniform sampler2D curvesImage;uniform lowp float skipTone;uniform lowp float shadows;const mediump vec3 hsLuminanceWeighting = vec3(0.3, 0.3, 0.3);uniform lowp float highlights;uniform lowp float contrast;uniform lowp float fadeAmount;const mediump vec3 satLuminanceWeighting = vec3(0.2126, 0.7152, 0.0722);uniform lowp float saturation;uniform lowp float shadowsTintIntensity;uniform lowp float highlightsTintIntensity;uniform lowp vec3 shadowsTintColor;uniform lowp vec3 highlightsTintColor;uniform lowp float exposure;uniform lowp float warmth;uniform lowp float grain;const lowp float permTexUnit = 1.0 / 256.0;const lowp float permTexUnitHalf = 0.5 / 256.0;const lowp float grainsize = 2.3;uniform lowp float vignette;highp float getLuma(highp vec3 rgbP) {return (0.299 * rgbP.r) + (0.587 * rgbP.g) + (0.114 * rgbP.b);}lowp vec3 rgbToHsv(lowp vec3 c) {highp vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);highp vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);highp vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);highp float d = q.x - min(q.w, q.y);highp float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}lowp vec3 hsvToRgb(lowp vec3 c) {highp vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);highp vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);}highp vec3 rgbToHsl(highp vec3 color) {highp vec3 hsl;highp float fmin = min(min(color.r, color.g), color.b);highp float fmax = max(max(color.r, color.g), color.b);highp float delta = fmax - fmin;hsl.z = (fmax + fmin) / 2.0;if (delta == 0.0) {hsl.x = 0.0;hsl.y = 0.0;} else {if (hsl.z < 0.5) {hsl.y = delta / (fmax + fmin);} else {hsl.y = delta / (2.0 - fmax - fmin);}highp float deltaR = (((fmax - color.r) / 6.0) + (delta / 2.0)) / delta;highp float deltaG = (((fmax - color.g) / 6.0) + (delta / 2.0)) / delta;highp float deltaB = (((fmax - color.b) / 6.0) + (delta / 2.0)) / delta;if (color.r == fmax) {hsl.x = deltaB - deltaG;} else if (color.g == fmax) {hsl.x = (1.0 / 3.0) + deltaR - deltaB;} else if (color.b == fmax) {hsl.x = (2.0 / 3.0) + deltaG - deltaR;}if (hsl.x < 0.0) {hsl.x += 1.0;} else if (hsl.x > 1.0) {hsl.x -= 1.0;}}return hsl;}highp float hueToRgb(highp float f1, highp float f2, highp float hue) {if (hue < 0.0) {hue += 1.0;} else if (hue > 1.0) {hue -= 1.0;}highp float res;if ((6.0 * hue) < 1.0) {res = f1 + (f2 - f1) * 6.0 * hue;} else if ((2.0 * hue) < 1.0) {res = f2;} else if ((3.0 * hue) < 2.0) {res = f1 + (f2 - f1) * ((2.0 / 3.0) - hue) * 6.0;} else {res = f1;} return res;}highp vec3 hslToRgb(highp vec3 hsl) {if (hsl.y == 0.0) {return vec3(hsl.z);} else {highp float f2;if (hsl.z < 0.5) {f2 = hsl.z * (1.0 + hsl.y);} else {f2 = (hsl.z + hsl.y) - (hsl.y * hsl.z);}highp float f1 = 2.0 * hsl.z - f2;return vec3(hueToRgb(f1, f2, hsl.x + (1.0/3.0)), hueToRgb(f1, f2, hsl.x), hueToRgb(f1, f2, hsl.x - (1.0/3.0)));}}highp vec3 rgbToYuv(highp vec3 inP) {highp float luma = getLuma(inP);return vec3(luma, (1.0 / 1.772) * (inP.b - luma), (1.0 / 1.402) * (inP.r - luma));}lowp vec3 yuvToRgb(highp vec3 inP) {return vec3(1.402 * inP.b + inP.r, (inP.r - (0.299 * 1.402 / 0.587) * inP.b - (0.114 * 1.772 / 0.587) * inP.g), 1.772 * inP.g + inP.r);}lowp float easeInOutSigmoid(lowp float value, lowp float strength) {if (value > 0.5) {return 1.0 - pow(2.0 - 2.0 * value, 1.0 / (1.0 - strength)) * 0.5;} else {return pow(2.0 * value, 1.0 / (1.0 - strength)) * 0.5;}}lowp vec3 applyLuminanceCurve(lowp vec3 pixel) {highp float index = floor(clamp(pixel.z / (1.0 / 200.0), 0.0, 199.0));pixel.y = mix(0.0, pixel.y, smoothstep(0.0, 0.1, pixel.z) * (1.0 - smoothstep(0.8, 1.0, pixel.z)));pixel.z = texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).a;return pixel;}lowp vec3 applyRGBCurve(lowp vec3 pixel) {highp float index = floor(clamp(pixel.r / (1.0 / 200.0), 0.0, 199.0));pixel.r = texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).r;index = floor(clamp(pixel.g / (1.0 / 200.0), 0.0, 199.0));pixel.g = clamp(texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).g, 0.0, 1.0);index = floor(clamp(pixel.b / (1.0 / 200.0), 0.0, 199.0));pixel.b = clamp(texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).b, 0.0, 1.0);return pixel;}highp vec3 fadeAdjust(highp vec3 color, highp float fadeVal) {return (color * (1.0 - fadeVal)) + ((color + (vec3(-0.9772) * pow(vec3(color), vec3(3.0)) + vec3(1.708) * pow(vec3(color), vec3(2.0)) + vec3(-0.1603) * vec3(color) + vec3(0.2878) - color * vec3(0.9))) * fadeVal);}lowp vec3 tintRaiseShadowsCurve(lowp vec3 color) {return vec3(-0.003671) * pow(color, vec3(3.0)) + vec3(0.3842) * pow(color, vec3(2.0)) + vec3(0.3764) * color + vec3(0.2515);}lowp vec3 tintShadows(lowp vec3 texel, lowp vec3 tintColor, lowp float tintAmount) {return clamp(mix(texel, mix(texel, tintRaiseShadowsCurve(texel), tintColor), tintAmount), 0.0, 1.0);} lowp vec3 tintHighlights(lowp vec3 texel, lowp vec3 tintColor, lowp float tintAmount) {return clamp(mix(texel, mix(texel, vec3(1.0) - tintRaiseShadowsCurve(vec3(1.0) - texel), (vec3(1.0) - tintColor)), tintAmount), 0.0, 1.0);}highp vec4 rnm(in highp vec2 tc) {highp float noise = sin(dot(tc, vec2(12.9898, 78.233))) * 43758.5453;return vec4(fract(noise), fract(noise * 1.2154), fract(noise * 1.3453), fract(noise * 1.3647)) * 2.0 - 1.0;}highp float fade(in highp float t) {return t * t * t * (t * (t * 6.0 - 15.0) + 10.0);}highp float pnoise3D(in highp vec3 p) {highp vec3 pi = permTexUnit * floor(p) + permTexUnitHalf;highp vec3 pf = fract(p);highp float perm = rnm(pi.xy).a;highp float n000 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf);highp float n001 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(0.0, 0.0, 1.0));perm = rnm(pi.xy + vec2(0.0, permTexUnit)).a;highp float n010 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(0.0, 1.0, 0.0));highp float n011 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(0.0, 1.0, 1.0));perm = rnm(pi.xy + vec2(permTexUnit, 0.0)).a;highp float n100 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(1.0, 0.0, 0.0));highp float n101 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(1.0, 0.0, 1.0));perm = rnm(pi.xy + vec2(permTexUnit, permTexUnit)).a;highp float n110 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(1.0, 1.0, 0.0));highp float n111 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(1.0, 1.0, 1.0));highp vec4 n_x = mix(vec4(n000, n001, n010, n011), vec4(n100, n101, n110, n111), fade(pf.x));highp vec2 n_xy = mix(n_x.xy, n_x.zw, fade(pf.y));return mix(n_xy.x, n_xy.y, fade(pf.z));}lowp vec2 coordRot(in lowp vec2 tc, in lowp float angle) {return vec2(((tc.x * 2.0 - 1.0) * cos(angle) - (tc.y * 2.0 - 1.0) * sin(angle)) * 0.5 + 0.5, ((tc.y * 2.0 - 1.0) * cos(angle) + (tc.x * 2.0 - 1.0) * sin(angle)) * 0.5 + 0.5);}void main() {lowp vec4 source = texture2D(sourceImage, texCoord);lowp vec4 result = source;const lowp float toolEpsilon = 0.005;if (skipTone < toolEpsilon) {result = vec4(applyRGBCurve(hslToRgb(applyLuminanceCurve(rgbToHsl(result.rgb)))), result.a);}mediump float hsLuminance = dot(result.rgb, hsLuminanceWeighting);mediump float shadow = clamp((pow(hsLuminance, 1.0 / shadows) + (-0.76) * pow(hsLuminance, 2.0 / shadows)) - hsLuminance, 0.0, 1.0);mediump float highlight = clamp((1.0 - (pow(1.0 - hsLuminance, 1.0 / (2.0 - highlights)) + (-0.8) * pow(1.0 - hsLuminance, 2.0 / (2.0 - highlights)))) - hsLuminance, -1.0, 0.0);lowp vec3 hsresult = vec3(0.0, 0.0, 0.0) + ((hsLuminance + shadow + highlight) - 0.0) * ((result.rgb - vec3(0.0, 0.0, 0.0)) / (hsLuminance - 0.0));mediump float contrastedLuminance = ((hsLuminance - 0.5) * 1.5) + 0.5;mediump float whiteInterp = contrastedLuminance * contrastedLuminance * contrastedLuminance;mediump float whiteTarget = clamp(highlights, 1.0, 2.0) - 1.0;hsresult = mix(hsresult, vec3(1.0), whiteInterp * whiteTarget);mediump float invContrastedLuminance = 1.0 - contrastedLuminance;mediump float blackInterp = invContrastedLuminance * invContrastedLuminance * invContrastedLuminance;mediump float blackTarget = 1.0 - clamp(shadows, 0.0, 1.0);hsresult = mix(hsresult, vec3(0.0), blackInterp * blackTarget);result = vec4(hsresult.rgb, result.a);result = vec4(clamp(((result.rgb - vec3(0.5)) * contrast + vec3(0.5)), 0.0, 1.0), result.a);if (abs(fadeAmount) > toolEpsilon) {result.rgb = fadeAdjust(result.rgb, fadeAmount);}lowp float satLuminance = dot(result.rgb, satLuminanceWeighting);lowp vec3 greyScaleColor = vec3(satLuminance);result = vec4(clamp(mix(greyScaleColor, result.rgb, saturation), 0.0, 1.0), result.a);if (abs(shadowsTintIntensity) > toolEpsilon) {result.rgb = tintShadows(result.rgb, shadowsTintColor, shadowsTintIntensity * 2.0);}if (abs(highlightsTintIntensity) > toolEpsilon) {result.rgb = tintHighlights(result.rgb, highlightsTintColor, highlightsTintIntensity * 2.0);}if (abs(exposure) > toolEpsilon) {mediump float mag = exposure * 1.045;mediump float exppower = 1.0 + abs(mag);if (mag < 0.0) {exppower = 1.0 / exppower;}result.r = 1.0 - pow((1.0 - result.r), exppower);result.g = 1.0 - pow((1.0 - result.g), exppower);result.b = 1.0 - pow((1.0 - result.b), exppower);}if (abs(warmth) > toolEpsilon) {highp vec3 yuvVec;if (warmth > 0.0 ) {yuvVec = vec3(0.1765, -0.1255, 0.0902);} else {yuvVec = -vec3(0.0588, 0.1569, -0.1255);}highp vec3 yuvColor = rgbToYuv(result.rgb);highp float luma = yuvColor.r;highp float curveScale = sin(luma * 3.14159);yuvColor += 0.375 * warmth * curveScale * yuvVec;result.rgb = yuvToRgb(yuvColor);}if (abs(grain) > toolEpsilon) {highp vec3 rotOffset = vec3(1.425, 3.892, 5.835);highp vec2 rotCoordsR = coordRot(texCoord, rotOffset.x);highp vec3 noise = vec3(pnoise3D(vec3(rotCoordsR * vec2(width / grainsize, height / grainsize),0.0)));lowp vec3 lumcoeff = vec3(0.299,0.587,0.114);lowp float luminance = dot(result.rgb, lumcoeff);lowp float lum = smoothstep(0.2, 0.0, luminance);lum += luminance;noise = mix(noise,vec3(0.0),pow(lum,4.0));result.rgb = result.rgb + noise * grain;}if (abs(vignette) > toolEpsilon) {const lowp float midpoint = 0.7;const lowp float fuzziness = 0.62;lowp float radDist = length(texCoord - 0.5) / sqrt(0.5);lowp float mag = easeInOutSigmoid(radDist * midpoint, fuzziness) * vignette * 0.645;result.rgb = mix(pow(result.rgb, vec3(1.0 / (1.0 - mag))), vec3(0.0), mag * mag);}gl_FragColor = result;}");
                            if(v0_1 != 0 && v4_2 != 0) {
                                this.toolsShaderProgram = GLES20.glCreateProgram();
                                GLES20.glAttachShader(this.toolsShaderProgram, v0_1);
                                GLES20.glAttachShader(this.toolsShaderProgram, v4_2);
                                GLES20.glBindAttribLocation(this.toolsShaderProgram, 0, "position");
                                GLES20.glBindAttribLocation(this.toolsShaderProgram, 1, "inputTexCoord");
                                GLES20.glLinkProgram(this.toolsShaderProgram);
                                int[] v0_2 = new int[1];
                                int v6 = 35714;
                                GLES20.glGetProgramiv(this.toolsShaderProgram, v6, v0_2, 0);
                                if(v0_2[0] == 0) {
                                    GLES20.glDeleteProgram(this.toolsShaderProgram);
                                    this.toolsShaderProgram = 0;
                                }
                                else {
                                    this.positionHandle = GLES20.glGetAttribLocation(this.toolsShaderProgram, "position");
                                    this.inputTexCoordHandle = GLES20.glGetAttribLocation(this.toolsShaderProgram, "inputTexCoord");
                                    this.sourceImageHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "sourceImage");
                                    this.shadowsHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "shadows");
                                    this.highlightsHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "highlights");
                                    this.exposureHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "exposure");
                                    this.contrastHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "contrast");
                                    this.saturationHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "saturation");
                                    this.warmthHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "warmth");
                                    this.vignetteHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "vignette");
                                    this.grainHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "grain");
                                    this.widthHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "width");
                                    this.heightHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "height");
                                    this.curvesImageHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "curvesImage");
                                    this.skipToneHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "skipTone");
                                    this.fadeAmountHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "fadeAmount");
                                    this.shadowsTintIntensityHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "shadowsTintIntensity");
                                    this.highlightsTintIntensityHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "highlightsTintIntensity");
                                    this.shadowsTintColorHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "shadowsTintColor");
                                    this.highlightsTintColorHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "highlightsTintColor");
                                }

                                v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;uniform highp float inputWidth;uniform highp float inputHeight;varying vec2 leftTexCoord;varying vec2 rightTexCoord;varying vec2 topTexCoord;varying vec2 bottomTexCoord;void main() {gl_Position = position;texCoord = inputTexCoord;highp vec2 widthStep = vec2(1.0 / inputWidth, 0.0);highp vec2 heightStep = vec2(0.0, 1.0 / inputHeight);leftTexCoord = inputTexCoord - widthStep;rightTexCoord = inputTexCoord + widthStep;topTexCoord = inputTexCoord + heightStep;bottomTexCoord = inputTexCoord - heightStep;}");
                                v4_2 = this.loadShader(v5_1, "precision highp float;varying vec2 texCoord;varying vec2 leftTexCoord;varying vec2 rightTexCoord;varying vec2 topTexCoord;varying vec2 bottomTexCoord;uniform sampler2D sourceImage;uniform float sharpen;void main() {vec4 result = texture2D(sourceImage, texCoord);vec3 leftTextureColor = texture2D(sourceImage, leftTexCoord).rgb;vec3 rightTextureColor = texture2D(sourceImage, rightTexCoord).rgb;vec3 topTextureColor = texture2D(sourceImage, topTexCoord).rgb;vec3 bottomTextureColor = texture2D(sourceImage, bottomTexCoord).rgb;result.rgb = result.rgb * (1.0 + 4.0 * sharpen) - (leftTextureColor + rightTextureColor + topTextureColor + bottomTextureColor) * sharpen;gl_FragColor = result;}");
                                if(v0_1 != 0 && v4_2 != 0) {
                                    this.sharpenShaderProgram = GLES20.glCreateProgram();
                                    GLES20.glAttachShader(this.sharpenShaderProgram, v0_1);
                                    GLES20.glAttachShader(this.sharpenShaderProgram, v4_2);
                                    GLES20.glBindAttribLocation(this.sharpenShaderProgram, 0, "position");
                                    GLES20.glBindAttribLocation(this.sharpenShaderProgram, 1, "inputTexCoord");
                                    GLES20.glLinkProgram(this.sharpenShaderProgram);
                                    v0_2 = new int[1];
                                    GLES20.glGetProgramiv(this.sharpenShaderProgram, v6, v0_2, 0);
                                    if(v0_2[0] == 0) {
                                        GLES20.glDeleteProgram(this.sharpenShaderProgram);
                                        this.sharpenShaderProgram = 0;
                                    }
                                    else {
                                        this.sharpenPositionHandle = GLES20.glGetAttribLocation(this.sharpenShaderProgram, "position");
                                        this.sharpenInputTexCoordHandle = GLES20.glGetAttribLocation(this.sharpenShaderProgram, "inputTexCoord");
                                        this.sharpenSourceImageHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "sourceImage");
                                        this.sharpenWidthHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "inputWidth");
                                        this.sharpenHeightHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "inputHeight");
                                        this.sharpenHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "sharpen");
                                    }

                                    v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec4 inputTexCoord;uniform highp float texelWidthOffset;uniform highp float texelHeightOffset;varying vec2 blurCoordinates[9];void main() {gl_Position = position;vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);blurCoordinates[0] = inputTexCoord.xy;blurCoordinates[1] = inputTexCoord.xy + singleStepOffset * 1.458430;blurCoordinates[2] = inputTexCoord.xy - singleStepOffset * 1.458430;blurCoordinates[3] = inputTexCoord.xy + singleStepOffset * 3.403985;blurCoordinates[4] = inputTexCoord.xy - singleStepOffset * 3.403985;blurCoordinates[5] = inputTexCoord.xy + singleStepOffset * 5.351806;blurCoordinates[6] = inputTexCoord.xy - singleStepOffset * 5.351806;blurCoordinates[7] = inputTexCoord.xy + singleStepOffset * 7.302940;blurCoordinates[8] = inputTexCoord.xy - singleStepOffset * 7.302940;}");
                                    v4_2 = this.loadShader(v5_1, "uniform sampler2D sourceImage;varying highp vec2 blurCoordinates[9];void main() {lowp vec4 sum = vec4(0.0);sum += texture2D(sourceImage, blurCoordinates[0]) * 0.133571;sum += texture2D(sourceImage, blurCoordinates[1]) * 0.233308;sum += texture2D(sourceImage, blurCoordinates[2]) * 0.233308;sum += texture2D(sourceImage, blurCoordinates[3]) * 0.135928;sum += texture2D(sourceImage, blurCoordinates[4]) * 0.135928;sum += texture2D(sourceImage, blurCoordinates[5]) * 0.051383;sum += texture2D(sourceImage, blurCoordinates[6]) * 0.051383;sum += texture2D(sourceImage, blurCoordinates[7]) * 0.012595;sum += texture2D(sourceImage, blurCoordinates[8]) * 0.012595;gl_FragColor = sum;}");
                                    if(v0_1 != 0 && v4_2 != 0) {
                                        this.blurShaderProgram = GLES20.glCreateProgram();
                                        GLES20.glAttachShader(this.blurShaderProgram, v0_1);
                                        GLES20.glAttachShader(this.blurShaderProgram, v4_2);
                                        GLES20.glBindAttribLocation(this.blurShaderProgram, 0, "position");
                                        GLES20.glBindAttribLocation(this.blurShaderProgram, 1, "inputTexCoord");
                                        GLES20.glLinkProgram(this.blurShaderProgram);
                                        v0_2 = new int[1];
                                        GLES20.glGetProgramiv(this.blurShaderProgram, v6, v0_2, 0);
                                        if(v0_2[0] == 0) {
                                            GLES20.glDeleteProgram(this.blurShaderProgram);
                                            this.blurShaderProgram = 0;
                                        }
                                        else {
                                            this.blurPositionHandle = GLES20.glGetAttribLocation(this.blurShaderProgram, "position");
                                            this.blurInputTexCoordHandle = GLES20.glGetAttribLocation(this.blurShaderProgram, "inputTexCoord");
                                            this.blurSourceImageHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "sourceImage");
                                            this.blurWidthHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "texelWidthOffset");
                                            this.blurHeightHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "texelHeightOffset");
                                        }

                                        v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;void main() {gl_Position = position;texCoord = inputTexCoord;}");
                                        v4_2 = this.loadShader(v5_1, "varying highp vec2 texCoord;uniform sampler2D sourceImage;uniform sampler2D inputImageTexture2;uniform lowp float excludeSize;uniform lowp vec2 excludePoint;uniform lowp float excludeBlurSize;uniform highp float angle;uniform highp float aspectRatio;void main() {lowp vec4 sharpImageColor = texture2D(sourceImage, texCoord);lowp vec4 blurredImageColor = texture2D(inputImageTexture2, texCoord);highp vec2 texCoordToUse = vec2(texCoord.x, (texCoord.y * aspectRatio + 0.5 - 0.5 * aspectRatio));highp float distanceFromCenter = abs((texCoordToUse.x - excludePoint.x) * aspectRatio * cos(angle) + (texCoordToUse.y - excludePoint.y) * sin(angle));gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeSize - excludeBlurSize, excludeSize, distanceFromCenter));}");
                                        if(v0_1 != 0 && v4_2 != 0) {
                                            this.linearBlurShaderProgram = GLES20.glCreateProgram();
                                            GLES20.glAttachShader(this.linearBlurShaderProgram, v0_1);
                                            GLES20.glAttachShader(this.linearBlurShaderProgram, v4_2);
                                            GLES20.glBindAttribLocation(this.linearBlurShaderProgram, 0, "position");
                                            GLES20.glBindAttribLocation(this.linearBlurShaderProgram, 1, "inputTexCoord");
                                            GLES20.glLinkProgram(this.linearBlurShaderProgram);
                                            v0_2 = new int[1];
                                            GLES20.glGetProgramiv(this.linearBlurShaderProgram, v6, v0_2, 0);
                                            if(v0_2[0] == 0) {
                                                GLES20.glDeleteProgram(this.linearBlurShaderProgram);
                                                this.linearBlurShaderProgram = 0;
                                            }
                                            else {
                                                this.linearBlurPositionHandle = GLES20.glGetAttribLocation(this.linearBlurShaderProgram, "position");
                                                this.linearBlurInputTexCoordHandle = GLES20.glGetAttribLocation(this.linearBlurShaderProgram, "inputTexCoord");
                                                this.linearBlurSourceImageHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "sourceImage");
                                                this.linearBlurSourceImage2Handle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "inputImageTexture2");
                                                this.linearBlurExcludeSizeHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "excludeSize");
                                                this.linearBlurExcludePointHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "excludePoint");
                                                this.linearBlurExcludeBlurSizeHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "excludeBlurSize");
                                                this.linearBlurAngleHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "angle");
                                                this.linearBlurAspectRatioHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "aspectRatio");
                                            }

                                            v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;void main() {gl_Position = position;texCoord = inputTexCoord;}");
                                            v4_2 = this.loadShader(v5_1, "varying highp vec2 texCoord;uniform sampler2D sourceImage;uniform sampler2D inputImageTexture2;uniform lowp float excludeSize;uniform lowp vec2 excludePoint;uniform lowp float excludeBlurSize;uniform highp float aspectRatio;void main() {lowp vec4 sharpImageColor = texture2D(sourceImage, texCoord);lowp vec4 blurredImageColor = texture2D(inputImageTexture2, texCoord);highp vec2 texCoordToUse = vec2(texCoord.x, (texCoord.y * aspectRatio + 0.5 - 0.5 * aspectRatio));highp float distanceFromCenter = distance(excludePoint, texCoordToUse);gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeSize - excludeBlurSize, excludeSize, distanceFromCenter));}");
                                            if(v0_1 != 0 && v4_2 != 0) {
                                                this.radialBlurShaderProgram = GLES20.glCreateProgram();
                                                GLES20.glAttachShader(this.radialBlurShaderProgram, v0_1);
                                                GLES20.glAttachShader(this.radialBlurShaderProgram, v4_2);
                                                GLES20.glBindAttribLocation(this.radialBlurShaderProgram, 0, "position");
                                                GLES20.glBindAttribLocation(this.radialBlurShaderProgram, 1, "inputTexCoord");
                                                GLES20.glLinkProgram(this.radialBlurShaderProgram);
                                                v0_2 = new int[1];
                                                GLES20.glGetProgramiv(this.radialBlurShaderProgram, v6, v0_2, 0);
                                                if(v0_2[0] == 0) {
                                                    GLES20.glDeleteProgram(this.radialBlurShaderProgram);
                                                    this.radialBlurShaderProgram = 0;
                                                }
                                                else {
                                                    this.radialBlurPositionHandle = GLES20.glGetAttribLocation(this.radialBlurShaderProgram, "position");
                                                    this.radialBlurInputTexCoordHandle = GLES20.glGetAttribLocation(this.radialBlurShaderProgram, "inputTexCoord");
                                                    this.radialBlurSourceImageHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "sourceImage");
                                                    this.radialBlurSourceImage2Handle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "inputImageTexture2");
                                                    this.radialBlurExcludeSizeHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "excludeSize");
                                                    this.radialBlurExcludePointHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "excludePoint");
                                                    this.radialBlurExcludeBlurSizeHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "excludeBlurSize");
                                                    this.radialBlurAspectRatioHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "aspectRatio");
                                                }

                                                v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;void main() {gl_Position = position;texCoord = inputTexCoord;}");
                                                v4_2 = this.loadShader(v5_1, "precision highp float;varying vec2 texCoord;uniform sampler2D sourceImage;vec3 rgb_to_hsv(vec3 c) {vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);float d = q.x - min(q.w, q.y);float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}void main() {vec4 texel = texture2D(sourceImage, texCoord);gl_FragColor = vec4(rgb_to_hsv(texel.rgb), texel.a);}");
                                                if(v0_1 != 0 && v4_2 != 0) {
                                                    this.rgbToHsvShaderProgram = GLES20.glCreateProgram();
                                                    GLES20.glAttachShader(this.rgbToHsvShaderProgram, v0_1);
                                                    GLES20.glAttachShader(this.rgbToHsvShaderProgram, v4_2);
                                                    GLES20.glBindAttribLocation(this.rgbToHsvShaderProgram, 0, "position");
                                                    GLES20.glBindAttribLocation(this.rgbToHsvShaderProgram, 1, "inputTexCoord");
                                                    GLES20.glLinkProgram(this.rgbToHsvShaderProgram);
                                                    v0_2 = new int[1];
                                                    GLES20.glGetProgramiv(this.rgbToHsvShaderProgram, v6, v0_2, 0);
                                                    if(v0_2[0] == 0) {
                                                        GLES20.glDeleteProgram(this.rgbToHsvShaderProgram);
                                                        this.rgbToHsvShaderProgram = 0;
                                                    }
                                                    else {
                                                        this.rgbToHsvPositionHandle = GLES20.glGetAttribLocation(this.rgbToHsvShaderProgram, "position");
                                                        this.rgbToHsvInputTexCoordHandle = GLES20.glGetAttribLocation(this.rgbToHsvShaderProgram, "inputTexCoord");
                                                        this.rgbToHsvSourceImageHandle = GLES20.glGetUniformLocation(this.rgbToHsvShaderProgram, "sourceImage");
                                                    }

                                                    v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;void main() {gl_Position = position;texCoord = inputTexCoord;}");
                                                    v4_2 = this.loadShader(v5_1, "precision highp float;varying vec2 texCoord;uniform sampler2D sourceImage;uniform sampler2D inputImageTexture2;uniform float intensity;float enhance(float value) {const vec2 offset = vec2(0.001953125, 0.03125);value = value + offset.x;vec2 coord = (clamp(texCoord, 0.125, 1.0 - 0.125001) - 0.125) * 4.0;vec2 frac = fract(coord);coord = floor(coord);float p00 = float(coord.y * 4.0 + coord.x) * 0.0625 + offset.y;float p01 = float(coord.y * 4.0 + coord.x + 1.0) * 0.0625 + offset.y;float p10 = float((coord.y + 1.0) * 4.0 + coord.x) * 0.0625 + offset.y;float p11 = float((coord.y + 1.0) * 4.0 + coord.x + 1.0) * 0.0625 + offset.y;vec3 c00 = texture2D(inputImageTexture2, vec2(value, p00)).rgb;vec3 c01 = texture2D(inputImageTexture2, vec2(value, p01)).rgb;vec3 c10 = texture2D(inputImageTexture2, vec2(value, p10)).rgb;vec3 c11 = texture2D(inputImageTexture2, vec2(value, p11)).rgb;float c1 = ((c00.r - c00.g) / (c00.b - c00.g));float c2 = ((c01.r - c01.g) / (c01.b - c01.g));float c3 = ((c10.r - c10.g) / (c10.b - c10.g));float c4 = ((c11.r - c11.g) / (c11.b - c11.g));float c1_2 = mix(c1, c2, frac.x);float c3_4 = mix(c3, c4, frac.x);return mix(c1_2, c3_4, frac.y);}vec3 hsv_to_rgb(vec3 c) {vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);}void main() {vec4 texel = texture2D(sourceImage, texCoord);vec4 hsv = texel;hsv.y = min(1.0, hsv.y * 1.2);hsv.z = min(1.0, enhance(hsv.z) * 1.1);gl_FragColor = vec4(hsv_to_rgb(mix(texel.xyz, hsv.xyz, intensity)), texel.w);}");
                                                    if(v0_1 != 0 && v4_2 != 0) {
                                                        this.enhanceShaderProgram = GLES20.glCreateProgram();
                                                        GLES20.glAttachShader(this.enhanceShaderProgram, v0_1);
                                                        GLES20.glAttachShader(this.enhanceShaderProgram, v4_2);
                                                        GLES20.glBindAttribLocation(this.enhanceShaderProgram, 0, "position");
                                                        GLES20.glBindAttribLocation(this.enhanceShaderProgram, 1, "inputTexCoord");
                                                        GLES20.glLinkProgram(this.enhanceShaderProgram);
                                                        v0_2 = new int[1];
                                                        GLES20.glGetProgramiv(this.enhanceShaderProgram, v6, v0_2, 0);
                                                        if(v0_2[0] == 0) {
                                                            GLES20.glDeleteProgram(this.enhanceShaderProgram);
                                                            this.enhanceShaderProgram = 0;
                                                        }
                                                        else {
                                                            this.enhancePositionHandle = GLES20.glGetAttribLocation(this.enhanceShaderProgram, "position");
                                                            this.enhanceInputTexCoordHandle = GLES20.glGetAttribLocation(this.enhanceShaderProgram, "inputTexCoord");
                                                            this.enhanceSourceImageHandle = GLES20.glGetUniformLocation(this.enhanceShaderProgram, "sourceImage");
                                                            this.enhanceIntensityHandle = GLES20.glGetUniformLocation(this.enhanceShaderProgram, "intensity");
                                                            this.enhanceInputImageTexture2Handle = GLES20.glGetUniformLocation(this.enhanceShaderProgram, "inputImageTexture2");
                                                        }

                                                        v0_1 = this.loadShader(v3_1, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 texCoord;void main() {gl_Position = position;texCoord = inputTexCoord;}");
                                                        v3_1 = this.loadShader(v5_1, "varying highp vec2 texCoord;uniform sampler2D sourceImage;void main() {gl_FragColor = texture2D(sourceImage, texCoord);}");
                                                        if(v0_1 != 0 && v3_1 != 0) {
                                                            this.simpleShaderProgram = GLES20.glCreateProgram();
                                                            GLES20.glAttachShader(this.simpleShaderProgram, v0_1);
                                                            GLES20.glAttachShader(this.simpleShaderProgram, v3_1);
                                                            GLES20.glBindAttribLocation(this.simpleShaderProgram, 0, "position");
                                                            GLES20.glBindAttribLocation(this.simpleShaderProgram, 1, "inputTexCoord");
                                                            GLES20.glLinkProgram(this.simpleShaderProgram);
                                                            v0_2 = new int[1];
                                                            GLES20.glGetProgramiv(this.simpleShaderProgram, v6, v0_2, 0);
                                                            if(v0_2[0] == 0) {
                                                                GLES20.glDeleteProgram(this.simpleShaderProgram);
                                                                this.simpleShaderProgram = 0;
                                                            }
                                                            else {
                                                                this.simplePositionHandle = GLES20.glGetAttribLocation(this.simpleShaderProgram, "position");
                                                                this.simpleInputTexCoordHandle = GLES20.glGetAttribLocation(this.simpleShaderProgram, "inputTexCoord");
                                                                this.simpleSourceImageHandle = GLES20.glGetUniformLocation(this.simpleShaderProgram, "sourceImage");
                                                            }

                                                            if(this.currentBitmap != null) {
                                                                this.loadTexture(this.currentBitmap);
                                                            }

                                                            return 1;
                                                        }

                                                        this.finish();
                                                        return 0;
                                                    }

                                                    this.finish();
                                                    return 0;
                                                }

                                                this.finish();
                                                return 0;
                                            }

                                            this.finish();
                                            return 0;
                                        }

                                        this.finish();
                                        return 0;
                                    }

                                    this.finish();
                                    return 0;
                                }

                                this.finish();
                                return 0;
                            }

                            this.finish();
                            return 0;
                        }
                    }

                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("createWindowSurface failed " + GLUtils.getEGLErrorString(this.egl10.eglGetError()));
                    }

                    this.finish();
                    return 0;
                }

                this.finish();
                return 0;
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("eglConfig not initialized");
            }

            this.finish();
            return 0;
        }

        private int loadShader(int arg3, String arg4) {
            arg3 = GLES20.glCreateShader(arg3);
            GLES20.glShaderSource(arg3, arg4);
            GLES20.glCompileShader(arg3);
            int[] v4 = new int[1];
            GLES20.glGetShaderiv(arg3, 35713, v4, 0);
            if(v4[0] == 0) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e(GLES20.glGetShaderInfoLog(arg3));
                }

                GLES20.glDeleteShader(arg3);
                arg3 = 0;
            }

            return arg3;
        }

        private void loadTexture(Bitmap arg19) {
            EGLThread v0 = this;
            v0.renderBufferWidth = arg19.getWidth();
            v0.renderBufferHeight = arg19.getHeight();
            float v1 = ((float)AndroidUtilities.getPhotoSize());
            if((((float)v0.renderBufferWidth)) > v1 || (((float)v0.renderBufferHeight)) > v1 || v0.this$0.orientation % 360 != 0) {
                float v2 = 1f;
                if((((float)v0.renderBufferWidth)) > v1 || (((float)v0.renderBufferHeight)) > v1) {
                    v2 = v1 / (((float)arg19.getWidth()));
                    float v3 = v1 / (((float)arg19.getHeight()));
                    if(v2 < v3) {
                        v0.renderBufferWidth = ((int)v1);
                        v0.renderBufferHeight = ((int)((((float)arg19.getHeight())) * v2));
                    }
                    else {
                        v0.renderBufferHeight = ((int)v1);
                        v0.renderBufferWidth = ((int)((((float)arg19.getWidth())) * v3));
                        v2 = v3;
                    }
                }

                if(v0.this$0.orientation % 360 == 90 || v0.this$0.orientation % 360 == 270) {
                    int v1_1 = v0.renderBufferWidth;
                    v0.renderBufferWidth = v0.renderBufferHeight;
                    v0.renderBufferHeight = v1_1;
                }

                v0.currentBitmap = v0.createBitmap(arg19, v0.renderBufferWidth, v0.renderBufferHeight, v2);
            }

            GLES20.glGenFramebuffers(3, v0.renderFrameBuffer, 0);
            GLES20.glGenTextures(3, v0.renderTexture, 0);
            GLES20.glBindTexture(3553, v0.renderTexture[0]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLES20.glTexImage2D(3553, 0, 6408, v0.renderBufferWidth, v0.renderBufferHeight, 0, 6408, 5121, null);
            GLES20.glBindTexture(3553, v0.renderTexture[1]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLUtils.texImage2D(3553, 0, v0.currentBitmap, 0);
            GLES20.glBindTexture(3553, v0.renderTexture[2]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLES20.glTexImage2D(3553, 0, 6408, v0.renderBufferWidth, v0.renderBufferHeight, 0, 6408, 5121, null);
        }

        public void requestRender(boolean arg2) {
            this.requestRender(arg2, false);
        }

        public void requestRender(boolean arg2, boolean arg3) {
            this.postRunnable(new Runnable(arg2, arg3) {
                public void run() {
                    if(!this.this$1.needUpdateBlurTexture) {
                        this.this$1.needUpdateBlurTexture = this.val$updateBlur;
                    }

                    long v0 = System.currentTimeMillis();
                    if((this.val$force) || Math.abs(this.this$1.lastRenderCallTime - v0) > 30) {
                        this.this$1.lastRenderCallTime = v0;
                        this.this$1.drawRunnable.run();
                    }
                }
            });
        }

        public void run() {
            this.initied = this.initGL();
            super.run();
        }

        public void setSurfaceTextureSize(int arg1, int arg2) {
            this.surfaceWidth = arg1;
            this.surfaceHeight = arg2;
        }

        public void shutdown() {
            this.postRunnable(new Runnable() {
                public void run() {
                    this.this$1.finish();
                    this.this$1.currentBitmap = null;
                    Looper v0 = Looper.myLooper();
                    if(v0 != null) {
                        v0.quit();
                    }
                }
            });
        }
    }

    public class ToolsAdapter extends SelectionAdapter {
        private Context mContext;

        public ToolsAdapter(PhotoFilterView arg1, Context arg2) {
            PhotoFilterView.this = arg1;
            super();
            this.mContext = arg2;
        }

        public int getItemCount() {
            return 13;
        }

        public long getItemId(int arg3) {
            return ((long)arg3);
        }

        public int getItemViewType(int arg2) {
            if(arg2 != PhotoFilterView.this.tintShadowsTool) {
                if(arg2 == PhotoFilterView.this.tintHighlightsTool) {
                }
                else {
                    return 0;
                }
            }

            return 1;
        }

        public boolean isEnabled(ViewHolder arg1) {
            return 0;
        }

        public void onBindViewHolder(ViewHolder arg5, int arg6) {
            float v0_1;
            int v0;
            String v6;
            View v5;
            switch(arg5.getItemViewType()) {
                case 0: {
                    v5 = arg5.itemView;
                    ((PhotoEditToolCell)v5).setTag(Integer.valueOf(arg6));
                    int v2 = 100;
                    if(arg6 == PhotoFilterView.this.enhanceTool) {
                        v6 = LocaleController.getString("Enhance", 2131624686);
                        v0_1 = PhotoFilterView.this.enhanceValue;
                    }
                    else {
                        int v3 = -100;
                        if(arg6 == PhotoFilterView.this.highlightsTool) {
                            v6 = LocaleController.getString("Highlights", 2131624953);
                            v0_1 = PhotoFilterView.this.highlightsValue;
                        }
                        else if(arg6 == PhotoFilterView.this.contrastTool) {
                            v6 = LocaleController.getString("Contrast", 2131624483);
                            v0_1 = PhotoFilterView.this.contrastValue;
                        }
                        else if(arg6 == PhotoFilterView.this.exposureTool) {
                            v6 = LocaleController.getString("Exposure", 2131624776);
                            v0_1 = PhotoFilterView.this.exposureValue;
                        }
                        else if(arg6 == PhotoFilterView.this.warmthTool) {
                            v6 = LocaleController.getString("Warmth", 2131626406);
                            v0_1 = PhotoFilterView.this.warmthValue;
                        }
                        else if(arg6 == PhotoFilterView.this.saturationTool) {
                            v6 = LocaleController.getString("Saturation", 2131625937);
                            v0_1 = PhotoFilterView.this.saturationValue;
                        }
                        else if(arg6 == PhotoFilterView.this.vignetteTool) {
                            v6 = LocaleController.getString("Vignette", 2131626364);
                            v0_1 = PhotoFilterView.this.vignetteValue;
                            goto label_38;
                        }
                        else if(arg6 == PhotoFilterView.this.shadowsTool) {
                            v6 = LocaleController.getString("Shadows", 2131626057);
                            v0_1 = PhotoFilterView.this.shadowsValue;
                        }
                        else {
                            goto label_105;
                        }

                        ((PhotoEditToolCell)v5).setIconAndTextAndValue(v6, v0_1, v3, v2);
                        return;
                    label_105:
                        if(arg6 == PhotoFilterView.this.grainTool) {
                            v6 = LocaleController.getString("Grain", 2131624906);
                            v0_1 = PhotoFilterView.this.grainValue;
                            goto label_38;
                        }

                        if(arg6 == PhotoFilterView.this.sharpenTool) {
                            v6 = LocaleController.getString("Sharpen", 2131626083);
                            v0_1 = PhotoFilterView.this.sharpenValue;
                            goto label_38;
                        }

                        if(arg6 != PhotoFilterView.this.fadeTool) {
                            return;
                        }

                        v6 = LocaleController.getString("Fade", 2131624779);
                        v0_1 = PhotoFilterView.this.fadeValue;
                    }

                label_38:
                    ((PhotoEditToolCell)v5).setIconAndTextAndValue(v6, v0_1, 0, v2);
                    break;
                }
                case 1: {
                    v5 = arg5.itemView;
                    ((PhotoEditRadioCell)v5).setTag(Integer.valueOf(arg6));
                    if(arg6 == PhotoFilterView.this.tintShadowsTool) {
                        v6 = LocaleController.getString("TintShadows", 2131626242);
                        v0 = PhotoFilterView.this.tintShadowsColor;
                    }
                    else if(arg6 == PhotoFilterView.this.tintHighlightsTool) {
                        v6 = LocaleController.getString("TintHighlights", 2131626241);
                        v0 = PhotoFilterView.this.tintHighlightsColor;
                    }
                    else {
                        return;
                    }

                    ((PhotoEditRadioCell)v5).setIconAndTextAndValue(v6, 0, v0);
                    break;
                }
                default: {
                    break;
                }
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup arg1, int arg2) {
            PhotoEditToolCell v1;
            if(arg2 == 0) {
                v1 = new PhotoEditToolCell(this.mContext);
                v1.setSeekBarDelegate(new PhotoEditorSeekBarDelegate() {
                    public void onProgressChanged(int arg2, int arg3) {
                        if(arg2 == this.this$1.this$0.enhanceTool) {
                            this.this$1.this$0.enhanceValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.highlightsTool) {
                            this.this$1.this$0.highlightsValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.contrastTool) {
                            this.this$1.this$0.contrastValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.exposureTool) {
                            this.this$1.this$0.exposureValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.warmthTool) {
                            this.this$1.this$0.warmthValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.saturationTool) {
                            this.this$1.this$0.saturationValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.vignetteTool) {
                            this.this$1.this$0.vignetteValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.shadowsTool) {
                            this.this$1.this$0.shadowsValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.grainTool) {
                            this.this$1.this$0.grainValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.sharpenTool) {
                            this.this$1.this$0.sharpenValue = ((float)arg3);
                        }
                        else if(arg2 == this.this$1.this$0.fadeTool) {
                            this.this$1.this$0.fadeValue = ((float)arg3);
                        }

                        if(this.this$1.this$0.eglThread != null) {
                            this.this$1.this$0.eglThread.requestRender(true);
                        }
                    }
                });
            }
            else {
                PhotoEditRadioCell v1_1 = new PhotoEditRadioCell(this.mContext);
                ((View)v1_1).setOnClickListener(new View$OnClickListener() {
                    public void onClick(View arg3) {
                        if(((PhotoEditRadioCell)arg3).getTag().intValue() == this.this$1.this$0.tintShadowsTool) {
                            this.this$1.this$0.tintShadowsColor = ((PhotoEditRadioCell)arg3).getCurrentColor();
                        }
                        else {
                            this.this$1.this$0.tintHighlightsColor = ((PhotoEditRadioCell)arg3).getCurrentColor();
                        }

                        if(this.this$1.this$0.eglThread != null) {
                            this.this$1.this$0.eglThread.requestRender(false);
                        }
                    }
                });
            }

            return new Holder(((View)v1));
        }
    }

    private Bitmap bitmapToEdit;
    private float blurAngle;
    private PhotoFilterBlurControl blurControl;
    private float blurExcludeBlurSize;
    private Point blurExcludePoint;
    private float blurExcludeSize;
    private ImageView blurItem;
    private FrameLayout blurLayout;
    private TextView blurLinearButton;
    private TextView blurOffButton;
    private TextView blurRadialButton;
    private int blurType;
    private TextView cancelTextView;
    private int contrastTool;
    private float contrastValue;
    private static final int curveDataStep = 2;
    private static final int curveGranularity = 100;
    private ImageView curveItem;
    private FrameLayout curveLayout;
    private RadioButton[] curveRadioButton;
    private PhotoFilterCurvesControl curvesControl;
    private CurvesToolValue curvesToolValue;
    private TextView doneTextView;
    private EGLThread eglThread;
    private int enhanceTool;
    private float enhanceValue;
    private int exposureTool;
    private float exposureValue;
    private int fadeTool;
    private float fadeValue;
    private int grainTool;
    private float grainValue;
    private int highlightsTool;
    private float highlightsValue;
    private SavedFilterState lastState;
    private int orientation;
    private RecyclerListView recyclerListView;
    private int saturationTool;
    private float saturationValue;
    private int selectedTool;
    private int shadowsTool;
    private float shadowsValue;
    private int sharpenTool;
    private float sharpenValue;
    private boolean showOriginal;
    private TextureView textureView;
    private int tintHighlightsColor;
    private int tintHighlightsTool;
    private int tintShadowsColor;
    private int tintShadowsTool;
    private FrameLayout toolsView;
    private ImageView tuneItem;
    private int vignetteTool;
    private float vignetteValue;
    private int warmthTool;
    private float warmthValue;

    public PhotoFilterView(Context arg23, Bitmap arg24, int arg25, SavedFilterState arg26) {
        RadioButton v13_1;
        int v12_1;
        String v12;
        PhotoFilterView v0 = this;
        Context v1 = arg23;
        SavedFilterState v2 = arg26;
        super(arg23);
        v0.enhanceTool = 0;
        v0.exposureTool = 1;
        int v5 = 2;
        v0.contrastTool = v5;
        v0.saturationTool = 3;
        int v6 = 4;
        v0.warmthTool = v6;
        v0.fadeTool = 5;
        v0.highlightsTool = 6;
        v0.shadowsTool = 7;
        v0.vignetteTool = 8;
        v0.grainTool = 9;
        v0.sharpenTool = 10;
        v0.tintShadowsTool = 11;
        v0.tintHighlightsTool = 12;
        v0.curveRadioButton = new RadioButton[v6];
        if(v2 != null) {
            v0.enhanceValue = v2.enhanceValue;
            v0.exposureValue = v2.exposureValue;
            v0.contrastValue = v2.contrastValue;
            v0.warmthValue = v2.warmthValue;
            v0.saturationValue = v2.saturationValue;
            v0.fadeValue = v2.fadeValue;
            v0.tintShadowsColor = v2.tintShadowsColor;
            v0.tintHighlightsColor = v2.tintHighlightsColor;
            v0.highlightsValue = v2.highlightsValue;
            v0.shadowsValue = v2.shadowsValue;
            v0.vignetteValue = v2.vignetteValue;
            v0.grainValue = v2.grainValue;
            v0.blurType = v2.blurType;
            v0.sharpenValue = v2.sharpenValue;
            v0.curvesToolValue = v2.curvesToolValue;
            v0.blurExcludeSize = v2.blurExcludeSize;
            v0.blurExcludePoint = v2.blurExcludePoint;
            v0.blurExcludeBlurSize = v2.blurExcludeBlurSize;
            v0.blurAngle = v2.blurAngle;
            v0.lastState = v2;
        }
        else {
            v0.curvesToolValue = new CurvesToolValue();
            v0.blurExcludeSize = 0.35f;
            v0.blurExcludePoint = new Point(0.5f, 0.5f);
            v0.blurExcludeBlurSize = 0.15f;
            v0.blurAngle = 1.570796f;
        }

        Bitmap v2_1 = arg24;
        v0.bitmapToEdit = v2_1;
        v0.orientation = arg25;
        v0.textureView = new TextureView(v1);
        int v8 = -1;
        v0.addView(v0.textureView, LayoutHelper.createFrame(v8, v8, 51));
        v0.textureView.setVisibility(v6);
        v0.textureView.setSurfaceTextureListener(new TextureView$SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture arg5, int arg6, int arg7) {
                if(PhotoFilterView.access$5000(PhotoFilterView.this) == null && arg5 != null) {
                    PhotoFilterView.access$5002(PhotoFilterView.this, new EGLThread(PhotoFilterView.this, arg5, PhotoFilterView.access$5100(PhotoFilterView.this)));
                    PhotoFilterView.access$5000(PhotoFilterView.this).setSurfaceTextureSize(arg6, arg7);
                    PhotoFilterView.access$5000(PhotoFilterView.this).requestRender(true, true);
                }
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture arg2) {
                if(PhotoFilterView.access$5000(PhotoFilterView.this) != null) {
                    PhotoFilterView.access$5000(PhotoFilterView.this).shutdown();
                    PhotoFilterView.access$5002(PhotoFilterView.this, null);
                }

                return 1;
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture arg1, int arg2, int arg3) {
                if(PhotoFilterView.access$5000(PhotoFilterView.this) != null) {
                    PhotoFilterView.access$5000(PhotoFilterView.this).setSurfaceTextureSize(arg2, arg3);
                    PhotoFilterView.access$5000(PhotoFilterView.this).requestRender(false, true);
                    PhotoFilterView.access$5000(PhotoFilterView.this).postRunnable(new Runnable() {
                        public void run() {
                            if(PhotoFilterView.access$5000(this.this$1.this$0) != null) {
                                PhotoFilterView.access$5000(this.this$1.this$0).requestRender(false, true);
                            }
                        }
                    });
                }
            }

            public void onSurfaceTextureUpdated(SurfaceTexture arg1) {
            }
        });
        v0.blurControl = new PhotoFilterBlurControl(v1);
        v0.blurControl.setVisibility(v6);
        v0.addView(v0.blurControl, LayoutHelper.createFrame(v8, v8, 51));
        v0.blurControl.setDelegate(new PhotoFilterLinearBlurControlDelegate() {
            public void valueChanged(Point arg2, float arg3, float arg4, float arg5) {
                PhotoFilterView.access$1802(PhotoFilterView.this, arg4);
                PhotoFilterView.access$2002(PhotoFilterView.this, arg2);
                PhotoFilterView.access$1902(PhotoFilterView.this, arg3);
                PhotoFilterView.access$2102(PhotoFilterView.this, arg5);
                if(PhotoFilterView.access$5000(PhotoFilterView.this) != null) {
                    PhotoFilterView.access$5000(PhotoFilterView.this).requestRender(false);
                }
            }
        });
        v0.curvesControl = new PhotoFilterCurvesControl(v1, v0.curvesToolValue);
        v0.curvesControl.setDelegate(new PhotoFilterCurvesControlDelegate() {
            public void valueChanged() {
                if(PhotoFilterView.access$5000(PhotoFilterView.this) != null) {
                    PhotoFilterView.access$5000(PhotoFilterView.this).requestRender(false);
                }
            }
        });
        v0.curvesControl.setVisibility(v6);
        v0.addView(v0.curvesControl, LayoutHelper.createFrame(v8, v8, 51));
        v0.toolsView = new FrameLayout(v1);
        v0.addView(v0.toolsView, LayoutHelper.createFrame(v8, 186, 83));
        FrameLayout v2_2 = new FrameLayout(v1);
        v2_2.setBackgroundColor(-16777216);
        v0.toolsView.addView(((View)v2_2), LayoutHelper.createFrame(v8, 48, 83));
        v0.cancelTextView = new TextView(v1);
        v0.cancelTextView.setTextSize(1, 14f);
        v0.cancelTextView.setTextColor(v8);
        v0.cancelTextView.setGravity(17);
        v0.cancelTextView.setBackgroundDrawable(Theme.createSelectorDrawable(-12763843, 0));
        float v10 = 20f;
        v0.cancelTextView.setPadding(AndroidUtilities.dp(v10), 0, AndroidUtilities.dp(v10), 0);
        v0.cancelTextView.setText(LocaleController.getString("Cancel", 2131624257).toUpperCase());
        v0.cancelTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v2_2.addView(v0.cancelTextView, LayoutHelper.createFrame(-2, v8, 51));
        v0.doneTextView = new TextView(v1);
        v0.doneTextView.setTextSize(1, 14f);
        v0.doneTextView.setTextColor(-11420173);
        v0.doneTextView.setGravity(17);
        v0.doneTextView.setBackgroundDrawable(Theme.createSelectorDrawable(-12763843, 0));
        v0.doneTextView.setPadding(AndroidUtilities.dp(v10), 0, AndroidUtilities.dp(v10), 0);
        v0.doneTextView.setText(LocaleController.getString("Done", 2131624614).toUpperCase());
        v0.doneTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        v2_2.addView(v0.doneTextView, LayoutHelper.createFrame(-2, v8, 53));
        LinearLayout v9 = new LinearLayout(v1);
        v2_2.addView(((View)v9), LayoutHelper.createFrame(-2, v8, 1));
        v0.tuneItem = new ImageView(v1);
        v0.tuneItem.setScaleType(ImageView$ScaleType.CENTER);
        v0.tuneItem.setImageResource(2131231468);
        v0.tuneItem.setColorFilter(new PorterDuffColorFilter(-9649153, PorterDuff$Mode.MULTIPLY));
        v0.tuneItem.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        v9.addView(v0.tuneItem, LayoutHelper.createLinear(56, 48));
        v0.tuneItem.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg4) {
                PhotoFilterView.access$5202(PhotoFilterView.this, 0);
                PhotoFilterView.access$5300(PhotoFilterView.this).setColorFilter(new PorterDuffColorFilter(-9649153, PorterDuff$Mode.MULTIPLY));
                PhotoFilterView.access$5400(PhotoFilterView.this).setColorFilter(null);
                PhotoFilterView.access$5500(PhotoFilterView.this).setColorFilter(null);
                PhotoFilterView.this.switchMode();
            }
        });
        v0.blurItem = new ImageView(v1);
        v0.blurItem.setScaleType(ImageView$ScaleType.CENTER);
        v0.blurItem.setImageResource(2131231660);
        v0.blurItem.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        v9.addView(v0.blurItem, LayoutHelper.createLinear(56, 48));
        v0.blurItem.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg5) {
                PhotoFilterView.access$5202(PhotoFilterView.this, 1);
                PhotoFilterView.access$5300(PhotoFilterView.this).setColorFilter(null);
                PhotoFilterView.access$5400(PhotoFilterView.this).setColorFilter(new PorterDuffColorFilter(-9649153, PorterDuff$Mode.MULTIPLY));
                PhotoFilterView.access$5500(PhotoFilterView.this).setColorFilter(null);
                PhotoFilterView.this.switchMode();
            }
        });
        v0.curveItem = new ImageView(v1);
        v0.curveItem.setScaleType(ImageView$ScaleType.CENTER);
        v0.curveItem.setImageResource(2131231665);
        v0.curveItem.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        v9.addView(v0.curveItem, LayoutHelper.createLinear(56, 48));
        v0.curveItem.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg4) {
                PhotoFilterView.access$5202(PhotoFilterView.this, 2);
                PhotoFilterView.access$5300(PhotoFilterView.this).setColorFilter(null);
                PhotoFilterView.access$5400(PhotoFilterView.this).setColorFilter(null);
                PhotoFilterView.access$5500(PhotoFilterView.this).setColorFilter(new PorterDuffColorFilter(-9649153, PorterDuff$Mode.MULTIPLY));
                PhotoFilterView.this.switchMode();
            }
        });
        v0.recyclerListView = new RecyclerListView(v1);
        LinearLayoutManager v2_3 = new LinearLayoutManager(v1);
        v2_3.setOrientation(1);
        v0.recyclerListView.setLayoutManager(((LayoutManager)v2_3));
        v0.recyclerListView.setClipToPadding(false);
        v0.recyclerListView.setOverScrollMode(v5);
        v0.recyclerListView.setAdapter(new ToolsAdapter(v0, v1));
        v0.toolsView.addView(v0.recyclerListView, LayoutHelper.createFrame(v8, 120, 51));
        v0.curveLayout = new FrameLayout(v1);
        v0.curveLayout.setVisibility(v6);
        v0.toolsView.addView(v0.curveLayout, LayoutHelper.createFrame(-1, 78f, 1, 0f, 40f, 0f, 0f));
        LinearLayout v2_4 = new LinearLayout(v1);
        v2_4.setOrientation(0);
        v0.curveLayout.addView(((View)v2_4), LayoutHelper.createFrame(-2, -2, 1));
        int v7;
        for(v7 = 0; v7 < v6; ++v7) {
            FrameLayout v9_1 = new FrameLayout(v1);
            v9_1.setTag(Integer.valueOf(v7));
            v0.curveRadioButton[v7] = new RadioButton(v1);
            v0.curveRadioButton[v7].setSize(AndroidUtilities.dp(v10));
            v9_1.addView(v0.curveRadioButton[v7], LayoutHelper.createFrame(30, 30, 49));
            TextView v11 = new TextView(v1);
            v11.setTextSize(1, 12f);
            v11.setGravity(16);
            if(v7 == 0) {
                v12 = LocaleController.getString("CurvesAll", 2131624516);
                v11.setText(v12.substring(0, 1).toUpperCase() + v12.substring(1).toLowerCase());
                v11.setTextColor(v8);
                v0.curveRadioButton[v7].setColor(v8, v8);
            }
            else {
                if(v7 == 1) {
                    v12 = LocaleController.getString("CurvesRed", 2131624519);
                    v11.setText(v12.substring(0, 1).toUpperCase() + v12.substring(1).toLowerCase());
                    v12_1 = -1684147;
                    v11.setTextColor(v12_1);
                    v13_1 = v0.curveRadioButton[v7];
                }
                else if(v7 == v5) {
                    v12 = LocaleController.getString("CurvesGreen", 2131624518);
                    v11.setText(v12.substring(0, 1).toUpperCase() + v12.substring(1).toLowerCase());
                    v12_1 = -10831009;
                    v11.setTextColor(v12_1);
                    v13_1 = v0.curveRadioButton[v7];
                }
                else {
                    goto label_412;
                }

                v13_1.setColor(v12_1, v12_1);
                goto label_434;
            label_412:
                if(v7 != 3) {
                    goto label_434;
                }

                v12 = LocaleController.getString("CurvesBlue", 2131624517);
                v11.setText(v12.substring(0, 1).toUpperCase() + v12.substring(1).toLowerCase());
                v11.setTextColor(-12734994);
                v0.curveRadioButton[v7].setColor(-12734994, -12734994);
            }

        label_434:
            v9_1.addView(((View)v11), LayoutHelper.createFrame(-2, -2f, 49, 0f, 38f, 0f, 0f));
            int v13_2 = -2;
            int v14 = -2;
            float v15 = v7 == 0 ? 0f : 30f;
            v2_4.addView(((View)v9_1), LayoutHelper.createLinear(v13_2, v14, v15, 0f, 0f, 0f));
            v9_1.setOnClickListener(new View$OnClickListener() {
                public void onClick(View arg6) {
                    int v6 = arg6.getTag().intValue();
                    PhotoFilterView.access$1600(PhotoFilterView.this).activeType = v6;
                    int v1;
                    for(v1 = 0; v1 < 4; ++v1) {
                        RadioButton v2 = PhotoFilterView.access$5600(PhotoFilterView.this)[v1];
                        boolean v4 = v1 == v6 ? true : false;
                        v2.setChecked(v4, true);
                    }

                    PhotoFilterView.access$5700(PhotoFilterView.this).invalidate();
                }
            });
        }

        v0.blurLayout = new FrameLayout(v1);
        v0.blurLayout.setVisibility(v6);
        v0.toolsView.addView(v0.blurLayout, LayoutHelper.createFrame(280, 60f, 1, 0f, 40f, 0f, 0f));
        v0.blurOffButton = new TextView(v1);
        v0.blurOffButton.setCompoundDrawablePadding(AndroidUtilities.dp(2f));
        v0.blurOffButton.setTextSize(1, 13f);
        v0.blurOffButton.setGravity(1);
        v0.blurOffButton.setText(LocaleController.getString("BlurOff", 2131624209));
        v0.blurLayout.addView(v0.blurOffButton, LayoutHelper.createFrame(80, 60f));
        v0.blurOffButton.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                PhotoFilterView.access$1702(PhotoFilterView.this, 0);
                PhotoFilterView.access$5800(PhotoFilterView.this);
                PhotoFilterView.access$5900(PhotoFilterView.this).setVisibility(4);
                if(PhotoFilterView.access$5000(PhotoFilterView.this) != null) {
                    PhotoFilterView.access$5000(PhotoFilterView.this).requestRender(false);
                }
            }
        });
        v0.blurRadialButton = new TextView(v1);
        v0.blurRadialButton.setCompoundDrawablePadding(AndroidUtilities.dp(2f));
        v0.blurRadialButton.setTextSize(1, 13f);
        v0.blurRadialButton.setGravity(1);
        v0.blurRadialButton.setText(LocaleController.getString("BlurRadial", 2131624210));
        v0.blurLayout.addView(v0.blurRadialButton, LayoutHelper.createFrame(80, 80f, 51, 100f, 0f, 0f, 0f));
        v0.blurRadialButton.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                PhotoFilterView.access$1702(PhotoFilterView.this, 1);
                PhotoFilterView.access$5800(PhotoFilterView.this);
                PhotoFilterView.access$5900(PhotoFilterView.this).setVisibility(0);
                PhotoFilterView.access$5900(PhotoFilterView.this).setType(1);
                if(PhotoFilterView.access$5000(PhotoFilterView.this) != null) {
                    PhotoFilterView.access$5000(PhotoFilterView.this).requestRender(false);
                }
            }
        });
        v0.blurLinearButton = new TextView(v1);
        v0.blurLinearButton.setCompoundDrawablePadding(AndroidUtilities.dp(2f));
        v0.blurLinearButton.setTextSize(1, 13f);
        v0.blurLinearButton.setGravity(1);
        v0.blurLinearButton.setText(LocaleController.getString("BlurLinear", 2131624208));
        v0.blurLayout.addView(v0.blurLinearButton, LayoutHelper.createFrame(80, 80f, 51, 200f, 0f, 0f, 0f));
        v0.blurLinearButton.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg2) {
                PhotoFilterView.access$1702(PhotoFilterView.this, 2);
                PhotoFilterView.access$5800(PhotoFilterView.this);
                PhotoFilterView.access$5900(PhotoFilterView.this).setVisibility(0);
                PhotoFilterView.access$5900(PhotoFilterView.this).setType(0);
                if(PhotoFilterView.access$5000(PhotoFilterView.this) != null) {
                    PhotoFilterView.access$5000(PhotoFilterView.this).requestRender(false);
                }
            }
        });
        this.updateSelectedBlurType();
        if(Build$VERSION.SDK_INT >= 21) {
            v0.textureView.getLayoutParams().topMargin = AndroidUtilities.statusBarHeight;
            v0.curvesControl.getLayoutParams().topMargin = AndroidUtilities.statusBarHeight;
        }
    }

    static boolean access$000(PhotoFilterView arg0) {
        return arg0.showOriginal;
    }

    static float access$100(PhotoFilterView arg0) {
        return arg0.getEnhanceValue();
    }

    static float access$1000(PhotoFilterView arg0) {
        return arg0.getGrainValue();
    }

    static float access$1100(PhotoFilterView arg0) {
        return arg0.getFadeValue();
    }

    static int access$1200(PhotoFilterView arg0) {
        return arg0.tintHighlightsColor;
    }

    static int access$1202(PhotoFilterView arg0, int arg1) {
        arg0.tintHighlightsColor = arg1;
        return arg1;
    }

    static float access$1300(PhotoFilterView arg0) {
        return arg0.getTintHighlightsIntensityValue();
    }

    static int access$1400(PhotoFilterView arg0) {
        return arg0.tintShadowsColor;
    }

    static int access$1402(PhotoFilterView arg0, int arg1) {
        arg0.tintShadowsColor = arg1;
        return arg1;
    }

    static float access$1500(PhotoFilterView arg0) {
        return arg0.getTintShadowsIntensityValue();
    }

    static CurvesToolValue access$1600(PhotoFilterView arg0) {
        return arg0.curvesToolValue;
    }

    static int access$1700(PhotoFilterView arg0) {
        return arg0.blurType;
    }

    static int access$1702(PhotoFilterView arg0, int arg1) {
        arg0.blurType = arg1;
        return arg1;
    }

    static float access$1800(PhotoFilterView arg0) {
        return arg0.blurExcludeSize;
    }

    static float access$1802(PhotoFilterView arg0, float arg1) {
        arg0.blurExcludeSize = arg1;
        return arg1;
    }

    static float access$1900(PhotoFilterView arg0) {
        return arg0.blurExcludeBlurSize;
    }

    static float access$1902(PhotoFilterView arg0, float arg1) {
        arg0.blurExcludeBlurSize = arg1;
        return arg1;
    }

    static float access$200(PhotoFilterView arg0) {
        return arg0.getSharpenValue();
    }

    static Point access$2000(PhotoFilterView arg0) {
        return arg0.blurExcludePoint;
    }

    static Point access$2002(PhotoFilterView arg0, Point arg1) {
        arg0.blurExcludePoint = arg1;
        return arg1;
    }

    static float access$2100(PhotoFilterView arg0) {
        return arg0.blurAngle;
    }

    static float access$2102(PhotoFilterView arg0, float arg1) {
        arg0.blurAngle = arg1;
        return arg1;
    }

    static float access$300(PhotoFilterView arg0) {
        return arg0.getShadowsValue();
    }

    static float access$400(PhotoFilterView arg0) {
        return arg0.getHighlightsValue();
    }

    static int access$4500(PhotoFilterView arg0) {
        return arg0.orientation;
    }

    static float access$500(PhotoFilterView arg0) {
        return arg0.getExposureValue();
    }

    static EGLThread access$5000(PhotoFilterView arg0) {
        return arg0.eglThread;
    }

    static EGLThread access$5002(PhotoFilterView arg0, EGLThread arg1) {
        arg0.eglThread = arg1;
        return arg1;
    }

    static Bitmap access$5100(PhotoFilterView arg0) {
        return arg0.bitmapToEdit;
    }

    static int access$5202(PhotoFilterView arg0, int arg1) {
        arg0.selectedTool = arg1;
        return arg1;
    }

    static ImageView access$5300(PhotoFilterView arg0) {
        return arg0.tuneItem;
    }

    static ImageView access$5400(PhotoFilterView arg0) {
        return arg0.blurItem;
    }

    static ImageView access$5500(PhotoFilterView arg0) {
        return arg0.curveItem;
    }

    static RadioButton[] access$5600(PhotoFilterView arg0) {
        return arg0.curveRadioButton;
    }

    static PhotoFilterCurvesControl access$5700(PhotoFilterView arg0) {
        return arg0.curvesControl;
    }

    static void access$5800(PhotoFilterView arg0) {
        arg0.updateSelectedBlurType();
    }

    static PhotoFilterBlurControl access$5900(PhotoFilterView arg0) {
        return arg0.blurControl;
    }

    static float access$600(PhotoFilterView arg0) {
        return arg0.getContrastValue();
    }

    static int access$6000(PhotoFilterView arg0) {
        return arg0.enhanceTool;
    }

    static float access$6100(PhotoFilterView arg0) {
        return arg0.enhanceValue;
    }

    static float access$6102(PhotoFilterView arg0, float arg1) {
        arg0.enhanceValue = arg1;
        return arg1;
    }

    static int access$6200(PhotoFilterView arg0) {
        return arg0.highlightsTool;
    }

    static float access$6300(PhotoFilterView arg0) {
        return arg0.highlightsValue;
    }

    static float access$6302(PhotoFilterView arg0, float arg1) {
        arg0.highlightsValue = arg1;
        return arg1;
    }

    static int access$6400(PhotoFilterView arg0) {
        return arg0.contrastTool;
    }

    static float access$6500(PhotoFilterView arg0) {
        return arg0.contrastValue;
    }

    static float access$6502(PhotoFilterView arg0, float arg1) {
        arg0.contrastValue = arg1;
        return arg1;
    }

    static int access$6600(PhotoFilterView arg0) {
        return arg0.exposureTool;
    }

    static float access$6700(PhotoFilterView arg0) {
        return arg0.exposureValue;
    }

    static float access$6702(PhotoFilterView arg0, float arg1) {
        arg0.exposureValue = arg1;
        return arg1;
    }

    static int access$6800(PhotoFilterView arg0) {
        return arg0.warmthTool;
    }

    static float access$6900(PhotoFilterView arg0) {
        return arg0.warmthValue;
    }

    static float access$6902(PhotoFilterView arg0, float arg1) {
        arg0.warmthValue = arg1;
        return arg1;
    }

    static float access$700(PhotoFilterView arg0) {
        return arg0.getSaturationValue();
    }

    static int access$7000(PhotoFilterView arg0) {
        return arg0.saturationTool;
    }

    static float access$7100(PhotoFilterView arg0) {
        return arg0.saturationValue;
    }

    static float access$7102(PhotoFilterView arg0, float arg1) {
        arg0.saturationValue = arg1;
        return arg1;
    }

    static int access$7200(PhotoFilterView arg0) {
        return arg0.vignetteTool;
    }

    static float access$7300(PhotoFilterView arg0) {
        return arg0.vignetteValue;
    }

    static float access$7302(PhotoFilterView arg0, float arg1) {
        arg0.vignetteValue = arg1;
        return arg1;
    }

    static int access$7400(PhotoFilterView arg0) {
        return arg0.shadowsTool;
    }

    static float access$7500(PhotoFilterView arg0) {
        return arg0.shadowsValue;
    }

    static float access$7502(PhotoFilterView arg0, float arg1) {
        arg0.shadowsValue = arg1;
        return arg1;
    }

    static int access$7600(PhotoFilterView arg0) {
        return arg0.grainTool;
    }

    static float access$7700(PhotoFilterView arg0) {
        return arg0.grainValue;
    }

    static float access$7702(PhotoFilterView arg0, float arg1) {
        arg0.grainValue = arg1;
        return arg1;
    }

    static int access$7800(PhotoFilterView arg0) {
        return arg0.sharpenTool;
    }

    static float access$7900(PhotoFilterView arg0) {
        return arg0.sharpenValue;
    }

    static float access$7902(PhotoFilterView arg0, float arg1) {
        arg0.sharpenValue = arg1;
        return arg1;
    }

    static float access$800(PhotoFilterView arg0) {
        return arg0.getWarmthValue();
    }

    static int access$8000(PhotoFilterView arg0) {
        return arg0.fadeTool;
    }

    static float access$8100(PhotoFilterView arg0) {
        return arg0.fadeValue;
    }

    static float access$8102(PhotoFilterView arg0, float arg1) {
        arg0.fadeValue = arg1;
        return arg1;
    }

    static int access$8200(PhotoFilterView arg0) {
        return arg0.tintShadowsTool;
    }

    static int access$8300(PhotoFilterView arg0) {
        return arg0.tintHighlightsTool;
    }

    static float access$900(PhotoFilterView arg0) {
        return arg0.getVignetteValue();
    }

    private void fixLayout(int arg12, int arg13) {
        float v1_1;
        if(this.bitmapToEdit == null) {
            return;
        }

        float v0 = 28f;
        arg12 -= AndroidUtilities.dp(v0);
        int v1 = AndroidUtilities.dp(214f);
        int v3 = 21;
        int v2 = Build$VERSION.SDK_INT >= v3 ? AndroidUtilities.statusBarHeight : 0;
        arg13 -= v1 + v2;
        if(this.orientation % 360 == 90 || this.orientation % 360 == 270) {
            v1_1 = ((float)this.bitmapToEdit.getHeight());
            v2 = this.bitmapToEdit.getWidth();
        }
        else {
            v1_1 = ((float)this.bitmapToEdit.getWidth());
            v2 = this.bitmapToEdit.getHeight();
        }

        float v2_1 = ((float)v2);
        float v5 = ((float)arg12);
        float v6 = v5 / v1_1;
        float v7 = ((float)arg13);
        float v8 = v7 / v2_1;
        if(v6 > v8) {
            v1_1 = ((float)(((int)Math.ceil(((double)(v1_1 * v8))))));
            v2_1 = v7;
        }
        else {
            v2_1 = ((float)(((int)Math.ceil(((double)(v2_1 * v6))))));
            v1_1 = v5;
        }

        int v5_1 = ((int)Math.ceil(((double)((v5 - v1_1) / 2f + (((float)AndroidUtilities.dp(14f)))))));
        v7 = (v7 - v2_1) / 2f + (((float)AndroidUtilities.dp(14f)));
        int v6_1 = Build$VERSION.SDK_INT >= v3 ? AndroidUtilities.statusBarHeight : 0;
        v6_1 = ((int)Math.ceil(((double)(v7 + (((float)v6_1))))));
        ViewGroup$LayoutParams v7_1 = this.textureView.getLayoutParams();
        ((FrameLayout$LayoutParams)v7_1).leftMargin = v5_1;
        ((FrameLayout$LayoutParams)v7_1).topMargin = v6_1;
        ((FrameLayout$LayoutParams)v7_1).width = ((int)v1_1);
        ((FrameLayout$LayoutParams)v7_1).height = ((int)v2_1);
        PhotoFilterCurvesControl v1_2 = this.curvesControl;
        v2_1 = ((float)v5_1);
        v3 = Build$VERSION.SDK_INT >= v3 ? AndroidUtilities.statusBarHeight : 0;
        v1_2.setActualArea(v2_1, ((float)(v6_1 - v3)), ((float)((FrameLayout$LayoutParams)v7_1).width), ((float)((FrameLayout$LayoutParams)v7_1).height));
        this.blurControl.setActualAreaSize(((float)((FrameLayout$LayoutParams)v7_1).width), ((float)((FrameLayout$LayoutParams)v7_1).height));
        this.blurControl.getLayoutParams().height = AndroidUtilities.dp(38f) + arg13;
        this.curvesControl.getLayoutParams().height = arg13 + AndroidUtilities.dp(v0);
        if(AndroidUtilities.isTablet()) {
            arg13 = AndroidUtilities.dp(86f) * 10;
            ViewGroup$LayoutParams v0_1 = this.recyclerListView.getLayoutParams();
            if(arg13 < arg12) {
                ((FrameLayout$LayoutParams)v0_1).width = arg13;
                ((FrameLayout$LayoutParams)v0_1).leftMargin = (arg12 - arg13) / 2;
            }
            else {
                ((FrameLayout$LayoutParams)v0_1).width = -1;
                ((FrameLayout$LayoutParams)v0_1).leftMargin = 0;
            }
        }
    }

    public Bitmap getBitmap() {
        Bitmap v0 = this.eglThread != null ? this.eglThread.getTexture() : null;
        return v0;
    }

    public TextView getCancelTextView() {
        return this.cancelTextView;
    }

    private float getContrastValue() {
        return this.contrastValue / 100f * 0.3f + 1f;
    }

    public TextView getDoneTextView() {
        return this.doneTextView;
    }

    private float getEnhanceValue() {
        return this.enhanceValue / 100f;
    }

    private float getExposureValue() {
        return this.exposureValue / 100f;
    }

    private float getFadeValue() {
        return this.fadeValue / 100f;
    }

    private float getGrainValue() {
        return this.grainValue / 100f * 0.04f;
    }

    private float getHighlightsValue() {
        return (this.highlightsValue * 0.75f + 100f) / 100f;
    }

    private float getSaturationValue() {
        float v0 = this.saturationValue / 100f;
        if(v0 > 0f) {
            v0 *= 1.05f;
        }

        return v0 + 1f;
    }

    public SavedFilterState getSavedFilterState() {
        SavedFilterState v0 = new SavedFilterState();
        v0.enhanceValue = this.enhanceValue;
        v0.exposureValue = this.exposureValue;
        v0.contrastValue = this.contrastValue;
        v0.warmthValue = this.warmthValue;
        v0.saturationValue = this.saturationValue;
        v0.fadeValue = this.fadeValue;
        v0.tintShadowsColor = this.tintShadowsColor;
        v0.tintHighlightsColor = this.tintHighlightsColor;
        v0.highlightsValue = this.highlightsValue;
        v0.shadowsValue = this.shadowsValue;
        v0.vignetteValue = this.vignetteValue;
        v0.grainValue = this.grainValue;
        v0.blurType = this.blurType;
        v0.sharpenValue = this.sharpenValue;
        v0.curvesToolValue = this.curvesToolValue;
        v0.blurExcludeSize = this.blurExcludeSize;
        v0.blurExcludePoint = this.blurExcludePoint;
        v0.blurExcludeBlurSize = this.blurExcludeBlurSize;
        v0.blurAngle = this.blurAngle;
        return v0;
    }

    private float getShadowsValue() {
        return (this.shadowsValue * 0.55f + 100f) / 100f;
    }

    private float getSharpenValue() {
        return this.sharpenValue / 100f * 0.6f + 0.11f;
    }

    private float getTintHighlightsIntensityValue() {
        float v0 = this.tintHighlightsColor == 0 ? 0f : 0.5f;
        return v0;
    }

    private float getTintShadowsIntensityValue() {
        float v0 = this.tintShadowsColor == 0 ? 0f : 0.5f;
        return v0;
    }

    public FrameLayout getToolsView() {
        return this.toolsView;
    }

    private float getVignetteValue() {
        return this.vignetteValue / 100f;
    }

    private float getWarmthValue() {
        return this.warmthValue / 100f;
    }

    public boolean hasChanges() {
        boolean v1 = false;
        if(this.lastState != null) {
            if(this.enhanceValue != this.lastState.enhanceValue || this.contrastValue != this.lastState.contrastValue || this.highlightsValue != this.lastState.highlightsValue || this.exposureValue != this.lastState.exposureValue || this.warmthValue != this.lastState.warmthValue || this.saturationValue != this.lastState.saturationValue || this.vignetteValue != this.lastState.vignetteValue || this.shadowsValue != this.lastState.shadowsValue || this.grainValue != this.lastState.grainValue || this.sharpenValue != this.lastState.sharpenValue || this.fadeValue != this.lastState.fadeValue || this.tintHighlightsColor != this.lastState.tintHighlightsColor || this.tintShadowsColor != this.lastState.tintShadowsColor || !this.curvesToolValue.shouldBeSkipped()) {
                v1 = true;
            }

            return v1;
        }

        if(this.enhanceValue != 0f || this.contrastValue != 0f || this.highlightsValue != 0f || this.exposureValue != 0f || this.warmthValue != 0f || this.saturationValue != 0f || this.vignetteValue != 0f || this.shadowsValue != 0f || this.grainValue != 0f || this.sharpenValue != 0f || this.fadeValue != 0f || this.tintHighlightsColor != 0 || this.tintShadowsColor != 0 || !this.curvesToolValue.shouldBeSkipped()) {
            v1 = true;
        }

        return v1;
    }

    public void init() {
        this.textureView.setVisibility(0);
    }

    protected void onMeasure(int arg3, int arg4) {
        this.fixLayout(View$MeasureSpec.getSize(arg3), View$MeasureSpec.getSize(arg4));
        super.onMeasure(arg3, arg4);
    }

    public void onTouch(MotionEvent arg6) {
        if(arg6.getActionMasked() == 0 || arg6.getActionMasked() == 5) {
            ViewGroup$LayoutParams v0 = this.textureView.getLayoutParams();
            if(v0 == null) {
                return;
            }

            if(arg6.getX() < (((float)((FrameLayout$LayoutParams)v0).leftMargin))) {
                return;
            }

            if(arg6.getY() < (((float)((FrameLayout$LayoutParams)v0).topMargin))) {
                return;
            }

            if(arg6.getX() > (((float)(((FrameLayout$LayoutParams)v0).leftMargin + ((FrameLayout$LayoutParams)v0).width)))) {
                return;
            }

            if(arg6.getY() > (((float)(((FrameLayout$LayoutParams)v0).topMargin + ((FrameLayout$LayoutParams)v0).height)))) {
                return;
            }

            this.setShowOriginal(true);
        }
        else {
            if(arg6.getActionMasked() != 1 && arg6.getActionMasked() != 6) {
                return;
            }

            this.setShowOriginal(false);
        }
    }

    private void setShowOriginal(boolean arg2) {
        if(this.showOriginal == arg2) {
            return;
        }

        this.showOriginal = arg2;
        if(this.eglThread != null) {
            this.eglThread.requestRender(false);
        }
    }

    public void shutdown() {
        if(this.eglThread != null) {
            this.eglThread.shutdown();
            this.eglThread = null;
        }

        this.textureView.setVisibility(8);
    }

    public void switchMode() {
        int v2 = 4;
        if(this.selectedTool == 0) {
            this.blurControl.setVisibility(v2);
            this.blurLayout.setVisibility(v2);
            this.curveLayout.setVisibility(v2);
            this.curvesControl.setVisibility(v2);
            this.recyclerListView.setVisibility(0);
        }
        else if(this.selectedTool == 1) {
            this.recyclerListView.setVisibility(v2);
            this.curveLayout.setVisibility(v2);
            this.curvesControl.setVisibility(v2);
            this.blurLayout.setVisibility(0);
            if(this.blurType != 0) {
                this.blurControl.setVisibility(0);
            }

            this.updateSelectedBlurType();
        }
        else {
            if(this.selectedTool != 2) {
                return;
            }

            this.recyclerListView.setVisibility(v2);
            this.blurLayout.setVisibility(v2);
            this.blurControl.setVisibility(v2);
            this.curveLayout.setVisibility(0);
            this.curvesControl.setVisibility(0);
            this.curvesToolValue.activeType = 0;
            int v0;
            for(v0 = 0; v0 < v2; ++v0) {
                RadioButton v4 = this.curveRadioButton[v0];
                boolean v5 = v0 == 0 ? true : false;
                v4.setChecked(v5, false);
            }
        }
    }

    private void updateSelectedBlurType() {
        Drawable v0;
        int v1 = 2131230931;
        int v2 = 2131230933;
        int v3 = 2131230932;
        int v4 = -1;
        int v5 = -11420173;
        Drawable v6 = null;
        if(this.blurType == 0) {
            v0 = this.blurOffButton.getContext().getResources().getDrawable(v3).mutate();
            v0.setColorFilter(new PorterDuffColorFilter(v5, PorterDuff$Mode.MULTIPLY));
            this.blurOffButton.setCompoundDrawablesWithIntrinsicBounds(v6, v0, v6, v6);
            this.blurOffButton.setTextColor(v5);
            this.blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(0, v2, 0, 0);
            this.blurRadialButton.setTextColor(v4);
            goto label_26;
        }
        else if(this.blurType == 1) {
            this.blurOffButton.setCompoundDrawablesWithIntrinsicBounds(0, v3, 0, 0);
            this.blurOffButton.setTextColor(v4);
            v0 = this.blurOffButton.getContext().getResources().getDrawable(v2).mutate();
            v0.setColorFilter(new PorterDuffColorFilter(v5, PorterDuff$Mode.MULTIPLY));
            this.blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(v6, v0, v6, v6);
            this.blurRadialButton.setTextColor(v5);
        label_26:
            this.blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(0, v1, 0, 0);
            this.blurLinearButton.setTextColor(v4);
        }
        else if(this.blurType == 2) {
            this.blurOffButton.setCompoundDrawablesWithIntrinsicBounds(0, v3, 0, 0);
            this.blurOffButton.setTextColor(v4);
            this.blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(0, v2, 0, 0);
            this.blurRadialButton.setTextColor(v4);
            v0 = this.blurOffButton.getContext().getResources().getDrawable(v1).mutate();
            v0.setColorFilter(new PorterDuffColorFilter(v5, PorterDuff$Mode.MULTIPLY));
            this.blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(v6, v0, v6, v6);
            this.blurLinearButton.setTextColor(v5);
        }
    }
}

