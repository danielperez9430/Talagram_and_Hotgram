package org.linphone.core;

public interface LpConfig {
    boolean getBool(String arg1, String arg2, boolean arg3);

    float getFloat(String arg1, String arg2, float arg3);

    int getInt(String arg1, String arg2, int arg3);

    int[] getIntRange(String arg1, String arg2, int arg3, int arg4);

    String getString(String arg1, String arg2, String arg3);

    void setBool(String arg1, String arg2, boolean arg3);

    void setFloat(String arg1, String arg2, float arg3);

    void setInt(String arg1, String arg2, int arg3);

    void setIntRange(String arg1, String arg2, int arg3, int arg4);

    void setString(String arg1, String arg2, String arg3);

    void sync();
}

