package com.state;

public interface State {
	
	public String getDimension();
	
	public State operate(Operation oper);
	
	public boolean isFinal();
	
	public boolean isDone();
}
