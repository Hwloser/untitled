package com.hwloser.thrift.service;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService.Iface {
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private final static String userName = "hwloser";

  @Override
  public String getName(int id) throws TException {
    logger.info("received getName, id = {}:", id);
    return userName;
  }
  @Override
  public boolean isExist(String name) throws TException {
    logger.info("receive isExist, name = {}", name);
    return userName.equals(name);
  }
}
