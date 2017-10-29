package com.topsec.tsm.datastructure.exception;

/**
 * 
 * @author HuangXing
 * 
 */

public class UnsupportedOperation extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UnsupportedOperation(String err) {
    super(err);
  }

  public UnsupportedOperation(String err, Throwable t) {
    super(err, t);
  }
}
