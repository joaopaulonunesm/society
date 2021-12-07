package com.society.agendamento.infrastructure.dataproviders.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.society.agendamento.api.middlewares.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class ResponseApiDTO<T> implements Serializable {

	private static final long serialVersionUID = -8425765646924568026L;

	private T data;
	private List<ErrorResponse> errors;

	public ResponseApiDTO(T data){
		this.data = data;
	}

	public ResponseApiDTO(List<ErrorResponse> errors){
		this.errors = errors;
	}
}
