package org.linphone.core;

public final class VideoSize {
    public static final int CIF = 1;
    public static final int HVGA = 2;
    public static final int QCIF = 0;
    public static final int QVGA = 3;
    public static final VideoSize VIDEO_SIZE_1020P;
    public static final VideoSize VIDEO_SIZE_720P;
    public static final VideoSize VIDEO_SIZE_CIF;
    public static final VideoSize VIDEO_SIZE_HVGA;
    public static final VideoSize VIDEO_SIZE_QCIF;
    public static final VideoSize VIDEO_SIZE_QVGA;
    public static final VideoSize VIDEO_SIZE_VGA;
    public int height;
    public int width;

    static {
        VideoSize.VIDEO_SIZE_QCIF = new VideoSize(176, 144);
        VideoSize.VIDEO_SIZE_CIF = new VideoSize(352, 288);
        VideoSize.VIDEO_SIZE_QVGA = new VideoSize(320, 240);
        VideoSize.VIDEO_SIZE_HVGA = new VideoSize(320, 480);
        VideoSize.VIDEO_SIZE_VGA = new VideoSize(640, 480);
        VideoSize.VIDEO_SIZE_720P = new VideoSize(1280, 720);
        VideoSize.VIDEO_SIZE_1020P = new VideoSize(1920, 1080);
    }

    public VideoSize(int arg1, int arg2) {
        super();
        this.width = arg1;
        this.height = arg2;
    }

    public VideoSize() {
        super();
    }

    public VideoSize createInverted() {
        return new VideoSize(this.height, this.width);
    }

    @Deprecated public static final VideoSize createStandard(int arg1, boolean arg2) {
        int v0 = 320;
        switch(arg1) {
            case 0: {
                goto label_30;
            }
            case 1: {
                goto label_21;
            }
            case 2: {
                goto label_13;
            }
            case 3: {
                goto label_5;
            }
        }

        return new VideoSize();
    label_21:
        arg1 = 352;
        v0 = 288;
        VideoSize v2 = arg2 ? new VideoSize(v0, arg1) : new VideoSize(arg1, v0);
        return v2;
    label_5:
        arg1 = 240;
        return arg2 ? new VideoSize(arg1, v0) : new VideoSize(v0, arg1);
    label_13:
        arg1 = 480;
        return arg2 ? new VideoSize(v0, arg1) : new VideoSize(arg1, v0);
    label_30:
        arg1 = 176;
        v0 = 144;
        return arg2 ? new VideoSize(v0, arg1) : new VideoSize(arg1, v0);
    }

    public boolean equals(Object arg5) {
        if(this == (((VideoSize)arg5))) {
            return 1;
        }

        if(arg5 == null) {
            return 0;
        }

        if(this.getClass() != arg5.getClass()) {
            return 0;
        }

        if(this.height != ((VideoSize)arg5).height) {
            return 0;
        }

        if(this.width != ((VideoSize)arg5).width) {
            return 0;
        }

        return 1;
    }

    public int hashCode() {
        return (this.height + 31) * 31 + this.width;
    }

    public boolean isPortrait() {
        boolean v0 = this.height >= this.width ? true : false;
        return v0;
    }

    public boolean isValid() {
        boolean v0 = this.width <= 0 || this.height <= 0 ? false : true;
        return v0;
    }

    public String toDisplayableString() {
        return this.width + "x" + this.height;
    }

    public String toString() {
        return "width = " + this.width + " height = " + this.height;
    }
}

