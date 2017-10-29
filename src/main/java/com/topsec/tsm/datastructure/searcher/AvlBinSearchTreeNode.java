package com.topsec.tsm.datastructure.searcher;

import com.topsec.tsm.datastructure.tree.BinTreeNode;

/**
 * Created by hx on 16-10-3.
 */
public class AvlBinSearchTreeNode<E> extends BinSearchTreeNode<E> {
  @Override
  public void insert(E ele) {
   super.insert(ele);
    root=reBalance(startBN);
  }
  @Override
  public E remove(E e) {
    E r=super.remove(e);
    root=reBalance(startBN);
    return r;
  }

  public BinTreeNode<E> rotate(BinTreeNode<E> z){
    BinTreeNode<E> y=higherSubT(z);
    BinTreeNode<E> x=higherSubT(y);
    boolean isLeft=z.isLChild();
    BinTreeNode<E> p = z.getParent();
    BinTreeNode<E> a,b,c;
    BinTreeNode<E> t0,t1,t2,t3;
    //分两种情况
    if (y.isLChild()){//y是左孩子
      c=z;t3=z.getRChild();
      if (x.isLChild()){//左左失衡
        b=y;t2=y.getRChild();
        a=x;t0=x.getLChild();t1=x.getRChild();
      }else{//左右失衡
        a=y;t0=y.getLChild();
        b=x;t1=x.getLChild();t2=x.getRChild();

      }
    }else{//y是右孩子
      a=z;t0=z.getLChild();
      if (x.isRChild()){//x是右孩子,右右失衡
        b=y;t1=y.getLChild();
        c=x;t2=x.getLChild();t3=x.getRChild();
      }else{//x是左孩子,右左失衡
        c=y;t3=y.getRChild();
        b=x;t1=x.getLChild();t2=b.getRChild();
      }
    }
    //去掉节点
    z.sever();
    y.sever();
    x.sever();
    //去掉子树
    if (t0!=null)t0.sever();
    if (t1!=null)t1.sever();
    if (t2!=null)t2.sever();
    if (t3!=null)t3.sever();
    //重新连接
    b.setLChild(a);b.setRChild(c);
    a.setLChild(t0);a.setRChild(t1);
    c.setLChild(t2);c.setRChild(t3);
    //子树接入原树
    if (p!=null){
      if (isLeft)p.setLChild(b);
      else p.setRChild(b);
    }
    return b;//新的子树的根
  }

  private BinTreeNode reBalance(BinTreeNode<E> v) {
    if (v==null)return null;
    BinTreeNode<E> c = v;
    while(v!=null){//从v开始,检查祖先
      if (!isBalance(v))v=rotate(v);//v失衡,需要旋转
      c=v;v=v.getParent();//检查父亲
    }
    return c;
  }

  private boolean isBalance(BinTreeNode<E> v){
    if (v==null)return true;
    int lH = (v.hasLChild()) ? v.getLChild().getHeight() : -1;
    int rH = (v.hasRChild()) ? v.getRChild().getHeight() : -1;
    return Math.abs(lH-rH)<=1;
  }

}
