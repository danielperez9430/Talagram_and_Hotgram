package org.telegram.messenger.video;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TextureRenderer {
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
    private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 20;
    private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
    private static final String VERTEX_SHADER = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private float[] mMVPMatrix;
    private int mProgram;
    private float[] mSTMatrix;
    private int mTextureID;
    private FloatBuffer mTriangleVertices;
    private int maPositionHandle;
    private int maTextureHandle;
    private int muMVPMatrixHandle;
    private int muSTMatrixHandle;
    private int rotationAngle;

    public TextureRenderer(int arg3) {
        super();
        this.mMVPMatrix = new float[16];
        this.mSTMatrix = new float[16];
        this.mTextureID = -12345;
        this.rotationAngle = arg3;
        float[] v3 = new float[]{-1f, -1f, 0f, 0f, 0f, 1f, -1f, 0f, 1f, 0f, -1f, 1f, 0f, 0f, 1f, 1f, 1f, 0f, 1f, 1f};
        this.mTriangleVertices = ByteBuffer.allocateDirect(v3.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mTriangleVertices.put(v3).position(0);
        Matrix.setIdentityM(this.mSTMatrix, 0);
    }

    public void checkGlError(String arg4) {
        int v0 = GLES20.glGetError();
        if(v0 == 0) {
            return;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append(arg4);
        v2.append(": glError ");
        v2.append(v0);
        throw new RuntimeException(v2.toString());
    }

    private int createProgram(String arg4, String arg5) {
        int v4 = this.loadShader(35633, arg4);
        int v0 = 0;
        if(v4 == 0) {
            return 0;
        }

        int v5 = this.loadShader(35632, arg5);
        if(v5 == 0) {
            return 0;
        }

        int v1 = GLES20.glCreateProgram();
        this.checkGlError("glCreateProgram");
        if(v1 == 0) {
            return 0;
        }

        GLES20.glAttachShader(v1, v4);
        this.checkGlError("glAttachShader");
        GLES20.glAttachShader(v1, v5);
        this.checkGlError("glAttachShader");
        GLES20.glLinkProgram(v1);
        int[] v5_1 = new int[1];
        GLES20.glGetProgramiv(v1, 35714, v5_1, 0);
        if(v5_1[0] != 1) {
            GLES20.glDeleteProgram(v1);
        }
        else {
            v0 = v1;
        }

        return v0;
    }

    public void drawFrame(SurfaceTexture arg9, boolean arg10) {
        this.checkGlError("onDrawFrame start");
        arg9.getTransformMatrix(this.mSTMatrix);
        int v9 = 5;
        if(arg10) {
            this.mSTMatrix[v9] = -this.mSTMatrix[v9];
            this.mSTMatrix[13] = 1f - this.mSTMatrix[13];
        }

        GLES20.glUseProgram(this.mProgram);
        this.checkGlError("glUseProgram");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.mTextureID);
        this.mTriangleVertices.position(0);
        GLES20.glVertexAttribPointer(this.maPositionHandle, 3, 5126, false, 20, this.mTriangleVertices);
        this.checkGlError("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.maPositionHandle);
        this.checkGlError("glEnableVertexAttribArray maPositionHandle");
        this.mTriangleVertices.position(3);
        GLES20.glVertexAttribPointer(this.maTextureHandle, 2, 5126, false, 20, this.mTriangleVertices);
        this.checkGlError("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.maTextureHandle);
        this.checkGlError("glEnableVertexAttribArray maTextureHandle");
        GLES20.glUniformMatrix4fv(this.muSTMatrixHandle, 1, false, this.mSTMatrix, 0);
        GLES20.glUniformMatrix4fv(this.muMVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glDrawArrays(v9, 0, 4);
        this.checkGlError("glDrawArrays");
        GLES20.glFinish();
    }

    public int getTextureId() {
        return this.mTextureID;
    }

    private int loadShader(int arg4, String arg5) {
        int v0 = GLES20.glCreateShader(arg4);
        this.checkGlError("glCreateShader type=" + arg4);
        GLES20.glShaderSource(v0, arg5);
        GLES20.glCompileShader(v0);
        int[] v4 = new int[1];
        int v5 = 0;
        GLES20.glGetShaderiv(v0, 35713, v4, 0);
        if(v4[0] == 0) {
            GLES20.glDeleteShader(v0);
        }
        else {
            v5 = v0;
        }

        return v5;
    }

    public void surfaceCreated() {
        this.mProgram = this.createProgram("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        if(this.mProgram != 0) {
            this.maPositionHandle = GLES20.glGetAttribLocation(this.mProgram, "aPosition");
            this.checkGlError("glGetAttribLocation aPosition");
            int v1 = -1;
            if(this.maPositionHandle != v1) {
                this.maTextureHandle = GLES20.glGetAttribLocation(this.mProgram, "aTextureCoord");
                this.checkGlError("glGetAttribLocation aTextureCoord");
                if(this.maTextureHandle != v1) {
                    this.muMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix");
                    this.checkGlError("glGetUniformLocation uMVPMatrix");
                    if(this.muMVPMatrixHandle != v1) {
                        this.muSTMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uSTMatrix");
                        this.checkGlError("glGetUniformLocation uSTMatrix");
                        if(this.muSTMatrixHandle != v1) {
                            int[] v1_1 = new int[1];
                            GLES20.glGenTextures(1, v1_1, 0);
                            this.mTextureID = v1_1[0];
                            GLES20.glBindTexture(36197, this.mTextureID);
                            this.checkGlError("glBindTexture mTextureID");
                            GLES20.glTexParameteri(36197, 10241, 9729);
                            GLES20.glTexParameteri(36197, 10240, 9729);
                            GLES20.glTexParameteri(36197, 10242, 33071);
                            GLES20.glTexParameteri(36197, 10243, 33071);
                            this.checkGlError("glTexParameter");
                            Matrix.setIdentityM(this.mMVPMatrix, 0);
                            if(this.rotationAngle != 0) {
                                Matrix.rotateM(this.mMVPMatrix, 0, ((float)this.rotationAngle), 0f, 0f, 1f);
                            }

                            return;
                        }

                        throw new RuntimeException("Could not get attrib location for uSTMatrix");
                    }

                    throw new RuntimeException("Could not get attrib location for uMVPMatrix");
                }

                throw new RuntimeException("Could not get attrib location for aTextureCoord");
            }

            throw new RuntimeException("Could not get attrib location for aPosition");
        }

        throw new RuntimeException("failed creating program");
    }
}

