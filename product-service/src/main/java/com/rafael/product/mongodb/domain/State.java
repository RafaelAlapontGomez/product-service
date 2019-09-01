package com.rafael.product.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum State {
	Activo("Activo"),
	Inactivo("Inactivo");
	
	private String state;
	
	public static String getStateDesc(State estado) {
		for(State st: State.values()) {
			if (st.getState().equals(estado.getState())) {
				return st.getState();
			}
		}
		return null;
	}
	
	public static State getStateEnun(String estado) {
		for(State st: State.values()) {
			if (st.getState().equals(estado)) {
				return st;
			}
		}
		return null;
	}
}
