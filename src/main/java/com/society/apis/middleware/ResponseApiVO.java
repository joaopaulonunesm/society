package com.society.apis.middleware;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class ResponseApiVO<T> {

	private T data;
	private List<ErrorResponse> errors;

	public ResponseApiVO(T data){
		this.data = data;
	}

	public ResponseApiVO(List<ErrorResponse> errors){
		this.errors = errors;
	}
}
