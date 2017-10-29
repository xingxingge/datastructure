package com.topsec.tsm.datastructure.sorter;

import com.topsec.tsm.datastructure.strategy.DefaultStrategy;
import com.topsec.tsm.datastructure.strategy.Strategy;

public abstract class Sorter<E> {

  private Strategy<E> strategy;

  public Sorter() {
    strategy = new DefaultStrategy<>();
  }

  public Sorter(Strategy strategy) {
    this.strategy = strategy;
  }


  public Strategy<E> getStrategy() {
    return strategy;
  }

  public void setStrategy(Strategy<E> strategy) {
    this.strategy = strategy;
  }


  public   E[] sort(E[] objs){
    if (!isSortAvailable(objs)) return objs;
    return doSort(objs);

  }

  public abstract E[] doSort(E[] objs);

  private boolean isSortAvailable(E[] objs) {
    if (objs == null || objs.length < 1) return false;
    return true;
  }
}
