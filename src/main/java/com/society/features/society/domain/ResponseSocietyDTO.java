package com.society.features.society.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseSocietyDTO {

	private Object data;
	private Object error;

	public static ResponseSocietyDTO withError(Object error) {
		return ResponseSocietyDTO.builder().data(error).build();
	}

	public static ResponseSocietyDTO withData(Object data) {
		return ResponseSocietyDTO.builder().data(data).build();
	}
}
