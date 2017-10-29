package com.topsec.tsm.datastructure.iterator;

import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.Node;

public class LinkedListIterator<E> implements Iterator<E> {
	private LinkedList<E> list;
	private Node<E> current;

	public LinkedListIterator(LinkedList<E> list) {
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
			current = list.first();
		}
	}

	public void next()  throws OutOfBoundaryException {
		if (isDone())
			throw new OutOfBoundaryException("错误，已经没有元素");
		if (current == list.last())
			current = null;
		else
			current = list.getNext(current);
	}

	public boolean isDone() {
		return current == null;
	}

	public E currentItem() throws OutOfBoundaryException {
		if (isDone()) {
			throw new OutOfBoundaryException("错误，已经没有元素");
		}
		return current.getData();
	}
}