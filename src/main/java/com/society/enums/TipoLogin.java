package com.society.enums;

public enum TipoLogin {

	SOCIETY(1), USUARIO(2), MODERADOR(3);

	private Integer id;

	private TipoLogin(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public static TipoLogin buscarPorId(Integer id) {

		for (TipoLogin status : values()) {
			if (status.getId() == id)
				return status;
		}

		return null;
	}

}
