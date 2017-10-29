package com.topsec.tsm.datastructure.graph;

public class GraphList {
  public GraphNode first;
  public GraphNode last;
  public boolean visitable;

  public int getAjd(Object[] ajd) {
    GraphNode current = first;
    int length = 0;
    while (current != null) {
      // ajd[length++] = (int) current.info;
      current = current.link;
    }
    return length;
  }

  public void addNode(Object object) {
    GraphNode node = new GraphNode();
    node.info = object;
    if (first == null) {
      first = node;
      last = node;
    } else {
      last.link = node;
      last = node;
    }
  }
}
