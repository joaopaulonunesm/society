package com.society.features.login.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoLogin {

	SOCIETY(1), USUARIO(2), MODERADOR(3);

	private Integer id;

	public static TipoLogin buscarPorId(Integer id) {
		for (TipoLogin status : values()) {
			if (status.getId() == id)
				return status;
		}
		return null;
	}

}
