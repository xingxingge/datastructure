package com.topsec.tsm.datastructure.list;

import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.iterator.Iterator;

/**
 * 链表接口
 * **/
public interface List<E> {
  // 链表长度
  int getSize();

  // 判断是否为空
  boolean isEmpty();

  // 是否包含元素e

  boolean contains(E e);

  // 返回元素e所在的位置
  int indexOf(E e);

  // 在索引为i的元素后插入e
  void insert(int i, E e) throws OutOfBoundaryException;

  // 在元素obj之前插入e
  boolean insertBefore(E obj, E e);

  // 在元素obj之后插入e
  boolean insertAfter(E obj, E e);

  // 移除i号元素
  E remove(int i) throws OutOfBoundaryException;

  // 移除元素e
  boolean remove(E e);

  // 把第i位置的元素替换为e
  E replace(int i, E e) throws OutOfBoundaryException;

  // 获取i号元素
  E get(int i) throws OutOfBoundaryException;

  // 在线性表尾部插入e
  void insertLast(E e) throws OutOfBoundaryException;

  // 返回元素
  Iterator<E> elements();

  // 返回第一个元素
  E first();

  // 清除元素
  void clear();

}
