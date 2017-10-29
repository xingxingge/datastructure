package com.topsec.tsm.datastructure.searcher;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.Node;


public interface SearchTable<E> {
    // 查询查找表当前的规模
    int getSize();

    // 判断查找表是否为空
    boolean isEmpty();

    // 返回查找表中与元素 ele 关键字相同的元素位置;否则,返回 null
    Node<E> search(E ele);

    // 返回所有关键字与元素 ele 相同的元素位置
    Iterator<E> searchAll(E ele);

    // 按关键字插入元素 ele
    void insert(E ele);

    // 若查找表中存在与元素 ele 关键字相同元素,则删除一个并返回;否则,返回 null
    E remove(E ele);
}
