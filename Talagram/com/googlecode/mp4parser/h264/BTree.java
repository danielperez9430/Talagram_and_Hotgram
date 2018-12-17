package com.googlecode.mp4parser.h264;

public class BTree {
    private BTree one;
    private Object value;
    private BTree zero;

    public BTree() {
        super();
    }

    public void addString(String arg3, Object arg4) {
        BTree v0;
        if(arg3.length() == 0) {
            this.value = arg4;
            return;
        }

        if(arg3.charAt(0) == 48) {
            if(this.zero == null) {
                this.zero = new BTree();
            }

            v0 = this.zero;
        }
        else {
            if(this.one == null) {
                this.one = new BTree();
            }

            v0 = this.one;
        }

        v0.addString(arg3.substring(1), arg4);
    }

    public BTree down(int arg1) {
        if(arg1 == 0) {
            return this.zero;
        }

        return this.one;
    }

    public Object getValue() {
        return this.value;
    }
}

