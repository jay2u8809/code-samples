package com.jay2u8809.codesamples.individual.study.java.serialize;

import org.apache.commons.lang3.SerializationException;

import java.io.*;

/**
 * Serialize: 현재 시스템의 데이터를 다른 시스템에서 사용할 수 있는 데이터로 변환하는 것
 *   - Java 시스템 내에서 사용되는 객체를 다른 Java 시스템에서 사용할 수 있도록 byte 형태로 데이터를 변환하는 것
 * Deserialize: 변환된 전달된 데이터를 다시 현재의 시스템에서 사용할 수 있는 데이터로 변환하는 것
 *   - byte 형태로 변환된 데이터를 다시 객체로 변환하는 기술
 * Object <-> Byte[]
 */

public class SerializeUtil {

    public static <T> byte[] serialize(T obj) {
        byte[] byteArray;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            byteArray = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException();
        }

        return byteArray;
    }

    public static <T> T deserialize(byte[] beforeObjectByteArray) {
        T afterObj;

        try (ByteArrayInputStream bais = new ByteArrayInputStream(beforeObjectByteArray); ObjectInputStream ois = new ObjectInputStream(bais)) {
            afterObj = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SerializationException();
        }
        return afterObj;
    }
}
