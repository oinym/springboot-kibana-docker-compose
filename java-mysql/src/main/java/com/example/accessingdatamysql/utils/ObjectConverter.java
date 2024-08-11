package com.example.accessingdatamysql.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {
    public static String stringify(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = null;
        try {
            bytes = serialize(obj);

            return mapper.readValue(bytes, String.class);
        } catch (IOException e) {

            return bytes != null ? bytes.toString() : e.getMessage();
        }
    }

    static byte[] serialize(final Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
            out.flush();
            return bos.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
