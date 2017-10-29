package com.topsec.tsm.datastructure.sorter;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
// 按照方法名字顺序来执行测试
public class SorterTest {

  public static Integer[] objs = new Integer[] {9,9, 8,8, 7, 6, 5, 4, 3, 2, 1, 0};
  private Date start;
  private Date end;
  ExchangeSorter<Integer> exchangeSorter = new ExchangeSorter<>();
  Sorter<Integer> exchangeSorter1 = new ExchangeSorter<>();
  SelectSorter<Integer> selectSorter = new SelectSorter<>();
  MergeSorter<Integer> mergeSorter = new MergeSorter<>();
  InsertSorter<Integer> insertSorter = new InsertSorter<>();

  @BeforeClass
  public static void before() {
    System.out.println("\nBegin to test Class SorterTest...");
    System.out.println("待排序数组：");
    for (Object object : objs) {
      System.out.print(object + "\t");
    }
    System.out.println("");
  }

  @AfterClass
  public static void after() {
    System.out.println("\nSorterTest testing over...");
  }

  // 用于初始化测试用例
  @Before
  public void setUp() throws Exception {
    System.out.println("\nBegin to test...");
    objs = new Integer[] {9, 9,8,8, 7, 6, 5, 4, 3, 2, 1, 0};
    System.out.println("排序前数组：");
    for (Object object : objs) {
      System.out.print(object + "\t");
    }
    System.out.println("");
    start = new Date();
  }

  @After
  public void tearDown() throws Exception {
    end = new Date();
    System.out.println("排序后数组：");
    for (Object object : objs) {
      System.out.print(object + "\t");
    }
    System.out.println("\n耗时：" + (end.getTime() - start.getTime()) + "ms");
    System.out.println("The testing is over...");
  }

  // 直接插入排序
  @Test
  public void ainsertSortTest() {
//    objs=new Integer[]{0};
//    objs=new Integer[]{3,6,7,8,8,8,9,11,8,4,3};
    objs=new Integer[]{6,8,9,11,7,4,3};
//    objs = insertSorter.insertSort(objs, 0, objs.length - 1);
    objs = insertSorter.binSearchSort(objs, 0, objs.length - 1);
    System.out.print("直接插入法");
    // System.out.println(Arrays.asList(result));
    // Assert.assertEquals(obj, result);
  }

  @Test
  // 折半插入排序
  public void bbinSearchSortTest() {
    objs = insertSorter.binSearchSort(objs, 0, objs.length - 1);
    System.out.print("折半插入法");
  }

  // 希尔插入排序
  @Test
  public void cshellSortTest() {
    int[] delta = new int[] {5, 3, 1};
    objs = insertSorter.shellSort(objs, 0, objs.length - 1, delta);
    System.out.print("希尔插入法");
  }

  // 冒泡排序
  @Test
  public void dbubbleSortTest() {
    objs = exchangeSorter.bubbleSort(objs, 0, objs.length - 1);
    System.out.print("冒泡排序法");
  }

  // 快速排序
  @Test
  public void equickSortTest() {
    // obj = new Object[]{26,53,48,15,13,46,32,15};
    // obj = new Object[] {0,1,2,3,4,5,6,7,8,9};
    System.out.print("快速排序法");
    objs = exchangeSorter1.sort(objs);
  }
  // 快速排序
  @Test
  public void equickSort2Test() {
    objs = new Integer[] {26, 53, 48, 15, 13, 46, 32, 15};
    // obj = new Object[]{26,53,48,15,13,46,32,15};
    // obj = new Object[] {0,1,2,3,4,5,6,7,8,9};
    System.out.print("快速排序法");
    objs = exchangeSorter.quickSort(objs,false);
  }

  @Test
  // 简单选择排序
  public void fselectSortTest() {
    System.out.print("简单选择排序法");
    objs = selectSorter.selectSort2(objs, 0, objs.length - 1);
  }

  @Test
  @Ignore("这个方法还未实现")
  // 锦标赛选择排序
  public void gtournamentSortTest() {
    System.out.print("锦标赛选择排序法");
    objs = selectSorter.tournamentSort(objs, 0, objs.length - 1);
  }

  @Test
  public  void heapSortTest() {
    System.out.print("堆排序法");
    objs=new Integer[]{0,11,36,26,28,20,17,42,53};
    objs=selectSorter.heapSort(objs);
  }

  @Test
  @Ignore
  public  void heapSortSetionTest() {
    System.out.print("堆排序法（指定区间排序）");
    objs=new Integer[]{0,28,26,17,36,20,42,11,18};
    //预期结果0,28, 11 17 20 26 36,18
    objs=selectSorter.heapSort(objs,2,7);
  }

  //归并排序
  @Test
  @Ignore
  public  void imergeTest() {
    System.out.print("归并排序");
    objs=new Integer[]{1, 1,3,10,80, 3,5,9,13,16, 18,20};
//    objs=new Object[]{1, 10,12,12,18, 3,5,9,13,16, 18,20};
//    objs=new Object[]{10,80,3};
    //预期结果0,28, 11 17 20 26 36,18
    mergeSorter.merge(objs,1,4,9);
//    Sorter.merge(objs,0,1,2);
  }

  @Test
  public  void imergeSortTest() {
    System.out.print("归并排序测试");
//    objs=new Object[]{1, 1,3,10,80, 3,5,9,13,16, 18,20};
    objs=new Integer[]{4, 8, 9, 5, 2, 1, 4, 6};
    //预期结果0,28, 11 17 20 26 36,18
    mergeSorter.mergeSort(objs,0,7);
//    Sorter.merge3(objs,3,4,5);
  }



  @Test
  @Ignore("先不测试这个方法")
  public void sorterTest() throws Exception {
    for (Object i : objs) {
      System.out.print(i + "\t");
    }
    System.out.println("\n");
  }
}
