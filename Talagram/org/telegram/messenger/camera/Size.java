package org.telegram.messenger.camera;

public final class Size {
    public final int mHeight;
    public final int mWidth;

    public Size(int arg1, int arg2) {
        super();
        this.mWidth = arg1;
        this.mHeight = arg2;
    }

    public boolean equals(Object arg5) {
        boolean v0 = false;
        if(arg5 == null) {
            return 0;
        }

        if(this == (((Size)arg5))) {
            return 1;
        }

        if(((arg5 instanceof Size)) && this.mWidth == ((Size)arg5).mWidth && this.mHeight == ((Size)arg5).mHeight) {
            v0 = true;
        }

        return v0;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int hashCode() {
        return this.mHeight ^ (this.mWidth << 16 | this.mWidth >>> 16);
    }

    private static NumberFormatException invalidSize(String arg3) {
        StringBuilder v1 = new StringBuilder();
        v1.append("Invalid Size: \"");
        v1.append(arg3);
        v1.append("\"");
        throw new NumberFormatException(v1.toString());
    }

    public static Size parseSize(String arg3) {
        int v0 = arg3.indexOf(42);
        if(v0 < 0) {
            v0 = arg3.indexOf(120);
        }

        if(v0 >= 0) {
            try {
                return new Size(Integer.parseInt(arg3.substring(0, v0)), Integer.parseInt(arg3.substring(v0 + 1)));
            }
            catch(NumberFormatException ) {
                throw Size.invalidSize(arg3);
            }
        }

        throw Size.invalidSize(arg3);
    }

    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }
}

