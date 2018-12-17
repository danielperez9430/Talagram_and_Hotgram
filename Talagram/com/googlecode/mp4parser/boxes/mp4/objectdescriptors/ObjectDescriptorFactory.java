package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectDescriptorFactory {
    protected static Map descriptorRegistry;
    protected static Logger log;

    static {
        HashMap v4_1;
        ObjectDescriptorFactory.log = Logger.getLogger(ObjectDescriptorFactory.class.getName());
        ObjectDescriptorFactory.descriptorRegistry = new HashMap();
        HashSet v0 = new HashSet();
        ((Set)v0).add(DecoderSpecificInfo.class);
        ((Set)v0).add(SLConfigDescriptor.class);
        ((Set)v0).add(BaseDescriptor.class);
        ((Set)v0).add(ExtensionDescriptor.class);
        ((Set)v0).add(ObjectDescriptorBase.class);
        ((Set)v0).add(ProfileLevelIndicationDescriptor.class);
        ((Set)v0).add(AudioSpecificConfig.class);
        ((Set)v0).add(ExtensionProfileLevelDescriptor.class);
        ((Set)v0).add(ESDescriptor.class);
        ((Set)v0).add(DecoderConfigDescriptor.class);
        Iterator v0_1 = ((Set)v0).iterator();
        while(v0_1.hasNext()) {
            Object v1 = v0_1.next();
            Annotation v2 = ((Class)v1).getAnnotation(Descriptor.class);
            int[] v3 = ((Descriptor)v2).tags();
            int v2_1 = ((Descriptor)v2).objectTypeIndication();
            Object v4 = ObjectDescriptorFactory.descriptorRegistry.get(Integer.valueOf(v2_1));
            if(v4 == null) {
                v4_1 = new HashMap();
            }

            int v5 = v3.length;
            int v6;
            for(v6 = 0; v6 < v5; ++v6) {
                ((Map)v4_1).put(Integer.valueOf(v3[v6]), v1);
            }

            ObjectDescriptorFactory.descriptorRegistry.put(Integer.valueOf(v2_1), v4_1);
        }
    }

    public ObjectDescriptorFactory() {
        super();
    }

    public static BaseDescriptor createFrom(int arg6, ByteBuffer arg7) {
        Logger v2_1;
        UnknownDescriptor v2_2;
        int v0 = IsoTypeReader.readUInt8(arg7);
        Object v1 = ObjectDescriptorFactory.descriptorRegistry.get(Integer.valueOf(arg6));
        if(v1 == null) {
            v1 = ObjectDescriptorFactory.descriptorRegistry.get(Integer.valueOf(-1));
        }

        v1 = ((Map)v1).get(Integer.valueOf(v0));
        if(v1 == null || (((Class)v1).isInterface()) || (Modifier.isAbstract(((Class)v1).getModifiers()))) {
            v2_1 = ObjectDescriptorFactory.log;
            StringBuilder v3_1 = new StringBuilder("No ObjectDescriptor found for objectTypeIndication ");
            v3_1.append(Integer.toHexString(arg6));
            v3_1.append(" and tag ");
            v3_1.append(Integer.toHexString(v0));
            v3_1.append(" found: ");
            v3_1.append(v1);
            v2_1.warning(v3_1.toString());
            v2_2 = new UnknownDescriptor();
        }
        else {
            try {
                Object v2 = ((Class)v1).newInstance();
            }
            catch(Exception v7) {
                v2_1 = ObjectDescriptorFactory.log;
                Level v3 = Level.SEVERE;
                StringBuilder v4 = new StringBuilder("Couldn\'t instantiate BaseDescriptor class ");
                v4.append(v1);
                v4.append(" for objectTypeIndication ");
                v4.append(arg6);
                v4.append(" and tag ");
                v4.append(v0);
                v2_1.log(v3, v4.toString(), ((Throwable)v7));
                throw new RuntimeException(((Throwable)v7));
            }
        }

        ((BaseDescriptor)v2_2).parse(v0, arg7);
        return ((BaseDescriptor)v2_2);
    }
}

