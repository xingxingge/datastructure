package com.topsec.tsm.datastructure.sorter;

import com.topsec.tsm.datastructure.strategy.Strategy;


public class MergeSorter<E>  extends Sorter<E>{


  public MergeSorter(){
    super();
  }
  public MergeSorter(Strategy strategy){
    super(strategy);
  }

  @Override
  public E[] doSort(E[] objs) {
    return mergeSort(objs,0,objs.length-1);
  }

  /**
   * 归并排序-输入:数据元素数组 a,a待合并的两个有序区间[p..q]以及[q+1..r] 输出:将两个有序区间合并为一个有序区间
   */

  public void merge(E[] a, int p, int q, int r) {
    // { 11 13 15 23 26 7 12 13 18 28 }
    // {1,3,9,10,12 3,5,9,13,16}  [1, 1, 3, 10, 80, 3, 5, 9, 13, 16, 18, 20]
    // {1, 10,12,12,18, 3,5,9,13,16, 18,20};
    E[] b = (E[]) new Object[r - p + 1];// 临时数组
    int k = p, l = q + 1, n = 0;
    while (k <= q && l <= r) {
      if (getStrategy().compare(a[k], a[l]) <= 0) {
        while (k <= q && getStrategy().compare(a[k], a[l]) <= 0) {
          b[n++] = a[k++];
        }
      } else { // 找到比前面序列大的数，或者k超出
        while (l <= r && getStrategy().compare(a[k], a[l]) > 0) {
          b[n++] = a[l++];
        }
        b[n++] = a[k++]; //找到位置，把k放入
      }
    }
    while (k <= q) {
      b[n++] = a[k++];
    }
    while (l <= r) {
      b[n++] = a[l++];
    }
    // 返回数组
    for (int i = 0; i < b.length; i++) {
      a[p + i] = b[i];
    }
  }

  public void merge2(E[] a, int p, int q, int r) {
    // { 11 13 15 23 26 7 12 13 18 28 }
    // {1,3,9,10,12 3,5,9,13,16}  [1, 1, 3, 10, 80, 3, 5, 9, 13, 16, 18, 20]
    // {1, 10,12,12,18, 3,5,9,13,16, 18,20};
    E[] b = (E[]) new Object[r - p + 1];// 临时数组
    int k = p, l = q + 1, n = 0;
    while (k <= q && l <= r) {
      if (getStrategy().compare(a[k], a[l]) <= 0) {
        b[n++] = a[k++];
      } else { // 找到比前面序列大的数，或者k超出
        b[n++] = a[l++];
      }
    }
    while (k <= q) b[n++] = a[k++];
    while (l <= r) b[n++] = a[l++];
    // 返回数组
    for (int i = 0; i < b.length; i++) {
      a[p + i] = b[i];
    }
  }

  //归并排序递归实现
  public E[] mergeSort(E[] r, int low, int high) {
    if (low < high) {
      mergeSort(r, low, (high + low) / 2);
      mergeSort(r, (high + low) / 2 + 1, high);
      merge2(r, low, (high + low) / 2, high);
    }
    return r;
  }
}
