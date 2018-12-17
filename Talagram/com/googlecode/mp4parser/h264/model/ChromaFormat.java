package com.googlecode.mp4parser.h264.model;

public class ChromaFormat {
    public static ChromaFormat MONOCHROME;
    public static ChromaFormat YUV_420;
    public static ChromaFormat YUV_422;
    public static ChromaFormat YUV_444;
    private int id;
    private int subHeight;
    private int subWidth;

    static {
        ChromaFormat.MONOCHROME = new ChromaFormat(0, 0, 0);
        ChromaFormat.YUV_420 = new ChromaFormat(1, 2, 2);
        ChromaFormat.YUV_422 = new ChromaFormat(2, 2, 1);
        ChromaFormat.YUV_444 = new ChromaFormat(3, 1, 1);
    }

    public ChromaFormat(int arg1, int arg2, int arg3) {
        super();
        this.id = arg1;
        this.subWidth = arg2;
        this.subHeight = arg3;
    }

    public static ChromaFormat fromId(int arg1) {
        if(arg1 == ChromaFormat.MONOCHROME.id) {
            return ChromaFormat.MONOCHROME;
        }

        if(arg1 == ChromaFormat.YUV_420.id) {
            return ChromaFormat.YUV_420;
        }

        if(arg1 == ChromaFormat.YUV_422.id) {
            return ChromaFormat.YUV_422;
        }

        if(arg1 == ChromaFormat.YUV_444.id) {
            return ChromaFormat.YUV_444;
        }

        return null;
    }

    public int getId() {
        return this.id;
    }

    public int getSubHeight() {
        return this.subHeight;
    }

    public int getSubWidth() {
        return this.subWidth;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("ChromaFormat{\nid=");
        v0.append(this.id);
        v0.append(",\n");
        v0.append(" subWidth=");
        v0.append(this.subWidth);
        v0.append(",\n");
        v0.append(" subHeight=");
        v0.append(this.subHeight);
        v0.append('}');
        return v0.toString();
    }
}

