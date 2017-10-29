package com.topsec.tsm.datastructure.strategy;

import java.util.Objects;

public class DefaultStrategy<E> implements Strategy<E> {

  public boolean equal(E obj1, E obj2) {
    return Objects.equals(obj1, obj2);
  }

  public int compare(E obj1, E obj2) {
    if (obj1 instanceof Comparable && obj2 instanceof Comparable) {
      return ((Comparable) obj1).compareTo(obj2);
    }
    else {
      return obj1.toString().compareTo(obj2.toString());
    }
  }
}
