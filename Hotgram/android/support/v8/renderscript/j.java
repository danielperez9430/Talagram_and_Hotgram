package android.support.v8.renderscript;

public abstract class j extends i {
    j(long arg3, RenderScript arg5) {
        super(arg3, arg5);
        if(arg3 != 0) {
            return;
        }

        throw new h("Loading of ScriptIntrinsic failed.");
    }
}

