package com.topsec.tsm.datastructure.stack;

import com.topsec.tsm.datastructure.list.SLNode;


public class StackSLinkedHead<E> implements Stack<E> {

  private SLNode<E> head;
  private SLNode<E> top;
  private int size;

  public StackSLinkedHead() {
    head = new SLNode<E>();
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
    SLNode<E> p = new SLNode<E>(e, top);
    head.setNext(p);
    top = p;
    size++;

  }

  public E pop() throws StackEmptyException {
    if (size < 1) {
      throw new StackEmptyException("The stack is empty!");
    }
    head.setNext(top.getNext());
    E obj = top.getData();
    top = top.getNext();
    size--;
    return obj;

  }

  public E peek() throws StackEmptyException {
    if (size < 1) {
      throw new StackEmptyException("The stack is empty!");
    }
    return top.getData();
  }

  public static void main(String[] xargs) {
    StackSLinkedHead<String> ssh = new StackSLinkedHead<String>();
    ssh.push("huangxing");
    ssh.push("zsh");
    ssh.push("baby");
    System.out.println("Input stack:" + ssh.peek());
    ssh.pop();
    System.out.println("Input stack:" + ssh.peek());
    System.out.println("Get the size:" + ssh.getSize());

  }

  public void clear() {}

}
