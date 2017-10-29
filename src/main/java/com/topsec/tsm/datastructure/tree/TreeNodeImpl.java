package com.topsec.tsm.datastructure.tree;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.LinkedListDLNode;

public class TreeNodeImpl<E> implements TreeNode<E>, Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private TreeNode<E> parent;
	private LinkedList<TreeNode<E>> children = new LinkedListDLNode<TreeNode<E>>();
	private E object;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public TreeNode<E> getParent() {
		return parent;
	}

	@Override
	public LinkedList<TreeNode<E>> getChildren() {
		return children;
	}

	@Override
	public E getObject() {
		return object;
	}

	@Override
	public TreeNode<E> getChild(int index) {
		return null;
	}

	@Override
	public LinkedList<TreeNode<E>> getChildren(boolean leaf) {
		LinkedList<TreeNode<E>> retChildren = new LinkedListDLNode<TreeNode<E>>();
		for (Iterator<TreeNode<E>> itr = children.elements(); !itr.isDone(); itr
				.next()) {
			retChildren.insertLast(itr.currentItem());
		}
		return retChildren;
	}

	@Override
	public boolean isLeaf() {
		return (children.getSize() == 0);
	}

	@Override
	public void setName(String name) {
		Validate.notEmpty(name, "name is empty!");
		this.name = name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}

	@Override
	public void setChildren(LinkedList<TreeNode<E>> children) {
		Validate.notNull(children, "children is null!");
	}

	@Override
	public void setObject(E object) {
		this.object = object;
	}

	@Override
	public void addChild(TreeNode<E> child) {
	}

	@Override
	public Iterator<TreeNode<E>> preOrder(TreeNode<E> node) {
		return null;
	}

	@Override
	public Iterator<TreeNode<E>> postOrder(TreeNode<E> node) {
		return null;
	}

}
