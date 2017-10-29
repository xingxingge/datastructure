package com.topsec.tsm.datastructure.searcher;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SearcherTest {

  private Searcher searcher = new Searcher();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void sequentialSearchTest() {
    Integer[] obj = new Integer[]{0,0,4, 5, 6, 7, 8, 9, 1};
    Integer key = 10;
    int result = searcher.sequentialSearch(key, obj);
    Assert.assertEquals(0, result);
  }
  @Test
  public void sequentialSearchTest2() {
    Integer[] obj = new Integer[]{0,0,4, 5, 6, 7, 8, 9, 1};
    List<Integer> list = Arrays.asList(obj);
    Integer key = 1;
    int result = searcher.sequentialSearch(key, list);
    Assert.assertEquals(8, result);
  }

  @Test
  public void binSearchTest() {
    Integer[] obj = new Integer[]{0, 4, 5, 6, 7, 8, 9, 10};
    Integer key = 5;
    int result = searcher.binSearch(obj, 0, obj.length - 1, key);
    Assert.assertEquals(2, result);
  }
  @Test
  public void binSearchTes2() {
    Integer[] obj = new Integer[]{0, 4, 5, 6, 7, 8, 9, 10};
    Integer key = 5;
    int result =searcher.binSearch(obj,key);
    Assert.assertEquals(2, result);
  }

  @Test
  public void binSearchRecursionTest(){
    Integer[] obj = new Integer[]{0, 4, 5, 6, 7, 8, 9, 10,13,16,19,26,34,37,59};
    Integer key = 59;
    int result = searcher.binSearchRecursion(obj, 0, obj.length - 1, key);
    System.out.println(result);

  }
  @Test
  public void binSearchRecursionTest2(){
    Integer[] obj = new Integer[]{0, 4, 5, 6, 7, 8, 9, 10,13,16,19,26,34,37,59};
    Integer key = 59;
    int result = searcher.binSearchRecursion(obj,key);
    System.out.println(result);

  }

}
