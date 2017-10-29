package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.exception.UnsupportedOperation;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.LinkedListDLNode;
import com.topsec.tsm.datastructure.stack.Stack;
import com.topsec.tsm.datastructure.stack.StackSLinked;


/**
 * 有向图的类定义
 *
 * @author HuangXing
 */
public class DirectedGraph extends AbstractGraph {

  public DirectedGraph(int type) {
    super(type);
  }

  @Override
  public Iterator getVextex() {
    return super.getVertex();
  }

  @Override
  public void remove(Vertex v) {

  }

  @Override
  public void remove(Edge e) {

  }

  @Override
  // 分别实现，返回两个顶点之间的边，有向图需要在邻接表和逆邻接表中搜寻
  public Edge edgeFromTo(Vertex u, Vertex v) {
    LinkedList ll = u.getAdjacentEdges();
    Iterator it = ll.elements();
    while (!it.isDone()) {
      Edge ed = (Edge) it.currentItem();
      if (ed.getSecondVex() == v) {
        return ed;
      }
      it.next();

    }
    ll = u.getReAdjacentEdges();
    it = ll.elements();
    while (!it.isDone()) {
      Edge ed = (Edge) it.currentItem();
      if (ed.getSecondVex() == v) {
        return ed;
      }
      it.next();

    }
    return null;

  }

//  @Override
//  public Iterator adjVertexs(Vertex u) {
//    u.getAdjacentEdges()
//    return null;
//  }

  // 这个方法由无向图完成
  @Override
  public void generateMST() throws UnsupportedOperation {
    throw new UnsupportedOperationException("有向图不支持最小生成树");
  }

  @Override
  /**
   * 拓扑序列
   *
   * **/
  public Iterator toplogicalSort() {
    LinkedList topSeq = new LinkedListDLNode(); // 拓扑序列
    Stack s = new StackSLinked();
    Iterator it = getVertex();
    for (it.first(); !it.isDone(); it.next()) { // 初始化顶点集应用信息
      Vertex v = (Vertex) it.currentItem();
      v.setAppObj(Integer.valueOf(v.getInDeg()));
      if (v.getInDeg() == 0) s.push(v);
    }
    while (!s.isEmpty()) {
      Vertex v = (Vertex) s.pop();
      topSeq.insertLast(v); // 生成拓扑序列
      Iterator adjIt = adjVertexs(v); // 对于v的每个邻接点入度减1
      for (adjIt.first(); !adjIt.isDone(); adjIt.next()) {
        Vertex adjV = (Vertex) adjIt.currentItem();
        int in = getTopInDe(adjV) - 1;
        setTopInDe(adjV, in);
        if (in == 0) s.push(adjV); // 入度为0的顶点入栈
      }// for adjIt
    }// while
    if (topSeq.getSize() < getVexNum())
      return null;
    else
      return topSeq.elements();
  }

  /**
   * 拓扑排序算法中，对v.application的操作
   *
   * @param v
   * @return
   */
  // 取或设置顶点v的当前入度
  private int getTopInDe(Vertex v) {
    return ((Integer) v.getAppObj()).intValue();
  }

  private void setTopInDe(Vertex v, int indegree) {
    v.setAppObj(Integer.valueOf(indegree));
  }


  @Override
  /**
   * 求关键路径
   * **/
  public void criticalPath() {
    Iterator it = toplogicalSort();
    resetEdgeType(); // 重置图中各边的类型信息
    if (it == null) return;
    LinkedList reTopSeq = new LinkedListDLNode(); // 逆拓扑序列
    for (it.first(); !it.isDone(); it.next()) { // 初始化各点ve与vl，并生成逆拓扑序列
      Vertex v = (Vertex) it.currentItem();
      Vtime time = new Vtime(0, Integer.MAX_VALUE); // ve=0,vl=∞
      v.setAppObj(time);
      reTopSeq.insertFirst(v);
    }
    for (it.first(); !it.isDone(); it.next()) { // 正向拓扑序列求各点ve
      Vertex v = (Vertex) it.currentItem();
      Iterator adjIt = adjVertexs(v);
      for (adjIt.first(); !adjIt.isDone(); adjIt.next()) {
        Vertex adjV = (Vertex) adjIt.currentItem();
        Edge e = edgeFromTo(v, adjV);
        if (getVE(v) + e.getWeight() > getVE(adjV)) // 更新最早开始时间
          setVE(adjV, getVE(v) + e.getWeight());
      }
    }
    Vertex dest = (Vertex) reTopSeq.first().getData();
    setVL(dest, getVE(dest)); // 设置汇点vl=ve
    Iterator reIt = reTopSeq.elements();
    for (reIt.first(); !reIt.isDone(); reIt.next()) { // 逆向拓扑序列求各点vl
      Vertex v = (Vertex) reIt.currentItem();
      Iterator adjIt = adjVertexs(v);
      for (adjIt.first(); !adjIt.isDone(); adjIt.next()) {
        Vertex adjV = (Vertex) adjIt.currentItem();
        Edge e = edgeFromTo(v, adjV);
        if (getVL(v) > getVL(adjV) - e.getWeight()) // 更新最迟开始时间
          setVL(v, getVL(adjV) - e.getWeight());
      }
    }
    Iterator edIt = edges.elements();
    for (edIt.first(); !edIt.isDone(); edIt.next()) { // 求关键活动
      Edge e = (Edge) edIt.currentItem();
      Vertex u = e.getFirstVex();
      Vertex v = e.getSecondVex();
      if (getVE(u) == getVL(v) - e.getWeight()) e.setToCritical();
    }
  }

  public DirectedGraph reverse() {
    DirectedGraph directedGraph = new DirectedGraph(type);
    Iterator<Vertex> elements = vertexs.elements();//全部节点
    while (!elements.isDone()) {
      Vertex u = elements.currentItem();
      Vertex u1 = directedGraph.findVertex(u.getInfo());
      if (u1 == null) u1 = new Vertex(directedGraph, u.getInfo());

      Iterator<Edge> adjacentEdges = u.getAdjacentEdges().elements();//邻接点
      while (!adjacentEdges.isDone()) {
        Edge e = adjacentEdges.currentItem();
        Vertex v = e.getSecondVex();
        Vertex v1 = directedGraph.findVertex(v.getInfo());
        if (v1 == null) v1 = new Vertex(directedGraph, v.getInfo());
        Edge v1u1 = directedGraph.edgeFromTo(v1, u1);

        if (v1u1 == null) new Edge(directedGraph, v1, u1, null);
        adjacentEdges.next();
      }

      Iterator<Edge> reAdjacentEdges = u.getReAdjacentEdges().elements();//邻接点
      while (!reAdjacentEdges.isDone()) {
        Edge e = reAdjacentEdges.currentItem();
        Vertex w = e.getFirstVex();
        Vertex w1 = directedGraph.findVertex(w.getInfo());
        if (w1 == null) w1 = new Vertex(directedGraph, w.getInfo());
        Edge u1w1 = directedGraph.edgeFromTo(u1, w1);
        if (u1w1 == null) new Edge(directedGraph, u1, w1, null);
        reAdjacentEdges.next();
      }
      elements.next();

    }
    return directedGraph;
  }

  //求强联通分量

  public void ssc(Vertex first) {
    resetVexStatus();
    Stack<Vertex> s = new StackSLinked<>();//构建反向搜索时候的顺序
    dfsNoRecursion33(first, s);

//    for (Iterator<Vertex> it = getVertex(); !it.isDone(); it.next()) {
//      Vertex v = it.currentItem();
//      if (!v.isVisited()) {
//        dfsNoRecursion2(v, s);
//      }
//    }

      DirectedGraph reverse = reverse();//生成镜像图
    while (!s.isEmpty()) {
      Vertex pop = s.pop();
      pop=reverse.findVertex(pop.getInfo());
      if(pop.isVisited())continue;
      LinkedList<Vertex> list1 = new LinkedListDLNode<>();

      reverse.dfsNoRecursion(pop, list1);
      for (Iterator<Vertex> iterator = list1.elements(); !iterator.isDone(); iterator.next()) {
        Vertex v = iterator.currentItem();
        System.out.print(v.getInfo() + "\t");
      }
      System.out.println();
    }

  }


  protected void dfsNoRecursion33(Vertex v,Stack<Vertex> stack){
    resetVexStatus();
    dfsNoRecursion3(v,stack);
    for (Iterator<Vertex> it = vertexs.elements(); !it.isDone(); it.next()) {// 处理邻接点
      Vertex vertex = it.currentItem();
      if (!vertex.isVisited()) {
        dfsNoRecursion3(vertex,stack);
      }
    }

  }

  protected  void dfsNoRecursion3(Vertex v,Stack<Vertex> stack){
    stack.push(v);
    v.setToVisited();
    for (Iterator<Vertex> it = adjVertexs(v); !it.isDone(); it.next()) {// 处理邻接点
      Vertex adj = it.currentItem();
      if (!adj.isVisited()) {
        adj.setToVisited();
        dfsNoRecursion3(adj,stack);
        stack.push(adj);
      }
    }


  }

  protected void dfsNoRecursion2(Vertex v, Stack<Vertex> stack) {
    Stack<Vertex> s = new StackSLinked<>();
    s.push(v);
    while (!s.isEmpty()) {
      Vertex u = s.pop();
      if (!u.isVisited()) {
        stack.push(u);
        u.setToVisited();
        for (Iterator<Vertex> it = adjVertexs(u); !it.isDone(); it.next()) {// 处理邻接点
          Vertex adj = it.currentItem();
          if (!adj.isVisited()) {
            s.push(adj);
          }
        }
      }
    }
    stack.push(v);
  }


  /**
   * 求关键路径算法中，对v.application的操作
   */
  // 取顶点v的最早开始时间与最迟开始时间
  private int getVE(Vertex v) {
    return ((Vtime) v.getAppObj()).getVE();
  }

  private int getVL(Vertex v) {
    return ((Vtime) v.getAppObj()).getVL();
  }

  // 设置顶点v的最早开始时间与最迟开始时间
  private void setVE(Vertex v, int ve) {
    ((Vtime) v.getAppObj()).setVE(ve);
  }

  private void setVL(Vertex v, int vl) {
    ((Vtime) v.getAppObj()).setVL(vl);
  }
}
