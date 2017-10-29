package com.topsec.tsm.datastructure.list;

import com.topsec.tsm.datastructure.exception.InvalidNodeException;
import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.iterator.Iterator;

public interface LinkedList<E> {
  // 查询链接表当前的规模
  int getSize();

  // 判断链接表是否为空
  boolean isEmpty();

  // 返回第一个结点
  Node<E> first() throws OutOfBoundaryException;

  // 返回最后一结点
  Node<E> last() throws OutOfBoundaryException;

  // 返回p之后的结点
  Node<E> getNext(Node<E> p) throws InvalidNodeException, OutOfBoundaryException;

  // 返回p之前的结点
  Node<E> getPre(Node<E> p) throws InvalidNodeException, OutOfBoundaryException;

  // 将e作为第一个元素插入链接表,并返回e所在结点
  Node<E> insertFirst(E e);

  // 将e作为最后一个元素插入列表,并返回e所在结点
  Node<E> insertLast(E e);

  // 将e插入至p之后的位置,并返回e所在结点
  Node<E> insertAfter(Node<E> p, E e) throws InvalidNodeException;

  // 将e插入至p之前的位置,并返回e所在结点
  Node<E> insertBefore(Node<E> p, E e) throws InvalidNodeException;

  // 删除给定位置处的元素，并返回之
  E remove(Node<E> p) throws InvalidNodeException;

  // 删除首元素，并返回之
  E removeFirst() throws OutOfBoundaryException;

  // 删除末元素，并返回之
  E removeLast() throws OutOfBoundaryException;

  // 将处于给定位置的元素替换为新元素，并返回被替换的元素
  E replace(Node<E> p, E e) throws InvalidNodeException;

  // 元素迭代器
  Iterator<E> elements();

  // 检查是否包含元素值
  boolean contains(E e);

  // 查找符合条件的节点
  Node<E> find(E e);

  // 清除链接表
  void clear();
}


/*
 * 
 * 结点p在以下情况下可以认为是不合法的： p==null； p在链接表中不存在； 在调用方法getPre(p)时，p已经是第一个存有数据的结点；
 * 在调用方法getNext(p)时，p已经是最后一个存有数据的结点。
 */

