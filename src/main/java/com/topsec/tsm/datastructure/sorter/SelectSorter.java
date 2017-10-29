package com.topsec.tsm.datastructure.sorter;

import com.topsec.tsm.datastructure.strategy.Strategy;


/**
 * Created by hx on 16-6-7.
 */
public class SelectSorter<E> extends Sorter<E> {

  public SelectSorter(){
    super();
  }
  public SelectSorter(Strategy strategy){
    super(strategy);
  }

  @Override
  public E[] doSort(E[] objs) {
    return heapSort(objs);
  }

  /**
   * 选择类-简单选择排序hign-1-low+1，这种方式交换数据较多
   **/
  public E[] selectSort(E[] r, int low, int high) {
    for (int i = low; i < high; i++) {// 控制排序趟数,一共是hign-low+1-1趟
      for (int j = i + 1; j <= high; j++) {
        if (getStrategy().compare(r[i], r[j]) > 0) {
          E temp = r[i];
          r[i] = r[j];
          r[j] = temp;
        }
      }
    }
    return r;
  }

  /**
   * 选择类-简单选择排序hign-1-low+1,交换下标，效率比之前那个高
   **/
  public E[] selectSort2(E[] r, int low, int high) {
    for (int i = low; i <= high - 1; i++) {// 控制排序趟数,一共是hign-low+1-1趟
      int min = i;
      for (int j = min + 1; j <= high; j++) {
        if (getStrategy().compare(r[min], r[j]) > 0) {
          min = j; // 找到最小值的下标
        }
      }
      if (min != i) {
        E temp = r[i];
        r[i] = r[min];
        r[min] = temp;
      }
    }
    return r;
  }

  /**
   * 选择类-堆排序
   **/
  public E[] heapSort(E[] r, int low, int high) {
    // 初始化堆
    int n = high - low + 1 - 1;
    for (int i = low + n / 2; i >= low; i--) {
      heapAdjust(r, i, high);
    }
    // 调整根节点
    for (int i = high; i > low; i--) {
      E temp = r[low];
      r[low] = r[i];
      r[i] = temp;
      heapAdjust(r, low, i - 1);
    }
    return r;
  }


  /**
   * 输入:数据元素数组 r,数组 r 的待调整区间[low..high] 输出:调整 r[low..high]使之成为大顶堆
   */
  public void heapAdjust(E[] r, int low, int high) {
    E temp = r[low];
    for (int j = 2 * low; j <= high; j = j * 2) {
      // 沿关键之较大的元素向下进行筛选
      // j 指向关键值较大的元素
      if (j < high && getStrategy().compare(r[j], r[j + 1]) < 0) j++;
      // 若 temp 比其孩子都大,则插入到 low 所指位置
      if (getStrategy().compare(temp, r[j]) >= 0) break;// 每个值和temp比较，小于temp就跳出
      r[low] = r[j];
      low = j; // 向下筛选
    }
    r[low] = temp; // 把low的元素的值置为temp
  }

  // 实现初始化建堆和排序的过程。

  public E[] heapSort(E[] r) {
    int n = r.length - 1;
    for (int i = n / 2; i >= 1; i--)
      // 第一个非终端节点
      // 初始化建堆
      heapAdjust(r, i, n);
    for (int i = n; i > 1; i--) {
      // 不断输出堆顶元素并调整 r[1..i-1]为新堆
      E temp = r[1];
      // 交换堆顶与堆底元素
      r[1] = r[i];
      r[i] = temp;
      heapAdjust(r, 1, i - 1);
      // 调整
    }
    return r;
  }


  /**
   * 选择类-锦标赛(树形选择)排序
   **/
  public E[] tournamentSort(E[] r, int low, int high) {
    // 构造一颗完全二叉树,叶子节点是n个，所以高度是log(n)
    // 二叉树的高度是logN
    int height = (int) Math.log(r.length);
    System.out.println(height);
    return r;
  }



}
