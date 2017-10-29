package com.topsec.tsm.datastructure.strategy;

public interface Strategy<E> {
  boolean equal(E e, E data);
  int compare(E obj1, E obj2);
}
