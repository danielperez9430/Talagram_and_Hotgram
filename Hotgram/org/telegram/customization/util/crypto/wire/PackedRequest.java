package org.telegram.customization.util.crypto.wire;

public class PackedRequest {
    public enum Method {
        public static final enum Method GET;
        public static final enum Method POST;
        public static final enum Method PUT;

        static {
            Method.GET = new Method("GET", 0);
            Method.POST = new Method("POST", 1);
            Method.PUT = new Method("PUT", 2);
            Method.$VALUES = new Method[]{Method.GET, Method.POST, Method.PUT};
        }

        private Method(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static Method valueOf(String arg1) {
            return Enum.valueOf(Method.class, arg1);
        }

        public static Method[] values() {
            // Method was not decompiled
        }
    }

    private static int MAX_URL_SIZE = 4096;
    private String body;
    private Method method;
    private String url;

    static {
    }

    private PackedRequest(String arg2) {
        super();
        this.method = Method.GET;
        this.url = arg2;
    }

    private PackedRequest(String arg2, String arg3) {
        this(arg2, arg3, Method.POST);
    }

    private PackedRequest(String arg2, String arg3, Method arg4) {
        super();
        if(arg4 != Method.GET) {
            this.method = arg4;
            this.url = arg2;
            this.body = arg3;
            return;
        }

        throw new IllegalArgumentException("Method should be either POST or PUT.");
    }

    public ParceledRequest decode() {
        String v0 = this.method == Method.POST || this.method == Method.PUT ? this.body : this.url;
        return ParceledRequest.decode(v0);
    }

    public static PackedRequest from(String arg2) {
        if(arg2.length() > PackedRequest.MAX_URL_SIZE) {
            return new PackedRequest("", arg2);
        }

        return new PackedRequest(arg2);
    }

    public String getBody() {
        return this.body;
    }

    public Method getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
    }
}

