package com.topsec.tsm.datastructure.tree;

import com.topsec.tsm.datastructure.list.ListSLinked;
import com.topsec.tsm.datastructure.strategy.Strategy;

//孩子链表节点定义
public class TreeNodeChild<T>{
	private T data;
	private int parent;
	private ListSLinked<T> childLink;//孩子链表，单链表
	private int height;  //节点高度
	private int size; //节点子孙数
	private Strategy<T> strategy; //比较策略
	
	public TreeNodeChild(T data, int parent) {
		super();
		this.data = data;
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return "TreeNodeChildLindedList [data=" + data + ", parent=" + parent
				+ ", childLink=" + childLink + "]";
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public ListSLinked<T> getChildLink() {
		return childLink;
	}
	public void setChildLink(ListSLinked<T> childLink) {
		this.childLink = childLink;
	}
	

}
