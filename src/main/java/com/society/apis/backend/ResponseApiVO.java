package com.society.apis.backend;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApiVO {

	private Object data;
	private Object error;
	
	public ResponseApiVO withError(Object error) {
		this.error = error;
		return this;
	}
	
	public ResponseApiVO withData(Object data) {
		this.data = data;
		return this;
	}
}
