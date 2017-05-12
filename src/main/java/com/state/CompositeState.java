package com.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CompositeState implements State{
	
	private Map<String, State> subStates = new HashMap<String, State>();
	
	public void addSubState(State subState){
		subStates.put( subState.getDimension(), subState );
	}
	
	public State getSubState(String dimenstion){
		return subStates.get(dimenstion);
	}
	
	public boolean containSubState(State ss){
		return ss == subStates.get( ss.getDimension() );
	}
	
	@Override
	public State operate(Operation oper) {
		if( isSubStateOperation(oper) ) {
			subStateOperate( oper );
		} else {
			List<Operation> map = getSubStateOperationMap(oper);
			if( map!=null ) {
				map.forEach( e->subStateOperate(e));
			}
		}
		return this;
	}
	
	public void subStateOperate(Operation oper){
		subStates.put( oper.getDimension(), subStates.get(oper.getDimension()).operate(oper) );
	}
	
	public boolean isFinal(){
		return subStates.values().stream().allMatch( s->s.isFinal() );
	}
	
	@Override
	public boolean isDone() {
		return subStates.values().stream().allMatch( s->s.isDone() );
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append( getDimension() ).append(":{");
		subStates.values().forEach( ss-> {
			sb.append( ss.toString() ).append(",");
		} );
		sb.setCharAt( sb.length()-1, '}');
		return sb.toString();
	}
	
	protected abstract List<Operation> getSubStateOperationMap(Operation oper);
	
	private boolean isSubStateOperation(Operation oper){
		return !oper.getDimension().equals( getDimension() );
	}
}
