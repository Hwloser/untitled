package com.hwloser.protocol;

import lombok.Data;

@Data
public class RpcRequest {
  private String requestId; /* request id */

  private String className; /* request class name */
  private String methodName; /* request method name under class name */

  private Class<?> parameterType;
  private Object[] parameters;

}
