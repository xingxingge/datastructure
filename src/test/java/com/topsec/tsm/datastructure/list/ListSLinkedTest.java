package com.topsec.tsm.datastructure.list;

import com.topsec.tsm.datastructure.iterator.Iterator;
import org.junit.Test;

/**
 * Created by hx on 16-11-5.
 */
public class ListSLinkedTest {
  @Test
  public void remove() throws Exception {

  }

  @Test
  public void reverse() throws Exception {
    ListSLinked<Integer> list = new ListSLinked<>();
    list.insertLast(1);
    list.insertLast(2);
    list.insertLast(3);
    list.insertLast(4);
    list.insertLast(5);
    list=list.reverse();
    Iterator<Integer> elements = list.elements();
    while(!elements.isDone()){
      Integer i =elements.currentItem();
      elements.next();
      System.out.println(i);
    }

  }

  @Test
  public void reverse2() throws Exception {
    ListSLinked<Integer> list = new ListSLinked<>();
    list.insertLast(1);
    list.insertLast(2);
//    list.insertLast(3);
//    list.insertLast(4);
//    list.insertLast(5);
    list=list.reverse2();
    Iterator<Integer> elements = list.elements();
    while(!elements.isDone()){
      Integer i =elements.currentItem();
      elements.next();
      System.out.println(i);
    }

  }

  @Test
  public void hasCycleTest() throws Exception {
    ListSLinked<Integer> list = new ListSLinked<>();
    list.insertLast(0);
    list.insertLast(1);
    list.insertLast(2);
    list.insertLast(3);
    list.insertLast(4);
    SLNode<Integer> nodeN = list.getNode(list.getSize()-1);
    SLNode<Integer> node0 = list.getNode(2);
    nodeN.setNext(node0);
    System.out.println(list.hasCycle());
    System.out.println(list.detectCycle().getData());


  }

}
