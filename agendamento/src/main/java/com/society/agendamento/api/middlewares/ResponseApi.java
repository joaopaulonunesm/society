package com.society.agendamento.api.middlewares;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class ResponseApi<T> {

	private T data;
	private List<ErrorResponse> errors;

	public ResponseApi(T data){
		this.data = data;
	}

	public ResponseApi(List<ErrorResponse> errors){
		this.errors = errors;
	}
}
