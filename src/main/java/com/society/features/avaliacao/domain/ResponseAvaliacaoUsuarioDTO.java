package com.society.features.avaliacao.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAvaliacaoUsuarioDTO {

	private List<AvaliacaoUsuario> avaliacoes;
	private Integer quantidadeAvaliacoes;
	private BigDecimal media;
}
