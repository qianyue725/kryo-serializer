package com.example.serializer;

import com.esotericsoftware.kryo.Serializer;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class SerializableClassRegistry {


    private static final Map<Class, Object> registrations = new LinkedHashMap<>();

    /**
     * only supposed to be called at startup time
     */
    public static void registerClass(Class clazz) {
        registerClass(clazz, null);
    }

    /**
     * only supposed to be called at startup time
     */
    public static void registerClass(Class clazz, Serializer serializer) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class registered to kryo cannot be null!");
        }
        registrations.put(clazz, serializer);
    }

    public static Map<Class, Object> getRegisteredClasses() {
        return registrations;
    }
}
