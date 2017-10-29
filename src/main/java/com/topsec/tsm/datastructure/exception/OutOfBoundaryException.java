package com.topsec.tsm.datastructure.exception;

/**
 *
 * @author HuangXing
 *
 */
public class OutOfBoundaryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OutOfBoundaryException(String err) {
		super(err);
	}
	public OutOfBoundaryException(String err,Throwable t){
		super(err, t);
	}

}
