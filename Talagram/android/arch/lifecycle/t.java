package android.arch.lifecycle;

public class t {
    public interface a {
        s a(Class arg1);
    }

    private final a a;
    private final u b;

    public t(u arg1, a arg2) {
        super();
        this.a = arg2;
        this.b = arg1;
    }

    public s a(Class arg4) {
        String v0 = arg4.getCanonicalName();
        if(v0 != null) {
            StringBuilder v1 = new StringBuilder();
            v1.append("android.arch.lifecycle.ViewModelProvider.DefaultKey:");
            v1.append(v0);
            return this.a(v1.toString(), arg4);
        }

        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public s a(String arg3, Class arg4) {
        s v0 = this.b.a(arg3);
        if(arg4.isInstance(v0)) {
            return v0;
        }

        s v4 = this.a.a(arg4);
        this.b.a(arg3, v4);
        return v4;
    }
}

