package com.topsec.tsm.datastructure.iterator;

public interface Iterator<E> {
	void first();
	void next();
	boolean isDone();
	E currentItem();
}