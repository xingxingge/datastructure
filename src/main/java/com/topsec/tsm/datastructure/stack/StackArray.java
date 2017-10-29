package com.topsec.tsm.datastructure.stack;

/**
 * 栈的顺序实现
 * 
 * @author HuangXing
 * 
 */
public class StackArray<E> implements Stack<E> {
  private final int LEN = 8;
  private E[] elements;
  private int top;

  @SuppressWarnings("unchecked")
  public StackArray() {
    top = -1;
    elements = (E[]) new Object[LEN];
  }

  public int getSize() {
    return top + 1;
  }

  public boolean isEmpty() {
    return top < 0;
  }

  public void push(E e) {
    if (getSize() >= elements.length) {
      expandSpace();
    }
    elements[++top] = e;
  }

  @SuppressWarnings("unchecked")
  public void expandSpace() {
    E[] a = (E[]) new Object[elements.length * 2]; // 扩展两倍
    for (int i = 0; i < elements.length; i++) {
      a[i] = elements[i];
    }
    elements = a;
  }

  public E pop() throws StackEmptyException {
    if (isEmpty()) {
      throw new StackEmptyException("栈为空！");
    }
    E obj = elements[top];
    elements[top--] = null;// 顶部元素置空，然后top-1
    return obj;
  }

  public E peek() throws StackEmptyException {
    if (isEmpty()) {
      throw new StackEmptyException("栈为空！");
    }
    return elements[top];// 返回顶部元素
  }

  @SuppressWarnings("unchecked")
  public void clear() {
    top = -1;
    elements = (E[]) new Object[LEN];
  }

  public static void main(String[] args) {
    StackArray<Integer> sa = new StackArray<Integer>();
    System.out.println("栈是否为空：" + sa.isEmpty());
    for (int i = 1; i <= 20; i++) {
      sa.push(i);
    }
    // 元素一次出栈
    for (int i = 1; i <= 10; i++) {
      System.out.println("出栈: " + sa.pop());
      System.out.println("size: " + sa.getSize());
      System.out.println("栈顶元素: " + sa.peek());
    }
    sa.clear();
    sa.pop();
    sa.push(50);
  }
}
