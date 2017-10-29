package com.topsec.tsm.datastructure.searcher;


import java.util.List;

public class Searcher<E extends Comparable> {

  public int sequentialSearch(E key, E[] obj) {
    if (obj.length==0 || key==null) return 0;
    obj[0] = key;
    int i = obj.length - 1;
    while (key.compareTo(obj[i])!=0) {// 如果没找到元素，那么a[0]一定是key
      i--;
    }
    return i;
  }

  public int sequentialSearch(E key, List<E> obj) {
    E[] es= (E[]) obj.toArray();
    return  sequentialSearch(key,es);
  }

  public int binSearch(E[] s, int low, int high, E key) {
    while (low <= high) {
      int middle = (low + high) / 2;
      if (key.compareTo(s[middle]) == 0) {
        return middle;
      } else if (key.compareTo(s[middle]) > 0) {
        low = middle + 1;
      } else {
        high = middle - 1;
      }
    }
    return -1;
  }

  public int binSearch(E[] s, E key) {
    return binSearch(s,0,s.length-1,key);
  }

  /**
   * 二分查找递归算法
   */
  public int binSearchRecursion(E[] s,E key){
    return binSearchRecursion(s,0,s.length-1,key);

  }
  public int binSearchRecursion(E[] s, int low, int high, E key) {
    int mid = (low + high) / 2;
    if (low > high) {
      return -1;
    }
    if (key.compareTo(s[mid]) == 0) return mid;
    else if (key.compareTo(s[mid]) < 0) return binSearchRecursion(s, low, mid - 1, key);
    else return binSearchRecursion(s, mid + 1, high, key);
  }
}
