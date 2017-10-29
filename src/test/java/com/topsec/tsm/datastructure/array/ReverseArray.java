package com.topsec.tsm.datastructure.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by hx on 15-12-27.
 */
public class ReverseArray {

  public void swap(Integer[] array, int x, int y) {
    array[x] = array[x] ^ array[y];
    array[y] = array[x] ^ array[y];
    array[x] = array[x] ^ array[y];
  }

  //错误写法:不能让指向相同地址的元素使用异或运算进行交换!!
  public Integer[] reverseArryError(Integer[] array) {
    int first = 0;
    int last = array.length - 1;
    for (; first <= last; first++, last--) {
      swap(array, first, last);
    }
    return array;
  }

  //正确写法:
  public Integer[] reverseArryRight(Integer[] array) {
    int first = 0;
    int last = array.length - 1;
    for (; first < last; first++, last--) {
      swap(array, first, last);
    }
    return array;
  }


  @Test
  public void reverseArrayTest() {
    Integer[] arrays = new Integer[]{1, 2, 3, 4, 5};
    arrays = reverseArryError(arrays);
    System.out.println(Arrays.asList(arrays));
    arrays = new Integer[]{1, 2, 3, 4, 5};
    arrays = reverseArryRight(arrays);
    System.out.println(Arrays.asList(arrays));
    System.out.println(-50 << 1);
  }


  /**
   * 给定一个含有n个元素的整形数组a，再给定一个和sum，求出数组中满足给定和的所有元素组合
   */

  @Test
  public void testFind() {
    int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8,9};
    int n = array.length;
    int num = 1;
    while (num < (1 << array.length)) {
      int[] nums=getNums(num,array.length);
      if(getTotal(nums)==10){
        for (int i = 0; i <  nums.length; i++) {
          System.out.print(nums[i]+"\t");
        }
        System.out.println();
      }
      num++;
    }
  }

  //求数组元素的和
  private int getTotal(int[] nums) {
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
      i=i+nums[j];
    }
    return i;
  }

  //  获取数组中为1的元素
  private int[] getNums(int num, int n) {
    int[] array = new int[n+1];
    //取出每个为1的位
    int i = n-1;
    int mask = 1;
    while (i >=0) {
      int j = (num >> i) & mask;
      if (j == 1) {
        array[n-i]=n-i;
      }
      i--;
    }
    return array;

  }

  @Test
  public void  getNumsTest(){
    int num=10;
    int[] nums= getNums(20,8);
    System.out.println(getTotal(nums));
    for (int i = 0; i <  nums.length; i++) {
      System.out.print(nums[i]+"\t");
    }
  }


//    00000000----11111111
  //求出和为10的所有组合
  //先排序
  //找子集
  //二进制:1+9
}
