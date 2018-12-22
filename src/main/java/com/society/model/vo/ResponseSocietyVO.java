package com.society.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseSocietyVO {

	private Object data;
	private Object error;
	
	public ResponseSocietyVO withError(Object error) {
		this.error = error;
		return this;
	}
	
	public ResponseSocietyVO withData(Object data) {
		this.data = data;
		return this;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

}
