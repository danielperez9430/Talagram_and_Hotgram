package org.telegram.ui.Components;

import android.graphics.drawable.Drawable;

public abstract class StatusDrawable extends Drawable {
    public StatusDrawable() {
        super();
    }

    public abstract void setIsChat(boolean arg1);

    public abstract void start();

    public abstract void stop();
}

