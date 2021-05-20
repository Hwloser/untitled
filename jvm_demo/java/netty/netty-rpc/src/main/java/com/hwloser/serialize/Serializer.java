package com.hwloser.serialize;

public interface Serializer {

  /**
   * @param object Will serialized object
   */
  byte[] serialize(Object object);

  /**
   * @param clazz class tag of bytes
   * @param <T> erased objets class type
   */
  <T> T deserialize(Class<T> clazz, byte[] bytes);
}
