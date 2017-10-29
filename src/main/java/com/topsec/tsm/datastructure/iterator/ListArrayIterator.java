package com.topsec.tsm.datastructure.iterator;

import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.list.ListArray;

public class ListArrayIterator<E> implements Iterator<E> {

  private ListArray<E> list;
  private E current;

  public ListArrayIterator(ListArray<E> list) {
    this.list = list;
    if (list.isEmpty()) {
      current = null;
    } else {
      first();
    }
  }

  public void first() {
    if (list.isEmpty()) {
      current = null;
    } else {
      current = list.get(0);
    }
  }

  public void next() {
    if (isDone()) throw new OutOfBoundaryException("错误，已经没有元素");
    if (current == list.get(list.getSize()))
      current = null;
    else {
      int i = list.indexOf(current);
      current = list.get(++i);
    }
  }

  public boolean isDone() {
    return current == null;
  }
  public boolean hasNext() {
    return ! isDone();
  }

  public E currentItem() {
    if (isDone()) {
      throw new OutOfBoundaryException("错误，已经没有元素");
    }
    return current;
  }

}
