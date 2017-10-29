package com.topsec.tsm.datastructure.tree;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;

//树的节点定义
public interface TreeNode<T> {

	String getName();

	String getDescription();

	TreeNode<T> getParent();

	LinkedList<TreeNode<T>> getChildren();

	T getObject();

	TreeNode<T> getChild(int index);

	LinkedList<TreeNode<T>> getChildren(boolean leaf);

	boolean isLeaf();

	void setName(String name);

	void setDescription(String description);

	void setParent(TreeNode<T> parent);

	void setChildren(LinkedList<TreeNode<T>> children);

	void setObject(T object);

	void addChild(TreeNode<T> child);

	Iterator<TreeNode<T>> preOrder(TreeNode<T> node);// 树的先根遍历

	Iterator<TreeNode<T>> postOrder(TreeNode<T> node);// 树的后根遍历
}
