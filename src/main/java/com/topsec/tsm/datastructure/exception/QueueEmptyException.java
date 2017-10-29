package com.topsec.tsm.datastructure.exception;

/**
 * 
 * @author HuangXing
 *
 */
public class QueueEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public QueueEmptyException(String err) {
		super(err);
	}

	public QueueEmptyException(String err,Throwable t){
		super(err, t);
	}
}
