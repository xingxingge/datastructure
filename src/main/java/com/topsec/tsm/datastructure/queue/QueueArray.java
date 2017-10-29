package com.topsec.tsm.datastructure.queue;

import com.topsec.tsm.datastructure.exception.QueueEmptyException;

public class QueueArray<E> implements Queue<E> {
	private static final int CAP = 7;// 队列默认大小
	private E[] elements;// 队列元素数组
	private int capacity;// 数组尺寸
	private int front;// 队首部指针，指向队首
	private int rear;// 队尾指针，指向队尾最后一个位置

	public QueueArray() {
		this(CAP);
	}

	@SuppressWarnings("unchecked")
	public QueueArray(int cap) {
		capacity = cap + 1;// 因为要损失一个单元，所以实际使用的数组比CAP大1.
		elements = (E[]) new Object[capacity];// 新建数组，大小是8.
		front = rear = 0;// 初始状态
	}

	@Override
	public int getSize() {
		return (rear - front + capacity) % capacity;
	}

	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	// 数据元素e入队
	public void enqueue(E e) {
		if (getSize() == capacity - 1)
			expandSpace();
		elements[rear] = e;
		rear = (rear + 1) % capacity;
	}

	private void expandSpace() {
		@SuppressWarnings("unchecked")
		E[] a = (E[]) new Object[elements.length * 2];
		int i = front;
		int j = 0;
		while (i != rear) { // 将从front开始到rear前一个存储单元的元素复制到新数组
			a[j++] = elements[i];
			i = (i + 1) % capacity;
		}
		elements = a;
		capacity = elements.length;
		front = 0;
		rear = j; // 设置新的队首、队尾指针
	}

	// 队首元素出队
	public E dequeue() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("错误：队列为空");
		E obj = elements[front];
		elements[front] = null;
		front = (front + 1) % capacity;
		return obj;
	}

	// 取队首元素
	public E peek() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("错误：队列为空");
		return elements[front];
	}
}
