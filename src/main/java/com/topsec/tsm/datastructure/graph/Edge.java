package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.list.Node;


public class Edge {
	// 双链式存储边的定义
	public static final int NORMAL = 0;
	public static final int MST = 1; // MST边
	public static final int CRITICAL = 2;// 关键路径中的边

	private int weight; // 边的权重
	private Object info;// 边的信息
	private Node edgePosition; // 边在边表中的位置
	private Node firstVexPosition; // 边的第一顶点在顶点表中的位置
	private Node secondVexPosition;// 边的第二顶点在顶点表中的位置
	private Node edgeFirstPosition;
	private Node edgeSecondPosition;// 边在第一(二)顶点的邻接(逆邻接)边表中的位置，在无向图中就是两个顶点的邻接边表中的位置
	private int type; // 边的类型
	
	private int graphType; // 所在图的类型

	// 构造方法在图中引入一条边,顶点是u,v
	
	public Edge(Graph g, Vertex u, Vertex v, Object info) {
		this(g, u, v, info, 1);
	}

	public Edge(Graph g, Vertex u, Vertex v, Object info, int weight) {
		this.info = info;
		this.weight = weight;
		edgePosition = g.insert(this);
		firstVexPosition = u.getVexPosition();
		secondVexPosition = v.getVexPosition();
		type = Edge.NORMAL;
		graphType = g.getType();
		if (graphType == Graph.UndirectedGraph) {
			// 如果是无向图，边应该加入两个顶点的邻接边表，并返回边的位置
			edgeFirstPosition = u.getAdjacentEdges().insertLast(this);
			edgeSecondPosition = v.getAdjacentEdges().insertLast(this);

		} else {
			///如果是有向图,边加入起始点的邻接边表,终止点的逆邻接边表,返回边的位置，边只是其中的元素,node找那个包含边
			edgeFirstPosition = u.getAdjacentEdges().insertLast(this);
			edgeSecondPosition = v.getReAdjacentEdges().insertLast(this);
		}

	}

	// get && seg 方法
	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	// 获得边的第一顶点和第二顶点,返回的元素
	public Vertex getFirstVex() {
		return (Vertex) firstVexPosition.getData();
	}

	public Vertex getSecondVex() {
		return (Vertex) secondVexPosition.getData();
	}

	// 获得边第一顶点和第二顶点的在定点表中的位置
	public Node getFirstVexPosition() {
		return firstVexPosition;
	}

	public Node secondVexPosition() {
		return secondVexPosition;
	}

	// 返回边在第一顶点和第二顶点边表中的位置（邻接表和逆邻接表中的位置）
	public Node getEdgeFirstPosition() {
		return edgeFirstPosition;
	}

	public Node getEdgeSecondPosition() {
		return edgeSecondPosition;
	}

	// 返回边在边表中的位置

	public Node getEdgePosition() {
		return edgePosition;
	}

	// 与边类型相关的方法

	public void setToMST() {
		type = Edge.MST;
	}

	public void setToCritical() {
		type = Edge.CRITICAL;
	}

	public  void resetType() {
		type=Edge.NORMAL;
	}
	public boolean isMTSEdge(){
		return type==Edge.MST;
	}
	public boolean isCritical(){
		return type==Edge.CRITICAL;
	}
	

}
