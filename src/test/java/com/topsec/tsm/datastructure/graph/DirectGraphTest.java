package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.iterator.Iterator;
import junit.framework.TestCase;

/**
 * Created by hx on 16-12-10.
 */
public class DirectGraphTest extends TestCase{
  public void test1(){
    DirectedGraph g = new DirectedGraph(1);
 // 建立两个顶点,把这另个顶点加入定点表
    Vertex a = new Vertex(g, "a");
    Vertex b = new Vertex(g, "b");
    Vertex c = new Vertex(g, "c");
    Vertex d = new Vertex(g, "d");
    Vertex e = new Vertex(g, "e");

    Vertex f = new Vertex(g, "f");
    Vertex h = new Vertex(g, "h");
    Vertex i = new Vertex(g, "i");
    Vertex j = new Vertex(g, "j");
    new Edge(g, a, b, a.getInfo() + "--->" + b.getInfo());
    new Edge(g, b, e, b.getInfo() + "--->" + e.getInfo());
    new Edge(g, d, a, d.getInfo() + "--->" + a.getInfo());
    new Edge(g, e, a, e.getInfo() + "--->" + a.getInfo());
    new Edge(g, e, c, e.getInfo() + "--->" + c.getInfo());
    new Edge(g, f, e, f.getInfo() + "--->" + e.getInfo());
    new Edge(g, f, c, f.getInfo() + "--->" + c.getInfo());



    new Edge(g, a, h, a.getInfo() + "--->" + h.getInfo());
    new Edge(g, h, d, h.getInfo() + "--->" + d.getInfo());
    new Edge(g, f, h, f.getInfo() + "--->" + h.getInfo());

    new Edge(g, c, i, c.getInfo() + "--->" + i.getInfo());
    new Edge(g, i, j, i.getInfo() + "--->" + j.getInfo());
    new Edge(g, j, c, j.getInfo() + "--->" + c.getInfo());



    System.out.println("是否包含司令：" + g.containVertex("司令"));

    // 遍历输出顶点
    System.out.println("\n输出顶点： ");

    for (Iterator<Vertex> itVertex = g.getVertex();!itVertex.isDone();itVertex.next()) {
      Vertex v =  itVertex.currentItem();
      System.out.print(v.getInfo()+"\t");
    }
    // 遍历输出边
    System.out.println("\n输出边： ");

    for (Iterator<Edge> itEdgeFor = g.getEdge();!itEdgeFor.isDone(); itEdgeFor.next()) {
      Edge temp = itEdgeFor.currentItem();
      System.out.println(temp.getInfo());
    }

    // 深度优先遍历
    System.out.println("\n深度优先遍历： ");

    for (Iterator<Vertex> graphDFSTraverse = g.dfsTraverse(a);!graphDFSTraverse.isDone();graphDFSTraverse.next()) {
      Vertex temp =  graphDFSTraverse.currentItem();
      System.out.print(temp.getInfo()+"\t");
    }

    // 深度优先遍历非递归
    System.out.println("\n深度优先遍历非递归： ");

    for (Iterator<Vertex> vertexIterator = g.dfsNoRecursionTraverse(a);!vertexIterator.isDone();vertexIterator.next()) {
      Vertex temp = vertexIterator.currentItem();
      System.out.print(temp.getInfo()+"\t");
    }

    // 广度优先遍历，相当于层次模型遍历
    System.out.println("\n广度优先遍历： ");

    for (Iterator<Vertex> bfs = g.bfsTraverse(a);!bfs.isDone();bfs.next()) {
      Vertex temp = bfs.currentItem();
      System.out.print(temp.getInfo()+"\t");
    }
    // 判断u和v是否右边
    System.out.println("\nu和n是否邻接：" + g.areAdjacent(a, b));
    System.out.println("\n生成强联通子图(环):");
    g.ssc(i);
  }

  public void testScc(){
    DirectedGraph g = prepare();
    Vertex a = g.findVertex("f");
    g.ssc(a);

  }

  private DirectedGraph prepare(){
    DirectedGraph g = new DirectedGraph(1);
    Vertex a = new Vertex(g, "a");
    Vertex b = new Vertex(g, "b");
    Vertex c = new Vertex(g, "c");
    Vertex d = new Vertex(g, "d");
    Vertex e = new Vertex(g, "e");

    Vertex f = new Vertex(g, "f");

    new Edge(g, a, b, a.getInfo() + "--->" + b.getInfo());
    new Edge(g, a, c, b.getInfo() + "--->" + e.getInfo());
    new Edge(g, b, d, d.getInfo() + "--->" + a.getInfo());
    new Edge(g, c, d, e.getInfo() + "--->" + a.getInfo());
    new Edge(g, c, e, e.getInfo() + "--->" + c.getInfo());
    new Edge(g, d, a, f.getInfo() + "--->" + e.getInfo());
    new Edge(g, d, f, f.getInfo() + "--->" + c.getInfo());
    new Edge(g, e, f, f.getInfo() + "--->" + c.getInfo());
    return g;

  }



}
