package com.hwloser.protocol;

import lombok.Data;

@Data
public class RpcResponse {
  private String responseId;
  private Object result;
}
