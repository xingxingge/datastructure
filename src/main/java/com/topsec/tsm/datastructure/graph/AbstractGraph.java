package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.exception.UnsupportedOperation;
import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.LinkedListDLNode;
import com.topsec.tsm.datastructure.list.Node;
import com.topsec.tsm.datastructure.queue.Queue;
import com.topsec.tsm.datastructure.queue.QueueSLinked;
import com.topsec.tsm.datastructure.stack.Stack;
import com.topsec.tsm.datastructure.stack.StackSLinked;


public abstract class AbstractGraph implements Graph {
  protected LinkedList<Vertex> vertexs;// 顶点链表
  protected LinkedList<Edge> edges;// 边链表
  protected int type;// 图类型

  public AbstractGraph(int type) {
    this.type = type;
    vertexs = new LinkedListDLNode();
    edges = new LinkedListDLNode();
  }

  public int getType() {
    return type;
  }

  public int getVexNum() {
    return vertexs.getSize();
  }

  public int getEdgeNum() {
    return edges.getSize();
  }

  public Iterator getVertex() {
    return vertexs.elements();
  }

  public Iterator getEdge() {
    return edges.elements();
  }

  // 辅助方法：在图的顶点集中添加一个新顶点，返回给顶点
  public Node insert(Vertex v) {
    return vertexs.insertLast(v);
  }

  // 判断是否有元素值是e的顶点
  public boolean containVertex(Object e) {
    Iterator ir = getVertex();
    while (!ir.isDone()) {
      Vertex v = (Vertex) ir.currentItem();
      if (v.getInfo().equals(e.toString())) {
        return true;
      } else {
        ir.next();
      }

    }
    return false;

  }

  // 查找元素值与e相等的顶点
  @Override
  public Vertex findVertex(Object e) {
    Iterator ir = getVertex();
    while (!ir.isDone()) {
      Vertex v = (Vertex) ir.currentItem();
      if (v.getInfo().equals(e.toString())) {
        return v;
      } else {
        ir.next();
      }
    }
    return null;
  }

  // 辅助方法： 在图的边集中添加一条新 边
  public Node insert(Edge e) {
    return edges.insertLast(e);
  }

  // 判断顶点v是否为顶点u的邻接顶点。
  public boolean areAdjacent(Vertex u, Vertex v) {
    return edgeFromTo(u, v) != null; // 这个方法交给子类来实现
  }

  // 两种图都有的：从顶点v开始深度优先搜索遍历图。
  public Iterator dfsTraverse(Vertex v) {
    LinkedList traverseSeq = new LinkedListDLNode();// 遍历结果
    resetVexStatus(); // 重置所有顶点的状态
    dfsRecursion(v, traverseSeq); // 从v点出发深度优先搜索
    Iterator it = getVertex(); // 从图未曾访问的其他顶点重新搜索（调用图操作③）(先从v顶点开始，然后从余下没有访问过的顶点开始)
    for (it.first(); !it.isDone(); it.next()) {
      Vertex u = (Vertex) it.currentItem();
      if (!u.isVisited()) // 只要u没有访问就从新开始
        dfsRecursion(u, traverseSeq);
    }
    return traverseSeq.elements();
  }

  // 从顶点v出发深度优先搜索的递归算法
  protected   void dfsRecursion(Vertex v, LinkedList list) {
    v.setToVisited(); // 设置顶点v为已访问
    list.insertLast(v); // 访问顶点v
    Iterator it = adjVertexs(v); // 取得顶点v的所有邻接点（调用图操作⑧）
    for (it.first(); !it.isDone(); it.next()) {// 从邻接表开始搜索，如果节点没有被访问，就递归调用方法，进行进一步的遍历
      Vertex u = (Vertex) it.currentItem();
      if (!u.isVisited())
        dfsRecursion(u, list);
    }
  }

  // 两种图都有的：从顶点v出发深度优先搜索的非递归算法
  @Override
  public Iterator dfsNoRecursionTraverse(Vertex v) {
    LinkedList<Vertex> traverseSeq = new LinkedListDLNode<>();// 遍历结果
    resetVexStatus(); // 重置所有顶点的状态
    dfsNoRecursion(v, traverseSeq); // 从v点出发深度优先搜索
    for ( Iterator<Vertex> it = getVertex(); !it.isDone(); it.next()) {// 从图未曾访问的其他顶点重新搜索（调用图操作③）(先从v顶点开始，然后从余下没有访问过的顶点开始)
      Vertex u = it.currentItem();
      if (!u.isVisited()) // 只要u没有访问就从新开始
        dfsNoRecursion(u, traverseSeq);
    }
    return traverseSeq.elements();
  }

  // 从顶点v出发深度优先搜索的非递归算法
  protected void dfsNoRecursion(Vertex v, LinkedList<Vertex> list) {
    Stack<Vertex> s = new StackSLinked<>();
    s.push(v);
    while (!s.isEmpty()) {
      Vertex u = s.pop();
      if (!u.isVisited()) {
        u.setToVisited();
        list.insertLast(u);
        for (Iterator<Vertex> it = adjVertexs(u); !it.isDone(); it.next()) {// 处理邻接点
          Vertex adj = it.currentItem();
          if (!adj.isVisited()) s.push(adj);
        }
      }
    }
  }

  // 从顶点v开始广度优先搜索遍历图。
  public Iterator bfsTraverse(Vertex v) {
    LinkedList<Vertex> traverseSeq = new LinkedListDLNode<>();// 遍历结果
    resetVexStatus();// 重置顶点状态
    bfs(v, traverseSeq);// 从v点出发广度优先搜索
    for (Iterator<Vertex> it = getVertex(); !it.isDone(); it.next()) {// 从图中未访问的顶点重新搜索（调用图操作③）
      Vertex u =  it.currentItem();
      if (!u.isVisited())
        bfs(u, traverseSeq);
    }
    return traverseSeq.elements();
  }

  protected void bfs(Vertex v, LinkedList<Vertex> list) {
    Queue<Vertex> q = new QueueSLinked<>();
    v.setToVisited(); // 访问顶点v
    list.insertLast(v);
    q.enqueue(v); // 顶点v入队
    while (!q.isEmpty()) {
      Vertex u = q.dequeue(); // 队首元素出队
      for ( Iterator<Vertex> it = adjVertexs(u); !it.isDone(); it.next()) {// 访问其未曾访问的邻接点，并入队
        Vertex adj = it.currentItem();
        if (!adj.isVisited()) {
          adj.setToVisited();
          list.insertLast(adj);
          q.enqueue(adj);
        }
      }
    }
  }

  /**
   * Dijkstra算法的具体实现
   */
  // 两种图都有的： 求顶点v到图中所有顶点的最短路径。
  public Iterator shortestPath(Vertex v) {
    LinkedList sPath = new LinkedListDLNode(); // 所有的最短路径序列
    resetVexStatus(); // 重置图中各顶点的状态信息
    // 初始化，将v到各顶点的最短距离初始化为由v直接可达的距离
    Iterator it = getVertex(); // （调用图操作③）
    for (it.first(); !it.isDone(); it.next()) {
      Vertex u = (Vertex) it.currentItem();
      int weight = Integer.MAX_VALUE;
      Edge e = edgeFromTo(v, u); // （调用图操作⑦）
      if (e != null)
        weight = e.getWeight();
      if (u == v)
        weight = 0;
      Path p = new Path(weight, v, u);
      setPath(u, p);
    }
    v.setToVisited(); // 顶点v进入集合S
    sPath.insertLast(getPath(v)); // 求得的最短路径进入链接表
    for (int t = 1; t < getVexNum(); t++) { // 进行|V|-1 次循环找到|V|-1 条最短路径
      Vertex k = selectMin(it); // 找V- S中distance最小的点k
      k.setToVisited(); // 顶点k加入S
      sPath.insertLast(getPath(k)); // 求得的最短路径进入链接表
      int distK = getDistance(k); // 修正V- S中顶点当前最短路径
      Iterator adjIt = adjVertexs(k); // 取出k的所有邻接点
      for (adjIt.first(); !adjIt.isDone(); adjIt.next()) {
        Vertex adjV = (Vertex) adjIt.currentItem(); // k的邻接点adjV
        Edge e = edgeFromTo(k, adjV); // （调用图操作⑦）
        // 发现更短的路径
        if ((long) distK + (long) e.getWeight() < (long) getDistance(adjV)) {
          setDistance(adjV, distK + e.getWeight());
          amendPathInfo(k, adjV); // 以k的路径信息修改adjV的路径信息
        }
      }// for
    }// for(int t=1...
    return sPath.elements();
  }

  // 在顶点集合中选择路径距离最小的
  protected Vertex selectMin(Iterator it) {
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
      if (!v.isVisited() && getDistance(v) < getDistance(min))
        min = v;
    }
    return min;
  }

  // 以mid的路径信息修改end的路径信息
  protected void amendPathInfo(Vertex mid, Vertex end) {
    Iterator it = getPath(mid).getPathInfo();
    getPath(end).clearPathInfo();
    for (it.first(); !it.isDone(); it.next()) {
      getPath(end).addPathInfo(it.currentItem());
    }
    getPath(end).addPathInfo(mid.getInfo());
  }

  // 删除顶点v
  public abstract void remove(Vertex v);

  // 删除边e
  public abstract void remove(Edge e);

  // 返回u和v之间的边
  public abstract Edge edgeFromTo(Vertex u, Vertex v);

  // 返回顶点的所有邻接顶点
  public Iterator adjVertexs(Vertex u) {
    LinkedList adjVertexs = new LinkedListDLNode();
    if (this.vertexs.contains(u)) {
      Iterator ade = u.getAdjacentEdges().elements();
      while (!ade.isDone()) {
        Edge temp = (Edge) ade.currentItem();
        // 获取邻接边表的第二顶点
        adjVertexs.insertLast(temp.getSecondVex());
        ade.next();
      }
//			if (!adjVertexs.isEmpty()) {
//				return adjVertexs.elements();
//			}
    }
    return adjVertexs.elements();
  }

  // generateMST由无向图单独实现
  public abstract void generateMST() throws UnsupportedOperation;

  // toplogicalSort和criticalPath由有向图单独实现
  public abstract Iterator toplogicalSort() throws UnsupportedOperation;

  // 关键路径
  public abstract void criticalPath() throws UnsupportedOperation;

  // 重置顶点状态，访问状态设置成false，应用设置成null

  protected void resetVexStatus() {
    Iterator it = getVertex();
    for (it.first(); !it.isDone(); it.next()) {
      Vertex u = (Vertex) it.currentItem();
      u.resetStatus();
    }
  }

  // 重置图中各边的类型信息
  protected void resetEdgeType() {
    Iterator it = getEdge();
    for (it.first(); !it.isDone(); it.next()) {
      Edge e = (Edge) it.currentItem();
      e.resetType();
    }
  }

  // Dijkstra算法中，对v.application的操作
  protected int getDistance(Vertex v) {
    return ((Path) v.getAppObj()).getDistance();
  }

  protected void setDistance(Vertex v, int dis) {
    ((Path) v.getAppObj()).setDistance(dis);
  }

  protected Path getPath(Vertex v) {
    return (Path) v.getAppObj();
  }

  protected void setPath(Vertex v, Path p) {
    v.setAppObj(p);
  }
}
