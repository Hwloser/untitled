package com.hwloser.nacos;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.client.config.listener.impl.PropertiesListener;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.channels.FileChannel;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NacosClient implements InitializingBean {

  public static void main(String[] args) {
    SpringApplication.run(NacosClient.class, args);
  }

  private final Logger logger = LoggerFactory.getLogger(NacosClient.class);

  @Autowired
  private NacosConfigManager nacosConfigManager;

  @Value("${spring.nacos.config.data-id:huanwei.properties}")
  private String checkConfigDataId;
  @Value("${spring.nacos.config.group:huanwei}")
  private String checkConfigGroupId;

  @Value("${spring.nacos.config.properties.file.path:/tmp/config}")
  private String checkConfigPropertiesFilePath;

  @Override
  public void afterPropertiesSet() throws Exception {
    if (nacosConfigManager == null) {
      logger.warn("nacos config manager is null, disable nacos refresh check service!!!");
      return;
    }

    nacosConfigManager.getConfigService()
        .addListener(checkConfigDataId, checkConfigGroupId, new PropertiesListener() {
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
          @SneakyThrows
          @Override
          public void innerReceive(Properties properties) {
            logger.info("refresh nacos config, locate:{}", checkConfigPropertiesFilePath);
            File checkConfigPropertiesFile = new File(checkConfigPropertiesFilePath);
            try (FileChannel fileChannel = FileChannel.open(checkConfigPropertiesFile.toPath())) {
              fileChannel.lock();
              properties.store(
                  new BufferedWriter(
                      new FileWriter(checkConfigPropertiesFile)),
                  // the first line is required comments
                  "refresh nacos config");
            }
          }
        });
  }

}
