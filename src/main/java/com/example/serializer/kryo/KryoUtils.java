package com.example.serializer.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * The kryo utils used by dubbo
 *
 * @since 2.6.0
 */
public class KryoUtils {

    private static AbstractKryoFactory kryoFactory = new PooledKryoFactory();

    public static Kryo get() {
        return kryoFactory.getKryo();
    }

    public static void release(Kryo kryo) {
        kryoFactory.returnKryo(kryo);
    }

    public static void register(Class<?> clazz) {
        kryoFactory.registerClass(clazz);
    }

    public static void setRegistrationRequired(boolean registrationRequired) {
        kryoFactory.setRegistrationRequired(registrationRequired);
    }


    /**
     * 将字节数组反序列化为原对象
     *
     * @param clazz
     *            原对象的 Class
     * @param <T>
     *            原对象的类型
     * @return 原对象
     */
    public static <T> T readObjectFromInputStream(InputStream inputStream, Class<T> clazz) {
        Kryo kryo = get();
        Input input = new Input(inputStream);
        T t = kryo.readObject(input, clazz);
        release(kryo);
        return t;
    }

    /**
     * 将对象【及类型】序列化为字节数组
     *
     * @param obj
     *            任意对象
     * @param <T>
     *            对象的类型
     * @return 序列化后的字节数组
     */
    public static <T> byte[] writeToByteArray(T obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        Kryo kryo = get();
        kryo.writeClassAndObject(output, obj);
        output.flush();
        release(kryo);
        return byteArrayOutputStream.toByteArray();
    }

}
