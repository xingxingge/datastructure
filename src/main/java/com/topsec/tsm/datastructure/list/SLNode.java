package com.topsec.tsm.datastructure.list;


/**
 * 
 * 单链表节点，定义后继
 * */
public class SLNode<E> implements Node<E>{
	private E element; //元素节点
	private SLNode<E> next; //指向下一个节点
	
	public SLNode() {
		this(null,null);
	}
	

	//新定义一个节点
	public SLNode(E ele, SLNode<E> next) {
		this.element=ele;
		this.next=next;
	}

	public SLNode<E> getNext() {
		return next;
	}

	public void setNext(SLNode<E> next) {
		this.next = next;
	}

	public E getData() {
		return element;
	}

	public void setData(E obj) {
		this.element=obj;
	}
	

}
