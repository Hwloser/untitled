package com.hwloser.priority.queue;


/**
 * only cache char array
 */
public class PriorityQueue
    <Key extends Comparable<Key>> {

  private Key[] caches;
  // 用于存储 caches 中已经存在的元素的数量
  private int size = 0;

  @SuppressWarnings("unchecked")
  PriorityQueue(int capacity) {
    caches = (Key[]) new Comparable[capacity + 1];
  }

  public Key max() {
    return caches[1]; /* 0 存放标记为 */
  }

  /* operation */

  public void insert(Key e) {
    size++;
    caches[size] = e;
    swim(size);
  }

  public Key delMax() {
    // 最大堆的堆顶就是最大的元素
    Key max = caches[1];
    exchange(1, size);

    caches[size] = null; /* 清理最大的值 */
    size--;

    sink(1);
    /* 因为此时已经将最后一个值转移至此,
    这样就不需要调整数组的大小了 */
    return max;
  }

  public void swim(int index) {
    while (index > 1 && lessThen(parent(index), index)) {
      exchange(parent(index), index);
      index = parent(index);
    }
  }
  public void sink(int index) {
    while (leftChild(index) <= size) {
      int olderIndex = leftChild(index);

      int rightIndex = rightChild(index);
      if (rightIndex <= size && lessThen(olderIndex, rightIndex)) {
        olderIndex = rightChild(index);
      }

      if (lessThen(olderIndex, index)) {
        break;
      }

      exchange(index, olderIndex);
      index = olderIndex;
    }
  }

  /* exchange left and right value*/
  private void exchange(int left, int right) {
    Key temp = caches[left];
    caches[left] = caches[right];
    caches[right] = temp;
  }
  /* is left less right */
  private boolean lessThen(int left, int right) {
    return caches[left].compareTo(caches[right]) < 0;
  }

  /**
   * root 的父节点的索引
   */
  private int parent(int root) {
    return root / 2;
  }
  /**
   * 左节点的索引
   */
  private int leftChild(int root) {
    return root * 2;
  }
  /**
   * 右节点的索引
   */
  private int rightChild(int root) {
    return (root * 2) + 1;
  }

}
