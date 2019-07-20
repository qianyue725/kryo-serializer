package com.example.serializer.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.example.serializer.Serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 描述:
 *
 * @author qianyue
 * @date 2019-07-18 21:09
 */
public class KryoSerialization implements Serialization {

    @Override
    public void serialize(OutputStream output, Object object) throws IOException {
        Kryo kryo = KryoUtils.get();
        Output out = new Output(output);
        kryo.writeClassAndObject(out, object);
        out.flush();
        out.close();
        KryoUtils.release(kryo);
    }

    @Override
    public Object deserialize(InputStream input) throws IOException {
        Kryo kryo = KryoUtils.get();
        Input in = new Input(input);
        Object result = kryo.readClassAndObject(in);
        in.close();
        KryoUtils.release(kryo);
        return result;
    }

    @Override
    public Object deserialize(byte[] buf, int offset, int length) throws IOException {
        Kryo kryo = KryoUtils.get();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buf, offset, length);
        Input in = new Input(inputStream);
        Object result = kryo.readClassAndObject(in);
        in.close();
        KryoUtils.release(kryo);
        return result;
    }

    @Override
    public Object deserialize(byte[] buf) throws IOException {
        Kryo kryo = KryoUtils.get();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buf);
        Input in = new Input(inputStream);
        Object result = kryo.readClassAndObject(in);
        in.close();
        KryoUtils.release(kryo);
        return result;
    }
}