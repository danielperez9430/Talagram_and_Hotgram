package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import java.util.UUID;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.Point;
import org.telegram.ui.Components.Rect;

public class EntityView extends FrameLayout {
    public interface EntityViewDelegate {
        boolean allowInteraction(EntityView arg1);

        boolean onEntityLongClicked(EntityView arg1);

        boolean onEntitySelected(EntityView arg1);
    }

    public class SelectionView extends FrameLayout {
        public static final int SELECTION_LEFT_HANDLE = 1;
        public static final int SELECTION_RIGHT_HANDLE = 2;
        public static final int SELECTION_WHOLE_HANDLE = 3;
        private int currentHandle;
        protected Paint dotPaint;
        protected Paint dotStrokePaint;
        protected Paint paint;

        public SelectionView(EntityView arg2, Context arg3) {
            EntityView.this = arg2;
            super(arg3);
            this.paint = new Paint(1);
            this.dotPaint = new Paint(1);
            this.dotStrokePaint = new Paint(1);
            this.setWillNotDraw(false);
            this.paint.setColor(-1);
            this.dotPaint.setColor(-12793105);
            this.dotStrokePaint.setColor(-1);
            this.dotStrokePaint.setStyle(Paint$Style.STROKE);
            this.dotStrokePaint.setStrokeWidth(((float)AndroidUtilities.dp(1f)));
        }

        public boolean onTouchEvent(MotionEvent arg11) {
            int v1 = 3;
            boolean v2 = false;
            switch(arg11.getActionMasked()) {
                case 2: {
                    if(this.currentHandle == v1) {
                        v2 = EntityView.this.onTouchMove(arg11.getRawX(), arg11.getRawY());
                        goto label_129;
                    }

                    if(this.currentHandle == 0) {
                        goto label_129;
                    }

                    EntityView.this.hasTransformed = true;
                    Point v0_1 = new Point(arg11.getRawX() - EntityView.this.previousLocationX, arg11.getRawY() - EntityView.this.previousLocationY);
                    float v2_1 = ((float)Math.toRadians(((double)this.getRotation())));
                    double v4 = ((double)v0_1.x);
                    double v6 = ((double)v2_1);
                    double v8 = Math.cos(v6);
                    Double.isNaN(v4);
                    v4 *= v8;
                    v8 = ((double)v0_1.y);
                    v6 = Math.sin(v6);
                    Double.isNaN(v8);
                    float v0_2 = ((float)(v4 + v8 * v6));
                    if(this.currentHandle == 1) {
                        v0_2 *= -1f;
                    }

                    EntityView.this.scale(v0_2 * 2f / (((float)this.getWidth())) + 1f);
                    int v4_1 = 2;
                    v0_2 = ((float)(this.getLeft() + this.getWidth() / v4_1));
                    v2_1 = ((float)(this.getTop() + this.getHeight() / v4_1));
                    float v5 = arg11.getRawX() - (((float)this.getParent().getLeft()));
                    float v6_1 = arg11.getRawY() - (((float)this.getParent().getTop())) - (((float)AndroidUtilities.statusBarHeight));
                    float v7 = 0f;
                    if(this.currentHandle == 1) {
                        v6 = ((double)(v2_1 - v6_1));
                        v4 = ((double)(v0_2 - v5));
                        goto label_88;
                    }
                    else if(this.currentHandle == v4_1) {
                        v6 = ((double)(v6_1 - v2_1));
                        v4 = ((double)(v5 - v0_2));
                    label_88:
                        v7 = ((float)Math.atan2(v6, v4));
                    }

                    EntityView.this.rotate(((float)Math.toDegrees(((double)v7))));
                    EntityView.this.previousLocationX = arg11.getRawX();
                    EntityView.this.previousLocationY = arg11.getRawY();
                    goto label_113;
                }
                case 0: 
                case 5: {
                    int v0 = this.pointInsideHandle(arg11.getX(), arg11.getY());
                    if(v0 == 0) {
                        goto label_129;
                    }

                    this.currentHandle = v0;
                    EntityView.this.previousLocationX = arg11.getRawX();
                    EntityView.this.previousLocationY = arg11.getRawY();
                    EntityView.this.hasReleased = false;
                    goto label_113;
                }
                case 1: 
                case 3: 
                case 6: {
                    EntityView.this.onTouchUp();
                    this.currentHandle = 0;
                label_113:
                    v2 = true;
                    break;
                }
                default: {
                    break;
                }
            }

        label_129:
            if(this.currentHandle == v1) {
                EntityView.this.gestureDetector.onTouchEvent(arg11);
            }

            return v2;
        }

        protected int pointInsideHandle(float arg1, float arg2) {
            return 0;
        }

        protected void updatePosition() {
            Rect v0 = EntityView.this.getSelectionBounds();
            ViewGroup$LayoutParams v1 = this.getLayoutParams();
            ((FrameLayout$LayoutParams)v1).leftMargin = (((int)v0.x)) + EntityView.this.offsetX;
            ((FrameLayout$LayoutParams)v1).topMargin = (((int)v0.y)) + EntityView.this.offsetY;
            ((FrameLayout$LayoutParams)v1).width = ((int)v0.width);
            ((FrameLayout$LayoutParams)v1).height = ((int)v0.height);
            this.setLayoutParams(v1);
            this.setRotation(EntityView.this.getRotation());
        }
    }

    private boolean announcedSelection;
    private EntityViewDelegate delegate;
    private GestureDetector gestureDetector;
    private boolean hasPanned;
    private boolean hasReleased;
    private boolean hasTransformed;
    private int offsetX;
    private int offsetY;
    protected Point position;
    private float previousLocationX;
    private float previousLocationY;
    private boolean recognizedLongPress;
    protected SelectionView selectionView;
    private UUID uuid;

    public EntityView(Context arg2, Point arg3) {
        super(arg2);
        this.hasPanned = false;
        this.hasReleased = false;
        this.hasTransformed = false;
        this.announcedSelection = false;
        this.recognizedLongPress = false;
        this.position = new Point();
        this.uuid = UUID.randomUUID();
        this.position = arg3;
        this.gestureDetector = new GestureDetector(arg2, new GestureDetector$SimpleOnGestureListener() {
            public void onLongPress(MotionEvent arg2) {
                if(!EntityView.access$000(EntityView.this) && !EntityView.access$100(EntityView.this)) {
                    if(EntityView.access$200(EntityView.this)) {
                    }
                    else {
                        EntityView.access$302(EntityView.this, true);
                        if(EntityView.access$400(EntityView.this) != null) {
                            EntityView.this.performHapticFeedback(0);
                            EntityView.access$400(EntityView.this).onEntityLongClicked(EntityView.this);
                        }
                    }
                }
            }
        });
    }

    static boolean access$000(EntityView arg0) {
        return arg0.hasPanned;
    }

    static boolean access$100(EntityView arg0) {
        return arg0.hasTransformed;
    }

    static void access$1000(EntityView arg0) {
        arg0.onTouchUp();
    }

    static boolean access$102(EntityView arg0, boolean arg1) {
        arg0.hasTransformed = arg1;
        return arg1;
    }

    static GestureDetector access$1100(EntityView arg0) {
        return arg0.gestureDetector;
    }

    static boolean access$200(EntityView arg0) {
        return arg0.hasReleased;
    }

    static boolean access$202(EntityView arg0, boolean arg1) {
        arg0.hasReleased = arg1;
        return arg1;
    }

    static boolean access$302(EntityView arg0, boolean arg1) {
        arg0.recognizedLongPress = arg1;
        return arg1;
    }

    static EntityViewDelegate access$400(EntityView arg0) {
        return arg0.delegate;
    }

    static int access$500(EntityView arg0) {
        return arg0.offsetX;
    }

    static int access$600(EntityView arg0) {
        return arg0.offsetY;
    }

    static float access$700(EntityView arg0) {
        return arg0.previousLocationX;
    }

    static float access$702(EntityView arg0, float arg1) {
        arg0.previousLocationX = arg1;
        return arg1;
    }

    static float access$800(EntityView arg0) {
        return arg0.previousLocationY;
    }

    static float access$802(EntityView arg0, float arg1) {
        arg0.previousLocationY = arg1;
        return arg1;
    }

    static boolean access$900(EntityView arg0, float arg1, float arg2) {
        return arg0.onTouchMove(arg1, arg2);
    }

    protected SelectionView createSelectionView() {
        return null;
    }

    public void deselect() {
        if(this.selectionView == null) {
            return;
        }

        if(this.selectionView.getParent() != null) {
            this.selectionView.getParent().removeView(this.selectionView);
        }

        this.selectionView = null;
    }

    public Point getPosition() {
        return this.position;
    }

    public float getScale() {
        return this.getScaleX();
    }

    protected Rect getSelectionBounds() {
        return new Rect(0f, 0f, 0f, 0f);
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public boolean isSelected() {
        boolean v0 = this.selectionView != null ? true : false;
        return v0;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg1) {
        return this.delegate.allowInteraction(this);
    }

    public boolean onTouchEvent(MotionEvent arg6) {
        boolean v1 = false;
        if(arg6.getPointerCount() <= 1) {
            if(!this.delegate.allowInteraction(this)) {
            }
            else {
                float v0 = arg6.getRawX();
                float v3 = arg6.getRawY();
                switch(arg6.getActionMasked()) {
                    case 2: {
                        goto label_13;
                    }
                    case 0: 
                    case 5: {
                        goto label_18;
                    }
                    case 1: 
                    case 3: 
                    case 6: {
                        goto label_15;
                    }
                }

                goto label_29;
            label_18:
                if(!this.isSelected() && this.delegate != null) {
                    this.delegate.onEntitySelected(this);
                    this.announcedSelection = true;
                }

                this.previousLocationX = v0;
                this.previousLocationY = v3;
                this.hasReleased = false;
                goto label_16;
            label_13:
                v1 = this.onTouchMove(v0, v3);
                goto label_29;
            label_15:
                this.onTouchUp();
            label_16:
                v1 = true;
            label_29:
                this.gestureDetector.onTouchEvent(arg6);
            }
        }

        return v1;
    }

    private boolean onTouchMove(float arg7, float arg8) {
        float v0 = this.getParent().getScaleX();
        Point v1 = new Point((arg7 - this.previousLocationX) / v0, (arg8 - this.previousLocationY) / v0);
        v0 = ((float)Math.hypot(((double)v1.x), ((double)v1.y)));
        float v2 = this.hasPanned ? 6f : 16f;
        if(v0 > v2) {
            this.pan(v1);
            this.previousLocationX = arg7;
            this.previousLocationY = arg8;
            this.hasPanned = true;
            return 1;
        }

        return 0;
    }

    private void onTouchUp() {
        if(!this.recognizedLongPress && !this.hasPanned && !this.hasTransformed && !this.announcedSelection && this.delegate != null) {
            this.delegate.onEntitySelected(this);
        }

        this.recognizedLongPress = false;
        this.hasPanned = false;
        this.hasTransformed = false;
        this.hasReleased = true;
        this.announcedSelection = false;
    }

    public void pan(Point arg4) {
        this.position.x += arg4.x;
        this.position.y += arg4.y;
        this.updatePosition();
    }

    public void rotate(float arg1) {
        this.setRotation(arg1);
        this.updateSelectionView();
    }

    public void scale(float arg2) {
        this.setScale(Math.max(this.getScale() * arg2, 0.1f));
        this.updateSelectionView();
    }

    public void select(ViewGroup arg2) {
        SelectionView v0 = this.createSelectionView();
        this.selectionView = v0;
        arg2.addView(((View)v0));
        v0.updatePosition();
    }

    public void setDelegate(EntityViewDelegate arg1) {
        this.delegate = arg1;
    }

    public void setOffset(int arg1, int arg2) {
        this.offsetX = arg1;
        this.offsetY = arg2;
    }

    public void setPosition(Point arg1) {
        this.position = arg1;
        this.updatePosition();
    }

    public void setScale(float arg1) {
        this.setScaleX(arg1);
        this.setScaleY(arg1);
    }

    public void setSelectionVisibility(boolean arg2) {
        if(this.selectionView == null) {
            return;
        }

        SelectionView v0 = this.selectionView;
        int v2 = arg2 ? 0 : 8;
        v0.setVisibility(v2);
    }

    protected void updatePosition() {
        float v0 = (((float)this.getWidth())) / 2f;
        float v2 = (((float)this.getHeight())) / 2f;
        this.setX(this.position.x - v0);
        this.setY(this.position.y - v2);
        this.updateSelectionView();
    }

    public void updateSelectionView() {
        if(this.selectionView != null) {
            this.selectionView.updatePosition();
        }
    }
}

