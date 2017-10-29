package com.topsec.tsm.datastructure.iterator;

import com.topsec.tsm.datastructure.exception.OutOfBoundaryException;
import com.topsec.tsm.datastructure.list.ListSLinked;
import com.topsec.tsm.datastructure.list.SLNode;

public class ListSLinkedIterator<E> implements Iterator<E> {
	public ListSLinked<E> list;
	public SLNode<E> current;

	public ListSLinkedIterator(ListSLinked<E> list) {
		this.list = list;
		if (list.isEmpty()) {
			current = null;			
		}else {
			this.first();
		}
	}

	public void first() {
		if (list.isEmpty()) {
			current = null;
		} else {
			current=list.getNode(0);
		}
	}

	public void next() {
		if (null==current.getNext()) {
			current=null;			
		}else {
			current =current.getNext();
			}
	}

	public boolean isDone() {
		return current == null;
	}

	public E currentItem() {
		if (isDone()){
			throw new OutOfBoundaryException("没有元素了");
		}
		return current.getData();
	}

}
