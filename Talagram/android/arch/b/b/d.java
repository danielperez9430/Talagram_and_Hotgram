package android.arch.b.b;

import android.content.Context;

public class d {
    public static a a(Context arg2, Class arg3) {
        return new a(arg2, arg3, null);
    }

    public static a a(Context arg1, Class arg2, String arg3) {
        if(arg3 != null && arg3.trim().length() != 0) {
            return new a(arg1, arg2, arg3);
        }

        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }

    static Object a(Class arg5, String arg6) {
        StringBuilder v0_1;
        String v0 = arg5.getPackage().getName();
        String v1 = arg5.getCanonicalName();
        if(v0.isEmpty()) {
        }
        else {
            v1 = v1.substring(v0.length() + 1);
        }

        arg6 = v1.replace('.', '_') + arg6;
        try {
            v0 = v0.isEmpty() ? arg6 : v0 + "." + arg6;
            return Class.forName(v0).newInstance();
        }
        catch(InstantiationException ) {
            v0_1 = new StringBuilder();
            v0_1.append("Failed to create an instance of ");
            v0_1.append(arg5.getCanonicalName());
            throw new RuntimeException(v0_1.toString());
        }
        catch(IllegalAccessException ) {
            v0_1 = new StringBuilder();
            v0_1.append("Cannot access the constructor");
            v0_1.append(arg5.getCanonicalName());
            throw new RuntimeException(v0_1.toString());
        }
        catch(ClassNotFoundException ) {
            v1_1 = new StringBuilder();
            v1_1.append("cannot find implementation for ");
            v1_1.append(arg5.getCanonicalName());
            v1_1.append(". ");
            v1_1.append(arg6);
            v1_1.append(" does not exist");
            throw new RuntimeException(v1_1.toString());
        }
    }
}

