package com.society.model.vo;

import java.math.BigDecimal;
import java.util.List;

import com.society.model.AvaliacaoUsuario;

public class ResponseAvaliacaoUsuarioVO {

	private List<AvaliacaoUsuario> avaliacoes;
	private Integer quantidadeAvaliacoes;
	private BigDecimal media;

	public List<AvaliacaoUsuario> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoUsuario> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Integer getQuantidadeAvaliacoes() {
		return quantidadeAvaliacoes;
	}

	public void setQuantidadeAvaliacoes(Integer quantidadeAvaliacoes) {
		this.quantidadeAvaliacoes = quantidadeAvaliacoes;
	}

	public BigDecimal getMedia() {
		return media;
	}

	public void setMedia(BigDecimal media) {
		this.media = media;
	}

}
