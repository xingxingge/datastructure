package com.topsec.tsm.datastructure.sorter;

import com.topsec.tsm.datastructure.strategy.Strategy;


/**
 * Created by hx on 16-6-7.
 */
public class InsertSorter<E> extends Sorter<E> {

  public InsertSorter() {
    super();
  }

  public InsertSorter(Strategy strategy) {
    super(strategy);
  }

  @Override
  public E[] doSort(E[] objs) {
    return binSearchSort(objs,0,objs.length-1);
  }

  /**
   * 插入类-直接插入排序方法2,易懂
   **/
  public E[] insertSort(E[] x, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
      if (getStrategy().compare(x[i], x[i - 1]) < 0) {
        E temp = x[i];
         x[i] = x[i - 1];
        int j = i - 2;// 为了避免最低那位溢出，所以用i-2
        for (; j >= low && (getStrategy().compare(x[j], temp)) > 0; j--) {
          x[j + 1] = x[j];
        }
        x[j + 1] = temp;// 插入到正确的位置
      }
    }
    return x;
  }

  /**
   * 插入类-直接插入排序（实际是交换方法）
   **/
  public int[] insertSort2(int[] x, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
      for (int j = low; j < i; j++) {
        if (x[j] > x[i]) {
          x[j] = x[j] ^ x[i];
          x[i] = x[j] ^ x[i];
          x[j] = x[j] ^ x[i];
        }
      }
    }
    return x;
  }

  /**
   * 插入类-折半插入法
   **/
  public E[] binSearchSort(E[] x, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
      if (getStrategy().compare(x[i],x[i-1])>=0)continue;
      E temp = x[i];
      int lo = low;// 低位
      int hi = i - 1; // 高位
      while (lo <= hi) {
        int mid = (lo + hi) / 2;
        if (getStrategy().compare(temp, x[mid]) < 0) { // temp比中间数小
          hi = mid - 1;
        } else {// temp大于等于中间数
          lo = mid + 1;
        }
      }
      // hi位置是找到的位置，开始移动数据
      int j=i-1;
      for (; j > hi; j--)
        // 移动元素
        x[j + 1] = x[j];
      x[hi + 1] = temp;// 插入到正确的位置high
    }
    return x;
  }

  /**
   * 插入类-希尔排序
   **/
  public E[] shellSort(E[] r, int low, int high, int[] delta) {
    for (int k = 0; k < delta.length; k++) {
      r = shellInsert(r, low, high, delta[k]);
    }
    return r;
  }

  // 序列排序，需要分几个序列，然后合并在一起
  private E[] shellInsert(E[] r, int low, int high, int deltaK) {
    for (int i = low + deltaK; i <= high; i++)
      if (getStrategy().compare(r[i], r[i - deltaK]) < 0) {
        // 小于时,需将 r[i] 插入有序表
        E temp = r[i];
        int j = i - deltaK;
        for (; j >= low && getStrategy().compare(temp, r[j]) < 0; j = j - deltaK)
          r[j + deltaK] = r[j]; // 记录后移
        r[j + deltaK] = temp; // 插入到正确位置

      }
    return r;
  }
}
