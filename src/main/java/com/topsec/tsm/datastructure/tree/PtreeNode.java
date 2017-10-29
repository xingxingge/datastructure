package com.topsec.tsm.datastructure.tree;

import java.io.Serializable;

import com.topsec.tsm.datastructure.list.Node;

public class PtreeNode<E> implements Node<E>,Serializable {

	private static final long serialVersionUID = 1L;
	private E data;// 数据域
	private int parent;// 父节点的id

	public PtreeNode() {
		super();
	}

	public PtreeNode(E data, int parent) { // 实例化树节点
		super();
		this.data = data;
		this.parent=parent;
	}

	@Override
	public E getData() {
		return data;
	}

	@Override
	public void setData(E obj) {
		this.data = obj;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + parent;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		PtreeNode<E> other = (PtreeNode<E>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (parent != other.parent)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PTree [data=" + data + ", parent=" + parent + "]";
	}

}
