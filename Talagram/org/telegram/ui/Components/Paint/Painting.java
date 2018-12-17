package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.opengl.GLES20;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.telegram.messenger.DispatchQueue;
import org.telegram.ui.Components.Size;

public class Painting {
    public class PaintingData {
        public Bitmap bitmap;
        public ByteBuffer data;

        PaintingData(Painting arg1, Bitmap arg2, ByteBuffer arg3) {
            Painting.this = arg1;
            super();
            this.bitmap = arg2;
            this.data = arg3;
        }
    }

    public interface PaintingDelegate {
        void contentChanged(RectF arg1);

        DispatchQueue requestDispatchQueue();

        UndoStore requestUndoStore();

        void strokeCommited();
    }

    private Path activePath;
    private RectF activeStrokeBounds;
    private Slice backupSlice;
    private Texture bitmapTexture;
    private Brush brush;
    private Texture brushTexture;
    private int[] buffers;
    private ByteBuffer dataBuffer;
    private PaintingDelegate delegate;
    private int paintTexture;
    private boolean paused;
    private float[] projection;
    private float[] renderProjection;
    private RenderState renderState;
    private RenderView renderView;
    private int reusableFramebuffer;
    private Map shaders;
    private Size size;
    private int suppressChangesCounter;
    private ByteBuffer textureBuffer;
    private ByteBuffer vertexBuffer;

    public Painting(Size arg7) {
        super();
        this.buffers = new int[1];
        this.renderState = new RenderState();
        this.size = arg7;
        this.dataBuffer = ByteBuffer.allocateDirect((((int)this.size.width)) * (((int)this.size.height)) * 4);
        this.projection = GLMatrix.LoadOrtho(0f, this.size.width, 0f, this.size.height, -1f, 1f);
        int v0 = 32;
        if(this.vertexBuffer == null) {
            this.vertexBuffer = ByteBuffer.allocateDirect(v0);
            this.vertexBuffer.order(ByteOrder.nativeOrder());
        }

        this.vertexBuffer.putFloat(0f);
        this.vertexBuffer.putFloat(0f);
        this.vertexBuffer.putFloat(this.size.width);
        this.vertexBuffer.putFloat(0f);
        this.vertexBuffer.putFloat(0f);
        this.vertexBuffer.putFloat(this.size.height);
        this.vertexBuffer.putFloat(this.size.width);
        this.vertexBuffer.putFloat(this.size.height);
        this.vertexBuffer.rewind();
        if(this.textureBuffer == null) {
            this.textureBuffer = ByteBuffer.allocateDirect(v0);
            this.textureBuffer.order(ByteOrder.nativeOrder());
            this.textureBuffer.putFloat(0f);
            this.textureBuffer.putFloat(0f);
            this.textureBuffer.putFloat(1f);
            this.textureBuffer.putFloat(0f);
            this.textureBuffer.putFloat(0f);
            this.textureBuffer.putFloat(1f);
            this.textureBuffer.putFloat(1f);
            this.textureBuffer.putFloat(1f);
            this.textureBuffer.rewind();
        }
    }

    static Path access$002(Painting arg0, Path arg1) {
        arg0.activePath = arg1;
        return arg1;
    }

    static int access$100(Painting arg0) {
        return arg0.getReusableFramebuffer();
    }

    static RectF access$1000(Painting arg0) {
        return arg0.activeStrokeBounds;
    }

    static RectF access$1002(Painting arg0, RectF arg1) {
        arg0.activeStrokeBounds = arg1;
        return arg1;
    }

    static void access$1100(Painting arg0, RectF arg1) {
        arg0.registerUndo(arg1);
    }

    static void access$1200(Painting arg0) {
        arg0.beginSuppressingChanges();
    }

    static ByteBuffer access$1300(Painting arg0) {
        return arg0.vertexBuffer;
    }

    static ByteBuffer access$1400(Painting arg0) {
        return arg0.textureBuffer;
    }

    static void access$1500(Painting arg0, RectF arg1, Runnable arg2) {
        arg0.update(arg1, arg2);
    }

    static void access$1600(Painting arg0) {
        arg0.endSuppressingChanges();
    }

    static void access$1700(Painting arg0, Slice arg1) {
        arg0.restoreSlice(arg1);
    }

    static int access$1800(Painting arg0) {
        return arg0.getTexture();
    }

    static boolean access$1900(Painting arg0) {
        return arg0.isSuppressingChanges();
    }

    static int access$200(Painting arg0) {
        return arg0.getPaintTexture();
    }

    static boolean access$2002(Painting arg0, boolean arg1) {
        arg0.paused = arg1;
        return arg1;
    }

    static Slice access$2102(Painting arg0, Slice arg1) {
        arg0.backupSlice = arg1;
        return arg1;
    }

    static Size access$300(Painting arg0) {
        return arg0.size;
    }

    static Map access$400(Painting arg0) {
        return arg0.shaders;
    }

    static Brush access$500(Painting arg0) {
        return arg0.brush;
    }

    static Texture access$600(Painting arg0) {
        return arg0.brushTexture;
    }

    static Texture access$602(Painting arg0, Texture arg1) {
        arg0.brushTexture = arg1;
        return arg1;
    }

    static float[] access$700(Painting arg0) {
        return arg0.projection;
    }

    static RenderState access$800(Painting arg0) {
        return arg0.renderState;
    }

    static PaintingDelegate access$900(Painting arg0) {
        return arg0.delegate;
    }

    private void beginSuppressingChanges() {
        ++this.suppressChangesCounter;
    }

    public void cleanResources(boolean arg5) {
        if(this.reusableFramebuffer != 0) {
            this.buffers[0] = this.reusableFramebuffer;
            GLES20.glDeleteFramebuffers(1, this.buffers, 0);
            this.reusableFramebuffer = 0;
        }

        this.bitmapTexture.cleanResources(arg5);
        if(this.paintTexture != 0) {
            this.buffers[0] = this.paintTexture;
            GLES20.glDeleteTextures(1, this.buffers, 0);
            this.paintTexture = 0;
        }

        Texture v0 = null;
        if(this.brushTexture != null) {
            this.brushTexture.cleanResources(true);
            this.brushTexture = v0;
        }

        if(this.shaders != null) {
            Iterator v5 = this.shaders.values().iterator();
            while(v5.hasNext()) {
                v5.next().cleanResources();
            }

            this.shaders = ((Map)v0);
        }
    }

    public void commitStroke(int arg3) {
        this.renderView.performInContext(new Runnable(arg3) {
            public void run() {
                Painting.this.registerUndo(Painting.this.activeStrokeBounds);
                Painting.this.beginSuppressingChanges();
                Painting.this.update(null, new Runnable() {
                    public void run() {
                        org.telegram.ui.Components.Paint.Painting$2$1 v0 = this;
                        if(v0.this$1.this$0.shaders == null) {
                            return;
                        }

                        Map v1 = v0.this$1.this$0.shaders;
                        String v2 = v0.this$1.this$0.brush.isLightSaber() ? "compositeWithMaskLight" : "compositeWithMask";
                        Object v1_1 = v1.get(v2);
                        if(v1_1 == null) {
                            return;
                        }

                        GLES20.glUseProgram(((Shader)v1_1).program);
                        GLES20.glUniformMatrix4fv(((Shader)v1_1).getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(v0.this$1.this$0.projection));
                        GLES20.glUniform1i(((Shader)v1_1).getUniform("mask"), 0);
                        Shader.SetColorUniform(((Shader)v1_1).getUniform("color"), v0.this$1.val$color);
                        GLES20.glActiveTexture(33984);
                        GLES20.glBindTexture(3553, v0.this$1.this$0.getPaintTexture());
                        GLES20.glBlendFuncSeparate(770, 771, 770, 1);
                        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, v0.this$1.this$0.vertexBuffer);
                        GLES20.glEnableVertexAttribArray(0);
                        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, v0.this$1.this$0.textureBuffer);
                        GLES20.glEnableVertexAttribArray(1);
                        GLES20.glDrawArrays(5, 0, 4);
                    }
                });
                Painting.this.endSuppressingChanges();
                Painting.this.renderState.reset();
                Painting.this.activeStrokeBounds = null;
                Painting.this.activePath = null;
            }
        });
    }

    private void endSuppressingChanges() {
        --this.suppressChangesCounter;
    }

    public RectF getBounds() {
        return new RectF(0f, 0f, this.size.width, this.size.height);
    }

    private int getPaintTexture() {
        if(this.paintTexture == 0) {
            this.paintTexture = Texture.generateTexture(this.size);
        }

        return this.paintTexture;
    }

    public PaintingData getPaintingData(RectF arg28, boolean arg29) {
        PaintingData v2;
        Painting v0 = this;
        int v3 = ((int)arg28.left);
        int v4 = ((int)arg28.top);
        int v5 = ((int)arg28.width());
        int v1 = ((int)arg28.height());
        GLES20.glGenFramebuffers(1, v0.buffers, 0);
        int v13 = v0.buffers[0];
        GLES20.glBindFramebuffer(36160, v13);
        GLES20.glGenTextures(1, v0.buffers, 0);
        int v11 = v0.buffers[0];
        GLES20.glBindTexture(3553, v11);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        int v15 = v11;
        int v16 = v13;
        int v20 = v1;
        GLES20.glTexImage2D(3553, 0, 6408, v5, v1, 0, 6408, 5121, null);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, v15, 0);
        GLES20.glViewport(0, 0, ((int)v0.size.width), ((int)v0.size.height));
        PaintingData v13_1 = null;
        if(v0.shaders == null) {
            return v13_1;
        }

        Map v6 = v0.shaders;
        String v7 = arg29 ? "nonPremultipliedBlit" : "blit";
        Object v6_1 = v6.get(v7);
        if(v6_1 == null) {
            return v13_1;
        }

        GLES20.glUseProgram(((Shader)v6_1).program);
        Matrix v7_1 = new Matrix();
        v7_1.preTranslate(((float)(-v3)), ((float)(-v4)));
        GLES20.glUniformMatrix4fv(((Shader)v6_1).getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(GLMatrix.MultiplyMat4f(v0.projection, GLMatrix.LoadGraphicsMatrix(v7_1))));
        v3 = 33984;
        if(arg29) {
            GLES20.glUniform1i(((Shader)v6_1).getUniform("texture"), 0);
            GLES20.glActiveTexture(v3);
            v3 = this.getTexture();
            v4 = 3553;
        }
        else {
            v4 = 3553;
            GLES20.glUniform1i(((Shader)v6_1).getUniform("texture"), 0);
            GLES20.glActiveTexture(v3);
            GLES20.glBindTexture(v4, v0.bitmapTexture.texture());
            GLES20.glActiveTexture(v3);
            v3 = this.getTexture();
        }

        GLES20.glBindTexture(v4, v3);
        GLES20.glClearColor(0f, 0f, 0f, 0f);
        GLES20.glClear(16384);
        GLES20.glBlendFunc(1, 771);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, v0.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, v0.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        GLES20.glDrawArrays(5, 0, 4);
        v0.dataBuffer.limit(v5 * v20 * 4);
        GLES20.glReadPixels(0, 0, v5, v20, 6408, 5121, v0.dataBuffer);
        if(arg29) {
            v2 = new PaintingData(v0, ((Bitmap)v13_1), v0.dataBuffer);
        }
        else {
            Bitmap v2_1 = Bitmap.createBitmap(v5, v20, Bitmap$Config.ARGB_8888);
            v2_1.copyPixelsFromBuffer(v0.dataBuffer);
            v2 = new PaintingData(v0, v2_1, ((ByteBuffer)v13_1));
        }

        v0.buffers[0] = v16;
        GLES20.glDeleteFramebuffers(1, v0.buffers, 0);
        v0.buffers[0] = v15;
        GLES20.glDeleteTextures(1, v0.buffers, 0);
        return v2;
    }

    private int getReusableFramebuffer() {
        if(this.reusableFramebuffer == 0) {
            int[] v1 = new int[1];
            GLES20.glGenFramebuffers(1, v1, 0);
            this.reusableFramebuffer = v1[0];
            Utils.HasGLError();
        }

        return this.reusableFramebuffer;
    }

    public Size getSize() {
        return this.size;
    }

    private int getTexture() {
        if(this.bitmapTexture != null) {
            return this.bitmapTexture.texture();
        }

        return 0;
    }

    public boolean isPaused() {
        return this.paused;
    }

    private boolean isSuppressingChanges() {
        boolean v0 = this.suppressChangesCounter > 0 ? true : false;
        return v0;
    }

    public void onPause(Runnable arg3) {
        this.renderView.performInContext(new Runnable(arg3) {
            public void run() {
                Painting.this.paused = true;
                Painting.this.backupSlice = new Slice(Painting.this.getPaintingData(Painting.this.getBounds(), true).data, Painting.this.getBounds(), Painting.this.delegate.requestDispatchQueue());
                Painting.this.cleanResources(false);
                if(this.val$completionRunnable != null) {
                    this.val$completionRunnable.run();
                }
            }
        });
    }

    public void onResume() {
        this.restoreSlice(this.backupSlice);
        this.backupSlice = null;
        this.paused = false;
    }

    public void paintStroke(Path arg3, boolean arg4, Runnable arg5) {
        this.renderView.performInContext(new Runnable(arg3, arg4, arg5) {
            public void run() {
                RectF v0_2;
                Painting.this.activePath = this.val$path;
                int v1 = 36160;
                GLES20.glBindFramebuffer(v1, Painting.this.getReusableFramebuffer());
                int v2 = 3553;
                GLES20.glFramebufferTexture2D(v1, 36064, v2, Painting.this.getPaintTexture(), 0);
                Utils.HasGLError();
                if(GLES20.glCheckFramebufferStatus(v1) == 36053) {
                    GLES20.glViewport(0, 0, ((int)Painting.this.size.width), ((int)Painting.this.size.height));
                    if(this.val$clearBuffer) {
                        GLES20.glClearColor(0f, 0f, 0f, 0f);
                        GLES20.glClear(16384);
                    }

                    if(Painting.this.shaders == null) {
                        return;
                    }

                    Map v0 = Painting.this.shaders;
                    String v4 = Painting.this.brush.isLightSaber() ? "brushLight" : "brush";
                    Object v0_1 = v0.get(v4);
                    if(v0_1 == null) {
                        return;
                    }

                    GLES20.glUseProgram(((Shader)v0_1).program);
                    if(Painting.this.brushTexture == null) {
                        Painting.this.brushTexture = new Texture(Painting.this.brush.getStamp());
                    }

                    GLES20.glActiveTexture(33984);
                    GLES20.glBindTexture(v2, Painting.this.brushTexture.texture());
                    GLES20.glUniformMatrix4fv(((Shader)v0_1).getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(Painting.this.projection));
                    GLES20.glUniform1i(((Shader)v0_1).getUniform("texture"), 0);
                    v0_2 = Render.RenderPath(this.val$path, Painting.this.renderState);
                }
                else {
                    v0_2 = null;
                }

                GLES20.glBindFramebuffer(v1, 0);
                if(Painting.this.delegate != null) {
                    Painting.this.delegate.contentChanged(v0_2);
                }

                if(Painting.this.activeStrokeBounds != null) {
                    Painting.this.activeStrokeBounds.union(v0_2);
                }
                else {
                    Painting.this.activeStrokeBounds = v0_2;
                }

                if(this.val$action != null) {
                    this.val$action.run();
                }
            }
        });
    }

    private void registerUndo(RectF arg4) {
        if(arg4 == null) {
            return;
        }

        if(!arg4.setIntersect(arg4, this.getBounds())) {
            return;
        }

        this.delegate.requestUndoStore().registerUndo(UUID.randomUUID(), new Runnable(new Slice(this.getPaintingData(arg4, true).data, arg4, this.delegate.requestDispatchQueue())) {
            public void run() {
                Painting.this.restoreSlice(this.val$slice);
            }
        });
    }

    private void render(int arg19, int arg20) {
        Painting v0 = this;
        Map v1 = v0.shaders;
        String v2 = v0.brush.isLightSaber() ? "blitWithMaskLight" : "blitWithMask";
        Object v1_1 = v1.get(v2);
        if(v1_1 == null) {
            return;
        }

        GLES20.glUseProgram(((Shader)v1_1).program);
        GLES20.glUniformMatrix4fv(((Shader)v1_1).getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(v0.renderProjection));
        GLES20.glUniform1i(((Shader)v1_1).getUniform("texture"), 0);
        GLES20.glUniform1i(((Shader)v1_1).getUniform("mask"), 1);
        Shader.SetColorUniform(((Shader)v1_1).getUniform("color"), arg20);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.getTexture());
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, arg19);
        GLES20.glBlendFunc(1, 771);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, v0.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, v0.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        GLES20.glDrawArrays(5, 0, 4);
        Utils.HasGLError();
    }

    public void render() {
        if(this.shaders == null) {
            return;
        }

        if(this.activePath != null) {
            this.render(this.getPaintTexture(), this.activePath.getColor());
        }
        else {
            this.renderBlit();
        }
    }

    private void renderBlit() {
        Painting v0 = this;
        Object v1 = v0.shaders.get("blit");
        if(v1 == null) {
            return;
        }

        GLES20.glUseProgram(((Shader)v1).program);
        GLES20.glUniformMatrix4fv(((Shader)v1).getUniform("mvpMatrix"), 1, false, FloatBuffer.wrap(v0.renderProjection));
        GLES20.glUniform1i(((Shader)v1).getUniform("texture"), 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.getTexture());
        GLES20.glBlendFunc(1, 771);
        GLES20.glVertexAttribPointer(0, 2, 5126, false, 8, v0.vertexBuffer);
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glVertexAttribPointer(1, 2, 5126, false, 8, v0.textureBuffer);
        GLES20.glEnableVertexAttribArray(1);
        GLES20.glDrawArrays(5, 0, 4);
        Utils.HasGLError();
    }

    private void restoreSlice(Slice arg3) {
        this.renderView.performInContext(new Runnable(arg3) {
            public void run() {
                ByteBuffer v9 = this.val$slice.getData();
                GLES20.glBindTexture(3553, Painting.this.getTexture());
                GLES20.glTexSubImage2D(3553, 0, this.val$slice.getX(), this.val$slice.getY(), this.val$slice.getWidth(), this.val$slice.getHeight(), 6408, 5121, ((Buffer)v9));
                if(!Painting.this.isSuppressingChanges() && Painting.this.delegate != null) {
                    Painting.this.delegate.contentChanged(this.val$slice.getBounds());
                }

                this.val$slice.cleanResources();
            }
        });
    }

    public void setBitmap(Bitmap arg2) {
        if(this.bitmapTexture != null) {
            return;
        }

        this.bitmapTexture = new Texture(arg2);
    }

    public void setBrush(Brush arg2) {
        this.brush = arg2;
        if(this.brushTexture != null) {
            this.brushTexture.cleanResources(true);
            this.brushTexture = null;
        }
    }

    public void setDelegate(PaintingDelegate arg1) {
        this.delegate = arg1;
    }

    public void setRenderProjection(float[] arg1) {
        this.renderProjection = arg1;
    }

    public void setRenderView(RenderView arg1) {
        this.renderView = arg1;
    }

    public void setupShaders() {
        this.shaders = ShaderSet.setup();
    }

    private void update(RectF arg6, Runnable arg7) {
        int v1 = 36160;
        GLES20.glBindFramebuffer(v1, this.getReusableFramebuffer());
        GLES20.glFramebufferTexture2D(v1, 36064, 3553, this.getTexture(), 0);
        if(GLES20.glCheckFramebufferStatus(v1) == 36053) {
            GLES20.glViewport(0, 0, ((int)this.size.width), ((int)this.size.height));
            arg7.run();
        }

        GLES20.glBindFramebuffer(v1, 0);
        if(!this.isSuppressingChanges() && this.delegate != null) {
            this.delegate.contentChanged(arg6);
        }
    }
}

