package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

public class SnowflakesEffect {
    class org.telegram.ui.Components.SnowflakesEffect$1 {
    }

    class Particle {
        float alpha;
        float currentTime;
        float lifeTime;
        float scale;
        int type;
        float velocity;
        float vx;
        float vy;
        float x;
        float y;

        Particle(SnowflakesEffect arg1, org.telegram.ui.Components.SnowflakesEffect$1 arg2) {
            this(arg1);
        }

        private Particle(SnowflakesEffect arg1) {
            SnowflakesEffect.this = arg1;
            super();
        }

        public void draw(Canvas arg27) {
            Particle v0 = this;
            float v2 = 255f;
            if(v0.type != 0) {
                v0.this$0.particleThinPaint.setAlpha(((int)(v0.alpha * v2)));
                float v1 = -1.570796f;
                float v3 = AndroidUtilities.dpf2(2f) * 2f * v0.scale;
                float v4 = -AndroidUtilities.dpf2(0.57f) * 2f * v0.scale;
                float v5 = AndroidUtilities.dpf2(1.55f) * 2f * v0.scale;
                int v2_1 = 0;
                while(v2_1 < 6) {
                    double v6 = ((double)v1);
                    float v8 = (((float)Math.cos(v6))) * v3;
                    float v9 = (((float)Math.sin(v6))) * v3;
                    float v11 = v8 * 0.66f;
                    float v10 = 0.66f * v9;
                    arg27.drawLine(v0.x, v0.y, v0.x + v8, v0.y + v9, v0.this$0.particleThinPaint);
                    Double.isNaN(v6);
                    v6 = ((double)(((float)(v6 - 1.570796))));
                    double v8_1 = Math.cos(v6);
                    double v12 = ((double)v4);
                    Double.isNaN(v12);
                    double v14 = Math.sin(v6);
                    float v18 = v3;
                    float v19 = v4;
                    double v3_1 = ((double)v5);
                    Double.isNaN(v3_1);
                    v8 = ((float)(v8_1 * v12 - v14 * v3_1));
                    v14 = Math.sin(v6);
                    Double.isNaN(v12);
                    double v16 = Math.cos(v6);
                    Double.isNaN(v3_1);
                    arg27.drawLine(v0.x + v11, v0.y + v10, v0.x + v8, v0.y + (((float)(v14 * v12 + v16 * v3_1))), v0.this$0.particleThinPaint);
                    v8_1 = -Math.cos(v6);
                    Double.isNaN(v12);
                    v14 = Math.sin(v6);
                    Double.isNaN(v3_1);
                    v8 = ((float)(v8_1 * v12 - v14 * v3_1));
                    v14 = -Math.sin(v6);
                    Double.isNaN(v12);
                    v6 = Math.cos(v6);
                    Double.isNaN(v3_1);
                    arg27.drawLine(v0.x + v11, v0.y + v10, v0.x + v8, v0.y + (((float)(v14 * v12 + v6 * v3_1))), v0.this$0.particleThinPaint);
                    v1 += 1.047198f;
                    ++v2_1;
                    v3 = v18;
                    v4 = v19;
                }
            }
            else {
                v0.this$0.particlePaint.setAlpha(((int)(v0.alpha * v2)));
                arg27.drawPoint(v0.x, v0.y, v0.this$0.particlePaint);
            }
        }
    }

    final float angleDiff;
    private ArrayList freeParticles;
    private long lastAnimationTime;
    private Paint particlePaint;
    private Paint particleThinPaint;
    private ArrayList particles;

    public SnowflakesEffect() {
        super();
        this.angleDiff = 1.047198f;
        this.particles = new ArrayList();
        this.freeParticles = new ArrayList();
        this.particlePaint = new Paint(1);
        this.particlePaint.setStrokeWidth(((float)AndroidUtilities.dp(1.5f)));
        this.particlePaint.setColor(Theme.getColor("actionBarDefaultTitle") & -1644826);
        this.particlePaint.setStrokeCap(Paint$Cap.ROUND);
        this.particlePaint.setStyle(Paint$Style.STROKE);
        this.particleThinPaint = new Paint(1);
        this.particleThinPaint.setStrokeWidth(((float)AndroidUtilities.dp(0.5f)));
        this.particleThinPaint.setColor(Theme.getColor("actionBarDefaultTitle") & -1644826);
        this.particleThinPaint.setStrokeCap(Paint$Cap.ROUND);
        this.particleThinPaint.setStyle(Paint$Style.STROKE);
        int v0;
        for(v0 = 0; v0 < 20; ++v0) {
            this.freeParticles.add(new Particle(this, null));
        }
    }

    static Paint access$000(SnowflakesEffect arg0) {
        return arg0.particlePaint;
    }

    static Paint access$100(SnowflakesEffect arg0) {
        return arg0.particleThinPaint;
    }

    public void onDraw(View arg10, Canvas arg11) {
        Object v5;
        if(arg10 != null) {
            if(arg11 == null) {
            }
            else {
                int v0 = this.particles.size();
                int v2;
                for(v2 = 0; v2 < v0; ++v2) {
                    this.particles.get(v2).draw(arg11);
                }

                if(Utilities.random.nextFloat() > 0.7f) {
                    v0 = 100;
                    if(this.particles.size() < v0) {
                        int v11 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
                        float v2_1 = Utilities.random.nextFloat() * (((float)arg10.getMeasuredWidth()));
                        float v6 = 20f;
                        float v3 = (((float)v11)) + Utilities.random.nextFloat() * (((float)(arg10.getMeasuredHeight() - AndroidUtilities.dp(v6) - v11)));
                        double v7 = ((double)(Utilities.random.nextInt(40) + 70));
                        Double.isNaN(v7);
                        v7 *= 0.017453;
                        float v11_1 = ((float)Math.cos(v7));
                        float v4 = ((float)Math.sin(v7));
                        if(!this.freeParticles.isEmpty()) {
                            v5 = this.freeParticles.get(0);
                            this.freeParticles.remove(0);
                        }
                        else {
                            Particle v5_1 = new Particle(this, null);
                        }

                        ((Particle)v5).x = v2_1;
                        ((Particle)v5).y = v3;
                        ((Particle)v5).vx = v11_1;
                        ((Particle)v5).vy = v4;
                        ((Particle)v5).alpha = 0f;
                        ((Particle)v5).currentTime = 0f;
                        ((Particle)v5).scale = Utilities.random.nextFloat() * 1.2f;
                        ((Particle)v5).type = Utilities.random.nextInt(2);
                        ((Particle)v5).lifeTime = ((float)(Utilities.random.nextInt(v0) + 2000));
                        ((Particle)v5).velocity = Utilities.random.nextFloat() * 4f + v6;
                        this.particles.add(v5);
                    }
                }

                long v0_1 = System.currentTimeMillis();
                this.updateParticles(Math.min(17, v0_1 - this.lastAnimationTime));
                this.lastAnimationTime = v0_1;
                arg10.invalidate();
            }
        }
    }

    private void updateParticles(long arg9) {
        int v0 = this.particles.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = this.particles.get(v1);
            if(((Particle)v2).currentTime >= ((Particle)v2).lifeTime) {
                if(this.freeParticles.size() < 40) {
                    this.freeParticles.add(v2);
                }

                this.particles.remove(v1);
                --v1;
                --v0;
            }
            else {
                float v4 = 200f;
                float v3 = ((Particle)v2).currentTime < v4 ? AndroidUtilities.accelerateInterpolator.getInterpolation(((Particle)v2).currentTime / v4) : 1f - AndroidUtilities.decelerateInterpolator.getInterpolation((((Particle)v2).currentTime - v4) / (((Particle)v2).lifeTime - v4));
                ((Particle)v2).alpha = v3;
                float v5 = ((float)arg9);
                ((Particle)v2).x += ((Particle)v2).vx * ((Particle)v2).velocity * v5 / 500f;
                ((Particle)v2).y += ((Particle)v2).vy * ((Particle)v2).velocity * v5 / 500f;
                ((Particle)v2).currentTime += v5;
            }
        }
    }
}

