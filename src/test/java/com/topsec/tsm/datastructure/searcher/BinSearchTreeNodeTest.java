package com.topsec.tsm.datastructure.searcher;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.Node;
import com.topsec.tsm.datastructure.tree.BinTreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinSearchTreeNodeTest {
  BinSearchTreeNode<Integer> b1 = new BinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b2 = new BinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b3 = new BinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b4 = new BinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b5 = new BinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b6 = new BinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b7 = new BinSearchTreeNode<>();
  BinSearchTreeNode<Integer> b8 = new BinSearchTreeNode<>();
  private BinSearchTreeNode<Integer> root;

  private void print(Iterator<BinTreeNode<Integer>> itr) {
    System.out.print("中序遍历: ");
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
    root= (BinSearchTreeNode<Integer>) b1.setAndGetRoot();
    Iterator<BinTreeNode<Integer>> itr = root.inOrder();
    print(itr);
  }

  @Test
  public void searchTest() {
    System.out.println("查找算法实现:");
    //查找
    Node<Integer> node = root.search(5);
    Assert.assertEquals((Object) node.getData(), 5);
    if (node != null) System.out.println(node.getData());
  }

  @Test
  public void maxAndMinTest() {
    System.out.println("最大最小值:");
    Assert.assertEquals(root.max(b6), b8);
    Assert.assertEquals(root.min(b6), b1);
  }

  @Test
  public void getSuccessorTest() {
    System.out.println("后继");
    Node node = root.getSuccessor2(b8);
    if (node != null) {
      System.out.printf(node.getData().toString());

    }
    Assert.assertEquals(root.getSuccessor(b4), b5);
  }

  @Test
  public void getPredecessorTest() {
    System.out.println("前驱");
    Node node = root.getPredecessor(b6);
    if (node != null) {
      System.out.printf(node.getData().toString());

    }
//    Assert.assertEquals(b6.getPredecessor2(b6), b5);
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

  @Test
  public void higherSubTTest() {
    System.out.println(b2.higherSubT(b4).getData());
  }

}
