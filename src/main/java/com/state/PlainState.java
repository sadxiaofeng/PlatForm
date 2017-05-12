package com.state;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlainState implements State, Serializable {

	private int code;

	private String dimension;

	private String phase;

	private boolean isFinal = false;

	private boolean isDone = false;
	private Map<Operation, State> children;

	public PlainState(String dimension, String phase, int code) {
		this.dimension = dimension;
		this.phase = phase;
		this.code = code;
		children = new HashMap<Operation, State>();
	}

	public PlainState(String dimension, String phase, int code, boolean isFinal, boolean isDone) {
		this(dimension, phase, code);
		this.isFinal = isFinal;
		this.isDone = isDone;
	}

	public int getCode() {
		return code;
	}

	public String getDimension() {
		return dimension;
	}

	public String getPhase() {
		return phase;
	}

	public PlainState addChild(Operation operate, State child) {
		if (children.containsKey(operate)) {
			throw new IllegalOperateException("child for operate " + operate + " is already exit");
		} else {
			children.put(operate, child);
		}
		return this;
	}

	public PlainState operate(Operation oper) {
		State next = children.get(oper);
		if (next == null) {
			throw new IllegalOperateException("no child for operate " + oper);
		} else {
			return (PlainState) next;
		}
	}

	@Override
	public boolean isFinal() {
		return isFinal;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlainState) {
			PlainState other = (PlainState) obj;
			if (this.code == other.code && this.dimension.equals(other.dimension) && this.phase.equals(other.phase)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDone() {
		return isDone;
	}

}
