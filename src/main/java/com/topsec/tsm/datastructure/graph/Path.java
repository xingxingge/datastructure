package com.topsec.tsm.datastructure.graph;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.LinkedList;
import com.topsec.tsm.datastructure.list.LinkedListDLNode;

public class Path {
	private int distance; // 锟斤拷锟斤拷锟斤拷盏锟侥撅拷锟斤拷
	private Vertex start; // 锟斤拷锟斤拷锟较�
	private Vertex end; // 锟秸碉拷锟斤拷息
	private LinkedList pathInfo; // 锟斤拷愕斤拷盏锟酵撅拷锟斤拷亩锟斤拷锟斤拷锟斤拷锟�

	// 锟斤拷锟届方锟斤拷

	public Path() {
		this(Integer.MAX_VALUE, null, null);
	}

	public Path(int distance, Vertex start, Vertex end) {
		this.distance = distance;
		this.start = start;
		this.end = end;
		pathInfo = new LinkedListDLNode();
	}

	// 锟叫讹拷锟斤拷锟斤拷锟斤拷盏锟街拷锟斤拷欠锟斤拷锟斤拷路锟斤拷
	public boolean hasPath() {
		return distance != Integer.MAX_VALUE && start != null && end != null;
	}

	// 锟斤拷路锟斤拷锟斤拷锟斤拷
	public int pathLength() {
		if (!hasPath())
			return -1;
		else if (start == end)
			return 0;
		else
			return pathInfo.getSize() + 1;
	}

	// get&set methods
	public void setDistance(int dis) {
		distance = dis;
	}

	public void setStart(Vertex v) {
		start = v;
	}

	public void setEnd(Vertex v) {
		end = v;
	}

	public int getDistance() {
		return distance;
	}

	public Vertex getStart() {
		return start;
	}

	public Vertex getEnd() {
		return end;
	}

	public Iterator getPathInfo() {
		return pathInfo.elements();
	}

	// 锟斤拷锟铰凤拷锟斤拷锟较�
	public void clearPathInfo() {
		pathInfo = new LinkedListDLNode();
	}

	// 锟斤拷锟铰凤拷锟斤拷锟较�
	public void addPathInfo(Object info) {
		pathInfo.insertLast(info);
	}
}
