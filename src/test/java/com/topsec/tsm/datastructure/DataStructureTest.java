package com.topsec.tsm.datastructure;

import com.topsec.tsm.datastructure.iterator.Iterator;
import com.topsec.tsm.datastructure.list.*;
import com.topsec.tsm.datastructure.queue.Queue;
import com.topsec.tsm.datastructure.queue.QueueArray;
import com.topsec.tsm.datastructure.queue.QueueSLinked;

//import com.topsec.tsm.datastructure.list.ListArray;

/**
 * 测试各种数据结构
 * 
 * */
public class DataStructureTest {

	public static void listTest(List<String> list) {
		System.out
				.println("<<=====================线性表测试=====================>>");
		System.out.println("是否为空：" + list.isEmpty());
		System.out.println("-------插入数据----------");
		list.insert(0, "黄星");
		list.insert(1, "王五");
		list.insertBefore("黄星", "大芳");
		list.insertAfter("王五", "小红");
		System.out.println("线性表尺寸：" + list.getSize());
		System.out.println("-------输出数据----------");

		for (int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + "\t");
		}

		System.out.println("--------删除数据---------");
		System.out.println("删除：" + list.remove(0).toString());
		System.out.println("删除: " + list.remove("黄星"));
		System.out.println("--------替换元素---------");
		list.replace(1, "小芳");
		for (int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + "\t");
		}
		System.out.println("\n-----------------");
		System.out.println("是否包含字符串 黄星: " + list.contains("黄星"));
		System.out.println("-----------------");
		System.out.println("是否包含字符串 小芳: " + list.contains("小芳"));
		System.out.println("-----------------");
		System.out.println("字符串 小芳 所在位置: " + list.indexOf("小芳"));
		System.out.println("-------尾端插入数据----------");
		list.insertLast("星星哥");
		System.out.println("-------输出数据----------");
		for (int i = 0; i < list.getSize(); i++) {
			System.out.print(list.get(i) + "\t");
		}
		System.out.println("\n\n");

		System.out.println("-------迭代输出数据----------");
		Iterator<String> ls = list.elements();
		while (!ls.isDone()) {
			System.out.print(ls.currentItem().toString() + "\t");
			ls.next();
		}
		list.clear();
		System.out.println("\n"+list.getSize());
//		System.exit(0);
	}

	// 链接表测试方法
	public static void linkedListTest(LinkedList<String> list) {
		System.out
				.println("\n\n<<========================基于双向链表的链接表测试========================>>");

		System.out.println("是否为空：" + list.isEmpty());
		System.out.println("-----------------");
		System.out.println("线性表尺寸：" + list.getSize());
		System.out.println("-----------------");
		// 插入节点
		Node<String> node1 = list.insertFirst("司令");
		Node<String> node3 = list.insertAfter(node1, "军长");
		Node<String> node2 = list.insertBefore(node3, "副司令");
		Node<String> node4 = list.insertLast("师长");
		System.out.println("是否为空：" + list.isEmpty());
		System.out.println("-----------------");
		System.out.println("线性表尺寸：" + list.getSize());
		// 返回第一个节点
		Node<String> firstNode = list.first();
		System.out.println("-----------------");
		System.out.println("第一个节点: " + firstNode.getData());
		System.out.println("-----------------");
		// 返回最后一个节点
		Node<String> lastNode = list.last();
		System.out.println("最后一个节点: " + lastNode.getData());
		// 返回司令的下一个节点的元素值
		System.out.println("-----------------");
		Node<String> NodeNext = list.getNext(node1);
		System.out.println("司令的下一个节点:" + NodeNext.getData());
		// 删除副司令
		System.out.println("-----------------");
		Object obj1 = list.remove(node2);
		System.out.println("删除的节点:" + obj1.toString());
		// 替换师长节点
		System.out.println("-----------------");
		Object obj2 = list.replace(node4, "旅长");
		System.out.println("替换前的节点:" + obj2.toString());
		System.out.println("替换后的节点:" + node4.getData());
		// 查找节点
		System.out.println("-----------------");
		System.out.println("查找  军长 节点的后继节点: "
				+ list.getNext(list.find("军长")).getData());
		// 遍历输出所有节点
		System.out.println("-----------------");
		System.out.print("遍历输出所有节点：");

//		Iterator ldi = new LinkedListIterator(list);
		// while (!ldi.isDone()) {
		// System.out.print(ldi.currentItem() + "\t");
		// ldi.next();
		// }
		Iterator<String> ldi1 = list.elements();
		// 装饰者，在类ldi1中操作list对象
		while (!ldi1.isDone()) {
			System.out.print(ldi1.currentItem() + "\t");
			ldi1.next();
		}
		System.out.println("\n");
		list.clear();
		System.out.println(list.getSize());
	}
	
	//队列测试
	
	private static void queueTest(Queue<Integer> q) {
		System.out.println("<<=====================队列测试=====================>>");
		for (int i = 1; i <= 20; i++) {
			q.enqueue(i);
			
		}
		System.out.println("队列尺寸: "+q.getSize());
		System.out.println("队首元素：" + q.peek());
		//出队
		for (int i = 1; i <= 10; i++) {
			q.dequeue();
			
		}
		System.out.println("队列尺寸: "+q.getSize());
		System.out.println("队首元素：" + q.peek());
		for (int i = 1; i <= 40; i++) {
			q.enqueue(i);
			
		}
		System.out.println("队列尺寸: "+q.getSize());
		System.out.println("队首元素：" + q.peek());
		for (int i = 1; i <= 10; i++) {
			q.dequeue();
			
		}
		System.out.println("队列尺寸: "+q.getSize());
		System.out.println("队首元素：" + q.peek());
	}
	


	
	public static void main(String[] args) {
		/**
		 * 顺序表结构 优点： 可以根据1个元素的地址进行随机查找,不用像链表一样从头结点遍历.
		 * 
		 * 缺点：1.对于插入，删除操作，需要动态扩充数组，移动大量元素，代价高 2.动态扩充数组可能会造成空间浪费
		 * 
		 * 适用范围：数据结构简单，而且变化不大时候可以采用（需要经常查找，不删除，只从最后面插入的时候）
		 * */
		
		//顺序表
//		ListArray<String> la = new ListArray<String>(10);
//		 listTest(la);

		/**
		 * 单链表优点： 1. 插入、删除操作时候只需要调整对象引用即可，不用移动大量元素。2.由于不需要动态扩充，所以空间利用率较高
		 * 
		 * 缺点： 1.由于每个单元中添加了存储元素关系的指针，增加了存储空间 2.查找时每个元素都需要从头节点开始查找，全扫描时
		 * 后一个元素的查找不能从前一个元素的查找中受益 3.无法通过引用查找节点的前驱节点
		 * 
		 * 在单链表中进行顺序查找与在数组中完成相同 操作具有相同的时间复杂度，而在单链表中在已知特定结点引用的前提下完成数据元素的插
		 * 入与删除操作要比在数组中完成相同操作快得多 适用范围：线性表结构复杂，而且经常变化时可以考虑使用
		 * */


		/**
		 * 基于序号的操作实际上并不适合采用（单向或双向）链表来实现，因为为了在链表中定位数
		 * 据元素或序号，我们不得不沿着结点间的next（或pre）引用，从链表前端（双向链表也可 以从后端）开始逐一扫描。
		 **/

		//单链表
		ListSLinked<String> ls = new ListSLinked<String>();
		 listTest(ls);
//		 System.exit(0);

		/**
		 * 除了通过序号来访问线性结构中的元素，还可通过其他途径来得到线性结构中 的元素。例如我们能够直接通过结点来访问数据元素，通过结点接口，我们
		 * 看到结点实际上可以看成是可以存取数据元素的容器，数据元素与存放它的容器是一一对应
		 * 的。如果能够取得结点的引用，则可以取得相应结点存储的数据元素，并且在实际应用中的 许多情况下更希望以结点作为参数来完成某些操作。
		 * 如果能够以结点作为参数，那么就可以在Ο(1)时间内定位结点的地址，进而可以在更
		 * 短的时间内完成相应的操作。例如如果能够直接定位在链表中进行插入和删除结点的前驱， 那么相应的插入和删除操作都可以在Ο(1)完成。
		 * **/

		/*
		 * 
		 * 在链接表中提供基于结点的操作时，有一个问题需要考虑：需要将多少链接表的实现细
		 * 节暴露给使用它的程序员？如果将单链表或双向链表的细节，例如结点结构、首结点引用或
		 * 尾结点引用都提供给程序员。这样做可以使得程序员可以直接访问数据并修改内部链表结构
		 * （例如通过next和pre引用），但是基于安全性和面向对象的封装原则，我们并不这样做。
		 * 那么如何在向用户提供相关链表结点引用的基础上，却可以保证用户不会通过该引用对链表
		 * 的内部结构直接进行访问或修改呢？这实际上可以通过Node接口来实现，因
		 * 为任何链表结点（单链表结点、双向链表结点都实现了Node接口）都可被Node类型的变
		 * 量引用，而Node接口中只有存取数据元素的方法，因此程序员即可以存取数据，又不能对 内部链表结构进行修改。
		 */

		 

			/**
			 * 双向链表优点：通过一个结点的引用，不但能够访问其后续结点，也可以方便的访问其前驱结点
			 * 
			 * 缺点： 1.由于每个单元中添加了存储元素关系的指针，增加了存储空间 2.查找时每个元素都需要从头节点开始查找，而且相互之间没有受益
			 * 缺点：使用的存储空间更多
			 * */
		 
		/*
		 * 基于双向链表的表实现 优点：基于节点的操作，查找，插入，删除操作时候都不需要从头节点遍历线性表 缺点：使用的元素太多
		 */
		LinkedList<String> ldl = new LinkedListDLNode<String>();
		 linkedListTest(ldl);
		 
		 //队列顺序测试
		 Queue<Integer> qa = new QueueArray<Integer>();
		 queueTest(qa);
		 
		 //队列链式测试
		 
		 Queue<Integer> qs = new QueueSLinked<Integer>();
		 queueTest(qs);

		/**
		 * 二叉树的三叉链表表示法：参见类BinTreeNode中的内容
		 * 
		 */

		/**
		 * 图结构测试
		 * **/

		/**
		 * 虽然邻接表是图的一种很有效的存储结构，在邻接表中容易求得顶点和边的各种信息。
		 * 但是这种结构会给图的某些操作带来不便。例如，在无向图中，每条边在邻接表中对应了两
		 * 个边表结点，如果在图的应用中需要对边进行标记，或删除边等，此时需要找到表示同一条
		 * 边的两个边表结点，然后执行相同的操作，以保证数据的一致性，因此操作的实现比较麻烦。
		 * 另一方面，如果在邻接表中，将所有的顶点按照顺序的方式存储，会使得顶点的删除操
		 * 作所需的时间代价较大。首先在数组中删除一个元素，平均需要移动大约数组中一半的元素；
		 * 其次，在删除一个顶点时，需要将与之相关联的所有边删除，如上所述，在无向图中删除一
		 * 条边需要删除两个边表结点，较为复杂；再次，由于在删除某个顶点以后，会造成后续顶点
		 * 在顶点数组中的位置发生变化，因此要判断所有边表结点的邻接点域是否需要修改，如果其
		 * 邻接点域所指顶点位置发生变化，则需要使用新的指向替换原来的指向。以上操作总共需要
		 * Ο(|V|+|E|)的时间。解决这个问题的一种办法是，在删除顶点时，并不将数组中其后续顶点
		 * 前移，只是将相应位置设置为空，然后删除与之关联的所有边。但是这种方法会使得在图中
		 * 添加顶点之前需要先遍历顶点数组，查找数组中为空的位置，如果有则将新的顶点放入该位
		 * 置，如果没有则放到数组的尾部。这样添加一个新顶点的操作实现会比较复杂。
		 */


	}

}
