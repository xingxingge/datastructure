package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.LinkedListDLNode;
import com.topsec.tsm.datastructure.list.Node;


/**
 * 顶点定义类
 * **/
public class Vertex {
	private Object info; //顶点信息
	private LinkedList<Edge> adjacentEdges; //邻接边表
	private LinkedList<Edge> reAdjacentEdges;// 逆邻接边表,无向图为空
	private boolean visited;//访问状态
	private Node vexPosition;//顶点在顶点表中的位置
	private int graphType;//顶点在顶点表的类型
	private Object application;//应用，最短路径用path，关键路径使用Vtime
	//构造方法，在图g中引入一个顶点
	public Vertex(Graph g,Object info){
		this.info=info;
		adjacentEdges= new LinkedListDLNode();
		//adjacentEdges= new LinkedListDLNode();
		reAdjacentEdges= new LinkedListDLNode();
		//reAdjacentEdges= new LinkedListDLNode();
		this.visited=false;
		graphType=g.getType();
		vexPosition=g.insert(this);
	}
	//辅助方法：判断顶点所在图的类型
	private boolean isUnDiGraphNode(){
		return graphType==Graph.UndirectedGraph;//true是无向图
	}
	//获取设置顶点信息
	public Object getInfo() {
		return info;
	}
	//设置顶点信息
	public void setInfo(Object info) {
		this.info = info;
	}
	//获得顶点度的相关信息
	public int getDeg(){
		if (isUnDiGraphNode()) {
			//无向图返回邻接表的规模
			return adjacentEdges.getSize();	
		}
		else
			//有向图返回出度与入度和
			return getOutDeg()+getInDeg();
	}
	//返回出度
	public int getOutDeg(){
		//获取出度,两种图结构计算方法一样，等于邻接表的规模
		return adjacentEdges.getSize();
	}
	//获取入度，无向图的入度等于出度（邻接表的规模），有向图的入度等于逆邻接表的规模;
	public int getInDeg(){
		if (isUnDiGraphNode()) {
			//无向图返回邻接表的规模
			return adjacentEdges.getSize();	
		}
		else
			return reAdjacentEdges.getSize();		
	}
	//获取与顶点关联的边
	//出边
	public LinkedList<Edge> getAdjacentEdges() {
		return adjacentEdges;
	}
	//入边
	public LinkedList<Edge> getReAdjacentEdges() {
		if (isUnDiGraphNode()) {
			//无向图
			return adjacentEdges;	
		}
		else
			//有向图
			return reAdjacentEdges;	
	}
	
	//取顶点在所属图顶点集中的位置
	public Node getVexPosition() {
		return vexPosition;
	}
	
	//顶点访问相关方法

	public boolean isVisited() {
		return visited;
	}
	public void setToVisited() {
		visited = true;
	}
	public void setToUnVisited() {
		visited = false;
	}
	//获取设置顶点信息
	public Object getAppObj() {
		return application;
	}
	public void setAppObj(Object app) {
		this.application = app;
	}
	//重置顶点状态信息
	public void resetStatus(){
		visited=false;
		application=null;
	}
	//查找info为某个值的顶点
}
