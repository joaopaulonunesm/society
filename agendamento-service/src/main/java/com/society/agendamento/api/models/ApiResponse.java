package com.society.agendamento.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class ApiResponse<T> {

	private T data;
	private List<ErroResponse> errors;

	public ApiResponse(T data){
		this.data = data;
	}

	public static ApiResponse comErros(List<ErroResponse> erros){
		ApiResponse response = new ApiResponse();
		response.setErrors(erros);
		return response;
	}
}
