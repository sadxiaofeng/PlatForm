package com.state;


public class IllegalOperateException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public IllegalOperateException(String message){
		super(message);
	}

}
