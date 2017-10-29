package com.topsec.tsm.datastructure.list;

import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.iterator.ListSLinkedIterator;
import com.topsec.tsm.datastructure.strategy.DefaultStrategy;
import com.topsec.tsm.datastructure.strategy.Strategy;

public class ListSLinked<E> implements List<E> {
  private Strategy<E> strategy; //
  private SLNode<E> head; //单链表
  private int size; //

  public ListSLinked() {
    this(new DefaultStrategy<E>());
  }

  public ListSLinked(Strategy<E> strategy) {
    this.strategy = strategy;
    head = new SLNode<E>();
    size = 0;
  }


  private SLNode<E> getPreNode(E e) {
    SLNode<E> p = head;
    while (p.getNext() != null)
      if (strategy.equal(p.getNext().getData(), e))
        return p;
      else
        p = p.getNext();
    return null;
  }

  private SLNode<E> getPreNode(int i) {
    SLNode<E> p = head;
    for (; i > 0; i--)
      p = p.getNext();
    return p;
  }

  public SLNode<E> getNode(int i) {
    SLNode<E> p = head.getNext();
    for (; i > 0; i--) {
      p = p.getNext();
    }
    return p;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(E e) {
    SLNode<E> p = head.getNext();
    while (p != null) {
      if (strategy.equal(p.getData(), e)) {
        return true;
      } else
        p = p.getNext();
    }
    return false;
  }

  public int indexOf(E e) {
    SLNode<E> p = head.getNext();
    int index = 0;
    while (p != null)
      if (strategy.equal(p.getData(), e))
        return index;
      else {
        index++;
        p = p.getNext();
      }
    return -1;
  }

  public void insert(int i, E e) throws OutOfBoundaryException {
    if (i < 0 || i > size) {
      throw new OutOfBoundaryException("链表越界");
    }
    SLNode<E> p = getPreNode(i);
    SLNode<E> q = new SLNode<E>(e, p.getNext());
    p.setNext(q);
    ++size;

  }

  public boolean insertBefore(E obj, E e) {
    SLNode<E> p = getPreNode(obj);
    if (p != null) {
      SLNode<E> q = new SLNode<E>(e, p.getNext());
      p.setNext(q);
      size += 1;
      return true;
    }
    return false;
  }

  public boolean insertAfter(E obj, E e) {
//		SLNode p = head.getNext();
//		while (p != null)
//			if (strategy.equal(p.getData(), obj)) {
//				SLNode q = new SLNode(e, p.getNext());
//				p.setNext(q);
//				size++;
//				return true;
//			} else
//				p = p.getNext();
    SLNode<E> p = getPreNode(obj);
    if (p.getNext() != null) {
      SLNode<E> q = new SLNode<E>(e, p.getNext().getNext());
      p.getNext().setNext(q);
      size++;
      return true;
    }
    return false;
  }

  public E remove(int i) throws OutOfBoundaryException {
    if (i < 0 || i >= size)
      throw new OutOfBoundaryException("链表越界");
    SLNode<E> p = getPreNode(i);
    E obj = p.getNext().getData();
    p.setNext(p.getNext().getNext());
    size--;
    return obj;
  }

  public boolean remove(E e) {
    SLNode<E> p = getPreNode(e);
    if (p != null) {
      p.setNext(p.getNext().getNext());
      size--;
      return true;
    }
    return false;
  }

  public E replace(int i, E e) throws OutOfBoundaryException {
    if (i < 0 || i >= size)
      throw new OutOfBoundaryException("链表越界");
    SLNode<E> p = getNode(i);
    E obj = p.getData();
    p.setData(e);
    return obj;
  }

  public E get(int i) throws OutOfBoundaryException {
    if (i < 0 || i >= size)
      throw new OutOfBoundaryException("链表越界");
    SLNode<E> p = getNode(i);
    return p.getData();
  }

  public Iterator<E> elements() {
    return new ListSLinkedIterator<E>(this);
  }

  public void insertLast(E e) throws OutOfBoundaryException {
    insert(size, e);
  }

  public E first() {
    return head.getNext().getData();
  }

  public void clear() {
    head = new SLNode<E>();
    size = 0;
  }

  //单链表倒换
  public ListSLinked<E> reverse() {
    SLNode<E> node = this.head.getNext();
    SLNode<E> p1 = null;
    SLNode<E> p2 = node;
    while (p2 != null) {
      p2 = node.getNext();
      node.setNext(p1);
      p1 = node;
      node = p2;
    }
    this.head.setNext(p1);
    return this;
  }

}
