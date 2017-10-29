package com.topsec.tsm.datastructure.tree;

import org.junit.Test;

import com.topsec.tsm.datastructure.iterator.Iterator;

public class binTreeNodeTest {
  // 测试方法
  @Test
  public void binTreeNode() {
    BinTreeNode<Integer> bt = new BinTreeNode<Integer>();
    // 二叉树
    System.out.println("<<========================二叉树的三叉链表表示法========================>>");
    BinTreeNode<Integer> l = new BinTreeNode<Integer>();
    BinTreeNode<Integer> r = new BinTreeNode<Integer>();
    BinTreeNode<Integer> ll = new BinTreeNode<Integer>();
    BinTreeNode<Integer> lr = new BinTreeNode<Integer>();
    l.setData(2);
    r.setData(3);
    ll.setData(4);
    lr.setData(5);
    bt.setData(1);
    bt.setLChild(l);
    bt.setRChild(r);
    l.setLChild(ll);
    l.setRChild(lr);
    System.out.println(bt.hasParent());
    System.out.println(bt.getSize());
    System.out.println(bt.getData());
    // 先序遍历
    System.out.print("\n先序遍历：");
    Iterator<BinTreeNode<Integer>> it = bt.preOrder();
    while (!it.isDone()) {
      BinTreeNode<Integer> b = it.currentItem();
      System.out.print(b.getData() + "\t");
      it.next();
    }
    // 中序遍历
    System.out.print("\n中序遍历：");
    it = bt.inOrder();
    while (!it.isDone()) {
      BinTreeNode<Integer> b = it.currentItem();
      System.out.print(b.getData() + "\t");
      it.next();
    }
    // 后序遍历
    System.out.print("\n后序遍历：");
    it = bt.postOrder();
    while (!it.isDone()) {
      BinTreeNode<Integer> b = it.currentItem();
      System.out.print(b.getData() + "\t");
      it.next();
    }

    // 先序遍历非递归算法
    System.out.print("\n先序遍历非递归算法：");
    it = bt.preOrder1();
    while (!it.isDone()) {
      BinTreeNode<Integer> b = it.currentItem();
      System.out.print(b.getData() + "\t");
      it.next();
    }
    // 中序遍历非递归算法
    System.out.print("\n中序遍历非递归算法：");
    it = bt.inOrder1();
    while (!it.isDone()) {
      BinTreeNode<Integer> b = it.currentItem();
      System.out.print(b.getData() + "\t");
      it.next();
    }

    // 后序遍历非递归算法
    System.out.print("\n后序遍历非递归算法：");
    it = bt.postOrder1();
    while (!it.isDone()) {
      BinTreeNode<Integer> b = it.currentItem();
      System.out.print(b.getData() + "\t");
      it.next();
    }

    // 层次遍历非递归算法
    System.out.print("\n层次遍历非递归算法：");
    it = bt.levelOrder();
    while (!it.isDone()) {
      BinTreeNode<Integer> b = it.currentItem();
      System.out.print(b.getData() + "\t");
      it.next();
    }
    // 查找元素
    System.out.println("\n查找元素: " + bt.find(1).getData());
  }
}
