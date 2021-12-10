package com.society.gerenciamento.infrastructure.dataproviders.repository;

import com.society.gerenciamento.domain.enums.StatusSociety;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "society")
@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SocietyEntity {

	@Id
	@SequenceGenerator(name = "SOCIETYSEQ", sequenceName = "SOCIETY_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SOCIETYSEQ")
	private Long id;

	private String nome;
	private String nomeUrl;
	private String descricao;
	private BigDecimal valorHora;
	private String nomeResponsavel;
	private String celular;
	private String telefone;
	private String email;
	private String endereco;
	private String cep;
	private String observacao;
	private String urlFoto;
	private Long quantidadeCampos;
	private StatusSociety status;
}