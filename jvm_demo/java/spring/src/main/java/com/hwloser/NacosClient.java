package com.hwloser;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.client.config.listener.impl.PropertiesListener;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.FileWriter;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NacosClient implements InitializingBean {

  public static void main(String[] args) {
    SpringApplication.run(NacosClient.class, args);
  }

  private final Logger logger = LoggerFactory.getLogger(NacosClient.class);

  @NacosInjected
  private ConfigService config;

  @Value("spring.nacos.config.data-id: huanwei")
  private String checkConfigDataId;
  @Value("spring.nacos.config.group: huanwei")
  private String checkConfigGroupId;

  @Value("spring.nacos.config.properties.file.path")
  private String checkConfigPropertiesFilePath;

  @Override
  public void afterPropertiesSet() throws Exception {
    if (config == null) {
      logger.warn("nacos config is null, disable nacos refresh check service!!!");
      return;
    }
    config.addListener(checkConfigDataId, checkConfigGroupId, new PropertiesListener() {
      @Override
      public Executor getExecutor() {
        ThreadFactory namedFactory = new ThreadFactoryBuilder()
            .setDaemon(true)
            .setNameFormat("nacos refresh service")
            .build();
        return new ScheduledThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            namedFactory);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void innerReceive(Properties properties) {
        Optional.of(checkConfigPropertiesFilePath)
            .map(new Function<String, Object>() {
              @SneakyThrows
              @Override
              public Object apply(String path) {
                properties.store(new FileWriter(path), "refresh nacos config");
                return null;
              }
            });
      }
    });
  }

}
