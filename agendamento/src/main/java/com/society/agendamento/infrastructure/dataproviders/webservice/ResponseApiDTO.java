package com.society.agendamento.infrastructure.dataproviders.webservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.society.agendamento.api.models.ErroResponse;
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
	private List<ErroResponse> errors;

	public ResponseApiDTO(T data){
		this.data = data;
	}

	public ResponseApiDTO(List<ErroResponse> errors){
		this.errors = errors;
	}
}
