package com.coremedia.iso;

import com.coremedia.iso.boxes.Box;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertyBoxParserImpl extends AbstractBoxParser {
    static String[] EMPTY_STRING_ARRAY;
    StringBuilder buildLookupStrings;
    String clazzName;
    Pattern constuctorPattern;
    Properties mapping;
    String[] param;

    static {
        PropertyBoxParserImpl.EMPTY_STRING_ARRAY = new String[0];
    }

    public PropertyBoxParserImpl(String[] arg7) {
        int v2_1;
        InputStream v2;
        Enumeration v1_1;
        super();
        this.constuctorPattern = Pattern.compile("(.*)\\((.*?)\\)");
        this.buildLookupStrings = new StringBuilder();
        InputStream v0 = this.getClass().getResourceAsStream("/isoparser-default.properties");
        try {
            this.mapping = new Properties();
            try {
                this.mapping.load(v0);
                ClassLoader v1 = Thread.currentThread().getContextClassLoader();
                if(v1 == null) {
                    v1 = ClassLoader.getSystemClassLoader();
                }

                v1_1 = v1.getResources("isoparser-custom.properties");
            label_21:
                while(!v1_1.hasMoreElements()) {
                    goto label_23;
                }
            }
            catch(IOException v7_1) {
                goto label_48;
            }
        }
        catch(Throwable v7) {
            goto label_52;
        }

        try {
            v2 = v1_1.nextElement().openStream();
        }
        catch(Throwable v7) {
            goto label_52;
        }
        catch(IOException v7_1) {
            goto label_48;
        }

        try {
            this.mapping.load(v2);
            goto label_42;
        }
        catch(Throwable v7) {
            try {
                v2.close();
                throw v7;
            label_42:
                v2.close();
                goto label_21;
            }
            catch(Throwable v7) {
                goto label_52;
            }
            catch(IOException v7_1) {
                goto label_48;
            }
        }

        try {
        label_23:
            int v1_2 = arg7.length;
            v2_1 = 0;
        }
        catch(IOException v7_1) {
            goto label_48;
        }

        while(v2_1 < v1_2) {
            try {
                this.mapping.load(this.getClass().getResourceAsStream(arg7[v2_1]));
                ++v2_1;
                continue;
            }
            catch(Throwable v7) {
                goto label_52;
            }
            catch(IOException v7_1) {
                goto label_48;
            }
        }

        try {
            v0.close();
        }
        catch(IOException v7_1) {
            v7_1.printStackTrace();
        }

        return;
        try {
        label_48:
            throw new RuntimeException(((Throwable)v7_1));
        }
        catch(Throwable v7) {
            try {
            label_52:
                v0.close();
            }
            catch(IOException v0_1) {
                v0_1.printStackTrace();
            }

            throw v7;
        }
    }

    public PropertyBoxParserImpl(Properties arg2) {
        super();
        this.constuctorPattern = Pattern.compile("(.*)\\((.*?)\\)");
        this.buildLookupStrings = new StringBuilder();
        this.mapping = arg2;
    }

    public Box createBox(String arg7, byte[] arg8, String arg9) {
        this.invoke(arg7, arg8, arg9);
        try {
            Class v0 = Class.forName(this.clazzName);
            if(this.param.length <= 0) {
                goto label_57;
            }

            Class[] v1 = new Class[this.param.length];
            Object[] v2 = new Object[this.param.length];
            int v3;
            for(v3 = 0; true; ++v3) {
                if(v3 >= this.param.length) {
                    goto label_16;
                }

                if("userType".equals(this.param[v3])) {
                    v2[v3] = arg8;
                    v1[v3] = byte[].class;
                }
                else if("type".equals(this.param[v3])) {
                    v2[v3] = arg7;
                    v1[v3] = String.class;
                }
                else if("parent".equals(this.param[v3])) {
                    v2[v3] = arg9;
                    v1[v3] = String.class;
                }
                else {
                    break;
                }
            }

            StringBuilder v8 = new StringBuilder("No such param: ");
            v8.append(this.param[v3]);
            throw new InternalError(v8.toString());
        label_16:
            return v0.getConstructor(v1).newInstance(v2);
        label_57:
            return v0.newInstance();
        }
        catch(NoSuchMethodException v7) {
            throw new RuntimeException(((Throwable)v7));
        }
        catch(InvocationTargetException v7_1) {
            throw new RuntimeException(((Throwable)v7_1));
        }
        catch(IllegalAccessException v7_2) {
            throw new RuntimeException(((Throwable)v7_2));
        }
        catch(InstantiationException v7_3) {
            throw new RuntimeException(((Throwable)v7_3));
        }
        catch(ClassNotFoundException v7_4) {
            throw new RuntimeException(((Throwable)v7_4));
        }
    }

    public void invoke(String arg5, byte[] arg6, String arg7) {
        String[] v5_1;
        String v1_1;
        StringBuilder v6;
        if(arg6 == null) {
            v1_1 = this.mapping.getProperty(arg5);
            if(v1_1 != null) {
                goto label_52;
            }

            v6 = this.buildLookupStrings;
            v6.append(arg7);
            v6.append('-');
            v6.append(arg5);
            String v6_1 = v6.toString();
            this.buildLookupStrings.setLength(0);
            v1_1 = this.mapping.getProperty(v6_1);
        }
        else if("uuid".equals(arg5)) {
            Properties v1 = this.mapping;
            StringBuilder v2 = new StringBuilder("uuid[");
            v2.append(Hex.encodeHex(arg6).toUpperCase());
            v2.append("]");
            v1_1 = v1.getProperty(v2.toString());
            if(v1_1 == null) {
                v1 = this.mapping;
                v2 = new StringBuilder(String.valueOf(arg7));
                v2.append("-uuid[");
                v2.append(Hex.encodeHex(arg6).toUpperCase());
                v2.append("]");
                v1_1 = v1.getProperty(v2.toString());
            }

            if(v1_1 != null) {
                goto label_52;
            }

            v1_1 = this.mapping.getProperty("uuid");
        }
        else {
            throw new RuntimeException("we have a userType but no uuid box type. Something\'s wrong");
        }

    label_52:
        if(v1_1 == null) {
            v1_1 = this.mapping.getProperty("default");
        }

        if(v1_1 == null) {
            goto label_96;
        }

        if(!v1_1.endsWith(")")) {
            this.param = PropertyBoxParserImpl.EMPTY_STRING_ARRAY;
            this.clazzName = v1_1;
        }
        else {
            Matcher v5 = this.constuctorPattern.matcher(((CharSequence)v1_1));
            if(v5.matches()) {
                this.clazzName = v5.group(1);
                int v6_2 = 2;
                if(v5.group(v6_2).length() == 0) {
                    v5_1 = PropertyBoxParserImpl.EMPTY_STRING_ARRAY;
                }
                else if(v5.group(v6_2).length() > 0) {
                    v5_1 = v5.group(v6_2).split(",");
                }
                else {
                    v5_1 = new String[0];
                }

                this.param = v5_1;
            }
            else {
                goto label_88;
            }
        }

        return;
    label_88:
        v6 = new StringBuilder("Cannot work with that constructor: ");
        v6.append(v1_1);
        throw new RuntimeException(v6.toString());
    label_96:
        StringBuilder v7 = new StringBuilder("No box object found for ");
        v7.append(arg5);
        throw new RuntimeException(v7.toString());
    }
}

