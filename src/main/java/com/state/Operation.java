package com.state;

import java.io.Serializable;

/**
 * 
 * @author felix.wu
 *
 */
public class Operation implements Serializable {
	private static final long serialVersionUID = 2369812454836561143L;

	private String dimension;

	private String name;

	public Operation(String dimension, String name) {
		this.dimension = dimension;
		this.name = name;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		return (dimension + ":" + name).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Operation) {
			Operation another = (Operation) obj;
			return dimension.equals(another.getDimension()) && name.equals(another.getName());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Operation [dimension=" + dimension + ", name=" + name + "]";
	}

}
