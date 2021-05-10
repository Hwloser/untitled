package com.hwloser;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Listener {
  public static void main(String[] args) {
    SpringApplication.run(Listener.class, args);
  }
}

@RestController
class Controller {

  @Value("${hypers.cdp.tag.label.map.path}")
  public String path;

  @RequestMapping("/test")
  public String test() {
    return path;
  }
}