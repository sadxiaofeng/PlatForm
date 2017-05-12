package com.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于构建模式实现
 * @author felix.wu
 *
 */
public class StateBuilder{
	
	private static Map<String, Map<String,PlainState>> plainStateMap = new HashMap<String, Map<String,PlainState>>();
	
	private static Map<String, Class<? extends CompositeState>> compositeStateMap = new HashMap<String, Class<? extends CompositeState>>();
	
	static{
		//compositeStateMap.put("ORDER" , TaskState.class);
	}
	
	public static void registerPlainState(PlainState... states){
		for(PlainState state: states ) {
			Map<String,PlainState> subMap = plainStateMap.get(state.getDimension());
			if( subMap==null ) {
				subMap = new HashMap<String, PlainState>();
				plainStateMap.put( state.getDimension(), subMap );
			}
			
			PlainState s = subMap.get( state.getPhase() );
			if( s==null ) {
				subMap.put(state.getPhase(), state);
				
			} else {
				if( s!=state ) {
					throw new IllegalArgumentException( "State class already exit for " + state);
				}
			}
		}
	}
		
	public static State buildState(String state){
		char[] carray = state.toCharArray();
		int depth = 0;
		int mark = 0;
		for( int i=0; i<carray.length; i++ ) {
			if( carray[i]=='{' )
				depth++;
			else if( carray[i]=='}' ) 
				depth--;
			else if( depth==0 && carray[i]==':' ){
				mark = i;
				break;
			}
		}
		if( mark<=0 )
			throw new IllegalArgumentException( "paramter is illegal for " + state );
		
		String dimension = new String(carray, 0, mark);
		if( carray[mark+1]=='{' ) {
			Class<? extends CompositeState> csClass = compositeStateMap.get( dimension ); 
			CompositeState cs;
			try{
				cs = csClass.getConstructor().newInstance();
			}catch(Exception e){
				throw new IllegalArgumentException("paramter is illegal for " + state, e );
			}
			splitSubState( new String(carray, mark+2, carray.length-1-mark-2) ).forEach( subState-> cs.addSubState( buildState(subState) ) );
			return cs;
		} else {
			return plainStateMap.get( dimension ).get( new String(carray, mark+1, carray.length-mark-1) );
		}
	}
	
	private static List<String> splitSubState(String state){
		char[] carray = state.toCharArray();
		int depth = 0;
		int startMark = 0;
		List<String> result = new ArrayList<String>();
		for( int i=0; i<carray.length; i++ ) {
			if( carray[i]=='{' )
				depth++;
			else if( carray[i]=='}' ) 
				depth--;
			else if( depth==0 && carray[i]==',' ){
				result.add( new String(carray, startMark, i-startMark ) );
				startMark = ++i;
			} 
		}
		if( startMark<carray.length ) {
			result.add(new String(carray, startMark, carray.length-startMark) );
		}
		return result;
	}
}
