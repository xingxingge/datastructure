package com.topsec.tsm.datastructure.list;

/**
 * 双向链表节点,定义了前驱和后继节点
 * */

public class DLNode<E> implements Node<E> {
  private E element;
  private DLNode<E> pre;
  private DLNode<E> next;

  public DLNode() {
    this(null, null, null);
  }

  public DLNode(E ele, DLNode<E> pre, DLNode<E> next) {
    this.element = ele;
    this.pre = pre;
    this.next = next;
  }

  public DLNode<E> getPre() {
    return pre;
  }

  public void setPre(DLNode<E> pre) {
    this.pre = pre;
  }

  public DLNode<E> getNext() {
    return next;
  }

  public void setNext(DLNode<E> next) {
    this.next = next;
  }

  // 获取数据域
  public E getData() {
    return element;
  }

  public void setData(E obj) {
    this.element = obj;
  }
}
