package com.society.infrastructure.repositories.society;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.society.infrastructure.repositories.login.Login;
import com.society.usecases.society.StatusSociety;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Society {

	@Id
	@SequenceGenerator(name = "SOCIETYSEQ", sequenceName = "SOCIETY_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SOCIETYSEQ")
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_login")
	private Login login;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String nomeUrl;
	private String descricao;
	@Column(nullable = false)
	private Double valorHora;
	@Column(nullable = false)
	private String nomeResponsavel;
	@Column(nullable = false)
	private Long celular;
	private Long telefone;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private Integer cep;
	private String observacao;
	@Column(nullable = false)
	private String urlFoto;
	private Long quantidadeJogos;
	@Column(nullable = false)
	private Integer quantidadeCampos;
	private StatusSociety statusSociety;

	public void setNomeUrl(String nomeUrl) {
		this.nomeUrl = nomeUrl.replaceAll(" ", "-").replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
				.replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u")
				.replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I")
				.replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C')
				.replace('ñ', 'n').replace('Ñ', 'N').toLowerCase();
	}
}