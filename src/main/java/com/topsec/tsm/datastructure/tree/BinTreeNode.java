package com.topsec.tsm.datastructure.tree;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.LinkedListDLNode;
import com.topsec.tsm.datastructure.list.Node;
import com.topsec.tsm.datastructure.queue.Queue;
import com.topsec.tsm.datastructure.queue.QueueArray;
import com.topsec.tsm.datastructure.stack.Stack;
import com.topsec.tsm.datastructure.stack.StackSLinked;
import com.topsec.tsm.datastructure.strategy.DefaultStrategy;
import com.topsec.tsm.datastructure.strategy.Strategy;

/**
 * 二叉树的三叉链表表示方法 :
 * 如果规定对左子树的遍历先于对右子树的遍历，那么还剩下3 种情况：DLR、LDR、LRD。
 * 根据对根访问的不同顺序，分别称DLR为先根（序）遍历，
 * LDR为中根（序）遍历，LRD为后根（序）遍历 包含内容：树的常用方法，递归和非递归变得方法，层次遍历和查找
 *
 * @author HuangXing
 */

public class BinTreeNode<E> implements Node<E> {

  protected E data;
  protected BinTreeNode<E> parent;
  protected BinTreeNode<E> lChild;
  protected BinTreeNode<E> rChild;
  protected int height;
  protected int size;
  protected Strategy<E> strategy;
  protected BinTreeNode root;//根节点

  public BinTreeNode(E e) {
    data = e;
    height = 0;
    size = 1;
    root = parent = lChild = rChild = null;
    this.strategy = new DefaultStrategy<E>();

  }

  public BinTreeNode setAndGetRoot() {
      root = this;
      while (root.hasParent()) {
        root = root.getParent();
      }
    return root;
  }

  public BinTreeNode() {
    this(null);
  }

  public E getData() {
    return data;
  }

  public void setData(E obj) {
    data = obj;
  }

  /******
   * 辅助方法,判断当前结点位置情况
   ******/
  // 判断是否有父亲
  public boolean hasParent() {
    return parent != null;
  }

  // 判断是否有左孩子
  public boolean hasLChild() {
    return lChild != null;
  }

  // 判断是否有右孩子
  public boolean hasRChild() {
    return rChild != null;
  }

  // 判断是否为叶子结点
  public boolean isLeaf() {
    // 没有左子树和右子树
    return !hasLChild() && !hasRChild();
  }

  // 判断是否为某结点的左孩子
  // 具备父节点，而且是父节点的左节点
  public boolean isLChild() {
    return (hasParent() && this == parent.lChild);
  }

  // 判断是否为某结点的右孩子
  public boolean isRChild() {
    return (hasParent() && this == parent.rChild);
  }

  /******
   * 与height相关的方法
   ******/
  // 取结点的高度,即以该结点为根的树的高度
  public int getHeight() {
    return height;
  }

  // 更新当前结点及其祖先的高度
  public void updateHeight() {
    int newH = 0;// 新高度初始化为0,高度等于左右子树高度加1中的大者
    if (hasLChild()) newH = Math.max(newH, 1 + getLChild().getHeight());
    if (hasRChild()) newH = Math.max(newH, 1 + getRChild().getHeight());
    if (newH == height) return; // 高度没有发生变化则直接返回
    height = newH; // 否则更新高度
    if (hasParent()) getParent().updateHeight(); // 递归更新祖先的高度
  }

  /******
   * 与size相关的方法
   ******/
  // 取以该结点为根的树的结点数
  public int getSize() {
    return size;
  }

  // 更新当前结点及其祖先的子孙数
  public void updateSize() {
    size = 1; // 初始化为1,结点本身
    if (hasLChild()) size += getLChild().getSize(); // 加上左子树规模
    if (hasRChild()) size += getRChild().getSize(); // 加上右子树规模
    if (hasParent()) getParent().updateSize(); // 递归更新祖先的规模
  }

  /******
   * 与parent相关的方法
   ******/
  // 取父结点
  public BinTreeNode<E> getParent() {
    return parent;
  }

  // 断开与父亲的关系
  public void sever() {
    if (!hasParent()) return;
    if (isLChild())
      parent.lChild = null;
    else
      parent.rChild = null;
    parent.updateHeight(); // 更新父结点及其祖先高度
    parent.updateSize(); // 更新父结点及其祖先规模
    parent = null;
  }

  /******
   * 与lChild相关的方法
   ******/
  // 取左孩子
  public BinTreeNode<E> getLChild() {
    return lChild;
  }

  // 设置当前结点的左孩子,返回原左孩子,设置成lc
  public BinTreeNode<E> setLChild(BinTreeNode<E> lc) {
    BinTreeNode<E> oldLC = this.lChild;
    if (hasLChild()) {
      lChild.sever();
    } // 断开当前左孩子与结点的关系
    if (lc != null) {
      lc.sever(); // 断开lc与其父结点的关系
      this.lChild = lc; // 确定父子关系
      lc.parent = this;
      this.updateHeight(); // 更新当前结点及其祖先高度
      this.updateSize(); // 更新当前结点及其祖先规模
    }
    return oldLC; // 返回原左孩子
  }

  /******
   * 与rChild相关的方法
   ******/
  // 取右孩子
  public BinTreeNode<E> getRChild() {
    return rChild;
  }

  // 设置当前结点的右孩子,返回原右孩子
  public BinTreeNode<E> setRChild(BinTreeNode<E> rc) {
    BinTreeNode<E> oldRC = this.rChild;
    if (hasRChild()) {
      rChild.sever();
    } // 断开当前右孩子与结点的关系
    if (rc != null) {
      rc.sever(); // 断开lc与其父结点的关系
      this.rChild = rc; // 确定父子关系
      rc.parent = this;
      this.updateHeight(); // 更新当前结点及其祖先高度
      this.updateSize(); // 更新当前结点及其祖先规模
    }
    return oldRC; // 返回原右孩子
  }

  // 先序遍历二叉树
  public Iterator<BinTreeNode<E>> preOrder() {
    LinkedList<BinTreeNode<E>> list = new LinkedListDLNode<BinTreeNode<E>>();
    preOrderRecursion(this, list);
    return list.elements();
  }

  // 先序遍历的递归算法
  private void preOrderRecursion(BinTreeNode<E> rt, LinkedList<BinTreeNode<E>> list) {
    if (rt == null) return; // 递归基,空树直接返回
    list.insertLast(rt); // 访问根结点
    preOrderRecursion(rt.getLChild(), list); // 遍历左子树
    preOrderRecursion(rt.getRChild(), list); // 遍历右子树
  }

  // 中序遍历二叉树
  public Iterator<BinTreeNode<E>> inOrder() {
    LinkedList<BinTreeNode<E>> list = new LinkedListDLNode<BinTreeNode<E>>();
    inOrderRecursion(this, list);
    return list.elements();
  }

  // 中序遍历二叉树的递归算法
  private void inOrderRecursion(BinTreeNode<E> rt, LinkedList<BinTreeNode<E>> list) {
    if (rt == null) return;
    inOrderRecursion(rt.getLChild(), list); // 遍历左子树
    list.insertLast(rt); // 访问根结点
    inOrderRecursion(rt.getRChild(), list); // 遍历右子树

  }

  // 后序遍历
  public Iterator<BinTreeNode<E>> postOrder() {
    LinkedList<BinTreeNode<E>> list = new LinkedListDLNode<BinTreeNode<E>>();
    postOrderRecursion(this, list);
    return list.elements();
  }

  // 后序遍历二叉树的递归算法
  private void postOrderRecursion(BinTreeNode<E> rt, LinkedList<BinTreeNode<E>> list) {
    if (rt == null) return;
    postOrderRecursion(rt.getLChild(), list); // 遍历左子树
    postOrderRecursion(rt.getRChild(), list); // 遍历右子树
    list.insertLast(rt); // 访问根结点

  }

  // 先序遍历非递归：
  public Iterator<BinTreeNode<E>> preOrder1() {
    LinkedList<BinTreeNode<E>> list = new LinkedListDLNode<BinTreeNode<E>>();
    preOrderTraverse(this, list);
    return list.elements();
  }

  // 先序遍历的非递归算法
  private void preOrderTraverse(BinTreeNode<E> rt, LinkedList<BinTreeNode<E>> list) {
    if (rt == null) return;
    BinTreeNode<E> p = rt;
    Stack<BinTreeNode<E>> s = new StackSLinked<BinTreeNode<E>>();
    while (p != null) {
      while (p != null) { // 向左走到尽头
        list.insertLast(p); // 访问根
        if (p.hasRChild()) s.push(p.getRChild()); // 右子树根结点入栈
        p = p.getLChild();
      }
      if (!s.isEmpty()) p = s.pop(); // 右子树根退栈遍历右子树
    }
  }

  // 中序遍历非递归：
  public Iterator<BinTreeNode<E>> inOrder1() {
    LinkedList<BinTreeNode<E>> list = new LinkedListDLNode<BinTreeNode<E>>();
    inOrderTraverse(this, list);
    return list.elements();
  }

  // 中序遍历的非递归算法
  public void inOrderTraverse(BinTreeNode<E> rt, LinkedList<BinTreeNode<E>> list) {
    if (rt == null) return;

    BinTreeNode<E> p = rt;
    // 实例化一个栈对象
    Stack<BinTreeNode<E>> s = new StackSLinked<BinTreeNode<E>>();
    while (p != null || !s.isEmpty()) {
      while (p != null) {
        // 一直向左走，入栈
        s.push(p);
        p = p.getLChild();
      }
      // 节点的左子树遍历完成后，把中间节点出栈，再遍历右子树
      if (!s.isEmpty()) {
        p = s.pop();
        list.insertLast(p);
        p = p.getRChild();
      }
    }
  }

  // 后序遍历非递归
  public Iterator<BinTreeNode<E>> postOrder1() {
    LinkedList<BinTreeNode<E>> list = new LinkedListDLNode<BinTreeNode<E>>();
    postOrderTraverse(this, list);
    return list.elements();
  }

  // 后序遍历的非递归算法
  private void postOrderTraverse(BinTreeNode<E> rt, LinkedList<BinTreeNode<E>> list) {
    if (rt == null) return;
    BinTreeNode<E> p = rt;
    Stack<BinTreeNode<E>> s = new StackSLinked<BinTreeNode<E>>();
    while (p != null || !s.isEmpty()) {
      while (p != null) { // 先左后右不断深入
        s.push(p); // 将根节点入栈,在切换到左和右孩子节点,优先考虑左孩子
        if (p.hasLChild())
          p = p.getLChild();
        else
          p = p.getRChild();
      }
      if (!s.isEmpty()) {
        p = s.pop(); // 取出栈顶根结点访问之
        list.insertLast(p);
      }
      // 满足条件时，说明栈顶根节点右子树已访问，应出栈访问之
      while (!s.isEmpty() && (s.peek()).getRChild() == p) {
        p = s.pop();
        list.insertLast(p);
      }
      // 转向栈顶根结点的右子树继续后序遍历
      if (!s.isEmpty())
        p = s.peek().getRChild();
      else
        p = null;
    }
  }

  // 按层遍历二叉树
  public Iterator<BinTreeNode<E>> levelOrder() {
    LinkedList<BinTreeNode<E>> list = new LinkedListDLNode<BinTreeNode<E>>();
    levelOrderTraverse(this, list);
    return list.elements();
  }

  // 使用队列完成二叉树的按层遍历
  private void levelOrderTraverse(BinTreeNode<E> rt, LinkedList<BinTreeNode<E>> list) {
    if (rt == null) return;
    Queue<BinTreeNode<E>> q = new QueueArray<BinTreeNode<E>>();
    q.enqueue(rt); // 根结点入队
    while (!q.isEmpty()) {
      BinTreeNode<E> p = q.dequeue(); // 取出队首结点p并访问
      list.insertLast(p);
      if (p.hasLChild()) q.enqueue(p.getLChild());// 将p的非空左右孩子依次入队
      if (p.hasRChild()) q.enqueue(p.getRChild());
    }
  }

  // 在树中查找元素e，返回其所在结点
  public BinTreeNode<E> find(E e) {
    return searchE(this, e);
  }

  // 递归查找元素e
  private BinTreeNode<E> searchE(BinTreeNode<E> rt, E e) {
    if (rt == null) return null;
    if (strategy.equal(rt.getData(), e)) return rt; // 如果是根结点，返回根
    BinTreeNode<E> v = searchE(rt.getLChild(), e); // 否则在左子树中找
    if (v == null) v = searchE(rt.getRChild(), e); // 没找到，在右子树中找
    return v;
  }
}
