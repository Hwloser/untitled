package com.hwloser;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AStar {
  public static void main(String[] args) {
    PointNode[][] map = constructRandomSquareMap(16);
    printMap(map);


  }

  public static void aStartAlgorithm(int[][] map) {

    Set<Integer> openSets = new HashSet<>();
    Set<Integer> closeSets = new HashSet<>();


  }

  /**
   * 曼哈顿距离 -- 只允许上下左右四个方向移动
   *
   * @param D         指的是相邻节点（node）之间的移动代价，通常是一个固定的常量
   * @param pointNode 当前节点
   * @param goal      目标节点
   * @return 移动距离
   */
  private static double heuristic_1(double D, PointNode pointNode, PointNode goal) {
    int dx = Math.abs(pointNode.x - goal.x);
    int dy = Math.abs(pointNode.y - goal.y);
    return D * (dx + dy);
  }

  /**
   * 对角距离
   * <p>
   * 如果 所有节点都是正方形， D2的值就是 （√2 * D）
   * </p>
   *
   * @param D         相邻节点直接的移动代价
   * @param D2        指的是两个斜对角相邻节点之间的移动代价
   * @param pointNode 当前节点
   * @param goal      目标节点
   * @return 移动距离
   */
  private static double heuristic_2(double D, int D2, PointNode pointNode, PointNode goal) {
    int dx = Math.abs(pointNode.x - goal.x);
    int dy = Math.abs(pointNode.y - goal.y);
    return D * (dx + dy) + (D2 - 2 * D) * Math.min(dx, dy);
  }

  /**
   * 欧几里得距离
   * <p>
   * 欧几里得距离指的是两节点之间的 直线距离 计算公式 √ ( ( p^(2.x) - p^(1.x))^2  + ( p^(2.y) - p^(1.y) )^2 )
   * </p>
   *
   * @param D         相邻节点的移动代价
   * @param pointNode 当前节点
   * @param goal      目标节点
   * @return 移动距离
   */
  private static double heuristic_3(double D, PointNode pointNode, PointNode goal) {
    int dx = Math.abs(pointNode.x - goal.x);
    int dy = Math.abs(pointNode.y - goal.y);
    return D * Math.sqrt(dx * dx + dy * dy);
  }


  private static void printMap(PointNode[][] map) {
    for (PointNode[] ints : map) {
      for (PointNode anInt : ints) {
        System.out.print(anInt.isObstacle() ? "X" : 0);
        System.out.print("\t");
      }
      System.out.println();
    }
  }

  private static PointNode[][] constructRandomSquareMapWithObstacle(int sideLength) {
    PointNode[][] map = new PointNode[sideLength][sideLength];

    return map;
  }

  private static PointNode[][] constructRandomSquareMap(int sideLength) {
    Random r = new Random();
    PointNode[][] map = new PointNode[sideLength][sideLength];
    for (int i = 0; i < map.length; i++) {
      map[i] = new PointNode[sideLength];
      for (int j = 0; j < map[i].length; j++) {
        int trueValue = r.nextBoolean() ? 1 : 0;
        int x = i;
        int y = j;
        map[i][j] = new PointNode(trueValue, x, y);
      }
    }
    return map;
  }

  private static class PointNode {
    private final int trueValue;

    private final int x;
    private final int y;

    private int cost; /* 初始值为 代价最高 */

    public PointNode(int trueValue, int x, int y) {
      this.trueValue = trueValue;

      this.x = x;
      this.y = y;
      this.cost = Integer.MAX_VALUE;
    }

    public boolean isObstacle() {
      return trueValue != 0;
    }

    /**
     * 用于调整 point cost
     */
    public void setCost(int cost) {
      this.cost = cost;
    }

    public int getTrueValue() {
      return trueValue;
    }

    @Override
    public String toString() {
      //      return String.format("trueValue:%s , x=%s , y=%s", trueValue, x, y);
      return String.format("%s,%s", x, y);
    }
  }
}
