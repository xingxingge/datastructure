package com.topsec.tsm.datastructure.searcher;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.tree.BinTreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hx on 16-10-3.
 */
public class AvlBinSearchTreeNodeTest {
  BinSearchTreeNode<Integer> b1 = new AvlBinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b2 = new AvlBinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b3 = new AvlBinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b4 = new AvlBinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b5 = new AvlBinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b6 = new AvlBinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b7 = new AvlBinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b8 = new AvlBinSearchTreeNode<>();
  private AvlBinSearchTreeNode<Integer> root;

  private void print(Iterator<BinTreeNode<Integer>> itr) {
    while (!itr.isDone()) {
      System.out.print(itr.currentItem().getData() + "\t");
      itr.next();
    }
    System.out.println("\n------------------------------------------------");
  }
  @Before
  public void setUp() throws Exception {
    b1.setData(1);
    b2.setData(2);
    b3.setData(3);
    b4.setData(4);
    b5.setData(5);
    b6.setData(6);
    b7.setData(7);
    b8.setData(8);

    b2.setLChild(b1);
    b2.setRChild(b3);
    b4.setLChild(b2);
    b4.setRChild(b5);
    b6.setLChild(b4);
    b6.setRChild(b7);
    b7.setRChild(b8);
    root= (AvlBinSearchTreeNode<Integer>) b1.setAndGetRoot();
    Iterator<BinTreeNode<Integer>> itr = root.inOrder();
    System.out.println("中序遍历");
    print(itr);
    itr=root.levelOrder();
    System.out.println("层次遍历");
    print(itr);
  }
  @Test
  public void insertTest() {
    System.out.println("插入算法:");
    root.insert(4);
    Assert.assertEquals((Object) b5.getLChild().getData(), 4);
    root.insert(9);
    Assert.assertEquals((Object) b8.getRChild().getData(), 9);
    root.insert(6);
    Iterator<BinTreeNode<Integer>> itr = b2.setAndGetRoot().inOrder();
    print(itr);

  }

  @Test
  public void removeTest() {
    System.out.println("删除算法:");
    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(6));
    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(2));
    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(3));
    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(4));
    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(5));
//    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(6));
    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(7));
    System.out.println("删除元素:" + ((BinSearchTreeNode) b8.setAndGetRoot()).remove(6));
    System.out.println("删除元素:" + ((BinSearchTreeNode) b6.setAndGetRoot()).remove(8));
    print(b8.setAndGetRoot().inOrder());
  }




}
