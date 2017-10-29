package com.topsec.tsm.datastructure.stack;

import com.topsec.tsm.datastructure.list.SLNode;

/**
 * 栈的链式实现,使用单链表,头部元素是栈顶
 * 
 * @author HuangXing
 * 
 */
public class StackSLinked<E> implements Stack<E> {
  private SLNode<E> top;
  private int size;

  public StackSLinked() {
    top = null;
    size = 0;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void push(E e) {
    SLNode<E> q = new SLNode<E>(e, top);
    top = q;
    size++;
  }

  public E pop() throws StackEmptyException {
    if (size < 1) {
      throw new StackEmptyException("The stack is empty!");
    }
    E obj = top.getData();
    top = top.getNext();
    size--;
    return obj;
  }

  public E peek() throws StackEmptyException {

    if (size < 1) throw new StackEmptyException("The stack is empty!");
    return top.getData();

  }

  public static void main(String[] args) {
    StackSLinked<Integer> sl = new StackSLinked<Integer>();
    System.out.println("栈是否为空：" + sl.isEmpty());
    for (int i = 1; i <= 20; i++) {
      sl.push(i);
    }
    // 元素依次出栈
    for (int i = 1; i <= 10; i++) {
      sl.pop();
    }
    sl.clear();
    sl.push(100);
    System.out.println("栈顶元素: " + sl.peek());
    System.out.println("栈的长度： " + sl.getSize());
  }

  public void clear() {
    top = null;
    size = 0;

  }
}
