package com.example.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 描述:
 *
 * @author
 * @date 2019-07-18 21:09
 */
public interface Serialization {

    void serialize(OutputStream output, Object object) throws IOException;

    Object deserialize(InputStream input) throws IOException;

    Object deserialize(byte buf[], int offset, int length) throws IOException;

    Object deserialize(byte buf[]) throws IOException;
}