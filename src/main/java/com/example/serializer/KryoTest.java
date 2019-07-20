package com.example.serializer;

import com.example.serializer.kryo.KryoSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author qianyue
 * @date 2019-07-20 上午10:48
 */
public class KryoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KryoTest.class);

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        User user = new User();
        user.setUserName("test");
        user.setAge(18);
        user.setLikeList(list);

        KryoSerialization kryoSerialization = new KryoSerialization();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        kryoSerialization.serialize(output, user);
        byte[] bytes = output.toByteArray();

        Object deserialize = kryoSerialization.deserialize(bytes);
        LOGGER.debug("result: {}", deserialize);


        /*Kryo kryo = KryoUtils.get();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Output out = new Output(output);
        kryo.writeClassAndObject(out, user);
        out.close();
        byte[] bytes = output.toByteArray();

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        Input in = new Input(input);
        User result = (User) kryo.readClassAndObject(in);
        in.close();

        KryoUtils.release(kryo);

        System.out.println(result);*/
    }
}