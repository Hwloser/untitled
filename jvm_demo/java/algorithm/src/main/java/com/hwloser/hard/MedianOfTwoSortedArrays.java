package com.hwloser.hard;

import java.util.Arrays;
import java.util.Random;

public class MedianOfTwoSortedArrays {
  public static void main(String[] args) {

    MedianOfTwoSortedArrays c = new MedianOfTwoSortedArrays();

    int[] a = mockNums(8);
    int[] b = mockNums(12);

    Arrays.sort(a);
    Arrays.sort(b);

    System.out.println(Arrays.toString(a));
    System.out.println(Arrays.toString(b));

    a = new int[]{1, 3, 4, 9};
    b = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    double s = c.findMedianSortedArrays(a, b);

    System.out.println(s);
  }

  private static int[] mockNums(int length) {
    Random r = new Random();
    int[] ints = new int[length];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = r.nextInt(10);
    }
    return ints;
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int length1 = nums1.length, length2 = nums2.length;

    int totalLength = length1 + length2;

    if (totalLength % 2 == 1) { /* 奇数 */
      int midIndex = totalLength / 2;

      return getKthElement(nums1, nums2, midIndex + 1);
    } else { /* 偶数 */
      int midIndex1 = totalLength / 2 - 1;
      int midIndex2 = totalLength / 2;

      double median1 = getKthElement(nums1, nums2, midIndex1 + 1);
      double median2 = getKthElement(nums1, nums2, midIndex2 + 1);

      return (median1 + median2) / 2.0;
    }
  }

  public int getKthElement(int[] nums1, int[] nums2, int k) {
    int length1 = nums1.length, length2 = nums2.length;
    int index1 = 0, index2 = 0;

    while (true) {
      if (index1 == length1) {
        return nums2[index2 + k - 1];
      }
      if (index2 == length2) {
        return nums1[index1 + k - 1];
      }
      if (k == 1) {
        return Math.min(nums1[index1], nums2[index2]);
      }


      int half = k / 2;
      int newIndex1 = Math.min(index1 + half, length1) - 1;
      int newIndex2 = Math.min(index2 + half, length2) - 1;

      int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];

      if (pivot1 <= pivot2) {
        k -= (newIndex1 - index1 + 1);
        index1 = newIndex1 + 1;
      } else {
        k -= (newIndex2 - index2 + 1);
        index2 = newIndex2 + 1;
      }

    }

  }
}
