package com.topsec.tsm.datastructure.tree;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.List;
import com.topsec.tsm.datastructure.list.ListArray;


/**
 * 给树中的节点赋予一个具有某种意义的正数，我们称之为该结点的权。结 点 的带权路径 长度是从根结点到该结点之间的路径长度与结点权的乘积。
 * Huffman树又称最优二叉树,它是由n个带权叶子结点构成的所有二叉树中带权路径长度最小的二叉树
 * 
 * @author HuangXing
 * 
 */
public class HuffmanTreeNode extends BinTreeNode<Object> {
	private int weight; // 权值
	private String coding = ""; // 编码

	// 构造方法
	public HuffmanTreeNode(int weight) {
		this(weight, null);
	}

	public HuffmanTreeNode(int weight, Object e) {
		super(e);
		this.weight = weight;
	}

	// 改写父类方法
	public HuffmanTreeNode getParent() {
		return (HuffmanTreeNode) super.getParent();
	}

	public HuffmanTreeNode getLChild() {
		return (HuffmanTreeNode) super.getLChild();
	}

	public HuffmanTreeNode getRChild() {
		return (HuffmanTreeNode) super.getRChild();
	}

	// get&set方法
	public int getWeight() {
		return weight;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	private static HuffmanTreeNode buildHuffmanTree(HuffmanTreeNode[] nodes) {
		int n = nodes.length;
		if (n < 2)
			return nodes[0];
		List<HuffmanTreeNode> l = new ListArray<HuffmanTreeNode>(); // 根结点线性表，按weight从大到小有序
		for (int i = 0; i < n; i++)
			// 将结点逐一插入线性表
			insertToList(l, nodes[i]);
		for (int i = 1; i < n; i++) { // 选择weight最小的两棵树合并，循环n-1次
			HuffmanTreeNode min1 = (HuffmanTreeNode) l.remove(l.getSize() - 1);
			HuffmanTreeNode min2 = (HuffmanTreeNode) l.remove(l.getSize() - 1);
			HuffmanTreeNode newRoot = new HuffmanTreeNode(min1.getWeight()
					+ min2.getWeight());
			newRoot.setLChild(min1);
			newRoot.setRChild(min2); // 合并
			insertToList(l, newRoot);// 新树插入线性表
		}
		return (HuffmanTreeNode) l.get(0);// 返回Huffman树的根
	}

	// 将结点按照weight从大到小的顺序插入线性表
	private static void insertToList(List<HuffmanTreeNode> l, HuffmanTreeNode node) {
		for (int j = 0; j < l.getSize(); j++)
			if (node.getWeight() > ((HuffmanTreeNode) l.get(j)).getWeight()) {
				l.insert(j, node);
				return;
			}
		l.insert(l.getSize(), node);
	}

	// 递归生成Huffman编码
	protected static void generateHuffmanCode(HuffmanTreeNode root) {
		if (root == null)
			return;
		if (root.hasParent()) {
			if (root.isLChild())
				root.setCoding(root.getParent().getCoding() + "0"); // 向左为0
			else
				root.setCoding(root.getParent().getCoding() + "1"); // 向右为1
		}
		generateHuffmanCode(root.getLChild());
		generateHuffmanCode(root.getRChild());
	}

	public static void main(String[] args) {
		HuffmanTreeNode[] nodes = new HuffmanTreeNode[5];
		for (int i = 0, j = 97; i < nodes.length; i++, j++) {
			nodes[i] = new HuffmanTreeNode(i);
			Character c = (char) j;
			nodes[i].setCoding(c.toString());
		}
		HuffmanTreeNode output = buildHuffmanTree(nodes);
		Iterator<?> lt = output.preOrder();
		while (!lt.isDone()) {
			HuffmanTreeNode bHuffmanTreeNode = (HuffmanTreeNode) lt.currentItem();
			generateHuffmanCode(bHuffmanTreeNode);
			System.out.println(bHuffmanTreeNode.getCoding() + "\t"
					+ bHuffmanTreeNode.getWeight());
			lt.next();
		}

	}
}
