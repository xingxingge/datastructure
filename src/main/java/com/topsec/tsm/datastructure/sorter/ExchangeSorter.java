package com.topsec.tsm.datastructure.sorter;

import com.topsec.tsm.datastructure.strategy.Strategy;

import java.util.Random;

/**
 * Created by hx on 16-6-7.
 */
public class ExchangeSorter<E> extends Sorter<E> {

  public ExchangeSorter(){
    super();
  }
  public ExchangeSorter(Strategy strategy){
    super(strategy);
  }

  @Override
  public E[] doSort(E[] objs) {
    return quickSort(objs,true);
  }

  /** 冒泡**/

  public E[] bubbleSort(E[] r) {
    if (r==null || r.length<1){
      return r;
    }
    return bubbleSort(r, 0, r.length - 1);
  }

  public E[] quickSort(E[] r,boolean randomPivot){
    return quickSort(r,0,r.length-1,randomPivot);
  }

  public E[] bubbleSort(E[] r, int low, int high) {
    int n = high - low + 1; // 数组长度
    for (int i = 1; i <= n - 1; i++) { // 比较n-1趟
      for (int j = low; j <= high - i; j++) {
        if (getStrategy().compare(r[j], r[j + 1]) > 0) {
          E temp = r[j];
          r[j] = r[j + 1];
          r[j + 1] = temp;
        }
      }
    }
    return r;
  }

  /**交换类-**/
  private E[] quickSort(E[] r, int low, int high) {
    if (low < high) {
      int p = partition(r, low, high);
      quickSort(r, low, p - 1);
      quickSort(r, p + 1, high);
    }
    return r;
  }

  public E[] quickSort(E[] r, int low, int high, boolean randomPivot) {
    if (randomPivot && low < high) {
      int i = new Random().nextInt(high - low) + low;
      E temp = r[low];
      r[low] = r[i];
      r[i] = temp;
    }
    return quickSort(r, low, high);
  }

  /**快速排序找到中轴元素的算法**/
  private int partition(E[] r, int low, int high) {
    E first = r[low];
    while (low < high) {
      // 从高位查找第一个比first元素小的数,找到后让low等于这个数,退出
      while (high > low && getStrategy().compare(first, r[high]) <= 0) {
        high--;
      }
      r[low] = r[high];
      // 从低位开始查找第一个比first大的数,找到让high等于这个数,退出
      while (high > low && getStrategy().compare(first, r[low]) >= 0) {
        low++;
      }
      r[high] = r[low];
    }
    //中轴元素赋值
    r[low] = first;
    return low;
  }

}
