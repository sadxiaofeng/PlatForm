package com.state;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskState implements Serializable {

	private static final long serialVersionUID = -5439937238200676814L;

	public static class OPERATION {
//		public static Operation ASSIGN = new Operation("EXE", "ASSIGN");
//		public static Operation START = new Operation("EXE", "START");
//		public static Operation LOSE = new Operation("EXE", "LOSE");
//		public static Operation GIVE_UP = new Operation("EXE", "GIVE_UP");
//		public static Operation INTERRUPT = new Operation("EXE", "INTERRUPT");
//		public static Operation CANCEL = new Operation("EXE", "CANCEL");
//		public static Operation DONE = new Operation("EXE", "DONE");
		public static Operation APPROVE = new Operation("EXE", "APPROVE");
		public static Operation REJECT = new Operation("EXE", "REJECT");
		public static Operation DISABLE = new Operation("EXE", "DISABLE");
		public static Operation RESUME = new Operation("EXE", "RESUME");
		public static Operation MODIFY = new Operation("EXE", "MODIFY");
	}
	public static final List<Operation> ALL_OPERATION = new ArrayList<Operation>();
	public static final List<PlainState> ALL_TASK_STATE = new ArrayList<PlainState>();

	public static PlainState submitted = new PlainState("DPMS","SUBMITTED",0);
	public static PlainState reject = new PlainState("DPMS","REJECT",1);
	public static PlainState approved = new PlainState("DPMS","APPROVED",2);
	public static PlainState disable = new PlainState("DPMS","DISABLE",3);
	public static PlainState approvmd = new PlainState("DPMS","APPROVMD",4);
//	public static PlainState assignPending = new PlainState("EXE", "ASSIGN_PENDING", 0);
//	public static PlainState runPending = new PlainState("EXE", "RUN_PENDING", 1);
//	public static PlainState running = new PlainState("EXE", "RUNNING", 2);
//	public static PlainState finished = new PlainState("EXE", "FINISHED", 3, true, true);
//	public static PlainState interrupted = new PlainState("EXE", "INTERRUPTED", 4, true, true);
//	public static PlainState cancelled = new PlainState("EXE", "CANCELLED", 5, true, false);
//	public static PlainState giveuped = new PlainState("EXE", "GIVEUPED", -1);
//	public static PlainState discarded = new PlainState("EXE", "DISCARDED", -2);

	static {
//		assignPending.addChild(OPERATION.ASSIGN, runPending);
//		runPending.addChild(OPERATION.START, running);
//		running.addChild(OPERATION.DONE, finished).addChild(OPERATION.LOSE, discarded)
//				.addChild(OPERATION.GIVE_UP, giveuped).addChild(OPERATION.INTERRUPT, interrupted);
//		discarded.addChild(OPERATION.ASSIGN, assignPending);
//		giveuped.addChild(OPERATION.ASSIGN, assignPending);
//		interrupted.addChild(OPERATION.CANCEL, cancelled);
		
		submitted.addChild(OPERATION.APPROVE, approved).addChild(OPERATION.REJECT, reject).addChild(OPERATION.MODIFY, submitted);
		approved.addChild(OPERATION.DISABLE, disable).addChild(OPERATION.MODIFY, approvmd);
		reject.addChild(OPERATION.MODIFY, submitted);
		disable.addChild(OPERATION.RESUME, approved);
		approvmd.addChild(OPERATION.MODIFY, approvmd).addChild(OPERATION.APPROVE, approved).addChild(OPERATION.REJECT, approved);
		
		ALL_TASK_STATE.add(submitted);
		ALL_TASK_STATE.add(reject);
		ALL_TASK_STATE.add(approved);
		ALL_TASK_STATE.add(disable);
		ALL_TASK_STATE.add(approvmd);

		ALL_OPERATION.add(OPERATION.APPROVE);
		ALL_OPERATION.add(OPERATION.REJECT);
		ALL_OPERATION.add(OPERATION.DISABLE);
		ALL_OPERATION.add(OPERATION.RESUME);
		ALL_OPERATION.add(OPERATION.MODIFY);
//		ALL_TASK_STATE.add(assignPending);
//		ALL_TASK_STATE.add(runPending);
//		ALL_TASK_STATE.add(running);
//		ALL_TASK_STATE.add(finished);
//		ALL_TASK_STATE.add(interrupted);
//		ALL_TASK_STATE.add(cancelled);
//		ALL_TASK_STATE.add(giveuped);
//		ALL_TASK_STATE.add(discarded);
//		StateBuilder.registerPlainState(assignPending, runPending, running, finished, interrupted, cancelled, giveuped,
//				discarded);
		
	}

	public static String[] getProcessingState() {
		List<String> l = new ArrayList<String>();
		ALL_TASK_STATE.stream().filter(s -> !s.isFinal()).map(s -> s.toString()).forEach(s -> l.add(s));

		String[] result = new String[l.size()];
		for (int i = 0; i < l.size(); i++)
			result[i] = l.get(i);
		return result;
	}
	
	public static PlainState getByPhase(String phase){
		for(PlainState state : ALL_TASK_STATE){
			if(state.getPhase().equals(phase)){
				return state;
			}
		}
		return null;
	}

	public static Operation findOption(String name){
		for(Operation option : ALL_OPERATION){
			if(option.getName().equals(name)){
				return option;
			}
		}
		return null;
	}

	public static String process(String status,String option){
		PlainState state = TaskState.getByPhase(status);
		Operation Option = findOption(option);
		if(state!=null && Option!=null){
			return state.operate(Option).getPhase();
		}
		return null;
	}
}