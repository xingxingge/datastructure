package com.topsec.tsm.datastructure.exception;

/**
 * 
 * @author HuangXing
 *
 */
public class InvalidNodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidNodeException(String err){
		super(err);
	}

	public InvalidNodeException(String err,Throwable t){
		super(err, t);
	}
}
