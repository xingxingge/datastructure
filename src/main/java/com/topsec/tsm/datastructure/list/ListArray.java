package com.topsec.tsm.datastructure.list;

import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.iterator.ListArrayIterator;
import com.topsec.tsm.datastructure.strategy.DefaultStrategy;
import com.topsec.tsm.datastructure.strategy.Strategy;

/**
 * List的数组实现
 * 
 * **/
public class ListArray<E> implements List<E> {
  private final int LEN = 8; // 初始长度
  private Strategy<E> strategy; // 比较策略
  private int size; // List尺寸
  private E[] elements; // 返回元素

  // 返回长度
  public int getelementsize() {
    return elements.length;

  }

  public ListArray() {
    // 构造方法
    this(new DefaultStrategy<E>());
  }

  // 构造指定长度的链表
  @SuppressWarnings("unchecked")
  public ListArray(int len) {
    this.strategy = new DefaultStrategy<E>();
    size = 0; //
    elements = (E[]) new Object[len]; // 指定初始尺寸
  }

  // 构造方法，指定比较策略
  @SuppressWarnings("unchecked")
  public ListArray(Strategy<E> strategy) {
    this.strategy = strategy;
    size = 0;
    elements = (E[]) new Object[LEN];
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(E e) {
    for (int i = 0; i < size; i++) {
      if (strategy.equal(elements[i], e)) {
        return true;
      }
    }
    return false;
  }

  //
  public int indexOf(E e) {
    for (int i = 0; i < size; i++) {
      if (strategy.equal(elements[i], e)) {
        return i;
      }
    }
    return -1;
  }

  public void insert(int i, E e) throws OutOfBoundaryException {
    if (i < 0 || i > size) {

      throw new OutOfBoundaryException("数组越界");
    }
    if (size >= elements.length) {
      expandSpace();
    }
    for (int j = size; j > i; j--) {
      elements[j] = elements[j - 1];
    }
    elements[i] = e;
    size++;

  }

  // 辅助方法，扩展list

  @SuppressWarnings("unchecked")
  private void expandSpace() {
    E[] a = (E[]) new Object[elements.length * 2];
    for (int i = 0; i < elements.length; i++)
      a[i] = elements[i];
    elements = a;
  }

  public boolean insertBefore(E obj, E e) {
    int i = indexOf(obj);
    if (i < 0) {
      return false;
    }
    insert(i, e);
    return true;
  }

  public boolean insertAfter(E obj, E e) {
    int i = indexOf(obj);
    if (i < 0) {
      return false;
    }
    insert(i + 1, e);
    return true;
  }

  public E remove(int i) throws OutOfBoundaryException {
    if (i < 0 || i > size) {
      throw new OutOfBoundaryException("下标超出数组范围");
    }
    E obj = elements[i];
    for (int j = i; j < size - 1; j++) {
      elements[j] = elements[j + 1];
    }
    elements[--size] = null;
    return obj;
  }

  public boolean remove(E e) {
    int i = indexOf(e);
    if (i < 0) {
      return false;
    }
    remove(i);
    return true;
  }

  public E replace(int i, E e) throws OutOfBoundaryException {
    if (i < 0 || i > size) {
      throw new OutOfBoundaryException("数组下标越界");
    }
    E obj = elements[i];
    elements[i] = e;
    return obj;
  }

  public E get(int i) throws OutOfBoundaryException {
    if (i < 0 || i > size) {
      throw new OutOfBoundaryException("����Խ��");
    }
    return (E) elements[i];
  }

  public void insertLast(E e) {

    insert(size, e);
  }

  public Iterator<E> elements() {
    return new ListArrayIterator<E>(this);
  }

  public E first() {
    return elements[0];
  }

  @SuppressWarnings("unchecked")
  public void clear() {
    // 清除线性表
    size = 0; //
    elements = (E[]) new Object[LEN]; // 指定初始尺寸
  }

}
