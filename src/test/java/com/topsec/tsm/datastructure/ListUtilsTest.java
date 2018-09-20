package com.topsec.tsm.datastructure;

import com.topsec.tsm.datastructure.list.ListSLinked;
import com.topsec.tsm.datastructure.list.SLNode;

import org.junit.Test;

public class ListUtilsTest {
  @Test
  public void testGetIntersectionNode(){
    /**
     * 0 1 2 3 \
     *          4 5 6
     * 0 1 2   /
     */
    ListSLinked<Integer> list1 = new ListSLinked<>();
    list1.insertLast(0);
    list1.insertLast(1);
    list1.insertLast(2);
    list1.insertLast(3);
    list1.insertLast(4);
    list1.insertLast(5);
    list1.insertLast(6);
    ListSLinked<Integer> list2 = new ListSLinked<>();
    list2.insertLast(0);
    list2.insertLast(1);
    list2.insertLast(2);
//    list2.getNode(2).setNext(list1.getNode(4));
    SLNode<Integer> node1 = list1.getNode(0);
    SLNode<Integer> node2 = list2.getNode(0);
//    SLNode<Integer> node2 = node1;
    SLNode<Integer> intersectionNode = ListUtils.getIntersectionNode(node1, node2);
    if (intersectionNode!=null){
      System.out.println(intersectionNode.getData());
    }

  }

}
