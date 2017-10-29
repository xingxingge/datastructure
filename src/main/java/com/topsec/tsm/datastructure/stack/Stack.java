package com.topsec.tsm.datastructure.stack;

public interface Stack<E> {
  int getSize();   //栈长度

  boolean isEmpty();  //判断栈是否是空

  void push(E e);  //把元素压入栈顶

  E pop() throws StackEmptyException;  //栈顶元素出栈，返回栈顶元素

  E peek() throws StackEmptyException; //取栈顶元素，不出栈

  void clear(); //清空栈

}
