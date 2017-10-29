package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.exception.UnsupportedOperation;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;

public class UndirectedGraph extends AbstractGraph {

	public UndirectedGraph(int type) {
		super(type);
	}

	@Override
	public Iterator getVextex() {
		return super.getVertex();
	}

	// 分别实现
	public void remove(Vertex v) {

	}

	// 分别实现
	public void remove(Edge e) {

	}

	// 分别实现，返回两个顶点之间的边，无向图在邻接表中搜寻
	public Edge edgeFromTo(Vertex u, Vertex v) {
		// 如果v在u的邻接表，那么就是邻接的
		LinkedList ll = u.getAdjacentEdges();
		Iterator it = ll.elements();
		while (!it.isDone()) {
			Edge ed = (Edge) it.currentItem();
			if (ed.getSecondVex() == v) {
				return ed;
			}
			it.next();
		}
		return null;
	}

//	// 分别实现,无向图直接返回邻接表中的所有第二顶点
//	public Iterator adjVertexs(Vertex u) {
//		LinkedList adjVertexs = new LinkedListDLNode();
//		if (this.vertexs.contains(u)) {
//			Iterator ade = u.getAdjacentEdges().elements();
//			while (!ade.isDone()) {
//				Edge temp = (Edge) ade.currentItem();
//				// 获取邻接边表的第二顶点
//				adjVertexs.insertLast(temp.getSecondVex());
//				ade.next();
//			}
//			if (!adjVertexs.isEmpty()) {
//				return adjVertexs.elements();
//			}
//		}
//		return null;
//	}

	/**
	 * 
	 * 求MST时，对v.application的操作
	 * @param v
	 * @return
	 */
	// 获取到达顶点v的最小横切边权值
	protected int getCrossWeight(Vertex v) {
		if (getCrossEdge(v) != null)
			return getCrossEdge(v).getWeight();
		else
			return Integer.MAX_VALUE;
	}

	// 获取到达顶点v的最小横切边
	protected Edge getCrossEdge(Vertex v) {
		return (Edge) v.getAppObj();
	}

	// 设置到达顶点v的最小横切边
	protected void setCrossEdge(Vertex v, Edge e) {
		v.setAppObj(e);
	}

	/**
	 * 
	 * Prim算法：最小生成树
	 */
	// 无向图单独实现最小生成树
	public void generateMST() {
		resetVexStatus(); // 重置图中各顶点的状态信息
		resetEdgeType(); // 重置图中各边的类型信息
		Iterator it = getVertex(); // （调用图操作③）
		Vertex v = (Vertex) it.currentItem();// 选第一个顶点作为起点
		v.setToVisited(); // 顶点v进入集合S
		// 初始化顶点集合S到V- S各顶点的最短横切边
		for (it.first(); !it.isDone(); it.next()) {
			Vertex u = (Vertex) it.currentItem();
			Edge e = edgeFromTo(v, u); // （调用图操作⑦）
			setCrossEdge(u, e); // 设置到达V- S中顶点u的最短横切边
		}
		for (int t = 1; t < getVexNum(); t++) { // 进行|V|-1 次循环找到|V|-1 条边
			Vertex k = selectMinVertex(it); // 选择轻边在V- S中的顶点k
			k.setToVisited(); // 顶点k加入S
			Edge mst = getCrossEdge(k); // 割(S , V - S) 的轻边
			if (mst != null)
				mst.setToMST(); // 将边加入MST
			// 以k为中间顶点修改S到V- S中顶点的最短横切边
			Iterator adjIt = adjVertexs(k); // 取出k的所有邻接点
			for (adjIt.first(); !adjIt.isDone(); adjIt.next()) {
				Vertex adjV = (Vertex) adjIt.currentItem();
				Edge e = edgeFromTo(k, adjV); // （调用图操作⑦）
				if (e.getWeight() < getCrossWeight(adjV))// 发现到达adjV更短的横切边
					setCrossEdge(adjV, e);
			}// for
		}// for(int t=1...
	}

	// 查找轻边在V- S中的顶点
	protected Vertex selectMinVertex(Iterator it) {
		Vertex min = null;
		for (it.first(); !it.isDone(); it.next()) {
			Vertex v = (Vertex) it.currentItem();
			if (!v.isVisited()) {
				min = v;
				break;
			}
		}
		for (; !it.isDone(); it.next()) {
			Vertex v = (Vertex) it.currentItem();
			if (!v.isVisited() && getCrossWeight(v) < getCrossWeight(min))
				min = v;
		}
		return min;
	}
	
	// 下面两个由有向图单独实现
	@Override
	public Iterator toplogicalSort() throws UnsupportedOperation {
		throw new UnsupportedOperation("无向图不支持拓扑操作");
	}

	@Override
	public void criticalPath() throws UnsupportedOperation {
		throw new UnsupportedOperation("无向图不支持关键路径操作");

	}


}
