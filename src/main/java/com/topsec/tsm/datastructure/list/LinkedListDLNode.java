package com.topsec.tsm.datastructure.list;

import com.topsec.tsm.datastructure.exception.InvalidNodeException;
import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.iterator.LinkedListIterator;
import com.topsec.tsm.datastructure.list.DLNode;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.Node;
import com.topsec.tsm.datastructure.strategy.DefaultStrategy;
import com.topsec.tsm.datastructure.strategy.Strategy;

public class LinkedListDLNode<E> implements LinkedList<E> {
  private int size; // 规模
  private DLNode<E> head;// 头结点,哑元结点
  private DLNode<E> tail;// 尾结点,哑元结点
  private Strategy<E> stragety;

  /**
   * 在具有头尾结点的双向链表中插入和删除结点，无论插入和删除的结点位置在何处，因 为首尾结点的存在，插入、删除操作都可以被归结为3.3.2小节中介绍的在双向链表某个中
   * 间结点的插入和删除；并且因为首尾结点的存在，整个链表永远不会为空，因此在插入和删 除结点之后，也不用考虑链表由空变为非空或由非空变为空的情况下head和tail 的指向问
   * 题；从而简化了程序。
   **/

  // 构造方法，用于构造一个新的链接表，头尾相互指向
  public LinkedListDLNode() {
    size = 0;
    head = new DLNode<E>();// 构建只有头尾结点的链表
    tail = new DLNode<E>();
    head.setNext(tail);
    tail.setPre(head);
    this.stragety = new DefaultStrategy<E>();

  }

  // 辅助方法，判断结点p是否合法，如合法转换为DLNode,返回最后的节点
  private DLNode<E> checkPosition(Node<E> p) throws InvalidNodeException {
    if (p == null) throw new InvalidNodeException("错误：p为空。");
    if (p == head) throw new InvalidNodeException("错误：p指向头节点，非法。");
    if (p == tail) throw new InvalidNodeException("错误：p指向尾结点，非法。");
    if (p instanceof DLNode) {
      DLNode<E> node = (DLNode<E>) p;
      return node;
    }
    return null;

  }

  // 查询链接表当前的规模
  public int getSize() {
    return size;
  }

  // 判断链接表是否为空
  public boolean isEmpty() {
    return size == 0;
  }

  // 返回第一个结点
  public Node<E> first() throws OutOfBoundaryException {
    if (isEmpty()) throw new OutOfBoundaryException("错误：链接表为空。");
    return head.getNext();
  }

  // 返回最后一结点,不需要从头开始查找
  public Node<E> last() throws OutOfBoundaryException {
    if (isEmpty()) throw new OutOfBoundaryException("错误：链接表为空。");
    return tail.getPre();
  }

  // 返回p之后的结点
  public Node<E> getNext(Node<E> p) throws InvalidNodeException, OutOfBoundaryException {
    DLNode<E> node = checkPosition(p);
    node = node.getNext();
    // 判断p是否是最后的节点
    // checkPosition(node);
    if (node == tail) throw new OutOfBoundaryException("错误：已经是链接表尾端。");
    return node;
  }

  // 返回p之前的结点
  public Node<E> getPre(Node<E> p) throws InvalidNodeException, OutOfBoundaryException {
    DLNode<E> node = checkPosition(p);
    node = node.getPre();
    // 判断p是否是第一个节点
    if (node == head) throw new OutOfBoundaryException("错误：已经是链接表前端。");
    return node;
  }

  // 将e作为第一个元素插入链接表
  // 注意指向变化
  public Node<E> insertFirst(E e) {
    DLNode<E> node = new DLNode<E>(e, head, head.getNext());
    head.getNext().setPre(node);
    head.setNext(node);
    size++;
    return node;
  }

  // 将e作为最后一个元素插入列表,并返回e所在结点
  public Node<E> insertLast(E e) {
    DLNode<E> node = new DLNode<E>(e, tail.getPre(), tail);
    tail.getPre().setNext(node);
    tail.setPre(node);
    size++;
    return node;
  }

  // 将e插入至p之后的位置,并返回e所在结点
  public Node<E> insertAfter(Node<E> p, E e) throws InvalidNodeException {
    DLNode<E> node = checkPosition(p);
    DLNode<E> newNode = new DLNode<E>(e, node, node.getNext());
    node.getNext().setPre(newNode);
    node.setNext(newNode);
    size++;
    return newNode;
  }

  // 将e插入至p之前的位置,并返回e所在结点
  public Node<E> insertBefore(Node<E> p, E e) throws InvalidNodeException {
    DLNode<E> node = checkPosition(p);
    DLNode<E> newNode = new DLNode<E>(e, node.getPre(), node);
    node.getPre().setNext(newNode);
    node.setPre(newNode);
    size++;
    return newNode;
  }

  // 删除给定位置处的元素，并返回元素值
  public E remove(Node<E> p) throws InvalidNodeException {
    DLNode<E> node = checkPosition(p);
    E obj = node.getData();
    node.getPre().setNext(node.getNext());
    node.getNext().setPre(node.getPre());
    size--;
    return obj;
  }

  // 删除首元素，并返回之
  public E removeFirst() throws OutOfBoundaryException {
    return remove(head.getNext());
  }

  // 删除末元素，并返回之
  public E removeLast() throws OutOfBoundaryException {
    return remove(tail.getPre());
  }

  // 将处于给定位置的元素替换为新元素，并返回被替换的元素
  public E replace(Node<E> p, E e) throws InvalidNodeException {
    DLNode<E> node = checkPosition(p);
    E obj = node.getData();
    node.setData(e);
    return obj;
  }

  // 元素迭代器
  public Iterator<E> elements() {
    return new LinkedListIterator<E>(this);
  }

  public boolean contains(E e) {
    DLNode<E> p = head.getNext();
    while (p.getNext() != null) {
      if (stragety.equal(p.getData(), e)) {
        return true;
      } else {
        p = p.getNext();
      }
    }
    return false;
  }

  public Node<E> find(E e) {
    DLNode<E> p = head.getNext();
    while (p.getNext() != null) {
      if (stragety.equal(p.getData(), e)) {
        return p;
      } else {
        p = p.getNext();
      }
    }
    return null;
  }

  public void clear() {
    size = 0;
    head = new DLNode<E>();// 构建只有头尾结点的链表
    tail = new DLNode<E>();
    head.setNext(tail);
    tail.setPre(head);
  }
}
