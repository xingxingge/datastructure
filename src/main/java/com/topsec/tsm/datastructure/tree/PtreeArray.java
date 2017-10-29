package com.topsec.tsm.datastructure.tree;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.List;
import com.topsec.tsm.datastructure.list.ListArray;
import com.topsec.tsm.datastructure.strategy.DefaultStrategy;
import com.topsec.tsm.datastructure.strategy.Strategy;

/**
 * @author HuangXing
 * 
 * @param <E>
 */

public class PtreeArray<E> {
	private final int LEN = 8;
	private Strategy<PtreeNode<E>> strategy;
	private Strategy<E> strategy1;
	private int size;
	private PtreeNode<E>[] ptreeNodes;
	private int height; // 树的高度

	@SuppressWarnings("unchecked")
	public PtreeArray(Strategy<PtreeNode<E>> strategy, E data) {
		this.strategy = strategy;
		size = 1;
		this.ptreeNodes = (PtreeNode<E>[]) new Object[LEN];
		ptreeNodes[0] = new PtreeNode<E>(data, -1);
		this.height = 0;
	}

	public PtreeArray(E data) {
		this(new DefaultStrategy<PtreeNode<E>>(), data);
	}

	// 指定长度
	@SuppressWarnings("unchecked")
	public PtreeArray(E data, int length) {// 新建树，指定树的默认大小，以后按照两倍增长
		this.strategy = new DefaultStrategy<PtreeNode<E>>();
		this.strategy1 = new DefaultStrategy<E>();
		this.ptreeNodes = new PtreeNode[length];
		ptreeNodes[0] = new PtreeNode<E>(data, -1);// parent为为-1表示根节点
		size = 1;
		this.height = 0;
	}

	// 返回数组元素长度
	public int getElementSize() {
		return ptreeNodes.length;
	}

	// 返回树的尺寸
	public int getSize() {
		return size;
	}

	// 判断树是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	// 为某个指定的节点添加子节点
	// 在树的父亲数组表示法中，对于涉及查询儿子和兄弟信息的树操作，可能要遍历整个数组。为了节省查询时间，可以规定指示儿子的数组下标值大于父亲的数组下标值，而指示兄弟结点的数组下标值随着兄弟的从左到右是递增的
	// 首先要找到这个节点的下标
	public void addPreeNode(PtreeNode<E> p, E data) throws RuntimeException {

		if (size >= ptreeNodes.length) {
			expandSpace();
		}
		int i = getIndex(p);
		if (i >= 0) {
			PtreeNode<E> pt = new PtreeNode<E>(data, i);
			ptreeNodes[size++] = pt;
			// 更新高度
			int high = getHigh(pt);
			this.height = (this.height >= high) ? height : high;
		} else {
			throw new RuntimeException("找不到元素");
		}

	}

	private int getHigh(PtreeNode<E> p) {
		// 更新高度
		int high = 0;
		while (p.getParent() >= 0) {
			high++;
			p = this.get(p.getParent());
		}
		return high;
	}

	// 根据id值查找节点
	public PtreeNode<E> get(int index) {
		return this.ptreeNodes[index];

	}

	// 通过元素值搜索节点,比较元素是否相等
	public PtreeNode<E> get(E data) {
		for (int i = 0; i < size; i++) {
			if (strategy1.equal(ptreeNodes[i].getData(), data)) {
				return ptreeNodes[i];
			}
		}
		return null;

	}

	//
	// 判断某个节点是否有子节点
	public boolean hasChild(PtreeNode<E> e) {
		return !this.getChildren(e).isEmpty();
	}

	// 判断a节点是否是b的父节点
	public boolean isParent(PtreeNode<E> a, PtreeNode<E> b) {
		if (a.getParent() == b.getParent()) {
			return false;
		}
		return getIndex(a) == b.getParent();
	}

	// 辅助方法，查找某个节点的下标,如果没找到，返回-1
	private int getIndex(PtreeNode<E> p) {
		for (int i = p.getParent(); i < size; i++) {// 找到下标
			if (i == -1) {
				return 0;
			}
			if (strategy.equal(p, ptreeNodes[i])) {
				return i;
			}
		}
		return -1;
	}

	private void expandSpace() {
		@SuppressWarnings("unchecked")
		PtreeNode<E>[] ptn = new PtreeNode[ptreeNodes.length * 2];
		for (int i = 0; i < ptreeNodes.length; i++) {
			ptn[i] = ptreeNodes[i];
		}
		ptreeNodes = ptn;
	}

	// 获取树的高度
	public int getHeight() {
		return this.height;

	}

	// 返回根节点：
	public PtreeNode<E> getRoot() {
		if (isEmpty()) {
			return null;
		}
		return get(0);
	}

	// 深度优先遍历(先序遍历)，递归算法
	public List<PtreeNode<E>> DFSTraverse(PtreeNode<E> x) {
		List<PtreeNode<E>> list = new ListArray<PtreeNode<E>>();
		int index = getIndex(x);
		if (index < 0) {
			return list;
		}
		list.insertLast(x);

		List<PtreeNode<E>> inner = getChildren(x);// 拿到子节点
		for (int i = 0; i < inner.getSize(); i++) {
			PtreeNode<E> p = inner.get(i);
			if (hasChild(p)) {
				List<PtreeNode<E>> listInner = DFSTraverse(p);
				for (int j = 0; j < listInner.getSize(); j++) {
					list.insertLast(listInner.get(j));
				}
			} else {
				list.insertLast(p);
			}
		}
		return list;
	}

	// 广度优先遍历（先根遍历）
	public List<PtreeNode<E>> BFSTraverse(PtreeNode<E> x) {
		List<PtreeNode<E>> list = new ListArray<PtreeNode<E>>();
		if (getIndex(x) < 0) {
			return list;
		}
		List<PtreeNode<E>> inner = getChildren(x);// 首先顺序插入所有子节点
		for (int i = 0; i < inner.getSize(); i++) {
			PtreeNode<E> p = inner.get(i);
			list.insertLast(p);
		}
		// 遍历所有子节点，插入各自的子树
		for (int i = 0; i < inner.getSize(); i++) {
			PtreeNode<E> p = inner.get(i);
			if (hasChild(p)) {
				List<PtreeNode<E>> listInner = BFSTraverse(p);
				for (int j = 0; j < listInner.getSize(); j++) {
					list.insertLast(listInner.get(j));
				}
			} 
		}
		return list;
	}

	// 获取节点的所有子节点
	public List<PtreeNode<E>> getChildren(PtreeNode<E> x) {
		List<PtreeNode<E>> list = new ListArray<PtreeNode<E>>();
		int i = getIndex(x);
		for (int j = i + 1; j < ptreeNodes.length; j++) {
			if (null != this.get(j) && this.get(j).getParent() == i) {
				list.insertLast(this.get(j));
			}
		}
		return list;
	}

	// 深度优先遍历
	// 断开父子关系
	// 转成二叉树
	// 广度优先遍历
	// 查找元素的第一级子节点
	// 从元素出发，查找所有的子节点
	public static void main(String[] args) {
		PtreeArray<String> pt = new PtreeArray<String>("中国", 3);
		PtreeNode<String> rt = pt.getRoot();// 获取根节点
		System.out.println("根节点：" + rt.getData());
		System.out.println("节点数：" + pt.getSize());
		// 根节点添加一个子节点
		pt.addPreeNode(rt, "北京");
		pt.addPreeNode(rt, "上海");
		pt.addPreeNode(rt, "广州");
		PtreeNode<String> my1 = pt.get("广州");
		pt.addPreeNode(my1, "番禹");
		PtreeNode<String> m2 = pt.get("番禹");
		pt.addPreeNode(m2, "文豪路");
		pt.addPreeNode(m2, "大修路");
		PtreeNode<String> bj = pt.get("北京");
		pt.addPreeNode(bj, "海淀");
		pt.addPreeNode(bj, "丰台");
		PtreeNode<String> hd = pt.get("海淀");
		pt.addPreeNode(hd, "五道口");
		System.out.println("添加元素后的节点数：" + pt.getSize());
		System.out.println("内部数组长度：" + pt.getElementSize());
		List<PtreeNode<String>> list = pt.getChildren(m2);
		System.out.print(pt.get(4).getData() + "的节点是：");
		Iterator<PtreeNode<String>> itr = list.elements();
		while (!itr.isDone()) {
			System.out.print(itr.currentItem().getData() + "\t");
			itr.next();
		}
		System.out.println("\n树的高度：" + pt.getHeight());
		// dfsTraverse
		System.out.println("\n深度优先遍历：");
		itr = pt.DFSTraverse(rt).elements();
		while (!itr.isDone()) {
			System.out.print(itr.currentItem().getData() + "\t");
			itr.next();
		}
		System.out.println("\n广度优先遍历：");
		List<PtreeNode<String>> list1 =pt.BFSTraverse(rt);
		list1.insert(0, rt);
		itr = list1.elements();
		int count = 0;
		while (!itr.isDone() && count <= 12) {
			System.out.print(itr.currentItem().getData()  + "\t");
			count++;
			itr.next();
		}
	}
}
