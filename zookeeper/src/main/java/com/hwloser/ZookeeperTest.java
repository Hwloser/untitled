package com.hwloser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperTest {

  public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
    String connectString = "archlinux:2181";
    int sessionTimeout = 3000;

    ZooKeeper zk = new ZooKeeper(connectString, sessionTimeout, (watchedEvent -> {
      System.out.println("watch event attempt");
      System.out.println("watchedEvent -- " + watchedEvent);
    }));

    whoAmI(zk);

    String zNodePath = "/hwloser_root_path";
    createZNode(zk, zNodePath);

    System.out.println("create " + zNodePath);
    listZnode(zk);

    deleteZNode(zk, zNodePath);

    System.out.println("deleted :" + zNodePath);
    listZnode(zk);
  }

  private static void whoAmI(ZooKeeper zk) throws InterruptedException {
    System.out.println("zk.whoAmI() -- " + zk.whoAmI());
  }

  private static void listZnode(ZooKeeper zk) throws InterruptedException, KeeperException {
    List<String> list = zk.getChildren("/", false);
    System.out.println(list);
  }

  private static void createZNode(ZooKeeper zk, String ZNodePath)
      throws InterruptedException, KeeperException {
    if (Objects.isNull(zk.exists(ZNodePath, false))) {
      System.out.println("zNodePath is not exists");
      zk.create(
          ZNodePath,
          "hwloser_data".getBytes(StandardCharsets.UTF_8),
          Ids.OPEN_ACL_UNSAFE,
          CreateMode.PERSISTENT
      );
    }
  }

  private static void deleteZNode(ZooKeeper zk, String zNodePath)
      throws InterruptedException, KeeperException {
    if(Objects.nonNull(zk.exists(zNodePath, false))) {
      zk.delete(zNodePath, 0);
    }
  }
}
