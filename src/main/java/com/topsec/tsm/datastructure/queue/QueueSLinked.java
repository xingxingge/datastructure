package com.topsec.tsm.datastructure.queue;

import com.topsec.tsm.datastructure.exception.QueueEmptyException;
import com.topsec.tsm.datastructure.list.SLNode;
import com.topsec.tsm.datastructure.stack.StackEmptyException;

public class QueueSLinked<E> implements Queue<E> {
	private SLNode<E> front;
	private SLNode<E> rear;
	private int size;

	public QueueSLinked() {
		front = new SLNode<E>();
		rear = front;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(E e) {
		SLNode<E> p = new SLNode<E>(e, null);
		rear.setNext(p);
		rear=p;
		size++;
	}

	public E dequeue() throws QueueEmptyException {
		if (size < 1)
			throw new QueueEmptyException("���󣬶���Ϊ�գ�");
		SLNode<E> p = front.getNext();
		front.setNext(p.getNext());
		size--;
		if (size < 1) rear=front;
		return p.getData();
	}

	public E peek() throws QueueEmptyException {

		if (size < 1)
			throw new StackEmptyException("���󣬶���Ϊ�գ�");
		return front.getNext().getData();

	}

}
