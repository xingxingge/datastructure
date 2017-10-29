package com.topsec.tsm.datastructure.queue;

import com.topsec.tsm.datastructure.exception.QueueEmptyException;

public interface Queue<E> {
	int getSize();
	boolean isEmpty();
	void  enqueue(E e);
	E dequeue() throws QueueEmptyException;
	E peek() throws QueueEmptyException;
}
