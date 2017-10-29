package com.topsec.tsm.datastructure.tree;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.LinkedListDLNode;
import com.topsec.tsm.datastructure.strategy.Strategy;

//孩子兄弟链表节点定义
public class TreeNodeChildBrother<T> implements TreeNode<T> {
	private T object;
	private String name;
	private String  description;
	private TreeNode<T> parent;
	private LinkedList<TreeNode<T>> children = new LinkedListDLNode<TreeNode<T>>();
	private TreeNode<T> firstChild;
	private TreeNode<T> firstBrother;
	private int height;  //节点高度
	private int size; //节点子孙数
	private Strategy<T> strategy; //比较策略
	
	public TreeNodeChildBrother(T object, TreeNodeChildBrother<T> parent,
			TreeNodeChildBrother<T> firstChild,
			TreeNodeChildBrother<T> firstBrother) {
		super();
		this.object = object;
		this.parent = parent;
		this.firstChild = firstChild;
		this.firstBrother = firstBrother;
	}

	public TreeNode<T> getFirstChild() {
		return firstChild;
	}

	public void setFirstChild(TreeNode<T> firstChild) {
		this.firstChild = firstChild;
	}

	public TreeNode<T> getFirstBrother() {
		return firstBrother;
	}

	public void setFirstBrother(TreeNode<T> firstBrother) {
		this.firstBrother = firstBrother;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}




	@Override
	public String getName() {
		return name;
	}


	@Override
	public String getDescription() {
		return description;
	}


	@Override
	public TreeNode<T> getParent() {
		return parent;
	}


	@Override
	public LinkedList<TreeNode<T>> getChildren() {
		return children;
	}


	@Override
	public T getObject() {
		return object;
	}


	@Override
	public TreeNode<T> getChild(int index) {
		return null;
	}


	@Override
	public LinkedList<TreeNode<T>> getChildren(boolean leaf) {
		return null;
	}


	@Override
	public boolean isLeaf() {
		return false;
	}


	@Override
	public void setName(String name) {
		this.name=name;
	}


	@Override
	public void setDescription(String description) {
		this.description=description;
	}


	@Override
	public void setParent(TreeNode<T> parent) {
		this.parent=parent;
	}


	@Override
	public void setChildren(LinkedList<TreeNode<T>> children) {
		this.children=children;
	}


	@Override
	public void setObject(T object) {
		this.object=object;
	}


	@Override
	public void addChild(TreeNode<T> child) {
	}


	@Override
	public Iterator<TreeNode<T>> preOrder(TreeNode<T> node) {
		return null;
	}


	@Override
	public Iterator<TreeNode<T>> postOrder(TreeNode<T> node) {
		return null;
	}
	
	
}
