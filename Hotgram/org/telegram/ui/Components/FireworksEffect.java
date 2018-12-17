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

public class FireworksEffect {
    class org.telegram.ui.Components.FireworksEffect$1 {
    }

    class Particle {
        float alpha;
        int color;
        float currentTime;
        float lifeTime;
        float scale;
        int type;
        float velocity;
        float vx;
        float vy;
        float x;
        float y;

        Particle(FireworksEffect arg1, org.telegram.ui.Components.FireworksEffect$1 arg2) {
            this(arg1);
        }

        private Particle(FireworksEffect arg1) {
            FireworksEffect.this = arg1;
            super();
        }

        public void draw(Canvas arg4) {
            if(this.type != 0) {
            }
            else {
                FireworksEffect.this.particlePaint.setColor(this.color);
                FireworksEffect.this.particlePaint.setStrokeWidth((((float)AndroidUtilities.dp(1.5f))) * this.scale);
                FireworksEffect.this.particlePaint.setAlpha(((int)(this.alpha * 255f)));
                arg4.drawPoint(this.x, this.y, FireworksEffect.this.particlePaint);
            }
        }
    }

    final float angleDiff;
    private ArrayList freeParticles;
    private long lastAnimationTime;
    private Paint particlePaint;
    private ArrayList particles;

    public FireworksEffect() {
        super();
        this.angleDiff = 1.047198f;
        this.particles = new ArrayList();
        this.freeParticles = new ArrayList();
        this.particlePaint = new Paint(1);
        this.particlePaint.setStrokeWidth(((float)AndroidUtilities.dp(1.5f)));
        this.particlePaint.setColor(Theme.getColor("actionBarDefaultTitle") & -1644826);
        this.particlePaint.setStrokeCap(Paint$Cap.ROUND);
        this.particlePaint.setStyle(Paint$Style.STROKE);
        int v0;
        for(v0 = 0; v0 < 20; ++v0) {
            this.freeParticles.add(new Particle(this, null));
        }
    }

    static Paint access$000(FireworksEffect arg0) {
        return arg0.particlePaint;
    }

    public void onDraw(View arg12, Canvas arg13) {
        Object v8;
        if(arg12 != null) {
            if(arg13 == null) {
            }
            else {
                int v0 = this.particles.size();
                int v2;
                for(v2 = 0; v2 < v0; ++v2) {
                    this.particles.get(v2).draw(arg13);
                }

                if(Utilities.random.nextBoolean()) {
                    v0 = 8;
                    if(this.particles.size() + v0 < 150) {
                        int v13 = Build$VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0;
                        float v2_1 = Utilities.random.nextFloat() * (((float)arg12.getMeasuredWidth()));
                        float v6 = 20f;
                        float v3 = (((float)v13)) + Utilities.random.nextFloat() * (((float)(arg12.getMeasuredHeight() - AndroidUtilities.dp(v6) - v13)));
                        switch(Utilities.random.nextInt(4)) {
                            case 0: {
                                v13 = -13357350;
                                break;
                            }
                            case 1: {
                                v13 = -843755;
                                break;
                            }
                            case 2: {
                                v13 = -207021;
                                break;
                            }
                            case 3: {
                                v13 = -15088582;
                                break;
                            }
                            default: {
                                v13 = -5752;
                                break;
                            }
                        }

                        int v4;
                        for(v4 = 0; v4 < v0; ++v4) {
                            double v9 = ((double)(Utilities.random.nextInt(270) - 225));
                            Double.isNaN(v9);
                            v9 *= 0.017453;
                            float v5 = ((float)Math.cos(v9));
                            float v7 = ((float)Math.sin(v9));
                            if(!this.freeParticles.isEmpty()) {
                                v8 = this.freeParticles.get(0);
                                this.freeParticles.remove(0);
                            }
                            else {
                                Particle v8_1 = new Particle(this, null);
                            }

                            ((Particle)v8).x = v2_1;
                            ((Particle)v8).y = v3;
                            ((Particle)v8).vx = v5 * 1.5f;
                            ((Particle)v8).vy = v7;
                            ((Particle)v8).color = v13;
                            ((Particle)v8).alpha = 1f;
                            ((Particle)v8).currentTime = 0f;
                            ((Particle)v8).scale = Math.max(1f, Utilities.random.nextFloat() * 1.5f);
                            ((Particle)v8).type = 0;
                            ((Particle)v8).lifeTime = ((float)(Utilities.random.nextInt(1000) + 1000));
                            ((Particle)v8).velocity = Utilities.random.nextFloat() * 4f + v6;
                            this.particles.add(v8);
                        }
                    }
                }

                long v0_1 = System.currentTimeMillis();
                this.updateParticles(Math.min(17, v0_1 - this.lastAnimationTime));
                this.lastAnimationTime = v0_1;
                arg12.invalidate();
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
                ((Particle)v2).alpha = 1f - AndroidUtilities.decelerateInterpolator.getInterpolation(((Particle)v2).currentTime / ((Particle)v2).lifeTime);
                float v5 = ((float)arg9);
                ((Particle)v2).x += ((Particle)v2).vx * ((Particle)v2).velocity * v5 / 500f;
                ((Particle)v2).y += ((Particle)v2).vy * ((Particle)v2).velocity * v5 / 500f;
                ((Particle)v2).vy += v5 / 100f;
                ((Particle)v2).currentTime += v5;
            }
        }
    }
}

