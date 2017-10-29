package com.topsec.tsm.datastructure.searcher;

import com.topsec.tsm.datastructure.list.Node;
import com.topsec.tsm.datastructure.tree.BinTreeNode;

/**
 * Created by hx on 15-8-26.
 * 查找树的定义
 */

public class BinSearchTreeNode<E> extends BinTreeNode<E> {
  protected BinTreeNode<E> startBN;//待平衡出发点

  public Node<E> search(E e) {
    return binTSearchRe(root, e);
//    return binTSearch(this, e);

  }

  //查找算法的递归实现
  public Node<E> binTSearchRe(BinTreeNode<E> rt, E e) {
    if (rt == null) return null;
    switch (strategy.compare(e, rt.getData())) {
      case 0:
        return rt;//等于
      case -1:
        return binTSearchRe(rt.getLChild(), e);//小于
      default:
        return binTSearchRe(rt.getRChild(), e);//大于
    }
  }


  //查找算法的非递归实现
  //时间复杂度0(h)
  private Node<E> binTSearch(BinTreeNode<E> rt, E e) {
    //从根开始查找
    while (rt != null) {
      switch (strategy.compare(rt.getData(), e)) {
        case 0:
          return rt;
        case 1:
          rt = rt.getLChild();
          break;
        default:
          rt = rt.getRChild();
      }
    }
    return rt;
  }

  //最大值和最小值
  public Node<E> max(BinTreeNode<E> rt) {
    if (rt != null) {
      while (rt.hasRChild()) {
        rt = rt.getRChild();
      }
    }
    return rt;
  }

  public Node<E> min(BinTreeNode<E> rt) {
    if (rt != null) {
      while (rt.hasLChild()) {
        rt = rt.getLChild();
      }
    }
    return rt;
  }

  //后继节点
  /*
  后继查找办法
  如果有右孩子,那么直接就是右子树中的最小者
  如果没有右孩子,那么从自身向上查找到地一个是左孩子的节点,它的父节点就是后继
   */
  public BinTreeNode getSuccessor(BinTreeNode v) {
    if (v == null) return null;
    if (v.hasRChild()) {
      return (BinTreeNode) min(v.getRChild());
    }
    while (v.isRChild()) v = v.getParent();
    return v.getParent();
  }

  public BinTreeNode getSuccessor2(BinTreeNode v) {
    if (v == null) return null;
    //有右孩子
    if (v.hasRChild()) {
      return (BinTreeNode) min(v.getRChild());
    }
    while (v.hasParent()) {
      if (v.isLChild()) {
        return v.getParent();
      } else {
        //不是左孩子
        v = v.getParent();
      }
    }
    return null;
  }

  //前驱节点:与后继查找办法正好对称
  public BinTreeNode getPredecessor(BinTreeNode v) {
    if (v == null) return null;
    if (v.hasLChild()) {
      return (BinTreeNode) max(v.getLChild());
    }
    while (v.isLChild()) v = v.getParent();
    return v.getParent();
  }


  //从根节点开始插入元素:总是从叶子节点插入的

  public void insert(E ele) {
    BinTreeNode<E> current = setAndGetRoot();
    BinTreeNode<E> p = null;
    BinTreeNode<E> newNode = new BinTreeNode<>();
    newNode.setData(ele);
    if (current == null) {
      root = newNode;
      return;
    }
    while (current != null) {
      p = current;
      if (strategy.compare(current.getData(), ele) > 0) {
        current = current.getLChild();
      } else {
        current = current.getRChild();
      }
    }
    startBN = p;
    if (strategy.compare(p.getData(), ele) > 0) {
      p.setLChild(newNode);

    } else {
      p.setRChild(newNode);
    }
  }


  public E remove(E e) {
    BinTreeNode<E> v = (BinTreeNode<E>) search(e);
    if (v == null) {
      return null;
    }
    //确定需要删除的元素
    BinTreeNode<E> del;//待删除元素
    //不同时具备左右孩子
    if (!v.hasLChild() || !v.hasRChild()) {
      del = v;
    } else {//同时具备左右孩子,前驱不能同时具备两个孩子,删除方便
      E temp = v.getData();
      //获取前驱节点
      del = getPredecessor(v);
      v.setData(del.getData());//把值设置成前驱节点
      //删除前驱节点,这时候前驱不会同时有最有孩子
      del.setData(temp);
    }
    startBN = del.getParent();
    BinTreeNode<E> subT;
    if (del.hasLChild()) {
      subT = del.getLChild();//有左子树
    } else if (del.hasRChild()) {//有右子树
      subT = del.getRChild();
    } else {//左右子树都没有
      del.sever();//直接删除
      return del.getData();
    }

    if (subT != null) {//子树不是空的
      if (del.isLChild()) {//是左子树
        del.getParent().setLChild(subT);
      } else if (del.isRChild()) {//是右子树
        del.getParent().setRChild(subT);
      } else {//待删除节点没有父节点,而且子树不是空的,那么就是根节点(根节点只有一个子树),直接断开与父节点关系
        subT.sever();
        root = subT;
      }
    }
    return del.getData();
  }

  public BinTreeNode<E> higherSubT(BinTreeNode<E> v) {////返回节点v较高的子树
    if (v == null) return null;
    int lH = (v.hasLChild()) ? v.getLChild().getHeight() : -1;
    int rH = (v.hasRChild()) ? v.getRChild().getHeight() : -1;
    if (lH > rH) return v.getLChild();
    if (lH < rH) return v.getRChild();
    if (v.hasLChild()) return v.getLChild();
    else return v.getRChild();
  }


}
