package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.exception.UnsupportedOperation;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.Node;

public interface Graph {
  int UndirectedGraph = 0;// 无向图
  int DirectedGraph = 1;// 有向图

  //返回图的类型
  int getType();

  //返回图的顶点数
  int getVexNum();

  //返回图的边数
  int getEdgeNum();

  //返回图的所有顶点
  Iterator getVextex();

  //返回图的所有边
  Iterator getEdge();

  //删除一个顶点
  void remove(Vertex v);

  //查找一个顶点
  Vertex findVertex(Object e);

  //判断图是否包含一个元素
  boolean containVertex(Object e);

  //删除图的一条边
  void remove(Edge e);

  //添加一个顶点
  //list的每个元素都是一个顶点，每个顶点包含其他部分
  Node insert(Vertex v);

  //添加一条边
  Node insert(Edge e);

  //判断顶点u,v是否邻接
  boolean areAdjacent(Vertex u, Vertex v);

  //返回从u到v的边，如果没有就返回空
  Edge edgeFromTo(Vertex u, Vertex v);

  //返回从u出发可以到达的邻接顶点
  Iterator adjVertexs(Vertex u);

  //对图进行深度优先遍历
  Iterator dfsTraverse(Vertex v);

  //图的深度u优先非递归
  Iterator dfsNoRecursionTraverse(Vertex v);

  //对图进行广度优先遍历
  Iterator bfsTraverse(Vertex v);

  //请顶点v到其他顶点的最短路径
  Iterator shortestPath(Vertex v);

  //求无向图的最小生成树，有向图不支持
  void generateMST() throws UnsupportedOperation;

  //求有向图的拓扑序列，无向图不支持
  Iterator toplogicalSort() throws UnsupportedOperation;

  //求有向无环图的关键路径，无向图不支持
  void criticalPath() throws UnsupportedOperation;

}
